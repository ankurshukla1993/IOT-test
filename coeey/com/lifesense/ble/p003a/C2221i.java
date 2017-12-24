package com.lifesense.ble.p003a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import com.facebook.appevents.AppEventsConstants;
import com.lifesense.ble.bean.C2248h;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.commom.C2256b;
import humanize.util.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressLint({"DefaultLocale"})
public class C2221i {
    private static C2256b f2310a = C2256b.m1943a();

    private C2221i() {
    }

    private static byte m1600a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static int m1601a(String str, String str2) {
        if (str == null || str2 == DeviceTypeConstants.UNKNOW) {
            C2220h.m1596a(null, "Error!get max user number,type is unknow?-" + str2, 1);
            return -1;
        } else if (str2 != DeviceTypeConstants.SPHYGMOMAN_METER && str != null && str.lastIndexOf(".") != -1) {
            C2220h.m1596a(null, new StringBuilder(String.valueOf(str2)).append(" Max user number-").append(str.substring(str.lastIndexOf(".") + 1)).toString(), 3);
            return Integer.parseInt(str.substring(str.lastIndexOf(".") + 1));
        } else if (str2 == DeviceTypeConstants.SPHYGMOMAN_METER) {
            C2220h.m1596a(null, new StringBuilder(String.valueOf(str2)).append(" Max user number-").append(2).toString(), 3);
            return 2;
        } else {
            C2220h.m1596a(null, "Error!get max user number-" + str2, 1);
            return -1;
        }
    }

    public static ae m1602a(UUID uuid) {
        return f2310a.m1945a(uuid);
    }

    public static String m1603a(List list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        for (UUID uuid : list) {
            if (f2310a.m1948a(ae.A2).contains(uuid.toString())) {
                return ae.A2.toString();
            }
            if (f2310a.m1948a(ae.A3).contains(uuid.toString())) {
                return ae.A3.toString();
            }
            if (f2310a.m1948a(ae.GENERIC_FAT).contains(uuid.toString())) {
                return ae.GENERIC_FAT.toString();
            }
            if (f2310a.m1948a(ae.A4).contains(uuid.toString())) {
                return ae.A4.toString();
            }
        }
        return null;
    }

