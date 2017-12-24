package okhttp3.internal.cache;

import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy.Factory;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor implements Interceptor {
    private static final ResponseBody EMPTY_BODY = new C24961();
    final InternalCache cache;

    static class C24961 extends ResponseBody {
        C24961() {
        }

        public MediaType contentType() {
            return null;
        }

        public long contentLength() {
            return 0;
        }

        public BufferedSource source() {
            return new Buffer();
        }
    }

    public CacheInterceptor(InternalCache cache) {
        this.cache = cache;
    }

    public Response intercept(Chain chain) throws IOException {
        Response cacheCandidate = this.cache != null ? this.cache.get(chain.request()) : null;
        CacheStrategy strategy = new Factory(System.currentTimeMillis(), chain.request(), cacheCandidate).get();
        Request networkRequest = strategy.networkRequest;
        Response cacheResponse = strategy.cacheResponse;
        if (this.cache != null) {
            this.cache.trackResponse(strategy);
        }
        if (cacheCandidate != null && cacheResponse == null) {
            Util.closeQuietly(cacheCandidate.body());
        }
        if (networkRequest == null && cacheResponse == null) {
            return new Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(EMPTY_BODY).sentRequestAtMillis(-1).receivedResponseAtMillis(System.currentTimeMillis()).build();
        }
        if (networkRequest == null) {
            return cacheResponse.newBuilder().cacheResponse(stripBody(cacheResponse)).build();
        }
        Response networkResponse = null;
        try {
            Response response;
            networkResponse = chain.proceed(networkRequest);
            if (cacheResponse != null) {
                if (validate(cacheResponse, networkResponse)) {
                    response = cacheResponse.newBuilder().headers(combine(cacheResponse.headers(), networkResponse.headers())).cacheResponse(stripBody(cacheResponse)).networkResponse(stripBody(networkResponse)).build();
                    networkResponse.body().close();
                    this.cache.trackConditionalCacheHit();
                    this.cache.update(cacheResponse, response);
                    return response;
                }
                Util.closeQuietly(cacheResponse.body());
            }
            response = networkResponse.newBuilder().cacheResponse(stripBody(cacheResponse)).networkResponse(stripBody(networkResponse)).build();
            if (HttpHeaders.hasBody(response)) {
                return cacheWritingResponse(maybeCache(response, networkResponse.request(), this.cache), response);
            }
            return response;
        } finally {
            if (networkResponse == null && cacheCandidate != null) {
                Util.closeQuietly(cacheCandidate.body());
            }
        }
    }

    private static Response stripBody(Response response) {
        if (response == null || response.body() == null) {
            return response;
        }
        return response.newBuilder().body(null).build();
    }

    private CacheRequest maybeCache(Response userResponse, Request networkRequest, InternalCache responseCache) throws IOException {
        if (responseCache == null) {
            return null;
        }
        if (CacheStrategy.isCacheable(userResponse, networkRequest)) {
            return responseCache.put(userResponse);
        }
        if (!HttpMethod.invalidatesCache(networkRequest.method())) {
            return null;
        }
        try {
            responseCache.remove(networkRequest);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink cacheBodyUnbuffered = cacheRequest.body();
        if (cacheBodyUnbuffered == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
        return response.newBuilder().body(new RealResponseBody(response.headers(), Okio.buffer(new Source() {
            boolean cacheRequestClosed;

            public long read(Buffer sink, long byteCount) throws IOException {
                try {
                    long bytesRead = source.read(sink, byteCount);
                    if (bytesRead == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            cacheBody.close();
                        }
                        return -1;
                    }
                    sink.copyTo(cacheBody.buffer(), sink.size() - bytesRead, bytesRead);
                    cacheBody.emitCompleteSegments();
                    return bytesRead;
                } catch (IOException e) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e;
                }
            }

            public Timeout timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!(this.cacheRequestClosed || Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        }))).build();
    }

    private static boolean validate(Response cached, Response network) {
        if (network.code() == 304) {
            return true;
        }
        Date lastModified = cached.headers().getDate("Last-Modified");
        if (lastModified != null) {
            Date networkLastModified = network.headers().getDate("Last-Modified");
            if (networkLastModified != null && networkLastModified.getTime() < lastModified.getTime()) {
                return true;
            }
        }
        return false;
    }

    private static Headers combine(Headers cachedHeaders, Headers networkHeaders) {
        int i;
        Headers.Builder result = new Headers.Builder();
        int size = cachedHeaders.size();
        for (i = 0; i < size; i++) {
            String fieldName = cachedHeaders.name(i);
            String value = cachedHeaders.value(i);
            if (!(com.google.common.net.HttpHeaders.WARNING.equalsIgnoreCase(fieldName) && value.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) && (!isEndToEnd(fieldName) || networkHeaders.get(fieldName) == null)) {
                Internal.instance.addLenient(result, fieldName, value);
            }
        }
        size = networkHeaders.size();
        for (i = 0; i < size; i++) {
            fieldName = networkHeaders.name(i);
            if (!"Content-Length".equalsIgnoreCase(fieldName) && isEndToEnd(fieldName)) {
                Internal.instance.addLenient(result, fieldName, networkHeaders.value(i));
            }
        }
        return result.build();
    }

    static boolean isEndToEnd(String fieldName) {
        if (com.google.common.net.HttpHeaders.CONNECTION.equalsIgnoreCase(fieldName) || "Keep-Alive".equalsIgnoreCase(fieldName) || com.google.common.net.HttpHeaders.PROXY_AUTHENTICATE.equalsIgnoreCase(fieldName) || "Proxy-Authorization".equalsIgnoreCase(fieldName) || com.google.common.net.HttpHeaders.TE.equalsIgnoreCase(fieldName) || "Trailers".equalsIgnoreCase(fieldName) || com.google.common.net.HttpHeaders.TRANSFER_ENCODING.equalsIgnoreCase(fieldName) || com.google.common.net.HttpHeaders.UPGRADE.equalsIgnoreCase(fieldName)) {
            return false;
        }
        return true;
    }
}
