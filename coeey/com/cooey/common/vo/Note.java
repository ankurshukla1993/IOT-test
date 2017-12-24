package com.cooey.common.vo;

import io.realm.NoteRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class Note extends RealmObject implements NoteRealmProxyInterface {
    private boolean active;
    private String applicationId;
    private boolean archived;
    private String content;
    private long createdOn;
    @PrimaryKey
    private String id;
    private String tenantId;
    private String type;
    private long updatedOn;
    private String userId;

    public boolean realmGet$active() {
        return this.active;
    }

    public String realmGet$applicationId() {
        return this.applicationId;
    }

    public boolean realmGet$archived() {
        return this.archived;
    }

    public String realmGet$content() {
        return this.content;
    }

    public long realmGet$createdOn() {
        return this.createdOn;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public long realmGet$updatedOn() {
        return this.updatedOn;
    }

    public String realmGet$userId() {
        return this.userId;
    }

    public void realmSet$active(boolean z) {
        this.active = z;
    }

    public void realmSet$applicationId(String str) {
        this.applicationId = str;
    }

    public void realmSet$archived(boolean z) {
        this.archived = z;
    }

    public void realmSet$content(String str) {
        this.content = str;
    }

    public void realmSet$createdOn(long j) {
        this.createdOn = j;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$updatedOn(long j) {
        this.updatedOn = j;
    }

    public void realmSet$userId(String str) {
        this.userId = str;
    }

    public Note() {
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

    public String getContents() {
        return realmGet$content();
    }

    public void setContents(String contents) {
        realmSet$content(contents);
    }

    public String getUserId() {
        return realmGet$userId();
    }

    public void setUserId(String userId) {
        realmSet$userId(userId);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public String getApplicationId() {
        return realmGet$applicationId();
    }

    public void setApplicationId(String applicationId) {
        realmSet$applicationId(applicationId);
    }

    public boolean isArchived() {
        return realmGet$archived();
    }

    public void setArchived(boolean archived) {
        realmSet$archived(archived);
    }

    public boolean isActive() {
        return realmGet$active();
    }

    public void setActive(boolean active) {
        realmSet$active(active);
    }
}
