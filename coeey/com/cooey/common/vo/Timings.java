package com.cooey.common.vo;

import io.realm.RealmObject;
import io.realm.TimingsRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Timings extends RealmObject implements TimingsRealmProxyInterface {
    private String FRIDAY;
    private String MONDAY;
    private String SATURDAY;
    private String SUNDAY;
    private String THURSDAY;
    private String TUESDAY;
    private String WEDNESDAY;

    public String realmGet$FRIDAY() {
        return this.FRIDAY;
    }

    public String realmGet$MONDAY() {
        return this.MONDAY;
    }

    public String realmGet$SATURDAY() {
        return this.SATURDAY;
    }

    public String realmGet$SUNDAY() {
        return this.SUNDAY;
    }

    public String realmGet$THURSDAY() {
        return this.THURSDAY;
    }

    public String realmGet$TUESDAY() {
        return this.TUESDAY;
    }

    public String realmGet$WEDNESDAY() {
        return this.WEDNESDAY;
    }

    public void realmSet$FRIDAY(String str) {
        this.FRIDAY = str;
    }

    public void realmSet$MONDAY(String str) {
        this.MONDAY = str;
    }

    public void realmSet$SATURDAY(String str) {
        this.SATURDAY = str;
    }

    public void realmSet$SUNDAY(String str) {
        this.SUNDAY = str;
    }

    public void realmSet$THURSDAY(String str) {
        this.THURSDAY = str;
    }

    public void realmSet$TUESDAY(String str) {
        this.TUESDAY = str;
    }

    public void realmSet$WEDNESDAY(String str) {
        this.WEDNESDAY = str;
    }

    public Timings() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public void setWEDNESDAY(String wEDNESDAY) {
        realmSet$WEDNESDAY(wEDNESDAY);
    }

    public String getWEDNESDAY() {
        return realmGet$WEDNESDAY();
    }

    public void setMONDAY(String mONDAY) {
        realmSet$MONDAY(mONDAY);
    }

    public String getMONDAY() {
        return realmGet$MONDAY();
    }

    public void setTHURSDAY(String tHURSDAY) {
        realmSet$THURSDAY(tHURSDAY);
    }

    public String getTHURSDAY() {
        return realmGet$THURSDAY();
    }

    public void setSUNDAY(String sUNDAY) {
        realmSet$SUNDAY(sUNDAY);
    }

    public String getSUNDAY() {
        return realmGet$SUNDAY();
    }

    public void setTUESDAY(String tUESDAY) {
        realmSet$TUESDAY(tUESDAY);
    }

    public String getTUESDAY() {
        return realmGet$TUESDAY();
    }

    public void setFRIDAY(String fRIDAY) {
        realmSet$FRIDAY(fRIDAY);
    }

    public String getFRIDAY() {
        return realmGet$FRIDAY();
    }

    public void setSATURDAY(String sATURDAY) {
        realmSet$SATURDAY(sATURDAY);
    }

    public String getSATURDAY() {
        return realmGet$SATURDAY();
    }
}
