package com.appeaser.sublimepickerlibrary.datepicker;

import java.text.DateFormat;
import java.util.Calendar;

public class SelectedDate {
    private Calendar mFirstDate;
    private Calendar mSecondDate;

    public enum Type {
        SINGLE,
        RANGE
    }

    public SelectedDate(Calendar startDate, Calendar endDate) {
        this.mFirstDate = startDate;
        this.mSecondDate = endDate;
    }

    public SelectedDate(Calendar date) {
        this.mSecondDate = date;
        this.mFirstDate = date;
    }

    public SelectedDate(SelectedDate date) {
        this.mFirstDate = Calendar.getInstance();
        this.mSecondDate = Calendar.getInstance();
        if (date != null) {
            this.mFirstDate.setTimeInMillis(date.getStartDate().getTimeInMillis());
            this.mSecondDate.setTimeInMillis(date.getEndDate().getTimeInMillis());
        }
    }

    public Calendar getFirstDate() {
        return this.mFirstDate;
    }

    public void setFirstDate(Calendar firstDate) {
        this.mFirstDate = firstDate;
    }

    public Calendar getSecondDate() {
        return this.mSecondDate;
    }

    public void setSecondDate(Calendar secondDate) {
        this.mSecondDate = secondDate;
    }

    public void setDate(Calendar date) {
        this.mFirstDate = date;
        this.mSecondDate = date;
    }

    public Calendar getStartDate() {
        return compareDates(this.mFirstDate, this.mSecondDate) == -1 ? this.mFirstDate : this.mSecondDate;
    }

    public Calendar getEndDate() {
        return compareDates(this.mFirstDate, this.mSecondDate) == 1 ? this.mFirstDate : this.mSecondDate;
    }

    public Type getType() {
        return compareDates(this.mFirstDate, this.mSecondDate) == 0 ? Type.SINGLE : Type.RANGE;
    }

    public static int compareDates(Calendar a, Calendar b) {
        int aYear = a.get(1);
        int bYear = b.get(1);
        int aMonth = a.get(2);
        int bMonth = b.get(2);
        int aDayOfMonth = a.get(5);
        int bDayOfMonth = b.get(5);
        if (aYear < bYear) {
            return -1;
        }
        if (aYear > bYear) {
            return 1;
        }
        if (aMonth < bMonth) {
            return -1;
        }
        if (aMonth > bMonth) {
            return 1;
        }
        if (aDayOfMonth < bDayOfMonth) {
            return -1;
        }
        if (aDayOfMonth > bDayOfMonth) {
            return 1;
        }
        return 0;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.mFirstDate.setTimeInMillis(timeInMillis);
        this.mSecondDate.setTimeInMillis(timeInMillis);
    }

    public void set(int field, int value) {
        this.mFirstDate.set(field, value);
        this.mSecondDate.set(field, value);
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        if (this.mFirstDate != null) {
            toReturn.append(DateFormat.getDateInstance().format(this.mFirstDate.getTime()));
            toReturn.append("\n");
        }
        if (this.mSecondDate != null) {
            toReturn.append(DateFormat.getDateInstance().format(this.mSecondDate.getTime()));
        }
        return toReturn.toString();
    }
}
