package com.ihealth.devices;

import android.os.Message;

public interface IMessageParser {
    IHealthDeviceFragment parse(Message message);
}
