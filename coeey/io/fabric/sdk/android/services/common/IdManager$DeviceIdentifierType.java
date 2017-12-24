package io.fabric.sdk.android.services.common;

public enum IdManager$DeviceIdentifierType {
    WIFI_MAC_ADDRESS(1),
    BLUETOOTH_MAC_ADDRESS(2),
    FONT_TOKEN(53),
    ANDROID_ID(100),
    ANDROID_DEVICE_ID(101),
    ANDROID_SERIAL(102),
    ANDROID_ADVERTISING_ID(103);
    
    public final int protobufIndex;

    private IdManager$DeviceIdentifierType(int pbufIndex) {
        this.protobufIndex = pbufIndex;
    }
}
