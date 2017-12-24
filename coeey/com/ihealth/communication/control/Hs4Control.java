package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A6InsSet;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hs4Control implements DeviceControl {
    private C2023a f1168a;
    private BaseComm f1169b;
    private A6InsSet f1170c;
    private F0InsSet f1171d;
    private String f1172e;
    private String f1173f;
    private String f1174g = null;
    private UpDeviceControl f1175h = new ec(this);

    public Hs4Control(String userName, Context context, BaseComm mBaseComm, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1169b = mBaseComm;
        this.f1172e = mac;
        this.f1173f = userName;
        this.f1170c = new A6InsSet(userName, context, mBaseComm, mac, type, baseCommCallback, insCallback);
        this.f1171d = new F0InsSet(mBaseComm, this.f1170c.getBaseCommProtocol(), context, mac, type, insCallback);
        this.f1168a = new C2023a(mac, type, HsProfile.ACTION_ERROR_HS);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1168a);
    }

    private void m586a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1168a.m232a(arrayList, 4500, c2026d);
    }

    public void destroy() {
        if (this.f1170c != null) {
            this.f1170c.destroy();
        }
        this.f1170c = null;
        if (this.f1171d != null) {
            this.f1171d.destroy();
        }
        this.f1171d = null;
        this.f1169b = null;
    }

    public void disconnect() {
        this.f1169b.disconnect(this.f1172e);
    }

    public void getOfflineData() {
        m586a(new ea(this), HsProfile.ACTION_HISTORICAL_DATA_HS, HsProfile.ACTION_NO_HISTORICALDATA, HsProfile.ACTION_ERROR_HS);
    }

    public UpDeviceControl getUpDeviceControl() {
        return this.f1175h;
    }

    public void init() {
        this.f1170c.identify();
    }

    public void measureOnline(int unit, int userId) {
        m586a(new eb(this, unit, userId), HsProfile.ACTION_ONLINE_RESULT_HS, HsProfile.ACTION_ERROR_HS);
    }
}
