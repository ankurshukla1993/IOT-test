package com.facebook.react.views.picker;

import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;

class ReactPicker$1 implements Runnable {
    final /* synthetic */ ReactPicker this$0;

    ReactPicker$1(ReactPicker this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        this.this$0.measure(MeasureSpec.makeMeasureSpec(this.this$0.getWidth(), Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), Ints.MAX_POWER_OF_TWO));
        this.this$0.layout(this.this$0.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
    }
}
