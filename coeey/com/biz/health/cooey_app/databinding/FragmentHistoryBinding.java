package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.history.HistoryViewModel;
import com.cooey.common.BindingAdapters;

public class FragmentHistoryBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @NonNull
    public final ViewPager historyViewPager;
    private long mDirtyFlags = -1;
    @Nullable
    private HistoryViewModel mHistoryViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TabLayout tabs;

    public FragmentHistoryBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.historyViewPager = (ViewPager) bindings[2];
        this.historyViewPager.setTag(null);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tabs = (TabLayout) bindings[1];
        this.tabs.setTag(null);
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
        if (15 != variableId) {
            return false;
        }
        setHistoryViewModel((HistoryViewModel) variable);
        return true;
    }

    public void setHistoryViewModel(@Nullable HistoryViewModel HistoryViewModel) {
        updateRegistration(0, HistoryViewModel);
        this.mHistoryViewModel = HistoryViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(15);
        super.requestRebind();
    }

    @Nullable
    public HistoryViewModel getHistoryViewModel() {
        return this.mHistoryViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeHistoryViewModel((HistoryViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeHistoryViewModel(HistoryViewModel HistoryViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId != 16) {
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
        PagerAdapter historyViewModelHistoryViewPagerAdapter = null;
        HistoryViewModel historyViewModel = this.mHistoryViewModel;
        if (!((dirtyFlags & 7) == 0 || historyViewModel == null)) {
            historyViewModelHistoryViewPagerAdapter = historyViewModel.getHistoryViewPagerAdapter();
        }
        if ((dirtyFlags & 7) != 0) {
            BindingAdapters.onSetViewPager(this.historyViewPager, historyViewModelHistoryViewPagerAdapter);
        }
        if ((4 & dirtyFlags) != 0) {
            BindingAdapters.onSetViewPager(this.tabs, this.historyViewPager);
        }
    }

    @NonNull
    public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (FragmentHistoryBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_history, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.fragment_history, null, false), bindingComponent);
    }

    @NonNull
    public static FragmentHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentHistoryBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/fragment_history_0".equals(view.getTag())) {
            return new FragmentHistoryBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
