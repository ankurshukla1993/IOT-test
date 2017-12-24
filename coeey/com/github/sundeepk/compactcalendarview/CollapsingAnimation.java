package com.github.sundeepk.compactcalendarview;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.google.android.flexbox.FlexItem;

class CollapsingAnimation extends Animation {
    private CompactCalendarController compactCalendarController;
    private final boolean down;
    private int targetGrowRadius;
    private final int targetHeight;
    private final CompactCalendarView view;

    public CollapsingAnimation(CompactCalendarView view, CompactCalendarController compactCalendarController, int targetHeight, int targetGrowRadius, boolean down) {
        this.view = view;
        this.compactCalendarController = compactCalendarController;
        this.targetHeight = targetHeight;
        this.targetGrowRadius = targetGrowRadius;
        this.down = down;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        float grow;
        if (this.down) {
            newHeight = (int) (((float) this.targetHeight) * interpolatedTime);
            grow = interpolatedTime * ((float) (this.targetGrowRadius * 2));
        } else {
            float progress = FlexItem.FLEX_SHRINK_DEFAULT - interpolatedTime;
            newHeight = (int) (((float) this.targetHeight) * progress);
            grow = progress * ((float) (this.targetGrowRadius * 2));
        }
        this.compactCalendarController.setGrowProgress(grow);
        this.view.getLayoutParams().height = newHeight;
        this.view.requestLayout();
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    public boolean willChangeBounds() {
        return true;
    }
}
