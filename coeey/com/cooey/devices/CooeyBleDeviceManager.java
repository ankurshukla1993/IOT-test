package com.cooey.devices;

import android.content.Context;
import com.cooey.devices.vo.Device;
import com.lifesense.ble.LsBleManager;
import com.lifesense.ble.PairCallback;
import com.lifesense.ble.ReceiveDataCallback;
import com.lifesense.ble.SearchCallback;
import com.lifesense.ble.bean.BloodPressureData;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.SexType;
import com.lifesense.ble.bean.WeightData_A2;
import com.lifesense.ble.bean.WeightData_A3;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.commom.BroadcastType;
import com.lifesense.ble.commom.DeviceType;
import com.lifesense.fat.FatPercentage;
import java.util.ArrayList;
import java.util.List;

public class CooeyBleDeviceManager {
    private final Context context;
    List<DeviceType> deviceTypeList = new ArrayList();
    LsBleManager lsBleManager = LsBleManager.newInstance();
    WeightUserInfo weightUserInfo;

    public CooeyBleDeviceManager(Context context, List<com.cooey.devices.vo.DeviceType> deviceTypeList) {
        this.context = context;
        for (com.cooey.devices.vo.DeviceType deviceType : deviceTypeList) {
            if (deviceType == com.cooey.devices.vo.DeviceType.SPYHGOMANOMETER) {
                this.deviceTypeList.add(DeviceType.SPHYGMOMANOMETER);
            }
            if (deviceType == com.cooey.devices.vo.DeviceType.BODY_ANALYZER) {
                this.deviceTypeList.add(DeviceType.WEIGHT_SCALE);
                this.deviceTypeList.add(DeviceType.KITCHEN_SCALE);
                this.deviceTypeList.add(DeviceType.FAT_SCALE);
            }
        }
    }

    public CooeyBleDeviceManager(Context context, List<com.cooey.devices.vo.DeviceType> deviceTypeList, WeightUserInfo userInfo) {
        this.context = context;
        this.weightUserInfo = userInfo;
        for (com.cooey.devices.vo.DeviceType deviceType : deviceTypeList) {
            if (deviceType == com.cooey.devices.vo.DeviceType.SPYHGOMANOMETER) {
                this.deviceTypeList.add(DeviceType.SPHYGMOMANOMETER);
            }
            if (deviceType == com.cooey.devices.vo.DeviceType.BODY_ANALYZER) {
                this.deviceTypeList.add(DeviceType.WEIGHT_SCALE);
                this.deviceTypeList.add(DeviceType.KITCHEN_SCALE);
                this.deviceTypeList.add(DeviceType.FAT_SCALE);
            }
        }
    }

    public void startSearch(final DeviceSearchCallback deviceSearchCallback) {
        if (deviceSearchCallback == null) {
            throw new NullPointerException("The first parameter of startSearch cannot be null");
        }
        this.lsBleManager.searchLsDevice(new SearchCallback() {
            public void onSearchResults(LsDeviceInfo lsDeviceInfo) {
                Device device = new Device();
                device.setBroadcastID(lsDeviceInfo.getBroadcastID());
                device.setDeviceId(lsDeviceInfo.getDeviceId());
                device.setDeviceName(lsDeviceInfo.getDeviceName());
                device.setDeviceSn(lsDeviceInfo.getDeviceSn());
                device.setDeviceType(lsDeviceInfo.getDeviceType());
                device.setDeviceUserNumber(lsDeviceInfo.getDeviceUserNumber());
                device.setFirmwareVersion(lsDeviceInfo.getFirmwareVersion());
                device.setHardwareVersion(lsDeviceInfo.getHardwareVersion());
                device.setManufactureName(lsDeviceInfo.getManufactureName());
                device.setMaxUserQuantity(lsDeviceInfo.getMaxUserQuantity());
                device.setModelNumber(lsDeviceInfo.getModelNumber());
                device.setDeviceSn(lsDeviceInfo.getDeviceSn());
                device.setPairSingSignature(lsDeviceInfo.getPairSingSignature());
                device.setPairStatus(lsDeviceInfo.getPairStatus());
                device.setPassword(lsDeviceInfo.getPassword());
                device.setFirmwareVersion(lsDeviceInfo.getFirmwareVersion());
                device.setProtocolType(lsDeviceInfo.getProtocolType());
                device.setSoftwareVersion(lsDeviceInfo.getSoftwareVersion());
                device.setSupportDownloadInfoFeature(lsDeviceInfo.getSupportDownloadInfoFeature());
                device.setSystemId(lsDeviceInfo.getSystemId());
                deviceSearchCallback.onSearchResult(device);
            }
        }, this.deviceTypeList, BroadcastType.PAIR);
    }

    public void stopSearch() {
        this.lsBleManager.stopSearch();
    }

