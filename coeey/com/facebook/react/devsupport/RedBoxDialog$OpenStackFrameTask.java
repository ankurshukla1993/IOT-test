package com.facebook.react.devsupport;

import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import org.json.JSONObject;

class RedBoxDialog$OpenStackFrameTask extends AsyncTask<StackFrame, Void, Void> {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final DevSupportManager mDevSupportManager;

    private RedBoxDialog$OpenStackFrameTask(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    protected Void doInBackground(StackFrame... stackFrames) {
        try {
            String openStackFrameUrl = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/open-stack-frame").query(null).build().toString();
            OkHttpClient client = new OkHttpClient();
            for (StackFrame frame : stackFrames) {
                client.newCall(new Builder().url(openStackFrameUrl).post(RequestBody.create(JSON, stackFrameToJson(frame).toString())).build()).execute();
            }
        } catch (Throwable e) {
            FLog.m112e(ReactConstants.TAG, "Could not open stack frame", e);
        }
        return null;
    }

    private static JSONObject stackFrameToJson(StackFrame frame) {
        return new JSONObject(MapBuilder.of(UriUtil.LOCAL_FILE_SCHEME, frame.getFile(), "methodName", frame.getMethod(), "lineNumber", Integer.valueOf(frame.getLine()), "column", Integer.valueOf(frame.getColumn())));
    }
}
