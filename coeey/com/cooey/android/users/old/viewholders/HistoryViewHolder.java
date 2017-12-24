package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class HistoryViewHolder extends ChildViewHolder {
    private Context context;
    private TextView txtHistory;

    public HistoryViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.txtHistory = (TextView) itemView.findViewById(C0757R.id.txt_history_value);
    }

    public void setHistoryAndExamination(Careplan careplan) {
        if (careplan == null || careplan.getHistory() == null || careplan.getHistory().length() <= 0) {
            this.txtHistory.setText("No History or Exam");
        } else {
            this.txtHistory.setText(careplan.getHistory());
        }
    }
}
