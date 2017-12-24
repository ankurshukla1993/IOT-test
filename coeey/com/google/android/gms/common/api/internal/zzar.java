package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import com.google.android.gms.internal.zzcwo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzar implements zzbk {
    private final Context mContext;
    private final zza<? extends zzcwb, zzcwc> zzfkf;
    private final Lock zzfmy;
    private final zzr zzfnd;
    private final Map<Api<?>, Boolean> zzfng;
    private final zze zzfni;
    private ConnectionResult zzfnr;
    private final zzbl zzfob;
    private int zzfoe;
    private int zzfof = 0;
    private int zzfog;
    private final Bundle zzfoh = new Bundle();
    private final Set<zzc> zzfoi = new HashSet();
    private zzcwb zzfoj;
    private boolean zzfok;
    private boolean zzfol;
    private boolean zzfom;
    private zzan zzfon;
    private boolean zzfoo;
    private boolean zzfop;
    private ArrayList<Future<?>> zzfoq = new ArrayList();

    public zzar(zzbl com_google_android_gms_common_api_internal_zzbl, zzr com_google_android_gms_common_internal_zzr, Map<Api<?>, Boolean> map, zze com_google_android_gms_common_zze, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, Lock lock, Context context) {
        this.zzfob = com_google_android_gms_common_api_internal_zzbl;
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzfng = map;
        this.zzfni = com_google_android_gms_common_zze;
        this.zzfkf = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc;
        this.zzfmy = lock;
        this.mContext = context;
    }

    private final void zza(zzcwo com_google_android_gms_internal_zzcwo) {
        if (zzbt(0)) {
            ConnectionResult zzagt = com_google_android_gms_internal_zzcwo.zzagt();
            if (zzagt.isSuccess()) {
                zzbt zzbcw = com_google_android_gms_internal_zzcwo.zzbcw();
                ConnectionResult zzagt2 = zzbcw.zzagt();
                if (zzagt2.isSuccess()) {
                    this.zzfom = true;
                    this.zzfon = zzbcw.zzald();
                    this.zzfoo = zzbcw.zzale();
                    this.zzfop = zzbcw.zzalf();
                    zzahr();
                    return;
                }
                String valueOf = String.valueOf(zzagt2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                zze(zzagt2);
            } else if (zzd(zzagt)) {
                zzaht();
                zzahr();
            } else {
                zze(zzagt);
            }
        }
    }

    private final boolean zzahq() {
        this.zzfog--;
        if (this.zzfog > 0) {
            return false;
        }
        if (this.zzfog < 0) {
            Log.w("GoogleApiClientConnecting", this.zzfob.zzfmo.zzaia());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zze(new ConnectionResult(8, null));
            return false;
        } else if (this.zzfnr == null) {
            return true;
        } else {
            this.zzfob.zzfpz = this.zzfoe;
            zze(this.zzfnr);
            return false;
        }
    }

    private final void zzahr() {
        if (this.zzfog == 0) {
            if (!this.zzfol || this.zzfom) {
                ArrayList arrayList = new ArrayList();
                this.zzfof = 1;
                this.zzfog = this.zzfob.zzfph.size();
                for (zzc com_google_android_gms_common_api_Api_zzc : this.zzfob.zzfph.keySet()) {
                    if (!this.zzfob.zzfpw.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                        arrayList.add((Api.zze) this.zzfob.zzfph.get(com_google_android_gms_common_api_Api_zzc));
                    } else if (zzahq()) {
                        zzahs();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zzfoq.add(zzbo.zzaid().submit(new zzax(this, arrayList)));
                }
            }
        }
    }

    private final void zzahs() {
        this.zzfob.zzaic();
        zzbo.zzaid().execute(new zzas(this));
        if (this.zzfoj != null) {
            if (this.zzfoo) {
                this.zzfoj.zza(this.zzfon, this.zzfop);
            }
            zzbf(false);
        }
        for (zzc com_google_android_gms_common_api_Api_zzc : this.zzfob.zzfpw.keySet()) {
            ((Api.zze) this.zzfob.zzfph.get(com_google_android_gms_common_api_Api_zzc)).disconnect();
        }
        this.zzfob.zzfqa.zzj(this.zzfoh.isEmpty() ? null : this.zzfoh);
    }

    private final void zzaht() {
        this.zzfol = false;
        this.zzfob.zzfmo.zzfpi = Collections.emptySet();
        for (zzc com_google_android_gms_common_api_Api_zzc : this.zzfoi) {
            if (!this.zzfob.zzfpw.containsKey(com_google_android_gms_common_api_Api_zzc)) {
                this.zzfob.zzfpw.put(com_google_android_gms_common_api_Api_zzc, new ConnectionResult(17, null));
            }
        }
    }

    private final void zzahu() {
        ArrayList arrayList = this.zzfoq;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zzfoq.clear();
    }

    private final Set<Scope> zzahv() {
        if (this.zzfnd == null) {
            return Collections.emptySet();
        }
        Set<Scope> hashSet = new HashSet(this.zzfnd.zzakj());
        Map zzakl = this.zzfnd.zzakl();
        for (Api api : zzakl.keySet()) {
            if (!this.zzfob.zzfpw.containsKey(api.zzaft())) {
                hashSet.addAll(((zzt) zzakl.get(api)).zzees);
            }
        }
        return hashSet;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(com.google.android.gms.common.ConnectionResult r6, com.google.android.gms.common.api.Api<?> r7, boolean r8) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = r7.zzafr();
        r3 = r2.getPriority();
        if (r8 == 0) goto L_0x0015;
    L_0x000c:
        r2 = r6.hasResolution();
        if (r2 == 0) goto L_0x002f;
    L_0x0012:
        r2 = r0;
    L_0x0013:
        if (r2 == 0) goto L_0x003f;
    L_0x0015:
        r2 = r5.zzfnr;
        if (r2 == 0) goto L_0x001d;
    L_0x0019:
        r2 = r5.zzfoe;
        if (r3 >= r2) goto L_0x003f;
    L_0x001d:
        if (r0 == 0) goto L_0x0023;
    L_0x001f:
        r5.zzfnr = r6;
        r5.zzfoe = r3;
    L_0x0023:
        r0 = r5.zzfob;
        r0 = r0.zzfpw;
        r1 = r7.zzaft();
        r0.put(r1, r6);
        return;
    L_0x002f:
        r2 = r5.zzfni;
        r4 = r6.getErrorCode();
        r2 = r2.zzbp(r4);
        if (r2 == 0) goto L_0x003d;
    L_0x003b:
        r2 = r0;
        goto L_0x0013;
    L_0x003d:
        r2 = r1;
        goto L_0x0013;
    L_0x003f:
        r0 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzar.zzb(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    private final void zzbf(boolean z) {
        if (this.zzfoj != null) {
            if (this.zzfoj.isConnected() && z) {
                this.zzfoj.zzbcp();
            }
            this.zzfoj.disconnect();
            this.zzfon = null;
        }
    }

    private final boolean zzbt(int i) {
        if (this.zzfof == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzfob.zzfmo.zzaia());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", "mRemainingConnections=" + this.zzfog);
        valueOf = zzbu(this.zzfof);
        String zzbu = zzbu(i);
        Log.wtf("GoogleApiClientConnecting", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(zzbu).length()).append("GoogleApiClient connecting is in step ").append(valueOf).append(" but received callback for step ").append(zzbu).toString(), new Exception());
        zze(new ConnectionResult(8, null));
        return false;
    }

    private static String zzbu(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private final boolean zzd(ConnectionResult connectionResult) {
        return this.zzfok && !connectionResult.hasResolution();
    }

    private final void zze(ConnectionResult connectionResult) {
        zzahu();
        zzbf(!connectionResult.hasResolution());
        this.zzfob.zzg(connectionResult);
        this.zzfob.zzfqa.zzc(connectionResult);
    }

    public final void begin() {
        this.zzfob.zzfpw.clear();
        this.zzfol = false;
        this.zzfnr = null;
        this.zzfof = 0;
        this.zzfok = true;
        this.zzfom = false;
        this.zzfoo = false;
        Map hashMap = new HashMap();
        int i = 0;
        for (Api api : this.zzfng.keySet()) {
            Api.zze com_google_android_gms_common_api_Api_zze = (Api.zze) this.zzfob.zzfph.get(api.zzaft());
            int i2 = (api.zzafr().getPriority() == 1 ? 1 : 0) | i;
            boolean booleanValue = ((Boolean) this.zzfng.get(api)).booleanValue();
            if (com_google_android_gms_common_api_Api_zze.zzaam()) {
                this.zzfol = true;
                if (booleanValue) {
                    this.zzfoi.add(api.zzaft());
                } else {
                    this.zzfok = false;
                }
            }
            hashMap.put(com_google_android_gms_common_api_Api_zze, new zzat(this, api, booleanValue));
            i = i2;
        }
        if (i != 0) {
            this.zzfol = false;
        }
        if (this.zzfol) {
            this.zzfnd.zzc(Integer.valueOf(System.identityHashCode(this.zzfob.zzfmo)));
            ConnectionCallbacks com_google_android_gms_common_api_internal_zzba = new zzba();
            this.zzfoj = (zzcwb) this.zzfkf.zza(this.mContext, this.zzfob.zzfmo.getLooper(), this.zzfnd, this.zzfnd.zzakp(), com_google_android_gms_common_api_internal_zzba, com_google_android_gms_common_api_internal_zzba);
        }
        this.zzfog = this.zzfob.zzfph.size();
        this.zzfoq.add(zzbo.zzaid().submit(new zzau(this, hashMap)));
    }

    public final void connect() {
    }

    public final boolean disconnect() {
        zzahu();
        zzbf(true);
        this.zzfob.zzg(null);
        return true;
    }

    public final void onConnected(Bundle bundle) {
        if (zzbt(1)) {
            if (bundle != null) {
                this.zzfoh.putAll(bundle);
            }
            if (zzahq()) {
                zzahs();
            }
        }
    }

    public final void onConnectionSuspended(int i) {
        zze(new ConnectionResult(8, null));
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zzbt(1)) {
            zzb(connectionResult, api, z);
            if (zzahq()) {
                zzahs();
            }
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        this.zzfob.zzfmo.zzfnm.add(t);
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
