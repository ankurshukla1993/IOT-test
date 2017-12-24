package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    @VisibleForTesting
    static final String PRODUCER_NAME = "VideoThumbnailProducer";
    private final Executor mExecutor;

    public LocalVideoThumbnailProducer(Executor executor) {
        this.mExecutor = executor;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        ProducerListener listener = producerContext.getListener();
        String requestId = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final StatefulProducerRunnable cancellableProducerRunnable = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, listener, PRODUCER_NAME, requestId) {
            protected CloseableReference<CloseableImage> getResult() throws Exception {
                Bitmap thumbnailBitmap = ThumbnailUtils.createVideoThumbnail(imageRequest.getSourceFile().getPath(), LocalVideoThumbnailProducer.calculateKind(imageRequest));
                if (thumbnailBitmap == null) {
                    return null;
                }
                return CloseableReference.of(new CloseableStaticBitmap(thumbnailBitmap, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0));
            }

            protected Map<String, String> getExtraMapOnSuccess(CloseableReference<CloseableImage> result) {
                return ImmutableMap.of(LocalVideoThumbnailProducer.CREATED_THUMBNAIL, String.valueOf(result != null));
            }

            protected void disposeResult(CloseableReference<CloseableImage> result) {
                CloseableReference.closeSafely(result);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                cancellableProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(cancellableProducerRunnable);
    }

    private static int calculateKind(ImageRequest imageRequest) {
        if (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) {
            return 1;
        }
        return 3;
    }
}
