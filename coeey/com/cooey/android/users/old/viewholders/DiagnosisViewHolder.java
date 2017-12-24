package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class DiagnosisViewHolder extends ChildViewHolder {
    private Context context;
    TextView txtDiagnosis;

    public DiagnosisViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtDiagnosis = (TextView) itemView.findViewById(C0757R.id.txt_diagnosis_value);
    }

    public void setDiagnosisViewHolder(Careplan careplan) {
        if (careplan == null || careplan.getDiagnosis() == null || careplan.getDiagnosis().getHealthConditions() == null || careplan.getDiagnosis().getHealthConditions().length() <= 0) {
            this.txtDiagnosis.setText("No Diagnosis");
        } else {
            this.txtDiagnosis.setText(careplan.getDiagnosis().getHealthConditions());
        }
    }
}
