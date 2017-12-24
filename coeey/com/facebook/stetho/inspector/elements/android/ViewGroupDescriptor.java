package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.android.FragmentCompatUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

final class ViewGroupDescriptor extends AbstractChainedDescriptor<ViewGroup> implements HighlightableDescriptor<ViewGroup> {
    private final Map<View, Object> mViewToElementMap = Collections.synchronizedMap(new WeakHashMap());

    protected void onGetChildren(ViewGroup element, Accumulator<Object> children) {
        int N = element.getChildCount();
        for (int i = 0; i < N; i++) {
            View childView = element.getChildAt(i);
            if (isChildVisible(childView)) {
                children.store(getElementForView(element, childView));
            }
        }
    }

    private boolean isChildVisible(View child) {
        return !(child instanceof DocumentHiddenView);
    }

    private Object getElementForView(ViewGroup parentView, View childView) {
        Object value = this.mViewToElementMap.get(childView);
        if (value != null) {
            Object element = getElement(childView, value);
            if (element != null && childView.getParent() == parentView) {
                return element;
            }
            this.mViewToElementMap.remove(childView);
        }
        Object fragment = FragmentCompatUtil.findFragmentForView(childView);
        if (fragment == null || FragmentCompatUtil.isDialogFragment(fragment)) {
            this.mViewToElementMap.put(childView, this);
            return childView;
        }
        this.mViewToElementMap.put(childView, new WeakReference(fragment));
        return fragment;
    }

    private Object getElement(View childView, Object value) {
        return value == this ? childView : ((WeakReference) value).get();
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(ViewGroup element, Rect bounds) {
        return element;
    }

    @Nullable
    public Object getElementToHighlightAtPosition(ViewGroup element, int x, int y, Rect bounds) {
        View hitChild = null;
        for (int i = element.getChildCount() - 1; i >= 0; i--) {
            View childView = element.getChildAt(i);
            if (isChildVisible(childView) && childView.getVisibility() == 0) {
                childView.getHitRect(bounds);
                if (bounds.contains(x, y)) {
                    hitChild = childView;
                    break;
                }
            }
        }
        if (hitChild != null) {
            return hitChild;
        }
        bounds.set(0, 0, element.getWidth(), element.getHeight());
        return element;
    }
}
