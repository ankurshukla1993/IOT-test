package com.cooey.devices;

import com.cooey.devices.vo.Device;
import com.cooey.devices.vo.DeviceInfo;
import java.util.List;

public interface DevicePairCallback {
    void onDiscoverUserInfo(List<DeviceInfo> list);

    void onPairResults(Device device, int i);
}
