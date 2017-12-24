package com.facebook.react.views.image;

import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import javax.annotation.Nullable;

public class ImageResizeMode {
    public static ScalingUtils$ScaleType toScaleType(@Nullable String resizeModeValue) {
        if ("contain".equals(resizeModeValue)) {
            return ScalingUtils$ScaleType.FIT_CENTER;
        }
        if ("cover".equals(resizeModeValue)) {
            return ScalingUtils$ScaleType.CENTER_CROP;
        }
        if ("stretch".equals(resizeModeValue)) {
            return ScalingUtils$ScaleType.FIT_XY;
        }
        if ("center".equals(resizeModeValue)) {
            return ScalingUtils$ScaleType.CENTER_INSIDE;
        }
        if (resizeModeValue == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + resizeModeValue + "'");
    }

    public static ScalingUtils$ScaleType defaultValue() {
        return ScalingUtils$ScaleType.CENTER_CROP;
    }
}
