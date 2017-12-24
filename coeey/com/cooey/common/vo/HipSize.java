package com.cooey.common.vo;

import io.realm.HipSizeRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class HipSize extends RealmObject implements HipSizeRealmProxyInterface {
    private String unit;
    private String value;

    public String realmGet$unit() {
        return this.unit;
    }

    public String realmGet$value() {
        return this.value;
    }

    public void realmSet$unit(String str) {
        this.unit = str;
    }

    public void realmSet$value(String str) {
        this.value = str;
    }

    public HipSize(String value, String unit) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$value(value);
        realmSet$unit(unit);
    }

    public HipSize() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getValue() {
        return realmGet$value();
    }

    public void setValue(String value) {
        realmSet$value(value);
    }

    public String getUnit() {
        return realmGet$unit();
    }

    public void setUnit(String unit) {
        realmSet$unit(unit);
    }
}
