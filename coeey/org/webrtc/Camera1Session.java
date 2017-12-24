package org.webrtc;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowManager;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraSession.CreateSessionCallback;
import org.webrtc.CameraSession.Events;
import org.webrtc.CameraSession.FailureType;
import org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener;

class Camera1Session implements CameraSession {
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final String TAG = "Camera1Session";
    private static final Histogram camera1ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera1.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    private static final Histogram camera1StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StartTimeMs", 1, AbstractSpiCall.DEFAULT_TIMEOUT, 50);
    private static final Histogram camera1StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StopTimeMs", 1, AbstractSpiCall.DEFAULT_TIMEOUT, 50);
    private final Context applicationContext;
    private final Camera camera;
    private final int cameraId;
    private final Handler cameraThreadHandler;
    private final CaptureFormat captureFormat;
    private final boolean captureToTexture;
    private final long constructionTimeNs;
    private final Events events;
    private boolean firstFrameReported = false;
    private final CameraInfo info;
    private SessionState state;
    private final SurfaceTextureHelper surfaceTextureHelper;

    class C25851 implements ErrorCallback {
        C25851() {
        }

        public void onError(int error, Camera camera) {
            String errorMessage;
            if (error == 100) {
                errorMessage = "Camera server died!";
            } else {
                errorMessage = "Camera error: " + error;
            }
            Logging.m2188e(Camera1Session.TAG, errorMessage);
            Camera1Session.this.stopInternal();
            if (error == 2) {
                Camera1Session.this.events.onCameraDisconnected(Camera1Session.this);
            } else {
                Camera1Session.this.events.onCameraError(Camera1Session.this, errorMessage);
            }
        }
    }

    class C25862 implements OnTextureFrameAvailableListener {
        C25862() {
        }

