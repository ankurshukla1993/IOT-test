package com.cooey.common.converter;

import io.realm.RealmObject;
import io.realm.TagRealmProxyInterface;
import io.realm.annotations.RealmClass;
import io.realm.internal.RealmObjectProxy;

@RealmClass
public class Tag extends RealmObject implements TagRealmProxyInterface {
    private String value;

    public String realmGet$value() {
        return this.value;
    }

    public void realmSet$value(String str) {
        this.value = str;
    }

    public Tag() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getValue() {
        return realmGet$value();
    }

    public void setValue(String value) {
        realmSet$value(value);
    }
}
