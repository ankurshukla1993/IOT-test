package com.ihealth.communication.control;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.Hs5InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class Hs5ControlForBt {
    protected Hs5InsSet f1191a;
    private BaseComm f1192b;
    private BaseCommCallback f1193c;
    private String f1194d;
    private String f1195e;
    private InsCallback f1196f;

    public Hs5ControlForBt(BaseComm mBaseComm, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1192b = mBaseComm;
        this.f1194d = mac;
        this.f1193c = baseCommCallback;
        this.f1195e = type;
        this.f1196f = insCallback;
        this.f1191a = new Hs5InsSet(mac, type, mBaseComm, baseCommCallback, insCallback);
    }

    private void m603a(int i, String str) {
        Log.w("Hs5ControlForBt", str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i);
            jSONObject.put("description", str);
            this.f1196f.onNotify(this.f1194d, this.f1195e, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        this.f1192b.disconnect();
    }

    public void init() {
        this.f1193c.onConnectionStateChange(this.f1194d, iHealthDevicesManager.TYPE_HS5_BT, 1, 0, null);
    }

    public void setWifi(String ssid, int type, String pw) {
        Context context = this.f1192b.getContext();
        if (context == null) {
            throw new RuntimeException("Context is null.");
        } else if (TextUtils.isEmpty(ssid)) {
            m603a(400, "setWifi() ssid is null or empty.");
        } else {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                type = 4;
                for (ScanResult scanResult : wifiManager.getScanResults()) {
                    if (ssid.contains(scanResult.SSID)) {
                        String str = scanResult.capabilities;
                        if (str.contains("WPA2") && str.contains("WPA") && str.contains("Mixed")) {
                            type = 4;
                            this.f1191a.setWifi(ssid, type, pw);
                            return;
                        }
                        if (str.contains("WPA2")) {
                            type = 3;
                        } else if (str.contains("WPA")) {
                            type = 2;
                        } else if (str.contains("WEP")) {
                            type = 1;
                        }
                        this.f1191a.setWifi(ssid, type, pw);
                        return;
                    }
                }
                this.f1191a.setWifi(ssid, type, pw);
                return;
            }
            m603a(500, "setWifi() wifi is disabled, please enable it.");
        }
    }
}
