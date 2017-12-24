package com.facebook.react.devsupport;

class DevSupportManagerImpl$8 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$8(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        DevSupportManagerImpl.access$000(this.this$0).setReloadOnJSChangeEnabled(!DevSupportManagerImpl.access$000(this.this$0).isReloadOnJSChangeEnabled());
    }
}
