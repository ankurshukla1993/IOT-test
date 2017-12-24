package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

public class BaseControllerListener<INFO> implements ControllerListener<INFO> {
    private static final ControllerListener<Object> NO_OP_LISTENER = new BaseControllerListener();

    public static <INFO> ControllerListener<INFO> getNoOpListener() {
        return NO_OP_LISTENER;
    }

    public void onSubmit(String id, Object callerContext) {
    }

    public void onFinalImageSet(String id, @Nullable INFO info, @Nullable Animatable animatable) {
    }

    public void onIntermediateImageSet(String id, @Nullable INFO info) {
    }

    public void onIntermediateImageFailed(String id, Throwable throwable) {
    }

    public void onFailure(String id, Throwable throwable) {
    }

    public void onRelease(String id) {
    }
}
