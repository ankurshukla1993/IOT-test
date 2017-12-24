package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatterBuilder {
    private static final int DAYS = 3;
    private static final int HOURS = 4;
    private static final int MAX_FIELD = 9;
    private static final int MILLIS = 7;
    private static final int MINUTES = 5;
    private static final int MONTHS = 1;
    private static final ConcurrentMap<String, Pattern> PATTERNS = new ConcurrentHashMap();
    private static final int PRINT_ZERO_ALWAYS = 4;
    private static final int PRINT_ZERO_IF_SUPPORTED = 3;
    private static final int PRINT_ZERO_NEVER = 5;
    private static final int PRINT_ZERO_RARELY_FIRST = 1;
    private static final int PRINT_ZERO_RARELY_LAST = 2;
    private static final int SECONDS = 6;
    private static final int SECONDS_MILLIS = 8;
    private static final int SECONDS_OPTIONAL_MILLIS = 9;
    private static final int WEEKS = 2;
    private static final int YEARS = 0;
    private List<Object> iElementPairs;
    private FieldFormatter[] iFieldFormatters;
    private int iMaxParsedDigits;
    private int iMinPrintedDigits;
    private boolean iNotParser;
    private boolean iNotPrinter;
    private PeriodFieldAffix iPrefix;
    private int iPrintZeroSetting;
    private boolean iRejectSignedValues;

    static class Composite implements PeriodPrinter, PeriodParser {
        private final PeriodParser[] iParsers;
        private final PeriodPrinter[] iPrinters;

        Composite(List<Object> list) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.size() <= 0) {
                this.iPrinters = null;
            } else {
                this.iPrinters = (PeriodPrinter[]) arrayList.toArray(new PeriodPrinter[arrayList.size()]);
            }
            if (arrayList2.size() <= 0) {
                this.iParsers = null;
            } else {
                this.iParsers = (PeriodParser[]) arrayList2.toArray(new PeriodParser[arrayList2.size()]);
            }
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int i2 = 0;
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            while (i2 < i) {
                length--;
                if (length < 0) {
                    break;
                }
                i2 += periodPrinterArr[length].countFieldsToPrint(readablePeriod, Integer.MAX_VALUE, locale);
            }
            return i2;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            int i = 0;
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                i += periodPrinterArr[length].calculatePrintedLength(readablePeriod, locale);
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            for (PeriodPrinter printTo : this.iPrinters) {
                printTo.printTo(stringBuffer, readablePeriod, locale);
            }
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            for (PeriodPrinter printTo : this.iPrinters) {
                printTo.printTo(writer, readablePeriod, locale);
            }
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            PeriodParser[] periodParserArr = this.iParsers;
            if (periodParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = periodParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = periodParserArr[i2].parseInto(readWritablePeriod, str, i, locale);
            }
            return i;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof PeriodPrinter) {
                    if (obj instanceof Composite) {
                        addArrayToList(list2, ((Composite) obj).iPrinters);
                    } else {
                        list2.add(obj);
                    }
                }
                obj = list.get(i + 1);
                if (obj instanceof PeriodParser) {
                    if (obj instanceof Composite) {
                        addArrayToList(list3, ((Composite) obj).iParsers);
                    } else {
                        list3.add(obj);
                    }
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

    interface PeriodFieldAffix {
        int calculatePrintedLength(int i);

        void finish(Set<PeriodFieldAffix> set);

        String[] getAffixes();

        int parse(String str, int i);

        void printTo(Writer writer, int i) throws IOException;

        void printTo(StringBuffer stringBuffer, int i);

        int scan(String str, int i);
    }

    static abstract class IgnorableAffix implements PeriodFieldAffix {
        private volatile String[] iOtherAffixes;

        IgnorableAffix() {
        }

        public void finish(Set<PeriodFieldAffix> set) {
            if (this.iOtherAffixes == null) {
                int i = Integer.MAX_VALUE;
                String str = null;
                String[] affixes = getAffixes();
                int length = affixes.length;
                int i2 = 0;
                while (i2 < length) {
                    int length2;
                    String str2 = affixes[i2];
                    if (str2.length() < i) {
                        length2 = str2.length();
                    } else {
                        str2 = str;
                        length2 = i;
                    }
                    i2++;
                    i = length2;
                    str = str2;
                }
                Set hashSet = new HashSet();
                for (PeriodFieldAffix periodFieldAffix : set) {
                    if (periodFieldAffix != null) {
                        for (String str3 : periodFieldAffix.getAffixes()) {
                            if (str3.length() > i || (str3.equalsIgnoreCase(str) && !str3.equals(str))) {
                                hashSet.add(str3);
                            }
                        }
                    }
                }
                this.iOtherAffixes = (String[]) hashSet.toArray(new String[hashSet.size()]);
            }
        }

        protected boolean matchesOtherAffix(int i, String str, int i2) {
            if (this.iOtherAffixes != null) {
                for (String str2 : this.iOtherAffixes) {
                    int length = str2.length();
                    if ((i < length && str.regionMatches(true, i2, str2, 0, length)) || (i == length && str.regionMatches(false, i2, str2, 0, length))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class CompositeAffix extends IgnorableAffix {
        private final PeriodFieldAffix iLeft;
        private final String[] iLeftRightCombinations;
        private final PeriodFieldAffix iRight;

        CompositeAffix(PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iLeft = periodFieldAffix;
            this.iRight = periodFieldAffix2;
            Set hashSet = new HashSet();
            for (String str : this.iLeft.getAffixes()) {
                for (String str2 : this.iRight.getAffixes()) {
                    hashSet.add(str + str2);
                }
            }
            this.iLeftRightCombinations = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }

        public int calculatePrintedLength(int i) {
            return this.iLeft.calculatePrintedLength(i) + this.iRight.calculatePrintedLength(i);
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            this.iLeft.printTo(stringBuffer, i);
            this.iRight.printTo(stringBuffer, i);
        }

        public void printTo(Writer writer, int i) throws IOException {
            this.iLeft.printTo(writer, i);
            this.iRight.printTo(writer, i);
        }

        public int parse(String str, int i) {
            int parse = this.iLeft.parse(str, i);
            if (parse < 0) {
                return parse;
            }
            parse = this.iRight.parse(str, parse);
            if (parse < 0 || !matchesOtherAffix(parse(str, parse) - parse, str, i)) {
                return parse;
            }
            return i ^ -1;
        }

        public int scan(String str, int i) {
            int scan = this.iLeft.scan(str, i);
            if (scan >= 0) {
                int scan2 = this.iRight.scan(str, this.iLeft.parse(str, scan));
                if (scan2 < 0 || !matchesOtherAffix(this.iRight.parse(str, scan2) - scan, str, i)) {
                    return scan > 0 ? scan : scan2;
                }
            }
            return i ^ -1;
        }

        public String[] getAffixes() {
            return (String[]) this.iLeftRightCombinations.clone();
        }
    }

    static class FieldFormatter implements PeriodPrinter, PeriodParser {
        private final FieldFormatter[] iFieldFormatters;
        private final int iFieldType;
        private final int iMaxParsedDigits;
        private final int iMinPrintedDigits;
        private final PeriodFieldAffix iPrefix;
        private final int iPrintZeroSetting;
        private final boolean iRejectSignedValues;
        private final PeriodFieldAffix iSuffix;

        FieldFormatter(int i, int i2, int i3, boolean z, int i4, FieldFormatter[] fieldFormatterArr, PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iMinPrintedDigits = i;
            this.iPrintZeroSetting = i2;
            this.iMaxParsedDigits = i3;
            this.iRejectSignedValues = z;
            this.iFieldType = i4;
            this.iFieldFormatters = fieldFormatterArr;
            this.iPrefix = periodFieldAffix;
            this.iSuffix = periodFieldAffix2;
        }

        FieldFormatter(FieldFormatter fieldFormatter, PeriodFieldAffix periodFieldAffix) {
            this.iMinPrintedDigits = fieldFormatter.iMinPrintedDigits;
            this.iPrintZeroSetting = fieldFormatter.iPrintZeroSetting;
            this.iMaxParsedDigits = fieldFormatter.iMaxParsedDigits;
            this.iRejectSignedValues = fieldFormatter.iRejectSignedValues;
            this.iFieldType = fieldFormatter.iFieldType;
            this.iFieldFormatters = fieldFormatter.iFieldFormatters;
            this.iPrefix = fieldFormatter.iPrefix;
            if (fieldFormatter.iSuffix != null) {
                periodFieldAffix = new CompositeAffix(fieldFormatter.iSuffix, periodFieldAffix);
            }
            this.iSuffix = periodFieldAffix;
        }

        public void finish(FieldFormatter[] fieldFormatterArr) {
            Set hashSet = new HashSet();
            Set hashSet2 = new HashSet();
            for (FieldFormatter fieldFormatter : fieldFormatterArr) {
                if (!(fieldFormatter == null || equals(fieldFormatter))) {
                    hashSet.add(fieldFormatter.iPrefix);
                    hashSet2.add(fieldFormatter.iSuffix);
                }
            }
            if (this.iPrefix != null) {
                this.iPrefix.finish(hashSet);
            }
            if (this.iSuffix != null) {
                this.iSuffix.finish(hashSet2);
            }
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            if (i <= 0) {
                return 0;
            }
            if (this.iPrintZeroSetting == 4 || getFieldValue(readablePeriod) != Long.MAX_VALUE) {
                return 1;
            }
            return 0;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return 0;
            }
            int max = Math.max(FormatUtils.calculateDigitCount(fieldValue), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
                max = (fieldValue < 0 ? Math.max(max, 5) : Math.max(max, 4)) + 1;
                if (this.iFieldType == 9 && Math.abs(fieldValue) % 1000 == 0) {
                    max -= 4;
                }
                fieldValue /= 1000;
            }
            int i = (int) fieldValue;
            if (this.iPrefix != null) {
                max += this.iPrefix.calculatePrintedLength(i);
            }
            if (this.iSuffix != null) {
                return max + this.iSuffix.calculatePrintedLength(i);
            }
            return max;
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                if (this.iPrefix != null) {
                    this.iPrefix.printTo(stringBuffer, i);
                }
                int length = stringBuffer.length();
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.appendUnpaddedInteger(stringBuffer, i);
                } else {
                    FormatUtils.appendPaddedInteger(stringBuffer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    i2 = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || i2 > 0) {
                        if (fieldValue < 0 && fieldValue > -1000) {
                            stringBuffer.insert(length, '-');
                        }
                        stringBuffer.append('.');
                        FormatUtils.appendPaddedInteger(stringBuffer, i2, 3);
                    }
                }
                if (this.iSuffix != null) {
                    this.iSuffix.printTo(stringBuffer, i);
                }
            }
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                if (this.iPrefix != null) {
                    this.iPrefix.printTo(writer, i);
                }
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.writeUnpaddedInteger(writer, i);
                } else {
                    FormatUtils.writePaddedInteger(writer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    i2 = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || i2 > 0) {
                        writer.write(46);
                        FormatUtils.writePaddedInteger(writer, i2, 3);
                    }
                }
                if (this.iSuffix != null) {
                    this.iSuffix.printTo(writer, i);
                }
            }
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            Object obj = this.iPrintZeroSetting == 4 ? 1 : null;
            if (i >= str.length()) {
                return obj != null ? i ^ -1 : i;
            } else {
                int i2;
                int scan;
                if (this.iPrefix != null) {
                    i = this.iPrefix.parse(str, i);
                    if (i >= 0) {
                        obj = 1;
                    } else if (obj == null) {
                        return i ^ -1;
                    } else {
                        return i;
                    }
                }
                if (this.iSuffix == null || obj != null) {
                    i2 = -1;
                } else {
                    scan = this.iSuffix.scan(str, i);
                    if (scan < 0) {
                        return obj == null ? scan ^ -1 : scan;
                    } else {
                        obj = 1;
                        i2 = scan;
                    }
                }
                if (obj == null && !isSupported(readWritablePeriod.getPeriodType(), this.iFieldType)) {
                    return i;
                }
                int min;
                Object obj2;
                if (i2 > 0) {
                    min = Math.min(this.iMaxParsedDigits, i2 - i);
                } else {
                    min = Math.min(this.iMaxParsedDigits, str.length() - i);
                }
                int i3 = -1;
                Object obj3 = null;
                Object obj4 = null;
                int i4 = min;
                min = 0;
                scan = i;
                while (min < i4) {
                    char charAt = str.charAt(scan + min);
                    if (min == 0 && ((charAt == '-' || charAt == '+') && !this.iRejectSignedValues)) {
                        obj4 = charAt == '-' ? 1 : null;
                        if (min + 1 >= i4) {
                            break;
                        }
                        charAt = str.charAt((scan + min) + 1);
                        if (charAt < '0') {
                            break;
                        } else if (charAt > '9') {
                            obj2 = obj4;
                            break;
                        } else {
                            if (obj4 != null) {
                                min++;
                            } else {
                                scan++;
                            }
                            i4 = Math.min(i4 + 1, str.length() - scan);
                        }
                    } else {
                        if (charAt < '0' || charAt > '9') {
                            if ((charAt != '.' && charAt != ',') || (this.iFieldType != 8 && this.iFieldType != 9)) {
                                break;
                            } else if (i3 >= 0) {
                                obj2 = obj4;
                                break;
                            } else {
                                i3 = (scan + min) + 1;
                                i4 = Math.min(i4 + 1, str.length() - scan);
                            }
                        } else {
                            obj3 = 1;
                        }
                        min++;
                    }
                }
                obj2 = obj4;
                if (obj3 == null) {
                    return scan ^ -1;
                }
                if (i2 >= 0 && scan + min != i2) {
                    return scan;
                }
                if (this.iFieldType != 8 && this.iFieldType != 9) {
                    setFieldValue(readWritablePeriod, this.iFieldType, parseInt(str, scan, min));
                } else if (i3 < 0) {
                    setFieldValue(readWritablePeriod, 6, parseInt(str, scan, min));
                    setFieldValue(readWritablePeriod, 7, 0);
                } else {
                    int parseInt = parseInt(str, scan, (i3 - scan) - 1);
                    setFieldValue(readWritablePeriod, 6, parseInt);
                    int i5 = (scan + min) - i3;
                    if (i5 <= 0) {
                        i5 = 0;
                    } else {
                        if (i5 >= 3) {
                            i5 = parseInt(str, i3, 3);
                        } else {
                            i3 = parseInt(str, i3, i5);
                            if (i5 == 1) {
                                i5 = i3 * 100;
                            } else {
                                i5 = i3 * 10;
                            }
                        }
                        if (obj2 != null || parseInt < 0) {
                            i5 = -i5;
                        }
                    }
                    setFieldValue(readWritablePeriod, 7, i5);
                }
                min += scan;
                if (min >= 0 && this.iSuffix != null) {
                    min = this.iSuffix.parse(str, min);
                }
                return min;
            }
        }

        private int parseInt(String str, int i, int i2) {
            int i3 = 0;
            if (i2 >= 10) {
                return Integer.parseInt(str.substring(i, i + i2));
            }
            if (i2 <= 0) {
                return 0;
            }
            int i4;
            int i5 = i + 1;
            int charAt = str.charAt(i);
            int i6 = i2 - 1;
            if (charAt == 45) {
                i6--;
                if (i6 < 0) {
                    return 0;
                }
                i3 = 1;
                i4 = i5 + 1;
                charAt = str.charAt(i5);
            } else {
                i4 = i5;
            }
            charAt -= 48;
            i5 = i4;
            while (true) {
                i4 = i6 - 1;
                if (i6 <= 0) {
                    break;
                }
                charAt = (((charAt << 1) + (charAt << 3)) + str.charAt(i5)) - 48;
                i5++;
                i6 = i4;
            }
            return i3 != 0 ? -charAt : charAt;
        }

        long getFieldValue(ReadablePeriod readablePeriod) {
            PeriodType periodType;
            if (this.iPrintZeroSetting == 4) {
                periodType = null;
            } else {
                periodType = readablePeriod.getPeriodType();
            }
            if (periodType != null && !isSupported(periodType, this.iFieldType)) {
                return Long.MAX_VALUE;
            }
            long j;
            switch (this.iFieldType) {
                case 0:
                    j = (long) readablePeriod.get(DurationFieldType.years());
                    break;
                case 1:
                    j = (long) readablePeriod.get(DurationFieldType.months());
                    break;
                case 2:
                    j = (long) readablePeriod.get(DurationFieldType.weeks());
                    break;
                case 3:
                    j = (long) readablePeriod.get(DurationFieldType.days());
                    break;
                case 4:
                    j = (long) readablePeriod.get(DurationFieldType.hours());
                    break;
                case 5:
                    j = (long) readablePeriod.get(DurationFieldType.minutes());
                    break;
                case 6:
                    j = (long) readablePeriod.get(DurationFieldType.seconds());
                    break;
                case 7:
                    j = (long) readablePeriod.get(DurationFieldType.millis());
                    break;
                case 8:
                case 9:
                    j = (long) readablePeriod.get(DurationFieldType.millis());
                    j += ((long) readablePeriod.get(DurationFieldType.seconds())) * 1000;
                    break;
                default:
                    return Long.MAX_VALUE;
            }
            if (j == 0) {
                int min;
                switch (this.iPrintZeroSetting) {
                    case 1:
                        if (!isZero(readablePeriod) || this.iFieldFormatters[this.iFieldType] != this) {
                            return Long.MAX_VALUE;
                        }
                        min = Math.min(this.iFieldType, 8) - 1;
                        while (min >= 0 && min <= 9) {
                            if (isSupported(periodType, min) && this.iFieldFormatters[min] != null) {
                                return Long.MAX_VALUE;
                            }
                            min--;
                        }
                        break;
                    case 2:
                        if (isZero(readablePeriod) && this.iFieldFormatters[this.iFieldType] == this) {
                            min = this.iFieldType + 1;
                            while (min <= 9) {
                                if (isSupported(periodType, min) && this.iFieldFormatters[min] != null) {
                                    return Long.MAX_VALUE;
                                }
                                min++;
                            }
                            break;
                        }
                        return Long.MAX_VALUE;
                        break;
                    case 5:
                        return Long.MAX_VALUE;
                }
            }
            return j;
        }

        boolean isZero(ReadablePeriod readablePeriod) {
            int size = readablePeriod.size();
            for (int i = 0; i < size; i++) {
                if (readablePeriod.getValue(i) != 0) {
                    return false;
                }
            }
            return true;
        }

        boolean isSupported(PeriodType periodType, int i) {
            switch (i) {
                case 0:
                    return periodType.isSupported(DurationFieldType.years());
                case 1:
                    return periodType.isSupported(DurationFieldType.months());
                case 2:
                    return periodType.isSupported(DurationFieldType.weeks());
                case 3:
                    return periodType.isSupported(DurationFieldType.days());
                case 4:
                    return periodType.isSupported(DurationFieldType.hours());
                case 5:
                    return periodType.isSupported(DurationFieldType.minutes());
                case 6:
                    return periodType.isSupported(DurationFieldType.seconds());
                case 7:
                    return periodType.isSupported(DurationFieldType.millis());
                case 8:
                case 9:
                    return periodType.isSupported(DurationFieldType.seconds()) || periodType.isSupported(DurationFieldType.millis());
                default:
                    return false;
            }
        }

        void setFieldValue(ReadWritablePeriod readWritablePeriod, int i, int i2) {
            switch (i) {
                case 0:
                    readWritablePeriod.setYears(i2);
                    return;
                case 1:
                    readWritablePeriod.setMonths(i2);
                    return;
                case 2:
                    readWritablePeriod.setWeeks(i2);
                    return;
                case 3:
                    readWritablePeriod.setDays(i2);
                    return;
                case 4:
                    readWritablePeriod.setHours(i2);
                    return;
                case 5:
                    readWritablePeriod.setMinutes(i2);
                    return;
                case 6:
                    readWritablePeriod.setSeconds(i2);
                    return;
                case 7:
                    readWritablePeriod.setMillis(i2);
                    return;
                default:
                    return;
            }
        }

        int getFieldType() {
            return this.iFieldType;
        }
    }

    static class Literal implements PeriodPrinter, PeriodParser {
        static final Literal EMPTY = new Literal("");
        private final String iText;

        Literal(String str) {
            this.iText = str;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            return 0;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            return this.iText.length();
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            stringBuffer.append(this.iText);
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            writer.write(this.iText);
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            if (str.regionMatches(true, i, this.iText, 0, this.iText.length())) {
                return this.iText.length() + i;
            }
            return i ^ -1;
        }
    }

    static class PluralAffix extends IgnorableAffix {
        private final String iPluralText;
        private final String iSingularText;

        PluralAffix(String str, String str2) {
            this.iSingularText = str;
            this.iPluralText = str2;
        }

        public int calculatePrintedLength(int i) {
            return (i == 1 ? this.iSingularText : this.iPluralText).length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(i == 1 ? this.iSingularText : this.iPluralText);
        }

        public void printTo(Writer writer, int i) throws IOException {
            writer.write(i == 1 ? this.iSingularText : this.iPluralText);
        }

        public int parse(String str, int i) {
            String str2;
            String str3;
            String str4 = this.iPluralText;
            String str5 = this.iSingularText;
            if (str4.length() < str5.length()) {
                str2 = str4;
                str3 = str5;
            } else {
                str2 = str5;
                str3 = str4;
            }
            if (str.regionMatches(true, i, str3, 0, str3.length()) && !matchesOtherAffix(str3.length(), str, i)) {
                return str3.length() + i;
            }
            if (!str.regionMatches(true, i, str2, 0, str2.length()) || matchesOtherAffix(str2.length(), str, i)) {
                return i ^ -1;
            }
            return str2.length() + i;
        }

        public int scan(String str, int i) {
            String str2;
            String str3 = this.iPluralText;
            String str4 = this.iSingularText;
            if (str3.length() < str4.length()) {
                str2 = str4;
            } else {
                str2 = str3;
                str3 = str4;
            }
            int length = str2.length();
            int length2 = str3.length();
            int length3 = str.length();
            int i2 = i;
            while (i2 < length3) {
                if (str.regionMatches(true, i2, str2, 0, length) && !matchesOtherAffix(str2.length(), str, i2)) {
                    return i2;
                }
                if (str.regionMatches(true, i2, str3, 0, length2) && !matchesOtherAffix(str3.length(), str, i2)) {
                    return i2;
                }
                i2++;
            }
            return i ^ -1;
        }

        public String[] getAffixes() {
            return new String[]{this.iSingularText, this.iPluralText};
        }
    }

    static class RegExAffix extends IgnorableAffix {
        private static final Comparator<String> LENGTH_DESC_COMPARATOR = new C25561();
        private final Pattern[] iPatterns;
        private final String[] iSuffixes;
        private final String[] iSuffixesSortedDescByLength;

        static class C25561 implements Comparator<String> {
            C25561() {
            }

            public int compare(String str, String str2) {
                return str2.length() - str.length();
            }
        }

        RegExAffix(String[] strArr, String[] strArr2) {
            this.iSuffixes = (String[]) strArr2.clone();
            this.iPatterns = new Pattern[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                Pattern pattern = (Pattern) PeriodFormatterBuilder.PATTERNS.get(strArr[i]);
                if (pattern == null) {
                    pattern = Pattern.compile(strArr[i]);
                    PeriodFormatterBuilder.PATTERNS.putIfAbsent(strArr[i], pattern);
                }
                this.iPatterns[i] = pattern;
            }
            this.iSuffixesSortedDescByLength = (String[]) this.iSuffixes.clone();
            Arrays.sort(this.iSuffixesSortedDescByLength, LENGTH_DESC_COMPARATOR);
        }

        private int selectSuffixIndex(int i) {
            CharSequence valueOf = String.valueOf(i);
            for (int i2 = 0; i2 < this.iPatterns.length; i2++) {
                if (this.iPatterns[i2].matcher(valueOf).matches()) {
                    return i2;
                }
            }
            return this.iPatterns.length - 1;
        }

        public int calculatePrintedLength(int i) {
            return this.iSuffixes[selectSuffixIndex(i)].length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iSuffixes[selectSuffixIndex(i)]);
        }

        public void printTo(Writer writer, int i) throws IOException {
            writer.write(this.iSuffixes[selectSuffixIndex(i)]);
        }

        public int parse(String str, int i) {
            for (String str2 : this.iSuffixesSortedDescByLength) {
                if (str.regionMatches(true, i, str2, 0, str2.length()) && !matchesOtherAffix(str2.length(), str, i)) {
                    return str2.length() + i;
                }
            }
            return i ^ -1;
        }

        public int scan(String str, int i) {
            int length = str.length();
            int i2 = i;
            while (i2 < length) {
                for (String str2 : this.iSuffixesSortedDescByLength) {
                    if (str.regionMatches(true, i2, str2, 0, str2.length()) && !matchesOtherAffix(str2.length(), str, i2)) {
                        return i2;
                    }
                }
                i2++;
            }
            return i ^ -1;
        }

        public String[] getAffixes() {
            return (String[]) this.iSuffixes.clone();
        }
    }

    static class Separator implements PeriodPrinter, PeriodParser {
        private volatile PeriodParser iAfterParser;
        private volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private final PeriodPrinter iBeforePrinter;
        private final String iFinalText;
        private final String[] iParsedForms;
        private final String iText;
        private final boolean iUseAfter;
        private final boolean iUseBefore;

        Separator(String str, String str2, String[] strArr, PeriodPrinter periodPrinter, PeriodParser periodParser, boolean z, boolean z2) {
            this.iText = str;
            this.iFinalText = str2;
            if ((str2 == null || str.equals(str2)) && (strArr == null || strArr.length == 0)) {
                this.iParsedForms = new String[]{str};
            } else {
                Collection treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                treeSet.add(str);
                treeSet.add(str2);
                if (strArr != null) {
                    int length = strArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        }
                        treeSet.add(strArr[length]);
                    }
                }
                ArrayList arrayList = new ArrayList(treeSet);
                Collections.reverse(arrayList);
                this.iParsedForms = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            this.iBeforePrinter = periodPrinter;
            this.iBeforeParser = periodParser;
            this.iUseBefore = z;
            this.iUseAfter = z2;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int countFieldsToPrint = this.iBeforePrinter.countFieldsToPrint(readablePeriod, i, locale);
            if (countFieldsToPrint < i) {
                return countFieldsToPrint + this.iAfterPrinter.countFieldsToPrint(readablePeriod, i, locale);
            }
            return countFieldsToPrint;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            int calculatePrintedLength = periodPrinter.calculatePrintedLength(readablePeriod, locale) + periodPrinter2.calculatePrintedLength(readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                    return calculatePrintedLength;
                }
                if (!this.iUseAfter) {
                    return calculatePrintedLength + this.iText.length();
                }
                int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                if (countFieldsToPrint > 0) {
                    countFieldsToPrint = (countFieldsToPrint > 1 ? this.iText : this.iFinalText).length() + calculatePrintedLength;
                } else {
                    countFieldsToPrint = calculatePrintedLength;
                }
                return countFieldsToPrint;
            } else if (!this.iUseAfter || periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                return calculatePrintedLength;
            } else {
                return calculatePrintedLength + this.iText.length();
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            stringBuffer.append(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        stringBuffer.append(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                stringBuffer.append(this.iText);
            }
            periodPrinter2.printTo(stringBuffer, readablePeriod, locale);
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(writer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            writer.write(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        writer.write(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                writer.write(this.iText);
            }
            periodPrinter2.printTo(writer, readablePeriod, locale);
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            boolean z = true;
            int i2 = 0;
            int parseInto = this.iBeforeParser.parseInto(readWritablePeriod, str, i, locale);
            if (parseInto < 0) {
                return parseInto;
            }
            int parseInto2;
            if (parseInto > i) {
                String[] strArr = this.iParsedForms;
                int length = strArr.length;
                int i3 = 0;
                while (i3 < length) {
                    String str2 = strArr[i3];
                    if (!(str2 == null || str2.length() == 0)) {
                        if (!str.regionMatches(true, parseInto, str2, 0, str2.length())) {
                            i3++;
                        }
                    }
                    if (str2 != null) {
                        i2 = str2.length();
                    }
                    parseInto += i2;
                    parseInto2 = this.iAfterParser.parseInto(readWritablePeriod, str, parseInto, locale);
                    if (parseInto2 < 0) {
                        return parseInto2;
                    }
                    if (z || parseInto2 != parseInto || r4 <= 0) {
                        return (parseInto2 > parseInto || z || this.iUseBefore) ? parseInto2 : parseInto ^ -1;
                    } else {
                        return parseInto ^ -1;
                    }
                }
            }
            z = false;
            i2 = -1;
            parseInto2 = this.iAfterParser.parseInto(readWritablePeriod, str, parseInto, locale);
            if (parseInto2 < 0) {
                return parseInto2;
            }
            if (z) {
            }
            if (parseInto2 > parseInto) {
            }
        }

        Separator finish(PeriodPrinter periodPrinter, PeriodParser periodParser) {
            this.iAfterPrinter = periodPrinter;
            this.iAfterParser = periodParser;
            return this;
        }
    }

    static class SimpleAffix extends IgnorableAffix {
        private final String iText;

        SimpleAffix(String str) {
            this.iText = str;
        }

        public int calculatePrintedLength(int i) {
            return this.iText.length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iText);
        }

        public void printTo(Writer writer, int i) throws IOException {
            writer.write(this.iText);
        }

        public int parse(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            if (!str.regionMatches(true, i, str2, 0, length) || matchesOtherAffix(length, str, i)) {
                return i ^ -1;
            }
            return i + length;
        }

        public int scan(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            int length2 = str.length();
            int i2 = i;
            while (i2 < length2) {
                if (str.regionMatches(true, i2, str2, 0, length) && !matchesOtherAffix(length, str, i2)) {
                    return i2;
                }
                switch (str.charAt(i2)) {
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        i2++;
                    default:
                        break;
                }
                return i ^ -1;
            }
            return i ^ -1;
        }

        public String[] getAffixes() {
            return new String[]{this.iText};
        }
    }

    public PeriodFormatterBuilder() {
        clear();
    }

    public PeriodFormatter toFormatter() {
        PeriodFormatter toFormatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
        for (FieldFormatter fieldFormatter : this.iFieldFormatters) {
            if (fieldFormatter != null) {
                fieldFormatter.finish(this.iFieldFormatters);
            }
        }
        this.iFieldFormatters = (FieldFormatter[]) this.iFieldFormatters.clone();
        return toFormatter;
    }

    public PeriodPrinter toPrinter() {
        if (this.iNotPrinter) {
            return null;
        }
        return toFormatter().getPrinter();
    }

    public PeriodParser toParser() {
        if (this.iNotParser) {
            return null;
        }
        return toFormatter().getParser();
    }

    public void clear() {
        this.iMinPrintedDigits = 1;
        this.iPrintZeroSetting = 2;
        this.iMaxParsedDigits = 10;
        this.iRejectSignedValues = false;
        this.iPrefix = null;
        if (this.iElementPairs == null) {
            this.iElementPairs = new ArrayList();
        } else {
            this.iElementPairs.clear();
        }
        this.iNotPrinter = false;
        this.iNotParser = false;
        this.iFieldFormatters = new FieldFormatter[10];
    }

    public PeriodFormatterBuilder append(PeriodFormatter periodFormatter) {
        if (periodFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        clearPrefix();
        append0(periodFormatter.getPrinter(), periodFormatter.getParser());
        return this;
    }

    public PeriodFormatterBuilder append(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        if (periodPrinter == null && periodParser == null) {
            throw new IllegalArgumentException("No printer or parser supplied");
        }
        clearPrefix();
        append0(periodPrinter, periodParser);
        return this;
    }

    public PeriodFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        clearPrefix();
        Object literal = new Literal(str);
        append0(literal, literal);
        return this;
    }

    public PeriodFormatterBuilder minimumPrintedDigits(int i) {
        this.iMinPrintedDigits = i;
        return this;
    }

    public PeriodFormatterBuilder maximumParsedDigits(int i) {
        this.iMaxParsedDigits = i;
        return this;
    }

    public PeriodFormatterBuilder rejectSignedValues(boolean z) {
        this.iRejectSignedValues = z;
        return this;
    }

    public PeriodFormatterBuilder printZeroRarelyLast() {
        this.iPrintZeroSetting = 2;
        return this;
    }

    public PeriodFormatterBuilder printZeroRarelyFirst() {
        this.iPrintZeroSetting = 1;
        return this;
    }

    public PeriodFormatterBuilder printZeroIfSupported() {
        this.iPrintZeroSetting = 3;
        return this;
    }

    public PeriodFormatterBuilder printZeroAlways() {
        this.iPrintZeroSetting = 4;
        return this;
    }

    public PeriodFormatterBuilder printZeroNever() {
        this.iPrintZeroSetting = 5;
        return this;
    }

    public PeriodFormatterBuilder appendPrefix(String str) {
        if (str != null) {
            return appendPrefix(new SimpleAffix(str));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendPrefix(String str, String str2) {
        if (str != null && str2 != null) {
            return appendPrefix(new PluralAffix(str, str2));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendPrefix(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length >= 1 && strArr.length == strArr2.length) {
            return appendPrefix(new RegExAffix(strArr, strArr2));
        }
        throw new IllegalArgumentException();
    }

    private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix periodFieldAffix) {
        if (periodFieldAffix == null) {
            throw new IllegalArgumentException();
        }
        if (this.iPrefix != null) {
            periodFieldAffix = new CompositeAffix(this.iPrefix, periodFieldAffix);
        }
        this.iPrefix = periodFieldAffix;
        return this;
    }

    public PeriodFormatterBuilder appendYears() {
        appendField(0);
        return this;
    }

    public PeriodFormatterBuilder appendMonths() {
        appendField(1);
        return this;
    }

    public PeriodFormatterBuilder appendWeeks() {
        appendField(2);
        return this;
    }

    public PeriodFormatterBuilder appendDays() {
        appendField(3);
        return this;
    }

    public PeriodFormatterBuilder appendHours() {
        appendField(4);
        return this;
    }

    public PeriodFormatterBuilder appendMinutes() {
        appendField(5);
        return this;
    }

    public PeriodFormatterBuilder appendSeconds() {
        appendField(6);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithMillis() {
        appendField(8);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        appendField(9);
        return this;
    }

    public PeriodFormatterBuilder appendMillis() {
        appendField(7);
        return this;
    }

    public PeriodFormatterBuilder appendMillis3Digit() {
        appendField(7, 3);
        return this;
    }

    private void appendField(int i) {
        appendField(i, this.iMinPrintedDigits);
    }

    private void appendField(int i, int i2) {
        Object fieldFormatter = new FieldFormatter(i2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, i, this.iFieldFormatters, this.iPrefix, null);
        append0(fieldFormatter, fieldFormatter);
        this.iFieldFormatters[i] = fieldFormatter;
        this.iPrefix = null;
    }

    public PeriodFormatterBuilder appendSuffix(String str) {
        if (str != null) {
            return appendSuffix(new SimpleAffix(str));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendSuffix(String str, String str2) {
        if (str != null && str2 != null) {
            return appendSuffix(new PluralAffix(str, str2));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendSuffix(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length >= 1 && strArr.length == strArr2.length) {
            return appendSuffix(new RegExAffix(strArr, strArr2));
        }
        throw new IllegalArgumentException();
    }

    private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix periodFieldAffix) {
        Object obj = null;
        Object obj2;
        if (this.iElementPairs.size() > 0) {
            obj2 = this.iElementPairs.get(this.iElementPairs.size() - 2);
            obj = obj2;
            obj2 = this.iElementPairs.get(this.iElementPairs.size() - 1);
        } else {
            obj2 = null;
        }
        if (obj == null || r1 == null || obj != r1 || !(obj instanceof FieldFormatter)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        clearPrefix();
        FieldFormatter fieldFormatter = new FieldFormatter((FieldFormatter) obj, periodFieldAffix);
        this.iElementPairs.set(this.iElementPairs.size() - 2, fieldFormatter);
        this.iElementPairs.set(this.iElementPairs.size() - 1, fieldFormatter);
        this.iFieldFormatters[fieldFormatter.getFieldType()] = fieldFormatter;
        return this;
    }

    public PeriodFormatterBuilder appendSeparator(String str) {
        return appendSeparator(str, str, null, true, true);
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String str) {
        return appendSeparator(str, str, null, false, true);
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String str) {
        return appendSeparator(str, str, null, true, false);
    }

    public PeriodFormatterBuilder appendSeparator(String str, String str2) {
        return appendSeparator(str, str2, null, true, true);
    }

    public PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr) {
        return appendSeparator(str, str2, strArr, true, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.joda.time.format.PeriodFormatterBuilder appendSeparator(java.lang.String r10, java.lang.String r11, java.lang.String[] r12, boolean r13, boolean r14) {
        /*
        r9 = this;
        if (r10 == 0) goto L_0x0004;
    L_0x0002:
        if (r11 != 0) goto L_0x000a;
    L_0x0004:
        r0 = new java.lang.IllegalArgumentException;
        r0.<init>();
        throw r0;
    L_0x000a:
        r9.clearPrefix();
        r1 = r9.iElementPairs;
        r0 = r1.size();
        if (r0 != 0) goto L_0x002b;
    L_0x0015:
        if (r14 == 0) goto L_0x002a;
    L_0x0017:
        if (r13 != 0) goto L_0x002a;
    L_0x0019:
        r0 = new org.joda.time.format.PeriodFormatterBuilder$Separator;
        r4 = org.joda.time.format.PeriodFormatterBuilder.Literal.EMPTY;
        r5 = org.joda.time.format.PeriodFormatterBuilder.Literal.EMPTY;
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r6 = r13;
        r7 = r14;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        r9.append0(r0, r0);
    L_0x002a:
        return r9;
    L_0x002b:
        r0 = 0;
        r2 = r1.size();
    L_0x0030:
        r2 = r2 + -1;
        if (r2 < 0) goto L_0x0082;
    L_0x0034:
        r3 = r1.get(r2);
        r3 = r3 instanceof org.joda.time.format.PeriodFormatterBuilder.Separator;
        if (r3 == 0) goto L_0x005d;
    L_0x003c:
        r0 = r1.get(r2);
        r0 = (org.joda.time.format.PeriodFormatterBuilder.Separator) r0;
        r2 = r2 + 1;
        r3 = r1.size();
        r1 = r1.subList(r2, r3);
        r8 = r1;
    L_0x004d:
        if (r0 == 0) goto L_0x0060;
    L_0x004f:
        r0 = r8.size();
        if (r0 != 0) goto L_0x0060;
    L_0x0055:
        r0 = new java.lang.IllegalStateException;
        r1 = "Cannot have two adjacent separators";
        r0.<init>(r1);
        throw r0;
    L_0x005d:
        r2 = r2 + -1;
        goto L_0x0030;
    L_0x0060:
        r1 = createComposite(r8);
        r8.clear();
        r0 = new org.joda.time.format.PeriodFormatterBuilder$Separator;
        r2 = 0;
        r4 = r1[r2];
        r4 = (org.joda.time.format.PeriodPrinter) r4;
        r2 = 1;
        r5 = r1[r2];
        r5 = (org.joda.time.format.PeriodParser) r5;
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r6 = r13;
        r7 = r14;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        r8.add(r0);
        r8.add(r0);
        goto L_0x002a;
    L_0x0082:
        r8 = r1;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.PeriodFormatterBuilder.appendSeparator(java.lang.String, java.lang.String, java.lang.String[], boolean, boolean):org.joda.time.format.PeriodFormatterBuilder");
    }

    private void clearPrefix() throws IllegalStateException {
        if (this.iPrefix != null) {
            throw new IllegalStateException("Prefix not followed by field");
        }
        this.iPrefix = null;
    }

    private PeriodFormatterBuilder append0(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        int i;
        int i2 = 1;
        this.iElementPairs.add(periodPrinter);
        this.iElementPairs.add(periodParser);
        boolean z = this.iNotPrinter;
        if (periodPrinter == null) {
            i = 1;
        } else {
            i = 0;
        }
        this.iNotPrinter = i | z;
        boolean z2 = this.iNotParser;
        if (periodParser != null) {
            i2 = 0;
        }
        this.iNotParser = z2 | i2;
        return this;
    }

    private static PeriodFormatter toFormatter(List<Object> list, boolean z, boolean z2) {
        if (z && z2) {
            throw new IllegalStateException("Builder has created neither a printer nor a parser");
        }
        int size = list.size();
        if (size >= 2 && (list.get(0) instanceof Separator)) {
            Separator separator = (Separator) list.get(0);
            if (separator.iAfterParser == null && separator.iAfterPrinter == null) {
                PeriodFormatter toFormatter = toFormatter(list.subList(2, size), z, z2);
                Object finish = separator.finish(toFormatter.getPrinter(), toFormatter.getParser());
                return new PeriodFormatter(finish, finish);
            }
        }
        Object[] createComposite = createComposite(list);
        if (z) {
            return new PeriodFormatter(null, (PeriodParser) createComposite[1]);
        }
        if (z2) {
            return new PeriodFormatter((PeriodPrinter) createComposite[0], null);
        }
        return new PeriodFormatter((PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1]);
    }

    private static Object[] createComposite(List<Object> list) {
        switch (list.size()) {
            case 0:
                return new Object[]{Literal.EMPTY, Literal.EMPTY};
            case 1:
                return new Object[]{list.get(0), list.get(1)};
            default:
                Composite composite = new Composite(list);
                return new Object[]{composite, composite};
        }
    }
}
