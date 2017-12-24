package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.iid.MessengerCompat;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.List;
import java.util.concurrent.ExecutionException;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public final class zzw {
    private static PendingIntent zzhzr;
    private static int zzicx = 0;
    private static long zzidg;
    private static int zznur = 0;
    private static int zznus;
    private Context zzaif;
    private Messenger zzhzv;
    private Messenger zzida;
    private MessengerCompat zzidb;
    private final SimpleArrayMap<String, zzz> zznut = new SimpleArrayMap();

    public zzw(Context context) {
        this.zzaif = context.getApplicationContext();
        this.zzhzv = new Messenger(new zzx(this, Looper.getMainLooper()));
    }

    private static String zza(KeyPair keyPair, String... strArr) {
        String str = null;
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes("UTF-8");
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature instance = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                instance.initSign(privateKey);
                instance.update(bytes);
                str = FirebaseInstanceId.zzn(instance.sign());
            } catch (Throwable e) {
                Log.e("FirebaseInstanceId", "Unable to sign registration request", e);
            }
        } catch (Throwable e2) {
            Log.e("FirebaseInstanceId", "Unable to encode string", e2);
        }
        return str;
    }

    private final Bundle zzae(Bundle bundle) throws IOException {
        Bundle zzaf = zzaf(bundle);
        if (zzaf == null || !zzaf.containsKey("google.messenger")) {
            return zzaf;
        }
        zzaf = zzaf(bundle);
        return (zzaf == null || !zzaf.containsKey("google.messenger")) ? zzaf : null;
    }

    private final Bundle zzaf(Bundle bundle) throws IOException {
        String zzauw = zzauw();
        zzy com_google_firebase_iid_zzy = new zzy();
        synchronized (this.zznut) {
            this.zznut.put(zzauw, com_google_firebase_iid_zzy);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzidg != 0 && elapsedRealtime <= zzidg) {
            elapsedRealtime = zzidg - elapsedRealtime;
            Log.w("FirebaseInstanceId", "Backoff mode, next request attempt: " + elapsedRealtime + " interval: " + zznus);
            throw new IOException("RETRY_LATER");
        } else if (zzev(this.zzaif) == 0) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
        } else {
            Bundle zzchk;
            Intent intent = new Intent();
            intent.setPackage("com.google.android.gms");
            if (zzev(this.zzaif) == 2) {
                intent.setAction("com.google.iid.TOKEN_REQUEST");
            } else {
                intent.setAction("com.google.android.c2dm.intent.REGISTER");
            }
            intent.putExtras(bundle);
            zzc(this.zzaif, intent);
            intent.putExtra("kid", new StringBuilder(String.valueOf(zzauw).length() + 5).append("|ID|").append(zzauw).append("|").toString());
            intent.putExtra("X-kid", new StringBuilder(String.valueOf(zzauw).length() + 5).append("|ID|").append(zzauw).append("|").toString());
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(intent.getExtras());
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
            }
            intent.putExtra("google.messenger", this.zzhzv);
            if (!(this.zzida == null && this.zzidb == null)) {
                Message obtain = Message.obtain();
                obtain.obj = intent;
                try {
                    if (this.zzida != null) {
                        this.zzida.send(obtain);
                    } else {
                        this.zzidb.send(obtain);
                    }
                } catch (RemoteException e) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        Log.d("FirebaseInstanceId", "Messenger failed, fallback to startService");
                    }
                }
                zzchk = com_google_firebase_iid_zzy.zzchk();
                synchronized (this.zznut) {
                    this.zznut.remove(zzauw);
                }
                return zzchk;
            }
            if (zzev(this.zzaif) == 2) {
                this.zzaif.sendBroadcast(intent);
            } else {
                this.zzaif.startService(intent);
            }
            try {
                zzchk = com_google_firebase_iid_zzy.zzchk();
                synchronized (this.zznut) {
                    this.zznut.remove(zzauw);
                }
                return zzchk;
            } catch (Throwable th) {
                synchronized (this.zznut) {
                    this.zznut.remove(zzauw);
                }
            }
        }
    }

    private static synchronized String zzauw() {
        String num;
        synchronized (zzw.class) {
            int i = zzicx;
            zzicx = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzbl(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = r5.zznut;
        monitor-enter(r2);
        if (r6 != 0) goto L_0x0025;
    L_0x0005:
        r0 = 0;
        r1 = r0;
    L_0x0007:
        r0 = r5.zznut;	 Catch:{ all -> 0x0046 }
        r0 = r0.size();	 Catch:{ all -> 0x0046 }
        if (r1 >= r0) goto L_0x001e;
    L_0x000f:
        r0 = r5.zznut;	 Catch:{ all -> 0x0046 }
        r0 = r0.valueAt(r1);	 Catch:{ all -> 0x0046 }
        r0 = (com.google.firebase.iid.zzz) r0;	 Catch:{ all -> 0x0046 }
        r0.onError(r7);	 Catch:{ all -> 0x0046 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0007;
    L_0x001e:
        r0 = r5.zznut;	 Catch:{ all -> 0x0046 }
        r0.clear();	 Catch:{ all -> 0x0046 }
    L_0x0023:
        monitor-exit(r2);	 Catch:{ all -> 0x0046 }
    L_0x0024:
        return;
    L_0x0025:
        r0 = r5.zznut;	 Catch:{ all -> 0x0046 }
        r0 = r0.remove(r6);	 Catch:{ all -> 0x0046 }
        r0 = (com.google.firebase.iid.zzz) r0;	 Catch:{ all -> 0x0046 }
        if (r0 != 0) goto L_0x004f;
    L_0x002f:
        r1 = "FirebaseInstanceId";
        r3 = "Missing callback for ";
        r0 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x0046 }
        r4 = r0.length();	 Catch:{ all -> 0x0046 }
        if (r4 == 0) goto L_0x0049;
    L_0x003d:
        r0 = r3.concat(r0);	 Catch:{ all -> 0x0046 }
    L_0x0041:
        android.util.Log.w(r1, r0);	 Catch:{ all -> 0x0046 }
        monitor-exit(r2);	 Catch:{ all -> 0x0046 }
        goto L_0x0024;
    L_0x0046:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0046 }
        throw r0;
    L_0x0049:
        r0 = new java.lang.String;	 Catch:{ all -> 0x0046 }
        r0.<init>(r3);	 Catch:{ all -> 0x0046 }
        goto L_0x0041;
    L_0x004f:
        r0.onError(r7);	 Catch:{ all -> 0x0046 }
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzw.zzbl(java.lang.String, java.lang.String):void");
    }

    public static synchronized void zzc(Context context, Intent intent) {
        synchronized (zzw.class) {
            if (zzhzr == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzhzr = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra(SettingsJsonConstants.APP_KEY, zzhzr);
        }
    }

    public static boolean zzeu(Context context) {
        return zzev(context) != 0;
    }

    @VisibleForTesting
    private static synchronized int zzev(Context context) {
        int i = 0;
        synchronized (zzw.class) {
            if (zznur != 0) {
                i = zznur;
            } else {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
                    Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
                } else {
                    Intent intent;
                    List queryIntentServices;
                    if (!zzq.isAtLeastO()) {
                        intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                        intent.setPackage("com.google.android.gms");
                        queryIntentServices = packageManager.queryIntentServices(intent, 0);
                        if (queryIntentServices != null && queryIntentServices.size() > 0) {
                            zznur = 1;
                            i = 1;
                        }
                    }
                    intent = new Intent("com.google.iid.TOKEN_REQUEST");
                    intent.setPackage("com.google.android.gms");
                    queryIntentServices = packageManager.queryBroadcastReceivers(intent, 0);
                    if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                        Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
                        if (zzq.isAtLeastO()) {
                            zznur = 2;
                        } else {
                            zznur = 1;
                        }
                        i = zznur;
                    } else {
                        zznur = 2;
                        i = 2;
                    }
                }
            }
        }
        return i;
    }

    final Bundle zzc(Bundle bundle, KeyPair keyPair) throws IOException {
        Exception e;
        int zzam = FirebaseInstanceId.zzam(this.zzaif, "com.google.android.gms");
        bundle.putString("gmsv", Integer.toString(zzam));
        bundle.putString("osv", Integer.toString(VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(FirebaseInstanceId.zzes(this.zzaif)));
        bundle.putString("app_ver_name", FirebaseInstanceId.zzdk(this.zzaif));
        bundle.putString("cliv", "fiid-11720000");
        bundle.putString("appid", FirebaseInstanceId.zza(keyPair));
        bundle.putString("pub2", FirebaseInstanceId.zzn(keyPair.getPublic().getEncoded()));
        bundle.putString("sig", zza(keyPair, this.zzaif.getPackageName(), r1));
        if (zzam < PL2303Driver.BAUD12000000) {
            return zzae(bundle);
        }
        try {
            return (Bundle) Tasks.await(zzk.zzet(this.zzaif).zzi(1, bundle));
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(e);
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 22).append("Error making request: ").append(valueOf).toString());
        }
        return ((e.getCause() instanceof zzu) && ((zzu) e.getCause()).getErrorCode() == 4) ? zzae(bundle) : null;
    }

    final void zzc(Message message) {
        if (message != null) {
            if (message.obj instanceof Intent) {
                Intent intent = (Intent) message.obj;
                intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof MessengerCompat) {
                        this.zzidb = (MessengerCompat) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        this.zzida = (Messenger) parcelableExtra;
                    }
                }
                intent = (Intent) message.obj;
                if (intent != null) {
                    String stringExtra;
                    String valueOf;
                    String str;
                    if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
                        stringExtra = intent.getStringExtra("registration_id");
                        if (stringExtra == null) {
                            stringExtra = intent.getStringExtra("unregistered");
                        }
                        String stringExtra2;
                        String str2;
                        String str3;
                        String[] split;
                        Object obj;
                        if (stringExtra == null) {
                            stringExtra2 = intent.getStringExtra("error");
                            if (stringExtra2 == null) {
                                valueOf = String.valueOf(intent.getExtras());
                                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 49).append("Unexpected response, no error or registration id ").append(valueOf).toString());
                                return;
                            }
                            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                                str2 = "FirebaseInstanceId";
                                str3 = "Received InstanceID error ";
                                stringExtra = String.valueOf(stringExtra2);
                                Log.d(str2, stringExtra.length() != 0 ? str3.concat(stringExtra) : new String(str3));
                            }
                            if (stringExtra2.startsWith("|")) {
                                split = stringExtra2.split("\\|");
                                if (!"ID".equals(split[1])) {
                                    str3 = "FirebaseInstanceId";
                                    String str4 = "Unexpected structured response ";
                                    stringExtra = String.valueOf(stringExtra2);
                                    Log.w(str3, stringExtra.length() != 0 ? str4.concat(stringExtra) : new String(str4));
                                }
                                if (split.length > 2) {
                                    stringExtra = split[2];
                                    str = split[3];
                                    if (str.startsWith(":")) {
                                        str = str.substring(1);
                                    }
                                } else {
                                    str = "UNKNOWN";
                                    obj = null;
                                }
                                intent.putExtra("error", str);
                            } else {
                                stringExtra = null;
                                str = stringExtra2;
                            }
                            zzbl(stringExtra, str);
                            long longExtra = intent.getLongExtra(HttpHeaders.RETRY_AFTER, 0);
                            if (longExtra > 0) {
                                zznus = ((int) longExtra) * 1000;
                                zzidg = SystemClock.elapsedRealtime() + ((long) zznus);
                                Log.w("FirebaseInstanceId", "Explicit request from server to backoff: " + zznus);
                                return;
                            }
                            return;
                        }
                        Bundle extras;
                        zzz com_google_firebase_iid_zzz;
                        zzidg = 0;
                        zznus = 0;
                        if (stringExtra.startsWith("|")) {
                            split = stringExtra.split("\\|");
                            if (!"ID".equals(split[1])) {
                                stringExtra2 = "FirebaseInstanceId";
                                str3 = "Unexpected structured response ";
                                stringExtra = String.valueOf(stringExtra);
                                Log.w(stringExtra2, stringExtra.length() != 0 ? str3.concat(stringExtra) : new String(str3));
                            }
                            stringExtra2 = split[2];
                            if (split.length > 4) {
                                if ("SYNC".equals(split[3])) {
                                    zzi.zza(this.zzaif, null).zzchd();
                                } else if ("RST".equals(split[3])) {
                                    zzi.zza(this.zzaif, null).zzchb();
                                    intent.removeExtra("registration_id");
                                    obj = stringExtra2;
                                    extras = intent.getExtras();
                                    synchronized (this.zznut) {
                                        com_google_firebase_iid_zzz = (zzz) this.zznut.remove(obj);
                                        if (com_google_firebase_iid_zzz != null) {
                                            str = "FirebaseInstanceId";
                                            str2 = "Missing callback for ";
                                            valueOf = String.valueOf(obj);
                                            Log.w(str, valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
                                            return;
                                        }
                                        com_google_firebase_iid_zzz.zzag(extras);
                                        return;
                                    }
                                }
                            }
                            stringExtra = split[split.length - 1];
                            if (stringExtra.startsWith(":")) {
                                stringExtra = stringExtra.substring(1);
                            }
                            intent.putExtra("registration_id", stringExtra);
                            obj = stringExtra2;
                        } else {
                            obj = null;
                        }
                        if (obj == null) {
                            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                                Log.d("FirebaseInstanceId", "Ignoring response without a request ID");
                                return;
                            }
                            return;
                        }
                        extras = intent.getExtras();
                        synchronized (this.zznut) {
                            com_google_firebase_iid_zzz = (zzz) this.zznut.remove(obj);
                            if (com_google_firebase_iid_zzz != null) {
                                com_google_firebase_iid_zzz.zzag(extras);
                                return;
                            }
                            str = "FirebaseInstanceId";
                            str2 = "Missing callback for ";
                            valueOf = String.valueOf(obj);
                            if (valueOf.length() == 0) {
                            }
                            Log.w(str, valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
                            return;
                        }
                    } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        stringExtra = "FirebaseInstanceId";
                        str = "Unexpected response ";
                        valueOf = String.valueOf(intent.getAction());
                        Log.d(stringExtra, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return;
                    } else {
                        return;
                    }
                } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Unexpected response: null");
                    return;
                } else {
                    return;
                }
            }
            Log.w("FirebaseInstanceId", "Dropping invalid message");
        }
    }
}
