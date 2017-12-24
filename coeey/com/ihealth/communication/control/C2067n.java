package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.AaInsSet;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.p001a.C2026d;
import com.ihealth.communication.utils.Log;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class C2067n implements DeviceControl {
    protected Context f1013a;
    protected AaInsSet f1014b;
    protected F0InsSet f1015c;
    protected int f1016d;
    private final String f1017e;
    private BaseComm f1018f;
    private String f1019g;
    private String f1020h = null;
    private InsCallback f1021i;
    private String f1022j;
    private C2023a f1023k = null;
    private float f1024l;
    private int f1025m = 1;
    private float f1026n;
    private int f1027o;
    private int f1028p;
    private int f1029q;
    private int f1030r;
    private int f1031s;
    private UpDeviceControl f1032t = new C2092y(this);

    public C2067n(String str, BaseComm baseComm, Context context, String str2, String str3, String str4, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1017e = str;
        this.f1018f = baseComm;
        this.f1013a = context;
        this.f1019g = str2;
        this.f1014b = new AaInsSet(baseComm, context, str2, str3, str4, baseCommCallback, insCallback);
        this.f1015c = new F0InsSet(baseComm, this.f1014b.getBaseCommProtocol(), context, str2, str3, insCallback);
        this.f1021i = insCallback;
        this.f1022j = str3;
        this.f1016d = Integer.parseInt(iHealthDevicesManager.getInstance().getIdps(this.f1019g).getAccessoryFirmwareVersion().replace(".", ""));
        this.f1023k = new C2023a(str2, str3, AmProfile.ACTION_ERROR_AM);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(str2, this.f1023k);
    }

    private int m464a() {
        double d = this.f1028p == 0 ? (((13.397d * ((double) this.f1024l)) + (4.799d * ((double) this.f1026n))) - (5.677d * ((double) this.f1027o))) + 88.362d : this.f1028p == 1 ? (((9.247d * ((double) this.f1024l)) + (3.098d * ((double) this.f1026n))) - (4.33d * ((double) this.f1027o))) + 447.593d : 0.0d;
        switch (this.f1029q) {
            case 1:
                d *= 1.0d;
                break;
            case 2:
                d *= 1.05d;
                break;
            case 3:
                d *= 1.1d;
                break;
        }
        if (d < 0.0d) {
            d *= -1.0d;
        }
        return new BigDecimal(d).setScale(0, 4).intValue() - 2;
    }

    private void m468a(int i, String str) {
        Log.w(this.f1017e, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i);
            jSONObject.put("description", str);
            this.f1021i.onNotify(this.f1019g, this.f1022j, AmProfile.ACTION_ERROR_AM, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m469a(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1023k.m232a(arrayList, 4500, c2026d);
    }

    private void m473b(C2026d c2026d, String str, String... strArr) {
        List arrayList = new ArrayList(1);
        arrayList.add(str);
        arrayList.addAll(Arrays.asList(strArr));
        this.f1023k.m232a(arrayList, -1, c2026d);
    }

    protected void m480a(String str) {
        m468a(400, str);
    }

    protected void m481b(String str) {
        m468a((int) AmProfile.ERROR_ID_VERSION_NOT_SUPPORT, str);
    }

    protected void checkSwimPara() {
        m469a(new af(this), AmProfile.ACTION_GET_SWIMINFO_AM, new String[0]);
    }

    public void deleteAlarmClock(int id) {
        m469a(new am(this, id), AmProfile.ACTION_DELETE_ALARM_SUCCESS_AM, new String[0]);
    }

    public void destroy() {
        this.f1013a = null;
        if (this.f1014b != null) {
            this.f1014b.destroy();
        }
        this.f1014b = null;
        if (this.f1015c != null) {
            this.f1015c.destroy();
        }
        this.f1015c = null;
        this.f1018f = null;
    }

    public void disconnect() {
        this.f1018f.disconnect(this.f1019g);
        this.f1021i.destroy();
    }

    public void getActivityRemind() {
        m469a(new an(this), AmProfile.ACTION_GET_ACTIVITY_REMIND_AM, new String[0]);
    }

    public void getAlarmClockDetail(int... ids) {
        m469a(new ak(this, ids), AmProfile.ACTION_GET_ALARMINFO_AM, new String[0]);
    }

    public void getAlarmClockNum() {
        m469a(new aj(this), AmProfile.ACTION_GET_ALARMNUM_AM, new String[0]);
    }

    public void getHourMode() {
        m469a(new C2091x(this), AmProfile.ACTION_GET_HOUR_MODE_AM, new String[0]);
    }

    public String getIdps() {
        return iHealthDevicesManager.getInstance().getDevicesIDPS(this.f1019g);
    }

    protected void getPicture() {
        m469a(new ai(this), "get_picture_am", new String[0]);
    }

    public UpDeviceControl getUpDeviceControl() {
        return this.f1032t;
    }

    public void getUserId() {
        m469a(new C2093z(this), AmProfile.ACTION_USERID_AM, new String[0]);
    }

    public void getUserInfo() {
        m469a(new C2084q(this), AmProfile.ACTION_GET_USERINFO_AM, new String[0]);
    }

    public void init() {
        this.f1014b.identify();
    }

    public void queryAMState() {
        m469a(new ap(this), AmProfile.ACTION_QUERY_STATE_AM, new String[0]);
    }

    public void reset(int id) {
        m469a(new C2082o(this, id), AmProfile.ACTION_ERROR_AM, new String[0]);
    }

    protected void sendRandom() {
        m469a(new ae(this), AmProfile.ACTION_GET_RANDOM_AM, new String[0]);
    }

    public void setActivityRemind(int hour, int min, boolean isOn) {
        m469a(new ao(this, hour, min, isOn), AmProfile.ACTION_SET_ACTIVITYREMIND_SUCCESS_AM, new String[0]);
    }

    public void setAlarmClock(int id, int hour, int min, boolean isRepeat, int[] weeks, boolean isOn) {
        m469a(new al(this, id, hour, min, weeks, isRepeat, isOn), AmProfile.ACTION_SET_ALARMINFO_SUCCESS_AM, new String[0]);
    }

    public void setHourMode(int hourMode) {
        m469a(new C2090w(this, hourMode), AmProfile.ACTION_SET_HOUR_MODE_SUCCESS_AM, new String[0]);
    }

    protected void setMode(int mode) {
        m469a(new ac(this, mode), AmProfile.ACTION_SET_DEVICE_MODE_AM, new String[0]);
    }

    protected void setPicture(int index) {
        m469a(new ah(this, index), AmProfile.ACTION_SET_PICTURE_SUCCESS_AM, new String[0]);
    }

    protected void setSwimPara(boolean isOpen, int poolLength, int hours, int minutes, int unit) {
        m469a(new ag(this, poolLength, hours, minutes, unit, isOpen), AmProfile.ACTION_SET_SWIMINFO_AM, new String[0]);
    }

    public void setUserBmr(int bmr) {
        m469a(new C2085r(this, bmr), AmProfile.ACTION_SET_BMR_SUCCESS_AM, new String[0]);
    }

    public void setUserId(int id) {
        m469a(new C2083p(this, id), AmProfile.ACTION_SET_USERID_SUCCESS_AM, new String[0]);
    }

    protected void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel) {
        m469a(new aa(this, age, height, weight, gender, unit, target, activityLevel), AmProfile.ACTION_SET_USERINFO_SUCCESS_AM, new String[0]);
    }

    protected void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel, int min) {
        m469a(new ab(this, age, height, weight, gender, unit, target, activityLevel, min), AmProfile.ACTION_SET_USERINFO_SUCCESS_AM, new String[0]);
    }

    public void syncActivityData() {
        m473b(new C2086s(this), AmProfile.ACTION_SYNC_ACTIVITY_DATA_AM, new String[0]);
    }

    public void syncRealData() {
        m469a(new C2087t(this), AmProfile.ACTION_SYNC_REAL_DATA_AM, new String[0]);
    }

    public void syncRealTime() {
        m469a(new C2089v(this), AmProfile.ACTION_SYNC_TIME_SUCCESS_AM, new String[0]);
    }

    public void syncSleepData() {
        m473b(new C2088u(this), AmProfile.ACTION_SYNC_SLEEP_DATA_AM, new String[0]);
    }

    protected void syncStageReprotData() {
        m473b(new ad(this), AmProfile.ACTION_SYNC_STAGE_DATA_AM, new String[0]);
    }
}
