package com.ihealth.communication.ins;

public class GenerateKap {
    static {
        System.loadLibrary("iHealth");
    }

    public double getDataFromByteArray(byte[] sample, int sampleRate) {
        return processSampleDataByte(sample, sampleRate);
    }

    public byte[] getKa(String type) {
        return getKey(type);
    }

    public native byte[] getKey(String str);

    public native double processSampleDataByte(byte[] bArr, int i);
}
