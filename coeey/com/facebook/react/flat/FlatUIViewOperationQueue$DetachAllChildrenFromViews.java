package com.facebook.react.flat;

import com.facebook.react.uimanager.UIViewOperationQueue.UIOperation;
import javax.annotation.Nullable;

public final class FlatUIViewOperationQueue$DetachAllChildrenFromViews implements UIOperation {
    @Nullable
    private int[] mViewsToDetachAllChildrenFrom;
    final /* synthetic */ FlatUIViewOperationQueue this$0;

    public FlatUIViewOperationQueue$DetachAllChildrenFromViews(FlatUIViewOperationQueue this$0) {
        this.this$0 = this$0;
    }

    public void setViewsToDetachAllChildrenFrom(int[] viewsToDetachAllChildrenFrom) {
        this.mViewsToDetachAllChildrenFrom = viewsToDetachAllChildrenFrom;
    }

    public void execute() {
        FlatUIViewOperationQueue.access$100(this.this$0).detachAllChildrenFromViews(this.mViewsToDetachAllChildrenFrom);
    }
}
