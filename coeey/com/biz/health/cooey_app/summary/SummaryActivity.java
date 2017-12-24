package com.biz.health.cooey_app.summary;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.ActivitySummaryBinding;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.User;

public class SummaryActivity extends AppCompatActivity {
    private ActivitySummaryBinding activitySummaryBinding;
    private SummaryActivityViewModel summaryActivityViewModel;
    private User user;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = new PreferenceStore().getUser(this);
        this.summaryActivityViewModel = new SummaryActivityViewModel(this);
        this.summaryActivityViewModel.setUser(this.user);
        this.activitySummaryBinding = (ActivitySummaryBinding) DataBindingUtil.setContentView(this, C0644R.layout.activity_summary);
        this.activitySummaryBinding.setSummaryActivityViewModel(this.summaryActivityViewModel);
        setSupportActionBar(this.activitySummaryBinding.toolbar);
        getSupportActionBar().setTitle(C0644R.string.screen_summary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.summaryActivityViewModel.setSummaryRecylerAdapter(new SummaryRecylerAdapter(this, LayoutInflater.from(this), this.user));
    }
}
