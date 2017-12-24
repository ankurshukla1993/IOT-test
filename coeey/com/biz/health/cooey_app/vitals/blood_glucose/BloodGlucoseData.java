package com.biz.health.cooey_app.vitals.blood_glucose;

public class BloodGlucoseData {
    private String context;
    private String glucose;
    private String mood;
    private String notes;

    public BloodGlucoseData(String glucose, String context, String mood, String notes) {
        this.glucose = glucose;
        this.context = context;
        this.mood = mood;
        this.notes = notes;
    }

    public String getGlucose() {
        return this.glucose;
    }

    public String getContext() {
        return this.context;
    }

    public String getMood() {
        return this.mood;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setGlucose(String glucose) {
        this.glucose = glucose;
    }
}
