package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class YogaValue {
    static final YogaValue UNDEFINED = new YogaValue((float) YogaConstants.UNDEFINED, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.PIXEL);
    public final YogaUnit unit;
    public final float value;

    public YogaValue(float value, YogaUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @DoNotStrip
    YogaValue(float value, int unit) {
        this(value, YogaUnit.fromInt(unit));
    }

    public boolean equals(Object other) {
        if (!(other instanceof YogaValue)) {
            return false;
        }
        YogaValue otherValue = (YogaValue) other;
        if (this.value == otherValue.value && this.unit == otherValue.unit) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }
}
