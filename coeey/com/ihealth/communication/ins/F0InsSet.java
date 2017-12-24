package com.ihealth.communication.ins;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.imageutils.JfifUtil;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.control.UpDeviceControl;
import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.FirmWare;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.PublicMethod;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class F0InsSet extends IdentifyIns implements NewDataCallback, GetBaseCommProtocolCallback {
    public static final String MSG_DISCONNECTED = "ihealth_disconnected";
    private Context f1831a;
    private String f1832b;
    private String f1833c;
    private byte f1834l = (byte) -16;
    private BaseCommProtocol f1835m;
    private InsCallback f1836n;
    private Map f1837o = new HashMap();
    private FirmWare f1838p;
    private List f1839q;
    private List f1840r;
    private Map f1841s = new ConcurrentHashMap();
    private BroadcastReceiver f1842t = new C21251(this);

    class C21251 extends BroadcastReceiver {
        final /* synthetic */ F0InsSet f1827a;

        C21251(F0InsSet this$0) {
            this.f1827a = this$0;
        }

        public void onReceive(Context context, Intent intent) {
            if (F0InsSet.MSG_DISCONNECTED.equals(intent.getAction())) {
                this.f1827a.m757a(214);
            }
        }
    }

    enum Command {
        Unknown(0),
        GetUpgradeInfo(JfifUtil.MARKER_RST0),
        FirmwareTransmission_Start(209),
        Upgrade_Ready(210),
        FirmwareTransmission_InProgress(211),
        FirmwareTransmission_Finish(213),
        Upgrade_Stop(214);
        
        int f1830a;

        private Command(int what) {
            this.f1830a = what;
        }

        static Command m981a(int i) {
            for (Command command : values()) {
                if (command.f1830a == i) {
                    return command;
                }
            }
            return Unknown;
        }

        public String toString() {
            return String.format("%s(0x%02X)", new Object[]{name(), Integer.valueOf(this.f1830a)});
        }
    }

    public F0InsSet(BaseComm mBaseComm, BaseCommProtocol com, Context context, String mac, String type, InsCallback insCallback) {
        m983a("F0InsSet_Constructor", mac, type);
        this.f1831a = context;
        this.f1832b = mac;
        this.f1833c = type;
        this.f1835m = com;
        this.f1836n = insCallback;
        m759a(insCallback, mac, type, mBaseComm);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MSG_DISCONNECTED);
        this.f1831a.registerReceiver(this.f1842t, intentFilter);
    }

    private void m982a() {
        m983a("readyUpdate", new Object[0]);
        byte[] bArr = new byte[(this.f1840r.size() + 2)];
        bArr[0] = this.f1834l;
        bArr[1] = (byte) -46;
        for (int i = 0; i < this.f1840r.size(); i++) {
            bArr[i + 2] = ((Byte) this.f1840r.get(i)).byteValue();
        }
        m758a(210, 8000, 211, 214);
        this.f1835m.packageData(this.f1832b, bArr);
    }

    private static void m983a(String str, Object... objArr) {
        Log.p("F0InsSet", Level.INFO, str, objArr);
    }

    private void m984a(byte[] bArr, JSONObject jSONObject) {
        byte[] bArr2 = new byte[]{bArr[0], bArr[1]};
        String Bytes2HexString = PublicMethod.Bytes2HexString(new byte[]{bArr[2]});
        Object obj = Bytes2HexString.length() < 2 ? "0x0" + Bytes2HexString : "0x" + Bytes2HexString;
        Object obj2 = new byte[16];
        String str = "";
        System.arraycopy(bArr, 3, obj2, 0, 16);
        int length = obj2.length;
        int i = 0;
        int i2 = 0;
        while (i < length && obj2[i] != (byte) 0) {
            i2++;
            i++;
        }
        try {
            Object str2 = new String(ByteBufferUtil.bufferCut(obj2, 0, i2), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            String str3 = str;
        }
        Object obj3 = new byte[3];
        System.arraycopy(bArr, 19, obj3, 0, 3);
        String str4 = new String(obj3);
        obj3 = new byte[3];
        System.arraycopy(bArr, 22, obj3, 0, 3);
        String str5 = new String(obj3);
        Object obj4 = str4.substring(0, 1) + "." + str4.substring(1, 2) + "." + str4.substring(2, 3);
        String str6 = str5.substring(0, 1) + "." + str5.substring(1, 2) + "." + str5.substring(2, 3);
        int i3 = bArr[28] & 255;
        obj3 = "100";
        if (bArr.length >= 32) {
            obj2 = new byte[3];
            System.arraycopy(bArr, 29, obj2, 0, 3);
            obj3 = new String(obj2);
        }
        this.f1837o.put(this.f1832b, obj3);
        if (bArr[19] == (byte) 0 && bArr[20] == Framer.STDOUT_FRAME_PREFIX && bArr[21] == (byte) 48) {
            obj4 = "5.0.1";
        }
        int i4 = ((bArr[25] & 255) + ((bArr[26] & 255) * 256)) + (((bArr[27] & 255) * 256) * 256);
        int i5 = ((bArr2[1] & 255) << 8) + (bArr2[0] & 255);
        i4 = (i5 == 0 || i5 >= (i4 % 128 != 0 ? (i4 / 128) + 1 : i4 / 128)) ? 0 : 1;
        try {
            jSONObject.put(UpgradeProfile.DEVICE_UPGRADE_FLAG, i4);
            jSONObject.put(UpgradeProfile.DEVICE_FIRMWARE_VERSION, str6);
            jSONObject.put("status", i3);
            jSONObject.put("up.device.up.mode", obj3);
            this.f1836n.onNotify(this.f1832b, this.f1833c, UpgradeProfile.ACTION_DEVICE_UP_INFO, jSONObject.toString());
            jSONObject.put(UpgradeProfile.DEVICE_TYPE, obj);
            jSONObject.put("up.device.up.mode", str2);
            jSONObject.put(UpgradeProfile.DEVICE_HARDWARE_VERSION, obj4);
            jSONObject.put(UpgradeProfile.DEVICE_BLOCK_NUM, i5);
            iHealthDevicesUpgradeManager.getInstance().queryLatestVersionFromCloud(this.f1832b, this.f1833c, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        UpDeviceControl upDeviceControl = iHealthDevicesUpgradeManager.getInstance().getUpDeviceControl(this.f1832b, this.f1833c);
        if (upDeviceControl != null) {
            upDeviceControl.returnComm();
        }
    }

    private void m985b(byte b) {
        this.f1835m.packageData(this.f1832b, new byte[]{this.f1834l, b});
    }

    private void m986c() {
        UpDeviceControl upDeviceControl = iHealthDevicesUpgradeManager.getInstance().getUpDeviceControl(this.f1832b, this.f1833c);
        if (upDeviceControl != null) {
            upDeviceControl.returnComm();
        }
    }

    public void destroy() {
        m983a("destroy", new Object[0]);
        this.f1835m = null;
        this.f1836n = null;
        this.f1831a.unregisterReceiver(this.f1842t);
    }

    public BaseCommProtocol getBaseCommProtocol() {
        return this.f1835m;
    }

    public boolean getCurrentState(String mac) {
        m983a("getCurrentState", mac);
        return (this.f1841s.isEmpty() || this.f1841s.get(mac) == null) ? false : ((Boolean) this.f1841s.get(mac)).booleanValue();
    }

    public void haveNewData(int what, int stateId, byte[] command) {
        int i = 100;
        Log.p("F0InsSet", Level.DEBUG, "haveNewData", new Object[]{Command.m981a(what), Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(command)});
        m757a(what);
        JSONObject jSONObject = new JSONObject();
        switch (Command.m981a(what)) {
            case GetUpgradeInfo:
                if (command != null) {
                    m984a(command, jSONObject);
                    return;
                }
                return;
            case FirmwareTransmission_Start:
                m758a(210, 8000, 210, 214);
                try {
                    jSONObject.put("status", 0);
                    this.f1836n.onNotify(this.f1832b, this.f1833c, UpgradeProfile.ACTION_DEVICE_START_UP, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case Upgrade_Ready:
                m982a();
                return;
            case FirmwareTransmission_InProgress:
                int i2 = (command[0] & 255) + ((command[1] & 255) * 256);
                int size = (i2 * 100) / this.f1839q.size();
                if (size <= 100) {
                    i = size;
                }
                queryData(i2, this.f1832b, i);
                try {
                    jSONObject.put("progress", i);
                    this.f1836n.onNotify(this.f1832b, this.f1833c, UpgradeProfile.ACTION_DEVICE_UP_PROGRESS, jSONObject.toString());
                    return;
                } catch (Exception e2) {
                    return;
                }
            case FirmwareTransmission_Finish:
                m985b((byte) -43);
                i = command[0] & 255;
                if (i == 0 || i == 1) {
                    try {
                        jSONObject.put("progress", 100);
                        this.f1836n.onNotify(this.f1832b, this.f1833c, UpgradeProfile.ACTION_DEVICE_UP_PROGRESS, jSONObject.toString());
                    } catch (Exception e3) {
                    }
                }
                m986c();
                JSONObject jSONObject2 = new JSONObject();
                if (i == 0 || i == 1) {
                    try {
                        jSONObject2.put("status", 1);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                jSONObject2.put("status", 0);
                this.f1836n.onNotify(this.f1832b, this.f1833c, UpgradeProfile.ACTION_DEVICE_FINISH_UP, jSONObject2.toString());
                return;
            case Upgrade_Stop:
                m985b((byte) -42);
                if (this.f1836n != null) {
                    this.f1836n.onNotify(this.f1832b, this.f1833c, UpgradeProfile.ACTION_DEVICE_STOP_UP, null);
                }
                m986c();
                return;
            default:
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void queryData(int index, String mac, int progress) {
        m983a("queryData", Integer.valueOf(index), mac, Integer.valueOf(progress));
        try {
            byte[] bArr = (byte[]) this.f1839q.get(index);
            byte[] bArr2 = (byte[]) this.f1838p.getCrcList().get(index);
            byte[] bArr3 = new byte[(bArr.length + 6)];
            bArr3[0] = this.f1834l;
            bArr3[1] = (byte) -45;
            bArr3[3] = (byte) ((index >> 8) & 255);
            bArr3[2] = (byte) (index & 255);
            bArr3[4] = bArr2[0];
            bArr3[5] = bArr2[1];
            for (int i = 0; i < bArr.length; i++) {
                bArr3[i + 6] = bArr[i];
            }
            String str = (String) this.f1837o.get(mac);
            if (str == null || !str.equals("100") || progress <= 90) {
                m758a(211, 8000, 211, 213, 214);
            } else {
                m758a(211, 16000, 211, 213, 214);
            }
            this.f1835m.packageData(this.f1832b, bArr3);
        } catch (Exception e) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(UpgradeProfile.DEVICE_UP_ERROR, 406);
                this.f1836n.onNotify(mac, this.f1833c, UpgradeProfile.ACTION_DEVICE_ERROR, jSONObject.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void queryInformation() {
        m983a("queryInformation", new Object[0]);
        byte[] bArr = new byte[]{this.f1834l, (byte) -48};
        m758a((int) JfifUtil.MARKER_RST0, 4000, JfifUtil.MARKER_RST0, 214);
        this.f1835m.packageData(this.f1832b, bArr);
    }

    public void setCurrentState(String mac, boolean stateTemp) {
        m983a("setCurrentState", mac, Boolean.valueOf(stateTemp));
        this.f1841s.put(mac, Boolean.valueOf(stateTemp));
    }

    public void setFirmWare(FirmWare firmWare, List list) {
        m983a("setFirmWare", firmWare, list);
        this.f1838p = firmWare;
        this.f1839q = list;
    }

    public void setInfo(List list) {
        m983a("setInfo", list);
        this.f1840r = list;
    }

    public void startUpdate() {
        m983a("startUpdate", new Object[0]);
        byte[] bArr = new byte[]{this.f1834l, (byte) -47};
        m758a(209, 4000, 209, 210, 214);
        this.f1835m.packageData(this.f1832b, bArr);
    }

    public void stopUpdate() {
        m983a("stopUpdate", new Object[0]);
        byte[] bArr = new byte[6];
        bArr[0] = this.f1834l;
        bArr[1] = (byte) -42;
        this.f1835m.packageData(this.f1832b, bArr);
        m757a(211);
    }
}
