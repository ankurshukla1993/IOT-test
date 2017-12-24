package com.cooey.common.vo.careplan;

import io.realm.CourseRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Course extends RealmObject implements Serializable, CourseRealmProxyInterface {
    private String assignedBy;
    private boolean completed;
    private String description;
    private long endTime;
    @PrimaryKey
    private String id;
    private String name;
    private long startTime;
    private String tenantId;

    public String realmGet$assignedBy() {
        return this.assignedBy;
    }

    public boolean realmGet$completed() {
        return this.completed;
    }

    public String realmGet$description() {
        return this.description;
    }

    public long realmGet$endTime() {
        return this.endTime;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public long realmGet$startTime() {
        return this.startTime;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public void realmSet$assignedBy(String str) {
        this.assignedBy = str;
    }

    public void realmSet$completed(boolean z) {
        this.completed = z;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$endTime(long j) {
        this.endTime = j;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$startTime(long j) {
        this.startTime = j;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public Course() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public long getStartTime() {
        return realmGet$startTime();
    }

    public void setStartTime(long startTime) {
        realmSet$startTime(startTime);
    }

    public long getEndTime() {
        return realmGet$endTime();
    }

    public void setEndTime(long endTime) {
        realmSet$endTime(endTime);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String description) {
        realmSet$description(description);
    }

    public boolean isCompleted() {
        return realmGet$completed();
    }

    public void setCompleted(boolean completed) {
        realmSet$completed(completed);
    }

    public String getAssignedBy() {
        return realmGet$assignedBy();
    }

    public void setAssignedBy(String assignedBy) {
        realmSet$assignedBy(assignedBy);
    }

    public String toString() {
        return "Course{id='" + realmGet$id() + '\'' + ", tenantId='" + realmGet$tenantId() + '\'' + ", name='" + realmGet$name() + '\'' + ", startTime=" + realmGet$startTime() + ", endTime=" + realmGet$endTime() + ", description='" + realmGet$description() + '\'' + ", completed=" + realmGet$completed() + ", assignedBy='" + realmGet$assignedBy() + '\'' + '}';
    }
}
