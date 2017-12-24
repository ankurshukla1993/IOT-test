package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.generic.RoundingParams.RoundingMethod;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.MultiSourceHelper$MultiSourceResult;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

public class ReactImageView extends GenericDraweeView {
    public static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
    private static float[] sComputedCornerRadii = new float[4];
    private static final Matrix sInverse = new Matrix();
    private static final Matrix sMatrix = new Matrix();
    private int mBorderColor;
    @Nullable
    private float[] mBorderCornerRadii;
    private float mBorderRadius = YogaConstants.UNDEFINED;
    private float mBorderWidth;
    @Nullable
    private ImageSource mCachedImageSource;
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private ControllerListener mControllerForTesting;
    @Nullable
    private ControllerListener mControllerListener;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private int mFadeDurationMs = -1;
    @Nullable
    private ImageSource mImageSource;
    private boolean mIsDirty;
    @Nullable
    private Drawable mLoadingImageDrawable;
    private int mOverlayColor;
    private boolean mProgressiveRenderingEnabled;
    private ImageResizeMethod mResizeMethod = ImageResizeMethod.AUTO;
    private final RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    private ScaleType mScaleType = ImageResizeMode.defaultValue();
    private final List<ImageSource> mSources;

    private static GenericDraweeHierarchy buildHierarchy(Context context) {
        return new GenericDraweeHierarchyBuilder(context.getResources()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f)).build();
    }

    public ReactImageView(Context context, AbstractDraweeControllerBuilder draweeControllerBuilder, @Nullable Object callerContext) {
        super(context, buildHierarchy(context));
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mRoundedCornerPostprocessor = new RoundedCornerPostprocessor(this, null);
        this.mCallerContext = callerContext;
        this.mSources = new LinkedList();
    }

    public void setShouldNotifyLoadEvents(boolean shouldNotify) {
        if (shouldNotify) {
            this.mControllerListener = new 1(this, ((UIManagerModule) ((ReactContext) getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher());
        } else {
            this.mControllerListener = null;
        }
        this.mIsDirty = true;
    }

    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        this.mIsDirty = true;
    }

    public void setOverlayColor(int overlayColor) {
        this.mOverlayColor = overlayColor;
        this.mIsDirty = true;
    }

    public void setBorderWidth(float borderWidth) {
        this.mBorderWidth = PixelUtil.toPixelFromDIP(borderWidth);
        this.mIsDirty = true;
    }

    public void setBorderRadius(float borderRadius) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, borderRadius)) {
            this.mBorderRadius = borderRadius;
            this.mIsDirty = true;
        }
    }

    public void setBorderRadius(float borderRadius, int position) {
        if (this.mBorderCornerRadii == null) {
            this.mBorderCornerRadii = new float[4];
            Arrays.fill(this.mBorderCornerRadii, YogaConstants.UNDEFINED);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[position], borderRadius)) {
            this.mBorderCornerRadii[position] = borderRadius;
            this.mIsDirty = true;
        }
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mIsDirty = true;
    }

    public void setResizeMethod(ImageResizeMethod resizeMethod) {
        this.mResizeMethod = resizeMethod;
        this.mIsDirty = true;
    }

    public void setSource(@Nullable ReadableArray sources) {
        this.mSources.clear();
        if (!(sources == null || sources.size() == 0)) {
            String uri;
            ImageSource imageSource;
            if (sources.size() == 1) {
                uri = sources.getMap(0).getString(RNFetchBlobConst.DATA_ENCODE_URI);
                imageSource = new ImageSource(getContext(), uri);
                this.mSources.add(imageSource);
                if (Uri.EMPTY.equals(imageSource.getUri())) {
                    warnImageSource(uri);
                }
            } else {
                for (int idx = 0; idx < sources.size(); idx++) {
                    ReadableMap source = sources.getMap(idx);
                    uri = source.getString(RNFetchBlobConst.DATA_ENCODE_URI);
                    imageSource = new ImageSource(getContext(), uri, source.getDouble("width"), source.getDouble("height"));
                    this.mSources.add(imageSource);
                    if (Uri.EMPTY.equals(imageSource.getUri())) {
                        warnImageSource(uri);
                    }
                }
            }
        }
        this.mIsDirty = true;
    }

    public void setLoadingIndicatorSource(@Nullable String name) {
        Drawable drawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(getContext(), name);
        this.mLoadingImageDrawable = drawable != null ? new AutoRotateDrawable(drawable, 1000) : null;
        this.mIsDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean enabled) {
        this.mProgressiveRenderingEnabled = enabled;
    }

    public void setFadeDuration(int durationMs) {
        this.mFadeDurationMs = durationMs;
    }

    private void cornerRadii(float[] computedCorners) {
        float f;
        float defaultBorderRadius = !YogaConstants.isUndefined(this.mBorderRadius) ? this.mBorderRadius : 0.0f;
        if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[0])) {
            f = defaultBorderRadius;
        } else {
            f = this.mBorderCornerRadii[0];
        }
        computedCorners[0] = f;
        if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[1])) {
            f = defaultBorderRadius;
        } else {
            f = this.mBorderCornerRadii[1];
        }
        computedCorners[1] = f;
        if (this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[2])) {
            f = defaultBorderRadius;
        } else {
            f = this.mBorderCornerRadii[2];
        }
        computedCorners[2] = f;
        if (!(this.mBorderCornerRadii == null || YogaConstants.isUndefined(this.mBorderCornerRadii[3]))) {
            defaultBorderRadius = this.mBorderCornerRadii[3];
        }
        computedCorners[3] = defaultBorderRadius;
    }

    public void maybeUpdateView() {
        if (!this.mIsDirty) {
            return;
        }
        if (!hasMultipleSources() || (getWidth() > 0 && getHeight() > 0)) {
            setSourceImage();
            if (this.mImageSource != null) {
                boolean doResize = shouldResize(this.mImageSource);
                if (!doResize || (getWidth() > 0 && getHeight() > 0)) {
                    GenericDraweeHierarchy hierarchy = (GenericDraweeHierarchy) getHierarchy();
                    hierarchy.setActualImageScaleType(this.mScaleType);
                    if (this.mLoadingImageDrawable != null) {
                        hierarchy.setPlaceholderImage(this.mLoadingImageDrawable, ScaleType.CENTER);
                    }
                    boolean usePostprocessorScaling = (this.mScaleType == ScaleType.CENTER_CROP || this.mScaleType == ScaleType.FOCUS_CROP) ? false : true;
                    RoundingParams roundingParams = hierarchy.getRoundingParams();
                    if (usePostprocessorScaling) {
                        roundingParams.setCornersRadius(0.0f);
                    } else {
                        cornerRadii(sComputedCornerRadii);
                        roundingParams.setCornersRadii(sComputedCornerRadii[0], sComputedCornerRadii[1], sComputedCornerRadii[2], sComputedCornerRadii[3]);
                    }
                    roundingParams.setBorder(this.mBorderColor, this.mBorderWidth);
                    if (this.mOverlayColor != 0) {
                        roundingParams.setOverlayColor(this.mOverlayColor);
                    } else {
                        roundingParams.setRoundingMethod(RoundingMethod.BITMAP_ONLY);
                    }
                    hierarchy.setRoundingParams(roundingParams);
                    int i = this.mFadeDurationMs >= 0 ? this.mFadeDurationMs : this.mImageSource.isResource() ? 0 : 300;
                    hierarchy.setFadeDuration(i);
                    Postprocessor postprocessor = usePostprocessorScaling ? this.mRoundedCornerPostprocessor : null;
                    ResizeOptions resizeOptions = doResize ? new ResizeOptions(getWidth(), getHeight()) : null;
                    ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(this.mImageSource.getUri()).setPostprocessor(postprocessor).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build();
                    this.mDraweeControllerBuilder.reset();
                    this.mDraweeControllerBuilder.setAutoPlayAnimations(true).setCallerContext(this.mCallerContext).setOldController(getController()).setImageRequest(imageRequest);
                    if (this.mCachedImageSource != null) {
                        this.mDraweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(this.mCachedImageSource.getUri()).setPostprocessor(postprocessor).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build());
                    }
                    if (this.mControllerListener != null && this.mControllerForTesting != null) {
                        ForwardingControllerListener combinedListener = new ForwardingControllerListener();
                        combinedListener.addListener(this.mControllerListener);
                        combinedListener.addListener(this.mControllerForTesting);
                        this.mDraweeControllerBuilder.setControllerListener(combinedListener);
                    } else if (this.mControllerForTesting != null) {
                        this.mDraweeControllerBuilder.setControllerListener(this.mControllerForTesting);
                    } else if (this.mControllerListener != null) {
                        this.mDraweeControllerBuilder.setControllerListener(this.mControllerListener);
                    }
                    setController(this.mDraweeControllerBuilder.build());
                    this.mIsDirty = false;
                }
            }
        }
    }

    public void setControllerListener(ControllerListener controllerListener) {
        this.mControllerForTesting = controllerListener;
        this.mIsDirty = true;
        maybeUpdateView();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            boolean z = this.mIsDirty || hasMultipleSources();
            this.mIsDirty = z;
            maybeUpdateView();
        }
    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    private boolean hasMultipleSources() {
        return this.mSources.size() > 1;
    }

    private void setSourceImage() {
        this.mImageSource = null;
        if (!this.mSources.isEmpty()) {
            if (hasMultipleSources()) {
                MultiSourceHelper$MultiSourceResult multiSource = MultiSourceHelper.getBestSourceForSize(getWidth(), getHeight(), this.mSources);
                this.mImageSource = multiSource.getBestResult();
                this.mCachedImageSource = multiSource.getBestResultInCache();
                return;
            }
            this.mImageSource = (ImageSource) this.mSources.get(0);
        }
    }

    private boolean shouldResize(ImageSource imageSource) {
        if (this.mResizeMethod == ImageResizeMethod.AUTO) {
            if (UriUtil.isLocalContentUri(imageSource.getUri()) || UriUtil.isLocalFileUri(imageSource.getUri())) {
                return true;
            }
            return false;
        } else if (this.mResizeMethod == ImageResizeMethod.RESIZE) {
            return true;
        } else {
            return false;
        }
    }

    private void warnImageSource(String uri) {
    }
}
