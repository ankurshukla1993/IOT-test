package com.ihealth.communication.manager;

import java.util.Map;

public abstract class iHealthDevicesCallback {
    public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID) {
    }

    public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID, Map manufactorData) {
    }

    public void onDeviceNotify(String mac, String deviceType, String action, String message) {
    }

    public void onScanDevice(String mac, String deviceType, int rssi) {
    }

    public void onScanDevice(String mac, String deviceType, int rssi, Map map) {
    }

    public void onScanFinish() {
    }

    public void onUserStatus(String username, int userStatus) {
    }
}
