package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.ProtoRealm;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;

public class ActivityStore {
    Realm realm;

    public ActivityStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static ActivityStore getInstance(Context context) {
        return new ActivityStore(context);
    }

    public void writeToActivity(final Activity activity) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(activity);
            }
        });
    }

    public RealmResults<Activity> getAllActivities() {
        return this.realm.where(Activity.class).findAll();
    }

    public void close() {
        this.realm.close();
    }

    public Activity getActivity(String id) {
        return (Activity) this.realm.where(Activity.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }
}
