package com.alamkanak.weekview;

import java.util.Calendar;
import java.util.List;

public class MonthLoader implements WeekViewLoader {
    private MonthChangeListener mOnMonthChangeListener;

    public interface MonthChangeListener {
        List<? extends WeekViewEvent> onMonthChange(int i, int i2);
    }

    public MonthLoader(MonthChangeListener listener) {
        this.mOnMonthChangeListener = listener;
    }

    public double toWeekViewPeriodIndex(Calendar instance) {
        return ((double) ((instance.get(1) * 12) + instance.get(2))) + (((double) (instance.get(5) - 1)) / 30.0d);
    }

    public List<? extends WeekViewEvent> onLoad(int periodIndex) {
        return this.mOnMonthChangeListener.onMonthChange(periodIndex / 12, (periodIndex % 12) + 1);
    }

    public MonthChangeListener getOnMonthChangeListener() {
        return this.mOnMonthChangeListener;
    }

    public void setOnMonthChangeListener(MonthChangeListener onMonthChangeListener) {
        this.mOnMonthChangeListener = onMonthChangeListener;
    }
}
