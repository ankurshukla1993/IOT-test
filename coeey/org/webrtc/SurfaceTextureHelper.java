package org.webrtc;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase.Context;

public class SurfaceTextureHelper {
    private static final String TAG = "SurfaceTextureHelper";
    private final EglBase eglBase;
    private final Handler handler;
    private boolean hasPendingTexture;
    private boolean isQuitting;
    private volatile boolean isTextureInUse;
    private OnTextureFrameAvailableListener listener;
    private final int oesTextureId;
    private OnTextureFrameAvailableListener pendingListener;
    final Runnable setListenerRunnable;
    private final SurfaceTexture surfaceTexture;
    private YuvConverter yuvConverter;

    public interface OnTextureFrameAvailableListener {
        void onTextureFrameAvailable(int i, float[] fArr, long j);
    }

    class C26202 implements Runnable {
        C26202() {
        }

        public void run() {
            Logging.m2187d(SurfaceTextureHelper.TAG, "Setting listener to " + SurfaceTextureHelper.this.pendingListener);
            SurfaceTextureHelper.this.listener = SurfaceTextureHelper.this.pendingListener;
            SurfaceTextureHelper.this.pendingListener = null;
            if (SurfaceTextureHelper.this.hasPendingTexture) {
                SurfaceTextureHelper.this.updateTexImage();
                SurfaceTextureHelper.this.hasPendingTexture = false;
            }
        }
    }

    class C26213 implements OnFrameAvailableListener {
        C26213() {
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            SurfaceTextureHelper.this.hasPendingTexture = true;
            SurfaceTextureHelper.this.tryDeliverTextureFrame();
        }
    }

    class C26224 implements Runnable {
        C26224() {
        }

        public void run() {
            SurfaceTextureHelper.this.listener = null;
            SurfaceTextureHelper.this.pendingListener = null;
        }
    }

    class C26235 implements Runnable {
        C26235() {
        }

        public void run() {
            SurfaceTextureHelper.this.isTextureInUse = false;
            if (SurfaceTextureHelper.this.isQuitting) {
                SurfaceTextureHelper.this.release();
            } else {
                SurfaceTextureHelper.this.tryDeliverTextureFrame();
            }
        }
    }

    class C26246 implements Runnable {
        C26246() {
        }

        public void run() {
            SurfaceTextureHelper.this.isQuitting = true;
            if (!SurfaceTextureHelper.this.isTextureInUse) {
                SurfaceTextureHelper.this.release();
            }
        }
    }

    public static SurfaceTextureHelper create(final String threadName, final Context sharedContext) {
        HandlerThread thread = new HandlerThread(threadName);
        thread.start();
        final Handler handler = new Handler(thread.getLooper());
        return (SurfaceTextureHelper) ThreadUtils.invokeAtFrontUninterruptibly(handler, new Callable<SurfaceTextureHelper>() {
            public SurfaceTextureHelper call() {
                try {
                    return new SurfaceTextureHelper(sharedContext, handler);
                } catch (RuntimeException e) {
                    Logging.m2189e(SurfaceTextureHelper.TAG, threadName + " create failure", e);
                    return null;
                }
            }
        });
    }

    private SurfaceTextureHelper(Context sharedContext, Handler handler) {
        this.hasPendingTexture = false;
        this.isTextureInUse = false;
        this.isQuitting = false;
        this.setListenerRunnable = new C26202();
        if (handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("SurfaceTextureHelper must be created on the handler thread");
        }
        this.handler = handler;
        this.eglBase = EglBase.create(sharedContext, EglBase.CONFIG_PIXEL_BUFFER);
        try {
            this.eglBase.createDummyPbufferSurface();
            this.eglBase.makeCurrent();
            this.oesTextureId = GlUtil.generateTexture(36197);
            this.surfaceTexture = new SurfaceTexture(this.oesTextureId);
            this.surfaceTexture.setOnFrameAvailableListener(new C26213());
        } catch (RuntimeException e) {
            this.eglBase.release();
            handler.getLooper().quit();
            throw e;
        }
    }

    public void startListening(OnTextureFrameAvailableListener listener) {
        if (this.listener == null && this.pendingListener == null) {
            this.pendingListener = listener;
            this.handler.post(this.setListenerRunnable);
            return;
        }
        throw new IllegalStateException("SurfaceTextureHelper listener has already been set.");
    }

    public void stopListening() {
        Logging.m2187d(TAG, "stopListening()");
        this.handler.removeCallbacks(this.setListenerRunnable);
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new C26224());
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void returnTextureFrame() {
        this.handler.post(new C26235());
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    public void dispose() {
        Logging.m2187d(TAG, "dispose()");
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new C26246());
    }

    public void textureToYUV(ByteBuffer buf, int width, int height, int stride, int textureId, float[] transformMatrix) {
        if (textureId != this.oesTextureId) {
            throw new IllegalStateException("textureToByteBuffer called with unexpected textureId");
        }
        final ByteBuffer byteBuffer = buf;
        final int i = width;
        final int i2 = height;
        final int i3 = stride;
        final int i4 = textureId;
        final float[] fArr = transformMatrix;
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() {
            public void run() {
                if (SurfaceTextureHelper.this.yuvConverter == null) {
                    SurfaceTextureHelper.this.yuvConverter = new YuvConverter();
                }
                SurfaceTextureHelper.this.yuvConverter.convert(byteBuffer, i, i2, i3, i4, fArr);
            }
        });
    }

    private void updateTexImage() {
        synchronized (EglBase.lock) {
            this.surfaceTexture.updateTexImage();
        }
    }

    private void tryDeliverTextureFrame() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (!this.isQuitting && this.hasPendingTexture && !this.isTextureInUse && this.listener != null) {
            long timestampNs;
            this.isTextureInUse = true;
            this.hasPendingTexture = false;
            updateTexImage();
            float[] transformMatrix = new float[16];
            this.surfaceTexture.getTransformMatrix(transformMatrix);
            if (VERSION.SDK_INT >= 14) {
                timestampNs = this.surfaceTexture.getTimestamp();
            } else {
                timestampNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            }
            this.listener.onTextureFrameAvailable(this.oesTextureId, transformMatrix, timestampNs);
        }
    }

    private void release() {
        if (this.handler.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Wrong thread.");
        } else if (this.isTextureInUse || !this.isQuitting) {
            throw new IllegalStateException("Unexpected release.");
        } else {
            if (this.yuvConverter != null) {
                this.yuvConverter.release();
            }
            GLES20.glDeleteTextures(1, new int[]{this.oesTextureId}, 0);
            this.surfaceTexture.release();
            this.eglBase.release();
            this.handler.getLooper().quit();
        }
    }
}
