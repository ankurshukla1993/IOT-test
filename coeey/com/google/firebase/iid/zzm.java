package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zza;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

final class zzm implements ServiceConnection {
    int state;
    final Messenger zznuh;
    zzr zznui;
    final Queue<zzt<?>> zznuj;
    final SparseArray<zzt<?>> zznuk;
    final /* synthetic */ zzk zznul;

    private zzm(zzk com_google_firebase_iid_zzk) {
        this.zznul = com_google_firebase_iid_zzk;
        this.state = 0;
        this.zznuh = new Messenger(new Handler(Looper.getMainLooper(), new zzn(this)));
        this.zznuj = new LinkedList();
        this.zznuk = new SparseArray();
    }

    private final void zza(zzu com_google_firebase_iid_zzu) {
        for (zzt zzb : this.zznuj) {
            zzb.zzb(com_google_firebase_iid_zzu);
        }
        this.zznuj.clear();
        for (int i = 0; i < this.zznuk.size(); i++) {
            ((zzt) this.zznuk.valueAt(i)).zzb(com_google_firebase_iid_zzu);
        }
        this.zznuk.clear();
    }

    private final void zzchg() {
        this.zznul.zznue.execute(new zzp(this));
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zzm(0, "Null service connection");
        } else {
            try {
                this.zznui = new zzr(iBinder);
                this.state = 2;
                zzchg();
            } catch (RemoteException e) {
                zzm(0, e.getMessage());
            }
        }
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzm(2, "Service disconnected");
    }

    final synchronized boolean zzb(zzt com_google_firebase_iid_zzt) {
        boolean z = false;
        boolean z2 = true;
        synchronized (this) {
            switch (this.state) {
                case 0:
                    this.zznuj.add(com_google_firebase_iid_zzt);
                    if (this.state == 0) {
                        z = true;
                    }
                    zzbq.checkState(z);
                    if (Log.isLoggable("MessengerIpcClient", 2)) {
                        Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                    }
                    this.state = 1;
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gms");
                    if (!zza.zzalq().zza(this.zznul.zzaif, intent, this, 1)) {
                        zzm(0, "Unable to bind to service");
                        break;
                    }
                    this.zznul.zznue.schedule(new zzo(this), 30, TimeUnit.SECONDS);
                    break;
                case 1:
                    this.zznuj.add(com_google_firebase_iid_zzt);
                    break;
                case 2:
                    this.zznuj.add(com_google_firebase_iid_zzt);
                    zzchg();
                    break;
                case 3:
                case 4:
                    z2 = false;
                    break;
                default:
                    throw new IllegalStateException("Unknown state: " + this.state);
            }
        }
        return z2;
    }

    final synchronized void zzchh() {
        if (this.state == 2 && this.zznuj.isEmpty() && this.zznuk.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            zza.zzalq();
            this.zznul.zzaif.unbindService(this);
        }
    }

    final synchronized void zzchi() {
        if (this.state == 1) {
            zzm(1, "Timed out while binding");
        }
    }

    final boolean zzd(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Received response to request: " + i);
        }
        synchronized (this) {
            zzt com_google_firebase_iid_zzt = (zzt) this.zznuk.get(i);
            if (com_google_firebase_iid_zzt == null) {
                Log.w("MessengerIpcClient", "Received response for unknown request: " + i);
            } else {
                this.zznuk.remove(i);
                zzchh();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    com_google_firebase_iid_zzt.zzb(new zzu(4, "Not supported by GmsCore"));
                } else {
                    com_google_firebase_iid_zzt.zzad(data);
                }
            }
        }
        return true;
    }

    final synchronized void zzht(int i) {
        zzt com_google_firebase_iid_zzt = (zzt) this.zznuk.get(i);
        if (com_google_firebase_iid_zzt != null) {
            Log.w("MessengerIpcClient", "Timing out request: " + i);
            this.zznuk.remove(i);
            com_google_firebase_iid_zzt.zzb(new zzu(3, "Timed out waiting for response"));
            zzchh();
        }
    }

    final synchronized void zzm(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String str2 = "MessengerIpcClient";
            String str3 = "Disconnected: ";
            String valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                zza.zzalq();
                this.zznul.zzaif.unbindService(this);
                zza(new zzu(i, str));
                break;
            case 3:
                this.state = 4;
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException("Unknown state: " + this.state);
        }
    }
}
