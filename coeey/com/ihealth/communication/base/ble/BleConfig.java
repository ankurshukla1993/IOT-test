package com.ihealth.communication.base.ble;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BleConfig {
    public static final String UUID_180A = "0000180a-0000-1000-8000-00805f9b34fb";
    public static final String UUID_ABPM_SERVICE = "636f6d2e-6a69-7561-6e2e-425042563130";
    public static final String UUID_AM3S_SERVICE = "636f6d2e-6a69-7561-6e2e-414d56313100";
    public static final String UUID_AM3_Qualcomm_SERVICE = "636f6d2e-6a69-7561-6e2e-414d56313000";
    public static final String UUID_AM3_SERVICE = "0000180d-0000-1000-8000-00805f9b34fb";
    public static final String UUID_AM4_SERVICE = "636f6d2e-6a69-7561-6e2e-414d56313200";
    public static final String UUID_AV10_SERVICE = "636f6d2e-6a69-7561-6e2e-425041563130";
    public static final String UUID_AV20_SERVICE = "636f6d2e-6a69-7561-6e2e-425041563230";
    public static final String UUID_BG5l_SERVICE = "636f6d2e-6a69-7561-6e2e-424756343200";
    public static final String UUID_BG_READ = "00002a51-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BG_RECEIVE = "00002a18-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BG_RECEIVE_CONTENT = "00002a34-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BG_SEND_AND_RECEIVE = "00002a52-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BG_SERVICE = "00001808-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BP3L_SERVICE = "636f6d2e-6a69-7561-6e2e-425056323400";
    public static final String UUID_BP_READ = "00002a49-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BP_RECEIVE = "00002a35-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BP_RECEIVE_iHealth = "0000ff03-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BP_SEND = "0000ff03-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BP_SERVICE = "00001810-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BS_READ = "00002a9b-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BS_RECEIVE = "00002a9c-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BS_SERVICE = "0000181b-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC = "00002a19-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BTM_BATTERY_SERVICE = "0000180f-0000-1000-8000-00805f9b34fb";
    public static final String UUID_BTM_Primary_SERVICE = "636f6d2e-6a69-7561-6e2e-424c45303100";
    public static final String UUID_BTM_READ_DATA_CHARACTERISTIC = "7365642e-6a69-7561-6e2e-424c45303100";
    public static final String UUID_BTM_READ_DATA_SERVICE = "636f6d2e-6a69-7561-6e2e-424c45303100";
    public static final String UUID_BTM_WRITE_DATA_CHARACTERISTIC = "7265632e-6a69-7561-6e2e-424c45303100";
    public static final String UUID_BTM_WRITE_DATA_SERVICE = "636f6d2e-6a69-7561-6e2e-424c45303100";
    public static final String UUID_HS4_SERVICE = "636f6d2e-6a69-7561-6e2e-425753563031";
    public static final String UUID_HS_READ = "00002a9e-0000-1000-8000-00805f9b34fb";
    public static final String UUID_HS_RECEIVE = "00002a9d-0000-1000-8000-00805f9b34fb";
    public static final String UUID_HS_SERVICE = "0000181d-0000-1000-8000-00805f9b34fb";
    public static final String UUID_PO3_SERVICE = "0000ff70-0000-1000-8000-00805f9b34fb";
    public static final String UUID_PO3_SERVICE_128 = "636f6d2e-6a69-7561-6e2e-504f56313100";
    public static final String UUID_PO_READ = "00002a60-0000-1000-8000-00805f9b34fb";
    public static final String UUID_PO_RECEIVE = "00002a5e-0000-1000-8000-00805f9b34fb";
    public static final String UUID_PO_RECEIVE_CONTINUOUS = "00002a5f-0000-1000-8000-00805f9b34fb";
    public static final String UUID_PO_SEND_AND_RECEIVE = "00002a52-0000-1000-8000-00805f9b34fb";
    public static final String UUID_PO_SERVICE = "00001822-0000-1000-8000-00805f9b34fb";
    public static final String UUID_TIME_NOTIFY = "00002a2b-0000-1000-8000-00805f9b34fb";
    public static final String UUID_TIME_READ = "00002a2b-0000-1000-8000-00805f9b34fb";
    public static final String UUID_TIME_SERVICE = "00001805-0000-1000-8000-00805f9b34fb";
    public static final String UUID_TIME_WRITE = "00002a2b-0000-1000-8000-00805f9b34fb";

    public class BleUuid {
        public String ACCESSORY_FIRMWARE_VERSION = null;
        public String ACCESSORY_HARDWARE_VERSION = null;
        public String ACCESSORY_MANUFA = null;
        public String ACCESSORY_MODEL = null;
        public String ACCESSORY_NAME = null;
        public String ACCESSORY_SERIAL = null;
        public String BLE_IDPS_INFO = null;
        public String BLE_RECEIVE = null;
        public String BLE_SERVICE = null;
        public String BLE_TRANSMIT = null;
        public String PROTOCOL_STRING = null;
        public ArrayList listUuid = new ArrayList();
    }

    public static BleUuid getUuidString(List uuids) {
        BleUuid bleUuid = new BleUuid();
        for (UUID uuid : uuids) {
            if ("00002a24-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_MODEL = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a25-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_SERIAL = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a26-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_FIRMWARE_VERSION = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a27-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_HARDWARE_VERSION = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a29-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_MANUFA = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a30-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.PROTOCOL_STRING = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a31-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_NAME = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("00002a37-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("00002a39-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_AM3_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_AM3S_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-414d56313100".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-414d56313100".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_HS4_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7274782e-6a69-7561-6e2e-425753563031".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_180A.equals(uuid.toString())) {
                bleUuid.BLE_IDPS_INFO = uuid.toString();
            } else if ("0000ff01-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.PROTOCOL_STRING = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if ("0000ff02-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.ACCESSORY_NAME = uuid.toString();
                bleUuid.listUuid.add(uuid.toString());
            } else if (UUID_PO3_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("0000ff71-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_PO3_SERVICE_128.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7274782e-6a69-7561-6e2e-504f56313100".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_AM4_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-414d56313200".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-414d56313200".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_BP3L_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-425056323400".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-425056323400".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if (UUID_AV20_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-425041563230".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-425041563230".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if (UUID_AV10_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-425041563130".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-425041563130".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if (UUID_ABPM_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-425042563130".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-425042563130".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_AM3_Qualcomm_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-414d56313000".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-414d56313000".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("636f6d2e-6a69-7561-6e2e-424c45303100".equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_BTM_READ_DATA_CHARACTERISTIC.equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if (UUID_BTM_WRITE_DATA_CHARACTERISTIC.equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_BP_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_BP_RECEIVE.equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("0000ff03-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_BG5l_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if ("7365642e-6a69-7561-6e2e-424756343200".equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("7265632e-6a69-7561-6e2e-424756343200".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_BG_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_BG_RECEIVE.equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("00002a52-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_PO_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_PO_RECEIVE.equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if ("00002a52-0000-1000-8000-00805f9b34fb".equals(uuid.toString())) {
                bleUuid.BLE_TRANSMIT = uuid.toString();
            } else if (UUID_HS_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_HS_RECEIVE.equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            } else if (UUID_BS_SERVICE.equals(uuid.toString())) {
                bleUuid.BLE_SERVICE = uuid.toString();
            } else if (UUID_BS_RECEIVE.equals(uuid.toString())) {
                bleUuid.BLE_RECEIVE = uuid.toString();
            }
        }
        return bleUuid;
    }
}
