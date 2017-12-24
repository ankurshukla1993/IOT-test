package com.facebook.react.flat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.flat.FlatViewGroup.InvalidateCallback;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.MultiSourceHelper$MultiSourceResult;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

final class DrawImageWithDrawee extends AbstractDrawCommand implements DrawImage, ControllerListener {
    private static final String LOCAL_CONTENT_SCHEME = "content";
    private static final String LOCAL_FILE_SCHEME = "file";
    private int mBorderColor;
    private float mBorderRadius;
    private float mBorderWidth;
    @Nullable
    private InvalidateCallback mCallback;
    @Nullable
    private PorterDuffColorFilter mColorFilter;
    private int mFadeDuration = 300;
    private boolean mProgressiveRenderingEnabled;
    private int mReactTag;
    @Nullable
    private DraweeRequestHelper mRequestHelper;
    private ScaleType mScaleType = ImageResizeMode.defaultValue();
    private final List<ImageSource> mSources = new LinkedList();

    DrawImageWithDrawee() {
    }

    public boolean hasImageRequest() {
        return !this.mSources.isEmpty();
    }

    public void setSource(Context context, @Nullable ReadableArray sources) {
        this.mSources.clear();
        if (sources != null && sources.size() != 0) {
            if (sources.size() == 1) {
                this.mSources.add(new ImageSource(context, sources.getMap(0).getString(RNFetchBlobConst.DATA_ENCODE_URI)));
                return;
            }
            for (int idx = 0; idx < sources.size(); idx++) {
                ReadableMap source = sources.getMap(idx);
                this.mSources.add(new ImageSource(context, source.getString(RNFetchBlobConst.DATA_ENCODE_URI), source.getDouble("width"), source.getDouble("height")));
            }
        }
    }

    public void setTintColor(int tintColor) {
        if (tintColor == 0) {
            this.mColorFilter = null;
        } else {
            this.mColorFilter = new PorterDuffColorFilter(tintColor, Mode.SRC_ATOP);
        }
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setBorderWidth(float borderWidth) {
        this.mBorderWidth = borderWidth;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderRadius(float borderRadius) {
        this.mBorderRadius = borderRadius;
    }

    public float getBorderRadius() {
        return this.mBorderRadius;
    }

    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public void setFadeDuration(int fadeDuration) {
        this.mFadeDuration = fadeDuration;
    }

    public void setProgressiveRenderingEnabled(boolean enabled) {
        this.mProgressiveRenderingEnabled = enabled;
    }

    public void setReactTag(int reactTag) {
        this.mReactTag = reactTag;
    }

    public void onDraw(Canvas canvas) {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.getDrawable().draw(canvas);
        }
    }

    public void onAttached(InvalidateCallback callback) {
        this.mCallback = callback;
        if (this.mRequestHelper == null) {
            throw new RuntimeException("No DraweeRequestHelper - width: " + (getRight() - getLeft()) + " - height: " + (getBottom() - getTop()) + " - number of sources: " + this.mSources.size());
        }
        GenericDraweeHierarchy hierarchy = this.mRequestHelper.getHierarchy();
        RoundingParams roundingParams = hierarchy.getRoundingParams();
        if (shouldDisplayBorder()) {
            if (roundingParams == null) {
                roundingParams = new RoundingParams();
            }
            roundingParams.setBorder(this.mBorderColor, this.mBorderWidth);
            roundingParams.setCornersRadius(this.mBorderRadius);
            hierarchy.setRoundingParams(roundingParams);
        } else if (roundingParams != null) {
            hierarchy.setRoundingParams(null);
        }
        hierarchy.setActualImageScaleType(this.mScaleType);
        hierarchy.setActualImageColorFilter(this.mColorFilter);
        hierarchy.setFadeDuration(this.mFadeDuration);
        hierarchy.getTopLevelDrawable().setBounds(Math.round(getLeft()), Math.round(getTop()), Math.round(getRight()), Math.round(getBottom()));
        this.mRequestHelper.attach(callback);
    }

    public void onDetached() {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.detach();
        }
    }

    public void onSubmit(String id, Object callerContext) {
        if (this.mCallback != null && this.mReactTag != 0) {
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 4);
        }
    }

    public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
        if (this.mCallback != null && this.mReactTag != 0) {
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 2);
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 3);
        }
    }

    public void onIntermediateImageSet(String id, @Nullable Object imageInfo) {
    }

    public void onIntermediateImageFailed(String id, Throwable throwable) {
    }

    public void onFailure(String id, Throwable throwable) {
        if (this.mCallback != null && this.mReactTag != 0) {
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 1);
            this.mCallback.dispatchImageLoadEvent(this.mReactTag, 3);
        }
    }

    public void onRelease(String id) {
    }

    protected void onBoundsChanged() {
        super.onBoundsChanged();
        computeRequestHelper();
    }

    private void computeRequestHelper() {
        MultiSourceHelper$MultiSourceResult multiSource = MultiSourceHelper.getBestSourceForSize(Math.round(getRight() - getLeft()), Math.round(getBottom() - getTop()), this.mSources);
        ImageSource source = multiSource.getBestResult();
        ImageSource cachedSource = multiSource.getBestResultInCache();
        if (source == null) {
            this.mRequestHelper = null;
            return;
        }
        ResizeOptions resizeOptions = null;
        if (shouldResize(source)) {
            resizeOptions = new ResizeOptions((int) (getRight() - getLeft()), (int) (getBottom() - getTop()));
        }
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(source.getUri()).setResizeOptions(resizeOptions).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build();
        ImageRequest cachedImageRequest = null;
        if (cachedSource != null) {
            cachedImageRequest = ImageRequestBuilder.newBuilderWithSource(cachedSource.getUri()).setResizeOptions(resizeOptions).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build();
        }
        this.mRequestHelper = new DraweeRequestHelper((ImageRequest) Assertions.assertNotNull(imageRequest), cachedImageRequest, this);
    }

    private boolean shouldDisplayBorder() {
        return this.mBorderColor != 0 || this.mBorderRadius >= 0.5f;
    }

    private static boolean shouldResize(ImageSource imageSource) {
        Uri uri = imageSource.getUri();
        String type = uri == null ? null : uri.getScheme();
        return "file".equals(type) || "content".equals(type);
    }

    protected void onDebugDrawHighlight(Canvas canvas) {
        if (this.mCallback != null) {
            debugDrawCautionHighlight(canvas, "Invalidate Drawee");
        }
    }
}
