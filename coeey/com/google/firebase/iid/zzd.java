package com.google.firebase.iid;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzd {
    final Intent intent;
    private final PendingResult zziby;
    private boolean zzibz = false;
    private final ScheduledFuture<?> zzica;

    zzd(Intent intent, PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.intent = intent;
        this.zziby = pendingResult;
        this.zzica = scheduledExecutorService.schedule(new zze(this, intent), 9500, TimeUnit.MILLISECONDS);
    }

    final synchronized void finish() {
        if (!this.zzibz) {
            this.zziby.finish();
            this.zzica.cancel(false);
            this.zzibz = true;
        }
    }
}
