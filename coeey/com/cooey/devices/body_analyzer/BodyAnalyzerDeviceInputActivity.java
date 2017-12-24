package com.cooey.devices.body_analyzer;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyBleDeviceManager;
import com.cooey.devices.CooeyDeviceDataSource;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.DeviceDataRecieveCallback;
import com.cooey.devices.common.DeviceInputPagerAdapter;
import com.cooey.devices.databinding.ActivityBodyAnalyzerDeviceInputBinding;
import com.cooey.devices.vo.Device;
import com.cooey.devices.vo.DeviceType;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.lifesense.ble.bean.SexType;
import com.lifesense.ble.bean.UnitType;
import com.lifesense.ble.bean.WeightUserInfo;
import java.util.ArrayList;
import java.util.List;

public class BodyAnalyzerDeviceInputActivity extends AppCompatActivity implements UserInfoDialogListner {
    ActivityBodyAnalyzerDeviceInputBinding binding;
    private CooeyBleDeviceManager cooeyDeviceManager;
    int position = 0;
    WeightUserInfo weightUserInfo;

    class C09181 implements OnPageChangeListener {
        C09181() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            BodyAnalyzerDeviceInputActivity.this.position = position;
            if (position == 1) {
                new WeightUserInfoDialogFragment().newInstance().show(BodyAnalyzerDeviceInputActivity.this.getSupportFragmentManager(), "Dialog");
            }
            if (position > 0) {
                BodyAnalyzerDeviceInputActivity.this.binding.nextButton.setVisibility(8);
            } else {
                BodyAnalyzerDeviceInputActivity.this.binding.nextButton.setVisibility(0);
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    class C09192 implements OnClickListener {
        C09192() {
        }

        public void onClick(View view) {
            BodyAnalyzerDeviceInputActivity.this.binding.helpViewPager.setCurrentItem(BodyAnalyzerDeviceInputActivity.this.position + 1);
        }
    }

    class C09203 implements OnTouchListener {
        C09203() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class C09234 implements DeviceDataRecieveCallback {
        C09234() {
        }

        public void onReceiveBloodPressureData(float systolic, float diastolic, float heartRate, float meanArterialPressure) {
            if (CooeyDeviceManager.getInstance().getDeviceDataRecieveCallback() != null) {
                CooeyDeviceManager.getInstance().getDeviceDataRecieveCallback().onReceiveBloodPressureData(systolic, diastolic, heartRate, meanArterialPressure);
            }
        }

        public void onReceiveWeightData_A3(double weight, String unit, float basalMetabolism, float bodyFatRatio, float bodyWaterRatio, float visceralFatLevel, float muscleMassRatio, float boneDensity) {
            if (weight > 0.0d) {
                final double d = weight;
                final String str = unit;
                final float f = basalMetabolism;
                final float f2 = bodyFatRatio;
                final float f3 = bodyWaterRatio;
                final float f4 = visceralFatLevel;
                final float f5 = muscleMassRatio;
                final float f6 = boneDensity;
                BodyAnalyzerDeviceInputActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        float bmi = BodyAnalyzerDeviceInputActivity.calculateBmi(BodyAnalyzerDeviceInputActivity.this.weightUserInfo.getHeight(), (float) d);
                        Intent i = new Intent();
                        i.setAction("VitalValues");
                        i.putExtra("VITAL_TYPE", "WEIGHT");
                        i.putExtra("WEIGHT", (float) d);
                        i.putExtra("UNIT", str);
                        i.putExtra("BASALMETABOLISM", f);
                        i.putExtra("BODYFATRATIO", f2);
                        i.putExtra("BODYWATERRATIO", f3);
                        i.putExtra("VISCERALFATRATIO", f4);
                        i.putExtra("MUSCLEMASSRATIO", f5);
                        i.putExtra(DataBaseConstants.HSRESULT_BMI, bmi);
                        i.putExtra("BONEDENSITY", f6);
                        i.putExtra("PATIENT_ID", CooeyDeviceManager.userInfo.getPatientId());
                        i.putExtra("CONTEXT_ID", CooeyDeviceManager.userInfo.getContextId());
                        i.putExtra("CONTEXT_TYPE", CooeyDeviceManager.userInfo.getContextType());
                        BodyAnalyzerDeviceInputActivity.this.turnOnBluetooth(false);
                        BodyAnalyzerDeviceInputActivity.this.sendBroadcast(i);
                    }
                });
            }
        }

