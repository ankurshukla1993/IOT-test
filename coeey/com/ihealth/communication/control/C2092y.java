package com.ihealth.communication.control;

import com.ihealth.communication.utils.FirmWare;
import java.util.List;

class C2092y implements UpDeviceControl {
    final /* synthetic */ C2067n f1483a;

    C2092y(C2067n c2067n) {
        this.f1483a = c2067n;
    }

    public void borrowComm() {
        this.f1483a.f1014b.getBaseCommProtocol().setInsSet(this.f1483a.f1015c);
        this.f1483a.f1015c.setCurrentState(this.f1483a.f1019g, true);
    }

    public String getCurrentMac() {
        return this.f1483a.f1020h;
    }

    public boolean isUpgradeState() {
        return this.f1483a.f1015c.getCurrentState(this.f1483a.f1019g);
    }

    public void judgUpgrade() {
        this.f1483a.f1015c.queryInformation();
    }

    public void returnComm() {
        this.f1483a.f1015c.getBaseCommProtocol().setInsSet(this.f1483a.f1014b);
        this.f1483a.f1015c.setCurrentState(this.f1483a.f1019g, false);
    }

    public void setCurrentMac(String mac) {
        this.f1483a.f1020h = mac;
    }

    public void setData(FirmWare firmware, List list) {
        this.f1483a.f1015c.setFirmWare(firmware, list);
    }

    public void setInformation(List list) {
        this.f1483a.f1015c.setInfo(list);
    }

    public void startUpgrade() {
        this.f1483a.f1015c.startUpdate();
    }

    public void stopUpgrade() {
        this.f1483a.f1015c.stopUpdate();
    }
}
