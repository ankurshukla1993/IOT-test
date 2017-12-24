package humanize.spi.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import humanize.config.ConfigLoader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaCacheProvider implements CacheProvider {
    private static final CacheBuilderSpec spec = initSpec();
    private final Cache<Locale, ResourceBundle> bundles = CacheBuilder.from(spec).build();
    private final LoadingCache<String, Cache<Locale, Object>> formats = CacheBuilder.from(spec).build(new C23591());
    private final LoadingCache<String, Cache<Locale, String[]>> stringCaches = CacheBuilder.from(spec).build(new C23602());

    class C23591 extends CacheLoader<String, Cache<Locale, Object>> {
        C23591() {
        }

        public Cache<Locale, Object> load(String cache) throws Exception {
            return CacheBuilder.from(GuavaCacheProvider.spec).build();
        }
    }

    class C23602 extends CacheLoader<String, Cache<Locale, String[]>> {
        C23602() {
        }

        public Cache<Locale, String[]> load(String cache) throws Exception {
            return CacheBuilder.from(GuavaCacheProvider.spec).build();
        }
    }

    private static CacheBuilderSpec initSpec() {
        return CacheBuilderSpec.parse(ConfigLoader.loadProperties().getProperty(ConfigLoader.CACHE_BUILDER_SPEC));
    }

    public boolean containsBundle(Locale locale) {
        return this.bundles.getIfPresent(locale) != null;
    }

    public boolean containsFormat(String cache, Locale locale) {
        return getFormatsCache(cache).getIfPresent(locale) != null;
    }

    public boolean containsStrings(String cache, Locale locale) {
        return getStringCache(cache).getIfPresent(locale) != null;
    }

    public ResourceBundle getBundle(Locale locale, Callable<ResourceBundle> getCall) {
        try {
            return (ResourceBundle) this.bundles.get(locale, getCall);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getFormat(String cache, Locale locale, Callable<T> getCall) {
        try {
            return getFormatsCache(cache).get(locale, getCall);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getStrings(String cache, Locale locale, Callable<String[]> getCall) {
        try {
            return (String[]) getStringCache(cache).get(locale, getCall);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public ResourceBundle putBundle(Locale locale, ResourceBundle bundle) {
        this.bundles.put(locale, bundle);
        return bundle;
    }

    public <T> T putFormat(String cache, Locale locale, T format) {
        getFormatsCache(cache).put(locale, format);
        return format;
    }

    public String[] putStrings(String cache, Locale locale, String[] value) {
        getStringCache(cache).put(locale, value);
        return value;
    }

    private <T> Cache<Locale, T> getFormatsCache(String cache) {
        try {
            return (Cache) this.formats.get(cache);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Cache<Locale, String[]> getStringCache(String cache) {
        try {
            return (Cache) this.stringCaches.get(cache);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
