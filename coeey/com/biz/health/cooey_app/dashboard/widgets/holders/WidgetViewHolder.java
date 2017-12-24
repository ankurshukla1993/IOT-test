package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.cooey.common.vo.User;

public abstract class WidgetViewHolder extends ViewHolder {
    public abstract void bind(User user);

    public WidgetViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
    }
}
