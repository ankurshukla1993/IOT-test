package com.biz.health.cooey_app.account;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.biz.health.cooey_app.account.login_viewpager.LoginFragmentMain;
import com.biz.health.cooey_app.account.login_viewpager.LoginPageOne;
import com.biz.health.cooey_app.account.login_viewpager.LoginPageThree;
import com.biz.health.cooey_app.account.login_viewpager.LoginPageTwo;

public class LoginViewPagerAdapter extends FragmentStatePagerAdapter {
    public LoginViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LoginPageOne.newInstance();
            case 1:
                return LoginPageTwo.newInstance();
            case 2:
                return LoginPageThree.newInstance();
            case 3:
                return new LoginFragmentMain();
            default:
                return LoginPageOne.newInstance();
        }
    }

    public int getCount() {
        return 4;
    }
}
