package com.facebook.drawee.interfaces;

import android.graphics.drawable.Animatable;
import android.view.MotionEvent;
import javax.annotation.Nullable;

public interface DraweeController {
    Animatable getAnimatable();

    @Nullable
    DraweeHierarchy getHierarchy();

    void onAttach();

    void onDetach();

    boolean onTouchEvent(MotionEvent motionEvent);

    void setHierarchy(@Nullable DraweeHierarchy draweeHierarchy);
}
