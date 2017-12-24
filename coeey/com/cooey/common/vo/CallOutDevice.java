package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;

public class CallOutDevice {
    @SerializedName("altitude")
    private String altitude;
    @SerializedName("altitudeUncertainity")
    private String altitudeUncertainity;
    @SerializedName("assistedGPS")
    private String assistedGPS;
    @SerializedName("batteryStatus")
    private String batteryStatus;
    @SerializedName("command")
    private Object command;
    @SerializedName("commandExtension")
    private Object commandExtension;
    @SerializedName("commandId")
    private Object commandId;
    @SerializedName("createdOn")
    private Object createdOn;
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("fullMessage")
    private String fullMessage;
    @SerializedName("gpsSpeed")
    private String gpsSpeed;
    @SerializedName("heading")
    private String heading;
    @SerializedName("horizontalUncertainity")
    private String horizontalUncertainity;
    @SerializedName("horizontalUncertainityAngle")
    private String horizontalUncertainityAngle;
    @SerializedName("id")
    private String id;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("payloadSize")
    private int payloadSize;
    @SerializedName("perpendicularUncertainity")
    private String perpendicularUncertainity;
    @SerializedName("temperature")
    private String temperature;
    @SerializedName("timestamp")
    private long timestamp;

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAltitude() {
        return this.altitude;
    }

    public void setPerpendicularUncertainity(String perpendicularUncertainity) {
        this.perpendicularUncertainity = perpendicularUncertainity;
    }

    public String getPerpendicularUncertainity() {
        return this.perpendicularUncertainity;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return this.heading;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setAltitudeUncertainity(String altitudeUncertainity) {
        this.altitudeUncertainity = altitudeUncertainity;
    }

    public String getAltitudeUncertainity() {
        return this.altitudeUncertainity;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public String getFullMessage() {
        return this.fullMessage;
    }

    public void setGpsSpeed(String gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }

    public String getGpsSpeed() {
        return this.gpsSpeed;
    }

    public void setCommandExtension(Object commandExtension) {
        this.commandExtension = commandExtension;
    }

    public Object getCommandExtension() {
        return this.commandExtension;
    }

    public void setCommandId(Object commandId) {
        this.commandId = commandId;
    }

    public Object getCommandId() {
        return this.commandId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setCreatedOn(Object createdOn) {
        this.createdOn = createdOn;
    }

    public Object getCreatedOn() {
        return this.createdOn;
    }

    public void setCommand(Object command) {
        this.command = command;
    }

    public Object getCommand() {
        return this.command;
    }

    public void setPayloadSize(int payloadSize) {
        this.payloadSize = payloadSize;
    }

    public long getPayloadSize() {
        return (long) this.payloadSize;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getBatteryStatus() {
        return this.batteryStatus;
    }

    public void setHorizontalUncertainityAngle(String horizontalUncertainityAngle) {
        this.horizontalUncertainityAngle = horizontalUncertainityAngle;
    }

    public String getHorizontalUncertainityAngle() {
        return this.horizontalUncertainityAngle;
    }

    public void setHorizontalUncertainity(String horizontalUncertainity) {
        this.horizontalUncertainity = horizontalUncertainity;
    }

    public String getHorizontalUncertainity() {
        return this.horizontalUncertainity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setAssistedGPS(String assistedGPS) {
        this.assistedGPS = assistedGPS;
    }

    public String getAssistedGPS() {
        return this.assistedGPS;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
