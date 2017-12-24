package com.appeaser.sublimepickerlibrary.datepicker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.common.DecisionButtonLayout;
import com.appeaser.sublimepickerlibrary.common.DecisionButtonLayout.Callback;
import com.appeaser.sublimepickerlibrary.datepicker.DayPickerView.ProxyDaySelectionEventListener;
import com.appeaser.sublimepickerlibrary.utilities.AccessibilityUtils;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.util.Calendar;
import java.util.Locale;

public class RecurrenceEndDatePicker extends FrameLayout {
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final String TAG = RecurrenceEndDatePicker.class.getSimpleName();
    private ViewGroup mContainer;
    private Context mContext;
    private SelectedDate mCurrentDate;
    private Locale mCurrentLocale;
    private DayPickerView mDayPickerView;
    private DecisionButtonLayout mDecisionButtonLayout;
    private Callback mDecisionButtonLayoutCallback;
    private int mFirstDayOfWeek;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private OnDateSetListener mOnDateSetListener;
    private final ProxyDaySelectionEventListener mProxyDaySelectionEventListener;
    private Calendar mTempDate;
    private DatePickerValidationCallback mValidationCallback;

    class C05741 implements Callback {
        C05741() {
        }

        public void onOkay() {
            if (RecurrenceEndDatePicker.this.mOnDateSetListener != null) {
                RecurrenceEndDatePicker.this.mOnDateSetListener.onDateSet(RecurrenceEndDatePicker.this, RecurrenceEndDatePicker.this.mCurrentDate.getStartDate().get(1), RecurrenceEndDatePicker.this.mCurrentDate.getStartDate().get(2), RecurrenceEndDatePicker.this.mCurrentDate.getStartDate().get(5));
            }
        }

        public void onCancel() {
            if (RecurrenceEndDatePicker.this.mOnDateSetListener != null) {
                RecurrenceEndDatePicker.this.mOnDateSetListener.onDateOnlyPickerCancelled(RecurrenceEndDatePicker.this);
            }
        }
    }

    class C05752 implements ProxyDaySelectionEventListener {
        C05752() {
        }

        public void onDaySelected(DayPickerView view, Calendar day) {
            RecurrenceEndDatePicker.this.mCurrentDate = new SelectedDate(day);
            RecurrenceEndDatePicker.this.onDateChanged(true, true);
        }

        public void onDateRangeSelectionStarted(@NonNull SelectedDate selectedDate) {
            RecurrenceEndDatePicker.this.mCurrentDate = new SelectedDate(selectedDate);
            RecurrenceEndDatePicker.this.onDateChanged(false, false);
        }

        public void onDateRangeSelectionEnded(@Nullable SelectedDate selectedDate) {
            if (selectedDate != null) {
                RecurrenceEndDatePicker.this.mCurrentDate = new SelectedDate(selectedDate);
                RecurrenceEndDatePicker.this.onDateChanged(false, false);
            }
        }

        public void onDateRangeSelectionUpdated(@NonNull SelectedDate selectedDate) {
            RecurrenceEndDatePicker.this.mCurrentDate = new SelectedDate(selectedDate);
            RecurrenceEndDatePicker.this.onDateChanged(false, false);
        }
    }

    public interface DatePickerValidationCallback {
        void onDatePickerValidationChanged(boolean z);
    }

    public interface OnDateChangedListener {
        void onDateChanged(RecurrenceEndDatePicker recurrenceEndDatePicker, SelectedDate selectedDate);
    }

    public interface OnDateSetListener {
        void onDateOnlyPickerCancelled(RecurrenceEndDatePicker recurrenceEndDatePicker);

        void onDateSet(RecurrenceEndDatePicker recurrenceEndDatePicker, int i, int i2, int i3);
    }

    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C05761();
        private final int mListPosition;
        private final long mMaxDate;
        private final long mMinDate;
        private final int mSelectedDay;
        private final int mSelectedMonth;
        private final int mSelectedYear;

        static class C05761 implements Creator<SavedState> {
            C05761() {
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        }

        private SavedState(Parcelable superState, SelectedDate selectedDate, long minDate, long maxDate, int listPosition) {
            super(superState);
            this.mSelectedYear = selectedDate.getStartDate().get(1);
            this.mSelectedMonth = selectedDate.getStartDate().get(2);
            this.mSelectedDay = selectedDate.getStartDate().get(5);
            this.mMinDate = minDate;
            this.mMaxDate = maxDate;
            this.mListPosition = listPosition;
        }

        private SavedState(Parcel in) {
            super(in);
            this.mSelectedYear = in.readInt();
            this.mSelectedMonth = in.readInt();
            this.mSelectedDay = in.readInt();
            this.mMinDate = in.readLong();
            this.mMaxDate = in.readLong();
            this.mListPosition = in.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.mSelectedYear);
            dest.writeInt(this.mSelectedMonth);
            dest.writeInt(this.mSelectedDay);
            dest.writeLong(this.mMinDate);
            dest.writeLong(this.mMaxDate);
            dest.writeInt(this.mListPosition);
        }

