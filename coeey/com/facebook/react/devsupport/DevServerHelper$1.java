package com.facebook.react.devsupport;

import android.os.AsyncTask;
import javax.annotation.Nullable;
import okhttp3.ws.WebSocket;

class DevServerHelper$1 extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ DevServerHelper this$0;
    final /* synthetic */ DevServerHelper$PackagerCommandListener val$commandListener;

    class C12971 implements JSPackagerWebSocketClient$JSPackagerCallback {
        C12971() {
        }

        public void onMessage(@Nullable WebSocket webSocket, String target, String action) {
            if (DevServerHelper$1.this.val$commandListener != null && "bridge".equals(target)) {
                if ("reload".equals(action)) {
                    DevServerHelper$1.this.val$commandListener.onPackagerReloadCommand();
                } else if ("captureHeap".equals(action)) {
                    DevServerHelper$1.this.val$commandListener.onCaptureHeapCommand();
                } else if ("pokeSamplingProfiler".equals(action)) {
                    DevServerHelper$1.this.val$commandListener.onPokeSamplingProfilerCommand(webSocket);
                }
            }
        }
    }

    DevServerHelper$1(DevServerHelper this$0, DevServerHelper$PackagerCommandListener devServerHelper$PackagerCommandListener) {
        this.this$0 = this$0;
        this.val$commandListener = devServerHelper$PackagerCommandListener;
    }

    protected Void doInBackground(Void... params) {
        DevServerHelper.access$002(this.this$0, new JSPackagerWebSocketClient(DevServerHelper.access$100(this.this$0), new C12971()));
        DevServerHelper.access$000(this.this$0).connect();
        return null;
    }
}
