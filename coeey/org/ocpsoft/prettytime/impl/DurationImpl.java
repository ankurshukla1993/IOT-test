package org.ocpsoft.prettytime.impl;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeUnit;

public class DurationImpl implements Duration {
    private long delta;
    private long quantity;
    private TimeUnit unit;

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public long getDelta() {
        return this.delta;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }

    public boolean isInPast() {
        return getQuantity() < 0;
    }

    public boolean isInFuture() {
        return !isInPast();
    }

    public long getQuantityRounded(int tolerance) {
        long quantity = Math.abs(getQuantity());
        if (getDelta() == 0 || Math.abs((((double) getDelta()) / ((double) getUnit().getMillisPerUnit())) * 100.0d) <= ((double) tolerance)) {
            return quantity;
        }
        return quantity + 1;
    }
}
