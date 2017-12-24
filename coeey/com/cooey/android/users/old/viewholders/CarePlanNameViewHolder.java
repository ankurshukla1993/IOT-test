package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import java.util.Locale;

public class CarePlanNameViewHolder extends ChildViewHolder {
    private Context context;
    private TextView txtAssistanceLevel;
    private TextView txtCareplanName;
    private TextView txtDuration;
    private TextView txtEndTime;
    private TextView txtStartTime;

    public CarePlanNameViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtStartTime = (TextView) itemView.findViewById(C0757R.id.txt_start_time_value);
        this.txtCareplanName = (TextView) itemView.findViewById(C0757R.id.txt_view_careplan_name_value);
        this.txtEndTime = (TextView) itemView.findViewById(C0757R.id.txt_end_time_value);
        this.txtDuration = (TextView) itemView.findViewById(C0757R.id.txt_duration_value);
        this.txtAssistanceLevel = (TextView) itemView.findViewById(C0757R.id.txt_assis_level_value);
    }

    public void setCareplanName(Careplan careplanName) {
        if (careplanName != null) {
            if (careplanName.getName() != null) {
                this.txtCareplanName.setText(careplanName.getName());
            }
            if (careplanName.getCreatedOn() > 0) {
                this.txtStartTime.setText(DateUtil.getReadableTimeFromLong(Long.valueOf(careplanName.getCreatedOn())));
            }
            if (careplanName.getEndTime() > 0) {
                this.txtEndTime.setText(DateUtil.getReadableTimeFromLong(Long.valueOf(careplanName.getEndTime())));
            }
            if (careplanName.getNumOfDays() > 0) {
                this.txtDuration.setText(String.format(Locale.ENGLISH, "%d Days", new Object[]{Integer.valueOf(careplanName.getNumOfDays())}));
            }
        }
    }
}
