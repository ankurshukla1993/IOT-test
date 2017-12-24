package com.lifesense.ble.p003a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lifesense.ble.DeviceConnectState;
import com.lifesense.ble.ManagerStatus;
import com.lifesense.ble.bean.C2242b;
import com.lifesense.ble.bean.C2248h;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.bean.DeviceUserInfo;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerAlarmClock;
import com.lifesense.ble.bean.PedometerUserInfo;
import com.lifesense.ble.bean.VibrationVoice;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.commom.C2255a;
import com.lifesense.ble.p003a.p004a.C2198k;
import com.lifesense.ble.p003a.p004a.C2200m;
import com.lifesense.ble.p003a.p004a.C2206s;
import com.lifesense.ble.p003a.p004a.C2210w;
import com.lifesense.ble.p003a.p004a.C2212y;
import com.lifesense.ble.p003a.p004a.aa;
import com.lifesense.ble.p003a.p004a.ab;
import com.lifesense.ble.p003a.p004a.ac;
import humanize.util.Constants;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@SuppressLint({"DefaultLocale"})
public class C2235w {
    private static /* synthetic */ int[] f2359R;
    private Queue f2360A;
    private ad f2361B;
    private int f2362C;
    private int f2363D;
    private boolean f2364E;
    private Timer f2365F;
    private TimerTask f2366G;
    private int f2367H;
    private StringBuffer f2368I;
    private List f2369J;
    private String f2370K = "";
    private boolean f2371L;
    private boolean f2372M;
    private boolean f2373N;
    private boolean f2374O;
    private Context f2375P;
    private C2222j f2376Q = new C2236x(this);
    private ac f2377a;
    private C2214b f2378b;
    private C2224l f2379c;
    private List f2380d;
    private List f2381e;
    private int f2382f;
    private String f2383g;
    private int f2384h;
    private byte[] f2385i;
    private byte[] f2386j;
    private String f2387k;
    private UUID f2388l;
    private int f2389m;
    private LsDeviceInfo f2390n;
    private C2248h f2391o;
    private Thread f2392p;
    private int f2393q;
    private boolean f2394r;
    private boolean f2395s;
    private ae f2396t;
    private PedometerAlarmClock f2397u;
    private WeightUserInfo f2398v;
    private PedometerUserInfo f2399w;
    private VibrationVoice f2400x;
    private ag f2401y;
    private Queue f2402z;

    private void m1722a(ManagerStatus managerStatus) {
        if (this.f2379c != null) {
            this.f2379c.mo5583a(managerStatus);
        }
    }

    private void m1723a(ag agVar) {
        byte[] a;
        switch (C2235w.m1770g()[agVar.ordinal()]) {
            case 2:
                this.f2378b.m1585a(this.f2387k);
                return;
            case 3:
                if (m1778k()) {
                    this.f2393q = 0;
                    m1790q();
                    return;
                }
                m1780l();
                return;
            case 4:
                this.f2390n.setPassword(C2221i.m1613d(this.f2385i));
                m1723a(m1773i());
                return;
            case 5:
                this.f2392p.start();
                return;
            case 6:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return;
            case 7:
                this.f2390n.setBroadcastID(C2221i.m1613d(new byte[]{a[1], a[2], a[3], C2213a.m1542a()[4]}));
                m1739a(a);
                return;
            case 8:
                if (this.f2379c != null) {
                    this.f2379c.mo5582a(DeviceConnectState.CONNECTED_SUCCESS);
                }
                m1723a(m1773i());
                return;
            case 9:
                if (this.f2390n.getPassword() == null || this.f2386j == null) {
                    m1780l();
                    return;
                } else {
                    m1739a(C2213a.m1545a(this.f2390n.getPassword(), this.f2386j));
                    return;
                }
            case 10:
                m1739a(C2213a.m1544a(this.f2382f, this.f2383g));
                return;
            case 11:
                m1739a(C2213a.m1543a(this.f2384h));
                return;
            case 12:
                a = this.f2361B.m1554a();
                if (this.f2377a == ac.PAIR_DEVICE_STATUS && a != null && a.length > 3 && this.f2382f != 0) {
                    a[2] = (byte) this.f2382f;
                }
                m1739a(a);
                return;
            case 13:
                m1739a(this.f2361B.m1554a());
                return;
            case 14:
                m1739a(this.f2361B.m1554a());
                return;
            case 15:
                m1739a(this.f2361B.m1554a());
                return;
            case 16:
                C2220h.m1596a((Object) this, "Done!the pairing is successful.", 1);
                this.f2378b.m1593e();
                this.f2390n.setDeviceUserNumber(this.f2382f);
                m1734a(this.f2390n, C2225m.PAIR_SUCCESSFULLY);
                return;
            case 17:
                this.f2378b.m1593e();
                m1724a(C2227o.UPLOAD_SUCCESSFULLY);
                return;
            case 18:
                m1785o();
                return;
            case 19:
                m1739a(this.f2361B.m1554a());
                return;
            case 20:
                this.f2364E = false;
                m1735a(C2213a.m1550e());
                return;
            case 21:
                C2220h.m1596a((Object) this, "waiting to receive the measure data ...", 2);
                return;
            case 30:
                m1739a(this.f2361B.m1554a());
                return;
            case 31:
                m1739a(this.f2361B.m1554a());
                return;
            default:
                m1780l();
                return;
        }
    }

