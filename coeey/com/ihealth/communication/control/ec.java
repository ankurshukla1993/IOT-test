package com.ihealth.communication.control;

import com.ihealth.communication.utils.FirmWare;
import java.util.List;

class ec implements UpDeviceControl {
    final /* synthetic */ Hs4Control f1417a;

    ec(Hs4Control hs4Control) {
        this.f1417a = hs4Control;
    }

    public void borrowComm() {
        this.f1417a.f1170c.getBaseCommProtocol().setInsSet(this.f1417a.f1171d);
        this.f1417a.f1171d.setCurrentState(this.f1417a.f1172e, true);
    }

    public String getCurrentMac() {
        return this.f1417a.f1174g;
    }

    public boolean isUpgradeState() {
        return this.f1417a.f1171d.getCurrentState(this.f1417a.f1172e);
    }

    public void judgUpgrade() {
        this.f1417a.f1171d.queryInformation();
    }

    public void returnComm() {
        this.f1417a.f1171d.getBaseCommProtocol().setInsSet(this.f1417a.f1170c);
        this.f1417a.f1171d.setCurrentState(this.f1417a.f1172e, false);
    }

    public void setCurrentMac(String mac) {
        this.f1417a.f1174g = mac;
    }

    public void setData(FirmWare firmware, List list) {
        this.f1417a.f1171d.setFirmWare(firmware, list);
    }

    public void setInformation(List list) {
        this.f1417a.f1171d.setInfo(list);
    }

    public void startUpgrade() {
        this.f1417a.f1171d.startUpdate();
    }

    public void stopUpgrade() {
        this.f1417a.f1171d.stopUpdate();
    }
}
