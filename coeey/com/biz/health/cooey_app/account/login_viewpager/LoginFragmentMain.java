package com.biz.health.cooey_app.account.login_viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.FragmentLoginGuardianBinding;

public class LoginFragmentMain extends Fragment {
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLoginGuardianBinding fragmentLoginGuardianBinding = (FragmentLoginGuardianBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_login_guardian, container, false);
        View view = fragmentLoginGuardianBinding.getRoot();
        fragmentLoginGuardianBinding.setLoginViewModel(new LoginViewModel(getContext()));
        return view;
    }

    public static LoginFragmentMain newInstance() {
        return new LoginFragmentMain();
    }
}
