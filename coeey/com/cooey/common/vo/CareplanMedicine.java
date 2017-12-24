package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CareplanMedicine {
    @SerializedName("addedOn")
    private String addedOn;
    @SerializedName("applicationId")
    private String applicationId;
    @SerializedName("createdOn")
    private long createdOn;
    @SerializedName("description")
    private String description;
    @SerializedName("medicineId")
    private String medicineId;
    @SerializedName("name")
    private String name;
    @SerializedName("notes")
    private String notes;
    @SerializedName("reminders")
    private List<String> reminders;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("updatedOn")
    private long updatedOn;
    @SerializedName("userId")
    private String userId;

    public void setReminders(List<String> reminders) {
        this.reminders = reminders;
    }

    public List<String> getReminders() {
        return this.reminders;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineId() {
        return this.medicineId;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = (long) createdOn;
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getAddedOn() {
        return this.addedOn;
    }

    public String toString() {
        return "CareplanMedicine{reminders = '" + this.reminders + '\'' + ",notes = '" + this.notes + '\'' + ",medicineId = '" + this.medicineId + '\'' + ",tenantId = '" + this.tenantId + '\'' + ",name = '" + this.name + '\'' + ",description = '" + this.description + '\'' + ",updatedOn = '" + this.updatedOn + '\'' + ",applicationId = '" + this.applicationId + '\'' + ",createdOn = '" + this.createdOn + '\'' + ",userId = '" + this.userId + '\'' + ",addedOn = '" + this.addedOn + '\'' + "}";
    }
}
