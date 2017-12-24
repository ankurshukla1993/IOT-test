package com.appeaser.sublimepickerlibrary.recurrencepicker;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.TimeFormatException;
import com.appeaser.sublimepickerlibrary.C0563R;

public class EventRecurrenceFormatter {
    private static int[] mMonthRepeatByDayOfWeekIds;
    private static String[][] mMonthRepeatByDayOfWeekStrs;

    public static String getRepeatString(Context context, Resources r, EventRecurrence recurrence, boolean includeEndString) {
        StringBuilder sb;
        String endString = "";
        if (includeEndString) {
            sb = new StringBuilder();
            if (recurrence.until != null) {
                try {
                    Time t = new Time();
                    t.parse(recurrence.until);
                    String dateStr = DateUtils.formatDateTime(context, t.toMillis(false), 131072);
                    sb.append(r.getString(C0563R.string.endByDate, new Object[]{dateStr}));
                } catch (TimeFormatException e) {
                }
            }
            if (recurrence.count > 0) {
                sb.append(r.getQuantityString(C0563R.plurals.endByCount, recurrence.count, new Object[]{Integer.valueOf(recurrence.count)}));
            }
            endString = sb.toString();
        }
        int interval = recurrence.interval <= 1 ? 1 : recurrence.interval;
        switch (recurrence.freq) {
            case 4:
                return r.getQuantityString(C0563R.plurals.daily, interval, new Object[]{Integer.valueOf(interval)}) + endString;
            case 5:
                if (recurrence.repeatsOnEveryWeekDay()) {
                    return r.getString(C0563R.string.every_weekday) + endString;
                }
                String string;
                int dayOfWeekLength = 20;
                if (recurrence.bydayCount == 1) {
                    dayOfWeekLength = 10;
                }
                StringBuilder days = new StringBuilder();
                if (recurrence.bydayCount > 0) {
                    int count = recurrence.bydayCount - 1;
                    for (int i = 0; i < count; i++) {
                        days.append(dayToString(recurrence.byday[i], dayOfWeekLength));
                        days.append(", ");
                    }
                    days.append(dayToString(recurrence.byday[count], dayOfWeekLength));
                    string = days.toString();
                } else if (recurrence.startDate == null) {
                    return null;
                } else {
                    string = dayToString(EventRecurrence.timeDay2Day(recurrence.startDate.weekDay), 10);
                }
                return r.getQuantityString(C0563R.plurals.weekly, interval, new Object[]{Integer.valueOf(interval), string}) + endString;
            case 6:
                String monthlyStart;
                if (interval == 1) {
                    monthlyStart = r.getString(C0563R.string.monthly);
                } else {
                    monthlyStart = r.getQuantityString(C0563R.plurals.recurrence_interval_monthly, interval, new Object[]{Integer.valueOf(interval)});
                }
                if (recurrence.bydayCount != 1) {
                    return monthlyStart + endString;
                }
                int weekday = recurrence.startDate.weekDay;
                cacheMonthRepeatStrings(r, weekday);
                int dayNumber = (recurrence.startDate.monthDay - 1) / 7;
                sb = new StringBuilder();
                sb.append(monthlyStart);
                sb.append(" (");
                sb.append(mMonthRepeatByDayOfWeekStrs[weekday][dayNumber]);
                sb.append(")");
                sb.append(endString);
                return sb.toString();
            case 7:
                String yearlyStart;
                if (interval == 1) {
                    yearlyStart = r.getString(C0563R.string.yearly_plain);
                } else {
                    yearlyStart = r.getQuantityString(C0563R.plurals.recurrence_interval_yearly, interval, new Object[]{Integer.valueOf(interval)});
                }
                return yearlyStart + endString;
            default:
                return null;
        }
    }

    private static void cacheMonthRepeatStrings(Resources r, int weekday) {
        if (mMonthRepeatByDayOfWeekIds == null) {
            mMonthRepeatByDayOfWeekIds = new int[7];
            mMonthRepeatByDayOfWeekIds[0] = C0563R.array.repeat_by_nth_sun;
            mMonthRepeatByDayOfWeekIds[1] = C0563R.array.repeat_by_nth_mon;
            mMonthRepeatByDayOfWeekIds[2] = C0563R.array.repeat_by_nth_tues;
            mMonthRepeatByDayOfWeekIds[3] = C0563R.array.repeat_by_nth_wed;
            mMonthRepeatByDayOfWeekIds[4] = C0563R.array.repeat_by_nth_thurs;
            mMonthRepeatByDayOfWeekIds[5] = C0563R.array.repeat_by_nth_fri;
            mMonthRepeatByDayOfWeekIds[6] = C0563R.array.repeat_by_nth_sat;
        }
        if (mMonthRepeatByDayOfWeekStrs == null) {
            mMonthRepeatByDayOfWeekStrs = new String[7][];
        }
        if (mMonthRepeatByDayOfWeekStrs[weekday] == null) {
            mMonthRepeatByDayOfWeekStrs[weekday] = r.getStringArray(mMonthRepeatByDayOfWeekIds[weekday]);
        }
    }

    private static String dayToString(int day, int dayOfWeekLength) {
        return DateUtils.getDayOfWeekString(dayToUtilDay(day), dayOfWeekLength);
    }

    private static int dayToUtilDay(int day) {
        switch (day) {
            case 65536:
                return 1;
            case 131072:
                return 2;
            case 262144:
                return 3;
            case 524288:
                return 4;
            case 1048576:
                return 5;
            case 2097152:
                return 6;
            case 4194304:
                return 7;
            default:
                throw new IllegalArgumentException("bad day argument: " + day);
        }
    }
}
