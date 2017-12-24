package com.cooey.common.vo.careplan;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;
import io.realm.CareplanRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class Careplan extends RealmObject implements Parcelable, CareplanRealmProxyInterface {
    public static final Creator<Careplan> CREATOR = new 1();
    @SerializedName("assessment")
    private Assessment assessment;
    @SerializedName("assignedOn")
    private long assignedOn;
    @SerializedName("assignerId")
    private String assignerId;
    @SerializedName("beginTime")
    private long beginTime;
    @SerializedName("createdOn")
    private long createdOn;
    @SerializedName("description")
    private String description;
    @SerializedName("diagnosis")
    private Diagnosis diagnosis;
    @SerializedName("enabled")
    private boolean enabled;
    @SerializedName("endTime")
    private long endTime;
    @SerializedName("evaluation")
    private String evaluation;
    @SerializedName("goal")
    private String goal;
    @SerializedName("history")
    private String history;
    @SerializedName("id")
    @PrimaryKey
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("numOfDays")
    private int numOfDays;
    @SerializedName("parentId")
    private String parentId;
    @SerializedName("patientId")
    private String patientId;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("updatedOn")
    private long updatedOn;

    public Assessment realmGet$assessment() {
        return this.assessment;
    }

    public long realmGet$assignedOn() {
        return this.assignedOn;
    }

    public String realmGet$assignerId() {
        return this.assignerId;
    }

    public long realmGet$beginTime() {
        return this.beginTime;
    }

    public long realmGet$createdOn() {
        return this.createdOn;
    }

    public String realmGet$description() {
        return this.description;
    }

    public Diagnosis realmGet$diagnosis() {
        return this.diagnosis;
    }

    public boolean realmGet$enabled() {
        return this.enabled;
    }

    public long realmGet$endTime() {
        return this.endTime;
    }

    public String realmGet$evaluation() {
        return this.evaluation;
    }

    public String realmGet$goal() {
        return this.goal;
    }

    public String realmGet$history() {
        return this.history;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public int realmGet$numOfDays() {
        return this.numOfDays;
    }

    public String realmGet$parentId() {
        return this.parentId;
    }

    public String realmGet$patientId() {
        return this.patientId;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public long realmGet$updatedOn() {
        return this.updatedOn;
    }

    public void realmSet$assessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public void realmSet$assignedOn(long j) {
        this.assignedOn = j;
    }

    public void realmSet$assignerId(String str) {
        this.assignerId = str;
    }

    public void realmSet$beginTime(long j) {
        this.beginTime = j;
    }

    public void realmSet$createdOn(long j) {
        this.createdOn = j;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$diagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void realmSet$enabled(boolean z) {
        this.enabled = z;
    }

    public void realmSet$endTime(long j) {
        this.endTime = j;
    }

    public void realmSet$evaluation(String str) {
        this.evaluation = str;
    }

    public void realmSet$goal(String str) {
        this.goal = str;
    }

    public void realmSet$history(String str) {
        this.history = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$numOfDays(int i) {
        this.numOfDays = i;
    }

    public void realmSet$parentId(String str) {
        this.parentId = str;
    }

    public void realmSet$patientId(String str) {
        this.patientId = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$updatedOn(long j) {
        this.updatedOn = j;
    }

    protected Careplan(Parcel in) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$goal(in.readString());
        realmSet$patientId(in.readString());
        realmSet$description(in.readString());
        realmSet$assignedOn(in.readLong());
        realmSet$updatedOn(in.readLong());
        realmSet$history(in.readString());
        realmSet$createdOn(in.readLong());
        realmSet$parentId(in.readString());
        realmSet$enabled(in.readByte() != (byte) 0);
        realmSet$evaluation(in.readString());
        realmSet$numOfDays(in.readInt());
        realmSet$tenantId(in.readString());
        realmSet$name(in.readString());
        realmSet$id(in.readString());
        realmSet$beginTime(in.readLong());
        realmSet$endTime(in.readLong());
        realmSet$assignerId(in.readString());
    }

    public Careplan() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public void setGoal(String goal) {
        realmSet$goal(goal);
    }

    public String getGoal() {
        return realmGet$goal();
    }

    public void setPatientId(String patientId) {
        realmSet$patientId(patientId);
    }

    public String getPatientId() {
        return realmGet$patientId();
    }

    public void setDescription(String description) {
        realmSet$description(description);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        realmSet$diagnosis(diagnosis);
    }

    public Diagnosis getDiagnosis() {
        return realmGet$diagnosis();
    }

    public void setAssignedOn(int assignedOn) {
        realmSet$assignedOn((long) assignedOn);
    }

    public long getAssignedOn() {
        return realmGet$assignedOn();
    }

    public void setUpdatedOn(long updatedOn) {
        realmSet$updatedOn(updatedOn);
    }

    public long getUpdatedOn() {
        return realmGet$updatedOn();
    }

    public void setHistory(String history) {
        realmSet$history(history);
    }

    public String getHistory() {
        return realmGet$history();
    }

    public void setCreatedOn(long createdOn) {
        realmSet$createdOn(createdOn);
    }

    public long getCreatedOn() {
        return realmGet$createdOn();
    }

    public void setParentId(String parentId) {
        realmSet$parentId(parentId);
    }

    public String getParentId() {
        return realmGet$parentId();
    }

    public void setEnabled(boolean enabled) {
        realmSet$enabled(enabled);
    }

    public boolean isEnabled() {
        return realmGet$enabled();
    }

    public void setEvaluation(String evaluation) {
        realmSet$evaluation(evaluation);
    }

    public String getEvaluation() {
        return realmGet$evaluation();
    }

    public void setNumOfDays(int numOfDays) {
        realmSet$numOfDays(numOfDays);
    }

    public int getNumOfDays() {
        return realmGet$numOfDays();
    }

    public void setAssessment(Assessment assessment) {
        realmSet$assessment(assessment);
    }

    public Assessment getAssessment() {
        return realmGet$assessment();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setBeginTime(int beginTime) {
        realmSet$beginTime((long) beginTime);
    }

    public long getBeginTime() {
        return realmGet$beginTime();
    }

    public void setEndTime(long endTime) {
        realmSet$endTime(endTime);
    }

    public long getEndTime() {
        return realmGet$endTime();
    }

    public void setAssignerId(String assignerId) {
        realmSet$assignerId(assignerId);
    }

    public String getAssignerId() {
        return realmGet$assignerId();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(realmGet$goal());
        dest.writeString(realmGet$patientId());
        dest.writeString(realmGet$description());
        dest.writeLong(realmGet$assignedOn());
        dest.writeLong(realmGet$updatedOn());
        dest.writeString(realmGet$history());
        dest.writeLong(realmGet$createdOn());
        dest.writeString(realmGet$parentId());
        dest.writeByte((byte) (realmGet$enabled() ? 1 : 0));
        dest.writeString(realmGet$evaluation());
        dest.writeInt(realmGet$numOfDays());
        dest.writeString(realmGet$tenantId());
        dest.writeString(realmGet$name());
        dest.writeString(realmGet$id());
        dest.writeLong(realmGet$beginTime());
        dest.writeLong(realmGet$endTime());
        dest.writeString(realmGet$assignerId());
    }
}
