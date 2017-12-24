package com.cooey.devices.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;

public class DeviceItemBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final CardView bpCardView;
    @NonNull
    public final ImageView deviceImage;
    @NonNull
    public final TextView deviceName;
    @NonNull
    public final TextView deviceProfileNumber;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final Button takeReadingButton;

    static {
        sViewsWithIds.put(C0644R.id.bp_card_view, 1);
        sViewsWithIds.put(C0644R.id.device_image, 2);
        sViewsWithIds.put(C0644R.id.device_name, 3);
        sViewsWithIds.put(C0644R.id.device_profile_number, 4);
        sViewsWithIds.put(C0644R.id.take_reading_button, 5);
    }

    public DeviceItemBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.bpCardView = (CardView) bindings[1];
        this.deviceImage = (ImageView) bindings[2];
        this.deviceName = (TextView) bindings[3];
        this.deviceProfileNumber = (TextView) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.takeReadingButton = (Button) bindings[5];
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
    public static DeviceItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DeviceItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (DeviceItemBinding) DataBindingUtil.inflate(inflater, C0644R.layout.device_item, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static DeviceItemBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DeviceItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.device_item, null, false), bindingComponent);
    }

    @NonNull
    public static DeviceItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DeviceItemBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/device_item_0".equals(view.getTag())) {
            return new DeviceItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
