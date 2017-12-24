package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.ins.HS6InsSet;
import com.ihealth.communication.manager.iHealthDeviceHs6Callback;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;

public class HS6Control {
    public static final String ACTION_HS6_BIND = "hs6_bind";
    public static final String ACTION_HS6_ERROR = "hs6_error";
    public static final String ACTION_HS6_GET_TOKEN = "hs6_get_token";
    public static final String ACTION_HS6_SETWIFI = "hs6_setwifi";
    public static final String ACTION_HS6_SET_UNIT = "hs6_set_unit";
    public static final String ACTION_HS6_UNBIND = "hs6_unbind";
    public static final String BIND_HS6_RESULT = "bindResult";
    public static final String GET_TOKEN_RESULT = "getTokenResult";
    public static final String HS6_BIND_EXTRA = "bindData";
    public static final String HS6_ERROR = "hs6_error";
    public static final String HS6_MODEL = "model";
    public static final String HS6_POSITION = "position";
    public static final String HS6_SETTED_WIFI = "settedWifi";
    public static final String HS6_UNBIND_RESULT = "unBind";
    public static final String SETWIFI_RESULT = "setWifiResult";
    public static final String SET_UNIT_RESULT = "setUnitResult";
    public static final int Unit_Kg = 0;
    public static final int Unit_Lbs = 1;
    public static final int Unit_St = 2;
    private HS6InsSet f1162a;

    public HS6Control(String userName, Context context, String type, iHealthDeviceHs6Callback hs6Callback) {
        Log.p("HS6Control", Level.INFO, "HS6Control_Constructor", new Object[]{userName, context, type});
        this.f1162a = new HS6InsSet(context, userName, type, hs6Callback);
    }

    public boolean bindDeviceHS6(String birthday, float weight, int height, int isSporter, int gender, String serialNumber) {
        return this.f1162a.bindDeviceHS6(birthday, weight, height, isSporter, gender, serialNumber);
    }

    public boolean getToken(String clientId, String clientSecret, String username, String clientPara) {
        return this.f1162a.getToken(clientId, clientSecret, username, clientPara);
    }

    public boolean setUnit(String username, int unitType) {
        return this.f1162a.setUnit(username, unitType);
    }

    public boolean setWifi(String ssid, String password) {
        return this.f1162a.setWifi(ssid, password);
    }

    public boolean setWifi(String ssid, String password, String deviceKey) {
        return this.f1162a.setWifi(ssid, password, deviceKey);
    }

    public boolean unBindDeviceHS6(String serialNumber) {
        return this.f1162a.unBindDeviceHS6(serialNumber);
    }
}
