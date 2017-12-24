package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Intervention;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class VitalViewHolder extends ChildViewHolder {
    Context context;
    TextView txtVital;

    public VitalViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtVital = (TextView) itemView.findViewById(C0757R.id.text_vital_value);
    }

    public void setVitalViewHolder(Intervention intervention) {
        if (intervention != null && intervention.getName() != null && intervention.getName().length() > 0) {
            this.txtVital.setText(intervention.getName());
        }
    }
}
