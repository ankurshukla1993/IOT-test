package com.facebook.react.devsupport;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.widget.Toast;
import com.cooey.devices.five_in_one.bluetooth.Const;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
import com.facebook.react.bridge.Inspector;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.ShakeDetector;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.devsupport.DevInternalSettings.Listener;
import com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener;
import com.facebook.react.devsupport.DevServerHelper.PackagerStatusCallback;
import com.facebook.react.devsupport.JSCSamplingProfiler.ProfilerException;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback;
import com.facebook.react.modules.debug.DeveloperSettings;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Locale;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ws.WebSocket;

public class DevSupportManagerImpl implements DevSupportManager, PackagerCommandListener, Listener {
    private static final String EXOPACKAGE_LOCATION_FORMAT = "/data/local/tmp/exopackage/%s//secondary-dex";
    private static final int JAVA_ERROR_COOKIE = -1;
    private static final int JSEXCEPTION_ERROR_COOKIE = -1;
    private static final String JS_BUNDLE_FILE_NAME = "ReactNativeDevBundle.js";
    private final Context mApplicationContext;
    @Nullable
    private ReactContext mCurrentContext;
    private final LinkedHashMap<String, DevOptionHandler> mCustomDevOptions;
    @Nullable
    private DebugOverlayController mDebugOverlayController;
    private final DefaultNativeModuleCallExceptionHandler mDefaultNativeModuleCallExceptionHandler;
    @Nullable
    private AlertDialog mDevOptionsDialog;
    private final DevServerHelper mDevServerHelper;
    private DevInternalSettings mDevSettings;
    private boolean mIsDevSupportEnabled;
    private boolean mIsReceiverRegistered;
    private boolean mIsShakeDetectorStarted;
    @Nullable
    private final String mJSAppBundleName;
    private final File mJSBundleTempFile;
    private int mLastErrorCookie;
    @Nullable
    private StackFrame[] mLastErrorStack;
    @Nullable
    private String mLastErrorTitle;
    @Nullable
    private ErrorType mLastErrorType;
    private final ReactInstanceDevCommandsHandler mReactInstanceCommandsHandler;
    @Nullable
    private RedBoxDialog mRedBoxDialog;
    @Nullable
    private RedBoxHandler mRedBoxHandler;
    private final BroadcastReceiver mReloadAppBroadcastReceiver;
    private final ShakeDetector mShakeDetector;

    public DevSupportManagerImpl(Context applicationContext, ReactInstanceDevCommandsHandler reactInstanceCommandsHandler, @Nullable String packagerPathForJSBundleName, boolean enableOnCreate) {
        this(applicationContext, reactInstanceCommandsHandler, packagerPathForJSBundleName, enableOnCreate, null);
    }

    public DevSupportManagerImpl(Context applicationContext, ReactInstanceDevCommandsHandler reactInstanceCommandsHandler, @Nullable String packagerPathForJSBundleName, boolean enableOnCreate, @Nullable RedBoxHandler redBoxHandler) {
        this.mCustomDevOptions = new LinkedHashMap();
        this.mIsReceiverRegistered = false;
        this.mIsShakeDetectorStarted = false;
        this.mIsDevSupportEnabled = false;
        this.mLastErrorCookie = 0;
        this.mReactInstanceCommandsHandler = reactInstanceCommandsHandler;
        this.mApplicationContext = applicationContext;
        this.mJSAppBundleName = packagerPathForJSBundleName;
        this.mDevSettings = new DevInternalSettings(applicationContext, this);
        this.mDevServerHelper = new DevServerHelper(this.mDevSettings);
        this.mShakeDetector = new ShakeDetector(new 1(this));
        this.mReloadAppBroadcastReceiver = new 2(this);
        this.mJSBundleTempFile = new File(applicationContext.getFilesDir(), JS_BUNDLE_FILE_NAME);
        this.mDefaultNativeModuleCallExceptionHandler = new DefaultNativeModuleCallExceptionHandler();
        setDevSupportEnabled(enableOnCreate);
        this.mRedBoxHandler = redBoxHandler;
    }

    public void handleException(Exception e) {
        if (!this.mIsDevSupportEnabled) {
            this.mDefaultNativeModuleCallExceptionHandler.handleException(e);
        } else if (e instanceof JSException) {
            FLog.e(ReactConstants.TAG, "Exception in native call from JS", e);
            showNewError(e.getMessage() + "\n\n" + ((JSException) e).getStack(), new StackFrame[0], -1, ErrorType.JS);
        } else {
            showNewJavaError(e.getMessage(), e);
        }
    }

