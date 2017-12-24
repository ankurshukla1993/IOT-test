package com.lifesense.ble;

import com.lifesense.ble.bean.BloodPressureData;
import com.lifesense.ble.bean.HeightData;
import com.lifesense.ble.bean.PedometerData;
import com.lifesense.ble.bean.WeightData_A2;
import com.lifesense.ble.bean.WeightData_A3;
import com.lifesense.ble.bean.WeightUserInfo;
import java.util.List;

public abstract class ReceiveDataCallback {
    public void onAlarmClockWriteSuccess(String str, String str2) {
    }

    public void onConnectStateChange(DeviceConnectState deviceConnectState) {
    }

    public void onPedometerUserInfoWriteSuccess(String str, String str2) {
    }

    public void onReceiveBloodPressureData(BloodPressureData bloodPressureData) {
    }

    public void onReceiveHeightData(HeightData heightData) {
    }

    public void onReceivePedometerData(PedometerData pedometerData) {
    }

    public void onReceivePedometerDataForA4(List list, int i) {
    }

    public void onReceiveSleepInfoForA4(List list) {
    }

    public void onReceiveUserInfo(WeightUserInfo weightUserInfo) {
    }

    public void onReceiveWeightData_A3(WeightData_A3 weightData_A3) {
    }

    public void onReceiveWeightDta_A2(WeightData_A2 weightData_A2) {
    }

    public void onVibrationVoiceWriteSuccess(String str, String str2) {
    }

    public void onWeightUserInfoWriteSuccess(String str, String str2) {
    }
}
