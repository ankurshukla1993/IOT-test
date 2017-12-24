package org.joda.time;

import java.io.Serializable;

public abstract class DateTimeFieldType implements Serializable {
    static final byte CENTURY_OF_ERA = (byte) 3;
    private static final DateTimeFieldType CENTURY_OF_ERA_TYPE = new StandardDateTimeFieldType("centuryOfEra", (byte) 3, DurationFieldType.centuries(), DurationFieldType.eras());
    static final byte CLOCKHOUR_OF_DAY = (byte) 16;
    private static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("clockhourOfDay", (byte) 16, DurationFieldType.hours(), DurationFieldType.days());
    static final byte CLOCKHOUR_OF_HALFDAY = (byte) 15;
    private static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("clockhourOfHalfday", (byte) 15, DurationFieldType.hours(), DurationFieldType.halfdays());
    static final byte DAY_OF_MONTH = (byte) 8;
    private static final DateTimeFieldType DAY_OF_MONTH_TYPE = new StandardDateTimeFieldType("dayOfMonth", (byte) 8, DurationFieldType.days(), DurationFieldType.months());
    static final byte DAY_OF_WEEK = (byte) 12;
    private static final DateTimeFieldType DAY_OF_WEEK_TYPE = new StandardDateTimeFieldType("dayOfWeek", (byte) 12, DurationFieldType.days(), DurationFieldType.weeks());
    static final byte DAY_OF_YEAR = (byte) 6;
    private static final DateTimeFieldType DAY_OF_YEAR_TYPE = new StandardDateTimeFieldType("dayOfYear", (byte) 6, DurationFieldType.days(), DurationFieldType.years());
    static final byte ERA = (byte) 1;
    private static final DateTimeFieldType ERA_TYPE = new StandardDateTimeFieldType("era", (byte) 1, DurationFieldType.eras(), null);
    static final byte HALFDAY_OF_DAY = (byte) 13;
    private static final DateTimeFieldType HALFDAY_OF_DAY_TYPE = new StandardDateTimeFieldType("halfdayOfDay", (byte) 13, DurationFieldType.halfdays(), DurationFieldType.days());
    static final byte HOUR_OF_DAY = (byte) 17;
    private static final DateTimeFieldType HOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("hourOfDay", (byte) 17, DurationFieldType.hours(), DurationFieldType.days());
    static final byte HOUR_OF_HALFDAY = (byte) 14;
    private static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("hourOfHalfday", (byte) 14, DurationFieldType.hours(), DurationFieldType.halfdays());
    static final byte MILLIS_OF_DAY = (byte) 22;
    private static final DateTimeFieldType MILLIS_OF_DAY_TYPE = new StandardDateTimeFieldType("millisOfDay", (byte) 22, DurationFieldType.millis(), DurationFieldType.days());
    static final byte MILLIS_OF_SECOND = (byte) 23;
    private static final DateTimeFieldType MILLIS_OF_SECOND_TYPE = new StandardDateTimeFieldType("millisOfSecond", (byte) 23, DurationFieldType.millis(), DurationFieldType.seconds());
    static final byte MINUTE_OF_DAY = (byte) 18;
    private static final DateTimeFieldType MINUTE_OF_DAY_TYPE = new StandardDateTimeFieldType("minuteOfDay", (byte) 18, DurationFieldType.minutes(), DurationFieldType.days());
    static final byte MINUTE_OF_HOUR = (byte) 19;
    private static final DateTimeFieldType MINUTE_OF_HOUR_TYPE = new StandardDateTimeFieldType("minuteOfHour", (byte) 19, DurationFieldType.minutes(), DurationFieldType.hours());
    static final byte MONTH_OF_YEAR = (byte) 7;
    private static final DateTimeFieldType MONTH_OF_YEAR_TYPE = new StandardDateTimeFieldType("monthOfYear", (byte) 7, DurationFieldType.months(), DurationFieldType.years());
    static final byte SECOND_OF_DAY = (byte) 20;
    private static final DateTimeFieldType SECOND_OF_DAY_TYPE = new StandardDateTimeFieldType("secondOfDay", (byte) 20, DurationFieldType.seconds(), DurationFieldType.days());
    static final byte SECOND_OF_MINUTE = (byte) 21;
    private static final DateTimeFieldType SECOND_OF_MINUTE_TYPE = new StandardDateTimeFieldType("secondOfMinute", (byte) 21, DurationFieldType.seconds(), DurationFieldType.minutes());
    static final byte WEEKYEAR = (byte) 10;
    static final byte WEEKYEAR_OF_CENTURY = (byte) 9;
    private static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("weekyearOfCentury", (byte) 9, DurationFieldType.weekyears(), DurationFieldType.centuries());
    private static final DateTimeFieldType WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekyear", (byte) 10, DurationFieldType.weekyears(), null);
    static final byte WEEK_OF_WEEKYEAR = (byte) 11;
    private static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekOfWeekyear", (byte) 11, DurationFieldType.weeks(), DurationFieldType.weekyears());
    static final byte YEAR = (byte) 5;
    static final byte YEAR_OF_CENTURY = (byte) 4;
    private static final DateTimeFieldType YEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("yearOfCentury", (byte) 4, DurationFieldType.years(), DurationFieldType.centuries());
    static final byte YEAR_OF_ERA = (byte) 2;
    private static final DateTimeFieldType YEAR_OF_ERA_TYPE = new StandardDateTimeFieldType("yearOfEra", (byte) 2, DurationFieldType.years(), DurationFieldType.eras());
    private static final DateTimeFieldType YEAR_TYPE = new StandardDateTimeFieldType("year", (byte) 5, DurationFieldType.years(), null);
    private static final long serialVersionUID = -42615285973990L;
    private final String iName;

