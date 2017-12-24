package com.google.android.gms.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class zzdxb extends FirebaseUser {
    private boolean zzmca;
    private zzdwg zzmcv;
    private zzdwz zzmcw;
    private String zzmcx;
    private String zzmcy = "com.google.firebase.auth.internal.DefaultFirebaseUser";
    private List<zzdwz> zzmcz;
    private List<String> zzmda;
    private Map<String, zzdwz> zzmdb;
    private String zzmdc = "2";
    private boolean zzmdd;
    private FirebaseUserMetadata zzmde;

    public zzdxb(@NonNull FirebaseApp firebaseApp, @NonNull List<? extends UserInfo> list) {
        zzbq.checkNotNull(firebaseApp);
        this.zzmcx = firebaseApp.getName();
        zzap(list);
    }

    @Nullable
    public String getDisplayName() {
        return this.zzmcw.getDisplayName();
    }

    @Nullable
    public String getEmail() {
        return this.zzmcw.getEmail();
    }

    public FirebaseUserMetadata getMetadata() {
        return this.zzmde;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.zzmcw.getPhoneNumber();
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.zzmcw.getPhotoUrl();
    }

    @NonNull
    public List<? extends UserInfo> getProviderData() {
        return this.zzmcz;
    }

    @NonNull
    public String getProviderId() {
        return this.zzmcw.getProviderId();
    }

    @Nullable
    public final List<String> getProviders() {
        return this.zzmda;
    }

    @NonNull
    public String getUid() {
        return this.zzmcw.getUid();
    }

    public boolean isAnonymous() {
        return this.zzmdd;
    }

    public boolean isEmailVerified() {
        return this.zzmcw.isEmailVerified();
    }

    public final boolean isNewUser() {
        return this.zzmca;
    }

    public final void zza(@NonNull zzdwg com_google_android_gms_internal_zzdwg) {
        this.zzmcv = (zzdwg) zzbq.checkNotNull(com_google_android_gms_internal_zzdwg);
    }

    public final void zza(FirebaseUserMetadata firebaseUserMetadata) {
        this.zzmde = firebaseUserMetadata;
    }

    @NonNull
    public final FirebaseUser zzap(@NonNull List<? extends UserInfo> list) {
        zzbq.checkNotNull(list);
        this.zzmcz = new ArrayList(list.size());
        this.zzmda = new ArrayList(list.size());
        this.zzmdb = new ArrayMap();
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = (UserInfo) list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzmcw = (zzdwz) userInfo;
            } else {
                this.zzmda.add(userInfo.getProviderId());
            }
            this.zzmcz.add((zzdwz) userInfo);
            this.zzmdb.put(userInfo.getProviderId(), (zzdwz) userInfo);
        }
        if (this.zzmcw == null) {
            this.zzmcw = (zzdwz) this.zzmcz.get(0);
        }
        return this;
    }

    @NonNull
    public final FirebaseApp zzbpm() {
        return FirebaseApp.getInstance(this.zzmcx);
    }

    @NonNull
    public final zzdwg zzbpn() {
        return this.zzmcv;
    }

    @NonNull
    public final String zzbpo() {
        return this.zzmcv.zzaat();
    }

    @NonNull
    public final String zzbpp() {
        return zzbpn().getAccessToken();
    }

    public final List<zzdwz> zzbqi() {
        return this.zzmcz;
    }

    public final /* synthetic */ FirebaseUser zzcc(boolean z) {
        this.zzmdd = z;
        return this;
    }

    public final void zzcf(boolean z) {
        this.zzmca = z;
    }

    public final zzdxb zzom(@NonNull String str) {
        this.zzmdc = str;
        return this;
    }
}
