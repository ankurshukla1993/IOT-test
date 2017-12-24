package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;

final class zzai extends zzag implements Callback {
    private final Context mApplicationContext;
    private final Handler mHandler;
    private final HashMap<zzah, zzaj> zzfxs = new HashMap();
    private final zza zzfxt;
    private final long zzfxu;
    private final long zzfxv;

    zzai(Context context) {
        this.mApplicationContext = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzfxt = zza.zzalq();
        this.zzfxu = 5000;
        this.zzfxv = 300000;
    }

    public final boolean handleMessage(Message message) {
        zzah com_google_android_gms_common_internal_zzah;
        zzaj com_google_android_gms_common_internal_zzaj;
        switch (message.what) {
            case 0:
                synchronized (this.zzfxs) {
                    com_google_android_gms_common_internal_zzah = (zzah) message.obj;
                    com_google_android_gms_common_internal_zzaj = (zzaj) this.zzfxs.get(com_google_android_gms_common_internal_zzah);
                    if (com_google_android_gms_common_internal_zzaj != null && com_google_android_gms_common_internal_zzaj.zzala()) {
                        if (com_google_android_gms_common_internal_zzaj.isBound()) {
                            com_google_android_gms_common_internal_zzaj.zzge("GmsClientSupervisor");
                        }
                        this.zzfxs.remove(com_google_android_gms_common_internal_zzah);
                    }
                }
                return true;
            case 1:
                synchronized (this.zzfxs) {
                    com_google_android_gms_common_internal_zzah = (zzah) message.obj;
                    com_google_android_gms_common_internal_zzaj = (zzaj) this.zzfxs.get(com_google_android_gms_common_internal_zzah);
                    if (com_google_android_gms_common_internal_zzaj != null && com_google_android_gms_common_internal_zzaj.getState() == 3) {
                        String valueOf = String.valueOf(com_google_android_gms_common_internal_zzah);
                        Log.wtf("GmsClientSupervisor", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(valueOf).toString(), new Exception());
                        ComponentName componentName = com_google_android_gms_common_internal_zzaj.getComponentName();
                        if (componentName == null) {
                            componentName = com_google_android_gms_common_internal_zzah.getComponentName();
                        }
                        com_google_android_gms_common_internal_zzaj.onServiceDisconnected(componentName == null ? new ComponentName(com_google_android_gms_common_internal_zzah.getPackage(), "unknown") : componentName);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    protected final boolean zza(zzah com_google_android_gms_common_internal_zzah, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzbq.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzfxs) {
            zzaj com_google_android_gms_common_internal_zzaj = (zzaj) this.zzfxs.get(com_google_android_gms_common_internal_zzah);
            if (com_google_android_gms_common_internal_zzaj != null) {
                this.mHandler.removeMessages(0, com_google_android_gms_common_internal_zzah);
                if (!com_google_android_gms_common_internal_zzaj.zza(serviceConnection)) {
                    com_google_android_gms_common_internal_zzaj.zza(serviceConnection, str);
                    switch (com_google_android_gms_common_internal_zzaj.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(com_google_android_gms_common_internal_zzaj.getComponentName(), com_google_android_gms_common_internal_zzaj.getBinder());
                            break;
                        case 2:
                            com_google_android_gms_common_internal_zzaj.zzgd(str);
                            break;
                        default:
                            break;
                    }
                }
                String valueOf = String.valueOf(com_google_android_gms_common_internal_zzah);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
            }
            com_google_android_gms_common_internal_zzaj = new zzaj(this, com_google_android_gms_common_internal_zzah);
            com_google_android_gms_common_internal_zzaj.zza(serviceConnection, str);
            com_google_android_gms_common_internal_zzaj.zzgd(str);
            this.zzfxs.put(com_google_android_gms_common_internal_zzah, com_google_android_gms_common_internal_zzaj);
            isBound = com_google_android_gms_common_internal_zzaj.isBound();
        }
        return isBound;
    }

    protected final void zzb(zzah com_google_android_gms_common_internal_zzah, ServiceConnection serviceConnection, String str) {
        zzbq.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzfxs) {
            zzaj com_google_android_gms_common_internal_zzaj = (zzaj) this.zzfxs.get(com_google_android_gms_common_internal_zzah);
            String valueOf;
            if (com_google_android_gms_common_internal_zzaj == null) {
                valueOf = String.valueOf(com_google_android_gms_common_internal_zzah);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (com_google_android_gms_common_internal_zzaj.zza(serviceConnection)) {
                com_google_android_gms_common_internal_zzaj.zzb(serviceConnection, str);
                if (com_google_android_gms_common_internal_zzaj.zzala()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, com_google_android_gms_common_internal_zzah), this.zzfxu);
                }
            } else {
                valueOf = String.valueOf(com_google_android_gms_common_internal_zzah);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf).toString());
            }
        }
    }
}
