package com.google.firebase.iid;

import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import com.evernote.android.job.JobRequest;
import java.io.IOException;

final class zzy implements zzz {
    private Bundle zznuq;
    private final ConditionVariable zznuv;
    private String zznuw;

    private zzy() {
        this.zznuv = new ConditionVariable();
    }

    public final void onError(String str) {
        this.zznuw = str;
        this.zznuv.open();
    }

    public final void zzag(Bundle bundle) {
        this.zznuq = bundle;
        this.zznuv.open();
    }

    public final Bundle zzchk() throws IOException {
        if (!this.zznuv.block(JobRequest.DEFAULT_BACKOFF_MS)) {
            Log.w("FirebaseInstanceId", "No response");
            throw new IOException("TIMEOUT");
        } else if (this.zznuw == null) {
            return this.zznuq;
        } else {
            throw new IOException(this.zznuw);
        }
    }
}