    public void startPairing(Device device, final DevicePairCallback devicePairCallback) {
        this.lsBleManager.startPairing(device, new PairCallback() {
            public void onDiscoverUserInfo(List list) {
                super.onDiscoverUserInfo(list);
                devicePairCallback.onDiscoverUserInfo(list);
            }

            public void onPairResults(LsDeviceInfo lsDeviceInfo, int i) {
                super.onPairResults(lsDeviceInfo, i);
                if (lsDeviceInfo != null) {
                    Device device = new Device();
                    device.setBroadcastID(lsDeviceInfo.getBroadcastID());
                    device.setDeviceId(lsDeviceInfo.getDeviceId());
                    device.setDeviceName(lsDeviceInfo.getDeviceName());
                    device.setDeviceSn(lsDeviceInfo.getDeviceSn());
                    device.setDeviceType(lsDeviceInfo.getDeviceType());
                    device.setDeviceUserNumber(lsDeviceInfo.getDeviceUserNumber());
                    device.setFirmwareVersion(lsDeviceInfo.getFirmwareVersion());
                    device.setHardwareVersion(lsDeviceInfo.getHardwareVersion());
                    device.setManufactureName(lsDeviceInfo.getManufactureName());
                    device.setMaxUserQuantity(lsDeviceInfo.getMaxUserQuantity());
                    device.setModelNumber(lsDeviceInfo.getModelNumber());
                    device.setDeviceSn(lsDeviceInfo.getDeviceSn());
                    device.setPairSingSignature(lsDeviceInfo.getPairSingSignature());
                    device.setPairStatus(lsDeviceInfo.getPairStatus());
                    device.setPassword(lsDeviceInfo.getPassword());
                    device.setFirmwareVersion(lsDeviceInfo.getFirmwareVersion());
                    device.setProtocolType(lsDeviceInfo.getProtocolType());
                    device.setSoftwareVersion(lsDeviceInfo.getSoftwareVersion());
                    device.setSupportDownloadInfoFeature(lsDeviceInfo.getSupportDownloadInfoFeature());
                    device.setSystemId(lsDeviceInfo.getSystemId());
                    devicePairCallback.onPairResults(device, i);
                }
            }
        });
    }

    public void bindDeviceUser(int i, String s) {
        this.lsBleManager.bindDeviceUser(i, s);
    }

    public void addMeasureDevice(Device cooeyDevice) {
        this.lsBleManager.addMeasureDevice(cooeyDevice);
    }

    public void initialize(Context context) {
        this.lsBleManager.initialize(context);
    }

    public void startDataRecieve(final DeviceDataRecieveCallback deviceDataRecieveCallback) {
        this.lsBleManager.startDataReceiveService(new ReceiveDataCallback() {
            public void onReceiveBloodPressureData(BloodPressureData bloodPressureData) {
                super.onReceiveBloodPressureData(bloodPressureData);
                deviceDataRecieveCallback.onReceiveBloodPressureData(bloodPressureData.getSystolic(), bloodPressureData.getDiastolic(), bloodPressureData.getPulseRate(), bloodPressureData.getMeanArterialPressure());
            }

            public void onReceiveWeightData_A3(WeightData_A3 weightData_a3) {
                super.onReceiveWeightData_A3(weightData_a3);
                FatPercentage fp = FatPercentage.getInstance(FatPercentage.fda);
                double imp = fp.getImp((int) weightData_a3.getImpedance());
                if (CooeyBleDeviceManager.this.weightUserInfo != null) {
                    float bmi = (float) fp.getBmi(weightData_a3.getWeight(), (double) (CooeyBleDeviceManager.this.weightUserInfo.getHeight() / 100.0f));
                    int sex = 0;
                    if (CooeyBleDeviceManager.this.weightUserInfo.getSex() == SexType.FEMALE) {
                        sex = 1;
                    }
                    float bodyFatRatio = (float) fp.getFat(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                    float boneDensity = (float) fp.getBone(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                    float muscleMassRatio = (float) fp.getMuscle(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                    float bodyWaterRatio = (float) fp.getBodyWater(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                }
                deviceDataRecieveCallback.onReceiveWeightData_A3(weightData_a3.getWeight(), weightData_a3.getDeviceSelectedUnit(), weightData_a3.getBasalMetabolism(), weightData_a3.getBodyFatRatio(), weightData_a3.getBodyWaterRatio(), weightData_a3.getVisceralFatLevel(), weightData_a3.getMuscleMassRatio(), weightData_a3.getBoneDensity());
            }

            public void onReceiveWeightDta_A2(WeightData_A2 weightData_a2) {
                super.onReceiveWeightDta_A2(weightData_a2);
                float bmi = 0.0f;
                float bodyFatRatio = 0.0f;
                float boneDensity = 0.0f;
                float muscleMassRatio = 0.0f;
                float bodyWaterRatio = 0.0f;
                FatPercentage fp = FatPercentage.getInstance(FatPercentage.fda);
                double imp = fp.getImp((int) weightData_a2.getResistance_2());
                if (CooeyBleDeviceManager.this.weightUserInfo != null) {
                    bmi = (float) fp.getBmi(weightData_a2.getWeight(), (double) (CooeyBleDeviceManager.this.weightUserInfo.getHeight() / 100.0f));
                    int sex = 0;
                    if (CooeyBleDeviceManager.this.weightUserInfo.getSex() == SexType.FEMALE) {
                        sex = 1;
                    }
                    bodyFatRatio = (float) fp.getFat(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                    boneDensity = (float) fp.getBone(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                    muscleMassRatio = (float) fp.getMuscle(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                    bodyWaterRatio = (float) fp.getBodyWater(sex, imp, CooeyBleDeviceManager.this.weightUserInfo.getAge(), (double) bmi, false);
                }
                deviceDataRecieveCallback.onReceiveWeightDta_A2(weightData_a2.getWeight(), weightData_a2.getDeviceSelectedUnit(), bmi, bodyFatRatio, bodyWaterRatio, weightData_a2.getVisceralFatLevel(), muscleMassRatio, boneDensity);
            }
        });
    }

    public void destroy() {
        this.lsBleManager.stopDataReceiveService();
        this.lsBleManager.disconnectDevice();
    }
}
