package humanize.config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ConfigLoader {
    public static final String CACHE_BUILDER_SPEC = "cache.builder.spec";
    private static final Properties DEFAULTS = new Properties();

    static {
        DEFAULTS.setProperty(CACHE_BUILDER_SPEC, "expireAfterAccess=1h");
    }

    public static Properties loadProperties() {
        String path = System.getProperty("humanize.config");
        if (path == null) {
            path = "humanize.properties";
        }
        return loadProperties(path);
    }

    public static Properties loadProperties(String path) {
        Properties properties = new Properties(DEFAULTS);
        URL url = locateConfig(path);
        if (url != null) {
            try {
                properties.load(url.openConnection().getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;
    }

    public static URL locateConfig(String path) {
        URL url = asFile(path);
        if (url == null) {
            url = asURL(path);
        }
        if (url == null) {
            return asResource(path);
        }
        return url;
    }

    private static URL asFile(String path) {
        URL url = null;
        File file = new File(path);
        if (file.exists()) {
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException e) {
            }
        }
        return url;
    }

    private static final URL asResource(String path) {
        URL url = null;
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null) {
                url = contextClassLoader.getResource(path);
            }
            if (url == null) {
                url = ConfigLoader.class.getClassLoader().getResource(path);
            }
        } catch (Exception e) {
        }
        return url;
    }

    private static URL asURL(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
