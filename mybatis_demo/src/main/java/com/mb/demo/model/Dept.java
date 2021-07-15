package com.mb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {

    private Long id;

    private String deptName;

    // 非数据库字段
    List<Emp> emps;
}
