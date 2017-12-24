package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

public class ThumbnailBranchProducer implements Producer<EncodedImage> {
    private final ThumbnailProducer<EncodedImage>[] mThumbnailProducers;

    private class ThumbnailConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ProducerContext mProducerContext;
        private final int mProducerIndex;
        private final ResizeOptions mResizeOptions = this.mProducerContext.getImageRequest().getResizeOptions();

        public ThumbnailConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext, int producerIndex) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerIndex = producerIndex;
        }

        protected void onNewResultImpl(EncodedImage newResult, boolean isLast) {
            if (newResult != null && (!isLast || ThumbnailSizeChecker.isImageBigEnough(newResult, this.mResizeOptions))) {
                getConsumer().onNewResult(newResult, isLast);
            } else if (isLast) {
                EncodedImage.closeSafely(newResult);
                if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext)) {
                    getConsumer().onNewResult(null, true);
                }
            }
        }

        protected void onFailureImpl(Throwable t) {
            if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext)) {
                getConsumer().onFailure(t);
            }
        }
    }

    public ThumbnailBranchProducer(ThumbnailProducer<EncodedImage>... thumbnailProducers) {
        this.mThumbnailProducers = (ThumbnailProducer[]) Preconditions.checkNotNull(thumbnailProducers);
        Preconditions.checkElementIndex(0, this.mThumbnailProducers.length);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        if (context.getImageRequest().getResizeOptions() == null) {
            consumer.onNewResult(null, true);
        } else if (!produceResultsFromThumbnailProducer(0, consumer, context)) {
            consumer.onNewResult(null, true);
        }
    }

    private boolean produceResultsFromThumbnailProducer(int startIndex, Consumer<EncodedImage> consumer, ProducerContext context) {
        int producerIndex = findFirstProducerForSize(startIndex, context.getImageRequest().getResizeOptions());
        if (producerIndex == -1) {
            return false;
        }
        this.mThumbnailProducers[producerIndex].produceResults(new ThumbnailConsumer(consumer, context, producerIndex), context);
        return true;
    }

    private int findFirstProducerForSize(int startIndex, ResizeOptions resizeOptions) {
        for (int i = startIndex; i < this.mThumbnailProducers.length; i++) {
            if (this.mThumbnailProducers[i].canProvideImageForSize(resizeOptions)) {
                return i;
            }
        }
        return -1;
    }
}
