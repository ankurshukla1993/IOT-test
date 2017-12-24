package com.cooey.common.vo;

import java.util.List;

public class Content {
    private String applicationId;
    private int createdOn;
    private String id;
    private String name;
    private List<String> tags;
    private String tenantId;
    private String type;
    private int updatedOn;
    private String url;

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUpdatedOn(int updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getUpdatedOn() {
        return this.updatedOn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedOn() {
        return this.createdOn;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String toString() {
        return "Content{tenantId = '" + this.tenantId + '\'' + ",name = '" + this.name + '\'' + ",updatedOn = '" + this.updatedOn + '\'' + ",id = '" + this.id + '\'' + ",applicationId = '" + this.applicationId + '\'' + ",type = '" + this.type + '\'' + ",createdOn = '" + this.createdOn + '\'' + ",url = '" + this.url + '\'' + ",tags = '" + this.tags + '\'' + "}";
    }
}
