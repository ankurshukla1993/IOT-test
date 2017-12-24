package com.oney.WebRTCModule;

import android.support.annotation.Nullable;
import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.facebook.share.internal.ShareConstants;
import java.nio.charset.Charset;
import org.webrtc.DataChannel;
import org.webrtc.DataChannel.Buffer;
import org.webrtc.DataChannel.Observer;
import org.webrtc.DataChannel.State;

class DataChannelObserver implements Observer {
    private final DataChannel mDataChannel;
    private final int mId;
    private final int peerConnectionId;
    private final WebRTCModule webRTCModule;

    DataChannelObserver(WebRTCModule webRTCModule, int peerConnectionId, int id, DataChannel dataChannel) {
        this.peerConnectionId = peerConnectionId;
        this.mId = id;
        this.mDataChannel = dataChannel;
        this.webRTCModule = webRTCModule;
    }

    @Nullable
    private String dataChannelStateString(State dataChannelState) {
        switch (dataChannelState) {
            case CONNECTING:
                return "connecting";
            case OPEN:
                return "open";
            case CLOSING:
                return "closing";
            case CLOSED:
                return "closed";
            default:
                return null;
        }
    }

    public void onBufferedAmountChange(long amount) {
    }

    public void onStateChange() {
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.mId);
        params.putInt("peerConnectionId", this.peerConnectionId);
        params.putString("state", dataChannelStateString(this.mDataChannel.state()));
        this.webRTCModule.sendEvent("dataChannelStateChanged", params);
    }

    public void onMessage(Buffer buffer) {
        byte[] bytes;
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.mId);
        params.putInt("peerConnectionId", this.peerConnectionId);
        if (buffer.data.hasArray()) {
            bytes = buffer.data.array();
        } else {
            bytes = new byte[buffer.data.remaining()];
            buffer.data.get(bytes);
        }
        if (buffer.binary) {
            params.putString("type", "binary");
            params.putString("data", Base64.encodeToString(bytes, 2));
        } else {
            params.putString("type", ReactTextShadowNode.PROP_TEXT);
            params.putString("data", new String(bytes, Charset.forName("UTF-8")));
        }
        this.webRTCModule.sendEvent("dataChannelReceiveMessage", params);
    }
}
