package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import javax.annotation.Nullable;

class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    LayoutUpdateAnimation() {
    }

    boolean isValid() {
        return this.mDurationMs > 0;
    }

    @Nullable
    Animation createAnimationImpl(View view, int x, int y, int width, int height) {
        boolean animateLocation;
        if (view.getX() == ((float) x) && view.getY() == ((float) y)) {
            animateLocation = false;
        } else {
            animateLocation = true;
        }
        boolean animateSize;
        if (view.getWidth() == width && view.getHeight() == height) {
            animateSize = false;
        } else {
            animateSize = true;
        }
        if (!animateLocation && !animateSize) {
            return null;
        }
        if (animateLocation && animateSize) {
        }
        return new PositionAndSizeAnimation(view, x, y, width, height);
    }
}
