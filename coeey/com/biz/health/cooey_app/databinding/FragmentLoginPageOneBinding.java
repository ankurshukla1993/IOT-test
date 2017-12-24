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
import com.biz.health.cooey_app.account.login_viewpager.LoginPageOneModel;

public class FragmentLoginPageOneBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    @Nullable
    private LoginPageOneModel mLoginPageOneModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView txtView;
    @NonNull
    public final TextView txtViewSecondaryText;

    static {
        sViewsWithIds.put(C0644R.id.txt_view, 1);
        sViewsWithIds.put(C0644R.id.txt_view_secondary_text, 2);
    }

    public FragmentLoginPageOneBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtView = (TextView) bindings[1];
        this.txtViewSecondaryText = (TextView) bindings[2];
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
        if (20 != variableId) {
            return false;
        }
        setLoginPageOneModel((LoginPageOneModel) variable);
        return true;
    }

    public void setLoginPageOneModel(@Nullable LoginPageOneModel LoginPageOneModel) {
        this.mLoginPageOneModel = LoginPageOneModel;
    }

    @Nullable
    public LoginPageOneModel getLoginPageOneModel() {
        return this.mLoginPageOneModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLoginPageOneModel((LoginPageOneModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLoginPageOneModel(LoginPageOneModel LoginPageOneModel, int fieldId) {
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
    public static FragmentLoginPageOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLoginPageOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (FragmentLoginPageOneBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_login_page_one, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static FragmentLoginPageOneBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLoginPageOneBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.fragment_login_page_one, null, false), bindingComponent);
    }

    @NonNull
    public static FragmentLoginPageOneBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLoginPageOneBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/fragment_login_page_one_0".equals(view.getTag())) {
            return new FragmentLoginPageOneBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
