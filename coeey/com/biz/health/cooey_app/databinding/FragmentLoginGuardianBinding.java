package com.biz.health.cooey_app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.databinding.generated.callback.OnClickListener.Listener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.account.login_viewpager.LoginViewModel;
import com.cooey.common.BindingAdapters;

public class FragmentLoginGuardianBinding extends ViewDataBinding implements Listener {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final EditText email;
    @NonNull
    public final LinearLayout emailLoginForm;
    @NonNull
    public final Button emailSignInButton;
    private InverseBindingListener emailandroidTextAttrChanged = new C06841();
    @NonNull
    public final EditText login;
    @NonNull
    public final ScrollView loginForm;
    @NonNull
    public final ProgressBar loginProgress;
    private InverseBindingListener loginandroidTextAttrChanged = new C06852();
    @Nullable
    private final OnClickListener mCallback4;
    private long mDirtyFlags = -1;
    @Nullable
    private LoginViewModel mLoginViewModel;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final ProgressBar progressBar;

    class C06841 implements InverseBindingListener {
        C06841() {
        }

        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentLoginGuardianBinding.this.email);
            LoginViewModel loginViewModel = FragmentLoginGuardianBinding.this.mLoginViewModel;
            if (loginViewModel != null) {
                loginViewModel.setEmail(callbackArg_0);
            }
        }
    }

    class C06852 implements InverseBindingListener {
        C06852() {
        }

        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(FragmentLoginGuardianBinding.this.login);
            LoginViewModel loginViewModel = FragmentLoginGuardianBinding.this.mLoginViewModel;
            if (loginViewModel != null) {
                loginViewModel.setPassword(callbackArg_0);
            }
        }
    }

    static {
        sViewsWithIds.put(C0644R.id.login_progress, 5);
        sViewsWithIds.put(C0644R.id.login_form, 6);
        sViewsWithIds.put(C0644R.id.email_login_form, 7);
    }

    public FragmentLoginGuardianBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.email = (EditText) bindings[1];
        this.email.setTag(null);
        this.emailLoginForm = (LinearLayout) bindings[7];
        this.emailSignInButton = (Button) bindings[3];
        this.emailSignInButton.setTag(null);
        this.login = (EditText) bindings[2];
        this.login.setTag(null);
        this.loginForm = (ScrollView) bindings[6];
        this.loginProgress = (ProgressBar) bindings[5];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar = (ProgressBar) bindings[4];
        this.progressBar.setTag(null);
        setRootTag(root);
        this.mCallback4 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (23 != variableId) {
            return false;
        }
        setLoginViewModel((LoginViewModel) variable);
        return true;
    }

    public void setLoginViewModel(@Nullable LoginViewModel LoginViewModel) {
        updateRegistration(0, LoginViewModel);
        this.mLoginViewModel = LoginViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Nullable
    public LoginViewModel getLoginViewModel() {
        return this.mLoginViewModel;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLoginViewModel((LoginViewModel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLoginViewModel(LoginViewModel LoginViewModel, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 17) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (fieldId == 13) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (fieldId != 36) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String loginViewModelEmail = null;
        boolean loginViewModelInProgress = false;
        LoginViewModel loginViewModel = this.mLoginViewModel;
        String loginViewModelPassword = null;
        boolean LoginViewModelInProgress1 = false;
        if ((31 & dirtyFlags) != 0) {
            if (!((21 & dirtyFlags) == 0 || loginViewModel == null)) {
                loginViewModelEmail = loginViewModel.getEmail();
            }
            if (!((25 & dirtyFlags) == 0 || loginViewModel == null)) {
                loginViewModelPassword = loginViewModel.getPassword();
            }
            if ((19 & dirtyFlags) != 0) {
                if (loginViewModel != null) {
                    LoginViewModelInProgress1 = loginViewModel.isInProgress();
                }
                loginViewModelInProgress = !LoginViewModelInProgress1;
            }
        }
        if ((19 & dirtyFlags) != 0) {
            this.email.setEnabled(loginViewModelInProgress);
            this.emailSignInButton.setEnabled(loginViewModelInProgress);
            this.login.setEnabled(loginViewModelInProgress);
            this.progressBar.setIndeterminate(LoginViewModelInProgress1);
            BindingAdapters.onSetVisible(this.progressBar, LoginViewModelInProgress1);
        }
        if ((21 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.email, loginViewModelEmail);
        }
        if ((16 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.email, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.emailandroidTextAttrChanged);
            this.emailSignInButton.setOnClickListener(this.mCallback4);
            TextViewBindingAdapter.setTextWatcher(this.login, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.loginandroidTextAttrChanged);
        }
        if ((25 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.login, loginViewModelPassword);
        }
    }

    public final void _internalCallbackOnClick(int sourceId, View callbackArg_0) {
        LoginViewModel loginViewModel = this.mLoginViewModel;
        if (loginViewModel != null) {
            loginViewModel.onLoginClick();
        }
    }

    @NonNull
    public static FragmentLoginGuardianBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLoginGuardianBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (FragmentLoginGuardianBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_login_guardian, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static FragmentLoginGuardianBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLoginGuardianBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(C0644R.layout.fragment_login_guardian, null, false), bindingComponent);
    }

    @NonNull
    public static FragmentLoginGuardianBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentLoginGuardianBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/fragment_login_guardian_0".equals(view.getTag())) {
            return new FragmentLoginGuardianBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
