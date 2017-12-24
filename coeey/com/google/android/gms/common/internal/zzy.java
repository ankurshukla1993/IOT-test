package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.zzci;

final class zzy extends zzv {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ zzci zzfws;

    zzy(Intent intent, zzci com_google_android_gms_common_api_internal_zzci, int i) {
        this.val$intent = intent;
        this.zzfws = com_google_android_gms_common_api_internal_zzci;
        this.val$requestCode = i;
    }

    public final void zzaks() {
        if (this.val$intent != null) {
            this.zzfws.startActivityForResult(this.val$intent, this.val$requestCode);
        }
    }
}
