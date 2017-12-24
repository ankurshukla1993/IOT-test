package org.webrtc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
import org.webrtc.CameraSession.CreateSessionCallback;
import org.webrtc.CameraSession.Events;
import org.webrtc.CameraSession.FailureType;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.CameraVideoCapturer.CameraStatistics;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;
import org.webrtc.VideoCapturer.CapturerObserver;

abstract class CameraCapturer implements CameraVideoCapturer {
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    private Context applicationContext;
    private final CameraEnumerator cameraEnumerator;
    private String cameraName;
    private final Events cameraSessionEventsHandler = new C25912();
    private CameraStatistics cameraStatistics;
    private Handler cameraThreadHandler;
    private CapturerObserver capturerObserver;
    private final CreateSessionCallback createSessionCallback = new C25901();
    private CameraSession currentSession;
    private final CameraEventsHandler eventsHandler;
    private boolean firstFrameObserved;
    private int framerate;
    private int height;
    private int openAttemptsRemaining;
    private final Runnable openCameraTimeoutRunnable = new C25923();
    private boolean sessionOpening;
    private final Object stateLock = new Object();
    private SurfaceTextureHelper surfaceHelper;
    private CameraSwitchHandler switchEventsHandler;
    private SwitchState switchState = SwitchState.IDLE;
    private final Handler uiThreadHandler;
    private int width;

    class C25901 implements CreateSessionCallback {
        C25901() {
        }

        public void onDone(CameraSession session) {
            CameraCapturer.this.checkIsOnCameraThread();
            Logging.m2187d(CameraCapturer.TAG, "Create session done");
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(true);
                CameraCapturer.this.sessionOpening = false;
                CameraCapturer.this.currentSession = session;
                CameraCapturer.this.cameraStatistics = new CameraStatistics(CameraCapturer.this.surfaceHelper, CameraCapturer.this.eventsHandler);
                CameraCapturer.this.firstFrameObserved = false;
                CameraCapturer.this.stateLock.notifyAll();
                if (CameraCapturer.this.switchState == SwitchState.IN_PROGRESS) {
                    if (CameraCapturer.this.switchEventsHandler != null) {
                        CameraCapturer.this.switchEventsHandler.onCameraSwitchDone(CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                        CameraCapturer.this.switchEventsHandler = null;
                    }
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                } else if (CameraCapturer.this.switchState == SwitchState.PENDING) {
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                    CameraCapturer.this.switchCameraInternal(CameraCapturer.this.switchEventsHandler);
                }
            }
        }

