package com.cooey.devices.bp_monitor;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import chatkit.holders.BloodPressureCardViewHolder;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyBleDeviceManager;
import com.cooey.devices.CooeyDeviceDataSource;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.DeviceDataRecieveCallback;
import com.cooey.devices.common.DeviceInputPagerAdapter;
import com.cooey.devices.databinding.ActivityBpdeviceInputActvityBinding;
import com.cooey.devices.vo.Device;
import com.cooey.devices.vo.DeviceType;
import java.util.ArrayList;
import java.util.List;

public class BPDeviceInputActvity extends AppCompatActivity {
    ActivityBpdeviceInputActvityBinding binding;
    CooeyBleDeviceManager cooeyDeviceManager;
    private DeviceInputPagerAdapter deviceInputPagerAdapter;
    int position = 0;

    class C09371 implements OnPageChangeListener {
        C09371() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            BPDeviceInputActvity.this.position = position;
            if (position == 2) {
                BPDeviceInputActvity.this.startReading();
            }
            if (position > 1) {
                BPDeviceInputActvity.this.binding.nextButton.setVisibility(8);
            } else {
                BPDeviceInputActvity.this.binding.nextButton.setVisibility(0);
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    class C09382 implements OnClickListener {
        C09382() {
        }

        public void onClick(View view) {
            BPDeviceInputActvity.this.binding.helpViewPager.setCurrentItem(BPDeviceInputActvity.this.position + 1);
        }
    }

    class C09393 implements OnTouchListener {
        C09393() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class C09414 implements DeviceDataRecieveCallback {
        C09414() {
        }

        public void onReceiveBloodPressureData(final float systolic, final float diastolic, final float heartRate, float meanArterialPressure) {
            BPDeviceInputActvity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Intent i = new Intent();
                    i.setAction("VitalValues");
                    i.putExtra("VITAL_TYPE", "BLOOD_PRESSURE");
                    i.putExtra(BloodPressureCardViewHolder.SYSTOLIC, (int) systolic);
                    i.putExtra(BloodPressureCardViewHolder.DIASTOLIC, (int) diastolic);
                    i.putExtra("HEART_RATE", (int) heartRate);
                    i.putExtra("PATIENT_ID", CooeyDeviceManager.userInfo.getPatientId());
                    i.putExtra("CONTEXT_ID", CooeyDeviceManager.userInfo.getContextId());
                    i.putExtra("CONTEXT_TYPE", CooeyDeviceManager.userInfo.getContextType());
                    BPDeviceInputActvity.this.turnOnBluetooth(false);
                    BPDeviceInputActvity.this.sendBroadcast(i);
                }
            });
        }

        public void onReceiveWeightData_A3(double weight, String unit, float basalMetabolism, float bodyFatRatio, float bodyWaterRatio, float visceralFatLevel, float muscleMassRatio, float boneDensity) {
            String data = "";
        }

        public void onReceiveWeightDta_A2(double weight, String unit, float basalMetabolism, float bodyFatRatio, float bodyWaterRatio, float visceralFatLevel, float muscleMassRatio, float boneDensity) {
            String data = "";
        }

        public void onRecieveGlucoseData(float glucose) {
            DeviceDataRecieveCallback deviceDataRecieveCallback = CooeyDeviceManager.getInstance().getDeviceDataRecieveCallback();
            if (deviceDataRecieveCallback != null) {
                deviceDataRecieveCallback.onRecieveGlucoseData(glucose);
                BPDeviceInputActvity.this.cooeyDeviceManager.destroy();
                BPDeviceInputActvity.this.cooeyDeviceManager = null;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<DeviceType> deviceTypeList = new ArrayList();
        deviceTypeList.add(DeviceType.SPYHGOMANOMETER);
        this.cooeyDeviceManager = new CooeyBleDeviceManager(this, deviceTypeList);
        this.cooeyDeviceManager.initialize(this);
        this.binding = (ActivityBpdeviceInputActvityBinding) DataBindingUtil.setContentView(this, C0910R.layout.activity_bpdevice_input_actvity);
        if (!(isBluetoothEnabled() || isLocationEnabled())) {
            getPermissions();
        }
        this.deviceInputPagerAdapter = new DeviceInputPagerAdapter(getSupportFragmentManager(), DeviceType.SPYHGOMANOMETER);
        this.binding.helpViewPager.setOnPageChangeListener(new C09371());
        this.binding.nextButton.setOnClickListener(new C09382());
        this.binding.helpViewPager.setOnTouchListener(new C09393());
        this.binding.helpViewPager.setAdapter(this.deviceInputPagerAdapter);
    }

    private void startReading() {
        CooeyDeviceDataSource ds = new CooeyDeviceDataSource(this);
        ds.open();
        List<Device> devs = ds.getAllDevices();
        ds.close();
        if (!(devs == null || devs.size() == 0)) {
            for (Device device : devs) {
                this.cooeyDeviceManager.addMeasureDevice(device);
            }
        }
        this.cooeyDeviceManager.startDataRecieve(new C09414());
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
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            turnOnBluetooth(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION"}, 1224);
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

    public void onDestroy() {
        super.onDestroy();
        try {
            this.cooeyDeviceManager.destroy();
            this.cooeyDeviceManager = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
    }
}
