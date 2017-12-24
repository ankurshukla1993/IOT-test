package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.wifi.iHealthDeviceBPM1Callback;
import com.ihealth.communication.ins.Bpm1InsSet;
import org.json.JSONObject;

public class Bpm1Control {
    private final Bpm1InsSet f1147a;

    public Bpm1Control(Context context, String type, iHealthDeviceBPM1Callback iHealthDeviceBPM1Callback) {
        this.f1147a = new Bpm1InsSet(context, type, iHealthDeviceBPM1Callback);
    }

    public void connectConfiguration(String wifiConfig) {
        this.f1147a.connectConfiguration(wifiConfig);
    }

    public void connectDevice(String deviceTag, long delay) {
        this.f1147a.connectDevice(deviceTag, delay);
    }

    public void disconnect() {
        this.f1147a.disconnect();
    }

    public void getIDPS() {
        this.f1147a.getIDPS();
    }

    public void getRouters() {
        this.f1147a.getRouters();
    }

    public void sendRouter(JSONObject object) {
        this.f1147a.sendRouter(object);
    }
}
