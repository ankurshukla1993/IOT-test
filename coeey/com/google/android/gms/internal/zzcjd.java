package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.zze;
import com.google.android.gms.measurement.AppMeasurement$zzb;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcjd extends zzcii {
    private final zzcjr zzjfh;
    private zzcgb zzjfi;
    private volatile Boolean zzjfj;
    private final zzcfp zzjfk;
    private final zzckh zzjfl;
    private final List<Runnable> zzjfm = new ArrayList();
    private final zzcfp zzjfn;

    protected zzcjd(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
        this.zzjfl = new zzckh(com_google_android_gms_internal_zzchj.zzwh());
        this.zzjfh = new zzcjr(this);
        this.zzjfk = new zzcje(this, com_google_android_gms_internal_zzchj);
        this.zzjfn = new zzcjj(this, com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    private final void onServiceDisconnected(ComponentName componentName) {
        zzut();
        if (this.zzjfi != null) {
            this.zzjfi = null;
            zzawm().zzayx().zzj("Disconnected from device MeasurementService", componentName);
            zzut();
            zzxr();
        }
    }

    @WorkerThread
    private final void zzbah() {
        zzut();
        zzawm().zzayx().zzj("Processing queued up service tasks", Integer.valueOf(this.zzjfm.size()));
        for (Runnable run : this.zzjfm) {
            try {
                run.run();
            } catch (Throwable th) {
                zzawm().zzayr().zzj("Task exception while flushing queue", th);
            }
        }
        this.zzjfm.clear();
        this.zzjfn.cancel();
    }

    @Nullable
    @WorkerThread
    private final zzcff zzbq(boolean z) {
        return zzawb().zzja(z ? zzawm().zzayy() : null);
    }

    @WorkerThread
    private final void zzj(Runnable runnable) throws IllegalStateException {
        zzut();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzjfm.size()) >= 1000) {
            zzawm().zzayr().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzjfm.add(runnable);
            this.zzjfn.zzr(60000);
            zzxr();
        }
    }

    @WorkerThread
    private final void zzxg() {
        zzut();
        this.zzjfl.start();
        this.zzjfk.zzr(((Long) zzcfz.zziyo.get()).longValue());
    }

    @WorkerThread
    private final void zzxh() {
        zzut();
        if (isConnected()) {
            zzawm().zzayx().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    public final void disconnect() {
        zzut();
        zzwu();
        try {
            zza.zzalq();
            getContext().unbindService(this.zzjfh);
        } catch (IllegalStateException e) {
        } catch (IllegalArgumentException e2) {
        }
        this.zzjfi = null;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final boolean isConnected() {
        zzut();
        zzwu();
        return this.zzjfi != null;
    }

    @WorkerThread
    protected final void resetAnalyticsData() {
        zzut();
        zzwu();
        zzcff zzbq = zzbq(false);
        zzawf().resetAnalyticsData();
        zzj(new zzcjf(this, zzbq));
    }

    @WorkerThread
    protected final void zza(zzcgb com_google_android_gms_internal_zzcgb) {
        zzut();
        zzbq.checkNotNull(com_google_android_gms_internal_zzcgb);
        this.zzjfi = com_google_android_gms_internal_zzcgb;
        zzxg();
        zzbah();
    }

    @WorkerThread
    final void zza(zzcgb com_google_android_gms_internal_zzcgb, zzbej com_google_android_gms_internal_zzbej, zzcff com_google_android_gms_internal_zzcff) {
        zzut();
        zzwu();
        int i = 100;
        for (int i2 = 0; i2 < 1001 && r4 == 100; i2++) {
            List arrayList = new ArrayList();
            Object zzeb = zzawf().zzeb(100);
            if (zzeb != null) {
                arrayList.addAll(zzeb);
                i = zzeb.size();
            } else {
                i = 0;
            }
            if (com_google_android_gms_internal_zzbej != null && r4 < 100) {
                arrayList.add(com_google_android_gms_internal_zzbej);
            }
            ArrayList arrayList2 = (ArrayList) arrayList;
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                zzeb = arrayList2.get(i3);
                i3++;
                zzbej com_google_android_gms_internal_zzbej2 = (zzbej) zzeb;
                if (com_google_android_gms_internal_zzbej2 instanceof zzcfx) {
                    try {
                        com_google_android_gms_internal_zzcgb.zza((zzcfx) com_google_android_gms_internal_zzbej2, com_google_android_gms_internal_zzcff);
                    } catch (RemoteException e) {
                        zzawm().zzayr().zzj("Failed to send event to the service", e);
                    }
                } else if (com_google_android_gms_internal_zzbej2 instanceof zzckk) {
                    try {
                        com_google_android_gms_internal_zzcgb.zza((zzckk) com_google_android_gms_internal_zzbej2, com_google_android_gms_internal_zzcff);
                    } catch (RemoteException e2) {
                        zzawm().zzayr().zzj("Failed to send attribute to the service", e2);
                    }
                } else if (com_google_android_gms_internal_zzbej2 instanceof zzcfi) {
                    try {
                        com_google_android_gms_internal_zzcgb.zza((zzcfi) com_google_android_gms_internal_zzbej2, com_google_android_gms_internal_zzcff);
                    } catch (RemoteException e22) {
                        zzawm().zzayr().zzj("Failed to send conditional property to the service", e22);
                    }
                } else {
                    zzawm().zzayr().log("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    @WorkerThread
    protected final void zza(AppMeasurement$zzb appMeasurement$zzb) {
        zzut();
        zzwu();
        zzj(new zzcji(this, appMeasurement$zzb));
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzut();
        zzwu();
        zzj(new zzcjg(this, atomicReference, zzbq(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzcfi>> atomicReference, String str, String str2, String str3) {
        zzut();
        zzwu();
        zzj(new zzcjn(this, atomicReference, str, str2, str3, zzbq(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzckk>> atomicReference, String str, String str2, String str3, boolean z) {
        zzut();
        zzwu();
        zzj(new zzcjo(this, atomicReference, str, str2, str3, z, zzbq(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzckk>> atomicReference, boolean z) {
        zzut();
        zzwu();
        zzj(new zzcjq(this, atomicReference, zzbq(false), z));
    }

    public final /* bridge */ /* synthetic */ void zzavw() {
        super.zzavw();
    }

    public final /* bridge */ /* synthetic */ void zzavx() {
        super.zzavx();
    }

    public final /* bridge */ /* synthetic */ zzcfa zzavy() {
        return super.zzavy();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzavz() {
        return super.zzavz();
    }

    public final /* bridge */ /* synthetic */ zzcik zzawa() {
        return super.zzawa();
    }

    public final /* bridge */ /* synthetic */ zzcge zzawb() {
        return super.zzawb();
    }

    public final /* bridge */ /* synthetic */ zzcfr zzawc() {
        return super.zzawc();
    }

    public final /* bridge */ /* synthetic */ zzcjd zzawd() {
        return super.zzawd();
    }

    public final /* bridge */ /* synthetic */ zzciz zzawe() {
        return super.zzawe();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzawf() {
        return super.zzawf();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzawg() {
        return super.zzawg();
    }

    public final /* bridge */ /* synthetic */ zzcgh zzawh() {
        return super.zzawh();
    }

    public final /* bridge */ /* synthetic */ zzckn zzawi() {
        return super.zzawi();
    }

    public final /* bridge */ /* synthetic */ zzchd zzawj() {
        return super.zzawj();
    }

    public final /* bridge */ /* synthetic */ zzckc zzawk() {
        return super.zzawk();
    }

    public final /* bridge */ /* synthetic */ zzche zzawl() {
        return super.zzawl();
    }

    public final /* bridge */ /* synthetic */ zzcgj zzawm() {
        return super.zzawm();
    }

    public final /* bridge */ /* synthetic */ zzcgu zzawn() {
        return super.zzawn();
    }

    public final /* bridge */ /* synthetic */ zzcfk zzawo() {
        return super.zzawo();
    }

    protected final boolean zzaxn() {
        return false;
    }

    @WorkerThread
    protected final void zzb(zzckk com_google_android_gms_internal_zzckk) {
        zzut();
        zzwu();
        zzj(new zzcjp(this, zzawf().zza(com_google_android_gms_internal_zzckk), com_google_android_gms_internal_zzckk, zzbq(true)));
    }

    @WorkerThread
    protected final void zzbae() {
        zzut();
        zzwu();
        zzj(new zzcjk(this, zzbq(true)));
    }

    @WorkerThread
    protected final void zzbaf() {
        zzut();
        zzwu();
        zzj(new zzcjh(this, zzbq(true)));
    }

    final Boolean zzbag() {
        return this.zzjfj;
    }

    @WorkerThread
    protected final void zzc(zzcfx com_google_android_gms_internal_zzcfx, String str) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        zzut();
        zzwu();
        zzj(new zzcjl(this, true, zzawf().zza(com_google_android_gms_internal_zzcfx), com_google_android_gms_internal_zzcfx, zzbq(true), str));
    }

    @WorkerThread
    protected final void zzf(zzcfi com_google_android_gms_internal_zzcfi) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        zzut();
        zzwu();
        zzj(new zzcjm(this, true, zzawf().zzc(com_google_android_gms_internal_zzcfi), new zzcfi(com_google_android_gms_internal_zzcfi), zzbq(true), com_google_android_gms_internal_zzcfi));
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }

    @WorkerThread
    final void zzxr() {
        Object obj = 1;
        zzut();
        zzwu();
        if (!isConnected()) {
            if (this.zzjfj == null) {
                boolean z;
                zzut();
                zzwu();
                Boolean zzazc = zzawn().zzazc();
                if (zzazc == null || !zzazc.booleanValue()) {
                    Object obj2;
                    if (zzawb().zzayp() != 1) {
                        zzawm().zzayx().log("Checking service availability");
                        int isGooglePlayServicesAvailable = zze.zzafm().isGooglePlayServicesAvailable(zzawi().getContext());
                        int i;
                        switch (isGooglePlayServicesAvailable) {
                            case 0:
                                zzawm().zzayx().log("Service available");
                                i = 1;
                                z = true;
                                break;
                            case 1:
                                zzawm().zzayx().log("Service missing");
                                i = 1;
                                z = false;
                                break;
                            case 2:
                                zzawm().zzayw().log("Service container out of date");
                                zzcih zzawi = zzawi();
                                zze.zzafm();
                                if (zze.zzcd(zzawi.getContext()) >= 11400) {
                                    zzazc = zzawn().zzazc();
                                    z = zzazc == null || zzazc.booleanValue();
                                    obj2 = null;
                                    break;
                                }
                                i = 1;
                                z = false;
                                break;
                                break;
                            case 3:
                                zzawm().zzayt().log("Service disabled");
                                obj2 = null;
                                z = false;
                                break;
                            case 9:
                                zzawm().zzayt().log("Service invalid");
                                obj2 = null;
                                z = false;
                                break;
                            case 18:
                                zzawm().zzayt().log("Service updating");
                                i = 1;
                                z = true;
                                break;
                            default:
                                zzawm().zzayt().zzj("Unexpected service status", Integer.valueOf(isGooglePlayServicesAvailable));
                                obj2 = null;
                                z = false;
                                break;
                        }
                    }
                    obj2 = 1;
                    z = true;
                    if (obj2 != null) {
                        zzawn().zzbl(z);
                    }
                } else {
                    z = true;
                }
                this.zzjfj = Boolean.valueOf(z);
            }
            if (this.zzjfj.booleanValue()) {
                this.zzjfh.zzbai();
                return;
            }
            List queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                obj = null;
            }
            if (obj != null) {
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzjfh.zzn(intent);
                return;
            }
            zzawm().zzayr().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
        }
    }
}
