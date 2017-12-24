package com.ihealth.communication.cloud.data;

public class SleepData {
    private String f969a;
    private int f970b;

    public int getGrade() {
        return this.f970b;
    }

    public String getTime() {
        return this.f969a;
    }

    public void setGrade(int grade) {
        this.f970b = grade;
    }

    public void setTime(String time) {
        this.f969a = time;
    }
}
