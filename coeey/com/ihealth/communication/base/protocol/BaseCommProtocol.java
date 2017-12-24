package com.ihealth.communication.base.protocol;

import com.ihealth.communication.base.comm.NewDataCallback;

public interface BaseCommProtocol {
    void destroy();

    void packageData(String str, byte[] bArr);

    void packageData(byte[] bArr);

    void packageDataAsk(byte[] bArr);

    void packageDataFinish();

    void setInsSet(NewDataCallback newDataCallback);

    void unPackageData(byte[] bArr);

    void unPackageDataUuid(String str, byte[] bArr);
}
