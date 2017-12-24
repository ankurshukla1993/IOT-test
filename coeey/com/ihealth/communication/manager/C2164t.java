package com.ihealth.communication.manager;

import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

class C2164t extends Thread {
    final /* synthetic */ JSONObject f2027a;
    final /* synthetic */ String f2028b;
    final /* synthetic */ String f2029c;
    final /* synthetic */ iHealthDevicesUpgradeManager f2030d;

    C2164t(iHealthDevicesUpgradeManager com_ihealth_communication_manager_iHealthDevicesUpgradeManager, JSONObject jSONObject, String str, String str2) {
        this.f2030d = com_ihealth_communication_manager_iHealthDevicesUpgradeManager;
        this.f2027a = jSONObject;
        this.f2028b = str;
        this.f2029c = str2;
    }

    public void run() {
        try {
            String str = (String) this.f2027a.get(UpgradeProfile.DEVICE_TYPE);
            String str2 = (String) this.f2027a.get("up.device.up.mode");
            String str3 = (String) this.f2027a.get(UpgradeProfile.DEVICE_HARDWARE_VERSION);
            String str4 = (String) this.f2027a.get(UpgradeProfile.DEVICE_FIRMWARE_VERSION);
            if (str == null || str.length() == 0 || str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0 || str4 == null || str4.length() == 0) {
                if (this.f2030d.isUpgradeState(this.f2028b, this.f2029c)) {
                    this.f2030d.stopUpgrade(this.f2028b, this.f2029c);
                }
                if (this.f2030d.f2002i != null) {
                    this.f2030d.f2002i.returnComm();
                }
                this.f2030d.m1166a(this.f2028b, this.f2029c, 200);
                return;
            }
            Log.m1214v("iHealthDUM", "query info: firmwareVersion:" + str4 + " - productNum:" + str + " - productModel:" + str2 + " - hardwareVersion:" + str3);
            if (this.f2030d.f1999f) {
                if (this.f2030d.f2003j.a(str4, str, str2, str3, "")) {
                    String str5 = this.f2030d.f2003j.f.b;
                    str4 = str5.equals("") ? "0.0.0" : str5;
                    try {
                        this.f2027a.put(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION, str4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.f2030d.f1994a.put(this.f2028b, this.f2027a);
                    Log.m1214v("iHealthDUM", "latest version from cloud: " + str4);
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(UpgradeProfile.DEVICE_CLOUD_FIRMWARE_VERSION, str4);
                        jSONObject.put(UpgradeProfile.DEVICE_MANDATORY_FLAG, this.f2030d.f2003j.f.c);
                        this.f2030d.f2004k.onNotify(this.f2028b, this.f2029c, UpgradeProfile.ACTION_DEVICE_CLOUD_FIRMWARE_VERSION, jSONObject.toString());
                        return;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                if (this.f2030d.isUpgradeState(this.f2028b, this.f2029c)) {
                    this.f2030d.stopUpgrade(this.f2028b, this.f2029c);
                }
                Log.m1215w("iHealthDUM", "queryLatestVersionFromCloud() -- failed");
                if (this.f2030d.f2002i != null) {
                    this.f2030d.f2002i.returnComm();
                }
                this.f2030d.m1166a(this.f2028b, this.f2029c, 200);
            } else if (this.f2030d.f2006m) {
                this.f2030d.m1167a(this.f2028b, this.f2029c, this.f2027a);
            }
        } catch (JSONException e22) {
            e22.printStackTrace();
        }
    }
}
