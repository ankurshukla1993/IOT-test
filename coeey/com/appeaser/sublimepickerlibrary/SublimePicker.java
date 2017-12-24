package com.appeaser.sublimepickerlibrary;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.appeaser.sublimepickerlibrary.common.ButtonHandler;
import com.appeaser.sublimepickerlibrary.common.ButtonHandler.Callback;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate.Type;
import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker.DatePickerValidationCallback;
import com.appeaser.sublimepickerlibrary.datepicker.SublimeDatePicker.OnDateChangedListener;
import com.appeaser.sublimepickerlibrary.drawables.OverflowDrawable;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.OnRepeatOptionSetListener;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.appeaser.sublimepickerlibrary.timepicker.SublimeTimePicker;
import com.appeaser.sublimepickerlibrary.timepicker.SublimeTimePicker.TimePickerValidationCallback;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import humanize.util.Constants;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SublimePicker extends FrameLayout implements OnDateChangedListener, DatePickerValidationCallback, TimePickerValidationCallback {
    private static final long MONTH_IN_MILLIS = 2620800000L;
    private static final String TAG = SublimePicker.class.getSimpleName();
    private ImageView ivRecurrenceOptionsDP;
    private ImageView ivRecurrenceOptionsTP;
    private LinearLayout llMainContentHolder;
    private ButtonHandler mButtonLayout;
    private final Callback mButtonLayoutCallback;
    private Picker mCurrentPicker;
    private RecurrenceOption mCurrentRecurrenceOption;
    private SublimeDatePicker mDatePicker;
    private boolean mDatePickerEnabled;
    private boolean mDatePickerSyncStateCalled;
    private boolean mDatePickerValid;
    private DateFormat mDefaultDateFormatter;
    private DateFormat mDefaultTimeFormatter;
    private Picker mHiddenPicker;
    private SublimeListenerAdapter mListener;
    private SublimeOptions mOptions;
    private boolean mRecurrencePickerEnabled;
    private String mRecurrenceRule;
    private final OnRepeatOptionSetListener mRepeatOptionSetListener;
    private SublimeRecurrencePicker mSublimeRecurrencePicker;
    private SublimeTimePicker mTimePicker;
    private boolean mTimePickerEnabled;
    private boolean mTimePickerValid;

    class C05641 implements OnRepeatOptionSetListener {
        C05641() {
        }

        public void onRepeatOptionSet(RecurrenceOption option, String recurrenceRule) {
            SublimePicker.this.mCurrentRecurrenceOption = option;
            SublimePicker.this.mRecurrenceRule = recurrenceRule;
            onDone();
        }

        public void onDone() {
            if (SublimePicker.this.mDatePickerEnabled || SublimePicker.this.mTimePickerEnabled) {
                SublimePicker.this.updateCurrentPicker();
                SublimePicker.this.updateDisplay();
                return;
            }
            SublimePicker.this.mButtonLayoutCallback.onOkay();
        }
    }

    class C05652 implements Callback {
        C05652() {
        }

        public void onOkay() {
            SelectedDate selectedDate = null;
            if (SublimePicker.this.mDatePickerEnabled) {
                selectedDate = SublimePicker.this.mDatePicker.getSelectedDate();
            }
            int hour = -1;
            int minute = -1;
            if (SublimePicker.this.mTimePickerEnabled) {
                hour = SublimePicker.this.mTimePicker.getCurrentHour();
                minute = SublimePicker.this.mTimePicker.getCurrentMinute();
            }
            RecurrenceOption recurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
            String recurrenceRule = null;
            if (SublimePicker.this.mRecurrencePickerEnabled) {
                recurrenceOption = SublimePicker.this.mCurrentRecurrenceOption;
                if (recurrenceOption == RecurrenceOption.CUSTOM) {
                    recurrenceRule = SublimePicker.this.mRecurrenceRule;
                }
            }
            SublimePicker.this.mListener.onDateTimeRecurrenceSet(SublimePicker.this, selectedDate, hour, minute, recurrenceOption, recurrenceRule);
        }

        public void onCancel() {
            SublimePicker.this.mListener.onCancelled();
        }

        public void onSwitch() {
            SublimePicker.this.mCurrentPicker = SublimePicker.this.mCurrentPicker == Picker.DATE_PICKER ? Picker.TIME_PICKER : Picker.DATE_PICKER;
            SublimePicker.this.updateDisplay();
        }
    }

    class C05663 implements OnClickListener {
        C05663() {
        }

        public void onClick(View v) {
            SublimePicker.this.mCurrentPicker = Picker.REPEAT_OPTION_PICKER;
            SublimePicker.this.updateDisplay();
        }
    }

    class C05674 implements OnClickListener {
        C05674() {
        }

        public void onClick(View v) {
            SublimePicker.this.mCurrentPicker = Picker.REPEAT_OPTION_PICKER;
            SublimePicker.this.updateDisplay();
        }
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C05681();
        private final Picker sCurrentPicker;
        private final RecurrenceOption sCurrentRecurrenceOption;
        private final Picker sHiddenPicker;
        private final String sRecurrenceRule;

        static class C05681 implements Creator<SavedState> {
            C05681() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        private SavedState(Parcelable superState, Picker currentPicker, Picker hiddenPicker, RecurrenceOption recurrenceOption, String recurrenceRule) {
            super(superState);
            this.sCurrentPicker = currentPicker;
            this.sHiddenPicker = hiddenPicker;
            this.sCurrentRecurrenceOption = recurrenceOption;
            this.sRecurrenceRule = recurrenceRule;
        }

        private SavedState(Parcel in) {
            super(in);
            this.sCurrentPicker = Picker.valueOf(in.readString());
            this.sHiddenPicker = Picker.valueOf(in.readString());
            this.sCurrentRecurrenceOption = RecurrenceOption.valueOf(in.readString());
            this.sRecurrenceRule = in.readString();
        }

        public void writeToParcel(@NonNull Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(this.sCurrentPicker.name());
            dest.writeString(this.sHiddenPicker.name());
            dest.writeString(this.sCurrentRecurrenceOption.name());
            dest.writeString(this.sRecurrenceRule);
        }

        public Picker getCurrentPicker() {
            return this.sCurrentPicker;
        }

        public Picker getHiddenPicker() {
            return this.sHiddenPicker;
        }

        public RecurrenceOption getCurrentRepeatOption() {
            return this.sCurrentRecurrenceOption;
        }

        public String getRecurrenceRule() {
            return this.sRecurrenceRule;
        }
    }

    public SublimePicker(Context context) {
        this(context, null);
    }

    public SublimePicker(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.sublimePickerStyle);
    }

    public SublimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(createThemeWrapper(context), attrs, defStyleAttr);
        this.mCurrentRecurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
        this.mDatePickerValid = true;
        this.mTimePickerValid = true;
        this.mRepeatOptionSetListener = new C05641();
        this.mButtonLayoutCallback = new C05652();
        initializeLayout();
    }

    @TargetApi(21)
    public SublimePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(createThemeWrapper(context), attrs, defStyleAttr, defStyleRes);
        this.mCurrentRecurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
        this.mDatePickerValid = true;
        this.mTimePickerValid = true;
        this.mRepeatOptionSetListener = new C05641();
        this.mButtonLayoutCallback = new C05652();
        initializeLayout();
    }

    private static ContextThemeWrapper createThemeWrapper(Context context) {
        TypedArray forParent = context.obtainStyledAttributes(new int[]{C0563R.attr.sublimePickerStyle});
        int parentStyle = forParent.getResourceId(0, C0563R.style.SublimePickerStyleLight);
        forParent.recycle();
        return new ContextThemeWrapper(context, parentStyle);
    }

    private void initializeLayout() {
        Context context = getContext();
        SUtils.initializeResources(context);
        LayoutInflater.from(context).inflate(C0563R.layout.sublime_picker_view_layout, this, true);
        this.mDefaultDateFormatter = DateFormat.getDateInstance(2, Locale.getDefault());
        this.mDefaultTimeFormatter = DateFormat.getTimeInstance(3, Locale.getDefault());
        this.mDefaultTimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        this.llMainContentHolder = (LinearLayout) findViewById(C0563R.id.llMainContentHolder);
        this.mButtonLayout = new ButtonHandler(this);
        initializeRecurrencePickerSwitch();
        this.mDatePicker = (SublimeDatePicker) findViewById(C0563R.id.datePicker);
        this.mTimePicker = (SublimeTimePicker) findViewById(C0563R.id.timePicker);
        this.mSublimeRecurrencePicker = (SublimeRecurrencePicker) findViewById(C0563R.id.repeat_option_picker);
    }

    public void initializePicker(SublimeOptions options, SublimeListenerAdapter listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null.");
        }
        if (options != null) {
            options.verifyValidity();
        } else {
            options = new SublimeOptions();
        }
        this.mOptions = options;
        this.mListener = listener;
        processOptions();
        updateDisplay();
    }

    private void updateHiddenPicker() {
        if (this.mDatePickerEnabled && this.mTimePickerEnabled) {
            this.mHiddenPicker = this.mDatePicker.getVisibility() == 0 ? Picker.DATE_PICKER : Picker.TIME_PICKER;
        } else if (this.mDatePickerEnabled) {
            this.mHiddenPicker = Picker.DATE_PICKER;
        } else if (this.mTimePickerEnabled) {
            this.mHiddenPicker = Picker.TIME_PICKER;
        } else {
            this.mHiddenPicker = Picker.INVALID;
        }
    }

    private void updateCurrentPicker() {
        if (this.mHiddenPicker != Picker.INVALID) {
            this.mCurrentPicker = this.mHiddenPicker;
            return;
        }
        throw new RuntimeException("Logic issue: No valid option for mCurrentPicker");
    }

    private void updateDisplay() {
        CharSequence switchButtonText;
        if (this.mCurrentPicker == Picker.DATE_PICKER) {
            if (this.mTimePickerEnabled) {
                this.mTimePicker.setVisibility(8);
            }
            if (this.mRecurrencePickerEnabled) {
                this.mSublimeRecurrencePicker.setVisibility(8);
            }
            this.mDatePicker.setVisibility(0);
            this.llMainContentHolder.setVisibility(0);
            if (this.mButtonLayout.isSwitcherButtonEnabled()) {
                Date toFormat = new Date((((long) this.mTimePicker.getCurrentHour()) * 3600000) + (((long) this.mTimePicker.getCurrentMinute()) * 60000));
                switchButtonText = this.mListener.formatTime(toFormat);
                if (TextUtils.isEmpty(switchButtonText)) {
                    switchButtonText = this.mDefaultTimeFormatter.format(toFormat);
                }
                this.mButtonLayout.updateSwitcherText(Picker.DATE_PICKER, switchButtonText);
            }
            if (!this.mDatePickerSyncStateCalled) {
                this.mDatePickerSyncStateCalled = true;
            }
        } else if (this.mCurrentPicker == Picker.TIME_PICKER) {
            if (this.mDatePickerEnabled) {
                this.mDatePicker.setVisibility(8);
            }
            if (this.mRecurrencePickerEnabled) {
                this.mSublimeRecurrencePicker.setVisibility(8);
            }
            this.mTimePicker.setVisibility(0);
            this.llMainContentHolder.setVisibility(0);
            if (this.mButtonLayout.isSwitcherButtonEnabled()) {
                SelectedDate selectedDate = this.mDatePicker.getSelectedDate();
                switchButtonText = this.mListener.formatDate(selectedDate);
                if (TextUtils.isEmpty(switchButtonText)) {
                    if (selectedDate.getType() == Type.SINGLE) {
                        switchButtonText = this.mDefaultDateFormatter.format(new Date(this.mDatePicker.getSelectedDateInMillis()));
                    } else if (selectedDate.getType() == Type.RANGE) {
                        switchButtonText = formatDateRange(selectedDate);
                    }
                }
                this.mButtonLayout.updateSwitcherText(Picker.TIME_PICKER, switchButtonText);
            }
        } else if (this.mCurrentPicker == Picker.REPEAT_OPTION_PICKER) {
            updateHiddenPicker();
            this.mSublimeRecurrencePicker.updateView();
            if (this.mDatePickerEnabled || this.mTimePickerEnabled) {
                this.llMainContentHolder.setVisibility(8);
            }
            this.mSublimeRecurrencePicker.setVisibility(0);
        }
    }

    private String formatDateRange(SelectedDate selectedDate) {
        Calendar startDate = selectedDate.getStartDate();
        Calendar endDate = selectedDate.getEndDate();
        startDate.set(14, 0);
        startDate.set(13, 0);
        startDate.set(12, 0);
        startDate.set(10, 0);
        endDate.set(14, 0);
        endDate.set(13, 0);
        endDate.set(12, 0);
        endDate.set(10, 0);
        endDate.add(5, 1);
        float elapsedTime = (float) (endDate.getTimeInMillis() - startDate.getTimeInMillis());
        if (elapsedTime >= 3.14496E10f) {
            String str;
            float years = elapsedTime / 3.14496E10f;
            int yearsVal = ((years - ((float) ((int) years))) > 0.5f ? 1 : ((years - ((float) ((int) years))) == 0.5f ? 0 : -1)) > 0 ? (int) (FlexItem.FLEX_SHRINK_DEFAULT + years) : (int) years;
            StringBuilder append = new StringBuilder().append("~").append(yearsVal).append(Constants.SPACE);
            if (yearsVal == 1) {
                str = "year";
            } else {
                str = "years";
            }
            return append.append(str).toString();
        } else if (elapsedTime >= 2.6208E9f) {
            float months = elapsedTime / 2.6208E9f;
            int monthsVal = ((months - ((float) ((int) months))) > 0.5f ? 1 : ((months - ((float) ((int) months))) == 0.5f ? 0 : -1)) > 0 ? (int) (FlexItem.FLEX_SHRINK_DEFAULT + months) : (int) months;
            return "~" + monthsVal + Constants.SPACE + (monthsVal == 1 ? "month" : "months");
        } else {
            float days = elapsedTime / 8.64E7f;
            int daysVal = ((days - ((float) ((int) days))) > 0.5f ? 1 : ((days - ((float) ((int) days))) == 0.5f ? 0 : -1)) > 0 ? (int) (FlexItem.FLEX_SHRINK_DEFAULT + days) : (int) days;
            return "~" + daysVal + Constants.SPACE + (daysVal == 1 ? "day" : "days");
        }
    }

    private void initializeRecurrencePickerSwitch() {
        this.ivRecurrenceOptionsDP = (ImageView) findViewById(C0563R.id.ivRecurrenceOptionsDP);
        this.ivRecurrenceOptionsTP = (ImageView) findViewById(C0563R.id.ivRecurrenceOptionsTP);
        TypedArray typedArray = getContext().obtainStyledAttributes(C0563R.styleable.SublimePicker);
        try {
            int iconColor = typedArray.getColor(C0563R.styleable.SublimePicker_spOverflowIconColor, SUtils.COLOR_TEXT_PRIMARY_INVERSE);
            int pressedStateBgColor = typedArray.getColor(C0563R.styleable.SublimePicker_spOverflowIconPressedBgColor, SUtils.COLOR_TEXT_PRIMARY);
            this.ivRecurrenceOptionsDP.setImageDrawable(new OverflowDrawable(getContext(), iconColor));
            SUtils.setViewBackground(this.ivRecurrenceOptionsDP, SUtils.createOverflowButtonBg(pressedStateBgColor));
            this.ivRecurrenceOptionsTP.setImageDrawable(new OverflowDrawable(getContext(), iconColor));
            SUtils.setViewBackground(this.ivRecurrenceOptionsTP, SUtils.createOverflowButtonBg(pressedStateBgColor));
            this.ivRecurrenceOptionsDP.setOnClickListener(new C05663());
            this.ivRecurrenceOptionsTP.setOnClickListener(new C05674());
        } finally {
            typedArray.recycle();
        }
    }

    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mCurrentPicker, this.mHiddenPicker, this.mCurrentRecurrenceOption, this.mRecurrenceRule);
    }

    protected void onRestoreInstanceState(Parcelable state) {
        BaseSavedState bss = (BaseSavedState) state;
        super.onRestoreInstanceState(bss.getSuperState());
        SavedState ss = (SavedState) bss;
        this.mCurrentPicker = ss.getCurrentPicker();
        this.mCurrentRecurrenceOption = ss.getCurrentRepeatOption();
        this.mRecurrenceRule = ss.getRecurrenceRule();
        this.mHiddenPicker = ss.getHiddenPicker();
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchRestoreInstanceState(container);
        updateDisplay();
    }

    private void processOptions() {
        int i = 8;
        if (this.mOptions.animateLayoutChanges()) {
            LayoutTransition layoutTransition = new LayoutTransition();
            if (SUtils.isApi_16_OrHigher()) {
                layoutTransition.enableTransitionType(4);
            }
            setLayoutTransition(layoutTransition);
        } else {
            setLayoutTransition(null);
        }
        this.mDatePickerEnabled = this.mOptions.isDatePickerActive();
        this.mTimePickerEnabled = this.mOptions.isTimePickerActive();
        this.mRecurrencePickerEnabled = this.mOptions.isRecurrencePickerActive();
        if (this.mDatePickerEnabled) {
            int i2;
            this.mDatePicker.init(this.mOptions.getDateParams(), this.mOptions.canPickDateRange(), this);
            long[] dateRange = this.mOptions.getDateRange();
            if (dateRange[0] != Long.MIN_VALUE) {
                this.mDatePicker.setMinDate(dateRange[0]);
            }
            if (dateRange[1] != Long.MIN_VALUE) {
                this.mDatePicker.setMaxDate(dateRange[1]);
            }
            this.mDatePicker.setValidationCallback(this);
            ImageView imageView = this.ivRecurrenceOptionsDP;
            if (this.mRecurrencePickerEnabled) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        } else {
            this.llMainContentHolder.removeView(this.mDatePicker);
            this.mDatePicker = null;
        }
        if (this.mTimePickerEnabled) {
            int[] timeParams = this.mOptions.getTimeParams();
            this.mTimePicker.setCurrentHour(timeParams[0]);
            this.mTimePicker.setCurrentMinute(timeParams[1]);
            this.mTimePicker.setIs24HourView(this.mOptions.is24HourView());
            this.mTimePicker.setValidationCallback(this);
            ImageView imageView2 = this.ivRecurrenceOptionsTP;
            if (this.mRecurrencePickerEnabled) {
                i = 0;
            }
            imageView2.setVisibility(i);
        } else {
            this.llMainContentHolder.removeView(this.mTimePicker);
            this.mTimePicker = null;
        }
        if (this.mDatePickerEnabled && this.mTimePickerEnabled) {
            this.mButtonLayout.applyOptions(true, this.mButtonLayoutCallback);
        } else {
            this.mButtonLayout.applyOptions(false, this.mButtonLayoutCallback);
        }
        if (!(this.mDatePickerEnabled || this.mTimePickerEnabled)) {
            removeView(this.llMainContentHolder);
            this.llMainContentHolder = null;
            this.mButtonLayout = null;
        }
        this.mCurrentRecurrenceOption = this.mOptions.getRecurrenceOption();
        this.mRecurrenceRule = this.mOptions.getRecurrenceRule();
        if (this.mRecurrencePickerEnabled) {
            Calendar cal;
            if (this.mDatePickerEnabled) {
                cal = this.mDatePicker.getSelectedDate().getStartDate();
            } else {
                cal = SUtils.getCalendarForLocale(null, Locale.getDefault());
            }
            this.mSublimeRecurrencePicker.initializeData(this.mRepeatOptionSetListener, this.mCurrentRecurrenceOption, this.mRecurrenceRule, cal.getTimeInMillis());
        } else {
            removeView(this.mSublimeRecurrencePicker);
            this.mSublimeRecurrencePicker = null;
        }
        this.mCurrentPicker = this.mOptions.getPickerToShow();
        this.mHiddenPicker = Picker.INVALID;
    }

    private void reassessValidity() {
        ButtonHandler buttonHandler = this.mButtonLayout;
        boolean z = this.mDatePickerValid && this.mTimePickerValid;
        buttonHandler.updateValidity(z);
    }

    public void onDateChanged(SublimeDatePicker view, SelectedDate selectedDate) {
        this.mDatePicker.init(selectedDate, this.mOptions.canPickDateRange(), this);
    }

    public void onDatePickerValidationChanged(boolean valid) {
        this.mDatePickerValid = valid;
        reassessValidity();
    }

    public void onTimePickerValidationChanged(boolean valid) {
        this.mTimePickerValid = valid;
        reassessValidity();
    }
}
