package com.evernote.android.job;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public abstract class JobCreator$AddJobCreatorReceiver extends BroadcastReceiver {
    protected abstract void addJobCreator(@NonNull Context context, @NonNull JobManager jobManager);

    public final void onReceive(Context context, Intent intent) {
        if (context != null && intent != null && JobCreator.ACTION_ADD_JOB_CREATOR.equals(intent.getAction())) {
            try {
                addJobCreator(context, JobManager.create(context));
            } catch (JobManagerCreateException e) {
            }
        }
    }
}
