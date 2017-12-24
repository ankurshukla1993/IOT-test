package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.careplan.Careplan;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.List;

public class CarePlanStore {
    Realm realm;

    public CarePlanStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static CarePlanStore getInstance(Context context) {
        return new CarePlanStore(context);
    }

    public void writeToCareplan(final Careplan careplan) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(careplan);
            }
        });
    }

    public List<Careplan> getCareplan() {
        return this.realm.where(Careplan.class).findAll();
    }
}
