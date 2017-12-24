package com.facebook.imagepipeline.animated.factory;

import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.image.CloseableImage;

public interface AnimatedDrawableFactory {
    Drawable create(CloseableImage closeableImage);
}
