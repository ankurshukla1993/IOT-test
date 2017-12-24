package org.joda.time.chrono;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;

public final class IslamicChronology extends BasicChronology {
    public static final int AH = 1;
    private static final int CYCLE = 30;
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AH");
    private static final IslamicChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    public static final LeapYearPatternType LEAP_YEAR_15_BASED = new LeapYearPatternType(0, 623158436);
    public static final LeapYearPatternType LEAP_YEAR_16_BASED = new LeapYearPatternType(1, 623191204);
    public static final LeapYearPatternType LEAP_YEAR_HABASH_AL_HASIB = new LeapYearPatternType(3, 153692453);
    public static final LeapYearPatternType LEAP_YEAR_INDIAN = new LeapYearPatternType(2, 690562340);
    private static final int LONG_MONTH_LENGTH = 30;
    private static final int MAX_YEAR = 292271022;
    private static final long MILLIS_PER_CYCLE = 918518400000L;
    private static final long MILLIS_PER_LONG_MONTH = 2592000000L;
    private static final long MILLIS_PER_LONG_YEAR = 30672000000L;
    private static final long MILLIS_PER_MONTH = 2551440384L;
    private static final long MILLIS_PER_MONTH_PAIR = 5097600000L;
    private static final long MILLIS_PER_SHORT_YEAR = 30585600000L;
    private static final long MILLIS_PER_YEAR = 30617280288L;
    private static final long MILLIS_YEAR_1 = -42521587200000L;
    private static final int MIN_YEAR = -292269337;
    private static final int MONTH_PAIR_LENGTH = 59;
    private static final int SHORT_MONTH_LENGTH = 29;
    private static final ConcurrentHashMap<DateTimeZone, IslamicChronology[]> cCache = new ConcurrentHashMap();
    private static final long serialVersionUID = -3663823829888L;
    private final LeapYearPatternType iLeapYears;

    public static class LeapYearPatternType implements Serializable {
        private static final long serialVersionUID = 26581275372698L;
        final byte index;
        final int pattern;

        LeapYearPatternType(int i, int i2) {
            this.index = (byte) i;
            this.pattern = i2;
        }

        boolean isLeapYear(int i) {
            if (((1 << (i % 30)) & this.pattern) > 0) {
                return true;
            }
            return false;
        }

        private Object readResolve() {
            switch (this.index) {
                case (byte) 0:
                    return IslamicChronology.LEAP_YEAR_15_BASED;
                case (byte) 1:
                    return IslamicChronology.LEAP_YEAR_16_BASED;
                case (byte) 2:
                    return IslamicChronology.LEAP_YEAR_INDIAN;
                case (byte) 3:
                    return IslamicChronology.LEAP_YEAR_HABASH_AL_HASIB;
                default:
                    return this;
            }
        }

        public boolean equals(Object obj) {
            if ((obj instanceof LeapYearPatternType) && this.index == ((LeapYearPatternType) obj).index) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.index;
        }
    }

