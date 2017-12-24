package com.facebook.react.devsupport;

import com.facebook.react.common.ShakeDetector$ShakeListener;

class DevSupportManagerImpl$1 implements ShakeDetector$ShakeListener {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$1(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onShake() {
        this.this$0.showDevOptionsDialog();
    }
}
