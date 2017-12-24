package com.ihealth.old;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import com.ihealth.communication.manager.iHealthDevicesManager;

public class IHealthDeviceManager {
    private static final int REQUEST_PERMISSIONS = 4897;
    private int callbackId;
    String clientId = "51d045370f0e40a7b12c80e38ae8ba2b";
    String clientSecret = "387f287243cd49af81aa2c2705d65e39";
    private final Context context;
    private final IHealthDeviceStateCallback ihealthDeviceStateCallback;
    String userName = "ronak@cooey.co.in";

    public IHealthDeviceManager(Context context, IHealthDeviceStateCallback iHealthDeviceStateCallback) {
        this.context = context;
        this.ihealthDeviceStateCallback = iHealthDeviceStateCallback;
    }

    public void initialize() {
        iHealthDevicesManager.getInstance().init(this.context);
        checkPermissions();
    }

    private void checkPermissions() {
        StringBuilder tempRequest = new StringBuilder();
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.BLUETOOTH") != 0) {
            tempRequest.append("android.permission.BLUETOOTH");
        }
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            tempRequest.append("android.permission.WRITE_EXTERNAL_STORAGE,");
        }
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.READ_PHONE_STATE") != 0) {
            tempRequest.append("android.permission.READ_PHONE_STATE,");
        }
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") != 0) {
            tempRequest.append("android.permission.RECORD_AUDIO,");
        }
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            tempRequest.append("android.permission.ACCESS_FINE_LOCATION,");
        }
        if (tempRequest.length() > 0) {
            tempRequest.deleteCharAt(tempRequest.length() - 1);
            ActivityCompat.requestPermissions((Activity) this.context, tempRequest.toString().split(","), REQUEST_PERMISSIONS);
        }
    }

    public void destroy() {
        iHealthDevicesManager.getInstance().unRegisterClientCallback(this.callbackId);
        iHealthDevicesManager.getInstance().destroy();
    }
}
