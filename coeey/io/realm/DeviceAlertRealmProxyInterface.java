package io.realm;

public interface DeviceAlertRealmProxyInterface {
    String realmGet$altitude();

    String realmGet$altitudeUncertainity();

    String realmGet$assistedGPS();

    String realmGet$batteryStatus();

    String realmGet$command();

    String realmGet$commandExtension();

    String realmGet$commandId();

    long realmGet$createdOn();

    String realmGet$deviceId();

    String realmGet$fullMessage();

    String realmGet$gpsSpeed();

    String realmGet$heading();

    String realmGet$horizontalUncertainity();

    String realmGet$horizontalUncertainityAngle();

    String realmGet$id();

    double realmGet$latitude();

    double realmGet$longitude();

    long realmGet$payloadSize();

    String realmGet$perpendicularUncertainity();

    String realmGet$temperature();

    long realmGet$timestamp();

    void realmSet$altitude(String str);

    void realmSet$altitudeUncertainity(String str);

    void realmSet$assistedGPS(String str);

    void realmSet$batteryStatus(String str);

    void realmSet$command(String str);

    void realmSet$commandExtension(String str);

    void realmSet$commandId(String str);

    void realmSet$createdOn(long j);

    void realmSet$deviceId(String str);

    void realmSet$fullMessage(String str);

    void realmSet$gpsSpeed(String str);

    void realmSet$heading(String str);

    void realmSet$horizontalUncertainity(String str);

    void realmSet$horizontalUncertainityAngle(String str);

    void realmSet$id(String str);

    void realmSet$latitude(double d);

    void realmSet$longitude(double d);

    void realmSet$payloadSize(long j);

    void realmSet$perpendicularUncertainity(String str);

    void realmSet$temperature(String str);

    void realmSet$timestamp(long j);
}
