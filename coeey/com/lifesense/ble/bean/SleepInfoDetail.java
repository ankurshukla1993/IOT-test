package com.lifesense.ble.bean;

public class SleepInfoDetail {
    private int avgLevel;
    private int deepSleep;
    private long endTime;
    private int somnolence;
    private String startSleepTime = "";
    private long startTime;
    private int timeWakeUp;
    private String wakeUpTime = "";

    public int getAvgLevel() {
        return this.avgLevel;
    }

    public int getDeepSleep() {
        return this.deepSleep;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public int getSomnolence() {
        return this.somnolence;
    }

    public String getStartSleepTime() {
        return this.startSleepTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int getTimeWakeUp() {
        return this.timeWakeUp;
    }

    public String getWakeUpTime() {
        return this.wakeUpTime;
    }

    public void setAvgLevel(int i) {
        this.avgLevel = i;
    }

    public void setDeepSleep(int i) {
        this.deepSleep = i;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setSomnolence(int i) {
        this.somnolence = i;
    }

    public void setStartSleepTime(String str) {
        this.startSleepTime = str;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setTimeWakeUp(int i) {
        this.timeWakeUp = i;
    }

    public void setWakeUpTime(String str) {
        this.wakeUpTime = str;
    }

    public String toString() {
        return "SleepInfoDetail [startSleepTime=" + this.startSleepTime + ", wakeUpTime=" + this.wakeUpTime + ", deepSleep=" + this.deepSleep + ", somnolence=" + this.somnolence + ", timeWakeUp=" + this.timeWakeUp + ", avgLevel=" + this.avgLevel + "]";
    }
}
