package com.cooey.common.vo.careplan;

import io.realm.RealmObject;
import io.realm.SymoptomsRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Symoptoms extends RealmObject implements Serializable, SymoptomsRealmProxyInterface {
    private String note;

    public String realmGet$note() {
        return this.note;
    }

    public void realmSet$note(String str) {
        this.note = str;
    }

    public Symoptoms() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public Symoptoms(String note) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$note(note);
    }

    public String getNote() {
        return realmGet$note();
    }

    public void setNote(String note) {
        realmSet$note(note);
    }
}
