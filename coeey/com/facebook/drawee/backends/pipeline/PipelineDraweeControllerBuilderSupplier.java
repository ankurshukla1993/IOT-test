package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.animated.factory.AnimatedDrawableFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.Set;

public class PipelineDraweeControllerBuilderSupplier implements Supplier<PipelineDraweeControllerBuilder> {
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Context mContext;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilderSupplier(Context context) {
        this(context, ImagePipelineFactory.getInstance());
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory) {
        this(context, imagePipelineFactory, null);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, Set<ControllerListener> boundControllerListeners) {
        this.mContext = context;
        this.mImagePipeline = imagePipelineFactory.getImagePipeline();
        AnimatedFactory animatedFactory = imagePipelineFactory.getAnimatedFactory();
        AnimatedDrawableFactory animatedDrawableFactory = null;
        if (animatedFactory != null) {
            animatedDrawableFactory = animatedFactory.getAnimatedDrawableFactory(context);
        }
        this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory(context.getResources(), DeferredReleaser.getInstance(), animatedDrawableFactory, UiThreadImmediateExecutorService.getInstance());
        this.mBoundControllerListeners = boundControllerListeners;
    }

    public PipelineDraweeControllerBuilder get() {
        return new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners);
    }
}
