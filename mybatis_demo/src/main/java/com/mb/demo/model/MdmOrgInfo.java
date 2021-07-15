package com.mb.demo.model;

import java.util.Date;

public class MdmOrgInfo {
    private String id;

    private String name;

    private String abbreviation;

    private String orgCodeReal;

    private String description;

    private Integer opTenantId;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getOrgCodeReal() {
        return orgCodeReal;
    }

    public void setOrgCodeReal(String orgCodeReal) {
        this.orgCodeReal = orgCodeReal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOpTenantId() {
        return opTenantId;
    }

    public void setOpTenantId(Integer opTenantId) {
        this.opTenantId = opTenantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}