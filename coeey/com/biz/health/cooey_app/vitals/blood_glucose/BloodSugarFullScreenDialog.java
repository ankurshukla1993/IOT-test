package com.biz.health.cooey_app.vitals.blood_glucose;

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
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BloodSugarFullScreenDialog extends DialogFragment implements Callback {
    @BindView(2131361839)
    TextView addBloodSugarText;
    @BindView(2131361845)
    public RadioButton afterFood;
    @BindView(2131361874)
    public RadioButton beforeFood;
    private String dateString;
    private String dateText;
    private float glucose;
    @BindView(2131362152)
    public RadioButton happy;
    @BindView(2131362206)
    public RadioButton indifferent;
    @BindView(2131362349)
    public LinearLayout noteLinearLayout;
    @BindView(2131362354)
    public EditText notesText;
    @BindView(2131362404)
    public RadioButton randomTime;
    @BindView(2131362465)
    public RadioButton sad;
    @BindView(2131362616)
    public TextView timeText;
    private long timestamp;
    @BindView(2131362626)
    Toolbar toolbar;

    class C07121 implements OnMenuItemClickListener {
        C07121() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            BloodSugarFullScreenDialog.this.saveBloodGlucose();
            return false;
        }
    }

    class C07132 implements OnClickListener {
        C07132() {
        }

        public void onClick(View v) {
            BloodSugarFullScreenDialog.this.dismiss();
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(-1, -1);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public BloodSugarFullScreenDialog newInstance() {
        BloodSugarFullScreenDialog frag = new BloodSugarFullScreenDialog();
        frag.setStyle(0, C0644R.style.FullScreenDialogStyle);
        return frag;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(C0644R.layout.full_screen_dialog_blood_sugar_devices, container, false);
        ButterKnife.bind(this, view);
        this.dateString = DateUtil.getReadableStringFromDate(Calendar.getInstance().getTime());
        this.timeText.setText(this.dateString.toUpperCase());
        this.toolbar.setNavigationIcon(C0644R.drawable.ic_action_close);
        this.toolbar.inflateMenu(C0644R.menu.menu_vitals_dialog_save);
        this.toolbar.setOnMenuItemClickListener(new C07121());
        this.toolbar.setNavigationOnClickListener(new C07132());
        return view;
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

    private void saveBloodGlucose() {
        if (this.addBloodSugarText.getText().toString().trim().length() > 0) {
            String glucose = this.addBloodSugarText.getText().toString();
            if (this.notesText.getText().toString().trim().length() > 0) {
                addVitalData(new BloodGlucoseData(glucose, checkTimeMeasure(), checkMood(), this.notesText.getText().toString()));
                return;
            }
            addVitalData(new BloodGlucoseData(glucose, checkTimeMeasure(), checkMood(), ""));
            return;
        }
        Toast.makeText(getActivity(), C0644R.string.blood_sugar_not_added, 0).show();
        getDialog().dismiss();
    }

    private void addVitalData(final BloodGlucoseData data) {
        new Handler().post(new Runnable() {
            public void run() {
                Gson gson = new GsonBuilder().create();
                Vital secondaryVital = new Vital();
                String userId = new PreferenceStore().getUser(BloodSugarFullScreenDialog.this.getActivity()).getId();
                secondaryVital.setTakenBy(userId);
                secondaryVital.setVitalType("BLOOD_GLUCOSE");
                secondaryVital.setParameters(gson.toJson(data));
                secondaryVital.setUserId(userId);
                if (BloodSugarFullScreenDialog.this.timestamp > 0) {
                    secondaryVital.setTimestamp(BloodSugarFullScreenDialog.this.timestamp);
                    secondaryVital.setTakenOn(BloodSugarFullScreenDialog.this.timestamp);
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

    public void onCancelled() {
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
        if (this.afterFood.isChecked()) {
            return "PPBS";
        }
        if (this.beforeFood.isChecked()) {
            return "FBS";
        }
        if (this.randomTime.isChecked()) {
            return "Random";
        }
        return "PPBS";
    }

    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        Date date = cal.getTime();
        this.timestamp = cal.getTimeInMillis();
        this.dateString = TimeUtil.getTimeString(hourOfDay, minute);
        this.dateText = DateUtil.getMonthStringFromDate(date);
        this.timeText.setText(DateUtil.getDayString(date) + Constants.SPACE + this.dateText + Constants.SPACE + this.dateString);
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
