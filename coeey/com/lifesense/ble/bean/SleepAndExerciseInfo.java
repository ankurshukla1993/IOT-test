package com.lifesense.ble.bean;

public class SleepAndExerciseInfo {
    private long endTimeMillis;
    private double examount;
    private int level;
    private long startTimeMillis;
    private int type;

    public long getEndTimeMillis() {
        return this.endTimeMillis;
    }

    public double getExamount() {
        return this.examount;
    }

    public int getLevel() {
        return this.level;
    }

    public long getStartTimeMillis() {
        return this.startTimeMillis;
    }

    public int getType() {
        return this.type;
    }

    public void setEndTimeMillis(long j) {
        this.endTimeMillis = j;
    }

    public void setExamount(double d) {
        this.examount = d;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public void setStartTimeMillis(long j) {
        this.startTimeMillis = j;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "SleepAndExerciseInfo [startTimeMillis=" + this.startTimeMillis + ", endTimeMillis=" + this.endTimeMillis + ", type=" + this.type + ", level=" + this.level + ", examount=" + this.examount + "]";
    }
}
