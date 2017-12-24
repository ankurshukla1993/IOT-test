package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.InsCallback;
import org.joda.time.DateTimeConstants;

public class Am3sControl extends C2067n {
    public Am3sControl(BaseComm com, Context context, String mac, String type, String userName, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        super("Am3sControl", com, context, mac, type, userName, baseCommCallback, insCallback);
    }

    protected void checkSwimPara() {
        throw new UnsupportedOperationException("AM3S not support swim function.");
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

    public void getPicture() {
        super.getPicture();
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
        super.setMode(mode);
    }

    public void setMode(int mode, int minute) {
        if (minute % 30 != 0 || minute < 30 || minute >= DateTimeConstants.MINUTES_PER_DAY) {
            m480a("setMode() parameter minute should be: (minute % 30 == 0 && minute >= 30 && minute < 30 * 48)");
        } else {
            this.b.b5Ins(mode, minute);
        }
    }

    public void setPicture(int index) {
        super.setPicture(index);
    }

    protected void setSwimPara(boolean isOpen, int poolLength, int hours, int minutes, int unit) {
        throw new UnsupportedOperationException("AM3S not support swim function.");
    }

    public /* bridge */ /* synthetic */ void setUserBmr(int i) {
        super.setUserBmr(i);
    }

    public /* bridge */ /* synthetic */ void setUserId(int i) {
        super.setUserId(i);
    }

    public void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel) {
        super.setUserInfo(age, height, weight, gender, unit, target, activityLevel);
    }

    protected void setUserInfo(int age, int height, float weight, int gender, int unit, int target, int activityLevel, int min) {
        throw new UnsupportedOperationException("AM3S not support this method, please call setUserInfo(int, int, float, int, int, int, int) instead.");
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
