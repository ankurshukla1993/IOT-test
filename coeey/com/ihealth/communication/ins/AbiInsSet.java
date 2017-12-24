package com.ihealth.communication.ins;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.common.base.Ascii;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.control.AbiProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public class AbiInsSet extends IdentifyIns implements NewDataCallback {
    private final BaseComm f1678a;
    private Context f1679b;
    private BaseCommProtocol f1680c;
    private String f1681l;
    private String f1682m;
    private String f1683n;
    private String f1684o;
    private InsCallback f1685p;
    private BaseCommCallback f1686q;
    private A1DBtools f1687r;
    private boolean f1688s = false;

    class C21071 extends TimerTask {
        final /* synthetic */ AbiInsSet f1677a;

        C21071(AbiInsSet this$0) {
            this.f1677a = this$0;
        }

        public void run() {
            this.f1677a.f1685p.onNotify(this.f1677a.f1681l, this.f1677a.f1682m, AbiProfile.ACTION_STOP_ABI, "");
        }
    }

    public AbiInsSet(Context context, BaseComm mBaseComm, BaseCommProtocol comm, String userName, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1680c = comm;
        this.f1680c.setInsSet(this);
        this.f1679b = context;
        this.f1681l = mac;
        this.f1682m = type;
        this.f1683n = abiType;
        this.f1685p = insCallback;
        this.f1686q = mBaseCommCallback;
        this.f1687r = new A1DBtools();
        this.f1678a = mBaseComm;
        m759a(insCallback, mac, type, mBaseComm);
    }

    private void m885a() {
        this.f1680c.packageData(null, new byte[]{(byte) -95, (byte) 80, (byte) 0, (byte) 0, (byte) 0});
    }

    private void m886a(byte[] bArr, boolean z) {
        int i = (((((bArr[0] & 255) * 256) + (bArr[1] & 255)) * 300) + PL2303Driver.BAUD150) / 4096;
        byte[] bArr2 = new byte[8];
        for (int i2 = 2; i2 < 10; i2++) {
            bArr2[i2 - 2] = bArr[i2];
        }
        String str = "[" + String.valueOf(bArr2[0] & 255) + "," + String.valueOf(bArr2[1] & 255) + "," + String.valueOf(bArr2[2] & 255) + "," + String.valueOf(bArr2[3] & 255) + "," + String.valueOf(bArr2[4] & 255) + "," + String.valueOf(bArr2[5] & 255) + "," + String.valueOf(bArr2[6] & 255) + "," + String.valueOf(bArr2[7] & 255) + "]";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pressure", i);
            jSONObject.put("heartbeat", z);
            jSONObject.put(AbiProfile.PULSE_WAVE_ABI, str);
            this.f1685p.onNotify(this.f1681l, this.f1682m, AbiProfile.ACTION_ONLINE_PULSE_WAVE_ABI, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m889c() {
        this.f1680c.packageData(null, new byte[]{(byte) -95, (byte) 63, (byte) 0, (byte) 0, (byte) 0});
    }

    private void m890c(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = bArr[1] & 255;
        int i3 = bArr[2] & 255;
        int i4 = bArr[3] != (byte) 0 ? 1 : 0;
        int i5 = bArr[4] & 255;
        int i6 = i + i2;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.f1687r.save(this.f1679b, this.f1684o, this.f1681l, this.f1682m, i6, i2, i3, currentTimeMillis);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbiProfile.HIGH_BLOOD_PRESSURE_ABI, i6);
            jSONObject.put(AbiProfile.LOW_BLOOD_PRESSURE_ABI, i2);
            jSONObject.put("pulse", i3);
            jSONObject.put(AbiProfile.MEASUREMENT_AHR_ABI, i4);
            jSONObject.put("hsd", i5);
            jSONObject.put(AbiProfile.DATAID, MD5.md5String(PublicMethod.getBPDataID(this.f1681l, i3 + "", currentTimeMillis)));
            this.f1685p.onNotify(this.f1681l, this.f1682m, "online_result_bp", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m891d() {
        this.f1680c.packageDataAsk(new byte[]{(byte) -95});
    }

    public void destroy() {
    }

    public void getBatteryLevel() {
        this.f1680c.packageData(null, new byte[]{(byte) -95, (byte) 32, (byte) 0, (byte) 0, (byte) 0});
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        if (stateId == 0) {
            m891d();
        }
        m757a(what);
        JSONObject jSONObject = new JSONObject();
        int i;
        switch (what) {
            case 32:
                i = returnData[0] & 255;
                if (i <= 0 || i > 100) {
                    i = 100;
                }
                try {
                    jSONObject.put(iHealthDevicesManager.IHEALTH_DEVICE_MAC, this.f1681l);
                    jSONObject.put("type", this.f1682m);
                    jSONObject.put("battery", i);
                    this.f1685p.onNotify(this.f1681l, this.f1682m, AbiProfile.ACTION_BATTERY_ABI, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            case 36:
            case 49:
            case 50:
                return;
            case 48:
                if ((returnData[0] & 1) == 1) {
                    try {
                        jSONObject.put("type", this.f1682m);
                        this.f1685p.onNotify(this.f1681l, this.f1682m, AbiProfile.ACTION_ZERO_OVER_ABI, jSONObject.toString());
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    AbiControlSubManager.getInstance().setZore(this.f1681l);
                    if (AbiControlSubManager.getInstance().notNeedWaitZore(this.f1681l)) {
                        m885a();
                        return;
                    }
                    return;
                }
                try {
                    jSONObject.put("type", this.f1682m);
                    this.f1685p.onNotify(this.f1681l, this.f1682m, AbiProfile.ACTION_ZEROING_ABI, jSONObject.toString());
                    return;
                } catch (JSONException e22) {
                    e22.printStackTrace();
                    return;
                }
            case 54:
                if (!this.f1688s) {
                    this.f1688s = true;
                    m890c(returnData);
                    return;
                }
                return;
            case 56:
                i = returnData[0] & 255;
                try {
                    jSONObject.put("type", this.f1682m);
                    jSONObject.put("error", i);
                    this.f1685p.onNotify(this.f1681l, this.f1682m, "error_bp", jSONObject.toString());
                    return;
                } catch (JSONException e222) {
                    e222.printStackTrace();
                    return;
                }
            case 59:
                try {
                    jSONObject.put(iHealthDevicesManager.IHEALTH_DEVICE_MAC, this.f1681l);
                    jSONObject.put("type", this.f1682m);
                    this.f1685p.onNotify(this.f1681l, this.f1682m, AbiProfile.ACTION_INTERRUPTED_ABI, jSONObject.toString());
                    return;
                } catch (JSONException e2222) {
                    e2222.printStackTrace();
                    return;
                }
            case 60:
                m886a(returnData, false);
                if ((returnData[returnData.length - 1] & 1) == 1) {
                    AbiControlSubManager.getInstance().setPressure(this.f1681l);
                    if (AbiControlSubManager.getInstance().notNneedWaitPressure(this.f1681l)) {
                        m889c();
                        return;
                    }
                    return;
                }
                return;
            case 61:
                m886a(returnData, true);
                if ((returnData[returnData.length - 1] & 1) == 1) {
                    AbiControlSubManager.getInstance().setPressure(this.f1681l);
                    if (AbiControlSubManager.getInstance().notNneedWaitPressure(this.f1681l)) {
                        m889c();
                        return;
                    }
                    return;
                }
                return;
            case 62:
                try {
                    jSONObject.put("pressure", (((((returnData[0] & 255) * 256) + (returnData[1] & 255)) * 300) + PL2303Driver.BAUD150) / 4096);
                    this.f1685p.onNotify(this.f1681l, this.f1682m, "online_pressure_bp", jSONObject.toString());
                    return;
                } catch (JSONException e22222) {
                    e22222.printStackTrace();
                    return;
                }
            case 251:
                byte[] a = m762a(returnData, "BPabi", (byte) -95);
                m758a(252, 4000, 253, 254);
                this.f1680c.packageData(null, a);
                return;
            case 253:
                Map hashMap = new HashMap();
                hashMap.put("subtype", "abi");
                if (this.f1683n.equals(AbiProfile.ABI_ARM)) {
                    hashMap.put("position", "arm");
                    AbiControlSubManager.getInstance().addAbiControlSubUp(this.f1681l);
                } else if (this.f1683n.equals(AbiProfile.ABI_LEG)) {
                    hashMap.put("position", "leg");
                    AbiControlSubManager.getInstance().addAbiControlSubDown(this.f1681l);
                } else if (this.f1683n.equals(AbiProfile.ABI_UNKNOWN)) {
                    hashMap.put("position", "unknown");
                }
                this.f1686q.onConnectionStateChange(this.f1681l, this.f1682m, 1, 0, hashMap);
                return;
            case 254:
                this.f1678a.disconnect();
                return;
            default:
                Log.i("AbiInsSet", "no method");
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1680c.packageData(null, m760a((byte) -95));
    }

    public void interruptMeasure() {
        this.f1680c.packageData(null, new byte[]{(byte) -95, (byte) 55, (byte) 0, (byte) 0, (byte) 0});
        new Timer().schedule(new C21071(this), 500);
    }

    public void startMeasure() {
        this.f1688s = false;
        this.f1680c.packageData(null, new byte[]{(byte) -95, Framer.STDOUT_FRAME_PREFIX, (byte) 0, (byte) 0, (byte) 75, (byte) 0, (byte) 41, (byte) 0, Ascii.ESC, Ascii.RS});
    }
}
