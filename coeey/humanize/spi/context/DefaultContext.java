package humanize.spi.context;

import humanize.spi.MessageFormat;
import humanize.spi.cache.CacheProvider;
import humanize.text.MaskFormat;
import humanize.time.PrettyTimeFormat;
import humanize.util.Constants;
import humanize.util.UTF8Control;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;

public class DefaultContext implements Context, StandardContext {
    private static final String BUNDLE_LOCATION = "i18n.Humanize";
    private static final String CURRENCY = "currency";
    private static final String DATE_FORMAT = "date";
    private static final String DATE_TIME_FORMAT = "date.time";
    private static final String DECIMAL = "decimal";
    private static final String DIGITS = "digits";
    private static final String MASK = "mask";
    private static final String NUMBER = "number";
    private static final String ORDINAL_SUFFIXES = "ordinal.suffixes";
    private static final String PERCENT = "percent";
    private static final String PRETTY_TIME = "pretty.time";
    private static final String SIMPLE_DATE = "simple.date";
    private static final String TIME_SUFFIXES = "time.suffixes";
    private static final CacheProvider sharedCache = loadCacheProvider();
    private final CacheProvider localCache;
    private Locale locale;

    class C23611 implements Callable<ResourceBundle> {
        C23611() {
        }

        public ResourceBundle call() throws Exception {
            return ResourceBundle.getBundle(DefaultContext.BUNDLE_LOCATION, DefaultContext.this.locale, new UTF8Control());
        }
    }

    class C23622 implements Callable<DecimalFormat> {
        C23622() {
        }

        public DecimalFormat call() throws Exception {
            return (DecimalFormat) NumberFormat.getCurrencyInstance(DefaultContext.this.locale);
        }
    }

    class C23666 implements Callable<DecimalFormat> {
        C23666() {
        }

        public DecimalFormat call() throws Exception {
            return (DecimalFormat) DecimalFormat.getInstance(DefaultContext.this.locale);
        }
    }

    class C23677 implements Callable<MaskFormat> {
        C23677() {
        }

        public MaskFormat call() throws Exception {
            return new MaskFormat("");
        }
    }

    class C23688 implements Callable<NumberFormat> {
        C23688() {
        }

        public NumberFormat call() throws Exception {
            return NumberFormat.getInstance(DefaultContext.this.locale);
        }
    }

    class C23699 implements Callable<DecimalFormat> {
        C23699() {
        }

        public DecimalFormat call() throws Exception {
            return (DecimalFormat) NumberFormat.getPercentInstance(DefaultContext.this.locale);
        }
    }

    private static CacheProvider loadCacheProvider() {
        Iterator i$ = ServiceLoader.load(CacheProvider.class).iterator();
        if (i$.hasNext()) {
            return (CacheProvider) i$.next();
        }
        throw new RuntimeException("No CacheProvider was found");
    }

    public DefaultContext() {
        this(Locale.getDefault());
    }

    public DefaultContext(Locale locale) {
        this.localCache = loadCacheProvider();
        setLocale(locale);
    }

    public String digitStrings(int index) {
        return getStringByIndex(DIGITS, index);
    }

    public String formatDate(int style, Date value) {
        return getDateFormat(style).format(value);
    }

    public String formatDateTime(Date date) {
        return getDateTimeFormat().format(date);
    }

    public String formatDateTime(int dateStyle, int timeStyle, Date date) {
        return getDateTimeFormat(dateStyle, timeStyle).format(date);
    }

    public String formatDecimal(Number value) {
        return getNumberFormat().format(value);
    }

    public String formatMessage(String key, Object... args) {
        MessageFormat fmt = getMessageFormat();
        fmt.applyPattern(getBundle().getString(key));
        return fmt.render(args);
    }

    public String formatRelativeDate(Date reference, Date duration) {
        return getPrettyTimeFormat().format(reference, duration);
    }

    public String formatRelativeDate(Date reference, Date duration, long precision) {
        return getPrettyTimeFormat().format(reference, duration, precision);
    }

    public ResourceBundle getBundle() {
        return sharedCache.getBundle(this.locale, new C23611());
    }

    public DecimalFormat getCurrencyFormat() {
        return (DecimalFormat) sharedCache.getFormat("currency", this.locale, new C23622());
    }

    public DateFormat getDateFormat(final int style) {
        return (DateFormat) this.localCache.getFormat("date" + style, this.locale, new Callable<DateFormat>() {
            public DateFormat call() throws Exception {
                return DateFormat.getDateInstance(style, DefaultContext.this.locale);
            }
        });
    }

    public DateFormat getDateFormat(final String pattern) {
        return (DateFormat) this.localCache.getFormat(SIMPLE_DATE + pattern.hashCode(), this.locale, new Callable<DateFormat>() {
            public DateFormat call() throws Exception {
                return new SimpleDateFormat(pattern, DefaultContext.this.locale);
            }
        });
    }

    public DateFormat getDateTimeFormat() {
        return getDateTimeFormat(3, 3);
    }

    public DateFormat getDateTimeFormat(final int dateStyle, final int timeStyle) {
        return (DateFormat) this.localCache.getFormat(DATE_TIME_FORMAT + dateStyle + timeStyle, this.locale, new Callable<DateFormat>() {
            public DateFormat call() throws Exception {
                return DateFormat.getDateTimeInstance(dateStyle, timeStyle, DefaultContext.this.locale);
            }
        });
    }

    public DecimalFormat getDecimalFormat() {
        return (DecimalFormat) this.localCache.getFormat(DECIMAL, this.locale, new C23666());
    }

    public Locale getLocale() {
        return this.locale;
    }

    public MaskFormat getMaskFormat() {
        return (MaskFormat) this.localCache.getFormat(MASK, Locale.ROOT, new C23677());
    }

    public String getMessage(String key) {
        return getBundle().getString(key);
    }

    public MessageFormat getMessageFormat() {
        return new MessageFormat("", this.locale);
    }

    public NumberFormat getNumberFormat() {
        return (NumberFormat) sharedCache.getFormat(NUMBER, this.locale, new C23688());
    }

    public DecimalFormat getPercentFormat() {
        return (DecimalFormat) sharedCache.getFormat(PERCENT, this.locale, new C23699());
    }

    public PrettyTimeFormat getPrettyTimeFormat() {
        return (PrettyTimeFormat) sharedCache.getFormat(PRETTY_TIME, this.locale, new Callable<PrettyTimeFormat>() {
            public PrettyTimeFormat call() throws Exception {
                return new PrettyTimeFormat(DefaultContext.this.locale);
            }
        });
    }

    public String ordinalSuffix(int index) {
        return getStringByIndex(ORDINAL_SUFFIXES, index);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String timeSuffix(int index) {
        return getStringByIndex(TIME_SUFFIXES, index);
    }

    protected String getStringByIndex(String cacheName, int index) {
        return getStrings(cacheName)[index];
    }

    protected Collection<String> getStringList(String cacheName) {
        return Arrays.asList(getStrings(cacheName));
    }

    protected String[] getStrings(final String cacheName) {
        return sharedCache.getStrings(cacheName, this.locale, new Callable<String[]>() {
            public String[] call() throws Exception {
                return DefaultContext.this.getBundle().getString(cacheName).split(Constants.SPACE);
            }
        });
    }
}
