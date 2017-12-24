package com.cooey.devices.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.biz.health.cooey_app.C0644R;
import com.cooey.devices.body_analyzer.BodyAnalyzerDeviceViewModel;

public class ActivityBodyAnalyzerDeviceBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ViewPager helpViewPager;
    @Nullable
    private BodyAnalyzerDeviceViewModel mBodyAnalyzerDeviceViewModel;
    private long mDirtyFlags = -1;
    @NonNull
    private final CoordinatorLayout mboundView0;
    @NonNull
    public final Button nextButton;
    @NonNull
    public final Toolbar toolbar;

    static {
        sViewsWithIds.put(C0644R.id.toolbar, 1);
        sViewsWithIds.put(C0644R.id.help_view_pager, 2);
        sViewsWithIds.put(C0644R.id.next_button, 3);
    }

    public ActivityBodyAnalyzerDeviceBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.helpViewPager = (ViewPager) bindings[2];
        this.mboundView0 = (CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.nextButton = (Button) bindings[3];
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
        if (4 != variableId) {
            return false;
        }
        setBodyAnalyzerDeviceViewModel((BodyAnalyzerDeviceViewModel) variable);
        return true;
    }

    public void setBodyAnalyzerDeviceViewModel(@Nullable BodyAnalyzerDeviceViewModel BodyAnalyzerDeviceViewModel) {
        this.mBodyAnalyzerDeviceViewModel = BodyAnalyzerDeviceViewModel;
    }

    @Nullable
    public BodyAnalyzerDeviceViewModel getBodyAnalyzerDeviceViewModel() {
        return this.mBodyAnalyzerDeviceViewModel;
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
    public static ActivityBodyAnalyzerDeviceBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBodyAnalyzerDeviceBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityBodyAnalyzerDeviceBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_body_analyzer_device, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityBodyAnalyzerDeviceBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBodyAnalyzerDeviceBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_body_analyzer_device, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityBodyAnalyzerDeviceBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBodyAnalyzerDeviceBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_body_analyzer_device_0".equals(view.getTag())) {
            return new ActivityBodyAnalyzerDeviceBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
