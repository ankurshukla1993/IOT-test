package com.ihealth.communication.cloud.data;

public class ActivityData {
    private String f572a;
    private int f573b;
    private int f574c;

    public int getCalorie() {
        return this.f574c;
    }

    public int getSteps() {
        return this.f573b;
    }

    public String getTime() {
        return this.f572a;
    }

    public void setCalorie(int calorie) {
        this.f574c = calorie;
    }

    public void setSteps(int steps) {
        this.f573b = steps;
    }

    public void setTime(String time) {
        this.f572a = time;
    }
}
