package io.realm;

public interface FeedBackInputRealmProxyInterface {
    boolean realmGet$caretakerAlert();

    boolean realmGet$guardianAlert();

    String realmGet$label();

    boolean realmGet$patientAlert();

    String realmGet$type();

    String realmGet$value();

    void realmSet$caretakerAlert(boolean z);

    void realmSet$guardianAlert(boolean z);

    void realmSet$label(String str);

    void realmSet$patientAlert(boolean z);

    void realmSet$type(String str);

    void realmSet$value(String str);
}