    public static IslamicChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static IslamicChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), LEAP_YEAR_16_BASED);
    }

    public static IslamicChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, LEAP_YEAR_16_BASED);
    }

    public static IslamicChronology getInstance(DateTimeZone dateTimeZone, LeapYearPatternType leapYearPatternType) {
        IslamicChronology[] islamicChronologyArr;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        IslamicChronology[] islamicChronologyArr2 = (IslamicChronology[]) cCache.get(dateTimeZone);
        if (islamicChronologyArr2 == null) {
            Object obj = new IslamicChronology[4];
            islamicChronologyArr2 = (IslamicChronology[]) cCache.putIfAbsent(dateTimeZone, obj);
            if (islamicChronologyArr2 != null) {
                islamicChronologyArr = islamicChronologyArr2;
            } else {
                Object obj2 = obj;
            }
        } else {
            islamicChronologyArr = islamicChronologyArr2;
        }
        IslamicChronology islamicChronology = islamicChronologyArr[leapYearPatternType.index];
        if (islamicChronology == null) {
            synchronized (islamicChronologyArr) {
                islamicChronology = islamicChronologyArr[leapYearPatternType.index];
                if (islamicChronology == null) {
                    if (dateTimeZone == DateTimeZone.UTC) {
                        Chronology islamicChronology2 = new IslamicChronology(null, null, leapYearPatternType);
                        islamicChronology = new IslamicChronology(LimitChronology.getInstance(islamicChronology2, new DateTime(1, 1, 1, 0, 0, 0, 0, islamicChronology2), null), null, leapYearPatternType);
                    } else {
                        islamicChronology = new IslamicChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, leapYearPatternType), dateTimeZone), null, leapYearPatternType);
                    }
                    islamicChronologyArr[leapYearPatternType.index] = islamicChronology;
                }
            }
        }
        return islamicChronology;
    }

    IslamicChronology(Chronology chronology, Object obj, LeapYearPatternType leapYearPatternType) {
        super(chronology, obj, 4);
        this.iLeapYears = leapYearPatternType;
    }

    private Object readResolve() {
        Chronology base = getBase();
        return base == null ? getInstanceUTC() : getInstance(base.getZone());
    }

    public LeapYearPatternType getLeapYearPatternType() {
        return this.iLeapYears;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IslamicChronology)) {
            return false;
        }
        boolean z = getLeapYearPatternType().index == ((IslamicChronology) obj).getLeapYearPatternType().index && super.equals(obj);
        return z;
    }

    public int hashCode() {
        return (super.hashCode() * 13) + getLeapYearPatternType().hashCode();
    }

    int getYear(long j) {
        long j2 = j - MILLIS_YEAR_1;
        long j3 = j2 % MILLIS_PER_CYCLE;
        int i = (int) ((30 * (j2 / MILLIS_PER_CYCLE)) + 1);
        j2 = isLeapYear(i) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR;
        while (j3 >= j2) {
            j3 -= j2;
            i++;
            j2 = isLeapYear(i) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR;
        }
        return i;
    }

    long setYear(long j, int i) {
        int dayOfYear = getDayOfYear(j, getYear(j));
        int millisOfDay = getMillisOfDay(j);
        if (dayOfYear > 354 && !isLeapYear(i)) {
            dayOfYear--;
        }
        return ((long) millisOfDay) + getYearMonthDayMillis(i, 1, dayOfYear);
    }

    long getYearDifference(long j, long j2) {
        int year = getYear(j);
        int year2 = getYear(j2);
        year -= year2;
        if (j - getYearMillis(year) < j2 - getYearMillis(year2)) {
            year--;
        }
        return (long) year;
    }

    long getTotalMillisByYearMonth(int i, int i2) {
        int i3 = i2 - 1;
        if (i3 % 2 == 1) {
            return (((long) (i3 / 2)) * MILLIS_PER_MONTH_PAIR) + MILLIS_PER_LONG_MONTH;
        }
        return ((long) (i3 / 2)) * MILLIS_PER_MONTH_PAIR;
    }

    int getDayOfMonth(long j) {
        int dayOfYear = getDayOfYear(j) - 1;
        if (dayOfYear == 354) {
            return 30;
        }
        return ((dayOfYear % 59) % 30) + 1;
    }

    boolean isLeapYear(int i) {
        return this.iLeapYears.isLeapYear(i);
    }

    int getDaysInYearMax() {
        return 355;
    }

    int getDaysInYear(int i) {
        return isLeapYear(i) ? 355 : 354;
    }

    int getDaysInYearMonth(int i, int i2) {
        if ((i2 == 12 && isLeapYear(i)) || (i2 - 1) % 2 == 0) {
            return 30;
        }
        return 29;
    }

    int getDaysInMonthMax() {
        return 30;
    }

    int getDaysInMonthMax(int i) {
        if (i == 12 || (i - 1) % 2 == 0) {
            return 30;
        }
        return 29;
    }

    int getMonthOfYear(long j, int i) {
        int yearMillis = (int) ((j - getYearMillis(i)) / 86400000);
        if (yearMillis == 354) {
            return 12;
        }
        return ((yearMillis * 2) / 59) + 1;
    }

    long getAverageMillisPerYear() {
        return MILLIS_PER_YEAR;
    }

    long getAverageMillisPerYearDividedByTwo() {
        return 15308640144L;
    }

    long getAverageMillisPerMonth() {
        return MILLIS_PER_MONTH;
    }

    long calculateFirstDayOfYearMillis(int i) {
        if (i > MAX_YEAR) {
            throw new ArithmeticException("Year is too large: " + i + " > " + MAX_YEAR);
        } else if (i < MIN_YEAR) {
            throw new ArithmeticException("Year is too small: " + i + " < " + MIN_YEAR);
        } else {
            int i2 = i - 1;
            long j = (((long) (i2 / 30)) * MILLIS_PER_CYCLE) + MILLIS_YEAR_1;
            for (int i3 = 1; i3 < (i2 % 30) + 1; i3++) {
                j += isLeapYear(i3) ? MILLIS_PER_LONG_YEAR : MILLIS_PER_SHORT_YEAR;
            }
            return j;
        }
    }

    int getMinYear() {
        return 1;
    }

    int getMaxYear() {
        return MAX_YEAR;
    }

    long getApproxMillisAtEpochDividedByTwo() {
        return 21260793600000L;
    }

    protected void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.era = ERA_FIELD;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 12);
            fields.months = fields.monthOfYear.getDurationField();
        }
    }
}
