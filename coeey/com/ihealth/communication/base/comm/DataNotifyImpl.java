package com.ihealth.communication.base.comm;

public class DataNotifyImpl implements DataNotify {
    private NewDataCallback f351a;

    public void attach(NewDataCallback usbDataCallBack) {
        this.f351a = usbDataCallBack;
    }

    public void detach(NewDataCallback usbDataCallBack) {
    }

    public void haveNewData(int what, int stateId, byte[] command) {
        this.f351a.haveNewData(what, stateId, command);
    }
}
