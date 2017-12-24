package com.thefinestartist.utils.log;

import android.support.annotation.StringRes;
import com.thefinestartist.enums.LogLevel;
import com.thefinestartist.utils.content.ResourcesUtil;

public class Settings {
    private LogLevel logLevel = LogLevel.FULL;
    private boolean showThreadInfo = false;
    private int stackTraceCount = 0;
    private String tag = Settings.class.getSimpleName();

    public Settings(String tag) {
        this.tag = tag;
    }

    public Settings(@StringRes int tagRes) {
        this.tag = ResourcesUtil.getString(tagRes);
    }

    public Settings(Class clazz) {
        this.tag = clazz.getSimpleName();
    }

    public String getTag() {
        return this.tag;
    }

    public Settings setTag(String tag) {
        this.tag = tag;
        if (this == LogUtil.getDefaultSettings()) {
            LogUtil.getInstance().setToDefault();
        }
        return this;
    }

    public Settings setTag(@StringRes int tagRes) {
        this.tag = ResourcesUtil.getString(tagRes);
        if (this == LogUtil.getDefaultSettings()) {
            LogUtil.getInstance().setToDefault();
        }
        return this;
    }

    public Settings setTag(Class clazz) {
        this.tag = clazz.getSimpleName();
        if (this == LogUtil.getDefaultSettings()) {
            LogUtil.getInstance().setToDefault();
        }
        return this;
    }

    public boolean getShowThreadInfo() {
        return this.showThreadInfo;
    }

    public Settings setShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        if (this == LogUtil.getDefaultSettings()) {
            LogUtil.getInstance().setToDefault();
        }
        return this;
    }

    public int getStackTraceCount() {
        return this.stackTraceCount;
    }

    public Settings setStackTraceCount(int stackTraceCount) {
        this.stackTraceCount = stackTraceCount;
        if (this == LogUtil.getDefaultSettings()) {
            LogUtil.getInstance().setToDefault();
        }
        return this;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public Settings setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        if (this == LogUtil.getDefaultSettings()) {
            LogUtil.getInstance().setToDefault();
        }
        return this;
    }
}
