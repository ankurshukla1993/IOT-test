package com.lifesense.ble;

import com.lifesense.ble.bean.LsDeviceInfo;

public interface SearchCallback {
    void onSearchResults(LsDeviceInfo lsDeviceInfo);
}
