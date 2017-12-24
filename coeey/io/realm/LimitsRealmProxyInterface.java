package io.realm;

public interface LimitsRealmProxyInterface {
    int realmGet$higherLimit();

    int realmGet$lowerLimit();

    String realmGet$tenantId();

    void realmSet$higherLimit(int i);

    void realmSet$lowerLimit(int i);

    void realmSet$tenantId(String str);
}
