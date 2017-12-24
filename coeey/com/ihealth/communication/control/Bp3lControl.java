package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class Bp3lControl implements DeviceControl {
    private Context f1103a;
    private A1InsSet f1104b;
    private BaseComm f1105c;
    private String f1106d;
    private C2023a f1107e;

    public Bp3lControl(Context context, BaseComm com, String userName, String mac, String name, BaseCommCallback mBaseCommCallback, InsCallback insCallback) {
        this.f1105c = com;
        this.f1103a = context;
        this.f1106d = mac;
        this.f1104b = new A1InsSet(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1107e = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1107e);
    }

    public void destroy() {
        this.f1103a = null;
        if (this.f1104b != null) {
            this.f1104b.destroy();
        }
        this.f1104b = null;
        this.f1105c = null;
    }

    public void disconnect() {
        this.f1105c.disconnect(this.f1106d);
    }

    public void getBattery() {
        this.f1107e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cj(this));
    }

    public void init() {
        this.f1104b.identify();
    }

    public void interruptMeasure() {
        this.f1104b.interruptMeasure();
    }

    public void startMeasure() {
        this.f1107e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_STOP_BP, "error_bp", "online_result_bp", BpProfile.ACTION_INTERRUPTED_BP, iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new ck(this));
    }
}
