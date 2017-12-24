package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConstants;
import java.util.Arrays;

public class Spacing {
    public static final int ALL = 8;
    public static final int BOTTOM = 3;
    public static final int END = 5;
    public static final int HORIZONTAL = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int START = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 7;
    private static final int[] sFlagsMap = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256};
    private float mDefaultValue;
    private boolean mHasAliasesSet;
    private final float[] mSpacing;
    private int mValueFlags;

    public Spacing() {
        this(0.0f);
    }

    public Spacing(float defaultValue) {
        this.mSpacing = newFullSpacingArray();
        this.mValueFlags = 0;
        this.mDefaultValue = defaultValue;
    }

    public boolean set(int spacingType, float value) {
        boolean z = false;
        if (FloatUtil.floatsEqual(this.mSpacing[spacingType], value)) {
            return false;
        }
        this.mSpacing[spacingType] = value;
        if (YogaConstants.isUndefined(value)) {
            this.mValueFlags &= sFlagsMap[spacingType] ^ -1;
        } else {
            this.mValueFlags |= sFlagsMap[spacingType];
        }
        if (!((this.mValueFlags & sFlagsMap[8]) == 0 && (this.mValueFlags & sFlagsMap[7]) == 0 && (this.mValueFlags & sFlagsMap[6]) == 0)) {
            z = true;
        }
        this.mHasAliasesSet = z;
        return true;
    }

    public float get(int spacingType) {
        float defaultValue = (spacingType == 4 || spacingType == 5) ? YogaConstants.UNDEFINED : this.mDefaultValue;
        if (this.mValueFlags == 0) {
            return defaultValue;
        }
        if ((this.mValueFlags & sFlagsMap[spacingType]) != 0) {
            return this.mSpacing[spacingType];
        }
        if (!this.mHasAliasesSet) {
            return defaultValue;
        }
        int secondType = (spacingType == 1 || spacingType == 3) ? 7 : 6;
        if ((this.mValueFlags & sFlagsMap[secondType]) != 0) {
            return this.mSpacing[secondType];
        }
        if ((this.mValueFlags & sFlagsMap[8]) != 0) {
            return this.mSpacing[8];
        }
        return defaultValue;
    }

    public float getRaw(int spacingType) {
        return this.mSpacing[spacingType];
    }

    public void reset() {
        Arrays.fill(this.mSpacing, YogaConstants.UNDEFINED);
        this.mHasAliasesSet = false;
        this.mValueFlags = 0;
    }

    float getWithFallback(int spacingType, int fallbackType) {
        if ((this.mValueFlags & sFlagsMap[spacingType]) != 0) {
            return this.mSpacing[spacingType];
        }
        return get(fallbackType);
    }

    private static float[] newFullSpacingArray() {
        return new float[]{YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED};
    }
}
