package com.facebook.react.common.network;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class OkHttpCallUtil {
    private OkHttpCallUtil() {
    }

    public static void cancelTag(OkHttpClient client, Object tag) {
        for (Call call : client.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                return;
            }
        }
        for (Call call2 : client.dispatcher().runningCalls()) {
            if (tag.equals(call2.request().tag())) {
                call2.cancel();
                return;
            }
        }
    }
}
