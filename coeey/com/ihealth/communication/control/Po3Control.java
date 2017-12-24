package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.AcInsSet;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Po3Control implements DeviceControl {
    private C2023a f1205a;
    private BaseComm f1206b;
    private AcInsSet f1207c;
    private F0InsSet f1208d;
    private String f1209e;
    private String f1210f;
    private String f1211g = null;
    private UpDeviceControl f1212h = new er(this);

    public Po3Control(String username, Context context, String accessoryFirm, BaseComm mBaseComm, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1210f = username;
        this.f1206b = mBaseComm;
        this.f1209e = mac;
        this.f1207c = new AcInsSet(username, context, mBaseComm, mac, accessoryFirm, type, baseCommCallback, insCallback);
        this.f1208d = new F0InsSet(mBaseComm, this.f1207c.getBaseCommProtocol(), context, mac, type, insCallback);
        this.f1205a = new C2023a(mac, type, PoProfile.ACTION_ERROR_PO);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1205a);
    }

    private void m609a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1205a.m232a(arrayList, 4500, c2026d);
    }

    public void destroy() {
        if (this.f1207c != null) {
            this.f1207c.destroy();
        }
        this.f1207c = null;
        if (this.f1208d != null) {
            this.f1208d.destroy();
        }
        this.f1208d = null;
        this.f1206b = null;
    }

    public void disconnect() {
        this.f1206b.disconnect(this.f1209e);
    }

    public void getBattery() {
        m609a(new eo(this), PoProfile.ACTION_BATTERY_PO, PoProfile.ACTION_ERROR_PO);
    }

    public void getHistoryData() {
        m609a(new eq(this), PoProfile.ACTION_OFFLINEDATA_PO, PoProfile.ACTION_NO_OFFLINEDATA_PO, PoProfile.ACTION_ERROR_PO);
    }

    public UpDeviceControl getUpDeviceControl() {
        return this.f1212h;
    }

    public void init() {
        this.f1207c.identify();
    }

    public void startMeasure() {
        m609a(new ep(this), PoProfile.ACTION_RESULTDATA_PO, PoProfile.ACTION_ERROR_PO);
    }
}
