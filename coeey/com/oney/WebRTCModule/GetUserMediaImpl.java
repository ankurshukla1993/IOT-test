package com.oney.WebRTCModule;

import android.content.Context;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.webrtc.AudioTrack;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaConstraints.KeyValuePair;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

class GetUserMediaImpl {
    private static final int DEFAULT_FPS = 30;
    private static final int DEFAULT_HEIGHT = 720;
    private static final int DEFAULT_WIDTH = 1280;
    private static final String PERMISSION_AUDIO = "android.permission.RECORD_AUDIO";
    private static final String PERMISSION_VIDEO = "android.permission.CAMERA";
    static final String TAG = WebRTCModule.TAG;
    private final Map<String, VideoCapturer> mVideoCapturers = new HashMap();
    private final ReactApplicationContext reactContext;
    private final WebRTCModule webRTCModule;

    static /* synthetic */ class C22904 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    GetUserMediaImpl(WebRTCModule webRTCModule, ReactApplicationContext reactContext) {
        this.webRTCModule = webRTCModule;
        this.reactContext = reactContext;
    }

    private void addDefaultAudioConstraints(MediaConstraints audioConstraints) {
        audioConstraints.optional.add(new KeyValuePair("googNoiseSuppression", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        audioConstraints.optional.add(new KeyValuePair("googEchoCancellation", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        audioConstraints.optional.add(new KeyValuePair("echoCancellation", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        audioConstraints.optional.add(new KeyValuePair("googEchoCancellation2", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        audioConstraints.optional.add(new KeyValuePair("googDAEchoCancellation", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    private VideoCapturer createVideoCapturer(CameraEnumerator enumerator, boolean isFacing, String sourceId) {
        int i;
        String name;
        VideoCapturer videoCapturer;
        int i2 = 0;
        String[] deviceNames = enumerator.getDeviceNames();
        if (sourceId != null) {
            int length = deviceNames.length;
            i = 0;
            while (i < length) {
                name = deviceNames[i];
                if (name.equals(sourceId)) {
                    videoCapturer = enumerator.createCapturer(name, new CameraEventsHandler());
                    if (videoCapturer != null) {
                        Log.d(TAG, "create user specified camera " + name + " succeeded");
                        return videoCapturer;
                    }
                    Log.d(TAG, "create user specified camera " + name + " failed");
                } else {
                    i++;
                }
            }
        }
        String facingStr = isFacing ? "front" : "back";
        i = deviceNames.length;
        while (i2 < i) {
            name = deviceNames[i2];
            if (enumerator.isFrontFacing(name) == isFacing) {
                videoCapturer = enumerator.createCapturer(name, new CameraEventsHandler());
                if (videoCapturer != null) {
                    Log.d(TAG, "Create " + facingStr + " camera " + name + " succeeded");
                    return videoCapturer;
                }
                Log.d(TAG, "Create " + facingStr + " camera " + name + " failed");
            }
            i2++;
        }
        return null;
    }

    private String getFacingMode(ReadableMap mediaConstraints) {
        if (mediaConstraints == null) {
            return null;
        }
        return ReactBridgeUtil.getMapStrValue(mediaConstraints, "facingMode");
    }

    private ReactApplicationContext getReactApplicationContext() {
        return this.reactContext;
    }

    private String getSourceIdConstraint(ReadableMap mediaConstraints) {
        if (mediaConstraints != null && mediaConstraints.hasKey("optional") && mediaConstraints.getType("optional") == ReadableType.Array) {
            ReadableArray optional = mediaConstraints.getArray("optional");
            int size = optional.size();
            for (int i = 0; i < size; i++) {
                if (optional.getType(i) == ReadableType.Map) {
                    ReadableMap option = optional.getMap(i);
                    if (option.hasKey("sourceId") && option.getType("sourceId") == ReadableType.String) {
                        return option.getString("sourceId");
                    }
                }
            }
        }
        return null;
    }

    private AudioTrack getUserAudio(ReadableMap constraints) {
        MediaConstraints audioConstraints;
        if (constraints.getType("audio") == ReadableType.Boolean) {
            audioConstraints = new MediaConstraints();
            addDefaultAudioConstraints(audioConstraints);
        } else {
            audioConstraints = this.webRTCModule.parseMediaConstraints(constraints.getMap("audio"));
        }
        Log.i(TAG, "getUserMedia(audio): " + audioConstraints);
        String trackId = this.webRTCModule.getNextTrackUUID();
        PeerConnectionFactory pcFactory = this.webRTCModule.mFactory;
        return pcFactory.createAudioTrack(trackId, pcFactory.createAudioSource(audioConstraints));
    }

    void getUserMedia(ReadableMap constraints, Callback successCallback, final Callback errorCallback, MediaStream mediaStream) {
        ArrayList<String> requestPermissions = new ArrayList();
        if (constraints.hasKey("audio")) {
            switch (C22904.$SwitchMap$com$facebook$react$bridge$ReadableType[constraints.getType("audio").ordinal()]) {
                case 1:
                    if (constraints.getBoolean("audio")) {
                        requestPermissions.add(PERMISSION_AUDIO);
                        break;
                    }
                    break;
                case 2:
                    requestPermissions.add(PERMISSION_AUDIO);
                    break;
            }
        }
        if (constraints.hasKey(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
            switch (C22904.$SwitchMap$com$facebook$react$bridge$ReadableType[constraints.getType(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO).ordinal()]) {
                case 1:
                    if (constraints.getBoolean(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                        requestPermissions.add(PERMISSION_VIDEO);
                        break;
                    }
                    break;
                case 2:
                    requestPermissions.add(PERMISSION_VIDEO);
                    break;
            }
        }
        if (requestPermissions.isEmpty()) {
            errorCallback.invoke(new Object[]{"TypeError", "constraints requests no media types"});
            return;
        }
        final ReadableMap readableMap = constraints;
        final Callback callback = successCallback;
        final Callback callback2 = errorCallback;
        final MediaStream mediaStream2 = mediaStream;
        requestPermissions(requestPermissions, new Callback() {
            public void invoke(Object... args) {
                GetUserMediaImpl.this.getUserMedia(readableMap, callback, callback2, mediaStream2, args[0]);
            }
        }, new Callback() {
            public void invoke(Object... args) {
                errorCallback.invoke(new Object[]{"DOMException", "NotAllowedError"});
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getUserMedia(com.facebook.react.bridge.ReadableMap r13, com.facebook.react.bridge.Callback r14, com.facebook.react.bridge.Callback r15, org.webrtc.MediaStream r16, java.util.List<java.lang.String> r17) {
        /*
        r12 = this;
        r8 = 2;
        r6 = new org.webrtc.MediaStreamTrack[r8];
        r8 = "android.permission.RECORD_AUDIO";
        r0 = r17;
        r8 = r0.contains(r8);
        if (r8 == 0) goto L_0x0016;
    L_0x000d:
        r8 = 0;
        r9 = r12.getUserAudio(r13);
        r6[r8] = r9;
        if (r9 == 0) goto L_0x0029;
    L_0x0016:
        r8 = "android.permission.CAMERA";
        r0 = r17;
        r8 = r0.contains(r8);
        if (r8 == 0) goto L_0x0047;
    L_0x0020:
        r8 = 1;
        r9 = r12.getUserVideo(r13);
        r6[r8] = r9;
        if (r9 != 0) goto L_0x0047;
    L_0x0029:
        r9 = r6.length;
        r8 = 0;
    L_0x002b:
        if (r8 >= r9) goto L_0x0037;
    L_0x002d:
        r4 = r6[r8];
        if (r4 == 0) goto L_0x0034;
    L_0x0031:
        r4.dispose();
    L_0x0034:
        r8 = r8 + 1;
        goto L_0x002b;
    L_0x0037:
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r10 = 0;
        r8[r9] = r10;
        r9 = 1;
        r10 = "Failed to create new track";
        r8[r9] = r10;
        r15.invoke(r8);
    L_0x0046:
        return;
    L_0x0047:
        r7 = com.facebook.react.bridge.Arguments.createArray();
        r10 = r6.length;
        r8 = 0;
        r9 = r8;
    L_0x004e:
        if (r9 >= r10) goto L_0x00af;
    L_0x0050:
        r4 = r6[r9];
        if (r4 != 0) goto L_0x0058;
    L_0x0054:
        r8 = r9 + 1;
        r9 = r8;
        goto L_0x004e;
    L_0x0058:
        r1 = r4.id();
        r8 = r4 instanceof org.webrtc.AudioTrack;
        if (r8 == 0) goto L_0x00a6;
    L_0x0060:
        r8 = r4;
        r8 = (org.webrtc.AudioTrack) r8;
        r0 = r16;
        r0.addTrack(r8);
    L_0x0068:
        r8 = r12.webRTCModule;
        r8 = r8.localTracks;
        r8.put(r1, r4);
        r5 = com.facebook.react.bridge.Arguments.createMap();
        r2 = r4.kind();
        r8 = "enabled";
        r11 = r4.enabled();
        r5.putBoolean(r8, r11);
        r8 = "id";
        r5.putString(r8, r1);
        r8 = "kind";
        r5.putString(r8, r2);
        r8 = "label";
        r5.putString(r8, r2);
        r8 = "readyState";
        r11 = r4.state();
        r11 = r11.toString();
        r5.putString(r8, r11);
        r8 = "remote";
        r11 = 0;
        r5.putBoolean(r8, r11);
        r7.pushMap(r5);
        goto L_0x0054;
    L_0x00a6:
        r8 = r4;
        r8 = (org.webrtc.VideoTrack) r8;
        r0 = r16;
        r0.addTrack(r8);
        goto L_0x0068;
    L_0x00af:
        r3 = r16.label();
        r8 = TAG;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "MediaStream id: ";
        r9 = r9.append(r10);
        r9 = r9.append(r3);
        r9 = r9.toString();
        android.util.Log.d(r8, r9);
        r8 = r12.webRTCModule;
        r8 = r8.localStreams;
        r0 = r16;
        r8.put(r3, r0);
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r3;
        r9 = 1;
        r8[r9] = r7;
        r14.invoke(r8);
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.GetUserMediaImpl.getUserMedia(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback, com.facebook.react.bridge.Callback, org.webrtc.MediaStream, java.util.List):void");
    }

    private VideoTrack getUserVideo(ReadableMap constraints) {
        CameraEnumerator cameraEnumerator;
        ReadableMap videoConstraintsMap = null;
        ReadableMap videoConstraintsMandatory = null;
        if (constraints.getType(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) == ReadableType.Map) {
            videoConstraintsMap = constraints.getMap(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
            if (videoConstraintsMap.hasKey("mandatory") && videoConstraintsMap.getType("mandatory") == ReadableType.Map) {
                videoConstraintsMandatory = videoConstraintsMap.getMap("mandatory");
            }
        }
        Log.i(TAG, "getUserMedia(video): " + videoConstraintsMap);
        Context context = getReactApplicationContext();
        if (Camera2Enumerator.isSupported(context)) {
            Log.d(TAG, "Creating video capturer using Camera2 API.");
            cameraEnumerator = new Camera2Enumerator(context);
        } else {
            Log.d(TAG, "Creating video capturer using Camera1 API.");
            cameraEnumerator = new Camera1Enumerator(false);
        }
        String facingMode = getFacingMode(videoConstraintsMap);
        boolean isFacing = facingMode == null || !facingMode.equals("environment");
        VideoCapturer videoCapturer = createVideoCapturer(cameraEnumerator, isFacing, getSourceIdConstraint(videoConstraintsMap));
        if (videoCapturer == null) {
            return null;
        }
        PeerConnectionFactory pcFactory = this.webRTCModule.mFactory;
        VideoSource videoSource = pcFactory.createVideoSource(videoCapturer);
        videoCapturer.startCapture(videoConstraintsMandatory.hasKey(ViewProps.MIN_WIDTH) ? videoConstraintsMandatory.getInt(ViewProps.MIN_WIDTH) : DEFAULT_WIDTH, videoConstraintsMandatory.hasKey(ViewProps.MIN_HEIGHT) ? videoConstraintsMandatory.getInt(ViewProps.MIN_HEIGHT) : DEFAULT_HEIGHT, videoConstraintsMandatory.hasKey("minFrameRate") ? videoConstraintsMandatory.getInt("minFrameRate") : 30);
        String trackId = this.webRTCModule.getNextTrackUUID();
        this.mVideoCapturers.put(trackId, videoCapturer);
        return pcFactory.createVideoTrack(trackId, videoSource);
    }

    void removeVideoCapturer(String id) {
        VideoCapturer videoCapturer = (VideoCapturer) this.mVideoCapturers.get(id);
        if (videoCapturer != null) {
            try {
                videoCapturer.stopCapture();
            } catch (InterruptedException e) {
                Log.e(TAG, "removeVideoCapturer() Failed to stop video capturer");
            }
            this.mVideoCapturers.remove(id);
        }
    }

    private void requestPermissions(final ArrayList<String> permissions, final Callback successCallback, final Callback errorCallback) {
        PermissionUtils.requestPermissions(getReactApplicationContext(), (String[]) permissions.toArray(new String[permissions.size()]), new PermissionUtils.Callback() {
            public void invoke(String[] permissions_, int[] grantResults) {
                List<String> grantedPermissions = new ArrayList();
                List<String> deniedPermissions = new ArrayList();
                for (int i = 0; i < permissions_.length; i++) {
                    String permission = permissions_[i];
                    if (grantResults[i] == 0) {
                        grantedPermissions.add(permission);
                    } else {
                        deniedPermissions.add(permission);
                    }
                }
                Iterator it = permissions.iterator();
                while (it.hasNext()) {
                    if (!grantedPermissions.contains((String) it.next())) {
                        errorCallback.invoke(new Object[]{deniedPermissions});
                        return;
                    }
                }
                successCallback.invoke(new Object[]{grantedPermissions});
            }
        });
    }

    void switchCamera(String id) {
        VideoCapturer videoCapturer = (VideoCapturer) this.mVideoCapturers.get(id);
        if (videoCapturer != null) {
            ((CameraVideoCapturer) videoCapturer).switchCamera(null);
        }
    }
}
