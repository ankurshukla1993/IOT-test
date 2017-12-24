package com.ihealth.communication.control;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.InsCallback;

public class Bp723Control extends Bp926Control implements DeviceControl {
    public Bp723Control(Context context, BaseComm com, String userName, String mac, String name, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        super(context, com, userName, mac, name, insCallback, mBaseCommCallback);
    }
}
