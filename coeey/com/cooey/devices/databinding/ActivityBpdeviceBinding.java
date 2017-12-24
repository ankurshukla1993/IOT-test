package com.cooey.devices.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.biz.health.cooey_app.C0644R;
import com.cooey.devices.bp_monitor.BPDeviceViewModel;

public class ActivityBpdeviceBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final RelativeLayout activityBpdevice;
    @NonNull
    public final ViewPager helpViewPager;
    @Nullable
    private BPDeviceViewModel mBpDeviceViewModel;
    private long mDirtyFlags = -1;
    @NonNull
    public final Button nextButton;

    static {
        sViewsWithIds.put(C0644R.id.help_view_pager, 1);
        sViewsWithIds.put(C0644R.id.next_button, 2);
    }

    public ActivityBpdeviceBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.activityBpdevice = (RelativeLayout) bindings[0];
        this.activityBpdevice.setTag(null);
        this.helpViewPager = (ViewPager) bindings[1];
        this.nextButton = (Button) bindings[2];
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
        if (6 != variableId) {
            return false;
        }
        setBpDeviceViewModel((BPDeviceViewModel) variable);
        return true;
    }

    public void setBpDeviceViewModel(@Nullable BPDeviceViewModel BpDeviceViewModel) {
        this.mBpDeviceViewModel = BpDeviceViewModel;
    }

    @Nullable
    public BPDeviceViewModel getBpDeviceViewModel() {
        return this.mBpDeviceViewModel;
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
    public static ActivityBpdeviceBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBpdeviceBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityBpdeviceBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_bpdevice, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityBpdeviceBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBpdeviceBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_bpdevice, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityBpdeviceBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBpdeviceBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_bpdevice_0".equals(view.getTag())) {
            return new ActivityBpdeviceBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
