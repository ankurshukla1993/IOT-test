package com.biz.health.cooey_app.notifications;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class NotificationGenerator {
    private Context context;
    Map<String, NotificationGenerator> notificationGeneratorMap = new HashMap();

    public NotificationGenerator(Context context) {
        this.context = context;
        this.notificationGeneratorMap.put("VITAL_LIMIT", new VitalsNotificationGenerator(context));
    }

    public void generate(String title, String message, Map<String, String> map) {
    }
}
