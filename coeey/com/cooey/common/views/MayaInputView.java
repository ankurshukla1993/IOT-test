package com.cooey.common.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.cooey.common.C0842R;
import com.cooey.common.databinding.LayoutMayaInputBinding;

public class MayaInputView extends FrameLayout {
    private LayoutMayaInputBinding layoutMayaInputBinding;
    private FrameLayout mContentView;

    public MayaInputView(Context context) {
        this(context, null);
    }

    public MayaInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MayaInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.layoutMayaInputBinding = (LayoutMayaInputBinding) DataBindingUtil.inflate(LayoutInflater.from(context), C0842R.layout.layout_maya_input, this, false);
        this.mContentView = this.layoutMayaInputBinding.content;
    }

    public void addView(View child, int index, LayoutParams params) {
        if (this.mContentView == null) {
            super.addView(child, index, params);
        } else {
            this.mContentView.addView(child, index, params);
        }
    }
}
