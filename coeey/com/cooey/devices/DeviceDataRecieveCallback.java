package com.cooey.devices;

public interface DeviceDataRecieveCallback {
    void onReceiveBloodPressureData(float f, float f2, float f3, float f4);

    void onReceiveWeightData_A3(double d, String str, float f, float f2, float f3, float f4, float f5, float f6);

    void onReceiveWeightDta_A2(double d, String str, float f, float f2, float f3, float f4, float f5, float f6);

    void onRecieveGlucoseData(float f);
}
