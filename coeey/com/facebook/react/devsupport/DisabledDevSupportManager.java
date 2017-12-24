package com.facebook.react.devsupport;

import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.devsupport.DevServerHelper.PackagerStatusCallback;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import com.facebook.react.modules.debug.DeveloperSettings;
import java.io.File;
import javax.annotation.Nullable;

public class DisabledDevSupportManager implements DevSupportManager {
    private final DefaultNativeModuleCallExceptionHandler mDefaultNativeModuleCallExceptionHandler = new DefaultNativeModuleCallExceptionHandler();

    public void showNewJavaError(String message, Throwable e) {
    }

    public void addCustomDevOption(String optionName, DevOptionHandler optionHandler) {
    }

    public void showNewJSError(String message, ReadableArray details, int errorCookie) {
    }

    public void updateJSError(String message, ReadableArray details, int errorCookie) {
    }

    public void hideRedboxDialog() {
    }

    public void showDevOptionsDialog() {
    }

    public void setDevSupportEnabled(boolean isDevSupportEnabled) {
    }

    public boolean getDevSupportEnabled() {
        return false;
    }

    public DeveloperSettings getDevSettings() {
        return null;
    }

    public void onNewReactContextCreated(ReactContext reactContext) {
    }

    public void onReactInstanceDestroyed(ReactContext reactContext) {
    }

    public String getSourceMapUrl() {
        return null;
    }

    public String getSourceUrl() {
        return null;
    }

    public String getJSBundleURLForRemoteDebugging() {
        return null;
    }

    public String getDownloadedJSBundleFile() {
        return null;
    }

    public String getHeapCaptureUploadUrl() {
        return null;
    }

    public boolean hasUpToDateJSBundleInCache() {
        return false;
    }

    public void reloadSettings() {
    }

    public void handleReloadJS() {
    }

    public void reloadJSFromServer(String bundleURL) {
    }

    public void isPackagerRunning(PackagerStatusCallback callback) {
    }

    @Nullable
    public File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile) {
        return null;
    }

    @Nullable
    public String getLastErrorTitle() {
        return null;
    }

    @Nullable
    public StackFrame[] getLastErrorStack() {
        return null;
    }

    public void handleException(Exception e) {
        this.mDefaultNativeModuleCallExceptionHandler.handleException(e);
    }
}
