package com.facebook.drawee.controller;

import android.content.Context;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.FirstAvailableDataSourceSupplier;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements SimpleDraweeControllerBuilder {
    private static final NullPointerException NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
    private static final ControllerListener<Object> sAutoPlayAnimationsListener = new 1();
    private static final AtomicLong sIdCounter = new AtomicLong();
    private boolean mAutoPlayAnimations;
    private final Set<ControllerListener> mBoundControllerListeners;
    @Nullable
    private Object mCallerContext;
    private final Context mContext;
    @Nullable
    private ControllerListener<? super INFO> mControllerListener;
    @Nullable
    private Supplier<DataSource<IMAGE>> mDataSourceSupplier;
    @Nullable
    private REQUEST mImageRequest;
    @Nullable
    private REQUEST mLowResImageRequest;
    @Nullable
    private REQUEST[] mMultiImageRequests;
    @Nullable
    private DraweeController mOldController;
    private boolean mRetainImageOnFailure;
    private boolean mTapToRetryEnabled;
    private boolean mTryCacheOnlyFirst;

    protected abstract DataSource<IMAGE> getDataSourceForRequest(REQUEST request, Object obj, boolean z);

    protected abstract BUILDER getThis();

    protected abstract AbstractDraweeController obtainController();

    protected AbstractDraweeControllerBuilder(Context context, Set<ControllerListener> boundControllerListeners) {
        this.mContext = context;
        this.mBoundControllerListeners = boundControllerListeners;
        init();
    }

    private void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mMultiImageRequests = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mTapToRetryEnabled = false;
        this.mAutoPlayAnimations = false;
        this.mOldController = null;
    }

    public BUILDER reset() {
        init();
        return getThis();
    }

    public BUILDER setCallerContext(Object callerContext) {
        this.mCallerContext = callerContext;
        return getThis();
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public BUILDER setImageRequest(REQUEST imageRequest) {
        this.mImageRequest = imageRequest;
        return getThis();
    }

    @Nullable
    public REQUEST getImageRequest() {
        return this.mImageRequest;
    }

    public BUILDER setLowResImageRequest(REQUEST lowResImageRequest) {
        this.mLowResImageRequest = lowResImageRequest;
        return getThis();
    }

    @Nullable
    public REQUEST getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] firstAvailableImageRequests) {
        return setFirstAvailableImageRequests(firstAvailableImageRequests, true);
    }

    public BUILDER setFirstAvailableImageRequests(REQUEST[] firstAvailableImageRequests, boolean tryCacheOnlyFirst) {
        this.mMultiImageRequests = firstAvailableImageRequests;
        this.mTryCacheOnlyFirst = tryCacheOnlyFirst;
        return getThis();
    }

    @Nullable
    public REQUEST[] getFirstAvailableImageRequests() {
        return this.mMultiImageRequests;
    }

    public void setDataSourceSupplier(@Nullable Supplier<DataSource<IMAGE>> dataSourceSupplier) {
        this.mDataSourceSupplier = dataSourceSupplier;
    }

    @Nullable
    public Supplier<DataSource<IMAGE>> getDataSourceSupplier() {
        return this.mDataSourceSupplier;
    }

    public BUILDER setTapToRetryEnabled(boolean enabled) {
        this.mTapToRetryEnabled = enabled;
        return getThis();
    }

    public boolean getTapToRetryEnabled() {
        return this.mTapToRetryEnabled;
    }

    public BUILDER setRetainImageOnFailure(boolean enabled) {
        this.mRetainImageOnFailure = enabled;
        return getThis();
    }

    public boolean getRetainImageOnFailure() {
        return this.mRetainImageOnFailure;
    }

    public BUILDER setAutoPlayAnimations(boolean enabled) {
        this.mAutoPlayAnimations = enabled;
        return getThis();
    }

    public boolean getAutoPlayAnimations() {
        return this.mAutoPlayAnimations;
    }

    public BUILDER setControllerListener(ControllerListener<? super INFO> controllerListener) {
        this.mControllerListener = controllerListener;
        return getThis();
    }

    @Nullable
    public ControllerListener<? super INFO> getControllerListener() {
        return this.mControllerListener;
    }

    public BUILDER setOldController(@Nullable DraweeController oldController) {
        this.mOldController = oldController;
        return getThis();
    }

    @Nullable
    public DraweeController getOldController() {
        return this.mOldController;
    }

    public AbstractDraweeController build() {
        validate();
        if (this.mImageRequest == null && this.mMultiImageRequests == null && this.mLowResImageRequest != null) {
            this.mImageRequest = this.mLowResImageRequest;
            this.mLowResImageRequest = null;
        }
        return buildController();
    }

    protected void validate() {
        boolean z = false;
        boolean z2 = this.mMultiImageRequests == null || this.mImageRequest == null;
        Preconditions.checkState(z2, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.mDataSourceSupplier == null || (this.mMultiImageRequests == null && this.mImageRequest == null && this.mLowResImageRequest == null)) {
            z = true;
        }
        Preconditions.checkState(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }

    protected AbstractDraweeController buildController() {
        AbstractDraweeController controller = obtainController();
        controller.setRetainImageOnFailure(getRetainImageOnFailure());
        maybeBuildAndSetRetryManager(controller);
        maybeAttachListeners(controller);
        return controller;
    }

    protected static String generateUniqueControllerId() {
        return String.valueOf(sIdCounter.getAndIncrement());
    }

    protected Supplier<DataSource<IMAGE>> obtainDataSourceSupplier() {
        if (this.mDataSourceSupplier != null) {
            return this.mDataSourceSupplier;
        }
        Supplier<DataSource<IMAGE>> supplier = null;
        if (this.mImageRequest != null) {
            supplier = getDataSourceSupplierForRequest(this.mImageRequest);
        } else if (this.mMultiImageRequests != null) {
            supplier = getFirstAvailableDataSourceSupplier(this.mMultiImageRequests, this.mTryCacheOnlyFirst);
        }
        if (!(supplier == null || this.mLowResImageRequest == null)) {
            List<Supplier<DataSource<IMAGE>>> suppliers = new ArrayList(2);
            suppliers.add(supplier);
            suppliers.add(getDataSourceSupplierForRequest(this.mLowResImageRequest));
            supplier = IncreasingQualityDataSourceSupplier.create(suppliers);
        }
        if (supplier == null) {
            return DataSources.getFailedDataSourceSupplier(NO_REQUEST_EXCEPTION);
        }
        return supplier;
    }

    protected Supplier<DataSource<IMAGE>> getFirstAvailableDataSourceSupplier(REQUEST[] imageRequests, boolean tryBitmapCacheOnlyFirst) {
        List<Supplier<DataSource<IMAGE>>> suppliers = new ArrayList(imageRequests.length * 2);
        if (tryBitmapCacheOnlyFirst) {
            for (Object dataSourceSupplierForRequest : imageRequests) {
                suppliers.add(getDataSourceSupplierForRequest(dataSourceSupplierForRequest, true));
            }
        }
        for (Object dataSourceSupplierForRequest2 : imageRequests) {
            suppliers.add(getDataSourceSupplierForRequest(dataSourceSupplierForRequest2));
        }
        return FirstAvailableDataSourceSupplier.create(suppliers);
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(REQUEST imageRequest) {
        return getDataSourceSupplierForRequest(imageRequest, false);
    }

    protected Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(REQUEST imageRequest, boolean bitmapCacheOnly) {
        return new 2(this, imageRequest, getCallerContext(), bitmapCacheOnly);
    }

    protected void maybeAttachListeners(AbstractDraweeController controller) {
        if (this.mBoundControllerListeners != null) {
            for (ControllerListener<? super INFO> listener : this.mBoundControllerListeners) {
                controller.addControllerListener(listener);
            }
        }
        if (this.mControllerListener != null) {
            controller.addControllerListener(this.mControllerListener);
        }
        if (this.mAutoPlayAnimations) {
            controller.addControllerListener(sAutoPlayAnimationsListener);
        }
    }

    protected void maybeBuildAndSetRetryManager(AbstractDraweeController controller) {
        if (this.mTapToRetryEnabled) {
            RetryManager retryManager = controller.getRetryManager();
            if (retryManager == null) {
                retryManager = new RetryManager();
                controller.setRetryManager(retryManager);
            }
            retryManager.setTapToRetryEnabled(this.mTapToRetryEnabled);
            maybeBuildAndSetGestureDetector(controller);
        }
    }

    protected void maybeBuildAndSetGestureDetector(AbstractDraweeController controller) {
        if (controller.getGestureDetector() == null) {
            controller.setGestureDetector(GestureDetector.newInstance(this.mContext));
        }
    }

    protected Context getContext() {
        return this.mContext;
    }
}
