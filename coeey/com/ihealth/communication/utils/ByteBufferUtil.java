package com.ihealth.communication.utils;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ByteBufferUtil {
    static final char[] f2066a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static long BirthdayToLong(String Birthday) {
        long j = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date date = new Date();
        try {
            return simpleDateFormat.parse(Birthday + " 00:00:00").getTime() / 1000;
        } catch (Exception e) {
            Log.m1215w("ByteBufferUtil", "getDefaultTimerStr Exception ");
            e.printStackTrace();
            return j;
        }
    }

    public static String BufferCut(byte[] buffer, int begin, int length) {
        byte[] bArr = new byte[length];
        for (int i = begin; i < begin + length; i++) {
            bArr[i - begin] = buffer[i];
        }
        return Bytes2HexString(bArr, length);
    }

    public static byte[] BufferMerger(byte[] buffer1, byte[] buffer2) {
        int i = 0;
        if (buffer1 == null) {
            return buffer2;
        }
        byte[] bArr = new byte[(buffer1.length + buffer2.length)];
        for (int i2 = 0; i2 < buffer1.length; i2++) {
            bArr[i2] = buffer1[i2];
        }
        while (i < buffer2.length) {
            bArr[buffer1.length + i] = buffer2[i];
            i++;
        }
        return bArr;
    }

    public static byte[] BufferMergerEx(byte[] buffer1, byte[] buffer2, int start, int stop) {
        if (buffer1 == null) {
            return buffer2;
        }
        byte[] bArr = new byte[(buffer1.length + (stop - start))];
        for (int i = 0; i < buffer1.length; i++) {
            bArr[i] = buffer1[i];
        }
        while (start < stop) {
            bArr[buffer1.length + (start - 1)] = buffer2[start];
            start++;
        }
        return bArr;
    }

    public static int ByteBufferCopy(byte[] src, int srcBegin, int length, byte[] dst, int dstBegin) {
        int i = srcBegin;
        while (i < srcBegin + length) {
            dst[dstBegin + i] = src[i];
            i++;
        }
        return i + dstBegin;
    }

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

    public static String Bytes2HexString(byte[] b, int len) {
        String str = "";
        for (int i = 0; i < len; i++) {
            String toHexString = Integer.toHexString(b[i] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            str = str + toHexString.toUpperCase();
        }
        return str;
    }

    public static String Bytes2HexString(byte[] b, int start, int len) {
        String str = "";
        while (start < len) {
            String toHexString = Integer.toHexString(b[start] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            start++;
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

    private static byte m1207a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] bufferCut(byte[] buffer, int begin, int length) {
        byte[] bArr = new byte[length];
        for (int i = begin; i < begin + length; i++) {
            bArr[i - begin] = buffer[i];
        }
        return bArr;
    }

    public static int byte2ToInt(byte[] res) {
        return (res[1] & 255) | ((res[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    public static int byteToInt(byte[] res) {
        return res.length == 2 ? (res[0] & 255) | ((res[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) : (((res[0] & 255) | ((res[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)) | ((res[2] << 24) >>> 8)) | (res[3] << 24);
    }

    public static int byteToUserId(byte[] res) {
        int i = (res[2] & 255) * 256;
        return ((((((res[0] & 255) * 256) * 256) * 256) + (((res[1] & 255) * 256) * 256)) + i) + (res[3] & 255);
    }

    public static byte[] bytesCutt(int start, int stop, byte[] data) {
        byte[] bArr = new byte[((stop - start) + 1)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = data[start + i];
        }
        return bArr;
    }

    public static byte[] bytesCuttForProductProtocol(int start, byte[] data) {
        byte[] bArr = new byte[((data.length - start) - 1)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = data[i + start];
        }
        return bArr;
    }

    public static boolean bytesEqual(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    public static String decimalismToHexadecimal(int Num) {
        int i = 8;
        String str = "";
        char[] cArr = new char[]{'0', '0', '0', '0', '0', '0', '0', '0'};
        do {
            i--;
            cArr[i] = f2066a[Num & 15];
            Num >>>= 4;
        } while (Num != 0);
        for (char c : cArr) {
            str = str + c;
        }
        return str;
    }

    public static int[] hexByteToInt(byte[] bt, int len) {
        int[] iArr = new int[len];
        for (int i = 0; i < len; i++) {
            iArr[i] = bt[i];
            if (iArr[i] < 0) {
                iArr[i] = iArr[i] + 256;
            }
        }
        return iArr;
    }

    public static byte[] hexStr2Bytes(String src) {
        int length = src.length() / 2;
        System.out.println(length);
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = (i * 2) + 1;
            bArr[i] = Byte.decode("0x" + src.substring(i * 2, i2) + src.substring(i2, i2 + 1)).byteValue();
        }
        return bArr;
    }

    public static byte[] hexStringToByte(String hex) {
        int length = hex.length() / 2;
        byte[] bArr = new byte[length];
        char[] toCharArray = hex.toUpperCase().toCharArray();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (m1207a(toCharArray[i2 + 1]) | (m1207a(toCharArray[i2]) << 4));
        }
        return bArr;
    }

    public static byte[] intTo2Byte(int res) {
        return new byte[]{(byte) (res & 255), (byte) ((res >> 8) & 255)};
    }

    public static byte[] intTo4Byte(long res) {
        return new byte[]{(byte) ((int) (res & 255)), (byte) ((int) ((res >> 8) & 255)), (byte) ((int) ((res >> 16) & 255)), (byte) ((int) (res >>> 24))};
    }

    public static byte[] intToByteForuserId(int userId) {
        return new byte[]{(byte) ((ViewCompat.MEASURED_STATE_MASK & userId) >> 24), (byte) ((16711680 & userId) >> 16), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & userId) >> 8), (byte) (userId & 255)};
    }

    public static byte[] intToBytes2(int value) {
        return new byte[]{(byte) ((value >> 8) & 255), (byte) (value & 255)};
    }

    public static byte[] intToBytes4(int value) {
        return new byte[]{(byte) ((value >> 24) & 255), (byte) ((value >> 16) & 255), (byte) ((value >> 8) & 255), (byte) (value & 255)};
    }

    public static byte[] intToUserId(int userId) {
        return new byte[]{(byte) ((ViewCompat.MEASURED_STATE_MASK & userId) >> 24), (byte) ((16711680 & userId) >> 16), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & userId) >> 8), (byte) (userId & 255)};
    }

    public static String mac2Address(byte[] b) {
        String str = "";
        for (int i = 0; i < b.length; i++) {
            String toHexString = Integer.toHexString(b[i] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            str = str + toHexString.toUpperCase();
            if (i != b.length - 1) {
                str = str + ":";
            }
        }
        return str;
    }

    public static byte[] rejectBuffer(byte[] data) {
        byte[] bArr = new byte[(data.length - 4)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = data[i];
        }
        return bArr;
    }

    public int[] StringtoInts(String str) {
        return new int[str.length()];
    }
}
