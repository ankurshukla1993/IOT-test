package com.lifesense.ble.p003a;

import com.lifesense.ble.ManagerStatus;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.bean.HourSystem;
import com.lifesense.ble.bean.LengthUnit;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerAlarmClock;
import com.lifesense.ble.bean.PedometerUserInfo;
import com.lifesense.ble.bean.VibrationVoice;
import com.lifesense.ble.bean.WeightUserInfo;
import java.util.LinkedList;
import java.util.Queue;

public class C2234v {
    public static final String PROTOCOL_TYPE_A2 = "A2";
    public static final String PROTOCOL_TYPE_A3 = "A3";
    public static final String PROTOCOL_TYPE_A4 = "A4";
    public static final String PROTOCOL_TYPE_GENERIC_FAT = "GENERIC_FAT";
    private static PedometerAlarmClock f2354a;
    private static WeightUserInfo f2355b;
    private static PedometerUserInfo f2356c;
    private static af f2357d;
    private static VibrationVoice f2358e;

    private static Queue m1709a(LsDeviceInfo lsDeviceInfo) {
        if (f2357d == null && f2357d == af.WORKING_FREE) {
            C2220h.m1596a((Object) C2234v.class, "Failed to get ble message queue,for protocol mode(" + f2357d + ")", 1);
            return null;
        } else if (f2357d == af.WORKING_DEVICE_PAIRING_FOR_A2) {
            return C2234v.m1718e(lsDeviceInfo);
        } else {
            if (f2357d == af.WORKING_DATA_UPLOADING_FOR_A2) {
                return C2234v.m1714c(lsDeviceInfo);
            }
            if (f2357d == af.WORKING_DEVICE_PAIRING_FOR_A3) {
                return C2234v.m1719f(lsDeviceInfo);
            }
            if (f2357d == af.WORKING_DATA_UPLOADING_FOR_A3) {
                return C2234v.m1716d(lsDeviceInfo);
            }
            if (f2357d == af.WORKING_DATA_UPLOADING_FOR_GENERIC_FAT) {
                return C2234v.m1712b(lsDeviceInfo);
            }
            if (f2357d == af.WORKING_DEVICE_PAIRING_FOR_A4) {
                return C2234v.m1720g(lsDeviceInfo);
            }
            C2220h.m1596a((Object) C2234v.class, "Failed to get ble message queue,for protocol mode(" + f2357d + ")", 1);
            return null;
        }
    }

    public static Queue m1710a(String str, LsDeviceInfo lsDeviceInfo, PedometerAlarmClock pedometerAlarmClock, PedometerUserInfo pedometerUserInfo, WeightUserInfo weightUserInfo, VibrationVoice vibrationVoice, ManagerStatus managerStatus) {
        if (str == null || str.length() <= 0) {
            C2220h.m1596a((Object) C2234v.class, "Failed to init ble message queue,for null(" + str + ")", 1);
            return null;
        } else if (str.equals(PROTOCOL_TYPE_A2)) {
            if (managerStatus == ManagerStatus.DATA_RECEIVE) {
                f2357d = af.WORKING_DATA_UPLOADING_FOR_A2;
            } else if (managerStatus == ManagerStatus.DEVICE_PAIR) {
                f2357d = af.WORKING_DEVICE_PAIRING_FOR_A2;
            }
            f2354a = pedometerAlarmClock;
            f2356c = pedometerUserInfo;
            f2355b = weightUserInfo;
            f2358e = vibrationVoice;
            return C2234v.m1709a(lsDeviceInfo);
        } else if (str.equals(PROTOCOL_TYPE_A3)) {
            if (managerStatus == ManagerStatus.DATA_RECEIVE) {
                f2357d = af.WORKING_DATA_UPLOADING_FOR_A3;
            } else if (managerStatus == ManagerStatus.DEVICE_PAIR) {
                f2357d = af.WORKING_DEVICE_PAIRING_FOR_A3;
            }
            f2354a = pedometerAlarmClock;
            f2356c = pedometerUserInfo;
            f2355b = weightUserInfo;
            return C2234v.m1709a(lsDeviceInfo);
        } else if (str.equals(PROTOCOL_TYPE_GENERIC_FAT)) {
            f2357d = af.WORKING_DATA_UPLOADING_FOR_GENERIC_FAT;
            return C2234v.m1709a(lsDeviceInfo);
        } else if (str.equals(PROTOCOL_TYPE_A4)) {
            f2357d = af.WORKING_DEVICE_PAIRING_FOR_A4;
            return C2234v.m1709a(lsDeviceInfo);
        } else {
            C2220h.m1596a((Object) C2234v.class, "Failed to init ble message queue,for protocol type(" + str + ")", 1);
            return null;
        }
    }

