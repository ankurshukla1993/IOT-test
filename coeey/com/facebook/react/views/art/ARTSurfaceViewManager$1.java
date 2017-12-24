package com.facebook.react.views.art;

import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;

class ARTSurfaceViewManager$1 implements YogaMeasureFunction {
    ARTSurfaceViewManager$1() {
    }

    public long measure(YogaNodeAPI node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        throw new IllegalStateException("SurfaceView should have explicit width and height set");
    }
}
