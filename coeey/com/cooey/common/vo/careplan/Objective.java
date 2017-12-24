package com.cooey.common.vo.careplan;

import io.realm.ObjectiveRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Objective extends RealmObject implements Serializable, ObjectiveRealmProxyInterface {
    private String note;
    private String parameter;

    public String realmGet$note() {
        return this.note;
    }

    public String realmGet$parameter() {
        return this.parameter;
    }

    public void realmSet$note(String str) {
        this.note = str;
    }

    public void realmSet$parameter(String str) {
        this.parameter = str;
    }

    public Objective() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getNote() {
        return realmGet$note();
    }

    public void setNote(String note) {
        realmSet$note(note);
    }

    public String getParameter() {
        return realmGet$parameter();
    }

    public void setParameter(String parameter) {
        realmSet$parameter(parameter);
    }
}
