package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.systrace.Systrace;

class ModuleHolder$1 implements Runnable {
    final /* synthetic */ SimpleSettableFuture val$future;
    final /* synthetic */ NativeModule val$module;

    ModuleHolder$1(NativeModule nativeModule, SimpleSettableFuture simpleSettableFuture) {
        this.val$module = nativeModule;
        this.val$future = simpleSettableFuture;
    }

    public void run() {
        Systrace.beginSection(0, "initializeOnUiThread");
        try {
            this.val$module.initialize();
            this.val$future.set(null);
        } catch (Exception e) {
            this.val$future.setException(e);
        }
        Systrace.endSection(0);
    }
}
