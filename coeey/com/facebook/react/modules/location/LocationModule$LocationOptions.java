package com.facebook.react.modules.location;

import com.facebook.react.bridge.ReadableMap;

class LocationModule$LocationOptions {
    private final float distanceFilter;
    private final boolean highAccuracy;
    private final double maximumAge;
    private final long timeout;

    private LocationModule$LocationOptions(long timeout, double maximumAge, boolean highAccuracy, float distanceFilter) {
        this.timeout = timeout;
        this.maximumAge = maximumAge;
        this.highAccuracy = highAccuracy;
        this.distanceFilter = distanceFilter;
    }

    private static LocationModule$LocationOptions fromReactMap(ReadableMap map) {
        long timeout = map.hasKey("timeout") ? (long) map.getDouble("timeout") : Long.MAX_VALUE;
        double maximumAge = map.hasKey("maximumAge") ? map.getDouble("maximumAge") : Double.POSITIVE_INFINITY;
        boolean highAccuracy = map.hasKey("enableHighAccuracy") && map.getBoolean("enableHighAccuracy");
        return new LocationModule$LocationOptions(timeout, maximumAge, highAccuracy, map.hasKey("distanceFilter") ? (float) map.getDouble("distanceFilter") : 100.0f);
    }
}
