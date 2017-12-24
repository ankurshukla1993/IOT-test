package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.Button;
import com.facebook.react.uimanager.ViewDefaults;
import com.google.android.gms.C0658R;
import com.google.android.gms.common.util.zzi;

public final class zzby extends Button {
    public zzby(Context context) {
        this(context, null);
    }

    private zzby(Context context, AttributeSet attributeSet) {
        super(context, null, 16842824);
    }

    private static int zzf(int i, int i2, int i3, int i4) {
        switch (i) {
            case 0:
                return i2;
            case 1:
                return i3;
            case 2:
                return i4;
            default:
                throw new IllegalStateException("Unknown color scheme: " + i);
        }
    }

    public final void zza(Resources resources, int i, int i2) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(ViewDefaults.FONT_SIZE_SP);
        float f = resources.getDisplayMetrics().density;
        setMinHeight((int) ((f * 48.0f) + 0.5f));
        setMinWidth((int) ((f * 48.0f) + 0.5f));
        int zzf = zzf(i2, C0658R.drawable.common_google_signin_btn_icon_dark, C0658R.drawable.common_google_signin_btn_icon_light, C0658R.drawable.common_google_signin_btn_icon_light);
        int zzf2 = zzf(i2, C0658R.drawable.common_google_signin_btn_text_dark, C0658R.drawable.common_google_signin_btn_text_light, C0658R.drawable.common_google_signin_btn_text_light);
        switch (i) {
            case 0:
            case 1:
                break;
            case 2:
                zzf2 = zzf;
                break;
            default:
                throw new IllegalStateException("Unknown button size: " + i);
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zzf2));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(C0658R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        setTextColor((ColorStateList) zzbq.checkNotNull(resources.getColorStateList(zzf(i2, C0658R.color.common_google_signin_btn_text_dark, C0658R.color.common_google_signin_btn_text_light, C0658R.color.common_google_signin_btn_text_light))));
        switch (i) {
            case 0:
                setText(resources.getString(C0658R.string.common_signin_button_text));
                break;
            case 1:
                setText(resources.getString(C0658R.string.common_signin_button_text_long));
                break;
            case 2:
                setText(null);
                break;
            default:
                throw new IllegalStateException("Unknown button size: " + i);
        }
        setTransformationMethod(null);
        if (zzi.zzcp(getContext())) {
            setGravity(19);
        }
    }
}
