package org.example;

import org.example.util.GsonUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

    public boolean validateMac(String mac) {
        Pattern p = Pattern.compile("^([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$");
        Matcher m = p.matcher(mac);
        return m.find();
    }

    public boolean validateIpV4Addr(String ip) {
        Pattern p = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");
        Matcher m = p.matcher(ip);
        return m.find();
    }

    /*
    192.168.10.68            ether   90:2b:34:da:95:26   C                     eth0
     */
    @Test
    public void smoke() {
        final String line = "192.168.10.68            ether   90:2b:34:da:95:26   C                     eth0";
        String tokens[] = line.split("  *");
        System.out.println(GsonUtils.toJson(tokens));
        if (tokens.length != 5) {
            return;
        }

        if (!validateMac(tokens[2])) {
            return;
        }

        if (!validateIpV4Addr(tokens[0])) {
            return;
        }

        System.out.println("test done!");
    }
}
