package org.example;

import com.google.common.base.Strings;
import com.google.gson.reflect.TypeToken;
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
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MySinkTest {
    private static final Logger logger = LoggerFactory.getLogger(MySink.class);

    HttpClient client;
    String directusApiToken;
    String directusUrl;

    @Before
    public void setUp() {
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

    @Test
    public void createNetHosts() throws IOException {
        StringEntity requestEntity = new StringEntity(
                GsonUtils.toJson(NetHostVo.builder()
                        .ipAddr("192.168.11.32")
                        .hwType("ether")
                        .iface("eth0")
                        .macAddr("74955e88-ae0f-41b5-84b9-52b6f85b12dc")
                        .status("draft")
                        .build()),
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

    @Test
    public void getAllNetHosts() throws IOException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(directusUrl + "/items/NetHost")
                .build();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // logger.info("resp body: {}", json);

        DtsRespVo<NetHostVo> respVo = GsonUtils.gson.fromJson(json,
                new TypeToken<DtsRespVo<NetHostVo>>() {}.getType());
        logger.info("resp json: {}", GsonUtils.toJson(respVo));
    }

    @Test
    public void searchNetHost() throws IOException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(directusUrl + "/items/NetHost" + "?filter[ipAddr][_eq]=192.168.11.32")
                .build();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        // logger.info("resp body: {}", json);

        DtsRespVo<NetHostVo> respVo = GsonUtils.gson.fromJson(json,
                new TypeToken<DtsRespVo<NetHostVo>>() {}.getType());
        logger.info("resp json: {}", GsonUtils.toJson(respVo));
    }

    @Test
    public void updateNetHost() throws IOException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(directusUrl + "/items/NetHost" + "?filter[ipAddr][_eq]=192.168.11.32")
                .build();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);

        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

        DtsRespVo<NetHostVo> respVo = GsonUtils.gson.fromJson(json,
                new TypeToken<DtsRespVo<NetHostVo>>() {}.getType());
        logger.info("resp json: {}", GsonUtils.toJson(respVo));

        for (NetHostVo netHostVo : respVo.getData()) {
            netHostVo.setHwType("haha");

            StringEntity requestEntity = new StringEntity(
                    GsonUtils.toJson(netHostVo),
                    ContentType.APPLICATION_JSON);

            HttpPatch patch = new HttpPatch(directusUrl + "/items/NetHost/" + netHostVo.getId());
            patch.setEntity(requestEntity);
            response = client.execute(patch);

            statusCode = response.getStatusLine().getStatusCode();
            logger.info("status code: {}", statusCode);

            entity = response.getEntity();
            json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            logger.info("resp body: {}", json);
        }
    }


    @Test
    public void testVo() {
        DtsRespVo<NetHostVo> vo = new DtsRespVo();
        List<NetHostVo> netHostVos = new ArrayList<>();
        netHostVos.add(NetHostVo.builder()
                        .id(10)
                        .ipAddr("192.168.11.32")
                        .hwType("ether")
                        .iface("eth0")
                        .macAddr("74955e88-ae0f-41b5-84b9-52b6f85b12dc")
                        .status("draft")
                .build());
        vo.setData(netHostVos);

        System.out.println(GsonUtils.toJson(vo));

        Type listOfMyClassObject = new TypeToken<DtsRespVo<NetHostVo>>() {}.getType();

    }
}