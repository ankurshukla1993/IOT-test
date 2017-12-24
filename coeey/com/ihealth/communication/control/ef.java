package com.ihealth.communication.control;

import com.ihealth.communication.utils.FirmWare;
import java.util.List;

class ef implements UpDeviceControl {
    final /* synthetic */ Hs4sControl f1422a;

    ef(Hs4sControl hs4sControl) {
        this.f1422a = hs4sControl;
    }

    public void borrowComm() {
        this.f1422a.f1178c.getBaseCommProtocol().setInsSet(this.f1422a.f1179d);
        this.f1422a.f1179d.setCurrentState(this.f1422a.f1180e, true);
    }

    public String getCurrentMac() {
        return this.f1422a.f1181f;
    }

    public boolean isUpgradeState() {
        return this.f1422a.f1179d.getCurrentState(this.f1422a.f1180e);
    }

    public void judgUpgrade() {
        this.f1422a.f1179d.queryInformation();
    }

    public void returnComm() {
        this.f1422a.f1179d.getBaseCommProtocol().setInsSet(this.f1422a.f1178c);
        this.f1422a.f1179d.setCurrentState(this.f1422a.f1180e, false);
    }

    public void setCurrentMac(String mac) {
        this.f1422a.f1181f = mac;
    }

    public void setData(FirmWare firmware, List list) {
        this.f1422a.f1179d.setFirmWare(firmware, list);
    }

    public void setInformation(List list) {
        this.f1422a.f1179d.setInfo(list);
    }

    public void startUpgrade() {
        this.f1422a.f1179d.startUpdate();
    }

    public void stopUpgrade() {
        this.f1422a.f1179d.stopUpdate();
    }
}
