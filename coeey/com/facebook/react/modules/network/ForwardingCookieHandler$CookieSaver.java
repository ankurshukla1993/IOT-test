package com.facebook.react.modules.network;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.webkit.CookieSyncManager;
import com.evernote.android.job.JobRequest;

class ForwardingCookieHandler$CookieSaver {
    private static final int MSG_PERSIST_COOKIES = 1;
    private static final int TIMEOUT = 30000;
    private final Handler mHandler;
    final /* synthetic */ ForwardingCookieHandler this$0;

    class C13182 implements Runnable {
        C13182() {
        }

        public void run() {
            if (ForwardingCookieHandler.access$200()) {
                CookieSyncManager.getInstance().sync();
            } else {
                ForwardingCookieHandler$CookieSaver.this.flush();
            }
        }
    }

    public ForwardingCookieHandler$CookieSaver(final ForwardingCookieHandler forwardingCookieHandler) {
        this.this$0 = forwardingCookieHandler;
        this.mHandler = new Handler(Looper.getMainLooper(), new Callback() {
            public boolean handleMessage(Message msg) {
                if (msg.what != 1) {
                    return false;
                }
                ForwardingCookieHandler$CookieSaver.this.persistCookies();
                return true;
            }
        });
    }

    public void onCookiesModified() {
        if (ForwardingCookieHandler.access$200()) {
            this.mHandler.sendEmptyMessageDelayed(1, JobRequest.DEFAULT_BACKOFF_MS);
        }
    }

    public void persistCookies() {
        this.mHandler.removeMessages(1);
        ForwardingCookieHandler.access$400(this.this$0, new C13182());
    }

    @TargetApi(21)
    private void flush() {
        ForwardingCookieHandler.access$000(this.this$0).flush();
    }
}
