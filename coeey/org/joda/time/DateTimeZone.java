package org.joda.time;

import java.io.File;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

public abstract class DateTimeZone implements Serializable {
    private static final int MAX_MILLIS = 86399999;
    public static final DateTimeZone UTC = UTCDateTimeZone.INSTANCE;
    private static final AtomicReference<DateTimeZone> cDefault = new AtomicReference();
    private static final AtomicReference<NameProvider> cNameProvider = new AtomicReference();
    private static final AtomicReference<Provider> cProvider = new AtomicReference();
    private static final long serialVersionUID = 5546345482340108586L;
    private final String iID;

    public abstract boolean equals(Object obj);

    public abstract String getNameKey(long j);

    public abstract int getOffset(long j);

    public abstract int getStandardOffset(long j);

    public abstract boolean isFixed();

    public abstract long nextTransition(long j);

    public abstract long previousTransition(long j);

    public static DateTimeZone getDefault() {
        DateTimeZone dateTimeZone = (DateTimeZone) cDefault.get();
        if (dateTimeZone != null) {
            return dateTimeZone;
        }
        try {
            String property = System.getProperty("user.timezone");
            if (property != null) {
                dateTimeZone = forID(property);
            }
        } catch (RuntimeException e) {
        }
        if (dateTimeZone == null) {
            try {
                dateTimeZone = forTimeZone(TimeZone.getDefault());
            } catch (IllegalArgumentException e2) {
            }
        }
        if (dateTimeZone == null) {
            dateTimeZone = UTC;
        }
        if (cDefault.compareAndSet(null, dateTimeZone)) {
            return dateTimeZone;
        }
        return (DateTimeZone) cDefault.get();
    }

