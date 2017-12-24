package com.facebook.react.views.scroll;

class ReactHorizontalScrollView$1 implements Runnable {
    private boolean mSnappingToPage = false;
    final /* synthetic */ ReactHorizontalScrollView this$0;

    ReactHorizontalScrollView$1(ReactHorizontalScrollView this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (ReactHorizontalScrollView.access$000(this.this$0)) {
            ReactHorizontalScrollView.access$002(this.this$0, false);
            this.this$0.postOnAnimationDelayed(this, 20);
            return;
        }
        boolean doneWithAllScrolling = true;
        if (ReactHorizontalScrollView.access$100(this.this$0) && !this.mSnappingToPage) {
            this.mSnappingToPage = true;
            ReactHorizontalScrollView.access$200(this.this$0, 0);
            doneWithAllScrolling = false;
        }
        if (doneWithAllScrolling) {
            if (ReactHorizontalScrollView.access$300(this.this$0)) {
                ReactScrollViewHelper.emitScrollMomentumEndEvent(this.this$0);
            }
            ReactHorizontalScrollView.access$402(this.this$0, null);
            ReactHorizontalScrollView.access$500(this.this$0);
            return;
        }
        this.this$0.postOnAnimationDelayed(this, 20);
    }
}
