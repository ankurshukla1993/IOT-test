package com.appeaser.sublimepickerlibrary.helpers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.util.Calendar;
import java.util.Locale;

public class SublimeOptions implements Parcelable {
    public static final int ACTIVATE_DATE_PICKER = 1;
    public static final int ACTIVATE_RECURRENCE_PICKER = 4;
    public static final int ACTIVATE_TIME_PICKER = 2;
    public static final Creator<SublimeOptions> CREATOR = new C05851();
    private boolean mAnimateLayoutChanges;
    private boolean mCanPickDateRange;
    private int mDisplayOptions;
    private int mEndDayOfMonth;
    private int mEndMonth;
    private int mEndYear;
    private int mHourOfDay;
    private boolean mIs24HourView;
    private long mMaxDate;
    private long mMinDate;
    private int mMinute;
    private Picker mPickerToShow;
    private RecurrenceOption mRecurrenceOption;
    private String mRecurrenceRule;
    private int mStartDayOfMonth;
    private int mStartMonth;
    private int mStartYear;

    static class C05851 implements Creator<SublimeOptions> {
        C05851() {
        }

        public SublimeOptions createFromParcel(Parcel in) {
            return new SublimeOptions(in);
        }

        public SublimeOptions[] newArray(int size) {
            return new SublimeOptions[size];
        }
    }

    public class InvalidOptionsException extends RuntimeException {
        public InvalidOptionsException(String detailMessage) {
            super(detailMessage);
        }
    }

    public enum Picker {
        DATE_PICKER,
        TIME_PICKER,
        REPEAT_OPTION_PICKER,
        INVALID
    }

    public SublimeOptions() {
        this.mDisplayOptions = 7;
        this.mStartYear = -1;
        this.mStartMonth = -1;
        this.mStartDayOfMonth = -1;
        this.mEndYear = -1;
        this.mEndMonth = -1;
        this.mEndDayOfMonth = -1;
        this.mHourOfDay = -1;
        this.mMinute = -1;
        this.mMinDate = Long.MIN_VALUE;
        this.mMaxDate = Long.MIN_VALUE;
        this.mRecurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
        this.mRecurrenceRule = "";
        this.mPickerToShow = Picker.DATE_PICKER;
    }

    private SublimeOptions(Parcel in) {
        this.mDisplayOptions = 7;
        this.mStartYear = -1;
        this.mStartMonth = -1;
        this.mStartDayOfMonth = -1;
        this.mEndYear = -1;
        this.mEndMonth = -1;
        this.mEndDayOfMonth = -1;
        this.mHourOfDay = -1;
        this.mMinute = -1;
        this.mMinDate = Long.MIN_VALUE;
        this.mMaxDate = Long.MIN_VALUE;
        this.mRecurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
        this.mRecurrenceRule = "";
        this.mPickerToShow = Picker.DATE_PICKER;
        readFromParcel(in);
    }

    public SublimeOptions setAnimateLayoutChanges(boolean animateLayoutChanges) {
        this.mAnimateLayoutChanges = animateLayoutChanges;
        return this;
    }

    public boolean animateLayoutChanges() {
        return this.mAnimateLayoutChanges;
    }

    public SublimeOptions setPickerToShow(Picker picker) {
        this.mPickerToShow = picker;
        return this;
    }

    private boolean isPickerActive(Picker picker) {
        switch (picker) {
            case DATE_PICKER:
                return isDatePickerActive();
            case TIME_PICKER:
                return isTimePickerActive();
            case REPEAT_OPTION_PICKER:
                return isRecurrencePickerActive();
            default:
                return false;
        }
    }

    public Picker getPickerToShow() {
        return this.mPickerToShow;
    }

    public SublimeOptions setDisplayOptions(int displayOptions) {
        if (areValidDisplayOptions(displayOptions)) {
            this.mDisplayOptions = displayOptions;
            return this;
        }
        throw new IllegalArgumentException("Invalid display options.");
    }

    private boolean areValidDisplayOptions(int displayOptions) {
        return (displayOptions & -8) == 0;
    }

    public SublimeOptions setDateParams(int year, int month, int dayOfMonth) {
        return setDateParams(year, month, dayOfMonth, year, month, dayOfMonth);
    }

    public SublimeOptions setDateParams(int startYear, int startMonth, int startDayOfMonth, int endYear, int endMonth, int endDayOfMonth) {
        this.mStartYear = startYear;
        this.mStartMonth = startMonth;
        this.mStartDayOfMonth = startDayOfMonth;
        this.mEndYear = endYear;
        this.mEndMonth = endMonth;
        this.mEndDayOfMonth = endDayOfMonth;
        return this;
    }

    public SublimeOptions setDateParams(@NonNull Calendar calendar) {
        return setDateParams(calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(1), calendar.get(2), calendar.get(5));
    }

    public SublimeOptions setDateParams(@NonNull Calendar startCal, @NonNull Calendar endCal) {
        return setDateParams(startCal.get(1), startCal.get(2), startCal.get(5), endCal.get(1), endCal.get(2), endCal.get(5));
    }

    public SublimeOptions setDateParams(@NonNull SelectedDate selectedDate) {
        return setDateParams(selectedDate.getStartDate().get(1), selectedDate.getStartDate().get(2), selectedDate.getStartDate().get(5), selectedDate.getEndDate().get(1), selectedDate.getEndDate().get(2), selectedDate.getEndDate().get(5));
    }

    public SublimeOptions setDateRange(long minDate, long maxDate) {
        this.mMinDate = minDate;
        this.mMaxDate = maxDate;
        return this;
    }

