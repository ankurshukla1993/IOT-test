package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class AssessmentViewHolder extends ChildViewHolder {
    private Context context;
    TextView txtAssessment;

    public AssessmentViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtAssessment = (TextView) itemView.findViewById(C0757R.id.text_assessment_value);
    }

    public void setAssessmentViewHolder(Careplan careplan) {
        if (careplan == null || careplan.getAssessment() == null || careplan.getAssessment().getSubjective() == null || careplan.getAssessment().getSubjective().length() <= 0) {
            this.txtAssessment.setText("No Assessment");
        } else {
            this.txtAssessment.setText(careplan.getAssessment().getSubjective());
        }
    }
}
