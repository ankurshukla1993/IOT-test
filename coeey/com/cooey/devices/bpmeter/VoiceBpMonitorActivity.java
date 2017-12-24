package com.cooey.devices.bpmeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyDeviceManager;

public class VoiceBpMonitorActivity extends AppCompatActivity {
    private Button btnNext;

    class C09491 implements OnClickListener {
        C09491() {
        }

        public void onClick(View v) {
            VoiceBpMonitorActivity.this.startActivityForResult(new Intent(VoiceBpMonitorActivity.this, BpMeterScanActivity.class), CooeyDeviceManager.DEVICE_READ_REQUEST);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0910R.layout.activity_voice_bp_monitor);
        setSupportActionBar((Toolbar) findViewById(C0910R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.btnNext = (Button) findViewById(C0910R.id.btn_next);
        this.btnNext.setOnClickListener(new C09491());
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
