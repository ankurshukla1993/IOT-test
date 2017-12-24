package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A1InsSetforBp7s;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.utils.Log;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class Bp7sControl implements DeviceControl {
    private Context f1140a;
    private A1InsSetforBp7s f1141b;
    private BaseComm f1142c;
    private String f1143d;
    private String f1144e;
    private InsCallback f1145f;
    private C2023a f1146g;

    public Bp7sControl(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1142c = com;
        this.f1140a = context;
        this.f1143d = mac;
        this.f1144e = name;
        this.f1145f = insCallback;
        this.f1141b = new A1InsSetforBp7s(context, com, userName, mac, name, insCallback, mBaseCommCallback);
        this.f1146g = new C2023a(mac, name, null);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1146g);
    }

    private void m578a(int i, String str) {
        Log.w("Bp7sControl", str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i);
            jSONObject.put("description", str);
            this.f1145f.onNotify(this.f1143d, this.f1144e, "error_bp", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void angleSet(byte leftUpper, byte leftLow, byte rightUpper, byte rightLow) {
        this.f1146g.m232a(Arrays.asList(new String[]{BpProfile.ACTION_SET_ANGLE_SUCCESS_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dr(this, leftLow, leftUpper, rightLow, rightUpper));
    }

    public void destroy() {
        if (this.f1141b != null) {
            this.f1141b.destroy();
        }
        this.f1141b = null;
        this.f1140a = null;
        this.f1142c = null;
    }

    public void disconnect() {
        this.f1142c.disconnect();
    }

    public void getBattery() {
        this.f1146g.m232a(Arrays.asList(new String[]{BpProfile.ACTION_BATTERY_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dn(this));
    }

    public void getFunctionInfo() {
        this.f1146g.m232a(Arrays.asList(new String[]{BpProfile.ACTION_FUNCTION_INFORMATION_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new ds(this));
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1143d);
    }

    public void getOfflineData() {
        this.f1146g.m232a(Arrays.asList(new String[]{BpProfile.ACTION_HISTORICAL_OVER_BP, BpProfile.ACTION_HISTORICAL_DATA_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), -1, new dp(this));
    }

    public void getOfflineNum() {
        this.f1146g.m232a(Arrays.asList(new String[]{"offlinenum", "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new C2072do(this));
    }

    public void init() {
        this.f1141b.getIdps();
    }

    public void setUnit(int unit) {
        this.f1146g.m232a(Arrays.asList(new String[]{BpProfile.ACTION_SET_UNIT_SUCCESS_BP, "error_bp", iHealthDevicesManager.IHEALTH_COMM_TIMEOUT}), 4500, new dq(this, unit));
    }
}
