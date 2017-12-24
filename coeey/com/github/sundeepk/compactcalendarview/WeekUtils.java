package com.github.sundeepk.compactcalendarview;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class WeekUtils {
    static String[] getWeekdayNames(Locale locale, int day, boolean useThreeLetterAbbreviation) {
        String[] dayNames = new DateFormatSymbols(locale).getShortWeekdays();
        if (dayNames == null) {
            throw new IllegalStateException("Unable to determine weekday names from default locale");
        } else if (dayNames.length != 8) {
            throw new IllegalStateException("Expected weekday names from default locale to be of size 7 but: " + Arrays.toString(dayNames) + " with size " + dayNames.length + " was returned.");
        } else {
            weekDayNames = new String[7];
            String[] weekDaysFromSunday = new String[]{dayNames[1], dayNames[2], dayNames[3], dayNames[4], dayNames[5], dayNames[6], dayNames[7]};
            int currentDay = day - 1;
            int i = 0;
            while (i <= 6) {
                if (currentDay >= 7) {
                    currentDay = 0;
                }
                weekDayNames[i] = weekDaysFromSunday[currentDay];
                i++;
                currentDay++;
            }
            if (!useThreeLetterAbbreviation) {
                for (i = 0; i < weekDayNames.length; i++) {
                    weekDayNames[i] = weekDayNames[i].substring(0, 1);
                }
            }
            return weekDayNames;
        }
    }
}
