package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.android.FragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.Descriptor$Host;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import javax.annotation.Nullable;

final class FragmentDescriptor extends AbstractChainedDescriptor<Object> implements HighlightableDescriptor<Object> {
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final String TAG_ATTRIBUTE_NAME = "tag";
    private final FragmentAccessor mAccessor;

    public static DescriptorMap register(DescriptorMap map) {
        maybeRegister(map, FragmentCompat.getSupportLibInstance());
        maybeRegister(map, FragmentCompat.getFrameworkInstance());
        return map;
    }

    private static void maybeRegister(DescriptorMap map, @Nullable FragmentCompat compat) {
        if (compat != null) {
            LogUtil.d("Adding support for %s", new Object[]{compat.getFragmentClass().getName()});
            map.registerDescriptor(fragmentClass, new FragmentDescriptor(compat));
        }
    }

    private FragmentDescriptor(FragmentCompat compat) {
        this.mAccessor = compat.forFragment();
    }

    protected void onGetAttributes(Object element, AttributeAccumulator attributes) {
        int id = this.mAccessor.getId(element);
        if (id != 0) {
            attributes.store("id", ResourcesUtil.getIdStringQuietly(element, this.mAccessor.getResources(element), id));
        }
        String tag = this.mAccessor.getTag(element);
        if (tag != null && tag.length() > 0) {
            attributes.store("tag", tag);
        }
    }

    protected void onGetChildren(Object element, Accumulator<Object> children) {
        View view = this.mAccessor.getView(element);
        if (view != null) {
            children.store(view);
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(Object element, Rect bounds) {
        return this.mAccessor.getView(element);
    }

    @Nullable
    public Object getElementToHighlightAtPosition(Object element, int x, int y, Rect bounds) {
        Descriptor$Host host = getHost();
        View view = null;
        HighlightableDescriptor descriptor = null;
        if (host instanceof AndroidDescriptorHost) {
            view = this.mAccessor.getView(element);
            descriptor = ((AndroidDescriptorHost) host).getHighlightableDescriptor(view);
        }
        if (descriptor == null) {
            return null;
        }
        return descriptor.getElementToHighlightAtPosition(view, x, y, bounds);
    }
}
