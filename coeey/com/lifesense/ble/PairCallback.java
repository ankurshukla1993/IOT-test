package com.lifesense.ble;

import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.WeightData_A2;
import java.util.List;

public abstract class PairCallback {
    public void onDiscoverUserInfo(List list) {
    }

    public void onPairResults(LsDeviceInfo lsDeviceInfo, int i) {
    }

    public void onReceiveWeightDataWithOperatingMode2(WeightData_A2 weightData_A2) {
    }

    public void onSuccessForWritePedometerAlarmClock() {
    }

    public void onSuccessForWritePedometerUserInfo() {
    }

    public void onSuccessForWriteVibrationVoice() {
    }

    public void onSuccessForWriteWeightUserInfo() {
    }
}
