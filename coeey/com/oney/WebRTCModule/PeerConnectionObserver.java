package com.oney.WebRTCModule;

import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.facebook.share.internal.ShareConstants;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.webrtc.AudioTrack;
import org.webrtc.DataChannel;
import org.webrtc.DataChannel.Buffer;
import org.webrtc.DataChannel.Init;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnection.IceConnectionState;
import org.webrtc.PeerConnection.IceGatheringState;
import org.webrtc.PeerConnection.Observer;
import org.webrtc.PeerConnection.SignalingState;
import org.webrtc.RtpReceiver;
import org.webrtc.StatsObserver;
import org.webrtc.StatsReport;
import org.webrtc.StatsReport.Value;
import org.webrtc.VideoTrack;

class PeerConnectionObserver implements Observer {
    private static final String TAG = WebRTCModule.TAG;
    private final SparseArray<DataChannel> dataChannels = new SparseArray();
    private final int id;
    private PeerConnection peerConnection;
    final Map<String, MediaStream> remoteStreams;
    final Map<String, MediaStreamTrack> remoteTracks;
    private SoftReference<StringBuilder> statsToJSONStringBuilder = new SoftReference(null);
    private final WebRTCModule webRTCModule;

    PeerConnectionObserver(WebRTCModule webRTCModule, int id) {
        this.webRTCModule = webRTCModule;
        this.id = id;
        this.remoteStreams = new HashMap();
        this.remoteTracks = new HashMap();
    }

    PeerConnection getPeerConnection() {
        return this.peerConnection;
    }

    void setPeerConnection(PeerConnection peerConnection) {
        this.peerConnection = peerConnection;
    }

    void close() {
        this.peerConnection.close();
        this.remoteStreams.clear();
        this.remoteTracks.clear();
        this.dataChannels.clear();
    }

    void createDataChannel(String label, ReadableMap config) {
        Init init = new Init();
        if (config != null) {
            if (config.hasKey(ShareConstants.WEB_DIALOG_PARAM_ID)) {
                init.id = config.getInt(ShareConstants.WEB_DIALOG_PARAM_ID);
            }
            if (config.hasKey("ordered")) {
                init.ordered = config.getBoolean("ordered");
            }
            if (config.hasKey("maxRetransmitTime")) {
                init.maxRetransmitTimeMs = config.getInt("maxRetransmitTime");
            }
            if (config.hasKey("maxRetransmits")) {
                init.maxRetransmits = config.getInt("maxRetransmits");
            }
            if (config.hasKey("protocol")) {
                init.protocol = config.getString("protocol");
            }
            if (config.hasKey("negotiated")) {
                init.negotiated = config.getBoolean("negotiated");
            }
        }
        DataChannel dataChannel = this.peerConnection.createDataChannel(label, init);
        int dataChannelId = init.id;
        if (-1 != dataChannelId) {
            this.dataChannels.put(dataChannelId, dataChannel);
            registerDataChannelObserver(dataChannelId, dataChannel);
        }
    }

    void dataChannelClose(int dataChannelId) {
        DataChannel dataChannel = (DataChannel) this.dataChannels.get(dataChannelId);
        if (dataChannel != null) {
            dataChannel.close();
            this.dataChannels.remove(dataChannelId);
            return;
        }
        Log.d(TAG, "dataChannelClose() dataChannel is null");
    }

    void dataChannelSend(int dataChannelId, String data, String type) {
        DataChannel dataChannel = (DataChannel) this.dataChannels.get(dataChannelId);
        if (dataChannel != null) {
            byte[] byteArray;
            if (type.equals(ReactTextShadowNode.PROP_TEXT)) {
                try {
                    byteArray = data.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.d(TAG, "Could not encode text string as UTF-8.");
                    return;
                }
            } else if (type.equals("binary")) {
                byteArray = Base64.decode(data, 2);
            } else {
                Log.e(TAG, "Unsupported data type: " + type);
                return;
            }
            dataChannel.send(new Buffer(ByteBuffer.wrap(byteArray), type.equals("binary")));
            return;
        }
        Log.d(TAG, "dataChannelSend() dataChannel is null");
    }

    void getStats(String trackId, final Callback cb) {
        MediaStreamTrack track = null;
        if (!(trackId == null || trackId.isEmpty())) {
            track = (MediaStreamTrack) this.webRTCModule.localTracks.get(trackId);
            if (track == null) {
                track = (MediaStreamTrack) this.remoteTracks.get(trackId);
                if (track == null) {
                    Log.e(TAG, "peerConnectionGetStats() MediaStreamTrack not found for id: " + trackId);
                    return;
                }
            }
        }
        this.peerConnection.getStats(new StatsObserver() {
            public void onComplete(StatsReport[] reports) {
                cb.invoke(new Object[]{PeerConnectionObserver.this.statsToJSON(reports)});
            }
        }, track);
    }

