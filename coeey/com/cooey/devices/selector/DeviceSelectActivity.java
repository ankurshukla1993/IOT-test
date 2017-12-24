package com.cooey.devices.selector;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.body_analyzer.BodyAnalyzerDeviceActivity;
import com.cooey.devices.bp_monitor.BPDeviceActivity;
import com.cooey.devices.databinding.ActivityDeviceSelectBinding;

public class DeviceSelectActivity extends AppCompatActivity {

    class C09611 implements OnClickListener {
        C09611() {
        }

        public void onClick(View view) {
            DeviceSelectActivity.this.startActivityForResult(new Intent(DeviceSelectActivity.this, BPDeviceActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
        }
    }

    class C09622 implements OnClickListener {
        C09622() {
        }

        public void onClick(View view) {
            DeviceSelectActivity.this.startActivityForResult(new Intent(DeviceSelectActivity.this, BodyAnalyzerDeviceActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDeviceSelectBinding activityDeviceSelectBinding = (ActivityDeviceSelectBinding) DataBindingUtil.setContentView(this, C0910R.layout.activity_device_select);
        setSupportActionBar(activityDeviceSelectBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityDeviceSelectBinding.setDeviceSelectViewModel(new DeviceSelectViewModel());
        activityDeviceSelectBinding.bloodPressureDeviceButton.setOnClickListener(new C09611());
        activityDeviceSelectBinding.bodyAnalyzerButton.setOnClickListener(new C09622());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode, data);
        finish();
    }
}
