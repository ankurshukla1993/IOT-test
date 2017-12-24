package com.cooey.common.vo;

import io.realm.RealmBooleanRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;

@Parcel(analyze = {RealmBoolean.class}, value = Serialization.BEAN)
public class RealmBoolean extends RealmObject implements RealmBooleanRealmProxyInterface {
    public boolean value;

    public boolean realmGet$value() {
        return this.value;
    }

    public void realmSet$value(boolean z) {
        this.value = z;
    }

    public RealmBoolean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RealmBoolean(boolean value) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$value(value);
    }

    public boolean getValue() {
        return realmGet$value();
    }

    public void setValue(boolean value) {
        realmSet$value(value);
    }
}
