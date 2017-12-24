package com.ihealth.communication.cloudmanager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class C2064d implements OnClickListener {
    final /* synthetic */ String f998a;
    final /* synthetic */ C2062b f999b;

    C2064d(C2062b c2062b, String str) {
        this.f999b = c2062b;
        this.f998a = str;
    }

    public void onClick(DialogInterface dialog, int which) {
        new C2065e(this).start();
    }
}
