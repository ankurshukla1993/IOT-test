package com.ihealth.communication.base.comm;

public interface NewDataCallback {
    void haveNewData(int i, int i2, byte[] bArr);

    void haveNewDataUuid(String str, byte[] bArr);
}
