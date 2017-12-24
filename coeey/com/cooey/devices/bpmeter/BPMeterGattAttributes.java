package com.cooey.devices.bpmeter;

import java.util.UUID;

class BPMeterGattAttributes {
    static final UUID BPMETERUUID = UUID.fromString("000018f0-0000-1000-8000-00805f9b34fb");
    static final UUID BPMETERWRITEINFOUUID = UUID.fromString("00002af1-0000-1000-8000-00805f9b34fb");
    static final UUID BPMreadinfoCUID = UUID.fromString("00002af0-0000-1000-8000-00805f9b34fb");

    BPMeterGattAttributes() {
    }

    static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[(len / 2)];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
