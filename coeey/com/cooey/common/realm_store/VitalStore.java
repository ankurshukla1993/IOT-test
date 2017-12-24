package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.Vital;
import com.google.android.gms.measurement.AppMeasurement$Param;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.List;

public class VitalStore {
    private Realm realm;

    public VitalStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static VitalStore getInstance(Context context) {
        return new VitalStore(context);
    }

    public VitalStore(Realm realm) {
        this.realm = realm;
    }

    public void writeToVital(final Vital vitalObject) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(vitalObject);
            }
        });
    }

    public void writeToVital(final List<Vital> vitalList) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(vitalList);
                for (Vital vital : vitalList) {
                }
            }
        });
    }

    public RealmResults<Vital> getAllVitals() {
        return this.realm.where(Vital.class).findAllSorted(AppMeasurement$Param.TIMESTAMP, Sort.DESCENDING);
    }

    public RealmResults<Vital> getAllVitalsForUser(String id) {
        return this.realm.where(Vital.class).equalTo("userId", id).findAllSorted(AppMeasurement$Param.TIMESTAMP, Sort.DESCENDING);
    }

    public Vital getLastVital(String id) {
        return (Vital) this.realm.where(Vital.class).equalTo("userId", id).findFirst();
    }

    public void close() {
        this.realm.close();
    }

    public Vital getVital(String id) {
        return (Vital) this.realm.where(Vital.class).equalTo("vitalId", id).findFirst();
    }

    public RealmResults<Vital> getVitals(String type) {
        return this.realm.where(Vital.class).equalTo("vitalType", type).findAllSorted(AppMeasurement$Param.TIMESTAMP, Sort.DESCENDING);
    }

    public List<Vital> getVitalsForName(String name) {
        return this.realm.where(Vital.class).contains("parameters", name).findAll();
    }

    public boolean isVitalExist(String id) {
        return this.realm.where(Vital.class).equalTo("vitalId", id).count() > 0;
    }

    public boolean isSyncedVitalExist() {
        if (this.realm.where(Vital.class).equalTo("isSync", Boolean.valueOf(true)).count() > 0) {
            return true;
        }
        return false;
    }

    public void syncVital(String id) {
        final Vital realmResults = (Vital) this.realm.where(Vital.class).equalTo("vitalId", id).findFirst();
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                realmResults.setSync(true);
            }
        });
    }

    public void deleteVital(String id) {
        final Vital realmResults = (Vital) this.realm.where(Vital.class).equalTo("vitalId", id).findFirst();
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                realmResults.deleteFromRealm();
            }
        });
    }

    public List<Vital> getOfflineVitals(String patientId) {
        return this.realm.copyFromRealm(this.realm.where(Vital.class).equalTo("userId", patientId).equalTo("isSync", Boolean.valueOf(false)).findAllAsync());
    }

    public void deleteAllSynced() {
        final RealmResults<Vital> realmSyncedResults = this.realm.where(Vital.class).equalTo("isSync", Boolean.valueOf(true)).findAll();
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                realmSyncedResults.deleteAllFromRealm();
            }
        });
    }

    public void deleteAllVitals() {
        final RealmResults<Vital> realmResults = this.realm.where(Vital.class).findAll();
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                realmResults.deleteAllFromRealm();
            }
        });
    }
}
