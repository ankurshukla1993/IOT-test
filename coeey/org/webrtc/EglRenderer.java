package org.webrtc;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase.Context;
import org.webrtc.RendererCommon.GlDrawer;
import org.webrtc.RendererCommon.YuvUploader;
import org.webrtc.VideoRenderer.Callbacks;
import org.webrtc.VideoRenderer.I420Frame;

public class EglRenderer implements Callbacks {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final int MAX_SURFACE_CLEAR_COUNT = 3;
    private static final String TAG = "EglRenderer";
    private GlTextureFrameBuffer bitmapTextureFramebuffer;
    private GlDrawer drawer;
    private EglBase eglBase;
    private final EglSurfaceCreation eglSurfaceCreationRunnable = new EglSurfaceCreation();
    private final Object fpsReductionLock = new Object();
    private final ArrayList<FrameListenerAndParams> frameListeners = new ArrayList();
    private final Object frameLock = new Object();
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private final Object handlerLock = new Object();
    private float layoutAspectRatio;
    private final Object layoutLock = new Object();
    private final Runnable logStatisticsRunnable = new C26022();
    private long minRenderPeriodNs;
    private boolean mirror;
    private final String name;
    private long nextFrameTimeNs;
    private I420Frame pendingFrame;
    private final Runnable renderFrameRunnable = new C26011();
    private long renderSwapBufferTimeNs;
    private Handler renderThreadHandler;
    private long renderTimeNs;
    private final Object statisticsLock = new Object();
    private long statisticsStartTimeNs;
    private int[] yuvTextures = null;
    private final YuvUploader yuvUploader = new YuvUploader();

    class C26011 implements Runnable {
        C26011() {
        }

        public void run() {
            EglRenderer.this.renderFrameOnRenderThread();
        }
    }

    class C26022 implements Runnable {
        C26022() {
        }

