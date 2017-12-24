package com.facebook.react.devsupport;

class DevSupportManagerImpl$6 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$6(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        DevSupportManagerImpl.access$000(this.this$0).setRemoteJSDebugEnabled(!DevSupportManagerImpl.access$000(this.this$0).isRemoteJSDebugEnabled());
        this.this$0.handleReloadJS();
    }
}
