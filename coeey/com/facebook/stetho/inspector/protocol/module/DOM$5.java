package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.protocol.module.DOM.SetAttributesAsTextRequest;

class DOM$5 implements Runnable {
    final /* synthetic */ DOM this$0;
    final /* synthetic */ SetAttributesAsTextRequest val$request;

    DOM$5(DOM this$0, SetAttributesAsTextRequest setAttributesAsTextRequest) {
        this.this$0 = this$0;
        this.val$request = setAttributesAsTextRequest;
    }

    public void run() {
        Object element = DOM.access$300(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (element != null) {
            DOM.access$300(this.this$0).setAttributesAsText(element, this.val$request.text);
        }
    }
}
