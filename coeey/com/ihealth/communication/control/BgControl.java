package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BgInsSet;
import com.ihealth.communication.ins.InsCallback;

public class BgControl implements DeviceControl {
    private final String f1096a = getClass().getName();
    private BaseComm f1097b;
    private Context f1098c;
    private String f1099d;
    private String f1100e;
    private BgInsSet f1101f;
    private BaseCommCallback f1102g;

    public BgControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1097b = mBaseComm;
        this.f1098c = context;
        this.f1099d = mac;
        this.f1100e = type;
        this.f1102g = mBaseCommCallback;
        this.f1101f = new BgInsSet(context, mBaseComm, bleComm, userName, mac, type, insCallback, mBaseCommCallback);
    }

    public void destroy() {
        if (this.f1101f != null) {
            this.f1101f.destroy();
        }
        this.f1101f = null;
        this.f1098c = null;
        this.f1097b = null;
    }

    public void disconnect() {
        this.f1097b.disconnect(this.f1099d);
    }

    public void getBattery() {
        this.f1101f.getBattery();
    }

    public void getFeature() {
        this.f1101f.getFeature();
    }

    public void getMeasurement() {
        this.f1101f.getMeasurement();
    }

    public void getMeasurementContext() {
        this.f1101f.getMeasurementContext();
    }

    public void getRecord() {
        this.f1101f.getRecord();
    }

    public void init() {
        this.f1102g.onConnectionStateChange(this.f1099d, this.f1100e, 1, 0, null);
    }
}
