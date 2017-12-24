package com.facebook.react.views.switchview;

import android.graphics.PorterDuff.Mode;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ReactSwitchManager extends SimpleViewManager<ReactSwitch> {
    private static final OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new 1();
    private static final String REACT_CLASS = "AndroidSwitch";

    public String getName() {
        return REACT_CLASS;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSwitchShadowNode(null);
    }

    public Class getShadowNodeClass() {
        return ReactSwitchShadowNode.class;
    }

    protected ReactSwitch createViewInstance(ThemedReactContext context) {
        ReactSwitch view = new ReactSwitch(context);
        view.setShowText(false);
        return view;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwitch view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(name = "on")
    public void setOn(ReactSwitch view, boolean on) {
        view.setOnCheckedChangeListener(null);
        view.setOn(on);
        view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSwitch view, Integer color) {
        if (color == null) {
            view.getThumbDrawable().clearColorFilter();
        } else {
            view.getThumbDrawable().setColorFilter(color.intValue(), Mode.MULTIPLY);
        }
    }

    @ReactProp(customType = "Color", name = "trackTintColor")
    public void setTrackTintColor(ReactSwitch view, Integer color) {
        if (color == null) {
            view.getTrackDrawable().clearColorFilter();
        } else {
            view.getTrackDrawable().setColorFilter(color.intValue(), Mode.MULTIPLY);
        }
    }

    protected void addEventEmitters(ThemedReactContext reactContext, ReactSwitch view) {
        view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }
}
