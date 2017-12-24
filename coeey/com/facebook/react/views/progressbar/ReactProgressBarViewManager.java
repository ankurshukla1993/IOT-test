package com.facebook.react.views.progressbar;

import android.content.Context;
import android.widget.ProgressBar;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidProgressBar")
public class ReactProgressBarViewManager extends BaseViewManager<ProgressBarContainerView, ProgressBarShadowNode> {
    static final String DEFAULT_STYLE = "Normal";
    static final String PROP_ANIMATING = "animating";
    static final String PROP_INDETERMINATE = "indeterminate";
    static final String PROP_PROGRESS = "progress";
    static final String PROP_STYLE = "styleAttr";
    protected static final String REACT_CLASS = "AndroidProgressBar";
    private static Object sProgressBarCtorLock = new Object();

    public static ProgressBar createProgressBar(Context context, int style) {
        ProgressBar progressBar;
        synchronized (sProgressBarCtorLock) {
            progressBar = new ProgressBar(context, null, style);
        }
        return progressBar;
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected ProgressBarContainerView createViewInstance(ThemedReactContext context) {
        return new ProgressBarContainerView(context);
    }

    @ReactProp(name = "styleAttr")
    public void setStyle(ProgressBarContainerView view, @Nullable String styleName) {
        view.setStyle(styleName);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ProgressBarContainerView view, @Nullable Integer color) {
        view.setColor(color);
    }

    @ReactProp(name = "indeterminate")
    public void setIndeterminate(ProgressBarContainerView view, boolean indeterminate) {
        view.setIndeterminate(indeterminate);
    }

    @ReactProp(name = "progress")
    public void setProgress(ProgressBarContainerView view, double progress) {
        view.setProgress(progress);
    }

    @ReactProp(name = "animating")
    public void setAnimating(ProgressBarContainerView view, boolean animating) {
        view.setAnimating(animating);
    }

    public ProgressBarShadowNode createShadowNodeInstance() {
        return new ProgressBarShadowNode();
    }

    public Class<ProgressBarShadowNode> getShadowNodeClass() {
        return ProgressBarShadowNode.class;
    }

    public void updateExtraData(ProgressBarContainerView root, Object extraData) {
    }

    protected void onAfterUpdateTransaction(ProgressBarContainerView view) {
        view.apply();
    }

    static int getStyleFromString(@Nullable String styleStr) {
        if (styleStr == null) {
            throw new JSApplicationIllegalArgumentException("ProgressBar needs to have a style, null received");
        } else if (styleStr.equals("Horizontal")) {
            return 16842872;
        } else {
            if (styleStr.equals("Small")) {
                return 16842873;
            }
            if (styleStr.equals("Large")) {
                return 16842874;
            }
            if (styleStr.equals("Inverse")) {
                return 16843399;
            }
            if (styleStr.equals("SmallInverse")) {
                return 16843400;
            }
            if (styleStr.equals("LargeInverse")) {
                return 16843401;
            }
            if (styleStr.equals(DEFAULT_STYLE)) {
                return 16842871;
            }
            throw new JSApplicationIllegalArgumentException("Unknown ProgressBar style: " + styleStr);
        }
    }
}
