package okhttp3.ws;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;

public interface WebSocket {
    public static final MediaType BINARY = MediaType.parse("application/vnd.okhttp.websocket+binary");
    public static final MediaType TEXT = MediaType.parse("application/vnd.okhttp.websocket+text; charset=utf-8");

    void close(int i, String str) throws IOException;

    void sendMessage(RequestBody requestBody) throws IOException;

    void sendPing(Buffer buffer) throws IOException;
}
