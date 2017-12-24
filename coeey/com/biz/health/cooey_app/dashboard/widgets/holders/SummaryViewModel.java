package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.cooey.common.vo.User;

public class SummaryViewModel extends BaseObservable {
    private String bloodGlucoseText;
    private String bpText;
    private final User user;
    private String weight;

    public SummaryViewModel(User user) {
        this.user = user;
    }

    @Bindable
    public String getBloodGlucoseText() {
        return this.bloodGlucoseText;
    }

    public void setBloodGlucoseText(String bloodGlucoseText) {
        this.bloodGlucoseText = bloodGlucoseText;
        notifyPropertyChanged(3);
    }

    @Bindable
    public String getBpText() {
        return this.bpText;
    }

    public void setBpText(String bpText) {
        this.bpText = bpText;
        notifyPropertyChanged(7);
    }

    @Bindable
    public User getUser() {
        return this.user;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
