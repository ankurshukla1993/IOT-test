package com.lifesense.ble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.lifesense.ble.p003a.C2220h;

public class C2240b extends BroadcastReceiver {
    private C2239a f2406a;

    public C2240b(C2239a c2239a) {
        if (c2239a != null) {
            this.f2406a = c2239a;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                case 10:
                    C2220h.m1596a((Object) this, "Bluetooth broadcast receiver,State-off", 3);
                    this.f2406a.mo5596a(10);
                    return;
                case 11:
                    C2220h.m1596a((Object) this, "Bluetooth broadcast receiver,Turning-on", 3);
                    return;
                case 12:
                    C2220h.m1596a((Object) this, "Bluetooth broadcast receiver,State-on", 3);
                    this.f2406a.mo5596a(12);
                    return;
                case 13:
                    C2220h.m1596a((Object) this, "Bluetooth broadcast receiver,Turning-off", 3);
                    this.f2406a.mo5596a(13);
                    return;
                default:
                    return;
            }
        }
    }
}
