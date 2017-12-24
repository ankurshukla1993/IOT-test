package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class Bp7Control implements DeviceControl {
    private Context f1135a;
    private A1InsSet f1136b;
    private BaseComm f1137c;
    private String f1138d;
    private C2023a f1139e;

    public Bp7Control(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1137c = com;
        this.f1135a = context;
        this.f1138d = mac;
        this.f1136b = new A1InsSet(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1139e = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1139e);
    }

    public void conformAngle() {
        this.f1136b.angleYes();
    }

    public void destroy() {
        if (this.f1136b != null) {
            this.f1136b.destroy();
        }
        this.f1136b = null;
        this.f1135a = null;
        this.f1137c = null;
    }

    public void disableOffline() {
        this.f1139e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_DISENABLE_OFFLINE_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new di(this));
    }

    public void disconnect() {
        this.f1137c.disconnect();
    }

    public void enbleOffline() {
        this.f1139e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_ENABLE_OFFLINE_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dh(this));
    }

    public void getAngle() {
        this.f1136b.angleYes();
    }

    public void getBattery() {
        this.f1139e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dg(this));
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1138d);
    }

    public void getOfflineData() {
        this.f1139e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_DATA_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new dk(this));
    }

    public void getOfflineNum() {
        this.f1139e.m232a(Arrays.asList(new String[]{"offlinenum", "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dj(this));
    }

    public void init() {
        this.f1136b.getIdps();
    }

    public void interruptMeasure() {
        this.f1136b.interruptMeasure();
    }

    public void isEnableOffline() {
        this.f1139e.m232a(Arrays.asList(new String[]{"offlinestatus", "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dl(this));
    }

    public void startMeasure() {
        this.f1139e.m232a(Arrays.asList(new String[]{"online_result_bp", BpProfile.ACTION_INTERRUPTED_BP, BpProfile.ACTION_STOP_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new dm(this));
    }
}
