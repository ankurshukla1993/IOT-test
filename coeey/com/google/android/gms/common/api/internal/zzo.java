package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzo extends LifecycleCallback implements OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zzfke;
    protected final AtomicReference<zzp> zzflq;
    private final Handler zzflr;

    protected zzo(zzci com_google_android_gms_common_api_internal_zzci) {
        this(com_google_android_gms_common_api_internal_zzci, GoogleApiAvailability.getInstance());
    }

    private zzo(zzci com_google_android_gms_common_api_internal_zzci, GoogleApiAvailability googleApiAvailability) {
        super(com_google_android_gms_common_api_internal_zzci);
        this.zzflq = new AtomicReference(null);
        this.zzflr = new Handler(Looper.getMainLooper());
        this.zzfke = googleApiAvailability;
    }

    private static int zza(@Nullable zzp com_google_android_gms_common_api_internal_zzp) {
        return com_google_android_gms_common_api_internal_zzp == null ? -1 : com_google_android_gms_common_api_internal_zzp.zzags();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onActivityResult(int r7, int r8, android.content.Intent r9) {
        /*
        r6 = this;
        r5 = 18;
        r1 = 13;
        r2 = 1;
        r3 = 0;
        r0 = r6.zzflq;
        r0 = r0.get();
        r0 = (com.google.android.gms.common.api.internal.zzp) r0;
        switch(r7) {
            case 1: goto L_0x0034;
            case 2: goto L_0x0018;
            default: goto L_0x0011;
        };
    L_0x0011:
        r1 = r3;
    L_0x0012:
        if (r1 == 0) goto L_0x005a;
    L_0x0014:
        r6.zzagr();
    L_0x0017:
        return;
    L_0x0018:
        r1 = r6.zzfke;
        r4 = r6.getActivity();
        r4 = r1.isGooglePlayServicesAvailable(r4);
        if (r4 != 0) goto L_0x0068;
    L_0x0024:
        r1 = r2;
    L_0x0025:
        if (r0 == 0) goto L_0x0017;
    L_0x0027:
        r2 = r0.zzagt();
        r2 = r2.getErrorCode();
        if (r2 != r5) goto L_0x0012;
    L_0x0031:
        if (r4 != r5) goto L_0x0012;
    L_0x0033:
        goto L_0x0017;
    L_0x0034:
        r4 = -1;
        if (r8 != r4) goto L_0x0039;
    L_0x0037:
        r1 = r2;
        goto L_0x0012;
    L_0x0039:
        if (r8 != 0) goto L_0x0011;
    L_0x003b:
        if (r9 == 0) goto L_0x0043;
    L_0x003d:
        r2 = "<<ResolutionFailureErrorDetail>>";
        r1 = r9.getIntExtra(r2, r1);
    L_0x0043:
        r2 = new com.google.android.gms.common.api.internal.zzp;
        r4 = new com.google.android.gms.common.ConnectionResult;
        r5 = 0;
        r4.<init>(r1, r5);
        r0 = zza(r0);
        r2.<init>(r4, r0);
        r0 = r6.zzflq;
        r0.set(r2);
        r0 = r2;
        r1 = r3;
        goto L_0x0012;
    L_0x005a:
        if (r0 == 0) goto L_0x0017;
    L_0x005c:
        r1 = r0.zzagt();
        r0 = r0.zzags();
        r6.zza(r1, r0);
        goto L_0x0017;
    L_0x0068:
        r1 = r3;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzo.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), zza((zzp) this.zzflq.get()));
        zzagr();
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzflq.set(bundle.getBoolean("resolving_error", false) ? new zzp(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zzp com_google_android_gms_common_api_internal_zzp = (zzp) this.zzflq.get();
        if (com_google_android_gms_common_api_internal_zzp != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", com_google_android_gms_common_api_internal_zzp.zzags());
            bundle.putInt("failed_status", com_google_android_gms_common_api_internal_zzp.zzagt().getErrorCode());
            bundle.putParcelable("failed_resolution", com_google_android_gms_common_api_internal_zzp.zzagt().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    protected abstract void zzagm();

    protected final void zzagr() {
        this.zzflq.set(null);
        zzagm();
    }

    public final void zzb(ConnectionResult connectionResult, int i) {
        zzp com_google_android_gms_common_api_internal_zzp = new zzp(connectionResult, i);
        if (this.zzflq.compareAndSet(null, com_google_android_gms_common_api_internal_zzp)) {
            this.zzflr.post(new zzq(this, com_google_android_gms_common_api_internal_zzp));
        }
    }
}
