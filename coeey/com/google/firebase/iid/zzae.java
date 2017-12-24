package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

final class zzae extends BroadcastReceiver {
    private zzad zznvg;

    public zzae(zzad com_google_firebase_iid_zzad) {
        this.zznvg = com_google_firebase_iid_zzad;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zznvg != null && this.zznvg.zzchp()) {
            if (FirebaseInstanceId.zzcgz()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zzb(this.zznvg, 0);
            this.zznvg.getContext().unregisterReceiver(this);
            this.zznvg = null;
        }
    }

    public final void zzchq() {
        if (FirebaseInstanceId.zzcgz()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zznvg.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
