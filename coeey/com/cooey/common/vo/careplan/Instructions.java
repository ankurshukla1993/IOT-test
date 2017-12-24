package com.cooey.common.vo.careplan;

import io.realm.InstructionsRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class Instructions extends RealmObject implements InstructionsRealmProxyInterface {
    private String note;

    public String realmGet$note() {
        return this.note;
    }

    public void realmSet$note(String str) {
        this.note = str;
    }

    public Instructions() {
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
