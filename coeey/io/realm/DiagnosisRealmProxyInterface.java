package io.realm;

public interface DiagnosisRealmProxyInterface {
    String realmGet$healthConditions();

    String realmGet$symoptoms();

    void realmSet$healthConditions(String str);

    void realmSet$symoptoms(String str);
}
