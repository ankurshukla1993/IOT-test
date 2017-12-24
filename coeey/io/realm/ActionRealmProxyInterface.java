package io.realm;

public interface ActionRealmProxyInterface {
    boolean realmGet$completed();

    long realmGet$completedOn();

    long realmGet$createdOn();

    String realmGet$id();

    String realmGet$interventionId();

    long realmGet$latitude();

    long realmGet$longitude();

    String realmGet$notes();

    String realmGet$parameters();

    String realmGet$patientId();

    String realmGet$patientName();

    String realmGet$postAction();

    String realmGet$resolutionId();

    long realmGet$scheduledOn();

    String realmGet$tenantId();

    long realmGet$timestamp();

    String realmGet$title();

    String realmGet$type();

    long realmGet$updatedOn();

    void realmSet$completed(boolean z);

    void realmSet$completedOn(long j);

    void realmSet$createdOn(long j);

    void realmSet$id(String str);

    void realmSet$interventionId(String str);

    void realmSet$latitude(long j);

    void realmSet$longitude(long j);

    void realmSet$notes(String str);

    void realmSet$parameters(String str);

    void realmSet$patientId(String str);

    void realmSet$patientName(String str);

    void realmSet$postAction(String str);

    void realmSet$resolutionId(String str);

    void realmSet$scheduledOn(long j);

    void realmSet$tenantId(String str);

    void realmSet$timestamp(long j);

    void realmSet$title(String str);

    void realmSet$type(String str);

    void realmSet$updatedOn(long j);
}
