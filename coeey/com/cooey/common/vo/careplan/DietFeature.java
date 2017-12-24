package com.cooey.common.vo.careplan;

import io.realm.DietFeatureRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class DietFeature extends RealmObject implements DietFeatureRealmProxyInterface {
    private String id;
    private String tenantId;

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public DietFeature() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }
}
