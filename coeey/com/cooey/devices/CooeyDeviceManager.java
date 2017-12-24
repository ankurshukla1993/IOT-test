package com.cooey.devices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.cooey.devices.selector.DeviceSelectActivity;
import com.cooey.devices.vo.UserInfo;

public class CooeyDeviceManager {
    public static final int DEVICE_READ_REQUEST = 1308;
    private static CooeyDeviceManager cooeyDeviceManager;
    public static UserInfo userInfo;
    private int accentColor;
    private int backgroundColor;
    private DeviceDataRecieveCallback deviceDataRecieveCallback;
    private int primaryColor;
    private int secondaryColor;

    public CooeyDeviceManager setDeviceDataRecieveCallback(DeviceDataRecieveCallback deviceDataRecieveCallback) {
        this.deviceDataRecieveCallback = deviceDataRecieveCallback;
        return this;
    }

    public DeviceDataRecieveCallback getDeviceDataRecieveCallback() {
        return this.deviceDataRecieveCallback;
    }

    public static CooeyDeviceManager getInstance() {
        if (cooeyDeviceManager == null) {
            cooeyDeviceManager = new CooeyDeviceManager();
        }
        return cooeyDeviceManager;
    }

    public CooeyDeviceManager primaryColor(int color) {
        cooeyDeviceManager.primaryColor = color;
        return cooeyDeviceManager;
    }

    public CooeyDeviceManager secondaryColor(int color) {
        cooeyDeviceManager.secondaryColor = color;
        return cooeyDeviceManager;
    }

    public CooeyDeviceManager backgroundColor(int color) {
        cooeyDeviceManager.backgroundColor = color;
        return cooeyDeviceManager;
    }

    public CooeyDeviceManager accentColor(int color) {
        cooeyDeviceManager.accentColor = color;
        return cooeyDeviceManager;
    }

    public CooeyDeviceManager setUserInfo(UserInfo userInfo) {
        userInfo = userInfo;
        return this;
    }

    public void launchDeviceManager(Context context) {
        ((Activity) context).startActivityForResult(new Intent(context, DevicesActivity.class), DEVICE_READ_REQUEST);
    }

    public void launchIHealthDeviceManager(Context context) {
        Intent i = new Intent(context, DevicesActivity.class);
        i.putExtra("DEVICES", "IHEALTH");
        ((Activity) context).startActivityForResult(i, DEVICE_READ_REQUEST);
    }

    public void launchDeviceScanner(Context context) {
        context.startActivity(new Intent(context, ScanDevicesActivity.class));
    }

    public void launchDeviceSelector(Context context) {
        context.startActivity(new Intent(context, DeviceSelectActivity.class));
    }
}