        public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
            Camera1Session.this.checkIsOnCameraThread();
            if (Camera1Session.this.state != SessionState.RUNNING) {
                Logging.m2187d(Camera1Session.TAG, "Texture frame captured but camera is no longer running.");
                Camera1Session.this.surfaceTextureHelper.returnTextureFrame();
                return;
            }
            if (!Camera1Session.this.firstFrameReported) {
                Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                Camera1Session.this.firstFrameReported = true;
            }
            int rotation = Camera1Session.this.getFrameOrientation();
            if (Camera1Session.this.info.facing == 1) {
                transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
            }
            Camera1Session.this.events.onTextureFrameCaptured(Camera1Session.this, Camera1Session.this.captureFormat.width, Camera1Session.this.captureFormat.height, oesTextureId, transformMatrix, rotation, timestampNs);
        }
    }

    class C25873 implements PreviewCallback {
        C25873() {
        }

        public void onPreviewFrame(byte[] data, Camera callbackCamera) {
            Camera1Session.this.checkIsOnCameraThread();
            if (callbackCamera != Camera1Session.this.camera) {
                Logging.m2188e(Camera1Session.TAG, "Callback from a different camera. This should never happen.");
            } else if (Camera1Session.this.state != SessionState.RUNNING) {
                Logging.m2187d(Camera1Session.TAG, "Bytebuffer frame captured but camera is no longer running.");
            } else {
                long captureTimeNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                if (!Camera1Session.this.firstFrameReported) {
                    Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                    Camera1Session.this.firstFrameReported = true;
                }
                Camera1Session.this.events.onByteBufferFrameCaptured(Camera1Session.this, data, Camera1Session.this.captureFormat.width, Camera1Session.this.captureFormat.height, Camera1Session.this.getFrameOrientation(), captureTimeNs);
                Camera1Session.this.camera.addCallbackBuffer(data);
            }
        }
    }

    private enum SessionState {
        RUNNING,
        STOPPED
    }

    public static void create(CreateSessionCallback callback, Events events, boolean captureToTexture, Context applicationContext, SurfaceTextureHelper surfaceTextureHelper, int cameraId, int width, int height, int framerate) {
        long constructionTimeNs = System.nanoTime();
        Logging.m2187d(TAG, "Open camera " + cameraId);
        events.onCameraOpening();
        try {
            Camera camera = Camera.open(cameraId);
            try {
                camera.setPreviewTexture(surfaceTextureHelper.getSurfaceTexture());
                CameraInfo info = new CameraInfo();
                Camera.getCameraInfo(cameraId, info);
                Parameters parameters = camera.getParameters();
                CaptureFormat captureFormat = findClosestCaptureFormat(parameters, width, height, framerate);
                updateCameraParameters(camera, parameters, captureFormat, findClosestPictureSize(parameters, width, height), captureToTexture);
                if (!captureToTexture) {
                    int frameSize = captureFormat.frameSize();
                    for (int i = 0; i < 3; i++) {
                        camera.addCallbackBuffer(ByteBuffer.allocateDirect(frameSize).array());
                    }
                }
                camera.setDisplayOrientation(0);
                callback.onDone(new Camera1Session(events, captureToTexture, applicationContext, surfaceTextureHelper, cameraId, camera, info, captureFormat, constructionTimeNs));
            } catch (IOException e) {
                camera.release();
                callback.onFailure(FailureType.ERROR, e.getMessage());
            }
        } catch (RuntimeException e2) {
            callback.onFailure(FailureType.ERROR, e2.getMessage());
        }
    }

    private static void updateCameraParameters(Camera camera, Parameters parameters, CaptureFormat captureFormat, Size pictureSize, boolean captureToTexture) {
        List<String> focusModes = parameters.getSupportedFocusModes();
        parameters.setPreviewFpsRange(captureFormat.framerate.min, captureFormat.framerate.max);
        parameters.setPreviewSize(captureFormat.width, captureFormat.height);
        parameters.setPictureSize(pictureSize.width, pictureSize.height);
        if (!captureToTexture) {
            captureFormat.getClass();
            parameters.setPreviewFormat(17);
        }
        if (parameters.isVideoStabilizationSupported()) {
            parameters.setVideoStabilization(true);
        }
        if (focusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        camera.setParameters(parameters);
    }

    private static CaptureFormat findClosestCaptureFormat(Parameters parameters, int width, int height, int framerate) {
        List<FramerateRange> supportedFramerates = Camera1Enumerator.convertFramerates(parameters.getSupportedPreviewFpsRange());
        Logging.m2187d(TAG, "Available fps ranges: " + supportedFramerates);
        FramerateRange fpsRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(supportedFramerates, framerate);
        Size previewSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPreviewSizes()), width, height);
        CameraEnumerationAndroid.reportCameraResolution(camera1ResolutionHistogram, previewSize);
        return new CaptureFormat(previewSize.width, previewSize.height, fpsRange);
    }

    private static Size findClosestPictureSize(Parameters parameters, int width, int height) {
        return CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPictureSizes()), width, height);
    }

    private Camera1Session(Events events, boolean captureToTexture, Context applicationContext, SurfaceTextureHelper surfaceTextureHelper, int cameraId, Camera camera, CameraInfo info, CaptureFormat captureFormat, long constructionTimeNs) {
        Logging.m2187d(TAG, "Create new camera1 session on camera " + cameraId);
        this.cameraThreadHandler = new Handler();
        this.events = events;
        this.captureToTexture = captureToTexture;
        this.applicationContext = applicationContext;
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.cameraId = cameraId;
        this.camera = camera;
        this.info = info;
        this.captureFormat = captureFormat;
        this.constructionTimeNs = constructionTimeNs;
        startCapturing();
    }

    public void stop() {
        Logging.m2187d(TAG, "Stop camera1 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long stopStartTime = System.nanoTime();
            stopInternal();
            camera1StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - stopStartTime));
        }
    }

    private void startCapturing() {
        Logging.m2187d(TAG, "Start capturing");
        checkIsOnCameraThread();
        this.state = SessionState.RUNNING;
        this.camera.setErrorCallback(new C25851());
        if (this.captureToTexture) {
            listenForTextureFrames();
        } else {
            listenForBytebufferFrames();
        }
        try {
            this.camera.startPreview();
        } catch (RuntimeException e) {
            stopInternal();
            this.events.onCameraError(this, e.getMessage());
        }
    }

    private void stopInternal() {
        Logging.m2187d(TAG, "Stop internal");
        checkIsOnCameraThread();
        if (this.state == SessionState.STOPPED) {
            Logging.m2187d(TAG, "Camera is already stopped");
            return;
        }
        this.state = SessionState.STOPPED;
        this.surfaceTextureHelper.stopListening();
        this.camera.stopPreview();
        this.camera.release();
        this.events.onCameraClosed(this);
        Logging.m2187d(TAG, "Stop done");
    }

    private void listenForTextureFrames() {
        this.surfaceTextureHelper.startListening(new C25862());
    }

    private void listenForBytebufferFrames() {
        this.camera.setPreviewCallbackWithBuffer(new C25873());
    }

    private int getDeviceOrientation() {
        switch (((WindowManager) this.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    private int getFrameOrientation() {
        int rotation = getDeviceOrientation();
        if (this.info.facing == 0) {
            rotation = 360 - rotation;
        }
        return (this.info.orientation + rotation) % 360;
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }
}
