package org.webrtc;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglRenderer.FrameListener;
import org.webrtc.RendererCommon.GlDrawer;
import org.webrtc.RendererCommon.RendererEvents;
import org.webrtc.RendererCommon.ScalingType;
import org.webrtc.RendererCommon.VideoLayoutMeasure;
import org.webrtc.VideoRenderer.Callbacks;
import org.webrtc.VideoRenderer.I420Frame;

public class SurfaceViewRenderer extends SurfaceView implements Callback, Callbacks {
    private static final String TAG = "SurfaceViewRenderer";
    private final EglRenderer eglRenderer = new EglRenderer(this.resourceName);
    private boolean enableFixedSize;
    private int frameRotation;
    private boolean isFirstFrameRendered;
    private final Object layoutLock = new Object();
    private RendererEvents rendererEvents;
    private final String resourceName = getResourceName();
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private int surfaceHeight;
    private int surfaceWidth;
    private final VideoLayoutMeasure videoLayoutMeasure = new VideoLayoutMeasure();

    class C26272 implements Runnable {
        C26272() {
        }

        public void run() {
            SurfaceViewRenderer.this.updateSurfaceSize();
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

    public void init(EglBase.Context sharedContext, RendererEvents rendererEvents, int[] configAttributes, GlDrawer drawer) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = rendererEvents;
        synchronized (this.layoutLock) {
            this.rotatedFrameWidth = 0;
            this.rotatedFrameHeight = 0;
            this.frameRotation = 0;
        }
        this.eglRenderer.init(sharedContext, configAttributes, drawer);
    }

    public void release() {
        this.eglRenderer.release();
    }

    public void addFrameListener(FrameListener listener, float scale, GlDrawer drawer) {
        this.eglRenderer.addFrameListener(listener, scale, drawer);
    }

    public void addFrameListener(FrameListener listener, float scale) {
        this.eglRenderer.addFrameListener(listener, scale);
    }

    public void removeFrameListener(FrameListener listener) {
        this.eglRenderer.removeFrameListener(listener);
    }

    public void setEnableHardwareScaler(boolean enabled) {
        ThreadUtils.checkIsOnMainThread();
        this.enableFixedSize = enabled;
        updateSurfaceSize();
    }

    public void setMirror(boolean mirror) {
        this.eglRenderer.setMirror(mirror);
    }

    public void setScalingType(ScalingType scalingType) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(scalingType);
    }

    public void setScalingType(ScalingType scalingTypeMatchOrientation, ScalingType scalingTypeMismatchOrientation) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(scalingTypeMatchOrientation, scalingTypeMismatchOrientation);
    }

    public void setFpsReduction(float fps) {
        this.eglRenderer.setFpsReduction(fps);
    }

    public void disableFpsReduction() {
        this.eglRenderer.disableFpsReduction();
    }

    public void pauseVideo() {
        this.eglRenderer.pauseVideo();
    }

    public void renderFrame(I420Frame frame) {
        updateFrameDimensionsAndReportEvents(frame);
        this.eglRenderer.renderFrame(frame);
    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        Point size;
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            size = this.videoLayoutMeasure.measure(widthSpec, heightSpec, this.rotatedFrameWidth, this.rotatedFrameHeight);
        }
        setMeasuredDimension(size.x, size.y);
        logD("onMeasure(). New size: " + size.x + "x" + size.y);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.setLayoutAspectRatio(((float) (right - left)) / ((float) (bottom - top)));
        updateSurfaceSize();
    }

    private void updateSurfaceSize() {
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            if (!this.enableFixedSize || this.rotatedFrameWidth == 0 || this.rotatedFrameHeight == 0 || getWidth() == 0 || getHeight() == 0) {
                this.surfaceHeight = 0;
                this.surfaceWidth = 0;
                getHolder().setSizeFromLayout();
            } else {
                int drawnFrameWidth;
                int drawnFrameHeight;
                float layoutAspectRatio = ((float) getWidth()) / ((float) getHeight());
                if (((float) this.rotatedFrameWidth) / ((float) this.rotatedFrameHeight) > layoutAspectRatio) {
                    drawnFrameWidth = (int) (((float) this.rotatedFrameHeight) * layoutAspectRatio);
                    drawnFrameHeight = this.rotatedFrameHeight;
                } else {
                    drawnFrameWidth = this.rotatedFrameWidth;
                    drawnFrameHeight = (int) (((float) this.rotatedFrameWidth) / layoutAspectRatio);
                }
                int width = Math.min(getWidth(), drawnFrameWidth);
                int height = Math.min(getHeight(), drawnFrameHeight);
                logD("updateSurfaceSize. Layout size: " + getWidth() + "x" + getHeight() + ", frame size: " + this.rotatedFrameWidth + "x" + this.rotatedFrameHeight + ", requested surface size: " + width + "x" + height + ", old surface size: " + this.surfaceWidth + "x" + this.surfaceHeight);
                if (!(width == this.surfaceWidth && height == this.surfaceHeight)) {
                    this.surfaceWidth = width;
                    this.surfaceHeight = height;
                    getHolder().setFixedSize(width, height);
                }
            }
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.createEglSurface(holder.getSurface());
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        updateSurfaceSize();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        ThreadUtils.checkIsOnMainThread();
        final CountDownLatch completionLatch = new CountDownLatch(1);
        this.eglRenderer.releaseEglSurface(new Runnable() {
            public void run() {
                completionLatch.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(completionLatch);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        ThreadUtils.checkIsOnMainThread();
        logD("surfaceChanged: format: " + format + " size: " + width + "x" + height);
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + ": ";
        } catch (NotFoundException e) {
            return "";
        }
    }

    public void clearImage() {
        this.eglRenderer.clearImage();
    }

    private void updateFrameDimensionsAndReportEvents(I420Frame frame) {
        synchronized (this.layoutLock) {
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                logD("Reporting first rendered frame.");
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFirstFrameRendered();
                }
            }
            if (!(this.rotatedFrameWidth == frame.rotatedWidth() && this.rotatedFrameHeight == frame.rotatedHeight() && this.frameRotation == frame.rotationDegree)) {
                logD("Reporting frame resolution changed to " + frame.width + "x" + frame.height + " with rotation " + frame.rotationDegree);
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(frame.width, frame.height, frame.rotationDegree);
                }
                this.rotatedFrameWidth = frame.rotatedWidth();
                this.rotatedFrameHeight = frame.rotatedHeight();
                this.frameRotation = frame.rotationDegree;
                post(new C26272());
            }
        }
    }

    private void logD(String string) {
        Logging.m2187d(TAG, this.resourceName + string);
    }
}
