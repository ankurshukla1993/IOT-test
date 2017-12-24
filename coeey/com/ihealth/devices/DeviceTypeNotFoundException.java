package com.ihealth.devices;

public class DeviceTypeNotFoundException extends Throwable {
    public DeviceTypeNotFoundException(String deviceType) {
        super("The device type '" + deviceType + "' was not found");
    }
}
