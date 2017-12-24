package com.dnurse.exttestlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;

class C1051a extends BroadcastReceiver {
    final /* synthetic */ DnurseDeviceTest f162a;

    C1051a(DnurseDeviceTest dnurseDeviceTest) {
        this.f162a = dnurseDeviceTest;
    }

    public void onReceive(Context context, Intent intent) {
        Log.i("YinDuTest", "Get event");
        if (!intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            return;
        }
        if (intent.getIntExtra("state", 0) == 0 || intent.getIntExtra("microphone", 0) != 1) {
            if (this.f162a.f122G) {
                if (this.f162a.f146k != 0) {
                    this.f162a.m64c();
                    this.f162a.f131P.postDelayed(this.f162a.f137b, (long) this.f162a.f157v);
                }
                this.f162a.f122G = false;
                Log.i("YinDuTest", "Headset removed");
            }
        } else if (!this.f162a.f122G) {
            Log.i("YinDuTest", "Model: " + Build.MODEL);
            Log.i("YinDuTest", "Ver: " + VERSION.RELEASE);
            this.f162a.f122G = true;
            this.f162a.wakeupDevice();
        }
    }
}
