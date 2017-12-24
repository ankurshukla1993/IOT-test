package com.facebook.react.modules.core;

class Timing$2 implements Runnable {
    final /* synthetic */ Timing this$0;

    Timing$2(Timing this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        synchronized (Timing.access$1500(this.this$0)) {
            if (Timing.access$1600(this.this$0).size() > 0) {
                Timing.access$2100(this.this$0);
            } else {
                Timing.access$2200(this.this$0);
            }
        }
    }
}
