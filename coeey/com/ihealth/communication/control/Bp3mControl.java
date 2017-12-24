package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class Bp3mControl implements DeviceControl {
    private Context f1108a;
    private A1InsSet f1109b;
    private BaseComm f1110c;
    private String f1111d;
    private C2023a f1112e;

    public Bp3mControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1110c = com;
        this.f1108a = context;
        this.f1111d = mac;
        this.f1109b = new A1InsSet(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1112e = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1112e);
    }

    public void destroy() {
    }

    public void disconnect() {
    }

    public void getBattery() {
        this.f1112e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cl(this));
    }

    public void init() {
        this.f1109b.getIdps();
    }

    public void interruptMeasure() {
        this.f1109b.interruptMeasure();
    }

    public void startMeasure() {
        this.f1112e.m232a(Arrays.asList(new String[]{"online_result_bp", BpProfile.ACTION_STOP_BP, BpProfile.ACTION_INTERRUPTED_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT, BpProfile.ACTION_RESET_BP3M}), -1, new cm(this));
    }
}
