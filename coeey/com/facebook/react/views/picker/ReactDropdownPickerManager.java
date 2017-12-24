package com.facebook.react.views.picker;

import android.content.Context;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;

@ReactModule(name = "AndroidDropdownPicker")
public class ReactDropdownPickerManager extends ReactPickerManager {
    protected static final String REACT_CLASS = "AndroidDropdownPicker";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactPicker createViewInstance(ThemedReactContext reactContext) {
        return new ReactPicker((Context) reactContext, 1);
    }
}
