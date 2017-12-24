package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

public final class zzdwy implements AuthResult {
    private zzdxb zzmct;
    private zzdwx zzmcu = null;

    public zzdwy(@NonNull zzdxb com_google_android_gms_internal_zzdxb) {
        this.zzmct = (zzdxb) zzbq.checkNotNull(com_google_android_gms_internal_zzdxb);
        List zzbqi = this.zzmct.zzbqi();
        for (int i = 0; i < zzbqi.size(); i++) {
            if (!TextUtils.isEmpty(((zzdwz) zzbqi.get(i)).getRawUserInfo())) {
                this.zzmcu = new zzdwx(((zzdwz) zzbqi.get(i)).getProviderId(), ((zzdwz) zzbqi.get(i)).getRawUserInfo(), com_google_android_gms_internal_zzdxb.isNewUser());
            }
        }
        if (this.zzmcu == null) {
            this.zzmcu = new zzdwx(com_google_android_gms_internal_zzdxb.isNewUser());
        }
    }

    @Nullable
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzmcu;
    }

    @Nullable
    public final FirebaseUser getUser() {
        return this.zzmct;
    }
}
