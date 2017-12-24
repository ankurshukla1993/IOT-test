package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.protocol.module.DOM.SetInspectModeEnabledRequest;

class DOM$6 implements Runnable {
    final /* synthetic */ DOM this$0;
    final /* synthetic */ SetInspectModeEnabledRequest val$request;

    DOM$6(DOM this$0, SetInspectModeEnabledRequest setInspectModeEnabledRequest) {
        this.this$0 = this$0;
        this.val$request = setInspectModeEnabledRequest;
    }

    public void run() {
        DOM.access$300(this.this$0).setInspectModeEnabled(this.val$request.enabled);
    }
}
