package com.pekall.com.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应用使用日志
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUsageLogVo {

    private String logId;

    private String appPackage;

    private String appVersion;

    private String userAccount;

    private String deviceName;

    private Long startTime;

    private Long endTime;

    private Long flowBytes;
}
