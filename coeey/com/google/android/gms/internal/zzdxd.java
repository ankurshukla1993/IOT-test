package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.ProviderQueryResult;
import java.util.List;

public final class zzdxd implements ProviderQueryResult {
    private List<String> zzmda;

    public zzdxd(@NonNull zzdwc com_google_android_gms_internal_zzdwc) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzdwc);
        this.zzmda = com_google_android_gms_internal_zzdwc.getAllProviders();
    }

    @Nullable
    public final List<String> getProviders() {
        return this.zzmda;
    }
}
