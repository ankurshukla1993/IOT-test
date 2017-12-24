package com.facebook.imagepipeline.animated.factory;

import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.core.ExecutorSupplier;

public class AnimatedFactoryProvider {
    private static AnimatedFactory sImpl = null;
    private static boolean sImplLoaded;

    public static AnimatedFactory getAnimatedFactory(PlatformBitmapFactory platformBitmapFactory, ExecutorSupplier executorSupplier) {
        if (!sImplLoaded) {
            try {
                sImpl = (AnimatedFactory) Class.forName("com.facebook.imagepipeline.animated.factory.AnimatedFactoryImplSupport").getConstructor(new Class[]{PlatformBitmapFactory.class, ExecutorSupplier.class}).newInstance(new Object[]{platformBitmapFactory, executorSupplier});
            } catch (Throwable th) {
            }
            if (sImpl != null) {
                sImplLoaded = true;
                return sImpl;
            }
            try {
                sImpl = (AnimatedFactory) Class.forName("com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl").getConstructor(new Class[]{PlatformBitmapFactory.class, ExecutorSupplier.class}).newInstance(new Object[]{platformBitmapFactory, executorSupplier});
            } catch (Throwable th2) {
            }
            sImplLoaded = true;
        }
        return sImpl;
    }
}
