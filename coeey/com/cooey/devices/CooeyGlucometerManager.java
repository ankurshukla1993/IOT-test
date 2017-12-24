package com.cooey.devices;

import android.content.Context;
import android.util.SparseArray;
import com.dnurse.DnurseDevTestDef.IMeasureDataResultCallback;
import com.dnurse.exttestlib.DnurseDeviceTest;
import java.util.Calendar;

public class CooeyGlucometerManager {
    private final Context context;
    private DnurseDeviceTest dnurseDeviceTest;

    public CooeyGlucometerManager(Context context) {
        this.context = context;
        this.dnurseDeviceTest = new DnurseDeviceTest(context);
    }

    public void startMeasuring(final DataMeasureCallback dataMeasureCallback) {
        this.dnurseDeviceTest.startTest(new IMeasureDataResultCallback() {
            public void onMeasuring(int i, int i1) {
                dataMeasureCallback.onMeasuring(i, i1);
            }

            public void onSuccess(SparseArray sparseArray) {
                Calendar mTime = (Calendar) sparseArray.get(2);
                String mDeviceSN = (String) sparseArray.get(3);
                dataMeasureCallback.onComplete(((Float) sparseArray.get(1)).floatValue(), mTime, mDeviceSN);
            }
        });
    }

    public void stopMeasuring() {
        this.dnurseDeviceTest.stopTest();
    }

    public void resetDevice() {
        this.dnurseDeviceTest.wakeupDevice();
    }
}
