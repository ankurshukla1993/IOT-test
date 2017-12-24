package com.biz.health.cooey_app.medicine;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.Reminder;
import com.facebook.appevents.AppEventsConstants;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.nex3z.flowlayout.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MedicineListAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<Medicine> medicineList;

    public class MedicineViewHolder extends ViewHolder {
        @BindView(2131362620)
        FlowLayout timingsFlowLayout;
        @BindView(2131362601)
        TextView txtAddedOn;
        @BindView(2131362600)
        TextView txtAddedOnDate;
        @BindView(2131362602)
        TextView txtDaysTaken;
        @BindView(2131362603)
        TextView txtDaysTakenValue;
        @BindView(2131362751)
        TextView txtMedicineName;
        @BindView(2131362604)
        TextView txtMedicineNoteValue;
        @BindView(2131362752)
        TextView txtMedicineNotes;
        @BindView(2131362607)
        TextView txtMedicineTiming;
        @BindView(2131362605)
        TextView txtRemaining;
        @BindView(2131362606)
        TextView txtRemainingDate;

        public MedicineViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Medicine medicine) {
            if (medicine != null) {
                this.txtMedicineName.setText(medicine.getName());
                this.txtMedicineNoteValue.setText(medicine.getNotes());
                bindTimings(medicine);
            }
        }

        private void bindTimings(Medicine medicine) {
            if (this.timingsFlowLayout.getChildCount() > 0) {
                this.timingsFlowLayout.removeAllViews();
            }
            StringBuilder reminderTime = new StringBuilder();
            if (medicine != null) {
                long addedOn = medicine.getAddedOn();
                long tobeTakenTill = medicine.getToBeTakenTill();
                setAddedOnDate(addedOn);
                if (tobeTakenTill > 0) {
                    long remainingDay = getDuration(Calendar.getInstance().getTime().getTime(), tobeTakenTill);
                    if (remainingDay >= 0) {
                        this.txtRemainingDate.setText("" + remainingDay);
                    } else {
                        this.txtRemainingDate.setText(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    }
                }
                if (addedOn > 0) {
                    long daysTaken = getDuration(addedOn, Calendar.getInstance().getTime().getTime());
                    if (daysTaken >= 0) {
                        this.txtDaysTakenValue.setText("" + daysTaken);
                    } else {
                        this.txtDaysTakenValue.setText(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    }
                }
                if (medicine.getReminders() != null && medicine.getReminders().size() > 0) {
                    Iterator it = medicine.getReminders().iterator();
                    while (it.hasNext()) {
                        Reminder reminder = (Reminder) it.next();
                        Calendar calendar = Calendar.getInstance();
                        if (reminder.getActiveDays() != null && reminder.getActiveDays().size() > 0) {
                            if (((RealmBoolean) reminder.getActiveDays().get(calendar.get(7) - 1)).getValue()) {
                                if (reminderTime.toString().length() > 0) {
                                    reminderTime.append(",").append(reminder.getTimeOfDay());
                                } else {
                                    reminderTime.append(reminder.getTimeOfDay());
                                }
                            }
                        }
                    }
                    if (reminderTime.toString().trim().length() > 0 && !reminderTime.toString().isEmpty()) {
                        for (String time : reminderTime.toString().trim().split(",")) {
                            createTextView(time);
                        }
                    }
                }
            }
        }

        private void deleteMedicine(Medicine medicine) {
            Toast.makeText(MedicineListAdapter.this.context, medicine.getId(), 1).show();
        }

        private void setAddedOnDate(long milliSeconds) {
            if (milliSeconds > 0) {
                this.txtAddedOnDate.setText(new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_2, Locale.getDefault()).format(new Date(milliSeconds)));
            }
        }

        private long getDuration(long startDate, long endDate) {
            if (startDate <= 0 || endDate <= 0) {
                return 0;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(startDate);
            Date start = cal.getTime();
            cal.setTimeInMillis(endDate);
            return TimeUnit.MILLISECONDS.toDays(cal.getTime().getTime() - start.getTime());
        }

        private void createTextView(String text) {
            if (text != null && !text.isEmpty()) {
                TextView tvTime = new TextView(MedicineListAdapter.this.context);
                tvTime.setTextColor(-1);
                tvTime.setTextSize(11.0f);
                tvTime.setHeight(85);
                tvTime.setWidth(85);
                tvTime.setText(text);
                tvTime.setGravity(17);
                tvTime.setBackgroundResource(C0644R.drawable.circular_text);
                this.timingsFlowLayout.addView(tvTime);
            }
        }
    }

    public class MedicineViewHolder_ViewBinding implements Unbinder {
        private MedicineViewHolder target;

        @UiThread
        public MedicineViewHolder_ViewBinding(MedicineViewHolder target, View source) {
            this.target = target;
            target.txtMedicineName = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.txt_view_medicine_name, "field 'txtMedicineName'", TextView.class);
            target.txtMedicineNotes = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.txt_view_notes, "field 'txtMedicineNotes'", TextView.class);
            target.txtMedicineNoteValue = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_note_value, "field 'txtMedicineNoteValue'", TextView.class);
            target.txtMedicineTiming = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_timings, "field 'txtMedicineTiming'", TextView.class);
            target.txtAddedOn = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_added_on, "field 'txtAddedOn'", TextView.class);
            target.txtAddedOnDate = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_added_date, "field 'txtAddedOnDate'", TextView.class);
            target.txtRemaining = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_remaining, "field 'txtRemaining'", TextView.class);
            target.txtRemainingDate = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_remaining_value, "field 'txtRemainingDate'", TextView.class);
            target.txtDaysTaken = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_days_taken, "field 'txtDaysTaken'", TextView.class);
            target.txtDaysTakenValue = (TextView) Utils.findRequiredViewAsType(source, C0644R.id.text_view_days_taken_value, "field 'txtDaysTakenValue'", TextView.class);
            target.timingsFlowLayout = (FlowLayout) Utils.findRequiredViewAsType(source, C0644R.id.timings_flow_layout, "field 'timingsFlowLayout'", FlowLayout.class);
        }

        @CallSuper
        public void unbind() {
            MedicineViewHolder target = this.target;
            if (target == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target.txtMedicineName = null;
            target.txtMedicineNotes = null;
            target.txtMedicineNoteValue = null;
            target.txtMedicineTiming = null;
            target.txtAddedOn = null;
            target.txtAddedOnDate = null;
            target.txtRemaining = null;
            target.txtRemainingDate = null;
            target.txtDaysTaken = null;
            target.txtDaysTakenValue = null;
            target.timingsFlowLayout = null;
        }
    }

    public MedicineListAdapter(List<Medicine> medicineList, Context context) {
        this.medicineList = medicineList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MedicineViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0644R.layout.item_view_all_medicines, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ((MedicineViewHolder) holder).bind((Medicine) this.medicineList.get(position));
    }

    public int getItemCount() {
        return this.medicineList.size();
    }
}
