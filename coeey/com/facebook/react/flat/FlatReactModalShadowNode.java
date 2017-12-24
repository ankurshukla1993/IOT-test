package com.facebook.react.flat;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;

class FlatReactModalShadowNode extends FlatShadowNode implements AndroidView {
    private final Point mMaxPoint = new Point();
    private final Point mMinPoint = new Point();
    private boolean mPaddingChanged;

    FlatReactModalShadowNode() {
        forceMountToView();
        forceMountChildrenToView();
    }

    @TargetApi(16)
    public void addChildAt(ReactShadowNode child, int i) {
        int width;
        int height;
        super.addChildAt(child, i);
        Display display = ((WindowManager) getThemedContext().getSystemService("window")).getDefaultDisplay();
        display.getCurrentSizeRange(this.mMinPoint, this.mMaxPoint);
        int rotation = display.getRotation();
        if (rotation == 0 || rotation == 2) {
            width = this.mMinPoint.x;
            height = this.mMaxPoint.y;
        } else {
            width = this.mMaxPoint.x;
            height = this.mMinPoint.y;
        }
        child.setStyleWidth((float) width);
        child.setStyleHeight((float) height);
    }

    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public boolean isPaddingChanged() {
        return this.mPaddingChanged;
    }

    public void resetPaddingChanged() {
        this.mPaddingChanged = false;
    }

    public void setPadding(int spacingType, float padding) {
        YogaValue current = getStylePadding(spacingType);
        if (current.unit != YogaUnit.PIXEL || current.value != padding) {
            super.setPadding(spacingType, padding);
            this.mPaddingChanged = true;
            markUpdated();
        }
    }

    public void setPaddingPercent(int spacingType, float percent) {
        YogaValue current = getStylePadding(spacingType);
        if (current.unit != YogaUnit.PERCENT || current.value != percent) {
            super.setPadding(spacingType, percent);
            this.mPaddingChanged = true;
            markUpdated();
        }
    }
}
