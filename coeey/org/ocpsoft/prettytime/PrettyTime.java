package org.ocpsoft.prettytime;

import humanize.util.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.ocpsoft.prettytime.impl.DurationImpl;
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat;
import org.ocpsoft.prettytime.impl.ResourcesTimeUnit;
import org.ocpsoft.prettytime.units.Century;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Decade;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millennium;
import org.ocpsoft.prettytime.units.Millisecond;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Month;
import org.ocpsoft.prettytime.units.Second;
import org.ocpsoft.prettytime.units.TimeUnitComparator;
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Year;

public class PrettyTime {
    private volatile Locale locale;
    private List<TimeUnit> mCachedUnits;
    private volatile Date reference;
    private volatile Map<TimeUnit, TimeFormat> units;

    public PrettyTime() {
        this.locale = Locale.getDefault();
        this.units = new LinkedHashMap();
        initTimeUnits();
    }

    public PrettyTime(Date reference) {
        this();
        setReference(reference);
    }

    public PrettyTime(Locale locale) {
        this.locale = Locale.getDefault();
        this.units = new LinkedHashMap();
        setLocale(locale);
        initTimeUnits();
    }

    public PrettyTime(Date reference, Locale locale) {
        this(locale);
        setReference(reference);
    }

    public Duration approximateDuration(Date then) {
        if (then == null) {
            throw new IllegalArgumentException("Date to approximate must not be null.");
        }
        Date ref = this.reference;
        if (ref == null) {
            ref = new Date();
        }
        return calculateDuration(then.getTime() - ref.getTime());
    }

    private void initTimeUnits() {
        addUnit(new JustNow());
        addUnit(new Millisecond());
        addUnit(new Second());
        addUnit(new Minute());
        addUnit(new Hour());
        addUnit(new Day());
        addUnit(new Week());
        addUnit(new Month());
        addUnit(new Year());
        addUnit(new Decade());
        addUnit(new Century());
        addUnit(new Millennium());
    }

    private void addUnit(ResourcesTimeUnit unit) {
        registerUnit(unit, new ResourcesTimeFormat(unit));
    }

    private Duration calculateDuration(long difference) {
        long absoluteDifference = Math.abs(difference);
        List<TimeUnit> localUnits = getUnits();
        DurationImpl result = new DurationImpl();
        int i = 0;
        while (i < localUnits.size()) {
            TimeUnit unit = (TimeUnit) localUnits.get(i);
            long millisPerUnit = Math.abs(unit.getMillisPerUnit());
            long quantity = Math.abs(unit.getMaxQuantity());
            boolean isLastUnit = i == localUnits.size() + -1;
            if (0 == quantity && !isLastUnit) {
                quantity = ((TimeUnit) localUnits.get(i + 1)).getMillisPerUnit() / unit.getMillisPerUnit();
            }
            if (millisPerUnit * quantity > absoluteDifference || isLastUnit) {
                result.setUnit(unit);
                if (millisPerUnit > absoluteDifference) {
                    result.setQuantity(getSign(difference));
                    result.setDelta(0);
                } else {
                    result.setQuantity(difference / millisPerUnit);
                    result.setDelta(difference - (result.getQuantity() * millisPerUnit));
                }
                return result;
            }
            i++;
        }
        return result;
    }

    private long getSign(long difference) {
        if (0 > difference) {
            return -1;
        }
        return 1;
    }

    public List<Duration> calculatePreciseDuration(Date then) {
        if (then == null) {
            throw new IllegalArgumentException("Date to calculate must not be null.");
        }
        if (this.reference == null) {
            this.reference = new Date();
        }
        List<Duration> result = new ArrayList();
        Duration duration = calculateDuration(then.getTime() - this.reference.getTime());
        result.add(duration);
        while (0 != duration.getDelta()) {
            duration = calculateDuration(duration.getDelta());
            result.add(duration);
        }
        return result;
    }

    public String format(Date then) {
        if (then != null) {
            return format(approximateDuration(then));
        }
        throw new IllegalArgumentException("Date to format must not be null.");
    }

    public String format(Calendar then) {
        if (then != null) {
            return format(then.getTime());
        }
        throw new IllegalArgumentException("Provided Calendar must not be null.");
    }

    public String formatUnrounded(Date then) {
        if (then != null) {
            return formatUnrounded(approximateDuration(then));
        }
        throw new IllegalArgumentException("Date to format must not be null.");
    }

