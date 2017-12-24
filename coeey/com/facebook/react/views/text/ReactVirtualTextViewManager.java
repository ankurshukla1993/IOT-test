package com.facebook.react.views.text;

import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "RCTVirtualText")
public class ReactVirtualTextViewManager extends ReactRawTextManager {
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTVirtualText";

    public String getName() {
        return REACT_CLASS;
    }
}
