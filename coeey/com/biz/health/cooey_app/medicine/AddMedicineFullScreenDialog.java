package com.biz.health.cooey_app.medicine;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.vitals.SublimePickerFragment;
import com.biz.health.cooey_app.vitals.SublimePickerFragment.Callback;
import com.cooey.common.realm_store.ReminderStore;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.MedicineSearch;
import com.cooey.common.vo.Reminder;
import com.cooey.common.vo.Session;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import io.realm.RealmList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class AddMedicineFullScreenDialog extends DialogFragment implements OnClickListener, Callback {
    private Activity activity;
    int flag;
    FloatingActionButton floatingAddMedicineReminderTime;
    long longFromDate;
    long longToDate;
    private OnUpdateMedicineRemiderList mListener;
    private Medicine medicine;
    private MedicineReminderSchedule medicineReminderSchedule;
    private MedicinesScheduleAdapter medicineScheduleAdapter;
    private RealmList<Reminder> medicineScheduleList;
    private MedicineSearch medicineSearch;
    private String patientId;
    RecyclerView recylerScheduleMedcine;
    private Session session;
    TextView txtFromDate;
    @BindView(2131362751)
    TextView txtMedicineName;
    TextView txtNoReminder;
    TextView txtNotes;
    TextView txtToDate;

    class C06871 implements OnMenuItemClickListener {
        C06871() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            AddMedicineFullScreenDialog.this.saveMedicineReminder();
            return false;
        }
    }

    class C06882 implements OnClickListener {
        C06882() {
        }

        public void onClick(View v) {
            AddMedicineFullScreenDialog.this.getDialog().dismiss();
        }
    }

    static class C06893 implements DialogInterface.OnClickListener {
        C06893() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    public interface OnUpdateMedicineRemiderList {
        void onMedicineReminderAdded(Medicine medicine);
    }

    public AddMedicineFullScreenDialog newInstance(MedicineSearch medicineSearch) {
        AddMedicineFullScreenDialog frag = new AddMedicineFullScreenDialog();
        frag.setStyle(0, C0644R.style.FullScreenDialogStyle);
        Bundle args = new Bundle();
        args.putParcelable(CooeySQLHelper.COL_MEDID, medicineSearch);
        frag.setArguments(args);
        return frag;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(-1, -1);
            if (VERSION.SDK_INT >= 21) {
                getDialog().getWindow().setStatusBarColor(getResources().getColor(C0644R.color.colorPrimaryDark));
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.medicineSearch = (MedicineSearch) getArguments().getParcelable(CooeySQLHelper.COL_MEDID);
        this.medicineScheduleList = new RealmList();
        this.activity = getActivity();
        setHasOptionsMenu(true);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnUpdateMedicineRemiderList) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnUpdateListener");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0644R.layout.full_screen_dialog_medicine, container, false);
        ButterKnife.bind(view);
        this.recylerScheduleMedcine = (RecyclerView) view.findViewById(C0644R.id.recyler_medcine_schedule);
        this.medicine = new Medicine(null, this.medicineSearch.getName(), null, null, null, null);
        this.medicineReminderSchedule = new MedicineReminderSchedule();
        this.txtNotes = (TextView) view.findViewById(C0644R.id.notes_text);
        this.floatingAddMedicineReminderTime = (FloatingActionButton) view.findViewById(C0644R.id.fab_add_schedule);
        this.floatingAddMedicineReminderTime.setOnClickListener(this);
        this.txtNoReminder = (TextView) view.findViewById(C0644R.id.txt_no_reminder);
        this.txtNoReminder.setVisibility(0);
        this.session = new PreferenceStore().getSession(getActivity());
        this.medicineScheduleAdapter = new MedicinesScheduleAdapter(this.medicineScheduleList, getActivity(), this.medicineSearch.getId());
        this.txtFromDate = (TextView) view.findViewById(C0644R.id.txtFromDate);
        this.txtToDate = (TextView) view.findViewById(C0644R.id.txtToDate);
        this.txtFromDate.setOnClickListener(this);
        this.txtToDate.setOnClickListener(this);
        initToolBar(view);
        ((TextView) view.findViewById(C0644R.id.txt_view_medicine_name)).setText(this.medicineSearch.getName());
        initRecylerView();
        return view;
    }

    private void initToolBar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(C0644R.id.toolbar);
        toolbar.setNavigationIcon(C0644R.drawable.ic_action_close);
        toolbar.inflateMenu(C0644R.menu.menu_vitals_dialog_save);
        toolbar.setOnMenuItemClickListener(new C06871());
        toolbar.setNavigationOnClickListener(new C06882());
    }

    private void saveMedicineReminder() {
        PreferenceStore preferenceStore = new PreferenceStore();
        String notesText = this.txtNotes.getText().toString();
        this.medicineScheduleList = this.medicineScheduleAdapter.getMedicinescheduleList();
        this.medicine.setReminders(this.medicineScheduleList);
        if (this.longFromDate <= 0 || this.longToDate <= 0) {
            buildErrorDialog(getActivity(), getString(C0644R.string.please_add_from_date) + " and " + getString(C0644R.string.please_add_to_date));
        } else if (getDuration(this.longFromDate, this.longToDate) >= 0) {
            this.medicine.setAddedOn(this.longFromDate);
            this.medicine.setToBeTakenTill(this.longToDate);
            if (this.medicineScheduleList != null && this.medicineScheduleList.size() > 0) {
                this.medicine.setReminders(this.medicineScheduleList);
                Iterator it = this.medicineScheduleList.iterator();
                while (it.hasNext()) {
                    Reminder reminder = (Reminder) it.next();
                    if (reminder.getTimeOfDay() != null) {
                        ReminderStore.getInstance(getActivity()).writeToMedicine(reminder);
                    }
                }
            }
            this.medicine.setUserId(this.session.getUserId());
            this.medicine.setNotes(notesText);
            this.mListener.onMedicineReminderAdded(this.medicine);
            getDialog().dismiss();
            getActivity().startActivity(new Intent(getActivity(), ViewAllMedicinesActivity.class));
        } else {
            buildErrorDialog(getActivity(), "From date can not be ahead of End date");
        }
    }

    private void initRecylerView() {
        this.recylerScheduleMedcine.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recylerScheduleMedcine.setAdapter(this.medicineScheduleAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C0644R.id.fab_add_schedule:
                addNewTmeForSchedule();
                return;
            case C0644R.id.txtFromDate:
                this.flag = 1;
                displayCalendarView();
                return;
            case C0644R.id.txtToDate:
                this.flag = 2;
                displayCalendarView();
                return;
            default:
                return;
        }
    }

    public void onCancelled() {
    }

    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        if (this.flag == 1) {
            this.longFromDate = cal.getTime().getTime();
            this.txtFromDate.setText(new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_2, Locale.getDefault()).format(cal.getTime()));
        }
        if (this.flag == 2) {
            this.longToDate = cal.getTime().getTime();
            this.txtToDate.setText(new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_2, Locale.getDefault()).format(cal.getTime()));
        }
        return null;
    }

    private void addNewTmeForSchedule() {
        this.medicineScheduleList.add(new Reminder());
        this.medicineScheduleAdapter.notifyItemChanged(this.medicineScheduleList.size() - 1);
        this.txtNoReminder.setVisibility(8);
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

    private void displayCalendarView() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(this);
        Pair<Boolean, SublimeOptions> optionsPair = getOptions();
        if (((Boolean) optionsPair.first).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
            pickerFrag.setArguments(bundle);
            pickerFrag.setStyle(1, 0);
            pickerFrag.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "SUBLIME_PICKER");
            return;
        }
        Toast.makeText(getActivity(), "No pickers activated", 0).show();
    }

    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = (0 | 1) | 2;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.DATE_PICKER);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }

    public static AlertDialog buildErrorDialog(Context context, String message) {
        Builder alertBuilder = new Builder(context);
        alertBuilder.setTitle(context.getResources().getString(C0644R.string.error_dialog_title));
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton(context.getResources().getString(C0644R.string.error_dialog_btn), new C06893());
        return alertBuilder.show();
    }
}
