package com.facebook.react.uimanager;

final class UIViewOperationQueue$UpdatePropertiesOperation extends UIViewOperationQueue$ViewOperation {
    private final ReactStylesDiffMap mProps;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$UpdatePropertiesOperation(UIViewOperationQueue uIViewOperationQueue, int tag, ReactStylesDiffMap props) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mProps = props;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).updateProperties(this.mTag, this.mProps);
    }
}
