package com.facebook.react.views.scroll;

class ReactScrollView$1 implements Runnable {
    final /* synthetic */ ReactScrollView this$0;

    ReactScrollView$1(ReactScrollView this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (ReactScrollView.access$000(this.this$0)) {
            ReactScrollView.access$102(this.this$0, false);
            ReactScrollView.access$200(this.this$0);
            ReactScrollViewHelper.emitScrollMomentumEndEvent(this.this$0);
            return;
        }
        ReactScrollView.access$002(this.this$0, true);
        this.this$0.postOnAnimationDelayed(this, 20);
    }
}
