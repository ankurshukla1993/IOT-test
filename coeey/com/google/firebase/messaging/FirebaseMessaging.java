package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzw;
import java.util.regex.Pattern;

public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zznvl = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zznvm;
    private final FirebaseInstanceId zznvn;

    private FirebaseMessaging(FirebaseInstanceId firebaseInstanceId) {
        this.zznvn = firebaseInstanceId;
    }

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            if (zznvm == null) {
                zznvm = new FirebaseMessaging(FirebaseInstanceId.getInstance());
            }
            firebaseMessaging = zznvm;
        }
        return firebaseMessaging;
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        if (zzw.zzeu(applicationContext)) {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            zzw.zzc(applicationContext, intent);
            intent.setPackage("com.google.android.gms");
            intent.putExtras(remoteMessage.mBundle);
            applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
        Log.e("FirebaseMessaging", "Google Play services package is missing. Impossible to send message");
    }

    public void subscribeToTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            Object substring = str.substring(8);
        }
        if (substring == null || !zznvl.matcher(substring).matches()) {
            String str2 = "[a-zA-Z0-9-_.~%]{1,900}";
            throw new IllegalArgumentException(new StringBuilder((String.valueOf(substring).length() + 55) + String.valueOf(str2).length()).append("Invalid topic name: ").append(substring).append(" does not match the allowed format ").append(str2).toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zznvn;
        String valueOf = String.valueOf("S!");
        String valueOf2 = String.valueOf(substring);
        firebaseInstanceId.zzqp(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public void unsubscribeFromTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            Object substring = str.substring(8);
        }
        if (substring == null || !zznvl.matcher(substring).matches()) {
            String str2 = "[a-zA-Z0-9-_.~%]{1,900}";
            throw new IllegalArgumentException(new StringBuilder((String.valueOf(substring).length() + 55) + String.valueOf(str2).length()).append("Invalid topic name: ").append(substring).append(" does not match the allowed format ").append(str2).toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zznvn;
        String valueOf = String.valueOf("U!");
        String valueOf2 = String.valueOf(substring);
        firebaseInstanceId.zzqp(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }
}
