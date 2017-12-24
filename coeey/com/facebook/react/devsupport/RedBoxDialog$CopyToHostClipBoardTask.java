package com.facebook.react.devsupport;

import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

class RedBoxDialog$CopyToHostClipBoardTask extends AsyncTask<String, Void, Void> {
    private final DevSupportManager mDevSupportManager;

    private RedBoxDialog$CopyToHostClipBoardTask(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    protected Void doInBackground(String... clipBoardString) {
        try {
            String sendClipBoardUrl = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/copy-to-clipboard").query(null).build().toString();
            for (String string : clipBoardString) {
                new OkHttpClient().newCall(new Builder().url(sendClipBoardUrl).post(RequestBody.create(null, string)).build()).execute();
            }
        } catch (Throwable e) {
            FLog.m112e(ReactConstants.TAG, "Could not copy to the host clipboard", e);
        }
        return null;
    }
}
