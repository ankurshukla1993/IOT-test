package com.facebook.react.animation;

import android.view.View;

public class NoopAnimationPropertyUpdater implements AnimationPropertyUpdater {
    public void prepare(View view) {
    }

    public void onUpdate(View view, float value) {
    }

    public void onFinish(View view) {
    }
}