    private String statsToJSON(StatsReport[] reports) {
        StringBuilder s = (StringBuilder) this.statsToJSONStringBuilder.get();
        if (s == null) {
            s = new StringBuilder();
            this.statsToJSONStringBuilder = new SoftReference(s);
        }
        s.append('[');
        int reportCount = reports.length;
        for (int i = 0; i < reportCount; i++) {
            StatsReport report = reports[i];
            if (i != 0) {
                s.append(',');
            }
            s.append("{\"id\":\"").append(report.id).append("\",\"type\":\"").append(report.type).append("\",\"timestamp\":").append(report.timestamp).append(",\"values\":[");
            Value[] values = report.values;
            int valueCount = values.length;
            for (int j = 0; j < valueCount; j++) {
                Value v = values[j];
                if (j != 0) {
                    s.append(',');
                }
                s.append("{\"").append(v.name).append("\":\"").append(v.value).append("\"}");
            }
            s.append("]}");
        }
        s.append("]");
        String r = s.toString();
        s.setLength(0);
        return r;
    }

    public void onIceCandidate(IceCandidate candidate) {
        Log.d(TAG, "onIceCandidate");
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        WritableMap candidateParams = Arguments.createMap();
        candidateParams.putInt("sdpMLineIndex", candidate.sdpMLineIndex);
        candidateParams.putString("sdpMid", candidate.sdpMid);
        candidateParams.putString("candidate", candidate.sdp);
        params.putMap("candidate", candidateParams);
        this.webRTCModule.sendEvent("peerConnectionGotICECandidate", params);
    }

    public void onIceCandidatesRemoved(IceCandidate[] candidates) {
        Log.d(TAG, "onIceCandidatesRemoved");
    }

    public void onIceConnectionChange(IceConnectionState iceConnectionState) {
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        params.putString("iceConnectionState", iceConnectionStateString(iceConnectionState));
        this.webRTCModule.sendEvent("peerConnectionIceConnectionChanged", params);
    }

    public void onIceConnectionReceivingChange(boolean var1) {
    }

    public void onIceGatheringChange(IceGatheringState iceGatheringState) {
        Log.d(TAG, "onIceGatheringChange" + iceGatheringState.name());
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        params.putString("iceGatheringState", iceGatheringStateString(iceGatheringState));
        this.webRTCModule.sendEvent("peerConnectionIceGatheringChanged", params);
    }

    private String getReactTagForStream(MediaStream mediaStream) {
        for (Entry<String, MediaStream> e : this.remoteStreams.entrySet()) {
            if (((MediaStream) e.getValue()).equals(mediaStream)) {
                return (String) e.getKey();
            }
        }
        return null;
    }

    public void onAddStream(MediaStream mediaStream) {
        int i;
        String streamReactTag = null;
        String streamId = mediaStream.label();
        if ("default".equals(streamId)) {
            for (Entry<String, MediaStream> e : this.remoteStreams.entrySet()) {
                if (((MediaStream) e.getValue()).equals(mediaStream)) {
                    streamReactTag = (String) e.getKey();
                    break;
                }
            }
        }
        if (streamReactTag == null) {
            streamReactTag = this.webRTCModule.getNextStreamUUID();
            this.remoteStreams.put(streamReactTag, mediaStream);
        }
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        params.putString("streamId", streamId);
        params.putString("streamReactTag", streamReactTag);
        WritableArray tracks = Arguments.createArray();
        for (i = 0; i < mediaStream.videoTracks.size(); i++) {
            VideoTrack track = (VideoTrack) mediaStream.videoTracks.get(i);
            String trackId = track.id();
            this.remoteTracks.put(trackId, track);
            WritableMap trackInfo = Arguments.createMap();
            trackInfo.putString(ShareConstants.WEB_DIALOG_PARAM_ID, trackId);
            trackInfo.putString("label", "Video");
            trackInfo.putString("kind", track.kind());
            trackInfo.putBoolean(ViewProps.ENABLED, track.enabled());
            trackInfo.putString("readyState", track.state().toString());
            trackInfo.putBoolean("remote", true);
            tracks.pushMap(trackInfo);
        }
        for (i = 0; i < mediaStream.audioTracks.size(); i++) {
            AudioTrack track2 = (AudioTrack) mediaStream.audioTracks.get(i);
            trackId = track2.id();
            this.remoteTracks.put(trackId, track2);
            trackInfo = Arguments.createMap();
            trackInfo.putString(ShareConstants.WEB_DIALOG_PARAM_ID, trackId);
            trackInfo.putString("label", "Audio");
            trackInfo.putString("kind", track2.kind());
            trackInfo.putBoolean(ViewProps.ENABLED, track2.enabled());
            trackInfo.putString("readyState", track2.state().toString());
            trackInfo.putBoolean("remote", true);
            tracks.pushMap(trackInfo);
        }
        params.putArray("tracks", tracks);
        this.webRTCModule.sendEvent("peerConnectionAddedStream", params);
    }

