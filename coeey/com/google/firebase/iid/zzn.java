package com.google.firebase.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class zzn implements Callback {
    private final zzm zznum;

    zzn(zzm com_google_firebase_iid_zzm) {
        this.zznum = com_google_firebase_iid_zzm;
    }

    public final boolean handleMessage(Message message) {
        return this.zznum.zzd(message);
    }
}
