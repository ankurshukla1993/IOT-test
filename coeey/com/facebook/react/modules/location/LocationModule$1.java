package com.facebook.react.modules.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

class LocationModule$1 implements LocationListener {
    final /* synthetic */ LocationModule this$0;

    LocationModule$1(LocationModule this$0) {
        this.this$0 = this$0;
    }

    public void onLocationChanged(Location location) {
        ((RCTDeviceEventEmitter) LocationModule.access$100(this.this$0).getJSModule(RCTDeviceEventEmitter.class)).emit("geolocationDidChange", LocationModule.access$000(location));
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (status == 0) {
            LocationModule.access$200(this.this$0, PositionError.POSITION_UNAVAILABLE, "Provider " + provider + " is out of service.");
        } else if (status == 1) {
            LocationModule.access$200(this.this$0, PositionError.TIMEOUT, "Provider " + provider + " is temporarily unavailable.");
        }
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
