package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DeferredReleaser.Releasable;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.gestures.GestureDetector.ClickListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class AbstractDraweeController<T, INFO> implements DraweeController, Releasable, ClickListener {
    private static final Class<?> TAG = AbstractDraweeController.class;
    private Object mCallerContext;
    @Nullable
    private ControllerListener<INFO> mControllerListener;
    @Nullable
    private Drawable mControllerOverlay;
    @Nullable
    private DataSource<T> mDataSource;
    private final DeferredReleaser mDeferredReleaser;
    @Nullable
    private Drawable mDrawable;
    private final DraweeEventTracker mEventTracker = new DraweeEventTracker();
    @Nullable
    private T mFetchedImage;
    @Nullable
    private GestureDetector mGestureDetector;
    private boolean mHasFetchFailed;
    private String mId;
    private boolean mIsAttached;
    private boolean mIsRequestSubmitted;
    private boolean mRetainImageOnFailure;
    @Nullable
    private RetryManager mRetryManager;
    @Nullable
    private SettableDraweeHierarchy mSettableDraweeHierarchy;
    private final Executor mUiThreadImmediateExecutor;

    protected abstract Drawable createDrawable(T t);

    protected abstract DataSource<T> getDataSource();

    @Nullable
    protected abstract INFO getImageInfo(T t);

    protected abstract void releaseDrawable(@Nullable Drawable drawable);

    protected abstract void releaseImage(@Nullable T t);

    public AbstractDraweeController(DeferredReleaser deferredReleaser, Executor uiThreadImmediateExecutor, String id, Object callerContext) {
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadImmediateExecutor = uiThreadImmediateExecutor;
        init(id, callerContext, true);
    }

    protected void initialize(String id, Object callerContext) {
        init(id, callerContext, false);
    }

    private void init(String id, Object callerContext, boolean justConstructed) {
        this.mEventTracker.recordEvent(Event.ON_INIT_CONTROLLER);
        if (!(justConstructed || this.mDeferredReleaser == null)) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
        }
        this.mIsAttached = false;
        releaseFetch();
        this.mRetainImageOnFailure = false;
        if (this.mRetryManager != null) {
            this.mRetryManager.init();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.init();
            this.mGestureDetector.setClickListener(this);
        }
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).clearListeners();
        } else {
            this.mControllerListener = null;
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        this.mControllerOverlay = null;
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.mId, id);
        }
        this.mId = id;
        this.mCallerContext = callerContext;
    }

    public void release() {
        this.mEventTracker.recordEvent(Event.ON_RELEASE_CONTROLLER);
        if (this.mRetryManager != null) {
            this.mRetryManager.reset();
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector.reset();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.reset();
        }
        releaseFetch();
    }

    private void releaseFetch() {
        boolean wasRequestSubmitted = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        if (this.mDataSource != null) {
            this.mDataSource.close();
            this.mDataSource = null;
        }
        if (this.mDrawable != null) {
            releaseDrawable(this.mDrawable);
        }
        this.mDrawable = null;
        if (this.mFetchedImage != null) {
            logMessageAndImage("release", this.mFetchedImage);
            releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
        }
        if (wasRequestSubmitted) {
            getControllerListener().onRelease(this.mId);
        }
    }

    public String getId() {
        return this.mId;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Nullable
    protected RetryManager getRetryManager() {
        return this.mRetryManager;
    }

    protected void setRetryManager(@Nullable RetryManager retryManager) {
        this.mRetryManager = retryManager;
    }

    @Nullable
    protected GestureDetector getGestureDetector() {
        return this.mGestureDetector;
    }

    protected void setGestureDetector(@Nullable GestureDetector gestureDetector) {
        this.mGestureDetector = gestureDetector;
        if (this.mGestureDetector != null) {
            this.mGestureDetector.setClickListener(this);
        }
    }

    protected void setRetainImageOnFailure(boolean enabled) {
        this.mRetainImageOnFailure = enabled;
    }

    public void addControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).addListener(controllerListener);
        } else if (this.mControllerListener != null) {
            this.mControllerListener = InternalForwardingListener.createInternal(this.mControllerListener, controllerListener);
        } else {
            this.mControllerListener = controllerListener;
        }
    }

    public void removeControllerListener(ControllerListener<? super INFO> controllerListener) {
        Preconditions.checkNotNull(controllerListener);
        if (this.mControllerListener instanceof InternalForwardingListener) {
            ((InternalForwardingListener) this.mControllerListener).removeListener(controllerListener);
        } else if (this.mControllerListener == controllerListener) {
            this.mControllerListener = null;
        }
    }

    protected ControllerListener<INFO> getControllerListener() {
        if (this.mControllerListener == null) {
            return BaseControllerListener.getNoOpListener();
        }
        return this.mControllerListener;
    }

    @Nullable
    public DraweeHierarchy getHierarchy() {
        return this.mSettableDraweeHierarchy;
    }

    public void setHierarchy(@Nullable DraweeHierarchy hierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, hierarchy);
        }
        this.mEventTracker.recordEvent(hierarchy != null ? Event.ON_SET_HIERARCHY : Event.ON_CLEAR_HIERARCHY);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            release();
        }
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        if (hierarchy != null) {
            Preconditions.checkArgument(hierarchy instanceof SettableDraweeHierarchy);
            this.mSettableDraweeHierarchy = (SettableDraweeHierarchy) hierarchy;
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    protected void setControllerOverlay(@Nullable Drawable controllerOverlay) {
        this.mControllerOverlay = controllerOverlay;
        if (this.mSettableDraweeHierarchy != null) {
            this.mSettableDraweeHierarchy.setControllerOverlay(this.mControllerOverlay);
        }
    }

    @Nullable
    protected Drawable getControllerOverlay() {
        return this.mControllerOverlay;
    }

    public void onAttach() {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, this.mIsRequestSubmitted ? "request already submitted" : "request needs submit");
        }
        this.mEventTracker.recordEvent(Event.ON_ATTACH_CONTROLLER);
        Preconditions.checkNotNull(this.mSettableDraweeHierarchy);
        this.mDeferredReleaser.cancelDeferredRelease(this);
        this.mIsAttached = true;
        if (!this.mIsRequestSubmitted) {
            submitRequest();
        }
    }

    public void onDetach() {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        this.mEventTracker.recordEvent(Event.ON_DETACH_CONTROLLER);
        this.mIsAttached = false;
        this.mDeferredReleaser.scheduleDeferredRelease(this);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.mId, event);
        }
        if (this.mGestureDetector == null) {
            return false;
        }
        if (!this.mGestureDetector.isCapturingGesture() && !shouldHandleGesture()) {
            return false;
        }
        this.mGestureDetector.onTouchEvent(event);
        return true;
    }

    protected boolean shouldHandleGesture() {
        return shouldRetryOnTap();
    }

    private boolean shouldRetryOnTap() {
        return this.mHasFetchFailed && this.mRetryManager != null && this.mRetryManager.shouldRetryOnTap();
    }

    public boolean onClick() {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.mId);
        }
        if (!shouldRetryOnTap()) {
            return false;
        }
        this.mRetryManager.notifyTapToRetry();
        this.mSettableDraweeHierarchy.reset();
        submitRequest();
        return true;
    }

    protected void submitRequest() {
        this.mEventTracker.recordEvent(Event.ON_DATASOURCE_SUBMIT);
        getControllerListener().onSubmit(this.mId, this.mCallerContext);
        this.mSettableDraweeHierarchy.setProgress(0.0f, true);
        this.mIsRequestSubmitted = true;
        this.mHasFetchFailed = false;
        this.mDataSource = getDataSource();
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.mId, Integer.valueOf(System.identityHashCode(this.mDataSource)));
        }
        this.mDataSource.subscribe(new 1(this, this.mId, this.mDataSource.hasResult()), this.mUiThreadImmediateExecutor);
    }

    private void onNewResultInternal(String id, DataSource<T> dataSource, @Nullable T image, float progress, boolean isFinished, boolean wasImmediate) {
        if (isExpectedDataSource(id, dataSource)) {
            this.mEventTracker.recordEvent(isFinished ? Event.ON_DATASOURCE_RESULT : Event.ON_DATASOURCE_RESULT_INT);
            try {
                Drawable drawable = createDrawable(image);
                T previousImage = this.mFetchedImage;
                Drawable previousDrawable = this.mDrawable;
                this.mFetchedImage = image;
                this.mDrawable = drawable;
                if (isFinished) {
                    try {
                        logMessageAndImage("set_final_result @ onNewResult", image);
                        this.mDataSource = null;
                        this.mSettableDraweeHierarchy.setImage(drawable, FlexItem.FLEX_SHRINK_DEFAULT, wasImmediate);
                        getControllerListener().onFinalImageSet(id, getImageInfo(image), getAnimatable());
                    } catch (Throwable th) {
                        if (!(previousDrawable == null || previousDrawable == drawable)) {
                            releaseDrawable(previousDrawable);
                        }
                        if (!(previousImage == null || previousImage == image)) {
                            logMessageAndImage("release_previous_result @ onNewResult", previousImage);
                            releaseImage(previousImage);
                        }
                    }
                } else {
                    logMessageAndImage("set_intermediate_result @ onNewResult", image);
                    this.mSettableDraweeHierarchy.setImage(drawable, progress, wasImmediate);
                    getControllerListener().onIntermediateImageSet(id, getImageInfo(image));
                }
                if (!(previousDrawable == null || previousDrawable == drawable)) {
                    releaseDrawable(previousDrawable);
                }
                if (previousImage != null && previousImage != image) {
                    logMessageAndImage("release_previous_result @ onNewResult", previousImage);
                    releaseImage(previousImage);
                    return;
                }
                return;
            } catch (Exception exception) {
                logMessageAndImage("drawable_failed @ onNewResult", image);
                releaseImage(image);
                onFailureInternal(id, dataSource, exception, isFinished);
                return;
            }
        }
        logMessageAndImage("ignore_old_datasource @ onNewResult", image);
        releaseImage(image);
        dataSource.close();
    }

    private void onFailureInternal(String id, DataSource<T> dataSource, Throwable throwable, boolean isFinished) {
        if (isExpectedDataSource(id, dataSource)) {
            this.mEventTracker.recordEvent(isFinished ? Event.ON_DATASOURCE_FAILURE : Event.ON_DATASOURCE_FAILURE_INT);
            if (isFinished) {
                logMessageAndFailure("final_failed @ onFailure", throwable);
                this.mDataSource = null;
                this.mHasFetchFailed = true;
                if (this.mRetainImageOnFailure && this.mDrawable != null) {
                    this.mSettableDraweeHierarchy.setImage(this.mDrawable, FlexItem.FLEX_SHRINK_DEFAULT, true);
                } else if (shouldRetryOnTap()) {
                    this.mSettableDraweeHierarchy.setRetry(throwable);
                } else {
                    this.mSettableDraweeHierarchy.setFailure(throwable);
                }
                getControllerListener().onFailure(this.mId, throwable);
                return;
            }
            logMessageAndFailure("intermediate_failed @ onFailure", throwable);
            getControllerListener().onIntermediateImageFailed(this.mId, throwable);
            return;
        }
        logMessageAndFailure("ignore_old_datasource @ onFailure", throwable);
        dataSource.close();
    }

    private void onProgressUpdateInternal(String id, DataSource<T> dataSource, float progress, boolean isFinished) {
        if (!isExpectedDataSource(id, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onProgress", null);
            dataSource.close();
        } else if (!isFinished) {
            this.mSettableDraweeHierarchy.setProgress(progress, false);
        }
    }

    private boolean isExpectedDataSource(String id, DataSource<T> dataSource) {
        return id.equals(this.mId) && dataSource == this.mDataSource && this.mIsRequestSubmitted;
    }

    private void logMessageAndImage(String messageAndMethod, T image) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: %s: image: %s %x", new Object[]{Integer.valueOf(System.identityHashCode(this)), this.mId, messageAndMethod, getImageClass(image), Integer.valueOf(getImageHash(image))});
        }
    }

    private void logMessageAndFailure(String messageAndMethod, Throwable throwable) {
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, messageAndMethod, throwable);
        }
    }

    @Nullable
    public Animatable getAnimatable() {
        return this.mDrawable instanceof Animatable ? (Animatable) this.mDrawable : null;
    }

    protected String getImageClass(@Nullable T image) {
        return image != null ? image.getClass().getSimpleName() : "<null>";
    }

    protected int getImageHash(@Nullable T image) {
        return System.identityHashCode(image);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("isAttached", this.mIsAttached).add("isRequestSubmitted", this.mIsRequestSubmitted).add("hasFetchFailed", this.mHasFetchFailed).add("fetchedImage", getImageHash(this.mFetchedImage)).add("events", this.mEventTracker.toString()).toString();
    }
}
