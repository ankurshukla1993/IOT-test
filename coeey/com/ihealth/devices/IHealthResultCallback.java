package com.ihealth.devices;

import android.content.Intent;

public interface IHealthResultCallback {
    void onResult(int i, Intent intent);
}
