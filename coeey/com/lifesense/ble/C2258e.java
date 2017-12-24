package com.lifesense.ble;

import android.os.Message;
import com.lifesense.ble.bean.BloodPressureData;
import com.lifesense.ble.bean.C2242b;
import com.lifesense.ble.bean.C2253m;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.bean.HeightData;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerData;
import com.lifesense.ble.bean.WeightData_A2;
import com.lifesense.ble.bean.WeightData_A3;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.commom.C2255a;
import com.lifesense.ble.p003a.C2220h;
import com.lifesense.ble.p003a.C2224l;
import com.lifesense.ble.p003a.C2225m;
import com.lifesense.ble.p003a.C2226n;
import com.lifesense.ble.p003a.C2227o;
import com.lifesense.ble.p003a.C2234v;
import com.lifesense.ble.p003a.ae;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class C2258e implements C2224l {
    final /* synthetic */ LsBleManager f2456a;

    C2258e(LsBleManager lsBleManager) {
        this.f2456a = lsBleManager;
    }

    public void mo5582a(DeviceConnectState deviceConnectState) {
        C2220h.m1596a((Object) this, "on gatt connect state change..." + deviceConnectState, 3);
        Message obtainMessage = this.f2456a.callbackHandler.obtainMessage();
        obtainMessage.obj = deviceConnectState;
        obtainMessage.arg1 = 14;
        this.f2456a.callbackHandler.sendMessage(obtainMessage);
    }

    public void mo5583a(ManagerStatus managerStatus) {
        this.f2456a.setManagerStatus(managerStatus, "protocol Call back");
    }

    public void mo5584a(C2227o c2227o, ae aeVar) {
        if (c2227o == C2227o.UPLOAD_SUCCESSFULLY) {
            C2220h.m1596a((Object) this, "Done ! Data upload is complete...", 3);
        } else if (c2227o == C2227o.UPLOAD_FAILED) {
            C2220h.m1596a((Object) this, "Error ! Failed to upload..", 3);
        }
        if (aeVar == ae.A2 || aeVar == ae.A3 || aeVar == ae.A4) {
            Message obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.arg1 = 13;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        }
    }

    public void mo5585a(LsDeviceInfo lsDeviceInfo) {
        C2220h.m1596a((Object) this, "receive device info..." + lsDeviceInfo.toString(), 3);
        if (this.f2456a.genericFatConnectListener != null) {
            this.f2456a.genericFatConnectListener.onReceiveDeviceInfo(lsDeviceInfo);
        }
    }

    public void mo5586a(LsDeviceInfo lsDeviceInfo, C2225m c2225m) {
        this.f2456a.setManagerStatus(ManagerStatus.FREE, "Paired Results");
        Message obtainMessage;
        if (c2225m == C2225m.PAIR_SUCCESSFULLY && lsDeviceInfo != null) {
            C2220h.m1596a((Object) this, "paired results,successfully...", 3);
            obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = lsDeviceInfo;
            obtainMessage.arg1 = 3;
            obtainMessage.arg2 = 0;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        } else if (c2225m == C2225m.PAIR_FAILED) {
            C2220h.m1596a((Object) this, "paired results,failure...", 3);
            obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = null;
            obtainMessage.arg1 = 3;
            obtainMessage.arg2 = -1;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        }
    }

    public void mo5587a(LsDeviceInfo lsDeviceInfo, String str, UUID uuid, String str2) {
        if (lsDeviceInfo != null && str != null && str2 != null) {
            String deviceType = lsDeviceInfo.getDeviceType();
            Message obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            if (deviceType != null && deviceType.length() > 0 && deviceType.equals(DeviceTypeConstants.PEDOMETER)) {
                PedometerData b;
                if (str2.equals("CA")) {
                    b = C2255a.m1887b(str, str2);
                    if (b != null) {
                        this.f2456a.caPedometerDataList.clear();
                        b.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        b.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        b.setDeviceId(lsDeviceInfo.getDeviceId());
                        this.f2456a.caPedometerDataList.add(b);
                        obtainMessage.obj = this.f2456a.caPedometerDataList;
                        obtainMessage.arg1 = 19;
                        obtainMessage.arg2 = 202;
                        this.f2456a.callbackHandler.sendMessage(obtainMessage);
                    }
                } else if (str2.equals("CE")) {
                    this.f2456a.a4SleepInfoList = C2255a.m1876a(str, lsDeviceInfo.getDeviceId(), lsDeviceInfo.getDeviceId());
                    obtainMessage.obj = this.f2456a.a4SleepInfoList;
                    obtainMessage.arg1 = 20;
                    this.f2456a.callbackHandler.sendMessage(obtainMessage);
                } else if (str2.equals("C9")) {
                    b = C2255a.m1887b(str, str2);
                    if (b != null) {
                        b.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        b.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        b.setDeviceId(lsDeviceInfo.getDeviceId());
                        this.f2456a.c9PedometerDataList.add(b);
                        if (C2255a.m1867a(str, str2) == 0) {
                            obtainMessage.obj = this.f2456a.c9PedometerDataList;
                            obtainMessage.arg2 = 201;
                            obtainMessage.arg1 = 18;
                            this.f2456a.callbackHandler.sendMessage(obtainMessage);
                            this.f2456a.c9PedometerDataList = new ArrayList();
                            C2220h.m1596a((Object) this, "Data upload is complete,enable disconnect...", 3);
                            this.f2456a.protocolHendlerCenter.m1799a();
                            return;
                        }
                        return;
                    }
                    C2220h.m1596a((Object) this, "Failed to parse pedometer data for error...", 1);
                }
            }
        }
    }

    public void mo5588a(LsDeviceInfo lsDeviceInfo, byte[] bArr, UUID uuid) {
        String deviceType = lsDeviceInfo.getDeviceType();
        Message obtainMessage = this.f2456a.callbackHandler.obtainMessage();
        if (deviceType != null && deviceType.length() > 0) {
            if (deviceType.equals(DeviceTypeConstants.PEDOMETER)) {
                PedometerData b = C2255a.m1888b(bArr);
                b.setDeviceSn(lsDeviceInfo.getDeviceSn());
                b.setBroadcastId(lsDeviceInfo.getBroadcastID());
                b.setDeviceId(lsDeviceInfo.getDeviceId());
                obtainMessage.obj = b;
                obtainMessage.arg1 = 6;
                this.f2456a.callbackHandler.sendMessage(obtainMessage);
            } else if (deviceType.equals(DeviceTypeConstants.HEIGHT_RULER)) {
                HeightData c = C2255a.m1897c(bArr);
                c.setDeviceSn(lsDeviceInfo.getDeviceSn());
                c.setBroadcastId(lsDeviceInfo.getBroadcastID());
                c.setDeviceId(lsDeviceInfo.getDeviceId());
                obtainMessage.obj = c;
                obtainMessage.arg1 = 8;
                this.f2456a.callbackHandler.sendMessage(obtainMessage);
            } else if (deviceType.equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                if (uuid.equals(C2242b.WEIGHT_SCLAE_MEASUREMENT_CHARACTERISTIC_UUID)) {
                    this.f2456a.tempWeightData_A2 = C2255a.m1871a(bArr);
                    if (this.f2456a.tempWeightData_A2 != null && !this.f2456a.tempWeightData_A2.isHasAppendMeasurement()) {
                        C2220h.m1596a((Object) this, "Weight(A2) measure data-no fat data", 3);
                        this.f2456a.tempWeightData_A2.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        this.f2456a.tempWeightData_A2.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        this.f2456a.tempWeightData_A2.setDeviceId(lsDeviceInfo.getDeviceId());
                        obtainMessage.obj = this.f2456a.tempWeightData_A2;
                        obtainMessage.arg1 = 4;
                        this.f2456a.callbackHandler.sendMessage(obtainMessage);
                    }
                } else if (uuid.equals(C2242b.WEIGHT_SCLAE_APPEND_MEASUREMENT_CHARACTERISTIC_UUID)) {
                    C2220h.m1596a((Object) this, "Weight(A2) measure data-has fat data", 3);
                    WeightData_A2 a = C2255a.m1870a(this.f2456a.tempWeightData_A2, bArr);
                    a.setDeviceSn(lsDeviceInfo.getDeviceSn());
                    a.setBroadcastId(lsDeviceInfo.getBroadcastID());
                    a.setDeviceId(lsDeviceInfo.getDeviceId());
                    obtainMessage.obj = a;
                    obtainMessage.arg1 = 4;
                    this.f2456a.callbackHandler.sendMessage(obtainMessage);
                }
            } else if (deviceType.equals(DeviceTypeConstants.FAT_SCALE)) {
                if (lsDeviceInfo.getProtocolType().equals(C2234v.PROTOCOL_TYPE_GENERIC_FAT.toString())) {
                    if (uuid.equals(C2242b.WEIGHT_SCLAE_MEASUREMENT_CHARACTERISTIC_UUID)) {
                        this.f2456a.tempWeightData_A2 = C2255a.m1872a(bArr, this.f2456a.currenPersonalUserInfo);
                        C2220h.m1596a((Object) this, "Fat(Generic_fat) measure data...", 3);
                        this.f2456a.tempWeightData_A2.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        this.f2456a.tempWeightData_A2.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        this.f2456a.tempWeightData_A2.setDeviceId(lsDeviceInfo.getDeviceId());
                        obtainMessage.obj = this.f2456a.tempWeightData_A2;
                        obtainMessage.arg1 = 16;
                        this.f2456a.callbackHandler.sendMessage(obtainMessage);
                    }
                } else if (!lsDeviceInfo.getProtocolType().equals(C2234v.PROTOCOL_TYPE_A3)) {
                } else {
                    if (uuid.equals(C2242b.WEIGHT_SCALE_MEASUREMENT_UUID)) {
                        this.f2456a.tempWeightData_A3 = C2255a.m1902d(bArr);
                        if (this.f2456a.tempWeightData_A3 != null) {
                            C2220h.m1596a((Object) this, "Weight(A3) measure data-no fat data", 3);
                            this.f2456a.tempWeightData_A3.setDeviceSn(lsDeviceInfo.getDeviceSn());
                            this.f2456a.tempWeightData_A3.setBroadcastId(lsDeviceInfo.getBroadcastID());
                            this.f2456a.tempWeightData_A3.setDeviceId(lsDeviceInfo.getDeviceId());
                            obtainMessage.obj = this.f2456a.tempWeightData_A3;
                            obtainMessage.arg1 = 5;
                            this.f2456a.callbackHandler.sendMessage(obtainMessage);
                        }
                    } else if (uuid.equals(C2242b.WEIGHT_SCALE_APPEND_MEASUREMENT_UUID)) {
                        C2220h.m1596a((Object) this, "Weight(A3) measure data-has fat data", 3);
                        WeightData_A3 a2 = C2255a.m1873a(this.f2456a.tempWeightData_A3, bArr);
                        a2.setDeviceSn(lsDeviceInfo.getDeviceSn());
                        a2.setBroadcastId(lsDeviceInfo.getBroadcastID());
                        a2.setDeviceId(lsDeviceInfo.getDeviceId());
                        obtainMessage.obj = a2;
                        obtainMessage.arg1 = 5;
                        this.f2456a.callbackHandler.sendMessage(obtainMessage);
                    }
                }
            } else if (deviceType.equals(DeviceTypeConstants.SPHYGMOMAN_METER)) {
                BloodPressureData e = C2255a.m1906e(bArr);
                e.setDeviceSn(lsDeviceInfo.getDeviceSn());
                e.setBroadcastId(lsDeviceInfo.getBroadcastID());
                e.setDeviceId(lsDeviceInfo.getDeviceId());
                obtainMessage.obj = e;
                obtainMessage.arg1 = 7;
                this.f2456a.callbackHandler.sendMessage(obtainMessage);
            }
        }
    }

    public void mo5589a(WeightUserInfo weightUserInfo) {
        if (weightUserInfo != null) {
            Message obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = weightUserInfo;
            obtainMessage.arg1 = 9;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        }
    }

    public void mo5590a(String str, String str2, String str3, C2226n c2226n) {
        C2220h.m1596a((Object) this, "receive call back for user info write successfully...", 3);
        C2253m c2253m = new C2253m();
        c2253m.m1859a(str2);
        c2253m.m1861b(str3);
        c2253m.m1858a(c2226n);
        Message obtainMessage;
        if (c2226n == C2226n.PEDOMETER_ALARM_CLOCK) {
            this.f2456a.removeProductUserInfo(str, str2);
            obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = c2253m;
            obtainMessage.arg1 = 12;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        } else if (c2226n == C2226n.PEDOMETER_USER_INFO) {
            this.f2456a.removeProductUserInfo(str, str2);
            obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = c2253m;
            obtainMessage.arg1 = 11;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        } else if (c2226n == C2226n.WEIGHT_USER_INFO) {
            this.f2456a.removeProductUserInfo(str, str2);
            obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = c2253m;
            obtainMessage.arg1 = 10;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        } else if (c2226n == C2226n.VIBRATION_VOICE) {
            this.f2456a.removeProductUserInfo(str, str2);
            obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = c2253m;
            obtainMessage.arg1 = 17;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        }
    }

    public void mo5591a(List list) {
        if (list != null && list.size() > 0) {
            Message obtainMessage = this.f2456a.callbackHandler.obtainMessage();
            obtainMessage.obj = list;
            obtainMessage.arg1 = 2;
            this.f2456a.callbackHandler.sendMessage(obtainMessage);
        }
    }

    public void mo5592b(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo != null && lsDeviceInfo.getBroadcastID() != null) {
            if (this.f2456a.measuredDeviceMap == null || this.f2456a.measuredDeviceMap.size() != 1) {
                C2220h.m1596a((Object) this, "Disable connect device with broaedcastID(" + lsDeviceInfo.getBroadcastID() + ")...", 3);
                this.f2456a.disableConnectDeviceList.add(lsDeviceInfo.getBroadcastID());
                this.f2456a.initEnableConnectDeviceTimer(lsDeviceInfo.getBroadcastID());
            }
        }
    }
}