    public String format(Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration to format must not be null.");
        }
        TimeFormat format = getFormat(duration.getUnit());
        return format.decorate(duration, format.format(duration));
    }

    public String formatUnrounded(Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration to format must not be null.");
        }
        TimeFormat format = getFormat(duration.getUnit());
        return format.decorateUnrounded(duration, format.formatUnrounded(duration));
    }

    public String format(List<Duration> durations) {
        if (durations == null) {
            throw new IllegalArgumentException("Duration list must not be null.");
        } else if (durations == null) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder();
            Duration duration = null;
            TimeFormat format = null;
            int i = 0;
            while (i < durations.size()) {
                duration = (Duration) durations.get(i);
                format = getFormat(duration.getUnit());
                if (i == durations.size() + -1) {
                    builder.append(format.format(duration));
                } else {
                    builder.append(format.formatUnrounded(duration));
                    builder.append(Constants.SPACE);
                }
                i++;
            }
            return format.decorateUnrounded(duration, builder.toString());
        }
    }

    public String formatApproximateDuration(Date date) {
        return formatDuration(approximateDuration(date));
    }

    public String formatDuration(Duration duration) {
        return getFormat(duration.getUnit()).format(duration);
    }

    public TimeFormat getFormat(TimeUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Time unit must not be null.");
        } else if (this.units.get(unit) != null) {
            return (TimeFormat) this.units.get(unit);
        } else {
            return null;
        }
    }

    public Date getReference() {
        return this.reference;
    }

    public PrettyTime setReference(Date timestamp) {
        this.reference = timestamp;
        return this;
    }

    public List<TimeUnit> getUnits() {
        if (this.mCachedUnits == null) {
            List<TimeUnit> result = new ArrayList(this.units.keySet());
            Collections.sort(result, new TimeUnitComparator());
            this.mCachedUnits = Collections.unmodifiableList(result);
        }
        return this.mCachedUnits;
    }

    public <UNIT extends TimeUnit> UNIT getUnit(Class<UNIT> unitType) {
        if (unitType == null) {
            throw new IllegalArgumentException("Unit type to get must not be null.");
        }
        for (TimeUnit unit : this.units.keySet()) {
            if (unitType.isAssignableFrom(unit.getClass())) {
                return unit;
            }
        }
        return null;
    }

    public PrettyTime registerUnit(TimeUnit unit, TimeFormat format) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit to register must not be null.");
        } else if (format == null) {
            throw new IllegalArgumentException("Format to register must not be null.");
        } else {
            this.mCachedUnits = null;
            this.units.put(unit, format);
            if (unit instanceof LocaleAware) {
                ((LocaleAware) unit).setLocale(this.locale);
            }
            if (format instanceof LocaleAware) {
                ((LocaleAware) format).setLocale(this.locale);
            }
            return this;
        }
    }

    public <UNIT extends TimeUnit> TimeFormat removeUnit(Class<UNIT> unitType) {
        if (unitType == null) {
            throw new IllegalArgumentException("Unit type to remove must not be null.");
        }
        for (TimeUnit unit : this.units.keySet()) {
            if (unitType.isAssignableFrom(unit.getClass())) {
                this.mCachedUnits = null;
                return (TimeFormat) this.units.remove(unit);
            }
        }
        return null;
    }

    public TimeFormat removeUnit(TimeUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit to remove must not be null.");
        }
        this.mCachedUnits = null;
        return (TimeFormat) this.units.remove(unit);
    }

    public Locale getLocale() {
        return this.locale;
    }

    public PrettyTime setLocale(Locale locale) {
        this.locale = locale;
        for (TimeUnit unit : this.units.keySet()) {
            if (unit instanceof LocaleAware) {
                ((LocaleAware) unit).setLocale(locale);
            }
        }
        for (TimeFormat format : this.units.values()) {
            if (format instanceof LocaleAware) {
                ((LocaleAware) format).setLocale(locale);
            }
        }
        return this;
    }

    public String toString() {
        return "PrettyTime [reference=" + this.reference + ", locale=" + this.locale + "]";
    }

    public List<TimeUnit> clearUnits() {
        List<TimeUnit> result = getUnits();
        this.mCachedUnits = null;
        this.units.clear();
        return result;
    }
}
