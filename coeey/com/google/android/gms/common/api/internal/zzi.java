package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbq;
import humanize.util.Constants;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzo {
    private final SparseArray<zza> zzfld = new SparseArray();

    class zza implements OnConnectionFailedListener {
        public final int zzfle;
        public final GoogleApiClient zzflf;
        public final OnConnectionFailedListener zzflg;
        private /* synthetic */ zzi zzflh;

        public zza(zzi com_google_android_gms_common_api_internal_zzi, int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.zzflh = com_google_android_gms_common_api_internal_zzi;
            this.zzfle = i;
            this.zzflf = googleApiClient;
            this.zzflg = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            this.zzflh.zzb(connectionResult, this.zzfle);
        }
    }

    private zzi(zzci com_google_android_gms_common_api_internal_zzci) {
        super(com_google_android_gms_common_api_internal_zzci);
        this.zzfrj.zza("AutoManageHelper", (LifecycleCallback) this);
    }

    public static zzi zza(zzch com_google_android_gms_common_api_internal_zzch) {
        zzci zzb = LifecycleCallback.zzb(com_google_android_gms_common_api_internal_zzch);
        zzi com_google_android_gms_common_api_internal_zzi = (zzi) zzb.zza("AutoManageHelper", zzi.class);
        return com_google_android_gms_common_api_internal_zzi != null ? com_google_android_gms_common_api_internal_zzi : new zzi(zzb);
    }

    @Nullable
    private final zza zzbs(int i) {
        return this.zzfld.size() <= i ? null : (zza) this.zzfld.get(this.zzfld.keyAt(i));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zzfld.size(); i++) {
            zza zzbs = zzbs(i);
            if (zzbs != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zzbs.zzfle);
                printWriter.println(":");
                zzbs.zzflf.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzfld);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(Constants.SPACE).append(valueOf).toString());
        if (this.zzflq.get() == null) {
            for (int i = 0; i < this.zzfld.size(); i++) {
                zza zzbs = zzbs(i);
                if (zzbs != null) {
                    zzbs.zzflf.connect();
                }
            }
        }
    }

    public final void onStop() {
        super.onStop();
        for (int i = 0; i < this.zzfld.size(); i++) {
            zza zzbs = zzbs(i);
            if (zzbs != null) {
                zzbs.zzflf.disconnect();
            }
        }
    }

    public final void zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        zzbq.zza(this.zzfld.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        zzp com_google_android_gms_common_api_internal_zzp = (zzp) this.zzflq.get();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(com_google_android_gms_common_api_internal_zzp);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 49).append("starting AutoManage for client ").append(i).append(Constants.SPACE).append(z).append(Constants.SPACE).append(valueOf).toString());
        this.zzfld.put(i, new zza(this, i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && com_google_android_gms_common_api_internal_zzp == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf2).length() + 11).append("connecting ").append(valueOf2).toString());
            googleApiClient.connect();
        }
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza com_google_android_gms_common_api_internal_zzi_zza = (zza) this.zzfld.get(i);
        if (com_google_android_gms_common_api_internal_zzi_zza != null) {
            zzbr(i);
            OnConnectionFailedListener onConnectionFailedListener = com_google_android_gms_common_api_internal_zzi_zza.zzflg;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final void zzagm() {
        for (int i = 0; i < this.zzfld.size(); i++) {
            zza zzbs = zzbs(i);
            if (zzbs != null) {
                zzbs.zzflf.connect();
            }
        }
    }

    public final void zzbr(int i) {
        zza com_google_android_gms_common_api_internal_zzi_zza = (zza) this.zzfld.get(i);
        this.zzfld.remove(i);
        if (com_google_android_gms_common_api_internal_zzi_zza != null) {
            com_google_android_gms_common_api_internal_zzi_zza.zzflf.unregisterConnectionFailedListener(com_google_android_gms_common_api_internal_zzi_zza);
            com_google_android_gms_common_api_internal_zzi_zza.zzflf.disconnect();
        }
    }
}
