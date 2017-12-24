package com.ihealth.communication.cloud.data;

public class Summary_WorkOut {
    private int f976a;
    private String f977b;
    private int f978c;
    private int f979d;
    private float f980e;
    private int f981f;

    public int getCalorie() {
        return this.f981f;
    }

    public float getDistance() {
        return this.f980e;
    }

    public int getId() {
        return this.f976a;
    }

    public String getOverTime() {
        return this.f977b;
    }

    public int getSteps() {
        return this.f979d;
    }

    public int getUseTime() {
        return this.f978c;
    }

    public void setCalorie(int calorie) {
        this.f981f = calorie;
    }

    public void setDistance(float distance) {
        this.f980e = distance;
    }

    public void setId(int id) {
        this.f976a = id;
    }

    public void setOverTime(String overTime) {
        this.f977b = overTime;
    }

    public void setSteps(int steps) {
        this.f979d = steps;
    }

    public void setUseTime(int useTime) {
        this.f978c = useTime;
    }
}
