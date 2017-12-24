package com.facebook.react;

import com.facebook.react.cxxbridge.UiThreadUtil;
import com.facebook.react.devsupport.DevServerHelper$PackagerStatusCallback;
import com.facebook.react.modules.debug.DeveloperSettings;

class ReactInstanceManager$3 implements DevServerHelper$PackagerStatusCallback {
    final /* synthetic */ ReactInstanceManager this$0;
    final /* synthetic */ DeveloperSettings val$devSettings;

    ReactInstanceManager$3(ReactInstanceManager this$0, DeveloperSettings developerSettings) {
        this.this$0 = this$0;
        this.val$devSettings = developerSettings;
    }

    public void onPackagerStatusFetched(final boolean packagerIsRunning) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                if (packagerIsRunning) {
                    ReactInstanceManager.access$800(ReactInstanceManager$3.this.this$0).handleReloadJS();
                    return;
                }
                ReactInstanceManager$3.this.val$devSettings.setRemoteJSDebugEnabled(false);
                ReactInstanceManager.access$1300(ReactInstanceManager$3.this.this$0);
            }
        });
    }
}
