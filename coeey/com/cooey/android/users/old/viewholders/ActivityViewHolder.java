package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Intervention;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ActivityViewHolder extends ChildViewHolder {
    Context context;
    TextView txtActivity;

    public ActivityViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtActivity = (TextView) itemView.findViewById(C0757R.id.text_activity_value);
    }

    public void setActivityViewHolder(Intervention intervention) {
        if (intervention != null && intervention.getName() != null && intervention.getName().length() > 0) {
            this.txtActivity.setText(intervention.getName());
        }
    }
}
