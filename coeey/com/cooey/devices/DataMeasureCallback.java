package com.cooey.devices;

import java.util.Calendar;

public interface DataMeasureCallback {
    void onComplete(float f, Calendar calendar, String str);

    void onMeasuring(int i, int i2);
}
