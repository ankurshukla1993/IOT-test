package com.cooey.devices;

import com.cooey.devices.vo.Device;

public class DevicePairedEvent {
    Device lsDeviceInfo;

    public DevicePairedEvent(Device device) {
        this.lsDeviceInfo = device;
    }

    public Device getLsDeviceInfo() {
        return this.lsDeviceInfo;
    }
}
