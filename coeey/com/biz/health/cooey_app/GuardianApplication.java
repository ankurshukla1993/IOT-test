package com.biz.health.cooey_app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.biz.health.cooey_app.reminders.ReminderJobCreator;
import com.evernote.android.job.JobManager;
import io.realm.Realm;
import io.realm.RealmConfiguration.Builder;

public class GuardianApplication extends Application {
    private static GuardianApplication instance;
    private static Context mContext = null;

    public GuardianApplication() {
        instance = this;
        mContext = getBaseContext();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

    public static GuardianApplication getInstance() {
        if (instance != null) {
            return instance;
        }
        throw new IllegalStateException();
    }

    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new Builder().deleteRealmIfMigrationNeeded().build());
        JobManager.create(this).addJobCreator(new ReminderJobCreator(getApplicationContext()));
    }
}
