package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import java.util.concurrent.Executor;

public class PostprocessorProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String NAME = "PostprocessorProducer";
    @VisibleForTesting
    static final String POSTPROCESSOR = "Postprocessor";
    private final PlatformBitmapFactory mBitmapFactory;
    private final Executor mExecutor;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;

    class SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private SingleUsePostprocessorConsumer(PostprocessorConsumer postprocessorConsumer) {
            super(postprocessorConsumer);
        }

        protected void onNewResultImpl(CloseableReference<CloseableImage> newResult, boolean isLast) {
            if (isLast) {
                getConsumer().onNewResult(newResult, isLast);
            }
        }
    }

    public PostprocessorProducer(Producer<CloseableReference<CloseableImage>> inputProducer, PlatformBitmapFactory platformBitmapFactory, Executor executor) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
        this.mBitmapFactory = platformBitmapFactory;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext context) {
        Consumer<CloseableReference<CloseableImage>> postprocessorConsumer;
        ProducerListener listener = context.getListener();
        Postprocessor postprocessor = context.getImageRequest().getPostprocessor();
        PostprocessorConsumer basePostprocessorConsumer = new PostprocessorConsumer(this, consumer, listener, context.getId(), postprocessor, context);
        if (postprocessor instanceof RepeatedPostprocessor) {
            postprocessorConsumer = new RepeatedPostprocessorConsumer(this, basePostprocessorConsumer, (RepeatedPostprocessor) postprocessor, context, null);
        } else {
            postprocessorConsumer = new SingleUsePostprocessorConsumer(basePostprocessorConsumer);
        }
        this.mInputProducer.produceResults(postprocessorConsumer, context);
    }
}
