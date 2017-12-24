package com.facebook.react.views.picker;

import android.content.Context;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;

@ReactModule(name = "AndroidDialogPicker")
public class ReactDialogPickerManager extends ReactPickerManager {
    protected static final String REACT_CLASS = "AndroidDialogPicker";

    public String getName() {
        return REACT_CLASS;
    }

    protected ReactPicker createViewInstance(ThemedReactContext reactContext) {
        return new ReactPicker((Context) reactContext, 0);
    }
}
