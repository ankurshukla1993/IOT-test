package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class ARTShapeShadowNode extends ARTVirtualNode {
    private static final int CAP_BUTT = 0;
    private static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    private static final int JOIN_ROUND = 1;
    private static final int PATH_TYPE_ARC = 4;
    private static final int PATH_TYPE_CLOSE = 1;
    private static final int PATH_TYPE_CURVETO = 3;
    private static final int PATH_TYPE_LINETO = 2;
    private static final int PATH_TYPE_MOVETO = 0;
    @Nullable
    private float[] mFillColor;
    @Nullable
    protected Path mPath;
    private int mStrokeCap = 1;
    @Nullable
    private float[] mStrokeColor;
    @Nullable
    private float[] mStrokeDash;
    private int mStrokeJoin = 1;
    private float mStrokeWidth = FlexItem.FLEX_SHRINK_DEFAULT;

    @ReactProp(name = "d")
    public void setShapePath(@Nullable ReadableArray shapePath) {
        this.mPath = createPath(PropHelper.toFloatArray(shapePath));
        markUpdated();
    }

    @ReactProp(name = "stroke")
    public void setStroke(@Nullable ReadableArray strokeColors) {
        this.mStrokeColor = PropHelper.toFloatArray(strokeColors);
        markUpdated();
    }

    @ReactProp(name = "strokeDash")
    public void setStrokeDash(@Nullable ReadableArray strokeDash) {
        this.mStrokeDash = PropHelper.toFloatArray(strokeDash);
        markUpdated();
    }

    @ReactProp(name = "fill")
    public void setFill(@Nullable ReadableArray fillColors) {
        this.mFillColor = PropHelper.toFloatArray(fillColors);
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(int strokeCap) {
        this.mStrokeCap = strokeCap;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(int strokeJoin) {
        this.mStrokeJoin = strokeJoin;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        opacity *= this.mOpacity;
        if (opacity > 0.01f) {
            saveAndSetupCanvas(canvas);
            if (this.mPath == null) {
                throw new JSApplicationIllegalArgumentException("Shapes should have a valid path (d) prop");
            }
            if (setupFillPaint(paint, opacity)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (setupStrokePaint(paint, opacity)) {
                canvas.drawPath(this.mPath, paint);
            }
            restoreCanvas(canvas);
        }
        markUpdateSeen();
    }

    protected boolean setupStrokePaint(Paint paint, float opacity) {
        if (this.mStrokeWidth == 0.0f || this.mStrokeColor == null || this.mStrokeColor.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.STROKE);
        switch (this.mStrokeCap) {
            case 0:
                paint.setStrokeCap(Cap.BUTT);
                break;
            case 1:
                paint.setStrokeCap(Cap.ROUND);
                break;
            case 2:
                paint.setStrokeCap(Cap.SQUARE);
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeCap " + this.mStrokeCap + " unrecognized");
        }
        switch (this.mStrokeJoin) {
            case 0:
                paint.setStrokeJoin(Join.MITER);
                break;
            case 1:
                paint.setStrokeJoin(Join.ROUND);
                break;
            case 2:
                paint.setStrokeJoin(Join.BEVEL);
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeJoin " + this.mStrokeJoin + " unrecognized");
        }
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        paint.setARGB((int) (this.mStrokeColor.length > 3 ? (this.mStrokeColor[3] * opacity) * 255.0f : opacity * 255.0f), (int) (this.mStrokeColor[0] * 255.0f), (int) (this.mStrokeColor[1] * 255.0f), (int) (this.mStrokeColor[2] * 255.0f));
        if (this.mStrokeDash != null && this.mStrokeDash.length > 0) {
            paint.setPathEffect(new DashPathEffect(this.mStrokeDash, 0.0f));
        }
        return true;
    }

    protected boolean setupFillPaint(Paint paint, float opacity) {
        if (this.mFillColor == null || this.mFillColor.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.FILL);
        int colorType = (int) this.mFillColor[0];
        switch (colorType) {
            case 0:
                paint.setARGB((int) (this.mFillColor.length > 4 ? (this.mFillColor[4] * opacity) * 255.0f : opacity * 255.0f), (int) (this.mFillColor[1] * 255.0f), (int) (this.mFillColor[2] * 255.0f), (int) (this.mFillColor[3] * 255.0f));
                break;
            default:
                FLog.w(ReactConstants.TAG, "ART: Color type " + colorType + " not supported!");
                break;
        }
        return true;
    }

    private Path createPath(float[] data) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < data.length) {
            int i2 = i + 1;
            int type = (int) data[i];
            switch (type) {
                case 0:
                    i = i2 + 1;
                    i2 = i + 1;
                    path.moveTo(data[i2] * this.mScale, data[i] * this.mScale);
                    i = i2;
                    break;
                case 1:
                    path.close();
                    i = i2;
                    break;
                case 2:
                    i = i2 + 1;
                    i2 = i + 1;
                    path.lineTo(data[i2] * this.mScale, data[i] * this.mScale);
                    i = i2;
                    break;
                case 3:
                    i = i2 + 1;
                    i2 = i + 1;
                    i = i2 + 1;
                    i2 = i + 1;
                    i = i2 + 1;
                    i2 = i + 1;
                    path.cubicTo(data[i2] * this.mScale, data[i] * this.mScale, data[i2] * this.mScale, data[i] * this.mScale, data[i2] * this.mScale, data[i] * this.mScale);
                    i = i2;
                    break;
                case 4:
                    i = i2 + 1;
                    float x = data[i2] * this.mScale;
                    i2 = i + 1;
                    float y = data[i] * this.mScale;
                    i = i2 + 1;
                    float r = data[i2] * this.mScale;
                    i2 = i + 1;
                    float start = (float) Math.toDegrees((double) data[i]);
                    i = i2 + 1;
                    float end = (float) Math.toDegrees((double) data[i2]);
                    i2 = i + 1;
                    if (!(data[i] == 0.0f)) {
                        end = 360.0f - end;
                    }
                    path.addArc(new RectF(x - r, y - r, x + r, y + r), start, start - end);
                    i = i2;
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + type);
            }
        }
        return path;
    }
}
