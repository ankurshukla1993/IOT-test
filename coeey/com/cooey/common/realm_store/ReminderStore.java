package com.cooey.common.realm_store;

import android.content.Context;
import com.cooey.common.vo.ProtoRealm;
import com.cooey.common.vo.Reminder;
import com.facebook.share.internal.ShareConstants;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import java.util.List;

public class ReminderStore {
    private Realm realm;

    public ReminderStore(Context context) {
        this.realm = ProtoRealm.getInstance(context).getRealm();
    }

    public ReminderStore(Realm realm) {
        this.realm = realm;
    }

    public static ReminderStore getInstance(Realm realm) {
        return new ReminderStore(realm);
    }

    public static ReminderStore getInstance(Context context) {
        return new ReminderStore(context);
    }

    public void writeToMedicine(final Reminder reminder) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(reminder);
            }
        });
    }

    public void writeToMedicine(final List<Reminder> reminderList) {
        this.realm.executeTransaction(new Transaction() {
            public void execute(Realm realm1) {
                realm1.copyToRealmOrUpdate(reminderList);
            }
        });
    }

    public RealmResults<Reminder> getAllReminder(String id) {
        return this.realm.where(Reminder.class).equalTo("userId", id).findAll();
    }

    public Reminder getReminderById(String id) {
        return (Reminder) this.realm.where(Reminder.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
    }

    public void updateReminder(final String id, final int jobId) {
        Realm.getDefaultInstance().executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                Reminder updateReminder = (Reminder) realm.where(Reminder.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
                updateReminder.setJobId(jobId);
                realm.copyToRealmOrUpdate(updateReminder);
            }
        });
    }

    public void updateReminderId(final String id) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Transaction() {
            public void execute(Realm realm) {
                Reminder updateReminder = (Reminder) realm.where(Reminder.class).equalTo(ShareConstants.WEB_DIALOG_PARAM_ID, id).findFirst();
                updateReminder.setId(id);
                realm.copyToRealmOrUpdate(updateReminder);
            }
        });
        realm.close();
    }

    public void close() {
        this.realm.close();
    }
}
