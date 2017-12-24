package com.lifesense.ble.p003a;

import android.content.Context;
import android.util.Log;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lifesense.ble.DeviceConnectState;
import com.lifesense.ble.bean.C2242b;
import com.lifesense.ble.bean.C2248h;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerUserInfo;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class C2229q {
    private static /* synthetic */ int[] f2314J;
    private String f2315A = "";
    private boolean f2316B;
    private boolean f2317C;
    private boolean f2318D;
    private boolean f2319E;
    private DeviceConnectState f2320F;
    private List f2321G;
    private boolean f2322H;
    private C2222j f2323I = new C2230r(this);
    private C2223k f2324a;
    private C2233u f2325b;
    private C2214b f2326c;
    private List f2327d;
    private String f2328e;
    private UUID f2329f;
    private int f2330g;
    private LsDeviceInfo f2331h;
    private C2248h f2332i;
    private Thread f2333j;
    private int f2334k;
    private ae f2335l;
    private PedometerUserInfo f2336m;
    private ag f2337n;
    private Queue f2338o;
    private Queue f2339p;
    private ad f2340q;
    private int f2341r;
    private int f2342s;
    private boolean f2343t;
    private Timer f2344u;
    private TimerTask f2345v;
    private int f2346w;
    private StringBuffer f2347x;
    private List f2348y;
    private String f2349z = "";

    private C2229q() {
    }

    public C2229q(Context context) {
        if (context != null && this.f2323I != null) {
            this.f2326c = new C2214b();
            this.f2326c.m1583a(context);
            this.f2326c.m1581a(this.f2323I);
            this.f2325b = C2233u.FREE_STATUS;
            this.f2337n = ag.OPERATING_FREE;
            this.f2338o = null;
            this.f2339p = null;
            this.f2340q = null;
            this.f2321G = new ArrayList();
            this.f2322H = false;
            this.f2319E = false;
        }
    }

    private void m1639a(ag agVar) {
        switch (C2229q.m1666c()[agVar.ordinal()]) {
            case 2:
                this.f2326c.m1585a(this.f2328e);
                return;
            case 3:
                if (m1676g()) {
                    this.f2334k = 0;
                    m1677h();
                    return;
                }
                m1672e();
                return;
            case 5:
                this.f2333j.start();
                return;
            case 18:
                m1684k();
                return;
            case 19:
                m1655a(this.f2340q.m1554a());
                return;
            case 20:
                this.f2343t = false;
                m1651a(C2213a.m1550e());
                return;
            case 21:
                C2220h.m1596a((Object) this, "waiting to receive the measure data ...", 2);
                return;
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return;
            default:
                m1672e();
                return;
        }
    }

    private void m1651a(String str) {
        this.f2348y.clear();
        int length = str.length() / 40;
        int length2 = str.length() % 40;
        for (int i = 0; i < length; i++) {
            this.f2348y.add(str.substring(i * 40, (i * 40) + 40));
        }
        if (length2 > 0) {
            this.f2348y.add(str.substring(length * 40, str.length()));
        }
        m1687m();
    }

    private void m1652a(String str, String str2) {
        if (this.f2324a != null) {
            this.f2324a.mo5595a(this.f2331h, str, null, "CE");
        }
    }

    private void m1653a(String str, boolean z, String str2) {
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
        m1651a(C2213a.m1541a(str, g2.m1534d().toByteArray(), C2213a.ECI_resp_sendDataToManufacturerSvr));
    }

    private void m1654a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (!uuid.equals(C2242b.DEVICEINFO_SERVICE_UUID)) {
            return;
        }
        if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_MANUFACTURER_CHARACTERISTIC_UUID)) {
            this.f2331h.setManufactureName(C2221i.m1612c(bArr));
            C2220h.m1596a((Object) this, "Device information-ManufactureName-" + C2221i.m1612c(bArr), 2);
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_MODEL_CHARACTERISTIC_UUID)) {
            if (C2221i.m1612c(bArr) != null && C2221i.m1612c(bArr).length() > 0) {
                this.f2331h.setModelNumber(C2221i.m1612c(bArr));
                C2220h.m1596a((Object) this, "Device information-ModelNumber-" + C2221i.m1612c(bArr), 2);
            }
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_SERIAL_NUMBER_CHARACTERISTIC_UUID)) {
            String protocolType = this.f2331h.getProtocolType();
            if (protocolType != null && !protocolType.equals(ae.A4.toString())) {
                this.f2331h.setDeviceId(C2255a.m1907e(C2221i.m1612c(bArr)));
                this.f2331h.setDeviceSn(C2255a.m1910f(this.f2331h.getDeviceId()));
                C2220h.m1596a((Object) this, "Device information-DeviceId-" + this.f2331h.getDeviceId() + ";DeviceSn-" + this.f2331h.getDeviceSn(), 2);
            }
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_HARDWARE_REVISION_CHARACTERISTIC_UUID)) {
            this.f2331h.setHardwareVersion(C2221i.m1612c(bArr));
            C2220h.m1596a((Object) this, "Device information-HardwareVersion-" + C2221i.m1612c(bArr), 2);
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_FIRMWARE_REVISION_CHARACTERISTIC_UUID)) {
            this.f2331h.setFirmwareVersion(C2221i.m1612c(bArr));
            this.f2330g = C2221i.m1601a(this.f2331h.getFirmwareVersion(), this.f2331h.getDeviceType());
            this.f2331h.setMaxUserQuantity(this.f2330g);
            C2220h.m1596a((Object) this, "Device information-FirmwareVersion-" + C2221i.m1612c(bArr), 2);
        } else if (uuid2.equals(C2242b.DEVICEINFO_SERVICE_SOFTWARE_REVISION_CHARACTERISTIC_UUID)) {
            this.f2331h.setSoftwareVersion(C2221i.m1612c(bArr));
            C2220h.m1596a((Object) this, "Device information-SoftwareVersion-" + C2221i.m1612c(bArr), 2);
        }
    }

    private void m1655a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            m1682j();
        } else if (this.f2326c != null && this.f2329f != null) {
            if (!(this.f2335l == ae.A4 ? this.f2326c.m1589a(this.f2329f, C2242b.PEDOMETER_WRITE_CHARACTERISTIC_UUID_A4, bArr) : this.f2326c.m1589a(this.f2329f, C2242b.DOWNLOAD_INFORMATION_OR_COMMAND_CHARACTERISTIC_UUID, bArr))) {
                m1682j();
            }
        }
    }

    private void m1659b(String str) {
        String replace = str.replace(Constants.SPACE, "");
        if (replace.indexOf("FE01") != 0) {
            replace = replace.substring(replace.indexOf("FE01"), replace.length());
        }
        String substring = replace.substring(16, ((Integer.valueOf(replace.substring(4, 8), 16).intValue() - 8) * 2) + 16);
        int intValue = Integer.valueOf(replace.substring(8, 12), 16).intValue();
        this.f2349z = replace.substring(12, 16);
        byte[] a = ac.m1297a(substring);
        if (intValue == C2213a.ECI_req_auth) {
            C2220h.m1596a((Object) this, "receive auth request from device A4,sequenceNumber is (" + this.f2349z + ")", 3);
            m1639a(m1667d());
        } else if (intValue == C2213a.ECI_req_init) {
            C2220h.m1596a((Object) this, "receive init request from device A4,sequenceNumber is (" + this.f2349z + ")", 3);
            m1639a(m1667d());
        } else if (intValue == C2213a.ECI_req_sendDataToManufacturerSvr) {
            replace = m1663c(a);
            if (replace != null) {
                substring = replace.substring(0, 2);
                C2220h.m1596a((Object) this, "current request command id is:(0x" + substring + ")", 2);
                if (substring.equalsIgnoreCase("C7")) {
                    this.f2316B = true;
                    m1669d(replace);
                    this.f2315A = this.f2349z;
                    if (this.f2336m != null) {
                        C2220h.m1596a((Object) this, "write custom pedometer user info to device a4..." + this.f2336m.toString(), 3);
                        replace = C2213a.m1539a(this.f2349z, this.f2336m.getWeight(), this.f2336m.getHeight(), this.f2336m.getWeekTargetSteps(), 0, false);
                    } else {
                        C2220h.m1596a((Object) this, "write default pedometer user info to device a4...", 3);
                        replace = C2213a.m1539a(this.f2349z, 60.0f, 1.7f, 70000, 0, false);
                    }
                    this.f2337n = ag.OPERATING_WRITE_C7_COMMAND_TO_DEVICE;
                    m1651a(replace);
                    return;
                } else if (substring.equalsIgnoreCase("C4")) {
                    this.f2316B = true;
                    m1664c(replace);
                    this.f2337n = ag.OPERATING_WRITE_C4_COMMAND_TO_DEVICE;
                    m1653a(this.f2349z, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CA")) {
                    this.f2316B = true;
                    m1665c(replace, substring);
                    this.f2337n = ag.OPERATING_WRITE_CA_COMMAND_TO_DEVICE;
                    m1653a(this.f2349z, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("C9")) {
                    this.f2316B = true;
                    m1670d(replace, substring);
                    this.f2337n = ag.OPERATING_WRITE_C9_COMMAND_TO_DEVICE;
                    m1653a(this.f2349z, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CB")) {
                    this.f2316B = true;
                    this.f2337n = ag.OPERATING_WRITE_CB_COMMAND_TO_DEVICE;
                    m1660b(replace, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CE")) {
                    this.f2316B = true;
                    m1652a(replace, substring);
                    this.f2337n = ag.OPERATING_WRITE_CE_COMMAND_TO_DEVICE;
                    m1653a(this.f2349z, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("CC")) {
                    this.f2316B = true;
                    this.f2337n = ag.OPERATING_WRITE_CC_COMMAND_TO_DEVICE;
                    m1653a(this.f2349z, true, substring);
                    return;
                } else if (substring.equalsIgnoreCase("C3")) {
                    this.f2316B = true;
                    this.f2337n = ag.OPERATING_WRITE_C3_COMMAND_TO_DEVICE;
                    m1653a(this.f2349z, true, substring);
                    return;
                } else {
                    this.f2316B = false;
                    C2220h.m1596a((Object) this, "Failed to parse the command request...", 2);
                    return;
                }
            }
            C2220h.m1596a((Object) this, "Failed to parse packet contents...", 1);
        }
    }

    private void m1660b(String str, String str2) {
        C2220h.m1596a((Object) this, "receive command request(0xCB)..." + new ab(str).toString(), 3);
        m1653a(this.f2349z, true, str2);
    }

    private void m1661b(byte[] bArr) {
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
            this.f2342s = Integer.valueOf(replace.substring(4, 8), 16).intValue();
            this.f2347x.replace(0, this.f2347x.length(), "");
        }
        if (this.f2347x.length() < this.f2342s * 2) {
            this.f2347x.append(replace);
        }
        if (this.f2347x.length() >= this.f2342s * 2 && this.f2347x.length() < (this.f2342s * 2) + 40) {
            m1659b(this.f2347x.toString());
        }
    }

    private String m1663c(byte[] bArr) {
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

    private void m1664c(String str) {
        String substring = str.substring(2, str.length());
        int length = substring.length() / 36;
        for (int i = 0; i < length; i++) {
            C2220h.m1596a((Object) this, "receive pedometer measure data from (0xC4)...", 2);
            if (this.f2324a != null) {
                this.f2324a.mo5595a(this.f2331h, substring, null, "C4");
            }
        }
    }

    private void m1665c(String str, String str2) {
        String substring = str.substring(2, str.length());
        int length = substring.length() / 38;
        for (int i = 0; i < length; i++) {
            String substring2 = substring.substring(i * 38, (i * 38) + 38);
            C2220h.m1596a((Object) this, "receive pedometer every day measure data from(0xCA)...", 2);
            if (this.f2324a != null) {
                this.f2324a.mo5595a(this.f2331h, substring2, null, "CA");
            }
        }
    }

    static /* synthetic */ int[] m1666c() {
        int[] iArr = f2314J;
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
            f2314J = iArr;
        }
        return iArr;
    }

    private ag m1667d() {
        if (this.f2338o == null || this.f2338o.isEmpty()) {
            C2220h.m1596a((Object) this, "Failed to get next operating directive for null...", 1);
            return null;
        }
        this.f2338o.remove(this.f2340q);
        this.f2340q = (ad) this.f2338o.peek();
        if (this.f2340q != null) {
            this.f2337n = this.f2340q.m1555b();
            C2220h.m1596a((Object) this, "next step is :" + this.f2337n, 3);
            return this.f2337n;
        }
        C2220h.m1596a((Object) this, "Failed to get next operating directive by " + this.f2340q, 1);
        return null;
    }

    private void m1669d(String str) {
        Log.i("ProtobufData", "cmdPedometerC7=" + new aa(str).toString());
    }

    private void m1670d(String str, String str2) {
        String substring = str.substring(2, str.length());
        int length = substring.length() / 46;
        for (int i = 0; i < length; i++) {
            String substring2 = substring.substring(i * 46, (i * 46) + 46);
            C2220h.m1596a((Object) this, "receive pedometer every day measure data from(0xC9)...", 2);
            if (this.f2324a != null) {
                this.f2324a.mo5595a(this.f2331h, substring2, null, "C9");
            }
        }
    }

    private void m1672e() {
        if (this.f2324a != null) {
            this.f2320F = DeviceConnectState.DISCONNECTED;
            this.f2324a.mo5593a(DeviceConnectState.DISCONNECTED, this.f2331h.getBroadcastID());
        }
        this.f2341r = 0;
        m1674f();
    }

    private void m1674f() {
        C2220h.m1596a((Object) this, "stop measure data upload - " + m1679i(), 1);
        this.f2325b = C2233u.FREE_STATUS;
        this.f2337n = ag.OPERATING_FREE;
        this.f2320F = DeviceConnectState.DISCONNECTED;
        if (this.f2326c != null) {
            this.f2326c.m1593e();
        }
    }

    private boolean m1676g() {
        UUID uuid = C2242b.DEVICEINFO_SERVICE_UUID;
        if (!C2221i.m1607a(this.f2332i, uuid)) {
            return false;
        }
        this.f2327d = this.f2332i.m1843a(uuid.toString().substring(4, 8));
        return true;
    }

    private void m1677h() {
        if (this.f2334k >= this.f2327d.size()) {
            if (this.f2324a != null) {
                this.f2324a.mo5594a(this.f2331h);
            }
            m1639a(m1667d());
            return;
        }
        C2248h c2248h = (C2248h) this.f2327d.get(this.f2334k);
        if ((c2248h.m1850c() & 2) != 0) {
            C2220h.m1596a((Object) this, "read character number-" + c2248h.m1844a().toString(), 3);
            this.f2326c.m1586a(C2242b.DEVICEINFO_SERVICE_UUID, c2248h.m1844a());
            this.f2334k++;
            return;
        }
        C2220h.m1596a((Object) this, "read character number-" + c2248h.m1844a().toString(), 3);
        this.f2334k++;
        m1677h();
    }

    private String m1679i() {
        return this.f2337n != null ? this.f2337n.toString().replace('_', '/').replace("XOR", "default").toLowerCase() : null;
    }

    private void m1682j() {
        C2220h.m1596a((Object) this, "Error ! failed to write command -" + m1679i(), 1);
        m1672e();
    }

    private void m1684k() {
        UUID uuid = C2242b.DESCRIPTOR_UUID;
        this.f2326c.m1588a(this.f2329f, C2242b.PEDOMETER_CHARACTERISTIC_UUID_A4, true);
        this.f2326c.m1587a(this.f2329f, C2242b.PEDOMETER_CHARACTERISTIC_UUID_A4, uuid);
    }

    private void m1686l() {
        UUID uuid = C2242b.DESCRIPTOR_UUID;
        if (C2221i.m1607a(this.f2332i, this.f2329f)) {
            for (C2248h c2248h : this.f2332i.m1843a(this.f2329f.toString().substring(4, 8))) {
                if ((c2248h.m1850c() & 32) != 0) {
                    this.f2326c.m1588a(this.f2329f, c2248h.m1844a(), true);
                    this.f2326c.m1587a(this.f2329f, c2248h.m1844a(), uuid);
                    C2220h.m1596a((Object) this, "open current characteristics of channel-" + c2248h.m1844a().toString().substring(4, 8), 2);
                    Thread.sleep(300);
                }
            }
            C2220h.m1596a((Object) this, "open characteristics of channel done.", 3);
            return;
        }
        C2220h.m1596a((Object) this, "Error,Failed to get service uuid-" + this.f2329f, 1);
    }

    private void m1687m() {
        this.f2346w = 0;
        if (this.f2344u != null) {
            this.f2344u.cancel();
            this.f2344u = null;
        }
        if (this.f2345v != null) {
            this.f2345v.cancel();
            this.f2345v = null;
        }
        this.f2344u = new Timer();
        this.f2345v = new C2232t(this);
        this.f2344u.schedule(this.f2345v, 800, 800);
    }

    public void m1697a() {
        m1672e();
    }

    public void m1698a(C2223k c2223k) {
        this.f2324a = c2223k;
    }

    public void m1699a(LsDeviceInfo lsDeviceInfo, String str, Queue queue) {
        boolean z = false;
        if (str == null || queue == null || this.f2325b != C2233u.FREE_STATUS) {
            StringBuilder stringBuilder = new StringBuilder("Error,Failed to read measure data,for null...");
            if (str == null) {
                z = true;
            }
            C2220h.m1596a((Object) this, stringBuilder.append(z).toString(), 1);
            return;
        }
        this.f2325b = C2233u.DATA_UPLOAD_STATUS;
        this.f2331h = lsDeviceInfo;
        this.f2338o = queue;
        this.f2339p = queue;
        this.f2328e = str;
        C2220h.m1596a((Object) this, "upload address - " + str, 1);
        this.f2341r = 0;
        this.f2333j = new Thread(new C2231s(this));
        this.f2347x = new StringBuffer();
        this.f2348y = new ArrayList();
        this.f2316B = false;
        this.f2317C = false;
        this.f2318D = false;
        this.f2340q = (ad) this.f2338o.remove();
        this.f2337n = this.f2340q.m1555b();
        m1639a(this.f2337n);
    }

    public void m1700a(PedometerUserInfo pedometerUserInfo) {
        if (pedometerUserInfo != null) {
            this.f2322H = true;
            String a = C2213a.m1539a(this.f2315A, pedometerUserInfo.getWeight(), pedometerUserInfo.getHeight(), pedometerUserInfo.getWeekTargetSteps(), 0, false);
            if (this.f2321G != null) {
                if (this.f2321G.size() > 0) {
                    this.f2321G.clear();
                }
                this.f2321G.add(a);
            }
        }
    }

    public String m1701b() {
        return this.f2331h != null ? this.f2331h.getBroadcastID() : null;
    }

    public void m1702b(PedometerUserInfo pedometerUserInfo) {
        this.f2336m = pedometerUserInfo;
    }
}
