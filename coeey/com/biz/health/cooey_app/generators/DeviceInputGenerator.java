package com.biz.health.cooey_app.generators;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.vitals.DataType;
import com.cooey.android.vitals.Field;
import com.cooey.android.vitals.VitalBlueprint;
import com.cooey.android.vitals.views.VitalInputDialogFragment;
import com.cooey.android.vitals.views.VitalInputDialogFragment.SaveCallback;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ6\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ6\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJF\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJN\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ^\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\u001e"}, d2 = {"Lcom/biz/health/cooey_app/generators/DeviceInputGenerator;", "", "()V", "generateInputForBloodGlucose", "", "glucose", "", "userId", "", "onSaveCallback", "Lcom/cooey/android/vitals/views/VitalInputDialogFragment$SaveCallback;", "fragmentManager", "Landroid/support/v4/app/FragmentManager;", "generateInputForBloodGlucoseWithContextId", "contextId", "contextType", "generateInputForBloodPressure", "systolic", "diastolic", "heartRate", "generateInputForBloodPressureWithContextId", "generateInputForWeight", "weight", "", "bonemass", "bodyfat", "bodywater", "musclemass", "bmi", "generateInputForWeightWithContextId", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: DeviceInputGenerator.kt */
public final class DeviceInputGenerator {
    public final void generateInputForBloodPressure(int systolic, int diastolic, int heartRate, @NotNull String userId, @NotNull SaveCallback onSaveCallback, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(onSaveCallback, "onSaveCallback");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint = new VitalBlueprint(uuid, CTConstants.FAB_PRESSURE, "BLOOD_PRESSURE", Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
        List fields = new ArrayList();
        Field systolicField = new Field("Systolic", "hhMG", CooeySQLHelper.COL_SYS, DataType.INTEGER);
        Field diastolicField = new Field("Diastolic", "hhMG", CooeySQLHelper.COL_DIA, DataType.INTEGER);
        Field heartRateField = new Field("Heart Rate", "hhMG", "heartRate", DataType.INTEGER);
        fields.add(systolicField);
        fields.add(diastolicField);
        fields.add(heartRateField);
        HashMap values = new HashMap();
        values.put(CooeySQLHelper.COL_SYS, String.valueOf(systolic));
        values.put(CooeySQLHelper.COL_DIA, String.valueOf(diastolic));
        values.put("heartRate", String.valueOf(heartRate));
        vitalBlueprint.setFields(fields);
        VitalInputDialogFragment vitalInputFragment = new VitalInputDialogFragment();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(userId);
        vitalInputFragment.setValues(values);
        vitalInputFragment.setSaveCallback(onSaveCallback);
        vitalInputFragment.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
        FragmentTransaction dialogTransaction = fragmentManager.beginTransaction();
        if (dialogTransaction != null) {
            dialogTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (dialogTransaction != null) {
            FragmentTransaction add = dialogTransaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                add = add.addToBackStack(null);
                if (add != null) {
                    add.commit();
                }
            }
        }
    }

    public final void generateInputForBloodPressureWithContextId(int systolic, int diastolic, int heartRate, @NotNull String contextId, @NotNull String contextType, @NotNull String userId, @NotNull SaveCallback onSaveCallback, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(contextId, "contextId");
        Intrinsics.checkParameterIsNotNull(contextType, "contextType");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(onSaveCallback, "onSaveCallback");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint = new VitalBlueprint(uuid, CTConstants.FAB_PRESSURE, "BLOOD_PRESSURE", Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
        List fields = new ArrayList();
        Field systolicField = new Field("Systolic", "hhMG", CooeySQLHelper.COL_SYS, DataType.INTEGER);
        Field diastolicField = new Field("Diastolic", "hhMG", CooeySQLHelper.COL_DIA, DataType.INTEGER);
        Field heartRateField = new Field("Heart Rate", "hhMG", "heartRate", DataType.INTEGER);
        fields.add(systolicField);
        fields.add(diastolicField);
        fields.add(heartRateField);
        HashMap values = new HashMap();
        values.put(CooeySQLHelper.COL_SYS, String.valueOf(systolic));
        values.put(CooeySQLHelper.COL_DIA, String.valueOf(diastolic));
        values.put("heartRate", String.valueOf(heartRate));
        vitalBlueprint.setFields(fields);
        VitalInputDialogFragment vitalInputFragment = new VitalInputDialogFragment();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(userId);
        vitalInputFragment.setValues(values);
        vitalInputFragment.setSaveCallback(onSaveCallback);
        vitalInputFragment.setContextId(contextId);
        vitalInputFragment.setContextType(contextType);
        vitalInputFragment.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
        FragmentTransaction dialogTransaction = fragmentManager.beginTransaction();
        if (dialogTransaction != null) {
            dialogTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (dialogTransaction != null) {
            FragmentTransaction add = dialogTransaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                add = add.addToBackStack(null);
                if (add != null) {
                    add.commit();
                }
            }
        }
    }

    public final void generateInputForBloodGlucoseWithContextId(int glucose, @NotNull String contextId, @NotNull String contextType, @NotNull String userId, @NotNull SaveCallback onSaveCallback, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(contextId, "contextId");
        Intrinsics.checkParameterIsNotNull(contextType, "contextType");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(onSaveCallback, "onSaveCallback");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint = new VitalBlueprint(uuid, "Blood Glucose", "BLOOD_GLUCOSE", Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
        List fields = new ArrayList();
        fields.add(new Field("Glucose", "hhMG", "glucose", DataType.INTEGER));
        HashMap values = new HashMap();
        values.put("glucose", String.valueOf(glucose));
        vitalBlueprint.setFields(fields);
        VitalInputDialogFragment vitalInputFragment = new VitalInputDialogFragment();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(userId);
        vitalInputFragment.setValues(values);
        vitalInputFragment.setContextId(contextId);
        vitalInputFragment.setContextType(contextType);
        vitalInputFragment.setSaveCallback(onSaveCallback);
        vitalInputFragment.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
        FragmentTransaction dialogTransaction = fragmentManager.beginTransaction();
        if (dialogTransaction != null) {
            dialogTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (dialogTransaction != null) {
            FragmentTransaction add = dialogTransaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                add = add.addToBackStack(null);
                if (add != null) {
                    add.commit();
                }
            }
        }
    }

    public final void generateInputForBloodGlucose(int glucose, @NotNull String userId, @NotNull SaveCallback onSaveCallback, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(onSaveCallback, "onSaveCallback");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint = new VitalBlueprint(uuid, "Blood Glucose", "BLOOD_GLUCOSE", Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
        List fields = new ArrayList();
        fields.add(new Field("Glucose", "hhMG", "glucose", DataType.INTEGER));
        HashMap values = new HashMap();
        values.put("glucose", String.valueOf(glucose));
        vitalBlueprint.setFields(fields);
        VitalInputDialogFragment vitalInputFragment = new VitalInputDialogFragment();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(userId);
        vitalInputFragment.setValues(values);
        vitalInputFragment.setSaveCallback(onSaveCallback);
        vitalInputFragment.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
        FragmentTransaction dialogTransaction = fragmentManager.beginTransaction();
        if (dialogTransaction != null) {
            dialogTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (dialogTransaction != null) {
            FragmentTransaction add = dialogTransaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                add = add.addToBackStack(null);
                if (add != null) {
                    add.commit();
                }
            }
        }
    }

    public final void generateInputForWeight(float weight, float bonemass, float bodyfat, float bodywater, float musclemass, float bmi, @NotNull String userId, @NotNull SaveCallback onSaveCallback, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(onSaveCallback, "onSaveCallback");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint = new VitalBlueprint(uuid, CTConstants.FAB_WEIGHT, "WEIGHT", Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
        List fields = new ArrayList();
        Field field = new Field(CTConstants.FAB_WEIGHT, "hhMG", "WEIGHT", DataType.DECIMAL);
        Field bonemassField = new Field("BoneMass", "hhMG", "BONEMASS", DataType.DECIMAL);
        Field bodyFatField = new Field("Bodyfat", "hhMG", "BODYFAT", DataType.DECIMAL);
        Field bodyWaterField = new Field("Bodywater", "hhMG", "BODYWATER", DataType.DECIMAL);
        Field musclemassField = new Field("Musclemass", "hhMG", "MUSCLEMASS", DataType.DECIMAL);
        Field bmiField = new Field(DataBaseConstants.HSRESULT_BMI, "hhMG", DataBaseConstants.HSRESULT_BMI, DataType.DECIMAL);
        fields.add(field);
        fields.add(bonemassField);
        fields.add(bodyFatField);
        fields.add(bodyWaterField);
        fields.add(musclemassField);
        fields.add(bmiField);
        HashMap values = new HashMap();
        if (weight > ((float) 0)) {
            values.put("WEIGHT", String.valueOf(weight));
        }
        if (bonemass > ((float) 0)) {
            values.put("BONEMASS", String.valueOf(bonemass));
        }
        if (bodywater > ((float) 0)) {
            values.put("BODYFAT", String.valueOf(bodyfat));
        }
        if (bodywater > ((float) 0)) {
            values.put("BODYWATER", String.valueOf(bodywater));
        }
        if (musclemass > ((float) 0)) {
            values.put("MUSCLEMASS", String.valueOf(musclemass));
        }
        if (bmi > ((float) 0)) {
            values.put(DataBaseConstants.HSRESULT_BMI, String.valueOf(bmi));
        }
        vitalBlueprint.setFields(fields);
        VitalInputDialogFragment vitalInputFragment = new VitalInputDialogFragment();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(userId);
        vitalInputFragment.setValues(values);
        vitalInputFragment.setSaveCallback(onSaveCallback);
        vitalInputFragment.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
        FragmentTransaction dialogTransaction = fragmentManager.beginTransaction();
        if (dialogTransaction != null) {
            dialogTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (dialogTransaction != null) {
            FragmentTransaction add = dialogTransaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                add = add.addToBackStack(null);
                if (add != null) {
                    add.commit();
                }
            }
        }
    }

    public final void generateInputForWeightWithContextId(float weight, float bonemass, float bodyfat, float bodywater, float musclemass, float bmi, @NotNull String contextId, @NotNull String contextType, @NotNull String userId, @NotNull SaveCallback onSaveCallback, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(contextId, "contextId");
        Intrinsics.checkParameterIsNotNull(contextType, "contextType");
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        Intrinsics.checkParameterIsNotNull(onSaveCallback, "onSaveCallback");
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fragmentManager");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        VitalBlueprint vitalBlueprint = new VitalBlueprint(uuid, CTConstants.FAB_WEIGHT, "WEIGHT", Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
        List fields = new ArrayList();
        Field field = new Field(CTConstants.FAB_WEIGHT, "hhMG", "WEIGHT", DataType.DECIMAL);
        Field bonemassField = new Field("BoneMass", "hhMG", "BONEMASS", DataType.DECIMAL);
        Field bodyFatField = new Field("Bodyfat", "hhMG", "BODYFAT", DataType.DECIMAL);
        Field bodyWaterField = new Field("Bodywater", "hhMG", "BODYWATER", DataType.DECIMAL);
        Field musclemassField = new Field("Musclemass", "hhMG", "MUSCLEMASS", DataType.DECIMAL);
        Field bmiField = new Field(DataBaseConstants.HSRESULT_BMI, "hhMG", DataBaseConstants.HSRESULT_BMI, DataType.DECIMAL);
        fields.add(field);
        fields.add(bonemassField);
        fields.add(bodyFatField);
        fields.add(bodyWaterField);
        fields.add(musclemassField);
        fields.add(bmiField);
        HashMap values = new HashMap();
        if (weight > ((float) 0)) {
            values.put("WEIGHT", String.valueOf(weight));
        }
        if (bonemass > ((float) 0)) {
            values.put("BONEMASS", String.valueOf(bonemass));
        }
        if (bodywater > ((float) 0)) {
            values.put("BODYFAT", String.valueOf(bodyfat));
        }
        if (bodywater > ((float) 0)) {
            values.put("BODYWATER", String.valueOf(bodywater));
        }
        if (musclemass > ((float) 0)) {
            values.put("MUSCLEMASS", String.valueOf(musclemass));
        }
        if (bmi > ((float) 0)) {
            values.put(DataBaseConstants.HSRESULT_BMI, String.valueOf(bmi));
        }
        vitalBlueprint.setFields(fields);
        VitalInputDialogFragment vitalInputFragment = new VitalInputDialogFragment();
        vitalInputFragment.setVitalBlueprint(vitalBlueprint);
        vitalInputFragment.setUserId(userId);
        vitalInputFragment.setValues(values);
        vitalInputFragment.setContextId(contextId);
        vitalInputFragment.setContextType(contextType);
        vitalInputFragment.setSaveCallback(onSaveCallback);
        vitalInputFragment.setVitalRepository(PatientApplication.Companion.getVitalsRepository());
        FragmentTransaction dialogTransaction = fragmentManager.beginTransaction();
        if (dialogTransaction != null) {
            dialogTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        if (dialogTransaction != null) {
            FragmentTransaction add = dialogTransaction.add(16908290, (Fragment) vitalInputFragment);
            if (add != null) {
                add = add.addToBackStack(null);
                if (add != null) {
                    add.commit();
                }
            }
        }
    }
}
