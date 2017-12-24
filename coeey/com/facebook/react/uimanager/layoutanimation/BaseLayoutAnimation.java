package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.google.android.flexbox.FlexItem;

abstract class BaseLayoutAnimation extends AbstractLayoutAnimation {
    abstract boolean isReverse();

    BaseLayoutAnimation() {
    }

    boolean isValid() {
        return this.mDurationMs > 0 && this.mAnimatedProperty != null;
    }

    Animation createAnimationImpl(View view, int x, int y, int width, int height) {
        if (this.mAnimatedProperty != null) {
            float fromValue;
            switch (this.mAnimatedProperty) {
                case OPACITY:
                    if (isReverse()) {
                        fromValue = view.getAlpha();
                    } else {
                        fromValue = 0.0f;
                    }
                    return new OpacityAnimation(view, fromValue, isReverse() ? 0.0f : view.getAlpha());
                case SCALE_XY:
                    fromValue = isReverse() ? FlexItem.FLEX_SHRINK_DEFAULT : 0.0f;
                    float toValue = isReverse() ? 0.0f : FlexItem.FLEX_SHRINK_DEFAULT;
                    return new ScaleAnimation(fromValue, toValue, fromValue, toValue, 1, 0.5f, 1, 0.5f);
                default:
                    throw new IllegalViewOperationException("Missing animation for property : " + this.mAnimatedProperty);
            }
        }
        throw new IllegalViewOperationException("Missing animated property from animation config");
    }
}
