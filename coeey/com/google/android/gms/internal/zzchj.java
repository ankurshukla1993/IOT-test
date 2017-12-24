package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.evernote.android.job.JobRequest;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.stetho.server.http.HttpStatus;
import com.google.android.gms.common.api.internal.zzcc;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.time.DateTimeConstants;

public class zzchj {
    private static volatile zzchj zzjca;
    private final Context mContext;
    private final zzd zzasd;
    private boolean zzdqd = false;
    private final zzcfk zzjcb;
    private final zzcgu zzjcc;
    private final zzcgj zzjcd;
    private final zzche zzjce;
    private final zzckc zzjcf;
    private final zzchd zzjcg;
    private final AppMeasurement zzjch;
    private final FirebaseAnalytics zzjci;
    private final zzckn zzjcj;
    private final zzcgh zzjck;
    private final zzcfl zzjcl;
    private final zzcgf zzjcm;
    private final zzcgn zzjcn;
    private final zzciz zzjco;
    private final zzcjd zzjcp;
    private final zzcfr zzjcq;
    private final zzcik zzjcr;
    private final zzcge zzjcs;
    private final zzcgs zzjct;
    private final zzcki zzjcu;
    private final zzcfh zzjcv;
    private final zzcfa zzjcw;
    private boolean zzjcx;
    private Boolean zzjcy;
    private long zzjcz;
    private FileLock zzjda;
    private FileChannel zzjdb;
    private List<Long> zzjdc;
    private List<Runnable> zzjdd;
    private int zzjde;
    private int zzjdf;
    private long zzjdg;
    private long zzjdh;
    private boolean zzjdi;
    private boolean zzjdj;
    private boolean zzjdk;
    private final long zzjdl;

    class zza implements zzcfn {
        List<zzcky> zzaof;
        private /* synthetic */ zzchj zzjdm;
        zzclb zzjdn;
        List<Long> zzjdo;
        private long zzjdp;

        private zza(zzchj com_google_android_gms_internal_zzchj) {
            this.zzjdm = com_google_android_gms_internal_zzchj;
        }

        private static long zza(zzcky com_google_android_gms_internal_zzcky) {
            return ((com_google_android_gms_internal_zzcky.zzjin.longValue() / 1000) / 60) / 60;
        }

        public final boolean zza(long j, zzcky com_google_android_gms_internal_zzcky) {
            zzbq.checkNotNull(com_google_android_gms_internal_zzcky);
            if (this.zzaof == null) {
                this.zzaof = new ArrayList();
            }
            if (this.zzjdo == null) {
                this.zzjdo = new ArrayList();
            }
            if (this.zzaof.size() > 0 && zza((zzcky) this.zzaof.get(0)) != zza(com_google_android_gms_internal_zzcky)) {
                return false;
            }
            long zzhl = this.zzjdp + ((long) com_google_android_gms_internal_zzcky.zzhl());
            if (zzhl >= ((long) Math.max(0, ((Integer) zzcfz.zzixq.get()).intValue()))) {
                return false;
            }
            this.zzjdp = zzhl;
            this.zzaof.add(com_google_android_gms_internal_zzcky);
            this.zzjdo.add(Long.valueOf(j));
            return this.zzaof.size() < Math.max(1, ((Integer) zzcfz.zzixr.get()).intValue());
        }

        public final void zzb(zzclb com_google_android_gms_internal_zzclb) {
            zzbq.checkNotNull(com_google_android_gms_internal_zzclb);
            this.zzjdn = com_google_android_gms_internal_zzclb;
        }
    }

