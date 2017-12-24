package com.biz.health.cooey_app.summary.vitals;

import android.content.Context;
import android.databinding.BaseObservable;
import com.cooey.common.vo.User;

public class SummaryVitalsInfoViewModel extends BaseObservable {
    private Context context;
    private User user;

    public SummaryVitalsInfoViewModel(Context context, User user) {
        this.user = user;
        this.context = context;
    }
}
