package com.cooey.common.vo;

import android.content.Context;
import io.realm.Realm;

public class ProtoRealm {
    private static final String REALM_PROTO_ONE = "adcpl_patient_db.realm";
    public static ProtoRealm instance;
    public Realm realm = Realm.getDefaultInstance();

    public static ProtoRealm getInstance(Context context) {
        if (instance == null) {
            instance = new ProtoRealm(context);
        }
        return instance;
    }

    public ProtoRealm(Context context) {
    }

    public Realm getRealm() {
        return this.realm;
    }

    public void close() {
        if (this.realm != null) {
            this.realm.close();
        }
    }
}