        public void onReceiveWeightDta_A2(double weight, String unit, float basalMetabolism, float bodyFatRatio, float bodyWaterRatio, float visceralFatLevel, float muscleMassRatio, float boneDensity) {
            if (weight > 0.0d) {
                final double d = weight;
                final String str = unit;
                final float f = basalMetabolism;
                final float f2 = bodyFatRatio;
                final float f3 = bodyWaterRatio;
                final float f4 = visceralFatLevel;
                final float f5 = muscleMassRatio;
                final float f6 = boneDensity;
                BodyAnalyzerDeviceInputActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        float bmi = BodyAnalyzerDeviceInputActivity.calculateBmi(BodyAnalyzerDeviceInputActivity.this.weightUserInfo.getHeight(), (float) d);
                        Intent i = new Intent();
                        i.setAction("VitalValues");
                        i.putExtra("VITAL_TYPE", "WEIGHT");
                        i.putExtra("WEIGHT", (float) d);
                        i.putExtra("UNIT", str);
                        i.putExtra("BASALMETABOLISM", f);
                        i.putExtra("BODYFATRATIO", f2);
                        i.putExtra("BODYWATERRATIO", f3);
                        i.putExtra("VISCERALFATRATIO", f4);
                        i.putExtra("MUSCLEMASSRATIO", f5);
                        i.putExtra(DataBaseConstants.HSRESULT_BMI, bmi);
                        i.putExtra("BONEDENSITY", f6);
                        i.putExtra("PATIENT_ID", CooeyDeviceManager.userInfo.getPatientId());
                        i.putExtra("CONTEXT_ID", CooeyDeviceManager.userInfo.getContextId());
                        i.putExtra("CONTEXT_TYPE", CooeyDeviceManager.userInfo.getContextType());
                        BodyAnalyzerDeviceInputActivity.this.turnOnBluetooth(false);
                        BodyAnalyzerDeviceInputActivity.this.sendBroadcast(i);
                    }
                });
            }
        }

        public void onRecieveGlucoseData(float glucose) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityBodyAnalyzerDeviceInputBinding) DataBindingUtil.setContentView(this, C0910R.layout.activity_body_analyzer_device_input);
        if (!(isBluetoothEnabled() || isLocationEnabled())) {
            getPermissions();
        }
        this.weightUserInfo = new WeightUserInfo();
        DeviceInputPagerAdapter deviceInputPagerAdapter = new DeviceInputPagerAdapter(getSupportFragmentManager(), DeviceType.BODY_ANALYZER);
        this.binding.helpViewPager.setOnPageChangeListener(new C09181());
        this.binding.nextButton.setOnClickListener(new C09192());
        this.binding.helpViewPager.setOnTouchListener(new C09203());
        this.binding.helpViewPager.setAdapter(deviceInputPagerAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    private boolean isLocationEnabled() {
        return ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0;
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
        this.cooeyDeviceManager.startDataRecieve(new C09234());
    }

    public boolean isBluetoothEnabled() {
        try {
            return BluetoothAdapter.getDefaultAdapter().enable();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
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

    protected void getPermissions() {
        if (VERSION.SDK_INT < 23) {
            turnOnBluetooth(true);
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            turnOnBluetooth(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION"}, 1224);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
    }

    public void saveUserInfo(int age, float height, SexType sexType) {
        this.weightUserInfo.setAge(age);
        this.weightUserInfo.setHeight(height);
        this.weightUserInfo.setSex(sexType);
        this.weightUserInfo.setUnit(UnitType.UNIT_KG);
        List<DeviceType> deviceTypeList = new ArrayList();
        deviceTypeList.add(DeviceType.BODY_ANALYZER);
        this.cooeyDeviceManager = new CooeyBleDeviceManager(this, deviceTypeList, this.weightUserInfo);
        this.cooeyDeviceManager.initialize(this);
        startReading();
    }

    public static float calculateBmi(float userHeight, float weight) {
        if (userHeight == 0.0f) {
            return 0.0f;
        }
        float height = (float) (((double) userHeight) * 0.01d);
        return Float.valueOf(String.format("%.2f", new Object[]{Float.valueOf(weight / (height * height))})).floatValue();
    }
}