        public void run() {
            EglRenderer.this.logStatistics();
            synchronized (EglRenderer.this.handlerLock) {
                if (EglRenderer.this.renderThreadHandler != null) {
                    EglRenderer.this.renderThreadHandler.removeCallbacks(EglRenderer.this.logStatisticsRunnable);
                    EglRenderer.this.renderThreadHandler.postDelayed(EglRenderer.this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4));
                }
            }
        }
    }

    private class EglSurfaceCreation implements Runnable {
        private Object surface;

        private EglSurfaceCreation() {
        }

        public synchronized void setSurface(Object surface) {
            this.surface = surface;
        }

        public synchronized void run() {
            if (!(this.surface == null || EglRenderer.this.eglBase == null || EglRenderer.this.eglBase.hasSurface())) {
                if (this.surface instanceof Surface) {
                    EglRenderer.this.eglBase.createSurface((Surface) this.surface);
                } else if (this.surface instanceof SurfaceTexture) {
                    EglRenderer.this.eglBase.createSurface((SurfaceTexture) this.surface);
                } else {
                    throw new IllegalStateException("Invalid surface: " + this.surface);
                }
                EglRenderer.this.eglBase.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }
    }

    public interface FrameListener {
        void onFrame(Bitmap bitmap);
    }

    private static class FrameListenerAndParams {
        public final GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener listener, float scale, GlDrawer drawer) {
            this.listener = listener;
            this.scale = scale;
            this.drawer = drawer;
        }
    }

    public EglRenderer(String name) {
        this.name = name;
    }

    public void init(final Context sharedContext, final int[] configAttributes, GlDrawer drawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                throw new IllegalStateException(this.name + "Already initialized");
            }
            logD("Initializing EglRenderer");
            this.drawer = drawer;
            HandlerThread renderThread = new HandlerThread(this.name + TAG);
            renderThread.start();
            this.renderThreadHandler = new Handler(renderThread.getLooper());
            ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, new Runnable() {
                public void run() {
                    if (sharedContext == null) {
                        EglRenderer.this.logD("EglBase10.create context");
                        EglRenderer.this.eglBase = new EglBase10(null, configAttributes);
                        return;
                    }
                    EglRenderer.this.logD("EglBase.create shared context");
                    EglRenderer.this.eglBase = EglBase.create(sharedContext, configAttributes);
                }
            });
            this.renderThreadHandler.post(this.eglSurfaceCreationRunnable);
            resetStatistics(System.nanoTime());
            this.renderThreadHandler.postDelayed(this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4));
        }
    }

    public void createEglSurface(Surface surface) {
        createEglSurfaceInternal(surface);
    }

    public void createEglSurface(SurfaceTexture surfaceTexture) {
        createEglSurfaceInternal(surfaceTexture);
    }

    private void createEglSurfaceInternal(Object surface) {
        this.eglSurfaceCreationRunnable.setSurface(surface);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
        r5 = this;
        r2 = "Releasing.";
        r5.logD(r2);
        r0 = new java.util.concurrent.CountDownLatch;
        r2 = 1;
        r0.<init>(r2);
        r3 = r5.handlerLock;
        monitor-enter(r3);
        r2 = r5.renderThreadHandler;	 Catch:{ all -> 0x0057 }
        if (r2 != 0) goto L_0x0019;
    L_0x0012:
        r2 = "Already released";
        r5.logD(r2);	 Catch:{ all -> 0x0057 }
        monitor-exit(r3);	 Catch:{ all -> 0x0057 }
    L_0x0018:
        return;
    L_0x0019:
        r2 = r5.renderThreadHandler;	 Catch:{ all -> 0x0057 }
        r4 = r5.logStatisticsRunnable;	 Catch:{ all -> 0x0057 }
        r2.removeCallbacks(r4);	 Catch:{ all -> 0x0057 }
        r2 = r5.renderThreadHandler;	 Catch:{ all -> 0x0057 }
        r4 = new org.webrtc.EglRenderer$4;	 Catch:{ all -> 0x0057 }
        r4.<init>(r0);	 Catch:{ all -> 0x0057 }
        r2.postAtFrontOfQueue(r4);	 Catch:{ all -> 0x0057 }
        r2 = r5.renderThreadHandler;	 Catch:{ all -> 0x0057 }
        r1 = r2.getLooper();	 Catch:{ all -> 0x0057 }
        r2 = r5.renderThreadHandler;	 Catch:{ all -> 0x0057 }
        r4 = new org.webrtc.EglRenderer$5;	 Catch:{ all -> 0x0057 }
        r4.<init>(r1);	 Catch:{ all -> 0x0057 }
        r2.post(r4);	 Catch:{ all -> 0x0057 }
        r2 = 0;
        r5.renderThreadHandler = r2;	 Catch:{ all -> 0x0057 }
        monitor-exit(r3);	 Catch:{ all -> 0x0057 }
        org.webrtc.ThreadUtils.awaitUninterruptibly(r0);
        r3 = r5.frameLock;
        monitor-enter(r3);
        r2 = r5.pendingFrame;	 Catch:{ all -> 0x005a }
        if (r2 == 0) goto L_0x0050;
    L_0x0048:
        r2 = r5.pendingFrame;	 Catch:{ all -> 0x005a }
        org.webrtc.VideoRenderer.renderFrameDone(r2);	 Catch:{ all -> 0x005a }
        r2 = 0;
        r5.pendingFrame = r2;	 Catch:{ all -> 0x005a }
    L_0x0050:
        monitor-exit(r3);	 Catch:{ all -> 0x005a }
        r2 = "Releasing done.";
        r5.logD(r2);
        goto L_0x0018;
    L_0x0057:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0057 }
        throw r2;
    L_0x005a:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x005a }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.EglRenderer.release():void");
    }

    private void resetStatistics(long currentTimeNs) {
        synchronized (this.statisticsLock) {
            this.statisticsStartTimeNs = currentTimeNs;
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.renderTimeNs = 0;
            this.renderSwapBufferTimeNs = 0;
        }
    }

    public void printStackTrace() {
        synchronized (this.handlerLock) {
            Thread renderThread;
            if (this.renderThreadHandler == null) {
                renderThread = null;
            } else {
                renderThread = this.renderThreadHandler.getLooper().getThread();
            }
            if (renderThread != null) {
                StackTraceElement[] renderStackTrace = renderThread.getStackTrace();
                if (renderStackTrace.length > 0) {
                    logD("EglRenderer stack trace:");
                    for (StackTraceElement traceElem : renderStackTrace) {
                        logD(traceElem.toString());
                    }
                }
            }
        }
    }

    public void setMirror(boolean mirror) {
        logD("setMirror: " + mirror);
        synchronized (this.layoutLock) {
            this.mirror = mirror;
        }
    }

    public void setLayoutAspectRatio(float layoutAspectRatio) {
        logD("setLayoutAspectRatio: " + layoutAspectRatio);
        synchronized (this.layoutLock) {
            this.layoutAspectRatio = layoutAspectRatio;
        }
    }

    public void setFpsReduction(float fps) {
        logD("setFpsReduction: " + fps);
        synchronized (this.fpsReductionLock) {
            long previousRenderPeriodNs = this.minRenderPeriodNs;
            if (fps <= 0.0f) {
                this.minRenderPeriodNs = Long.MAX_VALUE;
            } else {
                this.minRenderPeriodNs = (long) (((float) TimeUnit.SECONDS.toNanos(1)) / fps);
            }
            if (this.minRenderPeriodNs != previousRenderPeriodNs) {
                this.nextFrameTimeNs = System.nanoTime();
            }
        }
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void pauseVideo() {
        setFpsReduction(0.0f);
    }

    public void addFrameListener(final FrameListener listener, final float scale) {
        postToRenderThread(new Runnable() {
            public void run() {
                EglRenderer.this.frameListeners.add(new FrameListenerAndParams(listener, scale, EglRenderer.this.drawer));
            }
        });
    }

    public void addFrameListener(final FrameListener listener, final float scale, final GlDrawer drawer) {
        postToRenderThread(new Runnable() {
            public void run() {
                EglRenderer.this.frameListeners.add(new FrameListenerAndParams(listener, scale, drawer));
            }
        });
    }

    public void removeFrameListener(final FrameListener listener) {
        final CountDownLatch latch = new CountDownLatch(1);
        postToRenderThread(new Runnable() {
            public void run() {
                latch.countDown();
                Iterator<FrameListenerAndParams> iter = EglRenderer.this.frameListeners.iterator();
                while (iter.hasNext()) {
                    if (((FrameListenerAndParams) iter.next()).listener == listener) {
                        iter.remove();
                    }
                }
            }
        });
        ThreadUtils.awaitUninterruptibly(latch);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderFrame(org.webrtc.VideoRenderer.I420Frame r11) {
        /*
        r10 = this;
        r4 = r10.statisticsLock;
        monitor-enter(r4);
        r3 = r10.framesReceived;	 Catch:{ all -> 0x001b }
        r3 = r3 + 1;
        r10.framesReceived = r3;	 Catch:{ all -> 0x001b }
        monitor-exit(r4);	 Catch:{ all -> 0x001b }
        r4 = r10.handlerLock;
        monitor-enter(r4);
        r3 = r10.renderThreadHandler;	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x001e;
    L_0x0011:
        r3 = "Dropping frame - Not initialized or already released.";
        r10.logD(r3);	 Catch:{ all -> 0x003e }
        org.webrtc.VideoRenderer.renderFrameDone(r11);	 Catch:{ all -> 0x003e }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
    L_0x001a:
        return;
    L_0x001b:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x001b }
        throw r3;
    L_0x001e:
        r5 = r10.fpsReductionLock;	 Catch:{ all -> 0x003e }
        monitor-enter(r5);	 Catch:{ all -> 0x003e }
        r6 = r10.minRenderPeriodNs;	 Catch:{ all -> 0x007b }
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x0050;
    L_0x0029:
        r0 = java.lang.System.nanoTime();	 Catch:{ all -> 0x007b }
        r6 = r10.nextFrameTimeNs;	 Catch:{ all -> 0x007b }
        r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r3 >= 0) goto L_0x0041;
    L_0x0033:
        r3 = "Dropping frame - fps reduction is active.";
        r10.logD(r3);	 Catch:{ all -> 0x007b }
        org.webrtc.VideoRenderer.renderFrameDone(r11);	 Catch:{ all -> 0x007b }
        monitor-exit(r5);	 Catch:{ all -> 0x007b }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        goto L_0x001a;
    L_0x003e:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        throw r3;
    L_0x0041:
        r6 = r10.nextFrameTimeNs;	 Catch:{ all -> 0x007b }
        r8 = r10.minRenderPeriodNs;	 Catch:{ all -> 0x007b }
        r6 = r6 + r8;
        r10.nextFrameTimeNs = r6;	 Catch:{ all -> 0x007b }
        r6 = r10.nextFrameTimeNs;	 Catch:{ all -> 0x007b }
        r6 = java.lang.Math.max(r6, r0);	 Catch:{ all -> 0x007b }
        r10.nextFrameTimeNs = r6;	 Catch:{ all -> 0x007b }
    L_0x0050:
        monitor-exit(r5);	 Catch:{ all -> 0x007b }
        r5 = r10.frameLock;	 Catch:{ all -> 0x003e }
        monitor-enter(r5);	 Catch:{ all -> 0x003e }
        r3 = r10.pendingFrame;	 Catch:{ all -> 0x0080 }
        if (r3 == 0) goto L_0x007e;
    L_0x0058:
        r2 = 1;
    L_0x0059:
        if (r2 == 0) goto L_0x0060;
    L_0x005b:
        r3 = r10.pendingFrame;	 Catch:{ all -> 0x0080 }
        org.webrtc.VideoRenderer.renderFrameDone(r3);	 Catch:{ all -> 0x0080 }
    L_0x0060:
        r10.pendingFrame = r11;	 Catch:{ all -> 0x0080 }
        r3 = r10.renderThreadHandler;	 Catch:{ all -> 0x0080 }
        r6 = r10.renderFrameRunnable;	 Catch:{ all -> 0x0080 }
        r3.post(r6);	 Catch:{ all -> 0x0080 }
        monitor-exit(r5);	 Catch:{ all -> 0x0080 }
        monitor-exit(r4);	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x001a;
    L_0x006d:
        r4 = r10.statisticsLock;
        monitor-enter(r4);
        r3 = r10.framesDropped;	 Catch:{ all -> 0x0078 }
        r3 = r3 + 1;
        r10.framesDropped = r3;	 Catch:{ all -> 0x0078 }
        monitor-exit(r4);	 Catch:{ all -> 0x0078 }
        goto L_0x001a;
    L_0x0078:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0078 }
        throw r3;
    L_0x007b:
        r3 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x007b }
        throw r3;	 Catch:{ all -> 0x003e }
    L_0x007e:
        r2 = 0;
        goto L_0x0059;
    L_0x0080:
        r3 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0080 }
        throw r3;	 Catch:{ all -> 0x003e }
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.EglRenderer.renderFrame(org.webrtc.VideoRenderer$I420Frame):void");
    }

    public void releaseEglSurface(final Runnable completionCallback) {
        this.eglSurfaceCreationRunnable.setSurface(null);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.removeCallbacks(this.eglSurfaceCreationRunnable);
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable() {
                    public void run() {
                        if (EglRenderer.this.eglBase != null) {
                            EglRenderer.this.eglBase.detachCurrent();
                            EglRenderer.this.eglBase.releaseSurface();
                        }
                        completionCallback.run();
                    }
                });
                return;
            }
            completionCallback.run();
        }
    }

    private void postToRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.post(runnable);
            }
        }
    }

    private void clearSurfaceOnRenderThread() {
        if (this.eglBase != null && this.eglBase.hasSurface()) {
            logD("clearSurface");
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(16384);
            this.eglBase.swapBuffers();
        }
    }

    public void clearImage() {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                return;
            }
            this.renderThreadHandler.postAtFrontOfQueue(new Runnable() {
                public void run() {
                    EglRenderer.this.clearSurfaceOnRenderThread();
                }
            });
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderFrameOnRenderThread() {
        /*
        r27 = this;
        r0 = r27;
        r5 = r0.frameLock;
        monitor-enter(r5);
        r0 = r27;
        r4 = r0.pendingFrame;	 Catch:{ all -> 0x0034 }
        if (r4 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r27;
        r0 = r0.pendingFrame;	 Catch:{ all -> 0x0034 }
        r18 = r0;
        r4 = 0;
        r0 = r27;
        r0.pendingFrame = r4;	 Catch:{ all -> 0x0034 }
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
        r0 = r27;
        r4 = r0.eglBase;
        if (r4 == 0) goto L_0x0029;
    L_0x001f:
        r0 = r27;
        r4 = r0.eglBase;
        r4 = r4.hasSurface();
        if (r4 != 0) goto L_0x0037;
    L_0x0029:
        r4 = "Dropping frame - No surface";
        r0 = r27;
        r0.logD(r4);
        org.webrtc.VideoRenderer.renderFrameDone(r18);
        goto L_0x000c;
    L_0x0034:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0034 }
        throw r4;
    L_0x0037:
        r22 = java.lang.System.nanoTime();
        r0 = r18;
        r4 = r0.samplingMatrix;
        r0 = r18;
        r5 = r0.rotationDegree;
        r5 = (float) r5;
        r26 = org.webrtc.RendererCommon.rotateTextureMatrix(r4, r5);
        r0 = r27;
        r5 = r0.layoutLock;
        monitor-enter(r5);
        r0 = r27;
        r4 = r0.layoutAspectRatio;	 Catch:{ all -> 0x00f1 }
        r6 = 0;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x00d9;
    L_0x0056:
        r4 = r18.rotatedWidth();	 Catch:{ all -> 0x00f1 }
        r4 = (float) r4;	 Catch:{ all -> 0x00f1 }
        r6 = r18.rotatedHeight();	 Catch:{ all -> 0x00f1 }
        r6 = (float) r6;	 Catch:{ all -> 0x00f1 }
        r19 = r4 / r6;
        r0 = r27;
        r4 = r0.mirror;	 Catch:{ all -> 0x00f1 }
        r0 = r27;
        r6 = r0.layoutAspectRatio;	 Catch:{ all -> 0x00f1 }
        r0 = r19;
        r21 = org.webrtc.RendererCommon.getLayoutMatrix(r4, r0, r6);	 Catch:{ all -> 0x00f1 }
        r0 = r27;
        r4 = r0.layoutAspectRatio;	 Catch:{ all -> 0x00f1 }
        r4 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x00c7;
    L_0x0078:
        r4 = r18.rotatedHeight();	 Catch:{ all -> 0x00f1 }
        r4 = (float) r4;	 Catch:{ all -> 0x00f1 }
        r0 = r27;
        r6 = r0.layoutAspectRatio;	 Catch:{ all -> 0x00f1 }
        r4 = r4 * r6;
        r0 = (int) r4;	 Catch:{ all -> 0x00f1 }
        r17 = r0;
        r16 = r18.rotatedHeight();	 Catch:{ all -> 0x00f1 }
    L_0x0089:
        r0 = r26;
        r1 = r21;
        r13 = org.webrtc.RendererCommon.multiplyMatrices(r0, r1);	 Catch:{ all -> 0x00f1 }
        monitor-exit(r5);	 Catch:{ all -> 0x00f1 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        android.opengl.GLES20.glClearColor(r4, r5, r6, r7);
        r4 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        android.opengl.GLES20.glClear(r4);
        r0 = r18;
        r4 = r0.yuvFrame;
        if (r4 == 0) goto L_0x0174;
    L_0x00a4:
        r0 = r27;
        r4 = r0.yuvTextures;
        if (r4 != 0) goto L_0x00f4;
    L_0x00aa:
        r4 = 3;
        r4 = new int[r4];
        r0 = r27;
        r0.yuvTextures = r4;
        r20 = 0;
    L_0x00b3:
        r4 = 3;
        r0 = r20;
        if (r0 >= r4) goto L_0x00f4;
    L_0x00b8:
        r0 = r27;
        r4 = r0.yuvTextures;
        r5 = 3553; // 0xde1 float:4.979E-42 double:1.7554E-320;
        r5 = org.webrtc.GlUtil.generateTexture(r5);
        r4[r20] = r5;
        r20 = r20 + 1;
        goto L_0x00b3;
    L_0x00c7:
        r17 = r18.rotatedWidth();	 Catch:{ all -> 0x00f1 }
        r4 = r18.rotatedWidth();	 Catch:{ all -> 0x00f1 }
        r4 = (float) r4;	 Catch:{ all -> 0x00f1 }
        r0 = r27;
        r6 = r0.layoutAspectRatio;	 Catch:{ all -> 0x00f1 }
        r4 = r4 / r6;
        r0 = (int) r4;	 Catch:{ all -> 0x00f1 }
        r16 = r0;
        goto L_0x0089;
    L_0x00d9:
        r0 = r27;
        r4 = r0.mirror;	 Catch:{ all -> 0x00f1 }
        if (r4 == 0) goto L_0x00ec;
    L_0x00df:
        r21 = org.webrtc.RendererCommon.horizontalFlipMatrix();	 Catch:{ all -> 0x00f1 }
    L_0x00e3:
        r17 = r18.rotatedWidth();	 Catch:{ all -> 0x00f1 }
        r16 = r18.rotatedHeight();	 Catch:{ all -> 0x00f1 }
        goto L_0x0089;
    L_0x00ec:
        r21 = org.webrtc.RendererCommon.identityMatrix();	 Catch:{ all -> 0x00f1 }
        goto L_0x00e3;
    L_0x00f1:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00f1 }
        throw r4;
    L_0x00f4:
        r0 = r27;
        r4 = r0.yuvUploader;
        r0 = r27;
        r5 = r0.yuvTextures;
        r0 = r18;
        r6 = r0.width;
        r0 = r18;
        r7 = r0.height;
        r0 = r18;
        r8 = r0.yuvStrides;
        r0 = r18;
        r9 = r0.yuvPlanes;
        r4.uploadYuvData(r5, r6, r7, r8, r9);
        r0 = r27;
        r4 = r0.drawer;
        r0 = r27;
        r5 = r0.yuvTextures;
        r9 = 0;
        r10 = 0;
        r0 = r27;
        r6 = r0.eglBase;
        r11 = r6.surfaceWidth();
        r0 = r27;
        r6 = r0.eglBase;
        r12 = r6.surfaceHeight();
        r6 = r13;
        r7 = r17;
        r8 = r16;
        r4.drawYuv(r5, r6, r7, r8, r9, r10, r11, r12);
    L_0x0131:
        r24 = java.lang.System.nanoTime();
        r0 = r27;
        r4 = r0.eglBase;
        r4.swapBuffers();
        r14 = java.lang.System.nanoTime();
        r0 = r27;
        r5 = r0.statisticsLock;
        monitor-enter(r5);
        r0 = r27;
        r4 = r0.framesRendered;	 Catch:{ all -> 0x0197 }
        r4 = r4 + 1;
        r0 = r27;
        r0.framesRendered = r4;	 Catch:{ all -> 0x0197 }
        r0 = r27;
        r6 = r0.renderTimeNs;	 Catch:{ all -> 0x0197 }
        r8 = r14 - r22;
        r6 = r6 + r8;
        r0 = r27;
        r0.renderTimeNs = r6;	 Catch:{ all -> 0x0197 }
        r0 = r27;
        r6 = r0.renderSwapBufferTimeNs;	 Catch:{ all -> 0x0197 }
        r8 = r14 - r24;
        r6 = r6 + r8;
        r0 = r27;
        r0.renderSwapBufferTimeNs = r6;	 Catch:{ all -> 0x0197 }
        monitor-exit(r5);	 Catch:{ all -> 0x0197 }
        r0 = r27;
        r1 = r18;
        r2 = r26;
        r0.notifyCallbacks(r1, r2);
        org.webrtc.VideoRenderer.renderFrameDone(r18);
        goto L_0x000c;
    L_0x0174:
        r0 = r27;
        r4 = r0.drawer;
        r0 = r18;
        r5 = r0.textureId;
        r9 = 0;
        r10 = 0;
        r0 = r27;
        r6 = r0.eglBase;
        r11 = r6.surfaceWidth();
        r0 = r27;
        r6 = r0.eglBase;
        r12 = r6.surfaceHeight();
        r6 = r13;
        r7 = r17;
        r8 = r16;
        r4.drawOes(r5, r6, r7, r8, r9, r10, r11, r12);
        goto L_0x0131;
    L_0x0197:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0197 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.EglRenderer.renderFrameOnRenderThread():void");
    }

    private void notifyCallbacks(I420Frame frame, float[] texMatrix) {
        if (!this.frameListeners.isEmpty()) {
            ArrayList<FrameListenerAndParams> tmpList = new ArrayList(this.frameListeners);
            this.frameListeners.clear();
            float[] bitmapMatrix = RendererCommon.multiplyMatrices(RendererCommon.multiplyMatrices(texMatrix, this.mirror ? RendererCommon.horizontalFlipMatrix() : RendererCommon.identityMatrix()), RendererCommon.verticalFlipMatrix());
            Iterator it = tmpList.iterator();
            while (it.hasNext()) {
                FrameListenerAndParams listenerAndParams = (FrameListenerAndParams) it.next();
                int scaledWidth = (int) (listenerAndParams.scale * ((float) frame.rotatedWidth()));
                int scaledHeight = (int) (listenerAndParams.scale * ((float) frame.rotatedHeight()));
                if (scaledWidth == 0 || scaledHeight == 0) {
                    listenerAndParams.listener.onFrame(null);
                } else {
                    if (this.bitmapTextureFramebuffer == null) {
                        this.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
                    }
                    this.bitmapTextureFramebuffer.setSize(scaledWidth, scaledHeight);
                    GLES20.glBindFramebuffer(36160, this.bitmapTextureFramebuffer.getFrameBufferId());
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.bitmapTextureFramebuffer.getTextureId(), 0);
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                    GLES20.glClear(16384);
                    if (frame.yuvFrame) {
                        listenerAndParams.drawer.drawYuv(this.yuvTextures, bitmapMatrix, frame.rotatedWidth(), frame.rotatedHeight(), 0, 0, scaledWidth, scaledHeight);
                    } else {
                        listenerAndParams.drawer.drawOes(frame.textureId, bitmapMatrix, frame.rotatedWidth(), frame.rotatedHeight(), 0, 0, scaledWidth, scaledHeight);
                    }
                    ByteBuffer bitmapBuffer = ByteBuffer.allocateDirect((scaledWidth * scaledHeight) * 4);
                    GLES20.glViewport(0, 0, scaledWidth, scaledHeight);
                    GLES20.glReadPixels(0, 0, scaledWidth, scaledHeight, 6408, 5121, bitmapBuffer);
                    GLES20.glBindFramebuffer(36160, 0);
                    GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
                    Bitmap bitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Config.ARGB_8888);
                    bitmap.copyPixelsFromBuffer(bitmapBuffer);
                    listenerAndParams.listener.onFrame(bitmap);
                }
            }
        }
    }

    private String averageTimeAsString(long sumTimeNs, int count) {
        return count <= 0 ? "NA" : TimeUnit.NANOSECONDS.toMicros(sumTimeNs / ((long) count)) + " μs";
    }

    private void logStatistics() {
        long currentTimeNs = System.nanoTime();
        synchronized (this.statisticsLock) {
            long elapsedTimeNs = currentTimeNs - this.statisticsStartTimeNs;
            if (elapsedTimeNs <= 0) {
                return;
            }
            float renderFps = ((float) (((long) this.framesRendered) * TimeUnit.SECONDS.toNanos(1))) / ((float) elapsedTimeNs);
            logD("Duration: " + TimeUnit.NANOSECONDS.toMillis(elapsedTimeNs) + " ms. Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered + ". Render fps: " + String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(renderFps)}) + ". Average render time: " + averageTimeAsString(this.renderTimeNs, this.framesRendered) + ". Average swapBuffer time: " + averageTimeAsString(this.renderSwapBufferTimeNs, this.framesRendered) + ".");
            resetStatistics(currentTimeNs);
        }
    }

    private void logD(String string) {
        Logging.m2187d(TAG, this.name + string);
    }
}
