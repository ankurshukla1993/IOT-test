package com.biz.health.cooey_app.vitals.bp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.utils.DateUtil;
import com.biz.health.cooey_app.utils.TimeUtil;
import com.biz.health.cooey_app.vitals.SublimePickerFragment;
import com.biz.health.cooey_app.vitals.SublimePickerFragment.Callback;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BPFullScreenDialog extends DialogFragment implements Callback {
    private String dateText;
    @BindView(2131362060)
    public EditText diastolicText;
    @BindView(2131362152)
    public RadioButton happy;
    @BindView(2131362156)
    public EditText heartBeatText;
    @BindView(2131362206)
    public RadioButton indifferent;
    @BindView(2131362232)
    public RadioButton leftHand;
    @BindView(2131362349)
    public LinearLayout noteLinearLayout;
    @BindView(2131362354)
    public EditText notesText;
    @BindView(2131362445)
    public RadioButton rightHand;
    @BindView(2131362465)
    public RadioButton sad;
    @BindView(2131362568)
    public EditText systolicText;
    private String timeString;
    @BindView(2131362616)
    public TextView timeText;
    private long timestamp;
    @BindView(2131362626)
    public Toolbar toolbar;

    class C07161 implements OnMenuItemClickListener {
        C07161() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            BPFullScreenDialog.this.saveBPData();
            return false;
        }
    }

    class C07172 implements OnClickListener {
        C07172() {
        }

        public void onClick(View v) {
            BPFullScreenDialog.this.dismiss();
        }
    }

    public BPFullScreenDialog newIntance() {
        BPFullScreenDialog bpFullScreenDialog = new BPFullScreenDialog();
        bpFullScreenDialog.setStyle(0, C0644R.style.FullScreenDialogStyle);
        return bpFullScreenDialog;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(-1, -1);
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0644R.layout.full_screen_dialog_bp, container, false);
        ButterKnife.bind(this, view);
        initToolBar();
        this.systolicText.requestFocus();
        this.dateText = DateUtil.getReadableStringFromDate(Calendar.getInstance().getTime());
        this.timeText.setText(this.dateText.toUpperCase());
        return view;
    }

    private void initToolBar() {
        this.toolbar.setNavigationIcon(C0644R.drawable.ic_action_close);
        this.toolbar.setTitle("BP Meter");
        this.toolbar.setTitleTextColor(-1);
        this.toolbar.inflateMenu(C0644R.menu.menu_vitals_dialog_save);
        this.toolbar.setOnMenuItemClickListener(new C07161());
        this.toolbar.setNavigationOnClickListener(new C07172());
    }

    public void onCancelled() {
    }

    @OnClick({2131362614})
    public void setTimeText() {
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

    private void saveBPData() {
        String notes = this.notesText.getText().toString();
        if (this.systolicText.getText().toString().trim().length() <= 0 || this.diastolicText.getText().toString().length() <= 0) {
            Toast.makeText(getActivity(), C0644R.string.bp_could_not_add, 0).show();
            getDialog().dismiss();
        } else if (this.heartBeatText.getText().toString().length() > 0) {
            addBPForVital(new BPData(this.diastolicText.getText().toString(), this.systolicText.getText().toString(), this.heartBeatText.getText().toString(), checkTimeMeasure(), checkMood(), notes));
        } else {
            addBPForVital(new BPData(this.diastolicText.getText().toString(), this.systolicText.getText().toString(), null, checkTimeMeasure(), checkMood(), notes));
        }
    }

    private void addBPForVital(final BPData bpData) {
        new Handler().post(new Runnable() {
            public void run() {
                Gson gson = new GsonBuilder().create();
                Vital secondaryVital = new Vital();
                String userId = new PreferenceStore().getUser(BPFullScreenDialog.this.getActivity()).getId();
                secondaryVital.setTakenBy(userId);
                secondaryVital.setVitalType("BLOOD_PRESSURE");
                secondaryVital.setParameters(gson.toJson(bpData));
                secondaryVital.setUserId(userId);
                if (BPFullScreenDialog.this.timestamp > 0) {
                    secondaryVital.setTimestamp(BPFullScreenDialog.this.timestamp);
                    secondaryVital.setTakenOn(BPFullScreenDialog.this.timestamp);
                } else {
                    secondaryVital.setTimestamp(Calendar.getInstance().getTime().getTime());
                    secondaryVital.setTakenOn(Calendar.getInstance().getTime().getTime());
                }
                secondaryVital.setDeviceReading(false);
                secondaryVital.setPlatform("ANDROID");
            }
        });
        getDialog().dismiss();
    }

    private String checkMood() {
        if (this.happy.isChecked()) {
            return "Happy";
        }
        if (this.sad.isChecked()) {
            return "Sad";
        }
        if (this.indifferent.isChecked()) {
            return "Indifferent";
        }
        return "Happy";
    }

    private String checkTimeMeasure() {
        if (this.leftHand.isChecked()) {
            return CTConstants.LEFTHAND;
        }
        if (this.rightHand.isChecked()) {
            return CTConstants.RIGHTHAND;
        }
        return CTConstants.RIGHTHAND;
    }

    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        Date date = cal.getTime();
        this.timestamp = date.getTime();
        this.timeString = TimeUtil.getTimeString(hourOfDay, minute);
        this.dateText = DateUtil.getMonthStringFromDate(date);
        this.timeText.setText(DateUtil.getDayString(date) + Constants.SPACE + this.dateText + Constants.SPACE + this.timeString);
        return null;
    }

    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = (0 | 1) | 2;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.DATE_PICKER);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }
}
