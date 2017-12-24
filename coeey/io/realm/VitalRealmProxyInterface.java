package io.realm;

public interface VitalRealmProxyInterface {
    String realmGet$contextId();

    String realmGet$contextType();

    boolean realmGet$deviceReading();

    String realmGet$deviceType();

    boolean realmGet$isSync();

    String realmGet$parameters();

    String realmGet$platform();

    String realmGet$postAction();

    String realmGet$takenBy();

    long realmGet$takenOn();

    String realmGet$tenantId();

    long realmGet$timestamp();

    String realmGet$userId();

    String realmGet$vitalId();

    String realmGet$vitalType();

    void realmSet$contextId(String str);

    void realmSet$contextType(String str);

    void realmSet$deviceReading(boolean z);

    void realmSet$deviceType(String str);

    void realmSet$isSync(boolean z);

    void realmSet$parameters(String str);

    void realmSet$platform(String str);

    void realmSet$postAction(String str);

    void realmSet$takenBy(String str);

    void realmSet$takenOn(long j);

    void realmSet$tenantId(String str);

    void realmSet$timestamp(long j);

    void realmSet$userId(String str);

    void realmSet$vitalId(String str);

    void realmSet$vitalType(String str);
}
