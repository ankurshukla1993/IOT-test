package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class Bp5Control implements DeviceControl {
    private Context f1118a;
    private A1InsSet f1119b;
    private BaseComm f1120c;
    private String f1121d;
    private C2023a f1122e;

    public Bp5Control(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1120c = com;
        this.f1118a = context;
        this.f1121d = mac;
        this.f1119b = new A1InsSet(context, com, userName, mac, type, insCallback, mBaseCommCallback);
        this.f1122e = new C2023a(mac, type, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1122e);
    }

    public void destroy() {
        if (this.f1119b != null) {
            this.f1119b.destroy();
        }
        this.f1119b = null;
        this.f1118a = null;
        this.f1120c = null;
    }

    public void disableOffline() {
        this.f1122e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_DISENABLE_OFFLINE_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new ct(this));
    }

    public void disconnect() {
        this.f1120c.disconnect();
    }

    public void enbleOffline() {
        this.f1122e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_ENABLE_OFFLINE_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cs(this));
    }

    public void getBattery() {
        this.f1122e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cr(this));
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1121d);
    }

    public void getOfflineData() {
        this.f1122e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_DATA_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new cv(this));
    }

    public void getOfflineNum() {
        this.f1122e.m232a(Arrays.asList(new String[]{"offlinenum", "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cu(this));
    }

    public void init() {
        this.f1119b.getIdps();
    }

    public void interruptMeasure() {
        this.f1119b.interruptMeasure();
    }

    public void isEnableOffline() {
        this.f1122e.m232a(Arrays.asList(new String[]{"offlinestatus", "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cw(this));
    }

    public void startMeasure() {
        this.f1122e.m232a(Arrays.asList(new String[]{"online_result_bp", BpProfile.ACTION_INTERRUPTED_BP, BpProfile.ACTION_STOP_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new cx(this));
    }
}
