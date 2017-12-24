package com.ihealth.communication.control;

public interface IBGDelegate extends DeviceControl {
    void deleteOfflineData();

    void getBattery();

    void getBottleId();

    void getBottleMessage();

    void getOfflineData();

    void holdLink();

    void setBottleId(long j);

    void setBottleMessage(String str);

    void setBottleMessage(String str, int i, String str2);

    void setBottleMessageWithInfo(int i, int i2, String str, int i3, String str2);

    void setTime();

    void setUnit(int i);

    void startMeasure(int i);
}
