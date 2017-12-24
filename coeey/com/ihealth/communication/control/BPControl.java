package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.BPInsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.utils.Log;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class BPControl implements DeviceControl {
    private final String f1033a = "BPControl";
    private BaseComm f1034b;
    private Context f1035c;
    private String f1036d;
    private String f1037e;
    private BPInsSet f1038f;
    private BaseCommCallback f1039g;
    private InsCallback f1040h;
    private C2023a f1041i;

    public BPControl(Context context, BaseComm mBaseComm, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1034b = mBaseComm;
        this.f1035c = context;
        this.f1036d = mac;
        this.f1037e = type;
        this.f1039g = mBaseCommCallback;
        this.f1040h = insCallback;
        this.f1038f = new BPInsSet(context, mBaseComm, bleComm, userName, mac, type, insCallback, mBaseCommCallback);
        this.f1041i = new C2023a(mac, type, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1041i);
    }

    private void m483a(int i, String str) {
        Log.w("BPControl", str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i);
            jSONObject.put("description", str);
            this.f1040h.onNotify(this.f1036d, this.f1037e, "error_bp", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void commandSetUser(int UserID) {
        this.f1041i.m232a(Arrays.asList(new String[]{BpProfile.ACTION_CONFORM_CHOOSE_USER_CBP, "error_bp"}), 4500, new at(this, UserID));
    }

    public void destroy() {
        if (this.f1038f != null) {
            this.f1038f.destroy();
        }
        this.f1038f = null;
        this.f1035c = null;
        this.f1034b = null;
    }

    public void disconnect() {
        this.f1034b.disconnect(this.f1036d);
    }

    public void getBattery() {
        this.f1041i.m232a(Arrays.asList(new String[]{"action_battery", "error_bp"}), 4500, new ar(this));
    }

    public void getData() {
        this.f1041i.m232a(Arrays.asList(new String[]{"action_history_data", "error_bp"}), -1, new au(this));
    }

    public void getFeature() {
        this.f1041i.m232a(Arrays.asList(new String[]{"action_feature", "error_bp"}), 4500, new as(this));
    }

    public void init() {
        this.f1039g.onConnectionStateChange(this.f1036d, this.f1037e, 1, 0, null);
    }

    public void setTime() {
        this.f1041i.m232a(Arrays.asList(new String[]{"action_set_time", "error_bp"}), 4500, new aq(this));
    }
}
