package com.ihealth.communication.ins;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;

public abstract class InsCallback {
    private static final int NOTIFY_DELAY = 100;
    private static final String TAG = "InsCallback";
    private String mCurrentAction;
    private Handler mNotifyHandler;
    private HandlerThread mNotifyThread;

    public InsCallback() {
        this.mCurrentAction = null;
        this.mNotifyThread = null;
        this.mNotifyHandler = null;
        this.mNotifyThread = new HandlerThread("InsCallback Notify Thread.");
        this.mNotifyThread.start();
        this.mNotifyHandler = new Handler(this.mNotifyThread.getLooper());
    }

    private boolean exitAction(String action) {
        Log.p(TAG, Level.DEBUG, "exitAction", new Object[]{action});
        if (!TextUtils.isEmpty(action) && action.equals(iHealthDevicesManager.IHEALTH_COMM_TIMEOUT)) {
            Log.d(TAG, "exitAction() because of timeout, exit action");
            this.mCurrentAction = null;
            return true;
        } else if (TextUtils.equals(this.mCurrentAction, action)) {
            Log.d(TAG, "exitAction() current action and action match, exit action");
            this.mCurrentAction = null;
            return true;
        } else {
            Log.e(TAG, "exitAction() current action and action not match, current action is " + this.mCurrentAction + ", action = " + action);
            return false;
        }
    }

    public void destroy() {
        this.mNotifyThread.quitSafely();
    }

    void enterAction(String action) {
        Log.p(TAG, Level.DEBUG, "enterAction", new Object[]{action});
        this.mCurrentAction = action;
    }

    public abstract void onNotify(String str, String str2, String str3, String str4);

    void onNotifyWithAction(String mac, String type, String action, String message) {
        if (exitAction(action)) {
            onNotifyWithDelay(mac, type, action, message);
        }
    }

    void onNotifyWithDelay(String mac, String type, String action, String message) {
        final String str = mac;
        final String str2 = type;
        final String str3 = action;
        final String str4 = message;
        this.mNotifyHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ InsCallback f1898e;

            public void run() {
                this.f1898e.onNotify(str, str2, str3, str4);
            }
        }, 100);
    }
}
