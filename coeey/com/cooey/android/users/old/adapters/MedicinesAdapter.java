package com.cooey.android.users.old.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.Reminder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class MedicinesAdapter extends Adapter<MedicinesViewHolder> {
    Context context;
    List<Medicine> medicineList = new ArrayList();

    public static class MedicinesViewHolder extends ViewHolder {
        public TextView txtDesc;
        public TextView txtMedName;
        public TextView txtReminder;

        public MedicinesViewHolder(View itemView) {
            super(itemView);
            this.txtMedName = (TextView) itemView.findViewById(C0757R.id.txtName);
            this.txtReminder = (TextView) itemView.findViewById(C0757R.id.txtReminder);
            this.txtDesc = (TextView) itemView.findViewById(C0757R.id.txtDesc);
        }
    }

    public MedicinesAdapter(Context context, List<Medicine> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
    }

    public MedicinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MedicinesViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_list_medicine_user, parent, false));
    }

    public void onBindViewHolder(MedicinesViewHolder holder, int position) {
        Medicine medicine = (Medicine) this.medicineList.get(position);
        if (!(medicine == null || medicine.getName() == null)) {
            holder.txtMedName.setText(medicine.getName());
        }
        StringBuilder reminderTime = new StringBuilder();
        if (!(medicine == null || medicine.getReminders() == null || medicine.getReminders().size() <= 0)) {
            Iterator it = medicine.getReminders().iterator();
            while (it.hasNext()) {
                Reminder reminder = (Reminder) it.next();
                Calendar calendar = Calendar.getInstance();
                if (reminder.getActiveDays() != null && reminder.getActiveDays().size() > 0 && ((RealmBoolean) reminder.getActiveDays().get(calendar.get(7) - 1)).getValue()) {
                    if (reminderTime.toString().length() > 0) {
                        reminderTime.append(",").append(reminder.getTimeOfDay());
                    } else {
                        reminderTime.append(reminder.getTimeOfDay());
                    }
                }
            }
            if (reminderTime.toString().trim().length() > 0 && !reminderTime.toString().isEmpty()) {
                holder.txtReminder.setText(reminderTime.toString());
            }
        }
        if (medicine != null && medicine.getNotes() != null) {
            holder.txtDesc.setText(medicine.getNotes());
        }
    }

    public int getItemCount() {
        return this.medicineList.size();
    }
}
