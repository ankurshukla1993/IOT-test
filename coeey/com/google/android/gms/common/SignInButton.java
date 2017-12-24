package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.C0658R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbx;
import com.google.android.gms.common.internal.zzby;
import com.google.android.gms.dynamic.zzq;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;
    private View zzfiw;
    private OnClickListener zzfix;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzfix = null;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C0658R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(C0658R.styleable.SignInButton_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(C0658R.styleable.SignInButton_colorScheme, 2);
            setStyle(this.mSize, this.mColor);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public final void onClick(View view) {
        if (this.zzfix != null && view == this.zzfiw) {
            this.zzfix.onClick(this);
        }
    }

    public final void setColorScheme(int i) {
        setStyle(this.mSize, i);
    }

    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.zzfiw.setEnabled(z);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.zzfix = onClickListener;
        if (this.zzfiw != null) {
            this.zzfiw.setOnClickListener(this);
        }
    }

    @Deprecated
    public final void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor);
    }

    public final void setSize(int i) {
        setStyle(i, this.mColor);
    }

    public final void setStyle(int i, int i2) {
        this.mSize = i;
        this.mColor = i2;
        Context context = getContext();
        if (this.zzfiw != null) {
            removeView(this.zzfiw);
        }
        try {
            this.zzfiw = zzbx.zzc(context, this.mSize, this.mColor);
        } catch (zzq e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int i3 = this.mSize;
            int i4 = this.mColor;
            View com_google_android_gms_common_internal_zzby = new zzby(context);
            com_google_android_gms_common_internal_zzby.zza(context.getResources(), i3, i4);
            this.zzfiw = com_google_android_gms_common_internal_zzby;
        }
        addView(this.zzfiw);
        this.zzfiw.setEnabled(isEnabled());
        this.zzfiw.setOnClickListener(this);
    }

    @Deprecated
    public final void setStyle(int i, int i2, Scope[] scopeArr) {
        setStyle(i, i2);
    }
}
