package com.ihealth.communication.ins;

import com.ihealth.communication.base.bt.BtCommThread;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BtCommProtocol;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class Hs5InsSet implements NewDataCallback {
    public static final String MSG_NOT_FOUND = "com.msg.not.found";
    public static final String MSG_SETTING_WIFI = "com.msg.setting.wifi";
    public static final String MSG_SET_WIFI_FAIL = "com.msg.wifi.fail";
    public static final String MSG_SET_WIFI_SUCCESS = "com.msg.wifi.success";
    private BtCommProtocol f1875a;
    private BaseCommCallback f1876b;
    private InsCallback f1877c;
    private String f1878d = "";
    private String f1879e = "";
    private Timer f1880f;
    private TimerTask f1881g;
    private long f1882h = 500;
    private long f1883i = 1000;
    private int f1884j;

    class C21291 extends TimerTask {
        final /* synthetic */ Hs5InsSet f1874a;

        C21291(Hs5InsSet this$0) {
            this.f1874a = this$0;
        }

        public void run() {
            if (BtCommThread.ioException) {
                this.f1874a.cancelTimer();
                if (this.f1874a.f1884j == 1) {
                    try {
                        this.f1874a.f1877c.onNotify(this.f1874a.f1878d, this.f1874a.f1879e, HsProfile.ACTION_SETWIFI_FAIL, new JSONObject().toString());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            this.f1874a.m1020a();
        }
    }

    public Hs5InsSet(String mac, String type, BaseComm com, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        m1022a("Hs5InsSet_Constructor", mac, type);
        this.f1875a = new BtCommProtocol(com, this);
        this.f1877c = insCallback;
        this.f1876b = baseCommCallback;
        this.f1878d = mac;
        this.f1879e = type;
    }

    private void m1020a() {
        this.f1875a.packageData(null, new byte[]{(byte) -87, (byte) -23});
    }

    private static void m1022a(String str, Object... objArr) {
        Log.p("Hs5InsSet", Level.INFO, str, objArr);
    }

    public void cancelTimer() {
        m1022a("cancelTimer", new Object[0]);
        if (this.f1880f != null) {
            this.f1880f.cancel();
        }
        if (this.f1881g != null) {
            this.f1881g.cancel();
        }
    }

    public void checkState() {
        m1022a("checkState", new Object[0]);
        this.f1880f = new Timer();
        this.f1881g = new C21291(this);
        this.f1880f.schedule(this.f1881g, this.f1882h, this.f1883i);
    }

    public void destroy() {
    }

    public void haveNewData(int what, int stateId, byte[] command) {
        r3 = new Object[3];
        r3[0] = String.format("0x%02X", new Object[]{Integer.valueOf(what)});
        r3[1] = Integer.valueOf(stateId);
        r3[2] = ByteBufferUtil.Bytes2HexString(command);
        Log.p("Hs5InsSet", Level.DEBUG, "haveNewData", r3);
        switch (what) {
            case 224:
                this.f1884j = command[0];
                Log.d("Hs5InsSet", "haveNewData:" + this.f1884j);
                switch (this.f1884j) {
                    case 1:
                        this.f1877c.onNotify(this.f1878d, this.f1879e, HsProfile.ACTION_SETTINGWIFI, null);
                        return;
                    case 2:
                        cancelTimer();
                        this.f1877c.onNotify(this.f1878d, this.f1879e, HsProfile.ACTION_SETWIFI_SUCCESS, null);
                        return;
                    case 3:
                        cancelTimer();
                        this.f1877c.onNotify(this.f1878d, this.f1879e, HsProfile.ACTION_SETWIFI_FAIL, null);
                        return;
                    default:
                        cancelTimer();
                        this.f1877c.onNotify(this.f1878d, this.f1879e, HsProfile.ACTION_SETWIFI_UNKNOW, null);
                        return;
                }
            default:
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void setWifi(String ssid, int type, String pw) {
        int i;
        int i2 = 34;
        m1022a("setWifi", ssid, Integer.valueOf(type), pw);
        Object obj = new byte[32];
        Object bytes = pw.getBytes();
        System.arraycopy(bytes, 0, obj, 0, bytes.length);
        int length = obj.length;
        byte[] bytes2 = ssid.getBytes();
        int length2 = bytes2.length;
        byte[] bArr = new byte[(length + 34)];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) type;
        for (i = 0; i < length2; i++) {
            bArr[i + 2] = bytes2[i];
        }
        for (i = length2 + 2; i < 34; i++) {
            bArr[i] = (byte) 0;
        }
        while (i2 < length + 34) {
            bArr[i2] = obj[i2 - 34];
            i2++;
        }
        this.f1875a.packageDataWithoutProtocol(bArr);
        checkState();
    }
}
