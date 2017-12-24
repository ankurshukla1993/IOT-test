package com.facebook.stetho.inspector.screencast;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Base64OutputStream;
import android.view.View;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Page.ScreencastFrameEvent;
import com.facebook.stetho.inspector.protocol.module.Page.ScreencastFrameEventMetadata;
import com.facebook.stetho.inspector.protocol.module.Page.StartScreencastRequest;
import java.io.ByteArrayOutputStream;

public final class ScreencastDispatcher {
    private static final long FRAME_DELAY = 200;
    private final ActivityTracker mActivityTracker = ActivityTracker.get();
    private Handler mBackgroundHandler;
    private Bitmap mBitmap;
    private final BitmapFetchRunnable mBitmapFetchRunnable = new BitmapFetchRunnable();
    private Canvas mCanvas;
    private ScreencastFrameEvent mEvent = new ScreencastFrameEvent();
    private final EventDispatchRunnable mEventDispatchRunnable = new EventDispatchRunnable();
    private HandlerThread mHandlerThread;
    private boolean mIsRunning;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private ScreencastFrameEventMetadata mMetadata = new ScreencastFrameEventMetadata();
    private JsonRpcPeer mPeer;
    private StartScreencastRequest mRequest;
    private ByteArrayOutputStream mStream;
    private final RectF mTempDst = new RectF();
    private final RectF mTempSrc = new RectF();

    private class BitmapFetchRunnable implements Runnable {
        private BitmapFetchRunnable() {
        }

        public void run() {
            updateScreenBitmap();
            ScreencastDispatcher.this.mBackgroundHandler.post(ScreencastDispatcher.this.mEventDispatchRunnable.withEndAction(this));
        }

        private void updateScreenBitmap() {
            if (ScreencastDispatcher.this.mIsRunning) {
                Activity activity = ScreencastDispatcher.this.mActivityTracker.tryGetTopActivity();
                if (activity != null) {
                    View rootView = activity.getWindow().getDecorView();
                    try {
                        if (ScreencastDispatcher.this.mBitmap == null) {
                            int viewWidth = rootView.getWidth();
                            int viewHeight = rootView.getHeight();
                            float scale = Math.min(((float) ScreencastDispatcher.this.mRequest.maxWidth) / ((float) viewWidth), ((float) ScreencastDispatcher.this.mRequest.maxHeight) / ((float) viewHeight));
                            int destWidth = (int) (((float) viewWidth) * scale);
                            int destHeight = (int) (((float) viewHeight) * scale);
                            ScreencastDispatcher.this.mBitmap = Bitmap.createBitmap(destWidth, destHeight, Config.RGB_565);
                            ScreencastDispatcher.this.mCanvas = new Canvas(ScreencastDispatcher.this.mBitmap);
                            Matrix matrix = new Matrix();
                            ScreencastDispatcher.this.mTempSrc.set(0.0f, 0.0f, (float) viewWidth, (float) viewHeight);
                            ScreencastDispatcher.this.mTempDst.set(0.0f, 0.0f, (float) destWidth, (float) destHeight);
                            matrix.setRectToRect(ScreencastDispatcher.this.mTempSrc, ScreencastDispatcher.this.mTempDst, ScaleToFit.CENTER);
                            ScreencastDispatcher.this.mCanvas.setMatrix(matrix);
                        }
                        rootView.draw(ScreencastDispatcher.this.mCanvas);
                    } catch (OutOfMemoryError e) {
                        LogUtil.m201w("Out of memory trying to allocate screencast Bitmap.");
                    }
                }
            }
        }
    }

    private class CancellationRunnable implements Runnable {
        private CancellationRunnable() {
        }

        public void run() {
            ScreencastDispatcher.this.mHandlerThread.interrupt();
            ScreencastDispatcher.this.mMainHandler.removeCallbacks(ScreencastDispatcher.this.mBitmapFetchRunnable);
            ScreencastDispatcher.this.mBackgroundHandler.removeCallbacks(ScreencastDispatcher.this.mEventDispatchRunnable);
            ScreencastDispatcher.this.mIsRunning = false;
            ScreencastDispatcher.this.mHandlerThread = null;
            ScreencastDispatcher.this.mBitmap = null;
            ScreencastDispatcher.this.mCanvas = null;
            ScreencastDispatcher.this.mStream = null;
        }
    }

    private class EventDispatchRunnable implements Runnable {
        private Runnable mEndAction;

        private EventDispatchRunnable() {
        }

        private EventDispatchRunnable withEndAction(Runnable endAction) {
            this.mEndAction = endAction;
            return this;
        }

        public void run() {
            if (ScreencastDispatcher.this.mIsRunning && ScreencastDispatcher.this.mBitmap != null) {
                int width = ScreencastDispatcher.this.mBitmap.getWidth();
                int height = ScreencastDispatcher.this.mBitmap.getHeight();
                ScreencastDispatcher.this.mStream.reset();
                Base64OutputStream base64Stream = new Base64OutputStream(ScreencastDispatcher.this.mStream, 0);
                ScreencastDispatcher.this.mBitmap.compress(CompressFormat.valueOf(ScreencastDispatcher.this.mRequest.format.toUpperCase()), ScreencastDispatcher.this.mRequest.quality, base64Stream);
                ScreencastDispatcher.this.mEvent.data = ScreencastDispatcher.this.mStream.toString();
                ScreencastDispatcher.this.mMetadata.pageScaleFactor = 1;
                ScreencastDispatcher.this.mMetadata.deviceWidth = width;
                ScreencastDispatcher.this.mMetadata.deviceHeight = height;
                ScreencastDispatcher.this.mEvent.metadata = ScreencastDispatcher.this.mMetadata;
                ScreencastDispatcher.this.mPeer.invokeMethod("Page.screencastFrame", ScreencastDispatcher.this.mEvent, null);
                ScreencastDispatcher.this.mMainHandler.postDelayed(this.mEndAction, ScreencastDispatcher.FRAME_DELAY);
            }
        }
    }

    public void startScreencast(JsonRpcPeer peer, StartScreencastRequest request) {
        LogUtil.m185d("Starting screencast");
        this.mRequest = request;
        this.mHandlerThread = new HandlerThread("Screencast Thread");
        this.mHandlerThread.start();
        this.mPeer = peer;
        this.mIsRunning = true;
        this.mStream = new ByteArrayOutputStream();
        this.mBackgroundHandler = new Handler(this.mHandlerThread.getLooper());
        this.mMainHandler.postDelayed(this.mBitmapFetchRunnable, FRAME_DELAY);
    }

    public void stopScreencast() {
        LogUtil.m185d("Stopping screencast");
        this.mBackgroundHandler.post(new CancellationRunnable());
    }
}
