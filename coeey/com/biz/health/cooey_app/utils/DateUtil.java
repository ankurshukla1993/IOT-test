package com.biz.health.cooey_app.utils;

import com.github.thunder413.datetimeutils.DateTimeFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtil {
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new C07081();

    static class C07081 extends HashMap<String, String> {
        C07081() {
            put("^\\d{8}$", "yyyyMMdd");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}$", DateTimeFormat.DATE_PATTERN_1);
            put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
            put("^\\d{12}$", "yyyyMMddHHmm");
            put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
            put("^\\d{14}$", "yyyyMMddHHmmss");
            put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", DateTimeFormat.DATE_TIME_PATTERN_1);
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        }
    }

    public static String getDateString(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yy").format(new Date(Long.valueOf(date).longValue()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getDateTimeString(Date date) {
        try {
            return new SimpleDateFormat("dd-MM-yy HH:mm").format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Date getDateFromDaysBefore(int days) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(6, days * -1);
        return cal.getTime();
    }

    public static String getDayString(Date date) {
        if (date != null) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return String.valueOf(cal.get(5));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    public static String getFormattedDateFromFacebookDate(String date) {
        try {
            return new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_1).format(new SimpleDateFormat("MM/dd/yyyy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getReadableStringFromDate(Date date) {
        try {
            return new SimpleDateFormat("d MMM, hh:mm aaa").format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getMonthStringFromDate(Date date) {
        try {
            return new SimpleDateFormat("MMM").format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static int getAgeFromDOB(String dob) {
        try {
            Date birthDate = new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_1).parse(dob);
            if (birthDate != null) {
                return getDiffYears(birthDate, new Date());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getDiffYears(Date first, Date last) {
        try {
            Calendar a = getCalendar(first);
            Calendar b = getCalendar(last);
            int diff = b.get(1) - a.get(1);
            if (a.get(2) > b.get(2) || (a.get(2) == b.get(2) && a.get(5) > b.get(5))) {
                return diff - 1;
            }
            return diff;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public static boolean isSameDate(Date currentDate, Date prevDate) {
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);
        Calendar previousCal = Calendar.getInstance();
        previousCal.setTime(prevDate);
        return currentCal.get(5) == previousCal.get(5);
    }

    public static String getReadableTimeFromDate(Date date) {
        try {
            return new SimpleDateFormat("hh:mm aaa").format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getStrinFromDate(Date date) {
        try {
            return new SimpleDateFormat("d MMM yyyy").format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static long getDateFromStringDate(String fromdate) {
        long startDate = 0;
        try {
            startDate = new SimpleDateFormat("d MMM yyyy").parse(fromdate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return (String) DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null;
    }
}
