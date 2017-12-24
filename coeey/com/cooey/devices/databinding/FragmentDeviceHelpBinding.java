package com.cooey.devices.databinding;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.devices.help.DeviceHelpViewModel;

public class FragmentDeviceHelpBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ImageView helpImage;
    @NonNull
    public final TextView helpText;
    @Nullable
    private DeviceHelpViewModel mDeviceHelpViewModel;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;

    static {
        sViewsWithIds.put(C0644R.id.help_image, 1);
        sViewsWithIds.put(C0644R.id.help_text, 2);
    }

    public FragmentDeviceHelpBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.helpImage = (ImageView) bindings[1];
        this.helpText = (TextView) bindings[2];
        this.mboundView0 = (RelativeLayout) bindings[0];
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
        if (11 != variableId) {
            return false;
        }
        setDeviceHelpViewModel((DeviceHelpViewModel) variable);
        return true;
    }

    public void setDeviceHelpViewModel(@Nullable DeviceHelpViewModel DeviceHelpViewModel) {
        this.mDeviceHelpViewModel = DeviceHelpViewModel;
    }

    @Nullable
    public DeviceHelpViewModel getDeviceHelpViewModel() {
        return this.mDeviceHelpViewModel;
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
    public static FragmentDeviceHelpBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDeviceHelpBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (FragmentDeviceHelpBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_device_help, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static FragmentDeviceHelpBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDeviceHelpBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.fragment_device_help, null, false), bindingComponent);
    }

    @NonNull
    public static FragmentDeviceHelpBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentDeviceHelpBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/fragment_device_help_0".equals(view.getTag())) {
            return new FragmentDeviceHelpBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