    public static void setDefault(DateTimeZone dateTimeZone) throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
        }
        if (dateTimeZone == null) {
            throw new IllegalArgumentException("The datetime zone must not be null");
        }
        cDefault.set(dateTimeZone);
    }

    @FromString
    public static DateTimeZone forID(String str) {
        if (str == null) {
            return getDefault();
        }
        if (str.equals("UTC")) {
            return UTC;
        }
        DateTimeZone zone = getProvider().getZone(str);
        if (zone != null) {
            return zone;
        }
        if (str.startsWith("+") || str.startsWith("-")) {
            int parseOffset = parseOffset(str);
            if (((long) parseOffset) == 0) {
                return UTC;
            }
            return fixedOffsetZone(printOffset(parseOffset), parseOffset);
        }
        throw new IllegalArgumentException("The datetime zone id '" + str + "' is not recognised");
    }

    public static DateTimeZone forOffsetHours(int i) throws IllegalArgumentException {
        return forOffsetHoursMinutes(i, 0);
    }

    public static DateTimeZone forOffsetHoursMinutes(int i, int i2) throws IllegalArgumentException {
        if (i == 0 && i2 == 0) {
            return UTC;
        }
        if (i < -23 || i > 23) {
            throw new IllegalArgumentException("Hours out of range: " + i);
        } else if (i2 < -59 || i2 > 59) {
            throw new IllegalArgumentException("Minutes out of range: " + i2);
        } else if (i <= 0 || i2 >= 0) {
            int i3 = i * 60;
            if (i3 < 0) {
                try {
                    i3 -= Math.abs(i2);
                } catch (ArithmeticException e) {
                    throw new IllegalArgumentException("Offset is too large");
                }
            }
            i3 += i2;
            return forOffsetMillis(FieldUtils.safeMultiply(i3, DateTimeConstants.MILLIS_PER_MINUTE));
        } else {
            throw new IllegalArgumentException("Positive hours must not have negative minutes: " + i2);
        }
    }

    public static DateTimeZone forOffsetMillis(int i) {
        if (i >= -86399999 && i <= MAX_MILLIS) {
            return fixedOffsetZone(printOffset(i), i);
        }
        throw new IllegalArgumentException("Millis out of range: " + i);
    }

    public static DateTimeZone forTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            return getDefault();
        }
        String id = timeZone.getID();
        if (id == null) {
            throw new IllegalArgumentException("The TimeZone id must not be null");
        } else if (id.equals("UTC")) {
            return UTC;
        } else {
            DateTimeZone dateTimeZone = null;
            String convertedId = getConvertedId(id);
            Provider provider = getProvider();
            if (convertedId != null) {
                dateTimeZone = provider.getZone(convertedId);
            }
            if (dateTimeZone == null) {
                dateTimeZone = provider.getZone(id);
            }
            if (dateTimeZone != null) {
                return dateTimeZone;
            }
            if (convertedId == null && (id.startsWith("GMT+") || id.startsWith("GMT-"))) {
                String substring = id.substring(3);
                if (substring.length() > 2) {
                    char charAt = substring.charAt(1);
                    if (charAt > '9' && Character.isDigit(charAt)) {
                        substring = convertToAsciiNumber(substring);
                    }
                }
                int parseOffset = parseOffset(substring);
                if (((long) parseOffset) == 0) {
                    return UTC;
                }
                return fixedOffsetZone(printOffset(parseOffset), parseOffset);
            }
            throw new IllegalArgumentException("The datetime zone id '" + id + "' is not recognised");
        }
    }

    private static String convertToAsciiNumber(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 0; i < stringBuilder.length(); i++) {
            int digit = Character.digit(stringBuilder.charAt(i), 10);
            if (digit >= 0) {
                stringBuilder.setCharAt(i, (char) (digit + 48));
            }
        }
        return stringBuilder.toString();
    }

    private static DateTimeZone fixedOffsetZone(String str, int i) {
        if (i == 0) {
            return UTC;
        }
        return new FixedDateTimeZone(str, null, i, i);
    }

    public static Set<String> getAvailableIDs() {
        return getProvider().getAvailableIDs();
    }

    public static Provider getProvider() {
        Provider provider = (Provider) cProvider.get();
        if (provider != null) {
            return provider;
        }
        provider = getDefaultProvider();
        if (cProvider.compareAndSet(null, provider)) {
            return provider;
        }
        return (Provider) cProvider.get();
    }

    public static void setProvider(Provider provider) throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
        }
        if (provider == null) {
            provider = getDefaultProvider();
        } else {
            validateProvider(provider);
        }
        cProvider.set(provider);
    }

    private static Provider validateProvider(Provider provider) {
        Set availableIDs = provider.getAvailableIDs();
        if (availableIDs == null || availableIDs.size() == 0) {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        } else if (!availableIDs.contains("UTC")) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        } else if (UTC.equals(provider.getZone("UTC"))) {
            return provider;
        } else {
            throw new IllegalArgumentException("Invalid UTC zone provided");
        }
    }

    private static Provider getDefaultProvider() {
        String property;
        try {
            property = System.getProperty("org.joda.time.DateTimeZone.Provider");
            if (property != null) {
                return validateProvider((Provider) Class.forName(property).newInstance());
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (SecurityException e2) {
        }
        try {
            property = System.getProperty("org.joda.time.DateTimeZone.Folder");
            if (property != null) {
                return validateProvider(new ZoneInfoProvider(new File(property)));
            }
        } catch (Throwable e3) {
            throw new RuntimeException(e3);
        } catch (SecurityException e4) {
        }
        try {
            return validateProvider(new ZoneInfoProvider("org/joda/time/tz/data"));
        } catch (Exception e5) {
            e5.printStackTrace();
            return new UTCProvider();
        }
    }

    public static NameProvider getNameProvider() {
        NameProvider nameProvider = (NameProvider) cNameProvider.get();
        if (nameProvider != null) {
            return nameProvider;
        }
        nameProvider = getDefaultNameProvider();
        if (cNameProvider.compareAndSet(null, nameProvider)) {
            return nameProvider;
        }
        return (NameProvider) cNameProvider.get();
    }

    public static void setNameProvider(NameProvider nameProvider) throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
        }
        if (nameProvider == null) {
            nameProvider = getDefaultNameProvider();
        }
        cNameProvider.set(nameProvider);
    }

    private static NameProvider getDefaultNameProvider() {
        NameProvider nameProvider;
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
            nameProvider = property != null ? (NameProvider) Class.forName(property).newInstance() : null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (SecurityException e2) {
            nameProvider = null;
        }
        if (nameProvider == null) {
            return new DefaultNameProvider();
        }
        return nameProvider;
    }

    private static String getConvertedId(String str) {
        return (String) LazyInit.CONVERSION_MAP.get(str);
    }

    private static int parseOffset(String str) {
        return -((int) LazyInit.OFFSET_FORMATTER.parseMillis(str));
    }

    private static String printOffset(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i >= 0) {
            stringBuffer.append('+');
        } else {
            stringBuffer.append('-');
            i = -i;
        }
        int i2 = i / DateTimeConstants.MILLIS_PER_HOUR;
        FormatUtils.appendPaddedInteger(stringBuffer, i2, 2);
        i2 = i - (i2 * DateTimeConstants.MILLIS_PER_HOUR);
        int i3 = i2 / DateTimeConstants.MILLIS_PER_MINUTE;
        stringBuffer.append(':');
        FormatUtils.appendPaddedInteger(stringBuffer, i3, 2);
        i2 -= i3 * DateTimeConstants.MILLIS_PER_MINUTE;
        if (i2 == 0) {
            return stringBuffer.toString();
        }
        i3 = i2 / 1000;
        stringBuffer.append(':');
        FormatUtils.appendPaddedInteger(stringBuffer, i3, 2);
        i2 -= i3 * 1000;
        if (i2 == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append('.');
        FormatUtils.appendPaddedInteger(stringBuffer, i2, 3);
        return stringBuffer.toString();
    }

    protected DateTimeZone(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        this.iID = str;
    }

    @ToString
    public final String getID() {
        return this.iID;
    }

    public final String getShortName(long j) {
        return getShortName(j, null);
    }

    public String getShortName(long j, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        String shortName;
        NameProvider nameProvider = getNameProvider();
        if (nameProvider instanceof DefaultNameProvider) {
            shortName = ((DefaultNameProvider) nameProvider).getShortName(locale, this.iID, nameKey, isStandardOffset(j));
        } else {
            shortName = nameProvider.getShortName(locale, this.iID, nameKey);
        }
        if (shortName == null) {
            return printOffset(getOffset(j));
        }
        return shortName;
    }

    public final String getName(long j) {
        return getName(j, null);
    }

    public String getName(long j, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        String name;
        NameProvider nameProvider = getNameProvider();
        if (nameProvider instanceof DefaultNameProvider) {
            name = ((DefaultNameProvider) nameProvider).getName(locale, this.iID, nameKey, isStandardOffset(j));
        } else {
            name = nameProvider.getName(locale, this.iID, nameKey);
        }
        if (name == null) {
            return printOffset(getOffset(j));
        }
        return name;
    }

    public final int getOffset(ReadableInstant readableInstant) {
        if (readableInstant == null) {
            return getOffset(DateTimeUtils.currentTimeMillis());
        }
        return getOffset(readableInstant.getMillis());
    }

    public boolean isStandardOffset(long j) {
        return getOffset(j) == getStandardOffset(j);
    }

    public int getOffsetFromLocal(long j) {
        long j2 = Long.MAX_VALUE;
        int offset = getOffset(j);
        long j3 = j - ((long) offset);
        int offset2 = getOffset(j3);
        if (offset != offset2) {
            if (offset - offset2 < 0) {
                long nextTransition = nextTransition(j3);
                if (nextTransition == j - ((long) offset)) {
                    nextTransition = Long.MAX_VALUE;
                }
                j3 = nextTransition(j - ((long) offset2));
                if (j3 != j - ((long) offset2)) {
                    j2 = j3;
                }
                if (nextTransition != j2) {
                    return offset;
                }
            }
        } else if (offset >= 0) {
            j2 = previousTransition(j3);
            if (j2 < j3) {
                int offset3 = getOffset(j2);
                if (j3 - j2 <= ((long) (offset3 - offset))) {
                    return offset3;
                }
            }
        }
        return offset2;
    }

    public long convertUTCToLocal(long j) {
        int offset = getOffset(j);
        long j2 = ((long) offset) + j;
        if ((j ^ j2) >= 0 || (((long) offset) ^ j) < 0) {
            return j2;
        }
        throw new ArithmeticException("Adding time zone offset caused overflow");
    }

    public long convertLocalToUTC(long j, boolean z, long j2) {
        int offset = getOffset(j2);
        long j3 = j - ((long) offset);
        return getOffset(j3) == offset ? j3 : convertLocalToUTC(j, z);
    }

    public long convertLocalToUTC(long j, boolean z) {
        int i;
        long j2 = Long.MAX_VALUE;
        int offset = getOffset(j);
        int offset2 = getOffset(j - ((long) offset));
        if (offset != offset2 && (z || offset < 0)) {
            long nextTransition = nextTransition(j - ((long) offset));
            if (nextTransition == j - ((long) offset)) {
                nextTransition = Long.MAX_VALUE;
            }
            long nextTransition2 = nextTransition(j - ((long) offset2));
            if (nextTransition2 != j - ((long) offset2)) {
                j2 = nextTransition2;
            }
            if (nextTransition != j2) {
                if (z) {
                    throw new IllegalInstantException(j, getID());
                }
                i = offset;
                j2 = j - ((long) i);
                if ((j ^ j2) < 0 || (((long) i) ^ j) >= 0) {
                    return j2;
                }
                throw new ArithmeticException("Subtracting time zone offset caused overflow");
            }
        }
        i = offset2;
        j2 = j - ((long) i);
        if ((j ^ j2) < 0) {
        }
        return j2;
    }

    public long getMillisKeepLocal(DateTimeZone dateTimeZone, long j) {
        DateTimeZone dateTimeZone2;
        if (dateTimeZone == null) {
            dateTimeZone2 = getDefault();
        } else {
            dateTimeZone2 = dateTimeZone;
        }
        return dateTimeZone2 == this ? j : dateTimeZone2.convertLocalToUTC(convertUTCToLocal(j), false, j);
    }

    public boolean isLocalDateTimeGap(LocalDateTime localDateTime) {
        if (isFixed()) {
            return false;
        }
        try {
            localDateTime.toDateTime(this);
            return false;
        } catch (IllegalInstantException e) {
            return true;
        }
    }

    public long adjustOffset(long j, boolean z) {
        long j2 = j - 10800000;
        long offset = (long) getOffset(j2);
        long offset2 = (long) getOffset(10800000 + j);
        if (offset <= offset2) {
            return j;
        }
        offset2 = offset - offset2;
        j2 = nextTransition(j2);
        offset = j2 - offset2;
        j2 += offset2;
        if (j < offset || j >= j2) {
            return j;
        }
        if (j - offset < offset2) {
            return z ? j + offset2 : j;
        } else {
            if (z) {
                return j;
            }
            return j - offset2;
        }
    }

    public TimeZone toTimeZone() {
        return TimeZone.getTimeZone(this.iID);
    }

    public int hashCode() {
        return getID().hashCode() + 57;
    }

    public String toString() {
        return getID();
    }

    protected Object writeReplace() throws ObjectStreamException {
        return new Stub(this.iID);
    }
}
