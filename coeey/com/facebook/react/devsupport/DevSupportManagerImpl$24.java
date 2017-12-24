package com.facebook.react.devsupport;

class DevSupportManagerImpl$24 implements DevServerHelper$OnServerContentChangeListener {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$24(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onServerContentChanged() {
        this.this$0.handleReloadJS();
    }
}
