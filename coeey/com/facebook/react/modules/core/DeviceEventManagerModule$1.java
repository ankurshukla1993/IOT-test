package com.facebook.react.modules.core;

import com.facebook.react.bridge.UiThreadUtil;

class DeviceEventManagerModule$1 implements Runnable {
    final /* synthetic */ DeviceEventManagerModule this$0;
    final /* synthetic */ DefaultHardwareBackBtnHandler val$backBtnHandler;

    DeviceEventManagerModule$1(DeviceEventManagerModule this$0, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.this$0 = this$0;
        this.val$backBtnHandler = defaultHardwareBackBtnHandler;
    }

    public void run() {
        UiThreadUtil.assertOnUiThread();
        this.val$backBtnHandler.invokeDefaultOnBackPressed();
    }
}
