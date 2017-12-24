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
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.CameraVideoCapturer.CameraStatistics;
import org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener;
import org.webrtc.VideoCapturer.CapturerObserver;

@Deprecated
public class VideoCapturerAndroid implements CameraVideoCapturer, PreviewCallback, OnTextureFrameAvailableListener {
    private static final int CAMERA_STOP_TIMEOUT_MS = 7000;
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final String TAG = "VideoCapturerAndroid";
    private static final Histogram videoCapturerAndroidResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.VideoCapturerAndroid.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    private static final Histogram videoCapturerAndroidStartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.VideoCapturerAndroid.StartTimeMs", 1, AbstractSpiCall.DEFAULT_TIMEOUT, 50);
    private static final Histogram videoCapturerAndroidStopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.VideoCapturerAndroid.StopTimeMs", 1, AbstractSpiCall.DEFAULT_TIMEOUT, 50);
    private Context applicationContext;
    private Camera camera;
    private final ErrorCallback cameraErrorCallback = new 1(this);
    private final Object cameraIdLock = new Object();
    private CameraStatistics cameraStatistics;
    private volatile Handler cameraThreadHandler;
    private CaptureFormat captureFormat;
    private final CameraEventsHandler eventsHandler;
    private boolean firstFrameReported;
    private CapturerObserver frameObserver = null;
    private int id;
    private CameraInfo info;
    private final AtomicBoolean isCameraRunning = new AtomicBoolean();
    private final boolean isCapturingToTexture;
    private int openCameraAttempts;
    private volatile boolean pendingCameraSwitch;
    private final Object pendingCameraSwitchLock = new Object();
    private final Set<byte[]> queuedBuffers = new HashSet();
    private int requestedFramerate;
    private int requestedHeight;
    private int requestedWidth;
    private long startStartTimeNs;
    private SurfaceTextureHelper surfaceHelper;

    public static VideoCapturerAndroid create(String name, CameraEventsHandler eventsHandler) {
        return create(name, eventsHandler, false);
    }

    @Deprecated
    public static VideoCapturerAndroid create(String name, CameraEventsHandler eventsHandler, boolean captureToTexture) {
        try {
            return new VideoCapturerAndroid(name, eventsHandler, captureToTexture);
        } catch (RuntimeException e) {
            Logging.e(TAG, "Couldn't create camera.", e);
            return null;
        }
    }

