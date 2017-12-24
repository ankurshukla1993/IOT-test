package com.facebook.react.modules.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class ForwardingCookieHandler extends CookieHandler {
    private static final String COOKIE_HEADER = "Cookie";
    private static final boolean USES_LEGACY_STORE = (VERSION.SDK_INT < 21);
    private static final String VERSION_ONE_HEADER = "Set-cookie2";
    private static final String VERSION_ZERO_HEADER = "Set-cookie";
    private final ReactContext mContext;
    @Nullable
    private CookieManager mCookieManager;
    private final CookieSaver mCookieSaver = new CookieSaver(this);

    public ForwardingCookieHandler(ReactContext context) {
        this.mContext = context;
    }

    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        String cookies = getCookieManager().getCookie(uri.toString());
        if (TextUtils.isEmpty(cookies)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookies));
    }

    public void put(URI uri, Map<String, List<String>> headers) throws IOException {
        String url = uri.toString();
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            if (key != null && isCookieHeader(key)) {
                addCookies(url, (List) entry.getValue());
            }
        }
    }

    public void clearCookies(Callback callback) {
        if (USES_LEGACY_STORE) {
            new 1(this, this.mContext, callback).execute(new Void[0]);
        } else {
            clearCookiesAsync(callback);
        }
    }

    private void clearCookiesAsync(Callback callback) {
        getCookieManager().removeAllCookies(new 2(this, callback));
    }

    public void destroy() {
        if (USES_LEGACY_STORE) {
            getCookieManager().removeExpiredCookie();
            this.mCookieSaver.persistCookies();
        }
    }

    private void addCookies(String url, List<String> cookies) {
        if (USES_LEGACY_STORE) {
            runInBackground(new 3(this, cookies, url));
            return;
        }
        for (String cookie : cookies) {
            addCookieAsync(url, cookie);
        }
        this.mCookieSaver.onCookiesModified();
    }

    @TargetApi(21)
    private void addCookieAsync(String url, String cookie) {
        getCookieManager().setCookie(url, cookie, null);
    }

    private static boolean isCookieHeader(String name) {
        return name.equalsIgnoreCase(VERSION_ZERO_HEADER) || name.equalsIgnoreCase(VERSION_ONE_HEADER);
    }

    private void runInBackground(Runnable runnable) {
        new 4(this, this.mContext, runnable).execute(new Void[0]);
    }

    private CookieManager getCookieManager() {
        if (this.mCookieManager == null) {
            possiblyWorkaroundSyncManager(this.mContext);
            this.mCookieManager = CookieManager.getInstance();
            if (USES_LEGACY_STORE) {
                this.mCookieManager.removeExpiredCookie();
            }
        }
        return this.mCookieManager;
    }

    private static void possiblyWorkaroundSyncManager(Context context) {
        if (USES_LEGACY_STORE) {
            CookieSyncManager.createInstance(context).sync();
        }
    }
}
