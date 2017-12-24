package com.biz.health.cooey_app.calendar_events;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.utils.DateUtil;
import com.biz.health.cooey_app.vitals.SublimePickerFragment;
import com.biz.health.cooey_app.vitals.SublimePickerFragment.Callback;
import com.cooey.common.vo.FreeSlot;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class BlockedDatesAdapter extends Adapter<BlockedDateViewHolder> {
    Activity context;
    int count;
    long eventEndTime;
    long eventStartTime;
    List<FreeSlot> freeSlots;

    public class BlockedDateViewHolder extends ViewHolder {
        LinearLayout freeTimeSlotsLayout;
        private TextView txtEndDate;
        private TextView txtStartDate;

        public BlockedDateViewHolder(View itemView) {
            super(itemView);
            this.txtStartDate = (TextView) itemView.findViewById(C0644R.id.txt_startDate);
            this.txtEndDate = (TextView) itemView.findViewById(C0644R.id.txt_endDate);
            this.freeTimeSlotsLayout = (LinearLayout) itemView.findViewById(C0644R.id.linear_layout_free_time_slots);
        }
    }

    public BlockedDatesAdapter(Activity context, List<FreeSlot> freeSlots) {
        this.context = context;
        this.freeSlots = freeSlots;
    }

    public BlockedDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BlockedDateViewHolder(LayoutInflater.from(this.context).inflate(C0644R.layout.blocked_dates_layout, parent, false));
    }

    public void onBindViewHolder(BlockedDateViewHolder holder, int position) {
        final FreeSlot freeSlot = (FreeSlot) this.freeSlots.get(position);
        if (freeSlot != null) {
            holder.txtStartDate.setText(DateUtil.getReadableTimeFromDate(new Date(freeSlot.getStartTime())));
            holder.txtEndDate.setText(DateUtil.getReadableTimeFromDate(new Date(freeSlot.getEndTime())));
            holder.freeTimeSlotsLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BlockedDatesAdapter.this.displayDialog("start time", freeSlot.getStartTime(), freeSlot.getEndTime());
                }
            });
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return this.freeSlots.size();
    }

    Pair<Boolean, SublimeOptions> getOptions(long longMills) {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = (0 | 1) | 2;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.TIME_PICKER);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(longMills);
        options.setDateParams(calendar);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }

    public void displayTimePicker(long startTime, long endTime) {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        Pair<Boolean, SublimeOptions> optionsPair = getOptions(startTime);
        if (((Boolean) optionsPair.first).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
            pickerFrag.setArguments(bundle);
            pickerFrag.setStyle(1, 0);
            pickerFrag.show(((AppCompatActivity) this.context).getSupportFragmentManager(), "SUBLIME_PICKER");
            final long j = startTime;
            final long j2 = endTime;
            pickerFrag.setCallback(new Callback() {
                public void onCancelled() {
                    BlockedDatesAdapter.this.count = 0;
                }

                public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
                    Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                    cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                    BlockedDatesAdapter.this.count++;
                    if (cal.getTimeInMillis() < j || cal.getTimeInMillis() > j2) {
                        BlockedDatesAdapter.this.count = 0;
                        Toast.makeText(BlockedDatesAdapter.this.context, "Please select the time within the free slot", 1).show();
                    } else {
                        if (BlockedDatesAdapter.this.count == 1) {
                            BlockedDatesAdapter.this.eventStartTime = cal.getTimeInMillis();
                            BlockedDatesAdapter.this.displayDialog("end time", j, j2);
                        }
                        if (BlockedDatesAdapter.this.count == 2) {
                            BlockedDatesAdapter.this.eventEndTime = cal.getTimeInMillis();
                            Intent intent = new Intent();
                            intent.putExtra("startTime", BlockedDatesAdapter.this.eventStartTime);
                            intent.putExtra("endTime", BlockedDatesAdapter.this.eventEndTime);
                            BlockedDatesAdapter.this.context.setResult(-1, intent);
                            BlockedDatesAdapter.this.context.finish();
                        }
                    }
                    return null;
                }
            });
            return;
        }
        Toast.makeText(this.context, "No pickers activated", 0).show();
    }

    private void displayDialog(String message, long startTime, long endTime) {
        Builder builder = new Builder(this.context);
        builder.setMessage("Please select " + message);
        final long j = startTime;
        final long j2 = endTime;
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BlockedDatesAdapter.this.displayTimePicker(j, j2);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
