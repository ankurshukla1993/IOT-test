package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Interval extends BaseInterval implements ReadableInterval, Serializable {
    private static final long serialVersionUID = 4922451897541386752L;

    public static Interval parse(String str) {
        return new Interval(str);
    }

    public static Interval parseWithOffset(String str) {
        ReadablePeriod readablePeriod = null;
        int indexOf = str.indexOf(47);
        if (indexOf < 0) {
            throw new IllegalArgumentException("Format requires a '/' separator: " + str);
        }
        String substring = str.substring(0, indexOf);
        if (substring.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + str);
        }
        String substring2 = str.substring(indexOf + 1);
        if (substring2.length() <= 0) {
            throw new IllegalArgumentException("Format invalid: " + str);
        }
        ReadableInstant readableInstant;
        DateTimeFormatter withOffsetParsed = ISODateTimeFormat.dateTimeParser().withOffsetParsed();
        PeriodFormatter standard = ISOPeriodFormat.standard();
        char charAt = substring.charAt(0);
        if (charAt == 'P' || charAt == 'p') {
            readableInstant = null;
            readablePeriod = standard.withParseType(PeriodType.standard()).parsePeriod(substring);
        } else {
            readableInstant = withOffsetParsed.parseDateTime(substring);
        }
        char charAt2 = substring2.charAt(0);
        if (charAt2 != 'P' && charAt2 != 'p') {
            ReadableInstant parseDateTime = withOffsetParsed.parseDateTime(substring2);
            if (readablePeriod != null) {
                return new Interval(readablePeriod, parseDateTime);
            }
            return new Interval(readableInstant, parseDateTime);
        } else if (readablePeriod == null) {
            return new Interval(readableInstant, standard.withParseType(PeriodType.standard()).parsePeriod(substring2));
        } else {
            throw new IllegalArgumentException("Interval composed of two durations: " + str);
        }
    }

    public Interval(long j, long j2) {
        super(j, j2, null);
    }

    public Interval(long j, long j2, DateTimeZone dateTimeZone) {
        super(j, j2, ISOChronology.getInstance(dateTimeZone));
    }

    public Interval(long j, long j2, Chronology chronology) {
        super(j, j2, chronology);
    }

    public Interval(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        super(readableInstant, readableInstant2);
    }

    public Interval(ReadableInstant readableInstant, ReadableDuration readableDuration) {
        super(readableInstant, readableDuration);
    }

    public Interval(ReadableDuration readableDuration, ReadableInstant readableInstant) {
        super(readableDuration, readableInstant);
    }

    public Interval(ReadableInstant readableInstant, ReadablePeriod readablePeriod) {
        super(readableInstant, readablePeriod);
    }

    public Interval(ReadablePeriod readablePeriod, ReadableInstant readableInstant) {
        super(readablePeriod, readableInstant);
    }

    public Interval(Object obj) {
        super(obj, null);
    }

    public Interval(Object obj, Chronology chronology) {
        super(obj, chronology);
    }

    public Interval toInterval() {
        return this;
    }

    public Interval overlap(ReadableInterval readableInterval) {
        ReadableInterval readableInterval2 = DateTimeUtils.getReadableInterval(readableInterval);
        if (overlaps(readableInterval2)) {
            return new Interval(Math.max(getStartMillis(), readableInterval2.getStartMillis()), Math.min(getEndMillis(), readableInterval2.getEndMillis()), getChronology());
        }
        return null;
    }

    public Interval gap(ReadableInterval readableInterval) {
        ReadableInterval readableInterval2 = DateTimeUtils.getReadableInterval(readableInterval);
        long startMillis = readableInterval2.getStartMillis();
        long endMillis = readableInterval2.getEndMillis();
        long startMillis2 = getStartMillis();
        long endMillis2 = getEndMillis();
        if (startMillis2 > endMillis) {
            return new Interval(endMillis, startMillis2, getChronology());
        }
        if (startMillis <= endMillis2) {
            return null;
        }
        return new Interval(endMillis2, startMillis, getChronology());
    }

    public boolean abuts(ReadableInterval readableInterval) {
        if (readableInterval == null) {
            long currentTimeMillis = DateTimeUtils.currentTimeMillis();
            if (getStartMillis() == currentTimeMillis || getEndMillis() == currentTimeMillis) {
                return true;
            }
            return false;
        } else if (readableInterval.getEndMillis() == getStartMillis() || getEndMillis() == readableInterval.getStartMillis()) {
            return true;
        } else {
            return false;
        }
    }

    public Interval withChronology(Chronology chronology) {
        return getChronology() == chronology ? this : new Interval(getStartMillis(), getEndMillis(), chronology);
    }

    public Interval withStartMillis(long j) {
        if (j == getStartMillis()) {
            return this;
        }
        return new Interval(j, getEndMillis(), getChronology());
    }

    public Interval withStart(ReadableInstant readableInstant) {
        return withStartMillis(DateTimeUtils.getInstantMillis(readableInstant));
    }

    public Interval withEndMillis(long j) {
        if (j == getEndMillis()) {
            return this;
        }
        return new Interval(getStartMillis(), j, getChronology());
    }

    public Interval withEnd(ReadableInstant readableInstant) {
        return withEndMillis(DateTimeUtils.getInstantMillis(readableInstant));
    }

    public Interval withDurationAfterStart(ReadableDuration readableDuration) {
        long durationMillis = DateTimeUtils.getDurationMillis(readableDuration);
        if (durationMillis == toDurationMillis()) {
            return this;
        }
        Chronology chronology = getChronology();
        long startMillis = getStartMillis();
        return new Interval(startMillis, chronology.add(startMillis, durationMillis, 1), chronology);
    }

    public Interval withDurationBeforeEnd(ReadableDuration readableDuration) {
        long durationMillis = DateTimeUtils.getDurationMillis(readableDuration);
        if (durationMillis == toDurationMillis()) {
            return this;
        }
        Chronology chronology = getChronology();
        long endMillis = getEndMillis();
        return new Interval(chronology.add(endMillis, durationMillis, -1), endMillis, chronology);
    }

    public Interval withPeriodAfterStart(ReadablePeriod readablePeriod) {
        if (readablePeriod == null) {
            return withDurationAfterStart(null);
        }
        Chronology chronology = getChronology();
        long startMillis = getStartMillis();
        return new Interval(startMillis, chronology.add(readablePeriod, startMillis, 1), chronology);
    }

    public Interval withPeriodBeforeEnd(ReadablePeriod readablePeriod) {
        if (readablePeriod == null) {
            return withDurationBeforeEnd(null);
        }
        Chronology chronology = getChronology();
        long endMillis = getEndMillis();
        return new Interval(chronology.add(readablePeriod, endMillis, -1), endMillis, chronology);
    }
}
