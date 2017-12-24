package com.cooey.common;

import android.app.Application;
import android.content.Context;
import io.realm.Realm;

public class BaseApplication extends Application {
    private static BaseApplication instance;
    private static Context mContext = null;

    public BaseApplication() {
        instance = this;
        mContext = getBaseContext();
    }

    public static Context getContext() {
        return mContext;
    }

    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