    private static class StandardDateTimeFieldType extends DateTimeFieldType {
        private static final long serialVersionUID = -9937958251642L;
        private final byte iOrdinal;
        private final transient DurationFieldType iRangeType;
        private final transient DurationFieldType iUnitType;

        StandardDateTimeFieldType(String str, byte b, DurationFieldType durationFieldType, DurationFieldType durationFieldType2) {
            super(str);
            this.iOrdinal = b;
            this.iUnitType = durationFieldType;
            this.iRangeType = durationFieldType2;
        }

        public DurationFieldType getDurationType() {
            return this.iUnitType;
        }

        public DurationFieldType getRangeDurationType() {
            return this.iRangeType;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StandardDateTimeFieldType)) {
                return false;
            }
            if (this.iOrdinal != ((StandardDateTimeFieldType) obj).iOrdinal) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1 << this.iOrdinal;
        }

        public DateTimeField getField(Chronology chronology) {
            Chronology chronology2 = DateTimeUtils.getChronology(chronology);
            switch (this.iOrdinal) {
                case (byte) 1:
                    return chronology2.era();
                case (byte) 2:
                    return chronology2.yearOfEra();
                case (byte) 3:
                    return chronology2.centuryOfEra();
                case (byte) 4:
                    return chronology2.yearOfCentury();
                case (byte) 5:
                    return chronology2.year();
                case (byte) 6:
                    return chronology2.dayOfYear();
                case (byte) 7:
                    return chronology2.monthOfYear();
                case (byte) 8:
                    return chronology2.dayOfMonth();
                case (byte) 9:
                    return chronology2.weekyearOfCentury();
                case (byte) 10:
                    return chronology2.weekyear();
                case (byte) 11:
                    return chronology2.weekOfWeekyear();
                case (byte) 12:
                    return chronology2.dayOfWeek();
                case (byte) 13:
                    return chronology2.halfdayOfDay();
                case (byte) 14:
                    return chronology2.hourOfHalfday();
                case (byte) 15:
                    return chronology2.clockhourOfHalfday();
                case (byte) 16:
                    return chronology2.clockhourOfDay();
                case (byte) 17:
                    return chronology2.hourOfDay();
                case (byte) 18:
                    return chronology2.minuteOfDay();
                case (byte) 19:
                    return chronology2.minuteOfHour();
                case (byte) 20:
                    return chronology2.secondOfDay();
                case (byte) 21:
                    return chronology2.secondOfMinute();
                case (byte) 22:
                    return chronology2.millisOfDay();
                case (byte) 23:
                    return chronology2.millisOfSecond();
                default:
                    throw new InternalError();
            }
        }

        private Object readResolve() {
            switch (this.iOrdinal) {
                case (byte) 1:
                    return DateTimeFieldType.ERA_TYPE;
                case (byte) 2:
                    return DateTimeFieldType.YEAR_OF_ERA_TYPE;
                case (byte) 3:
                    return DateTimeFieldType.CENTURY_OF_ERA_TYPE;
                case (byte) 4:
                    return DateTimeFieldType.YEAR_OF_CENTURY_TYPE;
                case (byte) 5:
                    return DateTimeFieldType.YEAR_TYPE;
                case (byte) 6:
                    return DateTimeFieldType.DAY_OF_YEAR_TYPE;
                case (byte) 7:
                    return DateTimeFieldType.MONTH_OF_YEAR_TYPE;
                case (byte) 8:
                    return DateTimeFieldType.DAY_OF_MONTH_TYPE;
                case (byte) 9:
                    return DateTimeFieldType.WEEKYEAR_OF_CENTURY_TYPE;
                case (byte) 10:
                    return DateTimeFieldType.WEEKYEAR_TYPE;
                case (byte) 11:
                    return DateTimeFieldType.WEEK_OF_WEEKYEAR_TYPE;
                case (byte) 12:
                    return DateTimeFieldType.DAY_OF_WEEK_TYPE;
                case (byte) 13:
                    return DateTimeFieldType.HALFDAY_OF_DAY_TYPE;
                case (byte) 14:
                    return DateTimeFieldType.HOUR_OF_HALFDAY_TYPE;
                case (byte) 15:
                    return DateTimeFieldType.CLOCKHOUR_OF_HALFDAY_TYPE;
                case (byte) 16:
                    return DateTimeFieldType.CLOCKHOUR_OF_DAY_TYPE;
                case (byte) 17:
                    return DateTimeFieldType.HOUR_OF_DAY_TYPE;
                case (byte) 18:
                    return DateTimeFieldType.MINUTE_OF_DAY_TYPE;
                case (byte) 19:
                    return DateTimeFieldType.MINUTE_OF_HOUR_TYPE;
                case (byte) 20:
                    return DateTimeFieldType.SECOND_OF_DAY_TYPE;
                case (byte) 21:
                    return DateTimeFieldType.SECOND_OF_MINUTE_TYPE;
                case (byte) 22:
                    return DateTimeFieldType.MILLIS_OF_DAY_TYPE;
                case (byte) 23:
                    return DateTimeFieldType.MILLIS_OF_SECOND_TYPE;
                default:
                    return this;
            }
        }
    }

    public abstract DurationFieldType getDurationType();

    public abstract DateTimeField getField(Chronology chronology);

    public abstract DurationFieldType getRangeDurationType();

    protected DateTimeFieldType(String str) {
        this.iName = str;
    }

    public static DateTimeFieldType millisOfSecond() {
        return MILLIS_OF_SECOND_TYPE;
    }

    public static DateTimeFieldType millisOfDay() {
        return MILLIS_OF_DAY_TYPE;
    }

    public static DateTimeFieldType secondOfMinute() {
        return SECOND_OF_MINUTE_TYPE;
    }

    public static DateTimeFieldType secondOfDay() {
        return SECOND_OF_DAY_TYPE;
    }

    public static DateTimeFieldType minuteOfHour() {
        return MINUTE_OF_HOUR_TYPE;
    }

    public static DateTimeFieldType minuteOfDay() {
        return MINUTE_OF_DAY_TYPE;
    }

    public static DateTimeFieldType hourOfDay() {
        return HOUR_OF_DAY_TYPE;
    }

    public static DateTimeFieldType clockhourOfDay() {
        return CLOCKHOUR_OF_DAY_TYPE;
    }

    public static DateTimeFieldType hourOfHalfday() {
        return HOUR_OF_HALFDAY_TYPE;
    }

    public static DateTimeFieldType clockhourOfHalfday() {
        return CLOCKHOUR_OF_HALFDAY_TYPE;
    }

    public static DateTimeFieldType halfdayOfDay() {
        return HALFDAY_OF_DAY_TYPE;
    }

    public static DateTimeFieldType dayOfWeek() {
        return DAY_OF_WEEK_TYPE;
    }

    public static DateTimeFieldType dayOfMonth() {
        return DAY_OF_MONTH_TYPE;
    }

    public static DateTimeFieldType dayOfYear() {
        return DAY_OF_YEAR_TYPE;
    }

    public static DateTimeFieldType weekOfWeekyear() {
        return WEEK_OF_WEEKYEAR_TYPE;
    }

    public static DateTimeFieldType weekyear() {
        return WEEKYEAR_TYPE;
    }

    public static DateTimeFieldType weekyearOfCentury() {
        return WEEKYEAR_OF_CENTURY_TYPE;
    }

    public static DateTimeFieldType monthOfYear() {
        return MONTH_OF_YEAR_TYPE;
    }

    public static DateTimeFieldType year() {
        return YEAR_TYPE;
    }

    public static DateTimeFieldType yearOfEra() {
        return YEAR_OF_ERA_TYPE;
    }

    public static DateTimeFieldType yearOfCentury() {
        return YEAR_OF_CENTURY_TYPE;
    }

    public static DateTimeFieldType centuryOfEra() {
        return CENTURY_OF_ERA_TYPE;
    }

    public static DateTimeFieldType era() {
        return ERA_TYPE;
    }

    public String getName() {
        return this.iName;
    }

    public boolean isSupported(Chronology chronology) {
        return getField(chronology).isSupported();
    }

    public String toString() {
        return getName();
    }
}
