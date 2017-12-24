package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class BranchOnSeparateImagesProducer implements Producer<EncodedImage> {
    private final Producer<EncodedImage> mInputProducer1;
    private final Producer<EncodedImage> mInputProducer2;

    private class OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private ProducerContext mProducerContext;

        private OnFirstImageConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
        }

        protected void onNewResultImpl(EncodedImage newResult, boolean isLast) {
            ImageRequest request = this.mProducerContext.getImageRequest();
            boolean isGoodEnough = ThumbnailSizeChecker.isImageBigEnough(newResult, request.getResizeOptions());
            if (newResult != null && (isGoodEnough || request.getLocalThumbnailPreviewsEnabled())) {
                Consumer consumer = getConsumer();
                boolean z = isLast && isGoodEnough;
                consumer.onNewResult(newResult, z);
            }
            if (isLast && !isGoodEnough) {
                EncodedImage.closeSafely(newResult);
                BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
            }
        }

        protected void onFailureImpl(Throwable t) {
            BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
        }
    }

    public BranchOnSeparateImagesProducer(Producer<EncodedImage> inputProducer1, Producer<EncodedImage> inputProducer2) {
        this.mInputProducer1 = inputProducer1;
        this.mInputProducer2 = inputProducer2;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        this.mInputProducer1.produceResults(new OnFirstImageConsumer(consumer, context), context);
    }
}
