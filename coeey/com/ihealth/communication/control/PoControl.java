package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.ins.PoInsSet;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoControl implements DeviceControl {
    private final String f1213a = getClass().getName();
    private C2023a f1214b;
    private BaseComm f1215c;
    private Context f1216d;
    private String f1217e;
    private String f1218f;
    private PoInsSet f1219g;
    private BaseCommCallback f1220h;

    public PoControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1215c = mBaseComm;
        this.f1216d = context;
        this.f1217e = mac;
        this.f1218f = type;
        this.f1220h = mBaseCommCallback;
        this.f1219g = new PoInsSet(context, mBaseComm, bleComm, userName, mac, type, insCallback, mBaseCommCallback);
        this.f1214b = new C2023a(mac, type, PoProfile.ACTION_ERROR_PO);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1214b);
    }

    private void m614a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1214b.m232a(arrayList, 4500, c2026d);
    }

    private void m615b(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1214b.m232a(arrayList, -1, c2026d);
    }

    public void destroy() {
        if (this.f1219g != null) {
            this.f1219g.destroy();
        }
        this.f1219g = null;
        this.f1216d = null;
        this.f1215c = null;
    }

    public void disconnect() {
        this.f1215c.disconnect(this.f1217e);
    }

    public void getBattery() {
        m615b(new es(this), PoProfile.ACTION_BATTERY_PO, PoProfile.ACTION_ERROR_PO);
    }

    public void getContinuous() {
        m614a(new eu(this), PoProfile.ACTION_CONTINUOUS_CPO, PoProfile.ACTION_ERROR_PO);
    }

    public void getFeatures() {
        this.f1219g.getFeatures();
    }

    public void getRecord() {
        m614a(new ev(this), PoProfile.ACTION_CONTINUOUS_CPO, PoProfile.ACTION_ERROR_PO);
    }

    public void getSpotCheck() {
        m614a(new et(this), PoProfile.ACTION_SPOT_CHECK_CPO, PoProfile.ACTION_ERROR_PO);
    }

    public void init() {
        this.f1220h.onConnectionStateChange(this.f1217e, this.f1218f, 1, 0, null);
    }
}
