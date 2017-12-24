package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforBp7s;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

public class Bp926Control implements DeviceControl {
    private Context f1131a;
    private A1InsSetforBp7s f1132b;
    private BaseComm f1133c;
    private String f1134d;

    public Bp926Control(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1133c = com;
        this.f1131a = context;
        this.f1134d = mac;
        this.f1132b = new A1InsSetforBp7s(context, com, userName, mac, name, insCallback, mBaseCommCallback);
    }

    public void destroy() {
        if (this.f1132b != null) {
            this.f1132b.destroy();
        }
        this.f1132b = null;
        this.f1131a = null;
        this.f1133c = null;
    }

    public void disconnect() {
        this.f1133c.disconnect(this.f1134d);
    }

    public void getBattery() {
        this.f1132b.getBatteryLevel();
    }

    public void getFunctionInfo() {
        this.f1132b.getFunctionInfo();
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1134d);
    }

    public void getOfflineData(int memSize) {
        this.f1132b.getOfflineData = true;
        this.f1132b.setMemory_Size(memSize);
        this.f1132b.getOffLineDataNum();
    }

    public void getOfflineDataOver() {
        this.f1132b.offlineDataOver();
    }

    public void getOfflineNum(int memSize) {
        this.f1132b.getOfflineData = false;
        this.f1132b.setMemory_Size(memSize);
        this.f1132b.getOffLineDataNum();
    }

    public void init() {
        this.f1132b.identify();
    }
}
