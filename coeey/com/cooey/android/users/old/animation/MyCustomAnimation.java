package com.cooey.android.users.old.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.flexbox.FlexItem;

public class MyCustomAnimation extends Animation {
    public static final int COLLAPSE = 1;
    public static final int EXPAND = 0;
    private int mEndHeight = this.mView.getHeight();
    private LayoutParams mLayoutParams;
    private int mType;
    private View mView;

    public MyCustomAnimation(View view, int duration, int type) {
        setDuration((long) duration);
        this.mView = view;
        this.mLayoutParams = (LayoutParams) view.getLayoutParams();
        this.mType = type;
        if (this.mType == 0) {
            this.mLayoutParams.height = 0;
        } else {
            this.mLayoutParams.height = -2;
        }
        view.setVisibility(0);
    }

    public int getHeight() {
        return this.mView.getHeight();
    }

    public void setHeight(int height) {
        this.mEndHeight = height;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < FlexItem.FLEX_SHRINK_DEFAULT) {
            if (this.mType == 0) {
                this.mLayoutParams.height = (int) (((float) this.mEndHeight) * interpolatedTime);
            } else {
                this.mLayoutParams.height = (int) (((float) this.mEndHeight) * (FlexItem.FLEX_SHRINK_DEFAULT - interpolatedTime));
            }
            this.mView.requestLayout();
        } else if (this.mType == 0) {
            this.mLayoutParams.height = -2;
            this.mView.requestLayout();
        } else {
            this.mView.setVisibility(8);
        }
    }
}