    public void onRemoveStream(MediaStream mediaStream) {
        String streamReactTag = getReactTagForStream(mediaStream);
        if (streamReactTag == null) {
            Log.w(TAG, "onRemoveStream - no remote stream for id: " + mediaStream.label());
            return;
        }
        Iterator it = mediaStream.videoTracks.iterator();
        while (it.hasNext()) {
            this.remoteTracks.remove(((VideoTrack) it.next()).id());
        }
        it = mediaStream.audioTracks.iterator();
        while (it.hasNext()) {
            this.remoteTracks.remove(((AudioTrack) it.next()).id());
        }
        this.remoteStreams.remove(streamReactTag);
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        params.putString("streamId", streamReactTag);
        this.webRTCModule.sendEvent("peerConnectionRemovedStream", params);
    }

    public void onDataChannel(DataChannel dataChannel) {
        int dataChannelId = -1;
        for (int i = 65536; i <= Integer.MAX_VALUE; i++) {
            if (this.dataChannels.get(i, null) == null) {
                dataChannelId = i;
                break;
            }
        }
        if (-1 != dataChannelId) {
            WritableMap dataChannelParams = Arguments.createMap();
            dataChannelParams.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, dataChannelId);
            dataChannelParams.putString("label", dataChannel.label());
            WritableMap params = Arguments.createMap();
            params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
            params.putMap("dataChannel", dataChannelParams);
            this.dataChannels.put(dataChannelId, dataChannel);
            registerDataChannelObserver(dataChannelId, dataChannel);
            this.webRTCModule.sendEvent("peerConnectionDidOpenDataChannel", params);
        }
    }

    private void registerDataChannelObserver(int dcId, DataChannel dataChannel) {
        dataChannel.registerObserver(new DataChannelObserver(this.webRTCModule, this.id, dcId, dataChannel));
    }

    public void onRenegotiationNeeded() {
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        this.webRTCModule.sendEvent("peerConnectionOnRenegotiationNeeded", params);
    }

    public void onSignalingChange(SignalingState signalingState) {
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.id);
        params.putString("signalingState", signalingStateString(signalingState));
        this.webRTCModule.sendEvent("peerConnectionSignalingStateChanged", params);
    }

    public void onAddTrack(RtpReceiver receiver, MediaStream[] mediaStreams) {
        Log.d(TAG, "onAddTrack");
    }

    @Nullable
    private String iceConnectionStateString(IceConnectionState iceConnectionState) {
        switch (iceConnectionState) {
            case NEW:
                return AppSettingsData.STATUS_NEW;
            case CHECKING:
                return "checking";
            case CONNECTED:
                return "connected";
            case COMPLETED:
                return "completed";
            case FAILED:
                return "failed";
            case DISCONNECTED:
                return "disconnected";
            case CLOSED:
                return "closed";
            default:
                return null;
        }
    }

    @Nullable
    private String iceGatheringStateString(IceGatheringState iceGatheringState) {
        switch (iceGatheringState) {
            case NEW:
                return AppSettingsData.STATUS_NEW;
            case GATHERING:
                return "gathering";
            case COMPLETE:
                return "complete";
            default:
                return null;
        }
    }

    @Nullable
    private String signalingStateString(SignalingState signalingState) {
        switch (signalingState) {
            case STABLE:
                return "stable";
            case HAVE_LOCAL_OFFER:
                return "have-local-offer";
            case HAVE_LOCAL_PRANSWER:
                return "have-local-pranswer";
            case HAVE_REMOTE_OFFER:
                return "have-remote-offer";
            case HAVE_REMOTE_PRANSWER:
                return "have-remote-pranswer";
            case CLOSED:
                return "closed";
            default:
                return null;
        }
    }
}
