package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.views.art.ARTVirtualNode;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import javax.annotation.Nullable;

class FlatARTSurfaceViewShadowNode extends FlatShadowNode implements AndroidView, SurfaceTextureListener {
    private boolean mPaddingChanged = false;
    @Nullable
    private Surface mSurface;

    FlatARTSurfaceViewShadowNode() {
        forceMountToView();
        forceMountChildrenToView();
    }

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return true;
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uiUpdater) {
        super.onCollectExtraUpdates(uiUpdater);
        drawOutput();
        uiUpdater.enqueueUpdateExtraData(getReactTag(), this);
    }

    private void drawOutput() {
        RuntimeException e;
        if (this.mSurface == null || !this.mSurface.isValid()) {
            markChildrenUpdatesSeen(this);
            return;
        }
        try {
            Canvas canvas = this.mSurface.lockCanvas(null);
            canvas.drawColor(0, Mode.CLEAR);
            Paint paint = new Paint();
            for (int i = 0; i < getChildCount(); i++) {
                ARTVirtualNode child = (ARTVirtualNode) getChildAt(i);
                child.draw(canvas, paint, FlexItem.FLEX_SHRINK_DEFAULT);
                child.markUpdateSeen();
            }
            if (this.mSurface != null) {
                this.mSurface.unlockCanvasAndPost(canvas);
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            Log.e(ReactConstants.TAG, e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        } catch (IllegalStateException e3) {
            e = e3;
            Log.e(ReactConstants.TAG, e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        }
    }

    private void markChildrenUpdatesSeen(ReactShadowNode shadowNode) {
        for (int i = 0; i < shadowNode.getChildCount(); i++) {
            ReactShadowNode child = shadowNode.getChildAt(i);
            child.markUpdateSeen();
            markChildrenUpdatesSeen(child);
        }
    }

    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public boolean isPaddingChanged() {
        return this.mPaddingChanged;
    }

    public void resetPaddingChanged() {
        this.mPaddingChanged = false;
    }

    public void setPadding(int spacingType, float padding) {
        YogaValue current = getStylePadding(spacingType);
        if (current.unit != YogaUnit.PIXEL || current.value != padding) {
            super.setPadding(spacingType, padding);
            this.mPaddingChanged = true;
            markUpdated();
        }
    }

    public void setPaddingPercent(int spacingType, float percent) {
        YogaValue current = getStylePadding(spacingType);
        if (current.unit != YogaUnit.PERCENT || current.value != percent) {
            super.setPadding(spacingType, percent);
            this.mPaddingChanged = true;
            markUpdated();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.mSurface = new Surface(surface);
        drawOutput();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        surface.release();
        this.mSurface = null;
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
