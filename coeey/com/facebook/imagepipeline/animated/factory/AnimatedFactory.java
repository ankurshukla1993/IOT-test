package com.facebook.imagepipeline.animated.factory;

import android.content.Context;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public interface AnimatedFactory {
    AnimatedDrawableFactory getAnimatedDrawableFactory(Context context);

    AnimatedImageFactory getAnimatedImageFactory();
}
