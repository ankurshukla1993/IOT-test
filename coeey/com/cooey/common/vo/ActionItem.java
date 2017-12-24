package com.cooey.common.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.facebook.appevents.AppEventsConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "actionItems")
public class ActionItem implements ListItem {
    @SerializedName("active")
    private String active;
    @SerializedName("applicationId")
    private String applicationId;
    @SerializedName("archived")
    private String archived;
    @SerializedName("assignerId")
    private String assignerId;
    @SerializedName("completed")
    @Expose
    private boolean completed;
    @SerializedName("completedBy")
    private String completedBy;
    @SerializedName("completedOn")
    @Expose
    private long completedOn;
    @SerializedName("contextId")
    private String contextId;
    @SerializedName("contextType")
    private String contextType;
    @SerializedName("createdOn")
    @Expose
    private long createdOn;
    @SerializedName("externalId")
    private String externalId;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("interventionId")
    private String interventionId;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("notes")
    private String notes;
    @SerializedName("ownerId")
    private String ownerId;
    @SerializedName("parameters")
    @Expose
    private String parameters;
    @SerializedName("parentType")
    private String parentType;
    @SerializedName("patientId")
    @Expose
    private String patientId;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("permissions")
    private String permissions;
    @SerializedName("postAction")
    private String postAction;
    @SerializedName("scheduledOn")
    @Expose
    private long scheduledOn;
    @SerializedName("tenantId")
    @Expose
    private String tenantId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("updatedOn")
    @Expose
    private long updatedOn;
    @SerializedName("visitId")
    private String visitId;

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getParameters() {
        return this.parameters;
    }

    public long getScheduledOn() {
        return this.scheduledOn;
    }

    public void setScheduledOn(long scheduledOn) {
        this.scheduledOn = scheduledOn;
    }

    public long getCompletedOn() {
        return this.completedOn;
    }

    public void setCompletedOn(long completedOn) {
        this.completedOn = completedOn;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPermissions() {
        return this.permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPostAction() {
        return this.postAction;
    }

    public void setPostAction(String postAction) {
        this.postAction = postAction;
    }

    public String getParentType() {
        return this.parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getContextType() {
        return this.contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getContextId() {
        return this.contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getInterventionId() {
        return this.interventionId;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public String getCompletedBy() {
        return this.completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getVisitId() {
        return this.visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getAssignerId() {
        return this.assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getArchived() {
        return this.archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public int getListItemType() {
        return 2;
    }

    public Map<String, String> getParameterMap() {
        return (Map) new GsonBuilder().create().fromJson(this.parameters, new HashMap().getClass());
    }

    public void setParameterMap(String key, String value) {
        Map<String, String> map = new HashMap();
        Gson gson = new GsonBuilder().create();
        map = (Map) gson.fromJson(this.parameters, map.getClass());
        map.put(key, value);
        setParameters(gson.toJson(map));
    }

    public boolean getPermissionFromType(int type, int permission) {
        boolean value = true;
        String caretakerBin = Integer.toBinaryString(Integer.parseInt("" + getPermissions().toCharArray()[type]));
        while (caretakerBin.length() < 3) {
            caretakerBin = AppEventsConstants.EVENT_PARAM_VALUE_NO + caretakerBin;
        }
        if (permission == 4) {
            if (Integer.parseInt("" + caretakerBin.charAt(0)) != 1) {
                value = false;
            }
            return value;
        } else if (permission == 5) {
            if (Integer.parseInt("" + caretakerBin.charAt(1)) != 1) {
                value = false;
            }
            return value;
        } else {
            if (Integer.parseInt("" + caretakerBin.charAt(1)) != 1) {
                value = false;
            }
            return value;
        }
    }
}
