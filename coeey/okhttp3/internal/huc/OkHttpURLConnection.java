package okhttp3.internal.huc;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.JavaNetHeaders;
import okhttp3.internal.URLFilter;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.platform.Platform;

public final class OkHttpURLConnection extends HttpURLConnection implements Callback {
    private static final Set<String> METHODS = new LinkedHashSet(Arrays.asList(new String[]{HttpRequest.METHOD_OPTIONS, HttpRequest.METHOD_GET, HttpRequest.METHOD_HEAD, HttpRequest.METHOD_POST, HttpRequest.METHOD_PUT, HttpRequest.METHOD_DELETE, HttpRequest.METHOD_TRACE, "PATCH"}));
    public static final String RESPONSE_SOURCE = (Platform.get().getPrefix() + "-Response-Source");
    public static final String SELECTED_PROTOCOL = (Platform.get().getPrefix() + "-Selected-Protocol");
    Call call;
    private Throwable callFailure;
    OkHttpClient client;
    boolean connectPending;
    private boolean executed;
    private long fixedContentLength;
    Handshake handshake;
    private final Object lock;
    private final NetworkInterceptor networkInterceptor;
    Response networkResponse;
    Proxy proxy;
    private Builder requestHeaders;
    private Response response;
    private Headers responseHeaders;
    URLFilter urlFilter;

    final class NetworkInterceptor implements Interceptor {
        private boolean proceed;

        NetworkInterceptor() {
        }

