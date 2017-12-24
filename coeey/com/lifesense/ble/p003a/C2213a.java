package com.lifesense.ble.p003a;

import com.facebook.appevents.AppEventsConstants;
import com.google.common.base.Ascii;
import com.google.protobuf.ByteString;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.commom.C2255a;
import com.lifesense.ble.p003a.p004a.C2190c;
import com.lifesense.ble.p003a.p004a.C2192e;
import com.lifesense.ble.p003a.p004a.C2198k;
import com.lifesense.ble.p003a.p004a.C2200m;
import com.lifesense.ble.p003a.p004a.C2202o;
import com.lifesense.ble.p003a.p004a.C2204q;
import com.lifesense.ble.p003a.p004a.C2210w;
import com.lifesense.ble.p003a.p004a.C2212y;
import com.lifesense.ble.p003a.p004a.ac;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Random;
import org.joda.time.DateTimeConstants;

public class C2213a {
    public static final byte DOWNLOAD_INFORMATION_ALARM_CLOCK_COMMAND = (byte) 66;
    public static final byte DOWNLOAD_INFORMATION_BROADCAST_ID_COMMAND = (byte) 33;
    public static final byte f2267x6ac1a5c = (byte) 82;
    public static final byte DOWNLOAD_INFORMATION_CLAIM_USER_NUMBER_IN_PRODUCT_COMMAND = (byte) 81;
    public static final byte DOWNLOAD_INFORMATION_COMFIRM_INFORMATION_COMMAND = (byte) 80;
    public static final byte DOWNLOAD_INFORMATION_CURRENT_STATE_COMMAND = (byte) 5;
    public static final byte DOWNLOAD_INFORMATION_CURRENT_TIME_COMMAND = (byte) 1;
    public static final byte DOWNLOAD_INFORMATION_DAY_TARGET_COMMAND = (byte) 7;
    public static final byte DOWNLOAD_INFORMATION_DELETE_PAIRED_INFORMATION_COMMAND = (byte) 83;
    public static final byte DOWNLOAD_INFORMATION_DEVICE_NAME_COMMAND = (byte) 9;
    public static final byte DOWNLOAD_INFORMATION_DISPLAY_AND_FUNCTION_COMMAND = (byte) 65;
    public static final byte DOWNLOAD_INFORMATION_DRINKING_REMINDER_COMMAND = (byte) 67;
    public static final byte DOWNLOAD_INFORMATION_ENABLE_DISCONNECT_COMMAND = (byte) 34;
    public static final byte DOWNLOAD_INFORMATION_EXERCISE_REMINDER_COMMAND = (byte) 68;
    public static final byte DOWNLOAD_INFORMATION_OPERATING_PRODUCT_COMMAND = (byte) 64;
    public static final byte f2268x7c6709c = (byte) 1;
    public static final byte DOWNLOAD_INFORMATION_OPERATING_PRODUCT_FLIGHT_MODE_SUBCOMMAND = (byte) 3;
    public static final byte DOWNLOAD_INFORMATION_OPERATING_PRODUCT_INVALID_SUBCOMMAND = (byte) 0;
    public static final byte DOWNLOAD_INFORMATION_OPERATING_PRODUCT_POWER_OFF_SUBCOMMAND = (byte) 2;
    public static final byte DOWNLOAD_INFORMATION_RESULT_COMMAND = (byte) 32;
    public static final byte DOWNLOAD_INFORMATION_TARGET_STATE = (byte) 6;
    public static final byte DOWNLOAD_INFORMATION_USER_MESSAGE_COMMAND = (byte) 4;
    public static final byte DOWNLOAD_INFORMATION_USER_NAME_COMMAND = (byte) 3;
    public static final byte DOWNLOAD_INFORMATION_UTC_COMMAND = (byte) 2;
    public static final byte DOWNLOAD_INFORMATION_WEEK_TARGET_COMMAND = (byte) 8;
    public static final int ECI_req_auth = 10001;
    public static final int ECI_req_init = 10003;
    public static final int ECI_req_sendDataToManufacturerSvr = 10002;
    public static final int ECI_resp_auth = 20001;
    public static final int ECI_resp_init = 20003;
    public static final int ECI_resp_sendDataToManufacturerSvr = 20002;
    public static final String HEADER = "fe01";

    private C2213a() {
    }

    private static String m1538a(float f) {
        return "fe" + C2213a.m1540a("" + Long.toHexString((long) (100.0f * f)), 6);
    }

