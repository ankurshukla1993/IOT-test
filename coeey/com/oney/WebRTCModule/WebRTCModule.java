package com.oney.WebRTCModule;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.cooey.android.users.old.utils.CTConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.webrtc.AudioTrack;
import org.webrtc.EglBase.Context;
import org.webrtc.IceCandidate;
import org.webrtc.Logging;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaConstraints.KeyValuePair;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.PeerConnection.Observer;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SessionDescription;
import org.webrtc.SessionDescription.Type;
import org.webrtc.VideoTrack;

public class WebRTCModule extends ReactContextBaseJavaModule {
    private static final String LANGUAGE = "language";
    static final String TAG = WebRTCModule.class.getCanonicalName();
    private final GetUserMediaImpl getUserMediaImpl;
    final Map<String, MediaStream> localStreams = new HashMap();
    final Map<String, MediaStreamTrack> localTracks = new HashMap();
    final PeerConnectionFactory mFactory;
    private final SparseArray<PeerConnectionObserver> mPeerConnectionObservers = new SparseArray();

    public WebRTCModule(ReactApplicationContext reactContext) {
        super(reactContext);
        PeerConnectionFactory.initializeAndroidGlobals(reactContext, true, true, true);
        this.mFactory = new PeerConnectionFactory(null);
        Context eglContext = EglUtils.getRootEglBaseContext();
        if (eglContext != null) {
            this.mFactory.setVideoHwAccelerationOptions(eglContext, eglContext);
        }
        this.getUserMediaImpl = new GetUserMediaImpl(this, reactContext);
    }

    public String getName() {
        return "WebRTCModule";
    }

    private String getCurrentLanguage() {
        return getReactApplicationContext().getResources().getConfiguration().locale.getLanguage();
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put(LANGUAGE, getCurrentLanguage());
        return constants;
    }

    @ReactMethod
    public void getLanguage(Callback callback) {
        System.out.println("The current language is " + getCurrentLanguage());
        callback.invoke(new Object[]{null, language});
    }

    private PeerConnection getPeerConnection(int id) {
        PeerConnectionObserver pco = (PeerConnectionObserver) this.mPeerConnectionObservers.get(id);
        return pco == null ? null : pco.getPeerConnection();
    }

    void sendEvent(String eventName, @Nullable WritableMap params) {
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit(eventName, params);
    }

