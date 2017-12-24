package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzx extends Handler {
    private /* synthetic */ zzw zznuu;

    zzx(zzw com_google_firebase_iid_zzw, Looper looper) {
        this.zznuu = com_google_firebase_iid_zzw;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zznuu.zzc(message);
    }
}