    public static String m1604a(byte[] bArr, String str) {
        StringBuilder stringBuilder = null;
        if (str == null || !str.equals(ae.A4.toString())) {
            String b = C2221i.m1611b(bArr);
            if (b != null) {
                C2220h.m1596a((Object) C2221i.class, "current scan device broadcast name:" + b, 3);
                return b;
            }
            C2220h.m1596a((Object) C2221i.class, "Failed to parse device broadcast name:" + b, 3);
            return null;
        }
        byte[] f = C2221i.m1615f(bArr);
        if (bArr != null && f.length > 0) {
            stringBuilder = new StringBuilder(f.length);
            int length = f.length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02X ", new Object[]{Byte.valueOf(f[i])}));
            }
        }
        String replace = stringBuilder.toString().replace(Constants.SPACE, "");
        replace = replace.substring(replace.length() - 12);
        C2220h.m1596a((Object) C2221i.class, "current scan device broadcast name:" + C2221i.m1611b(bArr) + "; broadcast Id=" + replace, 3);
        return replace;
    }

    public static List m1605a(byte[] bArr) {
        List arrayList = new ArrayList();
        int i = 0;
        while (i < bArr.length - 2) {
            int i2 = i + 1;
            byte b = bArr[i];
            if (b != (byte) 0) {
                int i3 = i2 + 1;
                switch (bArr[i2]) {
                    case (byte) 2:
                    case (byte) 3:
                        byte b2 = b;
                        i = i3;
                        i3 = b2;
                        while (i3 > 1) {
                            int i4 = i + 1;
                            i2 = i4 + 1;
                            i4 = ((bArr[i4] & 255) << 8) | (bArr[i] & 255);
                            i = i3 - 2;
                            arrayList.add(UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", new Object[]{Integer.valueOf(i4)})));
                            i3 = i;
                            i = i2;
                        }
                        break;
                    default:
                        i = (b - 1) + i3;
                        break;
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    public static UUID m1606a(String str, C2248h c2248h) {
        if (!(str == null || c2248h == null)) {
            List<String> b = f2310a.m1949b(str);
            for (C2248h c2248h2 : c2248h.m1849b()) {
                for (String equals : b) {
                    if (c2248h2.m1844a().toString().equals(equals)) {
                        C2220h.m1596a(null, "find service uuid success-" + str + "=" + c2248h2.m1844a(), 3);
                        return c2248h2.m1844a();
                    }
                }
            }
            C2220h.m1596a(null, "find service uuid failure-" + str + ";service list size=" + b.size(), 3);
        }
        return null;
    }

    public static boolean m1607a(C2248h c2248h, UUID uuid) {
        if (c2248h == null || c2248h.m1849b().isEmpty()) {
            return false;
        }
        for (C2248h a : c2248h.m1849b()) {
            if (a.m1844a().equals(uuid)) {
                return true;
            }
        }
        C2220h.m1596a(null, "find service uuid failure-" + uuid.toString().substring(4, 8), 3);
        return false;
    }

    public static byte[] m1608a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String toUpperCase = str.toUpperCase();
        int length = toUpperCase.length() / 2;
        char[] toCharArray = toUpperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (C2221i.m1600a(toCharArray[i2 + 1]) | (C2221i.m1600a(toCharArray[i2]) << 4));
        }
        return bArr;
    }

    public static byte[] m1609a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length <= 0 || bArr2.length <= 0) {
            return null;
        }
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static String m1610b(List list) {
        String str = DeviceTypeConstants.UNKNOW;
        if (list != null) {
            for (UUID uuid : list) {
                String a = f2310a.m1947a(uuid.toString());
                if (a != DeviceTypeConstants.UNKNOW) {
                    C2220h.m1596a((Object) C2221i.class, "set device type—?" + a + " uuid—?" + uuid.toString().substring(4, 8), 3);
                    return a;
                }
                str = a;
            }
            C2220h.m1596a((Object) C2221i.class, "Error,set device type failure-" + list.get(0), 1);
        }
        return str;
    }

    @SuppressLint({"NewApi"})
    public static String m1611b(byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        byte[] bArr2 = null;
        int i = 0;
        while (i < copyOf.length - 2) {
            int i2 = i + 1;
            byte b = copyOf[i];
            if (b != (byte) 0) {
                int i3 = i2 + 1;
                switch (copyOf[i2]) {
                    case (byte) 9:
                        bArr2 = new byte[b];
                        i2 = b;
                        i = i3;
                        i3 = 0;
                        while (i2 > 1) {
                            int i4 = i + 1;
                            bArr2[i3] = copyOf[i];
                            i2--;
                            i3++;
                            i = i4;
                        }
                        break;
                    default:
                        i = (b - 1) + i3;
                        break;
                }
            }
            return (bArr2 == null || bArr2.length <= 0) ? null : C2221i.m1616g(C2221i.m1615f(bArr2));
        }
        if (bArr2 == null) {
            return null;
        }
    }

    public static String m1612c(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            cArr[i2] = (char) bArr[i2];
        }
        String str = "";
        while (i < cArr.length) {
            str = new StringBuilder(String.valueOf(str)).append(cArr[i]).toString();
            i++;
        }
        return str.trim();
    }

    public static String m1613d(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            str = toHexString.length() == 1 ? new StringBuilder(String.valueOf(str)).append(AppEventsConstants.EVENT_PARAM_VALUE_NO).append(toHexString).toString() : new StringBuilder(String.valueOf(str)).append(toHexString).toString();
        }
        return str.toUpperCase().trim();
    }

    public static String m1614e(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            str = toHexString.length() == 1 ? new StringBuilder(String.valueOf(str)).append(AppEventsConstants.EVENT_PARAM_VALUE_NO).append(toHexString).toString() : new StringBuilder(String.valueOf(str)).append(toHexString).toString();
            if (i < bArr.length - 1) {
                str = new StringBuilder(String.valueOf(str)).append("-").toString();
            }
        }
        return str.toUpperCase();
    }

    @TargetApi(9)
    public static byte[] m1615f(byte[] bArr) {
        int length = bArr.length - 1;
        while (length >= 0 && bArr[length] == (byte) 0) {
            length--;
        }
        return Arrays.copyOf(bArr, length + 1);
    }

    public static String m1616g(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
