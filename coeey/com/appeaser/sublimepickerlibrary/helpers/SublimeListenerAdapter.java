package com.appeaser.sublimepickerlibrary.helpers;

import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import java.util.Date;

public abstract class SublimeListenerAdapter {
    public abstract void onCancelled();

    public abstract void onDateTimeRecurrenceSet(SublimePicker sublimePicker, SelectedDate selectedDate, int i, int i2, RecurrenceOption recurrenceOption, String str);

    public CharSequence formatDate(SelectedDate selectedDate) {
        return null;
    }

    public CharSequence formatTime(Date selectedTime) {
        return null;
    }
}
