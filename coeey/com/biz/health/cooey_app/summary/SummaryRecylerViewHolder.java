package com.biz.health.cooey_app.summary;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.cooey.common.vo.User;

public abstract class SummaryRecylerViewHolder extends ViewHolder {
    public abstract void bind(User user);

    public SummaryRecylerViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
    }
}
