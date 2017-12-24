package org.webrtc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

class EglBase10$1FakeSurfaceHolder implements SurfaceHolder {
    private final Surface surface;
    final /* synthetic */ EglBase10 this$0;

    EglBase10$1FakeSurfaceHolder(EglBase10 this$0, Surface surface) {
        this.this$0 = this$0;
        this.surface = surface;
    }

    public void addCallback(Callback callback) {
    }

    public void removeCallback(Callback callback) {
    }

    public boolean isCreating() {
        return false;
    }

    @Deprecated
    public void setType(int i) {
    }

    public void setFixedSize(int i, int i2) {
    }

    public void setSizeFromLayout() {
    }

    public void setFormat(int i) {
    }

    public void setKeepScreenOn(boolean b) {
    }

    public Canvas lockCanvas() {
        return null;
    }

    public Canvas lockCanvas(Rect rect) {
        return null;
    }

    public void unlockCanvasAndPost(Canvas canvas) {
    }

    public Rect getSurfaceFrame() {
        return null;
    }

    public Surface getSurface() {
        return this.surface;
    }
}
