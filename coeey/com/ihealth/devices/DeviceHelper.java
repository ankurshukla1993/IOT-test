package com.ihealth.devices;

import android.support.v4.app.Fragment;

public class DeviceHelper {
    public static Fragment getFragmentForDeviceType(String mac, String deviceType) throws DeviceTypeNotFoundException {
        throw new DeviceTypeNotFoundException(deviceType);
    }
}
