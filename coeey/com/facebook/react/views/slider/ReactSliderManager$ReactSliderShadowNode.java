package com.facebook.react.views.slider;

import android.view.View.MeasureSpec;
import android.widget.SeekBar;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;

class ReactSliderManager$ReactSliderShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
    private int mHeight;
    private boolean mMeasured;
    private int mWidth;

    private ReactSliderManager$ReactSliderShadowNode() {
        setMeasureFunction(this);
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        if (!this.mMeasured) {
            SeekBar reactSlider = new ReactSlider(getThemedContext(), null, 16842875);
            int spec = MeasureSpec.makeMeasureSpec(-2, 0);
            reactSlider.measure(spec, spec);
            this.mWidth = reactSlider.getMeasuredWidth();
            this.mHeight = reactSlider.getMeasuredHeight();
            this.mMeasured = true;
        }
        return YogaMeasureOutput.make(this.mWidth, this.mHeight);
    }
}
