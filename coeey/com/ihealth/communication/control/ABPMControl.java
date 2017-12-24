package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforABPM;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class ABPMControl implements DeviceControl {
    public static final int UNIT_KPA = 2;
    public static final int UNIT_MMHG = 1;
    private Context f1006a;
    private A1InsSetforABPM f1007b;
    private BaseComm f1008c;
    private String f1009d;
    private C2023a f1010e;

    public ABPMControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1008c = com;
        this.f1006a = context;
        this.f1009d = mac;
        this.f1007b = new A1InsSetforABPM(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1010e = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1010e);
    }

    public void deleteAllMemory() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_DELETE_ALL_MEMORY_SUCCESS_ABPM, "error_bp"}), 4500, new C2070c(this));
    }

    public void destroy() {
        if (this.f1007b != null) {
            this.f1007b.destroy();
        }
        this.f1007b = null;
        this.f1006a = null;
        this.f1008c = null;
    }

    public void disconnect() {
        this.f1008c.disconnect(this.f1009d);
    }

    public void getAlarmSetting() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_GET_ALARM_SETTING_ABPM, "error_bp"}), 4500, new C2077i(this));
    }

    public void getAlarmType() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_ALARM_TYPE_ABPM, "error_bp"}), 4500, new C2078j(this));
    }

    public void getBattery() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_ABPM, "error_bp"}), 4500, new C2068a(this));
    }

    public void getFunctionInfo() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_FUNCTION_INFORMATION_ABPM, "error_bp"}), 4500, new C2071d(this));
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1009d);
    }

    public void getMeasureTime() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_GET_CYCLE_MEASURE_ABPM, "error_bp"}), 4500, new C2075g(this));
    }

    public void getOfflineData() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_DATA_ABPM, "error_bp"}), 4500, new C2069b(this));
    }

    public void getOfflineNum() {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_NUM_ABPM, "error_bp"}), 4500, new C2079k(this));
    }

    public void init() {
        this.f1007b.identify();
    }

    public void setAlarm(int[]... args) {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_SET_ALARM_ABPM, "error_bp"}), 4500, new C2076h(this, args));
    }

    public void setDisplayUnit(int unit) {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_SET_UNIT_SUCCESS_ABPM, "error_bp"}), 4500, new C2073e(this, unit));
    }

    public void setMeasureTime(int measureTimeLength, boolean isMedicine, int[]... times) {
        this.f1010e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_SET_CYCLE_MEASURE_SUCCESS_ABPM, "error_bp"}), 4500, new C2074f(this, measureTimeLength, isMedicine, times));
    }
}
