package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

class DiffClampAnimatedNode extends ValueAnimatedNode {
    private final int mInputNodeTag;
    private double mLastValue;
    private final double mMax;
    private final double mMin;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public DiffClampAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNodeTag = config.getInt("input");
        this.mMin = config.getDouble("min");
        this.mMax = config.getDouble("max");
        double inputNodeValue = getInputNodeValue();
        this.mLastValue = inputNodeValue;
        this.mValue = inputNodeValue;
    }

    public void update() {
        double value = getInputNodeValue();
        double diff = value - this.mLastValue;
        this.mLastValue = value;
        this.mValue = Math.min(Math.max(this.mValue + diff, this.mMin), this.mMax);
    }

    private double getInputNodeValue() {
        AnimatedNode animatedNode = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodeTag);
        if (animatedNode != null && (animatedNode instanceof ValueAnimatedNode)) {
            return ((ValueAnimatedNode) animatedNode).getValue();
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.DiffClamp node");
    }
}
