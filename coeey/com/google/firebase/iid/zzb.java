package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    private final Object mLock = new Object();
    @VisibleForTesting
    final ExecutorService zzibs = Executors.newSingleThreadExecutor();
    private Binder zzibt;
    private int zzibu;
    private int zzibv = 0;

    private final void zzh(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.mLock) {
            this.zzibv--;
            if (this.zzibv == 0) {
                stopSelfResult(this.zzibu);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzibt == null) {
            this.zzibt = new zzf(this);
        }
        return this.zzibt;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.mLock) {
            this.zzibu = i2;
            this.zzibv++;
        }
        Intent zzp = zzp(intent);
        if (zzp == null) {
            zzh(intent);
            return 2;
        } else if (zzq(zzp)) {
            zzh(intent);
            return 2;
        } else {
            this.zzibs.execute(new zzc(this, zzp, intent));
            return 3;
        }
    }

    protected Intent zzp(Intent intent) {
        return intent;
    }

    public boolean zzq(Intent intent) {
        return false;
    }
}
