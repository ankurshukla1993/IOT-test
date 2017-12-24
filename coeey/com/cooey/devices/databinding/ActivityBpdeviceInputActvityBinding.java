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
import com.cooey.devices.bp_monitor.BPDeviceInputViewModel;

public class ActivityBpdeviceInputActvityBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final RelativeLayout activityBpdeviceInputActvity;
    @NonNull
    public final ViewPager helpViewPager;
    @Nullable
    private BPDeviceInputViewModel mBpDeviceInputViewModel;
    private long mDirtyFlags = -1;
    @NonNull
    public final Button nextButton;

    static {
        sViewsWithIds.put(C0644R.id.help_view_pager, 1);
        sViewsWithIds.put(C0644R.id.next_button, 2);
    }

    public ActivityBpdeviceInputActvityBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.activityBpdeviceInputActvity = (RelativeLayout) bindings[0];
        this.activityBpdeviceInputActvity.setTag(null);
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
        if (5 != variableId) {
            return false;
        }
        setBpDeviceInputViewModel((BPDeviceInputViewModel) variable);
        return true;
    }

    public void setBpDeviceInputViewModel(@Nullable BPDeviceInputViewModel BpDeviceInputViewModel) {
        this.mBpDeviceInputViewModel = BpDeviceInputViewModel;
    }

    @Nullable
    public BPDeviceInputViewModel getBpDeviceInputViewModel() {
        return this.mBpDeviceInputViewModel;
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
    public static ActivityBpdeviceInputActvityBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBpdeviceInputActvityBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityBpdeviceInputActvityBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_bpdevice_input_actvity, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityBpdeviceInputActvityBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBpdeviceInputActvityBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_bpdevice_input_actvity, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityBpdeviceInputActvityBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBpdeviceInputActvityBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_bpdevice_input_actvity_0".equals(view.getTag())) {
            return new ActivityBpdeviceInputActvityBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
