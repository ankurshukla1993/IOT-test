package com.biz.health.cooey_app.utils;

import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import humanize.util.Constants;
import java.sql.Time;
import java.util.TimeZone;

public class TimeUtil {
    public static String getTimeString(int hour, int minute) {
        String zone = "AM";
        String minuteString = String.valueOf(minute);
        if (minute < 10) {
            minuteString = AppEventsConstants.EVENT_PARAM_VALUE_NO + minuteString;
        }
        if (hour > 12) {
            return String.valueOf(hour - 12) + ":" + minuteString + " PM";
        }
        return String.valueOf(hour) + ":" + minuteString + " AM";
    }

    public static boolean isIndia() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            Log.v("TimeZoneTest", timeZone.getID());
            if (timeZone.getID().contains("America")) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Time getTime(String timeString) {
        String[] strings = timeString.split(Constants.SPACE);
        String[] timeVaues = strings[0].split(":");
        int hour = Integer.valueOf(timeVaues[0]).intValue();
        int minute = Integer.valueOf(timeVaues[1]).intValue();
        if (strings[1].contentEquals("AM")) {
            return new Time(hour, minute, 0);
        }
        return new Time(hour + 12, minute, 0);
    }
}
