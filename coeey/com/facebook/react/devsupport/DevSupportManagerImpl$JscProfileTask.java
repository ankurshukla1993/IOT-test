package com.facebook.react.devsupport;

import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

class DevSupportManagerImpl$JscProfileTask extends AsyncTask<String, Void, Void> {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final String mSourceUrl;

    private DevSupportManagerImpl$JscProfileTask(String sourceUrl) {
        this.mSourceUrl = sourceUrl;
    }

    protected Void doInBackground(String... jsonData) {
        try {
            String jscProfileUrl = Uri.parse(this.mSourceUrl).buildUpon().path("/jsc-profile").query(null).build().toString();
            OkHttpClient client = new OkHttpClient();
            for (String json : jsonData) {
                client.newCall(new Builder().url(jscProfileUrl).post(RequestBody.create(JSON, json)).build()).execute();
            }
        } catch (Throwable e) {
            FLog.m112e(ReactConstants.TAG, "Failed not talk to server", e);
        }
        return null;
    }
}
