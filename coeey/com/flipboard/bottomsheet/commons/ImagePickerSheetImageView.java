package com.flipboard.bottomsheet.commons;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

final class ImagePickerSheetImageView extends ImageView {
    public ImagePickerSheetImageView(Context context) {
        super(context);
    }

    public ImagePickerSheetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagePickerSheetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ImagePickerSheetImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int bothDimensionsSpec = widthMeasureSpec;
        super.onMeasure(bothDimensionsSpec, bothDimensionsSpec);
    }
}
