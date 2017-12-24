package com.db.chart.tooltip;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.db.chart.listener.OnTooltipEventListener;
import java.text.DecimalFormat;

public class Tooltip extends RelativeLayout {
    private int mBottomMargin;
    private ObjectAnimator mEnterAnimator;
    private ObjectAnimator mExitAnimator;
    private int mHeight;
    private Alignment mHorizontalAlignment = Alignment.CENTER;
    private int mLeftMargin;
    private boolean mOn;
    private int mRightMargin;
    private OnTooltipEventListener mTooltipEventListener;
    private TextView mTooltipValue;
    private int mTopMargin;
    private DecimalFormat mValueFormat;
    private Alignment mVerticalAlignment = Alignment.CENTER;
    private int mWidth;

    public enum Alignment {
        BOTTOM_TOP,
        TOP_BOTTOM,
        TOP_TOP,
        CENTER,
        BOTTOM_BOTTOM,
        LEFT_LEFT,
        RIGHT_LEFT,
        RIGHT_RIGHT,
        LEFT_RIGHT
    }

    public Tooltip(Context context) {
        super(context);
        init();
    }

    public Tooltip(Context context, int layoutId) {
        super(context);
        init();
        View layoutParent = inflate(getContext(), layoutId, null);
        layoutParent.setLayoutParams(new LayoutParams(-1, -1));
        addView(layoutParent);
    }

    public Tooltip(Context context, int layoutId, int valueId) {
        super(context);
        init();
        View layoutParent = inflate(getContext(), layoutId, null);
        layoutParent.setLayoutParams(new LayoutParams(-1, -1));
        addView(layoutParent);
        this.mTooltipValue = (TextView) findViewById(valueId);
    }

    private void init() {
        this.mWidth = -1;
        this.mHeight = -1;
        this.mLeftMargin = 0;
        this.mTopMargin = 0;
        this.mRightMargin = 0;
        this.mBottomMargin = 0;
        this.mOn = false;
        this.mValueFormat = new DecimalFormat();
    }

    public void prepare(Rect rect, float value) {
        int width = this.mWidth == -1 ? rect.width() : this.mWidth;
        int height = this.mHeight == -1 ? rect.height() : this.mHeight;
        LayoutParams layoutParams = new LayoutParams(width, height);
        if (this.mHorizontalAlignment == Alignment.RIGHT_LEFT) {
            layoutParams.leftMargin = (rect.left - width) - this.mRightMargin;
        }
        if (this.mHorizontalAlignment == Alignment.LEFT_LEFT) {
            layoutParams.leftMargin = rect.left + this.mLeftMargin;
        }
        if (this.mHorizontalAlignment == Alignment.CENTER) {
            layoutParams.leftMargin = rect.centerX() - (width / 2);
        }
        if (this.mHorizontalAlignment == Alignment.RIGHT_RIGHT) {
            layoutParams.leftMargin = (rect.right - width) - this.mRightMargin;
        }
        if (this.mHorizontalAlignment == Alignment.LEFT_RIGHT) {
            layoutParams.leftMargin = rect.right + this.mLeftMargin;
        }
        if (this.mVerticalAlignment == Alignment.BOTTOM_TOP) {
            layoutParams.topMargin = (rect.top - height) - this.mBottomMargin;
        } else if (this.mVerticalAlignment == Alignment.TOP_TOP) {
            layoutParams.topMargin = rect.top + this.mTopMargin;
        } else if (this.mVerticalAlignment == Alignment.CENTER) {
            layoutParams.topMargin = rect.centerY() - (height / 2);
        } else if (this.mVerticalAlignment == Alignment.BOTTOM_BOTTOM) {
            layoutParams.topMargin = (rect.bottom - height) - this.mBottomMargin;
        } else if (this.mVerticalAlignment == Alignment.TOP_BOTTOM) {
            layoutParams.topMargin = rect.bottom + this.mTopMargin;
        }
        setLayoutParams(layoutParams);
        if (this.mTooltipValue != null) {
            this.mTooltipValue.setText(this.mValueFormat.format((double) value));
        }
    }

    public void correctPosition(int left, int top, int right, int bottom) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (layoutParams.leftMargin < left) {
            layoutParams.leftMargin = left;
        }
        if (layoutParams.topMargin < top) {
            layoutParams.topMargin = top;
        }
        if (layoutParams.leftMargin + layoutParams.width > right) {
            layoutParams.leftMargin = right - layoutParams.width;
        }
        if (layoutParams.topMargin + layoutParams.height > bottom) {
            layoutParams.topMargin = bottom - layoutParams.height;
        }
        setLayoutParams(layoutParams);
    }

    @TargetApi(11)
    public void animateEnter() {
        this.mEnterAnimator.start();
    }

    @TargetApi(11)
    public void animateExit(final Runnable endAction) {
        this.mExitAnimator.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                endAction.run();
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        this.mExitAnimator.start();
    }

    public boolean hasEnterAnimation() {
        return this.mEnterAnimator != null;
    }

    public boolean hasExitAnimation() {
        return this.mExitAnimator != null;
    }

    public boolean on() {
        return this.mOn;
    }

    public Tooltip setHorizontalAlignment(Alignment alignment) {
        this.mHorizontalAlignment = alignment;
        return this;
    }

    public Tooltip setVerticalAlignment(Alignment alignment) {
        this.mVerticalAlignment = alignment;
        return this;
    }

    public Tooltip setDimensions(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    public Tooltip setMargins(int left, int top, int right, int bottom) {
        this.mLeftMargin = left;
        this.mTopMargin = top;
        this.mRightMargin = right;
        this.mBottomMargin = bottom;
        return this;
    }

    public void setOn(boolean on) {
        this.mOn = on;
    }

    public Tooltip setValueFormat(DecimalFormat format) {
        this.mValueFormat = format;
        return this;
    }

    @TargetApi(11)
    public ObjectAnimator setEnterAnimation(PropertyValuesHolder... values) {
        for (PropertyValuesHolder value : values) {
            if (value.getPropertyName().equals("alpha")) {
                setAlpha(0.0f);
            }
            if (value.getPropertyName().equals("rotation")) {
                setRotation(0.0f);
            }
            if (value.getPropertyName().equals("rotationX")) {
                setRotationX(0.0f);
            }
            if (value.getPropertyName().equals("rotationY")) {
                setRotationY(0.0f);
            }
            if (value.getPropertyName().equals("translationX")) {
                setTranslationX(0.0f);
            }
            if (value.getPropertyName().equals("translationY")) {
                setTranslationY(0.0f);
            }
            if (value.getPropertyName().equals("scaleX")) {
                setScaleX(0.0f);
            }
            if (value.getPropertyName().equals("scaleY")) {
                setScaleY(0.0f);
            }
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, values);
        this.mEnterAnimator = ofPropertyValuesHolder;
        return ofPropertyValuesHolder;
    }

    @TargetApi(11)
    public ObjectAnimator setExitAnimation(PropertyValuesHolder... values) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, values);
        this.mExitAnimator = ofPropertyValuesHolder;
        return ofPropertyValuesHolder;
    }
}
