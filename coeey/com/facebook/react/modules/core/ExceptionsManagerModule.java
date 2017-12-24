package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ReactModule(name = "ExceptionsManager")
public class ExceptionsManagerModule extends BaseJavaModule {
    protected static final String NAME = "ExceptionsManager";
    private static final Pattern mJsModuleIdPattern = Pattern.compile("(?:^|[/\\\\])(\\d+\\.js)$");
    private final DevSupportManager mDevSupportManager;

    public ExceptionsManagerModule(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    public String getName() {
        return NAME;
    }

    private static String stackFrameToModuleId(ReadableMap frame) {
        if (frame.hasKey(UriUtil.LOCAL_FILE_SCHEME) && !frame.isNull(UriUtil.LOCAL_FILE_SCHEME) && frame.getType(UriUtil.LOCAL_FILE_SCHEME) == ReadableType.String) {
            Matcher matcher = mJsModuleIdPattern.matcher(frame.getString(UriUtil.LOCAL_FILE_SCHEME));
            if (matcher.find()) {
                return matcher.group(1) + ":";
            }
        }
        return "";
    }

    private String stackTraceToString(String message, ReadableArray stack) {
        StringBuilder stringBuilder = new StringBuilder(message).append(", stack:\n");
        for (int i = 0; i < stack.size(); i++) {
            ReadableMap frame = stack.getMap(i);
            stringBuilder.append(frame.getString("methodName")).append("@").append(stackFrameToModuleId(frame)).append(frame.getInt("lineNumber"));
            if (frame.hasKey("column") && !frame.isNull("column") && frame.getType("column") == ReadableType.Number) {
                stringBuilder.append(":").append(frame.getInt("column"));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @ReactMethod
    public void reportFatalException(String title, ReadableArray details, int exceptionId) {
        showOrThrowError(title, details, exceptionId);
    }

    @ReactMethod
    public void reportSoftException(String title, ReadableArray details, int exceptionId) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(title, details, exceptionId);
        } else {
            FLog.e(ReactConstants.TAG, stackTraceToString(title, details));
        }
    }

    private void showOrThrowError(String title, ReadableArray details, int exceptionId) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.showNewJSError(title, details, exceptionId);
            return;
        }
        throw new JavascriptException(stackTraceToString(title, details));
    }

    @ReactMethod
    public void updateExceptionMessage(String title, ReadableArray details, int exceptionId) {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.updateJSError(title, details, exceptionId);
        }
    }

    @ReactMethod
    public void dismissRedbox() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            this.mDevSupportManager.hideRedboxDialog();
        }
    }
}