        public void onFailure(FailureType failureType, String error) {
            CameraCapturer.this.checkIsOnCameraThread();
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(false);
                CameraCapturer.this.openAttemptsRemaining = CameraCapturer.this.openAttemptsRemaining - 1;
                if (CameraCapturer.this.openAttemptsRemaining <= 0) {
                    Logging.m2191w(CameraCapturer.TAG, "Opening camera failed, passing: " + error);
                    CameraCapturer.this.sessionOpening = false;
                    CameraCapturer.this.stateLock.notifyAll();
                    if (CameraCapturer.this.switchState != SwitchState.IDLE) {
                        if (CameraCapturer.this.switchEventsHandler != null) {
                            CameraCapturer.this.switchEventsHandler.onCameraSwitchError(error);
                            CameraCapturer.this.switchEventsHandler = null;
                        }
                        CameraCapturer.this.switchState = SwitchState.IDLE;
                    }
                    if (failureType == FailureType.DISCONNECTED) {
                        CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    } else {
                        CameraCapturer.this.eventsHandler.onCameraError(error);
                    }
                } else {
                    Logging.m2191w(CameraCapturer.TAG, "Opening camera failed, retry: " + error);
                    CameraCapturer.this.createSessionInternal(500);
                }
            }
        }
    }

    class C25912 implements Events {
        C25912() {
        }

        public void onCameraOpening() {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession != null) {
                    Logging.m2191w(CameraCapturer.TAG, "onCameraOpening while session was open.");
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraOpening(CameraCapturer.this.cameraName);
            }
        }

        public void onCameraError(CameraSession session, String error) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.m2191w(CameraCapturer.TAG, "onCameraError from another session: " + error);
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraError(error);
                CameraCapturer.this.stopCapture();
            }
        }

        public void onCameraDisconnected(CameraSession session) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.m2191w(CameraCapturer.TAG, "onCameraDisconnected from another session.");
                    return;
                }
                CameraCapturer.this.eventsHandler.onCameraDisconnected();
                CameraCapturer.this.stopCapture();
            }
        }

        public void onCameraClosed(CameraSession session) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session == CameraCapturer.this.currentSession || CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraClosed();
                    return;
                }
                Logging.m2187d(CameraCapturer.TAG, "onCameraClosed from another session.");
            }
        }

        public void onByteBufferFrameCaptured(CameraSession session, byte[] data, int width, int height, int rotation, long timestamp) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.m2191w(CameraCapturer.TAG, "onByteBufferFrameCaptured from another session.");
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onByteBufferFrameCaptured(data, width, height, rotation, timestamp);
            }
        }

        public void onTextureFrameCaptured(CameraSession session, int width, int height, int oesTextureId, float[] transformMatrix, int rotation, long timestamp) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (session != CameraCapturer.this.currentSession) {
                    Logging.m2191w(CameraCapturer.TAG, "onTextureFrameCaptured from another session.");
                    CameraCapturer.this.surfaceHelper.returnTextureFrame();
                    return;
                }
                if (!CameraCapturer.this.firstFrameObserved) {
                    CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                    CameraCapturer.this.firstFrameObserved = true;
                }
                CameraCapturer.this.cameraStatistics.addFrame();
                CameraCapturer.this.capturerObserver.onTextureFrameCaptured(width, height, oesTextureId, transformMatrix, rotation, timestamp);
            }
        }
    }

    class C25923 implements Runnable {
        C25923() {
        }

        public void run() {
            CameraCapturer.this.eventsHandler.onCameraError("Camera failed to start within timeout.");
        }
    }

    class C25934 implements CameraEventsHandler {
        C25934() {
        }

        public void onCameraError(String errorDescription) {
        }

        public void onCameraDisconnected() {
        }

        public void onCameraFreezed(String errorDescription) {
        }

        public void onCameraOpening(String cameraName) {
        }

        public void onFirstFrameAvailable() {
        }

        public void onCameraClosed() {
        }
    }

    class C25945 implements Runnable {
        C25945() {
        }

        public void run() {
            CameraCapturer.this.createCameraSession(CameraCapturer.this.createSessionCallback, CameraCapturer.this.cameraSessionEventsHandler, CameraCapturer.this.applicationContext, CameraCapturer.this.surfaceHelper, CameraCapturer.this.cameraName, CameraCapturer.this.width, CameraCapturer.this.height, CameraCapturer.this.framerate);
        }
    }

    enum SwitchState {
        IDLE,
        PENDING,
        IN_PROGRESS
    }

    protected abstract void createCameraSession(CreateSessionCallback createSessionCallback, Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, String str, int i, int i2, int i3);

    public CameraCapturer(String cameraName, CameraEventsHandler eventsHandler, CameraEnumerator cameraEnumerator) {
        if (eventsHandler == null) {
            eventsHandler = new C25934();
        }
        this.eventsHandler = eventsHandler;
        this.cameraEnumerator = cameraEnumerator;
        this.cameraName = cameraName;
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        if (deviceNames.length == 0) {
            throw new RuntimeException("No cameras attached.");
        } else if (!Arrays.asList(deviceNames).contains(this.cameraName)) {
            throw new IllegalArgumentException("Camera name " + this.cameraName + " does not match any known camera device.");
        }
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context applicationContext, CapturerObserver capturerObserver) {
        Handler handler;
        this.applicationContext = applicationContext;
        this.capturerObserver = capturerObserver;
        this.surfaceHelper = surfaceTextureHelper;
        if (surfaceTextureHelper == null) {
            handler = null;
        } else {
            handler = surfaceTextureHelper.getHandler();
        }
        this.cameraThreadHandler = handler;
    }

    public void startCapture(int width, int height, int framerate) {
        Logging.m2187d(TAG, "startCapture: " + width + "x" + height + "@" + framerate);
        if (this.applicationContext == null) {
            throw new RuntimeException("CameraCapturer must be initialized before calling startCapture.");
        }
        synchronized (this.stateLock) {
            if (this.sessionOpening || this.currentSession != null) {
                Logging.m2191w(TAG, "Session already open");
                return;
            }
            this.width = width;
            this.height = height;
            this.framerate = framerate;
            this.sessionOpening = true;
            this.openAttemptsRemaining = 3;
            createSessionInternal(0);
        }
    }

    private void createSessionInternal(int delayMs) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, (long) (delayMs + 10000));
        this.cameraThreadHandler.postDelayed(new C25945(), (long) delayMs);
    }

    public void stopCapture() {
        Logging.m2187d(TAG, "Stop capture");
        synchronized (this.stateLock) {
            while (this.sessionOpening) {
                Logging.m2187d(TAG, "Stop capture: Waiting for session to open");
                ThreadUtils.waitUninterruptibly(this.stateLock);
            }
            if (this.currentSession != null) {
                Logging.m2187d(TAG, "Stop capture: Nulling session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession oldSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() {
                    public void run() {
                        oldSession.stop();
                    }
                });
                this.currentSession = null;
                this.capturerObserver.onCapturerStopped();
            } else {
                Logging.m2187d(TAG, "Stop capture: No session open");
            }
        }
        Logging.m2187d(TAG, "Stop capture done");
    }

    public void changeCaptureFormat(int width, int height, int framerate) {
        Logging.m2187d(TAG, "changeCaptureFormat: " + width + "x" + height + "@" + framerate);
        synchronized (this.stateLock) {
            stopCapture();
            startCapture(width, height, framerate);
        }
    }

    public void dispose() {
        Logging.m2187d(TAG, "dispose");
        stopCapture();
    }

    public void switchCamera(final CameraSwitchHandler switchEventsHandler) {
        Logging.m2187d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new Runnable() {
            public void run() {
                CameraCapturer.this.switchCameraInternal(switchEventsHandler);
            }
        });
    }

    public boolean isScreencast() {
        return false;
    }

    public void printStackTrace() {
        Thread cameraThread = null;
        if (this.cameraThreadHandler != null) {
            cameraThread = this.cameraThreadHandler.getLooper().getThread();
        }
        if (cameraThread != null) {
            StackTraceElement[] cameraStackTrace = cameraThread.getStackTrace();
            if (cameraStackTrace.length > 0) {
                Logging.m2187d(TAG, "CameraCapturer stack trace:");
                for (StackTraceElement traceElem : cameraStackTrace) {
                    Logging.m2187d(TAG, traceElem.toString());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void switchCameraInternal(org.webrtc.CameraVideoCapturer.CameraSwitchHandler r7) {
        /*
        r6 = this;
        r3 = "CameraCapturer";
        r4 = "switchCamera internal";
        org.webrtc.Logging.m2187d(r3, r4);
        r3 = r6.cameraEnumerator;
        r1 = r3.getDeviceNames();
        r3 = r1.length;
        r4 = 2;
        if (r3 >= r4) goto L_0x0019;
    L_0x0011:
        if (r7 == 0) goto L_0x0018;
    L_0x0013:
        r3 = "No camera to switch to.";
        r7.onCameraSwitchError(r3);
    L_0x0018:
        return;
    L_0x0019:
        r4 = r6.stateLock;
        monitor-enter(r4);
        r3 = r6.switchState;	 Catch:{ all -> 0x0032 }
        r5 = org.webrtc.CameraCapturer.SwitchState.IDLE;	 Catch:{ all -> 0x0032 }
        if (r3 == r5) goto L_0x0035;
    L_0x0022:
        r3 = "CameraCapturer";
        r5 = "switchCamera switchInProgress";
        org.webrtc.Logging.m2187d(r3, r5);	 Catch:{ all -> 0x0032 }
        if (r7 == 0) goto L_0x0030;
    L_0x002b:
        r3 = "Camera switch already in progress.";
        r7.onCameraSwitchError(r3);	 Catch:{ all -> 0x0032 }
    L_0x0030:
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        goto L_0x0018;
    L_0x0032:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        throw r3;
    L_0x0035:
        r3 = r6.sessionOpening;	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x004d;
    L_0x0039:
        r3 = r6.currentSession;	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x004d;
    L_0x003d:
        r3 = "CameraCapturer";
        r5 = "switchCamera: No session open";
        org.webrtc.Logging.m2187d(r3, r5);	 Catch:{ all -> 0x0032 }
        if (r7 == 0) goto L_0x004b;
    L_0x0046:
        r3 = "Camera is not running.";
        r7.onCameraSwitchError(r3);	 Catch:{ all -> 0x0032 }
    L_0x004b:
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        goto L_0x0018;
    L_0x004d:
        r6.switchEventsHandler = r7;	 Catch:{ all -> 0x0032 }
        r3 = r6.sessionOpening;	 Catch:{ all -> 0x0032 }
        if (r3 == 0) goto L_0x0059;
    L_0x0053:
        r3 = org.webrtc.CameraCapturer.SwitchState.PENDING;	 Catch:{ all -> 0x0032 }
        r6.switchState = r3;	 Catch:{ all -> 0x0032 }
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        goto L_0x0018;
    L_0x0059:
        r3 = org.webrtc.CameraCapturer.SwitchState.IN_PROGRESS;	 Catch:{ all -> 0x0032 }
        r6.switchState = r3;	 Catch:{ all -> 0x0032 }
        r3 = "CameraCapturer";
        r5 = "switchCamera: Stopping session";
        org.webrtc.Logging.m2187d(r3, r5);	 Catch:{ all -> 0x0032 }
        r3 = r6.cameraStatistics;	 Catch:{ all -> 0x0032 }
        r3.release();	 Catch:{ all -> 0x0032 }
        r3 = 0;
        r6.cameraStatistics = r3;	 Catch:{ all -> 0x0032 }
        r2 = r6.currentSession;	 Catch:{ all -> 0x0032 }
        r3 = r6.cameraThreadHandler;	 Catch:{ all -> 0x0032 }
        r5 = new org.webrtc.CameraCapturer$8;	 Catch:{ all -> 0x0032 }
        r5.<init>(r2);	 Catch:{ all -> 0x0032 }
        r3.post(r5);	 Catch:{ all -> 0x0032 }
        r3 = 0;
        r6.currentSession = r3;	 Catch:{ all -> 0x0032 }
        r3 = java.util.Arrays.asList(r1);	 Catch:{ all -> 0x0032 }
        r5 = r6.cameraName;	 Catch:{ all -> 0x0032 }
        r0 = r3.indexOf(r5);	 Catch:{ all -> 0x0032 }
        r3 = r0 + 1;
        r5 = r1.length;	 Catch:{ all -> 0x0032 }
        r3 = r3 % r5;
        r3 = r1[r3];	 Catch:{ all -> 0x0032 }
        r6.cameraName = r3;	 Catch:{ all -> 0x0032 }
        r3 = 1;
        r6.sessionOpening = r3;	 Catch:{ all -> 0x0032 }
        r3 = 1;
        r6.openAttemptsRemaining = r3;	 Catch:{ all -> 0x0032 }
        r3 = 0;
        r6.createSessionInternal(r3);	 Catch:{ all -> 0x0032 }
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        r3 = "CameraCapturer";
        r4 = "switchCamera done";
        org.webrtc.Logging.m2187d(r3, r4);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.CameraCapturer.switchCameraInternal(org.webrtc.CameraVideoCapturer$CameraSwitchHandler):void");
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThreadHandler.getLooper().getThread()) {
            Logging.m2188e(TAG, "Check is on camera thread failed.");
            throw new RuntimeException("Not on camera thread.");
        }
    }

    protected String getCameraName() {
        String str;
        synchronized (this.stateLock) {
            str = this.cameraName;
        }
        return str;
    }
}
