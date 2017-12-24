package com.facebook.react.views.image;

import android.graphics.PorterDuff.Mode;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.yoga.YogaConstants;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTImageView")
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    protected static final String REACT_CLASS = "RCTImageView";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;

    public String getName() {
        return REACT_CLASS;
    }

    public ReactImageManager(AbstractDraweeControllerBuilder draweeControllerBuilder, Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public ReactImageView createViewInstance(ThemedReactContext context) {
        return new ReactImageView(context, getDraweeControllerBuilder(), getCallerContext());
    }

    @ReactProp(name = "src")
    public void setSource(ReactImageView view, @Nullable ReadableArray sources) {
        view.setSource(sources);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactImageView view, @Nullable String source) {
        view.setLoadingIndicatorSource(source);
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(ReactImageView view, @Nullable Integer borderColor) {
        if (borderColor == null) {
            view.setBorderColor(0);
        } else {
            view.setBorderColor(borderColor.intValue());
        }
    }

    @ReactProp(name = "overlayColor")
    public void setOverlayColor(ReactImageView view, @Nullable Integer overlayColor) {
        if (overlayColor == null) {
            view.setOverlayColor(0);
        } else {
            view.setOverlayColor(overlayColor.intValue());
        }
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(ReactImageView view, float borderWidth) {
        view.setBorderWidth(borderWidth);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactImageView view, int index, float borderRadius) {
        if (!YogaConstants.isUndefined(borderRadius)) {
            borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactImageView view, @Nullable String resizeMode) {
        view.setScaleType(ImageResizeMode.toScaleType(resizeMode));
    }

    @ReactProp(name = "resizeMethod")
    public void setResizeMethod(ReactImageView view, @Nullable String resizeMethod) {
        if (resizeMethod == null || ReactScrollViewHelper.AUTO.equals(resizeMethod)) {
            view.setResizeMethod(ImageResizeMethod.AUTO);
        } else if ("resize".equals(resizeMethod)) {
            view.setResizeMethod(ImageResizeMethod.RESIZE);
        } else if ("scale".equals(resizeMethod)) {
            view.setResizeMethod(ImageResizeMethod.SCALE);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid resize method: '" + resizeMethod + "'");
        }
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(ReactImageView view, @Nullable Integer tintColor) {
        if (tintColor == null) {
            view.clearColorFilter();
        } else {
            view.setColorFilter(tintColor.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactImageView view, boolean enabled) {
        view.setProgressiveRenderingEnabled(enabled);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(ReactImageView view, int durationMs) {
        view.setFadeDuration(durationMs);
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactImageView view, boolean shouldNotifyLoadEvents) {
        view.setShouldNotifyLoadEvents(shouldNotifyLoadEvents);
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(ImageLoadEvent.eventNameForType(4), MapBuilder.of("registrationName", "onLoadStart"), ImageLoadEvent.eventNameForType(2), MapBuilder.of("registrationName", "onLoad"), ImageLoadEvent.eventNameForType(1), MapBuilder.of("registrationName", "onError"), ImageLoadEvent.eventNameForType(3), MapBuilder.of("registrationName", "onLoadEnd"));
    }

    protected void onAfterUpdateTransaction(ReactImageView view) {
        super.onAfterUpdateTransaction(view);
        view.maybeUpdateView();
    }
}
