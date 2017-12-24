package com.thefinestartist.finestwebview.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import java.util.List;

public class BroadCastManager {
    static final String EXTRA_CONTENT_DISPOSITION = "EXTRA_CONTENT_DISPOSITION";
    static final String EXTRA_CONTENT_LENGTH = "EXTRA_CONTENT_LENGTH";
    static final String EXTRA_KEY = "EXTRA_KEY";
    static final String EXTRA_MIME_TYPE = "EXTRA_MIME_TYPE";
    static final String EXTRA_PRECOMPOSED = "EXTRA_PRECOMPOSED";
    static final String EXTRA_PROGESS = "EXTRA_PROGESS";
    static final String EXTRA_TITLE = "EXTRA_TITLE";
    static final String EXTRA_TYPE = "EXTRA_TYPE";
    static final String EXTRA_URL = "EXTRA_URL";
    static final String EXTRA_USER_AGENT = "EXTRA_USER_AGENT";
    static final String WEBVIEW_EVENT = "WEBVIEW_EVENT";
    protected int key;
    protected List<WebViewListener> listeners;
    protected LocalBroadcastManager manager;
    protected BroadcastReceiver receiver = new C23331();

    class C23331 extends BroadcastReceiver {
        C23331() {
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null && BroadCastManager.this.key == intent.getIntExtra(BroadCastManager.EXTRA_KEY, Integer.MIN_VALUE)) {
                BroadCastManager.this.handleIntent(intent);
            }
        }
    }

    public enum Type {
        PROGRESS_CHANGED,
        RECEIVED_TITLE,
        RECEIVED_TOUCH_ICON_URL,
        PAGE_STARTED,
        PAGE_FINISHED,
        LOAD_RESOURCE,
        PAGE_COMMIT_VISIBLE,
        DOWNLOADED_START,
        UNREGISTER
    }

    public BroadCastManager(Context context, int key, @NonNull List<WebViewListener> listeners) {
        this.key = key;
        this.listeners = listeners;
        this.manager = LocalBroadcastManager.getInstance(context);
        this.manager.registerReceiver(this.receiver, new IntentFilter(WEBVIEW_EVENT));
    }

    private void handleIntent(@NonNull Intent intent) {
        switch ((Type) intent.getSerializableExtra(EXTRA_TYPE)) {
            case PROGRESS_CHANGED:
                onProgressChanged(intent);
                return;
            case RECEIVED_TITLE:
                onReceivedTitle(intent);
                return;
            case RECEIVED_TOUCH_ICON_URL:
                onReceivedTouchIconUrl(intent);
                return;
            case PAGE_STARTED:
                onPageStarted(intent);
                return;
            case PAGE_FINISHED:
                onPageFinished(intent);
                return;
            case LOAD_RESOURCE:
                onLoadResource(intent);
                return;
            case PAGE_COMMIT_VISIBLE:
                onPageCommitVisible(intent);
                return;
            case UNREGISTER:
                unregister();
                return;
            default:
                return;
        }
    }

    private static Intent getBaseIntent(int key, Type type) {
        return new Intent(WEBVIEW_EVENT).putExtra(EXTRA_KEY, key).putExtra(EXTRA_TYPE, type);
    }

    private static void sendBroadCast(Context context, Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void onProgressChanged(Context context, int key, int progress) {
        sendBroadCast(context, getBaseIntent(key, Type.PROGRESS_CHANGED).putExtra(EXTRA_PROGESS, progress));
    }

    public void onProgressChanged(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onProgressChanged(intent.getIntExtra(EXTRA_PROGESS, 0));
        }
    }

    public static void onReceivedTitle(Context context, int key, String title) {
        sendBroadCast(context, getBaseIntent(key, Type.RECEIVED_TITLE).putExtra(EXTRA_TITLE, title));
    }

    public void onReceivedTitle(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onReceivedTitle(intent.getStringExtra(EXTRA_TITLE));
        }
    }

    public static void onReceivedTouchIconUrl(Context context, int key, String url, boolean precomposed) {
        sendBroadCast(context, getBaseIntent(key, Type.RECEIVED_TOUCH_ICON_URL).putExtra(EXTRA_URL, url).putExtra(EXTRA_PRECOMPOSED, precomposed));
    }

    public void onReceivedTouchIconUrl(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onReceivedTouchIconUrl(intent.getStringExtra(EXTRA_URL), intent.getBooleanExtra(EXTRA_PRECOMPOSED, false));
        }
    }

    public static void onPageStarted(Context context, int key, String url) {
        sendBroadCast(context, getBaseIntent(key, Type.PAGE_STARTED).putExtra(EXTRA_URL, url));
    }

    public void onPageStarted(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onPageStarted(intent.getStringExtra(EXTRA_URL));
        }
    }

    public static void onPageFinished(Context context, int key, String url) {
        sendBroadCast(context, getBaseIntent(key, Type.PAGE_FINISHED).putExtra(EXTRA_URL, url));
    }

    public void onPageFinished(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onPageFinished(intent.getStringExtra(EXTRA_URL));
        }
    }

    public static void onLoadResource(Context context, int key, String url) {
        sendBroadCast(context, getBaseIntent(key, Type.LOAD_RESOURCE).putExtra(EXTRA_URL, url));
    }

    public void onLoadResource(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onLoadResource(intent.getStringExtra(EXTRA_URL));
        }
    }

    public static void onPageCommitVisible(Context context, int key, String url) {
        sendBroadCast(context, getBaseIntent(key, Type.PAGE_COMMIT_VISIBLE).putExtra(EXTRA_URL, url));
    }

    public void onPageCommitVisible(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onPageCommitVisible(intent.getStringExtra(EXTRA_URL));
        }
    }

    public static void onDownloadStart(Context context, int key, String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
        sendBroadCast(context, getBaseIntent(key, Type.DOWNLOADED_START).putExtra(EXTRA_URL, url).putExtra(EXTRA_USER_AGENT, userAgent).putExtra(EXTRA_CONTENT_DISPOSITION, contentDisposition).putExtra(EXTRA_MIME_TYPE, mimeType).putExtra(EXTRA_CONTENT_LENGTH, contentLength));
    }

    public void onDownloadStart(Intent intent) {
        for (WebViewListener listener : this.listeners) {
            listener.onDownloadStart(intent.getStringExtra(EXTRA_URL), intent.getStringExtra(EXTRA_USER_AGENT), intent.getStringExtra(EXTRA_CONTENT_DISPOSITION), intent.getStringExtra(EXTRA_MIME_TYPE), intent.getLongExtra(EXTRA_CONTENT_LENGTH, 0));
        }
    }

    public static void unregister(Context context, int key) {
        sendBroadCast(context, getBaseIntent(key, Type.UNREGISTER));
    }

    private void unregister() {
        if (this.manager != null && this.receiver != null) {
            this.manager.unregisterReceiver(this.receiver);
        }
    }
}
