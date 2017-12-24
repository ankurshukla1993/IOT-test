package org.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import org.webrtc.CameraSession.CreateSessionCallback;
import org.webrtc.CameraSession.Events;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;
import org.webrtc.VideoCapturer.CapturerObserver;

@TargetApi(21)
public class Camera2Capturer extends CameraCapturer {
    private final CameraManager cameraManager;
    private final Context context;

    public /* bridge */ /* synthetic */ void changeCaptureFormat(int i, int i2, int i3) {
        super.changeCaptureFormat(i, i2, i3);
    }

    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    public /* bridge */ /* synthetic */ void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        super.initialize(surfaceTextureHelper, context, capturerObserver);
    }

    public /* bridge */ /* synthetic */ boolean isScreencast() {
        return super.isScreencast();
    }

    public /* bridge */ /* synthetic */ void printStackTrace() {
        super.printStackTrace();
    }

    public /* bridge */ /* synthetic */ void startCapture(int i, int i2, int i3) {
        super.startCapture(i, i2, i3);
    }

    public /* bridge */ /* synthetic */ void stopCapture() {
        super.stopCapture();
    }

    public /* bridge */ /* synthetic */ void switchCamera(CameraSwitchHandler cameraSwitchHandler) {
        super.switchCamera(cameraSwitchHandler);
    }

    public Camera2Capturer(Context context, String cameraName, CameraEventsHandler eventsHandler) {
        super(cameraName, eventsHandler, new Camera2Enumerator(context));
        this.context = context;
        this.cameraManager = (CameraManager) context.getSystemService("camera");
    }

    protected void createCameraSession(CreateSessionCallback createSessionCallback, Events events, Context applicationContext, SurfaceTextureHelper surfaceTextureHelper, String cameraName, int width, int height, int framerate) {
        Camera2Session.create(createSessionCallback, events, applicationContext, this.cameraManager, surfaceTextureHelper, cameraName, width, height, framerate);
    }
}
