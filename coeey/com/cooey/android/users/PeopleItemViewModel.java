package com.cooey.android.users;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.api.client.models.User;

public class PeopleItemViewModel extends BaseObservable {
    private User user;

    @Bindable
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }

    public PeopleItemViewModel(User user) {
        setUser(user);
    }
}
