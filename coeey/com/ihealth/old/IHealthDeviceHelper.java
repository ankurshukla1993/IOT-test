package com.ihealth.old;

import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.devices.DeviceTypeNotFoundException;
import java.lang.reflect.Field;

public class IHealthDeviceHelper {
    public static String getDeviceNameFromType(String type) throws DeviceTypeNotFoundException {
        Object obj = -1;
        switch (type.hashCode()) {
            case 65959:
                if (type.equals(iHealthDevicesManager.TYPE_BP5)) {
                    obj = 1;
                    break;
                }
                break;
            case 79380:
                if (type.equals(iHealthDevicesManager.TYPE_PO3)) {
                    obj = null;
                    break;
                }
                break;
            case 2226410:
                if (type.equals(iHealthDevicesManager.TYPE_HS4S)) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return "Pulse Oximeter";
            case 1:
                return "Blood Pressure Monitor";
            case 2:
                return "Wireless Body Analyzer";
            default:
                throw new DeviceTypeNotFoundException(type);
        }
    }

    static long getLongTypeFromStringDeviceType(String type) throws DeviceTypeNotFoundException {
        Field[] fields = iHealthDevicesManager.class.getFields();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (fieldName.contains("DISCOVERY_") && fieldName.substring(10).contentEquals(type)) {
                try {
                    return field.getLong(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new DeviceTypeNotFoundException(type);
    }
}