    public SublimeOptions setTimeParams(int hourOfDay, int minute, boolean is24HourView) {
        this.mHourOfDay = hourOfDay;
        this.mMinute = minute;
        this.mIs24HourView = is24HourView;
        return this;
    }

    public SublimeOptions setRecurrenceParams(RecurrenceOption recurrenceOption, String recurrenceRule) {
        if (recurrenceOption == null || (recurrenceOption == RecurrenceOption.CUSTOM && TextUtils.isEmpty(recurrenceRule))) {
            recurrenceOption = RecurrenceOption.DOES_NOT_REPEAT;
            recurrenceRule = null;
        } else if (recurrenceOption != RecurrenceOption.CUSTOM) {
            recurrenceRule = null;
        }
        this.mRecurrenceOption = recurrenceOption;
        this.mRecurrenceRule = recurrenceRule;
        return this;
    }

    public String getRecurrenceRule() {
        return this.mRecurrenceRule == null ? "" : this.mRecurrenceRule;
    }

    public RecurrenceOption getRecurrenceOption() {
        return this.mRecurrenceOption == null ? RecurrenceOption.DOES_NOT_REPEAT : this.mRecurrenceOption;
    }

    public boolean isDatePickerActive() {
        return (this.mDisplayOptions & 1) == 1;
    }

    public boolean isTimePickerActive() {
        return (this.mDisplayOptions & 2) == 2;
    }

    public boolean isRecurrencePickerActive() {
        return (this.mDisplayOptions & 4) == 4;
    }

    public SelectedDate getDateParams() {
        Calendar startCal = SUtils.getCalendarForLocale(null, Locale.getDefault());
        if (this.mStartYear == -1 || this.mStartMonth == -1 || this.mStartDayOfMonth == -1) {
            this.mStartYear = startCal.get(1);
            this.mStartMonth = startCal.get(2);
            this.mStartDayOfMonth = startCal.get(5);
        } else {
            startCal.set(this.mStartYear, this.mStartMonth, this.mStartDayOfMonth);
        }
        Calendar endCal = SUtils.getCalendarForLocale(null, Locale.getDefault());
        if (this.mEndYear == -1 || this.mEndMonth == -1 || this.mEndDayOfMonth == -1) {
            this.mEndYear = endCal.get(1);
            this.mEndMonth = endCal.get(2);
            this.mEndDayOfMonth = endCal.get(5);
        } else {
            endCal.set(this.mEndYear, this.mEndMonth, this.mEndDayOfMonth);
        }
        return new SelectedDate(startCal, endCal);
    }

    public long[] getDateRange() {
        return new long[]{this.mMinDate, this.mMaxDate};
    }

    public int[] getTimeParams() {
        if (this.mHourOfDay == -1 || this.mMinute == -1) {
            Calendar cal = SUtils.getCalendarForLocale(null, Locale.getDefault());
            this.mHourOfDay = cal.get(11);
            this.mMinute = cal.get(12);
        }
        return new int[]{this.mHourOfDay, this.mMinute};
    }

    public boolean is24HourView() {
        return this.mIs24HourView;
    }

    public void verifyValidity() {
        if (this.mPickerToShow == null || this.mPickerToShow == Picker.INVALID) {
            throw new InvalidOptionsException("The picker set using setPickerToShow(Picker) cannot be null or Picker.INVALID.");
        } else if (!isPickerActive(this.mPickerToShow)) {
            throw new InvalidOptionsException("The picker you have requested to show(" + this.mPickerToShow.name() + ") is not activated. " + "Use setDisplayOptions(int) " + "to activate it, or use an activated Picker with setPickerToShow(Picker).");
        }
    }

    public SublimeOptions setCanPickDateRange(boolean canPickDateRange) {
        this.mCanPickDateRange = canPickDateRange;
        return this;
    }

    public boolean canPickDateRange() {
        return this.mCanPickDateRange;
    }

    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        boolean z = true;
        this.mAnimateLayoutChanges = in.readByte() != (byte) 0;
        this.mPickerToShow = Picker.valueOf(in.readString());
        this.mDisplayOptions = in.readInt();
        this.mStartYear = in.readInt();
        this.mStartMonth = in.readInt();
        this.mStartDayOfMonth = in.readInt();
        this.mEndYear = in.readInt();
        this.mEndMonth = in.readInt();
        this.mEndDayOfMonth = in.readInt();
        this.mHourOfDay = in.readInt();
        this.mMinute = in.readInt();
        this.mIs24HourView = in.readByte() != (byte) 0;
        this.mRecurrenceRule = in.readString();
        if (in.readByte() == (byte) 0) {
            z = false;
        }
        this.mCanPickDateRange = z;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeByte((byte) (this.mAnimateLayoutChanges ? 1 : 0));
        dest.writeString(this.mPickerToShow.name());
        dest.writeInt(this.mDisplayOptions);
        dest.writeInt(this.mStartYear);
        dest.writeInt(this.mStartMonth);
        dest.writeInt(this.mStartDayOfMonth);
        dest.writeInt(this.mEndYear);
        dest.writeInt(this.mEndMonth);
        dest.writeInt(this.mEndDayOfMonth);
        dest.writeInt(this.mHourOfDay);
        dest.writeInt(this.mMinute);
        dest.writeByte((byte) (this.mIs24HourView ? 1 : 0));
        dest.writeString(this.mRecurrenceRule);
        if (!this.mCanPickDateRange) {
            i = 0;
        }
        dest.writeByte((byte) i);
    }
}
