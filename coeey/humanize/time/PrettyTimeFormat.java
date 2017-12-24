package humanize.time;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import humanize.spi.FormatProvider;
import humanize.text.FormatFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeFormat;
import org.ocpsoft.prettytime.TimeUnit;

public class PrettyTimeFormat extends Format implements FormatProvider {
    private static final long serialVersionUID = -1398312177396430967L;
    private final Locale locale;
    private transient PrettyTime prettyTime;

    static class C23721 implements FormatFactory {
        C23721() {
        }

        public Format getFormat(String name, String args, Locale locale) {
            return new PrettyTimeFormat(locale);
        }
    }

    public static FormatFactory factory() {
        return new C23721();
    }

    public PrettyTimeFormat() {
        this(Locale.getDefault());
    }

    public PrettyTimeFormat(Locale locale) {
        this.prettyTime = new PrettyTime(locale);
        this.locale = locale;
    }

    public Duration approximateDuration(Date then) {
        return this.prettyTime.approximateDuration(then);
    }

    public List<Duration> calculatePreciseDuration(Date then) {
        return this.prettyTime.calculatePreciseDuration(then);
    }

    public List<TimeUnit> clearUnits() {
        return this.prettyTime.clearUnits();
    }

    public String format(Date ref, Date then) {
        return this.prettyTime.format(DurationHelper.calculateDuration(ref, then, this.prettyTime.getUnits()));
    }

    public String format(Date ref, Date then, long precision) {
        List retained = retainPrecision(DurationHelper.calculatePreciseDuration(ref, then, this.prettyTime.getUnits()), precision);
        return retained.isEmpty() ? "" : this.prettyTime.format(retained);
    }

    public String format(Duration duration) {
        return this.prettyTime.format(duration);
    }

    public String format(List<Duration> durations) {
        return this.prettyTime.format((List) durations);
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (Duration.class.isAssignableFrom(obj.getClass())) {
            return toAppendTo.append(this.prettyTime.format((Duration) obj));
        }
        if (Date.class.isAssignableFrom(obj.getClass())) {
            return toAppendTo.append(this.prettyTime.format((Date) obj));
        }
        if (List.class.isAssignableFrom(obj.getClass())) {
            return toAppendTo.append(this.prettyTime.format((List) obj));
        }
        if (Number.class.isAssignableFrom(obj.getClass())) {
            return toAppendTo.append(this.prettyTime.format(new Date(((Number) obj).longValue())));
        }
        throw new IllegalArgumentException(String.format("Class %s is not suitable for PrettyTimeFormat", new Object[]{obj.getClass()}));
    }

    public String formatUnrounded(Date then) {
        return this.prettyTime.formatUnrounded(then);
    }

    public String formatUnrounded(Duration duration) {
        return this.prettyTime.formatUnrounded(duration);
    }

    public FormatFactory getFactory() {
        return factory();
    }

    public TimeFormat getFormat(TimeUnit unit) {
        return this.prettyTime.getFormat(unit);
    }

    public String getFormatName() {
        return "prettytime";
    }

    public PrettyTime getPrettyTime() {
        return this.prettyTime;
    }

    public List<TimeUnit> getUnits() {
        return this.prettyTime.getUnits();
    }

    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    public PrettyTime registerUnit(TimeUnit unit, TimeFormat format) {
        return this.prettyTime.registerUnit(unit, format);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        this.prettyTime = new PrettyTime(this.locale);
    }

    private List<Duration> retainPrecision(List<Duration> durations, final long precision) {
        return ImmutableList.copyOf(Iterables.filter(durations, new Predicate<Duration>() {
            public boolean apply(Duration it) {
                return it.getUnit().getMillisPerUnit() >= precision;
            }
        }));
    }
}
