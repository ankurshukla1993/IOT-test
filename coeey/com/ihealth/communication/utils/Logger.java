package com.ihealth.communication.utils;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.ihealth.a.a;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger {
    private static String f2108a = "Logger";
    private static boolean f2109b = false;
    private static String f2110c = "_SDK_Debug.txt";
    private static String f2111d = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/");
    private static int f2112e = 7;
    private static HandlerThread f2113f = new HandlerThread("Logger thread.");
    private static Handler f2114g = new Handler(f2113f.getLooper());
    private static boolean f2115h = a.a;

    static {
        f2113f.start();
    }

    private static void m1218a(String str, String str2, String str3) {
        int myPid = Process.myPid();
        int myTid = Process.myTid();
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, 0 - f2112e);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_1);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = simpleDateFormat.format(instance.getTime());
        String format2 = simpleDateFormat2.format(date);
        f2114g.post(new C2176e(format, simpleDateFormat.format(date), format2, myPid, myTid, str, str2, str3));
    }

    private static void m1222b(String str) {
        String str2 = str + f2110c;
        File[] listFiles = new File(f2111d).listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length - 1; length >= 0; length--) {
                String name = listFiles[length].getName();
                if (name.endsWith(f2110c) && name.compareTo(str2) < 0) {
                    listFiles[length].delete();
                }
            }
        }
    }

    private static void m1223b(String str, String str2, int i, int i2, String str3, String str4, String str5) {
        try {
            Writer fileWriter = new FileWriter(new File(str), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format("%s %d-%d %s/%s: %s", new Object[]{str2, Integer.valueOf(i), Integer.valueOf(i2), str3, str4, str5}));
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void m1225d(String tag, String msg) {
        if (f2115h) {
            Log.d(tag, msg);
            m1218a("D", tag, msg);
        }
    }

    public static void m1226e(String tag, String msg) {
        Log.e(tag, msg);
        if (f2115h) {
            m1218a("E", tag, msg);
        }
    }

    public static void m1227i(String tag, String msg) {
        if (f2115h) {
            Log.i(tag, msg);
            m1218a("I", tag, msg);
        }
    }

    public static void m1228v(String tag, String msg) {
        if (f2115h) {
            Log.v(tag, msg);
            m1218a("V", tag, msg);
        }
    }

    public static void m1229w(String tag, String msg) {
        Log.w(tag, msg);
        if (f2115h) {
            m1218a("W", tag, msg);
        }
    }
}
