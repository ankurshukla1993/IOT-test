package com.cooey.common.vo;

import android.os.Parcel;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class MedicineSearch implements SearchSuggestion {
    private Object applicationId;
    private Object components;
    private int createdOn;
    private Object description;
    private String id;
    private String name;
    private String source;
    private Object tenantId;
    private int updatedOn;

    public void setComponents(Object components) {
        this.components = components;
    }

    public Object getComponents() {
        return this.components;
    }

    public void setTenantId(Object tenantId) {
        this.tenantId = tenantId;
    }

    public Object getTenantId() {
        return this.tenantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return this.description;
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

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }

    public void setApplicationId(Object applicationId) {
        this.applicationId = applicationId;
    }

    public Object getApplicationId() {
        return this.applicationId;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedOn() {
        return this.createdOn;
    }

    public String getBody() {
        return this.name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }
}
