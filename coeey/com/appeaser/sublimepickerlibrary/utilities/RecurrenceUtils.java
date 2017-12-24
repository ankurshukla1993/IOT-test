package com.appeaser.sublimepickerlibrary.utilities;

import java.util.Calendar;

public class RecurrenceUtils {
    public static int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if (startDay == 7) {
            return 6;
        }
        if (startDay == 2) {
            return 1;
        }
        return 0;
    }

    public static int getFirstDayOfWeekAsCalendar() {
        return convertDayOfWeekFromTimeToCalendar(getFirstDayOfWeek());
    }

    public static int convertDayOfWeekFromTimeToCalendar(int timeDayOfWeek) {
        switch (timeDayOfWeek) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            default:
                throw new IllegalArgumentException("Argument must be between Time.SUNDAY and Time.SATURDAY");
        }
    }
}
