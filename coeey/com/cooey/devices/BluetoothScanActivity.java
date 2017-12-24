package com.cooey.devices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BluetoothScanActivity extends AppCompatActivity {
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
    }
}
