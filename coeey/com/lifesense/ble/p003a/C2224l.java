package com.lifesense.ble.p003a;

import com.lifesense.ble.DeviceConnectState;
import com.lifesense.ble.ManagerStatus;
import com.lifesense.ble.bean.LsDeviceInfo;
import com.lifesense.ble.bean.WeightUserInfo;
import java.util.List;
import java.util.UUID;

public interface C2224l {
    void mo5582a(DeviceConnectState deviceConnectState);

    void mo5583a(ManagerStatus managerStatus);

    void mo5584a(C2227o c2227o, ae aeVar);

    void mo5585a(LsDeviceInfo lsDeviceInfo);

    void mo5586a(LsDeviceInfo lsDeviceInfo, C2225m c2225m);

    void mo5587a(LsDeviceInfo lsDeviceInfo, String str, UUID uuid, String str2);

    void mo5588a(LsDeviceInfo lsDeviceInfo, byte[] bArr, UUID uuid);

    void mo5589a(WeightUserInfo weightUserInfo);

    void mo5590a(String str, String str2, String str3, C2226n c2226n);

    void mo5591a(List list);

    void mo5592b(LsDeviceInfo lsDeviceInfo);
}
