package com.ihealth.communication.base.ble;

import com.ihealth.communication.base.comm.BaseComm;
import java.util.UUID;

public interface BleComm {
    void Obtain(UUID uuid);

    boolean Obtain(String str, UUID uuid, UUID uuid2);

    boolean connectDevice(String str);

    void destory();

    void disconnect(String str);

    BaseComm getBaseComm();

    void getService(String str, String str2, String str3, String str4, String str5, boolean z);

    void refresh(String str);

    void scan(boolean z);

    boolean writeDataExtra(String str, UUID uuid, UUID uuid2, byte[] bArr);
}
