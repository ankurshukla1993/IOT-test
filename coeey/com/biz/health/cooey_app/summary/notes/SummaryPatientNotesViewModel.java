package com.biz.health.cooey_app.summary.notes;

import android.databinding.BaseObservable;
import com.cooey.common.vo.User;

public class SummaryPatientNotesViewModel extends BaseObservable {
    private User user;

    public SummaryPatientNotesViewModel(User user) {
        this.user = user;
    }
}
