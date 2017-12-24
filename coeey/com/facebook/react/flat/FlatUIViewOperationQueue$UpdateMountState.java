package com.facebook.react.flat;

import com.facebook.react.uimanager.UIViewOperationQueue.UIOperation;
import javax.annotation.Nullable;

final class FlatUIViewOperationQueue$UpdateMountState implements UIOperation {
    @Nullable
    private final AttachDetachListener[] mAttachDetachListeners;
    @Nullable
    private final DrawCommand[] mDrawCommands;
    @Nullable
    private final NodeRegion[] mNodeRegions;
    private final int mReactTag;
    final /* synthetic */ FlatUIViewOperationQueue this$0;

    private FlatUIViewOperationQueue$UpdateMountState(FlatUIViewOperationQueue flatUIViewOperationQueue, @Nullable int reactTag, @Nullable DrawCommand[] drawCommands, @Nullable AttachDetachListener[] listeners, NodeRegion[] nodeRegions) {
        this.this$0 = flatUIViewOperationQueue;
        this.mReactTag = reactTag;
        this.mDrawCommands = drawCommands;
        this.mAttachDetachListeners = listeners;
        this.mNodeRegions = nodeRegions;
    }

    public void execute() {
        FlatUIViewOperationQueue.access$100(this.this$0).updateMountState(this.mReactTag, this.mDrawCommands, this.mAttachDetachListeners, this.mNodeRegions);
    }
}
