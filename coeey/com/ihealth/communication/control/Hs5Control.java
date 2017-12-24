package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.A9InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.ins.UserListInHs5;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import com.ihealth.communication.utils.IDPS;
import com.ihealth.communication.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Hs5Control implements DeviceControl {
    private static UserListInHs5 f1183c;
    private IDPS f1184a;
    private A9InsSet f1185b;
    private String f1186d;
    private BaseCommCallback f1187e;
    private String f1188f;
    private InsCallback f1189g;
    private C2023a f1190h = null;

    public Hs5Control(String userName, Context context, String deviceMac, String deviceIp, BaseComm comm, BaseCommCallback baseCommCallback, InsCallback insCallback, String type) {
        this.f1186d = deviceMac;
        this.f1187e = baseCommCallback;
        this.f1189g = insCallback;
        this.f1188f = type;
        this.f1184a = new IDPS();
        f1183c = new UserListInHs5();
        this.f1185b = new A9InsSet(userName, context, comm, deviceMac, deviceIp, this.f1189g, baseCommCallback, type, f1183c);
        this.f1190h = new C2023a(deviceMac, type, HsProfile.ACTION_ERROR_HS);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(deviceMac, this.f1190h);
    }

    private void m597a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1190h.m232a(arrayList, 4500, c2026d);
    }

    private void m600a(String str) {
        Log.w("Hs5Control", str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error", 401);
            jSONObject.put("description", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f1189g.onNotify(this.f1186d, this.f1188f, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
    }

    private void m601a(String str, Object obj) {
        String str2 = str + " But passed in " + obj;
        Log.w("Hs5Control", str2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error", 400);
            jSONObject.put("description", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f1189g.onNotify(this.f1186d, this.f1188f, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
    }

    private void m602b(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1190h.m232a(arrayList, -1, c2026d);
    }

    public void DeleteUserInScale(int userPstCode) {
        m597a(new ei(this, userPstCode), HsProfile.ACTION_DELETEUSER_HS, new String[0]);
    }

    public void WriteUserToScale(int userPstCode, int userId, int age, int height, int isSporter, int gender) {
        m597a(new eh(this, userPstCode, userId, age, height, isSporter, gender), HsProfile.ACTION_ADDUSER_HS, new String[0]);
    }

    public void creatManagement(int userId) {
        m597a(new eg(this, userId), HsProfile.ACTION_MANAGEMENT_HS, new String[0]);
    }

    public void destroy() {
    }

    public void disconnect() {
        this.f1187e.onConnectionStateChange(this.f1186d, this.f1188f, 2, 0, null);
    }

    public void getOfflineData(int userPstCode, int userId) {
        m602b(new ek(this, userPstCode, userId), HsProfile.ACTION_NO_SPECIFIED_USER, HsProfile.ACTION_NO_HISTORICALDATA, HsProfile.ACTION_HISTORICAL_DATA_HS);
    }

    public UserListInHs5 getUserListInHs5() {
        return f1183c;
    }

    public IDPS getWifiIDPSData() {
        return this.f1184a;
    }

    public void init() {
        this.f1185b.identify();
    }

    public void setWifiIDPSData(IDPS idps) {
        this.f1184a = idps;
    }

    public void startMeasure(int userPstCode, int userId) {
        m602b(new el(this, userPstCode, userId), HsProfile.ACTION_MEASUREMENT_HS, new String[0]);
    }

    public void updateUserInfo(int userPstCode, int userId, int age, int height, int isSporter, int gender) {
        m597a(new ej(this, userPstCode, userId, age, height, isSporter, gender), HsProfile.ACTION_UPDATEUSER_HS, new String[0]);
    }
}
