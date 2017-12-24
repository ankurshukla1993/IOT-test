package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.StringUtil;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.Descriptor$Host;
import java.util.List;
import javax.annotation.Nullable;

final class ActivityDescriptor extends AbstractChainedDescriptor<Activity> implements HighlightableDescriptor<Activity> {
    ActivityDescriptor() {
    }

    protected String onGetNodeName(Activity element) {
        return StringUtil.removePrefix(element.getClass().getName(), "android.app.");
    }

    protected void onGetChildren(Activity element, Accumulator<Object> children) {
        getDialogFragments(FragmentCompat.getSupportLibInstance(), element, children);
        getDialogFragments(FragmentCompat.getFrameworkInstance(), element, children);
        Window window = element.getWindow();
        if (window != null) {
            children.store(window);
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(Activity element, Rect bounds) {
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
    public Object getElementToHighlightAtPosition(Activity element, int x, int y, Rect bounds) {
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

    private static void getDialogFragments(@Nullable FragmentCompat compat, Activity activity, Accumulator<Object> accumulator) {
        if (compat != null && compat.getFragmentActivityClass().isInstance(activity)) {
            Object fragmentManager = compat.forFragmentActivity().getFragmentManager(activity);
            if (fragmentManager != null) {
                List<Object> addedFragments = compat.forFragmentManager().getAddedFragments(fragmentManager);
                if (addedFragments != null) {
                    int N = addedFragments.size();
                    for (int i = 0; i < N; i++) {
                        Object fragment = addedFragments.get(i);
                        if (compat.getDialogFragmentClass().isInstance(fragment)) {
                            accumulator.store(fragment);
                        }
                    }
                }
            }
        }
    }
}
