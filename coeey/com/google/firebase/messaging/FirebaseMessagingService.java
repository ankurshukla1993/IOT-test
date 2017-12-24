package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.iid.zzaa;
import com.google.firebase.iid.zzb;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class FirebaseMessagingService extends zzb {
    private static final Queue<String> zznvo = new ArrayDeque(10);

    static boolean zzak(Bundle bundle) {
        return bundle == null ? false : AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(bundle.getString("google.c.a.e"));
    }

    static void zzq(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleIntent(android.content.Intent r11) {
        /*
        r10 = this;
        r5 = 3;
        r4 = 2;
        r2 = -1;
        r3 = 1;
        r1 = 0;
        r0 = r11.getAction();
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        r0 = "";
    L_0x000d:
        r6 = r0.hashCode();
        switch(r6) {
            case 75300319: goto L_0x003c;
            case 366519424: goto L_0x0032;
            default: goto L_0x0014;
        };
    L_0x0014:
        r0 = r2;
    L_0x0015:
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x017a;
            default: goto L_0x0018;
        };
    L_0x0018:
        r1 = "FirebaseMessaging";
        r2 = "Unknown intent action: ";
        r0 = r11.getAction();
        r0 = java.lang.String.valueOf(r0);
        r3 = r0.length();
        if (r3 == 0) goto L_0x0189;
    L_0x002a:
        r0 = r2.concat(r0);
    L_0x002e:
        android.util.Log.d(r1, r0);
    L_0x0031:
        return;
    L_0x0032:
        r6 = "com.google.android.c2dm.intent.RECEIVE";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x0014;
    L_0x003a:
        r0 = r1;
        goto L_0x0015;
    L_0x003c:
        r6 = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x0014;
    L_0x0044:
        r0 = r3;
        goto L_0x0015;
    L_0x0046:
        r0 = "google.message_id";
        r6 = r11.getStringExtra(r0);
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 == 0) goto L_0x0097;
    L_0x0052:
        r0 = r1;
    L_0x0053:
        if (r0 != 0) goto L_0x007f;
    L_0x0055:
        r0 = "message_type";
        r0 = r11.getStringExtra(r0);
        if (r0 != 0) goto L_0x005f;
    L_0x005d:
        r0 = "gcm";
    L_0x005f:
        r7 = r0.hashCode();
        switch(r7) {
            case -2062414158: goto L_0x00e4;
            case 102161: goto L_0x00db;
            case 814694033: goto L_0x00fa;
            case 814800675: goto L_0x00ef;
            default: goto L_0x0066;
        };
    L_0x0066:
        r1 = r2;
    L_0x0067:
        switch(r1) {
            case 0: goto L_0x0105;
            case 1: goto L_0x0145;
            case 2: goto L_0x014a;
            case 3: goto L_0x0155;
            default: goto L_0x006a;
        };
    L_0x006a:
        r1 = "FirebaseMessaging";
        r2 = "Received message with unknown type: ";
        r0 = java.lang.String.valueOf(r0);
        r3 = r0.length();
        if (r3 == 0) goto L_0x0173;
    L_0x0078:
        r0 = r2.concat(r0);
    L_0x007c:
        android.util.Log.w(r1, r0);
    L_0x007f:
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 != 0) goto L_0x0031;
    L_0x0085:
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = "google.message_id";
        r0.putString(r1, r6);
        r1 = com.google.firebase.iid.zzk.zzet(r10);
        r1.zzh(r4, r0);
        goto L_0x0031;
    L_0x0097:
        r0 = zznvo;
        r0 = r0.contains(r6);
        if (r0 == 0) goto L_0x00c4;
    L_0x009f:
        r0 = "FirebaseMessaging";
        r0 = android.util.Log.isLoggable(r0, r5);
        if (r0 == 0) goto L_0x00bc;
    L_0x00a7:
        r7 = "FirebaseMessaging";
        r8 = "Received duplicate message: ";
        r0 = java.lang.String.valueOf(r6);
        r9 = r0.length();
        if (r9 == 0) goto L_0x00be;
    L_0x00b5:
        r0 = r8.concat(r0);
    L_0x00b9:
        android.util.Log.d(r7, r0);
    L_0x00bc:
        r0 = r3;
        goto L_0x0053;
    L_0x00be:
        r0 = new java.lang.String;
        r0.<init>(r8);
        goto L_0x00b9;
    L_0x00c4:
        r0 = zznvo;
        r0 = r0.size();
        r7 = 10;
        if (r0 < r7) goto L_0x00d3;
    L_0x00ce:
        r0 = zznvo;
        r0.remove();
    L_0x00d3:
        r0 = zznvo;
        r0.add(r6);
        r0 = r1;
        goto L_0x0053;
    L_0x00db:
        r3 = "gcm";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x0066;
    L_0x00e3:
        goto L_0x0067;
    L_0x00e4:
        r1 = "deleted_messages";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0066;
    L_0x00ec:
        r1 = r3;
        goto L_0x0067;
    L_0x00ef:
        r1 = "send_event";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0066;
    L_0x00f7:
        r1 = r4;
        goto L_0x0067;
    L_0x00fa:
        r1 = "send_error";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0066;
    L_0x0102:
        r1 = r5;
        goto L_0x0067;
    L_0x0105:
        r0 = r11.getExtras();
        r0 = zzak(r0);
        if (r0 == 0) goto L_0x0112;
    L_0x010f:
        com.google.firebase.messaging.zzd.zze(r10, r11);
    L_0x0112:
        r0 = r11.getExtras();
        if (r0 != 0) goto L_0x011d;
    L_0x0118:
        r0 = new android.os.Bundle;
        r0.<init>();
    L_0x011d:
        r1 = "android.support.content.wakelockid";
        r0.remove(r1);
        r1 = com.google.firebase.messaging.zza.zzah(r0);
        if (r1 == 0) goto L_0x013b;
    L_0x0128:
        r1 = com.google.firebase.messaging.zza.zzex(r10);
        r1 = r1.zzs(r0);
        if (r1 != 0) goto L_0x007f;
    L_0x0132:
        r1 = zzak(r0);
        if (r1 == 0) goto L_0x013b;
    L_0x0138:
        com.google.firebase.messaging.zzd.zzh(r10, r11);
    L_0x013b:
        r1 = new com.google.firebase.messaging.RemoteMessage;
        r1.<init>(r0);
        r10.onMessageReceived(r1);
        goto L_0x007f;
    L_0x0145:
        r10.onDeletedMessages();
        goto L_0x007f;
    L_0x014a:
        r0 = "google.message_id";
        r0 = r11.getStringExtra(r0);
        r10.onMessageSent(r0);
        goto L_0x007f;
    L_0x0155:
        r0 = "google.message_id";
        r0 = r11.getStringExtra(r0);
        if (r0 != 0) goto L_0x0163;
    L_0x015d:
        r0 = "message_id";
        r0 = r11.getStringExtra(r0);
    L_0x0163:
        r1 = new com.google.firebase.messaging.SendException;
        r2 = "error";
        r2 = r11.getStringExtra(r2);
        r1.<init>(r2);
        r10.onSendError(r0, r1);
        goto L_0x007f;
    L_0x0173:
        r0 = new java.lang.String;
        r0.<init>(r2);
        goto L_0x007c;
    L_0x017a:
        r0 = r11.getExtras();
        r0 = zzak(r0);
        if (r0 == 0) goto L_0x0031;
    L_0x0184:
        com.google.firebase.messaging.zzd.zzg(r10, r11);
        goto L_0x0031;
    L_0x0189:
        r0 = new java.lang.String;
        r0.<init>(r2);
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.handleIntent(android.content.Intent):void");
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exception) {
    }

    protected final Intent zzp(Intent intent) {
        return zzaa.zzchl().zzchm();
    }

    public final boolean zzq(Intent intent) {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (zzak(intent.getExtras())) {
            zzd.zzf(this, intent);
        }
        return true;
    }
}
