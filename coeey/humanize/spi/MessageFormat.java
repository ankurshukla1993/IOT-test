package humanize.spi;

import humanize.text.ExtendedMessageFormat;
import humanize.text.FormatFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;

public class MessageFormat extends ExtendedMessageFormat {
    private static final Map<String, FormatFactory> formatFactories = loadFormatFactories();
    private static final long serialVersionUID = -5384364921909539710L;

    private static Map<String, FormatFactory> loadFormatFactories() {
        Map<String, FormatFactory> factories = new HashMap();
        Iterator i$ = ServiceLoader.load(FormatProvider.class).iterator();
        while (i$.hasNext()) {
            registerProvider(factories, (FormatProvider) i$.next());
        }
        return factories;
    }

    private static void registerProvider(Map<String, FormatFactory> factories, FormatProvider provider) {
        String formatName = provider.getFormatName();
        FormatFactory factory = provider.getFactory();
        if (formatName.indexOf(124) > -1) {
            for (String name : formatName.split("\\|")) {
                factories.put(name, factory);
            }
            return;
        }
        factories.put(formatName, factory);
    }

    public MessageFormat(String pattern) {
        super(pattern, formatFactories);
    }

    public MessageFormat(String pattern, Locale locale) {
        super(pattern, locale, formatFactories);
    }

    public MessageFormat(String pattern, Locale locale, Map<String, ? extends FormatFactory> registry) {
        super(pattern, locale, registry);
    }

    public MessageFormat(String pattern, Map<String, ? extends FormatFactory> registry) {
        super(pattern, (Map) registry);
    }

    public String render(Object... arguments) {
        return format(arguments);
    }

    public StringBuffer render(StringBuffer buffer, Object... arguments) {
        return format(arguments, buffer, null);
    }
}
