package com.cooey.common.vo;

import io.realm.RealmObject;
import io.realm.UserFeatureRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class UserFeature extends RealmObject implements Serializable, UserFeatureRealmProxyInterface {
    private RealmBoolean realmBoolean;
    private String settingName;

    public RealmBoolean realmGet$realmBoolean() {
        return this.realmBoolean;
    }

    public String realmGet$settingName() {
        return this.settingName;
    }

    public void realmSet$realmBoolean(RealmBoolean realmBoolean) {
        this.realmBoolean = realmBoolean;
    }

    public void realmSet$settingName(String str) {
        this.settingName = str;
    }

    public UserFeature(String settingName, RealmBoolean realmBoolean) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$settingName(settingName);
        realmSet$realmBoolean(realmBoolean);
    }

    public UserFeature() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getSettingName() {
        return realmGet$settingName();
    }

    public void setSettingName(String settingName) {
        realmSet$settingName(settingName);
    }

    public RealmBoolean getRealmBoolean() {
        return realmGet$realmBoolean();
    }

    public void setRealmBoolean(RealmBoolean realmBoolean) {
        realmSet$realmBoolean(realmBoolean);
    }
}
