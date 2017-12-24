package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R$string;

public final class zzca {
    private final Resources zzfyz;
    private final String zzfza = this.zzfyz.getResourcePackageName(R$string.common_google_play_services_unknown_issue);

    public zzca(Context context) {
        zzbq.checkNotNull(context);
        this.zzfyz = context.getResources();
    }

    public final String getString(String str) {
        int identifier = this.zzfyz.getIdentifier(str, "string", this.zzfza);
        return identifier == 0 ? null : this.zzfyz.getString(identifier);
    }
}
