package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.SkipDateTimeField;

public final class CopticChronology extends BasicFixedMonthChronology {
    public static final int AM = 1;
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("AM");
    private static final CopticChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final int MAX_YEAR = 292272708;
    private static final int MIN_YEAR = -292269337;
    private static final ConcurrentHashMap<DateTimeZone, CopticChronology[]> cCache = new ConcurrentHashMap();
    private static final long serialVersionUID = -5972804258688333942L;

    public static CopticChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static CopticChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static CopticChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static CopticChronology getInstance(DateTimeZone dateTimeZone, int i) {
        CopticChronology[] copticChronologyArr;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        CopticChronology[] copticChronologyArr2 = (CopticChronology[]) cCache.get(dateTimeZone);
        if (copticChronologyArr2 == null) {
            Object obj = new CopticChronology[7];
            copticChronologyArr2 = (CopticChronology[]) cCache.putIfAbsent(dateTimeZone, obj);
            if (copticChronologyArr2 != null) {
                copticChronologyArr = copticChronologyArr2;
            } else {
                Object obj2 = obj;
            }
        } else {
            copticChronologyArr = copticChronologyArr2;
        }
        try {
            CopticChronology copticChronology = copticChronologyArr[i - 1];
            if (copticChronology == null) {
                synchronized (copticChronologyArr) {
                    copticChronology = copticChronologyArr[i - 1];
                    if (copticChronology == null) {
                        if (dateTimeZone == DateTimeZone.UTC) {
                            Chronology copticChronology2 = new CopticChronology(null, null, i);
                            copticChronology = new CopticChronology(LimitChronology.getInstance(copticChronology2, new DateTime(1, 1, 1, 0, 0, 0, 0, copticChronology2), null), null, i);
                        } else {
                            copticChronology = new CopticChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                        }
                        copticChronologyArr[i - 1] = copticChronology;
                    }
                }
            }
            return copticChronology;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    CopticChronology(Chronology chronology, Object obj, int i) {
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

    boolean isLeapDay(long j) {
        return dayOfMonth().get(j) == 6 && monthOfYear().isLeap(j);
    }

    long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1687;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            i2 = i3 >> 2;
            if (!isLeapYear(i)) {
                i2++;
            }
        }
        return ((((long) i2) + (((long) i3) * 365)) * 86400000) + 21859200000L;
    }

    int getMinYear() {
        return MIN_YEAR;
    }

    int getMaxYear() {
        return MAX_YEAR;
    }

    long getApproxMillisAtEpochDividedByTwo() {
        return 26607895200000L;
    }

    protected void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
            fields.era = ERA_FIELD;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
            fields.months = fields.monthOfYear.getDurationField();
        }
    }
}
