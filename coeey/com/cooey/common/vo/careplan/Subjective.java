package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.SubjectiveRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Subjective extends RealmObject implements Serializable, SubjectiveRealmProxyInterface {
    private String note;

    public String realmGet$note() {
        return this.note;
    }

    public void realmSet$note(String str) {
        this.note = str;
    }

    public Subjective() {
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
}
