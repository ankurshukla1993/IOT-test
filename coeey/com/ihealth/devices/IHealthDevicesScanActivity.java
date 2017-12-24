package com.ihealth.devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.devices.R;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.devices.bg5.BG5ValueFragment;
import com.ihealth.devices.bp5.BP5ValueFragment;
import com.ihealth.devices.po3.PO3ValueFragment;
import com.ihealth.old.ConnectionState;
import java.lang.reflect.Field;
import java.util.Map;

public class IHealthDevicesScanActivity extends AppCompatActivity {
    private static final int HANDLER_CONNECTED = 102;
    private static final int HANDLER_DISCONNECT = 103;
    private static final int HANDLER_SCAN = 101;
    private static final int HANDLER_USER_STATUE = 104;
    private static final int REQUEST_PERMISSIONS = 0;
    private static final String TAG = "LOG";
    private int callbackId;
    String clientId = "51d045370f0e40a7b12c80e38ae8ba2b";
    String clientSecret = "387f287243cd49af81aa2c2705d65e39";
    private ConnectionState connectionState = ConnectionState.IDLE;
    private String deviceType;
    private String mac;
    private iHealthDevicesCallback miHealthDevicesCallback = new C21782();
    String userName = "ronak@cooey.co.in";

    class C21771 implements OnClickListener {
        C21771() {
        }

        public void onClick(View v) {
            IHealthDevicesScanActivity.this.onBackPressed();
        }
    }

    class C21782 extends iHealthDevicesCallback {
        C21782() {
        }

        public void onScanDevice(String mac, String deviceType, int rssi, Map manufactorData) {
            iHealthDevicesManager.getInstance().connectDevice(IHealthDevicesScanActivity.this.userName, mac, deviceType);
        }

        public void onDeviceConnectionStateChange(String mac, String deviceType, int status, int errorID, Map manufactorData) {
            if (status == 1) {
                IHealthDevicesScanActivity.this.mac = mac;
                IHealthDevicesScanActivity.this.connectionState = ConnectionState.CONNECTED;
                IHealthDevicesScanActivity.this.replaceFragment(IHealthDevicesScanActivity.this.getDeviceFragment());
            } else if (status == 2) {
                IHealthDevicesScanActivity.this.connectionState = ConnectionState.IDLE;
            }
        }

        public void onUserStatus(String username, int userStatus) {
        }

        public void onDeviceNotify(String mac, String deviceType, String action, String message) {
            ((DeviceDataProcessor) IHealthDevicesScanActivity.this.getSupportFragmentManager().findFragmentByTag(deviceType)).process(mac, deviceType, action, message);
        }

        public void onScanFinish() {
            if (IHealthDevicesScanActivity.this.connectionState == ConnectionState.CONNECTING) {
                IHealthDevicesScanActivity.this.stopDiscovery();
            } else if (IHealthDevicesScanActivity.this.connectionState == ConnectionState.SCANNING) {
                IHealthDevicesScanActivity.this.stopDiscovery();
                IHealthDevicesScanActivity.this.startDiscovery();
            }
        }
    }

    class C21793 implements IHealthResultCallback {
        C21793() {
        }

        public void onResult(int resultCode, Intent i) {
            IHealthDevicesScanActivity.this.setResult(resultCode, i);
            IHealthDevicesScanActivity.this.finish();
        }
    }

    class C21804 implements IHealthResultCallback {
        C21804() {
        }

        public void onResult(int resultCode, Intent i) {
            IHealthDevicesScanActivity.this.setResult(resultCode, i);
            IHealthDevicesScanActivity.this.finish();
        }
    }

    class C21815 implements IHealthResultCallback {
        C21815() {
        }

        public void onResult(int resultCode, Intent i) {
            IHealthDevicesScanActivity.this.setResult(resultCode, i);
            IHealthDevicesScanActivity.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihealth_devices_scan);
        Intent intent = getIntent();
        this.deviceType = intent.getStringExtra("deviceType");
        this.mac = intent.getStringExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.deviceType != null) {
            if (this.deviceType.contentEquals(iHealthDevicesManager.TYPE_PO3)) {
                toolbar.setTitle("Pulse Oximeter");
            }
            if (this.deviceType.contentEquals(iHealthDevicesManager.TYPE_BP5)) {
                toolbar.setTitle("Blood Pressure Monitor");
            }
            if (this.deviceType.contentEquals(iHealthDevicesManager.TYPE_BG5)) {
                toolbar.setTitle("Blood Glucose Monitor");
            }
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new C21771());
        loadScanFragment();
        iHealthDevicesManager.getInstance().init(this);
        this.callbackId = iHealthDevicesManager.getInstance().registerClientCallback(this.miHealthDevicesCallback);
        iHealthDevicesManager.getInstance().sdkUserInAuthor(this, this.userName, this.clientId, this.clientSecret, this.callbackId);
        if (this.mac == null || this.mac.isEmpty()) {
            checkPermissions();
            startDiscovery();
            return;
        }
        iHealthDevicesManager.getInstance().connectDevice(this.userName, this.mac, this.deviceType);
    }

    private void loadScanFragment() {
        addFragment(IHealthDeviceHelpFragment.newInstance("", ""));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, fragment, this.deviceType);
        transaction.commit();
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.layout_container, fragment);
        transaction.commit();
    }

    private Fragment getDeviceFragment() {
        if (this.deviceType.contentEquals(iHealthDevicesManager.TYPE_PO3)) {
            return PO3ValueFragment.newInstance(this.mac, new C21793());
        }
        if (this.deviceType.contentEquals(iHealthDevicesManager.TYPE_BP5)) {
            return BP5ValueFragment.newInstance(this.mac, new C21804());
        }
        return BG5ValueFragment.newInstance(this.mac, new C21815());
    }

    private void startDiscovery() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Field field : iHealthDevicesManager.class.getFields()) {
            if (field.getName().contentEquals("DISCOVERY_" + this.deviceType)) {
                try {
                    iHealthDevicesManager.getInstance().startDiscovery(field.getLong(null));
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
                this.connectionState = ConnectionState.SCANNING;
            }
        }
    }

    private void stopDiscovery() {
        this.connectionState = ConnectionState.IDLE;
        iHealthDevicesManager.getInstance().stopDiscovery();
    }

    private void checkPermissions() {
        StringBuilder tempRequest = new StringBuilder();
        if (ActivityCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") != 0) {
            tempRequest.append("android.permission.BLUETOOTH");
        }
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            tempRequest.append("android.permission.WRITE_EXTERNAL_STORAGE,");
        }
        if (ActivityCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
            tempRequest.append("android.permission.READ_PHONE_STATE,");
        }
        if (ActivityCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != 0) {
            tempRequest.append("android.permission.RECORD_AUDIO,");
        }
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            tempRequest.append("android.permission.ACCESS_FINE_LOCATION,");
        }
        if (tempRequest.length() > 0) {
            tempRequest.deleteCharAt(tempRequest.length() - 1);
            ActivityCompat.requestPermissions(this, tempRequest.toString().split(","), 0);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onDestroy() {
        super.onDestroy();
        iHealthDevicesManager.getInstance().destroy();
    }
}
