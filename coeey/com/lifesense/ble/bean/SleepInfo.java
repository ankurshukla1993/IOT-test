package com.lifesense.ble.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class SleepInfo {
    private int level;
    private int sleepStatus;
    private long startTimeMillis;

    public static SleepInfo JsonObjecttoDBStepInfo(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SleepInfo sleepInfo = new SleepInfo();
        if (!jSONObject.isNull("startTimeMillis")) {
            sleepInfo.startTimeMillis = jSONObject.optLong("startTimeMillis");
        }
        if (!jSONObject.isNull("level")) {
            sleepInfo.level = jSONObject.optInt("level");
        }
        if (jSONObject.isNull("sleepStatus")) {
            return sleepInfo;
        }
        sleepInfo.sleepStatus = jSONObject.optInt("sleepStatus");
        return sleepInfo;
    }

    public long getEndTimeMillis() {
        return 300000 + this.startTimeMillis;
    }

    public int getLevel() {
        return this.level;
    }

    public int getSleepStatus() {
        return this.sleepStatus;
    }

    public long getStartTimeMillis() {
        return this.startTimeMillis;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setSleepStatus(int i) {
        this.sleepStatus = i;
    }

    public void setStartTimeMillis(long j) {
        this.startTimeMillis = j;
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("startTimeMillis", this.startTimeMillis);
            jSONObject.put("level", this.level);
            jSONObject.put("sleepStatus", this.sleepStatus);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "SleepAndExerciseInfo [startTimeMillis=" + this.startTimeMillis + ", level=" + this.level + "]";
    }
}
