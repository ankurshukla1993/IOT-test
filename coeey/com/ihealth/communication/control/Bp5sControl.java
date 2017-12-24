package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforBp5s;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import java.util.Arrays;

public class Bp5sControl implements DeviceControl {
    private Context f1123a;
    private A1InsSetforBp5s f1124b;
    private BaseComm f1125c;
    private String f1126d;
    private C2023a f1127e;
    private String f1128f = null;
    private F0InsSet f1129g;
    private UpDeviceControl f1130h = new df(this);

    public Bp5sControl(Context context, BaseComm com, String userName, String mac, String name, BaseCommCallback mBaseCommCallback, InsCallback insCallback) {
        this.f1125c = com;
        this.f1123a = context;
        this.f1126d = mac;
        this.f1124b = new A1InsSetforBp5s(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1127e = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1127e);
        this.f1129g = new F0InsSet(com, this.f1124b.getBaseCommProtocol(), context, mac, name, insCallback);
    }

    public void deleteRepeatedlyMeasureSetting(int schemeID) {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_DELETE_REPEATEDLY_MEASURE_SETTING_SUCCESS_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new de(this, schemeID));
    }

    public void destroy() {
        if (this.f1124b != null) {
            this.f1124b.destroy();
        }
        this.f1124b = null;
        if (this.f1129g != null) {
            this.f1129g.destroy();
        }
        this.f1129g = null;
        this.f1123a = null;
        this.f1125c = null;
    }

    public void disconnect() {
        this.f1125c.disconnect(this.f1126d);
    }

    public void getBattery() {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cy(this));
    }

    public void getFunctionInfo() {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_FUNCTION_INFORMATION_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new cz(this));
    }

    public void getMeasureStatus() {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_MEASURE_STATUS_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new da(this));
    }

    public void getOfflineData() {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_OVER_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new dc(this));
    }

    public void getOfflineDataNum() {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_NUM_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new db(this));
    }

    public UpDeviceControl getUpDeviceControl() {
        return this.f1130h;
    }

    public void init() {
        this.f1124b.identify();
    }

    public void setRepeatedlyMeasureParameter(int schemeID, int timedMode, int repeatMode, int startHour, int startMinute, int measureTime, int measureIntervalMSB, int measureIntervalLSB) {
        this.f1127e.m232a(Arrays.asList(new String[]{BpProfile.ACTION_SET_REPEATEDLY_MEASURE_SETTING_SUCCESS_BP5S, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dd(this, schemeID, timedMode, repeatMode, startHour, startMinute, measureTime, measureIntervalMSB, measureIntervalLSB));
    }
}
