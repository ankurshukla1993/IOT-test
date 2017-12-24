package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbei;

public abstract class zza {
    final zzb zzful;
    private int zzfum = 0;
    protected int zzfun = 0;
    private boolean zzfuo = false;
    private boolean zzfup = true;
    private boolean zzfuq = false;
    private boolean zzfur = true;

    public zza(Uri uri, int i) {
        this.zzful = new zzb(uri);
        this.zzfun = i;
    }

    final void zza(Context context, Bitmap bitmap, boolean z) {
        zzc.zzu(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    final void zza(Context context, zzbei com_google_android_gms_internal_zzbei) {
        if (this.zzfur) {
            zza(null, false, true, false);
        }
    }

    final void zza(Context context, zzbei com_google_android_gms_internal_zzbei, boolean z) {
        Drawable drawable = null;
        if (this.zzfun != 0) {
            drawable = context.getResources().getDrawable(this.zzfun);
        }
        zza(drawable, z, false, false);
    }

    protected abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    protected final boolean zzc(boolean z, boolean z2) {
        return (!this.zzfup || z2 || z) ? false : true;
    }
}
