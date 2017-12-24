package com.facebook.react.flat;

import android.util.SparseIntArray;
import com.facebook.react.uimanager.UIViewOperationQueue.UIOperation;
import javax.annotation.Nullable;

final class FlatUIViewOperationQueue$UpdateClippingMountState implements UIOperation {
    @Nullable
    private final AttachDetachListener[] mAttachDetachListeners;
    private final float[] mCommandMaxBot;
    private final float[] mCommandMinTop;
    @Nullable
    private final DrawCommand[] mDrawCommands;
    private final SparseIntArray mDrawViewIndexMap;
    @Nullable
    private final NodeRegion[] mNodeRegions;
    private final int mReactTag;
    private final float[] mRegionMaxBot;
    private final float[] mRegionMinTop;
    private final boolean mWillMountViews;
    final /* synthetic */ FlatUIViewOperationQueue this$0;

    private FlatUIViewOperationQueue$UpdateClippingMountState(FlatUIViewOperationQueue flatUIViewOperationQueue, @Nullable int reactTag, DrawCommand[] drawCommands, SparseIntArray drawViewIndexMap, float[] commandMaxBot, @Nullable float[] commandMinTop, @Nullable AttachDetachListener[] listeners, NodeRegion[] nodeRegions, float[] regionMaxBot, float[] regionMinTop, boolean willMountViews) {
        this.this$0 = flatUIViewOperationQueue;
        this.mReactTag = reactTag;
        this.mDrawCommands = drawCommands;
        this.mDrawViewIndexMap = drawViewIndexMap;
        this.mCommandMaxBot = commandMaxBot;
        this.mCommandMinTop = commandMinTop;
        this.mAttachDetachListeners = listeners;
        this.mNodeRegions = nodeRegions;
        this.mRegionMaxBot = regionMaxBot;
        this.mRegionMinTop = regionMinTop;
        this.mWillMountViews = willMountViews;
    }

    public void execute() {
        FlatUIViewOperationQueue.access$100(this.this$0).updateClippingMountState(this.mReactTag, this.mDrawCommands, this.mDrawViewIndexMap, this.mCommandMaxBot, this.mCommandMinTop, this.mAttachDetachListeners, this.mNodeRegions, this.mRegionMaxBot, this.mRegionMinTop, this.mWillMountViews);
    }
}
