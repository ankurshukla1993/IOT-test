package com.cooey.common.vo;

public class ChangeEvent {
    private long createdOn;
    private String eventId;
    private String guardianId;
    private long modifiedEndTime;
    private long modifiedStartTime;
    private String notes;
    private String tenantId;
    private long updatedOn;
    private String userId;

    public ChangeEvent(String userId, String guardianId, String tenantId, String eventId, String notes, long modifiedStartTime, long updatedOn, long createdOn, long modifiedEndTime) {
        this.userId = userId;
        this.guardianId = guardianId;
        this.tenantId = tenantId;
        this.eventId = eventId;
        this.notes = notes;
        this.modifiedStartTime = modifiedStartTime;
        this.modifiedEndTime = modifiedEndTime;
        this.updatedOn = updatedOn;
        this.createdOn = createdOn;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGuardianId() {
        return this.guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getModifiedStartTime() {
        return this.modifiedStartTime;
    }

    public void setModifiedStartTime(long modifiedStartTime) {
        this.modifiedStartTime = modifiedStartTime;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getModifiedEndTime() {
        return this.modifiedEndTime;
    }

    public void setModifiedEndTime(long modifiedEndTime) {
        this.modifiedEndTime = modifiedEndTime;
    }
}
