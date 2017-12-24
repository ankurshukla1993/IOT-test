package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;

public final class zzi {
    private static Map<String, zzi> zzick = new ArrayMap();
    static String zzicq;
    private static zzab zznua;
    private static zzw zznub;
    private Context mContext;
    private KeyPair zzicn;
    String zzico = "";

    private zzi(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.zzico = str;
    }

    public static synchronized zzi zza(Context context, @Nullable Bundle bundle) {
        zzi zza;
        synchronized (zzi.class) {
            zza = zza(context, null, null);
        }
        return zza;
    }

    public static synchronized zzi zza(Context context, @Nullable String str, @Nullable Bundle bundle) {
        zzi com_google_firebase_iid_zzi;
        synchronized (zzi.class) {
            if (str == null) {
                str = "";
            }
            Context applicationContext = context.getApplicationContext();
            if (zznua == null) {
                zznua = new zzab(applicationContext);
                zznub = new zzw(applicationContext);
            }
            zzicq = Integer.toString(FirebaseInstanceId.zzes(applicationContext));
            com_google_firebase_iid_zzi = (zzi) zzick.get(str);
            if (com_google_firebase_iid_zzi == null) {
                com_google_firebase_iid_zzi = new zzi(applicationContext, str, null);
                zzick.put(str, com_google_firebase_iid_zzi);
            }
        }
        return com_google_firebase_iid_zzi;
    }

    public static zzab zzcha() {
        return zznua;
    }

    public final long getCreationTime() {
        return zznua.zzqt(this.zzico);
    }

    public final String getToken(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj = 1;
        if (bundle.getString("ttl") != null || "jwt".equals(bundle.getString("type"))) {
            obj = null;
        } else {
            zzac zzo = zznua.zzo(this.zzico, str, str2);
            if (!(zzo == null || zzo.zzqy(zzicq))) {
                return zzo.zzlan;
            }
        }
        String zzb = zzb(str, str2, bundle);
        if (zzb == null || r0 == null) {
            return zzb;
        }
        zznua.zza(this.zzico, str, str2, zzb, zzicq);
        return zzb;
    }

    public final void zza(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zznua.zzf(this.zzico, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("delete", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        zzb(str, str2, bundle);
    }

    final KeyPair zzaus() {
        if (this.zzicn == null) {
            this.zzicn = zznua.zzqw(this.zzico);
        }
        if (this.zzicn == null) {
            this.zzicn = zznua.zzqu(this.zzico);
        }
        return this.zzicn;
    }

    public final void zzaut() {
        zznua.zzqv(this.zzico);
        this.zzicn = null;
    }

    public final String zzb(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "subtype";
        if (!"".equals(this.zzico)) {
            str = this.zzico;
        }
        bundle.putString(str3, str);
        Bundle zzc = zznub.zzc(bundle, zzaus());
        if (zzc == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        str3 = zzc.getString("registration_id");
        if (str3 == null) {
            str3 = zzc.getString("unregistered");
            if (str3 == null) {
                str3 = zzc.getString("error");
                if (str3 != null) {
                    throw new IOException(str3);
                }
                String valueOf = String.valueOf(zzc);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 20).append("Unexpected response ").append(valueOf).toString(), new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        }
        return str3;
    }

    final void zzchb() {
        zzaut();
        FirebaseInstanceId.getInstance().startSync();
    }

    final void zzchc() {
        if (!zznua.isEmpty()) {
            zzaut();
            zznua.zzaux();
            FirebaseInstanceId.getInstance().startSync();
        }
    }

    final void zzchd() {
        zznua.zzhu(this.zzico);
        FirebaseInstanceId.getInstance().startSync();
    }
}
