package com.cooey.common.vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.realm.ActionRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Action extends RealmObject implements ActionRealmProxyInterface {
    public boolean completed;
    public long completedOn;
    public long createdOn;
    @PrimaryKey
    public String id;
    public String interventionId;
    public long latitude;
    public long longitude;
    public String notes;
    public String parameters;
    public String patientId;
    public String patientName;
    private String postAction;
    public String resolutionId;
    public long scheduledOn;
    public String tenantId;
    private long timestamp;
    public String title;
    public String type;
    public long updatedOn;

    public boolean realmGet$completed() {
        return this.completed;
    }

    public long realmGet$completedOn() {
        return this.completedOn;
    }

    public long realmGet$createdOn() {
        return this.createdOn;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$interventionId() {
        return this.interventionId;
    }

    public long realmGet$latitude() {
        return this.latitude;
    }

    public long realmGet$longitude() {
        return this.longitude;
    }

    public String realmGet$notes() {
        return this.notes;
    }

    public String realmGet$parameters() {
        return this.parameters;
    }

    public String realmGet$patientId() {
        return this.patientId;
    }

    public String realmGet$patientName() {
        return this.patientName;
    }

    public String realmGet$postAction() {
        return this.postAction;
    }

    public String realmGet$resolutionId() {
        return this.resolutionId;
    }

    public long realmGet$scheduledOn() {
        return this.scheduledOn;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public String realmGet$title() {
        return this.title;
    }

    public String realmGet$type() {
        return this.type;
    }

    public long realmGet$updatedOn() {
        return this.updatedOn;
    }

    public void realmSet$completed(boolean z) {
        this.completed = z;
    }

    public void realmSet$completedOn(long j) {
        this.completedOn = j;
    }

    public void realmSet$createdOn(long j) {
        this.createdOn = j;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$interventionId(String str) {
        this.interventionId = str;
    }

    public void realmSet$latitude(long j) {
        this.latitude = j;
    }

    public void realmSet$longitude(long j) {
        this.longitude = j;
    }

    public void realmSet$notes(String str) {
        this.notes = str;
    }

    public void realmSet$parameters(String str) {
        this.parameters = str;
    }

    public void realmSet$patientId(String str) {
        this.patientId = str;
    }

    public void realmSet$patientName(String str) {
        this.patientName = str;
    }

    public void realmSet$postAction(String str) {
        this.postAction = str;
    }

    public void realmSet$resolutionId(String str) {
        this.resolutionId = str;
    }

    public void realmSet$scheduledOn(long j) {
        this.scheduledOn = j;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public void realmSet$title(String str) {
        this.title = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$updatedOn(long j) {
        this.updatedOn = j;
    }

    public Action() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long timestamp) {
        realmSet$timestamp(timestamp);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public long getCreatedOn() {
        return realmGet$createdOn();
    }

    public void setCreatedOn(long createdOn) {
        realmSet$createdOn(createdOn);
    }

    public long getUpdatedOn() {
        return realmGet$updatedOn();
    }

    public void setUpdatedOn(long updatedOn) {
        realmSet$updatedOn(updatedOn);
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getTitle() {
        return realmGet$title();
    }

    public void setTitle(String title) {
        realmSet$title(title);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public String getParameters() {
        return realmGet$parameters();
    }

    public void setParameters(String parameters) {
        realmSet$parameters(parameters);
    }

    public String getPatientId() {
        return realmGet$patientId();
    }

    public void setPatientId(String patientId) {
        realmSet$patientId(patientId);
    }

    public String getPatientName() {
        return realmGet$patientName();
    }

    public void setPatientName(String patientName) {
        realmSet$patientName(patientName);
    }

    public String getResolutionId() {
        return realmGet$resolutionId();
    }

    public void setResolutionId(String resolutionId) {
        realmSet$resolutionId(resolutionId);
    }

    public long getCompletedOn() {
        return realmGet$completedOn();
    }

    public void setCompletedOn(long completedOn) {
        realmSet$completedOn(completedOn);
    }

    public long getScheduledOn() {
        return realmGet$scheduledOn();
    }

    public void setScheduledOn(long scheduledOn) {
        realmSet$scheduledOn(scheduledOn);
    }

    public String getNotes() {
        return realmGet$notes();
    }

    public void setNotes(String notes) {
        realmSet$notes(notes);
    }

    public String getInterventionId() {
        return realmGet$interventionId();
    }

    public void setInterventionId(String interventionId) {
        realmSet$interventionId(interventionId);
    }

    public long getLatitude() {
        return realmGet$latitude();
    }

    public void setLatitude(long latitude) {
        realmSet$latitude(latitude);
    }

    public long getLongitude() {
        return realmGet$longitude();
    }

    public void setLongitude(long longitude) {
        realmSet$longitude(longitude);
    }

    public boolean isCompleted() {
        return realmGet$completed();
    }

    public void setCompleted(boolean completed) {
        realmSet$completed(completed);
    }

    public String getPostAction() {
        return realmGet$postAction();
    }

    public void setPostAction(String postAction) {
        realmSet$postAction(postAction);
    }

    public List<FeedBackInput> getFeedBackInputs() {
        try {
            return Arrays.asList((FeedBackInput[]) new Gson().fromJson(new Gson().toJson(((Map) new GsonBuilder().create().fromJson(realmGet$postAction(), new HashMap().getClass())).get("feedBackInput")), FeedBackInput[].class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
