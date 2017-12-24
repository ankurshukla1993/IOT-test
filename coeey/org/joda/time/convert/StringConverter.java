package org.joda.time.convert;

import kotlin.text.Typography;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

class StringConverter extends AbstractConverter implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter {
    static final StringConverter INSTANCE = new StringConverter();

    protected StringConverter() {
    }

    public long getInstantMillis(Object obj, Chronology chronology) {
        return ISODateTimeFormat.dateTimeParser().withChronology(chronology).parseMillis((String) obj);
    }

    public int[] getPartialValues(ReadablePartial readablePartial, Object obj, Chronology chronology, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter.getZone() != null) {
            chronology = chronology.withZone(dateTimeFormatter.getZone());
        }
        return chronology.get(readablePartial, dateTimeFormatter.withChronology(chronology).parseMillis((String) obj));
    }

    public long getDurationMillis(Object obj) {
        int i = 1;
        String str = (String) obj;
        int length = str.length();
        if (length < 4 || !((str.charAt(0) == 'P' || str.charAt(0) == 'p') && ((str.charAt(1) == 'T' || str.charAt(1) == 't') && (str.charAt(length - 1) == 'S' || str.charAt(length - 1) == 's')))) {
            throw new IllegalArgumentException("Invalid format: \"" + str + Typography.quote);
        }
        long parseLong;
        long j;
        String substring = str.substring(2, length - 1);
        length = 0;
        int i2 = 0;
        int i3 = -1;
        while (length < substring.length()) {
            if (substring.charAt(length) < '0' || substring.charAt(length) > '9') {
                if (length == 0 && substring.charAt(0) == '-') {
                    i2 = 1;
                } else {
                    int i4;
                    if (i2 != 0) {
                        i4 = 1;
                    } else {
                        i4 = 0;
                    }
                    if (length > i4 && substring.charAt(length) == '.' && i3 == -1) {
                        i3 = length;
                    } else {
                        throw new IllegalArgumentException("Invalid format: \"" + str + Typography.quote);
                    }
                }
            }
            length++;
        }
        if (i2 == 0) {
            i = 0;
        }
        if (i3 > 0) {
            parseLong = Long.parseLong(substring.substring(i, i3));
            String substring2 = substring.substring(i3 + 1);
            if (substring2.length() != 3) {
                substring2 = (substring2 + "000").substring(0, 3);
            }
            long j2 = parseLong;
            parseLong = (long) Integer.parseInt(substring2);
            j = j2;
        } else if (i2 != 0) {
            j = Long.parseLong(substring.substring(i, substring.length()));
            parseLong = 0;
        } else {
            j = Long.parseLong(substring);
            parseLong = 0;
        }
        if (i2 != 0) {
            return FieldUtils.safeAdd(FieldUtils.safeMultiply(-j, 1000), -parseLong);
        }
        return FieldUtils.safeAdd(FieldUtils.safeMultiply(j, 1000), parseLong);
    }

    public void setInto(ReadWritablePeriod readWritablePeriod, Object obj, Chronology chronology) {
        String str = (String) obj;
        PeriodFormatter standard = ISOPeriodFormat.standard();
        readWritablePeriod.clear();
        int parseInto = standard.parseInto(readWritablePeriod, str, 0);
        if (parseInto < str.length()) {
            if (parseInto < 0) {
                standard.withParseType(readWritablePeriod.getPeriodType()).parseMutablePeriod(str);
            }
            throw new IllegalArgumentException("Invalid format: \"" + str + Typography.quote);
        }
    }

    public void setInto(ReadWritableInterval readWritableInterval, Object obj, Chronology chronology) {
        ReadablePeriod readablePeriod = null;
        String str = (String) obj;
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
        Chronology chronology2;
        long j;
        DateTimeFormatter withChronology = ISODateTimeFormat.dateTimeParser().withChronology(chronology);
        PeriodFormatter standard = ISOPeriodFormat.standard();
        long j2 = 0;
        char charAt = substring.charAt(0);
        if (charAt == 'P' || charAt == 'p') {
            readablePeriod = standard.withParseType(getPeriodType(substring)).parsePeriod(substring);
            chronology2 = null;
        } else {
            DateTime parseDateTime = withChronology.parseDateTime(substring);
            j2 = parseDateTime.getMillis();
            chronology2 = parseDateTime.getChronology();
        }
        char charAt2 = substring2.charAt(0);
        if (charAt2 != 'P' && charAt2 != 'p') {
            DateTime parseDateTime2 = withChronology.parseDateTime(substring2);
            long millis = parseDateTime2.getMillis();
            if (chronology2 == null) {
                chronology2 = parseDateTime2.getChronology();
            }
            if (chronology == null) {
                chronology = chronology2;
            }
            if (readablePeriod != null) {
                j2 = chronology.add(readablePeriod, millis, -1);
                j = millis;
            } else {
                j = millis;
            }
        } else if (readablePeriod != null) {
            throw new IllegalArgumentException("Interval composed of two durations: " + str);
        } else {
            readablePeriod = standard.withParseType(getPeriodType(substring2)).parsePeriod(substring2);
            if (chronology == null) {
                chronology = chronology2;
            }
            j = chronology.add(readablePeriod, j2, 1);
        }
        readWritableInterval.setInterval(j2, j);
        readWritableInterval.setChronology(chronology);
    }

    public Class<?> getSupportedType() {
        return String.class;
    }
}
