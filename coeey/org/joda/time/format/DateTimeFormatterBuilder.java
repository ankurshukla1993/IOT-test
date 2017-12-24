package org.joda.time.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.MutableDateTime$Property;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

public class DateTimeFormatterBuilder {
    private ArrayList<Object> iElementPairs = new ArrayList();
    private Object iFormatter;

    static class CharacterLiteral implements InternalPrinter, InternalParser {
        private final char iValue;

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        public int estimatePrintedLength() {
            return 1;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public int estimateParsedLength() {
            return 1;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            if (i >= charSequence.length()) {
                return i ^ -1;
            }
            char charAt = charSequence.charAt(i);
            char c = this.iValue;
            if (charAt != c) {
                charAt = Character.toUpperCase(charAt);
                c = Character.toUpperCase(c);
                if (!(charAt == c || Character.toLowerCase(charAt) == Character.toLowerCase(c))) {
                    return i ^ -1;
                }
            }
            return i + 1;
        }
    }

    static class Composite implements InternalPrinter, InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final InternalPrinter[] iPrinters;

        Composite(List<Object> list) {
            int i;
            int i2;
            int i3 = 0;
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            } else {
                int size = arrayList.size();
                this.iPrinters = new InternalPrinter[size];
                i = 0;
                for (i2 = 0; i2 < size; i2++) {
                    InternalPrinter internalPrinter = (InternalPrinter) arrayList.get(i2);
                    i += internalPrinter.estimatePrintedLength();
                    this.iPrinters[i2] = internalPrinter;
                }
                this.iPrintedLengthEstimate = i;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
                return;
            }
            i = arrayList2.size();
            this.iParsers = new InternalParser[i];
            i2 = 0;
            while (i3 < i) {
                InternalParser internalParser = (InternalParser) arrayList2.get(i3);
                i2 += internalParser.estimateParsedLength();
                this.iParsers[i3] = internalParser;
                i3++;
            }
            this.iParsedLengthEstimate = i2;
        }

        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            Locale locale2;
            if (locale == null) {
                locale2 = Locale.getDefault();
            } else {
                locale2 = locale;
            }
            for (InternalPrinter printTo : internalPrinterArr) {
                printTo.printTo(appendable, j, chronology, i, dateTimeZone, locale2);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            for (InternalPrinter printTo : internalPrinterArr) {
                printTo.printTo(appendable, readablePartial, locale);
            }
        }

        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            InternalParser[] internalParserArr = this.iParsers;
            if (internalParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = internalParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = internalParserArr[i2].parseInto(dateTimeParserBucket, charSequence, i);
            }
            return i;
        }

        boolean isPrinter() {
            return this.iPrinters != null;
        }

        boolean isParser() {
            return this.iParsers != null;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                obj = list.get(i + 1);
                if (obj instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj).iParsers);
                } else {
                    list3.add(obj);
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object add : objArr) {
                    list.add(add);
                }
            }
        }
    }

    static abstract class NumberFormatter implements InternalPrinter, InternalParser {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Object obj;
            int i2 = 0;
            Object obj2 = null;
            int min = Math.min(this.iMaxParsedDigits, charSequence.length() - i);
            Object obj3 = null;
            while (i2 < min) {
                char charAt = charSequence.charAt(i + i2);
                if (i2 != 0 || ((charAt != '-' && charAt != '+') || !this.iSigned)) {
                    if (charAt < '0') {
                        break;
                    } else if (charAt > '9') {
                        obj = obj2;
                        break;
                    } else {
                        i2++;
                    }
                } else {
                    obj2 = charAt == '-' ? 1 : null;
                    if (charAt == '+') {
                        obj3 = 1;
                    } else {
                        obj3 = null;
                    }
                    if (i2 + 1 >= min) {
                        break;
                    }
                    charAt = charSequence.charAt((i + i2) + 1);
                    if (charAt < '0') {
                        break;
                    } else if (charAt > '9') {
                        obj = obj2;
                        break;
                    } else {
                        i2++;
                        min = Math.min(min + 1, charSequence.length() - i);
                    }
                }
            }
            obj = obj2;
            if (i2 == 0) {
                return i ^ -1;
            }
            int i3;
            int i4;
            if (i2 < 9) {
                if (obj == null && obj3 == null) {
                    i3 = i;
                } else {
                    i3 = i + 1;
                }
                i4 = i3 + 1;
                try {
                    int charAt2 = charSequence.charAt(i3) - 48;
                    i3 = i + i2;
                    int i5 = i4;
                    i4 = charAt2;
                    for (charAt2 = i5; charAt2 < i3; charAt2++) {
                        i4 = (charSequence.charAt(charAt2) + ((i4 << 3) + (i4 << 1))) - 48;
                    }
                    if (obj != null) {
                        i4 = -i4;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return i ^ -1;
                }
            } else if (obj3 != null) {
                i3 = i + i2;
                i4 = Integer.parseInt(charSequence.subSequence(i + 1, i3).toString());
            } else {
                i3 = i + i2;
                i4 = Integer.parseInt(charSequence.subSequence(i, i3).toString());
            }
            dateTimeParserBucket.saveField(this.iFieldType, i4);
            return i3;
        }
    }

    static class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendPaddedInteger(appendable, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException e) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(appendable, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                    return;
                } catch (RuntimeException e) {
                    DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
                    return;
                }
            }
            DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
        }
    }

    static class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int parseInto = super.parseInto(dateTimeParserBucket, charSequence, i);
            if (parseInto < 0) {
                return parseInto;
            }
            int i2 = this.iMaxParsedDigits + i;
            if (parseInto == i2) {
                return parseInto;
            }
            if (this.iSigned) {
                char charAt = charSequence.charAt(i);
                if (charAt == '-' || charAt == '+') {
                    i2++;
                }
            }
            if (parseInto > i2) {
                return (i2 + 1) ^ -1;
            }
            if (parseInto < i2) {
                return parseInto ^ -1;
            }
            return parseInto;
        }
    }

    static class Fraction implements InternalPrinter, InternalParser {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            if (i2 > 18) {
                i2 = 18;
            }
            this.iMinDigits = i;
            this.iMaxDigits = i2;
        }

        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            printTo(appendable, j, chronology);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            printTo(appendable, readablePartial.getChronology().set(readablePartial, 0), readablePartial.getChronology());
        }

        protected void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long remainder = field.remainder(j);
                if (remainder == 0) {
                    while (true) {
                        i--;
                        if (i >= 0) {
                            appendable.append('0');
                        } else {
                            return;
                        }
                    }
                }
                CharSequence num;
                long[] fractionData = getFractionData(remainder, field);
                remainder = fractionData[0];
                int i2 = (int) fractionData[1];
                if ((2147483647L & remainder) == remainder) {
                    num = Integer.toString((int) remainder);
                } else {
                    num = Long.toString(remainder);
                }
                int length = num.length();
                while (length < i2) {
                    appendable.append('0');
                    i--;
                    i2--;
                }
                if (i < i2) {
                    while (i < i2 && length > 1 && num.charAt(length - 1) == '0') {
                        i2--;
                        length--;
                    }
                    if (length < num.length()) {
                        for (i2 = 0; i2 < length; i2++) {
                            appendable.append(num.charAt(i2));
                        }
                        return;
                    }
                }
                appendable.append(num);
            } catch (RuntimeException e) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, i);
            }
        }

        private long[] getFractionData(long j, DateTimeField dateTimeField) {
            long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int i = this.iMaxDigits;
            while (true) {
                long j2;
                switch (i) {
                    case 1:
                        j2 = 10;
                        break;
                    case 2:
                        j2 = 100;
                        break;
                    case 3:
                        j2 = 1000;
                        break;
                    case 4:
                        j2 = 10000;
                        break;
                    case 5:
                        j2 = 100000;
                        break;
                    case 6:
                        j2 = 1000000;
                        break;
                    case 7:
                        j2 = 10000000;
                        break;
                    case 8:
                        j2 = 100000000;
                        break;
                    case 9:
                        j2 = 1000000000;
                        break;
                    case 10:
                        j2 = 10000000000L;
                        break;
                    case 11:
                        j2 = 100000000000L;
                        break;
                    case 12:
                        j2 = 1000000000000L;
                        break;
                    case 13:
                        j2 = 10000000000000L;
                        break;
                    case 14:
                        j2 = 100000000000000L;
                        break;
                    case 15:
                        j2 = 1000000000000000L;
                        break;
                    case 16:
                        j2 = 10000000000000000L;
                        break;
                    case 17:
                        j2 = 100000000000000000L;
                        break;
                    case 18:
                        j2 = 1000000000000000000L;
                        break;
                    default:
                        j2 = 1;
                        break;
                }
                if ((unitMillis * j2) / j2 == unitMillis) {
                    return new long[]{(j2 * j) / unitMillis, (long) i};
                }
                i--;
            }
        }

        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min = Math.min(this.iMaxDigits, charSequence.length() - i);
            long j = 0;
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            int i2 = 0;
            while (i2 < min) {
                char charAt = charSequence.charAt(i + i2);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += ((long) (charAt - 48)) * unitMillis;
            }
            unitMillis = j / 10;
            if (i2 == 0) {
                return i ^ -1;
            }
            if (unitMillis > 2147483647L) {
                return i ^ -1;
            }
            dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) unitMillis);
            return i2 + i;
        }
    }

    static class MatchingParser implements InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;

        MatchingParser(InternalParser[] internalParserArr) {
            this.iParsers = internalParserArr;
            int i = 0;
            int length = internalParserArr.length;
            while (true) {
                int i2 = length - 1;
                if (i2 >= 0) {
                    InternalParser internalParser = internalParserArr[i2];
                    if (internalParser != null) {
                        length = internalParser.estimateParsedLength();
                        if (length > i) {
                            i = length;
                            length = i2;
                        }
                    }
                    length = i;
                    i = length;
                    length = i2;
                } else {
                    this.iParsedLengthEstimate = i;
                    return;
                }
            }
        }

        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Object saveState;
            InternalParser[] internalParserArr = this.iParsers;
            int length = internalParserArr.length;
            Object saveState2 = dateTimeParserBucket.saveState();
            Object obj = null;
            int i2 = 0;
            int i3 = i;
            int i4 = i;
            while (i2 < length) {
                InternalParser internalParser = internalParserArr[i2];
                if (internalParser != null) {
                    int i5;
                    int parseInto = internalParser.parseInto(dateTimeParserBucket, charSequence, i);
                    if (parseInto >= i) {
                        if (parseInto > i4) {
                            if (parseInto >= charSequence.length() || i2 + 1 >= length || internalParserArr[i2 + 1] == null) {
                                return parseInto;
                            }
                            saveState = dateTimeParserBucket.saveState();
                            i5 = parseInto;
                        }
                        saveState = obj;
                        i5 = i4;
                    } else {
                        if (parseInto < 0) {
                            int i6 = parseInto ^ -1;
                            if (i6 > i3) {
                                i3 = i6;
                                saveState = obj;
                                i5 = i4;
                            }
                        }
                        saveState = obj;
                        i5 = i4;
                    }
                    dateTimeParserBucket.restoreState(saveState2);
                    i2++;
                    i4 = i5;
                    obj = saveState;
                } else if (i4 <= i) {
                    return i;
                } else {
                    saveState = 1;
                    if (i4 > i && (i4 != i || r1 == null)) {
                        return i3 ^ -1;
                    }
                    if (obj != null) {
                        dateTimeParserBucket.restoreState(obj);
                    }
                    return i4;
                }
            }
            saveState = null;
            if (i4 > i) {
            }
            if (obj != null) {
                dateTimeParserBucket.restoreState(obj);
            }
            return i4;
        }
    }

    static class StringLiteral implements InternalPrinter, InternalParser {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public int estimateParsedLength() {
            return this.iValue.length();
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            if (DateTimeFormatterBuilder.csStartsWithIgnoreCase(charSequence, i, this.iValue)) {
                return this.iValue.length() + i;
            }
            return i ^ -1;
        }
    }

    static class TextField implements InternalPrinter, InternalParser {
        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new ConcurrentHashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                appendable.append(print(j, chronology, locale));
            } catch (RuntimeException e) {
                appendable.append('�');
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            try {
                appendable.append(print(readablePartial, locale));
            } catch (RuntimeException e) {
                appendable.append('�');
            }
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(j, locale);
            }
            return field.getAsText(j, locale);
        }

        private String print(ReadablePartial readablePartial, Locale locale) {
            if (!readablePartial.isSupported(this.iFieldType)) {
                return "�";
            }
            DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
            if (this.iShort) {
                return field.getAsShortText(readablePartial, locale);
            }
            return field.getAsText(readablePartial, locale);
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map map;
            int maximumTextLength;
            Locale locale = dateTimeParserBucket.getLocale();
            Map map2 = (Map) cParseCache.get(locale);
            if (map2 == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                cParseCache.put(locale, concurrentHashMap);
                map = concurrentHashMap;
            } else {
                map = map2;
            }
            Object[] objArr = (Object[]) map.get(this.iFieldType);
            if (objArr == null) {
                Map concurrentHashMap2 = new ConcurrentHashMap(32);
                MutableDateTime$Property property = new MutableDateTime(0, DateTimeZone.UTC).property(this.iFieldType);
                int minimumValueOverall = property.getMinimumValueOverall();
                int maximumValueOverall = property.getMaximumValueOverall();
                if (maximumValueOverall - minimumValueOverall > 32) {
                    return i ^ -1;
                }
                maximumTextLength = property.getMaximumTextLength(locale);
                while (minimumValueOverall <= maximumValueOverall) {
                    property.set(minimumValueOverall);
                    concurrentHashMap2.put(property.getAsShortText(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsShortText(locale).toLowerCase(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsShortText(locale).toUpperCase(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsText(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsText(locale).toLowerCase(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsText(locale).toUpperCase(locale), Boolean.TRUE);
                    minimumValueOverall++;
                }
                if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                    concurrentHashMap2.put("BCE", Boolean.TRUE);
                    concurrentHashMap2.put("bce", Boolean.TRUE);
                    concurrentHashMap2.put("CE", Boolean.TRUE);
                    concurrentHashMap2.put("ce", Boolean.TRUE);
                    maximumTextLength = 3;
                }
                map.put(this.iFieldType, new Object[]{concurrentHashMap2, Integer.valueOf(maximumTextLength)});
                map = concurrentHashMap2;
            } else {
                map = (Map) objArr[0];
                maximumTextLength = ((Integer) objArr[1]).intValue();
            }
            for (maximumTextLength = Math.min(charSequence.length(), maximumTextLength + i); maximumTextLength > i; maximumTextLength--) {
                String obj = charSequence.subSequence(i, maximumTextLength).toString();
                if (map.containsKey(obj)) {
                    dateTimeParserBucket.saveField(this.iFieldType, obj, locale);
                    return maximumTextLength;
                }
            }
            return i ^ -1;
        }
    }

    enum TimeZoneId implements InternalPrinter, InternalParser {
        INSTANCE;
        
        private static final List<String> ALL_IDS = null;
        private static final List<String> BASE_GROUPED_IDS = null;
        private static final Map<String, List<String>> GROUPED_IDS = null;
        static final int MAX_LENGTH = 0;
        static final int MAX_PREFIX_LENGTH = 0;

        static {
            BASE_GROUPED_IDS = new ArrayList();
            ALL_IDS = new ArrayList(DateTimeZone.getAvailableIDs());
            Collections.sort(ALL_IDS);
            GROUPED_IDS = new HashMap();
            int i = 0;
            int i2 = 0;
            for (String str : ALL_IDS) {
                int indexOf = str.indexOf(47);
                if (indexOf >= 0) {
                    if (indexOf < str.length()) {
                        indexOf++;
                    }
                    int max = Math.max(i, indexOf);
                    String substring = str.substring(0, indexOf + 1);
                    String substring2 = str.substring(indexOf);
                    if (!GROUPED_IDS.containsKey(substring)) {
                        GROUPED_IDS.put(substring, new ArrayList());
                    }
                    ((List) GROUPED_IDS.get(substring)).add(substring2);
                    i = max;
                } else {
                    BASE_GROUPED_IDS.add(str);
                }
                i2 = Math.max(i2, str.length());
            }
            MAX_LENGTH = i2;
            MAX_PREFIX_LENGTH = i;
        }

        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int length;
            String str;
            List list;
            String str2;
            List list2 = BASE_GROUPED_IDS;
            int length2 = charSequence.length();
            int min = Math.min(length2, MAX_PREFIX_LENGTH + i);
            String str3 = "";
            for (int i2 = i; i2 < min; i2++) {
                if (charSequence.charAt(i2) == '/') {
                    Object obj;
                    str3 = charSequence.subSequence(i, i2 + 1).toString();
                    length = i + str3.length();
                    if (i2 < length2) {
                        obj = str3 + charSequence.charAt(i2 + 1);
                    } else {
                        str = str3;
                    }
                    list2 = (List) GROUPED_IDS.get(obj);
                    if (list2 == null) {
                        return i ^ -1;
                    }
                    list = list2;
                    str2 = null;
                    min = 0;
                    while (min < list.size()) {
                        str = (String) list.get(min);
                        if (!DateTimeFormatterBuilder.csStartsWith(charSequence, length, str) || (str2 != null && str.length() <= str2.length())) {
                            str = str2;
                        }
                        min++;
                        str2 = str;
                    }
                    if (str2 != null) {
                        return i ^ -1;
                    }
                    dateTimeParserBucket.setZone(DateTimeZone.forID(str3 + str2));
                    return str2.length() + length;
                }
            }
            length = i;
            list = list2;
            str2 = null;
            min = 0;
            while (min < list.size()) {
                str = (String) list.get(min);
                str = str2;
                min++;
                str2 = str;
            }
            if (str2 != null) {
                return i ^ -1;
            }
            dateTimeParserBucket.setZone(DateTimeZone.forID(str3 + str2));
            return str2.length() + length;
        }
    }

    static class TimeZoneName implements InternalPrinter, InternalParser {
        static final int LONG_NAME = 0;
        static final int SHORT_NAME = 1;
        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        TimeZoneName(int i, Map<String, DateTimeZone> map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(print(j - ((long) i), dateTimeZone, locale));
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            switch (this.iType) {
                case 0:
                    return dateTimeZone.getName(j, locale);
                case 1:
                    return dateTimeZone.getShortName(j, locale);
                default:
                    return "";
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map map = this.iParseLookup;
            Map defaultTimeZoneNames = map != null ? map : DateTimeUtils.getDefaultTimeZoneNames();
            String str = null;
            for (String str2 : defaultTimeZoneNames.keySet()) {
                String str22;
                if (!DateTimeFormatterBuilder.csStartsWith(charSequence, i, str22) || (str != null && str22.length() <= str.length())) {
                    str22 = str;
                }
                str = str22;
            }
            if (str == null) {
                return i ^ -1;
            }
            dateTimeParserBucket.setZone((DateTimeZone) defaultTimeZoneNames.get(str));
            return str.length() + i;
        }
    }

    static class TimeZoneOffset implements InternalPrinter, InternalParser {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            int i3 = 4;
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i <= 0 || i2 < i) {
                throw new IllegalArgumentException();
            }
            if (i > 4) {
                i2 = 4;
            } else {
                i3 = i;
            }
            this.iMinFields = i3;
            this.iMaxFields = i2;
        }

        public int estimatePrintedLength() {
            int i = (this.iMinFields + 1) << 1;
            if (this.iShowSeparators) {
                i += this.iMinFields - 1;
            }
            if (this.iZeroOffsetPrintText == null || this.iZeroOffsetPrintText.length() <= i) {
                return i;
            }
            return this.iZeroOffsetPrintText.length();
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            if (dateTimeZone != null) {
                if (i != 0 || this.iZeroOffsetPrintText == null) {
                    if (i >= 0) {
                        appendable.append('+');
                    } else {
                        appendable.append('-');
                        i = -i;
                    }
                    int i2 = i / DateTimeConstants.MILLIS_PER_HOUR;
                    FormatUtils.appendPaddedInteger(appendable, i2, 2);
                    if (this.iMaxFields != 1) {
                        i2 = i - (i2 * DateTimeConstants.MILLIS_PER_HOUR);
                        if (i2 != 0 || this.iMinFields > 1) {
                            int i3 = i2 / DateTimeConstants.MILLIS_PER_MINUTE;
                            if (this.iShowSeparators) {
                                appendable.append(':');
                            }
                            FormatUtils.appendPaddedInteger(appendable, i3, 2);
                            if (this.iMaxFields != 2) {
                                i2 -= i3 * DateTimeConstants.MILLIS_PER_MINUTE;
                                if (i2 != 0 || this.iMinFields > 2) {
                                    i3 = i2 / 1000;
                                    if (this.iShowSeparators) {
                                        appendable.append(':');
                                    }
                                    FormatUtils.appendPaddedInteger(appendable, i3, 2);
                                    if (this.iMaxFields != 3) {
                                        i2 -= i3 * 1000;
                                        if (i2 != 0 || this.iMinFields > 3) {
                                            if (this.iShowSeparators) {
                                                appendable.append('.');
                                            }
                                            FormatUtils.appendPaddedInteger(appendable, i2, 3);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                appendable.append(this.iZeroOffsetPrintText);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r10, java.lang.CharSequence r11, int r12) {
            /*
            r9 = this;
            r5 = 45;
            r4 = 43;
            r2 = 0;
            r1 = 1;
            r8 = 2;
            r0 = r11.length();
            r3 = r0 - r12;
            r0 = r9.iZeroOffsetParseText;
            if (r0 == 0) goto L_0x0023;
        L_0x0011:
            r0 = r9.iZeroOffsetParseText;
            r0 = r0.length();
            if (r0 != 0) goto L_0x0030;
        L_0x0019:
            if (r3 <= 0) goto L_0x0028;
        L_0x001b:
            r0 = r11.charAt(r12);
            if (r0 == r5) goto L_0x0023;
        L_0x0021:
            if (r0 != r4) goto L_0x0028;
        L_0x0023:
            if (r3 > r1) goto L_0x0047;
        L_0x0025:
            r12 = r12 ^ -1;
        L_0x0027:
            return r12;
        L_0x0028:
            r0 = java.lang.Integer.valueOf(r2);
            r10.setOffset(r0);
            goto L_0x0027;
        L_0x0030:
            r0 = r9.iZeroOffsetParseText;
            r0 = org.joda.time.format.DateTimeFormatterBuilder.csStartsWithIgnoreCase(r11, r12, r0);
            if (r0 == 0) goto L_0x0023;
        L_0x0038:
            r0 = java.lang.Integer.valueOf(r2);
            r10.setOffset(r0);
            r0 = r9.iZeroOffsetParseText;
            r0 = r0.length();
            r12 = r12 + r0;
            goto L_0x0027;
        L_0x0047:
            r0 = r11.charAt(r12);
            if (r0 != r5) goto L_0x005b;
        L_0x004d:
            r0 = r1;
        L_0x004e:
            r3 = r3 + -1;
            r4 = r12 + 1;
            r5 = r9.digitCount(r11, r4, r8);
            if (r5 >= r8) goto L_0x0062;
        L_0x0058:
            r12 = r4 ^ -1;
            goto L_0x0027;
        L_0x005b:
            if (r0 != r4) goto L_0x005f;
        L_0x005d:
            r0 = r2;
            goto L_0x004e;
        L_0x005f:
            r12 = r12 ^ -1;
            goto L_0x0027;
        L_0x0062:
            r5 = org.joda.time.format.FormatUtils.parseTwoDigits(r11, r4);
            r6 = 23;
            if (r5 <= r6) goto L_0x006d;
        L_0x006a:
            r12 = r4 ^ -1;
            goto L_0x0027;
        L_0x006d:
            r6 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
            r5 = r5 * r6;
            r3 = r3 + -2;
            r4 = r4 + 2;
            if (r3 > 0) goto L_0x0084;
        L_0x0077:
            r1 = r5;
            r12 = r4;
        L_0x0079:
            if (r0 == 0) goto L_0x0157;
        L_0x007b:
            r0 = -r1;
        L_0x007c:
            r0 = java.lang.Integer.valueOf(r0);
            r10.setOffset(r0);
            goto L_0x0027;
        L_0x0084:
            r6 = r11.charAt(r4);
            r7 = 58;
            if (r6 != r7) goto L_0x009e;
        L_0x008c:
            r2 = r3 + -1;
            r3 = r4 + 1;
            r4 = r3;
            r3 = r2;
            r2 = r1;
        L_0x0093:
            r6 = r9.digitCount(r11, r4, r8);
            if (r6 != 0) goto L_0x00a9;
        L_0x0099:
            if (r2 != 0) goto L_0x00a9;
        L_0x009b:
            r1 = r5;
            r12 = r4;
            goto L_0x0079;
        L_0x009e:
            r7 = 48;
            if (r6 < r7) goto L_0x00a6;
        L_0x00a2:
            r7 = 57;
            if (r6 <= r7) goto L_0x0093;
        L_0x00a6:
            r1 = r5;
            r12 = r4;
            goto L_0x0079;
        L_0x00a9:
            if (r6 >= r8) goto L_0x00af;
        L_0x00ab:
            r12 = r4 ^ -1;
            goto L_0x0027;
        L_0x00af:
            r6 = org.joda.time.format.FormatUtils.parseTwoDigits(r11, r4);
            r7 = 59;
            if (r6 <= r7) goto L_0x00bb;
        L_0x00b7:
            r12 = r4 ^ -1;
            goto L_0x0027;
        L_0x00bb:
            r7 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
            r6 = r6 * r7;
            r5 = r5 + r6;
            r3 = r3 + -2;
            r4 = r4 + 2;
            if (r3 > 0) goto L_0x00c9;
        L_0x00c6:
            r1 = r5;
            r12 = r4;
            goto L_0x0079;
        L_0x00c9:
            if (r2 == 0) goto L_0x00da;
        L_0x00cb:
            r6 = r11.charAt(r4);
            r7 = 58;
            if (r6 == r7) goto L_0x00d6;
        L_0x00d3:
            r1 = r5;
            r12 = r4;
            goto L_0x0079;
        L_0x00d6:
            r3 = r3 + -1;
            r4 = r4 + 1;
        L_0x00da:
            r6 = r9.digitCount(r11, r4, r8);
            if (r6 != 0) goto L_0x00e5;
        L_0x00e0:
            if (r2 != 0) goto L_0x00e5;
        L_0x00e2:
            r1 = r5;
            r12 = r4;
            goto L_0x0079;
        L_0x00e5:
            if (r6 >= r8) goto L_0x00eb;
        L_0x00e7:
            r12 = r4 ^ -1;
            goto L_0x0027;
        L_0x00eb:
            r6 = org.joda.time.format.FormatUtils.parseTwoDigits(r11, r4);
            r7 = 59;
            if (r6 <= r7) goto L_0x00f7;
        L_0x00f3:
            r12 = r4 ^ -1;
            goto L_0x0027;
        L_0x00f7:
            r6 = r6 * 1000;
            r5 = r5 + r6;
            r6 = r3 + -2;
            r3 = r4 + 2;
            if (r6 > 0) goto L_0x0104;
        L_0x0100:
            r1 = r5;
            r12 = r3;
            goto L_0x0079;
        L_0x0104:
            if (r2 == 0) goto L_0x011e;
        L_0x0106:
            r4 = r11.charAt(r3);
            r7 = 46;
            if (r4 == r7) goto L_0x011a;
        L_0x010e:
            r4 = r11.charAt(r3);
            r7 = 44;
            if (r4 == r7) goto L_0x011a;
        L_0x0116:
            r1 = r5;
            r12 = r3;
            goto L_0x0079;
        L_0x011a:
            r4 = r6 + -1;
            r3 = r3 + 1;
        L_0x011e:
            r4 = 3;
            r6 = r9.digitCount(r11, r3, r4);
            if (r6 != 0) goto L_0x012b;
        L_0x0125:
            if (r2 != 0) goto L_0x012b;
        L_0x0127:
            r1 = r5;
            r12 = r3;
            goto L_0x0079;
        L_0x012b:
            if (r6 >= r1) goto L_0x0131;
        L_0x012d:
            r12 = r3 ^ -1;
            goto L_0x0027;
        L_0x0131:
            r4 = r3 + 1;
            r2 = r11.charAt(r3);
            r2 = r2 + -48;
            r2 = r2 * 100;
            r2 = r2 + r5;
            if (r6 <= r1) goto L_0x015d;
        L_0x013e:
            r3 = r4 + 1;
            r1 = r11.charAt(r4);
            r1 = r1 + -48;
            r1 = r1 * 10;
            r1 = r1 + r2;
            if (r6 <= r8) goto L_0x015a;
        L_0x014b:
            r4 = r3 + 1;
            r2 = r11.charAt(r3);
            r2 = r2 + -48;
            r1 = r1 + r2;
            r12 = r4;
            goto L_0x0079;
        L_0x0157:
            r0 = r1;
            goto L_0x007c;
        L_0x015a:
            r12 = r3;
            goto L_0x0079;
        L_0x015d:
            r1 = r2;
            r12 = r4;
            goto L_0x0079;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }

        private int digitCount(CharSequence charSequence, int i, int i2) {
            int i3 = 0;
            for (int min = Math.min(charSequence.length() - i, i2); min > 0; min--) {
                char charAt = charSequence.charAt(i + i3);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }
    }

    static class TwoDigitYear implements InternalPrinter, InternalParser {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            char charAt;
            int charAt2;
            int i3 = 0;
            int length = charSequence.length() - i;
            if (this.iLenientParse) {
                i2 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = length;
                while (i2 < i6) {
                    charAt = charSequence.charAt(i + i2);
                    if (i2 != 0 || (charAt != '-' && charAt != '+')) {
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i2++;
                    } else {
                        if (charAt == '-') {
                            length = 1;
                        } else {
                            length = 0;
                        }
                        if (length != 0) {
                            i2++;
                            i4 = length;
                            i5 = 1;
                        } else {
                            i++;
                            i5 = 1;
                            i6--;
                            i4 = length;
                        }
                    }
                }
                if (i2 == 0) {
                    return i ^ -1;
                }
                if (!(i5 == 0 && i2 == 2)) {
                    if (i2 >= 9) {
                        length = i + i2;
                        i3 = Integer.parseInt(charSequence.subSequence(i, length).toString());
                    } else {
                        if (i4 != 0) {
                            length = i + 1;
                        } else {
                            length = i;
                        }
                        i3 = length + 1;
                        try {
                            charAt2 = charSequence.charAt(length) - 48;
                            length = i + i2;
                            int i7 = i3;
                            i3 = charAt2;
                            charAt2 = i7;
                            while (charAt2 < length) {
                                i2 = (i3 << 3) + (i3 << 1);
                                i7 = charAt2 + 1;
                                i3 = (charSequence.charAt(charAt2) + i2) - 48;
                                charAt2 = i7;
                            }
                            if (i4 != 0) {
                                i3 = -i3;
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            return i ^ -1;
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i3);
                    return length;
                }
            } else if (Math.min(2, length) < 2) {
                return i ^ -1;
            }
            charAt = charSequence.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i ^ -1;
            }
            length = charAt - 48;
            char charAt3 = charSequence.charAt(i + 1);
            if (charAt3 < '0' || charAt3 > '9') {
                return i ^ -1;
            }
            charAt2 = (((length << 1) + (length << 3)) + charAt3) - 48;
            length = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                length = dateTimeParserBucket.getPivotYear().intValue();
            }
            i2 = length - 50;
            if (i2 >= 0) {
                length = i2 % 100;
            } else {
                length = ((i2 + 1) % 100) + 99;
            }
            if (charAt2 < length) {
                i3 = 100;
            }
            dateTimeParserBucket.saveField(this.iType, ((i3 + i2) - length) + charAt2);
            return i + 2;
        }

        public int estimatePrintedLength() {
            return 2;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear < 0) {
                appendable.append('�');
                appendable.append('�');
                return;
            }
            FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException e) {
                return -1;
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                appendable.append('�');
                appendable.append('�');
                return;
            }
            FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
        }

        private int getTwoDigitYear(ReadablePartial readablePartial) {
            if (readablePartial.isSupported(this.iType)) {
                try {
                    int i = readablePartial.get(this.iType);
                    if (i < 0) {
                        i = -i;
                    }
                    return i % 100;
                } catch (RuntimeException e) {
                }
            }
            return -1;
        }
    }

    static class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendUnpaddedInteger(appendable, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException e) {
                appendable.append('�');
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(appendable, readablePartial.get(this.iFieldType));
                    return;
                } catch (RuntimeException e) {
                    appendable.append('�');
                    return;
                }
            }
            appendable.append('�');
        }
    }

    public DateTimeFormatter toFormatter() {
        InternalPrinter internalPrinter;
        InternalParser internalParser;
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            internalPrinter = (InternalPrinter) formatter;
        } else {
            internalPrinter = null;
        }
        if (isParser(formatter)) {
            internalParser = (InternalParser) formatter;
        } else {
            internalParser = null;
        }
        if (internalPrinter != null || internalParser != null) {
            return new DateTimeFormatter(internalPrinter, internalParser);
        }
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }

    public DateTimePrinter toPrinter() {
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            return InternalPrinterDateTimePrinter.of((InternalPrinter) formatter);
        }
        throw new UnsupportedOperationException("Printing is not supported");
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return InternalParserDateTimeParser.of((InternalParser) formatter);
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public boolean canBuildFormatter() {
        return isFormatter(getFormatter());
    }

    public boolean canBuildPrinter() {
        return isPrinter(getFormatter());
    }

    public boolean canBuildParser() {
        return isParser(getFormatter());
    }

    public void clear() {
        this.iFormatter = null;
        this.iElementPairs.clear();
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter != null) {
            return append0(dateTimeFormatter.getPrinter0(), dateTimeFormatter.getParser0());
        }
        throw new IllegalArgumentException("No formatter supplied");
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter) {
        checkPrinter(dateTimePrinter);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), null);
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser dateTimeParser) {
        checkPrinter(dateTimePrinter);
        checkParser(dateTimeParser);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        int i = 0;
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        int length = dateTimeParserArr.length;
        if (length != 1) {
            InternalParser[] internalParserArr = new InternalParser[length];
            while (i < length - 1) {
                InternalParser of = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
                internalParserArr[i] = of;
                if (of == null) {
                    throw new IllegalArgumentException("Incomplete parser array");
                }
                i++;
            }
            internalParserArr[i] = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
            return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), new MatchingParser(internalParserArr));
        } else if (dateTimeParserArr[0] != null) {
            return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParserArr[0]));
        } else {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new InternalParser[]{DateTimeParserInternalParser.of(dateTimeParser), null}));
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    private DateTimeFormatterBuilder append0(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iFormatter = null;
        this.iElementPairs.add(internalPrinter);
        this.iElementPairs.add(internalParser);
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        switch (str.length()) {
            case 0:
                return this;
            case 1:
                return append0(new CharacterLiteral(str.charAt(0)));
            default:
                return append0(new StringLiteral(str));
        }
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, false));
        } else {
            return append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
        }
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        } else if (i > 0) {
            return append0(new FixedNumber(dateTimeFieldType, i, false));
        } else {
            throw new IllegalArgumentException("Illegal number of digits: " + i);
        }
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, true));
        } else {
            return append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
        }
    }

    public DateTimeFormatterBuilder appendFixedSignedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        } else if (i > 0) {
            return append0(new FixedNumber(dateTimeFieldType, i, true));
        } else {
            throw new IllegalArgumentException("Illegal number of digits: " + i);
        }
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, false));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, true));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i >= 0 && i2 > 0) {
            return append0(new Fraction(dateTimeFieldType, i, i2));
        }
        throw new IllegalArgumentException();
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfDay(int i, int i2) {
        return appendFraction(DateTimeFieldType.dayOfYear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMillisOfSecond(int i) {
        return appendDecimal(DateTimeFieldType.millisOfSecond(), i, 3);
    }

    public DateTimeFormatterBuilder appendMillisOfDay(int i) {
        return appendDecimal(DateTimeFieldType.millisOfDay(), i, 8);
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendSecondOfDay(int i) {
        return appendDecimal(DateTimeFieldType.secondOfDay(), i, 5);
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendMinuteOfDay(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfDay(), i, 4);
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i) {
        return appendTwoDigitYear(i, false);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i) {
        return appendTwoDigitWeekyear(i, false);
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendYearOfCentury(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfCentury(), i, i2);
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneName(Map<String, DateTimeZone> map) {
        Object timeZoneName = new TimeZoneName(0, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName() {
        return append0(new TimeZoneName(1, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> map) {
        Object timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        DateTimeFormat.appendPatternTo(this, str);
        return this;
    }

    private Object getFormatter() {
        Object obj = this.iFormatter;
        if (obj == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj2 = this.iElementPairs.get(0);
                Object obj3 = this.iElementPairs.get(1);
                if (obj2 == null) {
                    obj = obj3;
                } else if (obj2 == obj3 || obj3 == null) {
                    obj = obj2;
                }
            }
            if (obj == null) {
                obj = new Composite(this.iElementPairs);
            }
            this.iFormatter = obj;
        }
        return obj;
    }

    private boolean isPrinter(Object obj) {
        if (!(obj instanceof InternalPrinter)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isPrinter();
        }
        return true;
    }

    private boolean isParser(Object obj) {
        if (!(obj instanceof InternalParser)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isParser();
        }
        return true;
    }

    private boolean isFormatter(Object obj) {
        return isPrinter(obj) || isParser(obj);
    }

    static void appendUnknownString(Appendable appendable, int i) throws IOException {
        while (true) {
            i--;
            if (i >= 0) {
                appendable.append('�');
            } else {
                return;
            }
        }
    }

    static boolean csStartsWith(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (charSequence.charAt(i + i2) != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    static boolean csStartsWithIgnoreCase(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i + i2);
            char charAt2 = str.charAt(i2);
            if (charAt != charAt2) {
                charAt = Character.toUpperCase(charAt);
                charAt2 = Character.toUpperCase(charAt2);
                if (!(charAt == charAt2 || Character.toLowerCase(charAt) == Character.toLowerCase(charAt2))) {
                    return false;
                }
            }
        }
        return true;
    }
}
