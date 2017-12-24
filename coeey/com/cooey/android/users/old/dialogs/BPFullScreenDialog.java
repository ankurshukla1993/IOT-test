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
import com.cooey.android.users.old.sublimepicker.SublimePickerFragment;
import com.cooey.android.users.old.sublimepicker.SublimePickerFragment.Callback;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.android.users.old.utils.TimeUtil;
import com.cooey.android.users.old.vitals.BPData;
import com.cooey.common.vo.Vital;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BPFullScreenDialog extends DialogFragment implements Callback {
    private Context context;
    private Date date;
    private String dateText;
    private int diastolic;
    private EditText diastolicText;
    private RadioButton happy;
    private EditText heartBeatText;
    private int heartRate;
    private int height;
    private RadioButton indifferent;
    private RadioButton leftHand;
    private OnUpdateVitalsList mListener;
    private LinearLayout noteLinearLayout;
    private EditText notesText;
    private String patientId;
    private RadioButton rightHand;
    private RadioButton sad;
    private ImageView settingShowImage;
    private int systolic;
    private EditText systolicText;
    private String timeString;
    private TextView timeText;
    private long timestampText;

    public interface OnUpdateVitalsList {
        void onComplete(Vital vital);
    }

    class C07851 implements OnMenuItemClickListener {
        C07851() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            BPFullScreenDialog.this.saveBPData(BPFullScreenDialog.this.patientId);
            return false;
        }
    }

    class C07862 implements OnClickListener {
        C07862() {
        }

        public void onClick(View v) {
            BPFullScreenDialog.this.getDialog().dismiss();
        }
    }

    class C07873 implements OnClickListener {
        C07873() {
        }

        public void onClick(View v) {
            SublimePickerFragment pickerFrag = new SublimePickerFragment();
            pickerFrag.setCallback(BPFullScreenDialog.this);
            Pair<Boolean, SublimeOptions> optionsPair = BPFullScreenDialog.this.getOptions();
            if (((Boolean) optionsPair.first).booleanValue()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("SUBLIME_OPTIONS", (Parcelable) optionsPair.second);
                pickerFrag.setArguments(bundle);
                pickerFrag.setStyle(1, 0);
                pickerFrag.show(((AppCompatActivity) BPFullScreenDialog.this.getActivity()).getSupportFragmentManager(), "SUBLIME_PICKER");
                return;
            }
            Toast.makeText(BPFullScreenDialog.this.getActivity(), "No pickers activated", 0).show();
        }
    }

    public BPFullScreenDialog newInstance(String patientId) {
        BPFullScreenDialog frag = new BPFullScreenDialog();
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        frag.setArguments(args);
        frag.setStyle(0, C0757R.style.FullScreenDialogStyle);
        return frag;
    }

    public BPFullScreenDialog newInstance(String patientId, int systolic, int diastolic, int heartRate) {
        BPFullScreenDialog frag = new BPFullScreenDialog();
        Bundle args = new Bundle();
        args.putString("patientId", patientId);
        args.putInt(CooeySQLHelper.COL_SYS, systolic);
        args.putInt(CooeySQLHelper.COL_DIA, diastolic);
        args.putInt("heartRate", heartRate);
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
        this.systolic = getArguments().getInt(CooeySQLHelper.COL_SYS);
        this.diastolic = getArguments().getInt(CooeySQLHelper.COL_DIA);
        this.heartRate = getArguments().getInt("heartRate");
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
        View view = inflater.inflate(C0757R.layout.full_screen_dialog_bp_user, container, false);
        this.systolicText = (EditText) view.findViewById(C0757R.id.sysval);
        this.systolicText.requestFocus();
        if (this.systolic != 0) {
            this.systolicText.setText(String.valueOf(this.systolic));
        }
        this.diastolicText = (EditText) view.findViewById(C0757R.id.diaval);
        if (this.diastolic != 0) {
            this.diastolicText.setText(String.valueOf(this.diastolic));
        }
        this.heartBeatText = (EditText) view.findViewById(C0757R.id.heartbeatVal);
        if (this.heartRate != 0) {
            this.heartBeatText.setText(String.valueOf(this.heartRate));
        }
        this.notesText = (EditText) view.findViewById(C0757R.id.notes_text);
        TextView helpText = (TextView) view.findViewById(C0757R.id.helpText);
        this.timeText = (TextView) view.findViewById(C0757R.id.timeText);
        RelativeLayout timeLayout = (RelativeLayout) view.findViewById(C0757R.id.timeLinearLayout);
        this.happy = (RadioButton) view.findViewById(C0757R.id.happy_mood);
        this.sad = (RadioButton) view.findViewById(C0757R.id.sad_mood);
        this.indifferent = (RadioButton) view.findViewById(C0757R.id.indifferent_mood);
        this.leftHand = (RadioButton) view.findViewById(C0757R.id.left_hand);
        this.rightHand = (RadioButton) view.findViewById(C0757R.id.right_hand);
        this.settingShowImage = (ImageView) view.findViewById(C0757R.id.settingShowImage);
        LinearLayout otherInfoLinearLayout = (LinearLayout) view.findViewById(C0757R.id.otherInfoLinearLayout);
        this.noteLinearLayout = (LinearLayout) view.findViewById(C0757R.id.noteLinearLayout);
        this.date = Calendar.getInstance().getTime();
        this.timestampText = Calendar.getInstance().getTime().getTime();
        this.dateText = DateUtil.getReadableStringFromDate(this.date);
        this.timeText.setText(this.dateText.toUpperCase());
        Toolbar toolbar = (Toolbar) view.findViewById(C0757R.id.toolbar);
        toolbar.setNavigationIcon(C0757R.drawable.ic_action_close);
        toolbar.setTitle("BP Meter");
        toolbar.setTitleTextColor(-1);
        toolbar.inflateMenu(C0757R.menu.menu_vitals_dialog_save);
        toolbar.setOnMenuItemClickListener(new C07851());
        toolbar.setNavigationOnClickListener(new C07862());
        timeLayout.setOnClickListener(new C07873());
        return view;
    }

    private void saveBPData(String patientId) {
        String notes = this.notesText.getText().toString();
        if (this.systolicText.getText().toString().trim().length() <= 0 || this.diastolicText.getText().toString().length() <= 0) {
            Toast.makeText(getActivity(), getString(C0757R.string.bp_could_not_add), 0).show();
            getDialog().dismiss();
        } else if (this.heartBeatText.getText().toString().length() > 0) {
            addBPForVital(new BPData(this.diastolicText.getText().toString(), this.systolicText.getText().toString(), this.heartBeatText.getText().toString(), checkTimeMeasure(), checkMood(), notes), patientId);
        } else {
            addBPForVital(new BPData(this.diastolicText.getText().toString(), this.systolicText.getText().toString(), null, checkTimeMeasure(), checkMood(), notes), patientId);
        }
    }

    private void addBPForVital(BPData bpData, String patientId) {
        Gson gson = new GsonBuilder().create();
        Vital vital = new Vital();
        vital.setVitalType("BLOOD_PRESSURE");
        vital.setParameters(gson.toJson((Object) bpData));
        vital.setUserId(patientId);
        vital.setTimestamp(Calendar.getInstance().getTime().getTime());
        vital.setTakenOn(Calendar.getInstance().getTime().getTime());
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

    private String checkTimeMeasure() {
        if (this.leftHand.isChecked()) {
            return CTConstants.LEFTHAND;
        }
        if (this.rightHand.isChecked()) {
            return CTConstants.RIGHTHAND;
        }
        return CTConstants.RIGHTHAND;
    }

    private boolean getDialogSettings(String key) {
        return getActivity().getSharedPreferences("DialogSettings", 0).getBoolean(key, false);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
