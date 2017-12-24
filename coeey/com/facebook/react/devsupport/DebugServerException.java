package com.facebook.react.devsupport;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class DebugServerException extends RuntimeException {
    private static final String GENERIC_ERROR_MESSAGE = "\n\nTry the following to fix the issue:\n• Ensure that the packager server is running\n• Ensure that your device/emulator is connected to your machine and has USB debugging enabled - run 'adb devices' to see a list of connected devices\n• Ensure Airplane Mode is disabled\n• If you're on a physical device connected to the same machine, run 'adb reverse tcp:8081 tcp:8081' to forward requests from your device\n• If your device is on the same Wi-Fi network, set 'Debug server host & port for device' in 'Dev settings' to your machine's IP address and the port of the local dev server - e.g. 10.0.1.1:8081\n\n";

    public static DebugServerException makeGeneric(String reason, Throwable t) {
        return makeGeneric(reason, "", t);
    }

    public static DebugServerException makeGeneric(String reason, String extra, Throwable t) {
        return new DebugServerException(reason + GENERIC_ERROR_MESSAGE + extra, t);
    }

    private DebugServerException(String description, String fileName, int lineNumber, int column) {
        super(description + "\n  at " + fileName + ":" + lineNumber + ":" + column);
    }

    public DebugServerException(String description) {
        super(description);
    }

    public DebugServerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    @Nullable
    public static DebugServerException parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(str);
            return new DebugServerException(jsonObject.getString("description"), shortenFileName(jsonObject.getString("filename")), jsonObject.getInt("lineNumber"), jsonObject.getInt("column"));
        } catch (JSONException e) {
            FLog.w(ReactConstants.TAG, "Could not parse DebugServerException from: " + str, e);
            return null;
        }
    }

    private static String shortenFileName(String fullFileName) {
        String[] parts = fullFileName.split("/");
        return parts[parts.length - 1];
    }
}
