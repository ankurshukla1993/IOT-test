package okhttp3.ws;

import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public interface WebSocketListener {
    void onClose(int i, String str);

    void onFailure(IOException iOException, Response response);

    void onMessage(ResponseBody responseBody) throws IOException;

    void onOpen(WebSocket webSocket, Response response);

    void onPong(Buffer buffer);
}
