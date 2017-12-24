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

public class Hs4sControl implements DeviceControl {
    private C2023a f1176a;
    private BaseComm f1177b;
    private A6InsSet f1178c;
    private F0InsSet f1179d;
    private String f1180e;
    private String f1181f = null;
    private UpDeviceControl f1182g = new ef(this);

    public Hs4sControl(String userName, Context context, BaseComm mBaseComm, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1177b = mBaseComm;
        this.f1180e = mac;
        this.f1178c = new A6InsSet(userName, context, mBaseComm, mac, type, baseCommCallback, insCallback);
        this.f1179d = new F0InsSet(mBaseComm, this.f1178c.getBaseCommProtocol(), context, mac, type, insCallback);
        this.f1176a = new C2023a(mac, type, HsProfile.ACTION_ERROR_HS);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1176a);
    }

    private void m592a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1176a.m232a(arrayList, 4500, c2026d);
    }

    public void destroy() {
        if (this.f1178c != null) {
            this.f1178c.destroy();
        }
        this.f1178c = null;
        if (this.f1179d != null) {
            this.f1179d.destroy();
        }
        this.f1179d = null;
        this.f1177b = null;
    }

    public void disconnect() {
        this.f1177b.disconnect();
    }

    public void getOfflineData() {
        m592a(new ed(this), HsProfile.ACTION_HISTORICAL_DATA_HS, HsProfile.ACTION_NO_HISTORICALDATA, HsProfile.ACTION_ERROR_HS);
    }

    public UpDeviceControl getUpDeviceControl() {
        return this.f1182g;
    }

    public void init() {
        this.f1178c.getIdps();
    }

    public void measureOnline(int unit, int userId) {
        m592a(new ee(this, unit, userId), HsProfile.ACTION_ONLINE_RESULT_HS, HsProfile.ACTION_ERROR_HS);
    }
}
