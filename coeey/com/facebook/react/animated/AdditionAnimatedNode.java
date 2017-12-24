package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class AdditionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public AdditionAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray inputNodes = config.getArray("input");
        this.mInputNodes = new int[inputNodes.size()];
        for (int i = 0; i < this.mInputNodes.length; i++) {
            this.mInputNodes[i] = inputNodes.getInt(i);
        }
    }

    public void update() {
        this.mValue = 0.0d;
        for (int nodeById : this.mInputNodes) {
            AnimatedNode animatedNode = this.mNativeAnimatedNodesManager.getNodeById(nodeById);
            if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
                throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.Add node");
            }
            this.mValue += ((ValueAnimatedNode) animatedNode).getValue();
        }
    }
}
