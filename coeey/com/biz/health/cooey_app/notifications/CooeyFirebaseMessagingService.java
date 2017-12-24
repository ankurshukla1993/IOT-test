package com.biz.health.cooey_app.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.biz.health.cooey_app.NewMessagesActivity;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.video_call.CallRingActivity;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.Vital;
import com.google.common.primitives.Ints;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import retrofit2.Response;

public class CooeyFirebaseMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            if (remoteMessage.getNotification() == null) {
                processNotificationData(remoteMessage.getData());
                return;
            }
            Response<Vital> response;
            Map<String, String> data = remoteMessage.getData();
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            Log.d("Notification Received", message);
            Session session = new PreferenceStore().getSession(getApplicationContext());
            if (((String) data.get("TYPE")).contentEquals("VITAL_LIMIT")) {
                try {
                    response = new ApiClient(getApplicationContext(), "http://api.cooey.co.in/ehealth/").getVitalsService().getVital(session.getId(), (String) data.get("VITAL_ID")).execute();
                    if (!(response == null || response.body() == null)) {
                        showVitalLimitNotification(title, message, (Vital) response.body());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (((String) data.get("TYPE")).contentEquals(CTConstants.VITAL_TYPE)) {
                try {
                    response = new ApiClient(getApplicationContext(), "http://api.cooey.co.in/ehealth/").getVitalsService().getVital(session.getId(), (String) data.get("VITAL_ID")).execute();
                    if (!(response == null || response.body() == null)) {
                        showVitalNotification(title, message, (Vital) response.body());
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (((String) data.get("TYPE")).contentEquals(CTConstants.EVENT)) {
                try {
                    Response<Activity> response2 = new ApiClient(getApplicationContext(), "http://api.cooey.co.in/ehealth/").getActivitiesService().getActivity(session.getId(), (String) data.get("EVENT_ID")).execute();
                    if (response2 != null && response2.body() != null) {
                        showEventNotification(title, message, (Activity) response2.body());
                    }
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processNotificationData(Map<String, String> data) {
        Intent intent;
        if (((String) data.get("TYPE")).contentEquals("VIDEO_CALL")) {
            String name = (String) data.get("name");
            String url = (String) data.get("url");
            String userId = (String) data.get("userId");
            intent = new Intent(this, CallRingActivity.class);
            intent.putExtra("url", url);
            intent.putExtra("name", name);
            intent.putExtra("userId", userId);
            intent.addFlags(268435456);
            intent.addFlags(67108864);
            intent.addFlags(131072);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            getApplication().startActivity(intent);
        } else if (((String) data.get("TYPE")).contentEquals("MESSAGE")) {
            String fromUserId = (String) data.get("fromUserId");
            String toUserId = (String) data.get("toUserId");
            intent = new Intent(this, NewMessagesActivity.class);
            intent.putExtra("senderId", fromUserId);
            intent.putExtra("receiverId", toUserId);
            intent.putExtra("authToken", new PreferenceStore().getSession(this).getId());
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, Ints.MAX_POWER_OF_TWO);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(2);
            Builder notificationBuilder = new Builder(this);
            notificationBuilder.setSmallIcon(C0644R.drawable.send_icon).setContentTitle("You got a message").setContentText("Tap to see the message").setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);
            ((NotificationManager) getSystemService("notification")).notify(0, notificationBuilder.build());
        }
    }

    private void showVitalLimitNotification(String title, String message, Vital vital) {
        Builder mBuilder = new Builder(this);
        if (vital.getVitalType().contentEquals("BLOOD_PRESSURE")) {
            mBuilder.setSmallIcon(C0644R.drawable.heart);
        }
        if (vital.getVitalType().contentEquals("BLOOD_GLUCOSE")) {
            mBuilder.setSmallIcon(C0644R.drawable.blood);
        }
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setColor(ContextCompat.getColor(getApplicationContext(), C0644R.color.appdark));
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        mBuilder.setContentIntent(stackBuilder.getPendingIntent(0, 134217728));
        ((NotificationManager) getSystemService("notification")).notify(new Random(3000).nextInt(), mBuilder.build());
    }

    private void showVitalNotification(String title, String message, Vital vital) {
        Builder mBuilder = new Builder(this);
        if (vital.getVitalType().contentEquals("BLOOD_PRESSURE")) {
            mBuilder.setSmallIcon(C0644R.drawable.heart);
        }
        if (vital.getVitalType().contentEquals("BLOOD_GLUCOSE")) {
            mBuilder.setSmallIcon(C0644R.drawable.blood);
        }
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setColor(ContextCompat.getColor(getApplicationContext(), C0644R.color.appdark));
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        mBuilder.setContentIntent(stackBuilder.getPendingIntent(0, 134217728));
        ((NotificationManager) getSystemService("notification")).notify(new Random(3000).nextInt(), mBuilder.build());
    }

    private void showEventNotification(String title, String message, Activity event) {
        Builder mBuilder = new Builder(this);
        mBuilder.setSmallIcon(C0644R.drawable.time);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setColor(ContextCompat.getColor(getApplicationContext(), C0644R.color.appdark));
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        mBuilder.setContentIntent(stackBuilder.getPendingIntent(0, 134217728));
        ((NotificationManager) getSystemService("notification")).notify(new Random(3000).nextInt(), mBuilder.build());
    }
}
