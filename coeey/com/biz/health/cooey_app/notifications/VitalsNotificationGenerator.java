package com.biz.health.cooey_app.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import java.util.Map;

public class VitalsNotificationGenerator extends NotificationGenerator {
    private final Context context;

    public VitalsNotificationGenerator(Context context) {
        super(context);
        this.context = context;
    }

    public void generate(String titile, String message, Map<String, String> map) {
        Builder mBuilder = new Builder(this.context).setSmallIcon(C0644R.drawable.bp_icon).setContentTitle("My notification").setContentText("Hello World!");
        Intent resultIntent = new Intent(this.context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        mBuilder.setContentIntent(stackBuilder.getPendingIntent(0, 134217728));
        ((NotificationManager) this.context.getSystemService("notification")).notify(98743, mBuilder.build());
    }
}
