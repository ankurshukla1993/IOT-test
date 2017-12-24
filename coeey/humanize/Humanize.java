package humanize;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import com.lifesense.ble.bean.DeviceTypeConstants;
import humanize.spi.Expose;
import humanize.spi.MessageFormat;
import humanize.spi.context.ContextFactory;
import humanize.spi.context.DefaultContext;
import humanize.spi.context.DefaultContextFactory;
import humanize.text.MaskFormat;
import humanize.text.util.InterpolationHelper;
import humanize.time.Pace;
import humanize.time.Pace.Accuracy;
import humanize.time.PrettyTimeFormat;
import humanize.time.TimeMillis;
import humanize.util.Constants;
import humanize.util.Constants.TimeStyle;
import humanize.util.Parameters.PaceParameters;
import humanize.util.Parameters.PluralizeParams;
import humanize.util.Parameters.SlugifyParams;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.BreakIterator;
import java.text.ChoiceFormat;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import javax.xml.bind.DatatypeConverter;
import me.xuender.unidecode.Unidecode;

public final class Humanize {
    private static final ThreadLocal<DefaultContext> context = new 1();
    private static final ContextFactory contextFactory = loadContextFactory();

    public static String binaryPrefix(Number value) {
        return prefix(value, 1024, Constants.binPrefixes);
    }

    @Expose
    public static String binaryPrefix(Number value, Locale locale) {
        return (String) withinLocale(new 2(value), locale);
    }

    public static String camelize(String text) {
        return camelize(text, false);
    }

    public static String camelize(String text, boolean capitalizeFirstChar) {
        StringBuilder sb = new StringBuilder();
        String[] tokens = text.split("[\\.\\s_-]+");
        if (tokens.length >= 2) {
            for (String token : tokens) {
                sb.append(capitalize(token));
            }
            return capitalizeFirstChar ? sb.toString() : sb.substring(0, 1).toLowerCase(currentLocale()) + sb.substring(1);
        } else if (capitalizeFirstChar) {
            return capitalize(text);
        } else {
            return text;
        }
    }

    public static String camelize(String text, boolean capitalizeFirstChar, Locale locale) {
        return (String) withinLocale(new 3(text, capitalizeFirstChar), locale);
    }

    @Expose
    public static String camelize(String text, Locale locale) {
        return camelize(text, false, locale);
    }

