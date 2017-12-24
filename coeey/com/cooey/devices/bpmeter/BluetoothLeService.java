package com.cooey.devices.bpmeter;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.List;

@TargetApi(18)
public class BluetoothLeService extends Service {
    public static final String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public static final String ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public static final String ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public static final String ACTION_GATT_SERVICES_DISCOVERED = "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public static final String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";
    public static final String EXTRA_DATA_BATTERY_STATUS = "com.cooey.devices.bpmeter.EXTRA_DATA_BATTERY_STATUS";
    public static final String EXTRA_DATA_BP_DIASTOLIC = "com.cooey.devices.bpmeter.EXTRA_DATA_BP_DIASTOLIC";
    public static final String EXTRA_DATA_BP_HEART_RATE = "com.cooey.devices.bpmeter.EXTRA_DATA_BP_HEART_RATE";
    public static final String EXTRA_DATA_BP_SYSTOLIC = "com.cooey.devices.bpmeter.EXTRA_DATA_BP_SYSTOLIC";
    public static final String EXTRA_DATA_DIASTOLIC_PROGRESS = "com.cooey.devices.bpmeter.EXTRA_DATA_DIASTOLIC_PROGRESS";
    public static final String EXTRA_DATA_ERROR = "com.cooey.devices.bpmeter.EXTRA_DATA_ERROR";
    public static final String EXTRA_DATA_HEARTRATE_PROGRESS = "com.cooey.devices.bpmeter.EXTRA_DATA_HEARTRATE_PROGRESS";
    public static final String EXTRA_DATA_SYSTOLIC_PROGRESS = "com.cooey.devices.bpmeter.EXTRA_DATA_SYSTOLIC_PROGRESS";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    private static final String TAG = BluetoothLeService.class.getSimpleName();
    private final IBinder mBinder = new LocalBinder();
    private BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothManager mBluetoothManager;
    private int mConnectionState = 0;
    private final BluetoothGattCallback mGattCallback = new C09421();

