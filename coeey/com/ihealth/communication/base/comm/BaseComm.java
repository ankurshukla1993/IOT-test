package com.ihealth.communication.base.comm;

import android.content.Context;
import com.ihealth.communication.base.protocol.BaseCommProtocol;

public interface BaseComm {
    void addCommContinueNotify(String str, BaseCommProtocol baseCommProtocol);

    void addCommNotify(BaseCommProtocol baseCommProtocol);

    void addCommNotify(String str, BaseCommProtocol baseCommProtocol);

    void disconnect();

    void disconnect(String str);

    Context getContext();

    void removeCommNotify(BaseCommProtocol baseCommProtocol);

    void removeCommNotify(String str);

    void sendData(String str, String str2, byte[] bArr);

    void sendData(String str, byte[] bArr);
}
