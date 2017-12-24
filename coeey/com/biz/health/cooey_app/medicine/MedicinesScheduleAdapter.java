package com.biz.health.cooey_app.medicine;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.Reminder;
import io.realm.RealmList;
import java.util.Calendar;

public class MedicinesScheduleAdapter extends Adapter<ViewHolder> {
    private Context context;
    private String medicineId;
    private RealmList<Reminder> reminders;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        CheckBox cbFriday;
        CheckBox cbMonday;
        CheckBox cbSaturday;
        CheckBox cbSunday;
        CheckBox cbThursday;
        CheckBox cbTuesday;
        CheckBox cbWednesday;
        ImageView imgDeleteSchedule;
        RelativeLayout relativeLayoutCollapse;
        RelativeLayout timeLayout;
        TextView txtSave;
        TextView txtTimeStamp;
        LinearLayout weekDaysLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtTimeStamp = (TextView) itemView.findViewById(C0644R.id.timeText);
            this.timeLayout = (RelativeLayout) itemView.findViewById(C0644R.id.timeLinearLayout);
            this.imgDeleteSchedule = (ImageView) itemView.findViewById(C0644R.id.imv_delete_schedule);
            this.weekDaysLinearLayout = (LinearLayout) itemView.findViewById(C0644R.id.linear_weekdays);
            this.relativeLayoutCollapse = (RelativeLayout) itemView.findViewById(C0644R.id.relative_save_delete_schedule);
            this.cbMonday = (CheckBox) itemView.findViewById(C0644R.id.cb_monday);
            this.cbTuesday = (CheckBox) itemView.findViewById(C0644R.id.cb_tuesday);
            this.cbWednesday = (CheckBox) itemView.findViewById(C0644R.id.cb_wednesday);
            this.cbThursday = (CheckBox) itemView.findViewById(C0644R.id.cb_thursday);
            this.cbFriday = (CheckBox) itemView.findViewById(C0644R.id.cb_friday);
            this.cbSaturday = (CheckBox) itemView.findViewById(C0644R.id.cb_saturday);
            this.cbSunday = (CheckBox) itemView.findViewById(C0644R.id.cb_sunday);
        }
    }

    public MedicinesScheduleAdapter(RealmList<Reminder> reminders, Context context, String medicineId) {
        this.reminders = reminders;
        this.context = context;
        this.medicineId = medicineId;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0644R.layout.item_add_medicine_adapter, parent, false));
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Reminder reminder = (Reminder) this.reminders.get(position);
        reminder.setItemId(this.medicineId);
        final RealmList<RealmBoolean> weekDaysList = new RealmList();
        for (int i = 0; i < 7; i++) {
            weekDaysList.add(new RealmBoolean(false));
        }
        holder.timeLayout.setOnClickListener(new OnClickListener() {

            class C06931 implements OnTimeSetListener {
                C06931() {
                }

                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    if (selectedMinute < 10) {
                        holder.txtTimeStamp.setText(selectedHour + ":0" + selectedMinute);
                        reminder.setTimeOfDay(selectedHour + ":0" + selectedMinute);
                        return;
                    }
                    holder.txtTimeStamp.setText(selectedHour + ":" + selectedMinute);
                    reminder.setTimeOfDay(selectedHour + ":" + selectedMinute);
                }
            }

            public void onClick(View view) {
                holder.weekDaysLinearLayout.setVisibility(0);
                holder.relativeLayoutCollapse.setVisibility(0);
                Calendar mcurrentTime = Calendar.getInstance();
                TimePickerDialog mTimePicker = new TimePickerDialog(MedicinesScheduleAdapter.this.context, new C06931(), mcurrentTime.get(11), mcurrentTime.get(12), true);
                mTimePicker.setTitle(MedicinesScheduleAdapter.this.context.getString(C0644R.string.select_time));
                mTimePicker.show();
            }
        });
        holder.imgDeleteSchedule.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MedicinesScheduleAdapter.this.reminders.remove(position);
                MedicinesScheduleAdapter.this.notifyItemChanged(position);
                MedicinesScheduleAdapter.this.notifyItemChanged(position, Integer.valueOf(MedicinesScheduleAdapter.this.reminders.size()));
            }
        });
        holder.cbMonday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(0, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(0, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
        holder.cbTuesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(1, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(1, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
        holder.cbWednesday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(2, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(2, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
        holder.cbThursday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(3, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(3, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
        holder.cbFriday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(4, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(4, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
        holder.cbSaturday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(5, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(5, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
        holder.cbSunday.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    weekDaysList.set(6, new RealmBoolean(true));
                    reminder.setActiveDays(weekDaysList);
                    return;
                }
                weekDaysList.set(6, new RealmBoolean(false));
                reminder.setActiveDays(weekDaysList);
            }
        });
    }

    public RealmList<Reminder> getMedicinescheduleList() {
        return this.reminders;
    }

    public int getItemCount() {
        return this.reminders.size();
    }
}
