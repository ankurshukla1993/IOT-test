package com.facebook.react.flat;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.UIViewOperationQueue.UIOperation;
import javax.annotation.Nullable;

public final class FlatUIViewOperationQueue$ViewManagerCommand implements UIOperation {
    @Nullable
    private final ReadableArray mArgs;
    private final int mCommand;
    private final int mReactTag;
    final /* synthetic */ FlatUIViewOperationQueue this$0;

    public FlatUIViewOperationQueue$ViewManagerCommand(FlatUIViewOperationQueue this$0, int reactTag, @Nullable int command, ReadableArray args) {
        this.this$0 = this$0;
        this.mReactTag = reactTag;
        this.mCommand = command;
        this.mArgs = args;
    }

    public void execute() {
        FlatUIViewOperationQueue.access$100(this.this$0).dispatchCommand(this.mReactTag, this.mCommand, this.mArgs);
    }
}
