package com.cooey.common.vo;

import io.realm.DeviceAlertRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class DeviceAlert extends RealmObject implements DeviceAlertRealmProxyInterface {
    public String altitude;
    public String altitudeUncertainity;
    public String assistedGPS;
    public String batteryStatus;
    public String command;
    public String commandExtension;
    public String commandId;
    public long createdOn;
    public String deviceId;
    public String fullMessage;
    public String gpsSpeed;
    public String heading;
    public String horizontalUncertainity;
    public String horizontalUncertainityAngle;
    public String id;
    public double latitude;
    public double longitude;
    public long payloadSize;
    public String perpendicularUncertainity;
    public String temperature;
    public long timestamp;

    public String realmGet$altitude() {
        return this.altitude;
    }

    public String realmGet$altitudeUncertainity() {
        return this.altitudeUncertainity;
    }

    public String realmGet$assistedGPS() {
        return this.assistedGPS;
    }

    public String realmGet$batteryStatus() {
        return this.batteryStatus;
    }

    public String realmGet$command() {
        return this.command;
    }

    public String realmGet$commandExtension() {
        return this.commandExtension;
    }

    public String realmGet$commandId() {
        return this.commandId;
    }

    public long realmGet$createdOn() {
        return this.createdOn;
    }

    public String realmGet$deviceId() {
        return this.deviceId;
    }

    public String realmGet$fullMessage() {
        return this.fullMessage;
    }

    public String realmGet$gpsSpeed() {
        return this.gpsSpeed;
    }

    public String realmGet$heading() {
        return this.heading;
    }

    public String realmGet$horizontalUncertainity() {
        return this.horizontalUncertainity;
    }

    public String realmGet$horizontalUncertainityAngle() {
        return this.horizontalUncertainityAngle;
    }

    public String realmGet$id() {
        return this.id;
    }

    public double realmGet$latitude() {
        return this.latitude;
    }

    public double realmGet$longitude() {
        return this.longitude;
    }

    public long realmGet$payloadSize() {
        return this.payloadSize;
    }

    public String realmGet$perpendicularUncertainity() {
        return this.perpendicularUncertainity;
    }

    public String realmGet$temperature() {
        return this.temperature;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public void realmSet$altitude(String str) {
        this.altitude = str;
    }

    public void realmSet$altitudeUncertainity(String str) {
        this.altitudeUncertainity = str;
    }

    public void realmSet$assistedGPS(String str) {
        this.assistedGPS = str;
    }

    public void realmSet$batteryStatus(String str) {
        this.batteryStatus = str;
    }

    public void realmSet$command(String str) {
        this.command = str;
    }

    public void realmSet$commandExtension(String str) {
        this.commandExtension = str;
    }

    public void realmSet$commandId(String str) {
        this.commandId = str;
    }

    public void realmSet$createdOn(long j) {
        this.createdOn = j;
    }

    public void realmSet$deviceId(String str) {
        this.deviceId = str;
    }

    public void realmSet$fullMessage(String str) {
        this.fullMessage = str;
    }

    public void realmSet$gpsSpeed(String str) {
        this.gpsSpeed = str;
    }

    public void realmSet$heading(String str) {
        this.heading = str;
    }

    public void realmSet$horizontalUncertainity(String str) {
        this.horizontalUncertainity = str;
    }

    public void realmSet$horizontalUncertainityAngle(String str) {
        this.horizontalUncertainityAngle = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$latitude(double d) {
        this.latitude = d;
    }

    public void realmSet$longitude(double d) {
        this.longitude = d;
    }

    public void realmSet$payloadSize(long j) {
        this.payloadSize = j;
    }

    public void realmSet$perpendicularUncertainity(String str) {
        this.perpendicularUncertainity = str;
    }

    public void realmSet$temperature(String str) {
        this.temperature = str;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public DeviceAlert() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public long getPayloadSize() {
        return realmGet$payloadSize();
    }

    public void setPayloadSize(long payloadSize) {
        realmSet$payloadSize(payloadSize);
    }

    public String getCommandId() {
        return realmGet$commandId();
    }

    public void setCommandId(String commandId) {
        realmSet$commandId(commandId);
    }

    public String getCommandExtension() {
        return realmGet$commandExtension();
    }

    public void setCommandExtension(String commandExtension) {
        realmSet$commandExtension(commandExtension);
    }

    public String getFullMessage() {
        return realmGet$fullMessage();
    }

    public void setFullMessage(String fullMessage) {
        realmSet$fullMessage(fullMessage);
    }

    public String getDeviceId() {
        return realmGet$deviceId();
    }

    public void setDeviceId(String deviceId) {
        realmSet$deviceId(deviceId);
    }

    public long getCreatedOn() {
        return realmGet$createdOn();
    }

    public void setCreatedOn(long createdOn) {
        realmSet$createdOn(createdOn);
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long timestamp) {
        realmSet$timestamp(timestamp);
    }

    public double getLatitude() {
        return realmGet$latitude();
    }

    public void setLatitude(double latitude) {
        realmSet$latitude(latitude);
    }

    public double getLongitude() {
        return realmGet$longitude();
    }

    public void setLongitude(double longitude) {
        realmSet$longitude(longitude);
    }

    public String getHeading() {
        return realmGet$heading();
    }

    public void setHeading(String heading) {
        realmSet$heading(heading);
    }

    public String getAltitude() {
        return realmGet$altitude();
    }

    public void setAltitude(String altitude) {
        realmSet$altitude(altitude);
    }

    public String getAssistedGPS() {
        return realmGet$assistedGPS();
    }

    public void setAssistedGPS(String assistedGPS) {
        realmSet$assistedGPS(assistedGPS);
    }

    public String getGpsSpeed() {
        return realmGet$gpsSpeed();
    }

    public void setGpsSpeed(String gpsSpeed) {
        realmSet$gpsSpeed(gpsSpeed);
    }

    public String getHorizontalUncertainity() {
        return realmGet$horizontalUncertainity();
    }

    public void setHorizontalUncertainity(String horizontalUncertainity) {
        realmSet$horizontalUncertainity(horizontalUncertainity);
    }

    public String getPerpendicularUncertainity() {
        return realmGet$perpendicularUncertainity();
    }

    public void setPerpendicularUncertainity(String perpendicularUncertainity) {
        realmSet$perpendicularUncertainity(perpendicularUncertainity);
    }

    public String getHorizontalUncertainityAngle() {
        return realmGet$horizontalUncertainityAngle();
    }

    public void setHorizontalUncertainityAngle(String horizontalUncertainityAngle) {
        realmSet$horizontalUncertainityAngle(horizontalUncertainityAngle);
    }

    public String getAltitudeUncertainity() {
        return realmGet$altitudeUncertainity();
    }

    public void setAltitudeUncertainity(String altitudeUncertainity) {
        realmSet$altitudeUncertainity(altitudeUncertainity);
    }

    public String getBatteryStatus() {
        return realmGet$batteryStatus();
    }

    public void setBatteryStatus(String batteryStatus) {
        realmSet$batteryStatus(batteryStatus);
    }

    public String getTemperature() {
        return realmGet$temperature();
    }

    public void setTemperature(String temperature) {
        realmSet$temperature(temperature);
    }

    public String getCommand() {
        return realmGet$command();
    }

    public void setCommand(String command) {
        realmSet$command(command);
    }
}
