package com.facebook.react.devsupport;

import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.devsupport.DevServerHelper.PackagerStatusCallback;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import com.facebook.react.modules.debug.DeveloperSettings;
import java.io.File;
import javax.annotation.Nullable;

public interface DevSupportManager extends NativeModuleCallExceptionHandler {
    void addCustomDevOption(String str, DevOptionHandler devOptionHandler);

    @Nullable
    File downloadBundleResourceFromUrlSync(String str, File file);

    DeveloperSettings getDevSettings();

    boolean getDevSupportEnabled();

    String getDownloadedJSBundleFile();

    String getHeapCaptureUploadUrl();

    String getJSBundleURLForRemoteDebugging();

    @Nullable
    StackFrame[] getLastErrorStack();

    @Nullable
    String getLastErrorTitle();

    String getSourceMapUrl();

    String getSourceUrl();

    void handleReloadJS();

    boolean hasUpToDateJSBundleInCache();

    void hideRedboxDialog();

    void isPackagerRunning(PackagerStatusCallback packagerStatusCallback);

    void onNewReactContextCreated(ReactContext reactContext);

    void onReactInstanceDestroyed(ReactContext reactContext);

    void reloadJSFromServer(String str);

    void reloadSettings();

    void setDevSupportEnabled(boolean z);

    void showDevOptionsDialog();

    void showNewJSError(String str, ReadableArray readableArray, int i);

    void showNewJavaError(String str, Throwable th);

    void updateJSError(String str, ReadableArray readableArray, int i);
}
