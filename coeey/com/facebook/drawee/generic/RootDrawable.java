package com.facebook.drawee.generic;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.VisibilityAwareDrawable;
import com.facebook.drawee.drawable.VisibilityCallback;
import javax.annotation.Nullable;

public class RootDrawable extends ForwardingDrawable implements VisibilityAwareDrawable {
    @VisibleForTesting
    @Nullable
    Drawable mControllerOverlay = null;
    @Nullable
    private VisibilityCallback mVisibilityCallback;

    public RootDrawable(Drawable drawable) {
        super(drawable);
    }

    public int getIntrinsicWidth() {
        return -1;
    }

    public int getIntrinsicHeight() {
        return -1;
    }

    public void setVisibilityCallback(@Nullable VisibilityCallback visibilityCallback) {
        this.mVisibilityCallback = visibilityCallback;
    }

    public boolean setVisible(boolean visible, boolean restart) {
        if (this.mVisibilityCallback != null) {
            this.mVisibilityCallback.onVisibilityChange(visible);
        }
        return super.setVisible(visible, restart);
    }

    @SuppressLint({"WrongCall"})
    public void draw(Canvas canvas) {
        if (isVisible()) {
            if (this.mVisibilityCallback != null) {
                this.mVisibilityCallback.onDraw();
            }
            super.draw(canvas);
            if (this.mControllerOverlay != null) {
                this.mControllerOverlay.setBounds(getBounds());
                this.mControllerOverlay.draw(canvas);
            }
        }
    }

    public void setControllerOverlay(@Nullable Drawable controllerOverlay) {
        this.mControllerOverlay = controllerOverlay;
        invalidateSelf();
    }
}
