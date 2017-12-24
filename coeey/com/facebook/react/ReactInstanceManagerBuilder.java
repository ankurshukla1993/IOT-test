package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class ReactInstanceManagerBuilder {
    @Nullable
    protected Application mApplication;
    @Nullable
    protected NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    @Nullable
    protected Activity mCurrentActivity;
    @Nullable
    protected DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    @Nullable
    protected LifecycleState mInitialLifecycleState;
    @Nullable
    protected String mJSBundleAssetUrl;
    @Nullable
    protected JSBundleLoader mJSBundleLoader;
    protected JSCConfig mJSCConfig = JSCConfig.EMPTY;
    @Nullable
    protected String mJSMainModuleName;
    protected boolean mLazyNativeModulesEnabled;
    protected boolean mLazyViewManagersEnabled;
    @Nullable
    protected NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    protected final List<ReactPackage> mPackages = new ArrayList();
    @Nullable
    protected RedBoxHandler mRedBoxHandler;
    @Nullable
    protected UIImplementationProvider mUIImplementationProvider;
    protected boolean mUseDeveloperSupport;

    ReactInstanceManagerBuilder() {
    }

    public ReactInstanceManagerBuilder setUIImplementationProvider(@Nullable UIImplementationProvider uiImplementationProvider) {
        this.mUIImplementationProvider = uiImplementationProvider;
        return this;
    }

    public ReactInstanceManagerBuilder setBundleAssetName(String bundleAssetName) {
        this.mJSBundleAssetUrl = bundleAssetName == null ? null : "assets://" + bundleAssetName;
        this.mJSBundleLoader = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSBundleFile(String jsBundleFile) {
        if (!jsBundleFile.startsWith("assets://")) {
            return setJSBundleLoader(JSBundleLoader.createFileLoader(jsBundleFile));
        }
        this.mJSBundleAssetUrl = jsBundleFile;
        this.mJSBundleLoader = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSBundleLoader(JSBundleLoader jsBundleLoader) {
        this.mJSBundleLoader = jsBundleLoader;
        this.mJSBundleAssetUrl = null;
        return this;
    }

    public ReactInstanceManagerBuilder setJSMainModuleName(String jsMainModuleName) {
        this.mJSMainModuleName = jsMainModuleName;
        return this;
    }

    public ReactInstanceManagerBuilder addPackage(ReactPackage reactPackage) {
        this.mPackages.add(reactPackage);
        return this;
    }

    public ReactInstanceManagerBuilder setBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener bridgeIdleDebugListener) {
        this.mBridgeIdleDebugListener = bridgeIdleDebugListener;
        return this;
    }

    public ReactInstanceManagerBuilder setApplication(Application application) {
        this.mApplication = application;
        return this;
    }

    public ReactInstanceManagerBuilder setCurrentActivity(Activity activity) {
        this.mCurrentActivity = activity;
        return this;
    }

    public ReactInstanceManagerBuilder setDefaultHardwareBackBtnHandler(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setUseDeveloperSupport(boolean useDeveloperSupport) {
        this.mUseDeveloperSupport = useDeveloperSupport;
        return this;
    }

    public ReactInstanceManagerBuilder setInitialLifecycleState(LifecycleState initialLifecycleState) {
        this.mInitialLifecycleState = initialLifecycleState;
        return this;
    }

    public ReactInstanceManagerBuilder setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler handler) {
        this.mNativeModuleCallExceptionHandler = handler;
        return this;
    }

    public ReactInstanceManagerBuilder setJSCConfig(JSCConfig jscConfig) {
        this.mJSCConfig = jscConfig;
        return this;
    }

    public ReactInstanceManagerBuilder setRedBoxHandler(@Nullable RedBoxHandler redBoxHandler) {
        this.mRedBoxHandler = redBoxHandler;
        return this;
    }

    public ReactInstanceManagerBuilder setLazyNativeModulesEnabled(boolean lazyNativeModulesEnabled) {
        this.mLazyNativeModulesEnabled = lazyNativeModulesEnabled;
        return this;
    }

    public ReactInstanceManagerBuilder setLazyViewManagersEnabled(boolean lazyViewManagersEnabled) {
        this.mLazyViewManagersEnabled = lazyViewManagersEnabled;
        return this;
    }

    public ReactInstanceManager build() {
        Assertions.assertNotNull(this.mApplication, "Application property has not been set with this builder");
        boolean z = (!this.mUseDeveloperSupport && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true;
        Assertions.assertCondition(z, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
        z = (this.mJSMainModuleName == null && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true;
        Assertions.assertCondition(z, "Either MainModuleName or JS Bundle File needs to be provided");
        if (this.mUIImplementationProvider == null) {
            this.mUIImplementationProvider = new UIImplementationProvider();
        }
        Context context = this.mApplication;
        Activity activity = this.mCurrentActivity;
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        JSBundleLoader createAssetLoader = (this.mJSBundleLoader != null || this.mJSBundleAssetUrl == null) ? this.mJSBundleLoader : JSBundleLoader.createAssetLoader(this.mApplication, this.mJSBundleAssetUrl);
        return new ReactInstanceManager(context, activity, defaultHardwareBackBtnHandler, createAssetLoader, this.mJSMainModuleName, this.mPackages, this.mUseDeveloperSupport, this.mBridgeIdleDebugListener, (LifecycleState) Assertions.assertNotNull(this.mInitialLifecycleState, "Initial lifecycle state was not set"), this.mUIImplementationProvider, this.mNativeModuleCallExceptionHandler, this.mJSCConfig, this.mRedBoxHandler, this.mLazyNativeModulesEnabled, this.mLazyViewManagersEnabled);
    }
}
