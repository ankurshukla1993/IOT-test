package com.facebook.react;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.UIManagerModule;
import javax.annotation.Nullable;

public class ReactRootView extends SizeMonitoringFrameLayout implements RootView {
    @Nullable
    private CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    private boolean mIsAttachedToInstance = false;
    @Nullable
    private String mJSModuleName;
    private final JSTouchDispatcher mJSTouchDispatcher = new JSTouchDispatcher(this);
    @Nullable
    private Bundle mLaunchOptions;
    @Nullable
    private ReactInstanceManager mReactInstanceManager;
    @Nullable
    private ReactRootViewEventListener mRootViewEventListener;
    private int mRootViewTag;
    private boolean mWasMeasured = false;

    public ReactRootView(Context context) {
        super(context);
    }

    public ReactRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactRootView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        this.mWasMeasured = true;
        if (this.mReactInstanceManager != null && !this.mIsAttachedToInstance) {
            UiThreadUtil.runOnUiThread(new 1(this));
        }
    }

    public void onChildStartedNativeGesture(MotionEvent androidEvent) {
        if (this.mReactInstanceManager == null || !this.mIsAttachedToInstance || this.mReactInstanceManager.getCurrentReactContext() == null) {
            FLog.w(ReactConstants.TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.mJSTouchDispatcher.onChildStartedNativeGesture(androidEvent, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        dispatchJSTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        dispatchJSTouchEvent(ev);
        super.onTouchEvent(ev);
        return true;
    }

    private void dispatchJSTouchEvent(MotionEvent event) {
        if (this.mReactInstanceManager == null || !this.mIsAttachedToInstance || this.mReactInstanceManager.getCurrentReactContext() == null) {
            FLog.w(ReactConstants.TAG, "Unable to dispatch touch to JS as the catalyst instance has not been attached");
            return;
        }
        this.mJSTouchDispatcher.handleTouchEvent(event, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String moduleName) {
        startReactApplication(reactInstanceManager, moduleName, null);
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String moduleName, @Nullable Bundle launchOptions) {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
        this.mReactInstanceManager = reactInstanceManager;
        this.mJSModuleName = moduleName;
        this.mLaunchOptions = launchOptions;
        if (!this.mReactInstanceManager.hasStartedCreatingInitialContext()) {
            this.mReactInstanceManager.createReactContextInBackground();
        }
        if (this.mWasMeasured) {
            attachToReactInstanceManager();
        }
    }

    public void unmountReactApplication() {
        if (this.mReactInstanceManager != null && this.mIsAttachedToInstance) {
            this.mReactInstanceManager.detachRootView(this);
            this.mIsAttachedToInstance = false;
        }
    }

    public void onAttachedToReactInstance() {
        if (this.mRootViewEventListener != null) {
            this.mRootViewEventListener.onAttachedToReactInstance(this);
        }
    }

    public void setEventListener(ReactRootViewEventListener eventListener) {
        this.mRootViewEventListener = eventListener;
    }

    String getJSModuleName() {
        return (String) Assertions.assertNotNull(this.mJSModuleName);
    }

    @Nullable
    Bundle getLaunchOptions() {
        return this.mLaunchOptions;
    }

    @VisibleForTesting
    void simulateAttachForTesting() {
        this.mIsAttachedToInstance = true;
        this.mWasMeasured = true;
    }

    private CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new CustomGlobalLayoutListener(this);
        }
        return this.mCustomGlobalLayoutListener;
    }

    private void attachToReactInstanceManager() {
        if (!this.mIsAttachedToInstance) {
            this.mIsAttachedToInstance = true;
            ((ReactInstanceManager) Assertions.assertNotNull(this.mReactInstanceManager)).attachMeasuredRootView(this);
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        Assertions.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    public int getRootViewTag() {
        return this.mRootViewTag;
    }

    public void setRootViewTag(int rootViewTag) {
        this.mRootViewTag = rootViewTag;
    }
}
