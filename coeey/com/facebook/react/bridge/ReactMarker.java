package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
public class ReactMarker {
    @Nullable
    private static MarkerListener sMarkerListener = null;

    public static void setMarkerListener(MarkerListener listener) {
        sMarkerListener = listener;
    }

    @DoNotStrip
    public static void logMarker(String name) {
        if (sMarkerListener != null) {
            sMarkerListener.logMarker(name);
        }
    }
}
