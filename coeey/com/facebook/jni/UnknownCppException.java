package com.facebook.jni;

import com.facebook.internal.AnalyticsEvents;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class UnknownCppException extends CppException {
    @DoNotStrip
    public UnknownCppException() {
        super(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    }

    @DoNotStrip
    public UnknownCppException(String message) {
        super(message);
    }
}
