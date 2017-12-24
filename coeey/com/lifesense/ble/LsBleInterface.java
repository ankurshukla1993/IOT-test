package com.lifesense.ble;

import android.content.Context;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerAlarmClock;
import com.lifesense.ble.bean.PedometerUserInfo;
import com.lifesense.ble.bean.PersonalUserInfo;
import com.lifesense.ble.bean.VibrationVoice;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.commom.BroadcastType;
import java.util.List;

public interface LsBleInterface {
    public static final int BLUETOOTH_STATE_OFF = 10;
    public static final int BLUETOOTH_STATE_ON = 12;
    public static final int COMMAND_CODE_C9 = 201;
    public static final int COMMAND_CODE_CA = 202;
    public static final int MANAGER_MSG_DISCOVER_USER_LIST = 2;
    public static final int MANAGER_MSG_ON_CONNECT_STATE_CHANGE = 21;
    public static final int MANAGER_MSG_ON_DISCOVERED_DEVICE_INFO = 15;
    public static final int MANAGER_MSG_ON_GATT_CONNECT_STATE_CHANGE = 14;
    public static final int MANAGER_MSG_PAIRED_RESULTS = 3;
    public static final int MANAGER_MSG_RECEIVE_BLOOD_PRESSURE_DATA = 7;
    public static final int MANAGER_MSG_RECEIVE_GENERIC_FAT_DATA = 16;
    public static final int MANAGER_MSG_RECEIVE_HEIGHT_DATA_A2 = 8;
    public static final int MANAGER_MSG_RECEIVE_PEDOMETER_DATA_A2 = 6;
    public static final int MANAGER_MSG_RECEIVE_PEDOMETER_DATA_A4_C9 = 18;
    public static final int MANAGER_MSG_RECEIVE_PEDOMETER_DATA_A4_CA = 19;
    public static final int MANAGER_MSG_RECEIVE_PEDOMETER_SLEEPINFO_DATA_A4 = 20;
    public static final int MANAGER_MSG_RECEIVE_PRODUCT_USER_INFO = 9;
    public static final int MANAGER_MSG_RECEIVE_WEIGHT_DATA_A2 = 4;
    public static final int MANAGER_MSG_RECEIVE_WEIGHT_DATA_A3 = 5;
    public static final int MANAGER_MSG_SCAN_RESULTS = 1;
    public static final int MANAGER_MSG_STARTUP_DATA_RECEIVE_SERVICE = 13;
    public static final int MANAGER_MSG_WRITE_SUCCESS_FOR_PEDOMETER_ALARM_CLOCK = 12;
    public static final int MANAGER_MSG_WRITE_SUCCESS_FOR_PEDOMETER_USER_INFO = 11;
    public static final int MANAGER_MSG_WRITE_SUCCESS_FOR_VIBRATION_VOICE = 17;
    public static final int MANAGER_MSG_WRITE_SUCCESS_FOR_WEIGHT_USER_INFO = 10;

    boolean addMeasureDevice(LsDeviceInfo lsDeviceInfo);

    boolean bindDeviceUser(int i, String str);

    boolean connectDevice(LsDeviceInfo lsDeviceInfo, PersonalUserInfo personalUserInfo, OnLsDeviceConnectListener onLsDeviceConnectListener);

    boolean deleteMeasureDevice(String str);

    void disconnectDevice();

    List getBluetoothState();

    double getFatRateValue(double d, double d2, double d3, int i, int i2, double d4, int i3);

    ManagerStatus getLsBleManagerStatus();

    List getSleepAndExerciseInfo(long j, long j2, List list);

    List getSleepAndExerciseInfo(List list, int i);

    List getStepInfo(List list, int i);

    boolean initialize(Context context);

    boolean isOpenBluetooth();

    boolean isSupportLowEnergy();

    boolean searchLsDevice(SearchCallback searchCallback, List list, BroadcastType broadcastType);

    boolean setEnableScanBroadcastName(List list);

    boolean setMeasureDevice(List list);

    void setPairCallback(PairCallback pairCallback);

    boolean setPedometerAlarmClock(PedometerAlarmClock pedometerAlarmClock);

    boolean setPedometerUserInfo(PedometerUserInfo pedometerUserInfo);

    boolean setProductUserInfo(WeightUserInfo weightUserInfo);

    boolean setVibrationVoice(VibrationVoice vibrationVoice);

    boolean startDataReceiveService(ReceiveDataCallback receiveDataCallback);

    boolean startPairing(LsDeviceInfo lsDeviceInfo, PairCallback pairCallback);

    boolean stopDataReceiveService();

    boolean stopSearch();
}
