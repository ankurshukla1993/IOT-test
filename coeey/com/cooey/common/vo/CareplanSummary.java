package com.cooey.common.vo;

import io.realm.CareplanSummaryRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class CareplanSummary extends RealmObject implements CareplanSummaryRealmProxyInterface {
    private String itemType;
    private String message;
    private double rateIndicator;

    public String realmGet$itemType() {
        return this.itemType;
    }

    public String realmGet$message() {
        return this.message;
    }

    public double realmGet$rateIndicator() {
        return this.rateIndicator;
    }

    public void realmSet$itemType(String str) {
        this.itemType = str;
    }

    public void realmSet$message(String str) {
        this.message = str;
    }

    public void realmSet$rateIndicator(double d) {
        this.rateIndicator = d;
    }

    public CareplanSummary() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getMessage() {
        return realmGet$message();
    }

    public void setMessage(String message) {
        realmSet$message(message);
    }

    public double getRateIndicator() {
        return realmGet$rateIndicator();
    }

    public void setRateIndicator(double rateIndicator) {
        realmSet$rateIndicator(rateIndicator);
    }

    public String getItemType() {
        return realmGet$itemType();
    }

    public void setItemType(String itemType) {
        realmSet$itemType(itemType);
    }
}
