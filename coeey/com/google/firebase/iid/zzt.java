package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzt<T> {
    final int what;
    final TaskCompletionSource<T> zzgow = new TaskCompletionSource();
    final int zzicx;
    final Bundle zznuq;

    zzt(int i, int i2, Bundle bundle) {
        this.zzicx = i;
        this.what = i2;
        this.zznuq = bundle;
    }

    final void finish(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            Log.d("MessengerIpcClient", new StringBuilder((String.valueOf(valueOf).length() + 16) + String.valueOf(valueOf2).length()).append("Finishing ").append(valueOf).append(" with ").append(valueOf2).toString());
        }
        this.zzgow.setResult(t);
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzicx;
        return "Request { what=" + i + " id=" + i2 + " oneWay=" + zzchj() + "}";
    }

    abstract void zzad(Bundle bundle);

    final void zzb(zzu com_google_firebase_iid_zzu) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(com_google_firebase_iid_zzu);
            Log.d("MessengerIpcClient", new StringBuilder((String.valueOf(valueOf).length() + 14) + String.valueOf(valueOf2).length()).append("Failing ").append(valueOf).append(" with ").append(valueOf2).toString());
        }
        this.zzgow.setException(com_google_firebase_iid_zzu);
    }

    abstract boolean zzchj();
}
