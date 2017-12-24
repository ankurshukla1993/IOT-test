package com.lifesense.ble.p003a;

import android.os.Environment;
import android.util.Log;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class C2220h {
    public static final int DEBUG_LEVEL_ADVANCED = 2;
    public static final int DEBUG_LEVEL_GENERAL = 1;
    public static final int DEBUG_LEVEL_SUPREME = 3;
    private static String f2302a = "test";
    private static List f2303b = new ArrayList();
    private static String f2304c;
    private static boolean f2305d = false;
    private static String f2306e = "/data/data/com.lifesense.thermometer/files/log.txt";
    private static String f2307f = "/danke";
    private static String f2308g = "/A4-WeightScale-log(";
    private static String f2309h = "/LSBLE-log.txt";

    static {
        f2303b.add(Integer.valueOf(1));
        f2303b.add(Integer.valueOf(2));
    }

    private static String m1594a() {
        return f2302a;
    }

    private static String m1595a(Object obj) {
        return "LS-BLE";
    }

    public static void m1596a(Object obj, String str, int i) {
        if (C2220h.m1594a().equals("sky")) {
            if (i == 1) {
                C2220h.m1598a(null, str, false);
                Log.i(C2220h.m1595a(obj), str);
            }
            if (i == 2) {
                Log.i(C2220h.m1595a(obj), str);
            }
            if (i == 3) {
                Log.e(C2220h.m1595a(obj), str);
            }
        } else if (f2303b.contains(Integer.valueOf(i))) {
            Log.d(C2220h.m1595a(obj), str);
        }
    }

    public static void m1597a(String str) {
        if (!f2305d) {
            f2304c = str;
            f2305d = true;
        }
    }

    private static void m1598a(String str, String str2, boolean z) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            StringBuffer stringBuffer = new StringBuffer();
            if (z) {
                String format = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(new Date(System.currentTimeMillis()));
                stringBuffer.append("********************************\r\n");
                stringBuffer.append("Recording Time : " + format + "\r\n");
                stringBuffer.append("Mac Address : " + str + "\r\n");
                stringBuffer.append("Connect Msg : " + str2 + "\r\n");
                stringBuffer.append("********************************\r\n");
            } else {
                stringBuffer.append("********************************\r\n");
                stringBuffer.append("Connect Msg : " + str2 + "\r\n");
            }
            C2220h.m1599b(stringBuffer.toString());
        }
    }

    private static void m1599b(String str) {
        f2308g + f2304c + ").txt";
        File file = new File(Environment.getExternalStorageDirectory(), f2309h);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("log file path =" + file.getAbsolutePath());
            }
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(new StringBuilder(String.valueOf(str)).append("\r\n").toString());
            fileWriter.close();
            System.out.println("Save successfuly.......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
