package com.facebook.react.devsupport;

class DevServerHelper$7 implements Runnable {
    final /* synthetic */ DevServerHelper this$0;

    DevServerHelper$7(DevServerHelper this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (DevServerHelper.access$400(this.this$0) != null) {
            DevServerHelper.access$400(this.this$0).onServerContentChanged();
        }
    }
}