    class C09421 extends BluetoothGattCallback {
        C09421() {
        }

        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intentAction;
            if (newState == 2) {
                intentAction = BluetoothLeService.ACTION_GATT_CONNECTED;
                BluetoothLeService.this.mConnectionState = 2;
                BluetoothLeService.this.broadcastUpdate(intentAction);
                Log.i(BluetoothLeService.TAG, "Connected to GATT server.");
                Log.i(BluetoothLeService.TAG, "Attempting to start service discovery:" + BluetoothLeService.this.mBluetoothGatt.discoverServices());
            } else if (newState == 0) {
                intentAction = BluetoothLeService.ACTION_GATT_DISCONNECTED;
                BluetoothLeService.this.mConnectionState = 0;
                Log.i(BluetoothLeService.TAG, "Disconnected from GATT server.");
                BluetoothLeService.this.broadcastUpdate(intentAction);
            }
        }

        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == 0) {
                BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
            } else {
                Log.w(BluetoothLeService.TAG, "onServicesDiscovered received: " + status);
            }
        }

        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == 0) {
                BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_DATA_AVAILABLE, characteristic);
            }
        }

        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_DATA_AVAILABLE, characteristic);
        }
    }

    public class LocalBinder extends Binder {
        BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    private void broadcastUpdate(String action) {
        sendBroadcast(new Intent(action));
    }

    private void broadcastUpdate(String action, BluetoothGattCharacteristic characteristic) {
        Intent intent = new Intent(action);
        if (characteristic.getUuid().equals(BPMeterGattAttributes.BPMreadinfoCUID)) {
            int format;
            if ((characteristic.getProperties() & 1) != 0) {
                format = 18;
                Log.d(TAG, "Battery Status format UINT16.");
            } else {
                format = 17;
                Log.d(TAG, "Battery Status format UINT8.");
            }
            int batterStatus;
            if (characteristic.getValue().length == 8) {
                batterStatus = characteristic.getIntValue(format, 6).intValue();
                Log.d(TAG, String.format("Received BatteryStatus rate: %d", new Object[]{Integer.valueOf(batterStatus)}));
                intent.putExtra(EXTRA_DATA_BATTERY_STATUS, String.valueOf(batterStatus));
                sendBroadcast(intent);
            } else if (characteristic.getValue().length == 9) {
                batterStatus = characteristic.getIntValue(format, 7).intValue();
                Log.d(TAG, String.format("Received BatteryStatus rate: %d", new Object[]{Integer.valueOf(batterStatus)}));
                intent.putExtra(EXTRA_DATA_BATTERY_STATUS, String.valueOf(batterStatus));
                sendBroadcast(intent);
            } else if (characteristic.getValue().length == 7) {
                int pressureValues = characteristic.getIntValue(format, 5).intValue();
                int diastolicPressureValues = characteristic.getIntValue(format, 6).intValue();
                Log.d(TAG, String.format("Received BP values rate: %d", new Object[]{Integer.valueOf(pressureValues)}));
                intent.putExtra(EXTRA_DATA_DIASTOLIC_PROGRESS, String.valueOf(diastolicPressureValues));
                intent.putExtra(EXTRA_DATA_SYSTOLIC_PROGRESS, String.valueOf(pressureValues));
                sendBroadcast(intent);
            } else if (characteristic.getValue().length != 17) {
            } else {
                if (characteristic.getIntValue(format, 5).intValue() != 255) {
                    int sysTolicValue = characteristic.getIntValue(format, 6).intValue();
                    int diastolicValue = characteristic.getIntValue(format, 8).intValue();
                    int heartRateValue = characteristic.getIntValue(format, 12).intValue();
                    intent.putExtra(EXTRA_DATA_BP_SYSTOLIC, sysTolicValue);
                    intent.putExtra(EXTRA_DATA_BP_DIASTOLIC, diastolicValue);
                    intent.putExtra(EXTRA_DATA_BP_HEART_RATE, heartRateValue);
                    sendBroadcast(intent);
                    return;
                }
                intent.putExtra(EXTRA_DATA_ERROR, "" + characteristic.getIntValue(format, 12).intValue());
                sendBroadcast(intent);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public boolean onUnbind(Intent intent) {
        close();
        return super.onUnbind(intent);
    }

    public boolean initialize() {
        if (this.mBluetoothManager == null) {
            this.mBluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            if (this.mBluetoothManager == null) {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }
        }
        this.mBluetoothAdapter = this.mBluetoothManager.getAdapter();
        if (this.mBluetoothAdapter != null) {
            return true;
        }
        Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
        return false;
    }

    public boolean connect(String address) {
        if (this.mBluetoothAdapter == null || address == null) {
            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        } else if (this.mBluetoothDeviceAddress == null || !address.equals(this.mBluetoothDeviceAddress) || this.mBluetoothGatt == null) {
            BluetoothDevice device = this.mBluetoothAdapter.getRemoteDevice(address);
            if (device == null) {
                Log.w(TAG, "Device not found.  Unable to connect.");
                return false;
            }
            this.mBluetoothGatt = device.connectGatt(this, false, this.mGattCallback);
            Log.d(TAG, "Trying to create a new connection.");
            this.mBluetoothDeviceAddress = address;
            this.mConnectionState = 1;
            return true;
        } else {
            Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
            if (!this.mBluetoothGatt.connect()) {
                return false;
            }
            this.mConnectionState = 1;
            return true;
        }
    }

    public void disconnect() {
        if (this.mBluetoothAdapter == null || this.mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
        } else {
            this.mBluetoothGatt.disconnect();
        }
    }

    public void close() {
        if (this.mBluetoothGatt != null) {
            this.mBluetoothGatt.close();
            this.mBluetoothGatt = null;
        }
    }

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (this.mBluetoothAdapter == null || this.mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
        } else {
            this.mBluetoothGatt.readCharacteristic(characteristic);
        }
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (this.mBluetoothGatt == null) {
            Log.e(TAG, "lost connection");
            return false;
        } else if (characteristic != null) {
            return this.mBluetoothGatt.writeCharacteristic(characteristic);
        } else {
            Log.e(TAG, "char not found!");
            return false;
        }
    }

    public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (this.mBluetoothAdapter == null || this.mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
        } else {
            this.mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
        }
    }

    public List<BluetoothGattService> getSupportedGattServices() {
        if (this.mBluetoothGatt == null) {
            return null;
        }
        return this.mBluetoothGatt.getServices();
    }
}
