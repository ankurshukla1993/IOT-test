package com.cooey.devices.five_in_one.data;

public class Temp {
    public int TEMP_INVALID = 0;
    private int status;
    private double temperature;

    public Temp(double temperature, int status) {
        this.temperature = temperature;
        this.status = status;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public int getStatus() {
        return this.status;
    }

    public String toString() {
        return String.format("TEMP: %.1f °C", new Object[]{Double.valueOf(this.temperature)});
    }
}
