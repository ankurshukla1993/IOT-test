package com.biz.health.cooey_app.vitals.weight;

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
import com.cooey.common.vo.Height;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class WeightFullScreenDialog extends DialogFragment implements Callback {
    @BindView(2131361844)
    RadioButton afterExercise;
    @BindView(2131361873)
    RadioButton beforeExercise;
    private float bmiValue;
    private String dateString;
    private String dateText;
    @BindView(2131362152)
    RadioButton happy;
    @BindView(2131362206)
    RadioButton indifferent;
    @BindView(2131362349)
    LinearLayout noteLinearLayout;
    @BindView(2131362354)
    EditText notesText;
    @BindView(2131362403)
    RadioButton randomExercise;
    @BindView(2131362465)
    RadioButton sad;
    @BindView(2131362616)
    TextView timeText;
    long timestamp;
    @BindView(2131362626)
    Toolbar toolbar;
    @BindView(2131362829)
    EditText weight;

    class C07201 implements OnMenuItemClickListener {
        C07201() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            WeightFullScreenDialog.this.saveBloodGlucose();
            return false;
        }
    }

    class C07212 implements OnClickListener {
        C07212() {
        }

        public void onClick(View v) {
            WeightFullScreenDialog.this.dismiss();
        }
    }

    public WeightFullScreenDialog newInstance() {
        WeightFullScreenDialog weightFullScreenDialog = new WeightFullScreenDialog();
        weightFullScreenDialog.setStyle(0, C0644R.style.FullScreenDialogStyle);
        return weightFullScreenDialog;
    }

    public void onCreate(@Nullable Bundle savendInstanceState) {
        setHasOptionsMenu(true);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0644R.layout.weight_info_dialog, container, false);
        ButterKnife.bind(this, view);
        initToolBar();
        this.dateString = DateUtil.getReadableStringFromDate(Calendar.getInstance().getTime());
        this.timeText.setText(this.dateString.toUpperCase());
        return view;
    }

    public void initToolBar() {
        this.toolbar.setNavigationIcon(C0644R.drawable.ic_action_close);
        this.toolbar.inflateMenu(C0644R.menu.menu_vitals_dialog_save);
        this.toolbar.setOnMenuItemClickListener(new C07201());
        this.toolbar.setNavigationOnClickListener(new C07212());
    }

    private void saveBloodGlucose() {
        Height height = new PreferenceStore().getUser(getActivity()).getHeight();
        String notes = this.notesText.getText().toString();
        if (height == null || height.getValue() == null) {
            this.bmiValue = 0.0f;
        } else {
            this.bmiValue = calculateBmi(Float.valueOf(height.getValue()).floatValue(), Float.valueOf(this.weight.getText().toString()).floatValue());
        }
        addVitalData(new Weight(this.weight.getText().toString(), "" + this.bmiValue, "", "", "", "", checkContext(), checkMood(), notes));
    }

    public static float calculateBmi(float userHeight, float weight) {
        if (userHeight == 0.0f) {
            return 0.0f;
        }
        float height = (float) (((double) userHeight) * 0.01d);
        return Float.valueOf(String.format("%.2f", new Object[]{Float.valueOf(weight / (height * height))})).floatValue();
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

    private String checkContext() {
        if (this.beforeExercise.isChecked()) {
            return "Before Exercise";
        }
        if (this.afterExercise.isChecked()) {
            return "After Exercise";
        }
        if (this.randomExercise.isChecked()) {
            return "Random";
        }
        return "Random";
    }

    private void addVitalData(final Weight data) {
        new Handler().post(new Runnable() {
            public void run() {
                Gson gson = new GsonBuilder().create();
                Vital weightVital = new Vital();
                String userId = new PreferenceStore().getUser(WeightFullScreenDialog.this.getActivity()).getId();
                weightVital.setTakenBy(userId);
                weightVital.setVitalType("WEIGHT");
                weightVital.setParameters(gson.toJson(data));
                weightVital.setUserId(userId);
                if (WeightFullScreenDialog.this.timestamp > 0) {
                    weightVital.setTimestamp(WeightFullScreenDialog.this.timestamp);
                    weightVital.setTakenOn(WeightFullScreenDialog.this.timestamp);
                } else {
                    weightVital.setTimestamp(Calendar.getInstance().getTime().getTime());
                    weightVital.setTakenOn(Calendar.getInstance().getTime().getTime());
                }
                weightVital.setDeviceReading(false);
                weightVital.setPlatform("ANDROID");
            }
        });
        getDialog().dismiss();
    }

    public void onCancelled() {
    }

    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        Date date = cal.getTime();
        this.timestamp = date.getTime();
        this.dateString = TimeUtil.getTimeString(hourOfDay, minute);
        this.dateText = DateUtil.getMonthStringFromDate(date);
        this.timeText.setText(DateUtil.getDayString(date) + Constants.SPACE + this.dateText + Constants.SPACE + this.dateString);
        return null;
    }

    public Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = (0 | 1) | 2;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.DATE_PICKER);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }
}
