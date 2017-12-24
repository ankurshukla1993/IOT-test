package com.facebook.imagepipeline.request;

import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest.ImageType;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import javax.annotation.Nullable;

public class ImageRequestBuilder {
    private boolean mAutoRotateEnabled = false;
    private boolean mDiskCacheEnabled = true;
    private ImageDecodeOptions mImageDecodeOptions = ImageDecodeOptions.defaults();
    private ImageType mImageType = ImageType.DEFAULT;
    private boolean mLocalThumbnailPreviewsEnabled = false;
    private RequestLevel mLowestPermittedRequestLevel = RequestLevel.FULL_FETCH;
    @Nullable
    private Postprocessor mPostprocessor = null;
    private boolean mProgressiveRenderingEnabled = false;
    private Priority mRequestPriority = Priority.HIGH;
    @Nullable
    private ResizeOptions mResizeOptions = null;
    private Uri mSourceUri = null;

    public static ImageRequestBuilder newBuilderWithSource(Uri uri) {
        return new ImageRequestBuilder().setSource(uri);
    }

    public static ImageRequestBuilder newBuilderWithResourceId(int resId) {
        return newBuilderWithSource(new Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(resId)).build());
    }

    public static ImageRequestBuilder fromRequest(ImageRequest imageRequest) {
        return newBuilderWithSource(imageRequest.getSourceUri()).setAutoRotateEnabled(imageRequest.getAutoRotateEnabled()).setImageDecodeOptions(imageRequest.getImageDecodeOptions()).setImageType(imageRequest.getImageType()).setLocalThumbnailPreviewsEnabled(imageRequest.getLocalThumbnailPreviewsEnabled()).setLowestPermittedRequestLevel(imageRequest.getLowestPermittedRequestLevel()).setPostprocessor(imageRequest.getPostprocessor()).setProgressiveRenderingEnabled(imageRequest.getProgressiveRenderingEnabled()).setRequestPriority(imageRequest.getPriority()).setResizeOptions(imageRequest.getResizeOptions());
    }

    private ImageRequestBuilder() {
    }

    public ImageRequestBuilder setSource(Uri uri) {
        Preconditions.checkNotNull(uri);
        this.mSourceUri = uri;
        return this;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public ImageRequestBuilder setLowestPermittedRequestLevel(RequestLevel requestLevel) {
        this.mLowestPermittedRequestLevel = requestLevel;
        return this;
    }

    public RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    public ImageRequestBuilder setAutoRotateEnabled(boolean enabled) {
        this.mAutoRotateEnabled = enabled;
        return this;
    }

    public boolean isAutoRotateEnabled() {
        return this.mAutoRotateEnabled;
    }

    public ImageRequestBuilder setResizeOptions(ResizeOptions resizeOptions) {
        this.mResizeOptions = resizeOptions;
        return this;
    }

    @Nullable
    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public ImageRequestBuilder setImageDecodeOptions(ImageDecodeOptions imageDecodeOptions) {
        this.mImageDecodeOptions = imageDecodeOptions;
        return this;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public ImageRequestBuilder setImageType(ImageType imageType) {
        this.mImageType = imageType;
        return this;
    }

    public ImageType getImageType() {
        return this.mImageType;
    }

    public ImageRequestBuilder setProgressiveRenderingEnabled(boolean enabled) {
        this.mProgressiveRenderingEnabled = enabled;
        return this;
    }

    public boolean isProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public ImageRequestBuilder setLocalThumbnailPreviewsEnabled(boolean enabled) {
        this.mLocalThumbnailPreviewsEnabled = enabled;
        return this;
    }

    public boolean isLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public ImageRequestBuilder disableDiskCache() {
        this.mDiskCacheEnabled = false;
        return this;
    }

    public boolean isDiskCacheEnabled() {
        return this.mDiskCacheEnabled && UriUtil.isNetworkUri(this.mSourceUri);
    }

    public ImageRequestBuilder setRequestPriority(Priority requestPriority) {
        this.mRequestPriority = requestPriority;
        return this;
    }

    public Priority getRequestPriority() {
        return this.mRequestPriority;
    }

    public ImageRequestBuilder setPostprocessor(Postprocessor postprocessor) {
        this.mPostprocessor = postprocessor;
        return this;
    }

    @Nullable
    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    public ImageRequest build() {
        validate();
        return new ImageRequest(this);
    }

    protected void validate() {
        if (this.mSourceUri == null) {
            throw new BuilderException("Source must be set!");
        }
        if (UriUtil.isLocalResourceUri(this.mSourceUri)) {
            if (!this.mSourceUri.isAbsolute()) {
                throw new BuilderException("Resource URI path must be absolute.");
            } else if (this.mSourceUri.getPath().isEmpty()) {
                throw new BuilderException("Resource URI must not be empty");
            } else {
                try {
                    Integer.parseInt(this.mSourceUri.getPath().substring(1));
                } catch (NumberFormatException e) {
                    throw new BuilderException("Resource URI path must be a resource id.");
                }
            }
        }
        if (UriUtil.isLocalAssetUri(this.mSourceUri) && !this.mSourceUri.isAbsolute()) {
            throw new BuilderException("Asset URI path must be absolute.");
        }
    }
}
