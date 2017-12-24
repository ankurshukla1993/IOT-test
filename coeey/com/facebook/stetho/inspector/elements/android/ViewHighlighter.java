package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

abstract class ViewHighlighter {

    private static final class NoopHighlighter extends ViewHighlighter {
        private NoopHighlighter() {
        }

        public void clearHighlight() {
        }

        public void setHighlightedView(View view, @Nullable Rect bounds, int color) {
        }
    }

    @TargetApi(18)
    private static final class OverlayHighlighter extends ViewHighlighter {
        private AtomicReference<Rect> mBoundsToHighlight = new AtomicReference();
        private AtomicInteger mContentColor = new AtomicInteger();
        private final Rect mEmptyRect = new Rect();
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private final ViewHighlightOverlays mHighlightOverlays = ViewHighlightOverlays.newInstance();
        private final Runnable mHighlightViewOnUiThreadRunnable = new C14471();
        private final Rect mHighlightedBounds = new Rect();
        private View mHighlightedView;
        private AtomicReference<View> mViewToHighlight = new AtomicReference();

        class C14471 implements Runnable {
            C14471() {
            }

            public void run() {
                OverlayHighlighter.this.highlightViewOnUiThread();
            }
        }

        public void clearHighlight() {
            setHighlightedViewImpl(null, null, 0);
        }

        public void setHighlightedView(View view, @Nullable Rect bounds, int color) {
            setHighlightedViewImpl((View) Util.throwIfNull(view), bounds, color);
        }

        private void setHighlightedViewImpl(@Nullable View view, @Nullable Rect bounds, int color) {
            this.mHandler.removeCallbacks(this.mHighlightViewOnUiThreadRunnable);
            this.mViewToHighlight.set(view);
            this.mBoundsToHighlight.set(bounds);
            this.mContentColor.set(color);
            this.mHandler.postDelayed(this.mHighlightViewOnUiThreadRunnable, 100);
        }

        private void highlightViewOnUiThread() {
            View viewToHighlight = (View) this.mViewToHighlight.getAndSet(null);
            Rect boundsToHighlight = (Rect) this.mBoundsToHighlight.getAndSet(null);
            if (boundsToHighlight == null) {
                boundsToHighlight = this.mEmptyRect;
            }
            if (viewToHighlight != this.mHighlightedView || !this.mHighlightedBounds.equals(boundsToHighlight)) {
                if (this.mHighlightedView != null) {
                    this.mHighlightOverlays.removeHighlight(this.mHighlightedView);
                }
                if (viewToHighlight != null) {
                    this.mHighlightOverlays.highlightView(viewToHighlight, boundsToHighlight, this.mContentColor.get());
                }
                this.mHighlightedView = viewToHighlight;
                if (boundsToHighlight == null) {
                    this.mHighlightedBounds.setEmpty();
                } else {
                    this.mHighlightedBounds.set(boundsToHighlight);
                }
            }
        }
    }

    public abstract void clearHighlight();

    public abstract void setHighlightedView(View view, @Nullable Rect rect, int i);

    public static ViewHighlighter newInstance() {
        if (VERSION.SDK_INT >= 18) {
            return new OverlayHighlighter();
        }
        LogUtil.m201w("Running on pre-JBMR2: View highlighting is not supported");
        return new NoopHighlighter();
    }

    protected ViewHighlighter() {
    }
}
