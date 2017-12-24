package com.cooey.common.vo.careplan;

import io.realm.LimitsRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Limits extends RealmObject implements Serializable, LimitsRealmProxyInterface {
    private int higherLimit;
    private int lowerLimit;
    private String tenantId;

    public int realmGet$higherLimit() {
        return this.higherLimit;
    }

    public int realmGet$lowerLimit() {
        return this.lowerLimit;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public void realmSet$higherLimit(int i) {
        this.higherLimit = i;
    }

    public void realmSet$lowerLimit(int i) {
        this.lowerLimit = i;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public Limits() {
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

    public int getLowerLimit() {
        return realmGet$lowerLimit();
    }

    public void setLowerLimit(int lowerLimit) {
        realmSet$lowerLimit(lowerLimit);
    }

    public int getHigherLimit() {
        return realmGet$higherLimit();
    }

    public void setHigherLimit(int higherLimit) {
        realmSet$higherLimit(higherLimit);
    }
}
