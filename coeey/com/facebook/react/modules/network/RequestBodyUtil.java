package com.facebook.react.modules.network;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;

class RequestBodyUtil {
    private static final String CONTENT_ENCODING_GZIP = "gzip";

    RequestBodyUtil() {
    }

    public static boolean isGzipEncoding(@Nullable String encodingType) {
        return "gzip".equalsIgnoreCase(encodingType);
    }

    @Nullable
    public static InputStream getFileInputStream(Context context, String fileContentUriStr) {
        try {
            return context.getContentResolver().openInputStream(Uri.parse(fileContentUriStr));
        } catch (Exception e) {
            FLog.e(ReactConstants.TAG, "Could not retrieve file for contentUri " + fileContentUriStr, e);
            return null;
        }
    }

    @Nullable
    public static RequestBody createGzip(MediaType mediaType, String body) {
        ByteArrayOutputStream gzipByteArrayOutputStream = new ByteArrayOutputStream();
        try {
            OutputStream gzipOutputStream = new GZIPOutputStream(gzipByteArrayOutputStream);
            gzipOutputStream.write(body.getBytes());
            gzipOutputStream.close();
            return RequestBody.create(mediaType, gzipByteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    public static RequestBody create(MediaType mediaType, InputStream inputStream) {
        return new 1(mediaType, inputStream);
    }

    public static ProgressRequestBody createProgressRequest(RequestBody requestBody, ProgressListener listener) {
        return new ProgressRequestBody(requestBody, listener);
    }

    public static RequestBody getEmptyBody(String method) {
        if (method.equals(HttpRequest.METHOD_POST) || method.equals(HttpRequest.METHOD_PUT) || method.equals("PATCH")) {
            return RequestBody.create(null, ByteString.EMPTY);
        }
        return null;
    }
}
