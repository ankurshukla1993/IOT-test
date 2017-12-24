package org.java_websocket.server;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;

public interface WebSocketServer$WebSocketServerFactory extends WebSocketFactory {
    void close();

    WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list);

    WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft);

    ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException;
}
