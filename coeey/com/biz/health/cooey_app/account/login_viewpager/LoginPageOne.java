package com.biz.health.cooey_app.account.login_viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.FragmentLoginPageOneBinding;

public class LoginPageOne extends Fragment {
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ((FragmentLoginPageOneBinding) DataBindingUtil.inflate(inflater, C0644R.layout.fragment_login_page_one, container, false)).getRoot();
    }

    public static LoginPageOne newInstance() {
        return new LoginPageOne();
    }
}
