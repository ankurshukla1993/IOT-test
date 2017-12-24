package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.android.flexbox.FlexItem;

public final class zzbec extends Drawable implements Callback {
    private int mAlpha;
    private int mFrom;
    private long zzdss;
    private boolean zzfup;
    private int zzfuu;
    private int zzfuv;
    private int zzfuw;
    private int zzfux;
    private boolean zzfuy;
    private zzbeg zzfuz;
    private Drawable zzfva;
    private Drawable zzfvb;
    private boolean zzfvc;
    private boolean zzfvd;
    private boolean zzfve;
    private int zzfvf;

    public zzbec(Drawable drawable, Drawable drawable2) {
        this(null);
        if (drawable == null) {
            drawable = zzbee.zzfvg;
        }
        this.zzfva = drawable;
        drawable.setCallback(this);
        zzbeg com_google_android_gms_internal_zzbeg = this.zzfuz;
        com_google_android_gms_internal_zzbeg.zzfvi |= drawable.getChangingConfigurations();
        if (drawable2 == null) {
            drawable2 = zzbee.zzfvg;
        }
        this.zzfvb = drawable2;
        drawable2.setCallback(this);
        com_google_android_gms_internal_zzbeg = this.zzfuz;
        com_google_android_gms_internal_zzbeg.zzfvi |= drawable2.getChangingConfigurations();
    }

    zzbec(zzbeg com_google_android_gms_internal_zzbeg) {
        this.zzfuu = 0;
        this.zzfuw = 255;
        this.mAlpha = 0;
        this.zzfup = true;
        this.zzfuz = new zzbeg(com_google_android_gms_internal_zzbeg);
    }

    private final boolean canConstantState() {
        if (!this.zzfvc) {
            boolean z = (this.zzfva.getConstantState() == null || this.zzfvb.getConstantState() == null) ? false : true;
            this.zzfvd = z;
            this.zzfvc = true;
        }
        return this.zzfvd;
    }

    public final void draw(Canvas canvas) {
        int i = 1;
        int i2 = 0;
        switch (this.zzfuu) {
            case 1:
                this.zzdss = SystemClock.uptimeMillis();
                this.zzfuu = 2;
                break;
            case 2:
                if (this.zzdss >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzdss)) / ((float) this.zzfux);
                    if (uptimeMillis < FlexItem.FLEX_SHRINK_DEFAULT) {
                        i = 0;
                    }
                    if (i != 0) {
                        this.zzfuu = 0;
                    }
                    this.mAlpha = (int) ((Math.min(uptimeMillis, FlexItem.FLEX_SHRINK_DEFAULT) * ((float) this.zzfuv)) + 0.0f);
                    break;
                }
                break;
        }
        i2 = i;
        i = this.mAlpha;
        boolean z = this.zzfup;
        Drawable drawable = this.zzfva;
        Drawable drawable2 = this.zzfvb;
        if (i2 != 0) {
            if (!z || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zzfuw) {
                drawable2.setAlpha(this.zzfuw);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z) {
            drawable.setAlpha(this.zzfuw - i);
        }
        drawable.draw(canvas);
        if (z) {
            drawable.setAlpha(this.zzfuw);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzfuw);
        }
        invalidateSelf();
    }

    public final int getChangingConfigurations() {
        return (super.getChangingConfigurations() | this.zzfuz.mChangingConfigurations) | this.zzfuz.zzfvi;
    }

    public final ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzfuz.mChangingConfigurations = getChangingConfigurations();
        return this.zzfuz;
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zzfva.getIntrinsicHeight(), this.zzfvb.getIntrinsicHeight());
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zzfva.getIntrinsicWidth(), this.zzfvb.getIntrinsicWidth());
    }

    public final int getOpacity() {
        if (!this.zzfve) {
            this.zzfvf = Drawable.resolveOpacity(this.zzfva.getOpacity(), this.zzfvb.getOpacity());
            this.zzfve = true;
        }
        return this.zzfvf;
    }

    public final void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final Drawable mutate() {
        if (!this.zzfuy && super.mutate() == this) {
            if (canConstantState()) {
                this.zzfva.mutate();
                this.zzfvb.mutate();
                this.zzfuy = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    protected final void onBoundsChange(Rect rect) {
        this.zzfva.setBounds(rect);
        this.zzfvb.setBounds(rect);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public final void setAlpha(int i) {
        if (this.mAlpha == this.zzfuw) {
            this.mAlpha = i;
        }
        this.zzfuw = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.zzfva.setColorFilter(colorFilter);
        this.zzfvb.setColorFilter(colorFilter);
    }

    public final void startTransition(int i) {
        this.mFrom = 0;
        this.zzfuv = this.zzfuw;
        this.mAlpha = 0;
        this.zzfux = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.zzfuu = 1;
        invalidateSelf();
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zzajs() {
        return this.zzfvb;
    }
}
