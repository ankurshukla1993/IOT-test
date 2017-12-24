package com.cooey.common.vo.careplan;

import io.realm.ActivityItemRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class ActivityItem extends RealmObject implements Serializable, ActivityItemRealmProxyInterface {
    @PrimaryKey
    private String id;
    private String name;
    private String parameters;
    private long timeStamp;

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$parameters() {
        return this.parameters;
    }

    public long realmGet$timeStamp() {
        return this.timeStamp;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$parameters(String str) {
        this.parameters = str;
    }

    public void realmSet$timeStamp(long j) {
        this.timeStamp = j;
    }

    public ActivityItem() {
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

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getParameters() {
        return realmGet$parameters();
    }

    public void setParameters(String parameters) {
        realmSet$parameters(parameters);
    }

    public long getTimeStamp() {
        return realmGet$timeStamp();
    }

    public void setTimeStamp(long timeStamp) {
        realmSet$timeStamp(timeStamp);
    }
}
