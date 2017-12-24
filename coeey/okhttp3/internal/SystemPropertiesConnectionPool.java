package okhttp3.internal;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;

public final class SystemPropertiesConnectionPool {
    private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000;
    public static final ConnectionPool INSTANCE;

    static {
        int maxIdleConnections;
        String keepAlive = System.getProperty("http.keepAlive");
        if (keepAlive == null || Boolean.parseBoolean(keepAlive)) {
            String maxIdleConnectionsString = System.getProperty("http.maxConnections");
            if (maxIdleConnectionsString != null) {
                maxIdleConnections = Integer.parseInt(maxIdleConnectionsString);
            } else {
                maxIdleConnections = 5;
            }
        } else {
            maxIdleConnections = 0;
        }
        String keepAliveDurationString = System.getProperty("http.keepAliveDuration");
        INSTANCE = new ConnectionPool(maxIdleConnections, keepAliveDurationString != null ? Long.parseLong(keepAliveDurationString) : DEFAULT_KEEP_ALIVE_DURATION_MS, TimeUnit.MILLISECONDS);
    }

    private SystemPropertiesConnectionPool() {
    }
}