    private void m1724a(C2227o c2227o) {
        m1722a(ManagerStatus.FREE);
        this.f2401y = ag.OPERATING_FREE;
        this.f2377a = ac.FREE_STATUS;
        this.f2399w = null;
        this.f2397u = null;
        this.f2398v = null;
        if (this.f2379c != null) {
            this.f2379c.mo5584a(c2227o, this.f2396t);
        }
    }

    private void m1734a(LsDeviceInfo lsDeviceInfo, C2225m c2225m) {
        m1722a(ManagerStatus.FREE);
        this.f2401y = ag.OPERATING_FREE;
        this.f2377a = ac.FREE_STATUS;
        this.f2399w = null;
        this.f2397u = null;
        this.f2398v = null;
        if (this.f2379c != null) {
            this.f2379c.mo5586a(lsDeviceInfo, c2225m);
        }
    }

    private void m1735a(String str) {
        this.f2369J.clear();
        int length = str.length() / 40;
        int length2 = str.length() % 40;
        for (int i = 0; i < length; i++) {
            this.f2369J.add(str.substring(i * 40, (i * 40) + 40));
        }
        if (length2 > 0) {
            this.f2369J.add(str.substring(length * 40, str.length()));
        }
        m1795t();
    }

    private void m1736a(String str, String str2) {
        if (this.f2379c != null) {
            this.f2379c.mo5587a(this.f2390n, str, null, "CE");
        }
    }

