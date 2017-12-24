package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.HsInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HsControl implements DeviceControl {
    private final String f1197a = getClass().getName();
    private C2023a f1198b;
    private BaseComm f1199c;
    private Context f1200d;
    private String f1201e;
    private String f1202f;
    private HsInsSet f1203g;
    private BaseCommCallback f1204h;

    public HsControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1199c = mBaseComm;
        this.f1200d = context;
        this.f1201e = mac;
        this.f1202f = type;
        this.f1204h = mBaseCommCallback;
        this.f1203g = new HsInsSet(context, mBaseComm, bleComm, userName, mac, type, insCallback, mBaseCommCallback);
        this.f1198b = new C2023a(mac, type, HsProfile.ACTION_ERROR_HS);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1198b);
    }

    private void m605a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1198b.m232a(arrayList, 4500, c2026d);
    }

    private void m606b(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1198b.m232a(arrayList, -1, c2026d);
    }

    public void destroy() {
        if (this.f1203g != null) {
            this.f1203g.destroy();
        }
        this.f1203g = null;
        this.f1200d = null;
        this.f1199c = null;
    }

    public void disconnect() {
        this.f1199c.disconnect(this.f1201e);
    }

    public void getBattery() {
        m606b(new em(this), HsProfile.ACTION_BATTERY_CHS, new String[0]);
    }

    public void getFeature() {
        this.f1203g.getFeature();
    }

    public void getMeasurement() {
        m605a(new en(this), HsProfile.ACTION_CHS_MEASUREMENT_DATA, new String[0]);
    }

    public void init() {
        this.f1204h.onConnectionStateChange(this.f1201e, this.f1202f, 1, 0, null);
    }
}
