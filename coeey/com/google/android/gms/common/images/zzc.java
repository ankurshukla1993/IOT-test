package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbec;
import com.google.android.gms.internal.zzbeh;
import java.lang.ref.WeakReference;

public final class zzc extends zza {
    private WeakReference<ImageView> zzfus;

    public zzc(ImageView imageView, int i) {
        super(null, i);
        com.google.android.gms.common.internal.zzc.zzu(imageView);
        this.zzfus = new WeakReference(imageView);
    }

    public zzc(ImageView imageView, Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzc.zzu(imageView);
        this.zzfus = new WeakReference(imageView);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImageView imageView = (ImageView) this.zzfus.get();
        ImageView imageView2 = (ImageView) ((zzc) obj).zzfus.get();
        return (imageView2 == null || imageView == null || !zzbg.equal(imageView2, imageView)) ? false : true;
    }

    public final int hashCode() {
        return 0;
    }

    protected final void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
        Uri uri = null;
        ImageView imageView = (ImageView) this.zzfus.get();
        if (imageView != null) {
            Drawable drawable2;
            Object obj = (z2 || z3) ? null : 1;
            if (obj != null && (imageView instanceof zzbeh)) {
                int zzaju = zzbeh.zzaju();
                if (this.zzfun != 0 && zzaju == this.zzfun) {
                    return;
                }
            }
            boolean zzc = zzc(z, z2);
            if (zzc) {
                drawable2 = imageView.getDrawable();
                if (drawable2 == null) {
                    drawable2 = null;
                } else if (drawable2 instanceof zzbec) {
                    drawable2 = ((zzbec) drawable2).zzajs();
                }
                drawable2 = new zzbec(drawable2, drawable);
            } else {
                drawable2 = drawable;
            }
            imageView.setImageDrawable(drawable2);
            if (imageView instanceof zzbeh) {
                if (z3) {
                    uri = this.zzful.uri;
                }
                zzbeh.zzn(uri);
                zzbeh.zzcd(obj != null ? this.zzfun : 0);
            }
            if (zzc) {
                ((zzbec) drawable2).startTransition(Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        }
    }
}
