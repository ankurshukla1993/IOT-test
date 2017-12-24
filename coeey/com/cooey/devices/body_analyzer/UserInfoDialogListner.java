package com.cooey.devices.body_analyzer;

import com.lifesense.ble.bean.SexType;

public interface UserInfoDialogListner {
    void saveUserInfo(int i, float f, SexType sexType);
}
