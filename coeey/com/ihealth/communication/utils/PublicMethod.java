package com.ihealth.communication.utils;

import android.content.Context;
import android.provider.Settings.System;
import com.facebook.appevents.AppEventsConstants;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.ihealth.communication.cloud.data.Data_AM_SleepSectionReport;
import com.lifesense.ble.bean.DeviceTypeConstants;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PublicMethod {
    public static String Bytes2HexString(byte[] b) {
        String str = "";
        for (byte b2 : b) {
            String toHexString = Integer.toHexString(b2 & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            str = str + toHexString.toUpperCase();
        }
        return str;
    }

    public static long String2TS(String dateStr) {
        long j = -1;
        try {
            j = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).parse(dateStr).getTime();
            return j / 1000;
        } catch (ParseException e) {
            ParseException parseException = e;
            long j2 = j;
            parseException.printStackTrace();
            return j2;
        }
    }

    public static String TS2String(long TS) {
        String str = "";
        Date date = new Date();
        date.setTime(1000 * TS);
        return new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(date);
    }

    public static String get2Value(float valueData) {
        String str = DeviceTypeConstants.UNKNOW;
        int intValue = new BigDecimal((double) valueData).setScale(0, 4).intValue();
        if (intValue < 10) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO + intValue;
        }
        str = intValue + "";
        return str.length() != 2 ? str.substring(str.length() - 2, str.length()) : str;
    }

    public static String getAMDataID(String MacId, String resultData, long TS) {
        String replaceAll = MacId.replaceAll(":", "");
        return replaceAll == null ? TS + resultData : replaceAll + TS + resultData;
    }

    public static String getBPDataID(String MacId, String resultData, long TS) {
        String replaceAll = MacId.replaceAll(":", "");
        return replaceAll == null ? resultData + TS : replaceAll + resultData + TS;
    }

    public static String getDataID(String MacId, String resultData, long TS) {
        String replaceAll = MacId.replaceAll(":", "");
        String str = get2Value(Float.valueOf(resultData).floatValue());
        return replaceAll == null ? TS + str + "00000000" : replaceAll + TS + str + "00000000";
    }

    public static String getDefaultTimerStr(Context context, String timeStr, int id) {
        String str = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        DateFormat dateFormat = null;
        switch (id) {
            case 1:
                dateFormat = DateFormat.getDateInstance(2, Locale.getDefault());
                break;
            case 2:
                dateFormat = DateFormat.getTimeInstance(3, Locale.getDefault());
                break;
            case 3:
                dateFormat = DateFormat.getDateTimeInstance(0, 3, Locale.getDefault());
                break;
            case 4:
                dateFormat = DateFormat.getDateTimeInstance(1, 3, Locale.getDefault());
                break;
            case 5:
                dateFormat = DateFormat.getDateInstance(3, Locale.getDefault());
                break;
        }
        SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) dateFormat;
        Date date = new Date();
        try {
            Date parse = simpleDateFormat.parse(timeStr);
            String string = System.getString(context.getContentResolver(), "time_12_24");
            SimpleDateFormat simpleDateFormat3;
            if (string == null) {
                simpleDateFormat3 = new SimpleDateFormat(simpleDateFormat2.toLocalizedPattern());
                return simpleDateFormat2.format(parse);
            } else if (string.equals("24")) {
                return new SimpleDateFormat(simpleDateFormat2.toLocalizedPattern().replace("a", "").replace("h", "H")).format(parse);
            } else {
                simpleDateFormat3 = new SimpleDateFormat(simpleDateFormat2.toLocalizedPattern());
                return simpleDateFormat2.format(parse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static long getTs() {
        return System.currentTimeMillis() / 1000;
    }

    public static boolean isCtlCode(String QRCode) {
        return (QRCode == null || QRCode.length() < 6) ? false : QRCode.substring(0, QRCode.length() - 6).equalsIgnoreCase(MD5.md5String(QRCode.substring(QRCode.length() - 6)));
    }

    public static boolean isOneCode(String QRCode) {
        return (QRCode == null || QRCode.length() < 6) ? false : QRCode.substring(6).equalsIgnoreCase(MD5.md5String(QRCode.substring(0, 6)));
    }

    public static ArrayList isRepeat_Sleep(ArrayList amSleepSectionsList) {
        int i;
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < amSleepSectionsList.size(); i++) {
            arrayList.add((Data_AM_SleepSectionReport) amSleepSectionsList.get((amSleepSectionsList.size() - 1) - i));
        }
        while (i2 < arrayList.size() - 1) {
            for (i = arrayList.size() - 1; i > i2; i--) {
                if (((Data_AM_SleepSectionReport) arrayList.get(i)).getTimeSectionId().equals(((Data_AM_SleepSectionReport) arrayList.get(i2)).getTimeSectionId())) {
                    arrayList.remove(i);
                }
            }
            i2++;
        }
        return arrayList;
    }
}
