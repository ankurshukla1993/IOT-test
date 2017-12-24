package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import java.util.Map;
import javax.annotation.Nullable;

abstract class AbstractLayoutAnimation {
    private static final Map<InterpolatorType, Interpolator> INTERPOLATOR = MapBuilder.of(InterpolatorType.LINEAR, new LinearInterpolator(), InterpolatorType.EASE_IN, new AccelerateInterpolator(), InterpolatorType.EASE_OUT, new DecelerateInterpolator(), InterpolatorType.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator(), InterpolatorType.SPRING, new SimpleSpringInterpolator());
    private static final boolean SLOWDOWN_ANIMATION_MODE = false;
    @Nullable
    protected AnimatedPropertyType mAnimatedProperty;
    private int mDelayMs;
    protected int mDurationMs;
    @Nullable
    private Interpolator mInterpolator;

    @Nullable
    abstract Animation createAnimationImpl(View view, int i, int i2, int i3, int i4);

    abstract boolean isValid();

    AbstractLayoutAnimation() {
    }

    public void reset() {
        this.mAnimatedProperty = null;
        this.mDurationMs = 0;
        this.mDelayMs = 0;
        this.mInterpolator = null;
    }

    public void initializeFromConfig(ReadableMap data, int globalDuration) {
        this.mAnimatedProperty = data.hasKey("property") ? AnimatedPropertyType.fromString(data.getString("property")) : null;
        if (data.hasKey("duration")) {
            globalDuration = data.getInt("duration");
        }
        this.mDurationMs = globalDuration;
        this.mDelayMs = data.hasKey("delay") ? data.getInt("delay") : 0;
        if (data.hasKey("type")) {
            this.mInterpolator = getInterpolator(InterpolatorType.fromString(data.getString("type")));
            if (!isValid()) {
                throw new IllegalViewOperationException("Invalid layout animation : " + data);
            }
            return;
        }
        throw new IllegalArgumentException("Missing interpolation type.");
    }

    @Nullable
    public final Animation createAnimation(View view, int x, int y, int width, int height) {
        if (!isValid()) {
            return null;
        }
        Animation animation = createAnimationImpl(view, x, y, width, height);
        if (animation == null) {
            return animation;
        }
        animation.setDuration((long) (this.mDurationMs * 1));
        animation.setStartOffset((long) (this.mDelayMs * 1));
        animation.setInterpolator(this.mInterpolator);
        return animation;
    }

    private static Interpolator getInterpolator(InterpolatorType type) {
        Interpolator interpolator = (Interpolator) INTERPOLATOR.get(type);
        if (interpolator != null) {
            return interpolator;
        }
        throw new IllegalArgumentException("Missing interpolator for type : " + type);
    }
}