    private List<IceServer> createIceServers(ReadableArray iceServersArray) {
        int size = iceServersArray == null ? 0 : iceServersArray.size();
        List<IceServer> iceServers = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            boolean hasUsernameAndCredential;
            ReadableMap iceServerMap = iceServersArray.getMap(i);
            if (iceServerMap.hasKey(CTConstants.USERNAME) && iceServerMap.hasKey("credential")) {
                hasUsernameAndCredential = true;
            } else {
                hasUsernameAndCredential = false;
            }
            if (iceServerMap.hasKey("url")) {
                if (hasUsernameAndCredential) {
                    iceServers.add(new IceServer(iceServerMap.getString("url"), iceServerMap.getString(CTConstants.USERNAME), iceServerMap.getString("credential")));
                } else {
                    iceServers.add(new IceServer(iceServerMap.getString("url")));
                }
            } else if (iceServerMap.hasKey("urls")) {
                switch (5.$SwitchMap$com$facebook$react$bridge$ReadableType[iceServerMap.getType("urls").ordinal()]) {
                    case 1:
                        if (!hasUsernameAndCredential) {
                            iceServers.add(new IceServer(iceServerMap.getString("urls")));
                            break;
                        }
                        iceServers.add(new IceServer(iceServerMap.getString("urls"), iceServerMap.getString(CTConstants.USERNAME), iceServerMap.getString("credential")));
                        break;
                    case 2:
                        ReadableArray urls = iceServerMap.getArray("urls");
                        for (int j = 0; j < urls.size(); j++) {
                            String url = urls.getString(j);
                            if (hasUsernameAndCredential) {
                                iceServers.add(new IceServer(url, iceServerMap.getString(CTConstants.USERNAME), iceServerMap.getString("credential")));
                            } else {
                                iceServers.add(new IceServer(url));
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return iceServers;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.webrtc.PeerConnection.RTCConfiguration parseRTCConfiguration(com.facebook.react.bridge.ReadableMap r11) {
        /*
        r10 = this;
        r7 = 2;
        r6 = 1;
        r4 = 0;
        r5 = -1;
        r2 = 0;
        if (r11 == 0) goto L_0x000d;
    L_0x0007:
        r8 = "iceServers";
        r2 = r11.getArray(r8);
    L_0x000d:
        r1 = r10.createIceServers(r2);
        r0 = new org.webrtc.PeerConnection$RTCConfiguration;
        r0.<init>(r1);
        if (r11 != 0) goto L_0x0019;
    L_0x0018:
        return r0;
    L_0x0019:
        r8 = "iceTransportPolicy";
        r8 = r11.hasKey(r8);
        if (r8 == 0) goto L_0x003e;
    L_0x0021:
        r8 = "iceTransportPolicy";
        r8 = r11.getType(r8);
        r9 = com.facebook.react.bridge.ReadableType.String;
        if (r8 != r9) goto L_0x003e;
    L_0x002b:
        r8 = "iceTransportPolicy";
        r3 = r11.getString(r8);
        if (r3 == 0) goto L_0x003e;
    L_0x0033:
        r8 = r3.hashCode();
        switch(r8) {
            case -1040041239: goto L_0x01ee;
            case 96673: goto L_0x01d8;
            case 3387192: goto L_0x01f9;
            case 108397201: goto L_0x01e3;
            default: goto L_0x003a;
        };
    L_0x003a:
        r8 = r5;
    L_0x003b:
        switch(r8) {
            case 0: goto L_0x0204;
            case 1: goto L_0x020a;
            case 2: goto L_0x0210;
            case 3: goto L_0x0216;
            default: goto L_0x003e;
        };
    L_0x003e:
        r8 = "bundlePolicy";
        r8 = r11.hasKey(r8);
        if (r8 == 0) goto L_0x0063;
    L_0x0046:
        r8 = "bundlePolicy";
        r8 = r11.getType(r8);
        r9 = com.facebook.react.bridge.ReadableType.String;
        if (r8 != r9) goto L_0x0063;
    L_0x0050:
        r8 = "bundlePolicy";
        r3 = r11.getString(r8);
        if (r3 == 0) goto L_0x0063;
    L_0x0058:
        r8 = r3.hashCode();
        switch(r8) {
            case -1924829944: goto L_0x021c;
            case -585638645: goto L_0x0232;
            case -562569205: goto L_0x0227;
            default: goto L_0x005f;
        };
    L_0x005f:
        r7 = r5;
    L_0x0060:
        switch(r7) {
            case 0: goto L_0x023c;
            case 1: goto L_0x0242;
            case 2: goto L_0x0248;
            default: goto L_0x0063;
        };
    L_0x0063:
        r7 = "rtcpMuxPolicy";
        r7 = r11.hasKey(r7);
        if (r7 == 0) goto L_0x0088;
    L_0x006b:
        r7 = "rtcpMuxPolicy";
        r7 = r11.getType(r7);
        r8 = com.facebook.react.bridge.ReadableType.String;
        if (r7 != r8) goto L_0x0088;
    L_0x0075:
        r7 = "rtcpMuxPolicy";
        r3 = r11.getString(r7);
        if (r3 == 0) goto L_0x0088;
    L_0x007d:
        r7 = r3.hashCode();
        switch(r7) {
            case -1109522818: goto L_0x024e;
            case 1095696741: goto L_0x0259;
            default: goto L_0x0084;
        };
    L_0x0084:
        r7 = r5;
    L_0x0085:
        switch(r7) {
            case 0: goto L_0x0264;
            case 1: goto L_0x026a;
            default: goto L_0x0088;
        };
    L_0x0088:
        r7 = "iceCandidatePoolSize";
        r7 = r11.hasKey(r7);
        if (r7 == 0) goto L_0x00a4;
    L_0x0090:
        r7 = "iceCandidatePoolSize";
        r7 = r11.getType(r7);
        r8 = com.facebook.react.bridge.ReadableType.Number;
        if (r7 != r8) goto L_0x00a4;
    L_0x009a:
        r7 = "iceCandidatePoolSize";
        r3 = r11.getInt(r7);
        if (r3 <= 0) goto L_0x00a4;
    L_0x00a2:
        r0.iceCandidatePoolSize = r3;
    L_0x00a4:
        r7 = "tcpCandidatePolicy";
        r7 = r11.hasKey(r7);
        if (r7 == 0) goto L_0x00c9;
    L_0x00ac:
        r7 = "tcpCandidatePolicy";
        r7 = r11.getType(r7);
        r8 = com.facebook.react.bridge.ReadableType.String;
        if (r7 != r8) goto L_0x00c9;
    L_0x00b6:
        r7 = "tcpCandidatePolicy";
        r3 = r11.getString(r7);
        if (r3 == 0) goto L_0x00c9;
    L_0x00be:
        r7 = r3.hashCode();
        switch(r7) {
            case -1609594047: goto L_0x0270;
            case 270940796: goto L_0x027b;
            default: goto L_0x00c5;
        };
    L_0x00c5:
        r7 = r5;
    L_0x00c6:
        switch(r7) {
            case 0: goto L_0x0286;
            case 1: goto L_0x028c;
            default: goto L_0x00c9;
        };
    L_0x00c9:
        r7 = "candidateNetworkPolicy";
        r7 = r11.hasKey(r7);
        if (r7 == 0) goto L_0x00ee;
    L_0x00d1:
        r7 = "candidateNetworkPolicy";
        r7 = r11.getType(r7);
        r8 = com.facebook.react.bridge.ReadableType.String;
        if (r7 != r8) goto L_0x00ee;
    L_0x00db:
        r7 = "candidateNetworkPolicy";
        r3 = r11.getString(r7);
        if (r3 == 0) goto L_0x00ee;
    L_0x00e3:
        r7 = r3.hashCode();
        switch(r7) {
            case -1823688232: goto L_0x029d;
            case 96673: goto L_0x0292;
            default: goto L_0x00ea;
        };
    L_0x00ea:
        r7 = r5;
    L_0x00eb:
        switch(r7) {
            case 0: goto L_0x02a8;
            case 1: goto L_0x02ae;
            default: goto L_0x00ee;
        };
    L_0x00ee:
        r7 = "keyType";
        r7 = r11.hasKey(r7);
        if (r7 == 0) goto L_0x0113;
    L_0x00f6:
        r7 = "keyType";
        r7 = r11.getType(r7);
        r8 = com.facebook.react.bridge.ReadableType.String;
        if (r7 != r8) goto L_0x0113;
    L_0x0100:
        r7 = "keyType";
        r3 = r11.getString(r7);
        if (r3 == 0) goto L_0x0113;
    L_0x0108:
        r7 = r3.hashCode();
        switch(r7) {
            case 81440: goto L_0x02b4;
            case 65786932: goto L_0x02bf;
            default: goto L_0x010f;
        };
    L_0x010f:
        r7 = r5;
    L_0x0110:
        switch(r7) {
            case 0: goto L_0x02ca;
            case 1: goto L_0x02d0;
            default: goto L_0x0113;
        };
    L_0x0113:
        r7 = "continualGatheringPolicy";
        r7 = r11.hasKey(r7);
        if (r7 == 0) goto L_0x0138;
    L_0x011b:
        r7 = "continualGatheringPolicy";
        r7 = r11.getType(r7);
        r8 = com.facebook.react.bridge.ReadableType.String;
        if (r7 != r8) goto L_0x0138;
    L_0x0125:
        r7 = "continualGatheringPolicy";
        r3 = r11.getString(r7);
        if (r3 == 0) goto L_0x0138;
    L_0x012d:
        r7 = r3.hashCode();
        switch(r7) {
            case -2128544187: goto L_0x02d6;
            case 1217112882: goto L_0x02e0;
            default: goto L_0x0134;
        };
    L_0x0134:
        r4 = r5;
    L_0x0135:
        switch(r4) {
            case 0: goto L_0x02eb;
            case 1: goto L_0x02f1;
            default: goto L_0x0138;
        };
    L_0x0138:
        r4 = "audioJitterBufferMaxPackets";
        r4 = r11.hasKey(r4);
        if (r4 == 0) goto L_0x0154;
    L_0x0140:
        r4 = "audioJitterBufferMaxPackets";
        r4 = r11.getType(r4);
        r5 = com.facebook.react.bridge.ReadableType.Number;
        if (r4 != r5) goto L_0x0154;
    L_0x014a:
        r4 = "audioJitterBufferMaxPackets";
        r3 = r11.getInt(r4);
        if (r3 <= 0) goto L_0x0154;
    L_0x0152:
        r0.audioJitterBufferMaxPackets = r3;
    L_0x0154:
        r4 = "iceConnectionReceivingTimeout";
        r4 = r11.hasKey(r4);
        if (r4 == 0) goto L_0x016e;
    L_0x015c:
        r4 = "iceConnectionReceivingTimeout";
        r4 = r11.getType(r4);
        r5 = com.facebook.react.bridge.ReadableType.Number;
        if (r4 != r5) goto L_0x016e;
    L_0x0166:
        r4 = "iceConnectionReceivingTimeout";
        r3 = r11.getInt(r4);
        r0.iceConnectionReceivingTimeout = r3;
    L_0x016e:
        r4 = "iceBackupCandidatePairPingInterval";
        r4 = r11.hasKey(r4);
        if (r4 == 0) goto L_0x0188;
    L_0x0176:
        r4 = "iceBackupCandidatePairPingInterval";
        r4 = r11.getType(r4);
        r5 = com.facebook.react.bridge.ReadableType.Number;
        if (r4 != r5) goto L_0x0188;
    L_0x0180:
        r4 = "iceBackupCandidatePairPingInterval";
        r3 = r11.getInt(r4);
        r0.iceBackupCandidatePairPingInterval = r3;
    L_0x0188:
        r4 = "audioJitterBufferFastAccelerate";
        r4 = r11.hasKey(r4);
        if (r4 == 0) goto L_0x01a2;
    L_0x0190:
        r4 = "audioJitterBufferFastAccelerate";
        r4 = r11.getType(r4);
        r5 = com.facebook.react.bridge.ReadableType.Boolean;
        if (r4 != r5) goto L_0x01a2;
    L_0x019a:
        r4 = "audioJitterBufferFastAccelerate";
        r3 = r11.getBoolean(r4);
        r0.audioJitterBufferFastAccelerate = r3;
    L_0x01a2:
        r4 = "pruneTurnPorts";
        r4 = r11.hasKey(r4);
        if (r4 == 0) goto L_0x01bc;
    L_0x01aa:
        r4 = "pruneTurnPorts";
        r4 = r11.getType(r4);
        r5 = com.facebook.react.bridge.ReadableType.Boolean;
        if (r4 != r5) goto L_0x01bc;
    L_0x01b4:
        r4 = "pruneTurnPorts";
        r3 = r11.getBoolean(r4);
        r0.pruneTurnPorts = r3;
    L_0x01bc:
        r4 = "presumeWritableWhenFullyRelayed";
        r4 = r11.hasKey(r4);
        if (r4 == 0) goto L_0x0018;
    L_0x01c4:
        r4 = "presumeWritableWhenFullyRelayed";
        r4 = r11.getType(r4);
        r5 = com.facebook.react.bridge.ReadableType.Boolean;
        if (r4 != r5) goto L_0x0018;
    L_0x01ce:
        r4 = "presumeWritableWhenFullyRelayed";
        r3 = r11.getBoolean(r4);
        r0.presumeWritableWhenFullyRelayed = r3;
        goto L_0x0018;
    L_0x01d8:
        r8 = "all";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x003a;
    L_0x01e0:
        r8 = r4;
        goto L_0x003b;
    L_0x01e3:
        r8 = "relay";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x003a;
    L_0x01eb:
        r8 = r6;
        goto L_0x003b;
    L_0x01ee:
        r8 = "nohost";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x003a;
    L_0x01f6:
        r8 = r7;
        goto L_0x003b;
    L_0x01f9:
        r8 = "none";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x003a;
    L_0x0201:
        r8 = 3;
        goto L_0x003b;
    L_0x0204:
        r8 = org.webrtc.PeerConnection.IceTransportsType.ALL;
        r0.iceTransportsType = r8;
        goto L_0x003e;
    L_0x020a:
        r8 = org.webrtc.PeerConnection.IceTransportsType.RELAY;
        r0.iceTransportsType = r8;
        goto L_0x003e;
    L_0x0210:
        r8 = org.webrtc.PeerConnection.IceTransportsType.NOHOST;
        r0.iceTransportsType = r8;
        goto L_0x003e;
    L_0x0216:
        r8 = org.webrtc.PeerConnection.IceTransportsType.NONE;
        r0.iceTransportsType = r8;
        goto L_0x003e;
    L_0x021c:
        r7 = "balanced";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x005f;
    L_0x0224:
        r7 = r4;
        goto L_0x0060;
    L_0x0227:
        r7 = "max-compat";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x005f;
    L_0x022f:
        r7 = r6;
        goto L_0x0060;
    L_0x0232:
        r8 = "max-bundle";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x005f;
    L_0x023a:
        goto L_0x0060;
    L_0x023c:
        r7 = org.webrtc.PeerConnection.BundlePolicy.BALANCED;
        r0.bundlePolicy = r7;
        goto L_0x0063;
    L_0x0242:
        r7 = org.webrtc.PeerConnection.BundlePolicy.MAXCOMPAT;
        r0.bundlePolicy = r7;
        goto L_0x0063;
    L_0x0248:
        r7 = org.webrtc.PeerConnection.BundlePolicy.MAXBUNDLE;
        r0.bundlePolicy = r7;
        goto L_0x0063;
    L_0x024e:
        r7 = "negotiate";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x0084;
    L_0x0256:
        r7 = r4;
        goto L_0x0085;
    L_0x0259:
        r7 = "require";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x0084;
    L_0x0261:
        r7 = r6;
        goto L_0x0085;
    L_0x0264:
        r7 = org.webrtc.PeerConnection.RtcpMuxPolicy.NEGOTIATE;
        r0.rtcpMuxPolicy = r7;
        goto L_0x0088;
    L_0x026a:
        r7 = org.webrtc.PeerConnection.RtcpMuxPolicy.REQUIRE;
        r0.rtcpMuxPolicy = r7;
        goto L_0x0088;
    L_0x0270:
        r7 = "enabled";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x00c5;
    L_0x0278:
        r7 = r4;
        goto L_0x00c6;
    L_0x027b:
        r7 = "disabled";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x00c5;
    L_0x0283:
        r7 = r6;
        goto L_0x00c6;
    L_0x0286:
        r7 = org.webrtc.PeerConnection.TcpCandidatePolicy.ENABLED;
        r0.tcpCandidatePolicy = r7;
        goto L_0x00c9;
    L_0x028c:
        r7 = org.webrtc.PeerConnection.TcpCandidatePolicy.DISABLED;
        r0.tcpCandidatePolicy = r7;
        goto L_0x00c9;
    L_0x0292:
        r7 = "all";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x00ea;
    L_0x029a:
        r7 = r4;
        goto L_0x00eb;
    L_0x029d:
        r7 = "low_cost";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x00ea;
    L_0x02a5:
        r7 = r6;
        goto L_0x00eb;
    L_0x02a8:
        r7 = org.webrtc.PeerConnection.CandidateNetworkPolicy.ALL;
        r0.candidateNetworkPolicy = r7;
        goto L_0x00ee;
    L_0x02ae:
        r7 = org.webrtc.PeerConnection.CandidateNetworkPolicy.LOW_COST;
        r0.candidateNetworkPolicy = r7;
        goto L_0x00ee;
    L_0x02b4:
        r7 = "RSA";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x010f;
    L_0x02bc:
        r7 = r4;
        goto L_0x0110;
    L_0x02bf:
        r7 = "ECDSA";
        r7 = r3.equals(r7);
        if (r7 == 0) goto L_0x010f;
    L_0x02c7:
        r7 = r6;
        goto L_0x0110;
    L_0x02ca:
        r7 = org.webrtc.PeerConnection.KeyType.RSA;
        r0.keyType = r7;
        goto L_0x0113;
    L_0x02d0:
        r7 = org.webrtc.PeerConnection.KeyType.ECDSA;
        r0.keyType = r7;
        goto L_0x0113;
    L_0x02d6:
        r6 = "gather_once";
        r6 = r3.equals(r6);
        if (r6 == 0) goto L_0x0134;
    L_0x02de:
        goto L_0x0135;
    L_0x02e0:
        r4 = "gather_continually";
        r4 = r3.equals(r4);
        if (r4 == 0) goto L_0x0134;
    L_0x02e8:
        r4 = r6;
        goto L_0x0135;
    L_0x02eb:
        r4 = org.webrtc.PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
        r0.continualGatheringPolicy = r4;
        goto L_0x0138;
    L_0x02f1:
        r4 = org.webrtc.PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        r0.continualGatheringPolicy = r4;
        goto L_0x0138;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.WebRTCModule.parseRTCConfiguration(com.facebook.react.bridge.ReadableMap):org.webrtc.PeerConnection$RTCConfiguration");
    }

    @ReactMethod
    public void peerConnectionInit(ReadableMap configuration, ReadableMap constraints, int id) {
        Observer observer = new PeerConnectionObserver(this, id);
        observer.setPeerConnection(this.mFactory.createPeerConnection(parseRTCConfiguration(configuration), parseMediaConstraints(constraints), observer));
        this.mPeerConnectionObservers.put(id, observer);
    }

    String getNextStreamUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (getStreamForReactTag(uuid) != null);
        return uuid;
    }

    String getNextTrackUUID() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (getTrackForId(uuid) != null);
        return uuid;
    }

    MediaStream getStreamForReactTag(String streamReactTag) {
        MediaStream stream = (MediaStream) this.localStreams.get(streamReactTag);
        if (stream == null) {
            int size = this.mPeerConnectionObservers.size();
            for (int i = 0; i < size; i++) {
                stream = (MediaStream) ((PeerConnectionObserver) this.mPeerConnectionObservers.valueAt(i)).remoteStreams.get(streamReactTag);
                if (stream != null) {
                    break;
                }
            }
        }
        return stream;
    }

    private MediaStreamTrack getTrackForId(String trackId) {
        MediaStreamTrack track = (MediaStreamTrack) this.localTracks.get(trackId);
        if (track == null) {
            int size = this.mPeerConnectionObservers.size();
            for (int i = 0; i < size; i++) {
                track = (MediaStreamTrack) ((PeerConnectionObserver) this.mPeerConnectionObservers.valueAt(i)).remoteTracks.get(trackId);
                if (track != null) {
                    break;
                }
            }
        }
        return track;
    }

    private void parseConstraints(ReadableMap src, List<KeyValuePair> dst) {
        ReadableMapKeySetIterator keyIterator = src.keySetIterator();
        while (keyIterator.hasNextKey()) {
            String key = keyIterator.nextKey();
            dst.add(new KeyValuePair(key, ReactBridgeUtil.getMapStrValue(src, key)));
        }
    }

    MediaConstraints parseMediaConstraints(ReadableMap constraints) {
        MediaConstraints mediaConstraints = new MediaConstraints();
        if (constraints.hasKey("mandatory") && constraints.getType("mandatory") == ReadableType.Map) {
            parseConstraints(constraints.getMap("mandatory"), mediaConstraints.mandatory);
        } else {
            Log.d(TAG, "mandatory constraints are not a map");
        }
        if (constraints.hasKey("optional") && constraints.getType("optional") == ReadableType.Array) {
            ReadableArray optional = constraints.getArray("optional");
            int size = optional.size();
            for (int i = 0; i < size; i++) {
                if (optional.getType(i) == ReadableType.Map) {
                    parseConstraints(optional.getMap(i), mediaConstraints.optional);
                }
            }
        } else {
            Log.d(TAG, "optional constraints are not an array");
        }
        return mediaConstraints;
    }

    @ReactMethod
    public void getUserMedia(ReadableMap constraints, Callback successCallback, Callback errorCallback) {
        MediaStream mediaStream = this.mFactory.createLocalMediaStream(getNextStreamUUID());
        if (mediaStream == null) {
            errorCallback.invoke(new Object[]{null, "Failed to create new media stream"});
            return;
        }
        this.getUserMediaImpl.getUserMedia(constraints, successCallback, errorCallback, mediaStream);
    }

    @ReactMethod
    public void mediaStreamTrackGetSources(Callback callback) {
        WritableArray array = Arguments.createArray();
        String[] names = new String[Camera.getNumberOfCameras()];
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            WritableMap info = getCameraInfo(i);
            if (info != null) {
                array.pushMap(info);
            }
        }
        WritableMap audio = Arguments.createMap();
        audio.putString("label", "Audio");
        audio.putString(ShareConstants.WEB_DIALOG_PARAM_ID, "audio-1");
        audio.putString("facing", "");
        audio.putString("kind", "audio");
        array.pushMap(audio);
        callback.invoke(new Object[]{array});
    }

    @ReactMethod
    public void mediaStreamTrackStop(String id) {
        MediaStreamTrack track = (MediaStreamTrack) this.localTracks.get(id);
        if (track == null) {
            Log.d(TAG, "mediaStreamTrackStop() track is null");
            return;
        }
        track.setEnabled(false);
        if (track.kind().equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
            this.getUserMediaImpl.removeVideoCapturer(id);
        }
        this.localTracks.remove(id);
    }

    @ReactMethod
    public void mediaStreamTrackSetEnabled(String id, boolean enabled) {
        MediaStreamTrack track = (MediaStreamTrack) this.localTracks.get(id);
        if (track == null) {
            Log.d(TAG, "mediaStreamTrackSetEnabled() track is null");
        } else if (track.enabled() != enabled) {
            track.setEnabled(enabled);
        }
    }

    @ReactMethod
    public void mediaStreamTrackSwitchCamera(String id) {
        if (((MediaStreamTrack) this.localTracks.get(id)) != null) {
            this.getUserMediaImpl.switchCamera(id);
        }
    }

    @ReactMethod
    public void mediaStreamTrackRelease(String streamId, String _trackId) {
        MediaStream stream = (MediaStream) this.localStreams.get(streamId);
        if (stream == null) {
            Log.d(TAG, "mediaStreamTrackRelease() stream is null");
            return;
        }
        MediaStreamTrack track = (MediaStreamTrack) this.localTracks.get(_trackId);
        if (track == null) {
            Log.d(TAG, "mediaStreamTrackRelease() track is null");
            return;
        }
        track.setEnabled(false);
        this.localTracks.remove(_trackId);
        if (track.kind().equals("audio")) {
            stream.removeTrack((AudioTrack) track);
        } else if (track.kind().equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
            stream.removeTrack((VideoTrack) track);
            this.getUserMediaImpl.removeVideoCapturer(_trackId);
        }
    }

    public WritableMap getCameraInfo(int index) {
        CameraInfo info = new CameraInfo();
        try {
            Camera.getCameraInfo(index, info);
            WritableMap params = Arguments.createMap();
            String facing = info.facing == 1 ? "front" : "back";
            params.putString("label", "Camera " + index + ", Facing " + facing + ", Orientation " + info.orientation);
            params.putString(ShareConstants.WEB_DIALOG_PARAM_ID, "" + index);
            params.putString("facing", facing);
            params.putString("kind", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
            return params;
        } catch (Exception e) {
            Logging.e("CameraEnumerationAndroid", "getCameraInfo failed on index " + index, e);
            return null;
        }
    }

    private MediaConstraints defaultConstraints() {
        MediaConstraints constraints = new MediaConstraints();
        constraints.mandatory.add(new KeyValuePair("OfferToReceiveAudio", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        constraints.mandatory.add(new KeyValuePair("OfferToReceiveVideo", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        constraints.optional.add(new KeyValuePair("DtlsSrtpKeyAgreement", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        return constraints;
    }

    @ReactMethod
    public void peerConnectionSetConfiguration(ReadableMap configuration, int id) {
        PeerConnection peerConnection = getPeerConnection(id);
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionSetConfiguration() peerConnection is null");
        } else {
            peerConnection.setConfiguration(parseRTCConfiguration(configuration));
        }
    }

    @ReactMethod
    public void peerConnectionAddStream(String streamId, int id) {
        MediaStream mediaStream = (MediaStream) this.localStreams.get(streamId);
        if (mediaStream == null) {
            Log.d(TAG, "peerConnectionAddStream() mediaStream is null");
            return;
        }
        PeerConnection peerConnection = getPeerConnection(id);
        if (peerConnection != null) {
            Log.d(TAG, "addStream" + peerConnection.addStream(mediaStream));
            return;
        }
        Log.d(TAG, "peerConnectionAddStream() peerConnection is null");
    }

    @ReactMethod
    public void peerConnectionRemoveStream(String streamId, int id) {
        MediaStream mediaStream = (MediaStream) this.localStreams.get(streamId);
        if (mediaStream == null) {
            Log.d(TAG, "peerConnectionRemoveStream() mediaStream is null");
            return;
        }
        PeerConnection peerConnection = getPeerConnection(id);
        if (peerConnection != null) {
            peerConnection.removeStream(mediaStream);
        } else {
            Log.d(TAG, "peerConnectionRemoveStream() peerConnection is null");
        }
    }

    @ReactMethod
    public void peerConnectionCreateOffer(int id, ReadableMap constraints, Callback callback) {
        PeerConnection peerConnection = getPeerConnection(id);
        if (peerConnection != null) {
            peerConnection.createOffer(new 1(this, callback), parseMediaConstraints(constraints));
            return;
        }
        Log.d(TAG, "peerConnectionCreateOffer() peerConnection is null");
        callback.invoke(new Object[]{Boolean.valueOf(false), "peerConnection is null"});
    }

    @ReactMethod
    public void peerConnectionCreateAnswer(int id, ReadableMap constraints, Callback callback) {
        PeerConnection peerConnection = getPeerConnection(id);
        if (peerConnection != null) {
            peerConnection.createAnswer(new 2(this, callback), parseMediaConstraints(constraints));
            return;
        }
        Log.d(TAG, "peerConnectionCreateAnswer() peerConnection is null");
        callback.invoke(new Object[]{Boolean.valueOf(false), "peerConnection is null"});
    }

    @ReactMethod
    public void peerConnectionSetLocalDescription(ReadableMap sdpMap, int id, Callback callback) {
        PeerConnection peerConnection = getPeerConnection(id);
        Log.d(TAG, "peerConnectionSetLocalDescription() start");
        if (peerConnection != null) {
            peerConnection.setLocalDescription(new 3(this, callback), new SessionDescription(Type.fromCanonicalForm(sdpMap.getString("type")), sdpMap.getString("sdp")));
        } else {
            Log.d(TAG, "peerConnectionSetLocalDescription() peerConnection is null");
            callback.invoke(new Object[]{Boolean.valueOf(false), "peerConnection is null"});
        }
        Log.d(TAG, "peerConnectionSetLocalDescription() end");
    }

    @ReactMethod
    public void peerConnectionSetRemoteDescription(ReadableMap sdpMap, int id, Callback callback) {
        PeerConnection peerConnection = getPeerConnection(id);
        Log.d(TAG, "peerConnectionSetRemoteDescription() start");
        if (peerConnection != null) {
            peerConnection.setRemoteDescription(new 4(this, callback), new SessionDescription(Type.fromCanonicalForm(sdpMap.getString("type")), sdpMap.getString("sdp")));
        } else {
            Log.d(TAG, "peerConnectionSetRemoteDescription() peerConnection is null");
            callback.invoke(new Object[]{Boolean.valueOf(false), "peerConnection is null"});
        }
        Log.d(TAG, "peerConnectionSetRemoteDescription() end");
    }

    @ReactMethod
    public void peerConnectionAddICECandidate(ReadableMap candidateMap, int id, Callback callback) {
        boolean result = false;
        PeerConnection peerConnection = getPeerConnection(id);
        Log.d(TAG, "peerConnectionAddICECandidate() start");
        if (peerConnection != null) {
            result = peerConnection.addIceCandidate(new IceCandidate(candidateMap.getString("sdpMid"), candidateMap.getInt("sdpMLineIndex"), candidateMap.getString("candidate")));
        } else {
            Log.d(TAG, "peerConnectionAddICECandidate() peerConnection is null");
        }
        callback.invoke(new Object[]{Boolean.valueOf(result)});
        Log.d(TAG, "peerConnectionAddICECandidate() end");
    }

    @ReactMethod
    public void peerConnectionGetStats(String trackId, int id, Callback cb) {
        PeerConnectionObserver pco = (PeerConnectionObserver) this.mPeerConnectionObservers.get(id);
        if (pco == null || pco.getPeerConnection() == null) {
            Log.d(TAG, "peerConnectionGetStats() peerConnection is null");
        } else {
            pco.getStats(trackId, cb);
        }
    }

    @ReactMethod
    public void peerConnectionClose(int id) {
        PeerConnectionObserver pco = (PeerConnectionObserver) this.mPeerConnectionObservers.get(id);
        if (pco == null || pco.getPeerConnection() == null) {
            Log.d(TAG, "peerConnectionClose() peerConnection is null");
            return;
        }
        pco.close();
        this.mPeerConnectionObservers.remove(id);
    }

    @ReactMethod
    public void mediaStreamRelease(String id) {
        MediaStream mediaStream = (MediaStream) this.localStreams.get(id);
        if (mediaStream != null) {
            Iterator it = mediaStream.videoTracks.iterator();
            while (it.hasNext()) {
                VideoTrack track = (VideoTrack) it.next();
                this.localTracks.remove(track.id());
                this.getUserMediaImpl.removeVideoCapturer(track.id());
            }
            it = mediaStream.audioTracks.iterator();
            while (it.hasNext()) {
                this.localTracks.remove(((AudioTrack) it.next()).id());
            }
            this.localStreams.remove(id);
            return;
        }
        Log.d(TAG, "mediaStreamRelease() mediaStream is null");
    }

    @ReactMethod
    public void createDataChannel(int peerConnectionId, String label, ReadableMap config) {
        PeerConnectionObserver pco = (PeerConnectionObserver) this.mPeerConnectionObservers.get(peerConnectionId);
        if (pco == null || pco.getPeerConnection() == null) {
            Log.d(TAG, "createDataChannel() peerConnection is null");
        } else {
            pco.createDataChannel(label, config);
        }
    }

    @ReactMethod
    public void dataChannelSend(int peerConnectionId, int dataChannelId, String data, String type) {
        PeerConnectionObserver pco = (PeerConnectionObserver) this.mPeerConnectionObservers.get(peerConnectionId);
        if (pco == null || pco.getPeerConnection() == null) {
            Log.d(TAG, "dataChannelSend() peerConnection is null");
        } else {
            pco.dataChannelSend(dataChannelId, data, type);
        }
    }

    @ReactMethod
    public void dataChannelClose(int peerConnectionId, int dataChannelId) {
        PeerConnectionObserver pco = (PeerConnectionObserver) this.mPeerConnectionObservers.get(peerConnectionId);
        if (pco == null || pco.getPeerConnection() == null) {
            Log.d(TAG, "dataChannelClose() peerConnection is null");
        } else {
            pco.dataChannelClose(dataChannelId);
        }
    }
}