    public static String m1539a(String str, float f, float f2, int i, int i2, boolean z) {
        C2212y g = C2210w.m1509g();
        g.m1529a(C2213a.m1551f());
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("C7");
            stringBuffer.append(ac.m1292a());
            stringBuffer.append(C2213a.m1538a(f));
            stringBuffer.append(C2213a.m1538a(f2 / 100.0f));
            stringBuffer.append(DeviceTypeConstants.WEIGHT_SCALE);
            String toHexString = Long.toHexString((long) i);
            if (toHexString.length() < 8) {
                toHexString = new StringBuilder(String.valueOf(ac.m1293a(8 - toHexString.length()))).append(toHexString).toString();
            }
            stringBuffer.append(toHexString);
            toHexString = Long.toHexString((long) i2);
            if (toHexString.length() < 6) {
                toHexString = new StringBuilder(String.valueOf(ac.m1293a(6 - toHexString.length()))).append(toHexString).toString();
            }
            stringBuffer.append(toHexString);
            if (z) {
                stringBuffer.append(DeviceTypeConstants.WEIGHT_SCALE);
            } else {
                stringBuffer.append(DeviceTypeConstants.UNKNOW);
            }
            g.m1526a(ByteString.copyFrom(stringBuffer.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return C2213a.m1541a(str, g.m1534d().toByteArray(), ECI_resp_sendDataToManufacturerSvr);
    }

    public static String m1540a(String str, int i) {
        Object obj = "";
        for (int i2 = 0; i2 < i - str.length(); i2++) {
            obj = new StringBuilder(String.valueOf(obj)).append(AppEventsConstants.EVENT_PARAM_VALUE_NO).toString();
        }
        return new StringBuilder(String.valueOf(obj)).append(str).toString();
    }

    public static String m1541a(String str, byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        String a = ac.m1295a(bArr);
        stringBuffer.append(HEADER);
        stringBuffer.append(C2213a.m1540a(Integer.toHexString((a.length() / 2) + 8), 4));
        stringBuffer.append(Integer.toHexString(i));
        stringBuffer.append(str);
        stringBuffer.append(a);
        return stringBuffer.toString();
    }

    public static byte[] m1542a() {
        int nextInt = new Random().nextInt();
        return new byte[]{(byte) 33, (byte) ((nextInt >> 24) & 255), (byte) ((nextInt >> 16) & 255), (byte) ((nextInt >> 8) & 255), (byte) (nextInt & 255)};
    }

    public static byte[] m1543a(int i) {
        return new byte[]{(byte) 83, (byte) i};
    }

    public static byte[] m1544a(int i, String str) {
        byte[] bytes;
        int i2 = 0;
        byte[] bArr = new byte[18];
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bytes = null;
        }
        bArr[0] = (byte) 3;
        bArr[1] = (byte) i;
        while (i2 < bytes.length) {
            bArr[i2 + 2] = bytes[i2];
            i2++;
        }
        for (int length = bytes.length + 1; length < bArr.length - 1; length++) {
            bArr[length + 1] = (byte) 32;
        }
        return bArr;
    }

    public static byte[] m1545a(String str, byte[] bArr) {
        if (C2213a.m1547b(str, bArr) == null) {
            return null;
        }
        return new byte[]{(byte) 32, C2213a.m1547b(str, bArr)[0], C2213a.m1547b(str, bArr)[1], C2213a.m1547b(str, bArr)[2], C2213a.m1547b(str, bArr)[3]};
    }

    public static byte[] m1546b() {
        byte[] a = C2255a.m1880a();
        return new byte[]{(byte) 2, a[0], a[1], a[2], a[3]};
    }

    private static byte[] m1547b(String str, byte[] bArr) {
        boolean z = false;
        byte[] a = C2221i.m1608a(str);
        if (a == null || bArr == null) {
            StringBuilder append = new StringBuilder("Error,the password is null ? ").append(a == null).append(" or randomNumber is null ?");
            if (bArr == null) {
                z = true;
            }
            C2220h.m1596a((Object) C2213a.class, append.append(z).toString(), 3);
            return null;
        }
        return new byte[]{(byte) (a[0] ^ bArr[0]), (byte) (a[1] ^ bArr[1]), (byte) (a[2] ^ bArr[2]), (byte) (a[3] ^ bArr[3])};
    }

    public static byte[] m1548c() {
        return new byte[]{(byte) 34};
    }

    public static byte[] m1549d() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) -2;
        bArr[1] = (byte) 1;
        bArr[3] = Ascii.SO;
        bArr[4] = (byte) 78;
        bArr[5] = (byte) 33;
        bArr[7] = (byte) 1;
        C2192e g = C2190c.m1304g();
        g.m1325a(C2213a.m1551f());
        g.m1321a(ByteString.copyFromUtf8(AppEventsConstants.EVENT_PARAM_VALUE_NO));
        bArr = C2221i.m1609a(bArr, g.m1329d().toByteArray());
        bArr[3] = (byte) bArr.length;
        return bArr;
    }

    public static String m1550e() {
        C2204q C = C2202o.m1386C();
        C.m1448a(C2213a.m1551f());
        C.m1445a(1);
        C.m1451b(2);
        Calendar instance = Calendar.getInstance();
        C.m1463h(instance.getTimeZone().getRawOffset() / DateTimeConstants.MILLIS_PER_HOUR);
        C.m1462g((int) (instance.getTimeInMillis() / 1000));
        return C2213a.m1541a("0002", C.m1455d().toByteArray(), ECI_resp_init);
    }

    private static C2198k m1551f() {
        C2200m g = C2198k.m1361g();
        g.m1377a(1);
        return g.m1383d();
    }
}
