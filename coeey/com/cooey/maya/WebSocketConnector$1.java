package com.cooey.maya;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpointConfig.Builder;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;

class WebSocketConnector$1 implements Runnable {
    final /* synthetic */ WebSocketConnector this$0;
    final /* synthetic */ Whole val$messageHandler;

    class C09841 extends Endpoint {
        C09841() {
        }

        public void onOpen(Session session, EndpointConfig config) {
            WebSocketConnector$1.this.this$0.session = session;
            session.addMessageHandler(WebSocketConnector$1.this.val$messageHandler);
        }
    }

    WebSocketConnector$1(WebSocketConnector this$0, Whole whole) {
        this.this$0 = this$0;
        this.val$messageHandler = whole;
    }

    public void run() {
        try {
            this.this$0.client.getProperties().put("org.glassfish.tyrus.client.ClientManager.ContainerTimeout", Integer.valueOf(30000));
            this.this$0.client.getProperties().put("org.glassfish.tyrus.client.http.logUpgrade", Boolean.valueOf(true));
            this.this$0.client.connectToServer(new C09841(), Builder.create().build(), URI.create(this.this$0.url));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
