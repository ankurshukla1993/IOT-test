package com.lifesense.ble;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.lifesense.ble.bean.BloodPressureData;
import com.lifesense.ble.bean.C2253m;
import com.lifesense.ble.bean.HeightData;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerData;
import com.lifesense.ble.bean.WeightData_A2;
import com.lifesense.ble.bean.WeightData_A3;
import com.lifesense.ble.bean.WeightUserInfo;
import java.util.List;

class C2262i extends Handler {
    final /* synthetic */ LsBleManager f2461a;

    public C2262i(LsBleManager lsBleManager, Looper looper) {
        this.f2461a = lsBleManager;
        super(looper);
    }

    public void handleMessage(Message message) {
        C2253m c2253m;
        List list;
        switch (message.arg1) {
            case 1:
                if (this.f2461a.mLsScanCallback != null && message.obj != null && (message.obj instanceof LsDeviceInfo)) {
                    this.f2461a.mLsScanCallback.onSearchResults((LsDeviceInfo) message.obj);
                    return;
                }
                return;
            case 2:
                if (this.f2461a.mPairCallback != null) {
                    this.f2461a.mPairCallback.onDiscoverUserInfo((List) message.obj);
                    return;
                }
                return;
            case 3:
                if (this.f2461a.mPairCallback == null) {
                    return;
                }
                if (message.obj == null || !(message.obj instanceof LsDeviceInfo)) {
                    this.f2461a.mPairCallback.onPairResults(null, message.arg2);
                    return;
                } else {
                    this.f2461a.mPairCallback.onPairResults((LsDeviceInfo) message.obj, message.arg2);
                    return;
                }
            case 4:
                if (message.obj != null && (message.obj instanceof WeightData_A2)) {
                    if (this.f2461a.mReceiveDataCallback != null) {
                        this.f2461a.mReceiveDataCallback.onReceiveWeightDta_A2((WeightData_A2) message.obj);
                    }
                    if (this.f2461a.mPairCallback != null) {
                        this.f2461a.mPairCallback.onReceiveWeightDataWithOperatingMode2((WeightData_A2) message.obj);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.f2461a.mReceiveDataCallback != null && message.obj != null && (message.obj instanceof WeightData_A3)) {
                    this.f2461a.mReceiveDataCallback.onReceiveWeightData_A3((WeightData_A3) message.obj);
                    return;
                }
                return;
            case 6:
                if (this.f2461a.mReceiveDataCallback != null && message.obj != null && (message.obj instanceof PedometerData)) {
                    this.f2461a.mReceiveDataCallback.onReceivePedometerData((PedometerData) message.obj);
                    return;
                }
                return;
            case 7:
                if (this.f2461a.mReceiveDataCallback != null && message.obj != null && (message.obj instanceof BloodPressureData)) {
                    this.f2461a.mReceiveDataCallback.onReceiveBloodPressureData((BloodPressureData) message.obj);
                    return;
                }
                return;
            case 8:
                if (this.f2461a.mReceiveDataCallback != null && message.obj != null && (message.obj instanceof HeightData)) {
                    this.f2461a.mReceiveDataCallback.onReceiveHeightData((HeightData) message.obj);
                    return;
                }
                return;
            case 9:
                if (this.f2461a.mReceiveDataCallback != null && message.obj != null && (message.obj instanceof WeightUserInfo)) {
                    this.f2461a.mReceiveDataCallback.onReceiveUserInfo((WeightUserInfo) message.obj);
                    return;
                }
                return;
            case 10:
                if (message.obj != null && (message.obj instanceof C2253m)) {
                    c2253m = (C2253m) message.obj;
                    if (this.f2461a.mReceiveDataCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DATA_RECEIVE) {
                        this.f2461a.mReceiveDataCallback.onWeightUserInfoWriteSuccess(c2253m.m1857a(), c2253m.m1860b());
                        return;
                    } else if (this.f2461a.mPairCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DEVICE_PAIR) {
                        this.f2461a.mPairCallback.onSuccessForWriteWeightUserInfo();
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 11:
                if (message.obj != null && (message.obj instanceof C2253m)) {
                    c2253m = (C2253m) message.obj;
                    if (this.f2461a.mReceiveDataCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DATA_RECEIVE) {
                        this.f2461a.mReceiveDataCallback.onPedometerUserInfoWriteSuccess(c2253m.m1857a(), c2253m.m1860b());
                        return;
                    } else if (this.f2461a.mPairCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DEVICE_PAIR) {
                        this.f2461a.mPairCallback.onSuccessForWritePedometerUserInfo();
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 12:
                if (message.obj != null && (message.obj instanceof C2253m)) {
                    c2253m = (C2253m) message.obj;
                    if (this.f2461a.mReceiveDataCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DATA_RECEIVE) {
                        this.f2461a.mReceiveDataCallback.onAlarmClockWriteSuccess(c2253m.m1857a(), c2253m.m1860b());
                        return;
                    } else if (this.f2461a.mPairCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DEVICE_PAIR) {
                        this.f2461a.mPairCallback.onSuccessForWritePedometerAlarmClock();
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 13:
                this.f2461a.startupDataReceiveService();
                return;
            case 14:
                if (message.obj != null && (message.obj instanceof DeviceConnectState)) {
                    if (this.f2461a.genericFatConnectListener != null) {
                        this.f2461a.genericFatConnectListener.onConnectStateChange((DeviceConnectState) message.obj);
                    }
                    if (this.f2461a.mReceiveDataCallback != null) {
                        this.f2461a.mReceiveDataCallback.onConnectStateChange((DeviceConnectState) message.obj);
                        return;
                    }
                    return;
                }
                return;
            case 15:
                if (message.obj != null && (message.obj instanceof LsDeviceInfo) && this.f2461a.genericFatConnectListener != null) {
                    this.f2461a.genericFatConnectListener.onReceiveDeviceInfo((LsDeviceInfo) message.obj);
                    return;
                }
                return;
            case 16:
                if (message.obj != null && (message.obj instanceof WeightData_A2) && this.f2461a.genericFatConnectListener != null) {
                    this.f2461a.genericFatConnectListener.onReceiveMeasuredData((WeightData_A2) message.obj);
                    return;
                }
                return;
            case 17:
                if (message.obj != null && (message.obj instanceof C2253m)) {
                    c2253m = (C2253m) message.obj;
                    if (this.f2461a.mReceiveDataCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DATA_RECEIVE) {
                        this.f2461a.mReceiveDataCallback.onVibrationVoiceWriteSuccess(c2253m.m1857a(), c2253m.m1860b());
                    }
                    if (this.f2461a.mPairCallback != null && this.f2461a.getLsBleManagerStatus() == ManagerStatus.DEVICE_PAIR) {
                        this.f2461a.mPairCallback.onSuccessForWriteVibrationVoice();
                        return;
                    }
                    return;
                }
                return;
            case 18:
                if (message.obj != null && this.f2461a.mReceiveDataCallback != null) {
                    list = (List) message.obj;
                    if (message.arg2 == 201) {
                        this.f2461a.mReceiveDataCallback.onReceivePedometerDataForA4(list, 201);
                        return;
                    }
                    return;
                }
                return;
            case 19:
                if (message.obj != null && this.f2461a.mReceiveDataCallback != null) {
                    list = (List) message.obj;
                    if (message.arg2 == 202) {
                        this.f2461a.mReceiveDataCallback.onReceivePedometerDataForA4(list, 202);
                        return;
                    }
                    return;
                }
                return;
            case 20:
                if (message.obj != null && this.f2461a.mReceiveDataCallback != null) {
                    this.f2461a.mReceiveDataCallback.onReceiveSleepInfoForA4((List) message.obj);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
