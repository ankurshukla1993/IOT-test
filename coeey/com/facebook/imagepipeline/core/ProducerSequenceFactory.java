package com.facebook.imagepipeline.core;

import android.net.Uri;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.RemoveImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.HashMap;
import java.util.Map;

public class ProducerSequenceFactory {
    private static final int MAX_SIMULTANEOUS_FILE_FETCH_AND_RESIZE = 5;
    @VisibleForTesting
    Producer<EncodedImage> mBackgroundNetworkFetchToEncodedMemorySequence;
    @VisibleForTesting
    Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> mCloseableImagePrefetchSequences = new HashMap();
    private Producer<EncodedImage> mCommonNetworkFetchToEncodedMemorySequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mDataFetchSequence;
    private final boolean mDownsampleEnabled;
    @VisibleForTesting
    Producer<CloseableReference<PooledByteBuffer>> mEncodedImageProducerSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalAssetFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalContentUriFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalImageFileFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalResourceFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mLocalVideoFileFetchSequence;
    @VisibleForTesting
    Producer<CloseableReference<CloseableImage>> mNetworkFetchSequence;
    @VisibleForTesting
    Producer<Void> mNetworkFetchToEncodedMemoryPrefetchSequence;
    private final NetworkFetcher mNetworkFetcher;
    @VisibleForTesting
    Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> mPostprocessorSequences = new HashMap();
    private final ProducerFactory mProducerFactory;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;
    private final boolean mWebpSupportEnabled;

    public ProducerSequenceFactory(ProducerFactory producerFactory, NetworkFetcher networkFetcher, boolean resizeAndRotateEnabledForNetwork, boolean downsampleEnabled, boolean webpSupportEnabled, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        this.mProducerFactory = producerFactory;
        this.mNetworkFetcher = networkFetcher;
        this.mResizeAndRotateEnabledForNetwork = resizeAndRotateEnabledForNetwork;
        this.mDownsampleEnabled = downsampleEnabled;
        this.mWebpSupportEnabled = webpSupportEnabled;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
    }

    public Producer<CloseableReference<PooledByteBuffer>> getEncodedImageProducerSequence(ImageRequest imageRequest) {
        validateEncodedImageRequest(imageRequest);
        synchronized (this) {
            if (this.mEncodedImageProducerSequence == null) {
                this.mEncodedImageProducerSequence = new RemoveImageTransformMetaDataProducer(getBackgroundNetworkFetchToEncodedMemorySequence());
            }
        }
        return this.mEncodedImageProducerSequence;
    }

