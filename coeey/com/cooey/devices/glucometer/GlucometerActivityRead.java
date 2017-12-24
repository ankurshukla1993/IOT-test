package com.cooey.devices.glucometer;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import chatkit.holders.BloodGlucoseContentViewHolder;
import com.cooey.devices.C0910R;
import com.cooey.devices.CooeyDeviceManager;
import com.cooey.devices.views.ControlledCommonViewPager;
import com.dnurse.DnurseDevTestDef.IMeasureDataResultCallback;
import com.dnurse.exttestlib.DnurseDeviceTest;
import java.util.Calendar;

public class GlucometerActivityRead extends AppCompatActivity {
    private static final String PREFERENCE_NAME = "COOEY_PREFERENCES";
    private static final String PROFILE_KEY = "PROFILE_JSON";
    private static final int RECORD_AUDIO_PERMISSION = 2121;
    private static DnurseDeviceTest glucometerDevice;
    ControlledCommonViewPager controlledCommonViewPager;
    private GlucometerViewPagerAdapter glucometerViewPagerAdapter;
    private Handler handler = new Handler();
    IMeasureDataResultCallback iMeasureDataResultCallback = new C09581();
    private String mDeviceSN;
    private int mDnurseState = 0;
    private float mTestResult;
    private Calendar mTime;
    private int m_arg0;
    private int m_arg1;
    int resetCount = 0;
    Toolbar toolbar;
    TextView toolbarTitle;

    class C09581 implements IMeasureDataResultCallback {

        class C09571 implements Runnable {
            C09571() {
            }

            public void run() {
                GlucometerActivityRead.this.measureData();
            }
        }

        C09581() {
        }

        public void onSuccess(SparseArray arg0) {
            GlucometerActivityRead.this.mTestResult = ((Float) arg0.get(1)).floatValue();
            GlucometerActivityRead.this.mTime = (Calendar) arg0.get(2);
            GlucometerActivityRead.this.mDeviceSN = (String) arg0.get(3);
        }

        public void onMeasuring(int arg0, int arg1) {
            GlucometerActivityRead.this.m_arg0 = arg0;
            GlucometerActivityRead.this.m_arg1 = arg1;
            GlucometerActivityRead.this.handler.post(new C09571());
        }
    }

    class C09592 implements OnClickListener {
        C09592() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C09603 implements OnClickListener {
        C09603() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            GlucometerActivityRead.this.finish();
        }
    }

