package com.biz.health.cooey_app.summary.vitals;

import android.databinding.ViewDataBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutVitalsBinding;
import com.biz.health.cooey_app.summary.SummaryRecylerViewHolder;
import com.cooey.common.vo.User;

public class SummaryVitalsViewHolder extends SummaryRecylerViewHolder {
    private ItemLayoutVitalsBinding itemLayoutVitalsBinding;

    public SummaryVitalsViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
        this.itemLayoutVitalsBinding = (ItemLayoutVitalsBinding) viewDataBinding;
    }

    public void bind(User user) {
    }
}
