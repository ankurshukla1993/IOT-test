package org.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import android.view.WindowManager;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraSession.CreateSessionCallback;
import org.webrtc.CameraSession.Events;
import org.webrtc.CameraSession.FailureType;
import org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener;

@TargetApi(21)
class Camera2Session implements CameraSession {
    private static final String TAG = "Camera2Session";
    private static final Histogram camera2ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera2.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    private static final Histogram camera2StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StartTimeMs", 1, AbstractSpiCall.DEFAULT_TIMEOUT, 50);
    private static final Histogram camera2StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StopTimeMs", 1, AbstractSpiCall.DEFAULT_TIMEOUT, 50);
    private final Context applicationContext;
    private final CreateSessionCallback callback;
    private CameraCharacteristics cameraCharacteristics;
    private CameraDevice cameraDevice;
    private final String cameraId;
    private final CameraManager cameraManager;
    private int cameraOrientation;
    private final Handler cameraThreadHandler;
    private CaptureFormat captureFormat;
    private CameraCaptureSession captureSession;
    private final long constructionTimeNs;
    private final Events events;
    private boolean firstFrameReported = false;
    private int fpsUnitFactor;
    private final int framerate;
    private final int height;
    private boolean isCameraFrontFacing;
    private SessionState state = SessionState.RUNNING;
    private Surface surface;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private final int width;

