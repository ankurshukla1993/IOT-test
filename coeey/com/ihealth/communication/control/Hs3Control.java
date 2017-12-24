package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.Hs3InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hs3Control implements DeviceControl {
    protected Context f1163a;
    protected Hs3InsSet f1164b;
    private final C2023a f1165c;
    private BaseComm f1166d;
    private String f1167e;

    public Hs3Control(String userName, BaseComm com, Context context, String deviceMac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1166d = com;
        this.f1163a = context;
        this.f1167e = userName;
        this.f1164b = new Hs3InsSet(userName, com, context, deviceMac, type, baseCommCallback, insCallback);
        this.f1165c = new C2023a(deviceMac, type, HsProfile.ACTION_ERROR_HS);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(deviceMac, this.f1165c);
    }

    private void m583a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1165c.m232a(arrayList, -1, c2026d);
    }

    public void destroy() {
        if (this.f1164b != null) {
            this.f1164b.destroy();
        }
        this.f1164b = null;
        this.f1166d = null;
        this.f1163a = null;
    }

    public void disconnect() {
        this.f1166d.disconnect();
    }

    public void getOfflineData() {
        m583a(new dz(this), HsProfile.ACTION_HISTORICAL_DATA_HS, HsProfile.ACTION_NO_HISTORICALDATA, HsProfile.ACTION_ERROR_HS);
    }

    public void init() {
        this.f1164b.createChannel();
    }
}
