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
import android.widget.LinearLayout;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.maya.MessageViewModel;

public class LayoutMessageBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @Nullable
    private MessageViewModel mMessageViewModel;
    @NonNull
    private final LinearLayout mboundView0;

    public LayoutMessageBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        this.mboundView0 = (LinearLayout) mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds)[0];
        this.mboundView0.setTag(null);
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
        if (33 != variableId) {
            return false;
        }
        setMessageViewModel((MessageViewModel) variable);
        return true;
    }

    public void setMessageViewModel(@Nullable MessageViewModel MessageViewModel) {
        this.mMessageViewModel = MessageViewModel;
    }

    @Nullable
    public MessageViewModel getMessageViewModel() {
        return this.mMessageViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeMessageViewModel((MessageViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeMessageViewModel(MessageViewModel MessageViewModel, int fieldId) {
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
    public static LayoutMessageBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMessageBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutMessageBinding) DataBindingUtil.inflate(inflater, C0644R.layout.layout_message, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutMessageBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMessageBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.layout_message, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutMessageBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutMessageBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_message_0".equals(view.getTag())) {
            return new LayoutMessageBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
