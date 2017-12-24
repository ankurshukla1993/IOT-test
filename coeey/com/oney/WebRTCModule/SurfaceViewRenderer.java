package com.oney.WebRTCModule;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Point;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;
import org.webrtc.EglBase;
import org.webrtc.GlRectDrawer;
import org.webrtc.Logging;
import org.webrtc.RendererCommon;
import org.webrtc.RendererCommon.GlDrawer;
import org.webrtc.RendererCommon.RendererEvents;
import org.webrtc.RendererCommon.ScalingType;
import org.webrtc.RendererCommon.YuvUploader;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRenderer.Callbacks;
import org.webrtc.VideoRenderer.I420Frame;

public class SurfaceViewRenderer extends SurfaceView implements Callback, Callbacks {
    private static final String TAG = "SurfaceViewRenderer";
    private Point desiredLayoutSize = new Point();
    private GlDrawer drawer;
    private EglBase eglBase;
    private long firstFrameTimeNs;
    private int frameHeight;
    private final Object frameLock = new Object();
    private int frameRotation;
    private int frameWidth;
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private final Object handlerLock = new Object();
    private boolean isSurfaceCreated;
    private final Object layoutLock = new Object();
    private final Point layoutSize = new Point();
    private final Runnable makeBlackRunnable = new C22982();
    private boolean mirror;
    private I420Frame pendingFrame;
    private final Runnable renderFrameRunnable = new C22971();
    private HandlerThread renderThread;
    private Handler renderThreadHandler;
    private long renderTimeNs;
    private RendererEvents rendererEvents;
    private ScalingType scalingType = ScalingType.SCALE_ASPECT_BALANCED;
    private final Object statisticsLock = new Object();
    private final Point surfaceSize = new Point();
    private int[] yuvTextures = null;
    private final YuvUploader yuvUploader = new YuvUploader();

    class C22971 implements Runnable {
        C22971() {
        }

        public void run() {
            SurfaceViewRenderer.this.renderFrameOnRenderThread();
        }
    }

    class C22982 implements Runnable {
        C22982() {
        }

        public void run() {
            SurfaceViewRenderer.this.makeBlack();
        }
    }

    class C23004 implements Runnable {
        C23004() {
        }

        public void run() {
            synchronized (SurfaceViewRenderer.this.layoutLock) {
                if (!(SurfaceViewRenderer.this.eglBase == null || !SurfaceViewRenderer.this.isSurfaceCreated || SurfaceViewRenderer.this.eglBase.hasSurface())) {
                    SurfaceViewRenderer.this.eglBase.createSurface(SurfaceViewRenderer.this.getHolder().getSurface());
                    SurfaceViewRenderer.this.eglBase.makeCurrent();
                    GLES20.glPixelStorei(3317, 1);
                }
            }
            SurfaceViewRenderer.this.makeBlack();
        }
    }

    class C23026 implements Runnable {
        C23026() {
        }

        public void run() {
            if (SurfaceViewRenderer.this.eglBase != null) {
                SurfaceViewRenderer.this.eglBase.detachCurrent();
                SurfaceViewRenderer.this.eglBase.releaseSurface();
            }
        }
    }

    class C23037 implements Runnable {
        C23037() {
        }

        public void run() {
            SurfaceViewRenderer.this.requestLayout();
        }
    }

    public SurfaceViewRenderer(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public void init(EglBase.Context sharedContext, RendererEvents rendererEvents) {
        init(sharedContext, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(final EglBase.Context sharedContext, RendererEvents rendererEvents, final int[] configAttributes, GlDrawer drawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                throw new IllegalStateException(getResourceName() + "Already initialized");
            }
            Logging.m2187d(TAG, getResourceName() + "Initializing.");
            this.rendererEvents = rendererEvents;
            this.drawer = drawer;
            this.renderThread = new HandlerThread(TAG);
            this.renderThread.start();
            this.renderThreadHandler = new Handler(this.renderThread.getLooper());
            ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, new Runnable() {
                public void run() {
                    SurfaceViewRenderer.this.eglBase = EglBase.create(sharedContext, configAttributes);
                }
            });
        }
        tryCreateEglSurface();
    }

