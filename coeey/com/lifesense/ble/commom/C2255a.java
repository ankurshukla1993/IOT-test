package com.lifesense.ble.commom;

import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.login.widget.ProfilePictureView;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.lifesense.ble.bean.BloodPressureData;
import com.lifesense.ble.bean.C2247g;
import com.lifesense.ble.bean.HeightData;
import com.lifesense.ble.bean.PedometerData;
import com.lifesense.ble.bean.PersonalUserInfo;
import com.lifesense.ble.bean.SexType;
import com.lifesense.ble.bean.SleepAndExerciseInfo;
import com.lifesense.ble.bean.SleepInfo_A4;
import com.lifesense.ble.bean.WeightData_A2;
import com.lifesense.ble.bean.WeightData_A3;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.p003a.C2220h;
import com.lifesense.ble.p003a.p004a.ac;
import humanize.util.Constants;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class C2255a {
    public static double m1862a(double d, double d2, double d3, int i, int i2) {
        if (d2 <= 0.0d) {
            return 0.0d;
        }
        double d4 = d2 - 10.0d;
        double d5 = d3 / 100.0d;
        if (i2 == 0) {
            d4 = (((((60.3d - ((((486583.0d * d5) * d5) / d) / d4)) + ((((9.146d * d) / d5) / d5) / d4)) - (((d5 * (251.193d * d5)) / d) / ((double) i))) + ((1625303.0d / d4) / d4)) - (d4 * 0.0139d)) + (0.05975d * ((double) i));
            return d4 < 5.0d ? 5.0d : d4;
        } else if (i2 != 1) {
            return 0.0d;
        } else {
            d4 = (((7816.359d / d5) / d4) + ((((57.621d - (((186.422d * d5) * d5) / d)) - ((((382280.0d * d5) * d5) / d) / d4)) + (((128.005d * d) / d5) / d4)) - ((0.0728d * d) / d5))) - ((((3.333d * d) / d5) / d5) / ((double) i));
            return d4 < 5.0d ? 5.0d : d4;
        }
    }

    public static double m1863a(double d, double d2, double d3, int i, int i2, double d4, int i3) {
        if (d == 0.0d || d2 == 0.0d || d3 == 0.0d || i == 0 || d4 == 0.0d) {
            return 0.0d;
        }
        double d5 = 0.0d;
        double d6 = 0.0d;
        double doubleValue = new BigDecimal(d).setScale(1, 4).doubleValue();
        double doubleValue2 = new BigDecimal(d2).setScale(1, 4).doubleValue();
        double doubleValue3 = new BigDecimal(d3).setScale(1, 4).doubleValue();
        doubleValue2 = new BigDecimal(doubleValue2 / (doubleValue3 * doubleValue3)).setScale(1, 4).doubleValue();
        if (i3 == 0) {
            if (i2 == 0) {
                if (doubleValue2 < 18.0d) {
                    if (d4 < 550.0d) {
                        d6 = 1.5d;
                    }
                    if (d4 >= 550.0d && d4 < 600.0d) {
                        d6 = 2.0d;
                    }
                    if (d4 >= 600.0d && d4 < 860.0d) {
                        d6 = 2.3d;
                    }
                }
                if (doubleValue2 >= 18.0d && doubleValue2 < 25.0d) {
                    if (d4 < 430.0d) {
                        d6 = 1.5d;
                    }
                    if (d4 >= 430.0d && d4 < 580.0d) {
                        d6 = 2.0d;
                    }
                    if (d4 >= 580.0d && d4 < 860.0d) {
                        d6 = 2.3d;
                    }
                }
                if (doubleValue2 >= 25.0d) {
                    if (d4 < 400.0d) {
                        d6 = 1.5d;
                    }
                    if (d4 >= 400.0d && d4 < 500.0d) {
                        d6 = 2.0d;
                    }
                    if (d4 >= 500.0d && d4 < 860.0d) {
                        d6 = 2.3d;
                    }
                }
            } else if (i2 == 1) {
                if (doubleValue2 < 18.0d) {
                    if (d4 < 500.0d) {
                        d6 = 1.5d;
                    }
                    if (d4 >= 500.0d && d4 < 700.0d) {
                        d6 = 2.0d;
                    }
                    if (d4 >= 700.0d && d4 < 860.0d) {
                        d6 = 2.3d;
                    }
                }
                if (doubleValue2 >= 18.0d && doubleValue2 < 25.0d) {
                    if (d4 < 480.0d) {
                        d6 = 1.5d;
                    }
                    if (d4 >= 480.0d && d4 < 650.0d) {
                        d6 = 2.0d;
                    }
                    if (d4 >= 650.0d && d4 < 860.0d) {
                        d6 = 2.3d;
                    }
                }
                if (doubleValue2 >= 25.0d) {
                    if (d4 < 450.0d) {
                        d6 = 1.5d;
                    }
                    if (d4 >= 450.0d && d4 < 550.0d) {
                        d6 = 2.0d;
                    }
                    if (d4 >= 550.0d && d4 < 860.0d) {
                        d6 = 2.3d;
                    }
                }
            }
            d6 = d4 >= 860.0d ? 1.96d : d4 < 860.0d ? (d6 * (d4 / 500.0d)) - 2.0d : 0.0d;
            return i2 == 0 ? (((-22.883d + (0.008049d * doubleValue)) + (1.601d * doubleValue2)) + (0.12d * ((double) i))) + (d6 * 3.479d) : i2 == 1 ? (((-11.876d - (0.004d * doubleValue)) + (1.754d * doubleValue2)) + (0.06739d * ((double) i))) + (d6 * 0.842d) : 0.0d;
        } else {
            d6 = 0.0d;
            if (d4 >= 410.0d) {
                d6 = 0.3d * (d4 - 400.0d);
            }
            if (d4 < 410.0d) {
                d6 = 3.0d;
            }
            if (i2 == 0) {
                d5 = (((1.479d + (4.4E-4d * d6)) * doubleValue2) + (0.1d * ((double) i))) - 21.764d;
            }
            return i2 == 1 ? (((1.506d + (d6 * 3.908E-4d)) * doubleValue2) + (0.1d * ((double) i))) - 12.834d : d5;
        }
    }

    public static double m1864a(double d, double d2, int i) {
        return i == 0 ? ((0.95d * d) - ((0.0095d * d2) * d)) - 0.13d : i == 1 ? (1.13d + (0.914d * d)) - ((0.00914d * d2) * d) : 0.0d;
    }

    public static double m1865a(double d, int i) {
        return i == 0 ? 0.116d + (0.0525d * d) : i == 1 ? -1.22d + (0.0944d * d) : 0.0d;
    }

    public static double m1866a(double d, int i, double d2, int i2) {
        double d3 = d2 / 100.0d;
        int i3 = i - 10;
        if (i2 == 0) {
            d3 = ((30.849d + ((((259672.5d * d3) * d3) / d) / ((double) i3))) + (((0.372d * ((double) i3)) / d3) / d)) - (((d3 * 2.581d) * d) / ((double) i3));
            return d3 < 30.0d ? 30.0d : d3;
        } else if (i2 != 1) {
            return 0.0d;
        } else {
            d3 = ((d3 * 160.445d) / d) + ((((((201468.7d * d3) * d3) / d) / ((double) i3)) + 23.018d) + ((421.543d / d) / d3));
            return d3 < 30.0d ? 30.0d : d3;
        }
    }

    public static int m1867a(String str, String str2) {
        if (str == null || str.length() <= 0 || str2 == null) {
            return 0;
        }
        if (str2.equals("C9")) {
            Integer.parseInt(ac.m1294a(str, 0, 1), 16);
            return Integer.parseInt(ac.m1294a(str, 2, 3), 16);
        }
        C2220h.m1596a((Object) C2255a.class, "Failed to parse pedometer data for unknown command code..." + str2, 1);
        return 0;
    }

    public static long m1868a(String str) {
        return C2255a.m1889b(str).getTime();
    }

    public static PedometerData m1869a(PedometerData pedometerData) {
        PedometerData pedometerData2 = new PedometerData();
        pedometerData2.setBattery(pedometerData.getBattery());
        pedometerData2.setBroadcastId(pedometerData.getBroadcastId());
        pedometerData2.setCalories(pedometerData.getCalories());
        pedometerData2.setDeviceId(pedometerData.getDeviceId());
        pedometerData2.setDeviceSn(pedometerData.getDeviceSn());
        pedometerData2.setExamount(pedometerData.getExamount());
        pedometerData2.setDistance((double) pedometerData.getDistance());
        pedometerData2.setExerciseTime(pedometerData.getExerciseTime());
        pedometerData2.setRunSteps(pedometerData.getRunSteps());
        pedometerData2.setWalkSteps(pedometerData.getWalkSteps());
        pedometerData2.setUserNo(pedometerData.getUserNo());
        pedometerData2.setSleepStatus(2);
        pedometerData2.setDate(C2255a.m1903d(pedometerData.getDate()));
        pedometerData2.setDistance((double) pedometerData.getDistance());
        return pedometerData2;
    }

    public static WeightData_A2 m1870a(WeightData_A2 weightData_A2, byte[] bArr) {
        if (weightData_A2 == null) {
            weightData_A2 = new WeightData_A2();
        }
        byte b = bArr[0];
        byte[] bArr2 = new byte[]{bArr[1], bArr[2], bArr[3], bArr[4]};
        int i = 5;
        if (((byte) (b & 1)) == (byte) 1) {
            weightData_A2.setUserNo(C2255a.m1892b(bArr[5]));
            i = 6;
        }
        if (((byte) (b & 2)) == (byte) 2) {
            weightData_A2.setBasalMetabolism(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 4)) == (byte) 4) {
            weightData_A2.setBodyFatRatio(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 8)) == (byte) 8) {
            weightData_A2.setBodyWaterRatio(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            weightData_A2.setVisceralFatLevel(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 32)) == (byte) 32) {
            weightData_A2.setMuscleMassRatio(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 64)) == (byte) 64) {
            weightData_A2.setBoneDensity(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) ((b & 128) >>> 7)) == (byte) 1) {
            weightData_A2.setBattery((byte) (bArr[i] & 7));
            if (((byte) (bArr[i] & 8)) == (byte) 8) {
                weightData_A2.setVoltageData(((double) ((bArr[i + 1] & 255) + ((((((bArr[i] & 128) >>> 7) * 16) * 16) * 16) + ((((bArr[i] & 64) >>> 6) * 16) * 16)))) / 100.0d);
            }
        }
        return weightData_A2;
    }

    public static WeightData_A2 m1871a(byte[] bArr) {
        WeightData_A2 weightData_A2 = new WeightData_A2();
        byte b = bArr[0];
        byte[] bArr2 = new byte[]{bArr[1], bArr[2], bArr[3], bArr[4]};
        weightData_A2.setWeight((double) C2255a.m1927m(bArr2));
        int i = 5;
        if (((byte) (b & 1)) == (byte) 1) {
            bArr2[0] = bArr[5];
            weightData_A2.UTCbytes[0] = bArr[5];
            bArr2[1] = bArr[6];
            weightData_A2.UTCbytes[1] = bArr[6];
            bArr2[2] = bArr[7];
            weightData_A2.UTCbytes[2] = bArr[7];
            bArr2[3] = bArr[8];
            weightData_A2.UTCbytes[3] = bArr[8];
            i = 9;
            weightData_A2.setDate(C2255a.m1919i(bArr2));
        }
        if (((byte) (b & 2)) == (byte) 2) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            bArr2[2] = bArr[i + 2];
            bArr2[3] = bArr[i + 3];
            i += 4;
            weightData_A2.setResistance_1((double) C2255a.m1927m(bArr2));
        }
        if (((byte) (b & 4)) == (byte) 4) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            bArr2[2] = bArr[i + 2];
            bArr2[3] = bArr[i + 3];
            i += 4;
            weightData_A2.setResistance_2((double) C2255a.m1927m(bArr2));
        }
        if (((byte) (b & 8)) == (byte) 8) {
            weightData_A2.setUserNo(bArr[i]);
            i++;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            byte b2 = bArr[i];
            switch (b2 & 1) {
                case 0:
                    weightData_A2.setWeightStatus(WeightData_A2.MEASUREMENT_STATUS_WEIGHT_STATUS_WEIGHT_UNSTABLE);
                    break;
                case 1:
                    weightData_A2.setWeightStatus(WeightData_A2.MEASUREMENT_STATUS_WEIGHT_STATUS_WEIGHT_STABLE);
                    break;
            }
            switch ((b2 & 14) >>> 1) {
                case 0:
                    weightData_A2.setImpedanceStatus(WeightData_A2.MEASUREMENT_STATUS_IMPEDANCE_STATUS_IDLE);
                    break;
                case 1:
                    weightData_A2.setImpedanceStatus(WeightData_A2.MEASUREMENT_STATUS_IMPEDANCE_STATUS_PROCESSING);
                    break;
                case 2:
                    weightData_A2.setImpedanceStatus(WeightData_A2.MEASUREMENT_STATUS_IMPEDANCE_STATUS_SHOES);
                    break;
                case 3:
                    weightData_A2.setImpedanceStatus(WeightData_A2.MEASUREMENT_STATUS_IMPEDANCE_STATUS_BAREFOOT);
                    break;
                case 4:
                    weightData_A2.setImpedanceStatus(WeightData_A2.MEASUREMENT_STATUS_IMPEDANCE_STATUS_FINISH);
                    break;
                case 5:
                    weightData_A2.setImpedanceStatus(WeightData_A2.MEASUREMENT_STATUS_IMPEDANCE_STATUS_ERROR);
                    break;
            }
            switch ((b2 & 16) >>> 4) {
                case 0:
                    weightData_A2.hasAppendMeasurement = false;
                    break;
                case 1:
                    weightData_A2.hasAppendMeasurement = true;
                    weightData_A2.setFlag(1);
                    break;
            }
        }
        return weightData_A2;
    }

    public static WeightData_A2 m1872a(byte[] bArr, PersonalUserInfo personalUserInfo) {
        WeightData_A2 a = C2255a.m1871a(bArr);
        if (personalUserInfo != null) {
            int i = 0;
            if (personalUserInfo.getSex() == SexType.FEMALE) {
                i = 1;
            }
            double a2 = C2255a.m1862a(personalUserInfo.getWeight(), a.getResistance_2(), personalUserInfo.getHeight(), personalUserInfo.getAge(), i);
            a.setBodyFatRatio((float) a2);
            a.setBodyWaterRatio((float) C2255a.m1866a(personalUserInfo.getWeight(), (int) a.getResistance_2(), personalUserInfo.getHeight(), i));
            double a3 = C2255a.m1864a(personalUserInfo.getWeight(), a2, i);
            a.setMuscleMassRatio((float) a3);
            a.setBasalMetabolism((float) C2255a.m1885b(personalUserInfo.getWeight(), personalUserInfo.getAge(), a3, i));
            a.setBoneDensity((float) C2255a.m1865a(a3, i));
        }
        return a;
    }

    public static WeightData_A3 m1873a(WeightData_A3 weightData_A3, byte[] bArr) {
        byte b = bArr[0];
        byte[] bArr2 = new byte[]{bArr[1], bArr[2], bArr[3], bArr[4]};
        weightData_A3.setUtc(C2255a.m1926l(bArr2));
        int i = 5;
        if (((byte) (b & 1)) == (byte) 1) {
            weightData_A3.setUserId(C2255a.m1892b(bArr[5]));
            i = 6;
        }
        if (((byte) (b & 2)) == (byte) 2) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            weightData_A3.setBasalMetabolism(C2255a.m1929n(bArr2));
            i += 2;
        }
        if (((byte) (b & 4)) == (byte) 4) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            weightData_A3.setBodyFatRatio(C2255a.m1929n(bArr2));
            i += 2;
        }
        if (((byte) (b & 8)) == (byte) 8) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            weightData_A3.setBodyWaterRatio(C2255a.m1929n(bArr2));
            i += 2;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            weightData_A3.setVisceralFatLevel(C2255a.m1929n(bArr2));
            i += 2;
        }
        if (((byte) (b & 32)) == (byte) 32) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            weightData_A3.setMuscleMassRatio(C2255a.m1929n(bArr2));
            i += 2;
        }
        if (((byte) (b & 64)) == (byte) 64) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            weightData_A3.setBoneDensity(C2255a.m1929n(bArr2));
            i += 2;
        }
        if (((byte) ((b & 128) >>> 7)) == (byte) 1) {
            weightData_A3.setBattery(C2255a.m1892b(bArr[i]));
        }
        return weightData_A3;
    }

    public static String m1874a(byte b) {
        int i;
        char[] cArr = new char[8];
        for (i = 0; i < 8; i++) {
            if (((b >>> i) & 1) == 1) {
                cArr[i] = '1';
            } else {
                cArr[i] = '0';
            }
        }
        String str = "";
        for (i = cArr.length - 1; i >= 0; i--) {
            str = new StringBuilder(String.valueOf(str)).append(cArr[i]).toString();
        }
        return str;
    }

    public static List m1875a(long j, long j2, List list) {
        int i;
        ArrayList arrayList = new ArrayList();
        List r = C2255a.m1934r(C2255a.m1932p(list));
        List arrayList2 = new ArrayList();
        for (i = 0; i < r.size(); i++) {
            long a = C2255a.m1868a(((PedometerData) r.get(i)).getDate());
            if (a > j && a < j2) {
                arrayList2.add((PedometerData) r.get(i));
            }
        }
        r = C2255a.m1931o(arrayList2);
        arrayList2 = new ArrayList();
        for (i = 0; i < r.size(); i++) {
            SleepAndExerciseInfo sleepAndExerciseInfo = new SleepAndExerciseInfo();
            sleepAndExerciseInfo.setStartTimeMillis(C2255a.m1868a(((PedometerData) r.get(i)).getDate()));
            sleepAndExerciseInfo.setType(0);
            sleepAndExerciseInfo.setLevel(((PedometerData) r.get(i)).getIntensityLevel());
            sleepAndExerciseInfo.setEndTimeMillis(300000 + sleepAndExerciseInfo.getStartTimeMillis());
            arrayList2.add(sleepAndExerciseInfo);
        }
        return arrayList2;
    }

    public static List m1876a(String str, String str2, String str3) {
        int i = 0;
        if (str == null || str.length() < 16) {
            return null;
        }
        TimeZone timeZone = TimeZone.getDefault();
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeZone(timeZone);
        long parseLong = (Long.parseLong(str.substring(2, 10), 16) - 1262304000) + ((long) (gregorianCalendar.get(15) / 1000));
        String substring = str.substring(16, str.length());
        List arrayList = new ArrayList();
        for (int i2 = 0; i2 + 2 <= substring.length(); i2 += 2) {
            SleepInfo_A4 sleepInfo_A4 = new SleepInfo_A4();
            sleepInfo_A4.setDeviceId(str2);
            sleepInfo_A4.setBroadcastId(str3);
            sleepInfo_A4.setUtc(((long) (i * 300)) + parseLong);
            sleepInfo_A4.setSleepStatus(Integer.parseInt(substring.substring(i2, i2 + 2), 16));
            sleepInfo_A4.setDate(C2255a.m1919i(C2255a.m1883a((300 * ((long) i)) + parseLong)));
            arrayList.add(sleepInfo_A4);
            i++;
        }
        return arrayList;
    }

    public static List m1877a(List list) {
        if (list == null) {
            return null;
        }
        List p = C2255a.m1932p(list);
        C2255a.m1939w(p);
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (int i = 1; i < p.size(); i++) {
            List list2;
            System.out.println("――>――>――>――>――>――>――>次数" + i);
            C2247g c2247g;
            PedometerData v;
            int walkSteps;
            if (C2255a.m1893b((PedometerData) p.get(i - 1), (PedometerData) p.get(i))) {
                arrayList2.add((PedometerData) p.get(i - 1));
                if (i == p.size() - 1) {
                    arrayList2.add((PedometerData) p.get(i));
                    c2247g = new C2247g();
                    c2247g.m1834a(C2255a.m1901d((PedometerData) p.get(i)));
                    c2247g.m1838b(C2255a.m1905e((PedometerData) p.get(i)));
                    v = C2255a.m1938v(arrayList2);
                    walkSteps = v.getWalkSteps() + v.getRunSteps();
                    c2247g.m1832a(v.getCalories());
                    c2247g.m1840d(v.getRunSteps());
                    c2247g.m1841e(v.getWalkSteps());
                    c2247g.m1839c(walkSteps);
                    c2247g.m1836b((double) v.getDistance());
                    c2247g.m1842f(v.getExerciseTime());
                    arrayList.add(c2247g);
                    list2 = arrayList2;
                } else {
                    list2 = arrayList2;
                }
            } else {
                arrayList2.add((PedometerData) p.get(i - 1));
                c2247g = new C2247g();
                c2247g.m1834a(C2255a.m1901d((PedometerData) p.get(i - 1)));
                c2247g.m1838b(C2255a.m1905e((PedometerData) p.get(i - 1)));
                v = C2255a.m1938v(arrayList2);
                c2247g.m1839c(v.getWalkSteps() + v.getRunSteps());
                c2247g.m1841e(v.getWalkSteps());
                c2247g.m1840d(v.getRunSteps());
                c2247g.m1832a(v.getCalories());
                c2247g.m1836b((double) v.getDistance());
                c2247g.m1842f(v.getExerciseTime());
                arrayList.add(c2247g);
                arrayList2 = new ArrayList();
                if (i == p.size() - 1) {
                    arrayList2.add((PedometerData) p.get(i));
                    c2247g = new C2247g();
                    c2247g.m1834a(C2255a.m1901d((PedometerData) p.get(i)));
                    c2247g.m1838b(C2255a.m1905e((PedometerData) p.get(i)));
                    v = C2255a.m1938v(arrayList2);
                    walkSteps = v.getWalkSteps() + v.getRunSteps();
                    c2247g.m1832a(v.getCalories());
                    c2247g.m1840d(v.getRunSteps());
                    c2247g.m1841e(v.getWalkSteps());
                    c2247g.m1842f(v.getExerciseTime());
                    c2247g.m1839c(walkSteps);
                    c2247g.m1836b((double) v.getDistance());
                    arrayList.add(c2247g);
                }
                list2 = arrayList2;
            }
            arrayList2 = list2;
        }
        return arrayList;
    }

    public static List m1878a(List list, int i) {
        return i == 0 ? C2255a.m1890b(list) : C2255a.m1877a(list);
    }

    public static boolean m1879a(PedometerData pedometerData, PedometerData pedometerData2) {
        boolean z = true;
        if (pedometerData == null || pedometerData2 == null) {
            z = false;
        } else {
            long b = C2255a.m1886b(pedometerData);
            long b2 = C2255a.m1886b(pedometerData2);
            long j = 0;
            if (b2 > b) {
                j = b2 - b;
            }
            if (b2 < b) {
                j = b - b2;
            }
            if (b2 != b && r2 >= 3600000) {
                return false;
            }
        }
        return z;
    }

    public static byte[] m1880a() {
        Date parse;
        ParseException e;
        Date date = null;
        Calendar instance = Calendar.getInstance();
        String str = instance.get(1);
        String str2 = (instance.get(2) + 1);
        String str3 = instance.get(5);
        String str4 = instance.get(11);
        String str5 = instance.get(12);
        String stringBuilder = new StringBuilder(String.valueOf(str)).append("-").append(str2).append("-").append(str3).append(Constants.SPACE).append(str4).append(":").append(str5).append(":").append(instance.get(13)).toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            parse = simpleDateFormat.parse(stringBuilder);
            try {
                date = simpleDateFormat.parse("2010-01-01 00:00:00");
            } catch (ParseException e2) {
                e = e2;
                e.printStackTrace();
                return C2255a.m1883a((parse.getTime() - date.getTime()) / 1000);
            }
        } catch (ParseException e3) {
            e = e3;
            parse = date;
            e.printStackTrace();
            return C2255a.m1883a((parse.getTime() - date.getTime()) / 1000);
        }
        return C2255a.m1883a((parse.getTime() - date.getTime()) / 1000);
    }

    public static byte[] m1881a(float f) {
        int floatToIntBits = Float.floatToIntBits(f);
        return new byte[]{(byte) (floatToIntBits & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & floatToIntBits) >> 8), (byte) ((16711680 & floatToIntBits) >> 16), (byte) ((floatToIntBits & ViewCompat.MEASURED_STATE_MASK) >> 24)};
    }

    public static byte[] m1882a(int i) {
        r0 = new byte[2];
        System.out.println("32位" + Integer.toBinaryString(i));
        r0[0] = (byte) (i & 255);
        r0[1] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >>> 8);
        return r0;
    }

    public static byte[] m1883a(long j) {
        r0 = new byte[4];
        long j2 = -1 & j;
        r0[0] = (byte) ((int) (255 & j2));
        r0[1] = (byte) ((int) ((65280 & j2) >>> 8));
        r0[2] = (byte) ((int) ((16711680 & j2) >>> 16));
        r0[3] = (byte) ((int) ((j2 & -16777216) >>> 24));
        return r0;
    }

    public static byte m1884b(int i) {
        return (byte) (i & 255);
    }

    public static double m1885b(double d, int i, double d2, int i2) {
        return i2 == 0 ? ((-72.421d + (30.809d * d2)) + (1.795d * d)) - (2.444d * ((double) i)) : i2 == 1 ? ((-40.135d + (25.669d * d2)) + (6.067d * d)) - (1.964d * ((double) i)) : 0.0d;
    }

    public static long m1886b(PedometerData pedometerData) {
        long a = C2255a.m1868a(pedometerData.getDate());
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(a);
        instance.set(12, instance.getMinimum(12));
        instance.set(13, instance.getMinimum(13));
        instance.set(14, instance.getMinimum(14));
        return instance.getTimeInMillis();
    }

    public static PedometerData m1887b(String str, String str2) {
        if (str == null || str.length() <= 0 || str2 == null) {
            return null;
        }
        if (str2.equals("CA")) {
            return C2255a.m1922j(str);
        }
        if (str2.equals("C9")) {
            return C2255a.m1915h(str);
        }
        if (str2.equals("C4")) {
            return C2255a.m1918i(str);
        }
        C2220h.m1596a((Object) C2255a.class, "Failed to parse pedometer data for unknown command code..." + str2, 1);
        return null;
    }

    public static PedometerData m1888b(byte[] bArr) {
        int i;
        PedometerData pedometerData = new PedometerData();
        byte b = bArr[0];
        byte[] bArr2 = new byte[]{bArr[1], bArr[2], bArr[3]};
        switch ((byte) (b & 1)) {
            case (byte) 0:
                pedometerData.setWalkSteps(C2255a.m1924k(bArr2));
                break;
            case (byte) 1:
                pedometerData.setRunSteps(C2255a.m1924k(bArr2));
                break;
        }
        if (((byte) (b & 2)) == (byte) 2) {
            pedometerData.setDate(C2255a.m1919i(new byte[]{bArr[4], bArr[5], bArr[6], bArr[7]}));
            i = 8;
        } else {
            i = 4;
        }
        if (((byte) (b & 4)) == (byte) 4) {
            pedometerData.setExamount((double) C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 8)) == (byte) 8) {
            pedometerData.setCalories((double) C2255a.m1927m(new byte[]{bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]}));
            i += 4;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            pedometerData.setExerciseTime(C2255a.m1921j(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 32)) == (byte) 32) {
            pedometerData.setDistance((double) C2255a.m1921j(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 64)) == (byte) 64) {
            byte b2 = bArr[i];
            pedometerData.setBattery(b2 & 7);
            pedometerData.setSleepStatus((b2 & 24) >>> 3);
            pedometerData.setIntensityLevel((b2 & 224) >>> 5);
        }
        return pedometerData;
    }

    public static Date m1889b(String str) {
        try {
            return new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List m1890b(List list) {
        if (list == null) {
            return null;
        }
        List p = C2255a.m1932p(list);
        C2255a.m1939w(p);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        List arrayList2 = new ArrayList();
        while (i5 < p.size()) {
            List list2;
            int i6;
            double d3;
            int i7;
            int i8;
            C2247g c2247g;
            double d4;
            double d5;
            int t;
            int u;
            int e;
            int f;
            int g;
            int h;
            double d6;
            int i9;
            if (C2255a.m1879a((PedometerData) p.get(i5 - 1), (PedometerData) p.get(i5))) {
                arrayList2.add((PedometerData) p.get(i5 - 1));
                if (i5 == p.size() - 1) {
                    arrayList2.add((PedometerData) p.get(i5));
                    c2247g = new C2247g();
                    c2247g.m1834a(C2255a.m1886b((PedometerData) p.get(i5)));
                    c2247g.m1838b(C2255a.m1896c((PedometerData) p.get(i5)));
                    d4 = 0.0d;
                    d5 = 0.0d;
                    if (arrayList2.size() != 1) {
                        t = C2255a.m1936t(arrayList2);
                        u = C2255a.m1937u(arrayList2);
                        e = C2255a.m1904e(arrayList2);
                        f = C2255a.m1908f(arrayList2);
                        g = C2255a.m1911g(arrayList2);
                        h = C2255a.m1914h(arrayList2);
                        d4 = C2255a.m1895c(arrayList2);
                        d6 = C2255a.m1900d(arrayList2);
                        t -= u;
                        u = e - f;
                        e = g - h;
                        d4 -= d6;
                        d5 = C2255a.m1917i(arrayList2) - C2255a.m1920j(arrayList2);
                        f = C2255a.m1923k(arrayList2) - C2255a.m1925l(arrayList2);
                    } else {
                        f = 0;
                        e = 0;
                        u = 0;
                        t = 0;
                        if (arrayList2.size() == 1) {
                            if (i5 != 1) {
                                u = (((PedometerData) arrayList2.get(0)).getRunSteps() + ((PedometerData) arrayList2.get(0)).getWalkSteps()) - i;
                                e = ((PedometerData) arrayList2.get(0)).getWalkSteps() - i2;
                                f = ((PedometerData) arrayList2.get(0)).getRunSteps() - i4;
                                d4 = ((PedometerData) arrayList2.get(0)).getCalories() - d;
                                d5 = ((double) ((PedometerData) arrayList2.get(0)).getDistance()) - d2;
                                t = ((PedometerData) arrayList2.get(0)).getExerciseTime() - i3;
                                if (u < 0) {
                                    d4 = 0.0d;
                                    d5 = 0.0d;
                                    f = 0;
                                    e = 0;
                                    u = 0;
                                    t = 0;
                                } else {
                                    i9 = u;
                                    u = e;
                                    e = f;
                                    f = t;
                                    t = i9;
                                }
                            } else {
                                u = ((PedometerData) arrayList2.get(0)).getWalkSteps() + ((PedometerData) arrayList2.get(0)).getRunSteps();
                                e = ((PedometerData) arrayList2.get(0)).getWalkSteps();
                                f = ((PedometerData) arrayList2.get(0)).getRunSteps();
                                d4 = ((PedometerData) arrayList2.get(0)).getCalories();
                                d5 = (double) ((PedometerData) arrayList2.get(0)).getDistance();
                                i9 = u;
                                u = e;
                                e = f;
                                f = ((PedometerData) arrayList2.get(0)).getExerciseTime();
                                t = i9;
                            }
                        }
                    }
                    c2247g.m1839c(t);
                    c2247g.m1840d(e);
                    c2247g.m1841e(u);
                    c2247g.m1832a(d4);
                    c2247g.m1836b(d5);
                    c2247g.m1842f(f);
                    c2247g.m1835a(((PedometerData) arrayList2.get(0)).getDeviceId());
                    c2247g.m1837b(((PedometerData) arrayList2.get(0)).getBattery());
                    c2247g.m1833a(((PedometerData) arrayList2.get(0)).getUserNo());
                    arrayList.add(c2247g);
                    list2 = arrayList2;
                    i6 = i3;
                    d3 = d2;
                    d2 = d;
                    i7 = i4;
                    i8 = i;
                    i = i2;
                } else {
                    list2 = arrayList2;
                    i6 = i3;
                    d3 = d2;
                    d2 = d;
                    i7 = i4;
                    i8 = i;
                    i = i2;
                }
            } else {
                arrayList2.add((PedometerData) p.get(i5 - 1));
                i = C2255a.m1936t(arrayList2);
                i2 = C2255a.m1904e(arrayList2);
                i4 = C2255a.m1911g(arrayList2);
                d = C2255a.m1895c(arrayList2);
                d2 = C2255a.m1917i(arrayList2);
                i3 = C2255a.m1923k(arrayList2);
                c2247g = new C2247g();
                C2247g c2247g2 = c2247g;
                c2247g2.m1834a(C2255a.m1886b((PedometerData) p.get(i5 - 1)));
                c2247g2 = c2247g;
                c2247g2.m1838b(C2255a.m1896c((PedometerData) p.get(i5 - 1)));
                d4 = 0.0d;
                d5 = 0.0d;
                if (arrayList2.size() != 1) {
                    t = C2255a.m1936t(arrayList2);
                    u = C2255a.m1937u(arrayList2);
                    e = C2255a.m1904e(arrayList2);
                    g = C2255a.m1908f(arrayList2);
                    h = C2255a.m1911g(arrayList2);
                    int h2 = C2255a.m1914h(arrayList2);
                    d6 = C2255a.m1895c(arrayList2);
                    double d7 = C2255a.m1900d(arrayList2);
                    f = t - u;
                    e -= g;
                    u = h - h2;
                    d4 = d6 - d7;
                    d5 = C2255a.m1917i(arrayList2) - C2255a.m1920j(arrayList2);
                    t = f;
                    f = e;
                    e = u;
                    u = C2255a.m1923k(arrayList2) - C2255a.m1925l(arrayList2);
                } else {
                    t = 0;
                    u = 0;
                    e = 0;
                    f = 0;
                    if (arrayList2.size() == 1) {
                        if (i5 != 1) {
                            f = (((PedometerData) arrayList2.get(0)).getRunSteps() + ((PedometerData) arrayList2.get(0)).getWalkSteps()) - i;
                            e = ((PedometerData) arrayList2.get(0)).getWalkSteps() - i2;
                            u = ((PedometerData) arrayList2.get(0)).getRunSteps() - i4;
                            d4 = ((PedometerData) arrayList2.get(0)).getCalories() - d;
                            d5 = ((double) ((PedometerData) arrayList2.get(0)).getDistance()) - d2;
                            t = ((PedometerData) arrayList2.get(0)).getExerciseTime() - i3;
                            if (f < 0) {
                                d4 = 0.0d;
                                d5 = 0.0d;
                                t = 0;
                                f = 0;
                                e = 0;
                                u = 0;
                            }
                        } else {
                            f = ((PedometerData) arrayList2.get(0)).getWalkSteps() + ((PedometerData) arrayList2.get(0)).getRunSteps();
                            e = ((PedometerData) arrayList2.get(0)).getWalkSteps();
                            u = ((PedometerData) arrayList2.get(0)).getRunSteps();
                            d4 = ((PedometerData) arrayList2.get(0)).getCalories();
                            d5 = (double) ((PedometerData) arrayList2.get(0)).getDistance();
                            t = f;
                            f = e;
                            e = u;
                            u = ((PedometerData) arrayList2.get(0)).getExerciseTime();
                        }
                    }
                    i9 = t;
                    t = f;
                    f = e;
                    e = u;
                    u = i9;
                }
                c2247g.m1839c(t);
                c2247g.m1840d(e);
                c2247g.m1841e(f);
                c2247g.m1832a(d4);
                c2247g.m1836b(d5);
                c2247g.m1842f(u);
                c2247g.m1835a(((PedometerData) arrayList2.get(0)).getDeviceId());
                c2247g.m1837b(((PedometerData) arrayList2.get(0)).getBattery());
                c2247g.m1833a(((PedometerData) arrayList2.get(0)).getUserNo());
                arrayList.add(c2247g);
                arrayList2 = new ArrayList();
                if (i5 == p.size() - 1) {
                    arrayList2.add((PedometerData) p.get(i5));
                    c2247g = new C2247g();
                    c2247g.m1834a(C2255a.m1886b((PedometerData) p.get(i5)));
                    c2247g.m1838b(C2255a.m1896c((PedometerData) p.get(i5)));
                    if (arrayList2.size() != 1) {
                        t = C2255a.m1936t(arrayList2);
                        u = C2255a.m1937u(arrayList2);
                        e = C2255a.m1904e(arrayList2);
                        f = C2255a.m1908f(arrayList2);
                        g = C2255a.m1911g(arrayList2);
                        h = C2255a.m1914h(arrayList2);
                        d4 = C2255a.m1895c(arrayList2);
                        d6 = C2255a.m1900d(arrayList2);
                        t -= u;
                        f = e - f;
                        e = g - h;
                        d4 -= d6;
                        d5 = C2255a.m1917i(arrayList2) - C2255a.m1920j(arrayList2);
                        u = C2255a.m1923k(arrayList2) - C2255a.m1925l(arrayList2);
                    } else {
                        t = 0;
                        if (arrayList2.size() == 1) {
                            if (i5 != 1) {
                                u = (((PedometerData) arrayList2.get(0)).getRunSteps() + ((PedometerData) arrayList2.get(0)).getWalkSteps()) - i;
                                f = ((PedometerData) arrayList2.get(0)).getWalkSteps() - i2;
                                e = ((PedometerData) arrayList2.get(0)).getRunSteps() - i4;
                                d4 = ((PedometerData) arrayList2.get(0)).getCalories() - d;
                                d5 = ((double) ((PedometerData) arrayList2.get(0)).getDistance()) - d2;
                                t = ((PedometerData) arrayList2.get(0)).getExerciseTime() - i3;
                                if (u < 0) {
                                    d4 = 0.0d;
                                    d5 = 0.0d;
                                    t = 0;
                                    u = 0;
                                    e = 0;
                                    f = 0;
                                } else {
                                    i9 = u;
                                    u = t;
                                    t = i9;
                                }
                            } else {
                                u = ((PedometerData) arrayList2.get(0)).getWalkSteps() + ((PedometerData) arrayList2.get(0)).getRunSteps();
                                f = ((PedometerData) arrayList2.get(0)).getWalkSteps();
                                e = ((PedometerData) arrayList2.get(0)).getRunSteps();
                                d4 = ((PedometerData) arrayList2.get(0)).getCalories();
                                d5 = (double) ((PedometerData) arrayList2.get(0)).getDistance();
                                i9 = u;
                                u = ((PedometerData) arrayList2.get(0)).getExerciseTime();
                                t = i9;
                            }
                        }
                    }
                    c2247g.m1839c(t);
                    c2247g.m1840d(e);
                    c2247g.m1841e(f);
                    c2247g.m1832a(d4);
                    c2247g.m1836b(d5);
                    c2247g.m1842f(u);
                    c2247g.m1835a(((PedometerData) arrayList2.get(0)).getDeviceId());
                    c2247g.m1837b(((PedometerData) arrayList2.get(0)).getBattery());
                    c2247g.m1833a(((PedometerData) arrayList2.get(0)).getUserNo());
                    arrayList.add(c2247g);
                }
                list2 = arrayList2;
                i6 = i3;
                d3 = d2;
                d2 = d;
                i7 = i4;
                i8 = i;
                i = i2;
            }
            i5++;
            i2 = i;
            i = i8;
            double d8 = d3;
            i4 = i7;
            i3 = i6;
            d = d2;
            arrayList2 = list2;
            d2 = d8;
        }
        return arrayList;
    }

    public static List m1891b(List list, int i) {
        int i2 = 0;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        List arrayList3 = new ArrayList();
        List r = C2255a.m1934r(C2255a.m1932p(list));
        if (i == 0) {
            arrayList2 = C2255a.m1940x(C2255a.m1941y(r));
            while (i2 < arrayList2.size()) {
                SleepAndExerciseInfo sleepAndExerciseInfo = new SleepAndExerciseInfo();
                sleepAndExerciseInfo.setStartTimeMillis(C2255a.m1868a(((PedometerData) arrayList2.get(i2)).getDate()));
                sleepAndExerciseInfo.setType(1);
                sleepAndExerciseInfo.setLevel(((PedometerData) arrayList2.get(i2)).getIntensityLevel());
                sleepAndExerciseInfo.setEndTimeMillis(sleepAndExerciseInfo.getStartTimeMillis() + 300000);
                arrayList.add(sleepAndExerciseInfo);
                i2++;
            }
            return arrayList;
        } else if (i == 1) {
            arrayList3 = C2255a.m1942z(r);
            for (r2 = 0; r2 < arrayList3.size(); r2++) {
                SleepAndExerciseInfo sleepAndExerciseInfo2 = new SleepAndExerciseInfo();
                sleepAndExerciseInfo2.setStartTimeMillis(C2255a.m1868a(((PedometerData) arrayList3.get(r2)).getDate()));
                sleepAndExerciseInfo2.setType(0);
                sleepAndExerciseInfo2.setExamount(((PedometerData) arrayList3.get(r2)).getExamount());
                sleepAndExerciseInfo2.setLevel(((PedometerData) arrayList3.get(r2)).getIntensityLevel());
                sleepAndExerciseInfo2.setEndTimeMillis(sleepAndExerciseInfo2.getStartTimeMillis() + 300000);
                arrayList2.add(sleepAndExerciseInfo2);
            }
            return arrayList2;
        } else if (i != 2) {
            return arrayList3;
        } else {
            arrayList = C2255a.m1935s(r);
            arrayList2 = C2255a.m1891b(r, 0);
            List b = C2255a.m1891b(arrayList, 1);
            for (r2 = 0; r2 < arrayList2.size(); r2++) {
                arrayList3.add((SleepAndExerciseInfo) arrayList2.get(r2));
            }
            while (i2 < b.size()) {
                arrayList3.add((SleepAndExerciseInfo) b.get(i2));
                i2++;
            }
            return C2255a.m1928m(arrayList3);
        }
    }

    public static short m1892b(byte b) {
        return (short) (((short) b) & 255);
    }

    public static boolean m1893b(PedometerData pedometerData, PedometerData pedometerData2) {
        boolean z = true;
        if (pedometerData == null || pedometerData2 == null) {
            z = false;
        } else {
            long d = C2255a.m1901d(pedometerData);
            long d2 = C2255a.m1901d(pedometerData2);
            long j = 0;
            if (d2 > d) {
                j = d2 - d;
            }
            if (d2 < d) {
                j = d - d2;
            }
            if (d2 != d && r2 >= 86400000) {
                return false;
            }
        }
        return z;
    }

    public static byte[] m1894b(float f) {
        r0 = new byte[4];
        String f2 = Float.toString(f);
        int parseInt = Integer.parseInt(f2.replace(".", ""));
        int i = -((f2.length() - 1) - f2.indexOf("."));
        r0[0] = C2255a.m1883a((long) parseInt)[0];
        r0[1] = C2255a.m1883a((long) parseInt)[1];
        r0[2] = C2255a.m1883a((long) parseInt)[2];
        r0[3] = (byte) i;
        return r0;
    }

    public static double m1895c(List list) {
        int i = 0;
        if (list == null) {
            return 0.0d;
        }
        double calories = ((PedometerData) list.get(0)).getCalories();
        while (i < list.size()) {
            double calories2 = ((PedometerData) list.get(i)).getCalories();
            if (calories2 > calories) {
                calories = calories2;
            }
            i++;
        }
        return calories;
    }

    public static long m1896c(PedometerData pedometerData) {
        long a = C2255a.m1868a(pedometerData.getDate());
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(a);
        instance.set(12, instance.getMaximum(12));
        instance.set(13, instance.getMaximum(13));
        instance.set(14, instance.getMaximum(14));
        return instance.getTimeInMillis();
    }

    public static HeightData m1897c(byte[] bArr) {
        HeightData heightData = new HeightData();
        byte b = bArr[0];
        switch (b & 1) {
            case 0:
                heightData.setUnit("metre");
                break;
            case 1:
                heightData.setUnit("inch");
                break;
        }
        byte[] bArr2 = new byte[]{bArr[1], bArr[2], bArr[3], bArr[4]};
        heightData.setHeight((double) (100.0f * C2255a.m1927m(bArr2)));
        int i = 5;
        if (((byte) (b & 2)) == (byte) 2) {
            bArr2[0] = bArr[5];
            bArr2[1] = bArr[6];
            bArr2[2] = bArr[7];
            bArr2[3] = bArr[8];
            heightData.setDate(C2255a.m1919i(bArr2));
            i = 9;
        }
        if (((byte) (b & 4)) == (byte) 4) {
            heightData.setUserNo(C2255a.m1892b(bArr[i]));
            i++;
        }
        if (((byte) (b & 8)) == (byte) 8) {
            switch (bArr[i]) {
                case (byte) 0:
                    heightData.heightStatus = HeightData.HEIGHT_UNLOCKED;
                    break;
                case (byte) 1:
                    heightData.heightStatus = HeightData.HEIGHT_LOCKED;
                    break;
            }
            i++;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            heightData.setBattery(C2255a.m1892b(bArr[i]));
        }
        return heightData;
    }

    public static boolean m1898c(String str) {
        Date b = C2255a.m1889b(str);
        Calendar instance = Calendar.getInstance();
        instance.setTime(b);
        int i = instance.get(12);
        return (i % 10 == 4 || i % 10 == 9) && instance.get(13) == 59;
    }

    public static byte[] m1899c(float f) {
        int i = 0;
        byte[] bArr = new byte[2];
        String f2 = Float.toString(f);
        String f3 = Float.toString(f);
        String num = Integer.toString(Integer.parseInt(f2.replace(".", "")));
        int i2 = 0;
        while (num.endsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO) && num.length() > 1) {
            num = num.substring(0, num.length() - 1);
            i2++;
        }
        int parseInt = Integer.parseInt(num);
        System.out.println("tmp=*" + parseInt);
        if (parseInt > 2047 || parseInt < -2048) {
            bArr = new byte[2];
            bArr[0] = (byte) 1;
            return bArr;
        }
        i2 += -((f3.length() - 1) - f3.indexOf("."));
        if (i2 > 7 || i2 < -8) {
            bArr = new byte[2];
            bArr[0] = (byte) 1;
            return bArr;
        }
        switch (i2) {
            case -8:
                i2 = 8;
                break;
            case -7:
                i2 = 9;
                break;
            case -6:
                i2 = 10;
                System.out.println("highFourBit=" + Integer.toBinaryString(10));
                break;
            case -5:
                i2 = 11;
                break;
            case ProfilePictureView.LARGE /*-4*/:
                i2 = 12;
                break;
            case -3:
                i2 = 13;
                break;
            case -2:
                i2 = 14;
                break;
            case -1:
                i2 = 15;
                break;
        }
        if (parseInt >= 0) {
            if (parseInt < 0) {
                parseInt = (((-parseInt) - 1) ^ -1) & 2047;
                i = 1;
            }
            return C2255a.m1882a(((i2 << 12) + (i << 11)) + parseInt);
        }
        if (parseInt < 0) {
            parseInt = (((-parseInt) - 1) ^ -1) & 2047;
            i = 1;
        }
        return C2255a.m1882a(((i2 << 12) + (i << 11)) + parseInt);
    }

    public static double m1900d(List list) {
        int i = 0;
        if (list == null) {
            return 0.0d;
        }
        double calories = ((PedometerData) list.get(0)).getCalories();
        while (i < list.size()) {
            double calories2 = ((PedometerData) list.get(i)).getCalories();
            if (calories2 < calories) {
                calories = calories2;
            }
            i++;
        }
        return calories;
    }

    public static long m1901d(PedometerData pedometerData) {
        long a = C2255a.m1868a(pedometerData.getDate());
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(a);
        instance.set(11, instance.getMinimum(11));
        instance.set(12, instance.getMinimum(12));
        instance.set(13, instance.getMinimum(13));
        instance.set(14, instance.getMinimum(14));
        return instance.getTimeInMillis();
    }

    public static WeightData_A3 m1902d(byte[] bArr) {
        byte b;
        WeightData_A3 weightData_A3 = new WeightData_A3();
        byte b2 = bArr[0];
        byte[] bArr2 = new byte[]{bArr[1], bArr[2], bArr[3], bArr[4]};
        weightData_A3.setWeight((double) C2255a.m1927m(bArr2));
        int i = 5;
        if (((byte) (b2 & 1)) == (byte) 1) {
            bArr2[0] = bArr[5];
            bArr2[1] = bArr[6];
            bArr2[2] = bArr[7];
            bArr2[3] = bArr[8];
            weightData_A3.setUtc(C2255a.m1926l(bArr2));
            weightData_A3.setDate(C2255a.m1919i(bArr2));
            i = 9;
        }
        if (((byte) (b2 & 2)) == (byte) 2) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            bArr2[2] = bArr[i + 2];
            bArr2[3] = bArr[i + 3];
            weightData_A3.setWeightDifferenceValue((double) C2255a.m1927m(bArr2));
            i += 4;
        }
        if (((byte) (b2 & 4)) == (byte) 4) {
            bArr2[0] = bArr[i];
            bArr2[1] = bArr[i + 1];
            bArr2[2] = bArr[i + 2];
            bArr2[3] = bArr[i + 3];
            weightData_A3.setImpedance((double) C2255a.m1927m(bArr2));
            i += 4;
        }
        if (((byte) (b2 & 8)) == (byte) 8) {
            weightData_A3.setUserId(C2255a.m1892b(bArr[i]));
            i++;
        }
        if (((byte) (b2 & 16)) == (byte) 16) {
            byte b3 = (byte) (bArr[i] & 1);
            if (b3 == (byte) 0) {
                weightData_A3.setWeightStatus(WeightData_A3.WEIGHT_STATUS_WEIGHT_UNSTABLE);
            }
            if (b3 == (byte) 1) {
                weightData_A3.setWeightStatus(WeightData_A3.WEIGHT_STATUS_WEIGHT_STABLE);
            }
            b3 = (byte) (((byte) (bArr[i] & 14)) >>> 1);
            if (b3 == (byte) 0) {
                weightData_A3.setImpedanceStatus(WeightData_A3.IMPEDANCE_STATUS_IDLE);
            }
            if (b3 == (byte) 1) {
                weightData_A3.setImpedanceStatus(WeightData_A3.IMPEDANCE_STATUS_PROCESSING);
            }
            if (b3 == (byte) 2) {
                weightData_A3.setImpedanceStatus(WeightData_A3.IMPEDANCE_STATUS_SHOES);
            }
            if (b3 == (byte) 3) {
                weightData_A3.setImpedanceStatus(WeightData_A3.IMPEDANCE_STATUS_BAREFOOT);
            }
            if (b3 == (byte) 4) {
                weightData_A3.setImpedanceStatus(WeightData_A3.IMPEDANCE_STATUS_FINISH);
            }
            if (b3 == (byte) 5) {
                weightData_A3.setImpedanceStatus(WeightData_A3.IMPEDANCE_STATUS_ERROR);
            }
            b3 = (byte) (((byte) (bArr[i] & 16)) >>> 4);
            if (b3 == (byte) 0) {
                weightData_A3.setAppendMeasurement(false);
            }
            if (b3 == (byte) 1) {
                weightData_A3.setAppendMeasurement(true);
            }
            b = (byte) (((byte) (bArr[i] & 32)) >>> 5);
            if (b == (byte) 0) {
                weightData_A3.setAccuracyStatus("0.2LB division");
            }
            if (b == (byte) 1) {
                weightData_A3.setAccuracyStatus("0.1LB division");
            }
        }
        b = (byte) ((b2 & 96) >>> 5);
        if (b == (byte) 0) {
            weightData_A3.setDeviceSelectedUnit("Kg");
        }
        if (b == (byte) 1) {
            weightData_A3.setDeviceSelectedUnit("lb");
        }
        if (b == (byte) 2) {
            weightData_A3.setDeviceSelectedUnit("St");
        }
        weightData_A3.isAppendMeasurement();
        return weightData_A3;
    }

    public static String m1903d(String str) {
        return new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(new Date(300000 + C2255a.m1889b(str).getTime()));
    }

    public static int m1904e(List list) {
        int i = 0;
        if (list != null) {
            int i2 = 0;
            i = ((PedometerData) list.get(0)).getWalkSteps();
            while (i2 < list.size()) {
                int walkSteps = ((PedometerData) list.get(i2)).getWalkSteps();
                if (walkSteps <= i) {
                    walkSteps = i;
                }
                i2++;
                i = walkSteps;
            }
        }
        return i;
    }

    public static long m1905e(PedometerData pedometerData) {
        long a = C2255a.m1868a(pedometerData.getDate());
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(a);
        instance.set(11, instance.getMaximum(11));
        instance.set(12, instance.getMaximum(12));
        instance.set(13, instance.getMaximum(13));
        instance.set(14, instance.getMaximum(14));
        return instance.getTimeInMillis();
    }

    public static BloodPressureData m1906e(byte[] bArr) {
        BloodPressureData bloodPressureData = new BloodPressureData();
        byte b = bArr[0];
        byte[] bArr2 = new byte[]{bArr[1], bArr[2]};
        byte[] bArr3 = new byte[]{bArr[3], bArr[4]};
        byte[] bArr4 = new byte[]{bArr[5], bArr[6]};
        int i = 7;
        bloodPressureData.setDeviceSelectedUnit("mmhg");
        bloodPressureData.setSystolic(C2255a.m1929n(bArr2));
        bloodPressureData.setDiastolic(C2255a.m1929n(bArr3));
        bloodPressureData.setMeanArterialPressure(C2255a.m1929n(bArr4));
        if (((byte) (b & 2)) == (byte) 2) {
            i = 11;
            bloodPressureData.setDate(C2255a.m1919i(new byte[]{bArr[7], bArr[8], bArr[9], bArr[10]}));
        }
        if (((byte) (b & 4)) == (byte) 4) {
            bArr2 = new byte[]{bArr[i], bArr[i + 1]};
            i += 2;
            bloodPressureData.setPulseRate(C2255a.m1929n(bArr2));
        }
        if (((byte) (b & 8)) == (byte) 8) {
            bloodPressureData.setUserId(C2255a.m1892b(bArr[i]));
            i++;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            int j = C2255a.m1921j(new byte[]{bArr[i], bArr[i + 1]});
            switch (j & 1) {
                case 0:
                    bloodPressureData.setBodyMovementDetectionFlag(BloodPressureData.NO_BODY_MOVEMENT);
                    break;
                case 1:
                    bloodPressureData.setBodyMovementDetectionFlag(BloodPressureData.BODY_MOVEMENT_DURING_MEASUREMENT);
                    break;
            }
            switch (j & 1) {
                case 0:
                    bloodPressureData.setCuffFitDetectionFlag(BloodPressureData.CUFF_FITS_PROPERLY);
                    break;
                case 1:
                    bloodPressureData.setCuffFitDetectionFlag(BloodPressureData.CUFF_TOO_LOOSE);
                    break;
            }
            switch (j & 1) {
                case 0:
                    bloodPressureData.setIrregularPulseDetectionFlag(BloodPressureData.NO_IRREGULAR_PULSE_DETECTED);
                    break;
                case 1:
                    bloodPressureData.setIrregularPulseDetectionFlag(BloodPressureData.IRREGULAR_PULSE_DETECTED);
                    break;
            }
            switch (j & 3) {
                case 0:
                    bloodPressureData.setPulseRateRangeDetectionFlags(BloodPressureData.PULSE_RATE_IS_WITHIN_THE_RANGE);
                    break;
                case 1:
                    bloodPressureData.setPulseRateRangeDetectionFlags(BloodPressureData.PULSE_RATE_EXCEEDS_UPPER_LIMIT);
                    break;
                case 2:
                    bloodPressureData.setPulseRateRangeDetectionFlags(BloodPressureData.PULSE_RATE_IS_LESS_THAN_LOWER_LIMIT);
                    break;
            }
            switch (j & 1) {
                case 0:
                    bloodPressureData.setMeasurementPositionDetectionFlag(BloodPressureData.PROPER_MEASUREMENT_POSITION);
                    break;
                case 1:
                    bloodPressureData.setMeasurementPositionDetectionFlag(BloodPressureData.IMPROPER_MEASUREMENT_POSITION);
                    break;
            }
            i += 2;
        }
        if (((byte) (b & 32)) == (byte) 1) {
            bloodPressureData.setBattery(bArr[i]);
        }
        return bloodPressureData;
    }

    public static String m1907e(String str) {
        return str.length() > 12 ? str.substring(0, 12) : str;
    }

    public static int m1908f(List list) {
        int i = 0;
        if (list != null) {
            int i2 = 0;
            i = ((PedometerData) list.get(0)).getWalkSteps();
            while (i2 < list.size()) {
                int walkSteps = ((PedometerData) list.get(i2)).getWalkSteps();
                if (walkSteps >= i) {
                    walkSteps = i;
                }
                i2++;
                i = walkSteps;
            }
        }
        return i;
    }

    public static WeightUserInfo m1909f(byte[] bArr) {
        int i;
        WeightUserInfo weightUserInfo = new WeightUserInfo();
        byte b = bArr[1];
        weightUserInfo.setProductUserNumber(C2255a.m1892b(bArr[2]));
        if (((byte) (b & 1)) == (byte) 1) {
            if (bArr[3] == (byte) 1) {
                weightUserInfo.setSex(SexType.MALE);
                weightUserInfo.setAthlete(false);
            } else if (bArr[3] == (byte) 2) {
                weightUserInfo.setSex(SexType.FEMALE);
                weightUserInfo.setAthlete(false);
            } else if (bArr[3] == (byte) 3) {
                weightUserInfo.setSex(SexType.MALE);
                weightUserInfo.setAthlete(true);
            } else if (bArr[3] == (byte) 4) {
                weightUserInfo.setSex(SexType.FEMALE);
                weightUserInfo.setAthlete(true);
            }
            i = 4;
        } else {
            i = 3;
        }
        if (((byte) (b & 2)) == (byte) 2) {
            weightUserInfo.setAge(bArr[i]);
            i++;
        }
        if (((byte) (b & 4)) == (byte) 4) {
            weightUserInfo.setHeight(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
            i += 2;
        }
        if (((byte) (b & 8)) == (byte) 8) {
            weightUserInfo.setAthleteActivityLevel(C2255a.m1892b(bArr[i]));
            i++;
        }
        if (((byte) (b & 16)) == (byte) 16) {
            weightUserInfo.setUnit(WeightUserInfo.getUnitTypeByUnitValue(bArr[i]));
            i++;
        }
        if (((byte) (b & 32)) == (byte) 32) {
            System.out.println("DataTranslateUtil.parseWeightUserInfo()保存用户信息");
        }
        if (((byte) (b & 32)) == (byte) 32) {
            System.out.println("DataTranslateUtil.parseWeightUserInfo()删除用户信息");
        }
        if (((byte) (b & 64)) == (byte) 64) {
            weightUserInfo.setGoalWeight(C2255a.m1927m(new byte[]{bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]}));
            i += 4;
        }
        if (((byte) ((b & 128) >>> 7)) == (byte) 1) {
            weightUserInfo.setWaistline(C2255a.m1929n(new byte[]{bArr[i], bArr[i + 1]}));
        }
        return weightUserInfo;
    }

    public static String m1910f(String str) {
        long g = C2255a.m1913g(str.substring(0, 6));
        long g2 = C2255a.m1913g(str.substring(6, 12));
        StringBuilder stringBuilder = new StringBuilder(Long.toString(g));
        StringBuilder stringBuilder2 = new StringBuilder(Long.toString(g2));
        int length = 8 - stringBuilder.length();
        StringBuilder stringBuilder3 = stringBuilder;
        for (int i = 0; i < length; i++) {
            stringBuilder3 = stringBuilder3.insert(0, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        length = 8 - stringBuilder2.length();
        CharSequence charSequence = stringBuilder2;
        for (int i2 = 0; i2 < length; i2++) {
            charSequence = charSequence.insert(0, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        stringBuilder3.append(charSequence);
        return stringBuilder3.toString();
    }

    public static int m1911g(List list) {
        int i = 0;
        if (list != null) {
            int i2 = 0;
            i = ((PedometerData) list.get(0)).getRunSteps();
            while (i2 < list.size()) {
                int runSteps = ((PedometerData) list.get(i2)).getRunSteps();
                if (runSteps <= i) {
                    runSteps = i;
                }
                i2++;
                i = runSteps;
            }
        }
        return i;
    }

    public static int m1912g(byte[] bArr) {
        return bArr[1];
    }

    public static long m1913g(String str) {
        String toLowerCase = str.toLowerCase();
        char[] toCharArray = toLowerCase.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < toLowerCase.length(); i2++) {
            int i3 = i << 4;
            char c = toCharArray[i2];
            i = (c < '0' || c > '9') ? 0 : (short) (c - 48);
            if (c >= 'a' && c <= 'f') {
                i = (short) ((c - 97) + 10);
            }
            i |= i3;
        }
        return (long) i;
    }

    public static int m1914h(List list) {
        int i = 0;
        if (list != null) {
            int i2 = 0;
            i = ((PedometerData) list.get(0)).getRunSteps();
            while (i2 < list.size()) {
                int runSteps = ((PedometerData) list.get(i2)).getRunSteps();
                if (runSteps >= i) {
                    runSteps = i;
                }
                i2++;
                i = runSteps;
            }
        }
        return i;
    }

    private static PedometerData m1915h(String str) {
        PedometerData pedometerData = new PedometerData();
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            Integer.parseInt(ac.m1294a(str, 0, 1), 16);
            Integer.parseInt(ac.m1294a(str, 2, 3), 16);
            pedometerData.setWalkSteps(Integer.parseInt(ac.m1294a(str, 4, 6).toLowerCase(), 16));
            Date a = ac.m1296a(ac.m1294a(str, 7, 10), false);
            Calendar instance = Calendar.getInstance();
            instance.setTime(a);
            instance.add(11, 8);
            instance.add(12, 0);
            pedometerData.setDate(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(instance.getTime()));
            String a2 = ac.m1294a(str, 11, 12);
            int parseInt = Integer.parseInt(a2.substring(1, 4), 16);
            int parseInt2 = Integer.parseInt(a2.substring(0, 1), 16);
            if (parseInt2 >= 8) {
                parseInt2 -= 16;
            }
            pedometerData.setExamount(Double.parseDouble(decimalFormat.format(((double) parseInt) * Math.pow(10.0d, (double) parseInt2))));
            pedometerData.setCalories(Math.pow(10.0d, (double) (Integer.parseInt(ac.m1294a(str, 13, 13), 16) + InputDeviceCompat.SOURCE_ANY)) * ((double) Integer.parseInt(ac.m1294a(str, 14, 16), 16)));
            pedometerData.setExerciseTime(Integer.parseInt(ac.m1294a(str, 17, 18), 16));
            pedometerData.setDistance((double) Integer.parseInt(ac.m1294a(str, 19, 20), 16));
            parseInt2 = Integer.parseInt(ac.m1294a(str, 21, 21), 16);
            pedometerData.setBattery(parseInt2 & 7);
            pedometerData.setSleepStatus((parseInt2 & 24) >>> 3);
            pedometerData.setIntensityLevel((parseInt2 & 224) >>> 5);
            BigDecimal bigDecimal = new BigDecimal(new StringBuilder(String.valueOf(Integer.parseInt(ac.m1294a(str, 22, 22), 16))).toString());
            BigDecimal bigDecimal2 = new BigDecimal("100");
            BigDecimal bigDecimal3 = new BigDecimal("1.6");
            bigDecimal.divide(bigDecimal2, 2, 4);
            return pedometerData;
        } catch (Exception e) {
            return null;
        }
    }

    public static String m1916h(byte[] bArr) {
        String str;
        byte[] bArr2 = new byte[(bArr.length - 2)];
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = bArr[i + 2];
        }
        try {
            str = new String(bArr2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "unknow";
        }
        return str.trim();
    }

    public static double m1917i(List list) {
        int i = 0;
        if (list == null) {
            return 0.0d;
        }
        double distance = (double) ((PedometerData) list.get(0)).getDistance();
        while (i < list.size()) {
            int distance2 = ((PedometerData) list.get(i)).getDistance();
            if (((double) distance2) > distance) {
                distance = (double) distance2;
            }
            i++;
        }
        return distance;
    }

    private static PedometerData m1918i(String str) {
        PedometerData pedometerData = new PedometerData();
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String str2 = "C4" + str;
            pedometerData.setWalkSteps(Integer.parseInt(ac.m1294a(str2, 1, 3).toLowerCase(), 16));
            Date a = ac.m1296a(ac.m1294a(str2, 4, 7), false);
            Calendar instance = Calendar.getInstance();
            instance.setTime(a);
            instance.add(11, 8);
            instance.add(12, 0);
            pedometerData.setDate(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(instance.getTime()));
            String a2 = ac.m1294a(str2, 8, 9);
            int parseInt = Integer.parseInt(a2.substring(1, 4), 16);
            int parseInt2 = Integer.parseInt(a2.substring(0, 1), 16);
            if (parseInt2 >= 8) {
                parseInt2 -= 16;
            }
            pedometerData.setExamount(Double.parseDouble(decimalFormat.format(((double) parseInt) * Math.pow(10.0d, (double) parseInt2))));
            pedometerData.setCalories(((double) Integer.parseInt(ac.m1294a(str2, 11, 13), 16)) * Math.pow(10.0d, (double) (Integer.parseInt(ac.m1294a(str2, 10, 10), 16) + InputDeviceCompat.SOURCE_ANY)));
            ac.m1294a(str2, 14, 15);
            pedometerData.setExerciseTime(Integer.parseInt(ac.m1294a(str2, 14, 15), 16));
            pedometerData.setDistance((double) Integer.parseInt(ac.m1294a(str2, 16, 17), 16));
            parseInt2 = Integer.parseInt(ac.m1294a(str2, 18, 18), 16);
            pedometerData.setBattery(parseInt2 & 7);
            pedometerData.setSleepStatus((parseInt2 & 24) >>> 3);
            pedometerData.setIntensityLevel((parseInt2 & 224) >>> 5);
            return pedometerData;
        } catch (Exception e) {
            return null;
        }
    }

    public static String m1919i(byte[] bArr) {
        Date parse;
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(timeZone);
        try {
            parse = simpleDateFormat.parse("2010-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            parse = null;
        }
        Calendar instance = Calendar.getInstance(timeZone);
        instance.setTimeInMillis(parse.getTime() + (1000 * C2255a.m1926l(bArr)));
        return simpleDateFormat.format(instance.getTime());
    }

    public static double m1920j(List list) {
        int i = 0;
        if (list == null) {
            return 0.0d;
        }
        double distance = (double) ((PedometerData) list.get(0)).getDistance();
        while (i < list.size()) {
            int distance2 = ((PedometerData) list.get(i)).getDistance();
            if (((double) distance2) < distance) {
                distance = (double) distance2;
            }
            i++;
        }
        return distance;
    }

    public static int m1921j(byte[] bArr) {
        return (MotionEventCompat.ACTION_POINTER_INDEX_MASK & (bArr[1] << 8)) | (bArr[0] & 255);
    }

    private static PedometerData m1922j(String str) {
        PedometerData pedometerData = new PedometerData();
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            pedometerData.setWalkSteps(Integer.parseInt(ac.m1294a(str, 0, 2).toLowerCase(), 16));
            Date a = ac.m1296a(ac.m1294a(str, 3, 6), false);
            Calendar instance = Calendar.getInstance();
            instance.setTime(a);
            instance.add(11, 8);
            instance.add(12, 0);
            pedometerData.setDate(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(instance.getTime()));
            String a2 = ac.m1294a(str, 7, 8);
            int parseInt = Integer.parseInt(a2.substring(1, 4), 16);
            int parseInt2 = Integer.parseInt(a2.substring(0, 1), 16);
            if (parseInt2 >= 8) {
                parseInt2 -= 16;
            }
            pedometerData.setExamount(Double.parseDouble(decimalFormat.format(((double) parseInt) * Math.pow(10.0d, (double) parseInt2))));
            pedometerData.setCalories(Double.parseDouble(decimalFormat.format(((double) Integer.parseInt(ac.m1294a(str, 10, 12), 16)) * Math.pow(10.0d, (double) (Integer.parseInt(ac.m1294a(str, 9, 9), 16) + InputDeviceCompat.SOURCE_ANY)))));
            ac.m1294a(str, 13, 14);
            pedometerData.setExerciseTime(Integer.parseInt(ac.m1294a(str, 13, 14), 16));
            pedometerData.setDistance((double) Integer.parseInt(ac.m1294a(str, 15, 16), 16));
            parseInt2 = Integer.parseInt(ac.m1294a(str, 17, 17), 16);
            pedometerData.setBattery(parseInt2 & 7);
            pedometerData.setSleepStatus((parseInt2 & 24) >>> 3);
            pedometerData.setIntensityLevel((parseInt2 & 224) >>> 5);
            BigDecimal bigDecimal = new BigDecimal(new StringBuilder(String.valueOf(Integer.parseInt(ac.m1294a(str, 18, 18), 16))).toString());
            BigDecimal bigDecimal2 = new BigDecimal("100");
            BigDecimal bigDecimal3 = new BigDecimal("1.6");
            bigDecimal.divide(bigDecimal2, 2, 4);
            return pedometerData;
        } catch (Exception e) {
            return null;
        }
    }

    public static int m1923k(List list) {
        int i = 0;
        if (list != null) {
            int i2 = 0;
            i = ((PedometerData) list.get(0)).getExerciseTime();
            while (i2 < list.size()) {
                int exerciseTime = ((PedometerData) list.get(i2)).getExerciseTime();
                if (exerciseTime <= i) {
                    exerciseTime = i;
                }
                i2++;
                i = exerciseTime;
            }
        }
        return i;
    }

    public static int m1924k(byte[] bArr) {
        return (((16711680 & (bArr[2] << 16)) + 0) + (MotionEventCompat.ACTION_POINTER_INDEX_MASK & (bArr[1] << 8))) + (bArr[0] & 255);
    }

    public static int m1925l(List list) {
        int i = 0;
        if (list != null) {
            int i2 = 0;
            i = ((PedometerData) list.get(0)).getExerciseTime();
            while (i2 < list.size()) {
                int exerciseTime = ((PedometerData) list.get(i2)).getExerciseTime();
                if (exerciseTime >= i) {
                    exerciseTime = i;
                }
                i2++;
                i = exerciseTime;
            }
        }
        return i;
    }

    public static long m1926l(byte[] bArr) {
        return (((-16777216 & (((long) bArr[3]) << 24)) + (16711680 & (((long) bArr[2]) << 16))) + (65280 & (((long) bArr[1]) << 8))) + (255 & ((long) bArr[0]));
    }

    public static float m1927m(byte[] bArr) {
        return (float) (((double) ((float) ((int) C2255a.m1926l(new byte[]{bArr[0], bArr[1], bArr[2], (byte) 0})))) * Math.pow(10.0d, (double) bArr[3]));
    }

    public static List m1928m(List list) {
        List arrayList = new ArrayList();
        while (list.size() > 0) {
            arrayList.add(C2255a.m1930n(list));
        }
        System.out.println("Run.sortSleepAndExerciseInfoList():" + arrayList.size());
        return arrayList;
    }

    public static float m1929n(byte[] bArr) {
        int i = 0;
        System.out.println("高8！" + C2255a.m1874a(bArr[1]));
        System.out.println("低8！" + C2255a.m1874a(bArr[0]));
        int i2 = ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & (bArr[1] << 8)) + 0) + (bArr[0] & 255);
        System.out.println(Integer.toBinaryString(i2));
        int i3 = (32768 & i2) >>> 15;
        int i4 = (61440 & i2) >>> 12;
        int i5 = (i2 & 28672) >>> 12;
        int i6 = (i2 & 2048) >>> 11;
        System.out.println("twelfthBit:" + i6);
        i5 = i2 & 4095;
        i2 &= 2047;
        System.out.println("dishiyiwei:" + Integer.toBinaryString(i2));
        if (i3 == 1) {
            i4 = -(((i4 - 1) ^ -1) & 15);
            System.out.println("zhishu:" + i4);
        } else if (i3 == 0) {
            System.out.println("zhishu:" + 0);
        } else {
            i4 = 0;
        }
        if (i6 == 1) {
            System.out.println("value:" + 0);
            System.out.println("value$:" + i2);
            i = i2 ^ -1;
            System.out.println("value:" + i);
            i &= 2047;
            System.out.println("value:" + i);
            i = -(i + 1);
        } else if (i6 == 0) {
            i = i5;
        }
        float pow = (float) (((double) i) * Math.pow(10.0d, (double) i4));
        System.out.println(pow);
        return pow;
    }

    public static SleepAndExerciseInfo m1930n(List list) {
        int i = 0;
        int i2 = 0;
        SleepAndExerciseInfo sleepAndExerciseInfo = (SleepAndExerciseInfo) list.get(0);
        while (i2 < list.size()) {
            SleepAndExerciseInfo sleepAndExerciseInfo2;
            int i3;
            if (((SleepAndExerciseInfo) list.get(i2)).getStartTimeMillis() < sleepAndExerciseInfo.getStartTimeMillis()) {
                sleepAndExerciseInfo2 = (SleepAndExerciseInfo) list.get(i2);
                i3 = i2;
            } else {
                sleepAndExerciseInfo2 = sleepAndExerciseInfo;
                i3 = i;
            }
            i2++;
            i = i3;
            sleepAndExerciseInfo = sleepAndExerciseInfo2;
        }
        list.remove(i);
        return sleepAndExerciseInfo;
    }

    public static List m1931o(List list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            PedometerData pedometerData = new PedometerData();
            pedometerData = new PedometerData();
            pedometerData = (PedometerData) list.get(i);
            pedometerData.setSleepStatus(0);
            System.out.println("Run.fillData()" + pedometerData.getDate());
            PedometerData pedometerData2 = (PedometerData) list.get(i + 1);
            pedometerData2.setSleepStatus(0);
            System.out.println("Run.fillData()" + pedometerData2.getDate());
            long a = (((C2255a.m1868a(pedometerData2.getDate()) - C2255a.m1868a(pedometerData.getDate())) / 60) / 1000) / 5;
            System.out.println("value:" + a);
            if (a == 1) {
                arrayList.add(pedometerData);
            } else if (a > 1) {
                arrayList.add(pedometerData);
                PedometerData pedometerData3 = pedometerData;
                for (int i2 = 0; ((long) i2) < a - 1; i2++) {
                    pedometerData3 = C2255a.m1869a(pedometerData3);
                    if (!pedometerData3.getDate().equals(pedometerData2.getDate())) {
                        arrayList.add(pedometerData3);
                    }
                }
            }
        }
        arrayList.add((PedometerData) list.get(list.size() - 1));
        return arrayList;
    }

    public static List m1932p(List list) {
        List arrayList = new ArrayList(list.size());
        while (list.size() > 0) {
            arrayList.add(C2255a.m1933q(list));
        }
        return arrayList;
    }

    public static PedometerData m1933q(List list) {
        int i = 0;
        int i2 = 1;
        PedometerData pedometerData = (PedometerData) list.get(0);
        while (i2 < list.size()) {
            PedometerData pedometerData2;
            if (C2255a.m1868a(((PedometerData) list.get(i2)).getDate()) < C2255a.m1868a(pedometerData.getDate())) {
                pedometerData2 = (PedometerData) list.get(i2);
                i = i2;
            } else {
                pedometerData2 = pedometerData;
            }
            i2++;
            pedometerData = pedometerData2;
        }
        list.remove(i);
        return pedometerData;
    }

    public static List m1934r(List list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (C2255a.m1898c(((PedometerData) list.get(i)).getDate())) {
                arrayList.add((PedometerData) list.get(i));
            }
        }
        return arrayList;
    }

    public static List m1935s(List list) {
        List arrayList = new ArrayList();
        for (PedometerData pedometerData : list) {
            PedometerData pedometerData2 = new PedometerData();
            pedometerData2.setBattery(pedometerData.getBattery());
            pedometerData2.setBroadcastId(new String(pedometerData.getBroadcastId()));
            pedometerData2.setCalories(pedometerData.getCalories());
            pedometerData2.setDeviceId(new String(pedometerData.getDeviceId()));
            pedometerData2.setDeviceSn(new String(pedometerData.getDeviceSn()));
            pedometerData2.setExamount(pedometerData.getExamount());
            pedometerData2.setDistance((double) pedometerData.getDistance());
            pedometerData2.setExerciseTime(pedometerData.getExerciseTime());
            pedometerData2.setRunSteps(pedometerData.getRunSteps());
            pedometerData2.setWalkSteps(pedometerData.getWalkSteps());
            pedometerData2.setUserNo(pedometerData.getUserNo());
            pedometerData2.setSleepStatus(pedometerData.getSleepStatus());
            pedometerData2.setDate(new String(pedometerData.getDate()));
            pedometerData2.setIntensityLevel(pedometerData.getIntensityLevel());
            arrayList.add(pedometerData2);
        }
        return arrayList;
    }

    public static int m1936t(List list) {
        int i = 0;
        if (list != null) {
            int runSteps = ((PedometerData) list.get(0)).getRunSteps() + ((PedometerData) list.get(0)).getWalkSteps();
            int i2 = 0;
            i = runSteps;
            while (i2 < list.size()) {
                runSteps = ((PedometerData) list.get(i2)).getRunSteps() + ((PedometerData) list.get(i2)).getWalkSteps();
                if (runSteps <= i) {
                    runSteps = i;
                }
                i2++;
                i = runSteps;
            }
        }
        return i;
    }

    public static int m1937u(List list) {
        int i = 0;
        if (list != null) {
            int runSteps = ((PedometerData) list.get(0)).getRunSteps() + ((PedometerData) list.get(0)).getWalkSteps();
            int i2 = 0;
            i = runSteps;
            while (i2 < list.size()) {
                runSteps = ((PedometerData) list.get(i2)).getRunSteps() + ((PedometerData) list.get(i2)).getWalkSteps();
                if (runSteps >= i) {
                    runSteps = i;
                }
                i2++;
                i = runSteps;
            }
        }
        return i;
    }

    public static PedometerData m1938v(List list) {
        List p = C2255a.m1932p(list);
        return (PedometerData) p.get(p.size() - 1);
    }

    public static void m1939w(List list) {
    }

    private static List m1940x(List list) {
        List arrayList = new ArrayList();
        int size = list.size();
        if (size == 0) {
            return list;
        }
        for (int i = 0; i < size - 1; i++) {
            PedometerData pedometerData = new PedometerData();
            pedometerData = new PedometerData();
            pedometerData = (PedometerData) list.get(i);
            PedometerData pedometerData2 = (PedometerData) list.get(i + 1);
            long a = (((C2255a.m1868a(pedometerData2.getDate()) - C2255a.m1868a(pedometerData.getDate())) / 60) / 1000) / 5;
            System.out.println("value:" + a);
            if (a == 1 || pedometerData.getSleepStatus() == 3) {
                arrayList.add(pedometerData);
            } else if (a > 1 && pedometerData.getSleepStatus() != 3) {
                arrayList.add(pedometerData);
                PedometerData pedometerData3 = pedometerData;
                for (int i2 = 0; ((long) i2) < a - 1; i2++) {
                    pedometerData3 = C2255a.m1869a(pedometerData3);
                    if (!pedometerData3.getDate().equals(pedometerData2.getDate())) {
                        arrayList.add(pedometerData3);
                    }
                }
            }
        }
        arrayList.add((PedometerData) list.get(list.size() - 1));
        return arrayList;
    }

    private static List m1941y(List list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (((PedometerData) list.get(i)).getSleepStatus() != 0) {
                arrayList.add((PedometerData) list.get(i));
            }
        }
        return arrayList;
    }

    private static List m1942z(List list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (((PedometerData) list.get(i)).getSleepStatus() == 0) {
                arrayList.add((PedometerData) list.get(i));
            }
        }
        return arrayList;
    }
}
