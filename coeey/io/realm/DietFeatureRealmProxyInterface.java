package io.realm;

public interface DietFeatureRealmProxyInterface {
    String realmGet$id();

    String realmGet$tenantId();

    void realmSet$id(String str);

    void realmSet$tenantId(String str);
}
