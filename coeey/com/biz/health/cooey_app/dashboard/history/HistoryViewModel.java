package com.biz.health.cooey_app.dashboard.history;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import com.cooey.common.vo.User;

public class HistoryViewModel extends BaseObservable {
    private final Context context;
    private HistoryViewPagerAdapter historyViewPagerAdapter;

    @Bindable
    public HistoryViewPagerAdapter getHistoryViewPagerAdapter() {
        return this.historyViewPagerAdapter;
    }

    public void setHistoryViewPagerAdapter(HistoryViewPagerAdapter historyViewPagerAdapter) {
        this.historyViewPagerAdapter = historyViewPagerAdapter;
        notifyPropertyChanged(15);
    }

    public HistoryViewModel(Context context, User user) {
        this.context = context;
        this.historyViewPagerAdapter = new HistoryViewPagerAdapter(context, ((AppCompatActivity) context).getSupportFragmentManager(), user);
    }
}
