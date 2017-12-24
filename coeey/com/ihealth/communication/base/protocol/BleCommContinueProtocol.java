package com.ihealth.communication.base.protocol;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.NewDataCallback;

public class BleCommContinueProtocol implements BaseCommProtocol {
    private BaseComm f352a;
    private String f353b;
    private NewDataCallback f354c;

    public BleCommContinueProtocol(BaseComm com, String mac, NewDataCallback commCallback) {
        this.f352a = com;
        this.f353b = mac;
        this.f354c = commCallback;
        com.addCommContinueNotify(mac, this);
    }

    public void destroy() {
    }

    public void packageData(String mac, byte[] ins) {
    }

    public void packageData(byte[] ins) {
    }

    public void packageDataAsk(byte[] returnCommand) {
    }

    public void packageDataFinish() {
    }

    public void setInsSet(NewDataCallback dataCallback) {
    }

    public void unPackageData(byte[] data) {
    }

    public void unPackageDataUuid(String uuid, byte[] data) {
        this.f354c.haveNewDataUuid(uuid, data);
    }
}
