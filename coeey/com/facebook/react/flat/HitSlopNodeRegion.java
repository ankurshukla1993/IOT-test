package com.facebook.react.flat;

import android.graphics.Rect;

final class HitSlopNodeRegion extends NodeRegion {
    private final Rect mHitSlop;

    HitSlopNodeRegion(Rect hitSlop, float left, float top, float right, float bottom, int tag, boolean isVirtual) {
        super(left, top, right, bottom, tag, isVirtual);
        this.mHitSlop = hitSlop;
    }

    float getTouchableLeft() {
        return getLeft() - ((float) this.mHitSlop.left);
    }

    float getTouchableTop() {
        return getTop() - ((float) this.mHitSlop.top);
    }

    float getTouchableRight() {
        return getRight() + ((float) this.mHitSlop.right);
    }

    float getTouchableBottom() {
        return getBottom() + ((float) this.mHitSlop.bottom);
    }

    boolean withinBounds(float touchX, float touchY) {
        return getTouchableLeft() <= touchX && touchX < getTouchableRight() && getTouchableTop() <= touchY && touchY < getTouchableBottom();
    }
}
