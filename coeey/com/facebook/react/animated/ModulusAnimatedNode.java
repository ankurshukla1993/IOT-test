package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

class ModulusAnimatedNode extends ValueAnimatedNode {
    private final int mInputNode;
    private final int mModulus;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public ModulusAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNode = config.getInt("input");
        this.mModulus = config.getInt("modulus");
    }

    public void update() {
        AnimatedNode animatedNode = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNode);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
        }
        this.mValue = ((ValueAnimatedNode) animatedNode).mValue % ((double) this.mModulus);
    }
}
