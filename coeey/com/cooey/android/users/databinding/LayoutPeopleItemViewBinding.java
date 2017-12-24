package com.cooey.android.users.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.cooey.android.users.PeopleItemViewModel;
import com.cooey.api.client.models.User;

public class LayoutPeopleItemViewBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @Nullable
    private PeopleItemViewModel mPeopleItemViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final TextView mboundView1;

    public LayoutPeopleItemViewBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
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
        if (37 != variableId) {
            return false;
        }
        setPeopleItemViewModel((PeopleItemViewModel) variable);
        return true;
    }

    public void setPeopleItemViewModel(@Nullable PeopleItemViewModel PeopleItemViewModel) {
        updateRegistration(0, PeopleItemViewModel);
        this.mPeopleItemViewModel = PeopleItemViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(37);
        super.requestRebind();
    }

    @Nullable
    public PeopleItemViewModel getPeopleItemViewModel() {
        return this.mPeopleItemViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangePeopleItemViewModel((PeopleItemViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangePeopleItemViewModel(PeopleItemViewModel PeopleItemViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId != 51) {
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
        PeopleItemViewModel peopleItemViewModel = this.mPeopleItemViewModel;
        String peopleItemViewModelUserFirstName = null;
        User peopleItemViewModelUser = null;
        if ((dirtyFlags & 7) != 0) {
            if (peopleItemViewModel != null) {
                peopleItemViewModelUser = peopleItemViewModel.getUser();
            }
            if (peopleItemViewModelUser != null) {
                peopleItemViewModelUserFirstName = peopleItemViewModelUser.getFirstName();
            }
        }
        if ((dirtyFlags & 7) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, peopleItemViewModelUserFirstName);
        }
    }

    @NonNull
    public static LayoutPeopleItemViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutPeopleItemViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutPeopleItemViewBinding) DataBindingUtil.inflate(inflater, C0644R.layout.layout_people_item_view, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutPeopleItemViewBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutPeopleItemViewBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.layout_people_item_view, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutPeopleItemViewBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutPeopleItemViewBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_people_item_view_0".equals(view.getTag())) {
            return new LayoutPeopleItemViewBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
