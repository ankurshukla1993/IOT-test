package com.lifesense.ble;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.lifesense.ble.bean.DeviceTypeConstants;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.PedometerAlarmClock;
import com.lifesense.ble.bean.PedometerUserInfo;
import com.lifesense.ble.bean.PersonalUserInfo;
import com.lifesense.ble.bean.VibrationVoice;
import com.lifesense.ble.bean.WeightData_A2;
import com.lifesense.ble.bean.WeightData_A3;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.commom.BroadcastType;
import com.lifesense.ble.commom.C2255a;
import com.lifesense.ble.commom.C2256b;
import com.lifesense.ble.commom.DeviceType;
import com.lifesense.ble.p003a.C2214b;
import com.lifesense.ble.p003a.C2220h;
import com.lifesense.ble.p003a.C2221i;
import com.lifesense.ble.p003a.C2223k;
import com.lifesense.ble.p003a.C2224l;
import com.lifesense.ble.p003a.C2228p;
import com.lifesense.ble.p003a.C2229q;
import com.lifesense.ble.p003a.C2234v;
import com.lifesense.ble.p003a.C2235w;
import com.lifesense.ble.p003a.ae;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Timer;
import java.util.UUID;

public class LsBleManager implements LsBleInterface {
    private static Context mAppContext;
    private static LsBleManager mLsBleManager;
    private List a4SleepInfoList;
    private C2214b bleConnector;
    private Map bleDeviceAddressMap;
    private C2223k blePeripheralListener = new C2259f(this);
    private boolean bleStateChangeFlag;
    private List bleStateList;
    private List c9PedometerDataList;
    private List caPedometerDataList;
    private Handler callbackHandler;
    private HandlerThread callbackHandlerThread;
    private PersonalUserInfo currenPersonalUserInfo;
    private List disableConnectDeviceList;
    private List enableScanBroadcastName;
    private BroadcastType enableScanBroadcastType;
    private List enableScanServicesUUID;
    private boolean enableSpecialConditions;
    private OnLsDeviceConnectListener genericFatConnectListener;
    private boolean initFlag;
    private boolean isStopDataReceived;
    private Map longConnectedDeviceMap;
    private C2256b lsDeviceProperty;
    private SearchCallback mLsScanCallback;
    private PairCallback mPairCallback;
    private ReceiveDataCallback mReceiveDataCallback;
    private ManagerStatus managerStatus;
    private Map measuredDeviceMap;
    private Map pedometerAlarmClockMap;
    private Map pedometerUserInfoMap;
    private C2224l protocolCallback = new C2258e(this);
    private C2235w protocolHendlerCenter;
    private C2228p scanResultsCallback = new C2257d(this);
    private String tempProtocolType = "unknown";
    private WeightData_A2 tempWeightData_A2;
    private WeightData_A3 tempWeightData_A3;
    private Map vibrationVoiceMap;
    private Map weightUserInfoMap;

    private LsBleManager() {
    }

    private void addAddressToMap(String str, String str2, String str3) {
        if (str3 == null || !str3.equals(ae.A4.toString())) {
            str = str.charAt(0) == '1' ? str.length() > 6 ? str.substring(1, 6) : str.substring(1) : str.charAt(0) == '0' ? str.substring(6) : null;
        }
        if (!this.bleDeviceAddressMap.containsKey(str)) {
            C2220h.m1596a((Object) this, "put device to map,Device-" + str2 + ";key-" + str, 3);
            this.bleDeviceAddressMap.put(str, str2);
        }
    }

    private boolean addScanDeviceType(DeviceType deviceType) {
        if (!this.initFlag || deviceType == null || deviceType == DeviceType.UNKNOWN) {
            C2220h.m1596a((Object) this, "Failed to get service uuidaccording to the device type(" + deviceType + ")...", 1);
            return false;
        }
        Collection b = this.lsDeviceProperty.m1949b(this.lsDeviceProperty.m1946a(deviceType));
        if (b != null) {
            return !this.enableScanServicesUUID.containsAll(b) ? this.enableScanServicesUUID.addAll(b) : true;
        } else {
            C2220h.m1596a((Object) this, "Failed to get service uuidaccording to the device type(" + deviceType + ")...", 1);
            return false;
        }
    }

    private void addScanServicesUUIDByDeviceType(List list) {
        if (list != null) {
            for (DeviceType deviceType : list) {
                if (!(deviceType == null || deviceType == DeviceType.UNKNOWN)) {
                    Collection b = this.lsDeviceProperty.m1949b(this.lsDeviceProperty.m1946a(deviceType));
                    if (!(b == null || this.enableScanServicesUUID.containsAll(b))) {
                        this.enableScanServicesUUID.addAll(b);
                    }
                }
            }
        }
    }

