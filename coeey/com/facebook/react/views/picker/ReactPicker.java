package com.facebook.react.views.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;
import com.facebook.react.common.annotations.VisibleForTesting;
import javax.annotation.Nullable;

public class ReactPicker extends Spinner {
    private int mMode = 0;
    @Nullable
    private OnSelectListener mOnSelectListener;
    @Nullable
    private Integer mPrimaryColor;
    @Nullable
    private Integer mStagedSelection;
    private boolean mSuppressNextEvent;
    private final Runnable measureAndLayout = new 1(this);

    public ReactPicker(Context context) {
        super(context);
    }

    public ReactPicker(Context context, int mode) {
        super(context, mode);
        this.mMode = mode;
    }

    public ReactPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ReactPicker(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle, mode);
        this.mMode = mode;
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public void setOnSelectListener(@Nullable OnSelectListener onSelectListener) {
        if (getOnItemSelectedListener() == null) {
            this.mSuppressNextEvent = true;
            setOnItemSelectedListener(new 2(this));
        }
        this.mOnSelectListener = onSelectListener;
    }

    @Nullable
    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    public void setStagedSelection(int selection) {
        this.mStagedSelection = Integer.valueOf(selection);
    }

    public void updateStagedSelection() {
        if (this.mStagedSelection != null) {
            setSelectionWithSuppressEvent(this.mStagedSelection.intValue());
            this.mStagedSelection = null;
        }
    }

    private void setSelectionWithSuppressEvent(int position) {
        if (position != getSelectedItemPosition()) {
            this.mSuppressNextEvent = true;
            setSelection(position);
        }
    }

    @Nullable
    public Integer getPrimaryColor() {
        return this.mPrimaryColor;
    }

    public void setPrimaryColor(@Nullable Integer primaryColor) {
        this.mPrimaryColor = primaryColor;
    }

    @VisibleForTesting
    public int getMode() {
        return this.mMode;
    }
}
