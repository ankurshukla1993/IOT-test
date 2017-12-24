package com.facebook.react.flat;

import android.graphics.Canvas;

public abstract class DrawCommand {
    static final DrawCommand[] EMPTY_ARRAY = new DrawCommand[0];

    abstract void debugDraw(FlatViewGroup flatViewGroup, Canvas canvas);

    abstract void draw(FlatViewGroup flatViewGroup, Canvas canvas);

    abstract float getBottom();

    abstract float getLeft();

    abstract float getRight();

    abstract float getTop();
}