    private void cancelAllLongConnection() {
        if (this.longConnectedDeviceMap != null && !this.longConnectedDeviceMap.isEmpty()) {
            C2220h.m1596a((Object) this, "cancel all long connect with peripheral...", 2);
            for (Entry value : this.longConnectedDeviceMap.entrySet()) {
                C2229q c2229q = (C2229q) value.getValue();
                if (c2229q != null) {
                    C2220h.m1596a((Object) this, "cancel long connect with peripheral = " + c2229q.m1701b(), 3);
                    c2229q.m1697a();
                }
            }
            this.longConnectedDeviceMap.clear();
            if (this.disableConnectDeviceList != null) {
                this.disableConnectDeviceList.clear();
            }
        }
    }

    private void cancelLongConnectionWithBroadcastId(String str) {
        if (str != null && this.longConnectedDeviceMap != null && !this.longConnectedDeviceMap.isEmpty()) {
            C2229q c2229q = (C2229q) this.longConnectedDeviceMap.get(str);
            if (c2229q != null) {
                C2220h.m1596a((Object) this, "cancel long connect with peripheral = " + c2229q.m1701b(), 3);
                c2229q.m1697a();
            }
        }
    }

    private boolean checkBluetoothState() {
        boolean z;
        this.bleStateList = new ArrayList();
        if (!isOpenBluetooth()) {
            C2220h.m1596a((Object) this, "Bluetooth State-Turn off", 1);
            if (!isBluetoothStateExist(C2254c.BLUETOOTH_TURN_OFF)) {
                this.bleStateChangeFlag = true;
                this.bleStateList.add(C2254c.BLUETOOTH_TURN_OFF);
                z = false;
                if (!isSupportLowEnergy()) {
                    C2220h.m1596a((Object) this, "Bluetooth State-Low energy not supported ", 1);
                    if (!isBluetoothStateExist(C2254c.LOW_ENERGY_NOT_SUPPORTED)) {
                        this.bleStateList.add(C2254c.LOW_ENERGY_NOT_SUPPORTED);
                        z = false;
                    }
                }
                if (getSdkVersion() < 18) {
                    C2220h.m1596a((Object) this, "Bluetooth State-OS SDK not supported", 1);
                    if (!isBluetoothStateExist(C2254c.OS_SDK_NOT_SUPPORTED)) {
                        this.bleStateList.add(C2254c.OS_SDK_NOT_SUPPORTED);
                        z = false;
                    }
                }
                if (z && !isBluetoothStateExist(C2254c.BLUETOOTH_AVAILABLE)) {
                    this.bleStateList.add(C2254c.BLUETOOTH_AVAILABLE);
                }
                return z;
            }
        }
        z = true;
        if (isSupportLowEnergy()) {
            C2220h.m1596a((Object) this, "Bluetooth State-Low energy not supported ", 1);
            if (isBluetoothStateExist(C2254c.LOW_ENERGY_NOT_SUPPORTED)) {
                this.bleStateList.add(C2254c.LOW_ENERGY_NOT_SUPPORTED);
                z = false;
            }
        }
        if (getSdkVersion() < 18) {
            C2220h.m1596a((Object) this, "Bluetooth State-OS SDK not supported", 1);
            if (isBluetoothStateExist(C2254c.OS_SDK_NOT_SUPPORTED)) {
                this.bleStateList.add(C2254c.OS_SDK_NOT_SUPPORTED);
                z = false;
            }
        }
        this.bleStateList.add(C2254c.BLUETOOTH_AVAILABLE);
        return z;
    }

    private boolean checkConnectionConstraints(LsDeviceInfo lsDeviceInfo) {
        boolean z = true;
        if (lsDeviceInfo != null) {
            if (lsDeviceInfo.getBroadcastID() == null || lsDeviceInfo.getDeviceType() == null || lsDeviceInfo.getDeviceId() == null) {
                C2220h.m1596a((Object) this, "Error ! some property of LsDeviceInfo is invalid(null)", 1);
                z = false;
            } else if (lsDeviceInfo.getBroadcastID().length() == 0 || lsDeviceInfo.getDeviceType().length() == 0 || lsDeviceInfo.getDeviceId().length() == 0) {
                C2220h.m1596a((Object) this, "Error ! some property of LsDeviceInfo is invalid(0)", 1);
                return false;
            }
            return z;
        }
        C2220h.m1596a((Object) this, "Error ! is null ? true", 1);
        return false;
    }

