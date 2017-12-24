package com.lifesense.ble.commom;

import android.annotation.SuppressLint;
import com.lifesense.ble.bean.C2242b;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.p003a.C2234v;
import com.lifesense.ble.p003a.ae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressLint({"UseSparseArrays"})
public class C2256b {
    private static C2256b f2442a;
    private static final List f2443b;
    private static final List f2444c;
    private static final List f2445d;
    private static final List f2446e;
    private static final List f2447f;
    private static final List f2448g;
    private static final Map f2449h;
    private static final Map f2450i;
    private static final Map f2451j;
    private static final Map f2452k;
    private static Map f2453l;
    private static /* synthetic */ int[] f2454m;

    static {
        List arrayList = new ArrayList();
        arrayList.add(C2242b.WEIGHT_SCALE_SERVICE_UUID.toString());
        f2443b = Collections.unmodifiableList(arrayList);
        arrayList = new ArrayList();
        arrayList.add(C2242b.PEDOMETER_SERVICE_UUID.toString());
        arrayList.add(C2242b.PEDOMETER_SERVICE_UUID_A4.toString());
        arrayList.add(C2242b.PEDOMETER_SERVICE_UUID_WECHAT.toString());
        f2444c = Collections.unmodifiableList(arrayList);
        arrayList = new ArrayList();
        arrayList.add(C2242b.HEIGHT_SERVICE_UUID.toString());
        f2445d = Collections.unmodifiableList(arrayList);
        arrayList = new ArrayList();
        arrayList.add(C2242b.BLOOD_PRESSURE_SERVICE_UUID.toString());
        arrayList.add(C2242b.BLOOD_PRESSURE_A3_SERVICE_UUID.toString());
        f2446e = Collections.unmodifiableList(arrayList);
        arrayList = new ArrayList();
        arrayList.add(C2242b.FAT_SCALE_A3_SERVICE_UUID.toString());
        f2447f = Collections.unmodifiableList(arrayList);
        arrayList = new ArrayList();
        arrayList.add(C2242b.KITCHEN_SCALE_SERVICE_UUID.toString());
        f2448g = Collections.unmodifiableList(arrayList);
        Map hashMap = new HashMap();
        hashMap.put(DeviceType.UNKNOWN, DeviceTypeConstants.UNKNOW);
        hashMap.put(DeviceType.WEIGHT_SCALE, DeviceTypeConstants.WEIGHT_SCALE);
        hashMap.put(DeviceType.PEDOMETER, DeviceTypeConstants.PEDOMETER);
        hashMap.put(DeviceType.SPHYGMOMANOMETER, DeviceTypeConstants.SPHYGMOMAN_METER);
        hashMap.put(DeviceType.HEIGHT_RULER, DeviceTypeConstants.HEIGHT_RULER);
        hashMap.put(DeviceType.FAT_SCALE, DeviceTypeConstants.FAT_SCALE);
        f2449h = Collections.unmodifiableMap(hashMap);
        hashMap = new HashMap();
        hashMap.put(DeviceTypeConstants.UNKNOW, DeviceType.UNKNOWN);
        hashMap.put(DeviceTypeConstants.WEIGHT_SCALE, DeviceType.WEIGHT_SCALE);
        hashMap.put(DeviceTypeConstants.PEDOMETER, DeviceType.PEDOMETER);
        hashMap.put(DeviceTypeConstants.SPHYGMOMAN_METER, DeviceType.SPHYGMOMANOMETER);
        hashMap.put(DeviceTypeConstants.HEIGHT_RULER, DeviceType.HEIGHT_RULER);
        hashMap.put(DeviceTypeConstants.FAT_SCALE, DeviceType.FAT_SCALE);
        f2450i = Collections.unmodifiableMap(hashMap);
        hashMap = new HashMap();
        hashMap.put(DeviceTypeConstants.WEIGHT_SCALE, f2443b);
        hashMap.put(DeviceTypeConstants.SPHYGMOMAN_METER, f2446e);
        hashMap.put(DeviceTypeConstants.HEIGHT_RULER, f2445d);
        hashMap.put(DeviceTypeConstants.PEDOMETER, f2444c);
        hashMap.put(DeviceTypeConstants.FAT_SCALE, f2447f);
        f2451j = Collections.unmodifiableMap(hashMap);
        hashMap = new HashMap();
        hashMap.put(C2242b.WEIGHT_SCALE_SERVICE_UUID.toString(), DeviceTypeConstants.WEIGHT_SCALE);
        hashMap.put(C2242b.BLOOD_PRESSURE_SERVICE_UUID.toString(), DeviceTypeConstants.SPHYGMOMAN_METER);
        hashMap.put(C2242b.HEIGHT_SERVICE_UUID.toString(), DeviceTypeConstants.HEIGHT_RULER);
        hashMap.put(C2242b.PEDOMETER_SERVICE_UUID.toString(), DeviceTypeConstants.PEDOMETER);
        hashMap.put(C2242b.PEDOMETER_SERVICE_UUID_A4.toString(), DeviceTypeConstants.PEDOMETER);
        hashMap.put(C2242b.BLOOD_PRESSURE_A3_SERVICE_UUID.toString(), DeviceTypeConstants.SPHYGMOMAN_METER);
        hashMap.put(C2242b.FAT_SCALE_A3_SERVICE_UUID.toString(), DeviceTypeConstants.FAT_SCALE);
        f2452k = Collections.unmodifiableMap(hashMap);
        hashMap = new HashMap();
        hashMap.put("406A0", "LS406-B");
        hashMap.put("402A0", "LS402-B");
        hashMap.put("402A1", "LS402-B");
        hashMap.put("401A0", "LS401-B");
        hashMap.put("405A0", "LS405-B");
        hashMap.put("1014B", "TMB-1014-BT");
        hashMap.put("810A0", "LS810-B/TENSIO");
        hashMap.put("802A0", "LS802-B");
        hashMap.put("805A0", "LS805-B");
        hashMap.put("1018B", "TMB-1018-BT");
        hashMap.put("808A0", "LS808-B");
        hashMap.put("13950", "BU 550 connect");
        hashMap.put("13930", "BU 575 connect");
        hashMap.put("13960", "BW 300 connect");
        hashMap.put("10140", "vs-4300-w");
        hashMap.put("810A1", "LS810-B");
        hashMap.put("802A1", "vs-4400");
        hashMap.put("805A1", "vs-4000");
        hashMap.put("10180", "TMB-1018-BT");
        hashMap.put("10141", "vs-4300-b");
        hashMap.put("802A2", "LS802-B");
        hashMap.put("10181", "TMB-1018-BT");
        hashMap.put("10142", "TMB-1014-BT");
        hashMap.put("802A3", "LS802-B");
        hashMap.put("10182", "TMB-1018-BT");
        hashMap.put("10143", "RWBPM01");
        hashMap.put("802A4", "TENSIO SCREEN");
        hashMap.put("10144", "TMB-1014-BT");
        hashMap.put("10145", "TMB-1014-BT");
        hashMap.put("10146", "BPW-9154");
        hashMap.put("10147", "TMB-1014-BT");
        hashMap.put("10148", "TMB-1014-BT");
        hashMap.put("10149", "TMB-1014-BT");
        hashMap.put("1014A", "TMB-1014-BT");
        hashMap.put("1014C", "TMB-1014-BT");
        hashMap.put("995A0", "TMB995(BT4.0)");
        hashMap.put("102B ", "LS102-B");
        hashMap.put("103B ", "LS103-B");
        hashMap.put("106A0", "BS-705-BT");
        hashMap.put("12660", "WEB COACH One");
        hashMap.put("102B1", "LS102-B");
        hashMap.put("12661", "WEB COACH One");
        hashMap.put("12662", "WEB COACH One");
        hashMap.put("101A0", "LS101-B");
        hashMap.put("12300", C2234v.PROTOCOL_TYPE_A2);
        hashMap.put("12690", "B1");
        hashMap.put("922A0", "S1-B");
        hashMap.put("202B ", "LS202-B");
        hashMap.put("203B ", "LS203-B");
        hashMap.put("102B0", "LS102-B");
        hashMap.put("12301", C2234v.PROTOCOL_TYPE_A2);
        hashMap.put("106A1", "BS-705-B");
        hashMap.put("922A1", "S1-B");
        hashMap.put("1251B", "GBF-1251-B");
        hashMap.put("1255B", "BF-1255-B");
        hashMap.put("1256B", "BF-1256-B");
        hashMap.put("1257B", "GBF-1257-B");
        hashMap.put("12571", "GBF-1257-B");
        hashMap.put("1144B", "GBF-1144-B");
        hashMap.put("12670", "WEB COACH");
        hashMap.put("13190", "7222F");
        hashMap.put("202B ", "LS202-B");
        hashMap.put("203B ", "LS203-B");
        hashMap.put("202B5", "202");
        hashMap.put("203B0", "vs-3200-w");
        hashMap.put("12510", "GBF-1251-B");
        hashMap.put("12550", "vs-3100");
        hashMap.put("12560", "BF-1256-B");
        hashMap.put("12570", "GBF-1257-B");
        hashMap.put("12671", "WEB COACH");
        hashMap.put("13191", "7224FBOW");
        hashMap.put("202B6", "BS 440 connect");
        hashMap.put("203B1", "vs-3200-b");
        hashMap.put("13192", "SC-902");
        hashMap.put("203B2", "9154 BK3R");
        hashMap.put("203B3", "9154 WH3R");
        hashMap.put("203B4", "LS203-B");
        hashMap.put("202B0", "LS202-B");
        hashMap.put("1136B", "GKS-1136-BT");
        hashMap.put("305A0", "GKS-1136-BT");
        f2453l = Collections.unmodifiableMap(hashMap);
    }

