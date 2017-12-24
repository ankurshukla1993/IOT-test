package com.facebook.imagepipeline.memory;

public class NoOpPoolStatsTracker implements PoolStatsTracker {
    private static NoOpPoolStatsTracker sInstance = null;

    private NoOpPoolStatsTracker() {
    }

    public static synchronized NoOpPoolStatsTracker getInstance() {
        NoOpPoolStatsTracker noOpPoolStatsTracker;
        synchronized (NoOpPoolStatsTracker.class) {
            if (sInstance == null) {
                sInstance = new NoOpPoolStatsTracker();
            }
            noOpPoolStatsTracker = sInstance;
        }
        return noOpPoolStatsTracker;
    }

    public void setBasePool(BasePool basePool) {
    }

    public void onValueReuse(int bucketedSize) {
    }

    public void onSoftCapReached() {
    }

    public void onHardCapReached() {
    }

    public void onAlloc(int size) {
    }

    public void onFree(int sizeInBytes) {
    }

    public void onValueRelease(int sizeInBytes) {
    }
}
