package com.google.firebase.iid;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FirebaseInstanceId {
    private static Map<String, FirebaseInstanceId> zzick = new ArrayMap();
    private static final long zznts = TimeUnit.HOURS.toSeconds(8);
    private static zzj zzntt;
    private static ScheduledThreadPoolExecutor zzntu;
    private final FirebaseApp zzmfl;
    private final zzi zzntv;
    private final String zzntw;
    private boolean zzntx = false;

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzi com_google_firebase_iid_zzi) {
        this.zzmfl = firebaseApp;
        this.zzntv = com_google_firebase_iid_zzi;
        String gcmSenderId = this.zzmfl.getOptions().getGcmSenderId();
        if (gcmSenderId == null) {
            gcmSenderId = this.zzmfl.getOptions().getApplicationId();
            if (gcmSenderId.startsWith("1:")) {
                String[] split = gcmSenderId.split(":");
                if (split.length < 2) {
                    gcmSenderId = null;
                } else {
                    gcmSenderId = split[1];
                    if (gcmSenderId.isEmpty()) {
                        gcmSenderId = null;
                    }
                }
            }
        }
        this.zzntw = gcmSenderId;
        if (this.zzntw == null) {
            throw new IllegalStateException("IID failing to initialize, FirebaseApp is missing project ID");
        }
        zzac zzcgw = zzcgw();
        if (zzcgw == null || zzcgw.zzqy(zzi.zzicq) || zzntt.zzche() != null) {
            startSync();
        }
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static synchronized FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseInstanceId firebaseInstanceId;
        synchronized (FirebaseInstanceId.class) {
            firebaseInstanceId = (FirebaseInstanceId) zzick.get(firebaseApp.getOptions().getApplicationId());
            if (firebaseInstanceId == null) {
                zzi zza = zzi.zza(firebaseApp.getApplicationContext(), null);
                if (zzntt == null) {
                    zzntt = new zzj(zzi.zzcha());
                }
                firebaseInstanceId = new FirebaseInstanceId(firebaseApp, zza);
                zzick.put(firebaseApp.getOptions().getApplicationId(), firebaseInstanceId);
            }
        }
        return firebaseInstanceId;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final void zzac(Bundle bundle) {
        bundle.putString("gmp_app_id", this.zzmfl.getOptions().getApplicationId());
    }

    static int zzam(Context context, String str) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to find package ").append(valueOf).toString());
            return i;
        }
    }

    static void zzb(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzntu == null) {
                zzntu = new ScheduledThreadPoolExecutor(1);
            }
            zzntu.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    static zzj zzcgy() {
        return zzntt;
    }

    static boolean zzcgz() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    static String zzdk(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static int zzes(Context context) {
        return zzam(context, context.getPackageName());
    }

    static String zzn(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    @WorkerThread
    public void deleteInstanceId() throws IOException {
        this.zzntv.zza("*", "*", null);
        this.zzntv.zzaut();
    }

    @WorkerThread
    public void deleteToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzac(bundle);
        this.zzntv.zza(str, str2, bundle);
    }

    final FirebaseApp getApp() {
        return this.zzmfl;
    }

    public long getCreationTime() {
        return this.zzntv.getCreationTime();
    }

    @WorkerThread
    public String getId() {
        return zza(this.zzntv.zzaus());
    }

    @Nullable
    public String getToken() {
        zzac zzcgw = zzcgw();
        if (zzcgw == null || zzcgw.zzqy(zzi.zzicq)) {
            startSync();
        }
        return zzcgw != null ? zzcgw.zzlan : null;
    }

    @WorkerThread
    public String getToken(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        zzac(bundle);
        return this.zzntv.getToken(str, str2, bundle);
    }

    final synchronized void startSync() {
        if (!this.zzntx) {
            zzcb(0);
        }
    }

    final synchronized void zzcb(long j) {
        zzb(new zzad(this, Math.min(Math.max(30, j << 1), zznts)), j);
        this.zzntx = true;
    }

    @Nullable
    final zzac zzcgw() {
        return zzi.zzcha().zzo("", this.zzntw, "*");
    }

    final String zzcgx() throws IOException {
        return getToken(this.zzntw, "*");
    }

    final synchronized void zzco(boolean z) {
        this.zzntx = z;
    }

    public final synchronized void zzqp(String str) {
        zzntt.zzqp(str);
        startSync();
    }

    final void zzqq(String str) throws IOException {
        zzac zzcgw = zzcgw();
        if (zzcgw == null || zzcgw.zzqy(zzi.zzicq)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String str3 = zzcgw.zzlan;
        str2 = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        valueOf2 = valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2);
        zzac(bundle);
        this.zzntv.zzb(str3, valueOf2, bundle);
    }

    final void zzqr(String str) throws IOException {
        zzac zzcgw = zzcgw();
        if (zzcgw == null || zzcgw.zzqy(zzi.zzicq)) {
            throw new IOException("token not available");
        }
        Bundle bundle = new Bundle();
        String str2 = "gcm.topic";
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str);
        bundle.putString(str2, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        zzi com_google_firebase_iid_zzi = this.zzntv;
        String str3 = zzcgw.zzlan;
        valueOf = String.valueOf("/topics/");
        valueOf2 = String.valueOf(str);
        com_google_firebase_iid_zzi.zza(str3, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), bundle);
    }
}
