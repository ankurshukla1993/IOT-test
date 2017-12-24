package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class DivisionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public DivisionAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray inputNodes = config.getArray("input");
        this.mInputNodes = new int[inputNodes.size()];
        for (int i = 0; i < this.mInputNodes.length; i++) {
            this.mInputNodes[i] = inputNodes.getInt(i);
        }
    }

    public void update() {
        for (int i = 0; i < this.mInputNodes.length; i++) {
            AnimatedNode animatedNode = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodes[i]);
            if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.divide node");
            }
            double value = ((ValueAnimatedNode) animatedNode).getValue();
            if (i == 0) {
                this.mValue = value;
            } else if (value == 0.0d) {
                throw new JSApplicationCausedNativeException("Detected a division by zero in Animated.divide node");
            } else {
                this.mValue /= value;
            }
        }
    }
}
