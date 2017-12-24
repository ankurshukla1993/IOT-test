package com.facebook.react;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

class HeadlessJsTaskService$1 implements ReactInstanceManager$ReactInstanceEventListener {
    final /* synthetic */ HeadlessJsTaskService this$0;
    final /* synthetic */ ReactInstanceManager val$reactInstanceManager;
    final /* synthetic */ HeadlessJsTaskConfig val$taskConfig;

    HeadlessJsTaskService$1(HeadlessJsTaskService this$0, HeadlessJsTaskConfig headlessJsTaskConfig, ReactInstanceManager reactInstanceManager) {
        this.this$0 = this$0;
        this.val$taskConfig = headlessJsTaskConfig;
        this.val$reactInstanceManager = reactInstanceManager;
    }

    public void onReactContextInitialized(ReactContext reactContext) {
        HeadlessJsTaskService.access$000(this.this$0, reactContext, this.val$taskConfig);
        this.val$reactInstanceManager.removeReactInstanceEventListener(this);
    }
}