    private void measureData() {
        this.mDnurseState = this.m_arg0;
        if (this.mDnurseState == 0 || this.mDnurseState == 1 || this.mDnurseState == 2 || this.mDnurseState == 3 || this.mDnurseState == 4 || this.mDnurseState == 5 || this.mDnurseState == 6 || this.mDnurseState == 7 || this.mDnurseState != 8) {
        }
        switch (this.m_arg0) {
            case 0:
                try {
                    this.controlledCommonViewPager.setCurrentItem(0);
                    this.toolbarTitle.setText("GLUCOMETER - STEP 1");
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
            case 3:
                this.controlledCommonViewPager.setCurrentItem(1);
                try {
                    ((TextView) this.controlledCommonViewPager.getRootView().findViewById(C0910R.id.glucometer_status)).setText("PLEASE PUT IN THE TEST STRIP");
                    this.toolbarTitle.setText("GLUCOMETER - STEP 2");
                    return;
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    return;
                }
            case 4:
                try {
                    ((TextView) this.controlledCommonViewPager.getRootView().findViewById(C0910R.id.glucometer_status)).setText("PLEASE PUT BLOOD ON THE STRIP");
                    this.toolbarTitle.setText("GLUCOMETER - STEP 2");
                    return;
                } catch (Exception ex22) {
                    ex22.printStackTrace();
                    return;
                }
            case 5:
                try {
                    ((TextView) this.controlledCommonViewPager.getRootView().findViewById(C0910R.id.glucometer_status)).setText("THIS TEST STRIP IS OLD PLEASE PUT A NEW TEST STRIP IN");
                    this.toolbarTitle.setText("GLUCOMETER - STEP 2");
                    return;
                } catch (Exception ex222) {
                    ex222.printStackTrace();
                    return;
                }
            case 6:
                try {
                    ((TextView) this.controlledCommonViewPager.getRootView().findViewById(C0910R.id.glucometer_status)).setText("PLEASE PUT IN THE TEST STRIP");
                    return;
                } catch (Exception ex2222) {
                    ex2222.printStackTrace();
                    return;
                }
            case 7:
                Toast.makeText(this, "Calculating...", 0).show();
                return;
            case 8:
                try {
                    saveGlucoseValue();
                    return;
                } catch (Exception ex22222) {
                    ex22222.printStackTrace();
                    return;
                }
            case 9:
                try {
                    TextView textView = (TextView) this.controlledCommonViewPager.getRootView().findViewById(C0910R.id.glucometer_status);
                    glucometerDevice.wakeupDevice();
                    initializeDevice();
                    return;
                } catch (Exception ex222222) {
                    ex222222.printStackTrace();
                    return;
                }
            case 10:
                try {
                    ((TextView) this.controlledCommonViewPager.getRootView().findViewById(C0910R.id.glucometer_status)).setText("Low voltage please re-insert and start again.");
                    return;
                } catch (Exception ex2222222) {
                    ex2222222.printStackTrace();
                    return;
                }
            case 11:
                Toast.makeText(this, "Error: Could not calibarte", 0).show();
                return;
            case 12:
                Toast.makeText(this, "Error: Temperature low.", 0).show();
                return;
            case 13:
                Toast.makeText(this, "Error: Temperature High.", 0).show();
                return;
            case 14:
                Toast.makeText(this, "Error: Play Audio", 0).show();
                return;
            case 15:
                try {
                    Toast.makeText(this, "Error initializing please re-insert and try again.", 0).show();
                    return;
                } catch (Exception ex22222222) {
                    ex22222222.printStackTrace();
                    return;
                }
            case 16:
                try {
                    Toast.makeText(this, "Error initializing please re-insert and try again.", 0).show();
                    return;
                } catch (Exception ex222222222) {
                    ex222222222.printStackTrace();
                    return;
                }
            case 17:
                Toast.makeText(this, "Error initializing please re-insert and try again.", 0).show();
                return;
            default:
                return;
        }
    }

    private void saveGlucoseValue() {
        float mgdl = this.mTestResult * 18.0f;
        if (mgdl > 0.0f) {
            Intent i = new Intent();
            i.setAction("VitalValues");
            i.putExtra("VITAL_TYPE", BloodGlucoseContentViewHolder.GLUCOSE);
            i.putExtra(BloodGlucoseContentViewHolder.GLUCOSE, (int) mgdl);
            i.putExtra("PATIENT_ID", CooeyDeviceManager.userInfo.getPatientId());
            i.putExtra("CONTEXT_ID", CooeyDeviceManager.userInfo.getContextId());
            i.putExtra("CONTEXT_TYPE", CooeyDeviceManager.userInfo.getContextType());
            sendBroadcast(i);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0910R.layout.activity_glucometer_devices);
        getWindow().addFlags(128);
        Intent intent = getIntent();
        if (!(intent == null || intent.getExtras() == null || !intent.getExtras().getBoolean("SHOW_CHOOSE_DIALOG", false))) {
            showAlertDialog();
        }
        this.controlledCommonViewPager = (ControlledCommonViewPager) findViewById(C0910R.id.glucometer_view_pager);
        this.toolbar = (Toolbar) findViewById(C0910R.id.toolbar);
        this.toolbarTitle = (TextView) findViewById(C0910R.id.toolbar_title);
        initiateActionBar();
        this.glucometerViewPagerAdapter = new GlucometerViewPagerAdapter(getSupportFragmentManager());
        this.controlledCommonViewPager.setAdapter(this.glucometerViewPagerAdapter);
        this.toolbarTitle.setText("GLUCOMETER - STEP 1");
        if (ActivityCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, RECORD_AUDIO_PERMISSION);
        }
    }

    private void initializeDevice() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            if (glucometerDevice == null) {
                glucometerDevice = new DnurseDeviceTest(getApplicationContext());
            }
            glucometerDevice.startTest(this.iMeasureDataResultCallback);
        }
    }

    private void showAlertDialog() {
        Builder builder = new Builder(this);
        builder.setTitle("Cooey Glucometer");
        builder.setMessage("Have you plugged in the cooey glucometer?");
        builder.setPositiveButton("Yes", new C09592());
        builder.setNegativeButton("No", new C09603());
        builder.create().show();
    }

    protected void onResume() {
        super.onResume();
        initializeDevice();
    }

    private void initiateActionBar() {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case RECORD_AUDIO_PERMISSION /*2121*/:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    initializeDevice();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onPause() {
        super.onPause();
        try {
            glucometerDevice.stopTest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            glucometerDevice.stopTest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
