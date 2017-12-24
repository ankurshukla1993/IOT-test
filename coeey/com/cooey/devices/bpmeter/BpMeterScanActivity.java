package com.cooey.devices.bpmeter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import chatkit.holders.BloodPressureCardViewHolder;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.bpmeter.BluetoothLeService.LocalBinder;
import java.util.List;

public class BpMeterScanActivity extends AppCompatActivity implements OnClickListener {
    private static final int REQUEST_ENABLE_BT = 1;
    private static final long SCAN_PERIOD = 10000;
    private static final String TAG = BpMeterScanActivity.class.getSimpleName();
    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    public Button btnContinue;
    public Button button;
    public int dystolic;
    AnimationDrawable frameAnimation;
    public int heartRate;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeService mBluetoothLeService;
    private boolean mConnected = false;
    private String mDeviceAddress;
    private BroadcastReceiver mGattUpdateReceiver = new C09442();
    private Handler mHandler;
    private LeScanCallback mLeScanCallback = new C09485();
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private boolean mScanning;
    private final ServiceConnection mServiceConnection = new C09431();
    private ImageView progressBar;
    private ImageView progressBarAnimation;
    public int systolic;
    TextView txtBpRange;
    TextView txtConnectionStatus;
    TextView txtDeviceName;
    TextView txtDevieBatteryStatus;
    TextView txtProgressStatus;