    public void showNewJavaError(String message, Throwable e) {
        FLog.e(ReactConstants.TAG, "Exception in native call", e);
        showNewError(message, StackTraceHelper.convertJavaStackTrace(e), -1, ErrorType.NATIVE);
    }

    public void addCustomDevOption(String optionName, DevOptionHandler optionHandler) {
        this.mCustomDevOptions.put(optionName, optionHandler);
    }

    public void showNewJSError(String message, ReadableArray details, int errorCookie) {
        showNewError(message, StackTraceHelper.convertJsStackTrace(details), errorCookie, ErrorType.JS);
    }

    public void updateJSError(String message, ReadableArray details, int errorCookie) {
        UiThreadUtil.runOnUiThread(new 3(this, errorCookie, details, message));
    }

    public void hideRedboxDialog() {
        if (this.mRedBoxDialog != null) {
            this.mRedBoxDialog.dismiss();
        }
    }

    private void showNewError(String message, StackFrame[] stack, int errorCookie, ErrorType errorType) {
        UiThreadUtil.runOnUiThread(new 4(this, message, stack, errorCookie, errorType));
    }

    public void showDevOptionsDialog() {
        if (this.mDevOptionsDialog == null && this.mIsDevSupportEnabled && !ActivityManager.isUserAMonkey()) {
            Object string;
            LinkedHashMap<String, DevOptionHandler> options = new LinkedHashMap();
            options.put(this.mApplicationContext.getString(R.string.catalyst_reloadjs), new 5(this));
            if (this.mDevSettings.isRemoteJSDebugEnabled()) {
                string = this.mApplicationContext.getString(R.string.catalyst_debugjs_off);
            } else {
                string = this.mApplicationContext.getString(R.string.catalyst_debugjs);
            }
            options.put(string, new 6(this));
            if (Inspector.isSupported()) {
                options.put("Debug JS on-device (experimental)", new 7(this));
            }
            if (this.mDevSettings.isReloadOnJSChangeEnabled()) {
                string = this.mApplicationContext.getString(R.string.catalyst_live_reload_off);
            } else {
                string = this.mApplicationContext.getString(R.string.catalyst_live_reload);
            }
            options.put(string, new 8(this));
            if (this.mDevSettings.isHotModuleReplacementEnabled()) {
                string = this.mApplicationContext.getString(R.string.catalyst_hot_module_replacement_off);
            } else {
                string = this.mApplicationContext.getString(R.string.catalyst_hot_module_replacement);
            }
            options.put(string, new 9(this));
            options.put(this.mApplicationContext.getString(R.string.catalyst_element_inspector), new 10(this));
            if (this.mDevSettings.isFpsDebugEnabled()) {
                string = this.mApplicationContext.getString(R.string.catalyst_perf_monitor_off);
            } else {
                string = this.mApplicationContext.getString(R.string.catalyst_perf_monitor);
            }
            options.put(string, new 11(this));
            options.put(this.mApplicationContext.getString(R.string.catalyst_heap_capture), new 12(this));
            options.put(this.mApplicationContext.getString(R.string.catalyst_poke_sampling_profiler), new 13(this));
            options.put(this.mApplicationContext.getString(R.string.catalyst_settings), new 14(this));
            if (this.mCustomDevOptions.size() > 0) {
                options.putAll(this.mCustomDevOptions);
            }
            this.mDevOptionsDialog = new Builder(this.mApplicationContext).setItems((CharSequence[]) options.keySet().toArray(new String[0]), new 16(this, (DevOptionHandler[]) options.values().toArray(new DevOptionHandler[0]))).setOnCancelListener(new 15(this)).create();
            this.mDevOptionsDialog.getWindow().setType(Const.MESSAGE_OXIMETER_PARAMS);
            this.mDevOptionsDialog.show();
        }
    }

    public void setDevSupportEnabled(boolean isDevSupportEnabled) {
        this.mIsDevSupportEnabled = isDevSupportEnabled;
        reload();
    }

    public boolean getDevSupportEnabled() {
        return this.mIsDevSupportEnabled;
    }

    public DeveloperSettings getDevSettings() {
        return this.mDevSettings;
    }

    public void onNewReactContextCreated(ReactContext reactContext) {
        resetCurrentContext(reactContext);
    }

    public void onReactInstanceDestroyed(ReactContext reactContext) {
        if (reactContext == this.mCurrentContext) {
            resetCurrentContext(null);
        }
    }

