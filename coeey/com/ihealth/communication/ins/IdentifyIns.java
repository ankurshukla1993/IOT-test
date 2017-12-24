package com.ihealth.communication.ins;

import com.facebook.imageutils.JfifUtil;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.control.UpDeviceControl;
import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.manager.iHealthDevicesUpgradeManager;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class IdentifyIns {
    private byte[] f1488a = new byte[]{(byte) 67, (byte) 104, (byte) 47, (byte) 72, (byte) 81, (byte) 52, (byte) 76, (byte) 122, (byte) 73, (byte) 116, (byte) 89, (byte) 84, (byte) 52, Framer.STDERR_FRAME_PREFIX, (byte) 115, (byte) 61};
    private InsCallback f1489b;
    private String f1490c;
    protected byte[] f1491d = new byte[16];
    protected byte[] f1492e = new byte[16];
    protected byte[] f1493f = new byte[16];
    protected byte[] f1494g = new byte[16];
    protected byte[] f1495h = new byte[16];
    protected byte[] f1496i = new byte[16];
    protected byte[] f1497j = new byte[16];
    GenerateKap f1498k = new GenerateKap();
    private String f1499l;
    private BaseComm f1500m;
    private int[] f1501n;
    private int f1502o;
    private final Timer f1503p = new Timer();
    private TimerTask f1504q;

    class C21301 extends TimerTask {
        final /* synthetic */ IdentifyIns f1893a;

        C21301(IdentifyIns this$0) {
            this.f1893a = this$0;
        }

        public void run() {
            if (this.f1893a.f1489b != null) {
                if (this.f1893a.f1504q != null) {
                    this.f1893a.f1504q.cancel();
                }
                this.f1893a.f1504q = null;
                Object obj = null;
                int i;
                if (this.f1893a.f1502o >= 240) {
                    if (this.f1893a.f1500m != null) {
                        this.f1893a.f1500m.disconnect(this.f1893a.f1490c);
                        obj = 1;
                    }
                    i = 1;
                } else if (this.f1893a.f1502o >= JfifUtil.MARKER_RST0 && this.f1893a.f1502o <= 214) {
                    UpDeviceControl upDeviceControl = iHealthDevicesUpgradeManager.getInstance().getUpDeviceControl(this.f1893a.f1490c, this.f1893a.f1499l);
                    if (upDeviceControl != null) {
                        upDeviceControl.returnComm();
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(UpgradeProfile.DEVICE_UP_ERROR, 301);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.f1893a.f1489b.onNotify(this.f1893a.f1490c, this.f1893a.f1499l, UpgradeProfile.ACTION_DEVICE_ERROR, jSONObject.toString());
                    i = 1;
                }
                if (obj == null) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(iHealthDevicesManager.IHEALTH_COMM_TIMEOUT_COMMAND_ID, this.f1893a.f1502o);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    this.f1893a.f1489b.onNotifyWithAction(this.f1893a.f1490c, this.f1893a.f1499l, iHealthDevicesManager.IHEALTH_COMM_TIMEOUT, jSONObject2.toString());
                }
            }
        }
    }

    private byte[] m751a(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[(bArr.length + 2)];
        bArr2[0] = b;
        bArr2[1] = (byte) -4;
        for (int i = 2; i < bArr2.length; i++) {
            bArr2[i] = bArr[i - 2];
        }
        return bArr2;
    }

    protected void m757a(int i) {
        if (this.f1501n != null) {
            for (int i2 : this.f1501n) {
                if (i2 == i) {
                    if (this.f1504q != null) {
                        this.f1504q.cancel();
                    }
                    this.f1504q = null;
                    return;
                }
            }
        }
    }

    protected synchronized void m758a(int i, long j, int... iArr) {
        this.f1501n = iArr;
        this.f1502o = i;
        if (this.f1504q != null) {
            this.f1504q.cancel();
        }
        this.f1504q = new C21301(this);
        this.f1503p.schedule(this.f1504q, j);
    }

    protected void m759a(InsCallback insCallback, String str, String str2, BaseComm baseComm) {
        this.f1489b = insCallback;
        this.f1490c = str;
        this.f1499l = str2;
        this.f1500m = baseComm;
    }

    protected byte[] m760a(byte b) {
        byte[] b2 = m763b();
        byte[] bArr = new byte[(b2.length + 2)];
        bArr[0] = b;
        bArr[1] = (byte) -6;
        for (int i = 2; i < bArr.length; i++) {
            bArr[i] = b2[i - 2];
        }
        return bArr;
    }

    protected byte[] m761a(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = bArr[3 - i];
            bArr2[i + 4] = bArr[7 - i];
            bArr2[i + 8] = bArr[11 - i];
            bArr2[i + 12] = bArr[15 - i];
        }
        return bArr2;
    }

    protected byte[] m762a(byte[] bArr, String str, byte b) {
        if (bArr.length == 48) {
            for (int i = 0; i < 16; i++) {
                this.f1496i[i] = bArr[i];
                this.f1492e[i] = bArr[i + 16];
                this.f1494g[i] = bArr[i + 32];
            }
        }
        this.f1497j = XXTEA.encrypt(m761a(this.f1496i), getKa(str));
        this.f1493f = XXTEA.encrypt(m761a(this.f1492e), this.f1497j);
        this.f1495h = XXTEA.encrypt(m761a(this.f1494g), this.f1497j);
        return m751a(m761a(this.f1495h), b);
    }

    protected byte[] m763b() {
        new Random(System.currentTimeMillis()).nextBytes(this.f1491d);
        for (int i = 0; i < 16; i++) {
            if (this.f1491d[i] < (byte) 0) {
                this.f1491d[i] = (byte) (0 - this.f1491d[i]);
            }
        }
        return m761a(this.f1491d);
    }

    protected byte[] m764b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (((bArr[i] & 15) << 4) | ((bArr[i] & 240) >> 4));
        }
        return bArr2;
    }

    public byte[] getDeviceId() {
        return this.f1496i;
    }

    public byte[] getKa(String type) {
        return XXTEA.encrypt(m764b(this.f1498k.getKa(type)), m764b(this.f1488a));
    }
}
