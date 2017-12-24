package org.joda.time.format;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public class DateTimeFormatter {
    private final Chronology iChrono;
    private final int iDefaultYear;
    private final Locale iLocale;
    private final boolean iOffsetParsed;
    private final InternalParser iParser;
    private final Integer iPivotYear;
    private final InternalPrinter iPrinter;
    private final DateTimeZone iZone;

    public DateTimeFormatter(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        this(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParser));
    }

    DateTimeFormatter(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iPrinter = internalPrinter;
        this.iParser = internalParser;
        this.iLocale = null;
        this.iOffsetParsed = false;
        this.iChrono = null;
        this.iZone = null;
        this.iPivotYear = null;
        this.iDefaultYear = CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
    }

    private DateTimeFormatter(InternalPrinter internalPrinter, InternalParser internalParser, Locale locale, boolean z, Chronology chronology, DateTimeZone dateTimeZone, Integer num, int i) {
        this.iPrinter = internalPrinter;
        this.iParser = internalParser;
        this.iLocale = locale;
        this.iOffsetParsed = z;
        this.iChrono = chronology;
        this.iZone = dateTimeZone;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    public boolean isPrinter() {
        return this.iPrinter != null;
    }

    public DateTimePrinter getPrinter() {
        return InternalPrinterDateTimePrinter.of(this.iPrinter);
    }

    InternalPrinter getPrinter0() {
        return this.iPrinter;
    }

    public boolean isParser() {
        return this.iParser != null;
    }

    public DateTimeParser getParser() {
        return InternalParserDateTimeParser.of(this.iParser);
    }

    InternalParser getParser0() {
        return this.iParser;
    }

    public DateTimeFormatter withLocale(Locale locale) {
        if (locale == getLocale() || (locale != null && locale.equals(getLocale()))) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, locale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeFormatter withOffsetParsed() {
        return this.iOffsetParsed ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, null, this.iPivotYear, this.iDefaultYear);
    }

    public boolean isOffsetParsed() {
        return this.iOffsetParsed;
    }

    public DateTimeFormatter withChronology(Chronology chronology) {
        if (this.iChrono == chronology) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, chronology, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public Chronology getChronology() {
        return this.iChrono;
    }

    @Deprecated
    public Chronology getChronolgy() {
        return this.iChrono;
    }

    public DateTimeFormatter withZoneUTC() {
        return withZone(DateTimeZone.UTC);
    }

    public DateTimeFormatter withZone(DateTimeZone dateTimeZone) {
        if (this.iZone == dateTimeZone) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, dateTimeZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public DateTimeFormatter withPivotYear(Integer num) {
        if (this.iPivotYear == num || (this.iPivotYear != null && this.iPivotYear.equals(num))) {
            return this;
        }
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, num, this.iDefaultYear);
    }

    public DateTimeFormatter withPivotYear(int i) {
        return withPivotYear(Integer.valueOf(i));
    }

    public Integer getPivotYear() {
        return this.iPivotYear;
    }

    public DateTimeFormatter withDefaultYear(int i) {
        return new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, i);
    }

    public int getDefaultYear() {
        return this.iDefaultYear;
    }

    public void printTo(StringBuffer stringBuffer, ReadableInstant readableInstant) {
        try {
            printTo((Appendable) stringBuffer, readableInstant);
        } catch (IOException e) {
        }
    }

    public void printTo(StringBuilder stringBuilder, ReadableInstant readableInstant) {
        try {
            printTo((Appendable) stringBuilder, readableInstant);
        } catch (IOException e) {
        }
    }

    public void printTo(Writer writer, ReadableInstant readableInstant) throws IOException {
        printTo((Appendable) writer, readableInstant);
    }

    public void printTo(Appendable appendable, ReadableInstant readableInstant) throws IOException {
        printTo(appendable, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }

    public void printTo(StringBuffer stringBuffer, long j) {
        try {
            printTo((Appendable) stringBuffer, j);
        } catch (IOException e) {
        }
    }

    public void printTo(StringBuilder stringBuilder, long j) {
        try {
            printTo((Appendable) stringBuilder, j);
        } catch (IOException e) {
        }
    }

    public void printTo(Writer writer, long j) throws IOException {
        printTo((Appendable) writer, j);
    }

    public void printTo(Appendable appendable, long j) throws IOException {
        printTo(appendable, j, null);
    }

    public void printTo(StringBuffer stringBuffer, ReadablePartial readablePartial) {
        try {
            printTo((Appendable) stringBuffer, readablePartial);
        } catch (IOException e) {
        }
    }

    public void printTo(StringBuilder stringBuilder, ReadablePartial readablePartial) {
        try {
            printTo((Appendable) stringBuilder, readablePartial);
        } catch (IOException e) {
        }
    }

    public void printTo(Writer writer, ReadablePartial readablePartial) throws IOException {
        printTo((Appendable) writer, readablePartial);
    }

    public void printTo(Appendable appendable, ReadablePartial readablePartial) throws IOException {
        InternalPrinter requirePrinter = requirePrinter();
        if (readablePartial == null) {
            throw new IllegalArgumentException("The partial must not be null");
        }
        requirePrinter.printTo(appendable, readablePartial, this.iLocale);
    }

    public String print(ReadableInstant readableInstant) {
        Appendable stringBuilder = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(stringBuilder, readableInstant);
        } catch (IOException e) {
        }
        return stringBuilder.toString();
    }

    public String print(long j) {
        Appendable stringBuilder = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(stringBuilder, j);
        } catch (IOException e) {
        }
        return stringBuilder.toString();
    }

    public String print(ReadablePartial readablePartial) {
        Appendable stringBuilder = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(stringBuilder, readablePartial);
        } catch (IOException e) {
        }
        return stringBuilder.toString();
    }

    private void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
        InternalPrinter requirePrinter = requirePrinter();
        Chronology selectChronology = selectChronology(chronology);
        DateTimeZone zone = selectChronology.getZone();
        int offset = zone.getOffset(j);
        long j2 = ((long) offset) + j;
        if ((j ^ j2) < 0 && (((long) offset) ^ j) >= 0) {
            zone = DateTimeZone.UTC;
            offset = 0;
            j2 = j;
        }
        requirePrinter.printTo(appendable, j2, selectChronology.withUTC(), offset, zone, this.iLocale);
    }

    private InternalPrinter requirePrinter() {
        InternalPrinter internalPrinter = this.iPrinter;
        if (internalPrinter != null) {
            return internalPrinter;
        }
        throw new UnsupportedOperationException("Printing not supported");
    }

    public int parseInto(ReadWritableInstant readWritableInstant, String str, int i) {
        InternalParser requireParser = requireParser();
        if (readWritableInstant == null) {
            throw new IllegalArgumentException("Instant must not be null");
        }
        long millis = readWritableInstant.getMillis();
        Chronology chronology = readWritableInstant.getChronology();
        int i2 = DateTimeUtils.getChronology(chronology).year().get(millis);
        millis += (long) chronology.getZone().getOffset(millis);
        Chronology selectChronology = selectChronology(chronology);
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(millis, selectChronology, this.iLocale, this.iPivotYear, i2);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, i);
        readWritableInstant.setMillis(dateTimeParserBucket.computeMillis(false, str));
        if (this.iOffsetParsed && dateTimeParserBucket.getOffsetInteger() != null) {
            selectChronology = selectChronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
        } else if (dateTimeParserBucket.getZone() != null) {
            selectChronology = selectChronology.withZone(dateTimeParserBucket.getZone());
        }
        readWritableInstant.setChronology(selectChronology);
        if (this.iZone != null) {
            readWritableInstant.setZone(this.iZone);
        }
        return parseInto;
    }

    public long parseMillis(String str) {
        return new DateTimeParserBucket(0, selectChronology(this.iChrono), this.iLocale, this.iPivotYear, this.iDefaultYear).doParseMillis(requireParser(), str);
    }

    public LocalDate parseLocalDate(String str) {
        return parseLocalDateTime(str).toLocalDate();
    }

    public LocalTime parseLocalTime(String str) {
        return parseLocalDateTime(str).toLocalTime();
    }

    public LocalDateTime parseLocalDateTime(String str) {
        InternalParser requireParser = requireParser();
        Chronology withUTC = selectChronology(null).withUTC();
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0, withUTC, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            long computeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (dateTimeParserBucket.getOffsetInteger() != null) {
                withUTC = withUTC.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                withUTC = withUTC.withZone(dateTimeParserBucket.getZone());
            }
            return new LocalDateTime(computeMillis, withUTC);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    public DateTime parseDateTime(String str) {
        InternalParser requireParser = requireParser();
        Chronology selectChronology = selectChronology(null);
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0, selectChronology, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            long computeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (this.iOffsetParsed && dateTimeParserBucket.getOffsetInteger() != null) {
                selectChronology = selectChronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                selectChronology = selectChronology.withZone(dateTimeParserBucket.getZone());
            }
            DateTime dateTime = new DateTime(computeMillis, selectChronology);
            if (this.iZone != null) {
                return dateTime.withZone(this.iZone);
            }
            return dateTime;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    public MutableDateTime parseMutableDateTime(String str) {
        InternalParser requireParser = requireParser();
        Chronology selectChronology = selectChronology(null);
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0, selectChronology, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int parseInto = requireParser.parseInto(dateTimeParserBucket, str, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= str.length()) {
            long computeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (this.iOffsetParsed && dateTimeParserBucket.getOffsetInteger() != null) {
                selectChronology = selectChronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                selectChronology = selectChronology.withZone(dateTimeParserBucket.getZone());
            }
            MutableDateTime mutableDateTime = new MutableDateTime(computeMillis, selectChronology);
            if (this.iZone != null) {
                mutableDateTime.setZone(this.iZone);
            }
            return mutableDateTime;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, parseInto));
    }

    private InternalParser requireParser() {
        InternalParser internalParser = this.iParser;
        if (internalParser != null) {
            return internalParser;
        }
        throw new UnsupportedOperationException("Parsing not supported");
    }

    private Chronology selectChronology(Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        if (this.iChrono != null) {
            chronology2 = this.iChrono;
        }
        if (this.iZone != null) {
            return chronology2.withZone(this.iZone);
        }
        return chronology2;
    }
}