    private boolean checkScanConditions(String str) {
        if (this.enableScanServicesUUID == null || this.enableScanServicesUUID.isEmpty()) {
            return false;
        }
        for (String equals : this.enableScanServicesUUID) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSpecialConditions(String str) {
        return (str == null || str.length() <= 0) ? false : this.enableSpecialConditions ? (this.enableScanBroadcastName == null || this.enableScanBroadcastName.size() <= 0) ? false : this.enableScanBroadcastName.contains(str) : true;
    }

    private LsDeviceInfo findDeviceByBroadcastID(String str) {
        LsDeviceInfo lsDeviceInfo = null;
        if (str != null && this.measuredDeviceMap != null && this.measuredDeviceMap.size() > 0 && this.measuredDeviceMap.containsKey(str)) {
            lsDeviceInfo = (LsDeviceInfo) this.measuredDeviceMap.get(str);
        }
        if (lsDeviceInfo != null) {
            C2220h.m1596a((Object) this, "find device by broacast name success-" + str, 3);
        } else {
            C2220h.m1596a((Object) this, "find device by broacast name(" + str + ") has device ? no", 3);
        }
        return lsDeviceInfo;
    }

    private boolean findDeviceByServiceUuid(List list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (UUID uuid : list) {
            if (this.lsDeviceProperty.m1948a(ae.A2).contains(uuid.toString())) {
                this.tempProtocolType = ae.A2.toString();
                return checkScanConditions(uuid.toString());
            } else if (this.lsDeviceProperty.m1948a(ae.A3).contains(uuid.toString())) {
                this.tempProtocolType = ae.A3.toString();
                return checkScanConditions(uuid.toString());
            } else if (this.lsDeviceProperty.m1948a(ae.GENERIC_FAT).contains(uuid.toString())) {
                this.tempProtocolType = ae.GENERIC_FAT.toString();
                return checkScanConditions(uuid.toString());
            } else if (this.lsDeviceProperty.m1948a(ae.A4).contains(uuid.toString())) {
                this.tempProtocolType = ae.A4.toString();
                return checkScanConditions(uuid.toString());
            }
        }
        return false;
    }

    private String getConnectAddressBykey(String str) {
        if (str == null || this.bleDeviceAddressMap == null || this.bleDeviceAddressMap.size() <= 0) {
            return null;
        }
        if (this.bleDeviceAddressMap.containsKey(str)) {
            return (String) this.bleDeviceAddressMap.get(str);
        }
        C2220h.m1596a((Object) this, "Failed to get address by key(" + str + ")", 1);
        return null;
    }

    private boolean getMeasureData(LsDeviceInfo lsDeviceInfo, String str) {
        if (this.protocolHendlerCenter == null || lsDeviceInfo == null) {
            return false;
        }
        if (hasUserInfoToWrite()) {
            String deviceType = lsDeviceInfo.getDeviceType();
            if (deviceType != null && deviceType.length() > 0) {
                if (deviceType.equals(DeviceTypeConstants.PEDOMETER)) {
                    PedometerUserInfo pedometerUserInfo = (PedometerUserInfo) this.pedometerUserInfoMap.get(lsDeviceInfo.getDeviceId());
                    this.protocolHendlerCenter.m1803a((PedometerAlarmClock) this.pedometerAlarmClockMap.get(lsDeviceInfo.getDeviceId()));
                    this.protocolHendlerCenter.m1804a(pedometerUserInfo);
                } else if (deviceType.equals(DeviceTypeConstants.FAT_SCALE) || deviceType.equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                    this.protocolHendlerCenter.m1806a((WeightUserInfo) this.weightUserInfoMap.get(lsDeviceInfo.getDeviceId()));
                } else if (deviceType.equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                    this.protocolHendlerCenter.m1805a((VibrationVoice) this.vibrationVoiceMap.get(lsDeviceInfo.getDeviceId()));
                }
            }
        }
        String connectAddressBykey = getConnectAddressBykey(lsDeviceInfo.getBroadcastID());
        Queue a = C2234v.m1710a(str, lsDeviceInfo, this.protocolHendlerCenter.m1811c(), this.protocolHendlerCenter.m1813e(), this.protocolHendlerCenter.m1812d(), this.protocolHendlerCenter.m1814f(), ManagerStatus.DATA_RECEIVE);
        if (str.equals(ae.A4.toString())) {
            if (this.longConnectedDeviceMap == null || this.longConnectedDeviceMap.size() != 0) {
                Log.w("LS-BLE", " can not connect two pedometer at the same time.....");
            } else {
                this.disableConnectDeviceList.add(lsDeviceInfo.getBroadcastID());
                C2229q c2229q = new C2229q(mAppContext);
                c2229q.m1698a(this.blePeripheralListener);
                c2229q.m1702b(this.protocolHendlerCenter.m1813e());
                c2229q.m1699a(lsDeviceInfo, connectAddressBykey, a);
                this.longConnectedDeviceMap.put(lsDeviceInfo.getBroadcastID(), c2229q);
            }
            if (this.measuredDeviceMap != null && this.measuredDeviceMap.size() > 1) {
                Message obtainMessage = this.callbackHandler.obtainMessage();
                obtainMessage.arg1 = 13;
                this.callbackHandler.sendMessage(obtainMessage);
            }
        } else {
            this.protocolHendlerCenter.m1810b(lsDeviceInfo, connectAddressBykey, a);
        }
        return true;
    }

    private UUID[] getPairedServicesUUID() {
        if (this.enableScanServicesUUID == null || this.enableScanServicesUUID.size() <= 0) {
            return null;
        }
        UUID[] uuidArr = new UUID[this.enableScanServicesUUID.size()];
        int i = 0;
        for (String fromString : this.enableScanServicesUUID) {
            uuidArr[i] = UUID.fromString(fromString);
            i++;
        }
        return uuidArr;
    }

    private int getSdkVersion() {
        return VERSION.SDK_INT;
    }

    private boolean handleScanResultsFilter(char c, List list, String str) {
        if (this.enableScanBroadcastType == BroadcastType.ALL) {
            return findDeviceByServiceUuid(list);
        }
        if (this.enableScanBroadcastType != BroadcastType.PAIR || c != '1') {
            return (this.enableScanBroadcastType == BroadcastType.NORMAL && c == '0') ? findDeviceByServiceUuid(list) : false;
        } else {
            String str2 = null;
            if (str != null) {
                str2 = str.length() > 6 ? str.substring(1, 6) : str.substring(1);
            }
            return checkSpecialConditions(str2) ? findDeviceByServiceUuid(list) : false;
        }
    }

    private void handleScanResultsForDataUploadMode(String str, String str2) {
        if (str != null && str.length() > 6) {
            LsDeviceInfo lsDeviceInfo = null;
            if (!str2.equals(ae.A4.toString())) {
                lsDeviceInfo = findDeviceByBroadcastID(str.substring(6));
            } else if (this.disableConnectDeviceList == null || !this.disableConnectDeviceList.contains(str)) {
                lsDeviceInfo = findDeviceByBroadcastID(str);
            } else {
                C2220h.m1596a((Object) this, "Failed to get measured data with broadcastId(" + str + "),for disable connect time...", 3);
            }
            if (lsDeviceInfo != null && this.bleConnector != null) {
                this.bleConnector.m1592d();
                getMeasureData(lsDeviceInfo, str2);
            }
        }
    }

    private void handleScanResultsForSearchMode(char c, List list, String str, String str2, byte[] bArr) {
        if (handleScanResultsFilter(c, list, str)) {
            LsDeviceInfo lsDeviceInfo = new LsDeviceInfo();
            if (str.charAt(0) == '0' && str.length() > 6) {
                lsDeviceInfo.setDeviceName(str.substring(1));
                lsDeviceInfo.setBroadcastID(str.substring(6));
            } else if (str.charAt(0) == '1') {
                lsDeviceInfo.setDeviceName(str.length() > 6 ? str.substring(1, 6) : str.substring(1));
                lsDeviceInfo.setBroadcastID(null);
            }
            if (str.length() >= 6) {
                lsDeviceInfo.setModelNumber(this.lsDeviceProperty.m1951d(str.substring(1, 6)));
            }
            lsDeviceInfo.setDeviceType(C2221i.m1610b(list));
            lsDeviceInfo.setProtocolType(this.tempProtocolType);
            if (str2 == null || !str2.equals(ae.A4.toString())) {
                lsDeviceInfo.setPairStatus(Integer.parseInt(str.substring(0, 1)));
            } else {
                lsDeviceInfo.setDeviceName(C2221i.m1611b(bArr));
                lsDeviceInfo.setBroadcastID(str);
                lsDeviceInfo.setPairStatus(0);
            }
            Message obtainMessage = this.callbackHandler.obtainMessage();
            obtainMessage.obj = lsDeviceInfo;
            obtainMessage.arg1 = 1;
            this.callbackHandler.sendMessage(obtainMessage);
        }
    }

    private boolean hasUserInfoToWrite() {
        return (this.pedometerUserInfoMap != null && this.pedometerUserInfoMap.size() > 0) || ((this.weightUserInfoMap != null && this.weightUserInfoMap.size() > 0) || ((this.pedometerAlarmClockMap != null && this.pedometerAlarmClockMap.size() > 0) || (this.vibrationVoiceMap != null && this.vibrationVoiceMap.size() > 0)));
    }

    private void initEnableConnectDeviceTimer(String str) {
        new Timer().schedule(new C2261h(this, str), (long) 240000);
    }

    private boolean isBluetoothStateExist(C2254c c2254c) {
        return this.bleStateList.contains(c2254c);
    }

    public static LsBleManager newInstance() {
        LsBleManager lsBleManager;
        synchronized (LsBleManager.class) {
            try {
                if (mLsBleManager == null) {
                    lsBleManager = new LsBleManager();
                    mLsBleManager = lsBleManager;
                } else {
                    lsBleManager = mLsBleManager;
                }
            } catch (Throwable th) {
                Class cls = LsBleManager.class;
            }
        }
        return lsBleManager;
    }

    private void registerBleStateChangeReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        context.registerReceiver(new C2240b(new C2260g(this)), intentFilter);
    }

