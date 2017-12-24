package com.biz.health.cooey_app.vitals.bp;

public class BPData {
    private String context;
    private String diastolic;
    private String hearRate;
    private String mood;
    private String notes;
    private String systolic;

    public BPData(String diastolic, String systolic, String hearRate, String context, String mood, String notes) {
        this.diastolic = diastolic;
        this.systolic = systolic;
        this.hearRate = hearRate;
        this.context = context;
        this.mood = mood;
        this.notes = notes;
    }

    public String getDiastolic() {
        return this.diastolic;
    }

    public String getSystolic() {
        return this.systolic;
    }

    public String getHearRate() {
        return this.hearRate;
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
}
