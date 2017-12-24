package com.ihealth.communication.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirmWare {
    private byte[] f2084a;
    private String f2085b;
    private String f2086c;
    private long f2087d;
    private byte[] f2088e;
    private byte[] f2089f;
    private int f2090g;
    private List f2091h;
    private int f2092i;
    private List f2093j;
    private List f2094k = new ArrayList();

    public class FirmwareInfo {
        private byte[] f2082a;
        private byte[] f2083b;

        public byte[] getFwSizeN() {
            return this.f2083b;
        }

        public byte[] getFwVerN() {
            return this.f2082a;
        }

        public void setFwSizeN(byte[] fwSizeN) {
            this.f2083b = fwSizeN;
        }

        public void setFwVerN(byte[] fwVerN) {
            this.f2082a = fwVerN;
        }

        public String toString() {
            return "FirmwareInfo{fwVerN=" + Arrays.toString(this.f2082a) + ", fwSizeN=" + Arrays.toString(this.f2083b) + '}';
        }
    }

    public byte[] getBlockNum() {
        return this.f2084a;
    }

    public long getCrcCode() {
        return this.f2087d;
    }

    public List getCrcList() {
        return this.f2094k;
    }

    public int getFwAmount() {
        return this.f2092i;
    }

    public List getFwList() {
        return this.f2093j;
    }

    public byte[] getFwSize() {
        return this.f2089f;
    }

    public byte[] getFwVer() {
        return this.f2088e;
    }

    public List getHwVerList() {
        return this.f2091h;
    }

    public String getModel() {
        return this.f2085b;
    }

    public int getSupportHwAmount() {
        return this.f2090g;
    }

    public String getType() {
        return this.f2086c;
    }

    public void setBlockNum(byte[] blockNum) {
        this.f2084a = blockNum;
    }

    public void setCrcCode(long crcCode) {
        this.f2087d = crcCode;
    }

    public void setCrcList(List crcList) {
        this.f2094k = crcList;
    }

    public void setFwAmount(int fwAmount) {
        this.f2092i = fwAmount;
    }

    public void setFwList(List fwList) {
        this.f2093j = fwList;
    }

    public void setFwSize(byte[] fwSize) {
        this.f2089f = fwSize;
    }

    public void setFwVer(byte[] fwVer) {
        this.f2088e = fwVer;
    }

    public void setHwVerList(List hwVerList) {
        this.f2091h = hwVerList;
    }

    public void setModel(String model) {
        this.f2085b = model;
    }

    public void setSupportHwAmount(int supportHwAmount) {
        this.f2090g = supportHwAmount;
    }

    public void setType(String type) {
        this.f2086c = type;
    }

    public String toString() {
        return "FirmWare{model='" + this.f2085b + '\'' + ", type='" + this.f2086c + '\'' + ", crcCode=" + this.f2087d + ", fwVer=" + Arrays.toString(this.f2088e) + ", fwSize=" + Arrays.toString(this.f2089f) + ", supportHwAmount=" + this.f2090g + ", hwVerList=" + this.f2091h + ", fwAmount=" + this.f2092i + ", fwList=" + this.f2093j + ", crcList=" + this.f2094k + '}';
    }
}
