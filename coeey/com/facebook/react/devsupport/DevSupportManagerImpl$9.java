package com.facebook.react.devsupport;

class DevSupportManagerImpl$9 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$9(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        DevSupportManagerImpl.access$000(this.this$0).setHotModuleReplacementEnabled(!DevSupportManagerImpl.access$000(this.this$0).isHotModuleReplacementEnabled());
        this.this$0.handleReloadJS();
    }
}
