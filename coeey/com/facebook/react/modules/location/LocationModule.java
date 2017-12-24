package com.facebook.react.modules.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.SystemClock;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import javax.annotation.Nullable;

@ReactModule(name = "LocationObserver")
public class LocationModule extends ReactContextBaseJavaModule {
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener = new 1(this);
    @Nullable
    private String mWatchedProvider;

    public LocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "LocationObserver";
    }

    @ReactMethod
    public void getCurrentPosition(ReadableMap options, Callback success, Callback error) {
        LocationOptions locationOptions = LocationOptions.access$300(options);
        try {
            LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService(Param.LOCATION);
            String provider = getValidProvider(locationManager, LocationOptions.access$400(locationOptions));
            if (provider == null) {
                error.invoke(new Object[]{"No available location provider."});
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            if (location == null || ((double) (SystemClock.currentTimeMillis() - location.getTime())) >= LocationOptions.access$500(locationOptions)) {
                new SingleUpdateRequest(locationManager, provider, LocationOptions.access$600(locationOptions), success, error, null).invoke();
                return;
            }
            success.invoke(new Object[]{locationToMap(location)});
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(ReadableMap options) {
        if (!"gps".equals(this.mWatchedProvider)) {
            LocationOptions locationOptions = LocationOptions.access$300(options);
            try {
                LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService(Param.LOCATION);
                String provider = getValidProvider(locationManager, LocationOptions.access$400(locationOptions));
                if (provider == null) {
                    emitError(PositionError.PERMISSION_DENIED, "No location provider available.");
                    return;
                }
                if (!provider.equals(this.mWatchedProvider)) {
                    locationManager.removeUpdates(this.mLocationListener);
                    locationManager.requestLocationUpdates(provider, 1000, LocationOptions.access$800(locationOptions), this.mLocationListener);
                }
                this.mWatchedProvider = provider;
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService(Param.LOCATION)).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }

    @Nullable
    private static String getValidProvider(LocationManager locationManager, boolean highAccuracy) {
        String provider = highAccuracy ? "gps" : "network";
        if (!locationManager.isProviderEnabled(provider)) {
            provider = provider.equals("gps") ? "network" : "gps";
            if (!locationManager.isProviderEnabled(provider)) {
                return null;
            }
        }
        return provider;
    }

    private static WritableMap locationToMap(Location location) {
        WritableMap map = Arguments.createMap();
        WritableMap coords = Arguments.createMap();
        coords.putDouble("latitude", location.getLatitude());
        coords.putDouble("longitude", location.getLongitude());
        coords.putDouble("altitude", location.getAltitude());
        coords.putDouble("accuracy", (double) location.getAccuracy());
        coords.putDouble("heading", (double) location.getBearing());
        coords.putDouble("speed", (double) location.getSpeed());
        map.putMap("coords", coords);
        map.putDouble(AppMeasurement$Param.TIMESTAMP, (double) location.getTime());
        if (VERSION.SDK_INT >= 18) {
            map.putBoolean("mocked", location.isFromMockProvider());
        }
        return map;
    }

    private void emitError(int code, String message) {
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("geolocationError", PositionError.buildError(code, message));
    }

    private static void throwLocationPermissionMissing(SecurityException e) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", e);
    }
}
