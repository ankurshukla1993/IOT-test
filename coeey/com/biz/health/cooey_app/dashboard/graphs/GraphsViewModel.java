package com.biz.health.cooey_app.dashboard.graphs;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.common.vo.User;

public class GraphsViewModel extends BaseObservable {
    private User user;

    public GraphsViewModel(User user) {
        this.user = user;
    }

    @Bindable
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(51);
    }
}
