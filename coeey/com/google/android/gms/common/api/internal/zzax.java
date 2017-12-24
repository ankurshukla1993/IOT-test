package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api.zze;
import java.util.ArrayList;

final class zzax extends zzbb {
    private /* synthetic */ zzar zzfor;
    private final ArrayList<zze> zzfox;

    public zzax(zzar com_google_android_gms_common_api_internal_zzar, ArrayList<zze> arrayList) {
        this.zzfor = com_google_android_gms_common_api_internal_zzar;
        super(com_google_android_gms_common_api_internal_zzar);
        this.zzfox = arrayList;
    }

    @WorkerThread
    public final void zzahp() {
        this.zzfor.zzfob.zzfmo.zzfpi = this.zzfor.zzahv();
        ArrayList arrayList = this.zzfox;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((zze) obj).zza(this.zzfor.zzfon, this.zzfor.zzfob.zzfmo.zzfpi);
        }
    }
}
