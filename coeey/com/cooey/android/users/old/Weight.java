package com.cooey.android.users.old;

public class Weight {
    private String bmi;
    private String bodyFat;
    private String boneMass;
    private String context;
    private String mood;
    private String muscleMass;
    private String notes;
    private String totalWater;
    public String weight;

    public Weight(String weight, String bmi, String bodyFat, String muscleMass, String totalWater, String boneMass, String context, String mood, String notes) {
        this.weight = weight;
        this.bmi = bmi;
        this.bodyFat = bodyFat;
        this.muscleMass = muscleMass;
        this.totalWater = totalWater;
        this.boneMass = boneMass;
        this.context = context;
        this.mood = mood;
        this.notes = notes;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return this.bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBodyFat() {
        return this.bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    public String getMuscleMass() {
        return this.muscleMass;
    }

    public void setMuscleMass(String muscleMass) {
        this.muscleMass = muscleMass;
    }

    public String getTotalWater() {
        return this.totalWater;
    }

    public void setTotalWater(String totalWater) {
        this.totalWater = totalWater;
    }

    public String getBoneMass() {
        return this.boneMass;
    }

    public void setBoneMass(String boneMass) {
        this.boneMass = boneMass;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMood() {
        return this.mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String toString() {
        return "Weight{weight='" + this.weight + '\'' + ", bmi='" + this.bmi + '\'' + ", bodyFat='" + this.bodyFat + '\'' + ", muscleMass='" + this.muscleMass + '\'' + ", totalWater='" + this.totalWater + '\'' + ", boneMass='" + this.boneMass + '\'' + ", context='" + this.context + '\'' + ", mood='" + this.mood + '\'' + ", notes='" + this.notes + '\'' + '}';
    }
}
