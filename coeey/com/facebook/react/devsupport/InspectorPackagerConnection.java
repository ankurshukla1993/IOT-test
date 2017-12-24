package com.facebook.react.devsupport;

import android.support.v4.app.NotificationCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Inspector;
import com.facebook.react.bridge.Inspector.LocalConnection;
import com.facebook.react.bridge.Inspector.Page;
import com.facebook.react.bridge.Inspector.RemoteConnection;
import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InspectorPackagerConnection {
    private static final String TAG = "InspectorPackagerConnection";
    private final Connection mConnection;
    private final Map<String, LocalConnection> mInspectorConnections = new HashMap();

    public InspectorPackagerConnection(String url) {
        this.mConnection = new Connection(this, url);
    }

    public void connect() {
        this.mConnection.connect();
    }

    public void closeQuietly() {
        this.mConnection.close();
    }

    public void sendOpenEvent(String pageId) {
        Throwable e;
        try {
            sendEvent("open", makePageIdPayload(pageId));
            return;
        } catch (JSONException e2) {
            e = e2;
        } catch (IOException e3) {
            e = e3;
        }
        FLog.m112e(TAG, "Failed to open page", e);
    }

    void handleProxyMessage(JSONObject message) throws JSONException, IOException {
        String event = message.getString(NotificationCompat.CATEGORY_EVENT);
        Object obj = -1;
        switch (event.hashCode()) {
            case 530405532:
                if (event.equals("disconnect")) {
                    obj = 3;
                    break;
                }
                break;
            case 951351530:
                if (event.equals("connect")) {
                    obj = 2;
                    break;
                }
                break;
            case 1328613653:
                if (event.equals("wrappedEvent")) {
                    obj = 1;
                    break;
                }
                break;
            case 1962251790:
                if (event.equals("getPages")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                sendEvent("getPages", getPages());
                return;
            case 1:
                handleWrappedEvent(message.getJSONObject("payload"));
                return;
            case 2:
                handleConnect(message.getJSONObject("payload"));
                return;
            case 3:
                handleDisconnect(message.getJSONObject("payload"));
                return;
            default:
                throw new IllegalArgumentException("Unknown event: " + event);
        }
    }

    void closeAllConnections() {
        for (Entry<String, LocalConnection> entry : this.mInspectorConnections.entrySet()) {
            ((LocalConnection) entry.getValue()).disconnect();
        }
        this.mInspectorConnections.clear();
    }

    private void handleConnect(JSONObject payload) throws JSONException, IOException {
        final String pageId = payload.getString("pageId");
        if (((LocalConnection) this.mInspectorConnections.remove(pageId)) != null) {
            throw new IllegalStateException("Already connected: " + pageId);
        }
        try {
            this.mInspectorConnections.put(pageId, Inspector.connect(Integer.parseInt(pageId), new RemoteConnection() {
                public void onMessage(String message) {
                    Throwable e;
                    try {
                        InspectorPackagerConnection.this.sendWrappedEvent(pageId, message);
                        return;
                    } catch (IOException e2) {
                        e = e2;
                    } catch (JSONException e3) {
                        e = e3;
                    }
                    FLog.m152w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e);
                }

                public void onDisconnect() {
                    Throwable e;
                    try {
                        InspectorPackagerConnection.this.mInspectorConnections.remove(pageId);
                        InspectorPackagerConnection.this.sendEvent("disconnect", InspectorPackagerConnection.this.makePageIdPayload(pageId));
                        return;
                    } catch (IOException e2) {
                        e = e2;
                    } catch (JSONException e3) {
                        e = e3;
                    }
                    FLog.m152w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e);
                }
            }));
        } catch (Throwable e) {
            FLog.m152w(TAG, "Failed to open page: " + pageId, e);
            sendEvent("disconnect", makePageIdPayload(pageId));
        }
    }

    private void handleDisconnect(JSONObject payload) throws JSONException {
        LocalConnection inspectorConnection = (LocalConnection) this.mInspectorConnections.remove(payload.getString("pageId"));
        if (inspectorConnection != null) {
            inspectorConnection.disconnect();
        }
    }

    private void handleWrappedEvent(JSONObject payload) throws JSONException, IOException {
        String pageId = payload.getString("pageId");
        String wrappedEvent = payload.getString("wrappedEvent");
        LocalConnection inspectorConnection = (LocalConnection) this.mInspectorConnections.get(pageId);
        if (inspectorConnection == null) {
            throw new IllegalStateException("Not connected: " + pageId);
        }
        inspectorConnection.sendMessage(wrappedEvent);
    }

    private JSONArray getPages() throws JSONException {
        List<Page> pages = Inspector.getPages();
        JSONArray array = new JSONArray();
        for (Page page : pages) {
            JSONObject jsonPage = new JSONObject();
            jsonPage.put(ShareConstants.WEB_DIALOG_PARAM_ID, String.valueOf(page.getId()));
            jsonPage.put("title", page.getTitle());
            array.put(jsonPage);
        }
        return array;
    }

    private void sendWrappedEvent(String pageId, String message) throws IOException, JSONException {
        JSONObject payload = new JSONObject();
        payload.put("pageId", pageId);
        payload.put("wrappedEvent", message);
        sendEvent("wrappedEvent", payload);
    }

    private void sendEvent(String name, Object payload) throws JSONException, IOException {
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put(NotificationCompat.CATEGORY_EVENT, name);
        jsonMessage.put("payload", payload);
        this.mConnection.send(jsonMessage);
    }

    private JSONObject makePageIdPayload(String pageId) throws JSONException {
        JSONObject payload = new JSONObject();
        payload.put("pageId", pageId);
        return payload;
    }
}
