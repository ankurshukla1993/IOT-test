package com.lifesense.ble;

import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.WeightData_A2;

public interface OnLsDeviceConnectListener {
    void onConnectStateChange(DeviceConnectState deviceConnectState);

    void onReceiveDeviceInfo(LsDeviceInfo lsDeviceInfo);

    void onReceiveMeasuredData(WeightData_A2 weightData_A2);
}
