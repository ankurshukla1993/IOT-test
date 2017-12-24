package com.cooey.common.vo.careplan;

import io.realm.ActivityFeatureRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class ActivityFeature extends RealmObject implements ActivityFeatureRealmProxyInterface {
    private String id;
    private String name;
    private String parameters;

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$parameters() {
        return this.parameters;
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

    public ActivityFeature() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getActivityId() {
        return realmGet$id();
    }

    public void setActivityId(String id) {
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
}
