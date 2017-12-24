package okhttp3.internal.http;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.StreamAllocation;
import okio.BufferedSink;
import okio.Okio;

public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }

    public Response intercept(Chain chain) throws IOException {
        HttpStream httpStream = ((RealInterceptorChain) chain).httpStream();
        StreamAllocation streamAllocation = ((RealInterceptorChain) chain).streamAllocation();
        Request request = chain.request();
        long sentRequestMillis = System.currentTimeMillis();
        httpStream.writeRequestHeaders(request);
        if (HttpMethod.permitsRequestBody(request.method()) && request.body() != null) {
            BufferedSink bufferedRequestBody = Okio.buffer(httpStream.createRequestBody(request, request.body().contentLength()));
            request.body().writeTo(bufferedRequestBody);
            bufferedRequestBody.close();
        }
        httpStream.finishRequest();
        Response response = httpStream.readResponseHeaders().request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        if (!(this.forWebSocket && response.code() == 101)) {
            response = response.newBuilder().body(httpStream.openResponseBody(response)).build();
        }
        if ("close".equalsIgnoreCase(response.request().header(HttpHeaders.CONNECTION)) || "close".equalsIgnoreCase(response.header(HttpHeaders.CONNECTION))) {
            streamAllocation.noNewStreams();
        }
        int code = response.code();
        if ((code != ScanDevice.LINK_USB && code != ScanDevice.LINK_AU) || response.body().contentLength() <= 0) {
            return response;
        }
        throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + response.body().contentLength());
    }
}
