package com.google.android.gms.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.zzp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class zzcho extends zzcgc {
    private final zzchj zzitk;
    private Boolean zzjdq;
    @Nullable
    private String zzjdr;

    public zzcho(zzchj com_google_android_gms_internal_zzchj) {
        this(com_google_android_gms_internal_zzchj, null);
    }

    private zzcho(zzchj com_google_android_gms_internal_zzchj, @Nullable String str) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzchj);
        this.zzitk = com_google_android_gms_internal_zzchj;
        this.zzjdr = null;
    }

    @BinderThread
    private final void zzb(zzcff com_google_android_gms_internal_zzcff, boolean z) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcff);
        zzf(com_google_android_gms_internal_zzcff.packageName, false);
        this.zzitk.zzawi().zzka(com_google_android_gms_internal_zzcff.zziux);
    }

    @BinderThread
    private final void zzf(String str, boolean z) {
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            this.zzitk.zzawm().zzayr().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.zzjdq == null) {
                    if ("com.google.android.gms".equals(this.zzjdr) || zzx.zzf(this.zzitk.getContext(), Binder.getCallingUid()) || zzp.zzcg(this.zzitk.getContext()).zzbq(Binder.getCallingUid())) {
                        z2 = true;
                    }
                    this.zzjdq = Boolean.valueOf(z2);
                }
                if (this.zzjdq.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.zzitk.zzawm().zzayr().zzj("Measurement Service called with invalid calling package. appId", zzcgj.zzje(str));
                throw e;
            }
        }
        if (this.zzjdr == null && zzo.zzb(this.zzitk.getContext(), Binder.getCallingUid(), str)) {
            this.zzjdr = str;
        }
        if (!str.equals(this.zzjdr)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }

    @BinderThread
    public final List<zzckk> zza(zzcff com_google_android_gms_internal_zzcff, boolean z) {
        Object e;
        zzb(com_google_android_gms_internal_zzcff, false);
        try {
            List<zzckm> list = (List) this.zzitk.zzawl().zzc(new zzcie(this, com_google_android_gms_internal_zzcff)).get();
            List<zzckk> arrayList = new ArrayList(list.size());
            for (zzckm com_google_android_gms_internal_zzckm : list) {
                if (z || !zzckn.zzkc(com_google_android_gms_internal_zzckm.mName)) {
                    arrayList.add(new zzckk(com_google_android_gms_internal_zzckm));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zzitk.zzawm().zzayr().zze("Failed to get user attributes. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.zzitk.zzawm().zzayr().zze("Failed to get user attributes. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), e);
            return null;
        }
    }

    @BinderThread
    public final List<zzcfi> zza(String str, String str2, zzcff com_google_android_gms_internal_zzcff) {
        Object e;
        zzb(com_google_android_gms_internal_zzcff, false);
        try {
            return (List) this.zzitk.zzawl().zzc(new zzchw(this, com_google_android_gms_internal_zzcff, str, str2)).get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        this.zzitk.zzawm().zzayr().zzj("Failed to get conditional user properties", e);
        return Collections.emptyList();
    }

    @BinderThread
    public final List<zzckk> zza(String str, String str2, String str3, boolean z) {
        Object e;
        zzf(str, true);
        try {
            List<zzckm> list = (List) this.zzitk.zzawl().zzc(new zzchv(this, str, str2, str3)).get();
            List<zzckk> arrayList = new ArrayList(list.size());
            for (zzckm com_google_android_gms_internal_zzckm : list) {
                if (z || !zzckn.zzkc(com_google_android_gms_internal_zzckm.mName)) {
                    arrayList.add(new zzckk(com_google_android_gms_internal_zzckm));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zzitk.zzawm().zzayr().zze("Failed to get user attributes. appId", zzcgj.zzje(str), e);
            return Collections.emptyList();
        } catch (ExecutionException e3) {
            e = e3;
            this.zzitk.zzawm().zzayr().zze("Failed to get user attributes. appId", zzcgj.zzje(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzckk> zza(String str, String str2, boolean z, zzcff com_google_android_gms_internal_zzcff) {
        Object e;
        zzb(com_google_android_gms_internal_zzcff, false);
        try {
            List<zzckm> list = (List) this.zzitk.zzawl().zzc(new zzchu(this, com_google_android_gms_internal_zzcff, str, str2)).get();
            List<zzckk> arrayList = new ArrayList(list.size());
            for (zzckm com_google_android_gms_internal_zzckm : list) {
                if (z || !zzckn.zzkc(com_google_android_gms_internal_zzckm.mName)) {
                    arrayList.add(new zzckk(com_google_android_gms_internal_zzckm));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zzitk.zzawm().zzayr().zze("Failed to get user attributes. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), e);
            return Collections.emptyList();
        } catch (ExecutionException e3) {
            e = e3;
            this.zzitk.zzawm().zzayr().zze("Failed to get user attributes. appId", zzcgj.zzje(com_google_android_gms_internal_zzcff.packageName), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zza(long j, String str, String str2, String str3) {
        this.zzitk.zzawl().zzg(new zzcig(this, str2, str3, str, j));
    }

    @BinderThread
    public final void zza(zzcff com_google_android_gms_internal_zzcff) {
        zzb(com_google_android_gms_internal_zzcff, false);
        Runnable com_google_android_gms_internal_zzcif = new zzcif(this, com_google_android_gms_internal_zzcff);
        if (this.zzitk.zzawl().zzazg()) {
            com_google_android_gms_internal_zzcif.run();
        } else {
            this.zzitk.zzawl().zzg(com_google_android_gms_internal_zzcif);
        }
    }

    @BinderThread
    public final void zza(zzcfi com_google_android_gms_internal_zzcfi, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi.zzivl);
        zzb(com_google_android_gms_internal_zzcff, false);
        zzcfi com_google_android_gms_internal_zzcfi2 = new zzcfi(com_google_android_gms_internal_zzcfi);
        com_google_android_gms_internal_zzcfi2.packageName = com_google_android_gms_internal_zzcff.packageName;
        if (com_google_android_gms_internal_zzcfi.zzivl.getValue() == null) {
            this.zzitk.zzawl().zzg(new zzchq(this, com_google_android_gms_internal_zzcfi2, com_google_android_gms_internal_zzcff));
        } else {
            this.zzitk.zzawl().zzg(new zzchr(this, com_google_android_gms_internal_zzcfi2, com_google_android_gms_internal_zzcff));
        }
    }

    @BinderThread
    public final void zza(zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        zzb(com_google_android_gms_internal_zzcff, false);
        this.zzitk.zzawl().zzg(new zzchz(this, com_google_android_gms_internal_zzcfx, com_google_android_gms_internal_zzcff));
    }

    @BinderThread
    public final void zza(zzcfx com_google_android_gms_internal_zzcfx, String str, String str2) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        zzbq.zzgh(str);
        zzf(str, true);
        this.zzitk.zzawl().zzg(new zzcia(this, com_google_android_gms_internal_zzcfx, str));
    }

    @BinderThread
    public final void zza(zzckk com_google_android_gms_internal_zzckk, zzcff com_google_android_gms_internal_zzcff) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzckk);
        zzb(com_google_android_gms_internal_zzcff, false);
        if (com_google_android_gms_internal_zzckk.getValue() == null) {
            this.zzitk.zzawl().zzg(new zzcic(this, com_google_android_gms_internal_zzckk, com_google_android_gms_internal_zzcff));
        } else {
            this.zzitk.zzawl().zzg(new zzcid(this, com_google_android_gms_internal_zzckk, com_google_android_gms_internal_zzcff));
        }
    }

    @BinderThread
    public final byte[] zza(zzcfx com_google_android_gms_internal_zzcfx, String str) {
        Object e;
        zzbq.zzgh(str);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        zzf(str, true);
        this.zzitk.zzawm().zzayw().zzj("Log and bundle. event", this.zzitk.zzawh().zzjb(com_google_android_gms_internal_zzcfx.name));
        long nanoTime = this.zzitk.zzwh().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzitk.zzawl().zzd(new zzcib(this, com_google_android_gms_internal_zzcfx, str)).get();
            if (bArr == null) {
                this.zzitk.zzawm().zzayr().zzj("Log and bundle returned null. appId", zzcgj.zzje(str));
                bArr = new byte[0];
            }
            this.zzitk.zzawm().zzayw().zzd("Log and bundle processed. event, size, time_ms", this.zzitk.zzawh().zzjb(com_google_android_gms_internal_zzcfx.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzitk.zzwh().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException e2) {
            e = e2;
            this.zzitk.zzawm().zzayr().zzd("Failed to log and bundle. appId, event, error", zzcgj.zzje(str), this.zzitk.zzawh().zzjb(com_google_android_gms_internal_zzcfx.name), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.zzitk.zzawm().zzayr().zzd("Failed to log and bundle. appId, event, error", zzcgj.zzje(str), this.zzitk.zzawh().zzjb(com_google_android_gms_internal_zzcfx.name), e);
            return null;
        }
    }

    @BinderThread
    public final void zzb(zzcff com_google_android_gms_internal_zzcff) {
        zzb(com_google_android_gms_internal_zzcff, false);
        this.zzitk.zzawl().zzg(new zzchp(this, com_google_android_gms_internal_zzcff));
    }

    @BinderThread
    public final void zzb(zzcfi com_google_android_gms_internal_zzcfi) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi.zzivl);
        zzf(com_google_android_gms_internal_zzcfi.packageName, true);
        zzcfi com_google_android_gms_internal_zzcfi2 = new zzcfi(com_google_android_gms_internal_zzcfi);
        if (com_google_android_gms_internal_zzcfi.zzivl.getValue() == null) {
            this.zzitk.zzawl().zzg(new zzchs(this, com_google_android_gms_internal_zzcfi2));
        } else {
            this.zzitk.zzawl().zzg(new zzcht(this, com_google_android_gms_internal_zzcfi2));
        }
    }

    @BinderThread
    public final String zzc(zzcff com_google_android_gms_internal_zzcff) {
        zzb(com_google_android_gms_internal_zzcff, false);
        return this.zzitk.zzjr(com_google_android_gms_internal_zzcff.packageName);
    }

    @BinderThread
    public final void zzd(zzcff com_google_android_gms_internal_zzcff) {
        zzf(com_google_android_gms_internal_zzcff.packageName, false);
        this.zzitk.zzawl().zzg(new zzchy(this, com_google_android_gms_internal_zzcff));
    }

    @BinderThread
    public final List<zzcfi> zzj(String str, String str2, String str3) {
        Object e;
        zzf(str, true);
        try {
            return (List) this.zzitk.zzawl().zzc(new zzchx(this, str, str2, str3)).get();
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        this.zzitk.zzawm().zzayr().zzj("Failed to get conditional user properties", e);
        return Collections.emptyList();
    }
}
