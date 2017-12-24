package com.facebook.react.devsupport;

class DevSupportManagerImpl$10 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$10(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        DevSupportManagerImpl.access$000(this.this$0).setElementInspectorEnabled(!DevSupportManagerImpl.access$000(this.this$0).isElementInspectorEnabled());
        DevSupportManagerImpl.access$700(this.this$0).toggleElementInspector();
    }
}
