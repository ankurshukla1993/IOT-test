package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor$Host;
import javax.annotation.Nullable;

final class WindowDescriptor extends AbstractChainedDescriptor<Window> implements HighlightableDescriptor<Window> {
    WindowDescriptor() {
    }

    protected void onGetChildren(Window element, Accumulator<Object> children) {
        View decorView = element.peekDecorView();
        if (decorView != null) {
            children.store(decorView);
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(Window element, Rect bounds) {
        return element.peekDecorView();
    }

    @Nullable
    public Object getElementToHighlightAtPosition(Window element, int x, int y, Rect bounds) {
        Descriptor$Host host = getHost();
        View view = null;
        HighlightableDescriptor descriptor = null;
        if (host instanceof AndroidDescriptorHost) {
            view = element.peekDecorView();
            descriptor = ((AndroidDescriptorHost) host).getHighlightableDescriptor(view);
        }
        if (descriptor == null) {
            return null;
        }
        return descriptor.getElementToHighlightAtPosition(view, x, y, bounds);
    }
}
