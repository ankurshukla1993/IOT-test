package com.cooey.common.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.viewmodels.MayaInputViewModel;

public class LayoutMayaInputBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final FrameLayout content;
    private long mDirtyFlags = -1;
    @Nullable
    private MayaInputViewModel mMayaInputViewModel;

    public LayoutMayaInputBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        this.content = (FrameLayout) mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds)[0];
        this.content.setTag(null);
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
        if (25 != variableId) {
            return false;
        }
        setMayaInputViewModel((MayaInputViewModel) variable);
        return true;
    }

    public void setMayaInputViewModel(@Nullable MayaInputViewModel MayaInputViewModel) {
        this.mMayaInputViewModel = MayaInputViewModel;
    }

    @Nullable
    public MayaInputViewModel getMayaInputViewModel() {
        return this.mMayaInputViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMayaInputViewModel((MayaInputViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMayaInputViewModel(MayaInputViewModel MayaInputViewModel, int fieldId) {
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
    }

    @NonNull
    public static LayoutMayaInputBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMayaInputBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutMayaInputBinding) DataBindingUtil.inflate(inflater, C0644R.layout.layout_maya_input, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutMayaInputBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMayaInputBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.layout_maya_input, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutMayaInputBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMayaInputBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_maya_input_0".equals(view.getTag())) {
            return new LayoutMayaInputBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