    private void removeDisableConnectDevice(String str) {
        int i = 0;
        if (str != null && str.length() > 0) {
            if (this.disableConnectDeviceList != null && this.disableConnectDeviceList.size() > 0 && this.disableConnectDeviceList.contains(str)) {
                C2220h.m1596a((Object) this, "Enable reconnect device with broadcastID(" + str + ")", 3);
                this.disableConnectDeviceList.remove(str);
                i = 1;
            }
            if (this.longConnectedDeviceMap != null && this.longConnectedDeviceMap.containsKey(str)) {
                C2220h.m1596a((Object) this, "Remove long connect device with broadcastID(" + str + ")", 1);
                this.longConnectedDeviceMap.remove(str);
                i = 1;
            }
            if (this.measuredDeviceMap != null && this.measuredDeviceMap.size() == 1 && r0 != 0 && !this.isStopDataReceived) {
                Message obtainMessage = this.callbackHandler.obtainMessage();
                obtainMessage.arg1 = 13;
                this.callbackHandler.sendMessage(obtainMessage);
            }
        }
    }

    private void removeProductUserInfo(String str, String str2) {
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            if (str.equals(DeviceTypeConstants.FAT_SCALE) || str.equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                if (!this.weightUserInfoMap.isEmpty() && this.weightUserInfoMap.containsKey(str2)) {
                    this.weightUserInfoMap.remove(str2);
                }
                if (!this.vibrationVoiceMap.isEmpty() && this.vibrationVoiceMap.containsKey(str2)) {
                    this.vibrationVoiceMap.remove(str2);
                }
            } else if (str.equals(DeviceTypeConstants.PEDOMETER)) {
                if (!this.pedometerUserInfoMap.isEmpty() && this.pedometerUserInfoMap.containsKey(str2)) {
                    this.pedometerUserInfoMap.remove(str2);
                }
                if (!this.pedometerAlarmClockMap.isEmpty() && this.pedometerAlarmClockMap.containsKey(str2)) {
                    this.pedometerAlarmClockMap.remove(str2);
                }
            }
        }
    }

    private void setBleCurrentState(int i) {
        if (i == 10) {
            if (this.managerStatus == ManagerStatus.DATA_RECEIVE) {
                this.bleStateChangeFlag = true;
            } else if (this.managerStatus == ManagerStatus.DEVICE_PAIR) {
                this.bleStateChangeFlag = true;
                Message obtainMessage = this.callbackHandler.obtainMessage();
                obtainMessage.obj = null;
                obtainMessage.arg1 = 3;
                obtainMessage.arg2 = -1;
                this.callbackHandler.sendMessage(obtainMessage);
            }
        } else if (i == 12 && this.managerStatus == ManagerStatus.DATA_RECEIVE && this.bleStateChangeFlag) {
            C2220h.m1596a((Object) this, "continue to read the measurement data flow-", 1);
            this.bleStateChangeFlag = false;
            startupDataReceiveService();
        }
    }

    private void setManagerStatus(ManagerStatus managerStatus, String str) {
        synchronized (this) {
            if (managerStatus != null) {
                C2220h.m1596a((Object) this, "set manager status: " + managerStatus.toString() + ", current working mode is :" + str, 3);
            }
            this.managerStatus = managerStatus;
        }
    }

    private boolean startupDataReceiveService() {
        if (this.initFlag && checkBluetoothState()) {
            if (this.bleConnector.m1591c()) {
                this.bleConnector.m1592d();
            }
            C2220h.m1596a((Object) this, "start up data receive success-" + this.measuredDeviceMap.size(), 3);
            setManagerStatus(ManagerStatus.DATA_RECEIVE, "start up data receive");
            this.enableScanBroadcastType = BroadcastType.NORMAL;
            return this.bleConnector.m1584a(this.scanResultsCallback);
        }
        C2220h.m1596a((Object) this, "Failed to start data receive...", 1);
        return false;
    }

    private void updatePedometerWeekTarget(PedometerUserInfo pedometerUserInfo) {
        if (this.longConnectedDeviceMap != null && !this.longConnectedDeviceMap.isEmpty()) {
            C2220h.m1596a((Object) this, "update week target,find long connect peripheral...", 2);
            for (Entry value : this.longConnectedDeviceMap.entrySet()) {
                C2229q c2229q = (C2229q) value.getValue();
                if (c2229q != null) {
                    C2220h.m1596a((Object) this, "update week target to peripheral = " + c2229q.m1701b(), 3);
                    c2229q.m1700a(pedometerUserInfo);
                }
            }
        }
    }

    public boolean addMeasureDevice(LsDeviceInfo lsDeviceInfo) {
        if (lsDeviceInfo == null || !this.initFlag || this.measuredDeviceMap == null) {
            C2220h.m1596a((Object) this, "Failed to add measure device(null)", 1);
            return false;
        } else if (!checkConnectionConstraints(lsDeviceInfo)) {
            return false;
        } else {
            this.measuredDeviceMap.put(lsDeviceInfo.getBroadcastID(), lsDeviceInfo);
            if (this.protocolHendlerCenter != null) {
                if (this.measuredDeviceMap.size() == 1) {
                    this.protocolHendlerCenter.m1807a(true);
                } else {
                    this.protocolHendlerCenter.m1807a(false);
                }
            }
            return addScanDeviceType(this.lsDeviceProperty.m1950c(lsDeviceInfo.getDeviceType()));
        }
    }

    public boolean bindDeviceUser(int i, String str) {
        return (!this.initFlag || this.protocolHendlerCenter == null || str == null || str.length() <= 0) ? false : this.protocolHendlerCenter.m1808a(i, str);
    }

    public boolean connectDevice(LsDeviceInfo lsDeviceInfo, PersonalUserInfo personalUserInfo, OnLsDeviceConnectListener onLsDeviceConnectListener) {
        if (!this.initFlag || this.protocolHendlerCenter == null || lsDeviceInfo == null || onLsDeviceConnectListener == null || !checkBluetoothState()) {
            return false;
        }
        if (getLsBleManagerStatus() == ManagerStatus.FREE) {
            setManagerStatus(ManagerStatus.DATA_RECEIVE, "connectDeviceWithCallback");
            this.genericFatConnectListener = onLsDeviceConnectListener;
            String substring = (lsDeviceInfo.getDeviceName() == null || lsDeviceInfo.getDeviceName().length() <= 5) ? null : lsDeviceInfo.getDeviceName().substring(5);
            this.currenPersonalUserInfo = personalUserInfo;
            this.protocolHendlerCenter.m1810b(lsDeviceInfo, getConnectAddressBykey(substring), C2234v.m1710a(lsDeviceInfo.getProtocolType(), lsDeviceInfo, null, null, null, null, ManagerStatus.DATA_RECEIVE));
            return true;
        }
        C2220h.m1596a((Object) this, "Failed to pairing device,for manager status...", 1);
        return false;
    }

    public boolean deleteMeasureDevice(String str) {
        if (str == null || str.length() <= 0) {
            C2220h.m1596a((Object) this, "failed to deleted the measure device,by broadcastID -( " + str + " )", 1);
            return false;
        } else if (this.measuredDeviceMap == null || this.measuredDeviceMap.size() <= 0) {
            C2220h.m1596a((Object) this, "Failed  to deleted the measure device,no device", 3);
            return false;
        } else if (!this.measuredDeviceMap.containsKey(str)) {
            return false;
        } else {
            this.measuredDeviceMap.remove(str);
            C2220h.m1596a((Object) this, "successfully  to deleted the measure device,by broadcastID -( " + str + " )", 3);
            removeDisableConnectDevice(str);
            cancelLongConnectionWithBroadcastId(str);
            return true;
        }
    }

    public void disconnectDevice() {
        if (this.initFlag && this.protocolHendlerCenter != null) {
            this.protocolHendlerCenter.m1809b();
        }
    }

    public List getBluetoothState() {
        return (this.bleStateList == null || this.bleStateList.size() <= 0) ? null : this.bleStateList;
    }

    public double getFatRateValue(double d, double d2, double d3, int i, int i2, double d4, int i3) {
        return C2255a.m1863a(d, d2, d3, i, i2, d4, i3);
    }

    public ManagerStatus getLsBleManagerStatus() {
        ManagerStatus managerStatus;
        synchronized (this) {
            managerStatus = (!this.initFlag || this.managerStatus == null) ? null : this.managerStatus;
        }
        return managerStatus;
    }

    public List getSleepAndExerciseInfo(long j, long j2, List list) {
        return (list == null || list.size() <= 0) ? null : C2255a.m1875a(j, j2, list);
    }

    public List getSleepAndExerciseInfo(List list, int i) {
        return (list == null || list.size() <= 0) ? null : C2255a.m1891b(list, i);
    }

    public List getStepInfo(List list, int i) {
        return (list == null || list.size() <= 0) ? null : C2255a.m1878a(list, i);
    }

    public boolean initialize(Context context) {
        if (context == null || getSdkVersion() < 18) {
            C2220h.m1596a((Object) this, "Failed to initialize LsBleManager,because of sdk version-" + getSdkVersion(), 1);
            this.initFlag = false;
            return false;
        }
        C2220h.m1597a(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(new Date(System.currentTimeMillis())));
        this.isStopDataReceived = false;
        mAppContext = context;
        setManagerStatus(ManagerStatus.FREE, "initialize lsBleManager instance...");
        this.enableSpecialConditions = false;
        this.lsDeviceProperty = C2256b.m1943a();
        this.callbackHandlerThread = new HandlerThread("LsBleManagerHandler");
        this.callbackHandlerThread.start();
        this.callbackHandler = new C2262i(this, this.callbackHandlerThread.getLooper());
        this.bleStateChangeFlag = false;
        this.bleDeviceAddressMap = new HashMap();
        this.pedometerAlarmClockMap = new HashMap();
        this.weightUserInfoMap = new HashMap();
        this.pedometerUserInfoMap = new HashMap();
        this.measuredDeviceMap = new HashMap();
        this.vibrationVoiceMap = new HashMap();
        this.enableScanServicesUUID = new ArrayList();
        this.c9PedometerDataList = new ArrayList();
        this.a4SleepInfoList = new ArrayList();
        this.caPedometerDataList = new ArrayList();
        this.bleConnector = new C2214b();
        this.bleConnector.m1583a(context);
        this.protocolHendlerCenter = new C2235w();
        this.protocolHendlerCenter.m1801a(this.bleConnector, this.protocolCallback);
        this.protocolHendlerCenter.m1800a(mAppContext);
        this.disableConnectDeviceList = new ArrayList();
        this.longConnectedDeviceMap = new HashMap();
        registerBleStateChangeReceiver(context);
        this.initFlag = true;
        checkBluetoothState();
        return true;
    }

    public boolean isOpenBluetooth() {
        return (!this.initFlag || this.bleConnector == null) ? false : this.bleConnector.m1590b();
    }

    public boolean isSupportLowEnergy() {
        return (!this.initFlag || this.bleConnector == null) ? false : this.bleConnector.m1582a();
    }

    public boolean searchLsDevice(SearchCallback searchCallback, List list, BroadcastType broadcastType) {
        if (!this.initFlag || this.bleConnector == null || searchCallback == null || !checkBluetoothState()) {
            C2220h.m1596a((Object) this, "Failed to search device,for null...", 1);
            return false;
        } else if (getLsBleManagerStatus() == ManagerStatus.FREE) {
            this.mLsScanCallback = searchCallback;
            setManagerStatus(ManagerStatus.DEVICE_SEARCH, "Search Device");
            this.enableScanBroadcastType = broadcastType;
            addScanServicesUUIDByDeviceType(list);
            return this.bleConnector.m1584a(this.scanResultsCallback);
        } else {
            C2220h.m1596a((Object) this, "Failed to search device,for manager status..." + this.managerStatus, 1);
            return false;
        }
    }

    public boolean setEnableScanBroadcastName(List list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        this.enableScanBroadcastName = list;
        this.enableSpecialConditions = true;
        return true;
    }

    public boolean setMeasureDevice(List list) {
        if (this.measuredDeviceMap != null && this.measuredDeviceMap.size() > 0) {
            this.measuredDeviceMap.clear();
            if (this.disableConnectDeviceList != null && this.disableConnectDeviceList.size() > 0) {
                this.disableConnectDeviceList.clear();
            }
            cancelAllLongConnection();
        }
        if (!this.initFlag || list == null || list.size() <= 0) {
            return false;
        }
        for (LsDeviceInfo lsDeviceInfo : list) {
            if (!checkConnectionConstraints(lsDeviceInfo)) {
                return false;
            }
            this.measuredDeviceMap.put(lsDeviceInfo.getBroadcastID(), lsDeviceInfo);
            addScanDeviceType(this.lsDeviceProperty.m1950c(lsDeviceInfo.getDeviceType()));
        }
        if (this.protocolHendlerCenter != null) {
            if (this.measuredDeviceMap.size() == 1) {
                this.protocolHendlerCenter.m1807a(true);
            } else {
                this.protocolHendlerCenter.m1807a(false);
            }
        }
        return true;
    }

    public void setPairCallback(PairCallback pairCallback) {
        if (pairCallback != null) {
            this.mPairCallback = pairCallback;
        }
    }

    public boolean setPedometerAlarmClock(PedometerAlarmClock pedometerAlarmClock) {
        if (pedometerAlarmClock == null || this.pedometerAlarmClockMap == null || !this.initFlag) {
            return false;
        }
        String deviceId = pedometerAlarmClock.getDeviceId();
        if (deviceId == null || deviceId.length() <= 0) {
            if (this.protocolHendlerCenter == null) {
                return false;
            }
            this.protocolHendlerCenter.m1803a(pedometerAlarmClock);
            return true;
        } else if (this.pedometerAlarmClockMap.containsValue(pedometerAlarmClock)) {
            return true;
        } else {
            if (this.pedometerAlarmClockMap.containsKey(deviceId)) {
                this.pedometerAlarmClockMap.remove(deviceId);
            }
            this.pedometerAlarmClockMap.put(deviceId, pedometerAlarmClock);
            return true;
        }
    }

    public boolean setPedometerUserInfo(PedometerUserInfo pedometerUserInfo) {
        if (pedometerUserInfo == null || this.pedometerUserInfoMap == null || !this.initFlag) {
            return false;
        }
        String deviceId = pedometerUserInfo.getDeviceId();
        if (deviceId == null || deviceId.length() == 0) {
            if (this.protocolHendlerCenter == null) {
                return false;
            }
            this.protocolHendlerCenter.m1804a(pedometerUserInfo);
            return true;
        } else if (this.pedometerUserInfoMap.containsValue(pedometerUserInfo)) {
            return true;
        } else {
            if (this.pedometerUserInfoMap.containsKey(deviceId)) {
                updatePedometerWeekTarget(pedometerUserInfo);
                this.pedometerUserInfoMap.remove(deviceId);
            }
            this.pedometerUserInfoMap.put(deviceId, pedometerUserInfo);
            return true;
        }
    }

    public boolean setProductUserInfo(WeightUserInfo weightUserInfo) {
        if (!this.initFlag || weightUserInfo == null || this.weightUserInfoMap == null) {
            return false;
        }
        String deviceId = weightUserInfo.getDeviceId();
        if (deviceId == null || deviceId.length() == 0) {
            if (this.protocolHendlerCenter == null) {
                return false;
            }
            this.protocolHendlerCenter.m1806a(weightUserInfo);
            return true;
        } else if (this.weightUserInfoMap.containsValue(weightUserInfo)) {
            return true;
        } else {
            if (this.weightUserInfoMap.containsKey(deviceId)) {
                this.weightUserInfoMap.remove(deviceId);
            }
            this.weightUserInfoMap.put(deviceId, weightUserInfo);
            return true;
        }
    }

    public boolean setVibrationVoice(VibrationVoice vibrationVoice) {
        if (vibrationVoice == null || this.vibrationVoiceMap == null || !this.initFlag) {
            return false;
        }
        String deviceId = vibrationVoice.getDeviceId();
        if (deviceId == null || deviceId.length() == 0) {
            if (this.protocolHendlerCenter == null) {
                return false;
            }
            this.protocolHendlerCenter.m1805a(vibrationVoice);
            return true;
        } else if (this.vibrationVoiceMap.containsValue(vibrationVoice)) {
            return true;
        } else {
            if (this.vibrationVoiceMap.containsKey(deviceId)) {
                this.vibrationVoiceMap.remove(deviceId);
            }
            this.vibrationVoiceMap.put(deviceId, vibrationVoice);
            return true;
        }
    }

    public boolean startDataReceiveService(ReceiveDataCallback receiveDataCallback) {
        if (!this.initFlag || receiveDataCallback == null) {
            return false;
        }
        if (getLsBleManagerStatus() == ManagerStatus.FREE) {
            setManagerStatus(ManagerStatus.DATA_RECEIVE, "startDataReceive");
            this.mReceiveDataCallback = receiveDataCallback;
            this.isStopDataReceived = false;
            return startupDataReceiveService();
        }
        C2220h.m1596a((Object) this, "Failed to start data receive service,for manager status...", 1);
        return false;
    }

    public boolean startPairing(LsDeviceInfo lsDeviceInfo, PairCallback pairCallback) {
        if (!this.initFlag || this.protocolHendlerCenter == null || lsDeviceInfo == null || pairCallback == null || !checkBluetoothState()) {
            return false;
        }
        if (getLsBleManagerStatus() == ManagerStatus.FREE) {
            setManagerStatus(ManagerStatus.DEVICE_PAIR, "statr Pairing");
            this.mPairCallback = pairCallback;
            this.protocolHendlerCenter.m1802a(lsDeviceInfo, getConnectAddressBykey(lsDeviceInfo.getDeviceName()), C2234v.m1710a(lsDeviceInfo.getProtocolType(), lsDeviceInfo, this.protocolHendlerCenter.m1811c(), this.protocolHendlerCenter.m1813e(), this.protocolHendlerCenter.m1812d(), this.protocolHendlerCenter.m1814f(), ManagerStatus.DEVICE_PAIR));
            return true;
        }
        C2220h.m1596a((Object) this, "Failed to pairing device,for manager status...", 1);
        return false;
    }

    public boolean stopDataReceiveService() {
        if (!this.initFlag || this.bleConnector == null || this.protocolHendlerCenter == null) {
            C2220h.m1596a((Object) this, "Failed to stop data receive service...", 1);
            return false;
        }
        C2220h.m1596a((Object) this, "successfully to stop data receive service...", 3);
        setManagerStatus(ManagerStatus.FREE, "stopDataReceive");
        this.isStopDataReceived = true;
        this.bleConnector.m1592d();
        this.protocolHendlerCenter.m1809b();
        cancelAllLongConnection();
        return true;
    }

    public boolean stopSearch() {
        if (!this.initFlag || this.bleConnector == null) {
            return false;
        }
        setManagerStatus(ManagerStatus.FREE, "stop Search");
        return this.bleConnector.m1592d();
    }
}
