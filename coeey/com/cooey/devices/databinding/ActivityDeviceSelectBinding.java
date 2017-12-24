package com.cooey.devices.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.devices.selector.DeviceSelectViewModel;

public class ActivityDeviceSelectBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final Button bloodPressureDeviceButton;
    @NonNull
    public final Button bodyAnalyzerButton;
    @NonNull
    public final ImageView bodyAnalyzerImage;
    @NonNull
    public final CardView bpCardView;
    @NonNull
    public final ImageView bpMonitorImage;
    @NonNull
    public final NestedScrollView contentDeviceSelect;
    @Nullable
    private DeviceSelectViewModel mDeviceSelectViewModel;
    private long mDirtyFlags = -1;
    @NonNull
    private final CoordinatorLayout mboundView0;
    @NonNull
    public final Toolbar toolbar;

    static {
        sViewsWithIds.put(C0644R.id.toolbar, 1);
        sViewsWithIds.put(C0644R.id.content_device_select, 2);
        sViewsWithIds.put(C0644R.id.bp_card_view, 3);
        sViewsWithIds.put(C0644R.id.bp_monitor_image, 4);
        sViewsWithIds.put(C0644R.id.blood_pressure_device_button, 5);
        sViewsWithIds.put(C0644R.id.body_analyzer_image, 6);
        sViewsWithIds.put(C0644R.id.body_analyzer_button, 7);
    }

    public ActivityDeviceSelectBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.bloodPressureDeviceButton = (Button) bindings[5];
        this.bodyAnalyzerButton = (Button) bindings[7];
        this.bodyAnalyzerImage = (ImageView) bindings[6];
        this.bpCardView = (CardView) bindings[3];
        this.bpMonitorImage = (ImageView) bindings[4];
        this.contentDeviceSelect = (NestedScrollView) bindings[2];
        this.mboundView0 = (CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.toolbar = (Toolbar) bindings[1];
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
        if (12 != variableId) {
            return false;
        }
        setDeviceSelectViewModel((DeviceSelectViewModel) variable);
        return true;
    }

    public void setDeviceSelectViewModel(@Nullable DeviceSelectViewModel DeviceSelectViewModel) {
        this.mDeviceSelectViewModel = DeviceSelectViewModel;
    }

    @Nullable
    public DeviceSelectViewModel getDeviceSelectViewModel() {
        return this.mDeviceSelectViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeDeviceSelectViewModel((DeviceSelectViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeDeviceSelectViewModel(DeviceSelectViewModel DeviceSelectViewModel, int fieldId) {
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
    public static ActivityDeviceSelectBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeviceSelectBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityDeviceSelectBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_device_select, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityDeviceSelectBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeviceSelectBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_device_select, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityDeviceSelectBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeviceSelectBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_device_select_0".equals(view.getTag())) {
            return new ActivityDeviceSelectBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
