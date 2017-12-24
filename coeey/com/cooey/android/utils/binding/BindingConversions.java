package com.cooey.android.utils.binding;

import android.databinding.BindingConversion;

public class BindingConversions {
    @BindingConversion
    public static int convertBooleanToVisibility(boolean isVisible) {
        return isVisible ? 0 : 8;
    }
}
