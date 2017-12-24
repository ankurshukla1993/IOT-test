package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.nativecode.WebpTranscoder;
import com.facebook.imagepipeline.nativecode.WebpTranscoderFactory;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class WebpTranscodeProducer implements Producer<EncodedImage> {
    private static final int DEFAULT_JPEG_QUALITY = 80;
    private static final String PRODUCER_NAME = "WebpTranscodeProducer";
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    private class WebpTranscodeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ProducerContext mContext;
        private TriState mShouldTranscodeWhenFinished = TriState.UNSET;

        public WebpTranscodeConsumer(Consumer<EncodedImage> consumer, ProducerContext context) {
            super(consumer);
            this.mContext = context;
        }

        protected void onNewResultImpl(@Nullable EncodedImage newResult, boolean isLast) {
            if (this.mShouldTranscodeWhenFinished == TriState.UNSET && newResult != null) {
                this.mShouldTranscodeWhenFinished = WebpTranscodeProducer.shouldTranscode(newResult);
            }
            if (this.mShouldTranscodeWhenFinished == TriState.NO) {
                getConsumer().onNewResult(newResult, isLast);
            } else if (!isLast) {
            } else {
                if (this.mShouldTranscodeWhenFinished != TriState.YES || newResult == null) {
                    getConsumer().onNewResult(newResult, isLast);
                } else {
                    WebpTranscodeProducer.this.transcodeLastResult(newResult, getConsumer(), this.mContext);
                }
            }
        }
    }

    public WebpTranscodeProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> inputProducer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        this.mInputProducer.produceResults(new WebpTranscodeConsumer(consumer, context), context);
    }

    private void transcodeLastResult(EncodedImage originalResult, Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Preconditions.checkNotNull(originalResult);
        final EncodedImage encodedImageCopy = EncodedImage.cloneOrNull(originalResult);
        this.mExecutor.execute(new StatefulProducerRunnable<EncodedImage>(consumer, producerContext.getListener(), PRODUCER_NAME, producerContext.getId()) {
            protected EncodedImage getResult() throws Exception {
                PooledByteBufferOutputStream outputStream = WebpTranscodeProducer.this.mPooledByteBufferFactory.newOutputStream();
                CloseableReference<PooledByteBuffer> ref;
                try {
                    WebpTranscodeProducer.doTranscode(encodedImageCopy, outputStream);
                    ref = CloseableReference.of(outputStream.toByteBuffer());
                    EncodedImage encodedImage = new EncodedImage(ref);
                    encodedImage.copyMetaDataFrom(encodedImageCopy);
                    CloseableReference.closeSafely(ref);
                    outputStream.close();
                    return encodedImage;
                } catch (Throwable th) {
                    outputStream.close();
                }
            }

            protected void disposeResult(EncodedImage result) {
                EncodedImage.closeSafely(result);
            }

            protected void onSuccess(EncodedImage result) {
                EncodedImage.closeSafely(encodedImageCopy);
                super.onSuccess(result);
            }

            protected void onFailure(Exception e) {
                EncodedImage.closeSafely(encodedImageCopy);
                super.onFailure(e);
            }

            protected void onCancellation() {
                EncodedImage.closeSafely(encodedImageCopy);
                super.onCancellation();
            }
        });
    }

    private static TriState shouldTranscode(EncodedImage encodedImage) {
        Preconditions.checkNotNull(encodedImage);
        ImageFormat imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        switch (imageFormat) {
            case WEBP_SIMPLE:
            case WEBP_LOSSLESS:
            case WEBP_EXTENDED:
            case WEBP_EXTENDED_WITH_ALPHA:
                WebpTranscoder webpTranscoder = WebpTranscoderFactory.getWebpTranscoder();
                if (webpTranscoder == null) {
                    return TriState.NO;
                }
                return TriState.valueOf(!webpTranscoder.isWebpNativelySupported(imageFormat));
            case UNKNOWN:
                return TriState.UNSET;
            default:
                return TriState.NO;
        }
    }

    private static void doTranscode(EncodedImage encodedImage, PooledByteBufferOutputStream outputStream) throws Exception {
        InputStream imageInputStream = encodedImage.getInputStream();
        switch (ImageFormatChecker.getImageFormat_WrapIOException(imageInputStream)) {
            case WEBP_SIMPLE:
            case WEBP_EXTENDED:
                WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToJpeg(imageInputStream, outputStream, 80);
                return;
            case WEBP_LOSSLESS:
            case WEBP_EXTENDED_WITH_ALPHA:
                WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToPng(imageInputStream, outputStream);
                return;
            default:
                throw new IllegalArgumentException("Wrong image format");
        }
    }
}
