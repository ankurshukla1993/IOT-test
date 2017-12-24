package com.facebook.react.views.progressbar;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import javax.annotation.Nullable;

class ProgressBarContainerView extends FrameLayout {
    private static final int MAX_PROGRESS = 1000;
    private boolean mAnimating = true;
    @Nullable
    private Integer mColor;
    private boolean mIndeterminate = true;
    private double mProgress;
    @Nullable
    private ProgressBar mProgressBar;

    public ProgressBarContainerView(Context context) {
        super(context);
    }

    public void setStyle(@Nullable String styleName) {
        this.mProgressBar = ReactProgressBarViewManager.createProgressBar(getContext(), ReactProgressBarViewManager.getStyleFromString(styleName));
        this.mProgressBar.setMax(1000);
        removeAllViews();
        addView(this.mProgressBar, new LayoutParams(-1, -1));
    }

    public void setColor(@Nullable Integer color) {
        this.mColor = color;
    }

    public void setIndeterminate(boolean indeterminate) {
        this.mIndeterminate = indeterminate;
    }

    public void setProgress(double progress) {
        this.mProgress = progress;
    }

    public void setAnimating(boolean animating) {
        this.mAnimating = animating;
    }

    public void apply() {
        if (this.mProgressBar == null) {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }
        this.mProgressBar.setIndeterminate(this.mIndeterminate);
        setColor(this.mProgressBar);
        this.mProgressBar.setProgress((int) (this.mProgress * 1000.0d));
        if (this.mAnimating) {
            this.mProgressBar.setVisibility(0);
        } else {
            this.mProgressBar.setVisibility(8);
        }
    }

    private void setColor(ProgressBar progressBar) {
        Drawable drawable;
        if (progressBar.isIndeterminate()) {
            drawable = progressBar.getIndeterminateDrawable();
        } else {
            drawable = progressBar.getProgressDrawable();
        }
        if (drawable != null) {
            if (this.mColor != null) {
                drawable.setColorFilter(this.mColor.intValue(), Mode.SRC_IN);
            } else {
                drawable.clearColorFilter();
            }
        }
    }
}
