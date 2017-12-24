package com.ocetnik.timer;

class BackgroundTimerModule$1 implements Runnable {
    final /* synthetic */ BackgroundTimerModule this$0;
    final /* synthetic */ int val$delay;

    BackgroundTimerModule$1(BackgroundTimerModule this$0, int i) {
        this.this$0 = this$0;
        this.val$delay = i;
    }

    public void run() {
        BackgroundTimerModule.access$100(this.this$0, BackgroundTimerModule.access$000(this.this$0), "backgroundTimer");
        BackgroundTimerModule.access$300(this.this$0).postDelayed(BackgroundTimerModule.access$200(this.this$0), (long) this.val$delay);
    }
}
