package com.lifesense.ble;

import android.os.Message;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerData;
import com.lifesense.ble.commom.C2255a;
import com.lifesense.ble.p003a.C2220h;
import com.lifesense.ble.p003a.C2223k;
import java.util.ArrayList;
import java.util.UUID;

class C2259f implements C2223k {
    final /* synthetic */ LsBleManager f2457a;

    C2259f(LsBleManager lsBleManager) {
        this.f2457a = lsBleManager;
    }

    public void mo5593a(DeviceConnectState deviceConnectState, String str) {
        if (deviceConnectState == DeviceConnectState.DISCONNECTED || deviceConnectState == DeviceConnectState.CONNECTED_FAILED) {
            this.f2457a.removeDisableConnectDevice(str);
        }
    }

    public void mo5594a(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo != null) {
            C2220h.m1596a((Object) this, "ble peripheral listener receive device info..." + lsDeviceInfo.toString(), 3);
        }
    }

    public void mo5595a(LsDeviceInfo lsDeviceInfo, String str, UUID uuid, String str2) {
        if (lsDeviceInfo != null && str != null && str2 != null) {
            String deviceType = lsDeviceInfo.getDeviceType();
            Message obtainMessage = this.f2457a.callbackHandler.obtainMessage();
            if (deviceType != null && deviceType.length() > 0 && deviceType.equals(DeviceTypeConstants.PEDOMETER)) {
                PedometerData b;
                if (str2.equals("CA")) {
                    b = C2255a.m1887b(str, str2);
                    if (b != null) {
                        this.f2457a.caPedometerDataList.clear();
                        b.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        b.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        b.setDeviceId(lsDeviceInfo.getDeviceId());
                        this.f2457a.caPedometerDataList.add(b);
                        obtainMessage.obj = this.f2457a.caPedometerDataList;
                        obtainMessage.arg1 = 19;
                        obtainMessage.arg2 = 202;
                        this.f2457a.callbackHandler.sendMessage(obtainMessage);
                    }
                } else if (str2.equals("CE")) {
                    this.f2457a.a4SleepInfoList = C2255a.m1876a(str, lsDeviceInfo.getDeviceId(), lsDeviceInfo.getBroadcastID());
                    obtainMessage.obj = this.f2457a.a4SleepInfoList;
                    obtainMessage.arg1 = 20;
                    this.f2457a.callbackHandler.sendMessage(obtainMessage);
                } else if (str2.equals("C9")) {
                    b = C2255a.m1887b(str, str2);
                    if (b != null) {
                        b.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        b.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        b.setDeviceId(lsDeviceInfo.getDeviceId());
                        this.f2457a.c9PedometerDataList.add(b);
                        if (C2255a.m1867a(str, str2) == 0) {
                            obtainMessage.obj = this.f2457a.c9PedometerDataList;
                            obtainMessage.arg2 = 201;
                            obtainMessage.arg1 = 18;
                            this.f2457a.callbackHandler.sendMessage(obtainMessage);
                            this.f2457a.c9PedometerDataList = new ArrayList();
                            C2220h.m1596a((Object) this, "Data upload is complete,enable disconnect...", 3);
                            return;
                        }
                        return;
                    }
                    C2220h.m1596a((Object) this, "Failed to parse pedometer data for error...", 1);
                }
            }
        }
    }
}
