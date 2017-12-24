package com.facebook.stetho.inspector.elements.android;

import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.Descriptor;

class AndroidDocumentProvider$2 implements Accumulator<Object> {
    final /* synthetic */ AndroidDocumentProvider this$0;
    final /* synthetic */ Accumulator val$accumulator;

    AndroidDocumentProvider$2(AndroidDocumentProvider this$0, Accumulator accumulator) {
        this.this$0 = this$0;
        this.val$accumulator = accumulator;
    }

    public void store(Object element) {
        if (element instanceof Window) {
            this.val$accumulator.store((Window) element);
            return;
        }
        Descriptor elementDescriptor = this.this$0.getDescriptor(element);
        if (elementDescriptor != null) {
            elementDescriptor.getChildren(element, this);
        }
    }
}
