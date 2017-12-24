package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.CareplanSummary;
import com.cooey.common.vo.ProtoRealm;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.util.List;

public class ActionSummaryStore {
    Realm realm;

    public ActionSummaryStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static ActionSummaryStore getInstance(Context context) {
        return new ActionSummaryStore(context);
    }

    public void writeToActionSummary(final CareplanSummary actionSummary) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(actionSummary);
            }
        });
    }

    public void writeToActionSummaries(final List<CareplanSummary> actionSummaries) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(actionSummaries);
            }
        });
    }

    public List<CareplanSummary> getActionSummary() {
        return this.realm.where(CareplanSummary.class).findAll();
    }
}
