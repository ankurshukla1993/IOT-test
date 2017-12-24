package com.ihealth.communication.base.wifi;

import com.ihealth.communication.base.comm.BaseComm;

public class WifiSendThread implements Runnable {
    private byte[] f461a;
    private String f462b;
    private String f463c;
    private BaseComm f464d;

    public WifiSendThread(BaseComm comm) {
        this.f464d = comm;
    }

    public void run() {
        this.f464d.sendData(this.f462b, this.f463c, this.f461a);
    }

    public void setData(String address, String deviceIp, byte[] sendDatas) {
        this.f462b = address;
        this.f463c = deviceIp;
        this.f461a = sendDatas;
    }
}
