package com.facebook.react.uimanager;

import android.os.Build.VERSION;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.MatrixMathHelper.MatrixDecompositionContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import io.reactivex.annotations.SchedulerSupport;

public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C> {
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_BACKGROUND_COLOR = "backgroundColor";
    private static final String PROP_ELEVATION = "elevation";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    private static final String PROP_OPACITY = "opacity";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_ROTATION = "rotation";
    private static final String PROP_SCALE_X = "scaleX";
    private static final String PROP_SCALE_Y = "scaleY";
    public static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private static final String PROP_TRANSLATE_X = "translateX";
    private static final String PROP_TRANSLATE_Y = "translateY";
    private static final String PROP_Z_INDEX = "zIndex";
    private static MatrixDecompositionContext sMatrixDecompositionContext = new MatrixDecompositionContext();
    private static double[] sTransformDecompositionArray = new double[16];

    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(T view, int backgroundColor) {
        view.setBackgroundColor(backgroundColor);
    }

    @ReactProp(name = "transform")
    public void setTransform(T view, ReadableArray matrix) {
        if (matrix == null) {
            resetTransformProperty(view);
        } else {
            setTransformProperty(view, matrix);
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(T view, float opacity) {
        view.setAlpha(opacity);
    }

    @ReactProp(name = "elevation")
    public void setElevation(T view, float elevation) {
        if (VERSION.SDK_INT >= 21) {
            view.setElevation(PixelUtil.toPixelFromDIP(elevation));
        }
    }

    @ReactProp(name = "zIndex")
    public void setZIndex(T view, float zIndex) {
        ViewGroupManager.setViewZIndex(view, Math.round(zIndex));
    }

    @ReactProp(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(T view, boolean useHWTexture) {
        view.setLayerType(useHWTexture ? 2 : 0, null);
    }

    @ReactProp(name = "testID")
    public void setTestId(T view, String testId) {
        view.setTag(testId);
    }

    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(T view, String accessibilityLabel) {
        view.setContentDescription(accessibilityLabel);
    }

    @ReactProp(name = "accessibilityComponentType")
    public void setAccessibilityComponentType(T view, String accessibilityComponentType) {
        AccessibilityHelper.updateAccessibilityComponentType(view, accessibilityComponentType);
    }

    @ReactProp(name = "importantForAccessibility")
    public void setImportantForAccessibility(T view, String importantForAccessibility) {
        if (importantForAccessibility == null || importantForAccessibility.equals(ReactScrollViewHelper.AUTO)) {
            view.setImportantForAccessibility(0);
        } else if (importantForAccessibility.equals("yes")) {
            view.setImportantForAccessibility(1);
        } else if (importantForAccessibility.equals("no")) {
            view.setImportantForAccessibility(2);
        } else if (importantForAccessibility.equals("no-hide-descendants")) {
            view.setImportantForAccessibility(4);
        }
    }

    @ReactProp(name = "rotation")
    @Deprecated
    public void setRotation(T view, float rotation) {
        view.setRotation(rotation);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(T view, float scaleX) {
        view.setScaleX(scaleX);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(T view, float scaleY) {
        view.setScaleY(scaleY);
    }

    @ReactProp(defaultFloat = 0.0f, name = "translateX")
    @Deprecated
    public void setTranslateX(T view, float translateX) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(translateX));
    }

    @ReactProp(defaultFloat = 0.0f, name = "translateY")
    @Deprecated
    public void setTranslateY(T view, float translateY) {
        view.setTranslationY(PixelUtil.toPixelFromDIP(translateY));
    }

    @ReactProp(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(T view, String liveRegion) {
        if (VERSION.SDK_INT < 19) {
            return;
        }
        if (liveRegion == null || liveRegion.equals(SchedulerSupport.NONE)) {
            view.setAccessibilityLiveRegion(0);
        } else if (liveRegion.equals("polite")) {
            view.setAccessibilityLiveRegion(1);
        } else if (liveRegion.equals("assertive")) {
            view.setAccessibilityLiveRegion(2);
        }
    }

    private static void setTransformProperty(View view, ReadableArray transforms) {
        TransformHelper.processTransform(transforms, sTransformDecompositionArray);
        MatrixMathHelper.decomposeMatrix(sTransformDecompositionArray, sMatrixDecompositionContext);
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[1]));
        view.setRotation((float) sMatrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float) sMatrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float) sMatrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float) sMatrixDecompositionContext.scale[0]);
        view.setScaleY((float) sMatrixDecompositionContext.scale[1]);
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
        view.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(FlexItem.FLEX_SHRINK_DEFAULT);
        view.setScaleY(FlexItem.FLEX_SHRINK_DEFAULT);
    }
}
