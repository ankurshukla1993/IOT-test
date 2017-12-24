package com.biz.health.cooey_app.summary;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.common.vo.User;

public class SummaryActivityViewModel extends BaseObservable {
    private Context context;
    private SummaryRecylerAdapter summaryRecylerAdapter;
    private User user;

    public SummaryActivityViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(51);
    }

    @Bindable
    public SummaryRecylerAdapter getSummaryRecylerAdapter() {
        return this.summaryRecylerAdapter;
    }

    public void setSummaryRecylerAdapter(SummaryRecylerAdapter summaryRecylerAdapter) {
        this.summaryRecylerAdapter = summaryRecylerAdapter;
        notifyPropertyChanged(44);
    }
}
