package com.ihealth.communication.cloudmanager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class C2063c implements OnCancelListener {
    final /* synthetic */ C2062b f997a;

    C2063c(C2062b c2062b) {
        this.f997a = c2062b;
    }

    public void onCancel(DialogInterface dialog) {
        this.f997a.f996e.f1004b.onUserStatus(this.f997a.f992a, 7);
    }
}
