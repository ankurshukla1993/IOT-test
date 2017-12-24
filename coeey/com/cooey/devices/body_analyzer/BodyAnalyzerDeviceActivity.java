package com.cooey.devices.body_analyzer;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyBleDeviceManager;
import com.cooey.devices.CooeyDeviceDataSource;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.DevicePairCallback;
import com.cooey.devices.DeviceSearchCallback;
import com.cooey.devices.common.DeviceScanViewPager;
import com.cooey.devices.databinding.ActivityBodyAnalyzerDeviceBinding;
import com.cooey.devices.vo.Device;
import com.cooey.devices.vo.DeviceInfo;
import com.cooey.devices.vo.DeviceType;
import com.lifesense.ble.bean.SexType;
import com.lifesense.ble.bean.UnitType;
import com.lifesense.ble.bean.WeightUserInfo;
import java.util.ArrayList;
import java.util.List;

public class BodyAnalyzerDeviceActivity extends AppCompatActivity {
    ActivityBodyAnalyzerDeviceBinding binding;
    private int position = 0;

    class C09111 implements OnPageChangeListener {
        C09111() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            BodyAnalyzerDeviceActivity.this.position = position;
            if (position == 2) {
                BodyAnalyzerDeviceActivity.this.startScan();
            }
            if (position > 1) {
                BodyAnalyzerDeviceActivity.this.binding.nextButton.setVisibility(8);
            } else {
                BodyAnalyzerDeviceActivity.this.binding.nextButton.setVisibility(0);
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    class C09122 implements OnTouchListener {
        C09122() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class C09133 implements OnClickListener {
        C09133() {
        }

        public void onClick(View view) {
            BodyAnalyzerDeviceActivity.this.binding.helpViewPager.setCurrentItem(BodyAnalyzerDeviceActivity.this.position + 1);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityBodyAnalyzerDeviceBinding) DataBindingUtil.setContentView(this, C0910R.layout.activity_body_analyzer_device);
        DeviceScanViewPager deviceScanViewPager = new DeviceScanViewPager(getSupportFragmentManager(), DeviceType.BODY_ANALYZER);
        this.binding.helpViewPager.setOnPageChangeListener(new C09111());
        this.binding.helpViewPager.setOnTouchListener(new C09122());
        this.binding.helpViewPager.setAdapter(deviceScanViewPager);
        this.binding.nextButton.setOnClickListener(new C09133());
        getPermissions();
    }

    private void startScan() {
        final String[] items = new String[]{"Profile 1", " Profile 2", "Profile 3", "Profile 4", "Profile 5", "Profile 6", "Profile 7", "Profile 8"};
        List<DeviceType> deviceTypeList = new ArrayList();
        deviceTypeList.add(DeviceType.BODY_ANALYZER);
        WeightUserInfo weightUserInfo = new WeightUserInfo();
        weightUserInfo.setAge(27);
        weightUserInfo.setHeight(172.0f);
        weightUserInfo.setSex(SexType.MALE);
        weightUserInfo.setUnit(UnitType.UNIT_KG);
        final CooeyBleDeviceManager cooeyBleDeviceManager = new CooeyBleDeviceManager(this, deviceTypeList, weightUserInfo);
        cooeyBleDeviceManager.initialize(this);
        cooeyBleDeviceManager.startSearch(new DeviceSearchCallback() {

            class C09161 implements DevicePairCallback {

                class C09141 implements DialogInterface.OnClickListener {
                    C09141() {
                    }

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        CooeyDeviceDataSource ds = new CooeyDeviceDataSource(BodyAnalyzerDeviceActivity.this);
                        ds.open();
                        List<Device> devs = ds.getAllDevices();
                        if (devs == null || devs.size() <= 0) {
                            cooeyBleDeviceManager.bindDeviceUser(selectedPosition + 1, "First Name");
                            return;
                        }
                        for (Device device1 : devs) {
                            if (device1.getDeviceName().equalsIgnoreCase("1257B") && selectedPosition + 1 == device1.getDeviceUserNumber()) {
                                Toast.makeText(BodyAnalyzerDeviceActivity.this, "Device Already Paired", 0).show();
                                Intent intent = new Intent(BodyAnalyzerDeviceActivity.this, BodyAnalyzerDeviceInputActivity.class);
                                intent.putExtra("DeviceUserNumber", device1.getDeviceUserNumber());
                                BodyAnalyzerDeviceActivity.this.startActivityForResult(intent, CooeyDeviceManager.DEVICE_READ_REQUEST);
                                ds.close();
                            } else {
                                cooeyBleDeviceManager.bindDeviceUser(selectedPosition + 1, "First Name");
                            }
                        }
                    }
                }

                class C09152 implements Runnable {
                    C09152() {
                    }

                    public void run() {
                        BodyAnalyzerDeviceActivity.this.binding.helpViewPager.setCurrentItem(BodyAnalyzerDeviceActivity.this.position + 1);
                    }
                }

                C09161() {
                }

                public void onDiscoverUserInfo(List<DeviceInfo> list) {
                    new Builder(BodyAnalyzerDeviceActivity.this).setSingleChoiceItems(items, 0, null).setPositiveButton("Ok", new C09141()).show();
                }

                public void onPairResults(Device device, int i) {
                    CooeyDeviceDataSource ds = new CooeyDeviceDataSource(BodyAnalyzerDeviceActivity.this);
                    ds.open();
                    ds.createDeviceForUser(device, 1);
                    ds.close();
                    BodyAnalyzerDeviceActivity.this.runOnUiThread(new C09152());
                }
            }

            public void onSearchResult(Device device) {
                if (device.getDeviceName().contentEquals("1257B")) {
                    cooeyBleDeviceManager.stopSearch();
                    cooeyBleDeviceManager.startPairing(device, new C09161());
                }
            }
        });
    }

    protected void getPermissions() {
        if (VERSION.SDK_INT < 23) {
            turnOnBluetooth();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            turnOnBluetooth();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION"}, 1224);
        }
    }

    private void turnOnBluetooth() {
        try {
            BluetoothAdapter.getDefaultAdapter().enable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1224:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Toast.makeText(this, "Cannot scan devices without permissions.", 0).show();
                    return;
                }
                turnOnBluetooth();
                startScan();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
    }
}
