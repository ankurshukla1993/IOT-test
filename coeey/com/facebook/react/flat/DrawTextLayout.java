package com.facebook.react.flat;

import android.graphics.Canvas;
import android.text.Layout;
import com.facebook.fbui.textlayoutbuilder.util.LayoutMeasureUtil;

final class DrawTextLayout extends AbstractDrawCommand {
    private Layout mLayout;
    private float mLayoutHeight;
    private float mLayoutWidth;

    DrawTextLayout(Layout layout) {
        setLayout(layout);
    }

    public void setLayout(Layout layout) {
        this.mLayout = layout;
        this.mLayoutWidth = (float) layout.getWidth();
        this.mLayoutHeight = (float) LayoutMeasureUtil.getHeight(layout);
    }

    public Layout getLayout() {
        return this.mLayout;
    }

    public float getLayoutWidth() {
        return this.mLayoutWidth;
    }

    public float getLayoutHeight() {
        return this.mLayoutHeight;
    }

    protected void onDraw(Canvas canvas) {
        float left = getLeft();
        float top = getTop();
        canvas.translate(left, top);
        this.mLayout.draw(canvas);
        canvas.translate(-left, -top);
    }
}
