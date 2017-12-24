package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.internal.zzk;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzeug;
import com.google.android.gms.internal.zzeuh;
import com.google.android.gms.internal.zzeui;
import com.google.android.gms.internal.zzeuj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final Object sLock = new Object();
    static final Map<String, FirebaseApp> zzick = new ArrayMap();
    private static final List<String> zzlwf = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzlwg = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzlwh = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zzlwi = Arrays.asList(new String[0]);
    private static final Set<String> zzlwj = Collections.emptySet();
    private final Context mApplicationContext;
    private final String mName;
    private final FirebaseOptions zzlwk;
    private final AtomicBoolean zzlwl = new AtomicBoolean(false);
    private final AtomicBoolean zzlwm = new AtomicBoolean();
    private final List<zzb> zzlwn = new CopyOnWriteArrayList();
    private final List<zza> zzlwo = new CopyOnWriteArrayList();
    private final List<Object> zzlwp = new CopyOnWriteArrayList();
    private zzeui zzlwq;
    private zzc zzlwr;

    public interface zzc {
    }

    public interface zza {
        void zzbe(boolean z);
    }

    public interface zzb {
        void zzb(@NonNull zzeuj com_google_android_gms_internal_zzeuj);
    }

    @TargetApi(24)
    static class zzd extends BroadcastReceiver {
        private static AtomicReference<zzd> zzlws = new AtomicReference();
        private final Context mApplicationContext;

        private zzd(Context context) {
            this.mApplicationContext = context;
        }

        private static void zzeo(Context context) {
            if (zzlws.get() == null) {
                BroadcastReceiver com_google_firebase_FirebaseApp_zzd = new zzd(context);
                if (zzlws.compareAndSet(null, com_google_firebase_FirebaseApp_zzd)) {
                    context.registerReceiver(com_google_firebase_FirebaseApp_zzd, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.sLock) {
                for (FirebaseApp zza : FirebaseApp.zzick.values()) {
                    zza.zzbpa();
                }
            }
            this.mApplicationContext.unregisterReceiver(this);
        }
    }

    private FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.mApplicationContext = (Context) zzbq.checkNotNull(context);
        this.mName = zzbq.zzgh(str);
        this.zzlwk = (FirebaseOptions) zzbq.checkNotNull(firebaseOptions);
        this.zzlwr = new zzeug();
    }

    public static List<FirebaseApp> getApps(Context context) {
        List<FirebaseApp> arrayList;
        zzeuh.zzew(context);
        synchronized (sLock) {
            arrayList = new ArrayList(zzick.values());
            zzeuh.zzchr();
            Set<String> zzchs = zzeuh.zzchs();
            zzchs.removeAll(zzick.keySet());
            for (String str : zzchs) {
                zzeuh.zzra(str);
                arrayList.add(initializeApp(context, null, str));
            }
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (sLock) {
            firebaseApp = (FirebaseApp) zzick.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                String zzamc = zzs.zzamc();
                throw new IllegalStateException(new StringBuilder(String.valueOf(zzamc).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(zzamc).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        synchronized (sLock) {
            firebaseApp = (FirebaseApp) zzick.get(str.trim());
            if (firebaseApp != null) {
            } else {
                String str2;
                Iterable zzboz = zzboz();
                if (zzboz.isEmpty()) {
                    str2 = "";
                } else {
                    String str3 = "Available app names: ";
                    str2 = String.valueOf(TextUtils.join(", ", zzboz));
                    str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    public static FirebaseApp initializeApp(Context context) {
        FirebaseApp instance;
        synchronized (sLock) {
            if (zzick.containsKey(DEFAULT_APP_NAME)) {
                instance = getInstance();
            } else {
                FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
                if (fromResource == null) {
                    instance = null;
                } else {
                    instance = initializeApp(context, fromResource);
                }
            }
        }
        return instance;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzeuh.zzew(context);
        if (context.getApplicationContext() instanceof Application) {
            zzk.zza((Application) context.getApplicationContext());
            zzk.zzagp().zza(new zza());
        }
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (sLock) {
            zzbq.zza(!zzick.containsKey(trim), new StringBuilder(String.valueOf(trim).length() + 33).append("FirebaseApp name ").append(trim).append(" already exists!").toString());
            zzbq.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, trim, firebaseOptions);
            zzick.put(trim, firebaseApp);
        }
        zzeuh.zzf(firebaseApp);
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlwf);
        if (firebaseApp.zzbox()) {
            firebaseApp.zza(FirebaseApp.class, firebaseApp, zzlwg);
            firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzlwh);
        }
        return firebaseApp;
    }

    private final <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.mApplicationContext);
        if (isDeviceProtectedStorage) {
            zzd.zzeo(this.mApplicationContext);
        }
        for (String str : iterable) {
            String str2;
            if (isDeviceProtectedStorage) {
                try {
                    if (!zzlwi.contains(str2)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (zzlwj.contains(str2)) {
                        throw new IllegalStateException(String.valueOf(str2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(str2).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(str2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (Throwable e3) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
                } catch (Throwable e4) {
                    String str3 = "FirebaseApp";
                    String str4 = "Failed to initialize ";
                    str2 = String.valueOf(str2);
                    Log.wtf(str3, str2.length() != 0 ? str4.concat(str2) : new String(str4), e4);
                }
            }
            Method method = Class.forName(str2).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    public static void zzbe(boolean z) {
        synchronized (sLock) {
            ArrayList arrayList = new ArrayList(zzick.values());
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                FirebaseApp firebaseApp = (FirebaseApp) obj;
                if (firebaseApp.zzlwl.get()) {
                    firebaseApp.zzca(z);
                }
            }
        }
    }

    private final void zzbow() {
        zzbq.zza(!this.zzlwm.get(), (Object) "FirebaseApp was deleted");
    }

    private static List<String> zzboz() {
        Collection arraySet = new ArraySet();
        synchronized (sLock) {
            for (FirebaseApp name : zzick.values()) {
                arraySet.add(name.getName());
            }
            if (zzeuh.zzchr() != null) {
                arraySet.addAll(zzeuh.zzchs());
            }
        }
        List<String> arrayList = new ArrayList(arraySet);
        Collections.sort(arrayList);
        return arrayList;
    }

    private final void zzbpa() {
        zza(FirebaseApp.class, this, zzlwf);
        if (zzbox()) {
            zza(FirebaseApp.class, this, zzlwg);
            zza(Context.class, this.mApplicationContext, zzlwh);
        }
    }

    private final void zzca(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zza zzbe : this.zzlwo) {
            zzbe.zzbe(z);
        }
    }

    public boolean equals(Object obj) {
        return !(obj instanceof FirebaseApp) ? false : this.mName.equals(((FirebaseApp) obj).getName());
    }

    @NonNull
    public Context getApplicationContext() {
        zzbow();
        return this.mApplicationContext;
    }

    @NonNull
    public String getName() {
        zzbow();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzbow();
        return this.zzlwk;
    }

    public final Task<GetTokenResult> getToken(boolean z) {
        zzbow();
        return this.zzlwq == null ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : this.zzlwq.zzcb(z);
    }

    @Nullable
    public final String getUid() throws FirebaseApiNotAvailableException {
        zzbow();
        if (this.zzlwq != null) {
            return this.zzlwq.getUid();
        }
        throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        zzbow();
        if (this.zzlwl.compareAndSet(!z, z)) {
            boolean zzagq = zzk.zzagp().zzagq();
            if (z && zzagq) {
                zzca(true);
            } else if (!z && zzagq) {
                zzca(false);
            }
        }
    }

    public String toString() {
        return zzbg.zzw(this).zzg("name", this.mName).zzg("options", this.zzlwk).toString();
    }

    public final void zza(@NonNull zzeui com_google_android_gms_internal_zzeui) {
        this.zzlwq = (zzeui) zzbq.checkNotNull(com_google_android_gms_internal_zzeui);
    }

    @UiThread
    public final void zza(@NonNull zzeuj com_google_android_gms_internal_zzeuj) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (zzb zzb : this.zzlwn) {
            zzb.zzb(com_google_android_gms_internal_zzeuj);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    public final void zza(zza com_google_firebase_FirebaseApp_zza) {
        zzbow();
        if (this.zzlwl.get() && zzk.zzagp().zzagq()) {
            com_google_firebase_FirebaseApp_zza.zzbe(true);
        }
        this.zzlwo.add(com_google_firebase_FirebaseApp_zza);
    }

    public final void zza(@NonNull zzb com_google_firebase_FirebaseApp_zzb) {
        zzbow();
        zzbq.checkNotNull(com_google_firebase_FirebaseApp_zzb);
        this.zzlwn.add(com_google_firebase_FirebaseApp_zzb);
        this.zzlwn.size();
    }

    public final boolean zzbox() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    public final String zzboy() {
        String zzl = com.google.android.gms.common.util.zzb.zzl(getName().getBytes());
        String zzl2 = com.google.android.gms.common.util.zzb.zzl(getOptions().getApplicationId().getBytes());
        return new StringBuilder((String.valueOf(zzl).length() + 1) + String.valueOf(zzl2).length()).append(zzl).append("+").append(zzl2).toString();
    }
}