    private void m1737a(String str, boolean z, String str2) {
        C2200m g = C2198k.m1361g();
        g.m1377a(1);
        C2198k d = g.m1383d();
        C2212y g2 = C2210w.m1509g();
        g2.m1529a(d);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2);
            if (z) {
                stringBuffer.append(DeviceTypeConstants.WEIGHT_SCALE);
            } else {
                stringBuffer.append(DeviceTypeConstants.UNKNOW);
            }
            g2.m1526a(ByteString.copyFrom(stringBuffer.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        m1735a(C2213a.m1541a(str, g2.m1534d().toByteArray(), C2213a.ECI_resp_sendDataToManufacturerSvr));
    }

    private void m1738a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (!uuid.equals(C2242b.DEVICEINFO_SERVICE_UUID)) {
            return;
        }
        if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_MANUFACTURER_CHARACTERISTIC_UUID)) {
            this.f2390n.setManufactureName(C2221i.m1612c(bArr));
            C2220h.m1596a((Object) this, "Device information-ManufactureName-" + C2221i.m1612c(bArr), 2);
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_MODEL_CHARACTERISTIC_UUID)) {
            if (C2221i.m1612c(bArr) != null && C2221i.m1612c(bArr).length() > 0) {
                this.f2390n.setModelNumber(C2221i.m1612c(bArr));
                C2220h.m1596a((Object) this, "Device information-ModelNumber-" + C2221i.m1612c(bArr), 2);
            }
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_SERIAL_NUMBER_CHARACTERISTIC_UUID)) {
            String protocolType = this.f2390n.getProtocolType();
            if (protocolType != null && !protocolType.equals(ae.A4.toString())) {
                this.f2390n.setDeviceId(C2255a.m1907e(C2221i.m1612c(bArr)));
                this.f2390n.setDeviceSn(C2255a.m1910f(this.f2390n.getDeviceId()));
                C2220h.m1596a((Object) this, "Device information-DeviceId-" + this.f2390n.getDeviceId() + ";DeviceSn-" + this.f2390n.getDeviceSn(), 2);
            }
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_HARDWARE_REVISION_CHARACTERISTIC_UUID)) {
            this.f2390n.setHardwareVersion(C2221i.m1612c(bArr));
            C2220h.m1596a((Object) this, "Device information-HardwareVersion-" + C2221i.m1612c(bArr), 2);
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_FIRMWARE_REVISION_CHARACTERISTIC_UUID)) {
            this.f2390n.setFirmwareVersion(C2221i.m1612c(bArr));
            this.f2389m = C2221i.m1601a(this.f2390n.getFirmwareVersion(), this.f2390n.getDeviceType());
            this.f2390n.setMaxUserQuantity(this.f2389m);
            C2220h.m1596a((Object) this, "Device information-FirmwareVersion-" + C2221i.m1612c(bArr), 2);
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_SOFTWARE_REVISION_CHARACTERISTIC_UUID)) {
            this.f2390n.setSoftwareVersion(C2221i.m1612c(bArr));
            C2220h.m1596a((Object) this, "Device information-SoftwareVersion-" + C2221i.m1612c(bArr), 2);
        }
    }

    private void m1739a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            m1776j();
        } else if (this.f2378b != null && this.f2388l != null) {
            if (!(this.f2396t == ae.A4 ? this.f2378b.m1589a(this.f2388l, C2242b.PEDOMETER_WRITE_CHARACTERISTIC_UUID_A4, bArr) : this.f2378b.m1589a(this.f2388l, C2242b.DOWNLOAD_INFORMATION_OR_COMMAND_CHARACTERISTIC_UUID, bArr))) {
                m1776j();
            }
        }
    }

    private boolean m1740a(int i) {
        if (this.f2381e == null || this.f2381e.size() <= 0 || i > this.f2381e.size()) {
            return false;
        }
        for (DeviceUserInfo userNumber : this.f2381e) {
            if (userNumber.getUserNumber() == i) {
                return true;
            }
        }
        return false;
    }

    private void m1742b(ag agVar) {
        if (this.f2372M || this.f2374O) {
            C2220h.m1596a((Object) this, "Enable to set up long connection with device A4.....", 1);
            return;
        }
        C2220h.m1596a((Object) this, "Disable to set up long connection with device A4,init auto disconnect timer.....", 1);
        this.f2372M = true;
        new Timer().schedule(new ab(this, agVar), (long) AbstractSpiCall.DEFAULT_TIMEOUT);
    }

    private void m1747b(String str) {
        String replace = str.replace(Constants.SPACE, "");
        if (replace.indexOf("FE01") != 0) {
            replace = replace.substring(replace.indexOf("FE01"), replace.length());
        }
        String substring = replace.substring(16, ((Integer.valueOf(replace.substring(4, 8), 16).intValue() - 8) * 2) + 16);
        int intValue = Integer.valueOf(replace.substring(8, 12), 16).intValue();
        this.f2370K = replace.substring(12, 16);
        byte[] a = ac.m1297a(substring);
        if (intValue == C2213a.ECI_req_auth) {
            C2220h.m1596a((Object) this, "receive auth request from device A4,sequenceNumber is (" + this.f2370K + ")", 3);
            m1723a(m1773i());
        } else if (intValue == C2213a.ECI_req_init) {
            C2220h.m1596a((Object) this, "receive init request from device A4,sequenceNumber is (" + this.f2370K + ")", 3);
            m1723a(m1773i());
        } else if (intValue == C2213a.ECI_req_sendDataToManufacturerSvr) {
            replace = m1768f(a);
            if (replace != null) {
                substring = replace.substring(0, 2);
                C2220h.m1596a((Object) this, "current request command id is:(0x" + substring + ")", 2);
                if (substring.equalsIgnoreCase("C7")) {
                    m1761d(replace);
                    if (this.f2399w != null) {
                        C2220h.m1596a((Object) this, "write custom pedometer user info to device a4..." + this.f2399w.toString(), 3);
                        replace = C2213a.m1539a(this.f2370K, this.f2399w.getWeight(), this.f2399w.getHeight(), this.f2399w.getWeekStart(), 0, false);
                    } else {
                        C2220h.m1596a((Object) this, "write default pedometer user info to device a4...", 3);
                        replace = C2213a.m1539a(this.f2370K, 50.0f, 160.0f, 42000, 0, false);
                    }
                    this.f2401y = ag.OPERATING_WRITE_C7_COMMAND_TO_DEVICE;
                    m1735a(replace);
                    return;
                } else if (substring.equalsIgnoreCase("C4")) {
                    this.f2371L = true;
                    Log.i("C4============", "C4发送计步器测量数据到服务器");
                    m1756c(replace);
                    this.f2401y = ag.OPERATING_WRITE_C4_COMMAND_TO_DEVICE;
                    m1737a(this.f2370K, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CA")) {
                    this.f2371L = true;
                    Log.i("CA============", "CA发送计步器每天的测量数据到服务器");
                    m1757c(replace, substring);
                    this.f2401y = ag.OPERATING_WRITE_CA_COMMAND_TO_DEVICE;
                    m1737a(this.f2370K, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("C9")) {
                    this.f2371L = true;
                    Log.i("C9============", "C9发送计步器详细的测量数据到服务器");
                    m1762d(replace, substring);
                    this.f2401y = ag.OPERATING_WRITE_C9_COMMAND_TO_DEVICE;
                    m1737a(this.f2370K, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CB")) {
                    this.f2401y = ag.OPERATING_WRITE_CB_COMMAND_TO_DEVICE;
                    m1748b(replace, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CE")) {
                    Log.e("CE命令==============", "CE命令发送睡眠状态信息");
                    this.f2371L = true;
                    m1736a(replace, substring);
                    this.f2401y = ag.OPERATING_WRITE_CE_COMMAND_TO_DEVICE;
                    m1737a(this.f2370K, true, substring);
                    return;
                } else {
                    this.f2371L = false;
                    C2220h.m1596a((Object) this, "Failed to parse the command request...", 2);
                    return;
                }
            }
            C2220h.m1596a((Object) this, "Failed to parse packet contents...", 1);
        }
    }

    private void m1748b(String str, String str2) {
        C2220h.m1596a((Object) this, "receive command request(0xCB)..." + new ab(str).toString(), 3);
        m1737a(this.f2370K, true, str2);
    }

    private void m1749b(UUID uuid, UUID uuid2, byte[] bArr) {
        if (this.f2379c != null) {
            this.f2379c.mo5588a(this.f2390n, bArr, uuid2);
        }
    }

    private void m1750b(byte[] bArr) {
        this.f2386j[0] = bArr[1];
        this.f2386j[1] = bArr[2];
        this.f2386j[2] = bArr[3];
        this.f2386j[3] = bArr[4];
        if (this.f2377a == ac.PAIR_DEVICE_STATUS && this.f2401y == ag.OPERATING_WRITE_BROADCAST_ID) {
            this.f2395s = true;
            if (this.f2394r && this.f2395s) {
                m1723a(m1773i());
            }
        } else if (this.f2377a == ac.DATA_UPLOAD_STATUS && this.f2401y == ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS) {
            m1723a(m1773i());
        }
    }

    private boolean m1751b(int i, String str) {
        if (i == -1 || i == 0 || !m1740a(i)) {
            C2220h.m1596a((Object) this, "Error,userNumber is inValid:" + i, 1);
            return false;
        } else if (str != null && str.length() <= 16) {
            return true;
        } else {
            C2220h.m1596a((Object) this, "Error,userName is inValid:" + str, 1);
            return false;
        }
    }

    private void m1756c(String str) {
        String substring = str.substring(2, str.length());
        int length = substring.length() / 36;
        for (int i = 0; i < length; i++) {
            C2220h.m1596a((Object) this, "receive pedometer measure data from (0xC4)...", 2);
            if (this.f2379c != null) {
                this.f2379c.mo5587a(this.f2390n, substring, null, "C4");
            }
        }
    }

    private void m1757c(String str, String str2) {
        String substring = str.substring(2, str.length());
        int length = substring.length() / 38;
        for (int i = 0; i < length; i++) {
            String substring2 = substring.substring(i * 38, (i * 38) + 38);
            C2220h.m1596a((Object) this, "receive pedometer every day measure data from(0xCA)...", 2);
            if (this.f2379c != null) {
                this.f2379c.mo5587a(this.f2390n, substring2, null, "CA");
            }
        }
    }

    private void m1758c(byte[] bArr) {
        if (this.f2385i != null && bArr != null) {
            if (this.f2377a == ac.PAIR_DEVICE_STATUS && this.f2401y == ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS) {
                this.f2385i[0] = bArr[1];
                this.f2385i[1] = bArr[2];
                this.f2385i[2] = bArr[3];
                this.f2385i[3] = bArr[4];
                m1723a(m1773i());
                return;
            }
            m1780l();
        }
    }

    private void m1761d(String str) {
        Log.i("ProtobufData", "cmdPedometerC7=" + new aa(str).toString());
    }

    private void m1762d(String str, String str2) {
        String substring = str.substring(2, str.length());
        int length = substring.length() / 46;
        for (int i = 0; i < length; i++) {
            String substring2 = substring.substring(i * 46, (i * 46) + 46);
            C2220h.m1596a((Object) this, "receive pedometer every day measure data from(0xC9)...", 2);
            if (this.f2379c != null) {
                this.f2379c.mo5587a(this.f2390n, substring2, null, "C9");
            }
        }
    }

    private void m1763d(byte[] bArr) {
        DeviceUserInfo deviceUserInfo = new DeviceUserInfo(C2255a.m1912g(bArr), C2255a.m1916h(bArr));
        deviceUserInfo.setDeviceId(this.f2390n.getDeviceId());
        this.f2381e.add(deviceUserInfo);
        if (this.f2381e.size() == this.f2389m && this.f2381e.size() < 9 && this.f2379c != null) {
            this.f2379c.mo5591a(this.f2381e);
        }
    }

    private void m1766e(byte[] bArr) {
        StringBuilder stringBuilder = null;
        if (bArr != null && bArr.length > 0) {
            stringBuilder = new StringBuilder(bArr.length);
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02X ", new Object[]{Byte.valueOf(bArr[i])}));
            }
        }
        String replace = stringBuilder.toString().replace(Constants.SPACE, "");
        if (replace.contains("FE01") || replace.contains(C2213a.HEADER)) {
            this.f2363D = Integer.valueOf(replace.substring(4, 8), 16).intValue();
            this.f2368I.replace(0, this.f2368I.length(), "");
        }
        if (this.f2368I.length() < this.f2363D * 2) {
            this.f2368I.append(replace);
        }
        if (this.f2368I.length() >= this.f2363D * 2 && this.f2368I.length() < (this.f2363D * 2) + 40) {
            m1747b(this.f2368I.toString());
        }
    }

    private String m1768f(byte[] bArr) {
        C2206s a;
        try {
            a = C2206s.m1467a(bArr);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            a = null;
        }
        if (a != null && a.m1481f() != null) {
            return a.m1481f().toStringUtf8();
        }
        C2220h.m1596a((Object) this, "Failed to parse packet contents,for null(" + a + ")", 1);
        return null;
    }

    static /* synthetic */ int[] m1770g() {
        int[] iArr = f2359R;
        if (iArr == null) {
            iArr = new int[ag.values().length];
            try {
                iArr[ag.OPERATING_CONNECT_DEVICE.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ag.OPERATING_FREE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ag.OPERATING_PAIRED_RESULTS_PROCESS.ordinal()] = 16;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ag.OPERATING_READ_DEVICE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ag.OPERATING_RECEIVE_PASSWORD.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ag.OPERATING_RECEIVE_RANDOM_NUMBER.ordinal()] = 8;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ag.OPERATING_SET_INDICATE_FOR_CHARACTERISTICS.ordinal()] = 18;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ag.OPERATING_SET_NOTIFY_FOR_DESCRIPTOR.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ag.OPERATING_UPLOADED_RESULTS_PROCESS.ordinal()] = 17;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ag.OPERATING_WAITING_TO_RECEIVE_DATA.ordinal()] = 21;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[ag.OPERATING_WRITE_ALARM_CLOCK.ordinal()] = 13;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[ag.OPERATING_WRITE_AUTH_RESPONSE.ordinal()] = 19;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[ag.OPERATING_WRITE_BIND_USER_NUMBER.ordinal()] = 10;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[ag.OPERATING_WRITE_BROADCAST_ID.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[ag.OPERATING_WRITE_C3_COMMAND_TO_DEVICE.ordinal()] = 29;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[ag.OPERATING_WRITE_C4_COMMAND_TO_DEVICE.ordinal()] = 23;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[ag.OPERATING_WRITE_C7_COMMAND_TO_DEVICE.ordinal()] = 22;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[ag.OPERATING_WRITE_C9_COMMAND_TO_DEVICE.ordinal()] = 24;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[ag.OPERATING_WRITE_CA_COMMAND_TO_DEVICE.ordinal()] = 25;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[ag.OPERATING_WRITE_CB_COMMAND_TO_DEVICE.ordinal()] = 26;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[ag.OPERATING_WRITE_CC_COMMAND_TO_DEVICE.ordinal()] = 28;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[ag.OPERATING_WRITE_CE_COMMAND_TO_DEVICE.ordinal()] = 27;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[ag.OPERATING_WRITE_CURRENT_STATE_FOR_PEDOMETER_A2.ordinal()] = 31;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[ag.OPERATING_WRITE_DISCONNECT.ordinal()] = 15;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[ag.OPERATING_WRITE_INIT_RESPONSE.ordinal()] = 20;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[ag.OPERATING_WRITE_UNBIND_USER_NUMBER.ordinal()] = 11;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[ag.OPERATING_WRITE_USER_INFO.ordinal()] = 12;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[ag.OPERATING_WRITE_UTC_TIME.ordinal()] = 14;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[ag.OPERATING_WRITE_VIBRATION_VOICE.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[ag.OPERATING_WRITE_XOR_RESULTS.ordinal()] = 9;
            } catch (NoSuchFieldError e31) {
            }
            f2359R = iArr;
        }
        return iArr;
    }

    private void m1771h() {
        if (this.f2401y == ag.OPERATING_WRITE_BROADCAST_ID) {
            this.f2394r = true;
            if (this.f2395s && this.f2394r) {
                m1723a(m1773i());
            }
        } else if (this.f2401y == ag.OPERATING_WRITE_XOR_RESULTS) {
            ag i = m1773i();
            if (i == ag.OPERATING_WRITE_BIND_USER_NUMBER) {
                C2220h.m1596a((Object) this, "Pro(A3)-Pair,service uuid-" + this.f2388l.toString().substring(4, 8), 3);
                this.f2401y = i;
                return;
            }
            C2220h.m1596a((Object) this, "Pro(A2)-Pair,service uuid-" + this.f2388l.toString().substring(4, 8), 3);
            m1723a(i);
        } else if (this.f2401y == ag.OPERATING_WRITE_USER_INFO) {
            m1793s();
            m1723a(m1773i());
        } else if (this.f2401y == ag.OPERATING_WRITE_VIBRATION_VOICE) {
            m1793s();
            m1723a(m1773i());
        } else if (this.f2401y == ag.OPERATING_WRITE_ALARM_CLOCK) {
            if (this.f2379c != null) {
                this.f2379c.mo5590a(this.f2390n.getDeviceType(), this.f2397u.getDeviceId(), this.f2397u.getMemberId(), C2226n.PEDOMETER_ALARM_CLOCK);
            }
            m1723a(m1773i());
        } else if (this.f2401y == ag.OPERATING_WRITE_DISCONNECT) {
            this.f2401y = m1773i();
        } else {
            m1723a(m1773i());
        }
    }

    private ag m1773i() {
        if (this.f2402z == null || this.f2402z.isEmpty()) {
            C2220h.m1596a((Object) this, "Failed to get next operating directive for null...", 1);
            return null;
        }
        this.f2402z.remove(this.f2361B);
        this.f2361B = (ad) this.f2402z.peek();
        if (this.f2361B != null) {
            this.f2401y = this.f2361B.m1555b();
            C2220h.m1596a((Object) this, "next step is :" + this.f2401y, 3);
            return this.f2401y;
        }
        C2220h.m1596a((Object) this, "Failed to get next operating directive by " + this.f2361B, 1);
        return null;
    }

    private void m1776j() {
        C2220h.m1596a((Object) this, "Error ! failed to write command -" + m1791r(), 1);
        m1780l();
    }

    private boolean m1778k() {
        UUID uuid = C2242b.DEVICEINFO_SERVICE_UUID;
        if (!C2221i.m1607a(this.f2391o, uuid)) {
            return false;
        }
        this.f2380d = this.f2391o.m1843a(uuid.toString().substring(4, 8));
        return true;
    }

    private void m1780l() {
        if (this.f2379c != null) {
            this.f2379c.mo5582a(DeviceConnectState.DISCONNECTED);
        }
        this.f2362C = 0;
        if (this.f2377a == ac.PAIR_DEVICE_STATUS) {
            m1781m();
        } else if (this.f2377a == ac.DATA_UPLOAD_STATUS) {
            m1783n();
        } else {
            C2220h.m1596a((Object) this, "unknown working status...", 1);
            this.f2378b.m1593e();
        }
    }

    private void m1781m() {
        if (this.f2381e != null) {
            this.f2381e.clear();
        }
        C2220h.m1596a((Object) this, "Error,Failed to pair device-" + m1791r(), 1);
        this.f2389m = -1;
        m1734a(null, C2225m.PAIR_FAILED);
        this.f2378b.m1593e();
    }

    private void m1783n() {
        C2220h.m1596a((Object) this, "stop measure data upload - " + m1791r(), 1);
        this.f2377a = ac.FREE_STATUS;
        this.f2401y = ag.OPERATING_FREE;
        this.f2378b.m1593e();
        if (this.f2379c != null) {
            m1722a(ManagerStatus.FREE);
            this.f2379c.mo5584a(C2227o.UPLOAD_FAILED, this.f2396t);
        }
    }

    private void m1785o() {
        UUID uuid = C2242b.DESCRIPTOR_UUID;
        this.f2378b.m1588a(this.f2388l, C2242b.PEDOMETER_CHARACTERISTIC_UUID_A4, true);
        this.f2378b.m1587a(this.f2388l, C2242b.PEDOMETER_CHARACTERISTIC_UUID_A4, uuid);
    }

    private void m1787p() {
        UUID uuid = C2242b.DESCRIPTOR_UUID;
        if (C2221i.m1607a(this.f2391o, this.f2388l)) {
            for (C2248h c2248h : this.f2391o.m1843a(this.f2388l.toString().substring(4, 8))) {
                if ((c2248h.m1850c() & 32) != 0) {
                    this.f2378b.m1588a(this.f2388l, c2248h.m1844a(), true);
                    this.f2378b.m1587a(this.f2388l, c2248h.m1844a(), uuid);
                    C2220h.m1596a((Object) this, "open current characteristics of channel-" + c2248h.m1844a().toString().substring(4, 8), 2);
                    Thread.sleep(500);
                }
            }
            C2220h.m1596a((Object) this, "open characteristics of channel done.", 3);
            return;
        }
        C2220h.m1596a((Object) this, "Error,Failed to get service uuid-" + this.f2388l, 1);
    }

    private void m1790q() {
        if (this.f2393q >= this.f2380d.size()) {
            if (this.f2379c != null) {
                this.f2379c.mo5585a(this.f2390n);
            }
            m1723a(m1773i());
            return;
        }
        C2248h c2248h = (C2248h) this.f2380d.get(this.f2393q);
        if ((c2248h.m1850c() & 2) != 0) {
            C2220h.m1596a((Object) this, "read character number-" + c2248h.m1844a().toString(), 3);
            this.f2378b.m1586a(C2242b.DEVICEINFO_SERVICE_UUID, c2248h.m1844a());
            this.f2393q++;
            return;
        }
        C2220h.m1596a((Object) this, "read character number-" + c2248h.m1844a().toString(), 3);
        this.f2393q++;
        m1790q();
    }

    private String m1791r() {
        return this.f2401y != null ? this.f2401y.toString().replace('_', '/').replace("XOR", "default").toLowerCase() : null;
    }

    private void m1793s() {
        if (this.f2379c != null) {
            String deviceType = this.f2390n.getDeviceType();
            if (deviceType.equals(DeviceTypeConstants.PEDOMETER) && this.f2399w != null) {
                this.f2379c.mo5590a(deviceType, this.f2399w.getDeviceId(), this.f2399w.getMemberId(), C2226n.PEDOMETER_USER_INFO);
            } else if (deviceType.equals(DeviceTypeConstants.FAT_SCALE) || deviceType.equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                if (this.f2398v != null) {
                    this.f2379c.mo5590a(deviceType, this.f2398v.getDeviceId(), this.f2398v.getMemberId(), C2226n.WEIGHT_USER_INFO);
                }
                if (this.f2400x != null) {
                    this.f2379c.mo5590a(deviceType, this.f2400x.getDeviceId(), null, C2226n.VIBRATION_VOICE);
                }
            }
        }
    }

    private void m1795t() {
        this.f2367H = 0;
        if (this.f2365F != null) {
            this.f2365F.cancel();
            this.f2365F = null;
        }
        if (this.f2366G != null) {
            this.f2366G.cancel();
            this.f2366G = null;
        }
        this.f2365F = new Timer();
        this.f2366G = new aa(this);
        this.f2365F.schedule(this.f2366G, 800, 800);
    }

    public void m1799a() {
        if (this.f2378b != null) {
            this.f2373N = true;
        }
    }

    public void m1800a(Context context) {
        if (context != null) {
            this.f2375P = context;
        }
    }

    public void m1801a(C2214b c2214b, C2224l c2224l) {
        if (c2214b != null && c2224l != null) {
            this.f2378b = c2214b;
            this.f2378b.m1581a(this.f2376Q);
            this.f2379c = c2224l;
            this.f2377a = ac.FREE_STATUS;
            this.f2401y = ag.OPERATING_FREE;
            this.f2402z = null;
            this.f2360A = null;
            this.f2361B = null;
            this.f2374O = false;
        }
    }

    public void m1802a(LsDeviceInfo lsDeviceInfo, String str, Queue queue) {
        boolean z = false;
        if (lsDeviceInfo == null || str == null || queue == null || this.f2377a != ac.FREE_STATUS) {
            StringBuilder stringBuilder = new StringBuilder("Error,pair device failure. is null ?");
            if (str == null) {
                z = true;
            }
            C2220h.m1596a((Object) this, stringBuilder.append(z).toString(), 1);
            if (this.f2379c != null) {
                this.f2379c.mo5586a(null, C2225m.PAIR_FAILED);
                return;
            }
            return;
        }
        m1722a(ManagerStatus.DEVICE_PAIR);
        C2220h.m1596a((Object) this, "pair device with address-" + str, 1);
        this.f2377a = ac.PAIR_DEVICE_STATUS;
        this.f2390n = lsDeviceInfo;
        this.f2402z = queue;
        this.f2360A = queue;
        this.f2387k = str;
        this.f2385i = new byte[4];
        this.f2386j = new byte[4];
        this.f2381e = new ArrayList();
        this.f2382f = 0;
        this.f2362C = 0;
        this.f2384h = -1;
        this.f2395s = false;
        this.f2394r = false;
        this.f2392p = new Thread(new C2237y(this));
        this.f2361B = (ad) this.f2402z.remove();
        this.f2401y = this.f2361B.m1555b();
        m1723a(this.f2401y);
    }

    public void m1803a(PedometerAlarmClock pedometerAlarmClock) {
        if (pedometerAlarmClock != null) {
            this.f2397u = pedometerAlarmClock;
        }
    }

    public void m1804a(PedometerUserInfo pedometerUserInfo) {
        if (pedometerUserInfo != null) {
            this.f2399w = pedometerUserInfo;
        }
    }

    public void m1805a(VibrationVoice vibrationVoice) {
        if (vibrationVoice != null) {
            this.f2400x = vibrationVoice;
        }
    }

    public void m1806a(WeightUserInfo weightUserInfo) {
        if (weightUserInfo != null) {
            this.f2398v = weightUserInfo;
        }
    }

    public void m1807a(boolean z) {
        this.f2374O = z;
    }

    public boolean m1808a(int i, String str) {
        if (m1751b(i, str)) {
            this.f2382f = i;
            this.f2383g = str;
            if (this.f2401y == ag.OPERATING_WRITE_BIND_USER_NUMBER) {
                m1723a(this.f2401y);
                return true;
            }
            C2220h.m1596a((Object) this, "Error,failed to bind device user -" + m1791r(), 1);
            m1781m();
            return false;
        }
        m1781m();
        return false;
    }

    public void m1809b() {
        if (this.f2377a == ac.DATA_UPLOAD_STATUS) {
            C2220h.m1596a((Object) this, "interrupt measurement data upload task...", 1);
        } else if (this.f2377a == ac.PAIR_DEVICE_STATUS) {
            C2220h.m1596a((Object) this, "interrupt pair task...", 1);
            m1734a(null, C2225m.PAIR_FAILED);
        } else {
            C2220h.m1596a((Object) this, "no task interrupt ...", 1);
        }
        this.f2377a = ac.FREE_STATUS;
        this.f2401y = ag.OPERATING_FREE;
        m1722a(ManagerStatus.FREE);
        this.f2378b.m1593e();
    }

    public void m1810b(LsDeviceInfo lsDeviceInfo, String str, Queue queue) {
        boolean z = false;
        if (str == null || queue == null || this.f2377a != ac.FREE_STATUS) {
            StringBuilder stringBuilder = new StringBuilder("Error,Failed to read measure data,for null...");
            if (str == null) {
                z = true;
            }
            C2220h.m1596a((Object) this, stringBuilder.append(z).toString(), 1);
            return;
        }
        m1722a(ManagerStatus.DATA_RECEIVE);
        this.f2377a = ac.DATA_UPLOAD_STATUS;
        this.f2390n = lsDeviceInfo;
        this.f2402z = queue;
        this.f2360A = queue;
        this.f2387k = str;
        C2220h.m1596a((Object) this, "upload address - " + str, 1);
        this.f2362C = 0;
        this.f2386j = new byte[4];
        this.f2392p = new Thread(new C2238z(this));
        this.f2368I = new StringBuffer();
        this.f2369J = new ArrayList();
        this.f2371L = false;
        this.f2372M = false;
        this.f2373N = false;
        this.f2361B = (ad) this.f2402z.remove();
        this.f2401y = this.f2361B.m1555b();
        m1723a(this.f2401y);
    }

    public PedometerAlarmClock m1811c() {
        return this.f2397u;
    }

    public WeightUserInfo m1812d() {
        return this.f2398v;
    }

    public PedometerUserInfo m1813e() {
        return this.f2399w;
    }

    public VibrationVoice m1814f() {
        return this.f2400x;
    }
}
