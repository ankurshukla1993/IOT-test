package com.facebook.react.views.slider;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

public class ReactSliderManager extends SimpleViewManager<ReactSlider> {
    private static final OnSeekBarChangeListener ON_CHANGE_LISTENER = new 1();
    private static final String REACT_CLASS = "RCTSlider";
    private static final int STYLE = 16842875;

    public String getName() {
        return REACT_CLASS;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSliderShadowNode(null);
    }

    public Class getShadowNodeClass() {
        return ReactSliderShadowNode.class;
    }

    protected ReactSlider createViewInstance(ThemedReactContext context) {
        return new ReactSlider(context, null, STYLE);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSlider view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(defaultDouble = 0.0d, name = "value")
    public void setValue(ReactSlider view, double value) {
        view.setOnSeekBarChangeListener(null);
        view.setValue(value);
        view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    @ReactProp(defaultDouble = 0.0d, name = "minimumValue")
    public void setMinimumValue(ReactSlider view, double value) {
        view.setMinValue(value);
    }

    @ReactProp(defaultDouble = 1.0d, name = "maximumValue")
    public void setMaximumValue(ReactSlider view, double value) {
        view.setMaxValue(value);
    }

    @ReactProp(defaultDouble = 0.0d, name = "step")
    public void setStep(ReactSlider view, double value) {
        view.setStep(value);
    }

    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSlider view, Integer color) {
        if (color == null) {
            view.getThumb().clearColorFilter();
        } else {
            view.getThumb().setColorFilter(color.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(customType = "Color", name = "minimumTrackTintColor")
    public void setMinimumTrackTintColor(ReactSlider view, Integer color) {
        Drawable background = ((LayerDrawable) view.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908288);
        if (color == null) {
            background.clearColorFilter();
        } else {
            background.setColorFilter(color.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(customType = "Color", name = "maximumTrackTintColor")
    public void setMaximumTrackTintColor(ReactSlider view, Integer color) {
        Drawable progress = ((LayerDrawable) view.getProgressDrawable().getCurrent()).findDrawableByLayerId(16908301);
        if (color == null) {
            progress.clearColorFilter();
        } else {
            progress.setColorFilter(color.intValue(), Mode.SRC_IN);
        }
    }

    protected void addEventEmitters(ThemedReactContext reactContext, ReactSlider view) {
        view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(ReactSlidingCompleteEvent.EVENT_NAME, MapBuilder.of("registrationName", "onSlidingComplete"));
    }
}
