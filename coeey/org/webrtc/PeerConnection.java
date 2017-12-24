package org.webrtc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.webrtc.DataChannel.Init;

public class PeerConnection {
    private final List<MediaStream> localStreams = new LinkedList();
    private final long nativeObserver;
    private final long nativePeerConnection;
    private List<RtpReceiver> receivers = new LinkedList();
    private List<RtpSender> senders = new LinkedList();

    public interface Observer {
        void onAddStream(MediaStream mediaStream);

        void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr);

        void onDataChannel(DataChannel dataChannel);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr);

        void onIceConnectionChange(IceConnectionState iceConnectionState);

        void onIceConnectionReceivingChange(boolean z);

        void onIceGatheringChange(IceGatheringState iceGatheringState);

        void onRemoveStream(MediaStream mediaStream);

        void onRenegotiationNeeded();

        void onSignalingChange(SignalingState signalingState);
    }

    public enum BundlePolicy {
        BALANCED,
        MAXBUNDLE,
        MAXCOMPAT
    }

    public enum CandidateNetworkPolicy {
        ALL,
        LOW_COST
    }

    public enum ContinualGatheringPolicy {
        GATHER_ONCE,
        GATHER_CONTINUALLY
    }

    public enum IceConnectionState {
        NEW,
        CHECKING,
        CONNECTED,
        COMPLETED,
        FAILED,
        DISCONNECTED,
        CLOSED
    }

    public enum IceGatheringState {
        NEW,
        GATHERING,
        COMPLETE
    }

    public static class IceServer {
        public final String password;
        public final TlsCertPolicy tlsCertPolicy;
        public final String uri;
        public final String username;

        public IceServer(String uri) {
            this(uri, "", "");
        }

        public IceServer(String uri, String username, String password) {
            this(uri, username, password, TlsCertPolicy.TLS_CERT_POLICY_SECURE);
        }

        public IceServer(String uri, String username, String password, TlsCertPolicy tlsCertPolicy) {
            this.uri = uri;
            this.username = username;
            this.password = password;
            this.tlsCertPolicy = tlsCertPolicy;
        }

        public String toString() {
            return this.uri + " [" + this.username + ":" + this.password + "] [" + this.tlsCertPolicy + "]";
        }
    }

    public enum IceTransportsType {
        NONE,
        RELAY,
        NOHOST,
        ALL
    }

    public enum KeyType {
        RSA,
        ECDSA
    }

    public static class RTCConfiguration {
        public boolean audioJitterBufferFastAccelerate;
        public int audioJitterBufferMaxPackets;
        public BundlePolicy bundlePolicy = BundlePolicy.BALANCED;
        public CandidateNetworkPolicy candidateNetworkPolicy;
        public ContinualGatheringPolicy continualGatheringPolicy;
        public int iceBackupCandidatePairPingInterval;
        public int iceCandidatePoolSize;
        public Integer iceCheckMinInterval;
        public int iceConnectionReceivingTimeout;
        public List<IceServer> iceServers;
        public IceTransportsType iceTransportsType = IceTransportsType.ALL;
        public KeyType keyType;
        public boolean presumeWritableWhenFullyRelayed;
        public boolean pruneTurnPorts;
        public RtcpMuxPolicy rtcpMuxPolicy = RtcpMuxPolicy.REQUIRE;
        public TcpCandidatePolicy tcpCandidatePolicy = TcpCandidatePolicy.ENABLED;

        public RTCConfiguration(List<IceServer> iceServers) {
            CandidateNetworkPolicy candidateNetworkPolicy = this.candidateNetworkPolicy;
            this.candidateNetworkPolicy = CandidateNetworkPolicy.ALL;
            this.iceServers = iceServers;
            this.audioJitterBufferMaxPackets = 50;
            this.audioJitterBufferFastAccelerate = false;
            this.iceConnectionReceivingTimeout = -1;
            this.iceBackupCandidatePairPingInterval = -1;
            this.keyType = KeyType.ECDSA;
            this.continualGatheringPolicy = ContinualGatheringPolicy.GATHER_ONCE;
            this.iceCandidatePoolSize = 0;
            this.pruneTurnPorts = false;
            this.presumeWritableWhenFullyRelayed = false;
            this.iceCheckMinInterval = null;
        }
    }

    public enum RtcpMuxPolicy {
        NEGOTIATE,
        REQUIRE
    }

    public enum SignalingState {
        STABLE,
        HAVE_LOCAL_OFFER,
        HAVE_LOCAL_PRANSWER,
        HAVE_REMOTE_OFFER,
        HAVE_REMOTE_PRANSWER,
        CLOSED
    }

    public enum TcpCandidatePolicy {
        ENABLED,
        DISABLED
    }

    public enum TlsCertPolicy {
        TLS_CERT_POLICY_SECURE,
        TLS_CERT_POLICY_INSECURE_NO_CHECK
    }

    private static native void freeObserver(long j);

    private static native void freePeerConnection(long j);

    private native boolean nativeAddIceCandidate(String str, int i, String str2);

    private native boolean nativeAddLocalStream(long j);

    private native RtpSender nativeCreateSender(String str, String str2);

    private native List<RtpReceiver> nativeGetReceivers();

    private native List<RtpSender> nativeGetSenders();

    private native boolean nativeGetStats(StatsObserver statsObserver, long j);

    private native boolean nativeRemoveIceCandidates(IceCandidate[] iceCandidateArr);

    private native void nativeRemoveLocalStream(long j);

    private native boolean nativeStartRtcEventLog(int i, int i2);

    private native void nativeStopRtcEventLog();

    public native void close();

    public native void createAnswer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native DataChannel createDataChannel(String str, Init init);

    public native void createOffer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native SessionDescription getLocalDescription();

    public native SessionDescription getRemoteDescription();

    public native IceConnectionState iceConnectionState();

    public native IceGatheringState iceGatheringState();

    public native boolean nativeSetConfiguration(RTCConfiguration rTCConfiguration, long j);

    public native void setLocalDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native void setRemoteDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native SignalingState signalingState();

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    PeerConnection(long nativePeerConnection, long nativeObserver) {
        this.nativePeerConnection = nativePeerConnection;
        this.nativeObserver = nativeObserver;
    }

    public boolean setConfiguration(RTCConfiguration config) {
        return nativeSetConfiguration(config, this.nativeObserver);
    }

    public boolean addIceCandidate(IceCandidate candidate) {
        return nativeAddIceCandidate(candidate.sdpMid, candidate.sdpMLineIndex, candidate.sdp);
    }

    public boolean removeIceCandidates(IceCandidate[] candidates) {
        return nativeRemoveIceCandidates(candidates);
    }

    public boolean addStream(MediaStream stream) {
        if (!nativeAddLocalStream(stream.nativeStream)) {
            return false;
        }
        this.localStreams.add(stream);
        return true;
    }

    public void removeStream(MediaStream stream) {
        nativeRemoveLocalStream(stream.nativeStream);
        this.localStreams.remove(stream);
    }

    public RtpSender createSender(String kind, String stream_id) {
        RtpSender new_sender = nativeCreateSender(kind, stream_id);
        if (new_sender != null) {
            this.senders.add(new_sender);
        }
        return new_sender;
    }

    public List<RtpSender> getSenders() {
        for (RtpSender sender : this.senders) {
            sender.dispose();
        }
        this.senders = nativeGetSenders();
        return Collections.unmodifiableList(this.senders);
    }

    public List<RtpReceiver> getReceivers() {
        for (RtpReceiver receiver : this.receivers) {
            receiver.dispose();
        }
        this.receivers = nativeGetReceivers();
        return Collections.unmodifiableList(this.receivers);
    }

    public boolean getStats(StatsObserver observer, MediaStreamTrack track) {
        return nativeGetStats(observer, track == null ? 0 : track.nativeTrack);
    }

    public boolean startRtcEventLog(int file_descriptor, int max_size_bytes) {
        return nativeStartRtcEventLog(file_descriptor, max_size_bytes);
    }

    public void stopRtcEventLog() {
        nativeStopRtcEventLog();
    }

    public void dispose() {
        close();
        for (MediaStream stream : this.localStreams) {
            nativeRemoveLocalStream(stream.nativeStream);
            stream.dispose();
        }
        this.localStreams.clear();
        for (RtpSender sender : this.senders) {
            sender.dispose();
        }
        this.senders.clear();
        for (RtpReceiver receiver : this.receivers) {
            receiver.dispose();
        }
        this.receivers.clear();
        freePeerConnection(this.nativePeerConnection);
        freeObserver(this.nativeObserver);
    }
}
