package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.WorkerThread;
import android.util.Log;
import humanize.util.Constants;

public class FirebaseInstanceIdService extends zzb {
    public final void handleIntent(Intent intent) {
        if ("com.google.firebase.iid.TOKEN_REFRESH".equals(intent.getAction())) {
            onTokenRefresh();
            return;
        }
        zzi zza = zzi.zza((Context) this, intent.getStringExtra("subtype"), null);
        String stringExtra = intent.getStringExtra("CMD");
        if (Log.isLoggable("InstanceID", 3)) {
            String str = zza.zzico;
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("InstanceID", new StringBuilder(((String.valueOf(str).length() + 22) + String.valueOf(stringExtra).length()) + String.valueOf(valueOf).length()).append("Received command [").append(str).append("]: ").append(stringExtra).append(Constants.SPACE).append(valueOf).toString());
        }
        if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
            zza.zzchd();
        } else if ("RST".equals(stringExtra)) {
            zza.zzchb();
        } else if ("RST_FULL".equals(stringExtra)) {
            zza.zzchc();
        } else if ("SYNC".equals(stringExtra)) {
            zza.zzchd();
        }
    }

    @WorkerThread
    public void onTokenRefresh() {
    }

    protected final Intent zzp(Intent intent) {
        return (Intent) zzaa.zzchl().zznva.poll();
    }
}
