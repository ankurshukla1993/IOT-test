package com.facebook.react.uimanager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import javax.annotation.Nullable;

public class SizeMonitoringFrameLayout extends FrameLayout {
    @Nullable
    private OnSizeChangedListener mOnSizeChangedListener;

    public SizeMonitoringFrameLayout(Context context) {
        super(context);
    }

    public SizeMonitoringFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SizeMonitoringFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnSizeChangedListener(OnSizeChangedListener onSizeChangedListener) {
        this.mOnSizeChangedListener = onSizeChangedListener;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mOnSizeChangedListener != null) {
            this.mOnSizeChangedListener.onSizeChanged(w, h, oldw, oldh);
        }
    }
}