    public void printStackTrace() {
        Thread cameraThread = null;
        if (this.cameraThreadHandler != null) {
            cameraThread = this.cameraThreadHandler.getLooper().getThread();
        }
        if (cameraThread != null) {
            StackTraceElement[] cameraStackTraces = cameraThread.getStackTrace();
            if (cameraStackTraces.length > 0) {
                Logging.d(TAG, "VideoCapturerAndroid stacks trace:");
                for (StackTraceElement stackTrace : cameraStackTraces) {
                    Logging.d(TAG, stackTrace.toString());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void switchCamera(org.webrtc.CameraVideoCapturer.CameraSwitchHandler r5) {
        /*
        r4 = this;
        r1 = android.hardware.Camera.getNumberOfCameras();
        r2 = 2;
        if (r1 >= r2) goto L_0x000f;
    L_0x0007:
        if (r5 == 0) goto L_0x000e;
    L_0x0009:
        r1 = "No camera to switch to.";
        r5.onCameraSwitchError(r1);
    L_0x000e:
        return;
    L_0x000f:
        r2 = r4.pendingCameraSwitchLock;
        monitor-enter(r2);
        r1 = r4.pendingCameraSwitch;	 Catch:{ all -> 0x0026 }
        if (r1 == 0) goto L_0x0029;
    L_0x0016:
        r1 = "VideoCapturerAndroid";
        r3 = "Ignoring camera switch request.";
        org.webrtc.Logging.w(r1, r3);	 Catch:{ all -> 0x0026 }
        if (r5 == 0) goto L_0x0024;
    L_0x001f:
        r1 = "Pending camera switch already in progress.";
        r5.onCameraSwitchError(r1);	 Catch:{ all -> 0x0026 }
    L_0x0024:
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        goto L_0x000e;
    L_0x0026:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        throw r1;
    L_0x0029:
        r1 = 1;
        r4.pendingCameraSwitch = r1;	 Catch:{ all -> 0x0026 }
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        r1 = new org.webrtc.VideoCapturerAndroid$2;
        r1.<init>(r4, r5);
        r0 = r4.maybePostOnCameraThread(r1);
        if (r0 != 0) goto L_0x000e;
    L_0x0038:
        r2 = r4.pendingCameraSwitchLock;
        monitor-enter(r2);
        r1 = 0;
        r4.pendingCameraSwitch = r1;	 Catch:{ all -> 0x0047 }
        monitor-exit(r2);	 Catch:{ all -> 0x0047 }
        if (r5 == 0) goto L_0x000e;
    L_0x0041:
        r1 = "Camera is stopped.";
        r5.onCameraSwitchError(r1);
        goto L_0x000e;
    L_0x0047:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0047 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.VideoCapturerAndroid.switchCamera(org.webrtc.CameraVideoCapturer$CameraSwitchHandler):void");
    }

    public void changeCaptureFormat(int width, int height, int framerate) {
        maybePostOnCameraThread(new 3(this, width, height, framerate));
    }

    private int getCurrentCameraId() {
        int i;
        synchronized (this.cameraIdLock) {
            i = this.id;
        }
        return i;
    }

    public boolean isCapturingToTexture() {
        return this.isCapturingToTexture;
    }

    public VideoCapturerAndroid(String cameraName, CameraEventsHandler eventsHandler, boolean captureToTexture) {
        if (Camera.getNumberOfCameras() == 0) {
            throw new RuntimeException("No cameras available");
        }
        if (cameraName == null || cameraName.equals("")) {
            this.id = 0;
        } else {
            this.id = Camera1Enumerator.getCameraIndex(cameraName);
        }
        this.eventsHandler = eventsHandler;
        this.isCapturingToTexture = captureToTexture;
        Logging.d(TAG, "VideoCapturerAndroid isCapturingToTexture : " + this.isCapturingToTexture);
    }

    private void checkIsOnCameraThread() {
        if (this.cameraThreadHandler == null) {
            Logging.e(TAG, "Camera is not initialized - can't check thread.");
        } else if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    private boolean maybePostOnCameraThread(Runnable runnable) {
        return maybePostDelayedOnCameraThread(0, runnable);
    }

    private boolean maybePostDelayedOnCameraThread(int delayMs, Runnable runnable) {
        return this.cameraThreadHandler != null && this.isCameraRunning.get() && this.cameraThreadHandler.postAtTime(runnable, this, SystemClock.uptimeMillis() + ((long) delayMs));
    }

    public void dispose() {
        Logging.d(TAG, "dispose");
    }

    private boolean isInitialized() {
        return (this.applicationContext == null || this.frameObserver == null) ? false : true;
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context applicationContext, CapturerObserver frameObserver) {
        Logging.d(TAG, "initialize");
        if (applicationContext == null) {
            throw new IllegalArgumentException("applicationContext not set.");
        } else if (frameObserver == null) {
            throw new IllegalArgumentException("frameObserver not set.");
        } else if (isInitialized()) {
            throw new IllegalStateException("Already initialized");
        } else {
            Handler handler;
            this.applicationContext = applicationContext;
            this.frameObserver = frameObserver;
            this.surfaceHelper = surfaceTextureHelper;
            if (surfaceTextureHelper == null) {
                handler = null;
            } else {
                handler = surfaceTextureHelper.getHandler();
            }
            this.cameraThreadHandler = handler;
        }
    }

    public void startCapture(int width, int height, int framerate) {
        Logging.d(TAG, "startCapture requested: " + width + "x" + height + "@" + framerate);
        if (!isInitialized()) {
            throw new IllegalStateException("startCapture called in uninitialized state");
        } else if (this.surfaceHelper == null) {
            this.frameObserver.onCapturerStarted(false);
            if (this.eventsHandler != null) {
                this.eventsHandler.onCameraError("No SurfaceTexture created.");
            }
        } else if (this.isCameraRunning.getAndSet(true)) {
            Logging.e(TAG, "Camera has already been started.");
        } else if (!maybePostOnCameraThread(new 4(this, width, height, framerate))) {
            this.frameObserver.onCapturerStarted(false);
            if (this.eventsHandler != null) {
                this.eventsHandler.onCameraError("Could not post task to camera thread.");
            }
            this.isCameraRunning.set(false);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void startCaptureOnCameraThread(int r8, int r9, int r10) {
        /*
        r7 = this;
        r6 = 1;
        r5 = 0;
        r7.checkIsOnCameraThread();
        r2 = java.lang.System.nanoTime();
        r7.startStartTimeNs = r2;
        r1 = r7.isCameraRunning;
        r1 = r1.get();
        if (r1 != 0) goto L_0x001b;
    L_0x0013:
        r1 = "VideoCapturerAndroid";
        r2 = "startCaptureOnCameraThread: Camera is stopped";
        org.webrtc.Logging.e(r1, r2);
    L_0x001a:
        return;
    L_0x001b:
        r1 = r7.camera;
        if (r1 == 0) goto L_0x0027;
    L_0x001f:
        r1 = "VideoCapturerAndroid";
        r2 = "startCaptureOnCameraThread: Camera has already been started.";
        org.webrtc.Logging.e(r1, r2);
        goto L_0x001a;
    L_0x0027:
        r7.firstFrameReported = r5;
        r2 = r7.cameraIdLock;	 Catch:{ RuntimeException -> 0x00e7, IOException -> 0x00c7 }
        monitor-enter(r2);	 Catch:{ RuntimeException -> 0x00e7, IOException -> 0x00c7 }
        r1 = "VideoCapturerAndroid";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e4 }
        r3.<init>();	 Catch:{ all -> 0x00e4 }
        r4 = "Opening camera ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00e4 }
        r4 = r7.id;	 Catch:{ all -> 0x00e4 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00e4 }
        r3 = r3.toString();	 Catch:{ all -> 0x00e4 }
        org.webrtc.Logging.d(r1, r3);	 Catch:{ all -> 0x00e4 }
        r1 = r7.eventsHandler;	 Catch:{ all -> 0x00e4 }
        if (r1 == 0) goto L_0x0055;
    L_0x004a:
        r1 = r7.eventsHandler;	 Catch:{ all -> 0x00e4 }
        r3 = r7.id;	 Catch:{ all -> 0x00e4 }
        r3 = org.webrtc.Camera1Enumerator.getDeviceName(r3);	 Catch:{ all -> 0x00e4 }
        r1.onCameraOpening(r3);	 Catch:{ all -> 0x00e4 }
    L_0x0055:
        r1 = r7.id;	 Catch:{ all -> 0x00e4 }
        r1 = android.hardware.Camera.open(r1);	 Catch:{ all -> 0x00e4 }
        r7.camera = r1;	 Catch:{ all -> 0x00e4 }
        r1 = new android.hardware.Camera$CameraInfo;	 Catch:{ all -> 0x00e4 }
        r1.<init>();	 Catch:{ all -> 0x00e4 }
        r7.info = r1;	 Catch:{ all -> 0x00e4 }
        r1 = r7.id;	 Catch:{ all -> 0x00e4 }
        r3 = r7.info;	 Catch:{ all -> 0x00e4 }
        android.hardware.Camera.getCameraInfo(r1, r3);	 Catch:{ all -> 0x00e4 }
        monitor-exit(r2);	 Catch:{ all -> 0x00e4 }
        r1 = r7.camera;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r7.surfaceHelper;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r2.getSurfaceTexture();	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1.setPreviewTexture(r2);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = "VideoCapturerAndroid";
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2.<init>();	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r3 = "Camera orientation: ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r3 = r7.info;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r3 = r3.orientation;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r3 = " .Device orientation: ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r3 = r7.getDeviceOrientation();	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        org.webrtc.Logging.d(r1, r2);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = r7.camera;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r7.cameraErrorCallback;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1.setErrorCallback(r2);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r7.startPreviewOnCameraThread(r8, r9, r10);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = r7.frameObserver;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = 1;
        r1.onCapturerStarted(r2);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = r7.isCapturingToTexture;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        if (r1 == 0) goto L_0x00ba;
    L_0x00b5:
        r1 = r7.surfaceHelper;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1.startListening(r7);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
    L_0x00ba:
        r1 = new org.webrtc.CameraVideoCapturer$CameraStatistics;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = r7.surfaceHelper;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r3 = r7.eventsHandler;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1.<init>(r2, r3);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r7.cameraStatistics = r1;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        goto L_0x001a;
    L_0x00c7:
        r0 = move-exception;
    L_0x00c8:
        r1 = "VideoCapturerAndroid";
        r2 = "startCapture failed";
        org.webrtc.Logging.e(r1, r2, r0);
        r7.stopCaptureOnCameraThread(r6);
        r1 = r7.frameObserver;
        r1.onCapturerStarted(r5);
        r1 = r7.eventsHandler;
        if (r1 == 0) goto L_0x001a;
    L_0x00db:
        r1 = r7.eventsHandler;
        r2 = "Camera can not be started.";
        r1.onCameraError(r2);
        goto L_0x001a;
    L_0x00e4:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00e4 }
        throw r1;	 Catch:{ RuntimeException -> 0x00e7, IOException -> 0x00c7 }
    L_0x00e7:
        r0 = move-exception;
        r1 = r7.openCameraAttempts;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = r1 + 1;
        r7.openCameraAttempts = r1;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = r7.openCameraAttempts;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2 = 3;
        if (r1 >= r2) goto L_0x0108;
    L_0x00f3:
        r1 = "VideoCapturerAndroid";
        r2 = "Camera.open failed, retrying";
        org.webrtc.Logging.e(r1, r2, r0);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r1 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r2 = new org.webrtc.VideoCapturerAndroid$5;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r2.<init>(r7, r8, r9, r10);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        r7.maybePostDelayedOnCameraThread(r1, r2);	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        goto L_0x001a;
    L_0x0106:
        r0 = move-exception;
        goto L_0x00c8;
    L_0x0108:
        throw r0;	 Catch:{ IOException -> 0x00c7, RuntimeException -> 0x0106 }
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.VideoCapturerAndroid.startCaptureOnCameraThread(int, int, int):void");
    }

    private void startPreviewOnCameraThread(int width, int height, int framerate) {
        checkIsOnCameraThread();
        if (!this.isCameraRunning.get() || this.camera == null) {
            Logging.e(TAG, "startPreviewOnCameraThread: Camera is stopped");
            return;
        }
        Logging.d(TAG, "startPreviewOnCameraThread requested: " + width + "x" + height + "@" + framerate);
        this.requestedWidth = width;
        this.requestedHeight = height;
        this.requestedFramerate = framerate;
        Parameters parameters = this.camera.getParameters();
        List<FramerateRange> supportedFramerates = Camera1Enumerator.convertFramerates(parameters.getSupportedPreviewFpsRange());
        Logging.d(TAG, "Available fps ranges: " + supportedFramerates);
        FramerateRange fpsRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(supportedFramerates, framerate);
        List<Size> supportedPreviewSizes = Camera1Enumerator.convertSizes(parameters.getSupportedPreviewSizes());
        Size previewSize = CameraEnumerationAndroid.getClosestSupportedSize(supportedPreviewSizes, width, height);
        CameraEnumerationAndroid.reportCameraResolution(videoCapturerAndroidResolutionHistogram, previewSize);
        Logging.d(TAG, "Available preview sizes: " + supportedPreviewSizes);
        CaptureFormat captureFormat = new CaptureFormat(previewSize.width, previewSize.height, fpsRange);
        if (!captureFormat.equals(this.captureFormat)) {
            Logging.d(TAG, "isVideoStabilizationSupported: " + parameters.isVideoStabilizationSupported());
            if (parameters.isVideoStabilizationSupported()) {
                parameters.setVideoStabilization(true);
            }
            if (captureFormat.framerate.max > 0) {
                parameters.setPreviewFpsRange(captureFormat.framerate.min, captureFormat.framerate.max);
            }
            parameters.setPreviewSize(previewSize.width, previewSize.height);
            if (!this.isCapturingToTexture) {
                captureFormat.getClass();
                parameters.setPreviewFormat(17);
            }
            Size pictureSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPictureSizes()), width, height);
            parameters.setPictureSize(pictureSize.width, pictureSize.height);
            if (this.captureFormat != null) {
                this.camera.stopPreview();
                this.camera.setPreviewCallbackWithBuffer(null);
            }
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                Logging.d(TAG, "Enable continuous auto focus mode.");
                parameters.setFocusMode("continuous-video");
            }
            Logging.d(TAG, "Start capturing: " + captureFormat);
            this.captureFormat = captureFormat;
            this.camera.setParameters(parameters);
            this.camera.setDisplayOrientation(0);
            if (!this.isCapturingToTexture) {
                this.queuedBuffers.clear();
                int frameSize = captureFormat.frameSize();
                for (int i = 0; i < 3; i++) {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(frameSize);
                    this.queuedBuffers.add(buffer.array());
                    this.camera.addCallbackBuffer(buffer.array());
                }
                this.camera.setPreviewCallbackWithBuffer(this);
            }
            this.camera.startPreview();
        }
    }

    public void stopCapture() throws InterruptedException {
        Logging.d(TAG, "stopCapture");
        CountDownLatch barrier = new CountDownLatch(1);
        if (maybePostOnCameraThread(new 6(this, barrier))) {
            if (!barrier.await(7000, TimeUnit.MILLISECONDS)) {
                Logging.e(TAG, "Camera stop timeout");
                printStackTrace();
                if (this.eventsHandler != null) {
                    this.eventsHandler.onCameraError("Camera stop timeout");
                }
            }
            this.frameObserver.onCapturerStopped();
            Logging.d(TAG, "stopCapture done");
            return;
        }
        Logging.e(TAG, "Calling stopCapture() for already stopped camera.");
    }

    private void stopCaptureOnCameraThread(boolean stopHandler) {
        checkIsOnCameraThread();
        Logging.d(TAG, "stopCaptureOnCameraThread");
        long stopStartTime = System.nanoTime();
        if (this.surfaceHelper != null) {
            this.surfaceHelper.stopListening();
        }
        if (stopHandler) {
            this.isCameraRunning.set(false);
            this.cameraThreadHandler.removeCallbacksAndMessages(this);
        }
        if (this.cameraStatistics != null) {
            this.cameraStatistics.release();
            this.cameraStatistics = null;
        }
        Logging.d(TAG, "Stop preview.");
        if (this.camera != null) {
            this.camera.stopPreview();
            this.camera.setPreviewCallbackWithBuffer(null);
        }
        this.queuedBuffers.clear();
        this.captureFormat = null;
        Logging.d(TAG, "Release camera.");
        if (this.camera != null) {
            this.camera.release();
            this.camera = null;
        }
        if (this.eventsHandler != null) {
            this.eventsHandler.onCameraClosed();
        }
        videoCapturerAndroidStopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - stopStartTime));
        Logging.d(TAG, "stopCaptureOnCameraThread done");
    }

    private void switchCameraOnCameraThread() {
        checkIsOnCameraThread();
        if (this.isCameraRunning.get()) {
            Logging.d(TAG, "switchCameraOnCameraThread");
            stopCaptureOnCameraThread(false);
            synchronized (this.cameraIdLock) {
                this.id = (this.id + 1) % Camera.getNumberOfCameras();
            }
            startCaptureOnCameraThread(this.requestedWidth, this.requestedHeight, this.requestedFramerate);
            Logging.d(TAG, "switchCameraOnCameraThread done");
            return;
        }
        Logging.e(TAG, "switchCameraOnCameraThread: Camera is stopped");
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

    public void onPreviewFrame(byte[] data, Camera callbackCamera) {
        checkIsOnCameraThread();
        if (!this.isCameraRunning.get()) {
            Logging.e(TAG, "onPreviewFrame: Camera is stopped");
        } else if (!this.queuedBuffers.contains(data)) {
        } else {
            if (this.camera != callbackCamera) {
                throw new RuntimeException("Unexpected camera in callback!");
            }
            long captureTimeNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            if (!this.firstFrameReported) {
                onFirstFrameAvailable();
            }
            this.cameraStatistics.addFrame();
            this.frameObserver.onByteBufferFrameCaptured(data, this.captureFormat.width, this.captureFormat.height, getFrameOrientation(), captureTimeNs);
            this.camera.addCallbackBuffer(data);
        }
    }

    public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
        checkIsOnCameraThread();
        if (this.isCameraRunning.get()) {
            int rotation = getFrameOrientation();
            if (this.info.facing == 1) {
                transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
            }
            if (!this.firstFrameReported) {
                onFirstFrameAvailable();
            }
            this.cameraStatistics.addFrame();
            this.frameObserver.onTextureFrameCaptured(this.captureFormat.width, this.captureFormat.height, oesTextureId, transformMatrix, rotation, timestampNs);
            return;
        }
        Logging.e(TAG, "onTextureFrameAvailable: Camera is stopped");
        this.surfaceHelper.returnTextureFrame();
    }

    private void onFirstFrameAvailable() {
        if (this.eventsHandler != null) {
            this.eventsHandler.onFirstFrameAvailable();
        }
        videoCapturerAndroidStartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.startStartTimeNs));
        this.firstFrameReported = true;
    }

    public boolean isScreencast() {
        return false;
    }
}
