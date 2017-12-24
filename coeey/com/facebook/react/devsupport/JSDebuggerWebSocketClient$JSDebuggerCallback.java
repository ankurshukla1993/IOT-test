package com.facebook.react.devsupport;

import javax.annotation.Nullable;

public interface JSDebuggerWebSocketClient$JSDebuggerCallback {
    void onFailure(Throwable th);

    void onSuccess(@Nullable String str);
}
