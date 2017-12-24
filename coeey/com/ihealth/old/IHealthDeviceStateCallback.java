package com.ihealth.old;

public interface IHealthDeviceStateCallback {
    void OnDeviceDiscoveryFinished();

    void onDeviceConnected(IHealthDevice iHealthDevice);

    void onDeviceConnectionFailed(String str, int i);

    void onDeviceDisconnected(IHealthDevice iHealthDevice);

    void onDeviceDiscovered(IHealthDevice iHealthDevice);
}
