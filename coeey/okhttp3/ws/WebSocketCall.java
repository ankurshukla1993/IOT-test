package okhttp3.ws;

import com.google.common.net.HttpHeaders;
import humanize.util.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.net.ProtocolException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.ByteString;

public final class WebSocketCall {
    private final Call call;
    private final String key;
    private final Random random;

    private static class StreamWebSocket extends RealWebSocket {
        private final ExecutorService replyExecutor;
        private final StreamAllocation streamAllocation;

        static RealWebSocket create(StreamAllocation streamAllocation, Response response, Random random, WebSocketListener listener) {
            String url = response.request().url().toString();
            ThreadPoolExecutor replyExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque(), Util.threadFactory(Util.format("OkHttp %s WebSocket", url), true));
            replyExecutor.allowCoreThreadTimeOut(true);
            return new StreamWebSocket(streamAllocation, random, replyExecutor, listener, url);
        }

        private StreamWebSocket(StreamAllocation streamAllocation, Random random, ExecutorService replyExecutor, WebSocketListener listener, String url) {
            super(true, streamAllocation.connection().source, streamAllocation.connection().sink, random, replyExecutor, listener, url);
            this.streamAllocation = streamAllocation;
            this.replyExecutor = replyExecutor;
        }

        protected void close() throws IOException {
            this.replyExecutor.shutdown();
            this.streamAllocation.noNewStreams();
            this.streamAllocation.streamFinished(true, this.streamAllocation.stream());
        }
    }

    public static WebSocketCall create(OkHttpClient client, Request request) {
        return new WebSocketCall(client, request);
    }

    WebSocketCall(OkHttpClient client, Request request) {
        this(client, request, new SecureRandom());
    }

    WebSocketCall(OkHttpClient client, Request request, Random random) {
        if (HttpRequest.METHOD_GET.equals(request.method())) {
            this.random = random;
            byte[] nonce = new byte[16];
            random.nextBytes(nonce);
            this.key = ByteString.of(nonce).base64();
            this.call = client.newBuilder().protocols(Collections.singletonList(Protocol.HTTP_1_1)).build().newCall(request.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build());
            return;
        }
        throw new IllegalArgumentException("Request must be GET: " + request.method());
    }

    public void enqueue(final WebSocketListener listener) {
        Callback responseCallback = new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    WebSocketCall.this.createWebSocket(response, listener);
                } catch (IOException e) {
                    listener.onFailure(e, response);
                }
            }

            public void onFailure(Call call, IOException e) {
                listener.onFailure(e, null);
            }
        };
        Internal.instance.setCallWebSocket(this.call);
        this.call.enqueue(responseCallback);
    }

    public void cancel() {
        this.call.cancel();
    }

    private void createWebSocket(Response response, WebSocketListener listener) throws IOException {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + Constants.SPACE + response.message() + "'");
        }
        String headerConnection = response.header(HttpHeaders.CONNECTION);
        if (HttpHeaders.UPGRADE.equalsIgnoreCase(headerConnection)) {
            String headerUpgrade = response.header(HttpHeaders.UPGRADE);
            if ("websocket".equalsIgnoreCase(headerUpgrade)) {
                String headerAccept = response.header("Sec-WebSocket-Accept");
                String acceptExpected = Util.shaBase64(this.key + WebSocketProtocol.ACCEPT_MAGIC);
                if (acceptExpected.equals(headerAccept)) {
                    RealWebSocket webSocket = StreamWebSocket.create(Internal.instance.callEngineGetStreamAllocation(this.call), response, this.random, listener);
                    listener.onOpen(webSocket, response);
                    do {
                    } while (webSocket.readMessage());
                    return;
                }
                throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + "'");
            }
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + "'");
        }
        throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + "'");
    }
}
