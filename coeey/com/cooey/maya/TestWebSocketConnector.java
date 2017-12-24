package com.cooey.maya;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpointConfig.Builder;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

public class TestWebSocketConnector {
    final ClientManager client = ClientManager.createClient();
    public String patientId = "";
    public Session session;
    String url;

    public TestWebSocketConnector(String patientId) {
        this.patientId = patientId;
        this.url = "ws://207.46.129.96:8080/patient/" + patientId;
    }

    public void connect(final Whole<String> messageHandler) {
        try {
            this.client.connectToServer(new Endpoint() {
                public void onOpen(Session session, EndpointConfig config) {
                    TestWebSocketConnector.this.session = session;
                    session.addMessageHandler(messageHandler);
                }
            }, Builder.create().build(), URI.create(this.url));
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void close() {
        this.client.shutdown();
        try {
            this.session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