    class C09431 implements ServiceConnection {
        C09431() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder service) {
            BpMeterScanActivity.this.mBluetoothLeService = ((LocalBinder) service).getService();
            if (!BpMeterScanActivity.this.mBluetoothLeService.initialize()) {
                Log.e(BpMeterScanActivity.TAG, "Unable to initialize Bluetooth");
                BpMeterScanActivity.this.finish();
            }
            BpMeterScanActivity.this.mBluetoothLeService.connect(BpMeterScanActivity.this.mDeviceAddress);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            BpMeterScanActivity.this.mBluetoothLeService = null;
        }
    }

    class C09442 extends BroadcastReceiver {
        C09442() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                BpMeterScanActivity.this.mConnected = true;
                BpMeterScanActivity.this.updateConnectionState(C0910R.string.connected);
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                BpMeterScanActivity.this.mConnected = false;
                BpMeterScanActivity.this.updateConnectionState(C0910R.string.disconnected);
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                BpMeterScanActivity.this.displayGattServices(BpMeterScanActivity.this.mBluetoothLeService.getSupportedGattServices());
                BpMeterScanActivity.this.updateConnectionStateForButton(C0910R.string.start);
            } else if (!BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
            } else {
                if (intent.getStringExtra(BluetoothLeService.EXTRA_DATA_BATTERY_STATUS) != null) {
                    BpMeterScanActivity.this.updateDataBatteryStatus(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_BATTERY_STATUS));
                } else if (intent.getStringExtra(BluetoothLeService.EXTRA_DATA_SYSTOLIC_PROGRESS) != null) {
                    BpMeterScanActivity.this.updateDataSystolicValues(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_SYSTOLIC_PROGRESS));
                } else if (intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_SYSTOLIC, 0) != 0 && intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_DIASTOLIC, 0) != 0 && intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_HEART_RATE, 0) != 0) {
                    BpMeterScanActivity.this.updateBPValues(intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_SYSTOLIC, 0), intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_DIASTOLIC, 0), intent.getIntExtra(BluetoothLeService.EXTRA_DATA_BP_HEART_RATE, 0));
                } else if (intent.getStringExtra(BluetoothLeService.EXTRA_DATA_ERROR) != null) {
                    BpMeterScanActivity.this.updateErrorDetails(intent.getStringExtra(BluetoothLeService.EXTRA_DATA_ERROR));
                }
            }
        }
    }

    class C09453 implements Runnable {
        C09453() {
        }

        public void run() {
            BpMeterScanActivity.this.mScanning = false;
            BpMeterScanActivity.this.mBluetoothAdapter.stopLeScan(BpMeterScanActivity.this.mLeScanCallback);
        }
    }

    class C09485 implements LeScanCallback {
        C09485() {
        }

        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            BpMeterScanActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    String deviceName = device.getName();
                    if (deviceName != null && deviceName.length() > 0) {
                        if (deviceName.equalsIgnoreCase("Technaxx BP") || deviceName.equalsIgnoreCase("BPM-188")) {
                            BpMeterScanActivity.this.txtDeviceName.setText("Cooey BP");
                            BpMeterScanActivity.this.mDeviceAddress = device.getAddress();
                            if (BpMeterScanActivity.this.mScanning && BpMeterScanActivity.this.mDeviceAddress != null) {
                                BpMeterScanActivity.this.mBluetoothLeService.connect(BpMeterScanActivity.this.mDeviceAddress);
                            } else if (BpMeterScanActivity.this.mScanning) {
                                BpMeterScanActivity.this.mBluetoothLeService.disconnect();
                            }
                        }
                    }
                }
            });
        }
    }

    private void updateConnectionStateForButton(int start) {
        this.button.setText(start);
    }

    private void updateErrorDetails(String stringExtra) {
        if (stringExtra != null) {
            this.progressBar.setBackground(getResources().getDrawable(C0910R.drawable.count_0));
            this.txtProgressStatus.setText("Error Occured" + stringExtra);
            if (this.progressBarAnimation.getVisibility() == 0) {
                this.progressBarAnimation.setVisibility(8);
            }
            if (this.progressBar.getVisibility() == 8) {
                this.progressBar.setVisibility(0);
            }
            if (this.mConnected) {
                this.button.setText(getString(C0910R.string.start));
            } else {
                this.button.setText(getString(C0910R.string.connect));
            }
        }
    }

    private void updateDataSystolicValues(String stringExtra) {
        if (stringExtra != null) {
            if (this.progressBar.getVisibility() == 0) {
                this.progressBar.setVisibility(8);
            }
            if (this.progressBarAnimation.getVisibility() == 8) {
                this.progressBarAnimation.setVisibility(0);
            }
            this.frameAnimation = (AnimationDrawable) this.progressBarAnimation.getBackground();
            this.frameAnimation.start();
            this.txtProgressStatus.setText(stringExtra);
            this.button.setText("Stop");
            return;
        }
        this.txtProgressStatus.setText("-");
    }

    private void updateBPValues(int systolicValue, int diastolicValue, int heartRateValue) {
        if (this.button.getVisibility() == 0) {
            this.button.setVisibility(8);
        }
        if (this.btnContinue.getVisibility() == 8) {
            this.btnContinue.setVisibility(0);
        }
        if (this.progressBarAnimation.getVisibility() == 0) {
            this.progressBarAnimation.setVisibility(8);
        }
        if (this.progressBar.getVisibility() == 8) {
            this.progressBar.setVisibility(0);
        }
        this.progressBar.setBackground(getResources().getDrawable(C0910R.drawable.count_0));
        this.systolic = systolicValue;
        this.dystolic = diastolicValue;
        this.heartRate = heartRateValue;
        this.txtProgressStatus.setText(systolicValue + "/" + diastolicValue + "mmHg");
        checkBPRange(systolicValue, diastolicValue);
    }

    private void checkBPRange(int systolic, int diastolic) {
        int bpRangeTosend = 0;
        if (systolic < 90 && diastolic < 60) {
            bpRangeTosend = 1;
        } else if (systolic < 90 && diastolic >= 60 && diastolic < 90) {
            bpRangeTosend = 1;
        } else if (systolic < 90 && diastolic >= 90) {
            bpRangeTosend = 4;
        } else if (systolic >= 90 && systolic < 140 && diastolic >= 90) {
            bpRangeTosend = 3;
        } else if (systolic >= 90 && systolic < 140 && diastolic >= 60 && diastolic < 90) {
            bpRangeTosend = 2;
        } else if (systolic >= 90 && systolic < 140 && diastolic < 60) {
            bpRangeTosend = 1;
        } else if (systolic >= 140 && diastolic >= 90) {
            bpRangeTosend = 3;
        } else if (systolic >= 140 && diastolic >= 60 && diastolic < 90) {
            bpRangeTosend = 3;
        } else if (systolic >= 140 && diastolic < 60) {
            bpRangeTosend = 5;
        }
        setRangeOnUI(bpRangeTosend);
    }

    public void setRangeOnUI(int bpRange) {
        if (bpRange > 0 && bpRange <= 5) {
            if (this.txtBpRange.getVisibility() == 8) {
                this.txtBpRange.setVisibility(0);
            }
            switch (bpRange) {
                case 1:
                    this.txtBpRange.setText("Your Blood Pressure is Low");
                    return;
                case 2:
                    this.txtBpRange.setText("Your Blood Pressure is Normal");
                    return;
                case 3:
                    this.txtBpRange.setText("Your Blood Pressure is High");
                    return;
                case 4:
                    this.txtBpRange.setText("Your Blood Pressure is Low/High");
                    return;
                case 5:
                    this.txtBpRange.setText("Your Blood Pressure is High/Low");
                    return;
                default:
                    return;
            }
        }
    }

    private void displayData(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int charaProp = bluetoothGattCharacteristic.getProperties();
        if ((charaProp | 2) > 0) {
            if (this.mNotifyCharacteristic != null) {
                this.mBluetoothLeService.setCharacteristicNotification(this.mNotifyCharacteristic, false);
                this.mNotifyCharacteristic = null;
            }
            this.mBluetoothLeService.readCharacteristic(bluetoothGattCharacteristic);
        }
        if ((charaProp | 16) > 0) {
            this.mNotifyCharacteristic = bluetoothGattCharacteristic;
            this.mBluetoothLeService.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        }
    }

    private void updateDataBatteryStatus(String data) {
        if (data != null) {
            this.txtDevieBatteryStatus.setText(data);
        } else {
            this.txtDevieBatteryStatus.setText("-");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0910R.layout.activity_main_scan);
        getWindow().addFlags(128);
        this.button = (Button) findViewById(C0910R.id.btn_start_or_stop);
        setSupportActionBar((Toolbar) findViewById(C0910R.id.toolbar));
        getSupportActionBar().setTitle(getString(C0910R.string.take_reading));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.txtConnectionStatus = (TextView) findViewById(C0910R.id.txt_device_connection_status_value);
        this.txtDeviceName = (TextView) findViewById(C0910R.id.txt_device_name_value);
        this.txtDevieBatteryStatus = (TextView) findViewById(C0910R.id.txt_device_battery_status_value);
        this.progressBar = (ImageView) findViewById(C0910R.id.circularProgressbar);
        this.progressBarAnimation = (ImageView) findViewById(C0910R.id.circularProgressbar_progress);
        this.txtProgressStatus = (TextView) findViewById(C0910R.id.txt_progress);
        this.btnContinue = (Button) findViewById(C0910R.id.btn_continue);
        this.txtBpRange = (TextView) findViewById(C0910R.id.txt_high_or_low);
        this.btnContinue.setOnClickListener(this);
        this.mHandler = new Handler();
        this.systolic = 0;
        this.dystolic = 0;
        this.heartRate = 0;
        if (!(isBluetoothEnabled() || isLocationEnabled())) {
            getPermissions();
        }
        this.button.setOnClickListener(this);
        if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            Toast.makeText(this, C0910R.string.ble_not_supported, 0).show();
            finish();
        }
        this.mBluetoothAdapter = ((BluetoothManager) getSystemService("bluetooth")).getAdapter();
        if (this.mBluetoothAdapter == null) {
            Toast.makeText(this, C0910R.string.error_bluetooth_not_supported, 0).show();
            finish();
            return;
        }
        bindService(new Intent(this, BluetoothLeService.class), this.mServiceConnection, 1);
    }

    private void scanLeDevice(boolean enable) {
        if (enable) {
            this.mHandler.postDelayed(new C09453(), SCAN_PERIOD);
            this.mScanning = true;
            this.mBluetoothAdapter.startLeScan(this.mLeScanCallback);
            return;
        }
        this.mScanning = false;
        this.mBluetoothAdapter.stopLeScan(this.mLeScanCallback);
    }

    protected void onPause() {
        super.onPause();
        if (this.mConnected) {
            scanLeDevice(false);
        }
        if (this.mGattUpdateReceiver != null) {
            try {
                unregisterReceiver(this.mGattUpdateReceiver);
                this.mGattUpdateReceiver = null;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(this.mServiceConnection);
        this.mBluetoothLeService = null;
    }

    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (resourceId == C0910R.string.disconnected) {
                    BpMeterScanActivity.this.button.setText(BpMeterScanActivity.this.getString(C0910R.string.connect));
                }
                BpMeterScanActivity.this.txtConnectionStatus.setText(resourceId);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (!this.mBluetoothAdapter.isEnabled() && (this.mBluetoothAdapter == null || !this.mBluetoothAdapter.isEnabled())) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 1);
        }
        registerReceiver(this.mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (this.mBluetoothLeService != null) {
            Log.d(TAG, "Connect request result=" + this.mBluetoothLeService.connect(this.mDeviceAddress));
        }
    }

    public boolean isBluetoothEnabled() {
        try {
            return BluetoothAdapter.getDefaultAdapter().enable();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isLocationEnabled() {
        return ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    protected void getPermissions() {
        if (VERSION.SDK_INT < 23) {
            turnOnBluetooth(true);
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            turnOnBluetooth(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 1224);
        }
    }

    private void turnOnBluetooth(boolean turnOnOrOff) {
        if (turnOnOrOff) {
            try {
                BluetoothAdapter.getDefaultAdapter().enable();
                return;
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
        try {
            BluetoothAdapter.getDefaultAdapter().disable();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1224:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Toast.makeText(this, "Cannot scan devices without permissions.", 0).show();
                    return;
                } else {
                    turnOnBluetooth(true);
                    return;
                }
            default:
                return;
        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices != null) {
            for (BluetoothGattService gattService : gattServices) {
                if (gattService.getUuid().equals(BPMeterGattAttributes.BPMETERUUID)) {
                    for (BluetoothGattCharacteristic gattCharacteristic : gattService.getCharacteristics()) {
                        if (gattCharacteristic.getUuid().equals(BPMeterGattAttributes.BPMreadinfoCUID)) {
                            displayData(gattCharacteristic);
                        }
                    }
                }
            }
        }
    }

    public void onClick(View v) {
        if (v.getId() == C0910R.id.btn_start_or_stop) {
            if (!this.mScanning) {
                scanLeDevice(true);
            }
            if (this.mConnected && this.mBluetoothLeService != null && this.mBluetoothLeService.getSupportedGattServices() != null && this.button.getText().toString().equalsIgnoreCase("Start")) {
                for (BluetoothGattService gattService : this.mBluetoothLeService.getSupportedGattServices()) {
                    if (gattService.getUuid().equals(BPMeterGattAttributes.BPMETERUUID)) {
                        for (BluetoothGattCharacteristic gattCharacteristic : gattService.getCharacteristics()) {
                            if (gattCharacteristic.getUuid().equals(BPMeterGattAttributes.BPMETERWRITEINFOUUID)) {
                                boolean isValueWritten = gattCharacteristic.setValue(BPMeterGattAttributes.hexStringToByteArray("0x0240dc01a13c"));
                                this.mBluetoothLeService.writeCharacteristic(gattCharacteristic);
                            }
                        }
                    }
                }
            } else if (this.mConnected && this.button.getText().toString().equalsIgnoreCase("Stop")) {
                if (this.mConnected) {
                    scanLeDevice(false);
                }
                this.button.setText(getResources().getString(C0910R.string.connect));
                updateConnectionState(C0910R.string.disconnected);
                if (this.progressBarAnimation.getVisibility() == 0) {
                    this.progressBarAnimation.setVisibility(8);
                }
                if (this.progressBar.getVisibility() == 8) {
                    this.progressBar.setVisibility(0);
                }
                if (this.txtBpRange.getVisibility() == 0) {
                    this.txtBpRange.setVisibility(8);
                }
                this.mBluetoothLeService.disconnect();
            } else {
                this.button.setText("Connecting");
            }
        } else if (v.getId() == C0910R.id.btn_continue && this.systolic > 0) {
            Intent i = new Intent();
            i.setAction("VitalValues");
            i.putExtra("VITAL_TYPE", "BLOOD_PRESSURE");
            i.putExtra(BloodPressureCardViewHolder.SYSTOLIC, this.systolic);
            i.putExtra(BloodPressureCardViewHolder.DIASTOLIC, this.dystolic);
            i.putExtra("HEART_RATE", this.heartRate);
            i.putExtra("PATIENT_ID", CooeyDeviceManager.userInfo.getPatientId());
            i.putExtra("CONTEXT_ID", CooeyDeviceManager.userInfo.getContextId());
            i.putExtra("CONTEXT_TYPE", CooeyDeviceManager.userInfo.getContextType());
            turnOnBluetooth(false);
            sendBroadcast(i);
        }
    }
}
