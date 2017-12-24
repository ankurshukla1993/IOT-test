package com.facebook.react.devsupport;

import javax.annotation.Nullable;
import okhttp3.ws.WebSocket;

public interface DevServerHelper$PackagerCommandListener {
    void onCaptureHeapCommand();

    void onPackagerReloadCommand();

    void onPokeSamplingProfilerCommand(@Nullable WebSocket webSocket);
}