    private C2256b() {
    }

    public static C2256b m1943a() {
        if (f2442a != null) {
            return f2442a;
        }
        C2256b c2256b = new C2256b();
        f2442a = c2256b;
        return c2256b;
    }

    static /* synthetic */ int[] m1944b() {
        int[] iArr = f2454m;
        if (iArr == null) {
            iArr = new int[ae.values().length];
            try {
                iArr[ae.A2.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ae.A3.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ae.A4.ordinal()] = 6;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ae.GENERIC_FAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ae.GLUCOSE_METER_PROTOCOL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ae.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            f2454m = iArr;
        }
        return iArr;
    }

    public ae m1945a(UUID uuid) {
        return (uuid == null || uuid.toString().length() <= 0) ? ae.UNKNOWN : m1948a(ae.A2).contains(uuid.toString()) ? ae.A2 : m1948a(ae.GLUCOSE_METER_PROTOCOL).contains(uuid.toString()) ? ae.GLUCOSE_METER_PROTOCOL : m1948a(ae.A3).contains(uuid.toString()) ? ae.A3 : m1948a(ae.GENERIC_FAT).contains(uuid.toString()) ? ae.GENERIC_FAT : m1948a(ae.A4).contains(uuid.toString()) ? ae.A4 : ae.UNKNOWN;
    }

    public String m1946a(DeviceType deviceType) {
        return (deviceType == null || !f2449h.containsKey(deviceType)) ? DeviceTypeConstants.UNKNOW : (String) f2449h.get(deviceType);
    }

    public String m1947a(String str) {
        return (str == null || !f2452k.containsKey(str)) ? DeviceTypeConstants.UNKNOW : (String) f2452k.get(str);
    }

    public List m1948a(ae aeVar) {
        List arrayList;
        switch (C2256b.m1944b()[aeVar.ordinal()]) {
            case 2:
                arrayList = new ArrayList();
                arrayList.add(C2242b.PEDOMETER_SERVICE_UUID.toString());
                arrayList.add(C2242b.WEIGHT_SCALE_SERVICE_UUID.toString());
                arrayList.add(C2242b.BLOOD_PRESSURE_SERVICE_UUID.toString());
                arrayList.add(C2242b.HEIGHT_SERVICE_UUID.toString());
                arrayList.add(C2242b.KITCHEN_SCALE_SERVICE_UUID.toString());
                return arrayList;
            case 3:
                arrayList = new ArrayList();
                arrayList.add(C2242b.BLOOD_PRESSURE_A3_SERVICE_UUID.toString());
                arrayList.add(C2242b.FAT_SCALE_A3_SERVICE_UUID.toString());
                return arrayList;
            case 4:
                arrayList = new ArrayList();
                arrayList.add(C2242b.GENERIC_FAT_SCALE_SERVICE_UUID.toString());
                return arrayList;
            case 5:
                arrayList = new ArrayList();
                arrayList.add(C2242b.GLUCOSE_SEVICE_UUID.toString());
                return arrayList;
            case 6:
                arrayList = new ArrayList();
                arrayList.add(C2242b.PEDOMETER_SERVICE_UUID_A4.toString());
                return arrayList;
            default:
                return null;
        }
    }

    public List m1949b(String str) {
        return (str == null || !f2451j.containsKey(str)) ? null : (List) f2451j.get(str);
    }

    public DeviceType m1950c(String str) {
        return (str == null || !f2450i.containsKey(str)) ? null : (DeviceType) f2450i.get(str);
    }

    public String m1951d(String str) {
        return (str == null || !f2453l.containsKey(str)) ? null : (String) f2453l.get(str);
    }
}
