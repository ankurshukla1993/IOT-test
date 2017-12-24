package com.cooey.android.users.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.users.PeopleSelectorRecyclerViewAdapter;
import com.cooey.android.users.PeopleSelectorViewModel;
import com.cooey.common.BindingAdapters;

public class LayoutPeopleSelectorViewBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @Nullable
    private PeopleSelectorViewModel mPeopleSelectorViewModel;
    @NonNull
    private final FrameLayout mboundView0;
    @NonNull
    private final RecyclerView mboundView1;
    @NonNull
    private final ProgressBar mboundView2;

    public LayoutPeopleSelectorViewBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (RecyclerView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (ProgressBar) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (38 != variableId) {
            return false;
        }
        setPeopleSelectorViewModel((PeopleSelectorViewModel) variable);
        return true;
    }

    public void setPeopleSelectorViewModel(@Nullable PeopleSelectorViewModel PeopleSelectorViewModel) {
        updateRegistration(0, PeopleSelectorViewModel);
        this.mPeopleSelectorViewModel = PeopleSelectorViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(38);
        super.requestRebind();
    }

    @Nullable
    public PeopleSelectorViewModel getPeopleSelectorViewModel() {
        return this.mPeopleSelectorViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangePeopleSelectorViewModel((PeopleSelectorViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePeopleSelectorViewModel(PeopleSelectorViewModel PeopleSelectorViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 40) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (fieldId != 53) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean peopleSelectorViewModelUsersDataProgress = false;
        int peopleSelectorViewModelUsersDataProgressViewVISIBLEViewGONE = 0;
        PeopleSelectorRecyclerViewAdapter peopleSelectorViewModelRecyclerViewAdapter = null;
        PeopleSelectorViewModel peopleSelectorViewModel = this.mPeopleSelectorViewModel;
        if ((15 & dirtyFlags) != 0) {
            if ((dirtyFlags & 13) != 0) {
                if (peopleSelectorViewModel != null) {
                    peopleSelectorViewModelUsersDataProgress = peopleSelectorViewModel.isUsersDataProgress();
                }
                if ((dirtyFlags & 13) != 0) {
                    if (peopleSelectorViewModelUsersDataProgress) {
                        dirtyFlags |= 32;
                    } else {
                        dirtyFlags |= 16;
                    }
                }
                peopleSelectorViewModelUsersDataProgressViewVISIBLEViewGONE = peopleSelectorViewModelUsersDataProgress ? 0 : 8;
            }
            if (!((dirtyFlags & 11) == 0 || peopleSelectorViewModel == null)) {
                peopleSelectorViewModelRecyclerViewAdapter = peopleSelectorViewModel.getRecyclerViewAdapter();
            }
        }
        if ((dirtyFlags & 11) != 0) {
            BindingAdapters.onSetRecyclerViewAdapter(this.mboundView1, peopleSelectorViewModelRecyclerViewAdapter);
        }
        if ((8 & dirtyFlags) != 0) {
            BindingAdapters.onSetLayoutManager(this.mboundView1, "GRID");
        }
        if ((dirtyFlags & 13) != 0) {
            this.mboundView2.setVisibility(peopleSelectorViewModelUsersDataProgressViewVISIBLEViewGONE);
        }
    }

    @NonNull
    public static LayoutPeopleSelectorViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutPeopleSelectorViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutPeopleSelectorViewBinding) DataBindingUtil.inflate(inflater, C0644R.layout.layout_people_selector_view, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutPeopleSelectorViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutPeopleSelectorViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.layout_people_selector_view, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutPeopleSelectorViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutPeopleSelectorViewBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_people_selector_view_0".equals(view.getTag())) {
            return new LayoutPeopleSelectorViewBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
