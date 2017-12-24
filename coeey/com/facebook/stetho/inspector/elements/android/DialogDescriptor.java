package com.facebook.stetho.inspector.elements.android;

import android.app.Dialog;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor$Host;
import javax.annotation.Nullable;

final class DialogDescriptor extends AbstractChainedDescriptor<Dialog> implements HighlightableDescriptor<Dialog> {
    DialogDescriptor() {
    }

    protected void onGetChildren(Dialog element, Accumulator<Object> children) {
        Window window = element.getWindow();
        if (window != null) {
            children.store(window);
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(Dialog element, Rect bounds) {
        Descriptor$Host host = getHost();
        Window window = null;
        HighlightableDescriptor descriptor = null;
        if (host instanceof AndroidDescriptorHost) {
            window = element.getWindow();
            descriptor = ((AndroidDescriptorHost) host).getHighlightableDescriptor(window);
        }
        if (descriptor == null) {
            return null;
        }
        return descriptor.getViewAndBoundsForHighlighting(window, bounds);
    }

    @Nullable
    public Object getElementToHighlightAtPosition(Dialog element, int x, int y, Rect bounds) {
        Descriptor$Host host = getHost();
        Window window = null;
        HighlightableDescriptor descriptor = null;
        if (host instanceof AndroidDescriptorHost) {
            window = element.getWindow();
            descriptor = ((AndroidDescriptorHost) host).getHighlightableDescriptor(window);
        }
        if (descriptor == null) {
            return null;
        }
        return descriptor.getElementToHighlightAtPosition(window, x, y, bounds);
    }
}
