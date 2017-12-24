package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CareplanEvaluationViewHolder extends ChildViewHolder {
    private Context context;
    TextView txtEvaluationValue;

    public CareplanEvaluationViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtEvaluationValue = (TextView) itemView.findViewById(C0757R.id.txt_evaluation_value);
    }

    public void setEvaluation(Careplan careplan) {
        if (careplan == null || careplan.getEvaluation() == null || careplan.getEvaluation().length() <= 0) {
            this.txtEvaluationValue.setText("No Evaluation Added");
        } else {
            this.txtEvaluationValue.setText(careplan.getEvaluation());
        }
    }
}
