package humanize.util;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.share.internal.ShareConstants;
import com.google.common.base.Joiner;
import com.ihealth.communication.manager.iHealthDevicesManager;
import humanize.spi.context.DefaultContext;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class Constants {
    public static final Pattern COMB_DIACRITICAL = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    public static final String DEFAULT_SLUG_SEPARATOR = "-";
    public static final String EMPTY = "";
    public static final Pattern HYPEN_SPACE = Pattern.compile("[-\\s]+");
    public static final int ND_FACTOR = 82800000;
    public static final Pattern NOT_IN_BMP = Pattern.compile("([^\u0000-퟿-￿])");
    public static final Pattern ONLY_SLUG_CHARS = Pattern.compile("[^-\\w\\s]");
    public static final String ORDINAL_FMT = "%d%s";
    public static final Pattern PUNCTUATION = Pattern.compile("\\p{Punct}+");
    public static final String SPACE = " ";
    public static final Pattern SPLIT_CAMEL = Pattern.compile("(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])");
    public static final BigDecimal THOUSAND = BigDecimal.valueOf(1000);
    public static final Map<BigDecimal, String> bigDecExponents = new LinkedHashMap();
    public static final Map<Long, String> binPrefixes = new LinkedHashMap();
    public static final Joiner commaJoiner = Joiner.on(", ").skipNulls();
    public static final Map<Long, String> metricPrefixes = new LinkedHashMap();
    public static final Map<Long, String> nanoTimePrefixes = new LinkedHashMap();
    public static final List<String> titleIgnoredWords = Arrays.asList(new String[]{"a", "an", "and", "but", "nor", "it", "the", ShareConstants.WEB_DIALOG_PARAM_TO, "with", "in", ViewProps.ON, "of", "up", "or", "at", "into", "onto", "by", "from", "then", "for", "via", "versus"});
    public static final Pattern titleWordSperator = Pattern.compile(".+(\\||-|/).+");

    public enum TimeStyle {
        STANDARD {
            public String format(DefaultContext ctx, boolean neg, int h, int m, int s) {
                String str = "%s%d:%02d:%02d";
                Object[] objArr = new Object[4];
                objArr[0] = neg ? Character.valueOf('-') : "";
                objArr[1] = Integer.valueOf(h);
                objArr[2] = Integer.valueOf(m);
                objArr[3] = Integer.valueOf(s);
                return String.format(str, objArr);
            }
        },
        FRENCH_DECIMAL {
            public String format(DefaultContext ctx, boolean neg, int h, int m, int s) {
                String r;
                Object valueOf;
                if (h == 0) {
                    if (m == 0) {
                        r = String.format(Constants.ORDINAL_FMT, new Object[]{Integer.valueOf(s), ctx.timeSuffix(2)});
                    } else if (s == 0) {
                        r = String.format(Constants.ORDINAL_FMT, new Object[]{Integer.valueOf(m), ctx.timeSuffix(1)});
                    } else {
                        r = String.format("%d%s %d%s", new Object[]{Integer.valueOf(m), ctx.timeSuffix(1), Integer.valueOf(s), ctx.timeSuffix(2)});
                    }
                } else if (m == 0) {
                    if (s == 0) {
                        r = String.format(Constants.ORDINAL_FMT, new Object[]{Integer.valueOf(h), ctx.timeSuffix(0)});
                    } else {
                        r = String.format("%d%s %d%s", new Object[]{Integer.valueOf(h), ctx.timeSuffix(0), Integer.valueOf(s), ctx.timeSuffix(2)});
                    }
                } else if (s == 0) {
                    r = String.format("%d%s %d%s", new Object[]{Integer.valueOf(h), ctx.timeSuffix(0), Integer.valueOf(m), ctx.timeSuffix(1)});
                } else {
                    r = String.format("%d%s %d%s %d%s", new Object[]{Integer.valueOf(h), ctx.timeSuffix(0), Integer.valueOf(m), ctx.timeSuffix(1), Integer.valueOf(s), ctx.timeSuffix(2)});
                }
                StringBuilder stringBuilder = new StringBuilder();
                if (neg) {
                    valueOf = Character.valueOf('-');
                } else {
                    valueOf = "";
                }
                return stringBuilder.append(valueOf).append(r).toString();
            }
        };

        public abstract String format(DefaultContext defaultContext, boolean z, int i, int i2, int i3);
    }

    static {
        bigDecExponents.put(BigDecimal.TEN.pow(3), "thousand");
        bigDecExponents.put(BigDecimal.TEN.pow(6), "million");
        bigDecExponents.put(BigDecimal.TEN.pow(9), "billion");
        bigDecExponents.put(BigDecimal.TEN.pow(12), "trillion");
        bigDecExponents.put(BigDecimal.TEN.pow(15), "quadrillion");
        bigDecExponents.put(BigDecimal.TEN.pow(18), "quintillion");
        bigDecExponents.put(BigDecimal.TEN.pow(21), "sextillion");
        bigDecExponents.put(BigDecimal.TEN.pow(24), "septillion");
        bigDecExponents.put(BigDecimal.TEN.pow(27), "octillion");
        bigDecExponents.put(BigDecimal.TEN.pow(30), "nonillion");
        bigDecExponents.put(BigDecimal.TEN.pow(33), "decillion");
        bigDecExponents.put(BigDecimal.TEN.pow(36), "undecillion");
        bigDecExponents.put(BigDecimal.TEN.pow(39), "duodecillion");
        binPrefixes.put(Long.valueOf(1125899906842624L), "#.## PB");
        binPrefixes.put(Long.valueOf(1099511627776L), "#.## TB");
        binPrefixes.put(Long.valueOf(iHealthDevicesManager.DISCOVERY_HS5_WIFI), "#.## GB");
        binPrefixes.put(Long.valueOf(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), "#.## MB");
        binPrefixes.put(Long.valueOf(1024), "#.# KB");
        binPrefixes.put(Long.valueOf(0), "# bytes");
        metricPrefixes.put(Long.valueOf(1000000000000000L), "#.##P");
        metricPrefixes.put(Long.valueOf(1000000000000L), "#.##T");
        metricPrefixes.put(Long.valueOf(1000000000), "#.##G");
        metricPrefixes.put(Long.valueOf(1000000), "#.##M");
        metricPrefixes.put(Long.valueOf(1000), "#.#k");
        metricPrefixes.put(Long.valueOf(0), "#.#");
        nanoTimePrefixes.put(Long.valueOf(1000000000), "#.##s");
        nanoTimePrefixes.put(Long.valueOf(1000000), "#.###ms");
        nanoTimePrefixes.put(Long.valueOf(1000), "#.####µs");
        nanoTimePrefixes.put(Long.valueOf(0), "#.####ns");
    }
}
