package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.stats.zza;

public final class zzcjr implements ServiceConnection, zzf, zzg {
    final /* synthetic */ zzcjd zzjfo;
    private volatile boolean zzjfv;
    private volatile zzcgi zzjfw;

    protected zzcjr(zzcjd com_google_android_gms_internal_zzcjd) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
    }

    @MainThread
    public final void onConnected(@Nullable Bundle bundle) {
        zzbq.zzfz("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                zzcgb com_google_android_gms_internal_zzcgb = (zzcgb) this.zzjfw.zzakb();
                this.zzjfw = null;
                this.zzjfo.zzawl().zzg(new zzcju(this, com_google_android_gms_internal_zzcgb));
            } catch (DeadObjectException e) {
                this.zzjfw = null;
                this.zzjfv = false;
            } catch (IllegalStateException e2) {
                this.zzjfw = null;
                this.zzjfv = false;
            }
        }
    }

    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzbq.zzfz("MeasurementServiceConnection.onConnectionFailed");
        zzcgj zzazl = this.zzjfo.zzitk.zzazl();
        if (zzazl != null) {
            zzazl.zzayt().zzj("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzjfv = false;
            this.zzjfw = null;
        }
        this.zzjfo.zzawl().zzg(new zzcjw(this));
    }

    @MainThread
    public final void onConnectionSuspended(int i) {
        zzbq.zzfz("MeasurementServiceConnection.onConnectionSuspended");
        this.zzjfo.zzawm().zzayw().log("Service connection suspended");
        this.zzjfo.zzawl().zzg(new zzcjv(this));
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzcgb com_google_android_gms_internal_zzcgb;
        zzbq.zzfz("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zzjfv = false;
                this.zzjfo.zzawm().zzayr().log("Service connected with null binder");
                return;
            }
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder == null) {
                        com_google_android_gms_internal_zzcgb = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        com_google_android_gms_internal_zzcgb = queryLocalInterface instanceof zzcgb ? (zzcgb) queryLocalInterface : new zzcgd(iBinder);
                    }
                    try {
                        this.zzjfo.zzawm().zzayx().log("Bound to IMeasurementService interface");
                    } catch (RemoteException e) {
                        this.zzjfo.zzawm().zzayr().log("Service connect failed to get IMeasurementService");
                        if (com_google_android_gms_internal_zzcgb != null) {
                            this.zzjfv = false;
                            try {
                                zza.zzalq();
                                this.zzjfo.getContext().unbindService(this.zzjfo.zzjfh);
                            } catch (IllegalArgumentException e2) {
                            }
                        } else {
                            this.zzjfo.zzawl().zzg(new zzcjs(this, com_google_android_gms_internal_zzcgb));
                        }
                    }
                    if (com_google_android_gms_internal_zzcgb != null) {
                        this.zzjfv = false;
                        zza.zzalq();
                        this.zzjfo.getContext().unbindService(this.zzjfo.zzjfh);
                    } else {
                        this.zzjfo.zzawl().zzg(new zzcjs(this, com_google_android_gms_internal_zzcgb));
                    }
                }
                this.zzjfo.zzawm().zzayr().zzj("Got binder with a wrong descriptor", interfaceDescriptor);
                com_google_android_gms_internal_zzcgb = null;
                if (com_google_android_gms_internal_zzcgb != null) {
                    this.zzjfo.zzawl().zzg(new zzcjs(this, com_google_android_gms_internal_zzcgb));
                } else {
                    this.zzjfv = false;
                    zza.zzalq();
                    this.zzjfo.getContext().unbindService(this.zzjfo.zzjfh);
                }
            } catch (RemoteException e3) {
                com_google_android_gms_internal_zzcgb = null;
                this.zzjfo.zzawm().zzayr().log("Service connect failed to get IMeasurementService");
                if (com_google_android_gms_internal_zzcgb != null) {
                    this.zzjfo.zzawl().zzg(new zzcjs(this, com_google_android_gms_internal_zzcgb));
                } else {
                    this.zzjfv = false;
                    zza.zzalq();
                    this.zzjfo.getContext().unbindService(this.zzjfo.zzjfh);
                }
            }
        }
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        zzbq.zzfz("MeasurementServiceConnection.onServiceDisconnected");
        this.zzjfo.zzawm().zzayw().log("Service disconnected");
        this.zzjfo.zzawl().zzg(new zzcjt(this, componentName));
    }

    @WorkerThread
    public final void zzbai() {
        this.zzjfo.zzut();
        Context context = this.zzjfo.getContext();
        synchronized (this) {
            if (this.zzjfv) {
                this.zzjfo.zzawm().zzayx().log("Connection attempt already in progress");
            } else if (this.zzjfw != null) {
                this.zzjfo.zzawm().zzayx().log("Already awaiting connection attempt");
            } else {
                this.zzjfw = new zzcgi(context, Looper.getMainLooper(), this, this);
                this.zzjfo.zzawm().zzayx().log("Connecting to remote service");
                this.zzjfv = true;
                this.zzjfw.zzajx();
            }
        }
    }

    @WorkerThread
    public final void zzn(Intent intent) {
        this.zzjfo.zzut();
        Context context = this.zzjfo.getContext();
        zza zzalq = zza.zzalq();
        synchronized (this) {
            if (this.zzjfv) {
                this.zzjfo.zzawm().zzayx().log("Connection attempt already in progress");
                return;
            }
            this.zzjfo.zzawm().zzayx().log("Using local app measurement service");
            this.zzjfv = true;
            zzalq.zza(context, intent, this.zzjfo.zzjfh, 129);
        }
    }
}
