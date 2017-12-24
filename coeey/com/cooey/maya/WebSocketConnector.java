package com.cooey.maya;

import com.cooey.maya.utils.MayaConstants;
import javax.websocket.ClientEndpoint;
import javax.websocket.MessageHandler.Whole;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

@ClientEndpoint(configurator = ClientConfigurator.class)
public class WebSocketConnector {
    public String caretakerId = "";
    final ClientManager client = ClientManager.createClient();
    public String patientId = "";
    public Session session;
    String url;

    public WebSocketConnector(String patientId) {
        this.patientId = patientId;
        this.url = MayaConstants.MAYA_PATIENT_URL + patientId;
    }

    public WebSocketConnector(String patientId, String caretakerId) {
        this.patientId = patientId;
        this.caretakerId = caretakerId;
        this.url = MayaConstants.MAYA_CARETAKER_URL + caretakerId + "/patient/" + patientId;
    }

    public void connect(Whole<String> messageHandler) {
        new Thread(new 1(this, messageHandler)).start();
    }

    public Session getSession() {
        return this.session;
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
