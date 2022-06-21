package org.example;

import com.google.common.base.Strings;
import com.google.gson.reflect.TypeToken;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.example.util.GsonUtils;
import org.example.vo.DtsRespVo;
import org.example.vo.NetHostVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySink extends AbstractSink implements Configurable {
    private static final Logger logger = LoggerFactory.getLogger(MySink.class);

    // Default Max bytes to dump
    public static final int DEFAULT_MAX_BYTE_DUMP = 1024;

    // Max number of bytes to be dumped
    private int maxBytesToLog = DEFAULT_MAX_BYTE_DUMP;

    public static final String MAX_BYTES_DUMP_KEY = "maxBytesToLog";

    private HttpClient client;
    private String directusApiToken;
    private String directusUrl;

    @Override
    public void configure(Context context) {
        this.maxBytesToLog = context.getInteger(MAX_BYTES_DUMP_KEY, DEFAULT_MAX_BYTE_DUMP);
    }

    @Override
    public void start() {
        // Initialize the connection to the external repository (e.g. HDFS) that
        // this Sink will forward Events to ..
        logger.info("nethost sink started");

        directusApiToken = System.getenv("DRTS_API_TOKEN");
        if (Strings.isNullOrEmpty(directusApiToken)) {
            directusApiToken = "137c991b-d36c-405e-8657-e4132858e705";
        }

        directusUrl = System.getenv("DRTS_URL");
        if (Strings.isNullOrEmpty(directusUrl)) {
            directusUrl = "http://localhost:8055";
        }

        Header header = new BasicHeader(HttpHeaders.AUTHORIZATION,
                "Bearer " + directusApiToken);
        List<Header> headers = new ArrayList<>();
        headers.add(header);

        int timeout = 30;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        client = HttpClients.custom()
                .setDefaultHeaders(headers)
                .setDefaultRequestConfig(config)
                .build();
    }

    @Override
    public void stop() {
        // Disconnect from the external respository and do any
        // additional cleanup (e.g. releasing resources or nulling-out
        // field values) ..
        logger.info("nethost sink stopped");
    }

    @Override
    public Status process() throws EventDeliveryException {
        Status status = Status.READY;

        // Start transaction
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        Event event = null;
        try {
            txn.begin();
            event = ch.take();

            byte[] eventBody = null;
            if (event != null) {
                eventBody = event.getBody();
            }

            if (eventBody != null && eventBody.length > 0) {
                final String line = new String(event.getBody());
                logger.info("Sending request : {}", line);
                if (!validLine(line)) {
                    logger.warn("ignore error line: {}", line);
                    txn.commit();
                    return Status.READY;
                }

                String tokens[] = line.split("  *");
                List<NetHostVo> netHostVos = searchNetHost(tokens[0]);
                if (netHostVos == null || netHostVos.isEmpty()) {
                    createNetHost(NetHostVo.builder()
                            .ipAddr(tokens[0])
                            .hwType(tokens[1])
                            .macAddr(tokens[2])
                            .iface(tokens[4])
                            .build());
                } else {
                    for (NetHostVo netHostVo : netHostVos) {
                        updateNetHost(netHostVo);
                    }
                }
            } else {
                status = Status.BACKOFF;
                logger.warn("Processed empty event");
            }

            txn.commit();
        } catch (Exception e) {
            txn.rollback();
            throw new EventDeliveryException("Failed to log event: " + event, e);
        } finally {
            txn.close();
        }
        return status;
    }

    void createNetHost(NetHostVo netHostVo) throws IOException {
        StringEntity requestEntity = new StringEntity(
                GsonUtils.toJson(netHostVo),
                ContentType.APPLICATION_JSON);

        HttpPost request = new HttpPost(directusUrl + "/items/NetHost");
        request.setEntity(requestEntity);

        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        logger.info("resp body: {}", json);
    }

    private List<NetHostVo> searchNetHost(String ip) throws IOException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(directusUrl + "/items/NetHost" + "?filter[ipAddr][_eq]=" + ip)
                .build();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

        DtsRespVo<NetHostVo> respVo = GsonUtils.gson.fromJson(json,
                new TypeToken<DtsRespVo<NetHostVo>>() {
                }.getType());
        logger.info("resp json: {}", GsonUtils.toJson(respVo));
        return respVo.getData();
    }

    private void updateNetHost(NetHostVo netHostVo) throws IOException {
        StringEntity requestEntity = new StringEntity(
                GsonUtils.toJson(netHostVo),
                ContentType.APPLICATION_JSON);

        HttpPatch patch = new HttpPatch(directusUrl + "/items/NetHost/" + netHostVo.getId());
        patch.setEntity(requestEntity);
        HttpResponse response = client.execute(patch);

        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        logger.info("resp body: {}", json);
    }

    public boolean validateMacAddr(String mac) {
        Pattern p = Pattern.compile("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$");
        Matcher m = p.matcher(mac);
        return m.find();
    }

    public boolean validateIpV4Addr(String ip) {
        Pattern p = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");
        Matcher m = p.matcher(ip);
        return m.find();
    }

    private boolean validLine(String line) {
        String tokens[] = line.split("  *");
        System.out.println(GsonUtils.toJson(tokens));
        if (tokens.length != 5 ||
                !validateMacAddr(tokens[2]) ||
                !validateIpV4Addr(tokens[0])) {
            return false;
        }

        return true;
    }
}