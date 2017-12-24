package com.biz.health.cooey_app.databinding;

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
import android.widget.RelativeLayout;
import com.biz.health.cooey_app.BindingAdapters;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.account.LoginActivityViewModel;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

public class ActivityLoginMainBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private LoginActivityViewModel mLoginActivityViewModel;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final ViewPagerIndicator viewPagerIndicator;
    @NonNull
    public final ViewPager viewPagerLogin;

    static {
        sViewsWithIds.put(C0644R.id.view_pager_login, 2);
    }

    public ActivityLoginMainBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.viewPagerIndicator = (ViewPagerIndicator) bindings[1];
        this.viewPagerIndicator.setTag(null);
        this.viewPagerLogin = (ViewPager) bindings[2];
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
        if (19 != variableId) {
            return false;
        }
        setLoginActivityViewModel((LoginActivityViewModel) variable);
        return true;
    }

    public void setLoginActivityViewModel(@Nullable LoginActivityViewModel LoginActivityViewModel) {
        this.mLoginActivityViewModel = LoginActivityViewModel;
    }

    @Nullable
    public LoginActivityViewModel getLoginActivityViewModel() {
        return this.mLoginActivityViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLoginActivityViewModel((LoginActivityViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLoginActivityViewModel(LoginActivityViewModel LoginActivityViewModel, int fieldId) {
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
        if ((2 & dirtyFlags) != 0) {
            BindingAdapters.onSetCircleIndicatorAdapter(this.viewPagerIndicator, this.viewPagerLogin);
        }
    }

    @NonNull
    public static ActivityLoginMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLoginMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityLoginMainBinding) DataBindingUtil.inflate(inflater, C0644R.layout.activity_login_main, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityLoginMainBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLoginMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.activity_login_main, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityLoginMainBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLoginMainBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_login_main_0".equals(view.getTag())) {
            return new ActivityLoginMainBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
