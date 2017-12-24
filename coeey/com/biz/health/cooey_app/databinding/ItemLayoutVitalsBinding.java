package com.biz.health.cooey_app.databinding;

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
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.summary.vitals.SummaryVitalsInfoViewModel;

public class ItemLayoutVitalsBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private SummaryVitalsInfoViewModel mSummaryVitalsInfoViewModel;
    @NonNull
    public final TextView vitalsTitle;
    @NonNull
    public final LinearLayout vitalsTrendsContainer;

    static {
        sViewsWithIds.put(C0644R.id.vitals_title, 1);
    }

    public ItemLayoutVitalsBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.vitalsTitle = (TextView) bindings[1];
        this.vitalsTrendsContainer = (LinearLayout) bindings[0];
        this.vitalsTrendsContainer.setTag(null);
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
        if (46 != variableId) {
            return false;
        }
        setSummaryVitalsInfoViewModel((SummaryVitalsInfoViewModel) variable);
        return true;
    }

    public void setSummaryVitalsInfoViewModel(@Nullable SummaryVitalsInfoViewModel SummaryVitalsInfoViewModel) {
        this.mSummaryVitalsInfoViewModel = SummaryVitalsInfoViewModel;
    }

    @Nullable
    public SummaryVitalsInfoViewModel getSummaryVitalsInfoViewModel() {
        return this.mSummaryVitalsInfoViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeSummaryVitalsInfoViewModel((SummaryVitalsInfoViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeSummaryVitalsInfoViewModel(SummaryVitalsInfoViewModel SummaryVitalsInfoViewModel, int fieldId) {
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
    public static ItemLayoutVitalsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutVitalsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ItemLayoutVitalsBinding) DataBindingUtil.inflate(inflater, C0644R.layout.item_layout_vitals, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ItemLayoutVitalsBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutVitalsBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.item_layout_vitals, null, false), bindingComponent);
    }

    @NonNull
    public static ItemLayoutVitalsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ItemLayoutVitalsBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/item_layout_vitals_0".equals(view.getTag())) {
            return new ItemLayoutVitalsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
