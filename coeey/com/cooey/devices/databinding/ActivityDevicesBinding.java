package com.cooey.devices.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;

public class ActivityDevicesBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final AppBarLayout appBar;
    @NonNull
    public final RecyclerView deviceReclerView;
    @NonNull
    public final FloatingActionButton fab;
    private long mDirtyFlags = -1;
    @NonNull
    private final CoordinatorLayout mboundView0;
    @NonNull
    public final Toolbar toolbar;
    @NonNull
    public final CollapsingToolbarLayout toolbarLayout;

    static {
        sViewsWithIds.put(C0644R.id.app_bar, 1);
        sViewsWithIds.put(C0644R.id.toolbar_layout, 2);
        sViewsWithIds.put(C0644R.id.toolbar, 3);
        sViewsWithIds.put(C0644R.id.deviceReclerView, 4);
        sViewsWithIds.put(C0644R.id.fab, 5);
    }

    public ActivityDevicesBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.appBar = (AppBarLayout) bindings[1];
        this.deviceReclerView = (RecyclerView) bindings[4];
        this.fab = (FloatingActionButton) bindings[5];
        this.mboundView0 = (CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbar = (Toolbar) bindings[3];
        this.toolbarLayout = (CollapsingToolbarLayout) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        return true;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static ActivityDevicesBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDevicesBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityDevicesBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_devices, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityDevicesBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDevicesBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_devices, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityDevicesBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDevicesBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_devices_0".equals(view.getTag())) {
            return new ActivityDevicesBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
