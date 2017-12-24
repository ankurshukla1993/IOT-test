package humanize.spi.cache;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public interface CacheProvider {
    boolean containsBundle(Locale locale);

    boolean containsFormat(String str, Locale locale);

    boolean containsStrings(String str, Locale locale);

    ResourceBundle getBundle(Locale locale, Callable<ResourceBundle> callable);

    <T> T getFormat(String str, Locale locale, Callable<T> callable);

    String[] getStrings(String str, Locale locale, Callable<String[]> callable);

    ResourceBundle putBundle(Locale locale, ResourceBundle resourceBundle);

    <T> T putFormat(String str, Locale locale, T t);

    String[] putStrings(String str, Locale locale, String[] strArr);
}
