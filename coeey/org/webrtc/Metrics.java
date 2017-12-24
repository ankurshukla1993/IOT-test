package org.webrtc;

import java.util.HashMap;
import java.util.Map;

public class Metrics {
    private static final String TAG = "Metrics";
    public final Map<String, HistogramInfo> map = new HashMap();

    public static class HistogramInfo {
        public final int bucketCount;
        public final int max;
        public final int min;
        public final Map<Integer, Integer> samples = new HashMap();

        public HistogramInfo(int min, int max, int bucketCount) {
            this.min = min;
            this.max = max;
            this.bucketCount = bucketCount;
        }

        public void addSample(int value, int numEvents) {
            this.samples.put(Integer.valueOf(value), Integer.valueOf(numEvents));
        }
    }

    private static native void nativeEnable();

    private static native Metrics nativeGetAndReset();

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    private void add(String name, HistogramInfo info) {
        this.map.put(name, info);
    }

    public static void enable() {
        nativeEnable();
    }

    public static Metrics getAndReset() {
        return nativeGetAndReset();
    }
}
