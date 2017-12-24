package com.biz.health.cooey_app.summary.notes;

import android.databinding.ViewDataBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutNotesBinding;
import com.biz.health.cooey_app.summary.SummaryRecylerViewHolder;
import com.cooey.common.vo.User;

public class SummaryPatientNotesViewHolder extends SummaryRecylerViewHolder {
    private ItemLayoutNotesBinding itemLayoutNotesBinding;

    public SummaryPatientNotesViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
        this.itemLayoutNotesBinding = (ItemLayoutNotesBinding) viewDataBinding;
    }

    public void bind(User user) {
    }
}
