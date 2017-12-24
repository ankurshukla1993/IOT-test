package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.internal.zzbd;
import com.google.android.gms.common.api.internal.zzch;
import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.common.api.internal.zzcx;
import com.google.android.gms.common.api.internal.zzdi;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.api.internal.zzw;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.internal.zzcvy;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set<GoogleApiClient> zzfjt = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private final Context mContext;
        private Looper zzakm;
        private String zzdyu;
        private Account zzdzb;
        private final Set<Scope> zzfju;
        private final Set<Scope> zzfjv;
        private int zzfjw;
        private View zzfjx;
        private String zzfjy;
        private final Map<Api<?>, zzt> zzfjz;
        private final Map<Api<?>, ApiOptions> zzfka;
        private zzch zzfkb;
        private int zzfkc;
        private OnConnectionFailedListener zzfkd;
        private GoogleApiAvailability zzfke;
        private zza<? extends zzcwb, zzcwc> zzfkf;
        private final ArrayList<ConnectionCallbacks> zzfkg;
        private final ArrayList<OnConnectionFailedListener> zzfkh;
        private boolean zzfki;

        public Builder(@NonNull Context context) {
            this.zzfju = new HashSet();
            this.zzfjv = new HashSet();
            this.zzfjz = new ArrayMap();
            this.zzfka = new ArrayMap();
            this.zzfkc = -1;
            this.zzfke = GoogleApiAvailability.getInstance();
            this.zzfkf = zzcvy.zzdyi;
            this.zzfkg = new ArrayList();
            this.zzfkh = new ArrayList();
            this.zzfki = false;
            this.mContext = context;
            this.zzakm = context.getMainLooper();
            this.zzdyu = context.getPackageName();
            this.zzfjy = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzbq.checkNotNull(connectionCallbacks, "Must provide a connected listener");
            this.zzfkg.add(connectionCallbacks);
            zzbq.checkNotNull(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zzfkh.add(onConnectionFailedListener);
        }

        private final <O extends ApiOptions> void zza(Api<O> api, O o, Scope... scopeArr) {
            Set hashSet = new HashSet(api.zzafr().zzq(o));
            for (Object add : scopeArr) {
                hashSet.add(add);
            }
            this.zzfjz.put(api, new zzt(hashSet));
        }

        public final Builder addApi(@NonNull Api<? extends NotRequiredOptions> api) {
            zzbq.checkNotNull(api, "Api must not be null");
            this.zzfka.put(api, null);
            Collection zzq = api.zzafr().zzq(null);
            this.zzfjv.addAll(zzq);
            this.zzfju.addAll(zzq);
            return this;
        }

        public final <O extends HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            zzbq.checkNotNull(api, "Api must not be null");
            zzbq.checkNotNull(o, "Null options are not permitted for this Api");
            this.zzfka.put(api, o);
            Collection zzq = api.zzafr().zzq(o);
            this.zzfjv.addAll(zzq);
            this.zzfju.addAll(zzq);
            return this;
        }

        public final <O extends HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            zzbq.checkNotNull(api, "Api must not be null");
            zzbq.checkNotNull(o, "Null options are not permitted for this Api");
            this.zzfka.put(api, o);
            zza(api, o, scopeArr);
            return this;
        }

        public final Builder addApiIfAvailable(@NonNull Api<? extends NotRequiredOptions> api, Scope... scopeArr) {
            zzbq.checkNotNull(api, "Api must not be null");
            this.zzfka.put(api, null);
            zza(api, null, scopeArr);
            return this;
        }

        public final Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzbq.checkNotNull(connectionCallbacks, "Listener must not be null");
            this.zzfkg.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzbq.checkNotNull(onConnectionFailedListener, "Listener must not be null");
            this.zzfkh.add(onConnectionFailedListener);
            return this;
        }

        public final Builder addScope(@NonNull Scope scope) {
            zzbq.checkNotNull(scope, "Scope must not be null");
            this.zzfju.add(scope);
            return this;
        }

        public final GoogleApiClient build() {
            zzbq.checkArgument(!this.zzfka.isEmpty(), "must call addApi() to add at least one API");
            zzr zzagh = zzagh();
            Api api = null;
            Map zzakl = zzagh.zzakl();
            Map arrayMap = new ArrayMap();
            Map arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Object obj = null;
            for (Api api2 : this.zzfka.keySet()) {
                Api api22;
                Object obj2 = this.zzfka.get(api22);
                boolean z = zzakl.get(api22) != null;
                arrayMap.put(api22, Boolean.valueOf(z));
                ConnectionCallbacks com_google_android_gms_common_api_internal_zzw = new zzw(api22, z);
                arrayList.add(com_google_android_gms_common_api_internal_zzw);
                zzd zzafs = api22.zzafs();
                zze zza = zzafs.zza(this.mContext, this.zzakm, zzagh, obj2, com_google_android_gms_common_api_internal_zzw, com_google_android_gms_common_api_internal_zzw);
                arrayMap2.put(api22.zzaft(), zza);
                Object obj3 = zzafs.getPriority() == 1 ? obj2 != null ? 1 : null : obj;
                if (!zza.zzaaw()) {
                    api22 = api;
                } else if (api != null) {
                    String name = api22.getName();
                    String name2 = api.getName();
                    throw new IllegalStateException(new StringBuilder((String.valueOf(name).length() + 21) + String.valueOf(name2).length()).append(name).append(" cannot be used with ").append(name2).toString());
                }
                obj = obj3;
                api = api22;
            }
            if (api != null) {
                if (obj != null) {
                    name = api.getName();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(name).length() + 82).append("With using ").append(name).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
                }
                zzbq.zza(this.zzdzb == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzbq.zza(this.zzfju.equals(this.zzfjv), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            GoogleApiClient com_google_android_gms_common_api_internal_zzbd = new zzbd(this.mContext, new ReentrantLock(), this.zzakm, zzagh, this.zzfke, this.zzfkf, arrayMap, this.zzfkg, this.zzfkh, arrayMap2, this.zzfkc, zzbd.zza(arrayMap2.values(), true), arrayList, false);
            synchronized (GoogleApiClient.zzfjt) {
                GoogleApiClient.zzfjt.add(com_google_android_gms_common_api_internal_zzbd);
            }
            if (this.zzfkc >= 0) {
                zzi.zza(this.zzfkb).zza(this.zzfkc, com_google_android_gms_common_api_internal_zzbd, this.zzfkd);
            }
            return com_google_android_gms_common_api_internal_zzbd;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            zzch com_google_android_gms_common_api_internal_zzch = new zzch(fragmentActivity);
            zzbq.checkArgument(i >= 0, "clientId must be non-negative");
            this.zzfkc = i;
            this.zzfkd = onConnectionFailedListener;
            this.zzfkb = com_google_android_gms_common_api_internal_zzch;
            return this;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public final Builder setAccountName(String str) {
            this.zzdzb = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zzfjw = i;
            return this;
        }

        public final Builder setHandler(@NonNull Handler handler) {
            zzbq.checkNotNull(handler, "Handler must not be null");
            this.zzakm = handler.getLooper();
            return this;
        }

        public final Builder setViewForPopups(@NonNull View view) {
            zzbq.checkNotNull(view, "View must not be null");
            this.zzfjx = view;
            return this;
        }

        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public final zzr zzagh() {
            zzcwc com_google_android_gms_internal_zzcwc = zzcwc.zzjyz;
            if (this.zzfka.containsKey(zzcvy.API)) {
                com_google_android_gms_internal_zzcwc = (zzcwc) this.zzfka.get(zzcvy.API);
            }
            return new zzr(this.zzdzb, this.zzfju, this.zzfjz, this.zzfjw, this.zzfjx, this.zzdyu, this.zzfjy, com_google_android_gms_internal_zzcwc);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zzfjt) {
            String concat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient googleApiClient : zzfjt) {
                int i2 = i + 1;
                printWriter.append(str).append("GoogleApiClient#").println(i);
                googleApiClient.dump(concat, fileDescriptor, printWriter, strArr);
                i = i2;
            }
        }
    }

    public static Set<GoogleApiClient> zzage() {
        Set<GoogleApiClient> set;
        synchronized (zzfjt) {
            set = zzfjt;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    public <C extends zze> C zza(@NonNull zzc<C> com_google_android_gms_common_api_Api_zzc_C) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzdi com_google_android_gms_common_api_internal_zzdi) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzcx com_google_android_gms_common_api_internal_zzcx) {
        throw new UnsupportedOperationException();
    }

    public void zzagf() {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzdi com_google_android_gms_common_api_internal_zzdi) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <L> zzcl<L> zzs(@NonNull L l) {
        throw new UnsupportedOperationException();
    }
}
