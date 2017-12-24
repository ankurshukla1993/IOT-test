package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.JavaScriptModuleRegistry.Builder;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.ApplicationHolder;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.cxxbridge.Arguments;
import com.facebook.react.cxxbridge.CatalystInstanceImpl;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.cxxbridge.JSCJavaScriptExecutor.Factory;
import com.facebook.react.cxxbridge.JavaScriptExecutor;
import com.facebook.react.cxxbridge.NativeModuleRegistry;
import com.facebook.react.cxxbridge.ProxyJavaScriptExecutor;
import com.facebook.react.cxxbridge.UiThreadUtil;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.ReactInstanceDevCommandsHandler;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.debug.DeveloperSettings;
import com.facebook.react.uimanager.AppRegistry;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import humanize.util.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;

public class ReactInstanceManager {
    private static final String TAG = ReactInstanceManager.class.getSimpleName();
    private final Context mApplicationContext;
    private final List<ReactRootView> mAttachedRootViews = new ArrayList();
    private final DefaultHardwareBackBtnHandler mBackBtnHandler = new 2(this);
    @Nullable
    private final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    @Nullable
    private final JSBundleLoader mBundleLoader;
    @Nullable
    private Activity mCurrentActivity;
    @Nullable
    private volatile ReactContext mCurrentReactContext;
    @Nullable
    private DefaultHardwareBackBtnHandler mDefaultBackButtonImpl;
    private final ReactInstanceDevCommandsHandler mDevInterface = new 1(this);
    private final DevSupportManager mDevSupportManager;
    private volatile boolean mHasStartedCreatingInitialContext = false;
    private final JSCConfig mJSCConfig;
    @Nullable
    private final String mJSMainModuleName;
    private final boolean mLazyNativeModulesEnabled;
    private final boolean mLazyViewManagersEnabled;
    private LifecycleState mLifecycleState;
    private final MemoryPressureRouter mMemoryPressureRouter;
    @Nullable
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final List<ReactPackage> mPackages;
    @Nullable
    private ReactContextInitParams mPendingReactContextInitParams;
    @Nullable
    private ReactContextInitAsyncTask mReactContextInitAsyncTask;
    private final Collection<ReactInstanceEventListener> mReactInstanceEventListeners = Collections.synchronizedSet(new HashSet());
    private final UIImplementationProvider mUIImplementationProvider;
    private final boolean mUseDeveloperSupport;

    private static class Result<T> {
        @Nullable
        private final Exception mException;
        @Nullable
        private final T mResult;

        public static <T, U extends T> Result<T> of(U result) {
            return new Result((Object) result);
        }

        public static <T> Result<T> of(Exception exception) {
            return new Result(exception);
        }

        private Result(T result) {
            this.mException = null;
            this.mResult = result;
        }

        private Result(Exception exception) {
            this.mException = exception;
            this.mResult = null;
        }

        public T get() throws Exception {
            if (this.mException != null) {
                throw this.mException;
            }
            Assertions.assertNotNull(this.mResult);
            return this.mResult;
        }
    }

    public static ReactInstanceManagerBuilder builder() {
        return new ReactInstanceManagerBuilder();
    }

    ReactInstanceManager(Context applicationContext, @Nullable Activity currentActivity, @Nullable DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, @Nullable JSBundleLoader bundleLoader, @Nullable String jsMainModuleName, List<ReactPackage> packages, boolean useDeveloperSupport, @Nullable NotThreadSafeBridgeIdleDebugListener bridgeIdleDebugListener, LifecycleState initialLifecycleState, UIImplementationProvider uiImplementationProvider, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, JSCConfig jscConfig, @Nullable RedBoxHandler redBoxHandler, boolean lazyNativeModulesEnabled, boolean lazyViewManagersEnabled) {
        initializeSoLoaderIfNecessary(applicationContext);
        ApplicationHolder.setApplication((Application) applicationContext.getApplicationContext());
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(applicationContext);
        this.mApplicationContext = applicationContext;
        this.mCurrentActivity = currentActivity;
        this.mDefaultBackButtonImpl = defaultHardwareBackBtnHandler;
        this.mBundleLoader = bundleLoader;
        this.mJSMainModuleName = jsMainModuleName;
        this.mPackages = packages;
        this.mUseDeveloperSupport = useDeveloperSupport;
        this.mDevSupportManager = DevSupportManagerFactory.create(applicationContext, this.mDevInterface, this.mJSMainModuleName, useDeveloperSupport, redBoxHandler);
        this.mBridgeIdleDebugListener = bridgeIdleDebugListener;
        this.mLifecycleState = initialLifecycleState;
        this.mUIImplementationProvider = uiImplementationProvider;
        this.mMemoryPressureRouter = new MemoryPressureRouter(applicationContext);
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        this.mJSCConfig = jscConfig;
        this.mLazyNativeModulesEnabled = lazyNativeModulesEnabled;
        this.mLazyViewManagersEnabled = lazyViewManagersEnabled;
    }