    public String getSourceMapUrl() {
        if (this.mJSAppBundleName == null) {
            return "";
        }
        return this.mDevServerHelper.getSourceMapUrl((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getSourceUrl() {
        if (this.mJSAppBundleName == null) {
            return "";
        }
        return this.mDevServerHelper.getSourceUrl((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getJSBundleURLForRemoteDebugging() {
        return this.mDevServerHelper.getJSBundleURLForRemoteDebugging((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getDownloadedJSBundleFile() {
        return this.mJSBundleTempFile.getAbsolutePath();
    }

    public String getHeapCaptureUploadUrl() {
        return this.mDevServerHelper.getHeapCaptureUploadUrl();
    }

    public boolean hasUpToDateJSBundleInCache() {
        if (this.mIsDevSupportEnabled && this.mJSBundleTempFile.exists()) {
            try {
                if (this.mJSBundleTempFile.lastModified() > this.mApplicationContext.getPackageManager().getPackageInfo(this.mApplicationContext.getPackageName(), 0).lastUpdateTime) {
                    File exopackageDir = new File(String.format(Locale.US, EXOPACKAGE_LOCATION_FORMAT, new Object[]{packageName}));
                    if (!exopackageDir.exists() || this.mJSBundleTempFile.lastModified() > exopackageDir.lastModified()) {
                        return true;
                    }
                    return false;
                }
            } catch (NameNotFoundException e) {
                FLog.e(ReactConstants.TAG, "DevSupport is unable to get current app info");
            }
        }
        return false;
    }

    public boolean hasBundleInAssets(String bundleAssetName) {
        try {
            String[] assets = this.mApplicationContext.getAssets().list("");
            for (String equals : assets) {
                if (equals.equals(bundleAssetName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            FLog.e(ReactConstants.TAG, "Error while loading assets list");
        }
        return false;
    }

    private void resetCurrentContext(@Nullable ReactContext reactContext) {
        if (this.mCurrentContext != reactContext) {
            this.mCurrentContext = reactContext;
            if (this.mDebugOverlayController != null) {
                this.mDebugOverlayController.setFpsDebugViewVisible(false);
            }
            if (reactContext != null) {
                this.mDebugOverlayController = new DebugOverlayController(reactContext);
            }
            if (this.mDevSettings.isHotModuleReplacementEnabled() && this.mCurrentContext != null) {
                try {
                    URL sourceUrl = new URL(getSourceUrl());
                    ((HMRClient) this.mCurrentContext.getJSModule(HMRClient.class)).enable(AbstractSpiCall.ANDROID_CLIENT_TYPE, sourceUrl.getPath().substring(1), sourceUrl.getHost(), sourceUrl.getPort());
                } catch (MalformedURLException e) {
                    showNewJavaError(e.getMessage(), e);
                }
            }
            reloadSettings();
        }
    }

    public void reloadSettings() {
        reload();
    }

    public void onInternalSettingsChanged() {
        reloadSettings();
    }

    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        if (this.mRedBoxDialog != null) {
            this.mRedBoxDialog.dismiss();
        }
        if (this.mDevSettings.isRemoteJSDebugEnabled()) {
            reloadJSInProxyMode(showProgressDialog());
        } else {
            reloadJSFromServer(this.mDevServerHelper.getDevServerBundleURL((String) Assertions.assertNotNull(this.mJSAppBundleName)));
        }
    }

    public void isPackagerRunning(PackagerStatusCallback callback) {
        this.mDevServerHelper.isPackagerRunning(callback);
    }

    @Nullable
    public File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile) {
        return this.mDevServerHelper.downloadBundleResourceFromUrlSync(resourceURL, outputFile);
    }

    @Nullable
    public String getLastErrorTitle() {
        return this.mLastErrorTitle;
    }

    @Nullable
    public StackFrame[] getLastErrorStack() {
        return this.mLastErrorStack;
    }

    public void onPackagerReloadCommand() {
        UiThreadUtil.runOnUiThread(new 17(this));
    }

    public void onCaptureHeapCommand() {
        UiThreadUtil.runOnUiThread(new 18(this));
    }

    public void onPokeSamplingProfilerCommand(@Nullable WebSocket webSocket) {
        UiThreadUtil.runOnUiThread(new 19(this, webSocket));
    }

    private void handleCaptureHeap() {
        JSCHeapCapture.captureHeap(this.mApplicationContext.getCacheDir().getPath(), JSCHeapUpload.captureCallback(this.mDevServerHelper.getHeapCaptureUploadUrl()));
    }

    private void handlePokeSamplingProfiler(@Nullable WebSocket webSocket) {
        try {
            for (String result : JSCSamplingProfiler.poke(60000)) {
                String result2;
                Toast.makeText(this.mCurrentContext, result2 == null ? "Started JSC Sampling Profiler" : "Stopped JSC Sampling Profiler", 1).show();
                if (webSocket != null) {
                    MediaType mediaType = WebSocket.TEXT;
                    if (result2 == null) {
                        result2 = "";
                    }
                    webSocket.sendMessage(RequestBody.create(mediaType, result2));
                } else if (result2 != null) {
                    new JscProfileTask(getSourceUrl(), null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{result2});
                }
            }
        } catch (ProfilerException e) {
            showNewJavaError(e.getMessage(), e);
        } catch (IOException e2) {
            showNewJavaError(e2.getMessage(), e2);
        }
    }

    private void updateLastErrorInfo(String message, StackFrame[] stack, int errorCookie, ErrorType errorType) {
        this.mLastErrorTitle = message;
        this.mLastErrorStack = stack;
        this.mLastErrorCookie = errorCookie;
        this.mLastErrorType = errorType;
    }

    private void reloadJSInProxyMode(AlertDialog progressDialog) {
        this.mDevServerHelper.launchJSDevtools();
        this.mReactInstanceCommandsHandler.onReloadWithJSDebugger(new 20(this, progressDialog));
    }

    private JSExecutorConnectCallback getExecutorConnectCallback(AlertDialog progressDialog, SimpleSettableFuture<Boolean> future) {
        return new 21(this, future, progressDialog);
    }

    private AlertDialog showProgressDialog() {
        AlertDialog dialog = new Builder(this.mApplicationContext).setTitle(R.string.catalyst_jsload_title).setMessage(this.mApplicationContext.getString(this.mDevSettings.isRemoteJSDebugEnabled() ? R.string.catalyst_remotedbg_message : R.string.catalyst_jsload_message)).create();
        dialog.getWindow().setType(Const.MESSAGE_OXIMETER_PARAMS);
        dialog.show();
        return dialog;
    }

    public void reloadJSFromServer(String bundleURL) {
        AlertDialog progressDialog = showProgressDialog();
        this.mDevServerHelper.downloadBundleFromURL(new 22(this, progressDialog), this.mJSBundleTempFile, bundleURL);
        progressDialog.setOnCancelListener(new 23(this));
        progressDialog.setCancelable(true);
    }

    private void reload() {
        if (this.mIsDevSupportEnabled) {
            if (this.mDebugOverlayController != null) {
                this.mDebugOverlayController.setFpsDebugViewVisible(this.mDevSettings.isFpsDebugEnabled());
            }
            if (!this.mIsShakeDetectorStarted) {
                this.mShakeDetector.start((SensorManager) this.mApplicationContext.getSystemService("sensor"));
                this.mIsShakeDetectorStarted = true;
            }
            if (!this.mIsReceiverRegistered) {
                IntentFilter filter = new IntentFilter();
                filter.addAction(DevServerHelper.getReloadAppAction(this.mApplicationContext));
                this.mApplicationContext.registerReceiver(this.mReloadAppBroadcastReceiver, filter);
                this.mIsReceiverRegistered = true;
            }
            this.mDevServerHelper.openPackagerConnection(this);
            this.mDevServerHelper.openInspectorConnection();
            if (this.mDevSettings.isReloadOnJSChangeEnabled()) {
                this.mDevServerHelper.startPollingOnChangeEndpoint(new 24(this));
                return;
            } else {
                this.mDevServerHelper.stopPollingOnChangeEndpoint();
                return;
            }
        }
        if (this.mDebugOverlayController != null) {
            this.mDebugOverlayController.setFpsDebugViewVisible(false);
        }
        if (this.mIsShakeDetectorStarted) {
            this.mShakeDetector.stop();
            this.mIsShakeDetectorStarted = false;
        }
        if (this.mIsReceiverRegistered) {
            this.mApplicationContext.unregisterReceiver(this.mReloadAppBroadcastReceiver);
            this.mIsReceiverRegistered = false;
        }
        if (this.mRedBoxDialog != null) {
            this.mRedBoxDialog.dismiss();
        }
        if (this.mDevOptionsDialog != null) {
            this.mDevOptionsDialog.dismiss();
        }
        this.mDevServerHelper.closePackagerConnection();
        this.mDevServerHelper.closeInspectorConnection();
        this.mDevServerHelper.stopPollingOnChangeEndpoint();
    }
}
