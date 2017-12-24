package com.cooey.devices.bp_monitor;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import com.cooey.devices.BluetoothScanActivity;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyBleDeviceManager;
import com.cooey.devices.CooeyDeviceDataSource;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.DevicePairCallback;
import com.cooey.devices.DeviceSearchCallback;
import com.cooey.devices.common.DeviceScanViewPager;
import com.cooey.devices.databinding.ActivityBpdeviceBinding;
import com.cooey.devices.vo.Device;
import com.cooey.devices.vo.DeviceInfo;
import com.cooey.devices.vo.DeviceType;
import java.util.ArrayList;
import java.util.List;

public class BPDeviceActivity extends BluetoothScanActivity {
    ActivityBpdeviceBinding binding;
    private int position = 0;

    class C09301 implements OnPageChangeListener {
        C09301() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            BPDeviceActivity.this.position = position;
            if (position == 2) {
                BPDeviceActivity.this.startScan();
            }
            if (position > 1) {
                BPDeviceActivity.this.binding.nextButton.setVisibility(8);
            } else {
                BPDeviceActivity.this.binding.nextButton.setVisibility(0);
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    class C09312 implements OnTouchListener {
        C09312() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class C09323 implements OnClickListener {
        C09323() {
        }

        public void onClick(View view) {
            BPDeviceActivity.this.binding.helpViewPager.setCurrentItem(BPDeviceActivity.this.position + 1);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityBpdeviceBinding) DataBindingUtil.setContentView(this, C0910R.layout.activity_bpdevice);
        DeviceScanViewPager deviceScanViewPager = new DeviceScanViewPager(getSupportFragmentManager(), DeviceType.SPYHGOMANOMETER);
        this.binding.helpViewPager.setOnPageChangeListener(new C09301());
        this.binding.helpViewPager.setOnTouchListener(new C09312());
        this.binding.helpViewPager.setAdapter(deviceScanViewPager);
        this.binding.nextButton.setOnClickListener(new C09323());
        getPermissions();
    }

    private void startScan() {
        final String[] items = new String[]{"Profile 1", " Profile 2"};
        List<DeviceType> deviceTypeList = new ArrayList();
        deviceTypeList.add(DeviceType.SPYHGOMANOMETER);
        final CooeyBleDeviceManager cooeyBleDeviceManager = new CooeyBleDeviceManager(this, deviceTypeList);
        cooeyBleDeviceManager.initialize(this);
        cooeyBleDeviceManager.startSearch(new DeviceSearchCallback() {

            class C09351 implements DevicePairCallback {

                class C09331 implements DialogInterface.OnClickListener {
                    C09331() {
                    }

                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        CooeyDeviceDataSource ds = new CooeyDeviceDataSource(BPDeviceActivity.this);
                        ds.open();
                        List<Device> devs = ds.getAllDevices();
                        if (devs == null || devs.size() <= 0) {
                            cooeyBleDeviceManager.bindDeviceUser(selectedPosition + 1, "First Name");
                            return;
                        }
                        for (Device device1 : devs) {
                            if (device1.getDeviceName().equalsIgnoreCase("808A0") && selectedPosition + 1 == device1.getDeviceUserNumber()) {
                                Toast.makeText(BPDeviceActivity.this, "Device Already Paired", 0).show();
                                BPDeviceActivity.this.startActivityForResult(new Intent(BPDeviceActivity.this, BPDeviceInputActvity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
                                ds.close();
                            } else {
                                cooeyBleDeviceManager.bindDeviceUser(selectedPosition + 1, "First Name");
                            }
                        }
                    }
                }

                class C09342 implements Runnable {
                    C09342() {
                    }

                    public void run() {
                        BPDeviceActivity.this.binding.helpViewPager.setCurrentItem(BPDeviceActivity.this.position + 1);
                    }
                }

                C09351() {
                }

                public void onDiscoverUserInfo(List<DeviceInfo> list) {
                    new Builder(BPDeviceActivity.this).setSingleChoiceItems(items, 0, null).setPositiveButton("Ok", new C09331()).show();
                }

                public void onPairResults(Device device, int i) {
                    CooeyDeviceDataSource ds = new CooeyDeviceDataSource(BPDeviceActivity.this);
                    ds.open();
                    ds.createDeviceForUser(device, 1);
                    ds.close();
                    BPDeviceActivity.this.runOnUiThread(new C09342());
                }
            }

            public void onSearchResult(Device device) {
                if (device.getDeviceName().contentEquals("808A0")) {
                    cooeyBleDeviceManager.stopSearch();
                    cooeyBleDeviceManager.startPairing(device, new C09351());
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