    public DevSupportManager getDevSupportManager() {
        return this.mDevSupportManager;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    private static void initializeSoLoaderIfNecessary(Context applicationContext) {
        SoLoader.init(applicationContext, false);
    }

    public void createReactContextInBackground() {
        Assertions.assertCondition(!this.mHasStartedCreatingInitialContext, "createReactContextInBackground should only be called when creating the react application for the first time. When reloading JS, e.g. from a new file, explicitlyuse recreateReactContextInBackground");
        this.mHasStartedCreatingInitialContext = true;
        recreateReactContextInBackgroundInner();
    }

    public void recreateReactContextInBackground() {
        Assertions.assertCondition(this.mHasStartedCreatingInitialContext, "recreateReactContextInBackground should only be called after the initial createReactContextInBackground call.");
        recreateReactContextInBackgroundInner();
    }

    private void recreateReactContextInBackgroundInner() {
        UiThreadUtil.assertOnUiThread();
        if (!this.mUseDeveloperSupport || this.mJSMainModuleName == null) {
            recreateReactContextInBackgroundFromBundleLoader();
            return;
        }
        DeveloperSettings devSettings = this.mDevSupportManager.getDevSettings();
        if (this.mDevSupportManager.hasUpToDateJSBundleInCache() && !devSettings.isRemoteJSDebugEnabled()) {
            onJSBundleLoadedFromServer();
        } else if (this.mBundleLoader == null) {
            this.mDevSupportManager.handleReloadJS();
        } else {
            this.mDevSupportManager.isPackagerRunning(new 3(this, devSettings));
        }
    }

    private void recreateReactContextInBackgroundFromBundleLoader() {
        recreateReactContextInBackground(new Factory(this.mJSCConfig.getConfigMap()), this.mBundleLoader);
    }

    public boolean hasStartedCreatingInitialContext() {
        return this.mHasStartedCreatingInitialContext;
    }

    public void onBackPressed() {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = this.mCurrentReactContext;
        if (this.mCurrentReactContext == null) {
            FLog.w(ReactConstants.TAG, "Instance detached from instance manager");
            invokeDefaultOnBackPressed();
            return;
        }
        ((DeviceEventManagerModule) ((ReactContext) Assertions.assertNotNull(reactContext)).getNativeModule(DeviceEventManagerModule.class)).emitHardwareBackPressed();
    }

    private void invokeDefaultOnBackPressed() {
        UiThreadUtil.assertOnUiThread();
        if (this.mDefaultBackButtonImpl != null) {
            this.mDefaultBackButtonImpl.invokeDefaultOnBackPressed();
        }
    }

    public void onNewIntent(Intent intent) {
        if (this.mCurrentReactContext == null) {
            FLog.w(ReactConstants.TAG, "Instance detached from instance manager");
            return;
        }
        String action = intent.getAction();
        Uri uri = intent.getData();
        if ("android.intent.action.VIEW".equals(action) && uri != null) {
            ((DeviceEventManagerModule) ((ReactContext) Assertions.assertNotNull(this.mCurrentReactContext)).getNativeModule(DeviceEventManagerModule.class)).emitNewIntentReceived(uri);
        }
        this.mCurrentReactContext.onNewIntent(this.mCurrentActivity, intent);
    }

    private void toggleElementInspector() {
        if (this.mCurrentReactContext != null) {
            ((RCTDeviceEventEmitter) this.mCurrentReactContext.getJSModule(RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
        }
    }

    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = null;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeResumeLifecycleState();
    }

    public void onHostPause(Activity activity) {
        Assertions.assertNotNull(this.mCurrentActivity);
        Assertions.assertCondition(activity == this.mCurrentActivity, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + this.mCurrentActivity.getClass().getSimpleName() + Constants.SPACE + "Paused activity: " + activity.getClass().getSimpleName());
        onHostPause();
    }

    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultBackButtonImpl) {
        UiThreadUtil.assertOnUiThread();
        this.mDefaultBackButtonImpl = defaultBackButtonImpl;
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(true);
        }
        this.mCurrentActivity = activity;
        moveToResumedLifecycleState(false);
    }

    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeCreateLifecycleState();
        this.mCurrentActivity = null;
    }

    public void onHostDestroy(Activity activity) {
        if (activity == this.mCurrentActivity) {
            onHostDestroy();
        }
    }

    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mUseDeveloperSupport) {
            this.mDevSupportManager.setDevSupportEnabled(false);
        }
        moveToBeforeCreateLifecycleState();
        if (this.mReactContextInitAsyncTask != null) {
            this.mReactContextInitAsyncTask.cancel(true);
        }
        this.mMemoryPressureRouter.destroy(this.mApplicationContext);
        if (this.mCurrentReactContext != null) {
            this.mCurrentReactContext.destroy();
            this.mCurrentReactContext = null;
            this.mHasStartedCreatingInitialContext = false;
        }
        this.mCurrentActivity = null;
        ResourceDrawableIdHelper.getInstance().clear();
    }

    private void moveToResumedLifecycleState(boolean force) {
        if (this.mCurrentReactContext != null && (force || this.mLifecycleState == LifecycleState.BEFORE_RESUME || this.mLifecycleState == LifecycleState.BEFORE_CREATE)) {
            this.mCurrentReactContext.onHostResume(this.mCurrentActivity);
        }
        this.mLifecycleState = LifecycleState.RESUMED;
    }

    private void moveToBeforeResumeLifecycleState() {
        if (this.mCurrentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                this.mCurrentReactContext.onHostResume(this.mCurrentActivity);
                this.mCurrentReactContext.onHostPause();
            } else if (this.mLifecycleState == LifecycleState.RESUMED) {
                this.mCurrentReactContext.onHostPause();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
    }

    private void moveToBeforeCreateLifecycleState() {
        if (this.mCurrentReactContext != null) {
            if (this.mLifecycleState == LifecycleState.RESUMED) {
                this.mCurrentReactContext.onHostPause();
                this.mLifecycleState = LifecycleState.BEFORE_RESUME;
            }
            if (this.mLifecycleState == LifecycleState.BEFORE_RESUME) {
                this.mCurrentReactContext.onHostDestroy();
            }
        }
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (this.mCurrentReactContext != null) {
            this.mCurrentReactContext.onActivityResult(activity, requestCode, resultCode, data);
        }
    }

    public void showDevOptionsDialog() {
        UiThreadUtil.assertOnUiThread();
        this.mDevSupportManager.showDevOptionsDialog();
    }

    public void attachMeasuredRootView(ReactRootView rootView) {
        UiThreadUtil.assertOnUiThread();
        this.mAttachedRootViews.add(rootView);
        if (this.mReactContextInitAsyncTask == null && this.mCurrentReactContext != null) {
            attachMeasuredRootViewToInstance(rootView, this.mCurrentReactContext.getCatalystInstance());
        }
    }

    public void detachRootView(ReactRootView rootView) {
        UiThreadUtil.assertOnUiThread();
        if (this.mAttachedRootViews.remove(rootView) && this.mCurrentReactContext != null && this.mCurrentReactContext.hasActiveCatalystInstance()) {
            detachViewFromInstance(rootView, this.mCurrentReactContext.getCatalystInstance());
        }
    }

    public List<ViewManager> createAllViewManagers(ReactApplicationContext catalystApplicationContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        Systrace.beginSection(0, "createAllViewManagers");
        try {
            List<ViewManager> allViewManagers = new ArrayList();
            for (ReactPackage reactPackage : this.mPackages) {
                allViewManagers.addAll(reactPackage.createViewManagers(catalystApplicationContext));
            }
            return allViewManagers;
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
        }
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener listener) {
        this.mReactInstanceEventListeners.add(listener);
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener listener) {
        this.mReactInstanceEventListeners.remove(listener);
    }

    @VisibleForTesting
    @Nullable
    public ReactContext getCurrentReactContext() {
        return this.mCurrentReactContext;
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    private void onReloadWithJSDebugger(JavaJSExecutor.Factory jsExecutorFactory) {
        recreateReactContextInBackground(new ProxyJavaScriptExecutor.Factory(jsExecutorFactory), JSBundleLoader.createRemoteDebuggerBundleLoader(this.mDevSupportManager.getJSBundleURLForRemoteDebugging(), this.mDevSupportManager.getSourceUrl()));
    }

    private void onJSBundleLoadedFromServer() {
        recreateReactContextInBackground(new Factory(this.mJSCConfig.getConfigMap()), JSBundleLoader.createCachedBundleFromNetworkLoader(this.mDevSupportManager.getSourceUrl(), this.mDevSupportManager.getDownloadedJSBundleFile()));
    }

    private void recreateReactContextInBackground(JavaScriptExecutor.Factory jsExecutorFactory, JSBundleLoader jsBundleLoader) {
        UiThreadUtil.assertOnUiThread();
        ReactContextInitParams initParams = new ReactContextInitParams(this, jsExecutorFactory, jsBundleLoader);
        if (this.mReactContextInitAsyncTask == null) {
            this.mReactContextInitAsyncTask = new ReactContextInitAsyncTask(this, null);
            this.mReactContextInitAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ReactContextInitParams[]{initParams});
            return;
        }
        this.mPendingReactContextInitParams = initParams;
    }

    private void setupReactContext(ReactApplicationContext reactContext) {
        boolean z;
        int i = 0;
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        Systrace.beginSection(0, "setupReactContext");
        UiThreadUtil.assertOnUiThread();
        if (this.mCurrentReactContext == null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.assertCondition(z);
        this.mCurrentReactContext = (ReactContext) Assertions.assertNotNull(reactContext);
        CatalystInstance catalystInstance = (CatalystInstance) Assertions.assertNotNull(reactContext.getCatalystInstance());
        catalystInstance.initialize();
        this.mDevSupportManager.onNewReactContextCreated(reactContext);
        this.mMemoryPressureRouter.addMemoryPressureListener(catalystInstance);
        moveReactContextToCurrentLifecycleState();
        for (ReactRootView rootView : this.mAttachedRootViews) {
            attachMeasuredRootViewToInstance(rootView, catalystInstance);
        }
        ReactInstanceEventListener[] listeners = (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]);
        int length = listeners.length;
        while (i < length) {
            listeners[i].onReactContextInitialized(reactContext);
            i++;
        }
        Systrace.endSection(0);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
    }

    private void attachMeasuredRootViewToInstance(ReactRootView rootView, CatalystInstance catalystInstance) {
        Systrace.beginSection(0, "attachMeasuredRootViewToInstance");
        UiThreadUtil.assertOnUiThread();
        rootView.removeAllViews();
        rootView.setId(-1);
        int rootTag = ((UIManagerModule) catalystInstance.getNativeModule(UIManagerModule.class)).addMeasuredRootView(rootView);
        rootView.setRootViewTag(rootTag);
        WritableMap initialProps = Arguments.makeNativeMap(rootView.getLaunchOptions());
        String jsAppModuleName = rootView.getJSModuleName();
        WritableNativeMap appParams = new WritableNativeMap();
        appParams.putDouble("rootTag", (double) rootTag);
        appParams.putMap("initialProps", initialProps);
        ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication(jsAppModuleName, appParams);
        rootView.onAttachedToReactInstance();
        Systrace.endSection(0);
    }

    private void detachViewFromInstance(ReactRootView rootView, CatalystInstance catalystInstance) {
        UiThreadUtil.assertOnUiThread();
        ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(rootView.getId());
    }

    private void tearDownReactContext(ReactContext reactContext) {
        UiThreadUtil.assertOnUiThread();
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        for (ReactRootView rootView : this.mAttachedRootViews) {
            detachViewFromInstance(rootView, reactContext.getCatalystInstance());
        }
        reactContext.destroy();
        this.mDevSupportManager.onReactInstanceDestroyed(reactContext);
        this.mMemoryPressureRouter.removeMemoryPressureListener(reactContext.getCatalystInstance());
    }

    private ReactApplicationContext createReactContext(JavaScriptExecutor jsExecutor, JSBundleLoader jsBundleLoader) {
        FLog.i(ReactConstants.TAG, "Creating react context.");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START);
        ReactApplicationContext reactContext = new ReactApplicationContext(this.mApplicationContext);
        NativeModuleRegistryBuilder nativeModuleRegistryBuilder = new NativeModuleRegistryBuilder(reactContext, this.mLazyNativeModulesEnabled);
        Builder jsModulesBuilder = new Builder();
        if (this.mUseDeveloperSupport) {
            reactContext.setNativeModuleCallExceptionHandler(this.mDevSupportManager);
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        Systrace.beginSection(0, "createAndProcessCoreModulesPackage");
        try {
            processPackage(new CoreModulesPackage(this, this.mBackBtnHandler, this.mUIImplementationProvider, this.mLazyViewManagersEnabled), nativeModuleRegistryBuilder, jsModulesBuilder);
            for (ReactPackage reactPackage : this.mPackages) {
                Systrace.beginSection(0, "createAndProcessCustomReactPackage");
                try {
                    processPackage(reactPackage, nativeModuleRegistryBuilder, jsModulesBuilder);
                    Systrace.endSection(0);
                } catch (Throwable th) {
                    Systrace.endSection(0);
                }
            }
            ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
            Systrace.beginSection(0, "buildNativeModuleRegistry");
            try {
                NativeModuleRegistry nativeModuleRegistry = nativeModuleRegistryBuilder.build();
                CatalystInstanceImpl.Builder catalystInstanceBuilder = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(jsExecutor).setRegistry(nativeModuleRegistry).setJSModuleRegistry(jsModulesBuilder.build()).setJSBundleLoader(jsBundleLoader).setNativeModuleCallExceptionHandler(this.mNativeModuleCallExceptionHandler != null ? this.mNativeModuleCallExceptionHandler : this.mDevSupportManager);
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
                Systrace.beginSection(0, "createCatalystInstance");
                try {
                    CatalystInstance catalystInstance = catalystInstanceBuilder.build();
                    if (this.mBridgeIdleDebugListener != null) {
                        catalystInstance.addBridgeIdleDebugListener(this.mBridgeIdleDebugListener);
                    }
                    reactContext.initializeWithInstance(catalystInstance);
                    catalystInstance.runJSBundle();
                    return reactContext;
                } finally {
                    Systrace.endSection(0);
                    ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
                }
            } finally {
                Systrace.endSection(0);
                ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    private void processPackage(ReactPackage reactPackage, NativeModuleRegistryBuilder nativeModuleRegistryBuilder, Builder jsModulesBuilder) {
        SystraceMessage.beginSection(0, "processPackage").arg("className", reactPackage.getClass().getSimpleName()).flush();
        if (reactPackage instanceof ReactPackageLogger) {
            ((ReactPackageLogger) reactPackage).startProcessPackage();
        }
        nativeModuleRegistryBuilder.processPackage(reactPackage);
        for (Class<? extends JavaScriptModule> jsModuleClass : reactPackage.createJSModules()) {
            jsModulesBuilder.add(jsModuleClass);
        }
        if (reactPackage instanceof ReactPackageLogger) {
            ((ReactPackageLogger) reactPackage).endProcessPackage();
        }
        Systrace.endSection(0);
    }

    private void moveReactContextToCurrentLifecycleState() {
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            moveToResumedLifecycleState(true);
        }
    }
}
