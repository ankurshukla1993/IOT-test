package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;

public class RemoveImageTransformMetaDataProducer implements Producer<CloseableReference<PooledByteBuffer>> {
    private final Producer<EncodedImage> mInputProducer;

    private class RemoveImageTransformMetaDataConsumer extends DelegatingConsumer<EncodedImage, CloseableReference<PooledByteBuffer>> {
        private RemoveImageTransformMetaDataConsumer(Consumer<CloseableReference<PooledByteBuffer>> consumer) {
            super(consumer);
        }

        protected void onNewResultImpl(EncodedImage newResult, boolean isLast) {
            CloseableReference<PooledByteBuffer> ret = null;
            try {
                if (EncodedImage.isValid(newResult)) {
                    ret = newResult.getByteBufferRef();
                }
                getConsumer().onNewResult(ret, isLast);
            } finally {
                CloseableReference.closeSafely(ret);
            }
        }
    }

    public RemoveImageTransformMetaDataProducer(Producer<EncodedImage> inputProducer) {
        this.mInputProducer = inputProducer;
    }

    public void produceResults(Consumer<CloseableReference<PooledByteBuffer>> consumer, ProducerContext context) {
        this.mInputProducer.produceResults(new RemoveImageTransformMetaDataConsumer(consumer), context);
    }
}
