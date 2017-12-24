package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import com.evernote.android.job.JobRequest;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzey;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@KeepForSdkWithMembers
public class AdvertisingIdClient {
    private final Context mContext;
    @Nullable
    private com.google.android.gms.common.zza zzalv;
    @Nullable
    private zzex zzalw;
    private boolean zzalx;
    private Object zzaly;
    @Nullable
    private zza zzalz;
    private boolean zzama;
    private long zzamb;

    public static final class Info {
        private final String zzamh;
        private final boolean zzami;

        public Info(String str, boolean z) {
            this.zzamh = str;
            this.zzami = z;
        }

        public final String getId() {
            return this.zzamh;
        }

        public final boolean isLimitAdTrackingEnabled() {
            return this.zzami;
        }

        public final String toString() {
            String str = this.zzamh;
            return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append("}").append(this.zzami).toString();
        }
    }

    static class zza extends Thread {
        private WeakReference<AdvertisingIdClient> zzamd;
        private long zzame;
        CountDownLatch zzamf = new CountDownLatch(1);
        boolean zzamg = false;

        public zza(AdvertisingIdClient advertisingIdClient, long j) {
            this.zzamd = new WeakReference(advertisingIdClient);
            this.zzame = j;
            start();
        }

        private final void disconnect() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.zzamd.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzamg = true;
            }
        }

        public final void run() {
            try {
                if (!this.zzamf.await(this.zzame, TimeUnit.MILLISECONDS)) {
                    disconnect();
                }
            } catch (InterruptedException e) {
                disconnect();
            }
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, JobRequest.DEFAULT_BACKOFF_MS, false, false);
    }

    public AdvertisingIdClient(Context context, long j, boolean z, boolean z2) {
        this.zzaly = new Object();
        zzbq.checkNotNull(context);
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            this.mContext = context;
        } else {
            this.mContext = context;
        }
        this.zzalx = false;
        this.zzamb = j;
        this.zzama = z2;
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzd com_google_android_gms_ads_identifier_zzd = new zzd(context);
        boolean z = com_google_android_gms_ads_identifier_zzd.getBoolean("gads:ad_id_app_context:enabled", false);
        float f = com_google_android_gms_ads_identifier_zzd.getFloat("gads:ad_id_app_context:ping_ratio", 0.0f);
        boolean z2 = com_google_android_gms_ads_identifier_zzd.getBoolean("gads:ad_id_use_shared_preference:enabled", false);
        String string = com_google_android_gms_ads_identifier_zzd.getString("gads:ad_id_use_shared_preference:experiment_id", "");
        boolean z3 = com_google_android_gms_ads_identifier_zzd.getBoolean("com.google.android.gms.ads.identifier.service.PERSISTENT_START", false);
        if (z2) {
            Info info = zzb.zzc(context).getInfo();
            if (info != null) {
                return info;
            }
        }
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1, z, z3);
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            advertisingIdClient.start(false);
            Info info2 = advertisingIdClient.getInfo();
            advertisingIdClient.zza(info2, z, f, SystemClock.elapsedRealtime() - elapsedRealtime, string, null);
            advertisingIdClient.finish();
            return info2;
        } catch (Throwable th) {
            advertisingIdClient.finish();
        }
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    private final void start(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzbq.zzgi("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzalx) {
                finish();
            }
            this.zzalv = zzc(this.mContext, this.zzama);
            this.zzalw = zza(this.mContext, this.zzalv);
            this.zzalx = true;
            if (z) {
                zzbj();
            }
        }
    }

    private static zzex zza(Context context, com.google.android.gms.common.zza com_google_android_gms_common_zza) throws IOException {
        try {
            return zzey.zzc(com_google_android_gms_common_zza.zza(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    private final boolean zza(Info info, boolean z, float f, long j, String str, Throwable th) {
        if (Math.random() > ((double) f)) {
            return false;
        }
        Map hashMap = new HashMap();
        hashMap.put("app_context", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        if (info != null) {
            hashMap.put("limit_ad_tracking", info.isLimitAdTrackingEnabled() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (!(info == null || info.getId() == null)) {
            hashMap.put("ad_id_size", Integer.toString(info.getId().length()));
        }
        if (th != null) {
            hashMap.put("error", th.getClass().getName());
        }
        if (!(str == null || str.isEmpty())) {
            hashMap.put("experiment_id", str);
        }
        hashMap.put(JobStorage.COLUMN_TAG, "AdvertisingIdClient");
        hashMap.put("time_spent", Long.toString(j));
        new zza(this, hashMap).start();
        return true;
    }

    private final void zzbj() {
        synchronized (this.zzaly) {
            if (this.zzalz != null) {
                this.zzalz.zzamf.countDown();
                try {
                    this.zzalz.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.zzamb > 0) {
                this.zzalz = new zza(this, this.zzamb);
            }
        }
    }

    private static com.google.android.gms.common.zza zzc(Context context, boolean z) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (zze.zzafm().isGooglePlayServicesAvailable(context)) {
                case 0:
                case 2:
                    String str = z ? "com.google.android.gms.ads.identifier.service.PERSISTENT_START" : AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT;
                    Object com_google_android_gms_common_zza = new com.google.android.gms.common.zza();
                    Intent intent = new Intent(str);
                    intent.setPackage("com.google.android.gms");
                    try {
                        if (com.google.android.gms.common.stats.zza.zzalq().zza(context, intent, com_google_android_gms_common_zza, 1)) {
                            return com_google_android_gms_common_zza;
                        }
                        throw new IOException("Connection failure");
                    } catch (Throwable th) {
                        IOException iOException = new IOException(th);
                    }
                default:
                    throw new IOException("Google Play services not available");
            }
        } catch (NameNotFoundException e) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finish() {
        /*
        r3 = this;
        r0 = "Calling this from your main thread can lead to deadlock";
        com.google.android.gms.common.internal.zzbq.zzgi(r0);
        monitor-enter(r3);
        r0 = r3.mContext;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x000e;
    L_0x000a:
        r0 = r3.zzalv;	 Catch:{ all -> 0x0029 }
        if (r0 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r3);	 Catch:{ all -> 0x0029 }
    L_0x000f:
        return;
    L_0x0010:
        r0 = r3.zzalx;	 Catch:{ Throwable -> 0x002c }
        if (r0 == 0) goto L_0x001e;
    L_0x0014:
        com.google.android.gms.common.stats.zza.zzalq();	 Catch:{ Throwable -> 0x002c }
        r0 = r3.mContext;	 Catch:{ Throwable -> 0x002c }
        r1 = r3.zzalv;	 Catch:{ Throwable -> 0x002c }
        r0.unbindService(r1);	 Catch:{ Throwable -> 0x002c }
    L_0x001e:
        r0 = 0;
        r3.zzalx = r0;	 Catch:{ all -> 0x0029 }
        r0 = 0;
        r3.zzalw = r0;	 Catch:{ all -> 0x0029 }
        r0 = 0;
        r3.zzalv = r0;	 Catch:{ all -> 0x0029 }
        monitor-exit(r3);	 Catch:{ all -> 0x0029 }
        goto L_0x000f;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        r0 = move-exception;
        r1 = "AdvertisingIdClient";
        r2 = "AdvertisingIdClient unbindService failed.";
        android.util.Log.i(r1, r2, r0);	 Catch:{ all -> 0x0029 }
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.finish():void");
    }

    public Info getInfo() throws IOException {
        Info info;
        zzbq.zzgi("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.zzalx) {
                synchronized (this.zzaly) {
                    if (this.zzalz == null || !this.zzalz.zzamg) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    start(false);
                    if (!this.zzalx) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (Throwable e) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                    throw new IOException("Remote exception");
                } catch (Throwable e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            zzbq.checkNotNull(this.zzalv);
            zzbq.checkNotNull(this.zzalw);
            info = new Info(this.zzalw.getId(), this.zzalw.zzb(true));
        }
        zzbj();
        return info;
    }

    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        start(true);
    }
}
