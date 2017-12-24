package com.facebook.react.animated;

import com.facebook.internal.AnalyticsEvents;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class StyleAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final Map<String, Integer> mPropMapping = new HashMap();

    StyleAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableMap style = config.getMap(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        ReadableMapKeySetIterator iter = style.keySetIterator();
        while (iter.hasNextKey()) {
            String propKey = iter.nextKey();
            this.mPropMapping.put(propKey, Integer.valueOf(style.getInt(propKey)));
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap propsMap) {
        for (Entry<String, Integer> entry : this.mPropMapping.entrySet()) {
            AnimatedNode node = this.mNativeAnimatedNodesManager.getNodeById(((Integer) entry.getValue()).intValue());
            if (node == null) {
                throw new IllegalArgumentException("Mapped style node does not exists");
            } else if (node instanceof TransformAnimatedNode) {
                ((TransformAnimatedNode) node).collectViewUpdates(propsMap);
            } else if (node instanceof ValueAnimatedNode) {
                propsMap.putDouble((String) entry.getKey(), ((ValueAnimatedNode) node).getValue());
            } else {
                throw new IllegalArgumentException("Unsupported type of node used in property node " + node.getClass());
            }
        }
    }
}
