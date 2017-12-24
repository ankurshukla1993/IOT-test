package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforBp7s;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class Bp550BTControl implements DeviceControl {
    private Context f1113a;
    private A1InsSetforBp7s f1114b;
    private BaseComm f1115c;
    private String f1116d;
    private C2023a f1117e;

    public Bp550BTControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1115c = com;
        this.f1113a = context;
        this.f1116d = mac;
        this.f1114b = new A1InsSetforBp7s(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1117e = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1117e);
    }

    public void destroy() {
        if (this.f1114b != null) {
            this.f1114b.destroy();
        }
        this.f1114b = null;
        this.f1113a = null;
        this.f1115c = null;
    }

    public void disconnect() {
        this.f1115c.disconnect(this.f1116d);
    }

    public void getBattery() {
        this.f1117e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cn(this));
    }

    public void getFunctionInfo() {
        this.f1117e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_FUNCTION_INFORMATION_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cq(this));
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1116d);
    }

    public void getOfflineData() {
        this.f1117e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_OVER_BP, BpProfile.ACTION_HISTORICAL_DATA_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new cp(this));
    }

    public void getOfflineNum() {
        this.f1117e.m232a(Arrays.asList(new String[]{"offlinenum", "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new co(this));
    }

    public void init() {
        this.f1114b.identify();
    }
}
