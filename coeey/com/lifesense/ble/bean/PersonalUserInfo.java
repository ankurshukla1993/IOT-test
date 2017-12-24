package com.lifesense.ble.bean;

public class PersonalUserInfo {
    private int age;
    private double height;
    private SexType sex;
    private double weight;

    public int getAge() {
        return this.age;
    }

    public double getHeight() {
        return this.height;
    }

    public SexType getSex() {
        return this.sex;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public void setSex(SexType sexType) {
        this.sex = sexType;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public String toString() {
        return "PersonalUserInfo [weight=" + this.weight + ", height=" + this.height + ", age=" + this.age + ", sex=" + this.sex + "]";
    }
}
