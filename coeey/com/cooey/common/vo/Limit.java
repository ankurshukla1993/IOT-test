package com.cooey.common.vo;

import io.realm.LimitRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Limit extends RealmObject implements Serializable, LimitRealmProxyInterface {
    private String higherLimit;
    private String limitType;
    private String lowerLimit;

    public String realmGet$higherLimit() {
        return this.higherLimit;
    }

    public String realmGet$limitType() {
        return this.limitType;
    }

    public String realmGet$lowerLimit() {
        return this.lowerLimit;
    }

    public void realmSet$higherLimit(String str) {
        this.higherLimit = str;
    }

    public void realmSet$limitType(String str) {
        this.limitType = str;
    }

    public void realmSet$lowerLimit(String str) {
        this.lowerLimit = str;
    }

    public Limit(String lowerLimit, String higherLimit, String limitType) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$lowerLimit(lowerLimit);
        realmSet$higherLimit(higherLimit);
        realmSet$limitType(limitType);
    }

    public Limit() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getLowerLimit() {
        return realmGet$lowerLimit();
    }

    public void setLowerLimit(String lowerLimit) {
        realmSet$lowerLimit(lowerLimit);
    }

    public String getHigherLimit() {
        return realmGet$higherLimit();
    }

    public void setHigherLimit(String higherLimit) {
        realmSet$higherLimit(higherLimit);
    }

    public String getLimitType() {
        return realmGet$limitType();
    }

    public void setLimitType(String limitType) {
        realmSet$limitType(limitType);
    }
}
