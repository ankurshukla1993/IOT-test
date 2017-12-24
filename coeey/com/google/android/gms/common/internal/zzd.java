package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import humanize.util.Constants;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface> {
    private static String[] zzfwe = new String[]{"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private final Looper zzakm;
    private final zze zzfni;
    private int zzfvj;
    private long zzfvk;
    private long zzfvl;
    private int zzfvm;
    private long zzfvn;
    private zzam zzfvo;
    private final zzag zzfvp;
    private final Object zzfvq;
    private zzay zzfvr;
    protected zzj zzfvs;
    private T zzfvt;
    private final ArrayList<zzi<?>> zzfvu;
    private zzl zzfvv;
    private int zzfvw;
    private final zzf zzfvx;
    private final zzg zzfvy;
    private final int zzfvz;
    private final String zzfwa;
    private ConnectionResult zzfwb;
    private boolean zzfwc;
    protected AtomicInteger zzfwd;

    protected zzd(Context context, Looper looper, int i, zzf com_google_android_gms_common_internal_zzf, zzg com_google_android_gms_common_internal_zzg, String str) {
        this(context, looper, zzag.zzcl(context), zze.zzafm(), i, (zzf) zzbq.checkNotNull(com_google_android_gms_common_internal_zzf), (zzg) zzbq.checkNotNull(com_google_android_gms_common_internal_zzg), null);
    }

    protected zzd(Context context, Looper looper, zzag com_google_android_gms_common_internal_zzag, zze com_google_android_gms_common_zze, int i, zzf com_google_android_gms_common_internal_zzf, zzg com_google_android_gms_common_internal_zzg, String str) {
        this.mLock = new Object();
        this.zzfvq = new Object();
        this.zzfvu = new ArrayList();
        this.zzfvw = 1;
        this.zzfwb = null;
        this.zzfwc = false;
        this.zzfwd = new AtomicInteger(0);
        this.mContext = (Context) zzbq.checkNotNull(context, "Context must not be null");
        this.zzakm = (Looper) zzbq.checkNotNull(looper, "Looper must not be null");
        this.zzfvp = (zzag) zzbq.checkNotNull(com_google_android_gms_common_internal_zzag, "Supervisor must not be null");
        this.zzfni = (zze) zzbq.checkNotNull(com_google_android_gms_common_zze, "API availability must not be null");
        this.mHandler = new zzh(this, looper);
        this.zzfvz = i;
        this.zzfvx = com_google_android_gms_common_internal_zzf;
        this.zzfvy = com_google_android_gms_common_internal_zzg;
        this.zzfwa = str;
    }

    private final void zza(int i, T t) {
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        zzbq.checkArgument(z);
        synchronized (this.mLock) {
            this.zzfvw = i;
            this.zzfvt = t;
            switch (i) {
                case 1:
                    if (this.zzfvv != null) {
                        this.zzfvp.zza(zzhf(), zzajv(), 129, this.zzfvv, zzajw());
                        this.zzfvv = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    String zzalc;
                    String packageName;
                    if (!(this.zzfvv == null || this.zzfvo == null)) {
                        zzalc = this.zzfvo.zzalc();
                        packageName = this.zzfvo.getPackageName();
                        Log.e("GmsClient", new StringBuilder((String.valueOf(zzalc).length() + 70) + String.valueOf(packageName).length()).append("Calling connect() while still connected, missing disconnect() for ").append(zzalc).append(" on ").append(packageName).toString());
                        this.zzfvp.zza(this.zzfvo.zzalc(), this.zzfvo.getPackageName(), this.zzfvo.zzaky(), this.zzfvv, zzajw());
                        this.zzfwd.incrementAndGet();
                    }
                    this.zzfvv = new zzl(this, this.zzfwd.get());
                    this.zzfvo = new zzam(zzajv(), zzhf(), false, 129);
                    if (!this.zzfvp.zza(new zzah(this.zzfvo.zzalc(), this.zzfvo.getPackageName(), this.zzfvo.zzaky()), this.zzfvv, zzajw())) {
                        zzalc = this.zzfvo.zzalc();
                        packageName = this.zzfvo.getPackageName();
                        Log.e("GmsClient", new StringBuilder((String.valueOf(zzalc).length() + 34) + String.valueOf(packageName).length()).append("unable to connect to service: ").append(zzalc).append(" on ").append(packageName).toString());
                        zza(16, null, this.zzfwd.get());
                        break;
                    }
                    break;
                case 4:
                    zza((IInterface) t);
                    break;
            }
        }
    }

    private final boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzfvw != i) {
                z = false;
            } else {
                zza(i2, (IInterface) t);
                z = true;
            }
        }
        return z;
    }

    @Nullable
    private final String zzajw() {
        return this.zzfwa == null ? this.mContext.getClass().getName() : this.zzfwa;
    }

    private final boolean zzajy() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzfvw == 3;
        }
        return z;
    }

    private final boolean zzake() {
        if (this.zzfwc || TextUtils.isEmpty(zzhg()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(zzhg());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private final void zzcf(int i) {
        int i2;
        if (zzajy()) {
            i2 = 5;
            this.zzfwc = true;
        } else {
            i2 = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i2, this.zzfwd.get(), 16));
    }

    public void disconnect() {
        this.zzfwd.incrementAndGet();
        synchronized (this.zzfvu) {
            int size = this.zzfvu.size();
            for (int i = 0; i < size; i++) {
                ((zzi) this.zzfvu.get(i)).removeListener();
            }
            this.zzfvu.clear();
        }
        synchronized (this.zzfvq) {
            this.zzfvr = null;
        }
        zza(1, null);
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.mLock) {
            int i = this.zzfvw;
            IInterface iInterface = this.zzfvt;
        }
        synchronized (this.zzfvq) {
            zzay com_google_android_gms_common_internal_zzay = this.zzfvr;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append(zzhg()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (com_google_android_gms_common_internal_zzay == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(com_google_android_gms_common_internal_zzay.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzfvl > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzfvl;
            String format = simpleDateFormat.format(new Date(this.zzfvl));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(Constants.SPACE).append(format).toString());
        }
        if (this.zzfvk > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzfvj) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzfvj));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.zzfvk;
            format = simpleDateFormat.format(new Date(this.zzfvk));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(Constants.SPACE).append(format).toString());
        }
        if (this.zzfvn > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzfvm));
            append = printWriter.append(" lastFailedTime=");
            j = this.zzfvn;
            String format2 = simpleDateFormat.format(new Date(this.zzfvn));
            append.println(new StringBuilder(String.valueOf(format2).length() + 21).append(j).append(Constants.SPACE).append(format2).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzakm;
    }

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public final boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzfvw == 4;
        }
        return z;
    }

    public final boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzfvw == 2 || this.zzfvw == 3;
        }
        return z;
    }

    @CallSuper
    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfvm = connectionResult.getErrorCode();
        this.zzfvn = System.currentTimeMillis();
    }

    @CallSuper
    protected void onConnectionSuspended(int i) {
        this.zzfvj = i;
        this.zzfvk = System.currentTimeMillis();
    }

    protected final void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzo(this, i, null)));
    }

    protected void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzn(this, i, iBinder, bundle)));
    }

    @CallSuper
    protected void zza(@NonNull T t) {
        this.zzfvl = System.currentTimeMillis();
    }

    @WorkerThread
    public final void zza(zzan com_google_android_gms_common_internal_zzan, Set<Scope> set) {
        Throwable e;
        Bundle zzaae = zzaae();
        zzz com_google_android_gms_common_internal_zzz = new zzz(this.zzfvz);
        com_google_android_gms_common_internal_zzz.zzfwz = this.mContext.getPackageName();
        com_google_android_gms_common_internal_zzz.zzfxc = zzaae;
        if (set != null) {
            com_google_android_gms_common_internal_zzz.zzfxb = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (zzaam()) {
            com_google_android_gms_common_internal_zzz.zzfxd = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (com_google_android_gms_common_internal_zzan != null) {
                com_google_android_gms_common_internal_zzz.zzfxa = com_google_android_gms_common_internal_zzan.asBinder();
            }
        } else if (zzakc()) {
            com_google_android_gms_common_internal_zzz.zzfxd = getAccount();
        }
        com_google_android_gms_common_internal_zzz.zzfxe = zzajz();
        try {
            synchronized (this.zzfvq) {
                if (this.zzfvr != null) {
                    this.zzfvr.zza(new zzk(this, this.zzfwd.get()), com_google_android_gms_common_internal_zzz);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (Throwable e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            zzce(1);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RemoteException e4) {
            e2 = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            zza(8, null, null, this.zzfwd.get());
        } catch (RuntimeException e5) {
            e2 = e5;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            zza(8, null, null, this.zzfwd.get());
        }
    }

    public void zza(@NonNull zzj com_google_android_gms_common_internal_zzj) {
        this.zzfvs = (zzj) zzbq.checkNotNull(com_google_android_gms_common_internal_zzj, "Connection progress callbacks cannot be null.");
        zza(2, null);
    }

    protected final void zza(@NonNull zzj com_google_android_gms_common_internal_zzj, int i, @Nullable PendingIntent pendingIntent) {
        this.zzfvs = (zzj) zzbq.checkNotNull(com_google_android_gms_common_internal_zzj, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzfwd.get(), i, pendingIntent));
    }

    public void zza(@NonNull zzp com_google_android_gms_common_internal_zzp) {
        com_google_android_gms_common_internal_zzp.zzait();
    }

    protected Bundle zzaae() {
        return new Bundle();
    }

    public boolean zzaam() {
        return false;
    }

    public boolean zzaaw() {
        return false;
    }

    public Bundle zzaew() {
        return null;
    }

    public boolean zzafu() {
        return true;
    }

    @Nullable
    public final IBinder zzafv() {
        IBinder iBinder;
        synchronized (this.zzfvq) {
            if (this.zzfvr == null) {
                iBinder = null;
            } else {
                iBinder = this.zzfvr.asBinder();
            }
        }
        return iBinder;
    }

    protected String zzajv() {
        return "com.google.android.gms";
    }

    public final void zzajx() {
        int isGooglePlayServicesAvailable = this.zzfni.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, null);
            zza(new zzm(this), isGooglePlayServicesAvailable, null);
            return;
        }
        zza(new zzm(this));
    }

    public zzc[] zzajz() {
        return new zzc[0];
    }

    protected final void zzaka() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzakb() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzfvw == 5) {
                throw new DeadObjectException();
            }
            zzaka();
            zzbq.zza(this.zzfvt != null, (Object) "Client is connected but service is null");
            t = this.zzfvt;
        }
        return t;
    }

    public boolean zzakc() {
        return false;
    }

    protected Set<Scope> zzakd() {
        return Collections.EMPTY_SET;
    }

    public final void zzce(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzfwd.get(), i));
    }

    @Nullable
    protected abstract T zzd(IBinder iBinder);

    @NonNull
    protected abstract String zzhf();

    @NonNull
    protected abstract String zzhg();
}
