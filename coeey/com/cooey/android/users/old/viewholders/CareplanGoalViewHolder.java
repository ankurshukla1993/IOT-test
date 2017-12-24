package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CareplanGoalViewHolder extends ChildViewHolder {
    private Context context;
    TextView txtCareplanGoal;

    public CareplanGoalViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtCareplanGoal = (TextView) itemView.findViewById(C0757R.id.text_careplan_goal);
    }

    public void setCareplanGoal(Careplan careplan) {
        if (careplan == null || careplan.getGoal() == null || careplan.getGoal().length() <= 0) {
            this.txtCareplanGoal.setText("No Goals");
        } else {
            this.txtCareplanGoal.setText(careplan.getGoal());
        }
    }
}
