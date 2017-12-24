package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewParent;
import javax.annotation.Nullable;

abstract class DrawCommandManager {
    @Nullable
    abstract NodeRegion anyNodeRegionWithinBounds(float f, float f2);

    abstract void debugDraw(Canvas canvas);

    abstract void draw(Canvas canvas);

    abstract void getClippingRect(Rect rect);

    abstract SparseArray<View> getDetachedViews();

    abstract void mountDrawCommands(DrawCommand[] drawCommandArr, SparseIntArray sparseIntArray, float[] fArr, float[] fArr2, boolean z);

    abstract void mountNodeRegions(NodeRegion[] nodeRegionArr, float[] fArr, float[] fArr2);

    abstract void mountViews(ViewResolver viewResolver, int[] iArr, int[] iArr2);

    abstract void onClippedViewDropped(View view);

    abstract boolean updateClippingRect();

    @Nullable
    abstract NodeRegion virtualNodeRegionWithinBounds(float f, float f2);

    DrawCommandManager() {
    }

    protected static void ensureViewHasNoParent(View view) {
        ViewParent oldParent = view.getParent();
        if (oldParent != null) {
            throw new RuntimeException("Cannot add view " + view + " to DrawCommandManager while it has a parent " + oldParent);
        }
    }

    static DrawCommandManager getVerticalClippingInstance(FlatViewGroup flatViewGroup, DrawCommand[] drawCommands) {
        return new VerticalDrawCommandManager(flatViewGroup, drawCommands);
    }
}
