package io.realm;

public class DefaultCompactOnLaunchCallback implements CompactOnLaunchCallback {
    public boolean shouldCompact(long totalBytes, long usedBytes) {
        return totalBytes > 52428800 && ((double) usedBytes) / ((double) totalBytes) < 0.5d;
    }
}
