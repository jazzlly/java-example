package com.pekall.com.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 应用使用日志
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUsageLogVo {

    private static Random random = new Random();

    private String logId;

    private String appPackage;

    private String appVersion;

    private String userAccount;

    private String deviceName;

    private Long startTime;

    private Long endTime;

    private Long flowBytes;

    public static AppUsageLogVo buildRandomVo() {
        return AppUsageLogVo.builder()
                .logId(UUID.randomUUID().toString())
                .appPackage("com.pkg." + random.nextInt(5))
                .appVersion("1.2." + random.nextInt(3))
                .userAccount("user" + random.nextInt(8))
                .deviceName("device" + random.nextInt(10))
                .startTime(System.currentTimeMillis() -
                        random.nextInt((int) TimeUnit.MINUTES.toMillis(1)))
                .endTime(System.currentTimeMillis())
                .flowBytes((long)random.nextInt(5000))
                .build();
    }
}
