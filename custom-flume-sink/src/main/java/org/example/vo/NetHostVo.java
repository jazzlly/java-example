package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetHostVo {
    Integer id;
    String status;
    String ipAddr;
    String macAddr;
    String hwType;
    String iface;
}