        public int getSelectedDay() {
            return this.mSelectedDay;
        }

        public int getSelectedMonth() {
            return this.mSelectedMonth;
        }

        public int getSelectedYear() {
            return this.mSelectedYear;
        }

        public long getMinDate() {
            return this.mMinDate;
        }

        public long getMaxDate() {
            return this.mMaxDate;
        }

        public int getListPosition() {
            return this.mListPosition;
        }
    }

    public RecurrenceEndDatePicker(Context context) {
        this(context, null);
    }

    public RecurrenceEndDatePicker(Context context, AttributeSet attrs) {
        this(context, attrs, C0563R.attr.spDatePickerStyle);
    }

    public RecurrenceEndDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDecisionButtonLayoutCallback = new C05741();
        this.mProxyDaySelectionEventListener = new C05752();
        initializeLayout(attrs, defStyleAttr, C0563R.style.SublimeDatePickerStyle);
    }

    @TargetApi(21)
    public RecurrenceEndDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mDecisionButtonLayoutCallback = new C05741();
        this.mProxyDaySelectionEventListener = new C05752();
        initializeLayout(attrs, defStyleAttr, defStyleRes);
    }

    private void initializeLayout(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.mContext = getContext();
        setCurrentLocale(Locale.getDefault());
        this.mCurrentDate = new SelectedDate(Calendar.getInstance(this.mCurrentLocale));
        this.mTempDate = Calendar.getInstance(this.mCurrentLocale);
        this.mMinDate = Calendar.getInstance(this.mCurrentLocale);
        this.mMaxDate = Calendar.getInstance(this.mCurrentLocale);
        this.mMinDate.set(DEFAULT_START_YEAR, 0, 1);
        this.mMaxDate.set(DEFAULT_END_YEAR, 11, 31);
        Resources res = getResources();
        TypedArray a = this.mContext.obtainStyledAttributes(attrs, C0563R.styleable.SublimeDatePicker, defStyleAttr, defStyleRes);
        try {
            this.mContainer = (ViewGroup) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(C0563R.layout.recurrence_end_date_picker, this, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addView(this.mContainer);
        int firstDayOfWeek = a.getInt(C0563R.styleable.SublimeDatePicker_spFirstDayOfWeek, this.mCurrentDate.getFirstDate().getFirstDayOfWeek());
        String minDate = a.getString(C0563R.styleable.SublimeDatePicker_spMinDate);
        String maxDate = a.getString(C0563R.styleable.SublimeDatePicker_spMaxDate);
        Calendar tempDate = Calendar.getInstance();
        if (!SUtils.parseDate(minDate, tempDate)) {
            tempDate.set(DEFAULT_START_YEAR, 0, 1);
        }
        long minDateMillis = tempDate.getTimeInMillis();
        if (!SUtils.parseDate(maxDate, tempDate)) {
            tempDate.set(DEFAULT_END_YEAR, 11, 31);
        }
        long maxDateMillis = tempDate.getTimeInMillis();
        if (maxDateMillis < minDateMillis) {
            throw new IllegalArgumentException("maxDate must be >= minDate");
        }
        long setDateMillis = SUtils.constrain(System.currentTimeMillis(), minDateMillis, maxDateMillis);
        this.mMinDate.setTimeInMillis(minDateMillis);
        this.mMaxDate.setTimeInMillis(maxDateMillis);
        this.mCurrentDate.setTimeInMillis(setDateMillis);
        a.recycle();
        this.mDecisionButtonLayout = (DecisionButtonLayout) this.mContainer.findViewById(C0563R.id.redp_decision_button_layout);
        this.mDecisionButtonLayout.applyOptions(this.mDecisionButtonLayoutCallback);
        this.mDayPickerView = (DayPickerView) this.mContainer.findViewById(C0563R.id.redp_day_picker);
        setFirstDayOfWeek(firstDayOfWeek);
        this.mDayPickerView.setMinDate(this.mMinDate.getTimeInMillis());
        this.mDayPickerView.setMaxDate(this.mMaxDate.getTimeInMillis());
        this.mDayPickerView.setDate(this.mCurrentDate);
        this.mDayPickerView.setProxyDaySelectionEventListener(this.mProxyDaySelectionEventListener);
        this.mDayPickerView.setCanPickRange(false);
        String selectDay = res.getString(C0563R.string.select_day);
        onLocaleChanged(this.mCurrentLocale);
        AccessibilityUtils.makeAnnouncement(this.mDayPickerView, selectDay);
    }

    private void onLocaleChanged(Locale locale) {
        if (this.mDayPickerView != null) {
            onCurrentDateChanged(false);
        }
    }

    private void onCurrentDateChanged(boolean announce) {
        if (this.mDayPickerView != null && announce) {
            AccessibilityUtils.makeAnnouncement(this.mDayPickerView, DateUtils.formatDateTime(this.mContext, this.mCurrentDate.getStartDate().getTimeInMillis(), 20));
        }
    }

    public void init(int year, int monthOfYear, int dayOfMonth, OnDateSetListener callback) {
        this.mCurrentDate.set(1, year);
        this.mCurrentDate.set(2, monthOfYear);
        this.mCurrentDate.set(5, dayOfMonth);
        this.mOnDateSetListener = callback;
        onDateChanged(false, true);
    }

    public void updateDate(int year, int month, int dayOfMonth) {
        this.mCurrentDate.set(1, year);
        this.mCurrentDate.set(2, month);
        this.mCurrentDate.set(5, dayOfMonth);
        onDateChanged(false, true);
    }

    private void onDateChanged(boolean fromUser, boolean goToPosition) {
        this.mDayPickerView.setDate(new SelectedDate(this.mCurrentDate), false, goToPosition);
        onCurrentDateChanged(fromUser);
        if (fromUser) {
            SUtils.vibrateForDatePicker(this);
        }
    }

    public SelectedDate getSelectedDate() {
        return new SelectedDate(this.mCurrentDate);
    }

    public long getSelectedDateInMillis() {
        return this.mCurrentDate.getStartDate().getTimeInMillis();
    }

    public void setMinDate(long minDate) {
        this.mTempDate.setTimeInMillis(minDate);
        if (this.mTempDate.get(1) != this.mMinDate.get(1) || this.mTempDate.get(6) == this.mMinDate.get(6)) {
            if (this.mCurrentDate.getStartDate().before(this.mTempDate)) {
                this.mCurrentDate.getStartDate().setTimeInMillis(minDate);
                onDateChanged(false, true);
            }
            this.mMinDate.setTimeInMillis(minDate);
            this.mDayPickerView.setMinDate(minDate);
        }
    }

    public Calendar getMinDate() {
        return this.mMinDate;
    }

    public void setMaxDate(long maxDate) {
        this.mTempDate.setTimeInMillis(maxDate);
        if (this.mTempDate.get(1) != this.mMaxDate.get(1) || this.mTempDate.get(6) == this.mMaxDate.get(6)) {
            if (this.mCurrentDate.getEndDate().after(this.mTempDate)) {
                this.mCurrentDate.getEndDate().setTimeInMillis(maxDate);
                onDateChanged(false, true);
            }
            this.mMaxDate.setTimeInMillis(maxDate);
            this.mDayPickerView.setMaxDate(maxDate);
        }
    }

    public Calendar getMaxDate() {
        return this.mMaxDate;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        if (firstDayOfWeek < 1 || firstDayOfWeek > 7) {
            firstDayOfWeek = this.mCurrentDate.getFirstDate().getFirstDayOfWeek();
        }
        this.mFirstDayOfWeek = firstDayOfWeek;
        this.mDayPickerView.setFirstDayOfWeek(firstDayOfWeek);
    }

    public int getFirstDayOfWeek() {
        return this.mFirstDayOfWeek;
    }

    public void setEnabled(boolean enabled) {
        if (isEnabled() != enabled) {
            this.mContainer.setEnabled(enabled);
            this.mDayPickerView.setEnabled(enabled);
        }
    }

    public boolean isEnabled() {
        return this.mContainer.isEnabled();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        setCurrentLocale(newConfig.locale);
    }

    private void setCurrentLocale(Locale locale) {
        if (!locale.equals(this.mCurrentLocale)) {
            this.mCurrentLocale = locale;
            onLocaleChanged(locale);
        }
    }

    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.mCurrentDate, this.mMinDate.getTimeInMillis(), this.mMaxDate.getTimeInMillis(), this.mDayPickerView.getMostVisiblePosition());
    }

    @SuppressLint({"NewApi"})
    public void onRestoreInstanceState(Parcelable state) {
        BaseSavedState bss = (BaseSavedState) state;
        super.onRestoreInstanceState(bss.getSuperState());
        SavedState ss = (SavedState) bss;
        Calendar date = Calendar.getInstance(this.mCurrentLocale);
        date.set(ss.getSelectedYear(), ss.getSelectedMonth(), ss.getSelectedDay());
        this.mCurrentDate.setDate(date);
        this.mMinDate.setTimeInMillis(ss.getMinDate());
        this.mMaxDate.setTimeInMillis(ss.getMaxDate());
        onCurrentDateChanged(false);
        int listPosition = ss.getListPosition();
        if (listPosition != -1) {
            this.mDayPickerView.setPosition(listPosition);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        onPopulateAccessibilityEvent(event);
        return true;
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
        event.getText().add(this.mCurrentDate.getStartDate().getTime().toString());
    }

    public CharSequence getAccessibilityClassName() {
        return SublimeDatePicker.class.getName();
    }

    public void setValidationCallback(DatePickerValidationCallback callback) {
        this.mValidationCallback = callback;
    }

    protected void onValidationChanged(boolean valid) {
        if (this.mValidationCallback != null) {
            this.mValidationCallback.onDatePickerValidationChanged(valid);
        }
        this.mDecisionButtonLayout.updateValidity(valid);
    }
}
