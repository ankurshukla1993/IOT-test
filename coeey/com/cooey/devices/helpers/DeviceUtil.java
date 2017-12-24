package com.cooey.devices.helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.cooey.devices.C0910R;
import com.cooey.devices.vo.DeviceType;
import java.util.HashMap;

public class DeviceUtil {
    private static HashMap<String, String> deviceNameMapper;

    public static String getDeviceName(String deviceName) {
        if (deviceName.equalsIgnoreCase("1257B")) {
            return "Smart Body Analyzer";
        }
        if (deviceName.equalsIgnoreCase("808A0")) {
            return "Blood Pressure Monitor";
        }
        if (deviceName.equalsIgnoreCase("Bp Monitor")) {
            return "Blood Pressure Monitor";
        }
        if (deviceName.equalsIgnoreCase("WeighingScale")) {
            return "Smart Body Analyzer";
        }
        return deviceName;
    }

    public static Drawable getDeviceDrawable(String deviceName, Context context) {
        if (deviceName.equalsIgnoreCase("1257B")) {
            return context.getResources().getDrawable(C0910R.drawable.body_analyser_512);
        }
        if (deviceName.equalsIgnoreCase("808A0")) {
            return context.getResources().getDrawable(C0910R.drawable.bp_monitor);
        }
        if (deviceName.equalsIgnoreCase("Bp Monitor")) {
            return context.getResources().getDrawable(C0910R.drawable.bp_monitor);
        }
        if (deviceName.equalsIgnoreCase("WeighingScale")) {
            return context.getResources().getDrawable(C0910R.drawable.body_analyser_512);
        }
        if (deviceName.equalsIgnoreCase("Voice BP Monitor")) {
            return context.getResources().getDrawable(C0910R.drawable.voice_bp_monitor);
        }
        if (deviceName.equalsIgnoreCase("iHealth Blood Pressure Monitor")) {
            return context.getResources().getDrawable(C0910R.drawable.ihealth_bp5_monitor);
        }
        if (deviceName.equalsIgnoreCase("iHealth Pulse Oximeter")) {
            return context.getResources().getDrawable(C0910R.drawable.ihealth_pulse_oximeter3);
        }
        if (deviceName.equalsIgnoreCase("iHealth Smart Glucometer")) {
            return context.getResources().getDrawable(C0910R.drawable.ihealth_bg5_glucose_meters);
        }
        return context.getResources().getDrawable(C0910R.drawable.bp_monitor);
    }

    public static DeviceType getDeviceTypeFromName(String deviceName) {
        if (deviceName.equalsIgnoreCase("1257B")) {
            return DeviceType.BODY_ANALYZER;
        }
        if (deviceName.equalsIgnoreCase("Voice BP Monitor")) {
            return DeviceType.NEWBPMETER;
        }
        if (deviceName.equalsIgnoreCase("Bp Monitor")) {
            return DeviceType.SPYHGOMANOMETER;
        }
        if (deviceName.equalsIgnoreCase("WeighingScale")) {
            return DeviceType.BODY_ANALYZER;
        }
        if (deviceName.equalsIgnoreCase("iHealth Blood Pressure Monitor")) {
            return DeviceType.IHEALTH_BP5_MONITOR;
        }
        if (deviceName.contains("Oximeter") || deviceName.contains("oximeter")) {
            return DeviceType.IHEALTH_PULSE_OXIMETER_3;
        }
        if (deviceName.equalsIgnoreCase("iHealth Smart Glucometer")) {
            return DeviceType.IHEALTH_BG5_MONITOR;
        }
        return DeviceType.SPYHGOMANOMETER;
    }
}
