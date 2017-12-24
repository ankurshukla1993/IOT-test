package com.biz.health.cooey_app.summary.info;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.biz.health.cooey_app.utils.DateUtil;
import com.cooey.common.vo.User;

public class SummaryInfoViewModel extends BaseObservable {
    private int age;
    private Context context;
    private User user;

    public SummaryInfoViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        setAge(DateUtil.getAgeFromDOB(user.getDateOfBirth()));
        notifyPropertyChanged(51);
    }

    @Bindable
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(1);
    }
}
