package com.appeaser.sublimepickerlibrary.timepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;

public final class PickerDrawingSpace extends View {
    public PickerDrawingSpace(Context context) {
        this(context, null);
    }

    public PickerDrawingSpace(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PickerDrawingSpace(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public PickerDrawingSpace(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static int getDefaultSizeNonGreedy(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case Integer.MIN_VALUE:
                return Math.min(size, specSize);
            case 0:
                return size;
            case Ints.MAX_POWER_OF_TWO /*1073741824*/:
                return specSize;
            default:
                return result;
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSizeNonGreedy(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSizeNonGreedy(getSuggestedMinimumHeight(), heightMeasureSpec));
    }
}
