package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.VitalFeatureRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class VitalFeature extends RealmObject implements VitalFeatureRealmProxyInterface {
    private String id;
    private Limits limits;
    private String tenantId;
    private String type;

    public String realmGet$id() {
        return this.id;
    }

    public Limits realmGet$limits() {
        return this.limits;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$limits(Limits limits) {
        this.limits = limits;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public VitalFeature() {
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

    public String getType() {
        return realmGet$type();
    }

    public void setType(VitalType type) {
        realmSet$type(type.toString());
    }

    public Limits getLimits() {
        return realmGet$limits();
    }

    public void setLimits(Limits limits) {
        realmSet$limits(limits);
    }

    public String toString() {
        return "VitalFeature{id='" + realmGet$id() + '\'' + ", type=" + realmGet$type() + ", limits=" + realmGet$limits() + '}';
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }
}
