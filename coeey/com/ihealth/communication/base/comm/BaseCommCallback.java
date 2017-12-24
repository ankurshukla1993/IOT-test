package com.ihealth.communication.base.comm;

import java.util.Map;

public abstract class BaseCommCallback {
    public void onConnectionStateChange(String mac, String type, int status, int errorID, Map manufactorData) {
    }

    public void onScanDevice(String mac, String type) {
    }
}
