package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzg;

public final class zzbdg extends GoogleApi<NoOptions> implements zzbdb {
    private zzbdg(@NonNull Context context) {
        super(context, zzbcv.API, null, new zzg());
    }

    public static zzbdb zzby(@NonNull Context context) {
        return new zzbdg(context);
    }

    public final PendingResult<Status> zza(zzbde com_google_android_gms_internal_zzbde) {
        return zzc(new zzbdj(com_google_android_gms_internal_zzbde, zzagb()));
    }
}
