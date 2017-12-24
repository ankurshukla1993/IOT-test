package com.biz.health.cooey_app.broadcast_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import chatkit.holders.BloodGlucoseContentViewHolder;
import chatkit.holders.BloodPressureCardViewHolder;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.User;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import java.util.Date;

public class VitalsBroadCastReciver extends BroadcastReceiver {
    private static final String PREFERENCE_NAME = "COOEY_PREFERENCES";
    private static final String PROFILE_KEY = "PROFILE_JSON";
    public static float bmi;
    public static float bodyfat;
    public static float bodywater;
    public static float bonemass;
    public static int diastolic;
    public static int glucose;
    public static int heartRate;
    public static float musclemass;
    public static int systolic;
    public static String type;
    public static double weightValue;
    private String contextId;
    private String contextType;
    private Date dateString;
    private String patientId;
    private long timestamp;

    public void onReceive(Context context, Intent data) {
        Date dateString = new Date();
        this.timestamp = new Date().getTime();
        Bundle extras = data.getExtras();
        User user = new PreferenceStore().getUser(context);
        if (extras != null && data.getStringExtra("VITAL_TYPE") != null) {
            Intent intent;
            String vitalType = data.getStringExtra("VITAL_TYPE");
            if (vitalType.contentEquals(BloodGlucoseContentViewHolder.GLUCOSE)) {
                glucose = data.getIntExtra(BloodGlucoseContentViewHolder.GLUCOSE, 0);
                intent = new Intent(context, DeviceValuesActivity.class);
                intent.putExtra("VITAL_TYPE", BloodGlucoseContentViewHolder.GLUCOSE);
                intent.putExtra("glucose", glucose);
                intent.putExtra("PATIENT_ID", user.getId());
                intent.setFlags(268435456);
                context.startActivity(intent);
            }
            if (vitalType.contentEquals("BLOOD_PRESSURE")) {
                systolic = data.getIntExtra(BloodPressureCardViewHolder.SYSTOLIC, 0);
                diastolic = data.getIntExtra(BloodPressureCardViewHolder.DIASTOLIC, 0);
                heartRate = data.getIntExtra("HEART_RATE", 0);
                intent = new Intent(context, DeviceValuesActivity.class);
                intent.putExtra("VITAL_TYPE", "BLOOD_PRESSURE");
                intent.putExtra(BloodPressureCardViewHolder.SYSTOLIC, systolic);
                intent.putExtra(BloodPressureCardViewHolder.DIASTOLIC, diastolic);
                intent.putExtra("HEART_RATE", heartRate);
                intent.putExtra("PATIENT_ID", user.getId());
                intent.setFlags(268435456);
                context.startActivity(intent);
            }
            if (vitalType.contentEquals("WEIGHT")) {
                weightValue = (double) data.getFloatExtra("WEIGHT", 0.0f);
                bonemass = data.getFloatExtra("BONEDENSITY", 0.0f);
                bodyfat = data.getFloatExtra("BODYFATRATIO", 0.0f);
                bodywater = data.getFloatExtra("BODYWATERRATIO", 0.0f);
                musclemass = data.getFloatExtra("MUSCLEMASSRATIO", 0.0f);
                intent = new Intent(context, DeviceValuesActivity.class);
                intent.putExtra("VITAL_TYPE", "WEIGHT");
                intent.putExtra("WEIGHT", (float) weightValue);
                intent.putExtra("BODYFATRATIO", bodyfat);
                intent.putExtra("BODYWATERRATIO", bodywater);
                intent.putExtra("MUSCLEMASSRATIO", musclemass);
                intent.putExtra("BONEDENSITY", bonemass);
                intent.putExtra(DataBaseConstants.HSRESULT_BMI, bmi);
                intent.putExtra("PATIENT_ID", user.getId());
                intent.setFlags(268435456);
                context.startActivity(intent);
            }
        }
    }
}
