package com.pekall.com.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应用使用总量统计VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUsageAllStatsVo {

    /**
     * 访问总次数
     */
    Long totalCount;

    /**
     * 访问总流量
     */
    Long totalFlowBytes;

    /**
     * 访问总时长
     */
    Long totalDurationSecs;


    /**
     * 访问用户人数
     */
    Long totalUserCount;

    /**
     * 访问设备数
     */
    Long totalDeviceCount;
}
