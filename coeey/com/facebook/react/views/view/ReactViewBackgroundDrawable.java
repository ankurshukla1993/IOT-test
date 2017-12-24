package com.facebook.react.views.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nullable;

public class ReactViewBackgroundDrawable extends Drawable {
    private static final int DEFAULT_BORDER_ALPHA = 255;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_BORDER_RGB = 0;
    private int mAlpha = 255;
    @Nullable
    private Spacing mBorderAlpha;
    @Nullable
    private float[] mBorderCornerRadii;
    @Nullable
    private Spacing mBorderRGB;
    private float mBorderRadius = YogaConstants.UNDEFINED;
    @Nullable
    private BorderStyle mBorderStyle;
    @Nullable
    private Spacing mBorderWidth;
    private int mColor = 0;
    private boolean mNeedUpdatePathForBorderRadius = false;
    private final Paint mPaint = new Paint(1);
    @Nullable
    private PathEffect mPathEffectForBorderStyle;
    @Nullable
    private Path mPathForBorder;
    @Nullable
    private Path mPathForBorderRadius;
    @Nullable
    private Path mPathForBorderRadiusOutline;
    @Nullable
    private RectF mTempRectForBorderRadius;
    @Nullable
    private RectF mTempRectForBorderRadiusOutline;

    private enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        @Nullable
        public PathEffect getPathEffect(float borderWidth) {
            switch (1.$SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[ordinal()]) {
                case 1:
                    return null;
                case 2:
                    return new DashPathEffect(new float[]{borderWidth * 3.0f, borderWidth * 3.0f, borderWidth * 3.0f, 3.0f * borderWidth}, 0.0f);
                case 3:
                    return new DashPathEffect(new float[]{borderWidth, borderWidth, borderWidth, borderWidth}, 0.0f);
                default:
                    return null;
            }
        }
    }

    public void draw(Canvas canvas) {
        updatePathEffect();
        boolean roundedBorders = this.mBorderCornerRadii != null || (!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f);
        if ((this.mBorderStyle == null || this.mBorderStyle == BorderStyle.SOLID) && !roundedBorders) {
            drawRectangularBackgroundWithBorders(canvas);
        } else {
            drawRoundedBackgroundWithBorders(canvas);
        }
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    public void setAlpha(int alpha) {
        if (alpha != this.mAlpha) {
            this.mAlpha = alpha;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setColorFilter(ColorFilter cf) {
    }

    public int getOpacity() {
        return ColorUtil.getOpacityFromColor(ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    public void getOutline(Outline outline) {
        if (VERSION.SDK_INT < 21) {
            super.getOutline(outline);
        } else if ((YogaConstants.isUndefined(this.mBorderRadius) || this.mBorderRadius <= 0.0f) && this.mBorderCornerRadii == null) {
            outline.setRect(getBounds());
        } else {
            updatePath();
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
        }
    }

    public void setBorderWidth(int position, float width) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (!FloatUtil.floatsEqual(this.mBorderWidth.getRaw(position), width)) {
            this.mBorderWidth.set(position, width);
            if (position == 8) {
                this.mNeedUpdatePathForBorderRadius = true;
            }
            invalidateSelf();
        }
    }

    public void setBorderColor(int position, float rgb, float alpha) {
        setBorderRGB(position, rgb);
        setBorderAlpha(position, alpha);
    }

    private void setBorderRGB(int position, float rgb) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderRGB.getRaw(position), rgb)) {
            this.mBorderRGB.set(position, rgb);
            invalidateSelf();
        }
    }

    private void setBorderAlpha(int position, float alpha) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(position), alpha)) {
            this.mBorderAlpha.set(position, alpha);
            invalidateSelf();
        }
    }

    public void setBorderStyle(@Nullable String style) {
        BorderStyle borderStyle;
        if (style == null) {
            borderStyle = null;
        } else {
            borderStyle = BorderStyle.valueOf(style.toUpperCase(Locale.US));
        }
        if (this.mBorderStyle != borderStyle) {
            this.mBorderStyle = borderStyle;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setRadius(float radius) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, radius)) {
            this.mBorderRadius = radius;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setRadius(float radius, int position) {
        if (this.mBorderCornerRadii == null) {
            this.mBorderCornerRadii = new float[4];
            Arrays.fill(this.mBorderCornerRadii, YogaConstants.UNDEFINED);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[position], radius)) {
            this.mBorderCornerRadii[position] = radius;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setColor(int color) {
        this.mColor = color;
        invalidateSelf();
    }

    @VisibleForTesting
    public int getColor() {
        return this.mColor;
    }

    private void drawRoundedBackgroundWithBorders(Canvas canvas) {
        updatePath();
        int useColor = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if ((useColor >>> 24) != 0) {
            this.mPaint.setColor(useColor);
            this.mPaint.setStyle(Style.FILL);
            canvas.drawPath(this.mPathForBorderRadius, this.mPaint);
        }
        float fullBorderWidth = getFullBorderWidth();
        if (fullBorderWidth > 0.0f) {
            this.mPaint.setColor(ColorUtil.multiplyColorAlpha(getFullBorderColor(), this.mAlpha));
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setStrokeWidth(fullBorderWidth);
            canvas.drawPath(this.mPathForBorderRadius, this.mPaint);
        }
    }

    private void updatePath() {
        if (this.mNeedUpdatePathForBorderRadius) {
            float topLeftRadius;
            float topRightRadius;
            float bottomRightRadius;
            float bottomLeftRadius;
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mPathForBorderRadius == null) {
                this.mPathForBorderRadius = new Path();
                this.mTempRectForBorderRadius = new RectF();
                this.mPathForBorderRadiusOutline = new Path();
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            this.mPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            float fullBorderWidth = getFullBorderWidth();
            if (fullBorderWidth > 0.0f) {
                this.mTempRectForBorderRadius.inset(0.5f * fullBorderWidth, 0.5f * fullBorderWidth);
            }
            float defaultBorderRadius = !YogaConstants.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
            if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[0])) {
                topLeftRadius = defaultBorderRadius;
            } else {
                topLeftRadius = this.mBorderCornerRadii[0];
            }
            if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[1])) {
                topRightRadius = defaultBorderRadius;
            } else {
                topRightRadius = this.mBorderCornerRadii[1];
            }
            if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[2])) {
                bottomRightRadius = defaultBorderRadius;
            } else {
                bottomRightRadius = this.mBorderCornerRadii[2];
            }
            if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[3])) {
                bottomLeftRadius = defaultBorderRadius;
            } else {
                bottomLeftRadius = this.mBorderCornerRadii[3];
            }
            this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius}, Direction.CW);
            float extraRadiusForOutline = 0.0f;
            if (this.mBorderWidth != null) {
                extraRadiusForOutline = this.mBorderWidth.get(8) / 2.0f;
            }
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{topLeftRadius + extraRadiusForOutline, topLeftRadius + extraRadiusForOutline, topRightRadius + extraRadiusForOutline, topRightRadius + extraRadiusForOutline, bottomRightRadius + extraRadiusForOutline, bottomRightRadius + extraRadiusForOutline, bottomLeftRadius + extraRadiusForOutline, bottomLeftRadius + extraRadiusForOutline}, Direction.CW);
        }
    }

    private void updatePathEffect() {
        this.mPathEffectForBorderStyle = this.mBorderStyle != null ? this.mBorderStyle.getPathEffect(getFullBorderWidth()) : null;
        this.mPaint.setPathEffect(this.mPathEffectForBorderStyle);
    }

    private float getFullBorderWidth() {
        return (this.mBorderWidth == null || YogaConstants.isUndefined(this.mBorderWidth.getRaw(8))) ? 0.0f : this.mBorderWidth.getRaw(8);
    }

    private int getFullBorderColor() {
        float rgb = (this.mBorderRGB == null || YogaConstants.isUndefined(this.mBorderRGB.getRaw(8))) ? 0.0f : this.mBorderRGB.getRaw(8);
        float alpha = (this.mBorderAlpha == null || YogaConstants.isUndefined(this.mBorderAlpha.getRaw(8))) ? 255.0f : this.mBorderAlpha.getRaw(8);
        return colorFromAlphaAndRGBComponents(alpha, rgb);
    }

    private void drawRectangularBackgroundWithBorders(Canvas canvas) {
        int useColor = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if ((useColor >>> 24) != 0) {
            this.mPaint.setColor(useColor);
            this.mPaint.setStyle(Style.FILL);
            canvas.drawRect(getBounds(), this.mPaint);
        }
        if (getBorderWidth(0) > 0 || getBorderWidth(1) > 0 || getBorderWidth(2) > 0 || getBorderWidth(3) > 0) {
            Rect bounds = getBounds();
            int borderLeft = getBorderWidth(0);
            int borderTop = getBorderWidth(1);
            int borderRight = getBorderWidth(2);
            int borderBottom = getBorderWidth(3);
            int colorLeft = getBorderColor(0);
            int colorTop = getBorderColor(1);
            int colorRight = getBorderColor(2);
            int colorBottom = getBorderColor(3);
            int top = bounds.top;
            int left = bounds.left;
            int width = bounds.width();
            int height = bounds.height();
            this.mPaint.setAntiAlias(false);
            if (this.mPathForBorder == null) {
                this.mPathForBorder = new Path();
            }
            if (borderLeft > 0 && colorLeft != 0) {
                this.mPaint.setColor(colorLeft);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float) left, (float) top);
                this.mPathForBorder.lineTo((float) (left + borderLeft), (float) (top + borderTop));
                this.mPathForBorder.lineTo((float) (left + borderLeft), (float) ((top + height) - borderBottom));
                this.mPathForBorder.lineTo((float) left, (float) (top + height));
                this.mPathForBorder.lineTo((float) left, (float) top);
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            if (borderTop > 0 && colorTop != 0) {
                this.mPaint.setColor(colorTop);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float) left, (float) top);
                this.mPathForBorder.lineTo((float) (left + borderLeft), (float) (top + borderTop));
                this.mPathForBorder.lineTo((float) ((left + width) - borderRight), (float) (top + borderTop));
                this.mPathForBorder.lineTo((float) (left + width), (float) top);
                this.mPathForBorder.lineTo((float) left, (float) top);
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            if (borderRight > 0 && colorRight != 0) {
                this.mPaint.setColor(colorRight);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float) (left + width), (float) top);
                this.mPathForBorder.lineTo((float) (left + width), (float) (top + height));
                this.mPathForBorder.lineTo((float) ((left + width) - borderRight), (float) ((top + height) - borderBottom));
                this.mPathForBorder.lineTo((float) ((left + width) - borderRight), (float) (top + borderTop));
                this.mPathForBorder.lineTo((float) (left + width), (float) top);
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            if (borderBottom > 0 && colorBottom != 0) {
                this.mPaint.setColor(colorBottom);
                this.mPathForBorder.reset();
                this.mPathForBorder.moveTo((float) left, (float) (top + height));
                this.mPathForBorder.lineTo((float) (left + width), (float) (top + height));
                this.mPathForBorder.lineTo((float) ((left + width) - borderRight), (float) ((top + height) - borderBottom));
                this.mPathForBorder.lineTo((float) (left + borderLeft), (float) ((top + height) - borderBottom));
                this.mPathForBorder.lineTo((float) left, (float) (top + height));
                canvas.drawPath(this.mPathForBorder, this.mPaint);
            }
            this.mPaint.setAntiAlias(true);
        }
    }

    private int getBorderWidth(int position) {
        return this.mBorderWidth != null ? Math.round(this.mBorderWidth.get(position)) : 0;
    }

    private static int colorFromAlphaAndRGBComponents(float alpha, float rgb) {
        return (16777215 & ((int) rgb)) | (-16777216 & (((int) alpha) << 24));
    }

    private int getBorderColor(int position) {
        return colorFromAlphaAndRGBComponents(this.mBorderAlpha != null ? this.mBorderAlpha.get(position) : 255.0f, this.mBorderRGB != null ? this.mBorderRGB.get(position) : 0.0f);
    }
}
