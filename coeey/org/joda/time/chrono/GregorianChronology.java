package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;

public final class GregorianChronology extends BasicGJChronology {
    private static final int DAYS_0000_TO_1970 = 719527;
    private static final GregorianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final int MAX_YEAR = 292278993;
    private static final long MILLIS_PER_MONTH = 2629746000L;
    private static final long MILLIS_PER_YEAR = 31556952000L;
    private static final int MIN_YEAR = -292275054;
    private static final ConcurrentHashMap<DateTimeZone, GregorianChronology[]> cCache = new ConcurrentHashMap();
    private static final long serialVersionUID = -861407383323710522L;

    public static GregorianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static GregorianChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        Object obj;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        Object obj2 = (GregorianChronology[]) cCache.get(dateTimeZone);
        if (obj2 == null) {
            obj = new GregorianChronology[7];
            obj2 = (GregorianChronology[]) cCache.putIfAbsent(dateTimeZone, obj);
            if (obj2 != null) {
                obj = obj2;
            }
        } else {
            obj = obj2;
        }
        try {
            GregorianChronology gregorianChronology = obj[i - 1];
            if (gregorianChronology == null) {
                synchronized (obj) {
                    gregorianChronology = obj[i - 1];
                    if (gregorianChronology == null) {
                        if (dateTimeZone == DateTimeZone.UTC) {
                            gregorianChronology = new GregorianChronology(null, null, i);
                        } else {
                            gregorianChronology = new GregorianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                        }
                        obj[i - 1] = gregorianChronology;
                    }
                }
            }
            return gregorianChronology;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    private GregorianChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    private Object readResolve() {
        Chronology base = getBase();
        int minimumDaysInFirstWeek = getMinimumDaysInFirstWeek();
        if (minimumDaysInFirstWeek == 0) {
            minimumDaysInFirstWeek = 4;
        }
        return base == null ? getInstance(DateTimeZone.UTC, minimumDaysInFirstWeek) : getInstance(base.getZone(), minimumDaysInFirstWeek);
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    protected void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
        }
    }

    boolean isLeapYear(int i) {
        return (i & 3) == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    long calculateFirstDayOfYearMillis(int i) {
        int i2 = i / 100;
        if (i < 0) {
            i2 = (((i2 + 3) >> 2) + (((i + 3) >> 2) - i2)) - 1;
        } else {
            i2 = (i2 >> 2) + ((i >> 2) - i2);
            if (isLeapYear(i)) {
                i2--;
            }
        }
        return (((long) (i2 - DAYS_0000_TO_1970)) + (((long) i) * 365)) * 86400000;
    }

    int getMinYear() {
        return MIN_YEAR;
    }

    int getMaxYear() {
        return MAX_YEAR;
    }

    long getAverageMillisPerYear() {
        return MILLIS_PER_YEAR;
    }

    long getAverageMillisPerYearDividedByTwo() {
        return 15778476000L;
    }

    long getAverageMillisPerMonth() {
        return MILLIS_PER_MONTH;
    }

    long getApproxMillisAtEpochDividedByTwo() {
        return 31083597720000L;
    }
}
