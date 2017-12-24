package com.biz.health.cooey_app.summary.info;

import android.databinding.ViewDataBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutSummaryInfoBinding;
import com.biz.health.cooey_app.summary.SummaryRecylerViewHolder;
import com.cooey.common.vo.User;

public class SummaryInfoViewHolder extends SummaryRecylerViewHolder {
    ItemLayoutSummaryInfoBinding itemLayoutSummaryInfoBinding;

    public SummaryInfoViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
        this.itemLayoutSummaryInfoBinding = (ItemLayoutSummaryInfoBinding) viewDataBinding;
    }

    public void bind(User user) {
        this.itemLayoutSummaryInfoBinding.executePendingBindings();
    }
}
