package com.facebook.react.views.art;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureFunction;

@ReactModule(name = "ARTSurfaceView")
public class ARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, ARTSurfaceViewShadowNode> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new 1();
    protected static final String REACT_CLASS = "ARTSurfaceView";

    public String getName() {
        return REACT_CLASS;
    }

    public ARTSurfaceViewShadowNode createShadowNodeInstance() {
        ARTSurfaceViewShadowNode node = new ARTSurfaceViewShadowNode();
        node.setMeasureFunction(MEASURE_FUNCTION);
        return node;
    }

    public Class<ARTSurfaceViewShadowNode> getShadowNodeClass() {
        return ARTSurfaceViewShadowNode.class;
    }

    protected ARTSurfaceView createViewInstance(ThemedReactContext reactContext) {
        return new ARTSurfaceView(reactContext);
    }

    public void updateExtraData(ARTSurfaceView root, Object extraData) {
        root.setSurfaceTextureListener((ARTSurfaceViewShadowNode) extraData);
    }
}
