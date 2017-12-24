package com.biz.health.cooey_app.account;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;

public class LoginActivityViewModel extends BaseObservable {
    private Context context;
    private LoginViewPagerAdapter loginViewPagerAdapter;
    private ViewPager viewPager;

    public LoginActivityViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public LoginViewPagerAdapter getLoginViewPagerAdapter() {
        return this.loginViewPagerAdapter;
    }

    public void setLoginViewPagerAdapter(LoginViewPagerAdapter loginViewPagerAdapter) {
        this.loginViewPagerAdapter = loginViewPagerAdapter;
        notifyPropertyChanged(24);
    }
}
