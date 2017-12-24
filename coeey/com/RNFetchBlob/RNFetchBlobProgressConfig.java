package com.RNFetchBlob;

public class RNFetchBlobProgressConfig {
    int count = -1;
    public boolean enable = false;
    public int interval = -1;
    long lastTick = 0;
    int tick = 0;
    public ReportType type = ReportType.Download;

    public enum ReportType {
        Upload,
        Download
    }

    RNFetchBlobProgressConfig(boolean report, int interval, int count, ReportType type) {
        this.enable = report;
        this.interval = interval;
        this.type = type;
        this.count = count;
    }

    public boolean shouldReport(float progress) {
        boolean result;
        boolean checkCount = true;
        if (this.count > 0 && progress > 0.0f) {
            checkCount = Math.floor((double) (((float) this.count) * progress)) > ((double) this.tick);
        }
        if (System.currentTimeMillis() - this.lastTick > ((long) this.interval) && this.enable && checkCount) {
            result = true;
        } else {
            result = false;
        }
        if (result) {
            this.tick++;
            this.lastTick = System.currentTimeMillis();
        }
        return result;
    }
}
