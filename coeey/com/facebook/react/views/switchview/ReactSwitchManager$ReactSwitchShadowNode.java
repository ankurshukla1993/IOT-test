package com.facebook.react.views.switchview;

import android.view.View.MeasureSpec;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNodeAPI;

class ReactSwitchManager$ReactSwitchShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
    private int mHeight;
    private boolean mMeasured;
    private int mWidth;

    private ReactSwitchManager$ReactSwitchShadowNode() {
        setMeasureFunction(this);
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        if (!this.mMeasured) {
            ReactSwitch reactSwitch = new ReactSwitch(getThemedContext());
            int spec = MeasureSpec.makeMeasureSpec(-2, 0);
            reactSwitch.measure(spec, spec);
            this.mWidth = reactSwitch.getMeasuredWidth();
            this.mHeight = reactSwitch.getMeasuredHeight();
            this.mMeasured = true;
        }
        return YogaMeasureOutput.make(this.mWidth, this.mHeight);
    }
}
