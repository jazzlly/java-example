package com.mb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {
    // 如果开启了二级缓存，需要实现序列化接口

    private Long id;

    private Long deptId;

    private String username;

    private Timestamp createTime;

    // 关联查询部分实体
    private String deptName;

    // 关联查询整个实体， 使用嵌套结果
    // 多对一查询
    private Dept dept;
}
