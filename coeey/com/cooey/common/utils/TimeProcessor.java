package com.cooey.common.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeProcessor {
    public long getTimeRemaining(int hours, int minutes) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentHours = calendar.get(10);
        int currentMinutes = calendar.get(12);
        if (currentHours >= hours && currentMinutes >= minutes) {
            calendar.add(5, 1);
        }
        calendar.set(10, hours);
        calendar.set(12, minutes);
        return calendar.getTime().getTime() - date.getTime();
    }

    public long getTimeRemaining(Date time) {
        return time.getTime() - new Date().getTime();
    }
}
