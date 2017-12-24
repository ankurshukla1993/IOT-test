package com.appeaser.sublimepickerlibrary.datepicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ViewAnimator;
import com.google.common.primitives.Ints;
import java.util.ArrayList;

public class PickerViewAnimator extends ViewAnimator {
    private final ArrayList<View> mMatchParentChildren = new ArrayList(1);

    public PickerViewAnimator(Context context) {
        super(context);
    }

    public PickerViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        boolean measureMatchParentChildren = (MeasureSpec.getMode(widthMeasureSpec) == Ints.MAX_POWER_OF_TWO && MeasureSpec.getMode(heightMeasureSpec) == Ints.MAX_POWER_OF_TWO) ? false : true;
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int count = getChildCount();
        for (i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (getMeasureAllChildren() || child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                boolean matchWidth = lp.width == -1;
                boolean matchHeight = lp.height == -1;
                if (measureMatchParentChildren && (matchWidth || matchHeight)) {
                    this.mMatchParentChildren.add(child);
                }
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                int state = 0;
                if (measureMatchParentChildren && !matchWidth) {
                    maxWidth = Math.max(maxWidth, (child.getMeasuredWidth() + lp.leftMargin) + lp.rightMargin);
                    state = 0 | (child.getMeasuredWidthAndState() & ViewCompat.MEASURED_STATE_MASK);
                }
                if (measureMatchParentChildren && !matchHeight) {
                    maxHeight = Math.max(maxHeight, (child.getMeasuredHeight() + lp.topMargin) + lp.bottomMargin);
                    state |= (child.getMeasuredHeightAndState() >> 16) & InputDeviceCompat.SOURCE_ANY;
                }
                childState = combineMeasuredStates(childState, state);
            }
        }
        maxWidth += getPaddingLeft() + getPaddingRight();
        maxHeight = Math.max(maxHeight + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        Drawable drawable = getForeground();
        if (drawable != null) {
            maxHeight = Math.max(maxHeight, drawable.getMinimumHeight());
            maxWidth = Math.max(maxWidth, drawable.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState), resolveSizeAndState(maxHeight, heightMeasureSpec, childState << 16));
        int matchCount = this.mMatchParentChildren.size();
        for (i = 0; i < matchCount; i++) {
            int childWidthMeasureSpec;
            int childHeightMeasureSpec;
            child = (View) this.mMatchParentChildren.get(i);
            MarginLayoutParams lp2 = (MarginLayoutParams) child.getLayoutParams();
            if (lp2.width == -1) {
                childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - lp2.leftMargin) - lp2.rightMargin, Ints.MAX_POWER_OF_TWO);
            } else {
                childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, ((getPaddingLeft() + getPaddingRight()) + lp2.leftMargin) + lp2.rightMargin, lp2.width);
            }
            if (lp2.height == -1) {
                childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - lp2.topMargin) - lp2.bottomMargin, Ints.MAX_POWER_OF_TWO);
            } else {
                childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, ((getPaddingTop() + getPaddingBottom()) + lp2.topMargin) + lp2.bottomMargin, lp2.height);
            }
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
        this.mMatchParentChildren.clear();
    }
}
