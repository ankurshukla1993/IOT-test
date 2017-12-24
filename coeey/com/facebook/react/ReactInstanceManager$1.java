package com.facebook.react;

import com.facebook.react.bridge.JavaJSExecutor.Factory;
import com.facebook.react.devsupport.ReactInstanceDevCommandsHandler;

class ReactInstanceManager$1 implements ReactInstanceDevCommandsHandler {
    final /* synthetic */ ReactInstanceManager this$0;

    ReactInstanceManager$1(ReactInstanceManager this$0) {
        this.this$0 = this$0;
    }

    public void onReloadWithJSDebugger(Factory jsExecutorFactory) {
        ReactInstanceManager.access$000(this.this$0, jsExecutorFactory);
    }

    public void onJSBundleLoadedFromServer() {
        ReactInstanceManager.access$100(this.this$0);
    }

    public void toggleElementInspector() {
        ReactInstanceManager.access$200(this.this$0);
    }
}
