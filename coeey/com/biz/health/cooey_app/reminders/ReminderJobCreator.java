package com.biz.health.cooey_app.reminders;

import android.content.Context;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class ReminderJobCreator implements JobCreator {
    private final Context context;
    String medicineTag = "MEDICINE";

    public ReminderJobCreator(Context context) {
        this.context = context;
    }

    public Job create(String tag) {
        return null;
    }
}
