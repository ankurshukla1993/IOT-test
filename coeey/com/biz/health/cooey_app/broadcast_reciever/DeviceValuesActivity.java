package com.biz.health.cooey_app.broadcast_reciever;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import chatkit.holders.BloodGlucoseContentViewHolder;
import chatkit.holders.BloodPressureCardViewHolder;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.biz.health.cooey_app.generators.DeviceInputGenerator;
import com.cooey.android.vitals.Vital;
import com.cooey.android.vitals.views.VitalInputDialogFragment.SaveCallback;
import com.ihealth.communication.cloud.data.DataBaseConstants;

public class DeviceValuesActivity extends AppCompatActivity implements SaveCallback {
    public float bmi;
    public float bodyfat;
    public float bodywater;
    public float bonemass;
    private String contextId;
    private String contextType;
    public int diastolic;
    public float glucose;
    public int heartRate;
    public float musclemass;
    private String patientId;
    public int systolic;
    public String type;
    public float weight;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_device_value);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String fragment = intent.getStringExtra("VITAL_TYPE");
            if (fragment != null && fragment.contentEquals(BloodGlucoseContentViewHolder.GLUCOSE)) {
                this.glucose = (float) intent.getIntExtra("glucose", 0);
                this.patientId = intent.getStringExtra("PATIENT_ID");
            }
            if (fragment != null && fragment.contentEquals("BLOOD_PRESSURE")) {
                this.systolic = intent.getIntExtra(BloodPressureCardViewHolder.SYSTOLIC, 0);
                this.diastolic = intent.getIntExtra(BloodPressureCardViewHolder.DIASTOLIC, 0);
                this.heartRate = intent.getIntExtra("HEART_RATE", 0);
                this.patientId = intent.getStringExtra("PATIENT_ID");
            }
            if (fragment != null && fragment.contentEquals("WEIGHT")) {
                this.weight = intent.getFloatExtra("WEIGHT", 0.0f);
                this.bonemass = intent.getFloatExtra("BONEDENSITY", 0.0f);
                this.bodyfat = intent.getFloatExtra("BODYFATRATIO", 0.0f);
                this.bodywater = intent.getFloatExtra("BODYWATERRATIO", 0.0f);
                this.bmi = intent.getFloatExtra(DataBaseConstants.HSRESULT_BMI, 0.0f);
                this.musclemass = intent.getFloatExtra("MUSCLEMASSRATIO", 0.0f);
                this.patientId = intent.getStringExtra("PATIENT_ID");
            }
            DeviceInputGenerator deviceInputGenerator = new DeviceInputGenerator();
            if (this.glucose > 0.0f && this.contextType != null && this.contextId != null && this.patientId != null) {
                deviceInputGenerator.generateInputForBloodGlucoseWithContextId((int) this.glucose, this.contextId, this.contextType, this.patientId, this, getSupportFragmentManager());
            } else if (this.glucose > 0.0f) {
                deviceInputGenerator.generateInputForBloodGlucose((int) this.glucose, this.patientId, this, getSupportFragmentManager());
            }
            if (this.systolic > 0 && this.contextType != null && this.contextId != null && this.patientId != null) {
                deviceInputGenerator.generateInputForBloodPressureWithContextId(this.systolic, this.diastolic, this.heartRate, this.contextId, this.contextType, this.patientId, this, getSupportFragmentManager());
            } else if (this.systolic > 0) {
                deviceInputGenerator.generateInputForBloodPressure(this.systolic, this.diastolic, this.heartRate, this.patientId, this, getSupportFragmentManager());
            }
            if (this.weight > 0.0f && this.contextType != null && this.contextId != null && this.patientId != null) {
                deviceInputGenerator.generateInputForWeightWithContextId(this.weight, this.bonemass, this.bodyfat, this.bodywater, this.musclemass, this.bmi, this.contextId, this.contextType, this.patientId, this, getSupportFragmentManager());
            } else if (this.weight > 0.0f) {
                deviceInputGenerator.generateInputForWeight(this.weight, this.bonemass, this.bodyfat, this.bodywater, this.musclemass, this.bmi, this.patientId, this, getSupportFragmentManager());
            }
        }
    }

    public void onSave(Vital vital) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
