package com.biz.health.cooey_app.secondary_vital;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class WidgetsViewHolder extends ViewHolder {
    public abstract void updateView(int i);

    public WidgetsViewHolder(View itemView) {
        super(itemView);
    }
}
