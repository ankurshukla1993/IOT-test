package humanize.time;

import com.google.common.base.MoreObjects;

public class Pace {
    public static final Pace EMPTY = new Pace(0, Accuracy.NONE, TimeMillis.SECOND);
    private final String accuracy;
    private final String timeUnit;
    private final long value;

    public enum Accuracy {
        NONE,
        APROX,
        LESS_THAN
    }

    public Pace(long value, Accuracy accuracy, TimeMillis timeUnit) {
        this.value = value;
        this.accuracy = accuracy.name().toLowerCase();
        this.timeUnit = timeUnit.key();
    }

    public String getAccuracy() {
        return this.accuracy;
    }

    public String getTimeUnit() {
        return this.timeUnit;
    }

    public long getValue() {
        return this.value;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("value", this.value).add("accuracy", this.accuracy).add("timeUnit", this.timeUnit).toString();
    }
}
