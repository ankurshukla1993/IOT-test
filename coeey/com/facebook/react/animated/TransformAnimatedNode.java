package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;

class TransformAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final List<TransformConfig> mTransformConfigs;

    private class TransformConfig {
        public String mProperty;

        private TransformConfig() {
        }
    }

    private class AnimatedTransformConfig extends TransformConfig {
        public int mNodeTag;

        private AnimatedTransformConfig() {
            super();
        }
    }

    private class StaticTransformConfig extends TransformConfig {
        public double mValue;

        private StaticTransformConfig() {
            super();
        }
    }

    TransformAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableArray transforms = config.getArray("transforms");
        this.mTransformConfigs = new ArrayList(transforms.size());
        for (int i = 0; i < transforms.size(); i++) {
            ReadableMap transformConfigMap = transforms.getMap(i);
            String property = transformConfigMap.getString("property");
            if (transformConfigMap.getString("type").equals("animated")) {
                AnimatedTransformConfig transformConfig = new AnimatedTransformConfig();
                transformConfig.mProperty = property;
                transformConfig.mNodeTag = transformConfigMap.getInt("nodeTag");
                this.mTransformConfigs.add(transformConfig);
            } else {
                StaticTransformConfig transformConfig2 = new StaticTransformConfig();
                transformConfig2.mProperty = property;
                transformConfig2.mValue = transformConfigMap.getDouble("value");
                this.mTransformConfigs.add(transformConfig2);
            }
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap propsMap) {
        List<JavaOnlyMap> transforms = new ArrayList(this.mTransformConfigs.size());
        for (TransformConfig transformConfig : this.mTransformConfigs) {
            double value;
            if (transformConfig instanceof AnimatedTransformConfig) {
                AnimatedNode node = this.mNativeAnimatedNodesManager.getNodeById(((AnimatedTransformConfig) transformConfig).mNodeTag);
                if (node == null) {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                } else if (node instanceof ValueAnimatedNode) {
                    value = ((ValueAnimatedNode) node).getValue();
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + node.getClass());
                }
            }
            value = ((StaticTransformConfig) transformConfig).mValue;
            transforms.add(JavaOnlyMap.of(transformConfig.mProperty, Double.valueOf(value)));
        }
        propsMap.putArray("transform", JavaOnlyArray.from(transforms));
    }
}
