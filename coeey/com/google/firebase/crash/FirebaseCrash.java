package com.google.firebase.crash;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.apps.common.proguard.UsedByReflection;
import com.google.android.gms.internal.zzdxn;
import com.google.android.gms.internal.zzdxo;
import com.google.android.gms.internal.zzdxp;
import com.google.android.gms.internal.zzdxq;
import com.google.android.gms.internal.zzdxt;
import com.google.android.gms.internal.zzdxz;
import com.google.firebase.FirebaseApp;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@UsedByReflection("FirebaseApp")
public class FirebaseCrash {
    private static volatile FirebaseCrash zzmdi;
    private final Context mContext;
    private final FirebaseApp zzlyo;
    private final ExecutorService zzmdj;
    private final zzb zzmdk;
    private final CountDownLatch zzmdl = new CountDownLatch(1);
    private zzdxz zzmdm;

    public interface zza {
        @Nullable
        zzdxt zzbqq();
    }

    static final class zzb implements zza {
        private final Object zzmdo;
        private zzdxt zzmdp;

        private zzb() {
            this.zzmdo = new Object();
        }

        private final void zzb(@Nullable zzdxt com_google_android_gms_internal_zzdxt) {
            synchronized (this.zzmdo) {
                this.zzmdp = com_google_android_gms_internal_zzdxt;
            }
        }

        @Nullable
        public final zzdxt zzbqq() {
            zzdxt com_google_android_gms_internal_zzdxt;
            synchronized (this.zzmdo) {
                com_google_android_gms_internal_zzdxt = this.zzmdp;
            }
            return com_google_android_gms_internal_zzdxt;
        }
    }

    class zzc implements UncaughtExceptionHandler {
        private /* synthetic */ FirebaseCrash zzmdn;
        private final UncaughtExceptionHandler zzmdq;

        public zzc(@Nullable FirebaseCrash firebaseCrash, UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.zzmdn = firebaseCrash;
            this.zzmdq = uncaughtExceptionHandler;
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            Log.e("UncaughtException", "", th);
            if (!this.zzmdn.zzbqo()) {
                try {
                    Future zzh = this.zzmdn.zzh(th);
                    if (zzh != null) {
                        zzh.get(10000, TimeUnit.MILLISECONDS);
                    }
                } catch (Throwable e) {
                    Log.e("UncaughtException", "Ouch! My own exception handler threw an exception.", e);
                }
            }
            if (this.zzmdq != null) {
                this.zzmdq.uncaughtException(thread, th);
            }
        }
    }

    private FirebaseCrash(@NonNull FirebaseApp firebaseApp, @NonNull ExecutorService executorService) {
        this.zzlyo = firebaseApp;
        this.zzmdj = executorService;
        this.mContext = this.zzlyo.getApplicationContext();
        this.zzmdk = new zzb();
    }

    @Keep
    @UsedByReflection("FirebaseApp")
    public static FirebaseCrash getInstance(FirebaseApp firebaseApp) {
        if (zzmdi == null) {
            synchronized (FirebaseCrash.class) {
                if (zzmdi == null) {
                    ExecutorService threadPoolExecutor = new ThreadPoolExecutor(1, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    FirebaseCrash firebaseCrash = new FirebaseCrash(firebaseApp, threadPoolExecutor);
                    zzc com_google_firebase_crash_zzc = new zzc(firebaseApp, null);
                    Thread.setDefaultUncaughtExceptionHandler(new zzc(firebaseCrash, Thread.getDefaultUncaughtExceptionHandler()));
                    zzf com_google_firebase_crash_zzb = new zzb(firebaseCrash);
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
                    newFixedThreadPool.submit(new zze(com_google_firebase_crash_zzc, newFixedThreadPool.submit(new zzd(com_google_firebase_crash_zzc)), 10000, com_google_firebase_crash_zzb));
                    newFixedThreadPool.shutdown();
                    firebaseCrash.zzmdj.execute(new zza(firebaseCrash));
                    zzmdi = firebaseCrash;
                }
            }
        }
        return zzmdi;
    }

    public static boolean isCrashCollectionEnabled() {
        return zzbqn().zzbqp();
    }

    public static void log(String str) {
        zzbqn().zzoo(str);
    }

    public static void logcat(int i, String str, String str2) {
        FirebaseCrash zzbqn = zzbqn();
        if (str2 != null) {
            if (str == null) {
                str = "";
            }
            Log.println(i, str, str2);
            zzbqn.zzoo(str2);
        }
    }

    public static void report(Throwable th) {
        FirebaseCrash zzbqn = zzbqn();
        if (th != null && !zzbqn.zzbqo()) {
            zzbqn.zzmdj.submit(new zzdxn(zzbqn.mContext, zzbqn.zzmdk, th, zzbqn.zzmdm));
        }
    }

    public static void setCrashCollectionEnabled(boolean z) {
        FirebaseCrash zzbqn = zzbqn();
        if (!zzbqn.zzbqo()) {
            zzbqn.zzmdj.submit(new zzdxq(zzbqn.mContext, zzbqn.zzmdk, z));
        }
    }

    private final void zzbqm() {
        try {
            this.zzmdl.await(20000, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            Log.e("FirebaseCrash", "Failed waiting for crash api to load.", e);
        }
    }

    private static FirebaseCrash zzbqn() {
        return zzmdi != null ? zzmdi : getInstance(FirebaseApp.getInstance());
    }

    private final boolean zzbqp() {
        boolean z = false;
        if (!zzbqo()) {
            zzbqm();
            zzdxt zzbqq = this.zzmdk.zzbqq();
            if (zzbqq != null) {
                try {
                    z = zzbqq.zzbqp();
                } catch (RemoteException e) {
                }
            }
        }
        return z;
    }

    private final void zzoo(String str) {
        if (str != null && !zzbqo()) {
            this.zzmdj.submit(new zzdxo(this.mContext, this.zzmdk, str));
        }
    }

    final void zza(@Nullable zzdxt com_google_android_gms_internal_zzdxt) {
        if (com_google_android_gms_internal_zzdxt == null) {
            this.zzmdj.shutdownNow();
        } else {
            this.zzmdm = zzdxz.zzer(this.mContext);
            this.zzmdk.zzb(com_google_android_gms_internal_zzdxt);
            if (!(this.zzmdm == null || zzbqo())) {
                this.zzmdm.zza(this.mContext, this.zzmdj, this.zzmdk);
                Log.d("FirebaseCrash", "Firebase Analytics Listener for Firebase Crash is initialized");
            }
        }
        this.zzmdl.countDown();
    }

    public final boolean zzbqo() {
        return this.zzmdj.isShutdown();
    }

    @Nullable
    final Future<?> zzh(Throwable th) {
        return (th == null || zzbqo()) ? null : this.zzmdj.submit(new zzdxp(this.mContext, this.zzmdk, th, this.zzmdm));
    }
}
