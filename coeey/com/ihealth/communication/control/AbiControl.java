package com.ihealth.communication.control;

import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;
import java.util.Arrays;

public class AbiControl {
    private String f1011a;
    private C2023a f1012b;

    public AbiControl(String mac) {
        this.f1011a = mac;
        this.f1012b = new C2023a(mac, null, "error_bp");
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1012b);
    }

    public static String getAbiConnected() {
        return AbiControlSubManager.getInstance().getAbiConnected();
    }

    public static String getAbiConnectedForArm() {
        return AbiControlSubManager.getInstance().getAbiConnectedForArm();
    }

    public boolean disconnect() {
        return AbiControlSubManager.getInstance().disconnect(this.f1011a);
    }

    public boolean getBattery() {
        this.f1012b.m232a(Arrays.asList(new String[]{AbiProfile.ACTION_BATTERY_ABI, "error_bp"}), 2000, new C2080l(this));
        return true;
    }

    public boolean startMeasure() {
        this.f1012b.m232a(Arrays.asList(new String[]{"online_result_bp", AbiProfile.ACTION_STOP_ABI, AbiProfile.ACTION_INTERRUPTED_ABI, "error_bp"}), -1, new C2081m(this));
        return true;
    }

    public boolean stopMeasure() {
        return AbiControlSubManager.getInstance().stopMeasure(this.f1011a);
    }
}
