package com.facebook.react.jstasks;

import com.facebook.react.bridge.WritableMap;

public class HeadlessJsTaskConfig {
    private final boolean mAllowedInForeground;
    private final WritableMap mData;
    private final String mTaskKey;
    private final long mTimeout;

    public HeadlessJsTaskConfig(String taskKey, WritableMap data) {
        this(taskKey, data, 0, false);
    }

    public HeadlessJsTaskConfig(String taskKey, WritableMap data, long timeout) {
        this(taskKey, data, timeout, false);
    }

    public HeadlessJsTaskConfig(String taskKey, WritableMap data, long timeout, boolean allowedInForeground) {
        this.mTaskKey = taskKey;
        this.mData = data;
        this.mTimeout = timeout;
        this.mAllowedInForeground = allowedInForeground;
    }

    String getTaskKey() {
        return this.mTaskKey;
    }

    WritableMap getData() {
        return this.mData;
    }

    long getTimeout() {
        return this.mTimeout;
    }

    boolean isAllowedInForeground() {
        return this.mAllowedInForeground;
    }
}
