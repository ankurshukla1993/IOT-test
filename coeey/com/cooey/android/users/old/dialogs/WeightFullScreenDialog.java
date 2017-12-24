package com.cooey.android.users.old.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.cooey.android.users.old.Weight;
import com.cooey.android.users.old.animation.MyCustomAnimation;
import com.cooey.android.users.old.sublimepicker.SublimePickerFragment;
import com.cooey.android.users.old.sublimepicker.SublimePickerFragment.Callback;
import com.cooey.android.users.old.utils.CTUtility;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.android.users.old.utils.TimeUtil;
import com.cooey.common.vo.User;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class WeightFullScreenDialog extends DialogFragment implements Callback {
    private RadioButton afterExercise;
    private RadioButton beforeExercise;
    private float bmiValue;
    private float bodyfatValue;
    private float bodywaterValue;
    private float boneMassValue;
    private Context context;
    private Date date;
    private String dateText;
    private RadioButton happy;
    private int height;
    private RadioButton indifferent;
    private OnUpdateVitalsList mListener;
    private float muscleMassValue;
    private LinearLayout noteLinearLayout;
    private EditText notesText;
    private String patientId;
    private RadioButton randomExercise;
    private RadioButton sad;
    private ImageView settingShowImage;
    private String timeString;
    private TextView timeText;
    private long timestampText;
    private String userHeight;
    private EditText weight;
    private double weightValue;

    public interface OnUpdateVitalsList {
        void onComplete(Vital vital);
    }

    class C07931 implements OnMenuItemClickListener {
        C07931() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            WeightFullScreenDialog.this.saveWeightData(WeightFullScreenDialog.this.patientId, WeightFullScreenDialog.this.userHeight);
            return false;
        }
    }

    class C07942 implements OnClickListener {
        C07942() {
        }

        public void onClick(View v) {
            WeightFullScreenDialog.this.getDialog().dismiss();
        }
    }

    class C07953 implements OnClickListener {
        C07953() {
        }

        public void onClick(View v) {
            SublimePickerFragment pickerFrag = new SublimePickerFragment();
            pickerFrag.setCallback(WeightFullScreenDialog.this);
            Pair<Boolean, SublimeOptions> optionsPair = WeightFullScreenDialog.this.getOptions();
            if (((Boolean) optionsPair.first).booleanValue()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
                pickerFrag.setArguments(bundle);
                pickerFrag.setStyle(1, 0);
                pickerFrag.show(((AppCompatActivity) WeightFullScreenDialog.this.getActivity()).getSupportFragmentManager(), "SUBLIME_PICKER");
                return;
            }
            Toast.makeText(WeightFullScreenDialog.this.getActivity(), "No pickers activated", 0).show();
        }
    }

    class C07964 implements OnClickListener {
        C07964() {
        }

        public void onClick(View v) {
            if (WeightFullScreenDialog.this.noteLinearLayout.getVisibility() == 0) {
                MyCustomAnimation myCustomAnimation = new MyCustomAnimation(WeightFullScreenDialog.this.noteLinearLayout, 500, 1);
                WeightFullScreenDialog.this.height = myCustomAnimation.getHeight();
                WeightFullScreenDialog.this.noteLinearLayout.startAnimation(myCustomAnimation);
                WeightFullScreenDialog.this.settingShowImage.setImageResource(C0757R.drawable.plus);
                return;
            }
            MyCustomAnimation a = new MyCustomAnimation(WeightFullScreenDialog.this.noteLinearLayout, 500, 0);
            a.setHeight(WeightFullScreenDialog.this.height);
            WeightFullScreenDialog.this.noteLinearLayout.startAnimation(a);
            WeightFullScreenDialog.this.settingShowImage.setImageResource(C0757R.drawable.minus);
        }
    }

    public WeightFullScreenDialog newInstance(String patientId, User user) {
        WeightFullScreenDialog frag = new WeightFullScreenDialog();
        Bundle args = new Bundle();
        if (!(user == null || user.getHeight() == null || user.getHeight().getValue().length() <= 0)) {
            args.putString("userHeight", user.getHeight().getValue());
        }
        args.putString("patientId", patientId);
        frag.setArguments(args);
        frag.setStyle(0, C0757R.style.FullScreenDialogStyle);
        return frag;
    }

    public WeightFullScreenDialog newInstance(String patientId, double weight, float bmi, float bodyfat, float bodyWater, float musclemass, float bonemass) {
        WeightFullScreenDialog frag = new WeightFullScreenDialog();
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        args.putString("userHeight", this.userHeight);
        args.putDouble("weightValue", weight);
        args.putFloat("bmiValue", bmi);
        args.putFloat("bodyfatValue", bodyfat);
        args.putFloat("bodywater", bodyWater);
        args.putFloat("musclemass", musclemass);
        args.putFloat("bonemass", bonemass);
        frag.setArguments(args);
        frag.setStyle(0, C0757R.style.FullScreenDialogStyle);
        return frag;
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
        this.userHeight = getArguments().getString("userHeight");
        this.weightValue = getArguments().getDouble("weightValue");
        this.bmiValue = getArguments().getFloat("bmiValue");
        this.bodyfatValue = getArguments().getFloat("bodyfatValue");
        this.bodywaterValue = getArguments().getFloat("bodywaterValue");
        this.muscleMassValue = getArguments().getFloat("muscleMassValue");
        this.boneMassValue = getArguments().getFloat("boneMassValue");
        setHasOptionsMenu(true);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            Activity a = (Activity) context;
            try {
                this.mListener = (OnUpdateVitalsList) a;
            } catch (ClassCastException e) {
                e.printStackTrace();
                throw new ClassCastException(a.toString() + " must implement OnUpdateListener");
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(C0757R.layout.full_screen_dialog_weight_user, container, false);
        this.weight = (EditText) view.findViewById(C0757R.id.weight);
        this.weight.requestFocus();
        if (this.weightValue != 0.0d) {
            this.weight.setText(String.valueOf(this.weightValue));
        }
        this.notesText = (EditText) view.findViewById(C0757R.id.notes_text);
        TextView helpText = (TextView) view.findViewById(C0757R.id.helpText);
        this.timeText = (TextView) view.findViewById(C0757R.id.timeText);
        RelativeLayout timeLayout = (RelativeLayout) view.findViewById(C0757R.id.timeLinearLayout);
        this.happy = (RadioButton) view.findViewById(C0757R.id.happy_mood);
        this.sad = (RadioButton) view.findViewById(C0757R.id.sad_mood);
        this.indifferent = (RadioButton) view.findViewById(C0757R.id.indifferent_mood);
        this.beforeExercise = (RadioButton) view.findViewById(C0757R.id.before_exercise);
        this.afterExercise = (RadioButton) view.findViewById(C0757R.id.after_exercise);
        this.randomExercise = (RadioButton) view.findViewById(C0757R.id.random);
        this.settingShowImage = (ImageView) view.findViewById(C0757R.id.settingShowImage);
        LinearLayout otherInfoLinearLayout = (LinearLayout) view.findViewById(C0757R.id.otherInfoLinearLayout);
        this.noteLinearLayout = (LinearLayout) view.findViewById(C0757R.id.noteLinearLayout);
        this.date = Calendar.getInstance().getTime();
        this.timestampText = Calendar.getInstance().getTime().getTime();
        this.dateText = DateUtil.getReadableStringFromDate(this.date);
        this.timeText.setText(this.dateText.toUpperCase());
        Toolbar toolbar = (Toolbar) view.findViewById(C0757R.id.toolbar);
        toolbar.setNavigationIcon(C0757R.drawable.ic_action_close);
        toolbar.setTitle("Weight Meter");
        toolbar.setTitleTextColor(-1);
        toolbar.inflateMenu(C0757R.menu.menu_vitals_dialog_save);
        toolbar.setOnMenuItemClickListener(new C07931());
        toolbar.setNavigationOnClickListener(new C07942());
        timeLayout.setOnClickListener(new C07953());
        otherInfoLinearLayout.setOnClickListener(new C07964());
        return view;
    }

    private void saveWeightData(String patientId, String userHeight) {
        String notes = this.notesText.getText().toString();
        if (this.weight.getText().toString().trim().length() <= 0) {
            Toast.makeText(getActivity(), getString(C0757R.string.weight_could_not_be_added), 0).show();
            getDialog().dismiss();
        } else if (this.bmiValue <= 0.0f || this.bodyfatValue <= 0.0f || this.muscleMassValue <= 0.0f || this.bodywaterValue <= 0.0f || this.boneMassValue <= 0.0f) {
            if (userHeight == null || userHeight.length() <= 0) {
                this.bmiValue = 0.0f;
            } else {
                this.bmiValue = CTUtility.calculateBmi(Float.valueOf(userHeight).floatValue(), Float.valueOf(this.weight.getText().toString()).floatValue());
            }
            addWeightForVital(new Weight(this.weight.getText().toString(), "" + this.bmiValue, "", "", "", "", checkContext(), checkMood(), notes), patientId);
        } else {
            addWeightForVital(new Weight(this.weight.getText().toString(), "" + this.bmiValue, "" + this.bodyfatValue, "" + this.muscleMassValue, "" + this.bodywaterValue, "" + this.boneMassValue, checkContext(), checkMood(), notes), patientId);
        }
    }

    private void addWeightForVital(Weight weight, String patientId) {
        Gson gson = new GsonBuilder().create();
        Vital vital = new Vital();
        vital.setVitalType("WEIGHT");
        vital.setParameters(gson.toJson((Object) weight));
        vital.setUserId(patientId);
        vital.setTakenOn(Calendar.getInstance().getTime().getTime());
        vital.setTimestamp(Calendar.getInstance().getTime().getTime());
        this.mListener.onComplete(vital);
        getDialog().dismiss();
    }

    public void onCancelled() {
    }

    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, RecurrenceOption recurrenceOption, String recurrenceRule) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        Date date = cal.getTime();
        this.timeString = TimeUtil.getTimeString(hourOfDay, minute);
        this.dateText = DateUtil.getMonthStringFromDate(date);
        String dayOfDate = DateUtil.getDayString(date);
        this.timestampText = date.getTime();
        this.timeText.setText(dayOfDate + Constants.SPACE + this.dateText + Constants.SPACE + this.timeString);
        return null;
    }

    Pair<Boolean, SublimeOptions> getOptions() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = (0 | 1) | 2;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.DATE_PICKER);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
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

    private boolean getDialogSettings(String key) {
        return getActivity().getSharedPreferences("DialogSettings", 0).getBoolean(key, false);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
