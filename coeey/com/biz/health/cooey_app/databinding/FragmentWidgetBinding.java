package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.WidgetRecyclerAdapter;
import com.biz.health.cooey_app.dashboard.widgets.WidgetsViewModel;
import com.cooey.common.BindingAdapters;

public class FragmentWidgetBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @Nullable
    private WidgetsViewModel mWidgetsViewModel;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final RecyclerView mboundView1;

    public FragmentWidgetBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (RecyclerView) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (56 != variableId) {
            return false;
        }
        setWidgetsViewModel((WidgetsViewModel) variable);
        return true;
    }

    public void setWidgetsViewModel(@Nullable WidgetsViewModel WidgetsViewModel) {
        updateRegistration(0, WidgetsViewModel);
        this.mWidgetsViewModel = WidgetsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(56);
        super.requestRebind();
    }

    @Nullable
    public WidgetsViewModel getWidgetsViewModel() {
        return this.mWidgetsViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeWidgetsViewModel((WidgetsViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeWidgetsViewModel(WidgetsViewModel WidgetsViewModel, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        WidgetsViewModel widgetsViewModel = this.mWidgetsViewModel;
        WidgetRecyclerAdapter widgetsViewModelWidgetRecyclerAdapter = null;
        if (!((dirtyFlags & 3) == 0 || widgetsViewModel == null)) {
            widgetsViewModelWidgetRecyclerAdapter = widgetsViewModel.getWidgetRecyclerAdapter();
        }
        if ((dirtyFlags & 3) != 0) {
            BindingAdapters.onSetRecyclerViewAdapter(this.mboundView1, widgetsViewModelWidgetRecyclerAdapter);
        }
        if ((2 & dirtyFlags) != 0) {
            BindingAdapters.onSetLayoutManager(this.mboundView1, "LINEAR");
        }
    }

    @NonNull
    public static FragmentWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (FragmentWidgetBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_widget, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static FragmentWidgetBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWidgetBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.fragment_widget, null, false), bindingComponent);
    }

    @NonNull
    public static FragmentWidgetBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentWidgetBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/fragment_widget_0".equals(view.getTag())) {
            return new FragmentWidgetBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
