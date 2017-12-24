package com.biz.health.cooey_app.medicine;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.common.realm_store.MedicineStore;
import com.cooey.common.vo.Medicine;
import io.realm.Realm;
import java.util.Random;

public class MedicineReminderBroadcastReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null && intent.getStringExtra("REMINDER_TYPE") != null && intent.getStringExtra("REMINDER_TYPE").contentEquals("MEDICINE")) {
            Medicine medicine = MedicineStore.getInstance(Realm.getDefaultInstance()).getMedicineById(intent.getStringExtra("MedicineId"));
            if (medicine != null) {
                generateMedicineNotification(context, medicine.getName());
            } else {
                generateMedicineNotification(context, "");
            }
        }
    }

    public void generateMedicineNotification(Context context, String medicineName) {
        PendingIntent pIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, new Intent(context.getApplicationContext(), MainActivity.class), 0);
        Notification notification = new Builder(context.getApplicationContext()).setContentTitle(medicineName).setContentText("Time to take your medicine..").setSmallIcon(C0644R.drawable.icon).setContentIntent(pIntent).setSound(RingtoneManager.getDefaultUri(2)).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notification.flags |= 16;
        notificationManager.notify(new Random().nextInt(8999) + 1000, notification);
    }
}
