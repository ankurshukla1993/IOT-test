package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.MedicalContact;
import com.cooey.common.vo.ProtoRealm;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.List;

public class MedicalContactStore {
    private Realm realm;

    public MedicalContactStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static MedicalContactStore getInstance(Context context) {
        return new MedicalContactStore(context);
    }

    public void writeToMedicine(final MedicalContact medicalContact) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(medicalContact);
            }
        });
    }

    public void writeToMedicine(final List<MedicalContact> medicalContactsList) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(medicalContactsList);
            }
        });
    }

    public RealmResults<MedicalContact> getAllMedicinesContacts(String id) {
        return this.realm.where(MedicalContact.class).equalTo("userId", id).findAll();
    }

    public void close() {
        this.realm.close();
    }
}
