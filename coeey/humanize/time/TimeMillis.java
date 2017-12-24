package humanize.time;

public enum TimeMillis {
    SECOND(1000),
    MINUTE(60000),
    HOUR(3600000),
    DAY(86400000),
    WEEK(604800000),
    MONTH(2628000000L);
    
    private long millis;

    private TimeMillis(long millis) {
        this.millis = millis;
    }

    public String key() {
        return name().toLowerCase();
    }

    public long millis() {
        return this.millis;
    }
}
