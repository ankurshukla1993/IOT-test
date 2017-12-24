package com.biz.health.cooey_app.account;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.ActivityLoginMainBinding;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;

public class LoginMainActivity extends AppCompatActivity {
    private ActivityLoginMainBinding activityLoginMainBinding;
    private LoginActivityViewModel loginActivityViewModel;

    class C06761 implements OnPageChangeListener {
        C06761() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position == 3) {
                LoginMainActivity.this.activityLoginMainBinding.viewPagerIndicator.setVisibility(8);
            } else if (LoginMainActivity.this.activityLoginMainBinding.viewPagerIndicator.getVisibility() == 8) {
                LoginMainActivity.this.activityLoginMainBinding.viewPagerIndicator.setVisibility(0);
            }
        }

        public void onPageSelected(int position) {
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activityLoginMainBinding = (ActivityLoginMainBinding) DataBindingUtil.setContentView(this, C0644R.layout.activity_login_main);
        this.loginActivityViewModel = new LoginActivityViewModel(this);
        this.activityLoginMainBinding.setLoginActivityViewModel(this.loginActivityViewModel);
        Fabric.with(this, new Kit[]{new Crashlytics()});
        LoginViewPagerAdapter loginViewPagerAdapter = new LoginViewPagerAdapter(getSupportFragmentManager());
        this.loginActivityViewModel.setLoginViewPagerAdapter(loginViewPagerAdapter);
        this.activityLoginMainBinding.viewPagerLogin.setAdapter(loginViewPagerAdapter);
        this.activityLoginMainBinding.viewPagerIndicator.setupWithViewPager(this.activityLoginMainBinding.viewPagerLogin);
        this.activityLoginMainBinding.viewPagerLogin.addOnPageChangeListener(new C06761());
        this.activityLoginMainBinding.invalidateAll();
    }
}
