package com.facebook.react.devsupport;

import android.util.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSCHeapUpload {
    public static JSCHeapCapture$CaptureCallback captureCallback(final String uploadUrl) {
        return new JSCHeapCapture$CaptureCallback() {

            class C13041 implements Callback {
                C13041() {
                }

                public void onFailure(Call call, IOException e) {
                    Log.e("JSCHeapCapture", "Upload of heap capture failed: " + e.toString());
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        Log.e("JSCHeapCapture", "Upload of heap capture failed with code: " + Integer.toString(response.code()));
                    }
                }
            }

            public void onComplete(List<File> captures, List<JSCHeapCapture$CaptureException> failures) {
                for (JSCHeapCapture$CaptureException e : failures) {
                    Log.e("JSCHeapCapture", e.getMessage());
                }
                OkHttpClient httpClient = new Builder().build();
                for (File path : captures) {
                    httpClient.newCall(new Request.Builder().url(uploadUrl).method(HttpRequest.METHOD_POST, RequestBody.create(MediaType.parse("application/json"), path)).build()).enqueue(new C13041());
                }
            }
        };
    }
}
