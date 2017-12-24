package com.ihealth.communication.privatecontrol;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.control.DeviceControl;
import com.ihealth.communication.ins.AbiInsSet;
import com.ihealth.communication.ins.InsCallback;

public class AbiControlSub implements DeviceControl {
    private Context f2037a;
    private AbiInsSet f2038b;
    private BaseComm f2039c;
    private String f2040d;

    public AbiControlSub(Context context, BaseComm comm, BaseCommProtocol commProtocol, String username, String mac, String type, String abiType, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f2037a = context;
        this.f2039c = comm;
        this.f2040d = mac;
        this.f2038b = new AbiInsSet(context, comm, commProtocol, username, mac, type, abiType, insCallback, mBaseCommCallback);
    }

    public void destroy() {
    }

    public void disconnect() {
        this.f2039c.disconnect();
    }

    public void getBattery() {
        this.f2038b.getBatteryLevel();
    }

    public void init() {
        this.f2038b.identify();
    }

    public void interruptMeasure() {
        this.f2038b.interruptMeasure();
    }

    public void startMeasure() {
        this.f2038b.startMeasure();
    }
}
