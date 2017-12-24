package com.cooey.common.vo;

import io.realm.RealmObject;
import io.realm.RealmStringRealmProxyInterface;
import io.realm.annotations.RealmClass;
import io.realm.internal.RealmObjectProxy;

@RealmClass
public class RealmString extends RealmObject implements RealmStringRealmProxyInterface {
    private String val;

    public String realmGet$val() {
        return this.val;
    }

    public void realmSet$val(String str) {
        this.val = str;
    }

    public RealmString() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RealmString(String val) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$val(val);
    }

    public String getValue() {
        return realmGet$val();
    }

    public void setValue(String value) {
        realmSet$val(value);
    }
}
