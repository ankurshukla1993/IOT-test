package com.ihealth.androidbg.audio;

import android.support.v4.view.MotionEventCompat;

public class CrcCheck {
    int f244a;
    int f245b;
    int f246c;
    int[] f247d = new int[2];
    private int[] f248e;

    public CrcCheck(int[] crcstr) {
        this.f248e = crcstr;
    }

    public int getCRCValue() {
        this.f246c = 65535;
        this.f247d[0] = this.f246c & 255;
        this.f247d[1] = this.f246c & 255;
        this.f244a = 0;
        while (this.f244a < this.f248e.length) {
            this.f247d[0] = this.f247d[0] ^ this.f248e[this.f244a];
            this.f246c = this.f247d[0] + (this.f247d[1] << 8);
            this.f245b = 0;
            while (this.f245b < 8) {
                if ((this.f246c & 1) == 1) {
                    this.f246c = (this.f246c >> 1) ^ 4129;
                } else {
                    this.f246c >>= 1;
                }
                this.f245b++;
            }
            this.f247d[0] = this.f246c & 255;
            this.f247d[1] = (this.f246c & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            this.f244a++;
        }
        return this.f246c;
    }

    public int[] getCrcstr() {
        return this.f248e;
    }

    public void setCrcstr(int[] crcstr) {
        this.f248e = crcstr;
    }
}
