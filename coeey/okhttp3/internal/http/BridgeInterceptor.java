package okhttp3.internal.http;

import com.google.common.net.HttpHeaders;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    public Response intercept(Chain chain) throws IOException {
        Request userRequest = chain.request();
        Builder requestBuilder = userRequest.newBuilder();
        RequestBody body = userRequest.body();
        if (body != null) {
            MediaType contentType = body.contentType();
            if (contentType != null) {
                requestBuilder.header("Content-Type", contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                requestBuilder.header("Content-Length", Long.toString(contentLength));
                requestBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
            } else {
                requestBuilder.header(HttpHeaders.TRANSFER_ENCODING, "chunked");
                requestBuilder.removeHeader("Content-Length");
            }
        }
        if (userRequest.header(HttpHeaders.HOST) == null) {
            requestBuilder.header(HttpHeaders.HOST, Util.hostHeader(userRequest.url(), false));
        }
        if (userRequest.header(HttpHeaders.CONNECTION) == null) {
            requestBuilder.header(HttpHeaders.CONNECTION, "Keep-Alive");
        }
        boolean transparentGzip = false;
        if (userRequest.header("Accept-Encoding") == null) {
            transparentGzip = true;
            requestBuilder.header("Accept-Encoding", HttpRequest.ENCODING_GZIP);
        }
        List<Cookie> cookies = this.cookieJar.loadForRequest(userRequest.url());
        if (!cookies.isEmpty()) {
            requestBuilder.header(HttpHeaders.COOKIE, cookieHeader(cookies));
        }
        if (userRequest.header("User-Agent") == null) {
            requestBuilder.header("User-Agent", Version.userAgent());
        }
        Response networkResponse = chain.proceed(requestBuilder.build());
        HttpHeaders.receiveHeaders(this.cookieJar, userRequest.url(), networkResponse.headers());
        Response.Builder responseBuilder = networkResponse.newBuilder().request(userRequest);
        if (transparentGzip && HttpRequest.ENCODING_GZIP.equalsIgnoreCase(networkResponse.header("Content-Encoding")) && HttpHeaders.hasBody(networkResponse)) {
            Source responseBody = new GzipSource(networkResponse.body().source());
            Headers strippedHeaders = networkResponse.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build();
            responseBuilder.headers(strippedHeaders);
            responseBuilder.body(new RealResponseBody(strippedHeaders, Okio.buffer(responseBody)));
        }
        return responseBuilder.build();
    }

    private String cookieHeader(List<Cookie> cookies) {
        StringBuilder cookieHeader = new StringBuilder();
        int size = cookies.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cookieHeader.append("; ");
            }
            Cookie cookie = (Cookie) cookies.get(i);
            cookieHeader.append(cookie.name()).append('=').append(cookie.value());
        }
        return cookieHeader.toString();
    }
}
