package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import com.cooey.common.vo.careplan.Intervention;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class InterventionTypeViewHolder extends ChildViewHolder {
    Context context;

    public InterventionTypeViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
    }

    public void setInterventionTypeViewHolder(Intervention intervention) {
    }
}
