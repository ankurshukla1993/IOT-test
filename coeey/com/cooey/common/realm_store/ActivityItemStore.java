package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.careplan.ActivityItem;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.Sort;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ActivityItemStore {
    private Realm realm;

    public ActivityItemStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public static ActivityItemStore getInstance(Context context) {
        return new ActivityItemStore(context);
    }

    public void writeToActivity(final ActivityItem activityItem) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(activityItem);
            }
        });
    }

    public void writeToActivities(final List<ActivityItem> activityItem) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(activityItem);
            }
        });
    }

    public ActivityItem getActivityById(String id) {
        return (ActivityItem) this.realm.where(ActivityItem.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public List<ActivityItem> getAllActivities() {
        return this.realm.where(ActivityItem.class).findAllSorted("timeStamp", Sort.DESCENDING);
    }

    public ActivityItem getActivityItemByName(String name) {
        if (this.realm.where(ActivityItem.class).equalTo("name", name).findFirst() != null) {
            return (ActivityItem) this.realm.copyFromRealm(this.realm.where(ActivityItem.class).equalTo("name", name).findFirst());
        }
        return null;
    }

    public List<ActivityItem> getActivities(String activityName) {
        List<ActivityItem> activityItems = new ArrayList();
        for (int i = 0; i < 20; i++) {
            long timestamp = Calendar.getInstance().getTimeInMillis();
            ActivityItem activityItem = new ActivityItem();
            activityItem.setId(UUID.randomUUID().toString());
            activityItem.setName(activityName);
            activityItem.setParameters("{\"value\":\"" + new Random(100).nextInt() + "\"}");
            activityItem.setTimeStamp(new Random(timestamp).nextLong());
            activityItems.add(activityItem);
        }
        return activityItems;
    }
}
