package com.facebook.react.devsupport;

import android.content.Intent;

class DevSupportManagerImpl$14 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$14(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        Intent intent = new Intent(DevSupportManagerImpl.access$600(this.this$0), DevSettingsActivity.class);
        intent.setFlags(268435456);
        DevSupportManagerImpl.access$600(this.this$0).startActivity(intent);
    }
}
