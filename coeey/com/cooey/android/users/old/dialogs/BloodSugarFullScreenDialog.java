package com.cooey.android.users.old.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.animation.MyCustomAnimation;
import com.cooey.android.users.old.sublimepicker.SublimePickerFragment;
import com.cooey.android.users.old.sublimepicker.SublimePickerFragment.Callback;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.android.users.old.utils.TimeUtil;
import com.cooey.android.users.old.vitals.BloodGlucoseData;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BloodSugarFullScreenDialog extends DialogFragment implements Callback {
    private EditText addBloodSugarText;
    private RadioButton afterFood;
    private RadioButton beforeFood;
    private TextInputLayout bloodSugarTextInput;
    private String dateString;
    private String dateText;
    private float glucose;
    private RadioButton happy;
    private int height;
    private RadioButton indifferent;
    private EditText insulinText;
    private OnUpdateVitalsList mListener;
    private LinearLayout noteLinearLayout;
    private EditText notesText;
    private String patientId;
    private RadioButton randomTime;
    private RadioButton sad;
    private ImageView settingShowImage;
    private LinearLayout showLinearLayout;
    private TextView timeText;
    private long timestamp;
    private TextView unitsTextView;

    public interface OnUpdateVitalsList {
        void onComplete(Vital vital);
    }

    class C07881 implements OnMenuItemClickListener {
        C07881() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            BloodSugarFullScreenDialog.this.saveBloodGlucose(BloodSugarFullScreenDialog.this.patientId);
            return false;
        }
    }

    class C07892 implements OnClickListener {
        C07892() {
        }

        public void onClick(View v) {
            BloodSugarFullScreenDialog.this.getDialog().dismiss();
        }
    }

    class C07903 implements OnClickListener {
        C07903() {
        }

        public void onClick(View v) {
            SublimePickerFragment pickerFrag = new SublimePickerFragment();
            pickerFrag.setCallback(BloodSugarFullScreenDialog.this);
            Pair<Boolean, SublimeOptions> optionsPair = BloodSugarFullScreenDialog.this.getOptions();
            if (((Boolean) optionsPair.first).booleanValue()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
                pickerFrag.setArguments(bundle);
                pickerFrag.setStyle(1, 0);
                pickerFrag.show(((AppCompatActivity) BloodSugarFullScreenDialog.this.getActivity()).getSupportFragmentManager(), "SUBLIME_PICKER");
                return;
            }
            Toast.makeText(BloodSugarFullScreenDialog.this.getActivity(), "No pickers activated", 0).show();
        }
    }

    class C07914 implements OnClickListener {
        C07914() {
        }

        public void onClick(View v) {
            if (BloodSugarFullScreenDialog.this.showLinearLayout.getVisibility() == 0) {
                BloodSugarFullScreenDialog.this.settingShowImage.setImageResource(C0757R.drawable.plus);
                MyCustomAnimation myCustomAnimation = new MyCustomAnimation(BloodSugarFullScreenDialog.this.showLinearLayout, 500, 1);
                BloodSugarFullScreenDialog.this.height = myCustomAnimation.getHeight();
                BloodSugarFullScreenDialog.this.showLinearLayout.startAnimation(myCustomAnimation);
                return;
            }
            BloodSugarFullScreenDialog.this.settingShowImage.setImageResource(C0757R.drawable.minus);
            MyCustomAnimation a = new MyCustomAnimation(BloodSugarFullScreenDialog.this.showLinearLayout, 500, 0);
            a.setHeight(BloodSugarFullScreenDialog.this.height);
            BloodSugarFullScreenDialog.this.showLinearLayout.startAnimation(a);
        }
    }

    class C07925 implements OnClickListener {
        C07925() {
        }

        public void onClick(View v) {
            if (BloodSugarFullScreenDialog.this.noteLinearLayout.getVisibility() == 0) {
                MyCustomAnimation myCustomAnimation = new MyCustomAnimation(BloodSugarFullScreenDialog.this.noteLinearLayout, 500, 1);
                BloodSugarFullScreenDialog.this.height = myCustomAnimation.getHeight();
                BloodSugarFullScreenDialog.this.noteLinearLayout.startAnimation(myCustomAnimation);
                BloodSugarFullScreenDialog.this.settingShowImage.setImageResource(C0757R.drawable.plus);
                return;
            }
            MyCustomAnimation a = new MyCustomAnimation(BloodSugarFullScreenDialog.this.noteLinearLayout, 500, 0);
            a.setHeight(BloodSugarFullScreenDialog.this.height);
            BloodSugarFullScreenDialog.this.noteLinearLayout.startAnimation(a);
            BloodSugarFullScreenDialog.this.settingShowImage.setImageResource(C0757R.drawable.minus);
        }
    }

    public BloodSugarFullScreenDialog newInstance(String patientId) {
        BloodSugarFullScreenDialog frag = new BloodSugarFullScreenDialog();
        frag.setStyle(0, C0757R.style.FullScreenDialogStyle);
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        frag.setArguments(args);
        return frag;
    }

    public BloodSugarFullScreenDialog newInstance(String patientId, float glucose) {
        BloodSugarFullScreenDialog frag = new BloodSugarFullScreenDialog();
        frag.setStyle(0, C0757R.style.FullScreenDialogStyle);
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        args.putFloat("glucose", glucose);
        frag.setArguments(args);
        return frag;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            Activity a = (Activity) context;
            try {
                this.mListener = (OnUpdateVitalsList) a;
            } catch (ClassCastException e) {
                throw new ClassCastException(a.toString() + " must implement OnUpdateListener");
            }
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(-1, -1);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.patientId = getArguments().getString("patientId");
        this.glucose = getArguments().getFloat("glucose");
        setHasOptionsMenu(true);
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(C0757R.layout.full_screen_dialog_blood_sugar_user, container, false);
        this.addBloodSugarText = (AppCompatEditText) view.findViewById(C0757R.id.addBloodSugarText);
        if (this.glucose != 0.0f) {
            this.addBloodSugarText.setText(String.valueOf(this.glucose));
        }
        this.notesText = (AppCompatEditText) view.findViewById(C0757R.id.notes_text);
        TextView helpText = (TextView) view.findViewById(C0757R.id.helpText);
        this.timeText = (TextView) view.findViewById(C0757R.id.timeText);
        RelativeLayout timeLayout = (RelativeLayout) view.findViewById(C0757R.id.timeLinearLayout);
        this.happy = (RadioButton) view.findViewById(C0757R.id.happy_mood);
        this.sad = (RadioButton) view.findViewById(C0757R.id.sad_mood);
        this.indifferent = (RadioButton) view.findViewById(C0757R.id.indifferent_mood);
        this.afterFood = (RadioButton) view.findViewById(C0757R.id.after_food);
        this.beforeFood = (RadioButton) view.findViewById(C0757R.id.before_food);
        this.randomTime = (RadioButton) view.findViewById(C0757R.id.random_time);
        RadioButton radioBolus = (RadioButton) view.findViewById(C0757R.id.radioBolus);
        RadioButton radioBasal = (RadioButton) view.findViewById(C0757R.id.radioBasal);
        this.insulinText = (AppCompatEditText) view.findViewById(C0757R.id.addInsulinText);
        RadioButton radioPremix = (RadioButton) view.findViewById(C0757R.id.radioPreMix);
        LinearLayout insulinLinearLayout = (LinearLayout) view.findViewById(C0757R.id.insulinLinearLayout);
        this.showLinearLayout = (LinearLayout) view.findViewById(C0757R.id.showLinearLayout);
        this.bloodSugarTextInput = (TextInputLayout) view.findViewById(C0757R.id.bloodSugarTextInput);
        LinearLayout otherInfoLinearLayout = (LinearLayout) view.findViewById(C0757R.id.otherInfoLinearLayout);
        this.noteLinearLayout = (LinearLayout) view.findViewById(C0757R.id.noteLinearLayout);
        this.settingShowImage = (ImageView) view.findViewById(C0757R.id.settingShowImage);
        radioBolus.setChecked(true);
        Date date = Calendar.getInstance().getTime();
        this.timestamp = Calendar.getInstance().getTime().getTime();
        this.dateString = DateUtil.getReadableStringFromDate(date);
        this.timeText.setText(this.dateString.toUpperCase());
        Toolbar toolbar = (Toolbar) view.findViewById(C0757R.id.toolbar);
        toolbar.setNavigationIcon(C0757R.drawable.ic_action_close);
        toolbar.inflateMenu(C0757R.menu.menu_vitals_dialog_save);
        toolbar.setOnMenuItemClickListener(new C07881());
        toolbar.setNavigationOnClickListener(new C07892());
        timeLayout.setOnClickListener(new C07903());
        insulinLinearLayout.setOnClickListener(new C07914());
        otherInfoLinearLayout.setOnClickListener(new C07925());
        return view;
    }

    private void saveBloodGlucose(String patientId) {
        if (this.addBloodSugarText.getText().toString().trim().length() > 0) {
            String glucose = this.addBloodSugarText.getText().toString();
            if (this.notesText.getText().toString().trim().length() > 0) {
                addVitalData(new BloodGlucoseData(glucose, checkTimeMeasure(), checkMood(), this.notesText.getText().toString()), patientId);
                return;
            }
            addVitalData(new BloodGlucoseData(glucose, checkTimeMeasure(), checkMood(), ""), patientId);
            return;
        }
        Toast.makeText(getActivity(), getString(C0757R.string.blood_sugar_not_added), 0).show();
        getDialog().dismiss();
    }

    private void addVitalData(BloodGlucoseData data, String patientId) {
        Gson gson = new GsonBuilder().create();
        Vital vital = new Vital();
        vital.setVitalType("BLOOD_GLUCOSE");
        vital.setParameters(gson.toJson((Object) data));
        vital.setUserId(patientId);
        vital.setTakenOn(Calendar.getInstance().getTime().getTime());
        vital.setTimestamp(Calendar.getInstance().getTime().getTime());
        this.mListener.onComplete(vital);
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
        this.dateString = TimeUtil.getTimeString(hourOfDay, minute);
        this.dateText = DateUtil.getMonthStringFromDate(date);
        String dayOfDate = DateUtil.getDayString(date);
        this.timestamp = date.getTime();
        this.timeText.setText(dayOfDate + Constants.SPACE + this.dateText + Constants.SPACE + this.dateString);
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
