package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import javax.annotation.Nullable;

class ValueAnimatedNode extends AnimatedNode {
    double mOffset = 0.0d;
    double mValue = Double.NaN;
    @Nullable
    private AnimatedNodeValueListener mValueListener;

    public ValueAnimatedNode(ReadableMap config) {
        this.mValue = config.getDouble("value");
        this.mOffset = config.getDouble("offset");
    }

    public double getValue() {
        return this.mOffset + this.mValue;
    }

    public void flattenOffset() {
        this.mValue += this.mOffset;
        this.mOffset = 0.0d;
    }

    public void extractOffset() {
        this.mOffset += this.mValue;
        this.mValue = 0.0d;
    }

    public void onValueUpdate() {
        if (this.mValueListener != null) {
            this.mValueListener.onValueUpdate(this.mValue);
        }
    }

    public void setValueListener(@Nullable AnimatedNodeValueListener listener) {
        this.mValueListener = listener;
    }
}