    public Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        validateEncodedImageRequest(imageRequest);
        return getNetworkFetchToEncodedMemoryPrefetchSequence();
    }

    private static void validateEncodedImageRequest(ImageRequest imageRequest) {
        Preconditions.checkNotNull(imageRequest);
        Preconditions.checkArgument(UriUtil.isNetworkUri(imageRequest.getSourceUri()));
        Preconditions.checkArgument(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest$RequestLevel.ENCODED_MEMORY_CACHE.getValue());
    }

    public Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> pipelineSequence = getBasicDecodedImageSequence(imageRequest);
        if (imageRequest.getPostprocessor() != null) {
            return getPostprocessorSequence(pipelineSequence);
        }
        return pipelineSequence;
    }

    public Producer<Void> getDecodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        return getDecodedImagePrefetchSequence(getBasicDecodedImageSequence(imageRequest));
    }

    private Producer<CloseableReference<CloseableImage>> getBasicDecodedImageSequence(ImageRequest imageRequest) {
        Preconditions.checkNotNull(imageRequest);
        Uri uri = imageRequest.getSourceUri();
        Preconditions.checkNotNull(uri, "Uri is null.");
        if (UriUtil.isNetworkUri(uri)) {
            return getNetworkFetchSequence();
        }
        if (UriUtil.isLocalFileUri(uri)) {
            if (MediaUtils.isVideo(MediaUtils.extractMime(uri.getPath()))) {
                return getLocalVideoFileFetchSequence();
            }
            return getLocalImageFileFetchSequence();
        } else if (UriUtil.isLocalContentUri(uri)) {
            return getLocalContentUriFetchSequence();
        } else {
            if (UriUtil.isLocalAssetUri(uri)) {
                return getLocalAssetFetchSequence();
            }
            if (UriUtil.isLocalResourceUri(uri)) {
                return getLocalResourceFetchSequence();
            }
            if (UriUtil.isDataUri(uri)) {
                return getDataFetchSequence();
            }
            String uriString = uri.toString();
            if (uriString.length() > 30) {
                uriString = uriString.substring(0, 30) + "...";
            }
            throw new RuntimeException("Unsupported uri scheme! Uri is: " + uriString);
        }
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getNetworkFetchSequence() {
        if (this.mNetworkFetchSequence == null) {
            this.mNetworkFetchSequence = newBitmapCacheGetToDecodeSequence(getCommonNetworkFetchToEncodedMemorySequence());
        }
        return this.mNetworkFetchSequence;
    }

    private synchronized Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        if (this.mBackgroundNetworkFetchToEncodedMemorySequence == null) {
            this.mBackgroundNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newBackgroundThreadHandoffProducer(getCommonNetworkFetchToEncodedMemorySequence(), this.mThreadHandoffProducerQueue);
        }
        return this.mBackgroundNetworkFetchToEncodedMemorySequence;
    }

    private synchronized Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence() {
        if (this.mNetworkFetchToEncodedMemoryPrefetchSequence == null) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mNetworkFetchToEncodedMemoryPrefetchSequence = ProducerFactory.newSwallowResultProducer(getBackgroundNetworkFetchToEncodedMemorySequence());
        }
        return this.mNetworkFetchToEncodedMemoryPrefetchSequence;
    }

    private synchronized Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        if (this.mCommonNetworkFetchToEncodedMemorySequence == null) {
            this.mCommonNetworkFetchToEncodedMemorySequence = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(this.mProducerFactory.newNetworkFetchProducer(this.mNetworkFetcher)));
            if (this.mResizeAndRotateEnabledForNetwork && !this.mDownsampleEnabled) {
                this.mCommonNetworkFetchToEncodedMemorySequence = this.mProducerFactory.newResizeAndRotateProducer(this.mCommonNetworkFetchToEncodedMemorySequence);
            }
        }
        return this.mCommonNetworkFetchToEncodedMemorySequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence() {
        if (this.mLocalImageFileFetchSequence == null) {
            this.mLocalImageFileFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalFileFetchProducer());
        }
        return this.mLocalImageFileFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        if (this.mLocalVideoFileFetchSequence == null) {
            this.mLocalVideoFileFetchSequence = newBitmapCacheGetToBitmapCacheSequence(this.mProducerFactory.newLocalVideoThumbnailProducer());
        }
        return this.mLocalVideoFileFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        if (this.mLocalContentUriFetchSequence == null) {
            this.mLocalContentUriFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalContentUriFetchProducer(), new ThumbnailProducer[]{this.mProducerFactory.newLocalContentUriThumbnailFetchProducer(), this.mProducerFactory.newLocalExifThumbnailProducer()});
        }
        return this.mLocalContentUriFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        if (this.mLocalResourceFetchSequence == null) {
            this.mLocalResourceFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalResourceFetchProducer());
        }
        return this.mLocalResourceFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        if (this.mLocalAssetFetchSequence == null) {
            this.mLocalAssetFetchSequence = newBitmapCacheGetToLocalTransformSequence(this.mProducerFactory.newLocalAssetFetchProducer());
        }
        return this.mLocalAssetFetchSequence;
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        if (this.mDataFetchSequence == null) {
            Producer<EncodedImage> inputProducer = this.mProducerFactory.newDataFetchProducer();
            if (VERSION.SDK_INT < 18 && !this.mWebpSupportEnabled) {
                inputProducer = this.mProducerFactory.newWebpTranscodeProducer(inputProducer);
            }
            ProducerFactory producerFactory = this.mProducerFactory;
            inputProducer = ProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
            if (!this.mDownsampleEnabled) {
                inputProducer = this.mProducerFactory.newResizeAndRotateProducer(inputProducer);
            }
            this.mDataFetchSequence = newBitmapCacheGetToDecodeSequence(inputProducer);
        }
        return this.mDataFetchSequence;
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer) {
        return newBitmapCacheGetToLocalTransformSequence(inputProducer, new ThumbnailProducer[]{this.mProducerFactory.newLocalExifThumbnailProducer()});
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer, ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        return newBitmapCacheGetToDecodeSequence(newLocalTransformationsSequence(newEncodedCacheMultiplexToTranscodeSequence(inputProducer), thumbnailProducers));
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> inputProducer) {
        return newBitmapCacheGetToBitmapCacheSequence(this.mProducerFactory.newDecodeProducer(inputProducer));
    }

    private Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(Producer<EncodedImage> inputProducer) {
        if (VERSION.SDK_INT < 18 && !this.mWebpSupportEnabled) {
            inputProducer = this.mProducerFactory.newWebpTranscodeProducer(inputProducer);
        }
        return this.mProducerFactory.newEncodedCacheKeyMultiplexProducer(this.mProducerFactory.newEncodedMemoryCacheProducer(this.mProducerFactory.newDiskCacheProducer(inputProducer)));
    }

    private Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        return this.mProducerFactory.newBitmapMemoryCacheGetProducer(this.mProducerFactory.newBackgroundThreadHandoffProducer(this.mProducerFactory.newBitmapMemoryCacheKeyMultiplexProducer(this.mProducerFactory.newBitmapMemoryCacheProducer(inputProducer)), this.mThreadHandoffProducerQueue));
    }

    private Producer<EncodedImage> newLocalTransformationsSequence(Producer<EncodedImage> inputProducer, ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        Producer<EncodedImage> localImageProducer = ProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
        if (!this.mDownsampleEnabled) {
            localImageProducer = this.mProducerFactory.newResizeAndRotateProducer(localImageProducer);
        }
        ThrottlingProducer<EncodedImage> localImageThrottlingProducer = this.mProducerFactory.newThrottlingProducer(5, localImageProducer);
        ProducerFactory producerFactory = this.mProducerFactory;
        return ProducerFactory.newBranchOnSeparateImagesProducer(newLocalThumbnailProducer(thumbnailProducers), localImageThrottlingProducer);
    }

    private Producer<EncodedImage> newLocalThumbnailProducer(ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        ThumbnailBranchProducer thumbnailBranchProducer = this.mProducerFactory.newThumbnailBranchProducer(thumbnailProducers);
        return this.mDownsampleEnabled ? thumbnailBranchProducer : this.mProducerFactory.newResizeAndRotateProducer(thumbnailBranchProducer);
    }

    private synchronized Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        if (!this.mPostprocessorSequences.containsKey(inputProducer)) {
            this.mPostprocessorSequences.put(inputProducer, this.mProducerFactory.newPostprocessorBitmapMemoryCacheProducer(this.mProducerFactory.newPostprocessorProducer(inputProducer)));
        }
        return (Producer) this.mPostprocessorSequences.get(inputProducer);
    }

    private synchronized Producer<Void> getDecodedImagePrefetchSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        if (!this.mCloseableImagePrefetchSequences.containsKey(inputProducer)) {
            ProducerFactory producerFactory = this.mProducerFactory;
            this.mCloseableImagePrefetchSequences.put(inputProducer, ProducerFactory.newSwallowResultProducer(inputProducer));
        }
        return (Producer) this.mCloseableImagePrefetchSequences.get(inputProducer);
    }
}
