package com.facebook.drawee.generic;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;

public class RoundingParams {
    private int mBorderColor = 0;
    private float mBorderWidth = 0.0f;
    private float[] mCornersRadii = null;
    private int mOverlayColor = 0;
    private float mPadding = 0.0f;
    private boolean mRoundAsCircle = false;
    private RoundingMethod mRoundingMethod = RoundingMethod.BITMAP_ONLY;

    public enum RoundingMethod {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    public RoundingParams setRoundAsCircle(boolean roundAsCircle) {
        this.mRoundAsCircle = roundAsCircle;
        return this;
    }

    public boolean getRoundAsCircle() {
        return this.mRoundAsCircle;
    }

    public RoundingParams setCornersRadius(float radius) {
        Arrays.fill(getOrCreateRoundedCornersRadii(), radius);
        return this;
    }

    public RoundingParams setCornersRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        float[] radii = getOrCreateRoundedCornersRadii();
        radii[1] = topLeft;
        radii[0] = topLeft;
        radii[3] = topRight;
        radii[2] = topRight;
        radii[5] = bottomRight;
        radii[4] = bottomRight;
        radii[7] = bottomLeft;
        radii[6] = bottomLeft;
        return this;
    }

    public RoundingParams setCornersRadii(float[] radii) {
        boolean z;
        Preconditions.checkNotNull(radii);
        if (radii.length == 8) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "radii should have exactly 8 values");
        System.arraycopy(radii, 0, getOrCreateRoundedCornersRadii(), 0, 8);
        return this;
    }

    public float[] getCornersRadii() {
        return this.mCornersRadii;
    }

    public RoundingParams setRoundingMethod(RoundingMethod roundingMethod) {
        this.mRoundingMethod = roundingMethod;
        return this;
    }

    public RoundingMethod getRoundingMethod() {
        return this.mRoundingMethod;
    }

    public RoundingParams setOverlayColor(int overlayColor) {
        this.mOverlayColor = overlayColor;
        this.mRoundingMethod = RoundingMethod.OVERLAY_COLOR;
        return this;
    }

    public int getOverlayColor() {
        return this.mOverlayColor;
    }

    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }

    public static RoundingParams asCircle() {
        return new RoundingParams().setRoundAsCircle(true);
    }

    public static RoundingParams fromCornersRadius(float radius) {
        return new RoundingParams().setCornersRadius(radius);
    }

    public static RoundingParams fromCornersRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        return new RoundingParams().setCornersRadii(topLeft, topRight, bottomRight, bottomLeft);
    }

    public static RoundingParams fromCornersRadii(float[] radii) {
        return new RoundingParams().setCornersRadii(radii);
    }

    public RoundingParams setBorderWidth(float width) {
        Preconditions.checkArgument(width >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = width;
        return this;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public RoundingParams setBorderColor(int color) {
        this.mBorderColor = color;
        return this;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public RoundingParams setBorder(int color, float width) {
        Preconditions.checkArgument(width >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = width;
        this.mBorderColor = color;
        return this;
    }

    public RoundingParams setPadding(float padding) {
        Preconditions.checkArgument(padding >= 0.0f, "the padding cannot be < 0");
        this.mPadding = padding;
        return this;
    }

    public float getPadding() {
        return this.mPadding;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoundingParams that = (RoundingParams) o;
        if (this.mRoundAsCircle == that.mRoundAsCircle && this.mOverlayColor == that.mOverlayColor && Float.compare(that.mBorderWidth, this.mBorderWidth) == 0 && this.mBorderColor == that.mBorderColor && Float.compare(that.mPadding, this.mPadding) == 0 && this.mRoundingMethod == that.mRoundingMethod) {
            return Arrays.equals(this.mCornersRadii, that.mCornersRadii);
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.mRoundingMethod != null) {
            result = this.mRoundingMethod.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.mRoundAsCircle) {
            i = 1;
        } else {
            i = 0;
        }
        i3 = (i3 + i) * 31;
        if (this.mCornersRadii != null) {
            i = Arrays.hashCode(this.mCornersRadii);
        } else {
            i = 0;
        }
        i3 = (((i3 + i) * 31) + this.mOverlayColor) * 31;
        if (this.mBorderWidth != 0.0f) {
            i = Float.floatToIntBits(this.mBorderWidth);
        } else {
            i = 0;
        }
        i = (((i3 + i) * 31) + this.mBorderColor) * 31;
        if (this.mPadding != 0.0f) {
            i2 = Float.floatToIntBits(this.mPadding);
        }
        return i + i2;
    }
}
