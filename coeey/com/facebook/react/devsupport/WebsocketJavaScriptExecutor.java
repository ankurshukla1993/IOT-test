package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaJSExecutor.ProxyExecutorException;
import com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public class WebsocketJavaScriptExecutor implements JavaJSExecutor {
    private static final int CONNECT_RETRY_COUNT = 3;
    private static final long CONNECT_TIMEOUT_MS = 5000;
    private final HashMap<String, String> mInjectedObjects = new HashMap();
    @Nullable
    private JSDebuggerWebSocketClient mWebSocketClient;

    private static class JSExecutorCallbackFuture implements JSDebuggerCallback {
        @Nullable
        private Throwable mCause;
        @Nullable
        private String mResponse;
        private final Semaphore mSemaphore;

        private JSExecutorCallbackFuture() {
            this.mSemaphore = new Semaphore(0);
        }

        public void onSuccess(@Nullable String response) {
            this.mResponse = response;
            this.mSemaphore.release();
        }

        public void onFailure(Throwable cause) {
            this.mCause = cause;
            this.mSemaphore.release();
        }

        @Nullable
        public String get() throws Throwable {
            this.mSemaphore.acquire();
            if (this.mCause == null) {
                return this.mResponse;
            }
            throw this.mCause;
        }
    }

    public void connect(String webSocketServerUrl, JSExecutorConnectCallback callback) {
        connectInternal(webSocketServerUrl, new 1(this, callback, new AtomicInteger(3), webSocketServerUrl));
    }

    private void connectInternal(String webSocketServerUrl, JSExecutorConnectCallback callback) {
        JSDebuggerWebSocketClient client = new JSDebuggerWebSocketClient();
        Handler timeoutHandler = new Handler(Looper.getMainLooper());
        client.connect(webSocketServerUrl, new 2(this, client, timeoutHandler, callback));
        timeoutHandler.postDelayed(new 3(this, client, callback), CONNECT_TIMEOUT_MS);
    }

    public void close() {
        if (this.mWebSocketClient != null) {
            this.mWebSocketClient.closeQuietly();
        }
    }

    public void loadApplicationScript(String sourceURL) throws ProxyExecutorException {
        JSExecutorCallbackFuture callback = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).loadApplicationScript(sourceURL, this.mInjectedObjects, callback);
        try {
            callback.get();
        } catch (Throwable cause) {
            ProxyExecutorException proxyExecutorException = new ProxyExecutorException(cause);
        }
    }

    @Nullable
    public String executeJSCall(String methodName, String jsonArgsArray) throws ProxyExecutorException {
        JSExecutorCallbackFuture callback = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).executeJSCall(methodName, jsonArgsArray, callback);
        try {
            return callback.get();
        } catch (Throwable cause) {
            ProxyExecutorException proxyExecutorException = new ProxyExecutorException(cause);
        }
    }

    public void setGlobalVariable(String propertyName, String jsonEncodedValue) {
        this.mInjectedObjects.put(propertyName, jsonEncodedValue);
    }
}
