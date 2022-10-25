package com.pekall.com.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogMenuDict {

    private static Random random = new Random();

    private static List<String> codeList = new ArrayList<>(Arrays.asList("network", "user", "device", "org", "app"));

    private Long id;

    private Integer seq;

    private String name;

    private String code;

    public static LogMenuDict foo() {

        return builder()
                .id(random.nextLong())
                .seq(random.nextInt())
                .name("menu-" + UUID.randomUUID())
                .code(codeList.get(random.nextInt(codeList.size())))
                .build();
    }
}
