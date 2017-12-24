package com.evernote.android.job;

public interface JobCreator {
    public static final String ACTION_ADD_JOB_CREATOR = "com.evernote.android.job.ADD_JOB_CREATOR";

    Job create(String str);
}
