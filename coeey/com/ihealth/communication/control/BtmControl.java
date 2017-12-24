package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BtmInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BtmControl implements DeviceControl {
    public static final Byte FUNCTION_TARGET_OFFLINE = Byte.valueOf((byte) 2);
    public static final Byte FUNCTION_TARGET_ONLINE = Byte.valueOf((byte) 1);
    public static final Byte MEASURING_TARGET_BODY = Byte.valueOf((byte) 1);
    public static final Byte MEASURING_TARGET_OBJECT = Byte.valueOf((byte) 2);
    public static final Byte TEMPERATURE_UNIT_C = Byte.valueOf((byte) 1);
    public static final Byte TEMPERATURE_UNIT_F = Byte.valueOf((byte) 2);
    private C2023a f1155a;
    private Context f1156b;
    private BtmInsSet f1157c;
    private BaseComm f1158d;
    private String f1159e;
    private String f1160f;
    private BaseCommCallback f1161g;

    public BtmControl(Context context, BaseComm comm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1158d = comm;
        this.f1156b = context;
        this.f1159e = mac;
        this.f1160f = type;
        this.f1161g = mBaseCommCallback;
        this.f1157c = new BtmInsSet(context, comm, userName, mac, type, insCallback, mBaseCommCallback);
        this.f1155a = new C2023a(mac, type, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1155a);
    }

    private void m581a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1155a.m232a(arrayList, 4500, c2026d);
    }

    private void m582b(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1155a.m232a(arrayList, -1, c2026d);
    }

    public void destroy() {
        if (this.f1157c != null) {
            this.f1157c.destroy();
        }
        this.f1157c = null;
        this.f1156b = null;
        this.f1158d = null;
    }

    public void disconnect() {
        this.f1158d.disconnect(this.f1159e);
    }

    public void getBattery() {
        m582b(new dt(this), BtmProfile.ACTION_BTM_BATTERY, BtmProfile.ACTION_BTM_CALLBACK);
    }

    public void getMemoryData() {
        m581a(new dy(this), BtmProfile.ACTION_BTM_MEMORY, BtmProfile.ACTION_BTM_CALLBACK);
    }

    public void init() {
        this.f1157c.identify();
    }

    public void setMeasuringTarget(byte target) {
        m581a(new dw(this, target), BtmProfile.ACTION_BTM_CALLBACK, new String[0]);
    }

    public void setOfflineTarget(byte target) {
        m581a(new dx(this, target), BtmProfile.ACTION_BTM_CALLBACK, new String[0]);
    }

    public void setStandbyTime(int hour, int minute, int second) {
        m581a(new du(this, hour, minute, second), BtmProfile.ACTION_BTM_CALLBACK, new String[0]);
    }

    public void setTemperatureUnit(byte unit) {
        m581a(new dv(this, unit), BtmProfile.ACTION_BTM_CALLBACK, new String[0]);
    }
}
