package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashSet;
import java.util.Set;

final class zzaj implements ServiceConnection {
    private ComponentName mComponentName;
    private int mState = 2;
    private IBinder zzfwl;
    private final Set<ServiceConnection> zzfxw = new HashSet();
    private boolean zzfxx;
    private final zzah zzfxy;
    private /* synthetic */ zzai zzfxz;

    public zzaj(zzai com_google_android_gms_common_internal_zzai, zzah com_google_android_gms_common_internal_zzah) {
        this.zzfxz = com_google_android_gms_common_internal_zzai;
        this.zzfxy = com_google_android_gms_common_internal_zzah;
    }

    public final IBinder getBinder() {
        return this.zzfwl;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean isBound() {
        return this.zzfxx;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzfxz.zzfxs) {
            this.zzfxz.mHandler.removeMessages(1, this.zzfxy);
            this.zzfwl = iBinder;
            this.mComponentName = componentName;
            for (ServiceConnection onServiceConnected : this.zzfxw) {
                onServiceConnected.onServiceConnected(componentName, iBinder);
            }
            this.mState = 1;
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzfxz.zzfxs) {
            this.zzfxz.mHandler.removeMessages(1, this.zzfxy);
            this.zzfwl = null;
            this.mComponentName = componentName;
            for (ServiceConnection onServiceDisconnected : this.zzfxw) {
                onServiceDisconnected.onServiceDisconnected(componentName);
            }
            this.mState = 2;
        }
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        this.zzfxz.zzfxt;
        this.zzfxz.mApplicationContext;
        this.zzfxy.zzakz();
        this.zzfxw.add(serviceConnection);
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zzfxw.contains(serviceConnection);
    }

    public final boolean zzala() {
        return this.zzfxw.isEmpty();
    }

    public final void zzb(ServiceConnection serviceConnection, String str) {
        this.zzfxz.zzfxt;
        this.zzfxz.mApplicationContext;
        this.zzfxw.remove(serviceConnection);
    }

    public final void zzgd(String str) {
        this.mState = 3;
        this.zzfxx = this.zzfxz.zzfxt.zza(this.zzfxz.mApplicationContext, str, this.zzfxy.zzakz(), this, this.zzfxy.zzaky());
        if (this.zzfxx) {
            this.zzfxz.mHandler.sendMessageDelayed(this.zzfxz.mHandler.obtainMessage(1, this.zzfxy), this.zzfxz.zzfxv);
            return;
        }
        this.mState = 2;
        try {
            this.zzfxz.zzfxt;
            this.zzfxz.mApplicationContext.unbindService(this);
        } catch (IllegalArgumentException e) {
        }
    }

    public final void zzge(String str) {
        this.zzfxz.mHandler.removeMessages(1, this.zzfxy);
        this.zzfxz.zzfxt;
        this.zzfxz.mApplicationContext.unbindService(this);
        this.zzfxx = false;
        this.mState = 2;
    }
}
