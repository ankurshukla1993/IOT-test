package com.facebook.react.animation;

import com.google.android.flexbox.FlexItem;

public class ImmediateAnimation extends Animation {
    public ImmediateAnimation(int animationID, AnimationPropertyUpdater propertyUpdater) {
        super(animationID, propertyUpdater);
    }

    public void run() {
        onUpdate(FlexItem.FLEX_SHRINK_DEFAULT);
        finish();
    }
}
