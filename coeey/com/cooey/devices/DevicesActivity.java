package com.cooey.devices;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.Toast;
import com.cooey.devices.common.DeviceRecyclerViewAdapter;
import com.cooey.devices.databinding.ActivityDevicesBinding;

public class DevicesActivity extends AppCompatActivity {
    ActivityDevicesBinding binding;
    private BluetoothAdapter mBluetoothAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        DeviceRecyclerViewAdapter deviceRecyclerViewAdapter;
        super.onCreate(savedInstanceState);
        String devices = getIntent().getStringExtra("DEVICES");
        this.binding = (ActivityDevicesBinding) DataBindingUtil.setContentView(this, C0910R.layout.activity_devices);
        setSupportActionBar(this.binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPermissions();
        if (devices != null) {
            deviceRecyclerViewAdapter = new DeviceRecyclerViewAdapter(this, devices);
        } else {
            deviceRecyclerViewAdapter = new DeviceRecyclerViewAdapter(this);
        }
        this.binding.deviceReclerView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.deviceReclerView.setAdapter(deviceRecyclerViewAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                turnOnBluetooth(false);
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
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

    public void onBackPressed() {
        super.onBackPressed();
        turnOnBluetooth(false);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1224:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    Toast.makeText(this, "Cannot scan devices without permissions.", 0).show();
                    return;
                } else {
                    turnOnBluetooth(false);
                    return;
                }
            default:
                return;
        }
    }
}
