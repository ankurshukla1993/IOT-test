package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public abstract class ARTVirtualNode extends ReactShadowNode {
    protected static final float MIN_OPACITY_FOR_DRAW = 0.01f;
    private static final float[] sMatrixData = new float[9];
    private static final float[] sRawMatrix = new float[9];
    @Nullable
    private Matrix mMatrix = new Matrix();
    protected float mOpacity = FlexItem.FLEX_SHRINK_DEFAULT;
    protected final float mScale = DisplayMetricsHolder.getWindowDisplayMetrics().density;

    public abstract void draw(Canvas canvas, Paint paint, float f);

    public boolean isVirtual() {
        return true;
    }

    protected final void saveAndSetupCanvas(Canvas canvas) {
        canvas.save();
        if (this.mMatrix != null) {
            canvas.concat(this.mMatrix);
        }
    }

    protected void restoreCanvas(Canvas canvas) {
        canvas.restore();
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(float opacity) {
        this.mOpacity = opacity;
        markUpdated();
    }

    @ReactProp(name = "transform")
    public void setTransform(@Nullable ReadableArray transformArray) {
        if (transformArray != null) {
            int matrixSize = PropHelper.toFloatArray(transformArray, sMatrixData);
            if (matrixSize == 6) {
                setupMatrix();
            } else if (matrixSize != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        }
        this.mMatrix = null;
        markUpdated();
    }

    protected void setupMatrix() {
        sRawMatrix[0] = sMatrixData[0];
        sRawMatrix[1] = sMatrixData[2];
        sRawMatrix[2] = sMatrixData[4] * this.mScale;
        sRawMatrix[3] = sMatrixData[1];
        sRawMatrix[4] = sMatrixData[3];
        sRawMatrix[5] = sMatrixData[5] * this.mScale;
        sRawMatrix[6] = 0.0f;
        sRawMatrix[7] = 0.0f;
        sRawMatrix[8] = FlexItem.FLEX_SHRINK_DEFAULT;
        if (this.mMatrix == null) {
            this.mMatrix = new Matrix();
        }
        this.mMatrix.setValues(sRawMatrix);
    }
}
