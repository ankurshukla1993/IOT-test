package com.google.firebase.crash;

import android.util.Log;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzdxr;
import com.google.android.gms.internal.zzdxt;
import com.google.android.gms.internal.zzdxv;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class zze implements Runnable {
    private /* synthetic */ zzc zzmdt;
    private /* synthetic */ Future zzmdu;
    private /* synthetic */ long zzmdv = 10000;
    private /* synthetic */ zzf zzmdw;

    zze(zzc com_google_firebase_crash_zzc, Future future, long j, zzf com_google_firebase_crash_zzf) {
        this.zzmdt = com_google_firebase_crash_zzc;
        this.zzmdu = future;
        this.zzmdw = com_google_firebase_crash_zzf;
    }

    public final void run() {
        zzdxt com_google_android_gms_internal_zzdxt;
        Throwable e;
        FirebaseOptions options;
        String valueOf;
        try {
            com_google_android_gms_internal_zzdxt = (zzdxt) this.zzmdu.get(this.zzmdv, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
            Log.e("FirebaseCrash", "Failed to initialize crash reporting", e);
            this.zzmdu.cancel(true);
            com_google_android_gms_internal_zzdxt = null;
            if (com_google_android_gms_internal_zzdxt != null) {
                try {
                    options = this.zzmdt.zzlyo.getOptions();
                    com_google_android_gms_internal_zzdxt.zza(zzn.zzy(this.zzmdt.mContext), new zzdxr(options.getApplicationId(), options.getApiKey()));
                    if (this.zzmdt.zzmds == null) {
                        this.zzmdt.zzmds = FirebaseInstanceId.getInstance().getId();
                    }
                    com_google_android_gms_internal_zzdxt.zzop(this.zzmdt.zzmds);
                    valueOf = String.valueOf(zzdxv.zzbqt());
                    Log.i("FirebaseCrash", new StringBuilder(String.valueOf(valueOf).length() + 36).append("FirebaseCrash reporting initialized ").append(valueOf).toString());
                    this.zzmdw.zzc(com_google_android_gms_internal_zzdxt);
                } catch (Throwable e3) {
                    Throwable th = e3;
                    valueOf = "FirebaseCrash";
                    String str = "Failed to initialize crash reporting: ";
                    String valueOf2 = String.valueOf(th.getMessage());
                    Log.e(valueOf, valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                    zzf.zza(this.zzmdt.mContext, th);
                    this.zzmdw.zzbqr();
                    return;
                }
            }
            this.zzmdw.zzbqr();
            return;
        } catch (ExecutionException e4) {
            e3 = e4;
            Log.e("FirebaseCrash", "Failed to initialize crash reporting", e3);
            this.zzmdu.cancel(true);
            com_google_android_gms_internal_zzdxt = null;
            if (com_google_android_gms_internal_zzdxt != null) {
                this.zzmdw.zzbqr();
                return;
            }
            options = this.zzmdt.zzlyo.getOptions();
            com_google_android_gms_internal_zzdxt.zza(zzn.zzy(this.zzmdt.mContext), new zzdxr(options.getApplicationId(), options.getApiKey()));
            if (this.zzmdt.zzmds == null) {
                this.zzmdt.zzmds = FirebaseInstanceId.getInstance().getId();
            }
            com_google_android_gms_internal_zzdxt.zzop(this.zzmdt.zzmds);
            valueOf = String.valueOf(zzdxv.zzbqt());
            Log.i("FirebaseCrash", new StringBuilder(String.valueOf(valueOf).length() + 36).append("FirebaseCrash reporting initialized ").append(valueOf).toString());
            this.zzmdw.zzc(com_google_android_gms_internal_zzdxt);
        } catch (TimeoutException e5) {
            e3 = e5;
            Log.e("FirebaseCrash", "Failed to initialize crash reporting", e3);
            this.zzmdu.cancel(true);
            com_google_android_gms_internal_zzdxt = null;
            if (com_google_android_gms_internal_zzdxt != null) {
                options = this.zzmdt.zzlyo.getOptions();
                com_google_android_gms_internal_zzdxt.zza(zzn.zzy(this.zzmdt.mContext), new zzdxr(options.getApplicationId(), options.getApiKey()));
                if (this.zzmdt.zzmds == null) {
                    this.zzmdt.zzmds = FirebaseInstanceId.getInstance().getId();
                }
                com_google_android_gms_internal_zzdxt.zzop(this.zzmdt.zzmds);
                valueOf = String.valueOf(zzdxv.zzbqt());
                Log.i("FirebaseCrash", new StringBuilder(String.valueOf(valueOf).length() + 36).append("FirebaseCrash reporting initialized ").append(valueOf).toString());
                this.zzmdw.zzc(com_google_android_gms_internal_zzdxt);
            }
            this.zzmdw.zzbqr();
            return;
        }
        if (com_google_android_gms_internal_zzdxt != null) {
            this.zzmdw.zzbqr();
            return;
        }
        options = this.zzmdt.zzlyo.getOptions();
        com_google_android_gms_internal_zzdxt.zza(zzn.zzy(this.zzmdt.mContext), new zzdxr(options.getApplicationId(), options.getApiKey()));
        if (this.zzmdt.zzmds == null) {
            this.zzmdt.zzmds = FirebaseInstanceId.getInstance().getId();
        }
        com_google_android_gms_internal_zzdxt.zzop(this.zzmdt.zzmds);
        valueOf = String.valueOf(zzdxv.zzbqt());
        Log.i("FirebaseCrash", new StringBuilder(String.valueOf(valueOf).length() + 36).append("FirebaseCrash reporting initialized ").append(valueOf).toString());
        this.zzmdw.zzc(com_google_android_gms_internal_zzdxt);
    }
}
