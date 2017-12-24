package humanize.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

public class UTF8Control extends Control {
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
        String resourceName = toResourceName(toBundleName(baseName, locale), "properties");
        ResourceBundle bundle = null;
        InputStream stream = reload ? reload(loader.getResource(resourceName)) : loader.getResourceAsStream(resourceName);
        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }

    private InputStream reload(URL url) throws IOException {
        if (url != null) {
            URLConnection connection = url.openConnection();
            if (connection != null) {
                connection.setUseCaches(false);
                return connection.getInputStream();
            }
        }
        return null;
    }
}
