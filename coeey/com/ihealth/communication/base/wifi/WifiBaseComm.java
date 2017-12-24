package com.ihealth.communication.base.wifi;

import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;

public interface WifiBaseComm {
    void addCommNotify(NewDataCallback newDataCallback);

    void addCommNotify(String str, BaseCommProtocol baseCommProtocol);

    void disconnect();

    void disconnect(String str);

    void sendData(String str, String str2, byte[] bArr);

    void sendData(String str, byte[] bArr);

    void sendData(byte[] bArr);
}