    public static String capitalize(String text) {
        String tmp = text.trim();
        int len = tmp.length();
        if (len == 0) {
            return text;
        }
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            if (Character.isLetter(tmp.charAt(i))) {
                Locale locale = currentLocale();
                int lc = i + 1;
                if (i > 0) {
                    sb.append(tmp.substring(0, i));
                }
                sb.append(tmp.substring(i, lc).toUpperCase(locale));
                sb.append(tmp.substring(lc).toLowerCase(locale));
                if (sb.length() != 0) {
                    tmp = sb.toString();
                }
                return tmp;
            }
        }
        if (sb.length() != 0) {
            tmp = sb.toString();
        }
        return tmp;
    }

    @Expose
    public static String capitalize(String text, Locale locale) {
        return (String) withinLocale(new 4(text), locale);
    }

    public static DateFormat dateFormat(String pattern) {
        return ((DefaultContext) context.get()).getDateFormat(pattern);
    }

    public static DateFormat dateFormat(String pattern, Locale locale) {
        return (DateFormat) withinLocale(new 5(pattern), locale);
    }

    @Expose
    public static String decamelize(String words) {
        return Constants.SPLIT_CAMEL.matcher(words).replaceAll(Constants.SPACE);
    }

    public static String decamelize(String words, String replacement) {
        return Constants.SPLIT_CAMEL.matcher(words).replaceAll(replacement);
    }

    public static DecimalFormat decimalFormat(String pattern) {
        DecimalFormat decFmt = ((DefaultContext) context.get()).getDecimalFormat();
        decFmt.applyPattern(pattern);
        return decFmt;
    }

    public static DecimalFormat decimalFormat(String pattern, Locale locale) {
        return (DecimalFormat) withinLocale(new 6(pattern), locale);
    }

    public static String duration(Number seconds) {
        return duration(seconds, TimeStyle.STANDARD);
    }

    public static String duration(Number seconds, Locale locale) {
        return (String) withinLocale(new 7(seconds), locale);
    }

    public static String duration(Number seconds, TimeStyle style) {
        int s = seconds.intValue();
        boolean neg = s < 0;
        s = Math.abs(s);
        return style.format((DefaultContext) context.get(), neg, (s / 3600) % 60, (s / 60) % 60, s % 60);
    }

    public static String duration(Number seconds, TimeStyle style, Locale locale) {
        return (String) withinLocale(new 8(seconds, style), locale);
    }

    public static String fixLength(String text, int charsNum, char paddingChar) {
        return fixLength(text, charsNum, paddingChar, false);
    }

    public static String fixLength(String text, int charsNum, char paddingChar, boolean left) {
        boolean z;
        String str;
        if (charsNum > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "The number of characters must be greater than zero.");
        if (text == null) {
            str = "";
        } else {
            str = text;
        }
        String str2 = "%%%ss";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(left ? charsNum : -charsNum);
        return String.format(String.format(str2, objArr), new Object[]{str}).substring(0, charsNum).replace(' ', paddingChar);
    }

    public static String format(Locale locale, String pattern, Object... args) {
        return (String) withinLocale(new 9(pattern, args), locale);
    }

    public static String format(String pattern, Object... args) {
        return messageFormat(pattern).render(args);
    }

    public static String formatCurrency(Number value) {
        DecimalFormat decf = ((DefaultContext) context.get()).getCurrencyFormat();
        return stripZeros(decf, decf.format(value));
    }

    public static String formatCurrency(Number value, Locale locale) {
        return (String) withinLocale(new 10(value), locale);
    }

    public static String formatDate(Date value) {
        return formatDate(3, value);
    }

    public static String formatDate(Date value, Locale locale) {
        return (String) withinLocale(new 11(value), locale);
    }

    public static String formatDate(Date value, String pattern) {
        return new SimpleDateFormat(pattern, currentLocale()).format(value);
    }

    public static String formatDate(Date value, String pattern, Locale locale) {
        return (String) withinLocale(new 12(value, pattern), locale);
    }

    public static String formatDate(int style, Date value) {
        return ((DefaultContext) context.get()).formatDate(style, value);
    }

    public static String formatDate(int style, Date value, Locale locale) {
        return (String) withinLocale(new 13(style, value), locale);
    }

    public static String formatDateTime(Date value) {
        return ((DefaultContext) context.get()).formatDateTime(value);
    }

    public static String formatDateTime(Date value, Locale locale) {
        return (String) withinLocale(new 14(value), locale);
    }

    public static String formatDateTime(int dateStyle, int timeStyle, Date value) {
        return ((DefaultContext) context.get()).formatDateTime(dateStyle, timeStyle, value);
    }

    public static String formatDateTime(int dateStyle, int timeStyle, Date value, Locale locale) {
        return (String) withinLocale(new 15(dateStyle, timeStyle, value), locale);
    }

    public static String formatDecimal(Number value) {
        return ((DefaultContext) context.get()).formatDecimal(value);
    }

    public static String formatDecimal(Number value, Locale locale) {
        return (String) withinLocale(new 16(value), locale);
    }

    public static String formatPercent(Number value) {
        return ((DefaultContext) context.get()).getPercentFormat().format(value);
    }

    public static String formatPercent(Number value, Locale locale) {
        return (String) withinLocale(new 17(value), locale);
    }

    public static boolean lossyEquals(Locale locale, String source, String target) {
        return ((Boolean) withinLocale(new 18(source, target), locale)).booleanValue();
    }

    public static boolean lossyEquals(String source, String target) {
        Collator c = Collator.getInstance(currentLocale());
        c.setStrength(0);
        return c.equals(source, target);
    }

    public static String mask(String mask, String value) {
        return maskFormat(mask).format(value);
    }

    public static MaskFormat maskFormat(String mask) {
        MaskFormat maskFmt = ((DefaultContext) context.get()).getMaskFormat();
        maskFmt.setMask(mask);
        return maskFmt;
    }

    public static MessageFormat messageFormat(String pattern) {
        MessageFormat msg = ((DefaultContext) context.get()).getMessageFormat();
        msg.applyPattern(pattern);
        return msg;
    }

    public static MessageFormat messageFormat(String pattern, Locale locale) {
        return (MessageFormat) withinLocale(new 19(pattern), locale);
    }

    public static String metricPrefix(Number value) {
        return prefix(value, 1000, Constants.metricPrefixes);
    }

    @Expose
    public static String metricPrefix(Number value, Locale locale) {
        return (String) withinLocale(new 20(value), locale);
    }

    public static String nanoTime(Number value) {
        return prefix(value, 1000, Constants.nanoTimePrefixes);
    }

    @Expose
    public static String nanoTime(Number value, Locale locale) {
        return (String) withinLocale(new 21(value), locale);
    }

    public static String naturalDay(Date then) {
        return naturalDay(3, then);
    }

    @Expose
    public static String naturalDay(Date then, Locale locale) {
        return naturalDay(3, then, locale);
    }

    public static String naturalDay(int style, Date then) {
        long days = (then.getTime() - new Date().getTime()) / 82800000;
        if (days == 0) {
            return ((DefaultContext) context.get()).getMessage("today");
        }
        if (days == 1) {
            return ((DefaultContext) context.get()).getMessage("tomorrow");
        }
        if (days == -1) {
            return ((DefaultContext) context.get()).getMessage("yesterday");
        }
        return formatDate(style, then);
    }

    public static String naturalDay(int style, Date then, Locale locale) {
        return (String) withinLocale(new 22(style, then), locale);
    }

    public static String naturalTime(Date duration) {
        return naturalTime(new Date(), duration);
    }

    public static String naturalTime(Date reference, Date duration) {
        return ((DefaultContext) context.get()).formatRelativeDate(reference, duration);
    }

    public static String naturalTime(Date reference, Date duration, Locale locale) {
        return (String) withinLocale(new 23(reference, duration), locale);
    }

    public static String naturalTime(Date reference, Date duration, long precision) {
        return ((DefaultContext) context.get()).formatRelativeDate(reference, duration, precision);
    }

    public static String naturalTime(Date reference, Date duration, long precision, Locale locale) {
        return (String) withinLocale(new 24(reference, duration, precision), locale);
    }

    public static String naturalTime(Date reference, Date duration, TimeMillis precision) {
        return naturalTime(reference, duration, precision.millis());
    }

    public static String naturalTime(Date reference, Date duration, TimeMillis precision, Locale locale) {
        return naturalTime(reference, duration, precision.millis(), locale);
    }

    @Expose
    public static String naturalTime(Date duration, Locale locale) {
        return naturalTime(new Date(), duration, locale);
    }

    public static String naturalTime(Date duration, long precision) {
        return naturalTime(new Date(), duration, precision);
    }

    public static String naturalTime(Date duration, long precision, Locale locale) {
        return naturalTime(new Date(), duration, precision, locale);
    }

    public static String naturalTime(Date duration, TimeMillis precision) {
        return naturalTime(duration, precision.millis());
    }

    public static String naturalTime(Date duration, TimeMillis precision, Locale locale) {
        return naturalTime(duration, precision.millis(), locale);
    }

    public static String ordinal(Number value) {
        int vc = value.intValue() % 100;
        if (vc <= 10 || vc >= 14) {
            return String.format(Constants.ORDINAL_FMT, new Object[]{Integer.valueOf(v), ((DefaultContext) context.get()).ordinalSuffix(v % 10)});
        }
        return String.format(Constants.ORDINAL_FMT, new Object[]{Integer.valueOf(v), ((DefaultContext) context.get()).ordinalSuffix(0)});
    }

    @Expose
    public static String ordinal(Number value, Locale locale) {
        return (String) withinLocale(new 25(value), locale);
    }

    public static String oxford(Collection<?> items) {
        return oxford(items.toArray());
    }

    public static String oxford(Collection<?> items, int limit, String limitStr) {
        return oxford(items.toArray(), limit, limitStr);
    }

    @Expose
    public static String oxford(Collection<?> items, Locale locale) {
        return oxford(items.toArray(), locale);
    }

    public static String oxford(Object[] items) {
        return oxford(items, -1, null);
    }

    public static String oxford(Object[] items, int limit, String limitStr) {
        if (items == null || items.length == 0) {
            return "";
        }
        int itemsNum = items.length;
        if (itemsNum == 1) {
            return items[0].toString();
        }
        ResourceBundle bundle = ((DefaultContext) context.get()).getBundle();
        if (itemsNum == 2) {
            return format(bundle.getString("oxford.pair"), items[0], items[1]);
        }
        int limitIndex;
        String append;
        int extra = itemsNum - limit;
        if (limit <= 0 || extra <= 1) {
            limitIndex = itemsNum - 1;
            append = items[limitIndex].toString();
        } else {
            String pattern;
            limitIndex = limit;
            if (Strings.isNullOrEmpty(limitStr)) {
                pattern = bundle.getString("oxford.extra");
            } else {
                pattern = limitStr;
            }
            append = format(pattern, Integer.valueOf(extra));
        }
        return format(bundle.getString("oxford"), Constants.commaJoiner.join(Arrays.copyOf(items, limitIndex)), append);
    }

    public static String oxford(Object[] items, int limit, String limitStr, Locale locale) {
        return (String) withinLocale(new 26(items, limit, limitStr), locale);
    }

    public static String oxford(Object[] items, Locale locale) {
        return oxford(items, -1, null, locale);
    }

    public static Pace pace(Number value, long interval) {
        double dval = (double) Math.round(value.doubleValue());
        if (dval == 0.0d || interval == 0) {
            return Pace.EMPTY;
        }
        Pace args = null;
        double rate = Math.abs(dval / ((double) interval));
        TimeMillis[] intvls = TimeMillis.values();
        for (TimeMillis p : intvls) {
            double relativePace = rate * ((double) p.millis());
            if (relativePace >= 1.0d) {
                args = new Pace(Math.round(relativePace), Accuracy.APROX, p);
                break;
            }
        }
        if (args != null) {
            return args;
        }
        return new Pace(1, Accuracy.LESS_THAN, intvls[intvls.length - 1]);
    }

    public static Pace pace(Number value, TimeMillis interval) {
        return pace(value, interval.millis());
    }

    public static String paceFormat(Locale locale, Number value, long interval) {
        return (String) withinLocale(new 27(value, interval), locale);
    }

    public static String paceFormat(Locale locale, Number value, PaceParameters params) {
        return (String) withinLocale(new 28(value, params), locale);
    }

    public static String paceFormat(Number value, long interval) {
        ResourceBundle b = ((DefaultContext) context.get()).getBundle();
        return paceFormat(value, PaceParameters.begin(b.getString("pace.one")).none(b.getString("pace.none")).many(b.getString("pace.many")).interval(interval));
    }

    public static String paceFormat(Number value, PaceParameters params) {
        params.checkArguments();
        Pace args = pace(value, params.interval);
        ResourceBundle bundle = ((DefaultContext) context.get()).getBundle();
        String accuracy = bundle.getString(args.getAccuracy());
        String timeUnit = bundle.getString(args.getTimeUnit());
        params.exts(new Object[]{accuracy, timeUnit});
        return capitalize(pluralize(Long.valueOf(args.getValue()), params.plural));
    }

    public static String paceFormat(Number value, TimeMillis interval) {
        return paceFormat(value, interval.millis());
    }

    public static byte[] parseBase64(String base64str) {
        return DatatypeConverter.parseBase64Binary(base64str);
    }

    public static Date parseISODate(String dateStr) {
        return DatatypeConverter.parseDate(dateStr).getTime();
    }

    public static Date parseISODateTime(String dateStr) {
        return DatatypeConverter.parseDateTime(dateStr).getTime();
    }

    public static Date parseISOTime(String timeStr) {
        return DatatypeConverter.parseTime(timeStr).getTime();
    }

    public static Date parseSmartDate(String dateStr, String... fmts) {
        return parseSmartDateWithSeparator(dateStr, "[\\D-_\\s]+", fmts);
    }

    public static Date parseSmartDateWithSeparator(String dateStr, String separator, String... fmts) {
        String tmp = dateStr.replaceAll(separator, "/");
        String[] arr$ = fmts;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            try {
                DateFormat df = dateFormat(arr$[i$]);
                df.setLenient(false);
                return df.parse(tmp);
            } catch (ParseException e) {
                i$++;
            }
        }
        throw new IllegalArgumentException("Unable to parse date '" + dateStr + "'");
    }

    public static String pluralize(Locale locale, Number number, PluralizeParams params) {
        return (String) withinLocale(new 29(number, params), locale);
    }

    public static String pluralize(Number number, PluralizeParams p) {
        Preconditions.checkNotNull(p.many, "Please, specify a format for many elements");
        Preconditions.checkNotNull(p.one, "Please, specify a format for a single element");
        String none = p.none == null ? p.many : p.none;
        return pluralizeFormat("{0}", none, p.one, p.many).render(p.exts == null ? new Object[]{number} : ObjectArrays.concat(number, p.exts));
    }

    public static String pluralize(String one, String many, String none, Number number, Object... exts) {
        return pluralize(number, PluralizeParams.begin(one).many(many).none(none).exts(exts));
    }

    public static MessageFormat pluralizeFormat(String template) {
        String[] tokens = template.split("\\s*\\:{2}\\s*");
        if (tokens.length < 4) {
            if (tokens.length == 2) {
                tokens = new String[]{"{0}", tokens[1], tokens[0], tokens[1]};
            } else if (tokens.length == 3) {
                tokens = new String[]{"{0}", tokens[0], tokens[1], tokens[2]};
            } else {
                throw new IllegalArgumentException(String.format("Template '%s' must declare at least 2 tokens. V.gr. 'one thing::{0} things'", new Object[]{template}));
            }
        }
        return pluralizeFormat(tokens[0], (String[]) Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    public static MessageFormat pluralizeFormat(String template, Locale locale) {
        return (MessageFormat) withinLocale(new 30(template), locale);
    }

    public static MessageFormat pluralizeFormat(String pattern, String... choices) {
        double[] indexes = new double[choices.length];
        for (int i = 0; i < choices.length; i++) {
            indexes[i] = (double) i;
        }
        MessageFormat format = (MessageFormat) messageFormat(pattern).clone();
        format.setFormat(0, new ChoiceFormat(indexes, choices));
        return format;
    }

    public static PrettyTimeFormat prettyTimeFormat() {
        return ((DefaultContext) context.get()).getPrettyTimeFormat();
    }

    public static PrettyTimeFormat prettyTimeFormat(Locale locale) {
        return (PrettyTimeFormat) withinLocale(new 31(), locale);
    }

    @Expose
    public static String replaceSupplementary(String value) {
        return InterpolationHelper.replaceSupplementary(value, new 32());
    }

    public static Number roundToSignificantFigures(Number num, int precision) {
        return new BigDecimal(num.doubleValue()).round(new MathContext(precision, RoundingMode.HALF_EVEN));
    }

    @Expose
    public static String simplify(String text) {
        return Constants.COMB_DIACRITICAL.matcher(Normalizer.normalize(text, Form.NFD)).replaceAll("");
    }

    @Expose
    public static String slugify(String text) {
        return slugify(text, SlugifyParams.begin());
    }

    public static String slugify(String text, SlugifyParams params) {
        String result = CharMatcher.INVISIBLE.removeFrom(Constants.HYPEN_SPACE.matcher(CharMatcher.WHITESPACE.trimFrom(Constants.ONLY_SLUG_CHARS.matcher(Constants.PUNCTUATION.matcher(unidecode(text)).replaceAll("-")).replaceAll(""))).replaceAll(params.separator));
        return params.isToLowerCase ? result.toLowerCase() : result;
    }

    public static String spellBigNumber(Number value) {
        BigDecimal v = new BigDecimal(value.toString());
        if (Constants.THOUSAND.compareTo(v.abs()) > 0) {
            return value.toString();
        }
        boolean isPlural = needPlural(v.unscaledValue().intValue());
        for (BigDecimal bigNum : Constants.bigDecExponents.keySet()) {
            if (bigNum.multiply(Constants.THOUSAND).compareTo(v.abs()) > 0) {
                return ((DefaultContext) context.get()).formatMessage(isPlural ? ((String) Constants.bigDecExponents.get(bigNum)) + ".pl" : (String) Constants.bigDecExponents.get(bigNum), new Object[]{v.divide(bigNum)});
            }
        }
        return value.toString();
    }

    @Expose
    public static String spellBigNumber(Number value, Locale locale) {
        return (String) withinLocale(new 33(value), locale);
    }

    public static String spellDigit(Number value) {
        int v = value.intValue();
        if (v < 0 || v > 9) {
            return value.toString();
        }
        return ((DefaultContext) context.get()).digitStrings(v);
    }

    @Expose
    public static String spellDigit(Number value, Locale locale) {
        return (String) withinLocale(new 34(value), locale);
    }

    public static String times(Number num) {
        return new java.text.MessageFormat(((DefaultContext) context.get()).getBundle().getString("times.choice"), currentLocale()).format(new Object[]{Integer.valueOf(Math.abs(num.intValue()))});
    }

    @Expose
    public static String times(Number num, Locale locale) {
        return (String) withinLocale(new 35(num), locale);
    }

    @Expose
    public static String titleize(String text) {
        return titleize(text, null);
    }

    public static String titleize(String text, String[] intCaps) {
        return titleize(text.toLowerCase(Locale.ENGLISH).replaceAll("[\\s_]+", Constants.SPACE).trim(), Constants.SPACE, intCaps);
    }

    @Expose
    public static String underscore(String text) {
        return text.replaceAll("\\s+", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }

    @Expose
    public static String unidecode(String text) {
        return Unidecode.decode(text);
    }

    public static String unmask(String mask, String value) throws ParseException {
        return maskFormat(mask).parse(value);
    }

    public static String wordWrap(String value, int len) {
        if (len < 0 || value.length() <= len) {
            return value;
        }
        BreakIterator bi = BreakIterator.getWordInstance(currentLocale());
        bi.setText(value);
        return value.substring(0, bi.following(len));
    }

    private static Locale currentLocale() {
        return ((DefaultContext) context.get()).getLocale();
    }

    private static ContextFactory loadContextFactory() {
        Iterator i$ = ServiceLoader.load(ContextFactory.class).iterator();
        while (i$.hasNext()) {
            ContextFactory factory = (ContextFactory) i$.next();
            if (DefaultContextFactory.class.isAssignableFrom(factory.getClass())) {
                return factory;
            }
        }
        throw new RuntimeException("No ContextFactory was found");
    }

    private static boolean needPlural(int n) {
        for (int an = Math.abs(n); an > 0; an /= 10) {
            if (an % 10 > 1) {
                return true;
            }
        }
        return false;
    }

    private static String prefix(Number value, int min, Map<Long, String> prefixes) {
        DecimalFormat df = ((DefaultContext) context.get()).getDecimalFormat();
        long v = value.longValue();
        if (v < 0) {
            return value.toString();
        }
        for (Long num : prefixes.keySet()) {
            if (num.longValue() <= v) {
                df.applyPattern((String) prefixes.get(num));
                return stripZeros(df, df.format(v >= ((long) min) ? (double) (((float) v) / ((float) num.longValue())) : (double) v));
            }
        }
        return stripZeros(df, df.format(value.toString()));
    }

    private static String resolveInternalCapsWord(String word, String[] internalCaps) {
        for (String ic : internalCaps) {
            if (word.matches(String.format("(?i)[\\(\\[-]*%s[\\)\\]-]*", new Object[]{ic}))) {
                return word.replace(ic.toLowerCase(), ic);
            }
        }
        return capitalize(word);
    }

    private static String stripZeros(DecimalFormat decf, String fmtd) {
        return fmtd.replaceAll("\\" + decf.getDecimalFormatSymbols().getDecimalSeparator() + DeviceTypeConstants.UNKNOW, "");
    }

    private static String titleize(String str, String separator, String[] intCaps) {
        StringBuilder sb = new StringBuilder(str.length());
        String[] parts = str.split(separator);
        int i = 0;
        while (i < parts.length) {
            String word = parts[i];
            boolean notLastWord = i < parts.length + -1;
            if (i > 0 && notLastWord && Constants.titleIgnoredWords.contains(word)) {
                sb.append(word);
            } else {
                Matcher m = Constants.titleWordSperator.matcher(word);
                if (m.find()) {
                    sb.append(titleize(word, m.group(1), intCaps));
                    while (m.find()) {
                        sb.append(titleize(word, m.group(1), intCaps));
                    }
                } else {
                    sb.append(intCaps == null ? capitalize(word) : resolveInternalCapsWord(word, intCaps));
                }
            }
            if (notLastWord) {
                sb.append(separator);
            }
            i++;
        }
        return sb.toString();
    }

    private static <T> T withinLocale(Callable<T> operation, Locale locale) {
        DefaultContext ctx = (DefaultContext) context.get();
        Locale oldLocale = ctx.getLocale();
        try {
            ctx.setLocale(locale);
            T call = operation.call();
            ctx.setLocale(oldLocale);
            context.set(ctx);
            return call;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            ctx.setLocale(oldLocale);
            context.set(ctx);
        }
    }

    private Humanize() {
    }
}
