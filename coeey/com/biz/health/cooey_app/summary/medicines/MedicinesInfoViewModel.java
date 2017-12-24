package com.biz.health.cooey_app.summary.medicines;

import android.databinding.BaseObservable;
import com.cooey.common.vo.User;

public class MedicinesInfoViewModel extends BaseObservable {
    private User user;

    public MedicinesInfoViewModel(User user) {
        this.user = user;
    }
}
