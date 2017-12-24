package com.cooey.common.vo.careplan;

import com.google.gson.annotations.SerializedName;
import io.realm.DiagnosisRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Diagnosis extends RealmObject implements Serializable, DiagnosisRealmProxyInterface {
    @SerializedName("healthCondition")
    private String healthConditions;
    private String symoptoms;

    public String realmGet$healthConditions() {
        return this.healthConditions;
    }

    public String realmGet$symoptoms() {
        return this.symoptoms;
    }

    public void realmSet$healthConditions(String str) {
        this.healthConditions = str;
    }

    public void realmSet$symoptoms(String str) {
        this.symoptoms = str;
    }

    public Diagnosis() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getHealthConditions() {
        return realmGet$healthConditions();
    }

    public void setHealthConditions(String healthConditions) {
        realmSet$healthConditions(healthConditions);
    }

    public String getSymoptoms() {
        return realmGet$symoptoms();
    }

    public void setSymoptoms(String symoptoms) {
        realmSet$symoptoms(symoptoms);
    }
}
