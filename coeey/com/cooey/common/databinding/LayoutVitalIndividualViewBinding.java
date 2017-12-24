package com.cooey.common.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;

public class LayoutVitalIndividualViewBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @NonNull
    private final ConstraintLayout mboundView0;
    @NonNull
    public final TextView valueText;
    @NonNull
    public final TextView vitalText;

    static {
        sViewsWithIds.put(C0644R.id.value_text, 1);
        sViewsWithIds.put(C0644R.id.vital_text, 2);
    }

    public LayoutVitalIndividualViewBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.valueText = (TextView) bindings[1];
        this.vitalText = (TextView) bindings[2];
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
    public static LayoutVitalIndividualViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutVitalIndividualViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutVitalIndividualViewBinding) DataBindingUtil.inflate(inflater, C0644R.layout.layout_vital_individual_view, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutVitalIndividualViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutVitalIndividualViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.layout_vital_individual_view, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutVitalIndividualViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutVitalIndividualViewBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_vital_individual_view_0".equals(view.getTag())) {
            return new LayoutVitalIndividualViewBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
