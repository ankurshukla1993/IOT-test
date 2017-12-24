package com.cooey.common.vo;

import io.realm.AddressRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class Address extends RealmObject implements AddressRealmProxyInterface {
    private String city;
    private String line1;
    private String line2;
    private String state;

    public String realmGet$city() {
        return this.city;
    }

    public String realmGet$line1() {
        return this.line1;
    }

    public String realmGet$line2() {
        return this.line2;
    }

    public String realmGet$state() {
        return this.state;
    }

    public void realmSet$city(String str) {
        this.city = str;
    }

    public void realmSet$line1(String str) {
        this.line1 = str;
    }

    public void realmSet$line2(String str) {
        this.line2 = str;
    }

    public void realmSet$state(String str) {
        this.state = str;
    }

    public Address() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getLine1() {
        return realmGet$line1();
    }

    public void setLine1(String line1) {
        realmSet$line1(line1);
    }

    public String getLine2() {
        return realmGet$line2();
    }

    public void setLine2(String line2) {
        realmSet$line2(line2);
    }

    public String getCity() {
        return realmGet$city();
    }

    public void setCity(String city) {
        realmSet$city(city);
    }

    public String getState() {
        return realmGet$state();
    }

    public void setState(String state) {
        realmSet$state(state);
    }
}
