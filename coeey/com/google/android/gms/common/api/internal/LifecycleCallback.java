package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class LifecycleCallback {
    protected final zzci zzfrj;

    protected LifecycleCallback(zzci com_google_android_gms_common_api_internal_zzci) {
        this.zzfrj = com_google_android_gms_common_api_internal_zzci;
    }

    @Keep
    private static zzci getChimeraLifecycleFragmentImpl(zzch com_google_android_gms_common_api_internal_zzch) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    protected static zzci zzb(zzch com_google_android_gms_common_api_internal_zzch) {
        if (com_google_android_gms_common_api_internal_zzch.zzaix()) {
            return zzdd.zza(com_google_android_gms_common_api_internal_zzch.zzaja());
        }
        if (com_google_android_gms_common_api_internal_zzch.zzaiy()) {
            return zzcj.zzo(com_google_android_gms_common_api_internal_zzch.zzaiz());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }

    public static zzci zzn(Activity activity) {
        return zzb(new zzch(activity));
    }

    @MainThread
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final Activity getActivity() {
        return this.zzfrj.zzajb();
    }

    @MainThread
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @MainThread
    public void onCreate(Bundle bundle) {
    }

    @MainThread
    public void onDestroy() {
    }

    @MainThread
    public void onResume() {
    }

    @MainThread
    public void onSaveInstanceState(Bundle bundle) {
    }

    @MainThread
    public void onStart() {
    }

    @MainThread
    public void onStop() {
    }
}