    private static byte[] m1711a(String str) {
        return str != null ? (!str.equals(DeviceTypeConstants.PEDOMETER) || f2356c == null) ? (!str.equals(DeviceTypeConstants.FAT_SCALE) || f2355b == null) ? (!str.equals(DeviceTypeConstants.WEIGHT_SCALE) || f2355b == null) ? null : f2355b.getBytes() : f2355b.getBytes() : f2356c.getWeekTargetBytes() : null;
    }

    private static Queue m1712b(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null) {
            return null;
        }
        Queue linkedList = new LinkedList();
        ad adVar = new ad(ag.OPERATING_CONNECT_DEVICE, null);
        ad adVar2 = new ad(ag.OPERATING_READ_DEVICE_INFO, null);
        ad adVar3 = new ad(ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS, null);
        ad adVar4 = new ad(ag.OPERATING_WRITE_UTC_TIME, C2213a.m1546b());
        ad adVar5 = new ad(ag.OPERATING_WRITE_DISCONNECT, C2213a.m1548c());
        ad adVar6 = new ad(ag.OPERATING_UPLOADED_RESULTS_PROCESS, null);
        linkedList.add(adVar);
        linkedList.add(adVar2);
        linkedList.add(adVar3);
        linkedList.add(adVar4);
        linkedList.add(adVar5);
        linkedList.add(adVar6);
        return linkedList;
    }

    private static byte[] m1713b(String str) {
        byte[] bArr = null;
        if (str != null && str.length() > 0 && str.equals(DeviceTypeConstants.PEDOMETER) && f2356c != null) {
            C2220h.m1596a((Object) C2234v.class, "write custom pedometer current state ...", 3);
            bArr = new byte[]{(byte) 5, (byte) 0, (byte) 0};
            if (f2356c.getLengthUnit() == LengthUnit.MILE) {
                bArr[2] = (byte) (bArr[2] | 4);
            }
            if (f2356c.getHourSystem() == HourSystem.HOUR_12) {
                bArr[2] = (byte) (bArr[2] | 8);
            }
        }
        return bArr;
    }

    private static Queue m1714c(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null) {
            return null;
        }
        Queue linkedList = new LinkedList();
        ad adVar = new ad(ag.OPERATING_CONNECT_DEVICE, null);
        ad adVar2 = new ad(ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS, null);
        ad adVar3 = new ad(ag.OPERATING_RECEIVE_RANDOM_NUMBER, null);
        ad adVar4 = new ad(ag.OPERATING_WRITE_XOR_RESULTS, null);
        ad adVar5 = new ad(ag.OPERATING_WRITE_UTC_TIME, C2213a.m1546b());
        ad adVar6 = new ad(ag.OPERATING_WRITE_DISCONNECT, C2213a.m1548c());
        ad adVar7 = new ad(ag.OPERATING_UPLOADED_RESULTS_PROCESS, null);
        linkedList.add(adVar);
        linkedList.add(adVar2);
        linkedList.add(adVar3);
        linkedList.add(adVar4);
        linkedList.add(adVar5);
        byte[] a = C2234v.m1711a(lsDeviceInfo.getDeviceType());
        byte[] c = C2234v.m1715c(lsDeviceInfo.getDeviceType());
        byte[] d = C2234v.m1717d(lsDeviceInfo.getDeviceType());
        if (c != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_ALARM_CLOCK, c));
        }
        if (a != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_USER_INFO, a));
        }
        if (d != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_VIBRATION_VOICE, d));
        }
        a = C2234v.m1713b(lsDeviceInfo.getDeviceType());
        if (a != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_CURRENT_STATE_FOR_PEDOMETER_A2, a));
        }
        linkedList.add(adVar6);
        linkedList.add(adVar7);
        return linkedList;
    }

    private static byte[] m1715c(String str) {
        if (str == null || str.length() <= 0 || !str.equals(DeviceTypeConstants.PEDOMETER) || f2354a == null) {
            return null;
        }
        C2220h.m1596a((Object) C2234v.class, "write pedometer alarm clock...", 3);
        return f2354a.getBytes();
    }

    private static Queue m1716d(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null) {
            return null;
        }
        Queue linkedList = new LinkedList();
        ad adVar = new ad(ag.OPERATING_CONNECT_DEVICE, null);
        ad adVar2 = new ad(ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS, null);
        ad adVar3 = new ad(ag.OPERATING_RECEIVE_RANDOM_NUMBER, null);
        ad adVar4 = new ad(ag.OPERATING_WRITE_XOR_RESULTS, null);
        ad adVar5 = new ad(ag.OPERATING_WRITE_UTC_TIME, C2213a.m1546b());
        ad adVar6 = new ad(ag.OPERATING_WRITE_DISCONNECT, C2213a.m1548c());
        ad adVar7 = new ad(ag.OPERATING_UPLOADED_RESULTS_PROCESS, null);
        linkedList.add(adVar);
        linkedList.add(adVar2);
        linkedList.add(adVar3);
        linkedList.add(adVar4);
        byte[] a = C2234v.m1711a(lsDeviceInfo.getDeviceType());
        if (a != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_USER_INFO, a));
        }
        linkedList.add(adVar5);
        linkedList.add(adVar6);
        linkedList.add(adVar7);
        return linkedList;
    }

    private static byte[] m1717d(String str) {
        if (str == null || str.length() <= 0 || !str.equals(DeviceTypeConstants.WEIGHT_SCALE) || f2358e == null) {
            return null;
        }
        C2220h.m1596a((Object) C2234v.class, "write weight scale vibration voice...", 3);
        return f2358e.getBytes();
    }

    private static Queue m1718e(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null) {
            return null;
        }
        Queue linkedList = new LinkedList();
        ad adVar = new ad(ag.OPERATING_CONNECT_DEVICE, null);
        ad adVar2 = new ad(ag.OPERATING_READ_DEVICE_INFO, null);
        ad adVar3 = new ad(ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS, null);
        ad adVar4 = new ad(ag.OPERATING_RECEIVE_PASSWORD, null);
        ad adVar5 = new ad(ag.OPERATING_WRITE_BROADCAST_ID, C2213a.m1542a());
        ad adVar6 = new ad(ag.OPERATING_RECEIVE_RANDOM_NUMBER, null);
        ad adVar7 = new ad(ag.OPERATING_WRITE_XOR_RESULTS, null);
        ad adVar8 = new ad(ag.OPERATING_WRITE_UTC_TIME, C2213a.m1546b());
        ad adVar9 = new ad(ag.OPERATING_WRITE_DISCONNECT, C2213a.m1548c());
        ad adVar10 = new ad(ag.OPERATING_PAIRED_RESULTS_PROCESS, null);
        linkedList.add(adVar);
        linkedList.add(adVar2);
        linkedList.add(adVar3);
        linkedList.add(adVar4);
        linkedList.add(adVar5);
        linkedList.add(adVar6);
        linkedList.add(adVar7);
        linkedList.add(adVar8);
        byte[] a = C2234v.m1711a(lsDeviceInfo.getDeviceType());
        byte[] c = C2234v.m1715c(lsDeviceInfo.getDeviceType());
        byte[] d = C2234v.m1717d(lsDeviceInfo.getDeviceType());
        if (c != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_ALARM_CLOCK, c));
        }
        if (a != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_USER_INFO, a));
        }
        if (d != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_VIBRATION_VOICE, d));
        }
        a = C2234v.m1713b(lsDeviceInfo.getDeviceType());
        if (a != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_CURRENT_STATE_FOR_PEDOMETER_A2, a));
        }
        linkedList.add(adVar9);
        linkedList.add(adVar10);
        return linkedList;
    }

    private static Queue m1719f(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null) {
            return null;
        }
        Queue linkedList = new LinkedList();
        ad adVar = new ad(ag.OPERATING_CONNECT_DEVICE, null);
        ad adVar2 = new ad(ag.OPERATING_READ_DEVICE_INFO, null);
        ad adVar3 = new ad(ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS, null);
        ad adVar4 = new ad(ag.OPERATING_RECEIVE_PASSWORD, null);
        ad adVar5 = new ad(ag.OPERATING_WRITE_BROADCAST_ID, C2213a.m1542a());
        ad adVar6 = new ad(ag.OPERATING_RECEIVE_RANDOM_NUMBER, null);
        ad adVar7 = new ad(ag.OPERATING_WRITE_XOR_RESULTS, null);
        ad adVar8 = new ad(ag.OPERATING_WRITE_BIND_USER_NUMBER, null);
        ad adVar9 = new ad(ag.OPERATING_WRITE_UTC_TIME, C2213a.m1546b());
        ad adVar10 = new ad(ag.OPERATING_WRITE_DISCONNECT, C2213a.m1548c());
        ad adVar11 = new ad(ag.OPERATING_PAIRED_RESULTS_PROCESS, null);
        linkedList.add(adVar);
        linkedList.add(adVar2);
        linkedList.add(adVar3);
        linkedList.add(adVar4);
        linkedList.add(adVar5);
        linkedList.add(adVar6);
        linkedList.add(adVar7);
        linkedList.add(adVar8);
        byte[] a = C2234v.m1711a(lsDeviceInfo.getDeviceType());
        if (a != null) {
            linkedList.add(new ad(ag.OPERATING_WRITE_USER_INFO, a));
        } else {
            a = new byte[15];
            a[0] = (byte) 81;
            a[1] = (byte) 0;
            linkedList.add(new ad(ag.OPERATING_WRITE_USER_INFO, a));
        }
        linkedList.add(adVar9);
        linkedList.add(adVar10);
        linkedList.add(adVar11);
        return linkedList;
    }

    private static Queue m1720g(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null) {
            return null;
        }
        Queue linkedList = new LinkedList();
        ad adVar = new ad(ag.OPERATING_CONNECT_DEVICE, null);
        ad adVar2 = new ad(ag.OPERATING_READ_DEVICE_INFO, null);
        ad adVar3 = new ad(ag.OPERATING_SET_INDICATE_FOR_CHARACTERISTICS, null);
        ad adVar4 = new ad(ag.OPERATING_WRITE_AUTH_RESPONSE, C2213a.m1549d());
        ad adVar5 = new ad(ag.OPERATING_WRITE_INIT_RESPONSE, null);
        ad adVar6 = new ad(ag.OPERATING_WAITING_TO_RECEIVE_DATA, null);
        linkedList.add(adVar);
        linkedList.add(adVar2);
        linkedList.add(adVar3);
        linkedList.add(adVar4);
        linkedList.add(adVar5);
        linkedList.add(adVar6);
        return linkedList;
    }
}
