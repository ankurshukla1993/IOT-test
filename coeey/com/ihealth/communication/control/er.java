package com.ihealth.communication.control;

import com.ihealth.communication.utils.FirmWare;
import java.util.List;

class er implements UpDeviceControl {
    final /* synthetic */ Po3Control f1452a;

    er(Po3Control po3Control) {
        this.f1452a = po3Control;
    }

    public void borrowComm() {
        this.f1452a.f1207c.getBaseCommProtocol().setInsSet(this.f1452a.f1208d);
        this.f1452a.f1208d.setCurrentState(this.f1452a.f1209e, true);
    }

    public String getCurrentMac() {
        return this.f1452a.f1211g;
    }

    public boolean isUpgradeState() {
        return this.f1452a.f1208d.getCurrentState(this.f1452a.f1209e);
    }

    public void judgUpgrade() {
        this.f1452a.f1208d.queryInformation();
    }

    public void returnComm() {
        this.f1452a.f1208d.getBaseCommProtocol().setInsSet(this.f1452a.f1207c);
        this.f1452a.f1208d.setCurrentState(this.f1452a.f1209e, false);
    }

    public void setCurrentMac(String mac) {
        this.f1452a.f1211g = mac;
    }

    public void setData(FirmWare firmware, List list) {
        this.f1452a.f1208d.setFirmWare(firmware, list);
    }

    public void setInformation(List list) {
        this.f1452a.f1208d.setInfo(list);
    }

    public void startUpgrade() {
        this.f1452a.f1208d.startUpdate();
    }

    public void stopUpgrade() {
        this.f1452a.f1208d.stopUpdate();
    }
}
