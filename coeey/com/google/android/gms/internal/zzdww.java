package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.firebase.auth.ActionCodeResult;

public final class zzdww implements ActionCodeResult {
    private final String zzedt;
    private final int zziie;
    private final String zzmcr;

    public zzdww(@NonNull zzdwm com_google_android_gms_internal_zzdwm) {
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzdwm.zzbqe())) {
            this.zzedt = com_google_android_gms_internal_zzdwm.getEmail();
        } else {
            this.zzedt = com_google_android_gms_internal_zzdwm.zzbqe();
        }
        this.zzmcr = com_google_android_gms_internal_zzdwm.getEmail();
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzdwm.zzbqf())) {
            this.zziie = 3;
        } else if (com_google_android_gms_internal_zzdwm.zzbqf().equals("PASSWORD_RESET")) {
            this.zziie = 0;
        } else if (com_google_android_gms_internal_zzdwm.zzbqf().equals("VERIFY_EMAIL")) {
            this.zziie = 1;
        } else if (com_google_android_gms_internal_zzdwm.zzbqf().equals("RECOVER_EMAIL")) {
            this.zziie = 2;
        } else {
            this.zziie = 3;
        }
    }

    @Nullable
    public final String getData(int i) {
        switch (i) {
            case 0:
                return this.zzedt;
            case 1:
                return this.zzmcr;
            default:
                return null;
        }
    }

    public final int getOperation() {
        return this.zziie;
    }
}
