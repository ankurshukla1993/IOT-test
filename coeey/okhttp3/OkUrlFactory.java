package okhttp3;

import com.facebook.common.util.UriUtil;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import okhttp3.internal.URLFilter;
import okhttp3.internal.huc.OkHttpURLConnection;
import okhttp3.internal.huc.OkHttpsURLConnection;
import org.java_websocket.WebSocket;

public final class OkUrlFactory implements URLStreamHandlerFactory, Cloneable {
    private OkHttpClient client;
    private URLFilter urlFilter;

    public OkUrlFactory(OkHttpClient client) {
        this.client = client;
    }

    public OkHttpClient client() {
        return this.client;
    }

    public OkUrlFactory setClient(OkHttpClient client) {
        this.client = client;
        return this;
    }

    void setUrlFilter(URLFilter filter) {
        this.urlFilter = filter;
    }

    public OkUrlFactory clone() {
        return new OkUrlFactory(this.client);
    }

    public HttpURLConnection open(URL url) {
        return open(url, this.client.proxy());
    }

    HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient copy = this.client.newBuilder().proxy(proxy).build();
        if (protocol.equals(UriUtil.HTTP_SCHEME)) {
            return new OkHttpURLConnection(url, copy, this.urlFilter);
        }
        if (protocol.equals(UriUtil.HTTPS_SCHEME)) {
            return new OkHttpsURLConnection(url, copy, this.urlFilter);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    public URLStreamHandler createURLStreamHandler(final String protocol) {
        if (protocol.equals(UriUtil.HTTP_SCHEME) || protocol.equals(UriUtil.HTTPS_SCHEME)) {
            return new URLStreamHandler() {
                protected URLConnection openConnection(URL url) {
                    return OkUrlFactory.this.open(url);
                }

                protected URLConnection openConnection(URL url, Proxy proxy) {
                    return OkUrlFactory.this.open(url, proxy);
                }

                protected int getDefaultPort() {
                    if (protocol.equals(UriUtil.HTTP_SCHEME)) {
                        return 80;
                    }
                    if (protocol.equals(UriUtil.HTTPS_SCHEME)) {
                        return WebSocket.DEFAULT_WSS_PORT;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }
}
