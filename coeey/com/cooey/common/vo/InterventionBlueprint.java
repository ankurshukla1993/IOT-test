package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class InterventionBlueprint implements Parcelable, Serializable {
    public static final Creator<InterventionBlueprint> CREATOR = new C09021();
    private String applicationId;
    private String careplanBlueprintId;
    private boolean caretakerNotificationEnabled;
    private long createdOn;
    private boolean guaridanNotificationEnabled;
    private String id;
    private String instructions;
    private String name;
    private String ownerId;
    private String parameters;
    private String parentId;
    private boolean patientNotificationEnabled;
    private String permissions;
    private Schedule schedule;
    private String tenantId;
    private String type;
    private long updatedOn;

    static class C09021 implements Creator<InterventionBlueprint> {
        C09021() {
        }

        public InterventionBlueprint createFromParcel(Parcel in) {
            return new InterventionBlueprint(in);
        }

        public InterventionBlueprint[] newArray(int size) {
            return new InterventionBlueprint[size];
        }
    }

    protected InterventionBlueprint(Parcel in) {
        this.createdOn = in.readLong();
        this.updatedOn = in.readLong();
        this.tenantId = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.parameters = in.readString();
        this.parentId = in.readString();
        this.careplanBlueprintId = in.readString();
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCareplanBlueprintId() {
        return this.careplanBlueprintId;
    }

    public void setCareplanBlueprintId(String careplanBlueprintId) {
        this.careplanBlueprintId = careplanBlueprintId;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPermissions() {
        return this.permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPermission() {
        return this.permissions;
    }

    public void setPermission(String permissions) {
        this.permissions = permissions;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isPatientNotificationEnabled() {
        return this.patientNotificationEnabled;
    }

    public void setPatientNotificationEnabled(boolean patientNotificationEnabled) {
        this.patientNotificationEnabled = patientNotificationEnabled;
    }

    public boolean isCaretakerNotificationEnabled() {
        return this.caretakerNotificationEnabled;
    }

    public void setCaretakerNotificationEnabled(boolean caretakerNotificationEnabled) {
        this.caretakerNotificationEnabled = caretakerNotificationEnabled;
    }

    public boolean isGuaridanNotificationEnabled() {
        return this.guaridanNotificationEnabled;
    }

    public void setGuaridanNotificationEnabled(boolean guaridanNotificationEnabled) {
        this.guaridanNotificationEnabled = guaridanNotificationEnabled;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.createdOn);
        dest.writeLong(this.updatedOn);
        dest.writeString(this.tenantId);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.parameters);
        dest.writeString(this.parentId);
        dest.writeString(this.careplanBlueprintId);
    }
}
