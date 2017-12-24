package com.flipboard.bottomsheet.commons;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;

class Util {

    @TargetApi(21)
    static class ShadowOutline extends ViewOutlineProvider {
        int height;
        int width;

        ShadowOutline(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, this.width, this.height);
        }
    }

    static int dp2px(Context context, float dp) {
        return Math.round(TypedValue.applyDimension(1, dp, context.getResources().getDisplayMetrics()));
    }

    private Util() {
        throw new AssertionError("No Instances");
    }
}
