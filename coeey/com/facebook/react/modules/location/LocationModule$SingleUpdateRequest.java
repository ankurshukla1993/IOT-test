package com.facebook.react.modules.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import com.facebook.react.bridge.Callback;

class LocationModule$SingleUpdateRequest {
    private final Callback mError;
    private final Handler mHandler;
    private final LocationListener mLocationListener;
    private final LocationManager mLocationManager;
    private final String mProvider;
    private final Callback mSuccess;
    private final long mTimeout;
    private final Runnable mTimeoutRunnable;
    private boolean mTriggered;

    class C13151 implements Runnable {
        C13151() {
        }

        public void run() {
            synchronized (LocationModule$SingleUpdateRequest.this) {
                if (!LocationModule$SingleUpdateRequest.this.mTriggered) {
                    LocationModule$SingleUpdateRequest.this.mError.invoke(PositionError.buildError(PositionError.TIMEOUT, "Location request timed out"));
                    LocationModule$SingleUpdateRequest.this.mLocationManager.removeUpdates(LocationModule$SingleUpdateRequest.this.mLocationListener);
                    LocationModule$SingleUpdateRequest.this.mTriggered = true;
                }
            }
        }
    }

    class C13162 implements LocationListener {
        C13162() {
        }

        public void onLocationChanged(Location location) {
            synchronized (LocationModule$SingleUpdateRequest.this) {
                if (!LocationModule$SingleUpdateRequest.this.mTriggered) {
                    LocationModule$SingleUpdateRequest.this.mSuccess.invoke(LocationModule.access$000(location));
                    LocationModule$SingleUpdateRequest.this.mHandler.removeCallbacks(LocationModule$SingleUpdateRequest.this.mTimeoutRunnable);
                    LocationModule$SingleUpdateRequest.this.mTriggered = true;
                }
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    }

    private LocationModule$SingleUpdateRequest(LocationManager locationManager, String provider, long timeout, Callback success, Callback error) {
        this.mHandler = new Handler();
        this.mTimeoutRunnable = new C13151();
        this.mLocationListener = new C13162();
        this.mLocationManager = locationManager;
        this.mProvider = provider;
        this.mTimeout = timeout;
        this.mSuccess = success;
        this.mError = error;
    }

    public void invoke() {
        this.mLocationManager.requestSingleUpdate(this.mProvider, this.mLocationListener, null);
        this.mHandler.postDelayed(this.mTimeoutRunnable, this.mTimeout);
    }
}
