package com.facebook.react.flat;

import android.util.SparseIntArray;
import java.util.Arrays;

final class HorizontalDrawCommandManager extends ClippingDrawCommandManager {
    HorizontalDrawCommandManager(FlatViewGroup flatViewGroup, DrawCommand[] drawCommands) {
        super(flatViewGroup, drawCommands);
    }

    int commandStartIndex() {
        int start = Arrays.binarySearch(this.mCommandMaxBottom, (float) this.mClippingRect.left);
        return start < 0 ? start ^ -1 : start;
    }

    int commandStopIndex(int start) {
        int stop = Arrays.binarySearch(this.mCommandMinTop, start, this.mCommandMinTop.length, (float) this.mClippingRect.right);
        return stop < 0 ? stop ^ -1 : stop;
    }

    int regionStopIndex(float touchX, float touchY) {
        int stop = Arrays.binarySearch(this.mRegionMinTop, 1.0E-4f + touchX);
        return stop < 0 ? stop ^ -1 : stop;
    }

    boolean regionAboveTouch(int index, float touchX, float touchY) {
        return this.mRegionMaxBottom[index] < touchX;
    }

    public static void fillMaxMinArrays(NodeRegion[] regions, float[] maxRight, float[] minLeft) {
        int i;
        float last = 0.0f;
        for (i = 0; i < regions.length; i++) {
            last = Math.max(last, regions[i].getTouchableRight());
            maxRight[i] = last;
        }
        for (i = regions.length - 1; i >= 0; i--) {
            last = Math.min(last, regions[i].getTouchableLeft());
            minLeft[i] = last;
        }
    }

    public static void fillMaxMinArrays(DrawCommand[] commands, float[] maxRight, float[] minLeft, SparseIntArray drawViewIndexMap) {
        int i;
        float last = 0.0f;
        for (i = 0; i < commands.length; i++) {
            if (commands[i] instanceof DrawView) {
                DrawView drawView = commands[i];
                drawViewIndexMap.append(drawView.reactTag, i);
                last = Math.max(last, drawView.mLogicalRight);
            } else {
                last = Math.max(last, commands[i].getRight());
            }
            maxRight[i] = last;
        }
        for (i = commands.length - 1; i >= 0; i--) {
            if (commands[i] instanceof DrawView) {
                last = Math.min(last, ((DrawView) commands[i]).mLogicalLeft);
            } else {
                last = Math.min(last, commands[i].getLeft());
            }
            minLeft[i] = last;
        }
    }
}
