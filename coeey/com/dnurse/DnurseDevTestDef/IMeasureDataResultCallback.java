package com.dnurse.DnurseDevTestDef;

import android.util.SparseArray;

public interface IMeasureDataResultCallback {
    void onMeasuring(int i, int i2);

    void onSuccess(SparseArray sparseArray);
}
