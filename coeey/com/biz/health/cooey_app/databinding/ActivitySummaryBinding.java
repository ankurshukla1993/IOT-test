package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.summary.SummaryActivityViewModel;
import com.biz.health.cooey_app.summary.SummaryRecylerAdapter;
import com.cooey.common.BindingAdapters;

public class ActivitySummaryBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private SummaryActivityViewModel mSummaryActivityViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final RecyclerView recyclerviewSummary;
    @NonNull
    public final Toolbar toolbar;

    static {
        sViewsWithIds.put(C0644R.id.toolbar, 2);
    }

    public ActivitySummaryBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recyclerviewSummary = (RecyclerView) bindings[1];
        this.recyclerviewSummary.setTag(null);
        this.toolbar = (Toolbar) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (41 != variableId) {
            return false;
        }
        setSummaryActivityViewModel((SummaryActivityViewModel) variable);
        return true;
    }

    public void setSummaryActivityViewModel(@Nullable SummaryActivityViewModel SummaryActivityViewModel) {
        updateRegistration(0, SummaryActivityViewModel);
        this.mSummaryActivityViewModel = SummaryActivityViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(41);
        super.requestRebind();
    }

    @Nullable
    public SummaryActivityViewModel getSummaryActivityViewModel() {
        return this.mSummaryActivityViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeSummaryActivityViewModel((SummaryActivityViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeSummaryActivityViewModel(SummaryActivityViewModel SummaryActivityViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId != 44) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SummaryActivityViewModel summaryActivityViewModel = this.mSummaryActivityViewModel;
        SummaryRecylerAdapter summaryActivityViewModelSummaryRecylerAdapter = null;
        if (!((dirtyFlags & 7) == 0 || summaryActivityViewModel == null)) {
            summaryActivityViewModelSummaryRecylerAdapter = summaryActivityViewModel.getSummaryRecylerAdapter();
        }
        if ((dirtyFlags & 7) != 0) {
            BindingAdapters.onSetRecyclerViewAdapter(this.recyclerviewSummary, summaryActivityViewModelSummaryRecylerAdapter);
        }
        if ((4 & dirtyFlags) != 0) {
            BindingAdapters.onSetLayoutManager(this.recyclerviewSummary, "LINEAR");
        }
    }

    @NonNull
    public static ActivitySummaryBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySummaryBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivitySummaryBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_summary, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivitySummaryBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySummaryBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_summary, null, false), bindingComponent);
    }

    @NonNull
    public static ActivitySummaryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySummaryBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_summary_0".equals(view.getTag())) {
            return new ActivitySummaryBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
