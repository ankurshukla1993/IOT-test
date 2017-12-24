package com.cooey.messaging;

import com.google.gson.GsonBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class MessagesWebSocketConnector {
    private final String fromUserId;
    private WebSocketClient mWebSocketClient;
    String url;

    public WebSocketClient getmWebSocketClient() {
        return this.mWebSocketClient;
    }

    public MessagesWebSocketConnector(String fromUserId) {
        this.fromUserId = fromUserId;
        this.url = "ws://13.84.48.19:8080/messages/" + fromUserId;
    }

    public void connect(final MessageReceivedCallback messageReceivedCallback) {
        try {
            this.mWebSocketClient = new WebSocketClient(new URI(this.url)) {
                public void onOpen(ServerHandshake serverHandshake) {
                    String data = "";
                }

                public void onMessage(String messageString) {
                    messageReceivedCallback.onMessageReceived((UserMessage) new GsonBuilder().create().fromJson(messageString, UserMessage.class));
                }

                public void onClose(int i, String s, boolean b) {
                }

                public void onError(Exception e) {
                }
            };
            this.mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (this.mWebSocketClient != null) {
            this.mWebSocketClient.close();
        }
    }
}
