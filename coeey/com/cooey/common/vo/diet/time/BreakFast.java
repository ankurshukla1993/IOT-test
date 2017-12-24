package com.cooey.common.vo.diet.time;

import com.google.gson.annotations.SerializedName;
import io.realm.BreakFastRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class BreakFast extends RealmObject implements Serializable, BreakFastRealmProxyInterface {
    private String dietId;
    @SerializedName("name")
    private String name;

    public String realmGet$dietId() {
        return this.dietId;
    }

    public String realmGet$name() {
        return this.name;
    }

    public void realmSet$dietId(String str) {
        this.dietId = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public BreakFast(String name, String dietId) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$name(name);
        realmSet$dietId(dietId);
    }

    public BreakFast() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getDietId() {
        return realmGet$dietId();
    }

    public void setDietId(String dietId) {
        realmSet$dietId(dietId);
    }
}
