package com.oney.WebRTCModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.webrtc.EglBase;
import org.webrtc.MediaStream;
import org.webrtc.RendererCommon;
import org.webrtc.RendererCommon.RendererEvents;
import org.webrtc.RendererCommon.ScalingType;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoTrack;

public class WebRTCView extends ViewGroup {
    private static final ScalingType DEFAULT_SCALING_TYPE = ScalingType.SCALE_ASPECT_FIT;
    private static final Method IS_IN_LAYOUT;
    private static final String TAG = WebRTCModule.TAG;
    private int frameHeight;
    private int frameRotation;
    private int frameWidth;
    private final Object layoutSyncRoot = new Object();
    private boolean mirror;
    private final RendererEvents rendererEvents = new C23041();
    private final Runnable requestSurfaceViewRendererLayoutRunnable = new C23052();
    private ScalingType scalingType;
    private final SurfaceViewRenderer surfaceViewRenderer;
    private VideoRenderer videoRenderer;
    private VideoTrack videoTrack;

    class C23041 implements RendererEvents {
        C23041() {
        }

        public void onFirstFrameRendered() {
        }

        public void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {
            WebRTCView.this.onFrameResolutionChanged(videoWidth, videoHeight, rotation);
        }
    }

    class C23052 implements Runnable {
        C23052() {
        }

        public void run() {
            WebRTCView.this.requestSurfaceViewRendererLayout();
        }
    }

    static {
        Method isInLayout = null;
        try {
            Method m = WebRTCView.class.getMethod("isInLayout", new Class[0]);
            if (Boolean.TYPE.isAssignableFrom(m.getReturnType())) {
                isInLayout = m;
            }
        } catch (NoSuchMethodException e) {
        }
        IS_IN_LAYOUT = isInLayout;
    }

    public WebRTCView(Context context) {
        super(context);
        this.surfaceViewRenderer = new SurfaceViewRenderer(context);
        addView(this.surfaceViewRenderer);
        setMirror(false);
        setScalingType(DEFAULT_SCALING_TYPE);
    }

    private final SurfaceViewRenderer getSurfaceViewRenderer() {
        return this.surfaceViewRenderer;
    }

    private boolean invokeIsInLayout() {
        Method m = IS_IN_LAYOUT;
        boolean b = false;
        if (m != null) {
            try {
                b = ((Boolean) m.invoke(this, new Object[0])).booleanValue();
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e2) {
            }
        }
        return b;
    }

    protected void onAttachedToWindow() {
        try {
            tryAddRendererToVideoTrack();
        } finally {
            super.onAttachedToWindow();
        }
    }

    protected void onDetachedFromWindow() {
        try {
            removeRendererFromVideoTrack();
        } finally {
            super.onDetachedFromWindow();
        }
    }

