package com.facebook.react.flat;

class NodeRegion {
    static final NodeRegion EMPTY = new NodeRegion(0.0f, 0.0f, 0.0f, 0.0f, -1, false);
    static final NodeRegion[] EMPTY_ARRAY = new NodeRegion[0];
    private final float mBottom;
    final boolean mIsVirtual;
    private final float mLeft;
    private final float mRight;
    final int mTag;
    private final float mTop;

    NodeRegion(float left, float top, float right, float bottom, int tag, boolean isVirtual) {
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
        this.mTag = tag;
        this.mIsVirtual = isVirtual;
    }

    final boolean matches(float left, float top, float right, float bottom, boolean isVirtual) {
        return left == this.mLeft && top == this.mTop && right == this.mRight && bottom == this.mBottom && isVirtual == this.mIsVirtual;
    }

    final float getLeft() {
        return this.mLeft;
    }

    final float getTop() {
        return this.mTop;
    }

    final float getRight() {
        return this.mRight;
    }

    final float getBottom() {
        return this.mBottom;
    }

    float getTouchableLeft() {
        return getLeft();
    }

    float getTouchableTop() {
        return getTop();
    }

    float getTouchableRight() {
        return getRight();
    }

    float getTouchableBottom() {
        return getBottom();
    }

    boolean withinBounds(float touchX, float touchY) {
        return this.mLeft <= touchX && touchX < this.mRight && this.mTop <= touchY && touchY < this.mBottom;
    }

    int getReactTag(float touchX, float touchY) {
        return this.mTag;
    }

    boolean matchesTag(int tag) {
        return this.mTag == tag;
    }
}
