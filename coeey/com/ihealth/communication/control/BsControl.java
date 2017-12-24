package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BsInsSet;
import com.ihealth.communication.ins.InsCallback;

public class BsControl implements DeviceControl {
    private final String f1148a = getClass().getName();
    private BaseComm f1149b;
    private Context f1150c;
    private String f1151d;
    private String f1152e;
    private BsInsSet f1153f;
    private BaseCommCallback f1154g;

    public BsControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1149b = mBaseComm;
        this.f1150c = context;
        this.f1151d = mac;
        this.f1152e = type;
        this.f1154g = mBaseCommCallback;
        this.f1153f = new BsInsSet(context, mBaseComm, bleComm, userName, mac, type, insCallback, mBaseCommCallback);
    }

    public void destroy() {
        if (this.f1153f != null) {
            this.f1153f.destroy();
        }
        this.f1153f = null;
        this.f1150c = null;
        this.f1149b = null;
    }

    public void disconnect() {
        this.f1149b.disconnect(this.f1151d);
    }

    public void getBattery() {
        this.f1153f.getBattery();
    }

    public void getFeature() {
        this.f1153f.getFeature();
    }

    public void getMeasurement() {
        this.f1153f.getMeasurement();
    }

    public void init() {
        this.f1154g.onConnectionStateChange(this.f1151d, this.f1152e, 1, 0, null);
    }
}
