package com.ihealth.communication.base.comm;

public interface DataNotify {
    void attach(NewDataCallback newDataCallback);

    void detach(NewDataCallback newDataCallback);

    void haveNewData(int i, int i2, byte[] bArr);
}
