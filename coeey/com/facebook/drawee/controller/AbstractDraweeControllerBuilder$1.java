package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

class AbstractDraweeControllerBuilder$1 extends BaseControllerListener<Object> {
    AbstractDraweeControllerBuilder$1() {
    }

    public void onFinalImageSet(String id, @Nullable Object info, @Nullable Animatable anim) {
        if (anim != null) {
            anim.start();
        }
    }
}