        public void proceed() {
            synchronized (OkHttpURLConnection.this.lock) {
                this.proceed = true;
                OkHttpURLConnection.this.lock.notifyAll();
            }
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (OkHttpURLConnection.this.urlFilter != null) {
                OkHttpURLConnection.this.urlFilter.checkURLPermitted(request.url().url());
            }
            synchronized (OkHttpURLConnection.this.lock) {
                OkHttpURLConnection.this.connectPending = false;
                OkHttpURLConnection.this.proxy = chain.connection().route().proxy();
                OkHttpURLConnection.this.handshake = chain.connection().handshake();
                OkHttpURLConnection.this.lock.notifyAll();
                while (!this.proceed) {
                    try {
                        OkHttpURLConnection.this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
            }
            if (request.body() instanceof OutputStreamRequestBody) {
                request = ((OutputStreamRequestBody) request.body()).prepareToSendRequest(request);
            }
            Response response = chain.proceed(request);
            synchronized (OkHttpURLConnection.this.lock) {
                OkHttpURLConnection.this.networkResponse = response;
                OkHttpURLConnection.this.url = response.request().url().url();
            }
            return response;
        }
    }

    static final class UnexpectedException extends IOException {
        static final Interceptor INTERCEPTOR = new C25211();

        static class C25211 implements Interceptor {
            C25211() {
            }

            public Response intercept(Chain chain) throws IOException {
                Throwable e;
                try {
                    return chain.proceed(chain.request());
                } catch (Error e2) {
                    e = e2;
                    throw new UnexpectedException(e);
                } catch (RuntimeException e3) {
                    e = e3;
                    throw new UnexpectedException(e);
                }
            }
        }

        public UnexpectedException(Throwable cause) {
            super(cause);
        }
    }

    public OkHttpURLConnection(URL url, OkHttpClient client) {
        super(url);
        this.networkInterceptor = new NetworkInterceptor();
        this.requestHeaders = new Builder();
        this.fixedContentLength = -1;
        this.lock = new Object();
        this.connectPending = true;
        this.client = client;
    }

    public OkHttpURLConnection(URL url, OkHttpClient client, URLFilter urlFilter) {
        this(url, client);
        this.urlFilter = urlFilter;
    }

    public void connect() throws IOException {
        if (!this.executed) {
            Call call = buildCall();
            this.executed = true;
            call.enqueue(this);
            synchronized (this.lock) {
                while (this.connectPending && this.response == null && this.callFailure == null) {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                if (this.callFailure != null) {
                    throw propagate(this.callFailure);
                }
            }
        }
    }

    public void disconnect() {
        if (this.call != null) {
            this.networkInterceptor.proceed();
            this.call.cancel();
        }
    }

    public InputStream getErrorStream() {
        InputStream inputStream = null;
        try {
            Response response = getResponse();
            if (HttpHeaders.hasBody(response) && response.code() >= 400) {
                inputStream = response.body().byteStream();
            }
        } catch (IOException e) {
        }
        return inputStream;
    }

    private Headers getHeaders() throws IOException {
        if (this.responseHeaders == null) {
            Response response = getResponse();
            this.responseHeaders = response.headers().newBuilder().add(SELECTED_PROTOCOL, response.protocol().toString()).add(RESPONSE_SOURCE, responseSourceHeader(response)).build();
        }
        return this.responseHeaders;
    }

    private static String responseSourceHeader(Response response) {
        if (response.networkResponse() == null) {
            if (response.cacheResponse() == null) {
                return "NONE";
            }
            return "CACHE " + response.code();
        } else if (response.cacheResponse() == null) {
            return "NETWORK " + response.code();
        } else {
            return "CONDITIONAL_CACHE " + response.networkResponse().code();
        }
    }

    public String getHeaderField(int position) {
        String str = null;
        try {
            Headers headers = getHeaders();
            if (position >= 0 && position < headers.size()) {
                str = headers.value(position);
            }
        } catch (IOException e) {
        }
        return str;
    }

    public String getHeaderField(String fieldName) {
        if (fieldName != null) {
            return getHeaders().get(fieldName);
        }
        try {
            return StatusLine.get(getResponse()).toString();
        } catch (IOException e) {
            return null;
        }
    }

    public String getHeaderFieldKey(int position) {
        String str = null;
        try {
            Headers headers = getHeaders();
            if (position >= 0 && position < headers.size()) {
                str = headers.name(position);
            }
        } catch (IOException e) {
        }
        return str;
    }

    public Map<String, List<String>> getHeaderFields() {
        try {
            return JavaNetHeaders.toMultimap(getHeaders(), StatusLine.get(getResponse()).toString());
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }

    public Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return JavaNetHeaders.toMultimap(this.requestHeaders.build(), null);
        }
        throw new IllegalStateException("Cannot access request header fields after connection is set");
    }

    public InputStream getInputStream() throws IOException {
        if (this.doInput) {
            Response response = getResponse();
            if (response.code() < 400) {
                return response.body().byteStream();
            }
            throw new FileNotFoundException(this.url.toString());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    public OutputStream getOutputStream() throws IOException {
        OutputStreamRequestBody requestBody = (OutputStreamRequestBody) buildCall().request().body();
        if (requestBody == null) {
            throw new ProtocolException("method does not support a request body: " + this.method);
        }
        if (requestBody instanceof StreamedRequestBody) {
            connect();
            this.networkInterceptor.proceed();
        }
        if (!requestBody.isClosed()) {
            return requestBody.outputStream();
        }
        throw new ProtocolException("cannot write request body after response has been read");
    }

    public Permission getPermission() throws IOException {
        int hostPort;
        URL url = getURL();
        String hostname = url.getHost();
        if (url.getPort() != -1) {
            hostPort = url.getPort();
        } else {
            hostPort = HttpUrl.defaultPort(url.getProtocol());
        }
        if (usingProxy()) {
            InetSocketAddress proxyAddress = (InetSocketAddress) this.client.proxy().address();
            hostname = proxyAddress.getHostName();
            hostPort = proxyAddress.getPort();
        }
        return new SocketPermission(hostname + ":" + hostPort, "connect, resolve");
    }

    public String getRequestProperty(String field) {
        if (field == null) {
            return null;
        }
        return this.requestHeaders.get(field);
    }

    public void setConnectTimeout(int timeoutMillis) {
        this.client = this.client.newBuilder().connectTimeout((long) timeoutMillis, TimeUnit.MILLISECONDS).build();
    }

    public void setInstanceFollowRedirects(boolean followRedirects) {
        this.client = this.client.newBuilder().followRedirects(followRedirects).build();
    }

    public boolean getInstanceFollowRedirects() {
        return this.client.followRedirects();
    }

    public int getConnectTimeout() {
        return this.client.connectTimeoutMillis();
    }

    public void setReadTimeout(int timeoutMillis) {
        this.client = this.client.newBuilder().readTimeout((long) timeoutMillis, TimeUnit.MILLISECONDS).build();
    }

    public int getReadTimeout() {
        return this.client.readTimeoutMillis();
    }

    private Call buildCall() throws IOException {
        boolean stream = true;
        if (this.call != null) {
            return this.call;
        }
        this.connected = true;
        if (this.doOutput) {
            if (this.method.equals(HttpRequest.METHOD_GET)) {
                this.method = HttpRequest.METHOD_POST;
            } else if (!HttpMethod.permitsRequestBody(this.method)) {
                throw new ProtocolException(this.method + " does not support writing");
            }
        }
        if (this.requestHeaders.get("User-Agent") == null) {
            this.requestHeaders.add("User-Agent", defaultUserAgent());
        }
        OutputStreamRequestBody requestBody = null;
        if (HttpMethod.permitsRequestBody(this.method)) {
            if (this.requestHeaders.get("Content-Type") == null) {
                this.requestHeaders.add("Content-Type", HttpRequest.CONTENT_TYPE_FORM);
            }
            if (this.fixedContentLength == -1 && this.chunkLength <= 0) {
                stream = false;
            }
            long contentLength = -1;
            String contentLengthString = this.requestHeaders.get("Content-Length");
            if (this.fixedContentLength != -1) {
                contentLength = this.fixedContentLength;
            } else if (contentLengthString != null) {
                contentLength = Long.parseLong(contentLengthString);
            }
            requestBody = stream ? new StreamedRequestBody(contentLength) : new BufferedRequestBody(contentLength);
            requestBody.timeout().timeout((long) this.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
        Request request = new Request.Builder().url(Internal.instance.getHttpUrlChecked(getURL().toString())).headers(this.requestHeaders.build()).method(this.method, requestBody).build();
        if (this.urlFilter != null) {
            this.urlFilter.checkURLPermitted(request.url().url());
        }
        OkHttpClient.Builder clientBuilder = this.client.newBuilder();
        clientBuilder.interceptors().clear();
        clientBuilder.interceptors().add(UnexpectedException.INTERCEPTOR);
        clientBuilder.networkInterceptors().clear();
        clientBuilder.networkInterceptors().add(this.networkInterceptor);
        clientBuilder.dispatcher(new Dispatcher(this.client.dispatcher().executorService()));
        if (!getUseCaches()) {
            clientBuilder.cache(null);
        }
        Call newCall = clientBuilder.build().newCall(request);
        this.call = newCall;
        return newCall;
    }

    private String defaultUserAgent() {
        String agent = System.getProperty("http.agent");
        return agent != null ? Util.toHumanReadableAscii(agent) : Version.userAgent();
    }

    private Response getResponse() throws IOException {
        if (this.response != null) {
            return this.response;
        }
        if (this.networkResponse != null) {
            return this.networkResponse;
        }
        if (this.callFailure != null) {
            throw propagate(this.callFailure);
        }
        Call call = buildCall();
        this.networkInterceptor.proceed();
        OutputStreamRequestBody requestBody = (OutputStreamRequestBody) call.request().body();
        if (requestBody != null) {
            requestBody.outputStream().close();
        }
        if (this.executed) {
            synchronized (this.lock) {
                while (this.response == null && this.callFailure == null) {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
            }
        } else {
            this.executed = true;
            try {
                onResponse(call, call.execute());
            } catch (IOException e2) {
                onFailure(call, e2);
            }
        }
        synchronized (this.lock) {
            if (this.callFailure != null) {
                throw propagate(this.callFailure);
            } else if (this.response != null) {
                Response response = this.response;
                return response;
            } else {
                throw new AssertionError();
            }
        }
    }

    public boolean usingProxy() {
        if (this.proxy != null) {
            return true;
        }
        Proxy clientProxy = this.client.proxy();
        if (clientProxy == null || clientProxy.type() == Type.DIRECT) {
            return false;
        }
        return true;
    }

    public String getResponseMessage() throws IOException {
        return getResponse().message();
    }

    public int getResponseCode() throws IOException {
        return getResponse().code();
    }

    public void setRequestProperty(String field, String newValue) {
        if (this.connected) {
            throw new IllegalStateException("Cannot set request property after connection is made");
        } else if (field == null) {
            throw new NullPointerException("field == null");
        } else if (newValue == null) {
            Platform.get().log(5, "Ignoring header " + field + " because its value was null.", null);
        } else if ("X-Android-Transports".equals(field) || "X-Android-Protocols".equals(field)) {
            setProtocols(newValue, false);
        } else {
            this.requestHeaders.set(field, newValue);
        }
    }

    public void setIfModifiedSince(long newValue) {
        super.setIfModifiedSince(newValue);
        if (this.ifModifiedSince != 0) {
            this.requestHeaders.set(com.google.common.net.HttpHeaders.IF_MODIFIED_SINCE, HttpDate.format(new Date(this.ifModifiedSince)));
        } else {
            this.requestHeaders.removeAll(com.google.common.net.HttpHeaders.IF_MODIFIED_SINCE);
        }
    }

    public void addRequestProperty(String field, String value) {
        if (this.connected) {
            throw new IllegalStateException("Cannot add request property after connection is made");
        } else if (field == null) {
            throw new NullPointerException("field == null");
        } else if (value == null) {
            Platform.get().log(5, "Ignoring header " + field + " because its value was null.", null);
        } else if ("X-Android-Transports".equals(field) || "X-Android-Protocols".equals(field)) {
            setProtocols(value, true);
        } else {
            this.requestHeaders.add(field, value);
        }
    }

    private void setProtocols(String protocolsString, boolean append) {
        List<Protocol> protocolsList = new ArrayList();
        if (append) {
            protocolsList.addAll(this.client.protocols());
        }
        String[] split = protocolsString.split(",", -1);
        int length = split.length;
        int i = 0;
        while (i < length) {
            try {
                protocolsList.add(Protocol.get(split[i]));
                i++;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        this.client = this.client.newBuilder().protocols(protocolsList).build();
    }

    public void setRequestMethod(String method) throws ProtocolException {
        if (METHODS.contains(method)) {
            this.method = method;
            return;
        }
        throw new ProtocolException("Expected one of " + METHODS + " but was " + method);
    }

    public void setFixedLengthStreamingMode(int contentLength) {
        setFixedLengthStreamingMode((long) contentLength);
    }

    public void setFixedLengthStreamingMode(long contentLength) {
        if (this.connected) {
            throw new IllegalStateException("Already connected");
        } else if (this.chunkLength > 0) {
            throw new IllegalStateException("Already in chunked mode");
        } else if (contentLength < 0) {
            throw new IllegalArgumentException("contentLength < 0");
        } else {
            this.fixedContentLength = contentLength;
            this.fixedContentLength = (int) Math.min(contentLength, 2147483647L);
        }
    }

    public void onFailure(Call call, IOException e) {
        synchronized (this.lock) {
            if (e instanceof UnexpectedException) {
                e = e.getCause();
            }
            this.callFailure = e;
            this.lock.notifyAll();
        }
    }

    public void onResponse(Call call, Response response) {
        synchronized (this.lock) {
            this.response = response;
            this.handshake = response.handshake();
            this.url = response.request().url().url();
            this.lock.notifyAll();
        }
    }

    private static IOException propagate(Throwable throwable) throws IOException {
        if (throwable instanceof IOException) {
            throw ((IOException) throwable);
        } else if (throwable instanceof Error) {
            throw ((Error) throwable);
        } else if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        } else {
            throw new AssertionError();
        }
    }
}
