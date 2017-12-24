package com.ihealth.communication.control;

import com.ihealth.communication.utils.FirmWare;
import java.util.List;

class df implements UpDeviceControl {
    final /* synthetic */ Bp5sControl f1379a;

    df(Bp5sControl bp5sControl) {
        this.f1379a = bp5sControl;
    }

    public void borrowComm() {
        this.f1379a.f1124b.getBaseCommProtocol().setInsSet(this.f1379a.f1129g);
        this.f1379a.f1129g.setCurrentState(this.f1379a.f1126d, true);
    }

    public String getCurrentMac() {
        return this.f1379a.f1128f;
    }

    public boolean isUpgradeState() {
        return this.f1379a.f1129g.getCurrentState(this.f1379a.f1126d);
    }

    public void judgUpgrade() {
        this.f1379a.f1129g.queryInformation();
    }

    public void returnComm() {
        this.f1379a.f1129g.getBaseCommProtocol().setInsSet(this.f1379a.f1124b);
        this.f1379a.f1129g.setCurrentState(this.f1379a.f1126d, false);
    }

    public void setCurrentMac(String mac) {
        this.f1379a.f1128f = mac;
    }

    public void setData(FirmWare firmware, List list) {
        this.f1379a.f1129g.setFirmWare(firmware, list);
    }

    public void setInformation(List list) {
        this.f1379a.f1129g.setInfo(list);
    }

    public void startUpgrade() {
        this.f1379a.f1129g.startUpdate();
    }

    public void stopUpgrade() {
        this.f1379a.f1129g.stopUpdate();
    }
}
