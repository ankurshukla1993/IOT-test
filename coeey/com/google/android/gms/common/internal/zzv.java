package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzci;

public abstract class zzv implements OnClickListener {
    public static zzv zza(Activity activity, Intent intent, int i) {
        return new zzw(intent, activity, i);
    }

    public static zzv zza(@NonNull Fragment fragment, Intent intent, int i) {
        return new zzx(intent, fragment, i);
    }

    public static zzv zza(@NonNull zzci com_google_android_gms_common_api_internal_zzci, Intent intent, int i) {
        return new zzy(intent, com_google_android_gms_common_api_internal_zzci, 2);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zzaks();
        } catch (Throwable e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }

    protected abstract void zzaks();
}
