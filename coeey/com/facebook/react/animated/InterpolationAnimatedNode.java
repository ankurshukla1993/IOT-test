package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import javax.annotation.Nullable;

class InterpolationAnimatedNode extends ValueAnimatedNode {
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final double[] mInputRange;
    private final double[] mOutputRange;
    @Nullable
    private ValueAnimatedNode mParent;

    private static double[] fromDoubleArray(ReadableArray ary) {
        double[] res = new double[ary.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ary.getDouble(i);
        }
        return res;
    }

    private static double interpolate(double value, double inputMin, double inputMax, double outputMin, double outputMax, String extrapolateLeft, String extrapolateRight) {
        Object obj;
        double result = value;
        if (result < inputMin) {
            obj = -1;
            switch (extrapolateLeft.hashCode()) {
                case -1289044198:
                    if (extrapolateLeft.equals(EXTRAPOLATE_TYPE_EXTEND)) {
                        obj = 2;
                        break;
                    }
                    break;
                case -135761730:
                    if (extrapolateLeft.equals(EXTRAPOLATE_TYPE_IDENTITY)) {
                        obj = null;
                        break;
                    }
                    break;
                case 94742715:
                    if (extrapolateLeft.equals(EXTRAPOLATE_TYPE_CLAMP)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return result;
                case 1:
                    result = inputMin;
                    break;
                case 2:
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Invalid extrapolation type " + extrapolateLeft + "for left extrapolation");
            }
        }
        if (result > inputMax) {
            obj = -1;
            switch (extrapolateRight.hashCode()) {
                case -1289044198:
                    if (extrapolateRight.equals(EXTRAPOLATE_TYPE_EXTEND)) {
                        obj = 2;
                        break;
                    }
                    break;
                case -135761730:
                    if (extrapolateRight.equals(EXTRAPOLATE_TYPE_IDENTITY)) {
                        obj = null;
                        break;
                    }
                    break;
                case 94742715:
                    if (extrapolateRight.equals(EXTRAPOLATE_TYPE_CLAMP)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return result;
                case 1:
                    result = inputMax;
                    break;
                case 2:
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Invalid extrapolation type " + extrapolateRight + "for right extrapolation");
            }
        }
        return (((outputMax - outputMin) * (result - inputMin)) / (inputMax - inputMin)) + outputMin;
    }

    static double interpolate(double value, double[] inputRange, double[] outputRange, String extrapolateLeft, String extrapolateRight) {
        int rangeIndex = findRangeIndex(value, inputRange);
        return interpolate(value, inputRange[rangeIndex], inputRange[rangeIndex + 1], outputRange[rangeIndex], outputRange[rangeIndex + 1], extrapolateLeft, extrapolateRight);
    }

    private static int findRangeIndex(double value, double[] ranges) {
        int index = 1;
        while (index < ranges.length - 1 && ranges[index] < value) {
            index++;
        }
        return index - 1;
    }

    public InterpolationAnimatedNode(ReadableMap config) {
        this.mInputRange = fromDoubleArray(config.getArray("inputRange"));
        this.mOutputRange = fromDoubleArray(config.getArray("outputRange"));
        this.mExtrapolateLeft = config.getString("extrapolateLeft");
        this.mExtrapolateRight = config.getString("extrapolateRight");
    }

    public void onAttachedToNode(AnimatedNode parent) {
        if (this.mParent != null) {
            throw new IllegalStateException("Parent already attached");
        } else if (parent instanceof ValueAnimatedNode) {
            this.mParent = (ValueAnimatedNode) parent;
        } else {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
    }

    public void onDetachedFromNode(AnimatedNode parent) {
        if (parent != this.mParent) {
            throw new IllegalArgumentException("Invalid parent node provided");
        }
        this.mParent = null;
    }

    public void update() {
        if (this.mParent == null) {
            throw new IllegalStateException("Trying to update interpolation node that has not been attached to the parent");
        }
        this.mValue = interpolate(this.mParent.getValue(), this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
    }
}
