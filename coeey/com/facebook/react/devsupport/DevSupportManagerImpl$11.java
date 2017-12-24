package com.facebook.react.devsupport;

class DevSupportManagerImpl$11 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$11(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        DevSupportManagerImpl.access$000(this.this$0).setFpsDebugEnabled(!DevSupportManagerImpl.access$000(this.this$0).isFpsDebugEnabled());
    }
}
