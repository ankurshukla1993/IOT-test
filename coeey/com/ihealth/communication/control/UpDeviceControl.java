package com.ihealth.communication.control;

import com.ihealth.communication.utils.FirmWare;
import java.util.List;

public interface UpDeviceControl {
    void borrowComm();

    String getCurrentMac();

    boolean isUpgradeState();

    void judgUpgrade();

    void returnComm();

    void setCurrentMac(String str);

    void setData(FirmWare firmWare, List list);

    void setInformation(List list);

    void startUpgrade();

    void stopUpgrade();
}