    private class CameraCaptureCallback extends CaptureCallback {
        private CameraCaptureCallback() {
        }

        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            Logging.m2187d(Camera2Session.TAG, "Capture failed: " + failure);
        }
    }

    private class CameraStateCallback extends StateCallback {
        private CameraStateCallback() {
        }

        private String getErrorDescription(int errorCode) {
            switch (errorCode) {
                case 1:
                    return "Camera device is in use already.";
                case 2:
                    return "Camera device could not be opened because there are too many other open camera devices.";
                case 3:
                    return "Camera device could not be opened due to a device policy.";
                case 4:
                    return "Camera device has encountered a fatal error.";
                case 5:
                    return "Camera service has encountered a fatal error.";
                default:
                    return "Unknown camera error: " + errorCode;
            }
        }

        public void onDisconnected(CameraDevice camera) {
            Camera2Session.this.checkIsOnCameraThread();
            boolean startFailure = Camera2Session.this.captureSession == null && Camera2Session.this.state != SessionState.STOPPED;
            Camera2Session.this.state = SessionState.STOPPED;
            Camera2Session.this.stopInternal();
            if (startFailure) {
                Camera2Session.this.callback.onFailure(FailureType.DISCONNECTED, "Camera disconnected / evicted.");
            } else {
                Camera2Session.this.events.onCameraDisconnected(Camera2Session.this);
            }
        }

        public void onError(CameraDevice camera, int errorCode) {
            Camera2Session.this.checkIsOnCameraThread();
            Camera2Session.this.reportError(getErrorDescription(errorCode));
        }

        public void onOpened(CameraDevice camera) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.m2187d(Camera2Session.TAG, "Camera opened.");
            Camera2Session.this.cameraDevice = camera;
            SurfaceTexture surfaceTexture = Camera2Session.this.surfaceTextureHelper.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height);
            Camera2Session.this.surface = new Surface(surfaceTexture);
            try {
                camera.createCaptureSession(Arrays.asList(new Surface[]{Camera2Session.this.surface}), new CaptureSessionCallback(), Camera2Session.this.cameraThreadHandler);
            } catch (CameraAccessException e) {
                Camera2Session.this.reportError("Failed to create capture session. " + e);
            }
        }

        public void onClosed(CameraDevice camera) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.m2187d(Camera2Session.TAG, "Camera device closed.");
            Camera2Session.this.events.onCameraClosed(Camera2Session.this);
        }
    }

    private class CaptureSessionCallback extends CameraCaptureSession.StateCallback {

        class C25891 implements OnTextureFrameAvailableListener {
            C25891() {
            }

            public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
                Camera2Session.this.checkIsOnCameraThread();
                if (Camera2Session.this.state != SessionState.RUNNING) {
                    Logging.m2187d(Camera2Session.TAG, "Texture frame captured but camera is no longer running.");
                    Camera2Session.this.surfaceTextureHelper.returnTextureFrame();
                    return;
                }
                if (!Camera2Session.this.firstFrameReported) {
                    Camera2Session.this.firstFrameReported = true;
                    Camera2Session.camera2StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera2Session.this.constructionTimeNs));
                }
                int rotation = Camera2Session.this.getFrameOrientation();
                if (Camera2Session.this.isCameraFrontFacing) {
                    transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
                }
                Camera2Session.this.events.onTextureFrameCaptured(Camera2Session.this, Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height, oesTextureId, RendererCommon.rotateTextureMatrix(transformMatrix, (float) (-Camera2Session.this.cameraOrientation)), rotation, timestampNs);
            }
        }

        private CaptureSessionCallback() {
        }

        public void onConfigureFailed(CameraCaptureSession session) {
            Camera2Session.this.checkIsOnCameraThread();
            session.close();
            Camera2Session.this.reportError("Failed to configure capture session.");
        }

        public void onConfigured(CameraCaptureSession session) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.m2187d(Camera2Session.TAG, "Camera capture session configured.");
            Camera2Session.this.captureSession = session;
            try {
                Builder captureRequestBuilder = Camera2Session.this.cameraDevice.createCaptureRequest(3);
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(Integer.valueOf(Camera2Session.this.captureFormat.framerate.min / Camera2Session.this.fpsUnitFactor), Integer.valueOf(Camera2Session.this.captureFormat.framerate.max / Camera2Session.this.fpsUnitFactor)));
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(false));
                chooseStabilizationMode(captureRequestBuilder);
                chooseFocusMode(captureRequestBuilder);
                captureRequestBuilder.addTarget(Camera2Session.this.surface);
                session.setRepeatingRequest(captureRequestBuilder.build(), new CameraCaptureCallback(), Camera2Session.this.cameraThreadHandler);
                Camera2Session.this.surfaceTextureHelper.startListening(new C25891());
                Logging.m2187d(Camera2Session.TAG, "Camera device successfully started.");
                Camera2Session.this.callback.onDone(Camera2Session.this);
            } catch (CameraAccessException e) {
                Camera2Session.this.reportError("Failed to start capture request. " + e);
            }
        }

        private void chooseStabilizationMode(Builder captureRequestBuilder) {
            int[] availableOpticalStabilization = (int[]) Camera2Session.this.cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
            if (availableOpticalStabilization != null) {
                for (int mode : availableOpticalStabilization) {
                    if (mode == 1) {
                        captureRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, Integer.valueOf(1));
                        captureRequestBuilder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(0));
                        Logging.m2187d(Camera2Session.TAG, "Using optical stabilization.");
                        return;
                    }
                }
            }
            for (int mode2 : (int[]) Camera2Session.this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES)) {
                if (mode2 == 1) {
                    captureRequestBuilder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf(1));
                    captureRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, Integer.valueOf(0));
                    Logging.m2187d(Camera2Session.TAG, "Using video stabilization.");
                    return;
                }
            }
            Logging.m2187d(Camera2Session.TAG, "Stabilization not available.");
        }

        private void chooseFocusMode(Builder captureRequestBuilder) {
            for (int mode : (int[]) Camera2Session.this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES)) {
                if (mode == 3) {
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(3));
                    Logging.m2187d(Camera2Session.TAG, "Using continuous video auto-focus.");
                    return;
                }
            }
            Logging.m2187d(Camera2Session.TAG, "Auto-focus is not available.");
        }
    }

    private enum SessionState {
        RUNNING,
        STOPPED
    }

    public static void create(CreateSessionCallback callback, Events events, Context applicationContext, CameraManager cameraManager, SurfaceTextureHelper surfaceTextureHelper, String cameraId, int width, int height, int framerate) {
        Camera2Session camera2Session = new Camera2Session(callback, events, applicationContext, cameraManager, surfaceTextureHelper, cameraId, width, height, framerate);
    }

    private Camera2Session(CreateSessionCallback callback, Events events, Context applicationContext, CameraManager cameraManager, SurfaceTextureHelper surfaceTextureHelper, String cameraId, int width, int height, int framerate) {
        Logging.m2187d(TAG, "Create new camera2 session on camera " + cameraId);
        this.constructionTimeNs = System.nanoTime();
        this.cameraThreadHandler = new Handler();
        this.callback = callback;
        this.events = events;
        this.applicationContext = applicationContext;
        this.cameraManager = cameraManager;
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.cameraId = cameraId;
        this.width = width;
        this.height = height;
        this.framerate = framerate;
        start();
    }

    private void start() {
        checkIsOnCameraThread();
        Logging.m2187d(TAG, "start");
        try {
            this.cameraCharacteristics = this.cameraManager.getCameraCharacteristics(this.cameraId);
            this.cameraOrientation = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
            this.isCameraFrontFacing = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
            findCaptureFormat();
            openCamera();
        } catch (CameraAccessException e) {
            reportError("getCameraCharacteristics(): " + e.getMessage());
        }
    }

    private void findCaptureFormat() {
        checkIsOnCameraThread();
        Range[] fpsRanges = (Range[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        this.fpsUnitFactor = Camera2Enumerator.getFpsUnitFactor(fpsRanges);
        List<FramerateRange> framerateRanges = Camera2Enumerator.convertFramerates(fpsRanges, this.fpsUnitFactor);
        List<Size> sizes = Camera2Enumerator.getSupportedSizes(this.cameraCharacteristics);
        Logging.m2187d(TAG, "Available preview sizes: " + sizes);
        Logging.m2187d(TAG, "Available fps ranges: " + framerateRanges);
        if (framerateRanges.isEmpty() || sizes.isEmpty()) {
            reportError("No supported capture formats.");
            return;
        }
        FramerateRange bestFpsRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(framerateRanges, this.framerate);
        Size bestSize = CameraEnumerationAndroid.getClosestSupportedSize(sizes, this.width, this.height);
        CameraEnumerationAndroid.reportCameraResolution(camera2ResolutionHistogram, bestSize);
        this.captureFormat = new CaptureFormat(bestSize.width, bestSize.height, bestFpsRange);
        Logging.m2187d(TAG, "Using capture format: " + this.captureFormat);
    }

    private void openCamera() {
        checkIsOnCameraThread();
        Logging.m2187d(TAG, "Opening camera " + this.cameraId);
        this.events.onCameraOpening();
        try {
            this.cameraManager.openCamera(this.cameraId, new CameraStateCallback(), this.cameraThreadHandler);
        } catch (CameraAccessException e) {
            reportError("Failed to open camera: " + e);
        }
    }

    public void stop() {
        Logging.m2187d(TAG, "Stop camera2 session on camera " + this.cameraId);
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long stopStartTime = System.nanoTime();
            this.state = SessionState.STOPPED;
            stopInternal();
            camera2StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - stopStartTime));
        }
    }

    private void stopInternal() {
        Logging.m2187d(TAG, "Stop internal");
        checkIsOnCameraThread();
        this.surfaceTextureHelper.stopListening();
        if (this.captureSession != null) {
            this.captureSession.close();
            this.captureSession = null;
        }
        if (this.surface != null) {
            this.surface.release();
            this.surface = null;
        }
        if (this.cameraDevice != null) {
            this.cameraDevice.close();
            this.cameraDevice = null;
        }
        Logging.m2187d(TAG, "Stop done");
    }

    private void reportError(String error) {
        checkIsOnCameraThread();
        Logging.m2188e(TAG, "Error: " + error);
        boolean startFailure = this.captureSession == null && this.state != SessionState.STOPPED;
        this.state = SessionState.STOPPED;
        stopInternal();
        if (startFailure) {
            this.callback.onFailure(FailureType.ERROR, error);
        } else {
            this.events.onCameraError(this, error);
        }
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
        if (!this.isCameraFrontFacing) {
            rotation = 360 - rotation;
        }
        return (this.cameraOrientation + rotation) % 360;
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }
}