    private zzchj(zzcij com_google_android_gms_internal_zzcij) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcij);
        this.mContext = com_google_android_gms_internal_zzcij.mContext;
        this.zzjdg = -1;
        this.zzasd = zzh.zzalu();
        this.zzjdl = this.zzasd.currentTimeMillis();
        this.zzjcb = new zzcfk(this);
        zzcii com_google_android_gms_internal_zzcgu = new zzcgu(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcc = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcgj(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcd = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzckn(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcj = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcgh(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjck = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcfr(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcq = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcge(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcs = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcfl(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcl = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcgf(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcm = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcfh(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcv = com_google_android_gms_internal_zzcgu;
        this.zzjcw = new zzcfa(this);
        com_google_android_gms_internal_zzcgu = new zzcgn(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcn = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzciz(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjco = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcjd(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcp = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcik(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcr = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzcki(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcu = com_google_android_gms_internal_zzcgu;
        this.zzjct = new zzcgs(this);
        this.zzjch = new AppMeasurement(this);
        this.zzjci = new FirebaseAnalytics(this);
        com_google_android_gms_internal_zzcgu = new zzckc(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcf = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzchd(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjcg = com_google_android_gms_internal_zzcgu;
        com_google_android_gms_internal_zzcgu = new zzche(this);
        com_google_android_gms_internal_zzcgu.initialize();
        this.zzjce = com_google_android_gms_internal_zzcgu;
        if (this.mContext.getApplicationContext() instanceof Application) {
            zzcih zzawa = zzawa();
            if (zzawa.getContext().getApplicationContext() instanceof Application) {
                Application application = (Application) zzawa.getContext().getApplicationContext();
                if (zzawa.zzjec == null) {
                    zzawa.zzjec = new zzciy(zzawa);
                }
                application.unregisterActivityLifecycleCallbacks(zzawa.zzjec);
                application.registerActivityLifecycleCallbacks(zzawa.zzjec);
                zzawa.zzawm().zzayx().log("Registered activity lifecycle callback");
            }
        } else {
            zzawm().zzayt().log("Application context is not an Application");
        }
        this.zzjce.zzg(new zzchk(this));
    }

    @WorkerThread
    private final int zza(FileChannel fileChannel) {
        int i = 0;
        zzawl().zzut();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzawm().zzayr().log("Bad chanel to read from");
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            try {
                fileChannel.position(0);
                int read = fileChannel.read(allocate);
                if (read == 4) {
                    allocate.flip();
                    i = allocate.getInt();
                } else if (read != -1) {
                    zzawm().zzayt().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
            } catch (IOException e) {
                zzawm().zzayr().zzj("Failed to read from channel", e);
            }
        }
        return i;
    }

    private final zzcff zza(Context context, String str, String str2, boolean z, boolean z2) {
        Object charSequence;
        String str3 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        String str4 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int i = Integer.MIN_VALUE;
        String str5 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            zzawm().zzayr().log("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str3 = packageManager.getInstallerPackageName(str);
        } catch (IllegalArgumentException e) {
            zzawm().zzayr().zzj("Error retrieving installer package name. appId", zzcgj.zzje(str));
        }
        if (str3 == null) {
            str3 = "manual_install";
        } else if ("com.android.vending".equals(str3)) {
            str3 = "";
        }
        try {
            PackageInfo packageInfo = zzbgc.zzcy(context).getPackageInfo(str, 0);
            if (packageInfo != null) {
                CharSequence zzgo = zzbgc.zzcy(context).zzgo(str);
                if (TextUtils.isEmpty(zzgo)) {
                    String str6 = str5;
                } else {
                    charSequence = zzgo.toString();
                }
                try {
                    str4 = packageInfo.versionName;
                    i = packageInfo.versionCode;
                } catch (NameNotFoundException e2) {
                    zzawm().zzayr().zze("Error retrieving newly installed package info. appId, appName", zzcgj.zzje(str), charSequence);
                    return null;
                }
            }
            return new zzcff(str, str2, str4, (long) i, str3, 11720, zzawi().zzaf(context, str), null, z, false, "", 0, 0, 0, z2);
        } catch (NameNotFoundException e3) {
            charSequence = str5;
            zzawm().zzayr().zze("Error retrieving newly installed package info. appId, appName", zzcgj.zzje(str), charSequence);
            return null;
        }
    }

    private static void zza(zzcih com_google_android_gms_internal_zzcih) {
        if (com_google_android_gms_internal_zzcih == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzcii com_google_android_gms_internal_zzcii) {
        if (com_google_android_gms_internal_zzcii == null) {
            throw new IllegalStateException("Component not created");
        } else if (!com_google_android_gms_internal_zzcii.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    @WorkerThread
    private final boolean zza(int i, FileChannel fileChannel) {
        zzawl().zzut();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzawm().zzayr().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzawm().zzayr().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzawm().zzayr().zzj("Failed to write to channel", e);
            return false;
        }
    }

    private static boolean zza(zzcky com_google_android_gms_internal_zzcky, String str, Object obj) {
        if (TextUtils.isEmpty(str) || obj == null) {
            return false;
        }
        zzckz[] com_google_android_gms_internal_zzckzArr = com_google_android_gms_internal_zzcky.zzjim;
        int length = com_google_android_gms_internal_zzckzArr.length;
        int i = 0;
        while (i < length) {
            zzckz com_google_android_gms_internal_zzckz = com_google_android_gms_internal_zzckzArr[i];
            if (str.equals(com_google_android_gms_internal_zzckz.name)) {
                return ((obj instanceof Long) && obj.equals(com_google_android_gms_internal_zzckz.zzjiq)) || (((obj instanceof String) && obj.equals(com_google_android_gms_internal_zzckz.zzfzi)) || ((obj instanceof Double) && obj.equals(com_google_android_gms_internal_zzckz.zzjgq)));
            } else {
                i++;
            }
        }
        return false;
    }

    private final boolean zza(String str, zzcfx com_google_android_gms_internal_zzcfx) {
        long round;
        Object string = com_google_android_gms_internal_zzcfx.zziwy.getString(Param.CURRENCY);
        if (Event.ECOMMERCE_PURCHASE.equals(com_google_android_gms_internal_zzcfx.name)) {
            double doubleValue = com_google_android_gms_internal_zzcfx.zziwy.getDouble("value").doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                doubleValue = ((double) com_google_android_gms_internal_zzcfx.zziwy.getLong("value").longValue()) * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                zzawm().zzayt().zze("Data lost. Currency value is too big. appId", zzcgj.zzje(str), Double.valueOf(doubleValue));
                return false;
            }
            round = Math.round(doubleValue);
        } else {
            round = com_google_android_gms_internal_zzcfx.zziwy.getLong("value").longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String toUpperCase = string.toUpperCase(Locale.US);
            if (toUpperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                toUpperCase = String.valueOf(toUpperCase);
                String concat = toUpperCase.length() != 0 ? valueOf.concat(toUpperCase) : new String(valueOf);
                zzckm zzag = zzawg().zzag(str, concat);
                if (zzag == null || !(zzag.mValue instanceof Long)) {
                    zzcih zzawg = zzawg();
                    int zzb = this.zzjcb.zzb(str, zzcfz.zziym) - 1;
                    zzbq.zzgh(str);
                    zzawg.zzut();
                    zzawg.zzwu();
                    try {
                        zzawg.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzawg.zzawm().zzayr().zze("Error pruning currencies. appId", zzcgj.zzje(str), e);
                    }
                    zzag = new zzckm(str, com_google_android_gms_internal_zzcfx.zzivk, concat, this.zzasd.currentTimeMillis(), Long.valueOf(round));
                } else {
                    zzag = new zzckm(str, com_google_android_gms_internal_zzcfx.zzivk, concat, this.zzasd.currentTimeMillis(), Long.valueOf(round + ((Long) zzag.mValue).longValue()));
                }
                if (!zzawg().zza(zzag)) {
                    zzawm().zzayr().zzd("Too many unique user properties are set. Ignoring user property. appId", zzcgj.zzje(str), zzawh().zzjd(zzag.mName), zzag.mValue);
                    zzawi().zza(str, 9, null, null, 0);
                }
            }
        }
        return true;
    }

    private final zzckx[] zza(String str, zzcld[] com_google_android_gms_internal_zzcldArr, zzcky[] com_google_android_gms_internal_zzckyArr) {
        zzbq.zzgh(str);
        return zzavz().zza(str, com_google_android_gms_internal_zzckyArr, com_google_android_gms_internal_zzcldArr);
    }

    static void zzavw() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    @WorkerThread
    private final void zzazk() {
        zzcgl zzayv;
        zzawl().zzut();
        this.zzjcj.zzazk();
        this.zzjcc.zzazk();
        this.zzjcs.zzazk();
        zzawm().zzayv().zzj("App measurement is starting up, version", Long.valueOf(11720));
        zzawm().zzayv().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String appId = this.zzjcs.getAppId();
        if (zzawi().zzkd(appId)) {
            zzayv = zzawm().zzayv();
            appId = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
        } else {
            zzayv = zzawm().zzayv();
            String str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
            appId = String.valueOf(appId);
            appId = appId.length() != 0 ? str.concat(appId) : new String(str);
        }
        zzayv.log(appId);
        zzawm().zzayw().log("Debug-level message logging enabled");
        if (this.zzjde != this.zzjdf) {
            zzawm().zzayr().zze("Not all components initialized", Integer.valueOf(this.zzjde), Integer.valueOf(this.zzjdf));
        }
        this.zzdqd = true;
    }

    private final zzcgs zzazq() {
        if (this.zzjct != null) {
            return this.zzjct;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzcki zzazr() {
        zza(this.zzjcu);
        return this.zzjcu;
    }

    @WorkerThread
    private final boolean zzazs() {
        zzawl().zzut();
        try {
            this.zzjdb = new RandomAccessFile(new File(this.mContext.getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzjda = this.zzjdb.tryLock();
            if (this.zzjda != null) {
                zzawm().zzayx().log("Storage concurrent access okay");
                return true;
            }
            zzawm().zzayr().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzawm().zzayr().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzawm().zzayr().zzj("Failed to access storage lock file", e2);
        }
    }

    private final long zzazu() {
        long currentTimeMillis = this.zzasd.currentTimeMillis();
        zzcih zzawn = zzawn();
        zzawn.zzwu();
        zzawn.zzut();
        long j = zzawn.zzjaa.get();
        if (j == 0) {
            j = 1 + ((long) zzawn.zzawi().zzban().nextInt(DateTimeConstants.MILLIS_PER_DAY));
            zzawn.zzjaa.set(j);
        }
        return ((((j + currentTimeMillis) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzazw() {
        zzawl().zzut();
        zzwu();
        return zzawg().zzaxy() || !TextUtils.isEmpty(zzawg().zzaxt());
    }

    @WorkerThread
    private final void zzazx() {
        zzawl().zzut();
        zzwu();
        if (zzbaa()) {
            long abs;
            if (this.zzjdh > 0) {
                abs = 3600000 - Math.abs(this.zzasd.elapsedRealtime() - this.zzjdh);
                if (abs > 0) {
                    zzawm().zzayx().zzj("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                    zzazq().unregister();
                    zzazr().cancel();
                    return;
                }
                this.zzjdh = 0;
            }
            if (zzazj() && zzazw()) {
                long currentTimeMillis = this.zzasd.currentTimeMillis();
                long max = Math.max(0, ((Long) zzcfz.zziyi.get()).longValue());
                Object obj = (zzawg().zzaxz() || zzawg().zzaxu()) ? 1 : null;
                if (obj != null) {
                    CharSequence zzaxr = this.zzjcb.zzaxr();
                    abs = (TextUtils.isEmpty(zzaxr) || ".none.".equals(zzaxr)) ? Math.max(0, ((Long) zzcfz.zziyc.get()).longValue()) : Math.max(0, ((Long) zzcfz.zziyd.get()).longValue());
                } else {
                    abs = Math.max(0, ((Long) zzcfz.zziyb.get()).longValue());
                }
                long j = zzawn().zzizw.get();
                long j2 = zzawn().zzizx.get();
                long max2 = Math.max(zzawg().zzaxw(), zzawg().zzaxx());
                if (max2 == 0) {
                    currentTimeMillis = 0;
                } else {
                    max2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
                    j2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
                    j = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), j2);
                    currentTimeMillis = max2 + max;
                    if (obj != null && j > 0) {
                        currentTimeMillis = Math.min(max2, j) + abs;
                    }
                    if (!zzawi().zzf(j, abs)) {
                        currentTimeMillis = j + abs;
                    }
                    if (j2 != 0 && j2 >= max2) {
                        for (int i = 0; i < Math.min(20, Math.max(0, ((Integer) zzcfz.zziyk.get()).intValue())); i++) {
                            currentTimeMillis += (1 << i) * Math.max(0, ((Long) zzcfz.zziyj.get()).longValue());
                            if (currentTimeMillis > j2) {
                                break;
                            }
                        }
                        currentTimeMillis = 0;
                    }
                }
                if (currentTimeMillis == 0) {
                    zzawm().zzayx().log("Next upload time is 0");
                    zzazq().unregister();
                    zzazr().cancel();
                    return;
                } else if (zzazp().zzzh()) {
                    long j3 = zzawn().zzizy.get();
                    abs = Math.max(0, ((Long) zzcfz.zzixz.get()).longValue());
                    abs = !zzawi().zzf(j3, abs) ? Math.max(currentTimeMillis, abs + j3) : currentTimeMillis;
                    zzazq().unregister();
                    abs -= this.zzasd.currentTimeMillis();
                    if (abs <= 0) {
                        abs = Math.max(0, ((Long) zzcfz.zziye.get()).longValue());
                        zzawn().zzizw.set(this.zzasd.currentTimeMillis());
                    }
                    zzawm().zzayx().zzj("Upload scheduled in approximately ms", Long.valueOf(abs));
                    zzazr().zzr(abs);
                    return;
                } else {
                    zzawm().zzayx().log("No network");
                    zzazq().zzze();
                    zzazr().cancel();
                    return;
                }
            }
            zzawm().zzayx().log("Nothing to upload or uploading impossible");
            zzazq().unregister();
            zzazr().cancel();
        }
    }

    @WorkerThread
    private final void zzb(zzcfe com_google_android_gms_internal_zzcfe) {
        zzawl().zzut();
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzcfe.getGmpAppId())) {
            zzb(com_google_android_gms_internal_zzcfe.getAppId(), ScanDevice.LINK_USB, null, null, null);
            return;
        }
        String gmpAppId = com_google_android_gms_internal_zzcfe.getGmpAppId();
        String appInstanceId = com_google_android_gms_internal_zzcfe.getAppInstanceId();
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) zzcfz.zzixm.get()).encodedAuthority((String) zzcfz.zzixn.get());
        String str = "config/app/";
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", AbstractSpiCall.ANDROID_CLIENT_TYPE).appendQueryParameter("gmp_version", "11720");
        String uri = builder.build().toString();
        try {
            Map map;
            URL url = new URL(uri);
            zzawm().zzayx().zzj("Fetching remote configuration", com_google_android_gms_internal_zzcfe.getAppId());
            zzckv zzjm = zzawj().zzjm(com_google_android_gms_internal_zzcfe.getAppId());
            CharSequence zzjn = zzawj().zzjn(com_google_android_gms_internal_zzcfe.getAppId());
            if (zzjm == null || TextUtils.isEmpty(zzjn)) {
                map = null;
            } else {
                Map arrayMap = new ArrayMap();
                arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zzjn);
                map = arrayMap;
            }
            this.zzjdi = true;
            zzcih zzazp = zzazp();
            appInstanceId = com_google_android_gms_internal_zzcfe.getAppId();
            zzcgp com_google_android_gms_internal_zzchn = new zzchn(this);
            zzazp.zzut();
            zzazp.zzwu();
            zzbq.checkNotNull(url);
            zzbq.checkNotNull(com_google_android_gms_internal_zzchn);
            zzazp.zzawl().zzh(new zzcgr(zzazp, appInstanceId, url, null, map, com_google_android_gms_internal_zzchn));
        } catch (MalformedURLException e) {
            zzawm().zzayr().zze("Failed to parse config URL. Not fetching. appId", zzcgj.zzje(com_google_android_gms_internal_zzcfe.getAppId()), uri);
        }
    }

    @WorkerThread
    private final boolean zzbaa() {
        zzawl().zzut();
        zzwu();
        return this.zzjcx;
    }

    @WorkerThread
    private final void zzbab() {
        zzawl().zzut();
        if (this.zzjdi || this.zzjdj || this.zzjdk) {
            zzawm().zzayx().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzjdi), Boolean.valueOf(this.zzjdj), Boolean.valueOf(this.zzjdk));
            return;
        }
        zzawm().zzayx().log("Stopping uploading service(s)");
        if (this.zzjdd != null) {
            for (Runnable run : this.zzjdd) {
                run.run();
            }
            this.zzjdd.clear();
        }
    }

    @WorkerThread
    private final void zzc(zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
        zzbq.zzgh(com_google_android_gms_internal_zzcff.packageName);
        long nanoTime = System.nanoTime();
        zzawl().zzut();
        zzwu();
        String str = com_google_android_gms_internal_zzcff.packageName;
        zzawi();
        if (!zzckn.zzd(com_google_android_gms_internal_zzcfx, com_google_android_gms_internal_zzcff)) {
            return;
        }
        if (!com_google_android_gms_internal_zzcff.zzivc) {
            zzg(com_google_android_gms_internal_zzcff);
        } else if (zzawj().zzan(str, com_google_android_gms_internal_zzcfx.name)) {
            zzawm().zzayt().zze("Dropping blacklisted event. appId", zzcgj.zzje(str), zzawh().zzjb(com_google_android_gms_internal_zzcfx.name));
            Object obj = (zzawi().zzkf(str) || zzawi().zzkg(str)) ? 1 : null;
            if (obj == null && !"_err".equals(com_google_android_gms_internal_zzcfx.name)) {
                zzawi().zza(str, 11, "_ev", com_google_android_gms_internal_zzcfx.name, 0);
            }
            if (obj != null) {
                zzcfe zziv = zzawg().zziv(str);
                if (zziv != null) {
                    if (Math.abs(this.zzasd.currentTimeMillis() - Math.max(zziv.zzaxb(), zziv.zzaxa())) > ((Long) zzcfz.zziyh.get()).longValue()) {
                        zzawm().zzayw().log("Fetching config for blacklisted app");
                        zzb(zziv);
                    }
                }
            }
        } else {
            if (zzawm().zzae(2)) {
                zzawm().zzayx().zzj("Logging event", zzawh().zzb(com_google_android_gms_internal_zzcfx));
            }
            zzawg().beginTransaction();
            zzg(com_google_android_gms_internal_zzcff);
            if (("_iap".equals(com_google_android_gms_internal_zzcfx.name) || Event.ECOMMERCE_PURCHASE.equals(com_google_android_gms_internal_zzcfx.name)) && !zza(str, com_google_android_gms_internal_zzcfx)) {
                zzawg().setTransactionSuccessful();
                zzawg().endTransaction();
                return;
            }
            zzclb com_google_android_gms_internal_zzclb;
            try {
                boolean zzjt = zzckn.zzjt(com_google_android_gms_internal_zzcfx.name);
                boolean equals = "_err".equals(com_google_android_gms_internal_zzcfx.name);
                zzcfm zza = zzawg().zza(zzazu(), str, true, zzjt, false, equals, false);
                long intValue = zza.zziwd - ((long) ((Integer) zzcfz.zzixs.get()).intValue());
                if (intValue > 0) {
                    if (intValue % 1000 == 1) {
                        zzawm().zzayr().zze("Data loss. Too many events logged. appId, count", zzcgj.zzje(str), Long.valueOf(zza.zziwd));
                    }
                    zzawg().setTransactionSuccessful();
                    zzawg().endTransaction();
                    return;
                }
                zzcft zzba;
                zzcfs com_google_android_gms_internal_zzcfs;
                boolean z;
                if (zzjt) {
                    intValue = zza.zziwc - ((long) ((Integer) zzcfz.zzixu.get()).intValue());
                    if (intValue > 0) {
                        if (intValue % 1000 == 1) {
                            zzawm().zzayr().zze("Data loss. Too many public events logged. appId, count", zzcgj.zzje(str), Long.valueOf(zza.zziwc));
                        }
                        zzawi().zza(str, 16, "_ev", com_google_android_gms_internal_zzcfx.name, 0);
                        zzawg().setTransactionSuccessful();
                        zzawg().endTransaction();
                        return;
                    }
                }
                if (equals) {
                    intValue = zza.zziwf - ((long) Math.max(0, Math.min(1000000, this.zzjcb.zzb(com_google_android_gms_internal_zzcff.packageName, zzcfz.zzixt))));
                    if (intValue > 0) {
                        if (intValue == 1) {
                            zzawm().zzayr().zze("Too many error events logged. appId, count", zzcgj.zzje(str), Long.valueOf(zza.zziwf));
                        }
                        zzawg().setTransactionSuccessful();
                        zzawg().endTransaction();
                        return;
                    }
                }
                Bundle zzayl = com_google_android_gms_internal_zzcfx.zziwy.zzayl();
                zzawi().zza(zzayl, "_o", com_google_android_gms_internal_zzcfx.zzivk);
                if (zzawi().zzkd(str)) {
                    zzawi().zza(zzayl, "_dbg", Long.valueOf(1));
                    zzawi().zza(zzayl, "_r", Long.valueOf(1));
                }
                long zziw = zzawg().zziw(str);
                if (zziw > 0) {
                    zzawm().zzayt().zze("Data lost. Too many events stored on disk, deleted. appId", zzcgj.zzje(str), Long.valueOf(zziw));
                }
                zzcfs com_google_android_gms_internal_zzcfs2 = new zzcfs(this, com_google_android_gms_internal_zzcfx.zzivk, str, com_google_android_gms_internal_zzcfx.name, com_google_android_gms_internal_zzcfx.zziwz, 0, zzayl);
                zzcft zzae = zzawg().zzae(str, com_google_android_gms_internal_zzcfs2.mName);
                if (zzae != null) {
                    com_google_android_gms_internal_zzcfs2 = com_google_android_gms_internal_zzcfs2.zza(this, zzae.zziwr);
                    zzba = zzae.zzba(com_google_android_gms_internal_zzcfs2.zzffr);
                    com_google_android_gms_internal_zzcfs = com_google_android_gms_internal_zzcfs2;
                } else if (zzawg().zziz(str) < 500 || !zzjt) {
                    zzba = new zzcft(str, com_google_android_gms_internal_zzcfs2.mName, 0, 0, com_google_android_gms_internal_zzcfs2.zzffr, 0, null, null, null);
                    com_google_android_gms_internal_zzcfs = com_google_android_gms_internal_zzcfs2;
                } else {
                    zzawm().zzayr().zzd("Too many event names used, ignoring event. appId, name, supported count", zzcgj.zzje(str), zzawh().zzjb(com_google_android_gms_internal_zzcfs2.mName), Integer.valueOf(500));
                    zzawi().zza(str, 8, null, null, 0);
                    zzawg().endTransaction();
                    return;
                }
                zzawg().zza(zzba);
                zzawl().zzut();
                zzwu();
                zzbq.checkNotNull(com_google_android_gms_internal_zzcfs);
                zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
                zzbq.zzgh(com_google_android_gms_internal_zzcfs.mAppId);
                zzbq.checkArgument(com_google_android_gms_internal_zzcfs.mAppId.equals(com_google_android_gms_internal_zzcff.packageName));
                com_google_android_gms_internal_zzclb = new zzclb();
                com_google_android_gms_internal_zzclb.zzjit = Integer.valueOf(1);
                com_google_android_gms_internal_zzclb.zzjjb = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                com_google_android_gms_internal_zzclb.zzch = com_google_android_gms_internal_zzcff.packageName;
                com_google_android_gms_internal_zzclb.zziuy = com_google_android_gms_internal_zzcff.zziuy;
                com_google_android_gms_internal_zzclb.zzicq = com_google_android_gms_internal_zzcff.zzicq;
                com_google_android_gms_internal_zzclb.zzjjo = com_google_android_gms_internal_zzcff.zzive == -2147483648L ? null : Integer.valueOf((int) com_google_android_gms_internal_zzcff.zzive);
                com_google_android_gms_internal_zzclb.zzjjf = Long.valueOf(com_google_android_gms_internal_zzcff.zziuz);
                com_google_android_gms_internal_zzclb.zziux = com_google_android_gms_internal_zzcff.zziux;
                com_google_android_gms_internal_zzclb.zzjjk = com_google_android_gms_internal_zzcff.zziva == 0 ? null : Long.valueOf(com_google_android_gms_internal_zzcff.zziva);
                Pair zzjg = zzawn().zzjg(com_google_android_gms_internal_zzcff.packageName);
                if (zzjg == null || TextUtils.isEmpty((CharSequence) zzjg.first)) {
                    if (!zzawc().zzdt(this.mContext)) {
                        String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
                        if (string == null) {
                            zzawm().zzayt().zzj("null secure ID. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch));
                            string = "null";
                        } else if (string.isEmpty()) {
                            zzawm().zzayt().zzj("empty secure ID. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch));
                        }
                        com_google_android_gms_internal_zzclb.zzjjr = string;
                    }
                } else if (com_google_android_gms_internal_zzcff.zzivj) {
                    com_google_android_gms_internal_zzclb.zzjjh = (String) zzjg.first;
                    com_google_android_gms_internal_zzclb.zzjji = (Boolean) zzjg.second;
                }
                zzawc().zzwu();
                com_google_android_gms_internal_zzclb.zzjjc = Build.MODEL;
                zzawc().zzwu();
                com_google_android_gms_internal_zzclb.zzcv = VERSION.RELEASE;
                com_google_android_gms_internal_zzclb.zzjje = Integer.valueOf((int) zzawc().zzayi());
                com_google_android_gms_internal_zzclb.zzjjd = zzawc().zzayj();
                com_google_android_gms_internal_zzclb.zzjjg = null;
                com_google_android_gms_internal_zzclb.zzjiw = null;
                com_google_android_gms_internal_zzclb.zzjix = null;
                com_google_android_gms_internal_zzclb.zzjiy = null;
                com_google_android_gms_internal_zzclb.zzfhr = Long.valueOf(com_google_android_gms_internal_zzcff.zzivg);
                if (isEnabled() && zzcfk.zzaxs()) {
                    zzawb();
                    com_google_android_gms_internal_zzclb.zzjjt = null;
                }
                zzcfe zziv2 = zzawg().zziv(com_google_android_gms_internal_zzcff.packageName);
                if (zziv2 == null) {
                    zziv2 = new zzcfe(this, com_google_android_gms_internal_zzcff.packageName);
                    zziv2.zzil(zzawb().zzayn());
                    zziv2.zzio(com_google_android_gms_internal_zzcff.zzivf);
                    zziv2.zzim(com_google_android_gms_internal_zzcff.zziux);
                    zziv2.zzin(zzawn().zzjh(com_google_android_gms_internal_zzcff.packageName));
                    zziv2.zzap(0);
                    zziv2.zzak(0);
                    zziv2.zzal(0);
                    zziv2.setAppVersion(com_google_android_gms_internal_zzcff.zzicq);
                    zziv2.zzam(com_google_android_gms_internal_zzcff.zzive);
                    zziv2.zzip(com_google_android_gms_internal_zzcff.zziuy);
                    zziv2.zzan(com_google_android_gms_internal_zzcff.zziuz);
                    zziv2.zzao(com_google_android_gms_internal_zzcff.zziva);
                    zziv2.setMeasurementEnabled(com_google_android_gms_internal_zzcff.zzivc);
                    zziv2.zzay(com_google_android_gms_internal_zzcff.zzivg);
                    zzawg().zza(zziv2);
                }
                com_google_android_gms_internal_zzclb.zzjjj = zziv2.getAppInstanceId();
                com_google_android_gms_internal_zzclb.zzivf = zziv2.zzawr();
                List zziu = zzawg().zziu(com_google_android_gms_internal_zzcff.packageName);
                com_google_android_gms_internal_zzclb.zzjiv = new zzcld[zziu.size()];
                for (int i = 0; i < zziu.size(); i++) {
                    zzcld com_google_android_gms_internal_zzcld = new zzcld();
                    com_google_android_gms_internal_zzclb.zzjiv[i] = com_google_android_gms_internal_zzcld;
                    com_google_android_gms_internal_zzcld.name = ((zzckm) zziu.get(i)).mName;
                    com_google_android_gms_internal_zzcld.zzjjx = Long.valueOf(((zzckm) zziu.get(i)).zzjgr);
                    zzawi().zza(com_google_android_gms_internal_zzcld, ((zzckm) zziu.get(i)).mValue);
                }
                long zza2 = zzawg().zza(com_google_android_gms_internal_zzclb);
                zzcfl zzawg = zzawg();
                if (com_google_android_gms_internal_zzcfs.zziwo != null) {
                    Iterator it = com_google_android_gms_internal_zzcfs.zziwo.iterator();
                    while (it.hasNext()) {
                        if ("_r".equals((String) it.next())) {
                            z = true;
                            break;
                        }
                    }
                    z = zzawj().zzao(com_google_android_gms_internal_zzcfs.mAppId, com_google_android_gms_internal_zzcfs.mName);
                    zzcfm zza3 = zzawg().zza(zzazu(), com_google_android_gms_internal_zzcfs.mAppId, false, false, false, false, false);
                    if (z && zza3.zziwg < ((long) this.zzjcb.zzir(com_google_android_gms_internal_zzcfs.mAppId))) {
                        z = true;
                        if (zzawg.zza(com_google_android_gms_internal_zzcfs, zza2, z)) {
                            this.zzjdh = 0;
                        }
                        zzawg().setTransactionSuccessful();
                        if (zzawm().zzae(2)) {
                            zzawm().zzayx().zzj("Event recorded", zzawh().zza(com_google_android_gms_internal_zzcfs));
                        }
                        zzawg().endTransaction();
                        zzazx();
                        zzawm().zzayx().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                    }
                }
                z = false;
                if (zzawg.zza(com_google_android_gms_internal_zzcfs, zza2, z)) {
                    this.zzjdh = 0;
                }
                zzawg().setTransactionSuccessful();
                if (zzawm().zzae(2)) {
                    zzawm().zzayx().zzj("Event recorded", zzawh().zza(com_google_android_gms_internal_zzcfs));
                }
                zzawg().endTransaction();
                zzazx();
                zzawm().zzayx().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
            } catch (IOException e) {
                zzawm().zzayr().zze("Data loss. Failed to insert raw event metadata. appId", zzcgj.zzje(com_google_android_gms_internal_zzclb.zzch), e);
            } catch (Throwable th) {
                zzawg().endTransaction();
            }
        }
    }

    public static zzchj zzdu(Context context) {
        zzbq.checkNotNull(context);
        zzbq.checkNotNull(context.getApplicationContext());
        if (zzjca == null) {
            synchronized (zzchj.class) {
                if (zzjca == null) {
                    zzjca = new zzchj(new zzcij(context));
                }
            }
        }
        return zzjca;
    }

    @WorkerThread
    private final void zzg(zzcff com_google_android_gms_internal_zzcff) {
        Object obj = 1;
        zzawl().zzut();
        zzwu();
        zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
        zzbq.zzgh(com_google_android_gms_internal_zzcff.packageName);
        zzcfe zziv = zzawg().zziv(com_google_android_gms_internal_zzcff.packageName);
        String zzjh = zzawn().zzjh(com_google_android_gms_internal_zzcff.packageName);
        Object obj2 = null;
        if (zziv == null) {
            zzcfe com_google_android_gms_internal_zzcfe = new zzcfe(this, com_google_android_gms_internal_zzcff.packageName);
            com_google_android_gms_internal_zzcfe.zzil(zzawb().zzayn());
            com_google_android_gms_internal_zzcfe.zzin(zzjh);
            zziv = com_google_android_gms_internal_zzcfe;
            obj2 = 1;
        } else if (!zzjh.equals(zziv.zzawq())) {
            zziv.zzin(zzjh);
            zziv.zzil(zzawb().zzayn());
            int i = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux) || com_google_android_gms_internal_zzcff.zziux.equals(zziv.getGmpAppId()))) {
            zziv.zzim(com_google_android_gms_internal_zzcff.zziux);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zzivf) || com_google_android_gms_internal_zzcff.zzivf.equals(zziv.zzawr()))) {
            zziv.zzio(com_google_android_gms_internal_zzcff.zzivf);
            obj2 = 1;
        }
        if (!(com_google_android_gms_internal_zzcff.zziuz == 0 || com_google_android_gms_internal_zzcff.zziuz == zziv.zzaww())) {
            zziv.zzan(com_google_android_gms_internal_zzcff.zziuz);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zzicq) || com_google_android_gms_internal_zzcff.zzicq.equals(zziv.zzuy()))) {
            zziv.setAppVersion(com_google_android_gms_internal_zzcff.zzicq);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcff.zzive != zziv.zzawu()) {
            zziv.zzam(com_google_android_gms_internal_zzcff.zzive);
            obj2 = 1;
        }
        if (!(com_google_android_gms_internal_zzcff.zziuy == null || com_google_android_gms_internal_zzcff.zziuy.equals(zziv.zzawv()))) {
            zziv.zzip(com_google_android_gms_internal_zzcff.zziuy);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcff.zziva != zziv.zzawx()) {
            zziv.zzao(com_google_android_gms_internal_zzcff.zziva);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcff.zzivc != zziv.zzawy()) {
            zziv.setMeasurementEnabled(com_google_android_gms_internal_zzcff.zzivc);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zzivb) || com_google_android_gms_internal_zzcff.zzivb.equals(zziv.zzaxj()))) {
            zziv.zziq(com_google_android_gms_internal_zzcff.zzivb);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcff.zzivg != zziv.zzaxl()) {
            zziv.zzay(com_google_android_gms_internal_zzcff.zzivg);
            obj2 = 1;
        }
        if (com_google_android_gms_internal_zzcff.zzivj != zziv.zzaxm()) {
            zziv.zzbk(com_google_android_gms_internal_zzcff.zzivj);
        } else {
            obj = obj2;
        }
        if (obj != null) {
            zzawg().zza(zziv);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzh(java.lang.String r31, long r32) {
        /*
        r30 = this;
        r2 = r30.zzawg();
        r2.beginTransaction();
        r21 = new com.google.android.gms.internal.zzchj$zza;	 Catch:{ all -> 0x01be }
        r2 = 0;
        r0 = r21;
        r1 = r30;
        r0.<init>();	 Catch:{ all -> 0x01be }
        r14 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r4 = 0;
        r0 = r30;
        r0 = r0.zzjdg;	 Catch:{ all -> 0x01be }
        r16 = r0;
        com.google.android.gms.common.internal.zzbq.checkNotNull(r21);	 Catch:{ all -> 0x01be }
        r14.zzut();	 Catch:{ all -> 0x01be }
        r14.zzwu();	 Catch:{ all -> 0x01be }
        r3 = 0;
        r2 = r14.getWritableDatabase();	 Catch:{ SQLiteException -> 0x0abf }
        r5 = 0;
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ SQLiteException -> 0x0abf }
        if (r5 == 0) goto L_0x01c7;
    L_0x0031:
        r6 = -1;
        r5 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x0160;
    L_0x0037:
        r5 = 2;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0abf }
        r6 = 0;
        r7 = java.lang.String.valueOf(r16);	 Catch:{ SQLiteException -> 0x0abf }
        r5[r6] = r7;	 Catch:{ SQLiteException -> 0x0abf }
        r6 = 1;
        r7 = java.lang.String.valueOf(r32);	 Catch:{ SQLiteException -> 0x0abf }
        r5[r6] = r7;	 Catch:{ SQLiteException -> 0x0abf }
        r6 = r5;
    L_0x0049:
        r8 = -1;
        r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1));
        if (r5 == 0) goto L_0x016d;
    L_0x004f:
        r5 = "rowid <= ? and ";
    L_0x0051:
        r7 = java.lang.String.valueOf(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r7 = r7.length();	 Catch:{ SQLiteException -> 0x0abf }
        r7 = r7 + 148;
        r8 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0abf }
        r8.<init>(r7);	 Catch:{ SQLiteException -> 0x0abf }
        r7 = "select app_id, metadata_fingerprint from raw_events where ";
        r7 = r8.append(r7);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = r7.append(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r7 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;";
        r5 = r5.append(r7);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = r5.toString();	 Catch:{ SQLiteException -> 0x0abf }
        r3 = r2.rawQuery(r5, r6);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0abf }
        if (r5 != 0) goto L_0x0171;
    L_0x007e:
        if (r3 == 0) goto L_0x0083;
    L_0x0080:
        r3.close();	 Catch:{ all -> 0x01be }
    L_0x0083:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0093;
    L_0x0089:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x035a;
    L_0x0093:
        r2 = 1;
    L_0x0094:
        if (r2 != 0) goto L_0x0aaa;
    L_0x0096:
        r17 = 0;
        r0 = r21;
        r0 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r22 = r0;
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.size();	 Catch:{ all -> 0x01be }
        r2 = new com.google.android.gms.internal.zzcky[r2];	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjiu = r2;	 Catch:{ all -> 0x01be }
        r12 = 0;
        r2 = 0;
        r13 = r2;
    L_0x00af:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.size();	 Catch:{ all -> 0x01be }
        if (r13 >= r2) goto L_0x0613;
    L_0x00b9:
        r3 = r30.zzawj();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r2.zzch;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = r3.zzan(r4, r2);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0360;
    L_0x00d5:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r3 = r2.zzayt();	 Catch:{ all -> 0x01be }
        r4 = "Dropping blacklisted raw event. appId";
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r2 = r2.zzch;	 Catch:{ all -> 0x01be }
        r5 = com.google.android.gms.internal.zzcgj.zzje(r2);	 Catch:{ all -> 0x01be }
        r6 = r30.zzawh();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = r6.zzjb(r2);	 Catch:{ all -> 0x01be }
        r3.zze(r4, r5, r2);	 Catch:{ all -> 0x01be }
        r2 = r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r2 = r2.zzkf(r3);	 Catch:{ all -> 0x01be }
        if (r2 != 0) goto L_0x0120;
    L_0x0110:
        r2 = r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r2 = r2.zzkg(r3);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x035d;
    L_0x0120:
        r2 = 1;
    L_0x0121:
        if (r2 != 0) goto L_0x0adb;
    L_0x0123:
        r3 = "_err";
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = r3.equals(r2);	 Catch:{ all -> 0x01be }
        if (r2 != 0) goto L_0x0adb;
    L_0x0137:
        r2 = r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r4 = 11;
        r5 = "_ev";
        r0 = r21;
        r6 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r6 = r6.get(r13);	 Catch:{ all -> 0x01be }
        r6 = (com.google.android.gms.internal.zzcky) r6;	 Catch:{ all -> 0x01be }
        r6 = r6.name;	 Catch:{ all -> 0x01be }
        r7 = 0;
        r2.zza(r3, r4, r5, r6, r7);	 Catch:{ all -> 0x01be }
        r2 = r12;
        r3 = r17;
    L_0x0158:
        r4 = r13 + 1;
        r13 = r4;
        r12 = r2;
        r17 = r3;
        goto L_0x00af;
    L_0x0160:
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0abf }
        r6 = 0;
        r7 = java.lang.String.valueOf(r32);	 Catch:{ SQLiteException -> 0x0abf }
        r5[r6] = r7;	 Catch:{ SQLiteException -> 0x0abf }
        r6 = r5;
        goto L_0x0049;
    L_0x016d:
        r5 = "";
        goto L_0x0051;
    L_0x0171:
        r5 = 0;
        r4 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r3.close();	 Catch:{ SQLiteException -> 0x0abf }
        r13 = r5;
        r11 = r3;
        r12 = r4;
    L_0x0181:
        r3 = "raw_events_metadata";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r5 = 0;
        r6 = "metadata";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r5 = "app_id = ? and metadata_fingerprint = ?";
        r6 = 2;
        r6 = new java.lang.String[r6];	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 0;
        r6[r7] = r12;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 1;
        r6[r7] = r13;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 0;
        r8 = 0;
        r9 = "rowid";
        r10 = "2";
        r11 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = r11.moveToFirst();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        if (r3 != 0) goto L_0x0231;
    L_0x01a6:
        r2 = r14.zzawm();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r2 = r2.zzayr();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = "Raw event metadata record is missing. appId";
        r4 = com.google.android.gms.internal.zzcgj.zzje(r12);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r2.zzj(r3, r4);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        if (r11 == 0) goto L_0x0083;
    L_0x01b9:
        r11.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x01be:
        r2 = move-exception;
        r3 = r30.zzawg();
        r3.endTransaction();
        throw r2;
    L_0x01c7:
        r6 = -1;
        r5 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x0218;
    L_0x01cd:
        r5 = 2;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0abf }
        r6 = 0;
        r7 = 0;
        r5[r6] = r7;	 Catch:{ SQLiteException -> 0x0abf }
        r6 = 1;
        r7 = java.lang.String.valueOf(r16);	 Catch:{ SQLiteException -> 0x0abf }
        r5[r6] = r7;	 Catch:{ SQLiteException -> 0x0abf }
        r6 = r5;
    L_0x01dc:
        r8 = -1;
        r5 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1));
        if (r5 == 0) goto L_0x0221;
    L_0x01e2:
        r5 = " and rowid <= ?";
    L_0x01e4:
        r7 = java.lang.String.valueOf(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r7 = r7.length();	 Catch:{ SQLiteException -> 0x0abf }
        r7 = r7 + 84;
        r8 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0abf }
        r8.<init>(r7);	 Catch:{ SQLiteException -> 0x0abf }
        r7 = "select metadata_fingerprint from raw_events where app_id = ?";
        r7 = r8.append(r7);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = r7.append(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r7 = " order by rowid limit 1;";
        r5 = r5.append(r7);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = r5.toString();	 Catch:{ SQLiteException -> 0x0abf }
        r3 = r2.rawQuery(r5, r6);	 Catch:{ SQLiteException -> 0x0abf }
        r5 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0abf }
        if (r5 != 0) goto L_0x0224;
    L_0x0211:
        if (r3 == 0) goto L_0x0083;
    L_0x0213:
        r3.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x0218:
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0abf }
        r6 = 0;
        r7 = 0;
        r5[r6] = r7;	 Catch:{ SQLiteException -> 0x0abf }
        r6 = r5;
        goto L_0x01dc;
    L_0x0221:
        r5 = "";
        goto L_0x01e4;
    L_0x0224:
        r5 = 0;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0abf }
        r3.close();	 Catch:{ SQLiteException -> 0x0abf }
        r13 = r5;
        r11 = r3;
        r12 = r4;
        goto L_0x0181;
    L_0x0231:
        r3 = 0;
        r3 = r11.getBlob(r3);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r4 = 0;
        r5 = r3.length;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = com.google.android.gms.internal.zzfhb.zzn(r3, r4, r5);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r4 = new com.google.android.gms.internal.zzclb;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r4.<init>();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r4.zza(r3);	 Catch:{ IOException -> 0x02bb }
        r3 = r11.moveToNext();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        if (r3 == 0) goto L_0x025b;
    L_0x024a:
        r3 = r14.zzawm();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = r3.zzayt();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r5 = "Get multiple raw event metadata records, expected one. appId";
        r6 = com.google.android.gms.internal.zzcgj.zzje(r12);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3.zzj(r5, r6);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
    L_0x025b:
        r11.close();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r0 = r21;
        r0.zzb(r4);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r4 = -1;
        r3 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1));
        if (r3 == 0) goto L_0x02d4;
    L_0x0269:
        r5 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
        r3 = 3;
        r6 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = 0;
        r6[r3] = r12;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = 1;
        r6[r3] = r13;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = 2;
        r4 = java.lang.String.valueOf(r16);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r6[r3] = r4;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
    L_0x027b:
        r3 = "raw_events";
        r4 = 4;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 0;
        r8 = "rowid";
        r4[r7] = r8;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 1;
        r8 = "name";
        r4[r7] = r8;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 2;
        r8 = "timestamp";
        r4[r7] = r8;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 3;
        r8 = "data";
        r4[r7] = r8;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r7 = 0;
        r8 = 0;
        r9 = "rowid";
        r10 = 0;
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0ac2 }
        if (r2 != 0) goto L_0x02fb;
    L_0x02a3:
        r2 = r14.zzawm();	 Catch:{ SQLiteException -> 0x0ac2 }
        r2 = r2.zzayt();	 Catch:{ SQLiteException -> 0x0ac2 }
        r4 = "Raw event data disappeared while in transaction. appId";
        r5 = com.google.android.gms.internal.zzcgj.zzje(r12);	 Catch:{ SQLiteException -> 0x0ac2 }
        r2.zzj(r4, r5);	 Catch:{ SQLiteException -> 0x0ac2 }
        if (r3 == 0) goto L_0x0083;
    L_0x02b6:
        r3.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x02bb:
        r2 = move-exception;
        r3 = r14.zzawm();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = r3.zzayr();	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r4 = "Data loss. Failed to merge raw event metadata. appId";
        r5 = com.google.android.gms.internal.zzcgj.zzje(r12);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3.zze(r4, r5, r2);	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        if (r11 == 0) goto L_0x0083;
    L_0x02cf:
        r11.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x02d4:
        r5 = "app_id = ? and metadata_fingerprint = ?";
        r3 = 2;
        r6 = new java.lang.String[r3];	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = 0;
        r6[r3] = r12;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        r3 = 1;
        r6[r3] = r13;	 Catch:{ SQLiteException -> 0x02e0, all -> 0x0abb }
        goto L_0x027b;
    L_0x02e0:
        r2 = move-exception;
        r3 = r11;
        r4 = r12;
    L_0x02e3:
        r5 = r14.zzawm();	 Catch:{ all -> 0x0353 }
        r5 = r5.zzayr();	 Catch:{ all -> 0x0353 }
        r6 = "Data loss. Error selecting raw event. appId";
        r4 = com.google.android.gms.internal.zzcgj.zzje(r4);	 Catch:{ all -> 0x0353 }
        r5.zze(r6, r4, r2);	 Catch:{ all -> 0x0353 }
        if (r3 == 0) goto L_0x0083;
    L_0x02f6:
        r3.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x02fb:
        r2 = 0;
        r4 = r3.getLong(r2);	 Catch:{ SQLiteException -> 0x0ac2 }
        r2 = 3;
        r2 = r3.getBlob(r2);	 Catch:{ SQLiteException -> 0x0ac2 }
        r6 = 0;
        r7 = r2.length;	 Catch:{ SQLiteException -> 0x0ac2 }
        r2 = com.google.android.gms.internal.zzfhb.zzn(r2, r6, r7);	 Catch:{ SQLiteException -> 0x0ac2 }
        r6 = new com.google.android.gms.internal.zzcky;	 Catch:{ SQLiteException -> 0x0ac2 }
        r6.<init>();	 Catch:{ SQLiteException -> 0x0ac2 }
        r6.zza(r2);	 Catch:{ IOException -> 0x0334 }
        r2 = 1;
        r2 = r3.getString(r2);	 Catch:{ SQLiteException -> 0x0ac2 }
        r6.name = r2;	 Catch:{ SQLiteException -> 0x0ac2 }
        r2 = 2;
        r8 = r3.getLong(r2);	 Catch:{ SQLiteException -> 0x0ac2 }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x0ac2 }
        r6.zzjin = r2;	 Catch:{ SQLiteException -> 0x0ac2 }
        r0 = r21;
        r2 = r0.zza(r4, r6);	 Catch:{ SQLiteException -> 0x0ac2 }
        if (r2 != 0) goto L_0x0346;
    L_0x032d:
        if (r3 == 0) goto L_0x0083;
    L_0x032f:
        r3.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x0334:
        r2 = move-exception;
        r4 = r14.zzawm();	 Catch:{ SQLiteException -> 0x0ac2 }
        r4 = r4.zzayr();	 Catch:{ SQLiteException -> 0x0ac2 }
        r5 = "Data loss. Failed to merge raw event. appId";
        r6 = com.google.android.gms.internal.zzcgj.zzje(r12);	 Catch:{ SQLiteException -> 0x0ac2 }
        r4.zze(r5, r6, r2);	 Catch:{ SQLiteException -> 0x0ac2 }
    L_0x0346:
        r2 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x0ac2 }
        if (r2 != 0) goto L_0x02fb;
    L_0x034c:
        if (r3 == 0) goto L_0x0083;
    L_0x034e:
        r3.close();	 Catch:{ all -> 0x01be }
        goto L_0x0083;
    L_0x0353:
        r2 = move-exception;
    L_0x0354:
        if (r3 == 0) goto L_0x0359;
    L_0x0356:
        r3.close();	 Catch:{ all -> 0x01be }
    L_0x0359:
        throw r2;	 Catch:{ all -> 0x01be }
    L_0x035a:
        r2 = 0;
        goto L_0x0094;
    L_0x035d:
        r2 = 0;
        goto L_0x0121;
    L_0x0360:
        r3 = r30.zzawj();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r2.zzch;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r14 = r3.zzao(r4, r2);	 Catch:{ all -> 0x01be }
        if (r14 != 0) goto L_0x0391;
    L_0x037c:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zzkh(r2);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0610;
    L_0x0391:
        r3 = 0;
        r4 = 0;
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.zzjim;	 Catch:{ all -> 0x01be }
        if (r2 != 0) goto L_0x03b0;
    L_0x03a1:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r5 = 0;
        r5 = new com.google.android.gms.internal.zzckz[r5];	 Catch:{ all -> 0x01be }
        r2.zzjim = r5;	 Catch:{ all -> 0x01be }
    L_0x03b0:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r6 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r7 = r6.length;	 Catch:{ all -> 0x01be }
        r2 = 0;
        r5 = r2;
    L_0x03bf:
        if (r5 >= r7) goto L_0x03f1;
    L_0x03c1:
        r2 = r6[r5];	 Catch:{ all -> 0x01be }
        r8 = "_c";
        r9 = r2.name;	 Catch:{ all -> 0x01be }
        r8 = r8.equals(r9);	 Catch:{ all -> 0x01be }
        if (r8 == 0) goto L_0x03dd;
    L_0x03cd:
        r8 = 1;
        r3 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x01be }
        r2.zzjiq = r3;	 Catch:{ all -> 0x01be }
        r2 = 1;
        r3 = r2;
        r2 = r4;
    L_0x03d8:
        r4 = r5 + 1;
        r5 = r4;
        r4 = r2;
        goto L_0x03bf;
    L_0x03dd:
        r8 = "_r";
        r9 = r2.name;	 Catch:{ all -> 0x01be }
        r8 = r8.equals(r9);	 Catch:{ all -> 0x01be }
        if (r8 == 0) goto L_0x0ad8;
    L_0x03e7:
        r8 = 1;
        r4 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x01be }
        r2.zzjiq = r4;	 Catch:{ all -> 0x01be }
        r2 = 1;
        goto L_0x03d8;
    L_0x03f1:
        if (r3 != 0) goto L_0x0459;
    L_0x03f3:
        if (r14 == 0) goto L_0x0459;
    L_0x03f5:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r3 = r2.zzayx();	 Catch:{ all -> 0x01be }
        r5 = "Marking event as conversion";
        r6 = r30.zzawh();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = r6.zzjb(r2);	 Catch:{ all -> 0x01be }
        r3.zzj(r5, r2);	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r3 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r2 = r2.length;	 Catch:{ all -> 0x01be }
        r2 = r2 + 1;
        r2 = java.util.Arrays.copyOf(r3, r2);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzckz[]) r2;	 Catch:{ all -> 0x01be }
        r3 = new com.google.android.gms.internal.zzckz;	 Catch:{ all -> 0x01be }
        r3.<init>();	 Catch:{ all -> 0x01be }
        r5 = "_c";
        r3.name = r5;	 Catch:{ all -> 0x01be }
        r6 = 1;
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x01be }
        r3.zzjiq = r5;	 Catch:{ all -> 0x01be }
        r5 = r2.length;	 Catch:{ all -> 0x01be }
        r5 = r5 + -1;
        r2[r5] = r3;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r3 = r3.get(r13);	 Catch:{ all -> 0x01be }
        r3 = (com.google.android.gms.internal.zzcky) r3;	 Catch:{ all -> 0x01be }
        r3.zzjim = r2;	 Catch:{ all -> 0x01be }
    L_0x0459:
        if (r4 != 0) goto L_0x04bf;
    L_0x045b:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r3 = r2.zzayx();	 Catch:{ all -> 0x01be }
        r4 = "Marking event as real-time";
        r5 = r30.zzawh();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = r5.zzjb(r2);	 Catch:{ all -> 0x01be }
        r3.zzj(r4, r2);	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r3 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r2 = r2.length;	 Catch:{ all -> 0x01be }
        r2 = r2 + 1;
        r2 = java.util.Arrays.copyOf(r3, r2);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzckz[]) r2;	 Catch:{ all -> 0x01be }
        r3 = new com.google.android.gms.internal.zzckz;	 Catch:{ all -> 0x01be }
        r3.<init>();	 Catch:{ all -> 0x01be }
        r4 = "_r";
        r3.name = r4;	 Catch:{ all -> 0x01be }
        r4 = 1;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01be }
        r3.zzjiq = r4;	 Catch:{ all -> 0x01be }
        r4 = r2.length;	 Catch:{ all -> 0x01be }
        r4 = r4 + -1;
        r2[r4] = r3;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r3 = r3.get(r13);	 Catch:{ all -> 0x01be }
        r3 = (com.google.android.gms.internal.zzcky) r3;	 Catch:{ all -> 0x01be }
        r3.zzjim = r2;	 Catch:{ all -> 0x01be }
    L_0x04bf:
        r2 = 1;
        r3 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r4 = r30.zzazu();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r6 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r6 = r6.zzch;	 Catch:{ all -> 0x01be }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 1;
        r3 = r3.zza(r4, r6, r7, r8, r9, r10, r11);	 Catch:{ all -> 0x01be }
        r4 = r3.zziwg;	 Catch:{ all -> 0x01be }
        r0 = r30;
        r3 = r0.zzjcb;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r6 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r6 = r6.zzch;	 Catch:{ all -> 0x01be }
        r3 = r3.zzir(r6);	 Catch:{ all -> 0x01be }
        r6 = (long) r3;	 Catch:{ all -> 0x01be }
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 <= 0) goto L_0x0ad4;
    L_0x04ec:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r3 = 0;
    L_0x04f7:
        r4 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r4 = r4.length;	 Catch:{ all -> 0x01be }
        if (r3 >= r4) goto L_0x0528;
    L_0x04fc:
        r4 = "_r";
        r5 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r5 = r5[r3];	 Catch:{ all -> 0x01be }
        r5 = r5.name;	 Catch:{ all -> 0x01be }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x01be }
        if (r4 == 0) goto L_0x05a7;
    L_0x050a:
        r4 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r4 = r4.length;	 Catch:{ all -> 0x01be }
        r4 = r4 + -1;
        r4 = new com.google.android.gms.internal.zzckz[r4];	 Catch:{ all -> 0x01be }
        if (r3 <= 0) goto L_0x051a;
    L_0x0513:
        r5 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r6 = 0;
        r7 = 0;
        java.lang.System.arraycopy(r5, r6, r4, r7, r3);	 Catch:{ all -> 0x01be }
    L_0x051a:
        r5 = r4.length;	 Catch:{ all -> 0x01be }
        if (r3 >= r5) goto L_0x0526;
    L_0x051d:
        r5 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r6 = r3 + 1;
        r7 = r4.length;	 Catch:{ all -> 0x01be }
        r7 = r7 - r3;
        java.lang.System.arraycopy(r5, r6, r4, r3, r7);	 Catch:{ all -> 0x01be }
    L_0x0526:
        r2.zzjim = r4;	 Catch:{ all -> 0x01be }
    L_0x0528:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.name;	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zzjt(r2);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0610;
    L_0x053a:
        if (r14 == 0) goto L_0x0610;
    L_0x053c:
        r3 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r4 = r30.zzazu();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r6 = r2.zzch;	 Catch:{ all -> 0x01be }
        r7 = 0;
        r8 = 0;
        r9 = 1;
        r10 = 0;
        r11 = 0;
        r2 = r3.zza(r4, r6, r7, r8, r9, r10, r11);	 Catch:{ all -> 0x01be }
        r2 = r2.zziwe;	 Catch:{ all -> 0x01be }
        r0 = r30;
        r4 = r0.zzjcb;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r5 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r5 = r5.zzch;	 Catch:{ all -> 0x01be }
        r6 = com.google.android.gms.internal.zzcfz.zzixv;	 Catch:{ all -> 0x01be }
        r4 = r4.zzb(r5, r6);	 Catch:{ all -> 0x01be }
        r4 = (long) r4;	 Catch:{ all -> 0x01be }
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0610;
    L_0x056a:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r2 = r2.zzayt();	 Catch:{ all -> 0x01be }
        r3 = "Too many conversions. Not logging as conversion. appId";
        r0 = r21;
        r4 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r4.zzch;	 Catch:{ all -> 0x01be }
        r4 = com.google.android.gms.internal.zzcgj.zzje(r4);	 Catch:{ all -> 0x01be }
        r2.zzj(r3, r4);	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r5 = 0;
        r4 = 0;
        r7 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r8 = r7.length;	 Catch:{ all -> 0x01be }
        r3 = 0;
        r6 = r3;
    L_0x0592:
        if (r6 >= r8) goto L_0x05bc;
    L_0x0594:
        r3 = r7[r6];	 Catch:{ all -> 0x01be }
        r9 = "_c";
        r10 = r3.name;	 Catch:{ all -> 0x01be }
        r9 = r9.equals(r10);	 Catch:{ all -> 0x01be }
        if (r9 == 0) goto L_0x05ab;
    L_0x05a0:
        r4 = r5;
    L_0x05a1:
        r5 = r6 + 1;
        r6 = r5;
        r5 = r4;
        r4 = r3;
        goto L_0x0592;
    L_0x05a7:
        r3 = r3 + 1;
        goto L_0x04f7;
    L_0x05ab:
        r9 = "_err";
        r3 = r3.name;	 Catch:{ all -> 0x01be }
        r3 = r9.equals(r3);	 Catch:{ all -> 0x01be }
        if (r3 == 0) goto L_0x0ad0;
    L_0x05b5:
        r3 = 1;
        r29 = r4;
        r4 = r3;
        r3 = r29;
        goto L_0x05a1;
    L_0x05bc:
        if (r5 == 0) goto L_0x05e8;
    L_0x05be:
        if (r4 == 0) goto L_0x05e8;
    L_0x05c0:
        r3 = r2.zzjim;	 Catch:{ all -> 0x01be }
        r5 = 1;
        r5 = new com.google.android.gms.internal.zzckz[r5];	 Catch:{ all -> 0x01be }
        r6 = 0;
        r5[r6] = r4;	 Catch:{ all -> 0x01be }
        r3 = com.google.android.gms.common.util.zza.zza(r3, r5);	 Catch:{ all -> 0x01be }
        r3 = (com.google.android.gms.internal.zzckz[]) r3;	 Catch:{ all -> 0x01be }
        r2.zzjim = r3;	 Catch:{ all -> 0x01be }
        r4 = r17;
    L_0x05d2:
        r0 = r22;
        r5 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r3 = r12 + 1;
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.get(r13);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky) r2;	 Catch:{ all -> 0x01be }
        r5[r12] = r2;	 Catch:{ all -> 0x01be }
        r2 = r3;
        r3 = r4;
        goto L_0x0158;
    L_0x05e8:
        if (r4 == 0) goto L_0x05f9;
    L_0x05ea:
        r2 = "_err";
        r4.name = r2;	 Catch:{ all -> 0x01be }
        r2 = 10;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x01be }
        r4.zzjiq = r2;	 Catch:{ all -> 0x01be }
        r4 = r17;
        goto L_0x05d2;
    L_0x05f9:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r2 = r2.zzayr();	 Catch:{ all -> 0x01be }
        r3 = "Did not find conversion parameter. appId";
        r0 = r21;
        r4 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r4.zzch;	 Catch:{ all -> 0x01be }
        r4 = com.google.android.gms.internal.zzcgj.zzje(r4);	 Catch:{ all -> 0x01be }
        r2.zzj(r3, r4);	 Catch:{ all -> 0x01be }
    L_0x0610:
        r4 = r17;
        goto L_0x05d2;
    L_0x0613:
        r0 = r21;
        r2 = r0.zzaof;	 Catch:{ all -> 0x01be }
        r2 = r2.size();	 Catch:{ all -> 0x01be }
        if (r12 >= r2) goto L_0x062b;
    L_0x061d:
        r0 = r22;
        r2 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r2 = java.util.Arrays.copyOf(r2, r12);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky[]) r2;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjiu = r2;	 Catch:{ all -> 0x01be }
    L_0x062b:
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r2 = r2.zzch;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzjiv;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r4 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r0 = r30;
        r2 = r0.zza(r2, r3, r4);	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjjn = r2;	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzcfz.zzixh;	 Catch:{ all -> 0x01be }
        r2 = r2.get();	 Catch:{ all -> 0x01be }
        r2 = (java.lang.Boolean) r2;	 Catch:{ all -> 0x01be }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x091d;
    L_0x0653:
        r0 = r30;
        r2 = r0.zzjcb;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r4 = "1";
        r2 = r2.zzawj();	 Catch:{ all -> 0x01be }
        r5 = "measurement.event_sampling_enabled";
        r2 = r2.zzam(r3, r5);	 Catch:{ all -> 0x01be }
        r2 = r4.equals(r2);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x091d;
    L_0x066f:
        r23 = new java.util.HashMap;	 Catch:{ all -> 0x01be }
        r23.<init>();	 Catch:{ all -> 0x01be }
        r0 = r22;
        r2 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r2 = r2.length;	 Catch:{ all -> 0x01be }
        r0 = new com.google.android.gms.internal.zzcky[r2];	 Catch:{ all -> 0x01be }
        r24 = r0;
        r18 = 0;
        r2 = r30.zzawi();	 Catch:{ all -> 0x01be }
        r25 = r2.zzban();	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r26 = r0;
        r0 = r26;
        r0 = r0.length;	 Catch:{ all -> 0x01be }
        r27 = r0;
        r2 = 0;
        r20 = r2;
    L_0x0695:
        r0 = r20;
        r1 = r27;
        if (r0 >= r1) goto L_0x08e4;
    L_0x069b:
        r28 = r26[r20];	 Catch:{ all -> 0x01be }
        r0 = r28;
        r2 = r0.name;	 Catch:{ all -> 0x01be }
        r3 = "_ep";
        r2 = r2.equals(r3);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0725;
    L_0x06a9:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r2 = "_en";
        r0 = r28;
        r2 = com.google.android.gms.internal.zzckn.zza(r0, r2);	 Catch:{ all -> 0x01be }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x01be }
        r0 = r23;
        r3 = r0.get(r2);	 Catch:{ all -> 0x01be }
        r3 = (com.google.android.gms.internal.zzcft) r3;	 Catch:{ all -> 0x01be }
        if (r3 != 0) goto L_0x06d3;
    L_0x06c0:
        r3 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r4 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r4.zzch;	 Catch:{ all -> 0x01be }
        r3 = r3.zzae(r4, r2);	 Catch:{ all -> 0x01be }
        r0 = r23;
        r0.put(r2, r3);	 Catch:{ all -> 0x01be }
    L_0x06d3:
        r2 = r3.zziwt;	 Catch:{ all -> 0x01be }
        if (r2 != 0) goto L_0x08e0;
    L_0x06d7:
        r2 = r3.zziwu;	 Catch:{ all -> 0x01be }
        r4 = r2.longValue();	 Catch:{ all -> 0x01be }
        r6 = 1;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x06f6;
    L_0x06e3:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r28;
        r2 = r0.zzjim;	 Catch:{ all -> 0x01be }
        r4 = "_sr";
        r5 = r3.zziwu;	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zza(r2, r4, r5);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r0.zzjim = r2;	 Catch:{ all -> 0x01be }
    L_0x06f6:
        r2 = r3.zziwv;	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0719;
    L_0x06fa:
        r2 = r3.zziwv;	 Catch:{ all -> 0x01be }
        r2 = r2.booleanValue();	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0719;
    L_0x0702:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r28;
        r2 = r0.zzjim;	 Catch:{ all -> 0x01be }
        r3 = "_efs";
        r4 = 1;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zza(r2, r3, r4);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r0.zzjim = r2;	 Catch:{ all -> 0x01be }
    L_0x0719:
        r2 = r18 + 1;
        r24[r18] = r28;	 Catch:{ all -> 0x01be }
    L_0x071d:
        r3 = r20 + 1;
        r20 = r3;
        r18 = r2;
        goto L_0x0695;
    L_0x0725:
        r2 = 1;
        r3 = "_dbg";
        r4 = 1;
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r3 = zza(r0, r3, r4);	 Catch:{ all -> 0x01be }
        if (r3 != 0) goto L_0x0acc;
    L_0x0736:
        r2 = r30.zzawj();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r2 = r2.zzap(r3, r4);	 Catch:{ all -> 0x01be }
        r19 = r2;
    L_0x074a:
        if (r19 > 0) goto L_0x0766;
    L_0x074c:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r2 = r2.zzayt();	 Catch:{ all -> 0x01be }
        r3 = "Sample rate must be positive. event, rate";
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r5 = java.lang.Integer.valueOf(r19);	 Catch:{ all -> 0x01be }
        r2.zze(r3, r4, r5);	 Catch:{ all -> 0x01be }
        r2 = r18 + 1;
        r24[r18] = r28;	 Catch:{ all -> 0x01be }
        goto L_0x071d;
    L_0x0766:
        r0 = r28;
        r2 = r0.name;	 Catch:{ all -> 0x01be }
        r0 = r23;
        r2 = r0.get(r2);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcft) r2;	 Catch:{ all -> 0x01be }
        if (r2 != 0) goto L_0x0ac9;
    L_0x0774:
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r3 = r2.zzae(r3, r4);	 Catch:{ all -> 0x01be }
        if (r3 != 0) goto L_0x07c0;
    L_0x0788:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r2 = r2.zzayt();	 Catch:{ all -> 0x01be }
        r3 = "Event being bundled has no eventAggregate. appId, eventName";
        r0 = r21;
        r4 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r4.zzch;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r5 = r0.name;	 Catch:{ all -> 0x01be }
        r2.zze(r3, r4, r5);	 Catch:{ all -> 0x01be }
        r3 = new com.google.android.gms.internal.zzcft;	 Catch:{ all -> 0x01be }
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r2.zzch;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r5 = r0.name;	 Catch:{ all -> 0x01be }
        r6 = 1;
        r8 = 1;
        r0 = r28;
        r2 = r0.zzjin;	 Catch:{ all -> 0x01be }
        r10 = r2.longValue();	 Catch:{ all -> 0x01be }
        r12 = 0;
        r14 = 0;
        r15 = 0;
        r16 = 0;
        r3.<init>(r4, r5, r6, r8, r10, r12, r14, r15, r16);	 Catch:{ all -> 0x01be }
    L_0x07c0:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r2 = "_eid";
        r0 = r28;
        r2 = com.google.android.gms.internal.zzckn.zza(r0, r2);	 Catch:{ all -> 0x01be }
        r2 = (java.lang.Long) r2;	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0801;
    L_0x07cf:
        r4 = 1;
    L_0x07d0:
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ all -> 0x01be }
        r5 = 1;
        r0 = r19;
        if (r0 != r5) goto L_0x0803;
    L_0x07d9:
        r2 = r18 + 1;
        r24[r18] = r28;	 Catch:{ all -> 0x01be }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x01be }
        if (r4 == 0) goto L_0x071d;
    L_0x07e3:
        r4 = r3.zziwt;	 Catch:{ all -> 0x01be }
        if (r4 != 0) goto L_0x07ef;
    L_0x07e7:
        r4 = r3.zziwu;	 Catch:{ all -> 0x01be }
        if (r4 != 0) goto L_0x07ef;
    L_0x07eb:
        r4 = r3.zziwv;	 Catch:{ all -> 0x01be }
        if (r4 == 0) goto L_0x071d;
    L_0x07ef:
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r3 = r3.zza(r4, r5, r6);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r0 = r23;
        r0.put(r4, r3);	 Catch:{ all -> 0x01be }
        goto L_0x071d;
    L_0x0801:
        r4 = 0;
        goto L_0x07d0;
    L_0x0803:
        r0 = r25;
        r1 = r19;
        r5 = r0.nextInt(r1);	 Catch:{ all -> 0x01be }
        if (r5 != 0) goto L_0x0853;
    L_0x080d:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r28;
        r2 = r0.zzjim;	 Catch:{ all -> 0x01be }
        r5 = "_sr";
        r0 = r19;
        r6 = (long) r0;	 Catch:{ all -> 0x01be }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zza(r2, r5, r6);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r0.zzjim = r2;	 Catch:{ all -> 0x01be }
        r2 = r18 + 1;
        r24[r18] = r28;	 Catch:{ all -> 0x01be }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x01be }
        if (r4 == 0) goto L_0x083c;
    L_0x082f:
        r4 = 0;
        r0 = r19;
        r6 = (long) r0;	 Catch:{ all -> 0x01be }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x01be }
        r6 = 0;
        r3 = r3.zza(r4, r5, r6);	 Catch:{ all -> 0x01be }
    L_0x083c:
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r5 = r0.zzjin;	 Catch:{ all -> 0x01be }
        r6 = r5.longValue();	 Catch:{ all -> 0x01be }
        r3 = r3.zzbb(r6);	 Catch:{ all -> 0x01be }
        r0 = r23;
        r0.put(r4, r3);	 Catch:{ all -> 0x01be }
        goto L_0x071d;
    L_0x0853:
        r6 = r3.zziws;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r5 = r0.zzjin;	 Catch:{ all -> 0x01be }
        r8 = r5.longValue();	 Catch:{ all -> 0x01be }
        r6 = r8 - r6;
        r6 = java.lang.Math.abs(r6);	 Catch:{ all -> 0x01be }
        r8 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 < 0) goto L_0x08cb;
    L_0x086a:
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r28;
        r2 = r0.zzjim;	 Catch:{ all -> 0x01be }
        r5 = "_efs";
        r6 = 1;
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zza(r2, r5, r6);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r0.zzjim = r2;	 Catch:{ all -> 0x01be }
        r30.zzawi();	 Catch:{ all -> 0x01be }
        r0 = r28;
        r2 = r0.zzjim;	 Catch:{ all -> 0x01be }
        r5 = "_sr";
        r0 = r19;
        r6 = (long) r0;	 Catch:{ all -> 0x01be }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x01be }
        r2 = com.google.android.gms.internal.zzckn.zza(r2, r5, r6);	 Catch:{ all -> 0x01be }
        r0 = r28;
        r0.zzjim = r2;	 Catch:{ all -> 0x01be }
        r2 = r18 + 1;
        r24[r18] = r28;	 Catch:{ all -> 0x01be }
        r4 = r4.booleanValue();	 Catch:{ all -> 0x01be }
        if (r4 == 0) goto L_0x08b4;
    L_0x08a3:
        r4 = 0;
        r0 = r19;
        r6 = (long) r0;	 Catch:{ all -> 0x01be }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x01be }
        r6 = 1;
        r6 = java.lang.Boolean.valueOf(r6);	 Catch:{ all -> 0x01be }
        r3 = r3.zza(r4, r5, r6);	 Catch:{ all -> 0x01be }
    L_0x08b4:
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r0 = r28;
        r5 = r0.zzjin;	 Catch:{ all -> 0x01be }
        r6 = r5.longValue();	 Catch:{ all -> 0x01be }
        r3 = r3.zzbb(r6);	 Catch:{ all -> 0x01be }
        r0 = r23;
        r0.put(r4, r3);	 Catch:{ all -> 0x01be }
        goto L_0x071d;
    L_0x08cb:
        r4 = r4.booleanValue();	 Catch:{ all -> 0x01be }
        if (r4 == 0) goto L_0x08e0;
    L_0x08d1:
        r0 = r28;
        r4 = r0.name;	 Catch:{ all -> 0x01be }
        r5 = 0;
        r6 = 0;
        r2 = r3.zza(r2, r5, r6);	 Catch:{ all -> 0x01be }
        r0 = r23;
        r0.put(r4, r2);	 Catch:{ all -> 0x01be }
    L_0x08e0:
        r2 = r18;
        goto L_0x071d;
    L_0x08e4:
        r0 = r22;
        r2 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r2 = r2.length;	 Catch:{ all -> 0x01be }
        r0 = r18;
        if (r0 >= r2) goto L_0x08fb;
    L_0x08ed:
        r0 = r24;
        r1 = r18;
        r2 = java.util.Arrays.copyOf(r0, r1);	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcky[]) r2;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjiu = r2;	 Catch:{ all -> 0x01be }
    L_0x08fb:
        r2 = r23.entrySet();	 Catch:{ all -> 0x01be }
        r3 = r2.iterator();	 Catch:{ all -> 0x01be }
    L_0x0903:
        r2 = r3.hasNext();	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x091d;
    L_0x0909:
        r2 = r3.next();	 Catch:{ all -> 0x01be }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ all -> 0x01be }
        r4 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r2 = r2.getValue();	 Catch:{ all -> 0x01be }
        r2 = (com.google.android.gms.internal.zzcft) r2;	 Catch:{ all -> 0x01be }
        r4.zza(r2);	 Catch:{ all -> 0x01be }
        goto L_0x0903;
    L_0x091d:
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjix = r2;	 Catch:{ all -> 0x01be }
        r2 = -9223372036854775808;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjiy = r2;	 Catch:{ all -> 0x01be }
        r2 = 0;
    L_0x0935:
        r0 = r22;
        r3 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r3 = r3.length;	 Catch:{ all -> 0x01be }
        if (r2 >= r3) goto L_0x0975;
    L_0x093c:
        r0 = r22;
        r3 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r3 = r3[r2];	 Catch:{ all -> 0x01be }
        r4 = r3.zzjin;	 Catch:{ all -> 0x01be }
        r4 = r4.longValue();	 Catch:{ all -> 0x01be }
        r0 = r22;
        r6 = r0.zzjix;	 Catch:{ all -> 0x01be }
        r6 = r6.longValue();	 Catch:{ all -> 0x01be }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x095a;
    L_0x0954:
        r4 = r3.zzjin;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjix = r4;	 Catch:{ all -> 0x01be }
    L_0x095a:
        r4 = r3.zzjin;	 Catch:{ all -> 0x01be }
        r4 = r4.longValue();	 Catch:{ all -> 0x01be }
        r0 = r22;
        r6 = r0.zzjiy;	 Catch:{ all -> 0x01be }
        r6 = r6.longValue();	 Catch:{ all -> 0x01be }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x0972;
    L_0x096c:
        r3 = r3.zzjin;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjiy = r3;	 Catch:{ all -> 0x01be }
    L_0x0972:
        r2 = r2 + 1;
        goto L_0x0935;
    L_0x0975:
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r6 = r2.zzch;	 Catch:{ all -> 0x01be }
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r7 = r2.zziv(r6);	 Catch:{ all -> 0x01be }
        if (r7 != 0) goto L_0x0a09;
    L_0x0985:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r2 = r2.zzayr();	 Catch:{ all -> 0x01be }
        r3 = "Bundling raw events w/o app info. appId";
        r0 = r21;
        r4 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r4.zzch;	 Catch:{ all -> 0x01be }
        r4 = com.google.android.gms.internal.zzcgj.zzje(r4);	 Catch:{ all -> 0x01be }
        r2.zzj(r3, r4);	 Catch:{ all -> 0x01be }
    L_0x099c:
        r0 = r22;
        r2 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r2 = r2.length;	 Catch:{ all -> 0x01be }
        if (r2 <= 0) goto L_0x09d8;
    L_0x09a3:
        r2 = r30.zzawj();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r3 = r3.zzch;	 Catch:{ all -> 0x01be }
        r2 = r2.zzjm(r3);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x09b7;
    L_0x09b3:
        r3 = r2.zzjib;	 Catch:{ all -> 0x01be }
        if (r3 != 0) goto L_0x0a8e;
    L_0x09b7:
        r0 = r21;
        r2 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r2 = r2.zziux;	 Catch:{ all -> 0x01be }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x01be }
        if (r2 == 0) goto L_0x0a75;
    L_0x09c3:
        r2 = -1;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjjs = r2;	 Catch:{ all -> 0x01be }
    L_0x09cd:
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r0 = r22;
        r1 = r17;
        r2.zza(r0, r1);	 Catch:{ all -> 0x01be }
    L_0x09d8:
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r0 = r21;
        r3 = r0.zzjdo;	 Catch:{ all -> 0x01be }
        r2.zzag(r3);	 Catch:{ all -> 0x01be }
        r3 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r2 = r3.getWritableDatabase();	 Catch:{ all -> 0x01be }
        r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)";
        r5 = 2;
        r5 = new java.lang.String[r5];	 Catch:{ SQLiteException -> 0x0a96 }
        r7 = 0;
        r5[r7] = r6;	 Catch:{ SQLiteException -> 0x0a96 }
        r7 = 1;
        r5[r7] = r6;	 Catch:{ SQLiteException -> 0x0a96 }
        r2.execSQL(r4, r5);	 Catch:{ SQLiteException -> 0x0a96 }
    L_0x09f9:
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x01be }
        r2 = r30.zzawg();
        r2.endTransaction();
        r2 = 1;
    L_0x0a08:
        return r2;
    L_0x0a09:
        r0 = r22;
        r2 = r0.zzjiu;	 Catch:{ all -> 0x01be }
        r2 = r2.length;	 Catch:{ all -> 0x01be }
        if (r2 <= 0) goto L_0x099c;
    L_0x0a10:
        r2 = r7.zzawt();	 Catch:{ all -> 0x01be }
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 == 0) goto L_0x0a71;
    L_0x0a1a:
        r4 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x01be }
    L_0x0a1e:
        r0 = r22;
        r0.zzjja = r4;	 Catch:{ all -> 0x01be }
        r4 = r7.zzaws();	 Catch:{ all -> 0x01be }
        r8 = 0;
        r8 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r8 != 0) goto L_0x0ac6;
    L_0x0a2c:
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 == 0) goto L_0x0a73;
    L_0x0a32:
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x01be }
    L_0x0a36:
        r0 = r22;
        r0.zzjiz = r2;	 Catch:{ all -> 0x01be }
        r7.zzaxc();	 Catch:{ all -> 0x01be }
        r2 = r7.zzawz();	 Catch:{ all -> 0x01be }
        r2 = (int) r2;	 Catch:{ all -> 0x01be }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjjl = r2;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r2 = r0.zzjix;	 Catch:{ all -> 0x01be }
        r2 = r2.longValue();	 Catch:{ all -> 0x01be }
        r7.zzak(r2);	 Catch:{ all -> 0x01be }
        r0 = r22;
        r2 = r0.zzjiy;	 Catch:{ all -> 0x01be }
        r2 = r2.longValue();	 Catch:{ all -> 0x01be }
        r7.zzal(r2);	 Catch:{ all -> 0x01be }
        r2 = r7.zzaxk();	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzivb = r2;	 Catch:{ all -> 0x01be }
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r2.zza(r7);	 Catch:{ all -> 0x01be }
        goto L_0x099c;
    L_0x0a71:
        r4 = 0;
        goto L_0x0a1e;
    L_0x0a73:
        r2 = 0;
        goto L_0x0a36;
    L_0x0a75:
        r2 = r30.zzawm();	 Catch:{ all -> 0x01be }
        r2 = r2.zzayt();	 Catch:{ all -> 0x01be }
        r3 = "Did not find measurement config or missing version info. appId";
        r0 = r21;
        r4 = r0.zzjdn;	 Catch:{ all -> 0x01be }
        r4 = r4.zzch;	 Catch:{ all -> 0x01be }
        r4 = com.google.android.gms.internal.zzcgj.zzje(r4);	 Catch:{ all -> 0x01be }
        r2.zzj(r3, r4);	 Catch:{ all -> 0x01be }
        goto L_0x09cd;
    L_0x0a8e:
        r2 = r2.zzjib;	 Catch:{ all -> 0x01be }
        r0 = r22;
        r0.zzjjs = r2;	 Catch:{ all -> 0x01be }
        goto L_0x09cd;
    L_0x0a96:
        r2 = move-exception;
        r3 = r3.zzawm();	 Catch:{ all -> 0x01be }
        r3 = r3.zzayr();	 Catch:{ all -> 0x01be }
        r4 = "Failed to remove unused event metadata. appId";
        r5 = com.google.android.gms.internal.zzcgj.zzje(r6);	 Catch:{ all -> 0x01be }
        r3.zze(r4, r5, r2);	 Catch:{ all -> 0x01be }
        goto L_0x09f9;
    L_0x0aaa:
        r2 = r30.zzawg();	 Catch:{ all -> 0x01be }
        r2.setTransactionSuccessful();	 Catch:{ all -> 0x01be }
        r2 = r30.zzawg();
        r2.endTransaction();
        r2 = 0;
        goto L_0x0a08;
    L_0x0abb:
        r2 = move-exception;
        r3 = r11;
        goto L_0x0354;
    L_0x0abf:
        r2 = move-exception;
        goto L_0x02e3;
    L_0x0ac2:
        r2 = move-exception;
        r4 = r12;
        goto L_0x02e3;
    L_0x0ac6:
        r2 = r4;
        goto L_0x0a2c;
    L_0x0ac9:
        r3 = r2;
        goto L_0x07c0;
    L_0x0acc:
        r19 = r2;
        goto L_0x074a;
    L_0x0ad0:
        r3 = r4;
        r4 = r5;
        goto L_0x05a1;
    L_0x0ad4:
        r17 = r2;
        goto L_0x0528;
    L_0x0ad8:
        r2 = r4;
        goto L_0x03d8;
    L_0x0adb:
        r2 = r12;
        r3 = r17;
        goto L_0x0158;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzchj.zzh(java.lang.String, long):boolean");
    }

    @WorkerThread
    private final zzcff zzjq(String str) {
        zzcfe zziv = zzawg().zziv(str);
        if (zziv == null || TextUtils.isEmpty(zziv.zzuy())) {
            zzawm().zzayw().zzj("No app data available; dropping", str);
            return null;
        }
        try {
            String str2 = zzbgc.zzcy(this.mContext).getPackageInfo(str, 0).versionName;
            if (!(zziv.zzuy() == null || zziv.zzuy().equals(str2))) {
                zzawm().zzayt().zzj("App version does not match; dropping. appId", zzcgj.zzje(str));
                return null;
            }
        } catch (NameNotFoundException e) {
        }
        return new zzcff(str, zziv.getGmpAppId(), zziv.zzuy(), zziv.zzawu(), zziv.zzawv(), zziv.zzaww(), zziv.zzawx(), null, zziv.zzawy(), false, zziv.zzawr(), zziv.zzaxl(), 0, 0, zziv.zzaxm());
    }

    public final Context getContext() {
        return this.mContext;
    }

    @WorkerThread
    public final boolean isEnabled() {
        boolean z = false;
        zzawl().zzut();
        zzwu();
        if (this.zzjcb.zzaxo()) {
            return false;
        }
        Boolean zzis = this.zzjcb.zzis("firebase_analytics_collection_enabled");
        if (zzis != null) {
            z = zzis.booleanValue();
        } else if (!zzcc.zzaiw()) {
            z = true;
        }
        return zzawn().zzbm(z);
    }

    @WorkerThread
    protected final void start() {
        zzawl().zzut();
        zzawg().zzaxv();
        if (zzawn().zzizw.get() == 0) {
            zzawn().zzizw.set(this.zzasd.currentTimeMillis());
        }
        if (Long.valueOf(zzawn().zzjab.get()).longValue() == 0) {
            zzawm().zzayx().zzj("Persisting first open", Long.valueOf(this.zzjdl));
            zzawn().zzjab.set(this.zzjdl);
        }
        if (zzazj()) {
            if (!TextUtils.isEmpty(zzawb().getGmpAppId())) {
                String zzaza = zzawn().zzaza();
                if (zzaza == null) {
                    zzawn().zzji(zzawb().getGmpAppId());
                } else if (!zzaza.equals(zzawb().getGmpAppId())) {
                    zzawm().zzayv().log("Rechecking which service to use due to a GMP App Id change");
                    zzawn().zzazd();
                    this.zzjcp.disconnect();
                    this.zzjcp.zzxr();
                    zzawn().zzji(zzawb().getGmpAppId());
                    zzawn().zzjab.set(this.zzjdl);
                    zzawn().zzjac.zzjk(null);
                }
            }
            zzawa().zzjj(zzawn().zzjac.zzazf());
            if (!TextUtils.isEmpty(zzawb().getGmpAppId())) {
                zzcih zzawa = zzawa();
                zzawa.zzut();
                zzawa.zzwu();
                if (zzawa.zzitk.zzazj()) {
                    zzawa.zzawd().zzbaf();
                    String zzaze = zzawa.zzawn().zzaze();
                    if (!TextUtils.isEmpty(zzaze)) {
                        zzawa.zzawc().zzwu();
                        if (!zzaze.equals(VERSION.RELEASE)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_po", zzaze);
                            zzawa.zzc(ReactScrollViewHelper.AUTO, "_ou", bundle);
                        }
                    }
                }
                zzawd().zza(new AtomicReference());
            }
        } else if (isEnabled()) {
            if (!zzawi().zzdu("android.permission.INTERNET")) {
                zzawm().zzayr().log("App is missing INTERNET permission");
            }
            if (!zzawi().zzdu("android.permission.ACCESS_NETWORK_STATE")) {
                zzawm().zzayr().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!zzbgc.zzcy(this.mContext).zzami()) {
                if (!zzcha.zzbi(this.mContext)) {
                    zzawm().zzayr().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzcjx.zzk(this.mContext, false)) {
                    zzawm().zzayr().log("AppMeasurementService not registered/enabled");
                }
            }
            zzawm().zzayr().log("Uploading is not possible. App measurement disabled");
        }
        zzazx();
    }

    @WorkerThread
    protected final void zza(int i, Throwable th, byte[] bArr) {
        zzawl().zzut();
        zzwu();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzjdj = false;
                zzbab();
            }
        }
        List<Long> list = this.zzjdc;
        this.zzjdc = null;
        if ((i == 200 || i == ScanDevice.LINK_USB) && th == null) {
            try {
                zzawn().zzizw.set(this.zzasd.currentTimeMillis());
                zzawn().zzizx.set(0);
                zzazx();
                zzawm().zzayx().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzawg().beginTransaction();
                zzcih zzawg;
                try {
                    for (Long l : list) {
                        zzawg = zzawg();
                        long longValue = l.longValue();
                        zzawg.zzut();
                        zzawg.zzwu();
                        if (zzawg.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                        }
                    }
                    zzawg().setTransactionSuccessful();
                    zzawg().endTransaction();
                    if (zzazp().zzzh() && zzazw()) {
                        zzazv();
                    } else {
                        this.zzjdg = -1;
                        zzazx();
                    }
                    this.zzjdh = 0;
                } catch (SQLiteException e) {
                    zzawg.zzawm().zzayr().zzj("Failed to delete a bundle in a queue table", e);
                    throw e;
                } catch (Throwable th3) {
                    zzawg().endTransaction();
                }
            } catch (SQLiteException e2) {
                zzawm().zzayr().zzj("Database error while trying to delete uploaded bundles", e2);
                this.zzjdh = this.zzasd.elapsedRealtime();
                zzawm().zzayx().zzj("Disable upload, time", Long.valueOf(this.zzjdh));
            }
        } else {
            zzawm().zzayx().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzawn().zzizx.set(this.zzasd.currentTimeMillis());
            boolean z = i == 503 || i == 429;
            if (z) {
                zzawn().zzizy.set(this.zzasd.currentTimeMillis());
            }
            zzazx();
        }
        this.zzjdj = false;
        zzbab();
    }

    @WorkerThread
    public final byte[] zza(@NonNull zzcfx com_google_android_gms_internal_zzcfx, @Size(min = 1) String str) {
        zzwu();
        zzawl().zzut();
        zzavw();
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        zzbq.zzgh(str);
        zzfhk com_google_android_gms_internal_zzcla = new zzcla();
        zzawg().beginTransaction();
        try {
            zzcfe zziv = zzawg().zziv(str);
            byte[] bArr;
            if (zziv == null) {
                zzawm().zzayw().zzj("Log and bundle not available. package_name", str);
                bArr = new byte[0];
                return bArr;
            } else if (zziv.zzawy()) {
                long j;
                if (("_iap".equals(com_google_android_gms_internal_zzcfx.name) || Event.ECOMMERCE_PURCHASE.equals(com_google_android_gms_internal_zzcfx.name)) && !zza(str, com_google_android_gms_internal_zzcfx)) {
                    zzawm().zzayt().zzj("Failed to handle purchase event at single event bundle creation. appId", zzcgj.zzje(str));
                }
                zzclb com_google_android_gms_internal_zzclb = new zzclb();
                com_google_android_gms_internal_zzcla.zzjir = new zzclb[]{com_google_android_gms_internal_zzclb};
                com_google_android_gms_internal_zzclb.zzjit = Integer.valueOf(1);
                com_google_android_gms_internal_zzclb.zzjjb = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                com_google_android_gms_internal_zzclb.zzch = zziv.getAppId();
                com_google_android_gms_internal_zzclb.zziuy = zziv.zzawv();
                com_google_android_gms_internal_zzclb.zzicq = zziv.zzuy();
                long zzawu = zziv.zzawu();
                com_google_android_gms_internal_zzclb.zzjjo = zzawu == -2147483648L ? null : Integer.valueOf((int) zzawu);
                com_google_android_gms_internal_zzclb.zzjjf = Long.valueOf(zziv.zzaww());
                com_google_android_gms_internal_zzclb.zziux = zziv.getGmpAppId();
                com_google_android_gms_internal_zzclb.zzjjk = Long.valueOf(zziv.zzawx());
                if (isEnabled() && zzcfk.zzaxs() && this.zzjcb.zzit(com_google_android_gms_internal_zzclb.zzch)) {
                    zzawb();
                    com_google_android_gms_internal_zzclb.zzjjt = null;
                }
                Pair zzjg = zzawn().zzjg(zziv.getAppId());
                if (!(!zziv.zzaxm() || zzjg == null || TextUtils.isEmpty((CharSequence) zzjg.first))) {
                    com_google_android_gms_internal_zzclb.zzjjh = (String) zzjg.first;
                    com_google_android_gms_internal_zzclb.zzjji = (Boolean) zzjg.second;
                }
                zzawc().zzwu();
                com_google_android_gms_internal_zzclb.zzjjc = Build.MODEL;
                zzawc().zzwu();
                com_google_android_gms_internal_zzclb.zzcv = VERSION.RELEASE;
                com_google_android_gms_internal_zzclb.zzjje = Integer.valueOf((int) zzawc().zzayi());
                com_google_android_gms_internal_zzclb.zzjjd = zzawc().zzayj();
                com_google_android_gms_internal_zzclb.zzjjj = zziv.getAppInstanceId();
                com_google_android_gms_internal_zzclb.zzivf = zziv.zzawr();
                List zziu = zzawg().zziu(zziv.getAppId());
                com_google_android_gms_internal_zzclb.zzjiv = new zzcld[zziu.size()];
                for (int i = 0; i < zziu.size(); i++) {
                    zzcld com_google_android_gms_internal_zzcld = new zzcld();
                    com_google_android_gms_internal_zzclb.zzjiv[i] = com_google_android_gms_internal_zzcld;
                    com_google_android_gms_internal_zzcld.name = ((zzckm) zziu.get(i)).mName;
                    com_google_android_gms_internal_zzcld.zzjjx = Long.valueOf(((zzckm) zziu.get(i)).zzjgr);
                    zzawi().zza(com_google_android_gms_internal_zzcld, ((zzckm) zziu.get(i)).mValue);
                }
                Bundle zzayl = com_google_android_gms_internal_zzcfx.zziwy.zzayl();
                if ("_iap".equals(com_google_android_gms_internal_zzcfx.name)) {
                    zzayl.putLong("_c", 1);
                    zzawm().zzayw().log("Marking in-app purchase as real-time");
                    zzayl.putLong("_r", 1);
                }
                zzayl.putString("_o", com_google_android_gms_internal_zzcfx.zzivk);
                if (zzawi().zzkd(com_google_android_gms_internal_zzclb.zzch)) {
                    zzawi().zza(zzayl, "_dbg", Long.valueOf(1));
                    zzawi().zza(zzayl, "_r", Long.valueOf(1));
                }
                zzcft zzae = zzawg().zzae(str, com_google_android_gms_internal_zzcfx.name);
                if (zzae == null) {
                    zzawg().zza(new zzcft(str, com_google_android_gms_internal_zzcfx.name, 1, 0, com_google_android_gms_internal_zzcfx.zziwz, 0, null, null, null));
                    j = 0;
                } else {
                    j = zzae.zziwr;
                    zzawg().zza(zzae.zzba(com_google_android_gms_internal_zzcfx.zziwz).zzayk());
                }
                zzcfs com_google_android_gms_internal_zzcfs = new zzcfs(this, com_google_android_gms_internal_zzcfx.zzivk, str, com_google_android_gms_internal_zzcfx.name, com_google_android_gms_internal_zzcfx.zziwz, j, zzayl);
                zzcky com_google_android_gms_internal_zzcky = new zzcky();
                com_google_android_gms_internal_zzclb.zzjiu = new zzcky[]{com_google_android_gms_internal_zzcky};
                com_google_android_gms_internal_zzcky.zzjin = Long.valueOf(com_google_android_gms_internal_zzcfs.zzffr);
                com_google_android_gms_internal_zzcky.name = com_google_android_gms_internal_zzcfs.mName;
                com_google_android_gms_internal_zzcky.zzjio = Long.valueOf(com_google_android_gms_internal_zzcfs.zziwn);
                com_google_android_gms_internal_zzcky.zzjim = new zzckz[com_google_android_gms_internal_zzcfs.zziwo.size()];
                Iterator it = com_google_android_gms_internal_zzcfs.zziwo.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    zzckz com_google_android_gms_internal_zzckz = new zzckz();
                    int i3 = i2 + 1;
                    com_google_android_gms_internal_zzcky.zzjim[i2] = com_google_android_gms_internal_zzckz;
                    com_google_android_gms_internal_zzckz.name = str2;
                    zzawi().zza(com_google_android_gms_internal_zzckz, com_google_android_gms_internal_zzcfs.zziwo.get(str2));
                    i2 = i3;
                }
                com_google_android_gms_internal_zzclb.zzjjn = zza(zziv.getAppId(), com_google_android_gms_internal_zzclb.zzjiv, com_google_android_gms_internal_zzclb.zzjiu);
                com_google_android_gms_internal_zzclb.zzjix = com_google_android_gms_internal_zzcky.zzjin;
                com_google_android_gms_internal_zzclb.zzjiy = com_google_android_gms_internal_zzcky.zzjin;
                zzawu = zziv.zzawt();
                com_google_android_gms_internal_zzclb.zzjja = zzawu != 0 ? Long.valueOf(zzawu) : null;
                long zzaws = zziv.zzaws();
                if (zzaws != 0) {
                    zzawu = zzaws;
                }
                com_google_android_gms_internal_zzclb.zzjiz = zzawu != 0 ? Long.valueOf(zzawu) : null;
                zziv.zzaxc();
                com_google_android_gms_internal_zzclb.zzjjl = Integer.valueOf((int) zziv.zzawz());
                com_google_android_gms_internal_zzclb.zzjjg = Long.valueOf(11720);
                com_google_android_gms_internal_zzclb.zzjiw = Long.valueOf(this.zzasd.currentTimeMillis());
                com_google_android_gms_internal_zzclb.zzjjm = Boolean.TRUE;
                zziv.zzak(com_google_android_gms_internal_zzclb.zzjix.longValue());
                zziv.zzal(com_google_android_gms_internal_zzclb.zzjiy.longValue());
                zzawg().zza(zziv);
                zzawg().setTransactionSuccessful();
                zzawg().endTransaction();
                try {
                    bArr = new byte[com_google_android_gms_internal_zzcla.zzhl()];
                    zzfhc zzo = zzfhc.zzo(bArr, 0, bArr.length);
                    com_google_android_gms_internal_zzcla.zza(zzo);
                    zzo.zzcus();
                    return zzawi().zzp(bArr);
                } catch (IOException e) {
                    zzawm().zzayr().zze("Data loss. Failed to bundle and serialize. appId", zzcgj.zzje(str), e);
                    return null;
                }
            } else {
                zzawm().zzayw().zzj("Log and bundle disabled. package_name", str);
                bArr = new byte[0];
                zzawg().endTransaction();
                return bArr;
            }
        } finally {
            zzawg().endTransaction();
        }
    }

    public final zzcfa zzavy() {
        zza(this.zzjcw);
        return this.zzjcw;
    }

    public final zzcfh zzavz() {
        zza(this.zzjcv);
        return this.zzjcv;
    }

    public final zzcik zzawa() {
        zza(this.zzjcr);
        return this.zzjcr;
    }

    public final zzcge zzawb() {
        zza(this.zzjcs);
        return this.zzjcs;
    }

    public final zzcfr zzawc() {
        zza(this.zzjcq);
        return this.zzjcq;
    }

    public final zzcjd zzawd() {
        zza(this.zzjcp);
        return this.zzjcp;
    }

    public final zzciz zzawe() {
        zza(this.zzjco);
        return this.zzjco;
    }

    public final zzcgf zzawf() {
        zza(this.zzjcm);
        return this.zzjcm;
    }

    public final zzcfl zzawg() {
        zza(this.zzjcl);
        return this.zzjcl;
    }

    public final zzcgh zzawh() {
        zza(this.zzjck);
        return this.zzjck;
    }

    public final zzckn zzawi() {
        zza(this.zzjcj);
        return this.zzjcj;
    }

    public final zzchd zzawj() {
        zza(this.zzjcg);
        return this.zzjcg;
    }

    public final zzckc zzawk() {
        zza(this.zzjcf);
        return this.zzjcf;
    }

    public final zzche zzawl() {
        zza(this.zzjce);
        return this.zzjce;
    }

    public final zzcgj zzawm() {
        zza(this.zzjcd);
        return this.zzjcd;
    }

    public final zzcgu zzawn() {
        zza(this.zzjcc);
        return this.zzjcc;
    }

    public final zzcfk zzawo() {
        return this.zzjcb;
    }

    @WorkerThread
    protected final boolean zzazj() {
        boolean z = false;
        zzwu();
        zzawl().zzut();
        if (this.zzjcy == null || this.zzjcz == 0 || !(this.zzjcy == null || this.zzjcy.booleanValue() || Math.abs(this.zzasd.elapsedRealtime() - this.zzjcz) <= 1000)) {
            this.zzjcz = this.zzasd.elapsedRealtime();
            if (zzawi().zzdu("android.permission.INTERNET") && zzawi().zzdu("android.permission.ACCESS_NETWORK_STATE") && (zzbgc.zzcy(this.mContext).zzami() || (zzcha.zzbi(this.mContext) && zzcjx.zzk(this.mContext, false)))) {
                z = true;
            }
            this.zzjcy = Boolean.valueOf(z);
            if (this.zzjcy.booleanValue()) {
                this.zzjcy = Boolean.valueOf(zzawi().zzka(zzawb().getGmpAppId()));
            }
        }
        return this.zzjcy.booleanValue();
    }

    public final zzcgj zzazl() {
        return (this.zzjcd == null || !this.zzjcd.isInitialized()) ? null : this.zzjcd;
    }

    final zzche zzazm() {
        return this.zzjce;
    }

    public final AppMeasurement zzazn() {
        return this.zzjch;
    }

    public final FirebaseAnalytics zzazo() {
        return this.zzjci;
    }

    public final zzcgn zzazp() {
        zza(this.zzjcn);
        return this.zzjcn;
    }

    final long zzazt() {
        Long valueOf = Long.valueOf(zzawn().zzjab.get());
        return valueOf.longValue() == 0 ? this.zzjdl : Math.min(this.zzjdl, valueOf.longValue());
    }

    @WorkerThread
    public final void zzazv() {
        zzawl().zzut();
        zzwu();
        this.zzjdk = true;
        String zzaxt;
        String str;
        try {
            Boolean zzbag = zzawd().zzbag();
            if (zzbag == null) {
                zzawm().zzayt().log("Upload data called on the client side before use of service was decided");
                this.zzjdk = false;
                zzbab();
            } else if (zzbag.booleanValue()) {
                zzawm().zzayr().log("Upload called in the client side when service should be used");
                this.zzjdk = false;
                zzbab();
            } else if (this.zzjdh > 0) {
                zzazx();
                this.zzjdk = false;
                zzbab();
            } else {
                zzawl().zzut();
                if ((this.zzjdc != null ? 1 : null) != null) {
                    zzawm().zzayx().log("Uploading requested multiple times");
                    this.zzjdk = false;
                    zzbab();
                } else if (zzazp().zzzh()) {
                    long currentTimeMillis = this.zzasd.currentTimeMillis();
                    zzh(null, currentTimeMillis - zzcfk.zzaxq());
                    long j = zzawn().zzizw.get();
                    if (j != 0) {
                        zzawm().zzayw().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
                    }
                    zzaxt = zzawg().zzaxt();
                    Object zzaz;
                    if (TextUtils.isEmpty(zzaxt)) {
                        this.zzjdg = -1;
                        zzaz = zzawg().zzaz(currentTimeMillis - zzcfk.zzaxq());
                        if (!TextUtils.isEmpty(zzaz)) {
                            zzcfe zziv = zzawg().zziv(zzaz);
                            if (zziv != null) {
                                zzb(zziv);
                            }
                        }
                    } else {
                        if (this.zzjdg == -1) {
                            this.zzjdg = zzawg().zzaya();
                        }
                        List<Pair> zzl = zzawg().zzl(zzaxt, this.zzjcb.zzb(zzaxt, zzcfz.zzixo), Math.max(0, this.zzjcb.zzb(zzaxt, zzcfz.zzixp)));
                        if (!zzl.isEmpty()) {
                            zzclb com_google_android_gms_internal_zzclb;
                            Object obj;
                            int i;
                            List subList;
                            for (Pair pair : zzl) {
                                com_google_android_gms_internal_zzclb = (zzclb) pair.first;
                                if (!TextUtils.isEmpty(com_google_android_gms_internal_zzclb.zzjjh)) {
                                    obj = com_google_android_gms_internal_zzclb.zzjjh;
                                    break;
                                }
                            }
                            obj = null;
                            if (obj != null) {
                                for (i = 0; i < zzl.size(); i++) {
                                    com_google_android_gms_internal_zzclb = (zzclb) ((Pair) zzl.get(i)).first;
                                    if (!TextUtils.isEmpty(com_google_android_gms_internal_zzclb.zzjjh) && !com_google_android_gms_internal_zzclb.zzjjh.equals(obj)) {
                                        subList = zzl.subList(0, i);
                                        break;
                                    }
                                }
                            }
                            subList = zzl;
                            zzcla com_google_android_gms_internal_zzcla = new zzcla();
                            com_google_android_gms_internal_zzcla.zzjir = new zzclb[subList.size()];
                            Collection arrayList = new ArrayList(subList.size());
                            Object obj2 = (zzcfk.zzaxs() && this.zzjcb.zzit(zzaxt)) ? 1 : null;
                            for (i = 0; i < com_google_android_gms_internal_zzcla.zzjir.length; i++) {
                                com_google_android_gms_internal_zzcla.zzjir[i] = (zzclb) ((Pair) subList.get(i)).first;
                                arrayList.add((Long) ((Pair) subList.get(i)).second);
                                com_google_android_gms_internal_zzcla.zzjir[i].zzjjg = Long.valueOf(11720);
                                com_google_android_gms_internal_zzcla.zzjir[i].zzjiw = Long.valueOf(currentTimeMillis);
                                com_google_android_gms_internal_zzcla.zzjir[i].zzjjm = Boolean.valueOf(false);
                                if (obj2 == null) {
                                    com_google_android_gms_internal_zzcla.zzjir[i].zzjjt = null;
                                }
                            }
                            obj2 = zzawm().zzae(2) ? zzawh().zza(com_google_android_gms_internal_zzcla) : null;
                            obj = zzawi().zzb(com_google_android_gms_internal_zzcla);
                            str = (String) zzcfz.zzixy.get();
                            URL url = new URL(str);
                            zzbq.checkArgument(!arrayList.isEmpty());
                            if (this.zzjdc != null) {
                                zzawm().zzayr().log("Set uploading progress before finishing the previous upload");
                            } else {
                                this.zzjdc = new ArrayList(arrayList);
                            }
                            zzawn().zzizx.set(currentTimeMillis);
                            zzaz = "?";
                            if (com_google_android_gms_internal_zzcla.zzjir.length > 0) {
                                zzaz = com_google_android_gms_internal_zzcla.zzjir[0].zzch;
                            }
                            zzawm().zzayx().zzd("Uploading data. app, uncompressed size, data", zzaz, Integer.valueOf(obj.length), obj2);
                            this.zzjdj = true;
                            zzcih zzazp = zzazp();
                            zzcgp com_google_android_gms_internal_zzchm = new zzchm(this);
                            zzazp.zzut();
                            zzazp.zzwu();
                            zzbq.checkNotNull(url);
                            zzbq.checkNotNull(obj);
                            zzbq.checkNotNull(com_google_android_gms_internal_zzchm);
                            zzazp.zzawl().zzh(new zzcgr(zzazp, zzaxt, url, obj, null, com_google_android_gms_internal_zzchm));
                        }
                    }
                    this.zzjdk = false;
                    zzbab();
                } else {
                    zzawm().zzayx().log("Network not connected, ignoring upload request");
                    zzazx();
                    this.zzjdk = false;
                    zzbab();
                }
            }
        } catch (MalformedURLException e) {
            zzawm().zzayr().zze("Failed to parse upload URL. Not uploading. appId", zzcgj.zzje(zzaxt), str);
        } catch (Throwable th) {
            this.zzjdk = false;
            zzbab();
        }
    }

    final void zzazy() {
        this.zzjdf++;
    }

    @WorkerThread
    final void zzazz() {
        zzawl().zzut();
        zzwu();
        if (!this.zzjcx) {
            zzawm().zzayv().log("This instance being marked as an uploader");
            zzawl().zzut();
            zzwu();
            if (zzbaa() && zzazs()) {
                int zza = zza(this.zzjdb);
                int zzayo = zzawb().zzayo();
                zzawl().zzut();
                if (zza > zzayo) {
                    zzawm().zzayr().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza), Integer.valueOf(zzayo));
                } else if (zza < zzayo) {
                    if (zza(zzayo, this.zzjdb)) {
                        zzawm().zzayx().zze("Storage version upgraded. Previous, current version", Integer.valueOf(zza), Integer.valueOf(zzayo));
                    } else {
                        zzawm().zzayr().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza), Integer.valueOf(zzayo));
                    }
                }
            }
            this.zzjcx = true;
            zzazx();
        }
    }

    @WorkerThread
    final void zzb(zzcfi com_google_android_gms_internal_zzcfi, zzcff com_google_android_gms_internal_zzcff) {
        boolean z = true;
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        zzbq.zzgh(com_google_android_gms_internal_zzcfi.packageName);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi.zzivk);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi.zzivl);
        zzbq.zzgh(com_google_android_gms_internal_zzcfi.zzivl.name);
        zzawl().zzut();
        zzwu();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux)) {
            if (com_google_android_gms_internal_zzcff.zzivc) {
                zzcfi com_google_android_gms_internal_zzcfi2 = new zzcfi(com_google_android_gms_internal_zzcfi);
                com_google_android_gms_internal_zzcfi2.zzivn = false;
                zzawg().beginTransaction();
                try {
                    zzcfi zzah = zzawg().zzah(com_google_android_gms_internal_zzcfi2.packageName, com_google_android_gms_internal_zzcfi2.zzivl.name);
                    if (!(zzah == null || zzah.zzivk.equals(com_google_android_gms_internal_zzcfi2.zzivk))) {
                        zzawm().zzayt().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", zzawh().zzjd(com_google_android_gms_internal_zzcfi2.zzivl.name), com_google_android_gms_internal_zzcfi2.zzivk, zzah.zzivk);
                    }
                    if (zzah != null && zzah.zzivn) {
                        com_google_android_gms_internal_zzcfi2.zzivk = zzah.zzivk;
                        com_google_android_gms_internal_zzcfi2.zzivm = zzah.zzivm;
                        com_google_android_gms_internal_zzcfi2.zzivq = zzah.zzivq;
                        com_google_android_gms_internal_zzcfi2.zzivo = zzah.zzivo;
                        com_google_android_gms_internal_zzcfi2.zzivr = zzah.zzivr;
                        com_google_android_gms_internal_zzcfi2.zzivn = zzah.zzivn;
                        com_google_android_gms_internal_zzcfi2.zzivl = new zzckk(com_google_android_gms_internal_zzcfi2.zzivl.name, zzah.zzivl.zzjgn, com_google_android_gms_internal_zzcfi2.zzivl.getValue(), zzah.zzivl.zzivk);
                        z = false;
                    } else if (TextUtils.isEmpty(com_google_android_gms_internal_zzcfi2.zzivo)) {
                        com_google_android_gms_internal_zzcfi2.zzivl = new zzckk(com_google_android_gms_internal_zzcfi2.zzivl.name, com_google_android_gms_internal_zzcfi2.zzivm, com_google_android_gms_internal_zzcfi2.zzivl.getValue(), com_google_android_gms_internal_zzcfi2.zzivl.zzivk);
                        com_google_android_gms_internal_zzcfi2.zzivn = true;
                    } else {
                        z = false;
                    }
                    if (com_google_android_gms_internal_zzcfi2.zzivn) {
                        zzckk com_google_android_gms_internal_zzckk = com_google_android_gms_internal_zzcfi2.zzivl;
                        zzckm com_google_android_gms_internal_zzckm = new zzckm(com_google_android_gms_internal_zzcfi2.packageName, com_google_android_gms_internal_zzcfi2.zzivk, com_google_android_gms_internal_zzckk.name, com_google_android_gms_internal_zzckk.zzjgn, com_google_android_gms_internal_zzckk.getValue());
                        if (zzawg().zza(com_google_android_gms_internal_zzckm)) {
                            zzawm().zzayw().zzd("User property updated immediately", com_google_android_gms_internal_zzcfi2.packageName, zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), com_google_android_gms_internal_zzckm.mValue);
                        } else {
                            zzawm().zzayr().zzd("(2)Too many active user properties, ignoring", zzcgj.zzje(com_google_android_gms_internal_zzcfi2.packageName), zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), com_google_android_gms_internal_zzckm.mValue);
                        }
                        if (z && com_google_android_gms_internal_zzcfi2.zzivr != null) {
                            zzc(new zzcfx(com_google_android_gms_internal_zzcfi2.zzivr, com_google_android_gms_internal_zzcfi2.zzivm), com_google_android_gms_internal_zzcff);
                        }
                    }
                    if (zzawg().zza(com_google_android_gms_internal_zzcfi2)) {
                        zzawm().zzayw().zzd("Conditional property added", com_google_android_gms_internal_zzcfi2.packageName, zzawh().zzjd(com_google_android_gms_internal_zzcfi2.zzivl.name), com_google_android_gms_internal_zzcfi2.zzivl.getValue());
                    } else {
                        zzawm().zzayr().zzd("Too many conditional properties, ignoring", zzcgj.zzje(com_google_android_gms_internal_zzcfi2.packageName), zzawh().zzjd(com_google_android_gms_internal_zzcfi2.zzivl.name), com_google_android_gms_internal_zzcfi2.zzivl.getValue());
                    }
                    zzawg().setTransactionSuccessful();
                } finally {
                    zzawg().endTransaction();
                }
            } else {
                zzg(com_google_android_gms_internal_zzcff);
            }
        }
    }

    @WorkerThread
    final void zzb(zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
        zzbq.zzgh(com_google_android_gms_internal_zzcff.packageName);
        zzawl().zzut();
        zzwu();
        String str = com_google_android_gms_internal_zzcff.packageName;
        long j = com_google_android_gms_internal_zzcfx.zziwz;
        zzawi();
        if (!zzckn.zzd(com_google_android_gms_internal_zzcfx, com_google_android_gms_internal_zzcff)) {
            return;
        }
        if (com_google_android_gms_internal_zzcff.zzivc) {
            zzawg().beginTransaction();
            try {
                List emptyList;
                Object obj;
                zzcih zzawg = zzawg();
                zzbq.zzgh(str);
                zzawg.zzut();
                zzawg.zzwu();
                if (j < 0) {
                    zzawg.zzawm().zzayt().zze("Invalid time querying timed out conditional properties", zzcgj.zzje(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzawg.zzc("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzcfi com_google_android_gms_internal_zzcfi : r2) {
                    if (com_google_android_gms_internal_zzcfi != null) {
                        zzawm().zzayw().zzd("User property timed out", com_google_android_gms_internal_zzcfi.packageName, zzawh().zzjd(com_google_android_gms_internal_zzcfi.zzivl.name), com_google_android_gms_internal_zzcfi.zzivl.getValue());
                        if (com_google_android_gms_internal_zzcfi.zzivp != null) {
                            zzc(new zzcfx(com_google_android_gms_internal_zzcfi.zzivp, j), com_google_android_gms_internal_zzcff);
                        }
                        zzawg().zzai(str, com_google_android_gms_internal_zzcfi.zzivl.name);
                    }
                }
                zzawg = zzawg();
                zzbq.zzgh(str);
                zzawg.zzut();
                zzawg.zzwu();
                if (j < 0) {
                    zzawg.zzawm().zzayt().zze("Invalid time querying expired conditional properties", zzcgj.zzje(str), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzawg.zzc("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                List arrayList = new ArrayList(r2.size());
                for (zzcfi com_google_android_gms_internal_zzcfi2 : r2) {
                    if (com_google_android_gms_internal_zzcfi2 != null) {
                        zzawm().zzayw().zzd("User property expired", com_google_android_gms_internal_zzcfi2.packageName, zzawh().zzjd(com_google_android_gms_internal_zzcfi2.zzivl.name), com_google_android_gms_internal_zzcfi2.zzivl.getValue());
                        zzawg().zzaf(str, com_google_android_gms_internal_zzcfi2.zzivl.name);
                        if (com_google_android_gms_internal_zzcfi2.zzivt != null) {
                            arrayList.add(com_google_android_gms_internal_zzcfi2.zzivt);
                        }
                        zzawg().zzai(str, com_google_android_gms_internal_zzcfi2.zzivl.name);
                    }
                }
                ArrayList arrayList2 = (ArrayList) arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    obj = arrayList2.get(i);
                    i++;
                    zzc(new zzcfx((zzcfx) obj, j), com_google_android_gms_internal_zzcff);
                }
                zzawg = zzawg();
                String str2 = com_google_android_gms_internal_zzcfx.name;
                zzbq.zzgh(str);
                zzbq.zzgh(str2);
                zzawg.zzut();
                zzawg.zzwu();
                if (j < 0) {
                    zzawg.zzawm().zzayt().zzd("Invalid time querying triggered conditional properties", zzcgj.zzje(str), zzawg.zzawh().zzjb(str2), Long.valueOf(j));
                    emptyList = Collections.emptyList();
                } else {
                    emptyList = zzawg.zzc("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                List arrayList3 = new ArrayList(r2.size());
                for (zzcfi com_google_android_gms_internal_zzcfi3 : r2) {
                    if (com_google_android_gms_internal_zzcfi3 != null) {
                        zzckk com_google_android_gms_internal_zzckk = com_google_android_gms_internal_zzcfi3.zzivl;
                        zzckm com_google_android_gms_internal_zzckm = new zzckm(com_google_android_gms_internal_zzcfi3.packageName, com_google_android_gms_internal_zzcfi3.zzivk, com_google_android_gms_internal_zzckk.name, j, com_google_android_gms_internal_zzckk.getValue());
                        if (zzawg().zza(com_google_android_gms_internal_zzckm)) {
                            zzawm().zzayw().zzd("User property triggered", com_google_android_gms_internal_zzcfi3.packageName, zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), com_google_android_gms_internal_zzckm.mValue);
                        } else {
                            zzawm().zzayr().zzd("Too many active user properties, ignoring", zzcgj.zzje(com_google_android_gms_internal_zzcfi3.packageName), zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), com_google_android_gms_internal_zzckm.mValue);
                        }
                        if (com_google_android_gms_internal_zzcfi3.zzivr != null) {
                            arrayList3.add(com_google_android_gms_internal_zzcfi3.zzivr);
                        }
                        com_google_android_gms_internal_zzcfi3.zzivl = new zzckk(com_google_android_gms_internal_zzckm);
                        com_google_android_gms_internal_zzcfi3.zzivn = true;
                        zzawg().zza(com_google_android_gms_internal_zzcfi3);
                    }
                }
                zzc(com_google_android_gms_internal_zzcfx, com_google_android_gms_internal_zzcff);
                arrayList2 = (ArrayList) arrayList3;
                int size2 = arrayList2.size();
                i = 0;
                while (i < size2) {
                    obj = arrayList2.get(i);
                    i++;
                    zzc(new zzcfx((zzcfx) obj, j), com_google_android_gms_internal_zzcff);
                }
                zzawg().setTransactionSuccessful();
            } finally {
                zzawg().endTransaction();
            }
        } else {
            zzg(com_google_android_gms_internal_zzcff);
        }
    }

    @WorkerThread
    final void zzb(zzcfx com_google_android_gms_internal_zzcfx, String str) {
        zzcfe zziv = zzawg().zziv(str);
        if (zziv == null || TextUtils.isEmpty(zziv.zzuy())) {
            zzawm().zzayw().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = zzbgc.zzcy(this.mContext).getPackageInfo(str, 0).versionName;
            if (!(zziv.zzuy() == null || zziv.zzuy().equals(str2))) {
                zzawm().zzayt().zzj("App version does not match; dropping event. appId", zzcgj.zzje(str));
                return;
            }
        } catch (NameNotFoundException e) {
            if (!"_ui".equals(com_google_android_gms_internal_zzcfx.name)) {
                zzawm().zzayt().zzj("Could not find package. appId", zzcgj.zzje(str));
            }
        }
        zzcfx com_google_android_gms_internal_zzcfx2 = com_google_android_gms_internal_zzcfx;
        zzb(com_google_android_gms_internal_zzcfx2, new zzcff(str, zziv.getGmpAppId(), zziv.zzuy(), zziv.zzawu(), zziv.zzawv(), zziv.zzaww(), zziv.zzawx(), null, zziv.zzawy(), false, zziv.zzawr(), zziv.zzaxl(), 0, 0, zziv.zzaxm()));
    }

    final void zzb(zzcii com_google_android_gms_internal_zzcii) {
        this.zzjde++;
    }

    @WorkerThread
    final void zzb(zzckk com_google_android_gms_internal_zzckk, zzcff com_google_android_gms_internal_zzcff) {
        int i = 0;
        zzawl().zzut();
        zzwu();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux)) {
            if (com_google_android_gms_internal_zzcff.zzivc) {
                int zzjx = zzawi().zzjx(com_google_android_gms_internal_zzckk.name);
                String zza;
                if (zzjx != 0) {
                    zzawi();
                    zza = zzckn.zza(com_google_android_gms_internal_zzckk.name, 24, true);
                    if (com_google_android_gms_internal_zzckk.name != null) {
                        i = com_google_android_gms_internal_zzckk.name.length();
                    }
                    zzawi().zza(com_google_android_gms_internal_zzcff.packageName, zzjx, "_ev", zza, i);
                    return;
                }
                zzjx = zzawi().zzl(com_google_android_gms_internal_zzckk.name, com_google_android_gms_internal_zzckk.getValue());
                if (zzjx != 0) {
                    zzawi();
                    zza = zzckn.zza(com_google_android_gms_internal_zzckk.name, 24, true);
                    Object value = com_google_android_gms_internal_zzckk.getValue();
                    if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                        i = String.valueOf(value).length();
                    }
                    zzawi().zza(com_google_android_gms_internal_zzcff.packageName, zzjx, "_ev", zza, i);
                    return;
                }
                Object zzm = zzawi().zzm(com_google_android_gms_internal_zzckk.name, com_google_android_gms_internal_zzckk.getValue());
                if (zzm != null) {
                    zzckm com_google_android_gms_internal_zzckm = new zzckm(com_google_android_gms_internal_zzcff.packageName, com_google_android_gms_internal_zzckk.zzivk, com_google_android_gms_internal_zzckk.name, com_google_android_gms_internal_zzckk.zzjgn, zzm);
                    zzawm().zzayw().zze("Setting user property", zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), zzm);
                    zzawg().beginTransaction();
                    try {
                        zzg(com_google_android_gms_internal_zzcff);
                        boolean zza2 = zzawg().zza(com_google_android_gms_internal_zzckm);
                        zzawg().setTransactionSuccessful();
                        if (zza2) {
                            zzawm().zzayw().zze("User property set", zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), com_google_android_gms_internal_zzckm.mValue);
                        } else {
                            zzawm().zzayr().zze("Too many unique user properties are set. Ignoring user property", zzawh().zzjd(com_google_android_gms_internal_zzckm.mName), com_google_android_gms_internal_zzckm.mValue);
                            zzawi().zza(com_google_android_gms_internal_zzcff.packageName, 9, null, null, 0);
                        }
                        zzawg().endTransaction();
                        return;
                    } catch (Throwable th) {
                        zzawg().endTransaction();
                    }
                } else {
                    return;
                }
            }
            zzg(com_google_android_gms_internal_zzcff);
        }
    }

    @WorkerThread
    final void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        boolean z = true;
        zzawl().zzut();
        zzwu();
        zzbq.zzgh(str);
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzjdi = false;
                zzbab();
            }
        }
        zzawm().zzayx().zzj("onConfigFetched. Response size", Integer.valueOf(bArr.length));
        zzawg().beginTransaction();
        zzcfe zziv = zzawg().zziv(str);
        boolean z2 = (i == 200 || i == ScanDevice.LINK_USB || i == 304) && th == null;
        if (zziv == null) {
            zzawm().zzayt().zzj("App does not exist in onConfigFetched. appId", zzcgj.zzje(str));
        } else if (z2 || i == HttpStatus.HTTP_NOT_FOUND) {
            List list = map != null ? (List) map.get("Last-Modified") : null;
            String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
            if (i == HttpStatus.HTTP_NOT_FOUND || i == 304) {
                if (zzawj().zzjm(str) == null && !zzawj().zzb(str, null, null)) {
                    zzawg().endTransaction();
                    this.zzjdi = false;
                    zzbab();
                    return;
                }
            } else if (!zzawj().zzb(str, bArr, str2)) {
                zzawg().endTransaction();
                this.zzjdi = false;
                zzbab();
                return;
            }
            zziv.zzaq(this.zzasd.currentTimeMillis());
            zzawg().zza(zziv);
            if (i == HttpStatus.HTTP_NOT_FOUND) {
                zzawm().zzayu().zzj("Config not found. Using empty config. appId", str);
            } else {
                zzawm().zzayx().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            }
            if (zzazp().zzzh() && zzazw()) {
                zzazv();
            } else {
                zzazx();
            }
        } else {
            zziv.zzar(this.zzasd.currentTimeMillis());
            zzawg().zza(zziv);
            zzawm().zzayx().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
            zzawj().zzjo(str);
            zzawn().zzizx.set(this.zzasd.currentTimeMillis());
            if (!(i == 503 || i == 429)) {
                z = false;
            }
            if (z) {
                zzawn().zzizy.set(this.zzasd.currentTimeMillis());
            }
            zzazx();
        }
        zzawg().setTransactionSuccessful();
        zzawg().endTransaction();
        this.zzjdi = false;
        zzbab();
    }

    public final void zzbn(boolean z) {
        zzazx();
    }

    @WorkerThread
    final void zzc(zzcfi com_google_android_gms_internal_zzcfi, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        zzbq.zzgh(com_google_android_gms_internal_zzcfi.packageName);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi.zzivl);
        zzbq.zzgh(com_google_android_gms_internal_zzcfi.zzivl.name);
        zzawl().zzut();
        zzwu();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux)) {
            if (com_google_android_gms_internal_zzcff.zzivc) {
                zzawg().beginTransaction();
                try {
                    zzg(com_google_android_gms_internal_zzcff);
                    zzcfi zzah = zzawg().zzah(com_google_android_gms_internal_zzcfi.packageName, com_google_android_gms_internal_zzcfi.zzivl.name);
                    if (zzah != null) {
                        zzawm().zzayw().zze("Removing conditional user property", com_google_android_gms_internal_zzcfi.packageName, zzawh().zzjd(com_google_android_gms_internal_zzcfi.zzivl.name));
                        zzawg().zzai(com_google_android_gms_internal_zzcfi.packageName, com_google_android_gms_internal_zzcfi.zzivl.name);
                        if (zzah.zzivn) {
                            zzawg().zzaf(com_google_android_gms_internal_zzcfi.packageName, com_google_android_gms_internal_zzcfi.zzivl.name);
                        }
                        if (com_google_android_gms_internal_zzcfi.zzivt != null) {
                            Bundle bundle = null;
                            if (com_google_android_gms_internal_zzcfi.zzivt.zziwy != null) {
                                bundle = com_google_android_gms_internal_zzcfi.zzivt.zziwy.zzayl();
                            }
                            zzc(zzawi().zza(com_google_android_gms_internal_zzcfi.zzivt.name, bundle, zzah.zzivk, com_google_android_gms_internal_zzcfi.zzivt.zziwz, true, false), com_google_android_gms_internal_zzcff);
                        }
                    } else {
                        zzawm().zzayt().zze("Conditional user property doesn't exist", zzcgj.zzje(com_google_android_gms_internal_zzcfi.packageName), zzawh().zzjd(com_google_android_gms_internal_zzcfi.zzivl.name));
                    }
                    zzawg().setTransactionSuccessful();
                } finally {
                    zzawg().endTransaction();
                }
            } else {
                zzg(com_google_android_gms_internal_zzcff);
            }
        }
    }

    @WorkerThread
    final void zzc(zzckk com_google_android_gms_internal_zzckk, zzcff com_google_android_gms_internal_zzcff) {
        zzawl().zzut();
        zzwu();
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux)) {
            if (com_google_android_gms_internal_zzcff.zzivc) {
                zzawm().zzayw().zzj("Removing user property", zzawh().zzjd(com_google_android_gms_internal_zzckk.name));
                zzawg().beginTransaction();
                try {
                    zzg(com_google_android_gms_internal_zzcff);
                    zzawg().zzaf(com_google_android_gms_internal_zzcff.packageName, com_google_android_gms_internal_zzckk.name);
                    zzawg().setTransactionSuccessful();
                    zzawm().zzayw().zzj("User property removed", zzawh().zzjd(com_google_android_gms_internal_zzckk.name));
                } finally {
                    zzawg().endTransaction();
                }
            } else {
                zzg(com_google_android_gms_internal_zzcff);
            }
        }
    }

    final void zzd(zzcff com_google_android_gms_internal_zzcff) {
        zzawg().zziv(com_google_android_gms_internal_zzcff.packageName);
        zzcih zzawg = zzawg();
        String str = com_google_android_gms_internal_zzcff.packageName;
        zzbq.zzgh(str);
        zzawg.zzut();
        zzawg.zzwu();
        try {
            SQLiteDatabase writableDatabase = zzawg.getWritableDatabase();
            String[] strArr = new String[]{str};
            int delete = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + (((((((writableDatabase.delete("apps", "app_id=?", strArr) + 0) + writableDatabase.delete("events", "app_id=?", strArr)) + writableDatabase.delete("user_attributes", "app_id=?", strArr)) + writableDatabase.delete("conditional_properties", "app_id=?", strArr)) + writableDatabase.delete("raw_events", "app_id=?", strArr)) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr)) + writableDatabase.delete("queue", "app_id=?", strArr));
            if (delete > 0) {
                zzawg.zzawm().zzayx().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzawg.zzawm().zzayr().zze("Error resetting analytics data. appId, error", zzcgj.zzje(str), e);
        }
        zzf(zza(this.mContext, com_google_android_gms_internal_zzcff.packageName, com_google_android_gms_internal_zzcff.zziux, com_google_android_gms_internal_zzcff.zzivc, com_google_android_gms_internal_zzcff.zzivj));
    }

    @WorkerThread
    final void zzd(zzcfi com_google_android_gms_internal_zzcfi) {
        zzcff zzjq = zzjq(com_google_android_gms_internal_zzcfi.packageName);
        if (zzjq != null) {
            zzb(com_google_android_gms_internal_zzcfi, zzjq);
        }
    }

    final void zze(zzcff com_google_android_gms_internal_zzcff) {
        zzawl().zzut();
        zzwu();
        zzbq.zzgh(com_google_android_gms_internal_zzcff.packageName);
        zzg(com_google_android_gms_internal_zzcff);
    }

    @WorkerThread
    final void zze(zzcfi com_google_android_gms_internal_zzcfi) {
        zzcff zzjq = zzjq(com_google_android_gms_internal_zzcfi.packageName);
        if (zzjq != null) {
            zzc(com_google_android_gms_internal_zzcfi, zzjq);
        }
    }

    @WorkerThread
    public final void zzf(zzcff com_google_android_gms_internal_zzcff) {
        zzawl().zzut();
        zzwu();
        zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
        zzbq.zzgh(com_google_android_gms_internal_zzcff.packageName);
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux)) {
            zzcfe zziv = zzawg().zziv(com_google_android_gms_internal_zzcff.packageName);
            if (!(zziv == null || !TextUtils.isEmpty(zziv.getGmpAppId()) || TextUtils.isEmpty(com_google_android_gms_internal_zzcff.zziux))) {
                zziv.zzaq(0);
                zzawg().zza(zziv);
                zzawj().zzjp(com_google_android_gms_internal_zzcff.packageName);
            }
            if (com_google_android_gms_internal_zzcff.zzivc) {
                int i;
                Bundle bundle;
                long j = com_google_android_gms_internal_zzcff.zzivh;
                if (j == 0) {
                    j = this.zzasd.currentTimeMillis();
                }
                int i2 = com_google_android_gms_internal_zzcff.zzivi;
                if (i2 == 0 || i2 == 1) {
                    i = i2;
                } else {
                    zzawm().zzayt().zze("Incorrect app type, assuming installed app. appId, appType", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), Integer.valueOf(i2));
                    i = 0;
                }
                zzawg().beginTransaction();
                zzcih zzawg;
                String appId;
                try {
                    zziv = zzawg().zziv(com_google_android_gms_internal_zzcff.packageName);
                    if (!(zziv == null || zziv.getGmpAppId() == null || zziv.getGmpAppId().equals(com_google_android_gms_internal_zzcff.zziux))) {
                        zzawm().zzayt().zzj("New GMP App Id passed in. Removing cached database data. appId", zzcgj.zzje(zziv.getAppId()));
                        zzawg = zzawg();
                        appId = zziv.getAppId();
                        zzawg.zzwu();
                        zzawg.zzut();
                        zzbq.zzgh(appId);
                        SQLiteDatabase writableDatabase = zzawg.getWritableDatabase();
                        String[] strArr = new String[]{appId};
                        i2 = writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + ((((((((writableDatabase.delete("events", "app_id=?", strArr) + 0) + writableDatabase.delete("user_attributes", "app_id=?", strArr)) + writableDatabase.delete("conditional_properties", "app_id=?", strArr)) + writableDatabase.delete("apps", "app_id=?", strArr)) + writableDatabase.delete("raw_events", "app_id=?", strArr)) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr)) + writableDatabase.delete("event_filters", "app_id=?", strArr)) + writableDatabase.delete("property_filters", "app_id=?", strArr));
                        if (i2 > 0) {
                            zzawg.zzawm().zzayx().zze("Deleted application data. app, records", appId, Integer.valueOf(i2));
                        }
                        zziv = null;
                    }
                } catch (SQLiteException e) {
                    zzawg.zzawm().zzayr().zze("Error deleting application data. appId, error", zzcgj.zzje(appId), e);
                } catch (Throwable th) {
                    zzawg().endTransaction();
                }
                if (zziv != null) {
                    if (!(zziv.zzuy() == null || zziv.zzuy().equals(com_google_android_gms_internal_zzcff.zzicq))) {
                        bundle = new Bundle();
                        bundle.putString("_pv", zziv.zzuy());
                        zzb(new zzcfx("_au", new zzcfu(bundle), ReactScrollViewHelper.AUTO, j), com_google_android_gms_internal_zzcff);
                    }
                }
                zzg(com_google_android_gms_internal_zzcff);
                zzcft com_google_android_gms_internal_zzcft = null;
                if (i == 0) {
                    com_google_android_gms_internal_zzcft = zzawg().zzae(com_google_android_gms_internal_zzcff.packageName, "_f");
                } else if (i == 1) {
                    com_google_android_gms_internal_zzcft = zzawg().zzae(com_google_android_gms_internal_zzcff.packageName, "_v");
                }
                if (com_google_android_gms_internal_zzcft == null) {
                    long j2 = (1 + (j / 3600000)) * 3600000;
                    if (i == 0) {
                        zzb(new zzckk("_fot", j, Long.valueOf(j2), ReactScrollViewHelper.AUTO), com_google_android_gms_internal_zzcff);
                        zzawl().zzut();
                        zzwu();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1);
                        bundle2.putLong("_r", 1);
                        bundle2.putLong("_uwa", 0);
                        bundle2.putLong("_pfo", 0);
                        bundle2.putLong("_sys", 0);
                        bundle2.putLong("_sysu", 0);
                        if (this.mContext.getPackageManager() == null) {
                            zzawm().zzayr().zzj("PackageManager is null, first open report might be inaccurate. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName));
                        } else {
                            ApplicationInfo applicationInfo;
                            PackageInfo packageInfo = null;
                            try {
                                packageInfo = zzbgc.zzcy(this.mContext).getPackageInfo(com_google_android_gms_internal_zzcff.packageName, 0);
                            } catch (NameNotFoundException e2) {
                                zzawm().zzayr().zze("Package info is null, first open report might be inaccurate. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), e2);
                            }
                            if (packageInfo != null) {
                                if (packageInfo.firstInstallTime != 0) {
                                    Object obj = null;
                                    if (packageInfo.firstInstallTime != packageInfo.lastUpdateTime) {
                                        bundle2.putLong("_uwa", 1);
                                    } else {
                                        obj = 1;
                                    }
                                    zzb(new zzckk("_fi", j, Long.valueOf(obj != null ? 1 : 0), ReactScrollViewHelper.AUTO), com_google_android_gms_internal_zzcff);
                                }
                            }
                            try {
                                applicationInfo = zzbgc.zzcy(this.mContext).getApplicationInfo(com_google_android_gms_internal_zzcff.packageName, 0);
                            } catch (NameNotFoundException e22) {
                                zzawm().zzayr().zze("Application info is null, first open report might be inaccurate. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), e22);
                                applicationInfo = null;
                            }
                            if (applicationInfo != null) {
                                if ((applicationInfo.flags & 1) != 0) {
                                    bundle2.putLong("_sys", 1);
                                }
                                if ((applicationInfo.flags & 128) != 0) {
                                    bundle2.putLong("_sysu", 1);
                                }
                            }
                        }
                        zzcih zzawg2 = zzawg();
                        String str = com_google_android_gms_internal_zzcff.packageName;
                        zzbq.zzgh(str);
                        zzawg2.zzut();
                        zzawg2.zzwu();
                        j2 = zzawg2.zzal(str, "first_open_count");
                        if (j2 >= 0) {
                            bundle2.putLong("_pfo", j2);
                        }
                        zzb(new zzcfx("_f", new zzcfu(bundle2), ReactScrollViewHelper.AUTO, j), com_google_android_gms_internal_zzcff);
                    } else if (i == 1) {
                        zzb(new zzckk("_fvt", j, Long.valueOf(j2), ReactScrollViewHelper.AUTO), com_google_android_gms_internal_zzcff);
                        zzawl().zzut();
                        zzwu();
                        bundle = new Bundle();
                        bundle.putLong("_c", 1);
                        bundle.putLong("_r", 1);
                        zzb(new zzcfx("_v", new zzcfu(bundle), ReactScrollViewHelper.AUTO, j), com_google_android_gms_internal_zzcff);
                    }
                    bundle = new Bundle();
                    bundle.putLong("_et", 1);
                    zzb(new zzcfx("_e", new zzcfu(bundle), ReactScrollViewHelper.AUTO, j), com_google_android_gms_internal_zzcff);
                } else if (com_google_android_gms_internal_zzcff.zzivd) {
                    zzb(new zzcfx("_cd", new zzcfu(new Bundle()), ReactScrollViewHelper.AUTO, j), com_google_android_gms_internal_zzcff);
                }
                zzawg().setTransactionSuccessful();
                zzawg().endTransaction();
                return;
            }
            zzg(com_google_android_gms_internal_zzcff);
        }
    }

    @WorkerThread
    final void zzi(Runnable runnable) {
        zzawl().zzut();
        if (this.zzjdd == null) {
            this.zzjdd = new ArrayList();
        }
        this.zzjdd.add(runnable);
    }

    public final String zzjr(String str) {
        Object e;
        try {
            return (String) zzawl().zzc(new zzchl(this, str)).get(JobRequest.DEFAULT_BACKOFF_MS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e2) {
            e = e2;
        } catch (InterruptedException e3) {
            e = e3;
        } catch (ExecutionException e4) {
            e = e4;
        }
        zzawm().zzayr().zze("Failed to get app instance id. appId", zzcgj.zzje(str), e);
        return null;
    }

    public final zzd zzwh() {
        return this.zzasd;
    }

    final void zzwu() {
        if (!this.zzdqd) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }
}
