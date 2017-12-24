package com.ihealth.communication.utils;

import android.support.v4.view.MotionEventCompat;

public class CrcCheck {
    int f2071a;
    int f2072b;
    int f2073c;
    int[] f2074d = new int[2];
    private int[] f2075e;

    public CrcCheck(int[] crcstr) {
        this.f2075e = crcstr;
    }

    public int getCRCValue() {
        this.f2073c = 65535;
        this.f2074d[0] = this.f2073c & 255;
        this.f2074d[1] = this.f2073c & 255;
        this.f2071a = 0;
        while (this.f2071a < this.f2075e.length) {
            this.f2074d[0] = this.f2074d[0] ^ this.f2075e[this.f2071a];
            this.f2073c = this.f2074d[0] + (this.f2074d[1] << 8);
            this.f2072b = 0;
            while (this.f2072b < 8) {
                if ((this.f2073c & 1) == 1) {
                    this.f2073c = (this.f2073c >> 1) ^ 4129;
                } else {
                    this.f2073c >>= 1;
                }
                this.f2072b++;
            }
            this.f2074d[0] = this.f2073c & 255;
            this.f2074d[1] = (this.f2073c & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            this.f2071a++;
        }
        return this.f2073c;
    }

    public int[] getCrcstr() {
        return this.f2075e;
    }

    public void setCrcstr(int[] crcstr) {
        this.f2075e = crcstr;
    }
}