    private void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {
        boolean changed = false;
        synchronized (this.layoutSyncRoot) {
            if (this.frameHeight != videoHeight) {
                this.frameHeight = videoHeight;
                changed = true;
            }
            if (this.frameRotation != rotation) {
                this.frameRotation = rotation;
                changed = true;
            }
            if (this.frameWidth != videoWidth) {
                this.frameWidth = videoWidth;
                changed = true;
            }
        }
        if (changed) {
            post(this.requestSurfaceViewRendererLayoutRunnable);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = b - t;
        int width = r - l;
        if (height != 0 && width != 0) {
            int frameHeight;
            int frameRotation;
            int frameWidth;
            ScalingType scalingType;
            synchronized (this.layoutSyncRoot) {
                frameHeight = this.frameHeight;
                frameRotation = this.frameRotation;
                frameWidth = this.frameWidth;
                scalingType = this.scalingType;
            }
            SurfaceViewRenderer surfaceViewRenderer = getSurfaceViewRenderer();
            switch (scalingType) {
                case SCALE_ASPECT_FILL:
                    r = width;
                    l = 0;
                    b = height;
                    t = 0;
                    break;
                default:
                    if (frameHeight != 0 && frameWidth != 0) {
                        float frameAspectRatio;
                        if (frameRotation % 180 == 0) {
                            frameAspectRatio = ((float) frameWidth) / ((float) frameHeight);
                        } else {
                            frameAspectRatio = ((float) frameHeight) / ((float) frameWidth);
                        }
                        Point frameDisplaySize = RendererCommon.getDisplaySize(scalingType, frameAspectRatio, width, height);
                        l = (width - frameDisplaySize.x) / 2;
                        t = (height - frameDisplaySize.y) / 2;
                        r = l + frameDisplaySize.x;
                        b = t + frameDisplaySize.y;
                        break;
                    }
                    b = 0;
                    r = 0;
                    t = 0;
                    l = 0;
                    break;
                    break;
            }
        }
        b = 0;
        r = 0;
        t = 0;
        l = 0;
        this.surfaceViewRenderer.layout(l, t, r, b);
    }

    private void removeRendererFromVideoTrack() {
        if (this.videoRenderer != null) {
            this.videoTrack.removeRenderer(this.videoRenderer);
            this.videoRenderer.dispose();
            this.videoRenderer = null;
            getSurfaceViewRenderer().release();
            synchronized (this.layoutSyncRoot) {
                this.frameHeight = 0;
                this.frameRotation = 0;
                this.frameWidth = 0;
            }
            requestSurfaceViewRendererLayout();
        }
    }

    @SuppressLint({"WrongCall"})
    private void requestSurfaceViewRendererLayout() {
        getSurfaceViewRenderer().requestLayout();
        if (!invokeIsInLayout()) {
            onLayout(false, getLeft(), getTop(), getRight(), getBottom());
        }
    }

    public void setMirror(boolean mirror) {
        if (this.mirror != mirror) {
            this.mirror = mirror;
            getSurfaceViewRenderer().setMirror(mirror);
            requestSurfaceViewRendererLayout();
        }
    }

    public void setObjectFit(String objectFit) {
        setScalingType("cover".equals(objectFit) ? ScalingType.SCALE_ASPECT_FILL : ScalingType.SCALE_ASPECT_FIT);
    }

    private void setScalingType(ScalingType scalingType) {
        synchronized (this.layoutSyncRoot) {
            if (this.scalingType == scalingType) {
                return;
            }
            this.scalingType = scalingType;
            getSurfaceViewRenderer().setScalingType(scalingType);
            requestSurfaceViewRendererLayout();
        }
    }

    public void setStream(MediaStream mediaStream) {
        VideoTrack videoTrack;
        if (mediaStream == null) {
            videoTrack = null;
        } else {
            List<VideoTrack> videoTracks = mediaStream.videoTracks;
            videoTrack = videoTracks.isEmpty() ? null : (VideoTrack) videoTracks.get(0);
        }
        setVideoTrack(videoTrack);
    }

    private void setVideoTrack(VideoTrack videoTrack) {
        VideoTrack oldValue = this.videoTrack;
        if (oldValue != videoTrack) {
            if (oldValue != null) {
                removeRendererFromVideoTrack();
            }
            this.videoTrack = videoTrack;
            if (videoTrack != null) {
                tryAddRendererToVideoTrack();
            }
        }
    }

    public void setZOrder(int zOrder) {
        SurfaceViewRenderer surfaceViewRenderer = getSurfaceViewRenderer();
        switch (zOrder) {
            case 0:
                surfaceViewRenderer.setZOrderMediaOverlay(false);
                return;
            case 1:
                surfaceViewRenderer.setZOrderMediaOverlay(true);
                return;
            case 2:
                surfaceViewRenderer.setZOrderOnTop(true);
                return;
            default:
                return;
        }
    }

    private void tryAddRendererToVideoTrack() {
        if (this.videoRenderer == null && this.videoTrack != null && ViewCompat.isAttachedToWindow(this)) {
            EglBase.Context sharedContext = EglUtils.getRootEglBaseContext();
            if (sharedContext == null) {
                Log.e(TAG, "Failed to render a VideoTrack!");
                return;
            }
            SurfaceViewRenderer surfaceViewRenderer = getSurfaceViewRenderer();
            surfaceViewRenderer.init(sharedContext, this.rendererEvents);
            this.videoRenderer = new VideoRenderer(surfaceViewRenderer);
            this.videoTrack.addRenderer(this.videoRenderer);
        }
    }
}
