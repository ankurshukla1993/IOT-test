package com.ihealth.communication.ins;

public class XmitterBean {
    private String f1916a;
    private int f1917b;
    private int f1918c;
    private byte[] f1919d;
    private int f1920e;
    private int f1921f;
    private char[] f1922g;
    private char[] f1923h;

    public char[] getMac() {
        return this.f1922g;
    }

    public int getPassCRC() {
        return this.f1921f;
    }

    public int getPassLen() {
        return this.f1920e;
    }

    public byte[] getPassphrase() {
        return this.f1919d;
    }

    public char[] getPreamble() {
        return this.f1923h;
    }

    public String getSsid() {
        return this.f1916a;
    }

    public int getSsidCRC() {
        return this.f1918c;
    }

    public int getSsidLen() {
        return this.f1917b;
    }

    public void setMac(char[] mac) {
        this.f1922g = mac;
    }

    public void setPassCRC(int passCRC) {
        this.f1921f = passCRC;
    }

    public void setPassLen(int passLen) {
        this.f1920e = passLen;
    }

    public void setPassphrase(byte[] passphrase) {
        this.f1919d = passphrase;
    }

    public void setPreamble(char[] preamble) {
        this.f1923h = preamble;
    }

    public void setSsid(String ssid) {
        this.f1916a = ssid;
    }

    public void setSsidCRC(int ssidCRC) {
        this.f1918c = ssidCRC;
    }

    public void setSsidLen(int ssidLen) {
        this.f1917b = ssidLen;
    }
}
