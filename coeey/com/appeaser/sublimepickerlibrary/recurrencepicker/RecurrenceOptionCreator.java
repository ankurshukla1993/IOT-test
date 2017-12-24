package com.appeaser.sublimepickerlibrary.recurrencepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TimeFormatException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.common.DecisionButtonLayout;
import com.appeaser.sublimepickerlibrary.common.DecisionButtonLayout.Callback;
import com.appeaser.sublimepickerlibrary.datepicker.RecurrenceEndDatePicker;
import com.appeaser.sublimepickerlibrary.datepicker.RecurrenceEndDatePicker.OnDateSetListener;
import com.appeaser.sublimepickerlibrary.drawables.CheckableDrawable;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.appeaser.sublimepickerlibrary.utilities.RecurrenceUtils;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class RecurrenceOptionCreator extends FrameLayout implements OnItemSelectedListener, OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, OnClickListener, OnDateSetListener {
    private static final int COUNT_DEFAULT = 5;
    private static final int COUNT_MAX = 730;
    private static final int FIFTH_WEEK_IN_A_MONTH = 5;
    private static final int INTERVAL_DEFAULT = 1;
    private static final int INTERVAL_MAX = 99;
    private static final int LAST_NTH_DAY_OF_WEEK = -1;
    private static final String TAG = "RecurrenceOptionCreator";
    private static final int[] mFreqModelToEventRecurrence = new int[]{4, 5, 6, 7};
    private final int[] TIME_DAY_TO_CALENDAR_DAY;
    private DecisionButtonLayout mButtonLayout;
    private Callback mButtonLayoutCallback;
    private RecurrenceEndDatePicker mDateOnlyPicker;
    private EditText mEndCount;
    private String mEndCountLabel;
    private DateFormat mEndDateFormatter;
    private String mEndDateLabel;
    private TextView mEndDateTextView;
    private String mEndNeverStr;
    private Spinner mEndSpinner;
    private EndSpinnerAdapter mEndSpinnerAdapter;
    private ArrayList<CharSequence> mEndSpinnerArray;
    private Spinner mFreqSpinner;
    int mHeaderBackgroundColor;
    private boolean mHidePostEndCount;
    private EditText mInterval;
    private TextView mIntervalPostText;
    private TextView mIntervalPreText;
    private int mIntervalResId;
    private RecurrenceModel mModel;
    private String mMonthRepeatByDayOfWeekStr;
    private String[][] mMonthRepeatByDayOfWeekStrs;
    private RadioGroup mMonthRepeatByRadioGroup;
    private TextView mPostEndCount;
    private EventRecurrence mRecurrence;
    private View mRecurrencePicker;
    private OnRecurrenceSetListener mRecurrenceSetListener;
    private RadioButton mRepeatMonthlyByNthDayOfMonth;
    private RadioButton mRepeatMonthlyByNthDayOfWeek;
    private Resources mResources;
    private Time mTime;
    private Toast mToast;
    private WeekButton[] mWeekByDayButtons;
    private LinearLayout mWeekGroup;
    private LinearLayout mWeekGroup2;

    class C05881 implements Callback {
        C05881() {
        }

        public void onOkay() {
            String rrule;
            if (RecurrenceOptionCreator.this.mModel.recurrenceState == 0) {
                rrule = null;
            } else {
                RecurrenceOptionCreator.copyModelToEventRecurrence(RecurrenceOptionCreator.this.mModel, RecurrenceOptionCreator.this.mRecurrence);
                rrule = RecurrenceOptionCreator.this.mRecurrence.toString();
            }
            RecurrenceOptionCreator.this.mRecurrenceSetListener.onRecurrenceSet(rrule);
        }

        public void onCancel() {
            RecurrenceOptionCreator.this.mRecurrenceSetListener.onCancelled();
        }
    }

    class minMaxTextWatcher implements TextWatcher {
        private int mDefault;
        private int mMax;
        private int mMin;

        public minMaxTextWatcher(int min, int defaultInt, int max) {
            this.mMin = min;
            this.mMax = max;
            this.mDefault = defaultInt;
        }

        public void afterTextChanged(Editable s) {
            int value;
            boolean updated = false;
            try {
                value = Integer.parseInt(s.toString());
            } catch (NumberFormatException e) {
                value = this.mDefault;
            }
            if (value < this.mMin) {
                value = this.mMin;
                updated = true;
            } else if (value > this.mMax) {
                updated = true;
                value = this.mMax;
            }
            if (updated) {
                s.clear();
                s.append(Integer.toString(value));
            }
            RecurrenceOptionCreator.this.updateDoneButtonState();
            onChange(value);
        }

        void onChange(int value) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    private enum CurrentView {
        RECURRENCE_PICKER,
        DATE_ONLY_PICKER
    }

    private class EndSpinnerAdapter extends ArrayAdapter<CharSequence> {
        final String END_COUNT_MARKER = "%d";
        final String END_DATE_MARKER = "%s";
        private int mDropDownLayoutId;
        private String mEndDateString;
        private LayoutInflater mInflater;
        private int mItemLayoutId;
        private ArrayList<CharSequence> mStrings;
        private int mTextResourceId;
        private boolean mUseFormStrings;

        public EndSpinnerAdapter(Context context, ArrayList<CharSequence> strings, int itemLayoutId, int textResourceId, int dropDownLayoutId) {
            super(context, itemLayoutId, strings);
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
            this.mItemLayoutId = itemLayoutId;
            this.mTextResourceId = textResourceId;
            this.mDropDownLayoutId = dropDownLayoutId;
            this.mStrings = strings;
            this.mEndDateString = RecurrenceOptionCreator.this.getResources().getString(C0563R.string.recurrence_end_date);
            if (this.mEndDateString.indexOf("%s") <= 0) {
                this.mUseFormStrings = true;
            } else if (RecurrenceOptionCreator.this.getResources().getQuantityString(C0563R.plurals.recurrence_end_count, 1).indexOf("%d") <= 0) {
                this.mUseFormStrings = true;
            }
            if (this.mUseFormStrings) {
                RecurrenceOptionCreator.this.mEndSpinner.setLayoutParams(new LayoutParams(0, -2, FlexItem.FLEX_SHRINK_DEFAULT));
            }
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            if (convertView == null) {
                v = this.mInflater.inflate(this.mItemLayoutId, parent, false);
            } else {
                v = convertView;
            }
            TextView item = (TextView) v.findViewById(this.mTextResourceId);
            int markerStart;
            switch (position) {
                case 0:
                    item.setText((CharSequence) this.mStrings.get(0));
                    return v;
                case 1:
                    markerStart = this.mEndDateString.indexOf("%s");
                    if (markerStart == -1) {
                        return v;
                    }
                    if (this.mUseFormStrings || markerStart == 0) {
                        item.setText(RecurrenceOptionCreator.this.mEndDateLabel);
                        return v;
                    }
                    item.setText(this.mEndDateString.substring(0, markerStart).trim());
                    return v;
                case 2:
                    String endString = RecurrenceOptionCreator.this.mResources.getQuantityString(C0563R.plurals.recurrence_end_count, RecurrenceOptionCreator.this.mModel.endCount);
                    markerStart = endString.indexOf("%d");
                    if (markerStart == -1) {
                        return v;
                    }
                    if (this.mUseFormStrings || markerStart == 0) {
                        item.setText(RecurrenceOptionCreator.this.mEndCountLabel);
                        RecurrenceOptionCreator.this.mPostEndCount.setVisibility(8);
                        RecurrenceOptionCreator.this.mHidePostEndCount = true;
                        return v;
                    }
                    RecurrenceOptionCreator.this.mPostEndCount.setText(endString.substring(markerStart + "%d".length(), endString.length()).trim());
                    if (RecurrenceOptionCreator.this.mModel.end == 2) {
                        RecurrenceOptionCreator.this.mPostEndCount.setVisibility(0);
                    }
                    if (endString.charAt(markerStart - 1) == ' ') {
                        markerStart--;
                    }
                    item.setText(endString.substring(0, markerStart).trim());
                    return v;
                default:
                    return null;
            }
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v;
            if (convertView == null) {
                v = this.mInflater.inflate(this.mDropDownLayoutId, parent, false);
            } else {
                v = convertView;
            }
            ((TextView) v.findViewById(this.mTextResourceId)).setText((CharSequence) this.mStrings.get(position));
            return v;
        }
    }

    public interface OnRecurrenceSetListener {
        void onCancelled();

        void onRecurrenceSet(String str);
    }

    private class RecurrenceModel implements Parcelable {
        static final int END_BY_COUNT = 2;
        static final int END_BY_DATE = 1;
        static final int END_NEVER = 0;
        static final int FREQ_DAILY = 0;
        static final int FREQ_MONTHLY = 2;
        static final int FREQ_WEEKLY = 1;
        static final int FREQ_YEARLY = 3;
        static final int MONTHLY_BY_DATE = 0;
        static final int MONTHLY_BY_NTH_DAY_OF_WEEK = 1;
        static final int STATE_NO_RECURRENCE = 0;
        static final int STATE_RECURRENCE = 1;
        public final Creator<RecurrenceModel> CREATOR;
        int end;
        int endCount;
        Time endDate;
        int freq;
        int interval;
        int monthlyByDayOfWeek;
        int monthlyByMonthDay;
        int monthlyByNthDayOfWeek;
        int monthlyRepeat;
        int recurrenceState;
        boolean[] weeklyByDayOfWeek;

        class C05921 implements Creator<RecurrenceModel> {
            C05921() {
            }

            public RecurrenceModel createFromParcel(Parcel in) {
                return new RecurrenceModel(in);
            }

            public RecurrenceModel[] newArray(int size) {
                return new RecurrenceModel[size];
            }
        }

        public String toString() {
            return "Model [freq=" + this.freq + ", interval=" + this.interval + ", end=" + this.end + ", endDate=" + this.endDate + ", endCount=" + this.endCount + ", weeklyByDayOfWeek=" + Arrays.toString(this.weeklyByDayOfWeek) + ", monthlyRepeat=" + this.monthlyRepeat + ", monthlyByMonthDay=" + this.monthlyByMonthDay + ", monthlyByDayOfWeek=" + this.monthlyByDayOfWeek + ", monthlyByNthDayOfWeek=" + this.monthlyByNthDayOfWeek + "]";
        }

        public int describeContents() {
            return 0;
        }

        public RecurrenceModel() {
            this.freq = 1;
            this.interval = 1;
            this.endCount = 5;
            this.weeklyByDayOfWeek = new boolean[7];
            this.CREATOR = new C05921();
        }

        public RecurrenceModel(Parcel in) {
            this.freq = 1;
            this.interval = 1;
            this.endCount = 5;
            this.weeklyByDayOfWeek = new boolean[7];
            this.CREATOR = new C05921();
            readFromParcel(in);
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.freq);
            dest.writeInt(this.interval);
            dest.writeInt(this.end);
            dest.writeInt(this.endDate.year);
            dest.writeInt(this.endDate.month);
            dest.writeInt(this.endDate.monthDay);
            dest.writeInt(this.endCount);
            dest.writeBooleanArray(this.weeklyByDayOfWeek);
            dest.writeInt(this.monthlyRepeat);
            dest.writeInt(this.monthlyByMonthDay);
            dest.writeInt(this.monthlyByDayOfWeek);
            dest.writeInt(this.monthlyByNthDayOfWeek);
            dest.writeInt(this.recurrenceState);
        }

        private void readFromParcel(Parcel in) {
            this.freq = in.readInt();
            this.interval = in.readInt();
            this.end = in.readInt();
            this.endDate = new Time();
            this.endDate.year = in.readInt();
            this.endDate.month = in.readInt();
            this.endDate.monthDay = in.readInt();
            this.endCount = in.readInt();
            in.readBooleanArray(this.weeklyByDayOfWeek);
            this.monthlyRepeat = in.readInt();
            this.monthlyByMonthDay = in.readInt();
            this.monthlyByDayOfWeek = in.readInt();
            this.monthlyByNthDayOfWeek = in.readInt();
            this.recurrenceState = in.readInt();
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C05931();
        private final boolean mEndCountHasFocus;
        private final RecurrenceModel mRecurrenceModel;
        private final CurrentView sCurrentView;

        static class C05931 implements Creator<SavedState> {
            C05931() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        private SavedState(Parcelable superState, RecurrenceModel recurrenceModel, boolean endCountHasFocus, CurrentView currentView) {
            super(superState);
            this.mRecurrenceModel = recurrenceModel;
            this.mEndCountHasFocus = endCountHasFocus;
            this.sCurrentView = currentView;
        }

        private SavedState(Parcel in) {
            super(in);
            this.mRecurrenceModel = (RecurrenceModel) in.readParcelable(RecurrenceModel.class.getClassLoader());
            this.mEndCountHasFocus = in.readByte() != (byte) 0;
            this.sCurrentView = CurrentView.valueOf(in.readString());
        }

        public void writeToParcel(@NonNull Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.mRecurrenceModel, flags);
            dest.writeByte((byte) (this.mEndCountHasFocus ? 1 : 0));
            dest.writeString(this.sCurrentView.name());
        }

        public RecurrenceModel getRecurrenceModel() {
            return this.mRecurrenceModel;
        }

        public boolean getEndCountHasFocus() {
            return this.mEndCountHasFocus;
        }

        public CurrentView getCurrentView() {
            return this.sCurrentView;
        }
    }

    RecurrenceOption resolveRepeatOption() {
        if (this.mModel.freq == 0 && this.mModel.interval == 1 && this.mModel.end == 0) {
            return RecurrenceOption.DAILY;
        }
        return RecurrenceOption.CUSTOM;
    }

    public static boolean isSupportedMonthlyByNthDayOfWeek(int num) {
        return (num > 0 && num <= 5) || num == -1;
    }

    public static boolean canHandleRecurrenceRule(EventRecurrence er) {
        switch (er.freq) {
            case 4:
            case 5:
            case 6:
            case 7:
                if (er.count > 0 && !TextUtils.isEmpty(er.until)) {
                    return false;
                }
                int numOfByDayNum = 0;
                for (int i = 0; i < er.bydayCount; i++) {
                    if (isSupportedMonthlyByNthDayOfWeek(er.bydayNum[i])) {
                        numOfByDayNum++;
                    }
                }
                if (numOfByDayNum > 1) {
                    return false;
                }
                if ((numOfByDayNum > 0 && er.freq != 6) || er.bymonthdayCount > 1) {
                    return false;
                }
                if (er.freq == 6) {
                    if (er.bydayCount > 1) {
                        return false;
                    }
                    if (er.bydayCount > 0 && er.bymonthdayCount > 0) {
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }

    private static void copyEventRecurrenceToModel(EventRecurrence er, RecurrenceModel model) {
        switch (er.freq) {
            case 4:
                model.freq = 0;
                break;
            case 5:
                model.freq = 1;
                break;
            case 6:
                model.freq = 2;
                break;
            case 7:
                model.freq = 3;
                break;
            default:
                throw new IllegalStateException("freq=" + er.freq);
        }
        if (er.interval > 0) {
            model.interval = er.interval;
        }
        model.endCount = er.count;
        if (model.endCount > 0) {
            model.end = 2;
        }
        if (!TextUtils.isEmpty(er.until)) {
            if (model.endDate == null) {
                model.endDate = new Time();
            }
            try {
                model.endDate.parse(er.until);
            } catch (TimeFormatException e) {
                model.endDate = null;
            }
            if (model.end != 2 || model.endDate == null) {
                model.end = 1;
            } else {
                throw new IllegalStateException("freq=" + er.freq);
            }
        }
        Arrays.fill(model.weeklyByDayOfWeek, false);
        if (er.bydayCount > 0) {
            int count = 0;
            int i = 0;
            while (i < er.bydayCount) {
                int dayOfWeek = EventRecurrence.day2TimeDay(er.byday[i]);
                model.weeklyByDayOfWeek[dayOfWeek] = true;
                if (model.freq == 2 && isSupportedMonthlyByNthDayOfWeek(er.bydayNum[i])) {
                    model.monthlyByDayOfWeek = dayOfWeek;
                    model.monthlyByNthDayOfWeek = er.bydayNum[i];
                    model.monthlyRepeat = 1;
                    count++;
                }
                i++;
            }
            if (model.freq == 2) {
                if (er.bydayCount != 1) {
                    throw new IllegalStateException("Can handle only 1 byDayOfWeek in monthly");
                } else if (count != 1) {
                    throw new IllegalStateException("Didn't specify which nth day of week to repeat for a monthly");
                }
            }
        }
        if (model.freq != 2) {
            return;
        }
        if (er.bymonthdayCount == 1) {
            if (model.monthlyRepeat == 1) {
                throw new IllegalStateException("Can handle only by monthday or by nth day of week, not both");
            }
            model.monthlyByMonthDay = er.bymonthday[0];
            model.monthlyRepeat = 0;
        } else if (er.bymonthCount > 1) {
            throw new IllegalStateException("Can handle only one bymonthday");
        }
    }

    private static void copyModelToEventRecurrence(RecurrenceModel model, EventRecurrence er) {
        if (model.recurrenceState == 0) {
            throw new IllegalStateException("There's no recurrence");
        }
        er.freq = mFreqModelToEventRecurrence[model.freq];
        if (model.interval <= 1) {
            er.interval = 0;
        } else {
            er.interval = model.interval;
        }
        switch (model.end) {
            case 1:
                if (model.endDate != null) {
                    model.endDate.switchTimezone("UTC");
                    model.endDate.normalize(false);
                    er.until = model.endDate.format2445();
                    er.count = 0;
                    break;
                }
                throw new IllegalStateException("end = END_BY_DATE but endDate is null");
            case 2:
                er.count = model.endCount;
                er.until = null;
                if (er.count <= 0) {
                    throw new IllegalStateException("count is " + er.count);
                }
                break;
            default:
                er.count = 0;
                er.until = null;
                break;
        }
        er.bydayCount = 0;
        er.bymonthdayCount = 0;
        switch (model.freq) {
            case 1:
                int i;
                int count = 0;
                for (i = 0; i < 7; i++) {
                    if (model.weeklyByDayOfWeek[i]) {
                        count++;
                    }
                }
                if (er.bydayCount < count || er.byday == null || er.bydayNum == null) {
                    er.byday = new int[count];
                    er.bydayNum = new int[count];
                }
                er.bydayCount = count;
                for (i = 6; i >= 0; i--) {
                    if (model.weeklyByDayOfWeek[i]) {
                        count--;
                        er.bydayNum[count] = 0;
                        er.byday[count] = EventRecurrence.timeDay2Day(i);
                    }
                }
                break;
            case 2:
                if (model.monthlyRepeat != 0) {
                    if (model.monthlyRepeat == 1) {
                        if (isSupportedMonthlyByNthDayOfWeek(model.monthlyByNthDayOfWeek)) {
                            if (er.bydayCount < 1 || er.byday == null || er.bydayNum == null) {
                                er.byday = new int[1];
                                er.bydayNum = new int[1];
                            }
                            er.bydayCount = 1;
                            er.byday[0] = EventRecurrence.timeDay2Day(model.monthlyByDayOfWeek);
                            er.bydayNum[0] = model.monthlyByNthDayOfWeek;
                            break;
                        }
                        throw new IllegalStateException("month repeat by nth week but n is " + model.monthlyByNthDayOfWeek);
                    }
                } else if (model.monthlyByMonthDay > 0) {
                    if (er.bymonthday == null || er.bymonthdayCount < 1) {
                        er.bymonthday = new int[1];
                    }
                    er.bymonthday[0] = model.monthlyByMonthDay;
                    er.bymonthdayCount = 1;
                    break;
                }
                break;
        }
        if (!canHandleRecurrenceRule(er)) {
            throw new IllegalStateException("UI generated recurrence that it can't handle. ER:" + er.toString() + " Model: " + model.toString());
        }
    }

    public RecurrenceOptionCreator(Context context) {
        this(context, null);
    }

    public RecurrenceOptionCreator(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spRecurrenceOptionCreatorStyle);
    }

    public RecurrenceOptionCreator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spRecurrenceOptionCreatorStyle, C0563R.style.RecurrenceOptionCreatorStyle), attrs, defStyleAttr);
        this.mRecurrence = new EventRecurrence();
        this.mTime = new Time();
        this.mModel = new RecurrenceModel();
        this.TIME_DAY_TO_CALENDAR_DAY = new int[]{1, 2, 3, 4, 5, 6, 7};
        this.mIntervalResId = -1;
        this.mEndSpinnerArray = new ArrayList(3);
        this.mWeekByDayButtons = new WeekButton[7];
        this.mButtonLayoutCallback = new C05881();
        initializeLayout();
    }

    @TargetApi(21)
    public RecurrenceOptionCreator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(SUtils.createThemeWrapper(context, C0563R.attr.sublimePickerStyle, C0563R.style.SublimePickerStyleLight, C0563R.attr.spRecurrenceOptionCreatorStyle, C0563R.style.RecurrenceOptionCreatorStyle), attrs, defStyleAttr, defStyleRes);
        this.mRecurrence = new EventRecurrence();
        this.mTime = new Time();
        this.mModel = new RecurrenceModel();
        this.TIME_DAY_TO_CALENDAR_DAY = new int[]{1, 2, 3, 4, 5, 6, 7};
        this.mIntervalResId = -1;
        this.mEndSpinnerArray = new ArrayList(3);
        this.mWeekByDayButtons = new WeekButton[7];
        this.mButtonLayoutCallback = new C05881();
        initializeLayout();
    }

    void initializeLayout() {
        TypedArray a = getContext().obtainStyledAttributes(C0563R.styleable.RecurrenceOptionCreator);
        try {
            this.mHeaderBackgroundColor = a.getColor(C0563R.styleable.RecurrenceOptionCreator_spHeaderBackground, 0);
            this.mEndDateFormatter = DateFormat.getDateInstance(a.getInt(C0563R.styleable.RecurrenceOptionCreator_spEndDateFormat, 1) == 0 ? 3 : 2, Locale.getDefault());
            int weekButtonUnselectedTextColor = a.getColor(C0563R.styleable.RecurrenceOptionCreator_spWeekButtonUnselectedTextColor, SUtils.COLOR_ACCENT);
            int weekButtonSelectedTextColor = a.getColor(C0563R.styleable.RecurrenceOptionCreator_spWeekButtonSelectedTextColor, SUtils.COLOR_TEXT_PRIMARY_INVERSE);
            int weekButtonSelectedCircleColor = a.getColor(C0563R.styleable.RecurrenceOptionCreator_spWeekButtonSelectedCircleColor, SUtils.COLOR_ACCENT);
            this.mResources = getResources();
            LayoutInflater.from(getContext()).inflate(C0563R.layout.recurrence_picker, this);
            this.mRecurrencePicker = findViewById(C0563R.id.recurrence_picker);
            this.mDateOnlyPicker = (RecurrenceEndDatePicker) findViewById(C0563R.id.date_only_picker);
            this.mDateOnlyPicker.setVisibility(8);
            this.mButtonLayout = (DecisionButtonLayout) findViewById(C0563R.id.roc_decision_button_layout);
            this.mButtonLayout.applyOptions(this.mButtonLayoutCallback);
            SUtils.setViewBackground(findViewById(C0563R.id.freqSpinnerHolder), this.mHeaderBackgroundColor, 3);
            this.mFreqSpinner = (Spinner) findViewById(C0563R.id.freqSpinner);
            this.mFreqSpinner.setOnItemSelectedListener(this);
            ArrayAdapter<CharSequence> freqAdapter = ArrayAdapter.createFromResource(getContext(), C0563R.array.recurrence_freq, C0563R.layout.roc_freq_spinner_item);
            freqAdapter.setDropDownViewResource(C0563R.layout.roc_spinner_dropdown_item);
            this.mFreqSpinner.setAdapter(freqAdapter);
            Drawable freqSpinnerBg = ContextCompat.getDrawable(getContext(), C0563R.drawable.abc_spinner_mtrl_am_alpha);
            PorterDuffColorFilter cfFreqSpinner = new PorterDuffColorFilter(SUtils.COLOR_TEXT_PRIMARY_INVERSE, Mode.SRC_IN);
            if (freqSpinnerBg != null) {
                freqSpinnerBg.setColorFilter(cfFreqSpinner);
                SUtils.setViewBackground(this.mFreqSpinner, freqSpinnerBg);
            }
            this.mInterval = (EditText) findViewById(C0563R.id.interval);
            this.mInterval.addTextChangedListener(new minMaxTextWatcher(1, 1, 99) {
                void onChange(int v) {
                    if (RecurrenceOptionCreator.this.mIntervalResId != -1 && RecurrenceOptionCreator.this.mInterval.getText().toString().length() > 0) {
                        RecurrenceOptionCreator.this.mModel.interval = v;
                        RecurrenceOptionCreator.this.updateIntervalText();
                        RecurrenceOptionCreator.this.mInterval.requestLayout();
                    }
                }
            });
            this.mIntervalPreText = (TextView) findViewById(C0563R.id.intervalPreText);
            this.mIntervalPostText = (TextView) findViewById(C0563R.id.intervalPostText);
            this.mEndNeverStr = this.mResources.getString(C0563R.string.recurrence_end_continously);
            this.mEndDateLabel = this.mResources.getString(C0563R.string.recurrence_end_date_label);
            this.mEndCountLabel = this.mResources.getString(C0563R.string.recurrence_end_count_label);
            this.mEndSpinnerArray.add(this.mEndNeverStr);
            this.mEndSpinnerArray.add(this.mEndDateLabel);
            this.mEndSpinnerArray.add(this.mEndCountLabel);
            this.mEndSpinner = (Spinner) findViewById(C0563R.id.endSpinner);
            this.mEndSpinner.setOnItemSelectedListener(this);
            this.mEndSpinnerAdapter = new EndSpinnerAdapter(getContext(), this.mEndSpinnerArray, C0563R.layout.roc_end_spinner_item, C0563R.id.spinner_item, C0563R.layout.roc_spinner_dropdown_item);
            this.mEndSpinner.setAdapter(this.mEndSpinnerAdapter);
            this.mEndCount = (EditText) findViewById(C0563R.id.endCount);
            this.mEndCount.addTextChangedListener(new minMaxTextWatcher(1, 5, COUNT_MAX) {
                void onChange(int v) {
                    if (RecurrenceOptionCreator.this.mModel.endCount != v) {
                        RecurrenceOptionCreator.this.mModel.endCount = v;
                        RecurrenceOptionCreator.this.updateEndCountText();
                        RecurrenceOptionCreator.this.mEndCount.requestLayout();
                    }
                }
            });
            this.mPostEndCount = (TextView) findViewById(C0563R.id.postEndCount);
            this.mEndDateTextView = (TextView) findViewById(C0563R.id.endDate);
            this.mEndDateTextView.setOnClickListener(this);
            SUtils.setViewBackground(this.mEndDateTextView, SUtils.createButtonBg(getContext(), SUtils.COLOR_BUTTON_NORMAL, SUtils.COLOR_CONTROL_HIGHLIGHT));
            WeekButton.setStateColors(weekButtonUnselectedTextColor, weekButtonSelectedTextColor);
            this.mWeekGroup = (LinearLayout) findViewById(C0563R.id.weekGroup);
            this.mWeekGroup2 = (LinearLayout) findViewById(C0563R.id.weekGroup2);
            View eighthWeekDay = findViewById(C0563R.id.week_day_8);
            if (eighthWeekDay != null) {
                eighthWeekDay.setVisibility(4);
            }
            this.mMonthRepeatByDayOfWeekStrs = new String[7][];
            this.mMonthRepeatByDayOfWeekStrs[0] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_sun);
            this.mMonthRepeatByDayOfWeekStrs[1] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_mon);
            this.mMonthRepeatByDayOfWeekStrs[2] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_tues);
            this.mMonthRepeatByDayOfWeekStrs[3] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_wed);
            this.mMonthRepeatByDayOfWeekStrs[4] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_thurs);
            this.mMonthRepeatByDayOfWeekStrs[5] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_fri);
            this.mMonthRepeatByDayOfWeekStrs[6] = this.mResources.getStringArray(C0563R.array.repeat_by_nth_sat);
            int idx = RecurrenceUtils.getFirstDayOfWeek();
            String[] dayOfWeekString = new DateFormatSymbols().getShortWeekdays();
            int expandedWidthHeight = this.mResources.getDimensionPixelSize(C0563R.dimen.week_button_state_on_circle_size);
            WeekButton[] tempWeekButtons = new WeekButton[]{(WeekButton) findViewById(C0563R.id.week_day_1), (WeekButton) findViewById(C0563R.id.week_day_2), (WeekButton) findViewById(C0563R.id.week_day_3), (WeekButton) findViewById(C0563R.id.week_day_4), (WeekButton) findViewById(C0563R.id.week_day_5), (WeekButton) findViewById(C0563R.id.week_day_6), (WeekButton) findViewById(C0563R.id.week_day_7)};
            for (int i = 0; i < this.mWeekByDayButtons.length; i++) {
                this.mWeekByDayButtons[idx] = tempWeekButtons[i];
                SUtils.setViewBackground(this.mWeekByDayButtons[idx], new CheckableDrawable(weekButtonSelectedCircleColor, false, expandedWidthHeight));
                this.mWeekByDayButtons[idx].setTextColor(weekButtonUnselectedTextColor);
                this.mWeekByDayButtons[idx].setTextOff(dayOfWeekString[this.TIME_DAY_TO_CALENDAR_DAY[idx]]);
                this.mWeekByDayButtons[idx].setTextOn(dayOfWeekString[this.TIME_DAY_TO_CALENDAR_DAY[idx]]);
                this.mWeekByDayButtons[idx].setOnCheckedChangeListener(this);
                idx++;
                if (idx >= 7) {
                    idx = 0;
                }
            }
            this.mMonthRepeatByRadioGroup = (RadioGroup) findViewById(C0563R.id.monthGroup);
            this.mMonthRepeatByRadioGroup.setOnCheckedChangeListener(this);
            this.mRepeatMonthlyByNthDayOfWeek = (RadioButton) findViewById(C0563R.id.repeatMonthlyByNthDayOfTheWeek);
            this.mRepeatMonthlyByNthDayOfMonth = (RadioButton) findViewById(C0563R.id.repeatMonthlyByNthDayOfMonth);
        } finally {
            a.recycle();
        }
    }

    public void initializeData(long currentlyChosenTime, String timeZone, String recurrenceRule, @NonNull OnRecurrenceSetListener callback) {
        this.mRecurrence.wkst = EventRecurrence.timeDay2Day(RecurrenceUtils.getFirstDayOfWeek());
        this.mRecurrenceSetListener = callback;
        this.mTime.set(currentlyChosenTime);
        if (!TextUtils.isEmpty(timeZone)) {
            this.mTime.timezone = timeZone;
        }
        this.mTime.normalize(false);
        this.mModel.weeklyByDayOfWeek[this.mTime.weekDay] = true;
        if (TextUtils.isEmpty(recurrenceRule)) {
            this.mModel.recurrenceState = 1;
        } else {
            this.mModel.recurrenceState = 1;
            this.mRecurrence.parse(recurrenceRule);
            copyEventRecurrenceToModel(this.mRecurrence, this.mModel);
            if (this.mRecurrence.bydayCount == 0) {
                this.mModel.weeklyByDayOfWeek[this.mTime.weekDay] = true;
            }
        }
        if (this.mModel.endDate == null) {
            this.mModel.endDate = new Time(this.mTime);
            Time time;
            switch (this.mModel.freq) {
                case 0:
                case 1:
                    time = this.mModel.endDate;
                    time.month++;
                    break;
                case 2:
                    time = this.mModel.endDate;
                    time.month += 3;
                    break;
                case 3:
                    time = this.mModel.endDate;
                    time.year += 3;
                    break;
            }
            this.mModel.endDate.normalize(false);
        }
        togglePickerOptions();
        updateDialog();
        showRecurrencePicker();
    }

    private void togglePickerOptions() {
        int i = 0;
        if (this.mModel.recurrenceState == 0) {
            this.mFreqSpinner.setEnabled(false);
            this.mEndSpinner.setEnabled(false);
            this.mIntervalPreText.setEnabled(false);
            this.mInterval.setEnabled(false);
            this.mIntervalPostText.setEnabled(false);
            this.mMonthRepeatByRadioGroup.setEnabled(false);
            this.mEndCount.setEnabled(false);
            this.mPostEndCount.setEnabled(false);
            this.mEndDateTextView.setEnabled(false);
            this.mRepeatMonthlyByNthDayOfWeek.setEnabled(false);
            this.mRepeatMonthlyByNthDayOfMonth.setEnabled(false);
            for (Button button : this.mWeekByDayButtons) {
                button.setEnabled(false);
            }
        } else {
            findViewById(C0563R.id.options).setEnabled(true);
            this.mFreqSpinner.setEnabled(true);
            this.mEndSpinner.setEnabled(true);
            this.mIntervalPreText.setEnabled(true);
            this.mInterval.setEnabled(true);
            this.mIntervalPostText.setEnabled(true);
            this.mMonthRepeatByRadioGroup.setEnabled(true);
            this.mEndCount.setEnabled(true);
            this.mPostEndCount.setEnabled(true);
            this.mEndDateTextView.setEnabled(true);
            this.mRepeatMonthlyByNthDayOfWeek.setEnabled(true);
            this.mRepeatMonthlyByNthDayOfMonth.setEnabled(true);
            WeekButton[] weekButtonArr = this.mWeekByDayButtons;
            int length = weekButtonArr.length;
            while (i < length) {
                weekButtonArr[i].setEnabled(true);
                i++;
            }
        }
        updateDoneButtonState();
    }

    private void updateDoneButtonState() {
        if (this.mModel.recurrenceState == 0) {
            this.mButtonLayout.updateValidity(true);
        } else if (this.mInterval.getText().toString().length() == 0) {
            this.mButtonLayout.updateValidity(false);
        } else if (this.mEndCount.getVisibility() == 0 && this.mEndCount.getText().toString().length() == 0) {
            this.mButtonLayout.updateValidity(false);
        } else if (this.mModel.freq == 1) {
            for (CompoundButton b : this.mWeekByDayButtons) {
                if (b.isChecked()) {
                    this.mButtonLayout.updateValidity(true);
                    return;
                }
            }
            this.mButtonLayout.updateValidity(false);
        } else {
            this.mButtonLayout.updateValidity(true);
        }
    }

    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mModel, this.mEndCount.hasFocus(), this.mRecurrencePicker.getVisibility() == 0 ? CurrentView.RECURRENCE_PICKER : CurrentView.DATE_ONLY_PICKER);
    }

    protected void onRestoreInstanceState(Parcelable state) {
        BaseSavedState bss = (BaseSavedState) state;
        super.onRestoreInstanceState(bss.getSuperState());
        SavedState ss = (SavedState) bss;
        final boolean endCountHasFocus = ss.getEndCountHasFocus();
        RecurrenceModel m = ss.getRecurrenceModel();
        if (m != null) {
            this.mModel = m;
        }
        this.mRecurrence.wkst = EventRecurrence.timeDay2Day(RecurrenceUtils.getFirstDayOfWeek());
        togglePickerOptions();
        updateDialog();
        if (ss.getCurrentView() == CurrentView.RECURRENCE_PICKER) {
            showRecurrencePicker();
            post(new Runnable() {
                public void run() {
                    if (RecurrenceOptionCreator.this.mEndCount != null && endCountHasFocus) {
                        RecurrenceOptionCreator.this.mEndCount.requestFocus();
                    }
                }
            });
            return;
        }
        showDateOnlyPicker();
    }

    public void updateDialog() {
        int msgIndex = 5;
        int i = 8;
        String intervalStr = Integer.toString(this.mModel.interval);
        if (!intervalStr.equals(this.mInterval.getText().toString())) {
            this.mInterval.setText(intervalStr);
        }
        this.mFreqSpinner.setSelection(this.mModel.freq);
        this.mWeekGroup.setVisibility(this.mModel.freq == 1 ? 0 : 8);
        if (this.mWeekGroup2 != null) {
            int i2;
            LinearLayout linearLayout = this.mWeekGroup2;
            if (this.mModel.freq == 1) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            linearLayout.setVisibility(i2);
        }
        RadioGroup radioGroup = this.mMonthRepeatByRadioGroup;
        if (this.mModel.freq == 2) {
            i = 0;
        }
        radioGroup.setVisibility(i);
        switch (this.mModel.freq) {
            case 0:
                this.mIntervalResId = C0563R.plurals.recurrence_interval_daily;
                break;
            case 1:
                this.mIntervalResId = C0563R.plurals.recurrence_interval_weekly;
                for (int i3 = 0; i3 < 7; i3++) {
                    this.mWeekByDayButtons[i3].setCheckedNoAnimate(this.mModel.weeklyByDayOfWeek[i3]);
                }
                break;
            case 2:
                this.mIntervalResId = C0563R.plurals.recurrence_interval_monthly;
                if (this.mModel.monthlyRepeat == 0) {
                    this.mMonthRepeatByRadioGroup.check(C0563R.id.repeatMonthlyByNthDayOfMonth);
                } else if (this.mModel.monthlyRepeat == 1) {
                    this.mMonthRepeatByRadioGroup.check(C0563R.id.repeatMonthlyByNthDayOfTheWeek);
                }
                if (this.mMonthRepeatByDayOfWeekStr == null) {
                    if (this.mModel.monthlyByNthDayOfWeek == 0) {
                        this.mModel.monthlyByNthDayOfWeek = (this.mTime.monthDay + 6) / 7;
                        if (this.mModel.monthlyByNthDayOfWeek >= 5) {
                            this.mModel.monthlyByNthDayOfWeek = -1;
                        }
                        this.mModel.monthlyByDayOfWeek = this.mTime.weekDay;
                    }
                    String[] monthlyByNthDayOfWeekStrs = this.mMonthRepeatByDayOfWeekStrs[this.mModel.monthlyByDayOfWeek];
                    if (this.mModel.monthlyByNthDayOfWeek >= 0) {
                        msgIndex = this.mModel.monthlyByNthDayOfWeek;
                    }
                    this.mMonthRepeatByDayOfWeekStr = monthlyByNthDayOfWeekStrs[msgIndex - 1];
                    this.mRepeatMonthlyByNthDayOfWeek.setText(this.mMonthRepeatByDayOfWeekStr);
                    break;
                }
                break;
            case 3:
                this.mIntervalResId = C0563R.plurals.recurrence_interval_yearly;
                break;
        }
        updateIntervalText();
        updateDoneButtonState();
        this.mEndSpinner.setSelection(this.mModel.end);
        if (this.mModel.end == 1) {
            this.mEndDateTextView.setText(this.mEndDateFormatter.format(Long.valueOf(this.mModel.endDate.toMillis(false))));
        } else if (this.mModel.end == 2) {
            String countStr = Integer.toString(this.mModel.endCount);
            if (!countStr.equals(this.mEndCount.getText().toString())) {
                this.mEndCount.setText(countStr);
            }
        }
    }

    private void setEndSpinnerEndDateStr(String endDateString) {
        this.mEndSpinnerArray.set(1, endDateString);
        this.mEndSpinnerAdapter.notifyDataSetChanged();
    }

    private void doToast() {
        String rrule;
        Log.e(TAG, "Model = " + this.mModel.toString());
        if (this.mModel.recurrenceState == 0) {
            rrule = "Not repeating";
        } else {
            copyModelToEventRecurrence(this.mModel, this.mRecurrence);
            rrule = this.mRecurrence.toString();
        }
        if (this.mToast != null) {
            this.mToast.cancel();
        }
        this.mToast = Toast.makeText(getContext(), rrule, 1);
        this.mToast.show();
    }

    private void updateIntervalText() {
        if (this.mIntervalResId != -1) {
            String INTERVAL_COUNT_MARKER = "%d";
            String intervalString = this.mResources.getQuantityString(this.mIntervalResId, this.mModel.interval);
            int markerStart = intervalString.indexOf("%d");
            if (markerStart != -1) {
                this.mIntervalPostText.setText(intervalString.substring(markerStart + "%d".length(), intervalString.length()).trim());
                this.mIntervalPreText.setText(intervalString.substring(0, markerStart).trim());
            }
        }
    }

    private void updateEndCountText() {
        String END_COUNT_MARKER = "%d";
        String endString = this.mResources.getQuantityString(C0563R.plurals.recurrence_end_count, this.mModel.endCount);
        int markerStart = endString.indexOf("%d");
        if (markerStart == -1) {
            return;
        }
        if (markerStart == 0) {
            Log.e(TAG, "No text to put in to recurrence's end spinner.");
            return;
        }
        this.mPostEndCount.setText(endString.substring(markerStart + "%d".length(), endString.length()).trim());
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int i = 0;
        if (parent == this.mFreqSpinner) {
            this.mModel.freq = position;
        } else if (parent == this.mEndSpinner) {
            int i2;
            switch (position) {
                case 0:
                    this.mModel.end = 0;
                    break;
                case 1:
                    this.mModel.end = 1;
                    break;
                case 2:
                    this.mModel.end = 2;
                    if (this.mModel.endCount <= 1) {
                        this.mModel.endCount = 1;
                    } else if (this.mModel.endCount > COUNT_MAX) {
                        this.mModel.endCount = COUNT_MAX;
                    }
                    updateEndCountText();
                    break;
            }
            EditText editText = this.mEndCount;
            if (this.mModel.end == 2) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            editText.setVisibility(i2);
            TextView textView = this.mEndDateTextView;
            if (this.mModel.end == 1) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            textView.setVisibility(i2);
            TextView textView2 = this.mPostEndCount;
            if (this.mModel.end != 2 || this.mHidePostEndCount) {
                i = 8;
            }
            textView2.setVisibility(i);
        }
        updateDialog();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onDateSet(RecurrenceEndDatePicker view, int year, int monthOfYear, int dayOfMonth) {
        showRecurrencePicker();
        if (this.mModel.endDate == null) {
            this.mModel.endDate = new Time(this.mTime.timezone);
            Time time = this.mModel.endDate;
            Time time2 = this.mModel.endDate;
            this.mModel.endDate.second = 0;
            time2.minute = 0;
            time.hour = 0;
        }
        this.mModel.endDate.year = year;
        this.mModel.endDate.month = monthOfYear;
        this.mModel.endDate.monthDay = dayOfMonth;
        this.mModel.endDate.normalize(false);
        updateDialog();
    }

    public void onDateOnlyPickerCancelled(RecurrenceEndDatePicker view) {
        showRecurrencePicker();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int itemIdx = -1;
        int i = 0;
        while (i < 7) {
            if (itemIdx == -1 && buttonView == this.mWeekByDayButtons[i]) {
                itemIdx = i;
                this.mModel.weeklyByDayOfWeek[i] = isChecked;
            }
            i++;
        }
        updateDialog();
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == C0563R.id.repeatMonthlyByNthDayOfMonth) {
            this.mModel.monthlyRepeat = 0;
        } else if (checkedId == C0563R.id.repeatMonthlyByNthDayOfTheWeek) {
            this.mModel.monthlyRepeat = 1;
        }
        updateDialog();
    }

    public void onClick(View v) {
        if (this.mEndDateTextView == v) {
            showDateOnlyPicker();
        }
    }

    private void showRecurrencePicker() {
        this.mDateOnlyPicker.setVisibility(8);
        this.mRecurrencePicker.setVisibility(0);
    }

    private void showDateOnlyPicker() {
        this.mDateOnlyPicker.init(this.mModel.endDate.year, this.mModel.endDate.month, this.mModel.endDate.monthDay, this);
        this.mDateOnlyPicker.setFirstDayOfWeek(RecurrenceUtils.getFirstDayOfWeekAsCalendar());
        this.mRecurrencePicker.setVisibility(8);
        this.mDateOnlyPicker.setVisibility(0);
    }
}
