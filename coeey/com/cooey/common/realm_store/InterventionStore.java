package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.careplan.Intervention;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.List;

public class InterventionStore {
    Realm realm;

    public InterventionStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static InterventionStore getInstance(Context context) {
        return new InterventionStore(context);
    }

    public void writeToIntervention(final Intervention Intervention) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(Intervention);
            }
        });
    }

    public List<Intervention> getIntervention() {
        return this.realm.where(Intervention.class).findAll();
    }

    public RealmResults<Intervention> getInterventions(String ownerId) {
        return this.realm.where(Intervention.class).equalTo("ownerId", ownerId).findAll();
    }
}