    public void tryCreateEglSurface() {
        runOnRenderThread(new C23004());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
        r5 = this;
        r4 = 0;
        r0 = new java.util.concurrent.CountDownLatch;
        r1 = 1;
        r0.<init>(r1);
        r2 = r5.handlerLock;
        monitor-enter(r2);
        r1 = r5.renderThreadHandler;	 Catch:{ all -> 0x006d }
        if (r1 != 0) goto L_0x002c;
    L_0x000e:
        r1 = "SurfaceViewRenderer";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006d }
        r3.<init>();	 Catch:{ all -> 0x006d }
        r4 = r5.getResourceName();	 Catch:{ all -> 0x006d }
        r3 = r3.append(r4);	 Catch:{ all -> 0x006d }
        r4 = "Already released";
        r3 = r3.append(r4);	 Catch:{ all -> 0x006d }
        r3 = r3.toString();	 Catch:{ all -> 0x006d }
        org.webrtc.Logging.m2187d(r1, r3);	 Catch:{ all -> 0x006d }
        monitor-exit(r2);	 Catch:{ all -> 0x006d }
    L_0x002b:
        return;
    L_0x002c:
        r1 = r5.renderThreadHandler;	 Catch:{ all -> 0x006d }
        r3 = new com.oney.WebRTCModule.SurfaceViewRenderer$5;	 Catch:{ all -> 0x006d }
        r3.<init>(r0);	 Catch:{ all -> 0x006d }
        r1.postAtFrontOfQueue(r3);	 Catch:{ all -> 0x006d }
        r1 = 0;
        r5.renderThreadHandler = r1;	 Catch:{ all -> 0x006d }
        monitor-exit(r2);	 Catch:{ all -> 0x006d }
        org.webrtc.ThreadUtils.awaitUninterruptibly(r0);
        r1 = r5.renderThread;
        r1.quit();
        r2 = r5.frameLock;
        monitor-enter(r2);
        r1 = r5.pendingFrame;	 Catch:{ all -> 0x0070 }
        if (r1 == 0) goto L_0x0051;
    L_0x0049:
        r1 = r5.pendingFrame;	 Catch:{ all -> 0x0070 }
        org.webrtc.VideoRenderer.renderFrameDone(r1);	 Catch:{ all -> 0x0070 }
        r1 = 0;
        r5.pendingFrame = r1;	 Catch:{ all -> 0x0070 }
    L_0x0051:
        monitor-exit(r2);	 Catch:{ all -> 0x0070 }
        r1 = r5.renderThread;
        org.webrtc.ThreadUtils.joinUninterruptibly(r1);
        r5.renderThread = r4;
        r2 = r5.layoutLock;
        monitor-enter(r2);
        r1 = 0;
        r5.frameWidth = r1;	 Catch:{ all -> 0x0073 }
        r1 = 0;
        r5.frameHeight = r1;	 Catch:{ all -> 0x0073 }
        r1 = 0;
        r5.frameRotation = r1;	 Catch:{ all -> 0x0073 }
        r1 = 0;
        r5.rendererEvents = r1;	 Catch:{ all -> 0x0073 }
        monitor-exit(r2);	 Catch:{ all -> 0x0073 }
        r5.resetStatistics();
        goto L_0x002b;
    L_0x006d:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x006d }
        throw r1;
    L_0x0070:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0070 }
        throw r1;
    L_0x0073:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0073 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.SurfaceViewRenderer.release():void");
    }

    public void resetStatistics() {
        synchronized (this.statisticsLock) {
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.firstFrameTimeNs = 0;
            this.renderTimeNs = 0;
        }
    }

    public void setMirror(boolean mirror) {
        synchronized (this.layoutLock) {
            this.mirror = mirror;
        }
    }

    public void setScalingType(ScalingType scalingType) {
        synchronized (this.layoutLock) {
            this.scalingType = scalingType;
        }
    }

    public void renderFrame(I420Frame frame) {
        synchronized (this.statisticsLock) {
            this.framesReceived++;
        }
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                Logging.m2187d(TAG, getResourceName() + "Dropping frame - Not initialized or already released.");
                VideoRenderer.renderFrameDone(frame);
                return;
            }
            synchronized (this.frameLock) {
                if (this.pendingFrame != null) {
                    synchronized (this.statisticsLock) {
                        this.framesDropped++;
                    }
                    VideoRenderer.renderFrameDone(this.pendingFrame);
                }
                this.pendingFrame = frame;
                this.renderThreadHandler.post(this.renderFrameRunnable);
            }
        }
    }

    private Point getDesiredLayoutSize(int widthSpec, int heightSpec) {
        Point size;
        synchronized (this.layoutLock) {
            int maxWidth = getDefaultSize(Integer.MAX_VALUE, widthSpec);
            int maxHeight = getDefaultSize(Integer.MAX_VALUE, heightSpec);
            size = RendererCommon.getDisplaySize(this.scalingType, frameAspectRatio(), maxWidth, maxHeight);
            if (MeasureSpec.getMode(widthSpec) == Ints.MAX_POWER_OF_TWO) {
                size.x = maxWidth;
            }
            if (MeasureSpec.getMode(heightSpec) == Ints.MAX_POWER_OF_TWO) {
                size.y = maxHeight;
            }
        }
        return size;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r5, int r6) {
        /*
        r4 = this;
        r2 = r4.layoutLock;
        monitor-enter(r2);
        r1 = r4.frameWidth;	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x000b;
    L_0x0007:
        r1 = r4.frameHeight;	 Catch:{ all -> 0x004e }
        if (r1 != 0) goto L_0x0010;
    L_0x000b:
        super.onMeasure(r5, r6);	 Catch:{ all -> 0x004e }
        monitor-exit(r2);	 Catch:{ all -> 0x004e }
    L_0x000f:
        return;
    L_0x0010:
        r1 = r4.getDesiredLayoutSize(r5, r6);	 Catch:{ all -> 0x004e }
        r4.desiredLayoutSize = r1;	 Catch:{ all -> 0x004e }
        r1 = r4.desiredLayoutSize;	 Catch:{ all -> 0x004e }
        r1 = r1.x;	 Catch:{ all -> 0x004e }
        r3 = r4.getMeasuredWidth();	 Catch:{ all -> 0x004e }
        if (r1 != r3) goto L_0x002a;
    L_0x0020:
        r1 = r4.desiredLayoutSize;	 Catch:{ all -> 0x004e }
        r1 = r1.y;	 Catch:{ all -> 0x004e }
        r3 = r4.getMeasuredHeight();	 Catch:{ all -> 0x004e }
        if (r1 == r3) goto L_0x004c;
    L_0x002a:
        r0 = 1;
    L_0x002b:
        r1 = r4.desiredLayoutSize;	 Catch:{ all -> 0x004e }
        r1 = r1.x;	 Catch:{ all -> 0x004e }
        r3 = r4.desiredLayoutSize;	 Catch:{ all -> 0x004e }
        r3 = r3.y;	 Catch:{ all -> 0x004e }
        r4.setMeasuredDimension(r1, r3);	 Catch:{ all -> 0x004e }
        monitor-exit(r2);	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x000f;
    L_0x0039:
        r2 = r4.handlerLock;
        monitor-enter(r2);
        r1 = r4.renderThreadHandler;	 Catch:{ all -> 0x0049 }
        if (r1 == 0) goto L_0x0047;
    L_0x0040:
        r1 = r4.renderThreadHandler;	 Catch:{ all -> 0x0049 }
        r3 = r4.makeBlackRunnable;	 Catch:{ all -> 0x0049 }
        r1.postAtFrontOfQueue(r3);	 Catch:{ all -> 0x0049 }
    L_0x0047:
        monitor-exit(r2);	 Catch:{ all -> 0x0049 }
        goto L_0x000f;
    L_0x0049:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0049 }
        throw r1;
    L_0x004c:
        r0 = 0;
        goto L_0x002b;
    L_0x004e:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x004e }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.SurfaceViewRenderer.onMeasure(int, int):void");
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        synchronized (this.layoutLock) {
            this.layoutSize.x = right - left;
            this.layoutSize.y = bottom - top;
        }
        runOnRenderThread(this.renderFrameRunnable);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Logging.m2187d(TAG, getResourceName() + "Surface created.");
        synchronized (this.layoutLock) {
            this.isSurfaceCreated = true;
        }
        tryCreateEglSurface();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Logging.m2187d(TAG, getResourceName() + "Surface destroyed.");
        synchronized (this.layoutLock) {
            this.isSurfaceCreated = false;
            this.surfaceSize.x = 0;
            this.surfaceSize.y = 0;
        }
        runOnRenderThread(new C23026());
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Logging.m2187d(TAG, getResourceName() + "Surface changed: " + width + "x" + height);
        synchronized (this.layoutLock) {
            this.surfaceSize.x = width;
            this.surfaceSize.y = height;
        }
        runOnRenderThread(this.renderFrameRunnable);
    }

    private void runOnRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.post(runnable);
            }
        }
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + ": ";
        } catch (NotFoundException e) {
            return "";
        }
    }

    private void makeBlack() {
        if (Thread.currentThread() != this.renderThread) {
            throw new IllegalStateException(getResourceName() + "Wrong thread.");
        } else if (this.eglBase != null && this.eglBase.hasSurface()) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(16384);
            this.eglBase.swapBuffers();
        }
    }

    private boolean checkConsistentLayout() {
        if (Thread.currentThread() != this.renderThread) {
            throw new IllegalStateException(getResourceName() + "Wrong thread.");
        }
        boolean equals;
        synchronized (this.layoutLock) {
            equals = this.surfaceSize.equals(this.layoutSize);
        }
        return equals;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderFrameOnRenderThread() {
        /*
        r20 = this;
        r4 = java.lang.Thread.currentThread();
        r0 = r20;
        r5 = r0.renderThread;
        if (r4 == r5) goto L_0x0027;
    L_0x000a:
        r4 = new java.lang.IllegalStateException;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r20.getResourceName();
        r5 = r5.append(r6);
        r6 = "Wrong thread.";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x0027:
        r0 = r20;
        r5 = r0.frameLock;
        monitor-enter(r5);
        r0 = r20;
        r4 = r0.pendingFrame;	 Catch:{ all -> 0x0073 }
        if (r4 != 0) goto L_0x0034;
    L_0x0032:
        monitor-exit(r5);	 Catch:{ all -> 0x0073 }
    L_0x0033:
        return;
    L_0x0034:
        r0 = r20;
        r13 = r0.pendingFrame;	 Catch:{ all -> 0x0073 }
        r4 = 0;
        r0 = r20;
        r0.pendingFrame = r4;	 Catch:{ all -> 0x0073 }
        monitor-exit(r5);	 Catch:{ all -> 0x0073 }
        r0 = r20;
        r0.updateFrameDimensionsAndReportEvents(r13);
        r0 = r20;
        r4 = r0.eglBase;
        if (r4 == 0) goto L_0x0053;
    L_0x0049:
        r0 = r20;
        r4 = r0.eglBase;
        r4 = r4.hasSurface();
        if (r4 != 0) goto L_0x0076;
    L_0x0053:
        r4 = "SurfaceViewRenderer";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r20.getResourceName();
        r5 = r5.append(r6);
        r6 = "No surface to draw on";
        r5 = r5.append(r6);
        r5 = r5.toString();
        org.webrtc.Logging.m2187d(r4, r5);
        org.webrtc.VideoRenderer.renderFrameDone(r13);
        goto L_0x0033;
    L_0x0073:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0073 }
        throw r4;
    L_0x0076:
        r4 = r20.checkConsistentLayout();
        if (r4 != 0) goto L_0x0083;
    L_0x007c:
        r20.makeBlack();
        org.webrtc.VideoRenderer.renderFrameDone(r13);
        goto L_0x0033;
    L_0x0083:
        r0 = r20;
        r5 = r0.layoutLock;
        monitor-enter(r5);
        r0 = r20;
        r4 = r0.eglBase;	 Catch:{ all -> 0x0109 }
        r4 = r4.surfaceWidth();	 Catch:{ all -> 0x0109 }
        r0 = r20;
        r6 = r0.surfaceSize;	 Catch:{ all -> 0x0109 }
        r6 = r6.x;	 Catch:{ all -> 0x0109 }
        if (r4 != r6) goto L_0x00a8;
    L_0x0098:
        r0 = r20;
        r4 = r0.eglBase;	 Catch:{ all -> 0x0109 }
        r4 = r4.surfaceHeight();	 Catch:{ all -> 0x0109 }
        r0 = r20;
        r6 = r0.surfaceSize;	 Catch:{ all -> 0x0109 }
        r6 = r6.y;	 Catch:{ all -> 0x0109 }
        if (r4 == r6) goto L_0x00ab;
    L_0x00a8:
        r20.makeBlack();	 Catch:{ all -> 0x0109 }
    L_0x00ab:
        monitor-exit(r5);	 Catch:{ all -> 0x0109 }
        r18 = java.lang.System.nanoTime();
        r0 = r20;
        r5 = r0.layoutLock;
        monitor-enter(r5);
        r4 = r13.samplingMatrix;	 Catch:{ all -> 0x010c }
        r6 = r13.rotationDegree;	 Catch:{ all -> 0x010c }
        r6 = (float) r6;	 Catch:{ all -> 0x010c }
        r16 = org.webrtc.RendererCommon.rotateTextureMatrix(r4, r6);	 Catch:{ all -> 0x010c }
        r0 = r20;
        r4 = r0.mirror;	 Catch:{ all -> 0x010c }
        r6 = r20.frameAspectRatio();	 Catch:{ all -> 0x010c }
        r0 = r20;
        r7 = r0.layoutSize;	 Catch:{ all -> 0x010c }
        r7 = r7.x;	 Catch:{ all -> 0x010c }
        r7 = (float) r7;	 Catch:{ all -> 0x010c }
        r0 = r20;
        r8 = r0.layoutSize;	 Catch:{ all -> 0x010c }
        r8 = r8.y;	 Catch:{ all -> 0x010c }
        r8 = (float) r8;	 Catch:{ all -> 0x010c }
        r7 = r7 / r8;
        r15 = org.webrtc.RendererCommon.getLayoutMatrix(r4, r6, r7);	 Catch:{ all -> 0x010c }
        r0 = r16;
        r17 = org.webrtc.RendererCommon.multiplyMatrices(r0, r15);	 Catch:{ all -> 0x010c }
        monitor-exit(r5);	 Catch:{ all -> 0x010c }
        r4 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        android.opengl.GLES20.glClear(r4);
        r4 = r13.yuvFrame;
        if (r4 == 0) goto L_0x01b9;
    L_0x00e9:
        r0 = r20;
        r4 = r0.yuvTextures;
        if (r4 != 0) goto L_0x010f;
    L_0x00ef:
        r4 = 3;
        r4 = new int[r4];
        r0 = r20;
        r0.yuvTextures = r4;
        r14 = 0;
    L_0x00f7:
        r4 = 3;
        if (r14 >= r4) goto L_0x010f;
    L_0x00fa:
        r0 = r20;
        r4 = r0.yuvTextures;
        r5 = 3553; // 0xde1 float:4.979E-42 double:1.7554E-320;
        r5 = org.webrtc.GlUtil.generateTexture(r5);
        r4[r14] = r5;
        r14 = r14 + 1;
        goto L_0x00f7;
    L_0x0109:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0109 }
        throw r4;
    L_0x010c:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x010c }
        throw r4;
    L_0x010f:
        r0 = r20;
        r4 = r0.yuvUploader;
        r0 = r20;
        r5 = r0.yuvTextures;
        r6 = r13.width;
        r7 = r13.height;
        r8 = r13.yuvStrides;
        r9 = r13.yuvPlanes;
        r4.uploadYuvData(r5, r6, r7, r8, r9);
        r0 = r20;
        r4 = r0.drawer;
        r0 = r20;
        r5 = r0.yuvTextures;
        r7 = r13.rotatedWidth();
        r8 = r13.rotatedHeight();
        r9 = 0;
        r10 = 0;
        r0 = r20;
        r6 = r0.surfaceSize;
        r11 = r6.x;
        r0 = r20;
        r6 = r0.surfaceSize;
        r12 = r6.y;
        r6 = r17;
        r4.drawYuv(r5, r6, r7, r8, r9, r10, r11, r12);
    L_0x0145:
        r0 = r20;
        r4 = r0.eglBase;
        r4.swapBuffers();
        org.webrtc.VideoRenderer.renderFrameDone(r13);
        r0 = r20;
        r5 = r0.statisticsLock;
        monitor-enter(r5);
        r0 = r20;
        r4 = r0.framesRendered;	 Catch:{ all -> 0x01b6 }
        if (r4 != 0) goto L_0x018f;
    L_0x015a:
        r0 = r18;
        r2 = r20;
        r2.firstFrameTimeNs = r0;	 Catch:{ all -> 0x01b6 }
        r0 = r20;
        r6 = r0.layoutLock;	 Catch:{ all -> 0x01b6 }
        monitor-enter(r6);	 Catch:{ all -> 0x01b6 }
        r4 = "SurfaceViewRenderer";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01dc }
        r7.<init>();	 Catch:{ all -> 0x01dc }
        r8 = r20.getResourceName();	 Catch:{ all -> 0x01dc }
        r7 = r7.append(r8);	 Catch:{ all -> 0x01dc }
        r8 = "Reporting first rendered frame.";
        r7 = r7.append(r8);	 Catch:{ all -> 0x01dc }
        r7 = r7.toString();	 Catch:{ all -> 0x01dc }
        org.webrtc.Logging.m2187d(r4, r7);	 Catch:{ all -> 0x01dc }
        r0 = r20;
        r4 = r0.rendererEvents;	 Catch:{ all -> 0x01dc }
        if (r4 == 0) goto L_0x018e;
    L_0x0187:
        r0 = r20;
        r4 = r0.rendererEvents;	 Catch:{ all -> 0x01dc }
        r4.onFirstFrameRendered();	 Catch:{ all -> 0x01dc }
    L_0x018e:
        monitor-exit(r6);	 Catch:{ all -> 0x01dc }
    L_0x018f:
        r0 = r20;
        r4 = r0.framesRendered;	 Catch:{ all -> 0x01b6 }
        r4 = r4 + 1;
        r0 = r20;
        r0.framesRendered = r4;	 Catch:{ all -> 0x01b6 }
        r0 = r20;
        r6 = r0.renderTimeNs;	 Catch:{ all -> 0x01b6 }
        r8 = java.lang.System.nanoTime();	 Catch:{ all -> 0x01b6 }
        r8 = r8 - r18;
        r6 = r6 + r8;
        r0 = r20;
        r0.renderTimeNs = r6;	 Catch:{ all -> 0x01b6 }
        r0 = r20;
        r4 = r0.framesRendered;	 Catch:{ all -> 0x01b6 }
        r4 = r4 % 300;
        if (r4 != 0) goto L_0x01b3;
    L_0x01b0:
        r20.logStatistics();	 Catch:{ all -> 0x01b6 }
    L_0x01b3:
        monitor-exit(r5);	 Catch:{ all -> 0x01b6 }
        goto L_0x0033;
    L_0x01b6:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x01b6 }
        throw r4;
    L_0x01b9:
        r0 = r20;
        r4 = r0.drawer;
        r5 = r13.textureId;
        r7 = r13.rotatedWidth();
        r8 = r13.rotatedHeight();
        r9 = 0;
        r10 = 0;
        r0 = r20;
        r6 = r0.surfaceSize;
        r11 = r6.x;
        r0 = r20;
        r6 = r0.surfaceSize;
        r12 = r6.y;
        r6 = r17;
        r4.drawOes(r5, r6, r7, r8, r9, r10, r11, r12);
        goto L_0x0145;
    L_0x01dc:
        r4 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x01dc }
        throw r4;	 Catch:{ all -> 0x01b6 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.SurfaceViewRenderer.renderFrameOnRenderThread():void");
    }

    private float frameAspectRatio() {
        float f;
        synchronized (this.layoutLock) {
            if (this.frameWidth == 0 || this.frameHeight == 0) {
                f = 0.0f;
            } else {
                f = this.frameRotation % 180 == 0 ? ((float) this.frameWidth) / ((float) this.frameHeight) : ((float) this.frameHeight) / ((float) this.frameWidth);
            }
        }
        return f;
    }

    private void updateFrameDimensionsAndReportEvents(I420Frame frame) {
        synchronized (this.layoutLock) {
            if (!(this.frameWidth == frame.width && this.frameHeight == frame.height && this.frameRotation == frame.rotationDegree)) {
                Logging.m2187d(TAG, getResourceName() + "Reporting frame resolution changed to " + frame.width + "x" + frame.height + " with rotation " + frame.rotationDegree);
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(frame.width, frame.height, frame.rotationDegree);
                }
                this.frameWidth = frame.width;
                this.frameHeight = frame.height;
                this.frameRotation = frame.rotationDegree;
                post(new C23037());
            }
        }
    }

    private void logStatistics() {
        synchronized (this.statisticsLock) {
            Logging.m2187d(TAG, getResourceName() + "Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered);
            if (this.framesReceived > 0 && this.framesRendered > 0) {
                long timeSinceFirstFrameNs = System.nanoTime() - this.firstFrameTimeNs;
                Logging.m2187d(TAG, getResourceName() + "Duration: " + ((int) (((double) timeSinceFirstFrameNs) / 1000000.0d)) + " ms. FPS: " + ((((double) this.framesRendered) * 1.0E9d) / ((double) timeSinceFirstFrameNs)));
                Logging.m2187d(TAG, getResourceName() + "Average render time: " + ((int) (this.renderTimeNs / ((long) (this.framesRendered * 1000)))) + " us.");
            }
        }
    }
}
