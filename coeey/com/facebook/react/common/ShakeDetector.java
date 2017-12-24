package com.facebook.react.common;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.facebook.infer.annotation.Assertions;
import javax.annotation.Nullable;

public class ShakeDetector implements SensorEventListener {
    private static final int MAGNITUDE_THRESHOLD = 25;
    private static final int MAX_SAMPLES = 25;
    private static final int MIN_TIME_BETWEEN_SAMPLES_MS = 20;
    private static final int PERCENT_OVER_THRESHOLD_FOR_SHAKE = 66;
    private static final int VISIBLE_TIME_RANGE_MS = 500;
    private int mCurrentIndex;
    private long mLastTimestamp;
    @Nullable
    private double[] mMagnitudes;
    @Nullable
    private SensorManager mSensorManager;
    private final ShakeListener mShakeListener;
    @Nullable
    private long[] mTimestamps;

    public ShakeDetector(ShakeListener listener) {
        this.mShakeListener = listener;
    }

    public void start(SensorManager manager) {
        Assertions.assertNotNull(manager);
        Sensor accelerometer = manager.getDefaultSensor(1);
        if (accelerometer != null) {
            this.mSensorManager = manager;
            this.mLastTimestamp = -1;
            this.mCurrentIndex = 0;
            this.mMagnitudes = new double[25];
            this.mTimestamps = new long[25];
            this.mSensorManager.registerListener(this, accelerometer, 2);
        }
    }

    public void stop() {
        if (this.mSensorManager != null) {
            this.mSensorManager.unregisterListener(this);
            this.mSensorManager = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.timestamp - this.mLastTimestamp >= 20) {
            Assertions.assertNotNull(this.mTimestamps);
            Assertions.assertNotNull(this.mMagnitudes);
            float ax = sensorEvent.values[0];
            float ay = sensorEvent.values[1];
            float az = sensorEvent.values[2];
            this.mLastTimestamp = sensorEvent.timestamp;
            this.mTimestamps[this.mCurrentIndex] = sensorEvent.timestamp;
            this.mMagnitudes[this.mCurrentIndex] = Math.sqrt((double) (((ax * ax) + (ay * ay)) + (az * az)));
            maybeDispatchShake(sensorEvent.timestamp);
            this.mCurrentIndex = (this.mCurrentIndex + 1) % 25;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private void maybeDispatchShake(long currentTimestamp) {
        Assertions.assertNotNull(this.mTimestamps);
        Assertions.assertNotNull(this.mMagnitudes);
        int numOverThreshold = 0;
        int total = 0;
        for (int i = 0; i < 25; i++) {
            int index = ((this.mCurrentIndex - i) + 25) % 25;
            if (currentTimestamp - this.mTimestamps[index] < 500) {
                total++;
                if (this.mMagnitudes[index] >= 25.0d) {
                    numOverThreshold++;
                }
            }
        }
        if (((double) numOverThreshold) / ((double) total) > 0.66d) {
            this.mShakeListener.onShake();
        }
    }
}
