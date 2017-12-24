package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzdb;

final class zzdtt<O extends ApiOptions> extends GoogleApi<O> {
    public zzdtt(@NonNull Context context, Api<O> api, O o, zzdb com_google_android_gms_common_api_internal_zzdb) {
        super(context, api, o, com_google_android_gms_common_api_internal_zzdb);
    }
}
