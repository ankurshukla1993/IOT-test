package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.InsCallback;

public class Am4Control extends C2067n {
    public Am4Control(BaseComm com, Context context, String mac, String type, String userName, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        super("Am4Control", com, context, mac, type, userName, baseCommCallback, insCallback);
    }

    public void checkSwimPara() {
        super.checkSwimPara();
    }

    public /* bridge */ /* synthetic */ void deleteAlarmClock(int i) {
        super.deleteAlarmClock(i);
    }

    public /* bridge */ /* synthetic */ void destroy() {
        super.destroy();
    }

    public /* bridge */ /* synthetic */ void disconnect() {
        super.disconnect();
    }

    public /* bridge */ /* synthetic */ void getActivityRemind() {
        super.getActivityRemind();
    }

    public /* bridge */ /* synthetic */ void getAlarmClockDetail(int[] iArr) {
        super.getAlarmClockDetail(iArr);
    }

    public /* bridge */ /* synthetic */ void getAlarmClockNum() {
        super.getAlarmClockNum();
    }

    public /* bridge */ /* synthetic */ void getHourMode() {
        super.getHourMode();
    }

    public /* bridge */ /* synthetic */ String getIdps() {
        return super.getIdps();
    }

    protected void getPicture() {
        throw new UnsupportedOperationException("AM4 not support getPicture.");
    }

    public /* bridge */ /* synthetic */ UpDeviceControl getUpDeviceControl() {
        return super.getUpDeviceControl();
    }

    public /* bridge */ /* synthetic */ void getUserId() {
        super.getUserId();
    }

    public /* bridge */ /* synthetic */ void getUserInfo() {
        super.getUserInfo();
    }

    public /* bridge */ /* synthetic */ void init() {
        super.init();
    }

    public /* bridge */ /* synthetic */ void queryAMState() {
        super.queryAMState();
    }

    public /* bridge */ /* synthetic */ void reset(int i) {
        super.reset(i);
    }

    public void sendRandom() {
        super.sendRandom();
    }

    public /* bridge */ /* synthetic */ void setActivityRemind(int i, int i2, boolean z) {
        super.setActivityRemind(i, i2, z);
    }

    public /* bridge */ /* synthetic */ void setAlarmClock(int i, int i2, int i3, boolean z, int[] iArr, boolean z2) {
        super.setAlarmClock(i, i2, i3, z, iArr, z2);
    }

    public /* bridge */ /* synthetic */ void setHourMode(int i) {
        super.setHourMode(i);
    }

    protected void setMode(int mode) {
        throw new UnsupportedOperationException("AM4 not support setMode, it will change mode automatically.");
    }

    protected void setPicture(int index) {
        throw new UnsupportedOperationException("AM4 not support setPicture.");
    }

    public void setSwimPara(boolean isOpen, int poolLength, int hours, int minutes, int unit) {
        super.setSwimPara(isOpen, poolLength, hours, minutes, unit);
    }

    public /* bridge */ /* synthetic */ void setUserBmr(int i) {
        super.setUserBmr(i);
    }

    public /* bridge */ /* synthetic */ void setUserId(int i) {
        super.setUserId(i);
    }

    protected void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel) {
        throw new UnsupportedOperationException("AM4 not support this method, please call setUserInfo(int, int, float, int, int, int, int, int) instead.");
    }

    public void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel, int min) {
        super.setUserInfo(age, height, weight, gender, unit, target, activityLevel, min);
    }

    public /* bridge */ /* synthetic */ void syncActivityData() {
        super.syncActivityData();
    }

    public /* bridge */ /* synthetic */ void syncRealData() {
        super.syncRealData();
    }

    public /* bridge */ /* synthetic */ void syncRealTime() {
        super.syncRealTime();
    }

    public /* bridge */ /* synthetic */ void syncSleepData() {
        super.syncSleepData();
    }

    public void syncStageReprotData() {
        super.syncStageReprotData();
    }
}
