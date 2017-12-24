package com.cooey.devices.vo;

public class CooeyStatus {
    private String message = "";
    private int status = -2;

    public int getStatus() {
        return this.status;
    }

    public CooeyStatus setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public CooeyStatus setMessage(String message) {
        this.message = message;
        return this;
    }
}
