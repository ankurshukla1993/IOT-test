package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region.Op;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class ARTGroupShadowNode extends ARTVirtualNode {
    @Nullable
    protected RectF mClipping;

    @ReactProp(name = "clipping")
    public void setClipping(@Nullable ReadableArray clippingDims) {
        float[] clippingData = PropHelper.toFloatArray(clippingDims);
        if (clippingData != null) {
            this.mClipping = createClipping(clippingData);
            markUpdated();
        }
    }

    public boolean isVirtual() {
        return true;
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        opacity *= this.mOpacity;
        if (opacity > 0.01f) {
            saveAndSetupCanvas(canvas);
            if (this.mClipping != null) {
                Canvas canvas2 = canvas;
                canvas2.clipRect(this.mScale * this.mClipping.left, this.mScale * this.mClipping.top, this.mScale * this.mClipping.right, this.mScale * this.mClipping.bottom, Op.REPLACE);
            }
            for (int i = 0; i < getChildCount(); i++) {
                ARTVirtualNode child = (ARTVirtualNode) getChildAt(i);
                child.draw(canvas, paint, opacity);
                child.markUpdateSeen();
            }
            restoreCanvas(canvas);
        }
    }

    private static RectF createClipping(float[] data) {
        if (data.length == 4) {
            return new RectF(data[0], data[1], data[0] + data[2], data[1] + data[3]);
        }
        throw new JSApplicationIllegalArgumentException("Clipping should be array of length 4 (e.g. [x, y, width, height])");
    }
}
