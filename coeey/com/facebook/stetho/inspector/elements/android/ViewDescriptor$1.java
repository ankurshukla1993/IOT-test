package com.facebook.stetho.inspector.elements.android;

import com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty;
import java.util.Comparator;

class ViewDescriptor$1 implements Comparator<ViewCSSProperty> {
    final /* synthetic */ ViewDescriptor this$0;

    ViewDescriptor$1(ViewDescriptor this$0) {
        this.this$0 = this$0;
    }

    public int compare(ViewCSSProperty lhs, ViewCSSProperty rhs) {
        return lhs.getCSSName().compareTo(rhs.getCSSName());
    }
}
